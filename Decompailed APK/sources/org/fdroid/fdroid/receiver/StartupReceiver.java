package org.fdroid.fdroid.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.work.RepoUpdateWorker;

/* JADX INFO: loaded from: classes2.dex */
public class StartupReceiver extends BroadcastReceiver {
    private static final String TAG = "StartupReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Log.e(TAG, "Received ACTION_BOOT_COMPLETED intent, scheduling update!!!");
            RepoUpdateWorker.scheduleOrCancel(context);
        } else {
            Utils.debugLog(TAG, "received unsupported Intent " + intent);
        }
    }
}
