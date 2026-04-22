package androidx.core.app;

/* JADX INFO: loaded from: classes.dex */
public abstract class RemoteInput {
    static android.app.RemoteInput[] fromCompat(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        android.app.RemoteInput[] remoteInputArr2 = new android.app.RemoteInput[remoteInputArr.length];
        for (int i = 0; i < remoteInputArr.length; i++) {
            RemoteInput remoteInput = remoteInputArr[i];
            remoteInputArr2[i] = fromCompat((RemoteInput) null);
        }
        return remoteInputArr2;
    }

    static android.app.RemoteInput fromCompat(RemoteInput remoteInput) {
        return Api20Impl.fromCompat(remoteInput);
    }

    static class Api20Impl {
        public static android.app.RemoteInput fromCompat(RemoteInput remoteInput) {
            throw null;
        }
    }
}
