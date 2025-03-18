package me.jershdervis.monitorj.util;

//import com.maxmind.geoip.LookupService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

/**
 * Created by Josh on 26/06/2015.
 */
public class GeoIP {

    public static String HOST_EXTERNAL_IP;

    static {
//        try {
//            HOST_EXTERNAL_IP = new BufferedReader(new InputStreamReader(new URL("http://checkip.amazonaws.com/").openStream())).readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

//    private LookupService lookupService;

    public GeoIP() {
//        try {
//            this.lookupService = new LookupService(new File(MonitorJ.class.getResource("/GeoIP.dat").toURI()), LookupService.GEOIP_MEMORY_CACHE);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public String getCountryCode(String ipAddress) throws IOException {
//        return this.lookupService.getCountry(ipAddress).getCode().toLowerCase();
        return "CN";
    }

    public String getCountryName(String ipAddress) {
//        return this.lookupService.getCountry(ipAddress).getName();
        return "China";
    }

    public ImageIcon getCodeFlag(String countryCode) throws IOException {
        return new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("me/jershdervis/monitorj/resources/flags/" + countryCode + ".png")));
    }
}
