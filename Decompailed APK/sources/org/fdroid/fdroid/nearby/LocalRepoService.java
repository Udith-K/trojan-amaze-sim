package org.fdroid.fdroid.nearby;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class LocalRepoService extends IntentService {
    public static final String ACTION_CREATE = "org.fdroid.fdroid.nearby.action.CREATE";
    public static final String ACTION_STATUS = "localRepoStatusAction";
    public static final String EXTRA_PACKAGE_NAMES = "org.fdroid.fdroid.nearby.extra.PACKAGE_NAMES";
    public static final String EXTRA_STATUS = "localRepoStatusExtra";
    public static final int STATUS_ERROR = 2;
    public static final int STATUS_PROGRESS = 1;
    public static final int STATUS_STARTED = 0;
    public static final String TAG = "LocalRepoService";
    private String[] currentlyProcessedApps;
    private GenerateLocalRepoThread thread;

    public LocalRepoService() {
        super(TAG);
        this.currentlyProcessedApps = new String[0];
    }

    public static void create(Context context) {
        create(context, Collections.singleton(context.getPackageName()));
    }

    public static void create(Context context, Set<String> set) {
        Intent intent = new Intent(context, (Class<?>) LocalRepoService.class);
        intent.setAction(ACTION_CREATE);
        intent.putExtra(EXTRA_PACKAGE_NAMES, (String[]) set.toArray(new String[0]));
        context.startService(intent);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        Process.setThreadPriority(19);
        String[] stringArrayExtra = intent.getStringArrayExtra(EXTRA_PACKAGE_NAMES);
        if (stringArrayExtra == null || stringArrayExtra.length == 0) {
            Utils.debugLog(TAG, "no packageNames found, quitting");
            return;
        }
        Arrays.sort(stringArrayExtra);
        if (Arrays.equals(this.currentlyProcessedApps, stringArrayExtra)) {
            Utils.debugLog(TAG, "packageNames list unchanged, quitting");
            return;
        }
        this.currentlyProcessedApps = stringArrayExtra;
        GenerateLocalRepoThread generateLocalRepoThread = this.thread;
        if (generateLocalRepoThread != null) {
            generateLocalRepoThread.interrupt();
        }
        GenerateLocalRepoThread generateLocalRepoThread2 = new GenerateLocalRepoThread();
        this.thread = generateLocalRepoThread2;
        generateLocalRepoThread2.start();
    }

    private class GenerateLocalRepoThread extends Thread {
        private static final String TAG = "GenerateLocalRepoThread";

        private GenerateLocalRepoThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(19);
            LocalRepoService localRepoService = LocalRepoService.this;
            LocalRepoService.runProcess(localRepoService, localRepoService.currentlyProcessedApps);
        }
    }

    public static void runProcess(Context context, String[] strArr) {
        try {
            LocalRepoManager localRepoManager = LocalRepoManager.get(context);
            broadcast(context, 1, R.string.deleting_repo);
            localRepoManager.deleteRepo();
            broadcast(context, 1, R.string.linking_apks);
            localRepoManager.generateIndex(Utils.getSharingUri(FDroidApp.repo).toString(), FDroidApp.repo.getAddress(), strArr);
            broadcast(context, 0, (String) null);
        } catch (Exception e) {
            broadcast(context, 2, e.getLocalizedMessage());
            Log.e(TAG, "Error creating repo", e);
        }
    }

    static void broadcast(Context context, int i, String str) {
        Intent intent = new Intent(ACTION_STATUS);
        intent.putExtra(EXTRA_STATUS, i);
        if (str != null) {
            intent.putExtra("android.intent.extra.TEXT", str);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    static void broadcast(Context context, int i, int i2) {
        broadcast(context, i, context.getString(i2));
    }
}
