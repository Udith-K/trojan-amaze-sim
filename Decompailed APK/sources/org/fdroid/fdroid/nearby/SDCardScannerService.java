package org.fdroid.fdroid.nearby;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import androidx.core.app.JobIntentService;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.fdroid.fdroid.Utils;
import org.fdroid.index.v1.IndexV1UpdaterKt;

/* JADX INFO: loaded from: classes2.dex */
public class SDCardScannerService extends JobIntentService {
    private static final String ACTION_SCAN = "org.fdroid.fdroid.nearby.SCAN";
    private static final int JOB_ID = 946417208;
    private static final List<String> SKIP_DIRS = Arrays.asList(".android_secure", "LOST.DIR");
    public static final String TAG = "SDCardScannerService";

    public static void scan(Context context) {
        Intent intent = new Intent(context, (Class<?>) SDCardScannerService.class);
        intent.setAction(ACTION_SCAN);
        JobIntentService.enqueueWork(context, (Class<?>) SDCardScannerService.class, JOB_ID, intent);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        if (ACTION_SCAN.equals(intent.getAction())) {
            Process.setThreadPriority(19);
            HashSet<File> hashSet = new HashSet();
            for (File file : getExternalFilesDirs(null)) {
                Log.i(TAG, "getExternalFilesDirs " + file);
                if (file != null && file.isDirectory()) {
                    Log.i(TAG, "getExternalFilesDirs " + file);
                    try {
                        if (Environment.isExternalStorageRemovable(file)) {
                            String externalStorageState = Environment.getExternalStorageState(file);
                            if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
                                Collections.addAll(hashSet, checkExternalStorage(file.getParentFile().getParentFile().getParentFile().getParentFile(), externalStorageState));
                            } else {
                                Collections.addAll(hashSet, checkExternalStorage(file, externalStorageState));
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        Utils.debugLog(TAG, e.toString());
                    }
                }
            }
            Log.i(TAG, "sdcard files " + hashSet.toString());
            new ArrayList();
            for (File file2 : hashSet) {
                if (file2.isDirectory()) {
                    searchDirectory(file2);
                }
            }
        }
    }

    private File[] checkExternalStorage(File file, String str) {
        File[] fileArrListFiles = (file == null || !("mounted_ro".equals(str) || "mounted".equals(str))) ? null : file.listFiles();
        if (fileArrListFiles != null) {
            return fileArrListFiles;
        }
        Utils.debugLog(TAG, "checkExternalStorage returned blank, F-Droid probably doesn't have Storage perm!");
        return new File[0];
    }

    private void searchDirectory(File file) {
        File[] fileArrListFiles;
        if (SKIP_DIRS.contains(file.getName()) || (fileArrListFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                searchDirectory(file2);
            } else if (IndexV1UpdaterKt.SIGNED_FILE_NAME.equals(file2.getName())) {
                registerRepo(file2);
            }
        }
    }

    private void registerRepo(File file) {
        TreeUriScannerIntentService.registerRepo(this, Uri.fromFile(file.getParentFile()));
    }
}
