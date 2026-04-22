package org.fdroid.fdroid.installer;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.IOException;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.installer.ApkVerifier;
import org.fdroid.fdroid.privileged.views.AppDiff;
import org.fdroid.fdroid.privileged.views.AppSecurityPermissions;
import org.fdroid.fdroid.privileged.views.InstallConfirmActivity;
import org.fdroid.fdroid.privileged.views.UninstallDialogActivity;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Installer {
    public static final String ACTION_INSTALL_COMPLETE = "org.fdroid.fdroid.installer.Installer.action.INSTALL_COMPLETE";
    public static final String ACTION_INSTALL_INTERRUPTED = "org.fdroid.fdroid.installer.Installer.action.INSTALL_INTERRUPTED";
    public static final String ACTION_INSTALL_STARTED = "org.fdroid.fdroid.installer.Installer.action.INSTALL_STARTED";
    public static final String ACTION_INSTALL_USER_INTERACTION = "org.fdroid.fdroid.installer.Installer.action.INSTALL_USER_INTERACTION";
    public static final String ACTION_UNINSTALL_COMPLETE = "org.fdroid.fdroid.installer.Installer.action.UNINSTALL_COMPLETE";
    public static final String ACTION_UNINSTALL_INTERRUPTED = "org.fdroid.fdroid.installer.Installer.action.UNINSTALL_INTERRUPTED";
    public static final String ACTION_UNINSTALL_STARTED = "org.fdroid.fdroid.installer.Installer.action.UNINSTALL_STARTED";
    public static final String ACTION_UNINSTALL_USER_INTERACTION = "org.fdroid.fdroid.installer.Installer.action.UNINSTALL_USER_INTERACTION";
    public static final String AUTHORITY = "org.fdroid.fdroid.installer";
    public static final String EXTRA_APK = "org.fdroid.fdroid.installer.Installer.extra.APK";
    public static final String EXTRA_APP = "org.fdroid.fdroid.installer.Installer.extra.APP";
    public static final String EXTRA_ERROR_MESSAGE = "org.fdroid.fdroid.net.installer.Installer.extra.ERROR_MESSAGE";
    public static final String EXTRA_USER_INTERACTION_PI = "org.fdroid.fdroid.installer.Installer.extra.USER_INTERACTION_PI";
    private static final String TAG = "Installer";
    final Apk apk;
    final App app;
    final Context context;

    protected abstract void installPackageInternal(Uri uri, Uri uri2);

    protected abstract boolean isUnattended();

    protected abstract void uninstallPackage();

    Installer(Context context, App app, Apk apk) {
        this.context = context;
        this.app = app;
        this.apk = apk;
    }

    public Intent getPermissionScreen() {
        if (!isUnattended() || newPermissionCount() == 0) {
            return null;
        }
        Intent intent = new Intent(this.context, (Class<?>) InstallConfirmActivity.class);
        intent.putExtra(EXTRA_APP, this.app);
        intent.putExtra(EXTRA_APK, this.apk);
        return intent;
    }

    private int newPermissionCount() {
        Apk apk = this.apk;
        if (apk.targetSdkVersion >= 23) {
            return 0;
        }
        AppDiff appDiff = new AppDiff(this.context, apk);
        AppSecurityPermissions appSecurityPermissions = new AppSecurityPermissions(this.context, appDiff.apkPackageInfo);
        if (appDiff.installedApplicationInfo != null) {
            return appSecurityPermissions.getPermissionCount(4);
        }
        return appSecurityPermissions.getPermissionCount(65535);
    }

    public Intent getUninstallScreen() {
        String installerPackageName = null;
        if (!isUnattended()) {
            return null;
        }
        try {
            installerPackageName = this.context.getPackageManager().getInstallerPackageName(this.apk.packageName);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "App not installed: " + this.apk.packageName, e);
        }
        if (Build.VERSION.SDK_INT >= 24 && ("com.android.packageinstaller".equals(installerPackageName) || "com.google.android.packageinstaller".equals(installerPackageName))) {
            Utils.debugLog(TAG, "Falling back to default installer for uninstall");
            Intent intent = new Intent(this.context, (Class<?>) DefaultInstallerActivity.class);
            intent.setAction("org.fdroid.fdroid.installer.DefaultInstaller.action.UNINSTALL_PACKAGE");
            intent.putExtra(EXTRA_APP, this.app);
            intent.putExtra(EXTRA_APK, this.apk);
            return intent;
        }
        Intent intent2 = new Intent(this.context, (Class<?>) UninstallDialogActivity.class);
        intent2.putExtra(EXTRA_APP, this.app);
        intent2.putExtra(EXTRA_APK, this.apk);
        return intent2;
    }

    void sendBroadcastInstall(Uri uri, String str, PendingIntent pendingIntent) {
        sendBroadcastInstall(this.context, uri, str, this.app, this.apk, pendingIntent, null);
    }

    void sendBroadcastInstall(Uri uri, String str) {
        sendBroadcastInstall(this.context, uri, str, this.app, this.apk, null, null);
    }

    void sendBroadcastInstall(Uri uri, String str, String str2) {
        sendBroadcastInstall(this.context, uri, str, this.app, this.apk, null, str2);
    }

    static void sendBroadcastInstall(Context context, Uri uri, String str, App app, Apk apk, PendingIntent pendingIntent, String str2) {
        Intent intent = new Intent(str);
        intent.setData(uri);
        intent.putExtra(EXTRA_USER_INTERACTION_PI, pendingIntent);
        intent.putExtra(EXTRA_APP, app);
        intent.putExtra(EXTRA_APK, apk);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra(EXTRA_ERROR_MESSAGE, str2);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    void sendBroadcastUninstall(String str, String str2) {
        sendBroadcastUninstall(str, null, str2);
    }

    void sendBroadcastUninstall(String str) {
        sendBroadcastUninstall(str, null, null);
    }

    void sendBroadcastUninstall(String str, PendingIntent pendingIntent) {
        sendBroadcastUninstall(str, pendingIntent, null);
    }

    private void sendBroadcastUninstall(String str, PendingIntent pendingIntent, String str2) {
        sendBroadcastUninstall(this.context, this.app, this.apk, str, pendingIntent, str2);
    }

    static void sendBroadcastUninstall(Context context, App app, Apk apk, String str) {
        sendBroadcastUninstall(context, app, apk, str, null, null);
    }

    static void sendBroadcastUninstall(Context context, App app, Apk apk, String str, PendingIntent pendingIntent, String str2) {
        Uri uriFromParts = Uri.fromParts("package", apk.packageName, null);
        Intent intent = new Intent(str);
        intent.setData(uriFromParts);
        intent.putExtra(EXTRA_APP, app);
        intent.putExtra(EXTRA_APK, apk);
        intent.putExtra(EXTRA_USER_INTERACTION_PI, pendingIntent);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra(EXTRA_ERROR_MESSAGE, str2);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    static IntentFilter getInstallIntentFilter(Uri uri) {
        IntentFilter installInteractionIntentFilter = getInstallInteractionIntentFilter(uri);
        installInteractionIntentFilter.addAction(ACTION_INSTALL_STARTED);
        installInteractionIntentFilter.addAction(ACTION_INSTALL_COMPLETE);
        installInteractionIntentFilter.addAction(ACTION_INSTALL_INTERRUPTED);
        return installInteractionIntentFilter;
    }

    public static IntentFilter getInstallInteractionIntentFilter(Uri uri) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_INSTALL_USER_INTERACTION);
        intentFilter.addDataScheme(uri.getScheme());
        intentFilter.addDataAuthority(uri.getHost(), String.valueOf(uri.getPort()));
        intentFilter.addDataPath(uri.getPath(), 0);
        return intentFilter;
    }

    public static IntentFilter getInstallIntentFilter(String str) {
        return getInstallIntentFilter(Uri.parse(str));
    }

    public static IntentFilter getUninstallIntentFilter(String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_UNINSTALL_STARTED);
        intentFilter.addAction(ACTION_UNINSTALL_COMPLETE);
        intentFilter.addAction(ACTION_UNINSTALL_INTERRUPTED);
        intentFilter.addAction(ACTION_UNINSTALL_USER_INTERACTION);
        intentFilter.addDataScheme("package");
        intentFilter.addDataPath(str, 0);
        return intentFilter;
    }

    public void installPackage(Uri uri, Uri uri2) {
        try {
            Uri safeUri = ApkFileProvider.getSafeUri(this.context, uri, this.apk);
            try {
                new ApkVerifier(this.context, uri, this.apk).verifyApk();
            } catch (ApkVerifier.ApkPermissionUnequalException e) {
                final String string = this.context.getString(R.string.apk_permissions_mismatch, e.getMessage());
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: org.fdroid.fdroid.installer.Installer$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$installPackage$0(string);
                    }
                });
                try {
                    Thread.sleep(1500L);
                } catch (InterruptedException unused) {
                }
            } catch (ApkVerifier.ApkVerificationException e2) {
                Utils.debugLog(TAG, e2.getMessage(), e2);
                sendBroadcastInstall(uri2, ACTION_INSTALL_INTERRUPTED, e2.getMessage());
                return;
            }
            installPackageInternal(safeUri, uri2);
        } catch (IOException e3) {
            Utils.debugLog(TAG, e3.getMessage(), e3);
            sendBroadcastInstall(uri2, ACTION_INSTALL_INTERRUPTED, e3.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$installPackage$0(String str) {
        Toast.makeText(this.context, str, 1).show();
    }
}
