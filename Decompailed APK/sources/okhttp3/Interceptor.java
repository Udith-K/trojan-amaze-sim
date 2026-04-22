package okhttp3;

/* JADX INFO: compiled from: Interceptor.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface Interceptor {

    /* JADX INFO: compiled from: Interceptor.kt */
    public interface Chain {
        Call call();

        Response proceed(Request request);

        Request request();
    }

    Response intercept(Chain chain);
}
