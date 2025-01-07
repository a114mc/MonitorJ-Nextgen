package me.jershdervis.monitorj.stub.util;


/**
 * helper class to check the operating system this Java VM runs in
 * <p>
 * please keep the notes below as a pseudo-license
 * <p>
 * http://stackoverflow.com/questions/228477/how-do-i-programmatically-determine-operating-system-in-java
 * compare to http://svn.terracotta.org/svn/tc/dso/tags/2.6.4/code/base/common/src/com/tc/util/runtime/Os.java
 * http://www.docjar.com/html/api/org/apache/commons/lang/SystemUtils.java.html
 */

public class OSCheck {
    // cached result of OS detection
    protected static OSType detectedOS;

    /**
     * detect the operating system from the os.name System property and cache
     * the result
     *
     * @return - the operating system detected
     */

    public static OSType getOperatingSystemType() {
        if (detectedOS == null) {
            String osname = System.getProperty("os.name", "generic").toLowerCase();
            if ((osname.contains("mac")) || (osname.contains("darwin"))) {
                return OSType.MacOS;
            }
            if (osname.contains("win")) {
                return OSType.Windows;
            }
            if (osname.contains("nux")) {
                return OSType.Linux;
            }
            return OSType.Other;

        }
        return detectedOS;
    }

    /**
     * types of Operating Systems
     */
    public enum OSType {
        Windows, MacOS, Linux, Other
    }


}
