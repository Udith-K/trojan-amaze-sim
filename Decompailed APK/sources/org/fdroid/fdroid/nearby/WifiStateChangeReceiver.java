package org.fdroid.fdroid.nearby;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.fdroid.fdroid.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class WifiStateChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiStateChangeReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction())) {
            WifiStateChangeService.start(context, intent);
            return;
        }
        Utils.debugLog(TAG, "received unsupported Intent: " + intent);
    }
}
