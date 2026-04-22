package org.fdroid.fdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.net.DownloaderService;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        AppUpdateStatusManager appUpdateStatusManager;
        String stringExtra;
        appUpdateStatusManager = AppUpdateStatusManager.getInstance(context);
        stringExtra = intent.getStringExtra(DownloaderService.EXTRA_CANONICAL_URL);
        String action = intent.getAction();
        action.hashCode();
        switch (action) {
            case "org.fdroid.fdroid.installer.notifications.allinstalled.cleared":
                appUpdateStatusManager.clearAllInstalled();
                break;
            case "org.fdroid.fdroid.installer.notifications.installed.cleared":
                appUpdateStatusManager.removeApk(stringExtra);
                break;
            case "org.fdroid.fdroid.installer.notifications.allupdates.cleared":
                appUpdateStatusManager.clearAllUpdates();
                break;
            case "org.fdroid.fdroid.installer.notifications.update.cleared":
                AppUpdateStatusManager.AppUpdateStatus appUpdateStatus = appUpdateStatusManager.get(stringExtra);
                if (appUpdateStatus != null && appUpdateStatus.status == AppUpdateStatusManager.Status.InstallError) {
                    appUpdateStatusManager.removeApk(stringExtra);
                    break;
                }
                break;
        }
    }
}
