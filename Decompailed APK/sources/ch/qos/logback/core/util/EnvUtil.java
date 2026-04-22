package ch.qos.logback.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class EnvUtil {
    private static final Pattern versionPattern = Pattern.compile("^(1\\.)?([0-9]+)");

    private EnvUtil() {
    }

    public static boolean isJDK5() {
        return isJDK_N_OrHigher(5);
    }

    public static boolean isJDK7OrHigher() {
        return isJDK_N_OrHigher(7);
    }

    private static boolean isJDK_N_OrHigher(int i) {
        Matcher matcher = versionPattern.matcher(System.getProperty("java.version", ""));
        return matcher.find() && i <= Integer.parseInt(matcher.group(2));
    }
}
