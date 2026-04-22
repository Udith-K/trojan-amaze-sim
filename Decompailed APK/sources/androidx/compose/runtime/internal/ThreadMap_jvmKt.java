package androidx.compose.runtime.internal;

/* JADX INFO: compiled from: ThreadMap.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ThreadMap_jvmKt {
    private static final ThreadMap emptyThreadMap = new ThreadMap(0, new long[0], new Object[0]);

    public static final ThreadMap getEmptyThreadMap() {
        return emptyThreadMap;
    }
}
