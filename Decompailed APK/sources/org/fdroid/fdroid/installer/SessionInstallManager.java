package org.fdroid.fdroid.installer;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.ContextCompat;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.net.DownloaderService;

/* JADX INFO: loaded from: classes2.dex */
public class SessionInstallManager extends BroadcastReceiver {
    private static final String EXTRA_BUNDLE = "org.fdroid.fdroid.installer.SessionInstallManager.bundle";
    private static final String INSTALLER_ACTION_INSTALL = "org.fdroid.fdroid.installer.SessionInstallManager.install";
    private static final String INSTALLER_ACTION_UNINSTALL = "org.fdroid.fdroid.installer.SessionInstallManager.uninstall";
    private static final String TAG = "SessionInstallManager";
    private static Boolean isStockXiaomi;
    private final Context context;

    private boolean canUseInstallConstraints(String str) {
        return false;
    }

    public SessionInstallManager(Context context) {
        this.context = context;
        ContextCompat.registerReceiver(context, this, new IntentFilter(INSTALLER_ACTION_INSTALL), 4);
        ContextCompat.registerReceiver(context, this, new IntentFilter(INSTALLER_ACTION_UNINSTALL), 4);
        final PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
        Utils.runOffUiThread(new Runnable() { // from class: org.fdroid.fdroid.installer.SessionInstallManager$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                SessionInstallManager.lambda$new$0(packageInstaller);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(PackageInstaller packageInstaller) {
        for (PackageInstaller.SessionInfo sessionInfo : packageInstaller.getMySessions()) {
            Utils.debugLog(TAG, "Abandon session " + sessionInfo.getSessionId());
            try {
                packageInstaller.abandonSession(sessionInfo.getSessionId());
            } catch (SecurityException e) {
                Log.e(TAG, "Error abandoning session: ", e);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x00e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[Catch: all -> 0x00b2, SYNTHETIC, TRY_LEAVE, TryCatch #2 {all -> 0x00b2, blocks: (B:19:0x008b, B:21:0x0095, B:23:0x009d, B:26:0x00b4, B:48:0x00e2, B:47:0x00df, B:43:0x00d9), top: B:62:0x0059, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:? A[Catch: Exception -> 0x00c0, SYNTHETIC, TRY_LEAVE, TryCatch #0 {Exception -> 0x00c0, blocks: (B:27:0x00bc, B:55:0x00ee, B:54:0x00eb, B:50:0x00e5), top: B:60:0x004b, inners: #7 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void install(org.fdroid.fdroid.data.App r19, org.fdroid.fdroid.data.Apk r20, android.net.Uri r21, android.net.Uri r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.installer.SessionInstallManager.install(org.fdroid.fdroid.data.App, org.fdroid.fdroid.data.Apk, android.net.Uri, android.net.Uri):void");
    }

    private static PackageInstaller.SessionParams getSessionParams(App app, long j) {
        PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(1);
        sessionParams.setAppPackageName(app.packageName);
        sessionParams.setSize(j);
        sessionParams.setInstallLocation(0);
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            sessionParams.setInstallReason(4);
        }
        if (i >= 31) {
            sessionParams.setRequireUserAction(2);
        }
        if (i >= 33) {
            sessionParams.setPackageSource(2);
        }
        if (i >= 34) {
            sessionParams.setRequestUpdateOwnership(true);
        }
        return sessionParams;
    }

    public void uninstall(String str) {
        this.context.getPackageManager().getPackageInstaller().uninstall(str, getUninstallIntentSender(str));
    }

    private IntentSender getInstallIntentSender(int i, App app, Apk apk, Uri uri) {
        Intent intent = new Intent(INSTALLER_ACTION_INSTALL);
        intent.setPackage(this.context.getPackageName());
        intent.putExtra("android.content.pm.extra.SESSION_ID", i);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Installer.EXTRA_APP, app);
        bundle.putParcelable(Installer.EXTRA_APK, apk);
        intent.putExtra(EXTRA_BUNDLE, bundle);
        intent.putExtra(DownloaderService.EXTRA_CANONICAL_URL, uri);
        intent.addFlags(268435456);
        return PendingIntent.getBroadcast(this.context, i, intent, Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728).getIntentSender();
    }

    private IntentSender getUninstallIntentSender(String str) {
        Intent intent = new Intent(INSTALLER_ACTION_UNINSTALL);
        intent.setPackage(this.context.getPackageName());
        intent.putExtra("android.content.pm.extra.PACKAGE_NAME", str);
        intent.addFlags(268435456);
        return PendingIntent.getBroadcast(this.context, str.hashCode(), intent, Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728).getIntentSender();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (INSTALLER_ACTION_INSTALL.equals(intent.getAction())) {
            onInstallReceived(intent);
        } else {
            if (INSTALLER_ACTION_UNINSTALL.equals(intent.getAction())) {
                onUninstallReceived(intent);
                return;
            }
            throw new IllegalStateException("Unsupported broadcast action: " + intent.getAction());
        }
    }

    private void onInstallReceived(Intent intent) {
        int intExtra = intent.getIntExtra("android.content.pm.extra.SESSION_ID", -1);
        Intent intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.INTENT");
        Bundle bundleExtra = intent.getBundleExtra(EXTRA_BUNDLE);
        App app = (App) bundleExtra.getParcelable(Installer.EXTRA_APP);
        Apk apk = (Apk) bundleExtra.getParcelable(Installer.EXTRA_APK);
        Uri uri = (Uri) intent.getParcelableExtra(DownloaderService.EXTRA_CANONICAL_URL);
        int intExtra2 = intent.getIntExtra("android.content.pm.extra.STATUS", Integer.MIN_VALUE);
        String stringExtra = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
        Log.i(TAG, "Received install broadcast for " + app.packageName + " " + intExtra2 + ": " + stringExtra);
        if (intExtra2 == 0) {
            Installer.sendBroadcastInstall(this.context, uri, Installer.ACTION_INSTALL_COMPLETE, app, apk, null, null);
        } else {
            if (intExtra2 == -1) {
                Installer.sendBroadcastInstall(this.context, uri, Installer.ACTION_INSTALL_USER_INTERACTION, app, apk, PendingIntent.getActivity(this.context, intExtra, intent2, Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728), null);
                return;
            }
            if (intExtra2 == 3) {
                stringExtra = null;
            }
            Installer.sendBroadcastInstall(this.context, uri, Installer.ACTION_INSTALL_INTERRUPTED, app, apk, null, stringExtra);
        }
    }

    private void onUninstallReceived(Intent intent) {
        String stringExtra = intent.getStringExtra("android.content.pm.extra.PACKAGE_NAME");
        Intent intent2 = (Intent) intent.getParcelableExtra("android.intent.extra.INTENT");
        int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", Integer.MIN_VALUE);
        String stringExtra2 = intent.getStringExtra("android.content.pm.extra.STATUS_MESSAGE");
        Log.i(TAG, "Received uninstall broadcast for " + stringExtra + " " + intExtra + ": " + stringExtra2);
        if (intExtra == 0) {
            sendBroadcastUninstall(stringExtra, Installer.ACTION_UNINSTALL_COMPLETE, null, null);
        } else {
            if (intExtra == -1) {
                sendBroadcastUninstall(stringExtra, Installer.ACTION_UNINSTALL_USER_INTERACTION, PendingIntent.getActivity(this.context, stringExtra.hashCode(), intent2, Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728), null);
                return;
            }
            if (intExtra == 3) {
                stringExtra2 = null;
            }
            sendBroadcastUninstall(stringExtra, Installer.ACTION_UNINSTALL_INTERRUPTED, null, stringExtra2);
        }
    }

    private void sendBroadcastUninstall(String str, String str2, PendingIntent pendingIntent, String str3) {
        App app = new App();
        app.packageName = str;
        Apk apk = new Apk();
        apk.packageName = str;
        Installer.sendBroadcastUninstall(this.context, app, apk, str2, pendingIntent, str3);
    }

    public static boolean canBeUsed(Context context) {
        if (Preferences.get().forceOldInstaller() || Build.VERSION.SDK_INT < 31 || isStockXiaomi(context)) {
            return false;
        }
        return !PrivilegedInstaller.isDefault(context);
    }

    private static boolean isStockXiaomi(Context context) {
        if (isStockXiaomi == null) {
            String str = Build.BRAND;
            if ("Xiaomi".equalsIgnoreCase(str) || "Redmi".equalsIgnoreCase(str)) {
                if (Utils.getPackageInfo(context, "com.miui.securitycenter") != null) {
                    isStockXiaomi = Boolean.TRUE;
                } else {
                    isStockXiaomi = Boolean.valueOf(Utils.getPackageInfo(context, "com.miui.packageinstaller") != null);
                }
            } else {
                isStockXiaomi = Boolean.FALSE;
            }
        }
        return isStockXiaomi.booleanValue();
    }

    public static boolean isTargetSdkSupported(int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 31) {
            return false;
        }
        if (i2 == 31 && i >= 29) {
            return true;
        }
        if (i2 == 32 && i >= 29) {
            return true;
        }
        if (i2 == 33 && i >= 30) {
            return true;
        }
        if (i2 != 34 || i < 31) {
            return i2 >= 35 && i >= 33;
        }
        return true;
    }
}
