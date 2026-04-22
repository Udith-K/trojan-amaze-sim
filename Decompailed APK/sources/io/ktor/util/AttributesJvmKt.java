package io.ktor.util;

/* JADX INFO: compiled from: AttributesJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AttributesJvmKt {
    public static final Attributes Attributes(boolean z) {
        return z ? new ConcurrentSafeAttributes() : new HashMapAttributes();
    }
}
