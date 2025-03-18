package cn.a114;

import me.jershdervis.monitorj.stub.MonitorJStub;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Josh on 18/06/2015.
 */

public class Start {

    public static final Properties config = new Properties();
    public static boolean shouldLog;

    /**
     * Entry point of the program
     *
     * @param args anything but will be ignored
     */

    public static void main(String[] args) {
        try {
            config.load(Start.class.getResourceAsStream("/config.properties"));
            shouldLog = config.getProperty("LOG").equalsIgnoreCase("true");
        } catch (IOException e) {
            System.out.println("Failed to load client configuration, please check config.properties in the jar.");
        }
        new MonitorJStub(config.getProperty("ADDRESS"), Integer.parseInt(config.getProperty("PORT")), config.getProperty("KEY"), shouldLog);
    }
}
