package db.util;

/**
 * Created by vitaly on 3/26/14.
 */
public final class ClasspathUtil {
    public static String getFilePath(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
    }
}
