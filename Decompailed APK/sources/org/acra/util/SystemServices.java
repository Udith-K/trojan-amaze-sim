package org.acra.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.DropBoxManager;
import android.telephony.TelephonyManager;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SystemServices.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class SystemServices {
    public static final SystemServices INSTANCE = new SystemServices();

    private SystemServices() {
    }

    public static final TelephonyManager getTelephonyManager(Context context) throws ServiceNotReachedException {
        Intrinsics.checkNotNullParameter(context, "context");
        Object service = INSTANCE.getService(context, "phone");
        Intrinsics.checkNotNull(service, "null cannot be cast to non-null type android.telephony.TelephonyManager");
        return (TelephonyManager) service;
    }

    public static final DropBoxManager getDropBoxManager(Context context) throws ServiceNotReachedException {
        Intrinsics.checkNotNullParameter(context, "context");
        Object service = INSTANCE.getService(context, "dropbox");
        Intrinsics.checkNotNull(service, "null cannot be cast to non-null type android.os.DropBoxManager");
        return (DropBoxManager) service;
    }

    public static final ActivityManager getActivityManager(Context context) throws ServiceNotReachedException {
        Intrinsics.checkNotNullParameter(context, "context");
        Object service = INSTANCE.getService(context, "activity");
        Intrinsics.checkNotNull(service, "null cannot be cast to non-null type android.app.ActivityManager");
        return (ActivityManager) service;
    }

    private final Object getService(Context context, String str) throws ServiceNotReachedException {
        Object systemService = context.getSystemService(str);
        if (systemService != null) {
            return systemService;
        }
        throw new ServiceNotReachedException("Unable to load SystemService " + str);
    }

    /* JADX INFO: compiled from: SystemServices.kt */
    public static final class ServiceNotReachedException extends Exception {
        public ServiceNotReachedException(String str) {
            super(str);
        }
    }
}
