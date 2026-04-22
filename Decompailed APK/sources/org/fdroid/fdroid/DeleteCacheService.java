package org.fdroid.fdroid;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import androidx.core.app.JobIntentService;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import java.io.File;
import org.apache.commons.io.FileUtils;

/* JADX INFO: loaded from: classes2.dex */
public class DeleteCacheService extends JobIntentService {
    public static final String TAG = "DeleteCacheService";

    public static void deleteAll(Context context) {
        JobIntentService.enqueueWork(context, (Class<?>) DeleteCacheService.class, 5387314, new Intent(context, (Class<?>) DeleteCacheService.class));
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        Process.setThreadPriority(19);
        Log.w(TAG, "Deleting all cached contents!");
        Glide.get(this).clearDiskCache();
        try {
            FileUtils.deleteDirectory(getCacheDir());
            for (File file : ContextCompat.getExternalCacheDirs(this)) {
                FileUtils.deleteDirectory(file);
            }
        } catch (Throwable unused) {
        }
    }
}
