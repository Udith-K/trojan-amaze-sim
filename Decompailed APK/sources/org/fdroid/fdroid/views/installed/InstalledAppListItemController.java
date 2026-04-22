package org.fdroid.fdroid.views.installed;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import org.fdroid.database.AppPrefs;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.views.apps.AppListItemController;
import org.fdroid.fdroid.views.apps.AppListItemState;

/* JADX INFO: loaded from: classes2.dex */
public class InstalledAppListItemController extends AppListItemController {
    public InstalledAppListItemController(AppCompatActivity appCompatActivity, View view) {
        super(appCompatActivity, view);
    }

    @Override // org.fdroid.fdroid.views.apps.AppListItemController
    protected AppListItemState getCurrentViewState(App app, AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        return new AppListItemState(app).setStatusText(getInstalledVersion(app)).setSecondaryStatusText(getIgnoreStatus(app));
    }

    private CharSequence getInstalledVersion(App app) {
        int i;
        if (app.autoInstallVersionCode == app.installedVersionCode) {
            i = R.string.app_recommended_version_installed;
        } else {
            i = R.string.app_version_x_installed;
        }
        return this.activity.getString(i, app.installedVersionName);
    }

    private CharSequence getIgnoreStatus(App app) {
        AppPrefs appPrefs = app.prefs;
        if (appPrefs == null || !appPrefs.shouldIgnoreUpdate(app.autoInstallVersionCode)) {
            return null;
        }
        return this.activity.getString(R.string.installed_app__updates_ignored);
    }
}
