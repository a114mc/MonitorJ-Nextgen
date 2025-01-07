import me.jershdervis.monitorj.stub.MonitorJStub;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Josh on 18/06/2015.
 */
public class Start {

    private static final Properties config = new Properties();

    /**
     * Entry point of the program
     *
     * @param args anything but will be ignored
     */

    public static void main(String[] args) throws IOException {
        config.load(Start.class.getResourceAsStream("/config.properties"));
        new MonitorJStub(config.getProperty("ADDRESS"), Integer.parseInt(config.getProperty("PORT")), config.getProperty("KEY"));
    }
}
