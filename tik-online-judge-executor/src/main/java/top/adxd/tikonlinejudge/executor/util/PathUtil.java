package top.adxd.tikonlinejudge.executor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathUtil {
    private static final String WINDOWS_PATH_REGEX = "^[a-zA-Z]:\\\\.*";
    private static final Pattern compile = Pattern.compile("^[a-zA-Z]:");

    public static boolean isWindowsPath(String path) {
        if (path == null) {
            return false;
        }
        return path.matches(WINDOWS_PATH_REGEX);
    }

    public static String dosPath2unixPath(String dosPath) {
        if (!isWindowsPath(dosPath)) {
            return dosPath;
        }
        Matcher matcher = compile.matcher(dosPath);
        if (!matcher.find()) {
            return dosPath;
        }
        return matcher
                .replaceFirst(dosPath.charAt(0) + "")
                .replaceAll("\\\\", "/");
    }
}
