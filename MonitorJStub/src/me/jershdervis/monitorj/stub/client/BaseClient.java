package me.jershdervis.monitorj.stub.client;

import lombok.Getter;
import me.jershdervis.monitorj.stub.MonitorJStub;
import wiki.mtf.utils.FuckingPrintln;
import wiki.mtf.utils.SoutUtils;

import java.io.*;
import java.net.*;

/**
 * Created by Josh on 18/06/2015.
 */
public class BaseClient implements Runnable {


    private final long RECONNECT_DELAY = 10000L;
    private final String address;
    private final int port;
    /**
     * -- GETTER --
     *  Gets the Socket Object the the current server
     *
     * @return
     */
    @Getter
    private Socket serverSocketConnection;
    /**
     * -- GETTER --
     *  Gets the DataOutputStream Object of the current server
     *
     * @return
     */
    @Getter
    private DataOutputStream dataOutputStream;
    /**
     * -- GETTER --
     *  Gets the DataInputStream Object of the current server
     *
     * @return
     */
    @Getter
    private DataInputStream dataInputStream;
    private String exc = "";

    public BaseClient(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            reconnect:
            /**
             * Establish connection to server
             * If fails it will wait the delayed time and rerun through here
             */
            try {
                this.serverSocketConnection = this.connect(this.address, this.port);
                this.dataOutputStream = new DataOutputStream(this.serverSocketConnection.getOutputStream());
                this.dataInputStream = new DataInputStream(this.serverSocketConnection.getInputStream());
                FuckingPrintln.out("Connection Success!");
            } catch (IOException e) {
                exc = e.toString();
                if (exc.toLowerCase().contains("refused")) {
                    FuckingPrintln.out(new SoutUtils().yellowBGStr(exc));
                }
            }

            /**
             * Connection has been established successfully
             * Call the Connect Event
             */
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
                            FuckingPrintln.out(new SoutUtils().yellowBGStr(exc));
                        }
                    }

                    /**
                     * Calls the disconnect event as well as starting the reconnection process.
                     */
                    MonitorJStub.getInstance().EVENT_DISCONNECT.call(this);
                    this.delayReconnection();
                    break;
                }
            } catch (NullPointerException ignored) {

            }

            this.delayReconnection();
            continue;

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
    private String addressToIp(String address) throws MalformedURLException, UnknownHostException {
        boolean containsProtocol = address.toLowerCase().contains("http://");

        String externalIp = containsProtocol ? InetAddress.getByName(new URL(this.address).getHost()).getHostAddress() : InetAddress.getByName(new URL("http://" + this.address).getHost()).getHostAddress();

        try {
            String myExternalIp = new BufferedReader(new InputStreamReader(new URL("http://checkip.amazonaws.com/").openStream())).readLine();
            if (externalIp.equals(myExternalIp)) return "127.0.0.1";
        } catch (IOException e) {

        }
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
     * @param address
     * @param port
     * @return
     * @throws IOException
     */
    private Socket connect(String address, int port) throws IOException {
        address = this.addressToIp(address);
        FuckingPrintln.out("Attempting to connect to " + address + ":" + port);
        return new Socket(address, port);
    }

}
