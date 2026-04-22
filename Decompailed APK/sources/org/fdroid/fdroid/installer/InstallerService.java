package org.fdroid.fdroid.installer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.JobIntentService;
import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.net.DownloaderService;

/* JADX INFO: loaded from: classes2.dex */
public class InstallerService extends JobIntentService {
    private static final String ACTION_INSTALL = "org.fdroid.fdroid.installer.InstallerService.action.INSTALL";
    private static final String ACTION_UNINSTALL = "org.fdroid.fdroid.installer.InstallerService.action.UNINSTALL";
    public static final String TAG = "InstallerService";

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(Intent intent) {
        App app = (App) intent.getParcelableExtra(Installer.EXTRA_APP);
        final Apk apk = (Apk) intent.getParcelableExtra(Installer.EXTRA_APK);
        if (apk == null) {
            return;
        }
        Installer installerCreate = InstallerFactory.create(this, app, apk);
        if (ACTION_INSTALL.equals(intent.getAction())) {
            installerCreate.installPackage(intent.getData(), Uri.parse(intent.getStringExtra(DownloaderService.EXTRA_CANONICAL_URL)));
        } else if (ACTION_UNINSTALL.equals(intent.getAction())) {
            installerCreate.uninstallPackage();
            new Thread() { // from class: org.fdroid.fdroid.installer.InstallerService.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    File parentFile;
                    File[] fileArrListFiles;
                    setPriority(1);
                    File mainObbFile = apk.getMainObbFile();
                    if (mainObbFile == null || (parentFile = mainObbFile.getParentFile()) == null || (fileArrListFiles = parentFile.listFiles((FileFilter) new WildcardFileFilter("*.obb"))) == null) {
                        return;
                    }
                    for (File file : fileArrListFiles) {
                        Utils.debugLog(InstallerService.TAG, "Uninstalling OBB " + file);
                        FileUtils.deleteQuietly(file);
                    }
                }
            }.start();
        }
    }

    public static void install(Context context, Uri uri, Uri uri2, App app, Apk apk) {
        Installer.sendBroadcastInstall(context, uri2, Installer.ACTION_INSTALL_STARTED, app, apk, null, null);
        Intent intent = new Intent(context, (Class<?>) InstallerService.class);
        intent.setAction(ACTION_INSTALL);
        intent.setData(uri);
        intent.putExtra(DownloaderService.EXTRA_CANONICAL_URL, uri2.toString());
        intent.putExtra(Installer.EXTRA_APP, app);
        intent.putExtra(Installer.EXTRA_APK, apk);
        enqueueWork(context, intent);
    }

    public static void uninstall(Context context, App app, Apk apk) {
        Objects.requireNonNull(apk);
        Installer.sendBroadcastUninstall(context, app, apk, Installer.ACTION_UNINSTALL_STARTED);
        Intent intent = new Intent(context, (Class<?>) InstallerService.class);
        intent.setAction(ACTION_UNINSTALL);
        intent.putExtra(Installer.EXTRA_APP, app);
        intent.putExtra(Installer.EXTRA_APK, apk);
        enqueueWork(context, intent);
    }

    private static void enqueueWork(Context context, Intent intent) {
        JobIntentService.enqueueWork(context, (Class<?>) InstallerService.class, 8856468, intent);
    }
}
