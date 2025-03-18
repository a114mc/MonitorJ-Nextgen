package me.jershdervis.monitorj;

import lombok.Getter;
import me.jershdervis.monitorj.command.CommandManager;
import me.jershdervis.monitorj.eventapi.events.*;
import me.jershdervis.monitorj.server.PacketTaskManager;
import me.jershdervis.monitorj.server.ServerManager;
import me.jershdervis.monitorj.util.FileManager;
import me.jershdervis.monitorj.util.GeoIP;

/**
 * Created by Josh on 18/06/2015.
 */


public class MonitorJ {
    /**
     * Initialized within this classes constructor
     * <p>-- GETTER --</p>
     *  Gets the current classes instance
     */
    @Getter
    private static MonitorJ instance;

    /**
     * Program Event initialization
     */
    public final EventUILoaded EVENT_UI_LOADED = new EventUILoaded();
    public final EventClientConnect EVENT_CLIENT_CONNECT = new EventClientConnect();
    public final EventClientDisconnect EVENT_CLIENT_DISCONNECT = new EventClientDisconnect();
    public final EventReceivePacket EVENT_RECEIVE_PACKET = new EventReceivePacket();
    public final EventReceiveDesktopImage EVENT_RECEIVE_DESKTOP_IMAGE = new EventReceiveDesktopImage();


    /**
     * <p>-- GETTER --</p>
     *
     * Gets the CommandManager
     */
    @Getter
    private final CommandManager commandManager;

    /**
     * <p>-- GETTER --</p>
     *
     *  Gets the FileManager class
     */
    @Getter
    private final FileManager fileManager;
    /**
     * <p>-- GETTER --</p>
     *
     *  Returns the GeoIP class
     */
    @Getter
    private final GeoIP geoIP;

    /**
     * Initialized within this classes constructor
     * <p>-- GETTER --</p>
     * 
     *  Gets the PacketTaskManager class
     */
    @Getter
    private final PacketTaskManager packetTaskManager;
    /**
     * <p>-- GETTER --</p>
     * 
     *  Gets the ServerManager class
     */
    @Getter
    private final ServerManager serverManager;



    /**
     * Initializes:
     * <code>
     * <p>MonitorJ instance</p>
     * <p>PacketTaskManager packetTaskManager</p>
     * <p>ServerManager serverManager</p>
     * <p>UserInterface ui</p>
     * </code>
     */
    public MonitorJ() {
        instance = this;

        this.commandManager = new CommandManager();
        this.fileManager = new FileManager();
        this.geoIP = new GeoIP();
        this.packetTaskManager = new PacketTaskManager();
        this.serverManager = new ServerManager();

        this.commandManager.start();

    }
}
