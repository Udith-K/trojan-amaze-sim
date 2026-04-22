package org.fdroid.fdroid.installer;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.core.content.pm.PackageInfoCompat;
import androidx.core.util.Consumer;
import androidx.core.util.Pair;
import androidx.core.util.Supplier;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.compat.PackageManagerCompat;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.data.SanitizedFile;
import org.fdroid.fdroid.installer.ApkCache;
import org.fdroid.fdroid.net.DownloaderService;

/* JADX INFO: loaded from: classes2.dex */
public class InstallManagerService {
    private static final String ACTION_CANCEL = "org.fdroid.fdroid.installer.action.CANCEL";
    private static final String TAG = "InstallManagerService";

    @SuppressLint({"StaticFieldLeak"})
    private static InstallManagerService instance;
    private final AppUpdateStatusManager appUpdateStatusManager;
    private final Context context;
    private final LocalBroadcastManager localBroadcastManager;

    public static InstallManagerService getInstance(Context context) {
        if (instance == null) {
            instance = new InstallManagerService(context.getApplicationContext());
        }
        return instance;
    }

    public InstallManagerService(Context context) {
        this.context = context;
        this.localBroadcastManager = LocalBroadcastManager.getInstance(context);
        this.appUpdateStatusManager = AppUpdateStatusManager.getInstance(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_CANCEL);
        ContextCompat.registerReceiver(context, new BroadcastReceiver() { // from class: org.fdroid.fdroid.installer.InstallManagerService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Log.d(InstallManagerService.TAG, "Received cancel intent: " + intent);
                if (InstallManagerService.ACTION_CANCEL.equals(intent.getAction())) {
                    InstallManagerService.cancel(context2, intent.getStringExtra(DownloaderService.EXTRA_CANONICAL_URL));
                }
            }
        }, intentFilter, 4);
    }

    private void onCancel(String str) {
        DownloaderService.cancel(str);
        Apk apk = this.appUpdateStatusManager.getApk(str);
        if (apk != null) {
            Utils.debugLog(TAG, "also canceling OBB downloads");
            DownloaderService.cancel(apk.getPatchObbUrl());
            DownloaderService.cancel(apk.getMainObbUrl());
        }
    }

    private void queue(final String str, String str2, final App app, final Apk apk) {
        Utils.debugLog(TAG, "queue " + str2);
        if (TextUtils.isEmpty(str)) {
            Utils.debugLog(TAG, "empty canonicalUrl, nothing to do");
            return;
        }
        PackageInfo packageInfo = Utils.getPackageInfo(this.context, str2);
        if (packageInfo != null && PackageInfoCompat.getLongVersionCode(packageInfo) == apk.versionCode && TextUtils.equals(packageInfo.versionName, apk.versionName)) {
            Log.i(TAG, "Install action no longer valid since its installed, ignoring: " + str2);
            return;
        }
        this.appUpdateStatusManager.addApk(app, apk, AppUpdateStatusManager.Status.Downloading, null);
        getMainObb(str, apk);
        getPatchObb(str, apk);
        Utils.runOffUiThread(new Supplier() { // from class: org.fdroid.fdroid.installer.InstallManagerService$$ExternalSyntheticLambda1
            @Override // androidx.core.util.Supplier
            public final Object get() {
                return this.f$0.lambda$queue$0(apk);
            }
        }, new Consumer() { // from class: org.fdroid.fdroid.installer.InstallManagerService$$ExternalSyntheticLambda2
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$queue$1(str, app, apk, (Pair) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Pair lambda$queue$0(Apk apk) {
        return ApkCache.getApkCacheState(this.context, apk);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$queue$1(String str, App app, Apk apk, Pair pair) {
        ApkCache.ApkCacheState apkCacheState = (ApkCache.ApkCacheState) pair.first;
        final SanitizedFile sanitizedFile = (SanitizedFile) pair.second;
        if (apkCacheState == ApkCache.ApkCacheState.MISS_OR_PARTIAL) {
            Utils.debugLog(TAG, "download " + str + " " + sanitizedFile);
            DownloaderService.queue(this.context, str, app, apk);
            return;
        }
        if (apkCacheState == ApkCache.ApkCacheState.CACHED) {
            Utils.debugLog(TAG, "skip download, we have it, straight to install " + str + " " + sanitizedFile);
            Uri uri = Uri.parse(str);
            onDownloadStarted(uri);
            onDownloadComplete(uri, sanitizedFile, app, apk);
            return;
        }
        Utils.debugLog(TAG, "delete and download again " + str + " " + sanitizedFile);
        Objects.requireNonNull(sanitizedFile);
        Utils.runOffUiThread(new Runnable() { // from class: org.fdroid.fdroid.installer.InstallManagerService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                sanitizedFile.delete();
            }
        });
        DownloaderService.queue(this.context, str, app, apk);
    }

    public void onDownloadStarted(Uri uri) {
        this.appUpdateStatusManager.updateApk(uri.toString(), AppUpdateStatusManager.Status.Downloading, getDownloadCancelIntent(uri));
    }

    public void onDownloadProgress(Uri uri, App app, Apk apk, long j, long j2) {
        if (this.appUpdateStatusManager.get(uri.toString()) == null) {
            this.appUpdateStatusManager.addApk(app, apk, AppUpdateStatusManager.Status.Downloading, getDownloadCancelIntent(uri));
        }
        this.appUpdateStatusManager.updateApkProgress(uri.toString(), j2, j);
    }

    public void onDownloadComplete(Uri uri, File file, App app, Apk apk) {
        String string = uri.toString();
        Uri uriFromFile = Uri.fromFile(file);
        Utils.debugLog(TAG, "download completed of " + uri + " to " + uriFromFile);
        this.appUpdateStatusManager.updateApk(string, AppUpdateStatusManager.Status.ReadyToInstall, null);
        App app2 = this.appUpdateStatusManager.getApp(string);
        Apk apk2 = this.appUpdateStatusManager.getApk(string);
        if (app2 != null && apk2 != null) {
            app = app2;
            apk = apk2;
        }
        if (app != null && apk != null) {
            registerInstallReceiver(string);
            InstallerService.install(this.context, uriFromFile, uri, app, apk);
            return;
        }
        Log.e(TAG, "Could not install " + string + " because no app or apk available.");
    }

    public void onDownloadFailed(Uri uri, String str) {
        this.appUpdateStatusManager.setDownloadError(uri.toString(), str);
    }

    public void onDownloadCancelled(Uri uri) {
        this.appUpdateStatusManager.setDownloadCancelled(uri.toString());
    }

    private PendingIntent getDownloadCancelIntent(Uri uri) {
        Intent intent = new Intent(ACTION_CANCEL);
        intent.putExtra(DownloaderService.EXTRA_CANONICAL_URL, uri.toString());
        return PendingIntent.getBroadcast(this.context, 0, intent, 201326592);
    }

    private void getMainObb(String str, Apk apk) {
        getObb(str, apk.getMainObbUrl(), apk.getMainObbFile(), apk.obbMainFileSha256, apk.repoId);
    }

    private void getPatchObb(String str, Apk apk) {
        getObb(str, apk.getPatchObbUrl(), apk.getPatchObbFile(), apk.obbPatchFileSha256, apk.repoId);
    }

    private void getObb(final String str, String str2, final File file, final String str3, long j) {
        if (file == null || file.exists() || TextUtils.isEmpty(str2)) {
            return;
        }
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: org.fdroid.fdroid.installer.InstallManagerService.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloaderService.ACTION_STARTED.equals(action)) {
                    Utils.debugLog(InstallManagerService.TAG, action + " " + intent);
                    return;
                }
                if (DownloaderService.ACTION_PROGRESS.equals(action)) {
                    InstallManagerService.this.appUpdateStatusManager.updateApkProgress(str, intent.getLongExtra(DownloaderService.EXTRA_TOTAL_BYTES, 0L), intent.getLongExtra(DownloaderService.EXTRA_BYTES_READ, 0L));
                    return;
                }
                if (DownloaderService.ACTION_COMPLETE.equals(action)) {
                    InstallManagerService.this.localBroadcastManager.unregisterReceiver(this);
                    File file2 = new File(intent.getStringExtra(DownloaderService.EXTRA_DOWNLOAD_PATH));
                    Utils.debugLog(InstallManagerService.TAG, "OBB download completed " + intent.getDataString() + " to " + Uri.fromFile(file2));
                    try {
                        try {
                            if (Utils.isFileMatchingHash(file2, str3, "SHA-256")) {
                                Utils.debugLog(InstallManagerService.TAG, "Installing OBB " + file2 + " to " + file);
                                FileUtils.forceMkdirParent(file);
                                FileUtils.copyFile(file2, file);
                                StringBuilder sb = new StringBuilder();
                                sb.append(file.getName().substring(0, 4));
                                sb.append("*.obb");
                                for (File file3 : file.getParentFile().listFiles((FileFilter) new WildcardFileFilter(sb.toString()))) {
                                    if (!file3.equals(file)) {
                                        Utils.debugLog(InstallManagerService.TAG, "Deleting obsolete OBB " + file3);
                                        FileUtils.deleteQuietly(file3);
                                    }
                                }
                            } else {
                                Utils.debugLog(InstallManagerService.TAG, file2 + " deleted, did not match hash: " + str3);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        FileUtils.deleteQuietly(file2);
                        return;
                    } catch (Throwable th) {
                        FileUtils.deleteQuietly(file2);
                        throw th;
                    }
                }
                if (DownloaderService.ACTION_INTERRUPTED.equals(action)) {
                    InstallManagerService.this.localBroadcastManager.unregisterReceiver(this);
                } else {
                    if (DownloaderService.ACTION_CONNECTION_FAILED.equals(action)) {
                        InstallManagerService.this.localBroadcastManager.unregisterReceiver(this);
                        return;
                    }
                    throw new RuntimeException("intent action not handled!");
                }
            }
        };
        DownloaderService.queue(this.context, j, str2, str2);
        this.localBroadcastManager.registerReceiver(broadcastReceiver, DownloaderService.getIntentFilter(str2));
    }

    private void registerInstallReceiver(String str) {
        this.localBroadcastManager.registerReceiver(new BroadcastReceiver() { // from class: org.fdroid.fdroid.installer.InstallManagerService.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String dataString;
                dataString = intent.getDataString();
                String action = intent.getAction();
                action.hashCode();
                switch (action) {
                    case "org.fdroid.fdroid.installer.Installer.action.INSTALL_INTERRUPTED":
                        App app = (App) intent.getParcelableExtra(Installer.EXTRA_APP);
                        Apk apk = (Apk) intent.getParcelableExtra(Installer.EXTRA_APK);
                        String stringExtra = intent.getStringExtra(Installer.EXTRA_ERROR_MESSAGE);
                        if (!TextUtils.isEmpty(stringExtra)) {
                            InstallManagerService.this.appUpdateStatusManager.setApkError(app, apk, stringExtra);
                        } else {
                            InstallManagerService.this.appUpdateStatusManager.removeApk(dataString);
                        }
                        InstallManagerService.this.localBroadcastManager.unregisterReceiver(this);
                        return;
                    case "org.fdroid.fdroid.installer.Installer.action.INSTALL_USER_INTERACTION":
                        InstallManagerService.this.appUpdateStatusManager.addApk((App) intent.getParcelableExtra(Installer.EXTRA_APP), (Apk) intent.getParcelableExtra(Installer.EXTRA_APK), AppUpdateStatusManager.Status.ReadyToInstall, (PendingIntent) intent.getParcelableExtra(Installer.EXTRA_USER_INTERACTION_PI));
                        return;
                    case "org.fdroid.fdroid.installer.Installer.action.INSTALL_STARTED":
                        InstallManagerService.this.appUpdateStatusManager.updateApk(dataString, AppUpdateStatusManager.Status.Installing, null);
                        return;
                    case "org.fdroid.fdroid.installer.Installer.action.INSTALL_COMPLETE":
                        InstallManagerService.this.appUpdateStatusManager.updateApk(dataString, AppUpdateStatusManager.Status.Installed, null);
                        Apk apk2 = InstallManagerService.this.appUpdateStatusManager.getApk(dataString);
                        if (apk2 != null && apk2.isApk()) {
                            try {
                                PackageManagerCompat.setInstaller(context, context.getPackageManager(), apk2.packageName);
                                break;
                            } catch (SecurityException unused) {
                            }
                        }
                        InstallManagerService.this.localBroadcastManager.unregisterReceiver(this);
                        return;
                    default:
                        throw new RuntimeException("intent action not handled!");
                }
            }
        }, Installer.getInstallIntentFilter(Uri.parse(str)));
    }

    public static void queue(Context context, App app, Apk apk) {
        String canonicalUrl = apk.getCanonicalUrl();
        AppUpdateStatusManager.getInstance(context).addApk(app, apk, AppUpdateStatusManager.Status.PendingInstall, null);
        Utils.debugLog(TAG, "queue " + app.packageName + " " + apk.versionCode + " from " + canonicalUrl);
        getInstance(context).queue(canonicalUrl, apk.packageName, app, apk);
    }

    public static void cancel(Context context, String str) {
        getInstance(context).onCancel(str);
    }
}
