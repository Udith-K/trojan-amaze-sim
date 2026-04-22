package io.ktor.utils.io.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class UtilsKt {
    public static final int getIOIntProperty(String name, int i) {
        String property;
        Integer intOrNull;
        Intrinsics.checkNotNullParameter(name, "name");
        try {
            property = System.getProperty("io.ktor.utils.io." + name);
        } catch (SecurityException unused) {
            property = null;
        }
        return (property == null || (intOrNull = StringsKt.toIntOrNull(property)) == null) ? i : intOrNull.intValue();
    }
}
