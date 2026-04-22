package okhttp3;

/* JADX INFO: compiled from: Call.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface Call extends Cloneable {
    void cancel();

    void enqueue(Callback callback);

    boolean isCanceled();
}
