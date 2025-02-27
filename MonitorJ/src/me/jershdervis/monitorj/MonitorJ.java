package me.jershdervis.monitorj;

import lombok.Getter;
import me.jershdervis.monitorj.eventapi.events.*;
import me.jershdervis.monitorj.server.PacketTaskManager;
import me.jershdervis.monitorj.server.ServerManager;
import me.jershdervis.monitorj.ui.UserInterface;
import me.jershdervis.monitorj.util.FileManager;
import me.jershdervis.monitorj.util.GeoIP;

import javax.swing.*;

/**
 * Created by Josh on 18/06/2015.
 */


public class MonitorJ {



    /**
     * Initialized within this classes constructor
     * -- GETTER --
     *  Gets the current classes instance
     *
     * @return

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
     * Initialized within this classes constructor
     * -- GETTER --
     *  Gets the PacketTaskManager class
     *
     * @return

     */
    @Getter
    private final PacketTaskManager packetTaskManager;
    /**
     * -- GETTER --
     *  Gets the ServerManager class
     *
     * @return
     */
    @Getter
    private final ServerManager serverManager;
    /**
     * -- GETTER --
     *  Gets the FileManager class
     *
     * @return
     */
    @Getter
    private final FileManager fileManager;
//    /**
//     * -- GETTER --
//     *  Returns the UserInterface class
//     *
//     * @return
//     */
//    @Getter
//    private final UserInterface ui;
    /**
     * -- GETTER --
     *  Returns the GeoIP class
     *
     * @return
     */
    @Getter
    private final GeoIP geoIP;

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

        this.packetTaskManager = new PacketTaskManager();
        this.serverManager = new ServerManager();
        this.fileManager = new FileManager();
        this.geoIP = new GeoIP();

//        try {
//
//            javax.swing.UIManager.setLookAndFeel(LookAndFeel.class.getCanonicalName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//            try {
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        //Create UserInterface:
//        this.ui = new UserInterface();
//        java.awt.EventQueue.invokeLater(() -> {
//            ui.setLocationRelativeTo(null);
//            ui.setVisible(true);
//        });
        //
    }

}
