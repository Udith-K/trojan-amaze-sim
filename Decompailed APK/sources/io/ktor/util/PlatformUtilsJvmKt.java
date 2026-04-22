package io.ktor.util;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlatformUtilsJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PlatformUtilsJvmKt {
    public static final boolean isNewMemoryModel(PlatformUtils platformUtils) {
        Intrinsics.checkNotNullParameter(platformUtils, "<this>");
        return true;
    }

    public static final Platform getPlatform(PlatformUtils platformUtils) {
        Intrinsics.checkNotNullParameter(platformUtils, "<this>");
        return Platform.Jvm;
    }

    public static final boolean isDevelopmentMode(PlatformUtils platformUtils) {
        Intrinsics.checkNotNullParameter(platformUtils, "<this>");
        String property = System.getProperty("io.ktor.development");
        return property != null && Boolean.parseBoolean(property);
    }
}
