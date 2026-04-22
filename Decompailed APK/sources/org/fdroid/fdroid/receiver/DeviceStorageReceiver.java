package org.fdroid.fdroid.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.fdroid.fdroid.DeleteCacheService;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.work.CleanCacheWorker;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceStorageReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null && "android.intent.action.DEVICE_STORAGE_LOW".equals(intent.getAction())) {
            int percent = Utils.getPercent(Utils.getImageCacheDirAvailableMemory(context), Utils.getImageCacheDirTotalMemory(context));
            CleanCacheWorker.force(context);
            if (percent <= 2) {
                DeleteCacheService.deleteAll(context);
            }
        }
    }
}
