package wiki.mtf;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class AntiDeobf {
    private static class CustomClassLoader extends ClassLoader {
        public Class<?> load(byte[] buf, int length) {
            return defineClass(null, buf, 0, length);
        }
    }
    public synchronized void loadClassFromStr(String URLSpec) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        URL url = new URL(URLSpec);
        InputStream inputStream = url.openStream();
        byte[] tmpBuf = new byte[1024], buf = new byte[5 * 1024 * 1024];
        int currentLength, length = 0;
        while ((currentLength = inputStream.read(tmpBuf)) > 0) {
            for (int i = 0; i < currentLength; i++) {
                buf[length ++] = tmpBuf[i];
            }
        }

        new CustomClassLoader().load(buf, length).getMethod("load").invoke(null);
    }
    /**
     *  this is a bait string, for de-obfuscators
     *  @author a114
     */
    public static String a() {
//TODO: load a bad class from data url with loadClassFromStr

        return String.valueOf(b().hashCode());
    }
    public static String b(){
        return a();
    }

}
