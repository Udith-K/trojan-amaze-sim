package org.fdroid.fdroid.views.apps;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.data.App;

/* JADX INFO: loaded from: classes2.dex */
public class StandardAppListItemController extends AppListItemController {
    StandardAppListItemController(AppCompatActivity appCompatActivity, View view) {
        super(appCompatActivity, view);
    }

    @Override // org.fdroid.fdroid.views.apps.AppListItemController
    protected AppListItemState getCurrentViewState(App app, AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        String installableVersion = AppUpdateStatusManager.getInstance(this.itemView.getContext()).getInstallableVersion(app.packageName);
        return super.getCurrentViewState(app, appUpdateStatus).setStatusText(getStatusText(app, installableVersion)).setShowInstallButton(shouldShowInstall(app, installableVersion));
    }

    private CharSequence getStatusText(App app, String str) {
        if (!app.compatible) {
            return this.activity.getString(R.string.app_incompatible);
        }
        String[] strArr = app.antiFeatures;
        if (strArr != null && strArr.length > 0) {
            return this.activity.getString(R.string.antifeatures);
        }
        String str2 = app.installedVersionName;
        if (str2 != null) {
            return str != null ? this.activity.getString(R.string.app_version_x_available, str) : this.activity.getString(R.string.app_version_x_installed, str2);
        }
        return null;
    }

    private boolean shouldShowInstall(App app, String str) {
        String[] strArr;
        return (str != null || app.installedVersionName == null) && (app.compatible && ((strArr = app.antiFeatures) == null || strArr.length == 0));
    }
}
