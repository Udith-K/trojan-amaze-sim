package org.fdroid.fdroid.installer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import androidx.core.app.JobIntentService;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;

/* JADX INFO: loaded from: classes2.dex */
public class InstallHistoryService extends JobIntentService {
    private static final int JOB_ID = 1661780444;
    public static final Uri LOG_URI = Uri.parse("content://org.fdroid.fdroid.installer/install_history/all");
    public static final String TAG = "InstallHistoryService";
    private static BroadcastReceiver broadcastReceiver;

    public static void register(Context context) {
        if (broadcastReceiver != null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addDataScheme("http");
        intentFilter.addDataScheme("https");
        intentFilter.addDataScheme("package");
        intentFilter.addAction(Installer.ACTION_INSTALL_STARTED);
        intentFilter.addAction(Installer.ACTION_INSTALL_COMPLETE);
        intentFilter.addAction(Installer.ACTION_INSTALL_INTERRUPTED);
        intentFilter.addAction(Installer.ACTION_INSTALL_USER_INTERACTION);
        intentFilter.addAction(Installer.ACTION_UNINSTALL_STARTED);
        intentFilter.addAction(Installer.ACTION_UNINSTALL_COMPLETE);
        intentFilter.addAction(Installer.ACTION_UNINSTALL_INTERRUPTED);
        intentFilter.addAction(Installer.ACTION_UNINSTALL_USER_INTERACTION);
        broadcastReceiver = new BroadcastReceiver() { // from class: org.fdroid.fdroid.installer.InstallHistoryService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                InstallHistoryService.queue(context2, intent);
            }
        };
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void unregister(Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
        broadcastReceiver = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void queue(Context context, Intent intent) {
        Utils.debugLog(TAG, "queue " + intent);
        intent.setClass(context, InstallHistoryService.class);
        JobIntentService.enqueueWork(context, (Class<?>) InstallHistoryService.class, JOB_ID, intent);
    }

    public static File getInstallHistoryFile(Context context) {
        File file = new File(context.getCacheDir(), "install_history");
        file.mkdir();
        return new File(file, "all");
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) throws Throwable {
        FileWriter fileWriter;
        Utils.debugLog(TAG, "onHandleIntent " + intent);
        Process.setThreadPriority(19);
        long jCurrentTimeMillis = System.currentTimeMillis();
        Apk apk = (Apk) intent.getParcelableExtra(Installer.EXTRA_APK);
        String str = apk.packageName;
        long j = apk.versionCode;
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(String.valueOf(jCurrentTimeMillis));
        arrayList.add(str);
        arrayList.add(String.valueOf(j));
        arrayList.add(intent.getAction());
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter(getInstallHistoryFile(this), true);
            try {
                try {
                    PrintWriter printWriter2 = new PrintWriter(fileWriter);
                    try {
                        printWriter2.println(TextUtils.join(",", arrayList));
                        Utils.closeQuietly(printWriter2);
                    } catch (IOException e) {
                        e = e;
                        printWriter = printWriter2;
                        Utils.debugLog(TAG, e.getMessage());
                        Utils.closeQuietly(printWriter);
                    } catch (Throwable th) {
                        th = th;
                        printWriter = printWriter2;
                        Utils.closeQuietly(printWriter);
                        Utils.closeQuietly(fileWriter);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            fileWriter = null;
        } catch (Throwable th3) {
            th = th3;
            fileWriter = null;
        }
        Utils.closeQuietly(fileWriter);
    }
}
