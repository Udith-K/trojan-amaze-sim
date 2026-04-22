package org.acra.util;

import android.content.Context;
import android.widget.Toast;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;

/* JADX INFO: compiled from: ToastSender.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ToastSender {
    public static final ToastSender INSTANCE = new ToastSender();

    private ToastSender() {
    }

    public static final void sendToast(Context context, String str, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Toast.makeText(context, str, i).show();
        } catch (RuntimeException e) {
            ACRA.log.w(ACRA.LOG_TAG, "Could not send crash Toast", e);
        }
    }
}
