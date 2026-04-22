package io.ktor.util;

/* JADX INFO: compiled from: PlatformUtils.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PlatformUtils {
    public static final PlatformUtils INSTANCE;
    private static final boolean IS_BROWSER;
    private static final boolean IS_DEVELOPMENT_MODE;
    private static final boolean IS_JVM;
    private static final boolean IS_NATIVE;
    private static final boolean IS_NEW_MM_ENABLED;
    private static final boolean IS_NODE;

    private PlatformUtils() {
    }

    static {
        PlatformUtils platformUtils = new PlatformUtils();
        INSTANCE = platformUtils;
        IS_BROWSER = PlatformUtilsJvmKt.getPlatform(platformUtils) == Platform.Browser;
        IS_NODE = PlatformUtilsJvmKt.getPlatform(platformUtils) == Platform.Node;
        IS_JVM = PlatformUtilsJvmKt.getPlatform(platformUtils) == Platform.Jvm;
        IS_NATIVE = PlatformUtilsJvmKt.getPlatform(platformUtils) == Platform.Native;
        IS_DEVELOPMENT_MODE = PlatformUtilsJvmKt.isDevelopmentMode(platformUtils);
        IS_NEW_MM_ENABLED = PlatformUtilsJvmKt.isNewMemoryModel(platformUtils);
    }

    public final boolean getIS_BROWSER() {
        return IS_BROWSER;
    }

    public final boolean getIS_DEVELOPMENT_MODE() {
        return IS_DEVELOPMENT_MODE;
    }
}
