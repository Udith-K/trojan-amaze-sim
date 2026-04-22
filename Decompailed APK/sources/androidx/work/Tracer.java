package androidx.work;

/* JADX INFO: compiled from: Tracer.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Tracer {
    void beginAsyncSection(String str, int i);

    void beginSection(String str);

    void endAsyncSection(String str, int i);

    void endSection();

    boolean isEnabled();
}
