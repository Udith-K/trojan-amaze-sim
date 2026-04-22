package org.fdroid.fdroid.installer;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.net.DownloaderService;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultInstaller extends Installer {
    public static final String TAG = "DefaultInstaller";

    @Override // org.fdroid.fdroid.installer.Installer
    protected boolean isUnattended() {
        return false;
    }

    DefaultInstaller(Context context, App app, Apk apk) {
        super(context, app, apk);
    }

    @Override // org.fdroid.fdroid.installer.Installer
    protected void installPackageInternal(Uri uri, Uri uri2) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26 && i < 31 && this.context.getApplicationInfo().targetSdkVersion >= 26 && !this.context.getPackageManager().canRequestPackageInstalls()) {
            Intent intent = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES");
            intent.addFlags(268435456);
            intent.setData(Uri.parse("package:" + this.context.getPackageName()));
            this.context.startActivity(intent);
        }
        Intent intent2 = new Intent(this.context, (Class<?>) DefaultInstallerActivity.class);
        intent2.setAction("org.fdroid.fdroid.installer.DefaultInstaller.action.INSTALL_PACKAGE");
        intent2.putExtra(DownloaderService.EXTRA_CANONICAL_URL, uri2.toString());
        intent2.putExtra(Installer.EXTRA_APP, this.app);
        intent2.putExtra(Installer.EXTRA_APK, this.apk);
        intent2.setData(uri);
        sendBroadcastInstall(uri2, Installer.ACTION_INSTALL_USER_INTERACTION, PendingIntent.getActivity(this.context.getApplicationContext(), uri.hashCode(), intent2, 201326592));
    }

    @Override // org.fdroid.fdroid.installer.Installer
    protected void uninstallPackage() {
        Intent intent = new Intent(this.context, (Class<?>) DefaultInstallerActivity.class);
        intent.setAction("org.fdroid.fdroid.installer.DefaultInstaller.action.UNINSTALL_PACKAGE");
        intent.putExtra(Installer.EXTRA_APP, this.app);
        intent.putExtra(Installer.EXTRA_APK, this.apk);
        sendBroadcastUninstall(Installer.ACTION_UNINSTALL_USER_INTERACTION, PendingIntent.getActivity(this.context.getApplicationContext(), this.apk.packageName.hashCode(), intent, 201326592));
    }
}
