package org.fdroid.fdroid.work;

import android.content.Context;
import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import ch.qos.logback.core.CoreConstants;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.installer.ApkCache;
import org.fdroid.fdroid.nearby.LocalRepoManager;

/* JADX INFO: loaded from: classes2.dex */
public class CleanCacheWorker extends Worker {
    public static final String TAG = "CleanCacheWorker";

    public CleanCacheWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    public static void schedule(Context context) {
        WorkManager workManager = WorkManager.getInstance(context);
        long keepCacheTime = Preferences.get().getKeepCacheTime();
        long millis = TimeUnit.DAYS.toMillis(1L);
        if (keepCacheTime >= millis) {
            keepCacheTime = millis;
        }
        workManager.enqueueUniquePeriodicWork(TAG, ExistingPeriodicWorkPolicy.REPLACE, (PeriodicWorkRequest) ((PeriodicWorkRequest.Builder) new PeriodicWorkRequest.Builder(CleanCacheWorker.class, keepCacheTime, TimeUnit.MILLISECONDS).setConstraints(new Constraints.Builder().setRequiresBatteryNotLow(true).build())).build());
        Utils.debugLog(TAG, "Scheduled periodic work for cleaning the cache every " + (keepCacheTime / CoreConstants.MILLIS_IN_ONE_HOUR) + " hours");
    }

    public static void force(Context context) {
        WorkManager.getInstance(context).enqueueUniqueWork("CleanCacheWorker.force", ExistingWorkPolicy.KEEP, (OneTimeWorkRequest) new OneTimeWorkRequest.Builder(CleanCacheWorker.class).build());
        Utils.debugLog(TAG, "Enqueued forced run for cleaning the cache.");
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        Process.setThreadPriority(19);
        try {
            Utils.debugLog(TAG, "Starting cache cleaning job.");
            Context applicationContext = getApplicationContext();
            deleteExpiredApksFromCache(applicationContext);
            deleteStrayIndexFiles(applicationContext);
            deleteOldInstallerFiles(applicationContext);
            deleteOldIcons(applicationContext);
            Utils.debugLog(TAG, "Finished cache cleaning job.");
            return ListenableWorker.Result.success();
        } catch (Exception unused) {
            Utils.debugLog(TAG, "Failed cache cleaning job.");
            return ListenableWorker.Result.failure();
        }
    }

    static void deleteExpiredApksFromCache(Context context) {
        clearOldFiles(ApkCache.getApkCacheDir(context), Preferences.get().getKeepCacheTime());
    }

    static void deleteOldInstallerFiles(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            Utils.debugLog(TAG, "The files directory doesn't exist.");
            return;
        }
        File[] fileArrListFiles = filesDir.listFiles();
        if (fileArrListFiles == null) {
            Utils.debugLog(TAG, "The files directory doesn't have any files.");
            return;
        }
        List listAsList = Arrays.asList(LocalRepoManager.WEB_ROOT_ASSET_FILES);
        for (File file : fileArrListFiles) {
            if (file.isFile() && !file.getName().endsWith(".html") && !listAsList.contains(file.getName())) {
                clearOldFiles(file, TimeUnit.HOURS.toMillis(1L));
            }
        }
    }

    static void deleteStrayIndexFiles(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            Utils.debugLog(TAG, "The cache directory doesn't exist.");
            return;
        }
        File[] fileArrListFiles = cacheDir.listFiles();
        if (fileArrListFiles == null) {
            Utils.debugLog(TAG, "The cache directory doesn't have files.");
            return;
        }
        for (File file : fileArrListFiles) {
            if (file.getName().startsWith("index-")) {
                clearOldFiles(file, TimeUnit.HOURS.toMillis(1L));
            }
            if (file.getName().startsWith("dl-")) {
                clearOldFiles(file, TimeUnit.HOURS.toMillis(1L));
            }
        }
    }

    static void deleteOldIcons(Context context) {
        clearOldFiles(Utils.getImageCacheDir(context), TimeUnit.DAYS.toMillis(365L));
    }

    public static void clearOldFiles(File file, long j) {
        if (file == null) {
            Utils.debugLog(TAG, "No files to be cleared.");
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - j;
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                Utils.debugLog(TAG, "No more files to be cleared.");
                return;
            }
            for (File file2 : fileArrListFiles) {
                clearOldFiles(file2, j);
            }
            deleteFileAndLog(file);
            return;
        }
        Impl21.deleteIfOld(file, jCurrentTimeMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void deleteFileAndLog(File file) {
        if (file.delete()) {
            Utils.debugLog(TAG, "Deleted file: " + file);
        }
    }

    private static class Impl21 {
        private Impl21() {
        }

        static void deleteIfOld(File file, long j) {
            if (file == null || !file.exists()) {
                Utils.debugLog(CleanCacheWorker.TAG, "No files to be cleared.");
                return;
            }
            try {
                if (Os.lstat(file.getAbsolutePath()).st_atime * 1000 < j) {
                    CleanCacheWorker.deleteFileAndLog(file);
                }
            } catch (ErrnoException e) {
                Utils.debugLog(CleanCacheWorker.TAG, "An exception occurred while deleting: ", e);
            }
        }
    }
}
