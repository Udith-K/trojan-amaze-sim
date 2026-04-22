package okhttp3;

import java.io.IOException;

/* JADX INFO: compiled from: Callback.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface Callback {
    void onFailure(Call call, IOException iOException);

    void onResponse(Call call, Response response);
}
