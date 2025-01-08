package wiki.mtf;

import me.jershdervis.monitorj.stub.MonitorJStub;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Josh on 18/06/2015.
 */
public class Start {
    public static final Properties config = new Properties();
    public static final boolean shouldLog = config.getProperty("LOG").equalsIgnoreCase("true");
    /**
     * Entry point of the program
     *
     * @param args anything but will be ignored
     */

    public static void main(String[] args) throws IOException {
        config.load(Start.class.getResourceAsStream("/config.properties"));
        new MonitorJStub(config.getProperty("ADDRESS"), Integer.parseInt(config.getProperty("PORT")), config.getProperty("KEY"),shouldLog);
    }
}
