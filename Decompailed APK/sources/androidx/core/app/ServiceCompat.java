package androidx.core.app;

import android.app.Service;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public abstract class ServiceCompat {
    public static void stopForeground(Service service, int i) {
        if (Build.VERSION.SDK_INT >= 24) {
            Api24Impl.stopForeground(service, i);
        } else {
            service.stopForeground((i & 1) != 0);
        }
    }

    static class Api24Impl {
        static void stopForeground(Service service, int i) {
            service.stopForeground(i);
        }
    }
}
