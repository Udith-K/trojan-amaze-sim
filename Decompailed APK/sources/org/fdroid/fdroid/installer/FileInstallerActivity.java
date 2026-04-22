package org.fdroid.fdroid.installer;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.net.DownloaderService;

/* JADX INFO: loaded from: classes2.dex */
public class FileInstallerActivity extends FragmentActivity {
    static final String ACTION_INSTALL_FILE = "org.fdroid.fdroid.installer.FileInstaller.action.INSTALL_PACKAGE";
    static final String ACTION_UNINSTALL_FILE = "org.fdroid.fdroid.installer.FileInstaller.action.UNINSTALL_PACKAGE";
    private static final int MY_PERMISSIONS_REQUEST_STORAGE = 1;
    private static final String TAG = "FileInstallerActivity";
    private int act = 0;
    private FileInstallerActivity activity;
    private Apk apk;
    private App app;
    private Uri canonicalUri;
    private FileInstaller installer;
    private Uri localApkUri;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.activity = this;
        Intent intent = getIntent();
        String action = intent.getAction();
        this.localApkUri = intent.getData();
        this.app = (App) intent.getParcelableExtra(Installer.EXTRA_APP);
        Apk apk = (Apk) intent.getParcelableExtra(Installer.EXTRA_APK);
        this.apk = apk;
        this.installer = new FileInstaller(this, this.app, apk);
        if (ACTION_INSTALL_FILE.equals(action)) {
            this.canonicalUri = Uri.parse(intent.getStringExtra(DownloaderService.EXTRA_CANONICAL_URL));
            if (hasStoragePermission()) {
                installPackage(this.localApkUri, this.canonicalUri, this.apk);
                return;
            } else {
                requestPermission();
                this.act = 1;
                return;
            }
        }
        if (ACTION_UNINSTALL_FILE.equals(action)) {
            this.canonicalUri = null;
            if (hasStoragePermission()) {
                uninstallPackage(this.apk);
                return;
            } else {
                requestPermission();
                this.act = 2;
                return;
            }
        }
        throw new IllegalStateException("Intent action not specified!");
    }

    private boolean hasStoragePermission() {
        return ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    private void requestPermission() {
        if (hasStoragePermission()) {
            return;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            showDialog();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        }
    }

    private void showDialog() {
        new MaterialAlertDialogBuilder(this, R.style.Theme_App).setMessage(R.string.app_permission_storage).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.installer.FileInstallerActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showDialog$0(dialogInterface, i);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.installer.FileInstallerActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showDialog$1(dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialog$0(DialogInterface dialogInterface, int i) {
        ActivityCompat.requestPermissions(this.activity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showDialog$1(DialogInterface dialogInterface, int i) {
        int i2 = this.act;
        if (i2 == 1) {
            this.installer.sendBroadcastInstall(this.canonicalUri, Installer.ACTION_INSTALL_INTERRUPTED);
        } else if (i2 == 2) {
            this.installer.sendBroadcastUninstall(Installer.ACTION_UNINSTALL_INTERRUPTED);
        }
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1) {
            if (iArr.length > 0 && iArr[0] == 0) {
                int i2 = this.act;
                if (i2 == 1) {
                    installPackage(this.localApkUri, this.canonicalUri, this.apk);
                } else if (i2 == 2) {
                    uninstallPackage(this.apk);
                }
            } else {
                int i3 = this.act;
                if (i3 == 1) {
                    this.installer.sendBroadcastInstall(this.canonicalUri, Installer.ACTION_INSTALL_INTERRUPTED);
                } else if (i3 == 2) {
                    this.installer.sendBroadcastUninstall(Installer.ACTION_UNINSTALL_INTERRUPTED);
                }
            }
            finish();
        }
    }

    private void installPackage(Uri uri, Uri uri2, Apk apk) {
        Utils.debugLog(TAG, "Installing: " + uri.getPath());
        File installedMediaFile = apk.getInstalledMediaFile(this.activity.getApplicationContext());
        installedMediaFile.getParentFile().mkdirs();
        try {
            FileUtils.copyFile(new File(uri.getPath()), installedMediaFile);
        } catch (IOException e) {
            Utils.debugLog(TAG, "Failed to copy: " + e.getMessage());
            this.installer.sendBroadcastInstall(uri2, Installer.ACTION_INSTALL_INTERRUPTED);
        }
        if (!apk.isMediaInstalled(this.activity.getApplicationContext())) {
            this.installer.sendBroadcastInstall(uri2, Installer.ACTION_INSTALL_INTERRUPTED);
        } else {
            Utils.debugLog(TAG, "Copying worked: " + uri.getPath());
            if (!postInstall(uri2, apk, installedMediaFile)) {
                Toast.makeText(this, String.format(getString(R.string.app_installed_media), installedMediaFile), 1).show();
                this.installer.sendBroadcastInstall(uri2, Installer.ACTION_INSTALL_COMPLETE);
            }
        }
        finish();
    }

    private boolean postInstall(Uri uri, Apk apk, File file) {
        if (!file.getName().endsWith(".obf") && !file.getName().endsWith(".obf.zip")) {
            return false;
        }
        ObfInstallerService.install(this, uri, this.app, apk, file);
        return true;
    }

    private void uninstallPackage(Apk apk) {
        if (apk.isMediaInstalled(this.activity.getApplicationContext()) && !apk.getInstalledMediaFile(this.activity.getApplicationContext()).delete()) {
            this.installer.sendBroadcastUninstall(Installer.ACTION_UNINSTALL_INTERRUPTED);
        } else {
            this.installer.sendBroadcastUninstall(Installer.ACTION_UNINSTALL_COMPLETE);
            finish();
        }
    }
}
