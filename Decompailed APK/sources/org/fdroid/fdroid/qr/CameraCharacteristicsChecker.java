package org.fdroid.fdroid.qr;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CameraCharacteristicsChecker {
    public abstract boolean hasAutofocus();

    public static CameraCharacteristicsChecker getInstance(Context context) {
        return new CameraCharacteristicsMinApiLevel21(context);
    }

    static class FDroidDeviceException extends Exception {
        FDroidDeviceException(String str, Throwable th) {
            super(str, th);
        }
    }
}
