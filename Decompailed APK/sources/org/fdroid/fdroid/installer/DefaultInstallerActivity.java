package org.fdroid.fdroid.installer;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import ch.qos.logback.core.joran.action.Action;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.net.DownloaderService;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultInstallerActivity extends FragmentActivity {
    static final String ACTION_INSTALL_PACKAGE = "org.fdroid.fdroid.installer.DefaultInstaller.action.INSTALL_PACKAGE";
    static final String ACTION_UNINSTALL_PACKAGE = "org.fdroid.fdroid.installer.DefaultInstaller.action.UNINSTALL_PACKAGE";
    private static final int REQUEST_CODE_INSTALL = 0;
    private static final int REQUEST_CODE_UNINSTALL = 1;
    private static final String TAG = "DefaultInstallerActivit";
    private Uri canonicalUri;
    private DefaultInstaller installer;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        App app = (App) intent.getParcelableExtra(Installer.EXTRA_APP);
        Apk apk = (Apk) intent.getParcelableExtra(Installer.EXTRA_APK);
        this.installer = new DefaultInstaller(this, app, apk);
        if (ACTION_INSTALL_PACKAGE.equals(action)) {
            Uri data = intent.getData();
            this.canonicalUri = Uri.parse(intent.getStringExtra(DownloaderService.EXTRA_CANONICAL_URL));
            installPackage(data);
        } else {
            if (ACTION_UNINSTALL_PACKAGE.equals(action)) {
                uninstallPackage(apk.packageName);
                return;
            }
            throw new IllegalStateException("Intent action not specified!");
        }
    }

    @SuppressLint({"InlinedApi"})
    private void installPackage(Uri uri) {
        if (uri == null) {
            throw new RuntimeException("Set the data uri to point to an apk location!");
        }
        int i = Build.VERSION.SDK_INT;
        if (i < 24 && !Action.FILE_ATTRIBUTE.equals(uri.getScheme())) {
            throw new RuntimeException("PackageInstaller < Android N only supports file scheme!");
        }
        if (i >= 24 && !"content".equals(uri.getScheme())) {
            throw new RuntimeException("PackageInstaller >= Android N only supports content scheme!");
        }
        Intent intent = new Intent();
        if (i < 24) {
            intent.setAction("android.intent.action.INSTALL_PACKAGE");
            intent.setData(uri);
            intent.putExtra("android.intent.extra.RETURN_RESULT", true);
            intent.putExtra("android.intent.extra.NOT_UNKNOWN_SOURCE", true);
        } else {
            intent.setAction("android.intent.action.INSTALL_PACKAGE");
            intent.setData(uri);
            intent.addFlags(1);
            intent.putExtra("android.intent.extra.RETURN_RESULT", true);
            intent.putExtra("android.intent.extra.NOT_UNKNOWN_SOURCE", true);
        }
        try {
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "ActivityNotFoundException", e);
            this.installer.sendBroadcastInstall(this.canonicalUri, Installer.ACTION_INSTALL_INTERRUPTED, "This Android rom does not support ACTION_INSTALL_PACKAGE!");
            finish();
        }
    }

    private void uninstallPackage(String str) {
        try {
            getPackageManager().getPackageInfo(str, 0);
            Uri uriFromParts = Uri.fromParts("package", str, null);
            Intent intent = new Intent();
            intent.setData(uriFromParts);
            intent.setAction("android.intent.action.UNINSTALL_PACKAGE");
            intent.putExtra("android.intent.extra.RETURN_RESULT", true);
            try {
                startActivityForResult(intent, 1);
            } catch (ActivityNotFoundException e) {
                Log.e(TAG, "ActivityNotFoundException", e);
                this.installer.sendBroadcastUninstall(Installer.ACTION_UNINSTALL_INTERRUPTED, "This Android rom does not support ACTION_UNINSTALL_PACKAGE!");
                finish();
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e(TAG, "NameNotFoundException", e2);
            this.installer.sendBroadcastUninstall(Installer.ACTION_UNINSTALL_INTERRUPTED, "Package that is scheduled for uninstall is not installed!");
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 0) {
            if (i != 1) {
                throw new RuntimeException("Invalid request code!");
            }
            if (i2 == -1) {
                this.installer.sendBroadcastUninstall(Installer.ACTION_UNINSTALL_COMPLETE);
            } else if (i2 == 0) {
                this.installer.sendBroadcastUninstall(Installer.ACTION_UNINSTALL_INTERRUPTED);
            } else {
                this.installer.sendBroadcastUninstall(Installer.ACTION_UNINSTALL_INTERRUPTED, getString(R.string.uninstall_error_unknown));
            }
        } else if (i2 == -1) {
            this.installer.sendBroadcastInstall(this.canonicalUri, Installer.ACTION_INSTALL_COMPLETE);
        } else if (i2 == 0) {
            this.installer.sendBroadcastInstall(this.canonicalUri, Installer.ACTION_INSTALL_INTERRUPTED);
        } else {
            this.installer.sendBroadcastInstall(this.canonicalUri, Installer.ACTION_INSTALL_INTERRUPTED, getString(R.string.install_error_unknown));
        }
        finish();
    }
}
