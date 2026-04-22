package org.fdroid.fdroid.nearby;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.fdroid.fdroid.views.main.NearbyViewBinder;

/* JADX INFO: loaded from: classes2.dex */
public class UsbDeviceMediaMountedReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        if ("bad_removal".equals(action) || "mounted".equals(action) || "removed".equals(action) || "ejecting".equals(action)) {
            NearbyViewBinder.updateUsbOtg(context);
        }
    }
}
