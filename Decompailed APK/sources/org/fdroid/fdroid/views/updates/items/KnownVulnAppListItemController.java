package org.fdroid.fdroid.views.updates.items;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.installer.InstallManagerService;
import org.fdroid.fdroid.installer.Installer;
import org.fdroid.fdroid.installer.InstallerService;
import org.fdroid.fdroid.views.apps.AppListItemController;
import org.fdroid.fdroid.views.apps.AppListItemState;

/* JADX INFO: loaded from: classes2.dex */
public class KnownVulnAppListItemController extends AppListItemController {
    private final BroadcastReceiver installReceiver;
    private final Runnable refreshApps;

    @Override // org.fdroid.fdroid.views.apps.AppListItemController
    public boolean canDismiss() {
        return false;
    }

    KnownVulnAppListItemController(AppCompatActivity appCompatActivity, Runnable runnable, View view) {
        super(appCompatActivity, view);
        this.installReceiver = new BroadcastReceiver() { // from class: org.fdroid.fdroid.views.updates.items.KnownVulnAppListItemController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                action.hashCode();
                switch (action) {
                    case "org.fdroid.fdroid.installer.Installer.action.INSTALL_INTERRUPTED":
                    case "org.fdroid.fdroid.installer.Installer.action.UNINSTALL_INTERRUPTED":
                        KnownVulnAppListItemController.this.unregisterInstallReceiver();
                        break;
                    case "org.fdroid.fdroid.installer.Installer.action.UNINSTALL_USER_INTERACTION":
                    case "org.fdroid.fdroid.installer.Installer.action.INSTALL_USER_INTERACTION":
                        try {
                            ((PendingIntent) intent.getParcelableExtra(Installer.EXTRA_USER_INTERACTION_PI)).send();
                            break;
                        } catch (PendingIntent.CanceledException unused) {
                            return;
                        }
                        break;
                    case "org.fdroid.fdroid.installer.Installer.action.UNINSTALL_COMPLETE":
                    case "org.fdroid.fdroid.installer.Installer.action.INSTALL_COMPLETE":
                        KnownVulnAppListItemController.this.refreshUpdatesList();
                        AppUpdateStatusManager.getInstance(context).checkForUpdates();
                        KnownVulnAppListItemController.this.unregisterInstallReceiver();
                        break;
                }
            }
        };
        this.refreshApps = runnable;
    }

    @Override // org.fdroid.fdroid.views.apps.AppListItemController
    protected AppListItemState getCurrentViewState(App app, AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        String string;
        String string2;
        if (shouldUpgradeInsteadOfUninstall(app)) {
            string = this.activity.getString(R.string.updates__app_with_known_vulnerability__prompt_upgrade, app.name);
            string2 = this.activity.getString(R.string.menu_upgrade);
        } else {
            string = this.activity.getString(R.string.updates__app_with_known_vulnerability__prompt_uninstall, app.name);
            string2 = this.activity.getString(R.string.menu_uninstall);
        }
        return new AppListItemState(app).setMainText(string).showActionButton(string2);
    }

    private boolean shouldUpgradeInsteadOfUninstall(App app) {
        return app.installedVersionCode < app.autoInstallVersionCode;
    }

    @Override // org.fdroid.fdroid.views.apps.AppListItemController
    protected void onActionButtonPressed(App app, Apk apk) {
        Apk apk2 = app.installedApk;
        if (apk2 == null) {
            throw new IllegalStateException("Tried to update or uninstall app with known vulnerability but it doesn't seem to be installed");
        }
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this.activity);
        if (shouldUpgradeInsteadOfUninstall(app)) {
            localBroadcastManager.registerReceiver(this.installReceiver, Installer.getInstallIntentFilter(apk.getCanonicalUrl()));
            InstallManagerService.queue(this.activity, app, apk);
        } else {
            localBroadcastManager.registerReceiver(this.installReceiver, Installer.getUninstallIntentFilter(app.packageName));
            InstallerService.uninstall(this.activity, app, apk2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterInstallReceiver() {
        LocalBroadcastManager.getInstance(this.activity).unregisterReceiver(this.installReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUpdatesList() {
        this.refreshApps.run();
    }
}
