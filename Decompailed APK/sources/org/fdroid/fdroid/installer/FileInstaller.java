package org.fdroid.fdroid.installer;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.net.DownloaderService;

/* JADX INFO: loaded from: classes2.dex */
public class FileInstaller extends Installer {
    @Override // org.fdroid.fdroid.installer.Installer
    public Intent getPermissionScreen() {
        return null;
    }

    @Override // org.fdroid.fdroid.installer.Installer
    public Intent getUninstallScreen() {
        return null;
    }

    @Override // org.fdroid.fdroid.installer.Installer
    protected boolean isUnattended() {
        return false;
    }

    FileInstaller(Context context, App app, Apk apk) {
        super(context, app, apk);
    }

    @Override // org.fdroid.fdroid.installer.Installer
    public void installPackage(Uri uri, Uri uri2) {
        installPackageInternal(uri, uri2);
    }

    @Override // org.fdroid.fdroid.installer.Installer
    protected void installPackageInternal(Uri uri, Uri uri2) {
        Intent intent = new Intent(this.context, (Class<?>) FileInstallerActivity.class);
        intent.setAction("org.fdroid.fdroid.installer.FileInstaller.action.INSTALL_PACKAGE");
        intent.putExtra(DownloaderService.EXTRA_CANONICAL_URL, uri2.toString());
        intent.putExtra(Installer.EXTRA_APP, this.app);
        intent.putExtra(Installer.EXTRA_APK, this.apk);
        intent.setData(uri);
        sendBroadcastInstall(uri2, Installer.ACTION_INSTALL_USER_INTERACTION, PendingIntent.getActivity(this.context.getApplicationContext(), uri.hashCode(), intent, 201326592));
    }

    @Override // org.fdroid.fdroid.installer.Installer
    protected void uninstallPackage() {
        Intent intent = new Intent(this.context, (Class<?>) FileInstallerActivity.class);
        intent.setAction("org.fdroid.fdroid.installer.FileInstaller.action.UNINSTALL_PACKAGE");
        intent.putExtra(Installer.EXTRA_APP, this.app);
        intent.putExtra(Installer.EXTRA_APK, this.apk);
        sendBroadcastUninstall(Installer.ACTION_UNINSTALL_USER_INTERACTION, PendingIntent.getActivity(this.context.getApplicationContext(), this.apk.packageName.hashCode(), intent, 201326592));
    }
}
