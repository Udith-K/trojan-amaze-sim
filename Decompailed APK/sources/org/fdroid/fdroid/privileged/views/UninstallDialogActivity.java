package org.fdroid.fdroid.privileged.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.installer.Installer;

/* JADX INFO: loaded from: classes2.dex */
public class UninstallDialogActivity extends FragmentActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        int i;
        super.onCreate(bundle);
        final Intent intent = getIntent();
        final App app = (App) intent.getParcelableExtra(Installer.EXTRA_APP);
        final Apk apk = (Apk) intent.getParcelableExtra(Installer.EXTRA_APK);
        PackageManager packageManager = getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(apk.packageName, 8192);
            int i2 = applicationInfo.flags;
            boolean z = (i2 & 1) != 0;
            boolean z2 = (i2 & 128) != 0;
            if (z && !z2) {
                throw new RuntimeException("Cannot remove system apps unless we're uninstalling updates");
            }
            if (z2) {
                i = R.string.uninstall_update_confirm;
            } else {
                i = R.string.uninstall_confirm;
            }
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this, R.style.Theme_App);
            materialAlertDialogBuilder.setTitle(applicationInfo.loadLabel(packageManager));
            materialAlertDialogBuilder.setIcon(applicationInfo.loadIcon(packageManager));
            materialAlertDialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.privileged.views.UninstallDialogActivity$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    this.f$0.lambda$onCreate$0(app, apk, intent, dialogInterface, i3);
                }
            });
            materialAlertDialogBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: org.fdroid.fdroid.privileged.views.UninstallDialogActivity$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    this.f$0.lambda$onCreate$1(dialogInterface, i3);
                }
            });
            materialAlertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: org.fdroid.fdroid.privileged.views.UninstallDialogActivity$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    this.f$0.lambda$onCreate$2(dialogInterface);
                }
            });
            materialAlertDialogBuilder.setMessage(i);
            materialAlertDialogBuilder.create().show();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("UninstallDialogActivity", "Package to uninstall not found: " + apk.packageName, e);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(App app, Apk apk, Intent intent, DialogInterface dialogInterface, int i) {
        Intent intent2 = new Intent();
        intent2.putExtra(Installer.EXTRA_APP, app);
        intent2.putExtra(Installer.EXTRA_APK, apk);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1(DialogInterface dialogInterface, int i) {
        setResult(0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2(DialogInterface dialogInterface) {
        setResult(0);
        finish();
    }
}
