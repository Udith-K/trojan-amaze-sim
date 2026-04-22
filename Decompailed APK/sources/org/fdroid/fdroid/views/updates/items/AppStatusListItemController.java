package org.fdroid.fdroid.views.updates.items;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.views.apps.AppListItemController;
import org.fdroid.fdroid.views.apps.AppListItemState;
import org.fdroid.fdroid.views.updates.UpdatesAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class AppStatusListItemController extends AppListItemController {
    @Override // org.fdroid.fdroid.views.apps.AppListItemController
    public boolean canDismiss() {
        return true;
    }

    AppStatusListItemController(AppCompatActivity appCompatActivity, View view) {
        super(appCompatActivity, view);
    }

    @Override // org.fdroid.fdroid.views.apps.AppListItemController
    protected AppListItemState getCurrentViewState(App app, AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        return super.getCurrentViewState(app, appUpdateStatus).setStatusText(getStatusText(appUpdateStatus));
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.updates.items.AppStatusListItemController$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status;

        static {
            int[] iArr = new int[AppUpdateStatusManager.Status.values().length];
            $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status = iArr;
            try {
                iArr[AppUpdateStatusManager.Status.ReadyToInstall.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.Installed.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.Downloading.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private CharSequence getStatusText(AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        if (appUpdateStatus == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[appUpdateStatus.status.ordinal()];
        if (i == 1) {
            return this.activity.getString(R.string.app_list_download_ready);
        }
        if (i != 2) {
            return null;
        }
        return this.activity.getString(R.string.notification_content_single_installed);
    }

    @Override // org.fdroid.fdroid.views.apps.AppListItemController
    protected void onDismissApp(App app, final UpdatesAdapter updatesAdapter) {
        AppUpdateStatusManager.AppUpdateStatus currentStatus = getCurrentStatus();
        if (currentStatus != null) {
            final AppUpdateStatusManager appUpdateStatusManager = AppUpdateStatusManager.getInstance(this.activity);
            final AppUpdateStatusManager.AppUpdateStatus appUpdateStatus = appUpdateStatusManager.get(currentStatus.getCanonicalUrl());
            appUpdateStatusManager.removeApk(currentStatus.getCanonicalUrl());
            int i = AnonymousClass1.$SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[currentStatus.status.ordinal()];
            if (i != 1) {
                if (i == 3) {
                    cancelDownload();
                    Snackbar.make(this.itemView, R.string.app_list__dismiss_downloading_app, -1).show();
                }
            } else if (appUpdateStatus != null) {
                Snackbar.make(this.itemView, R.string.app_list__dismiss_installing_app, 0).setAction(R.string.undo, new View.OnClickListener() { // from class: org.fdroid.fdroid.views.updates.items.AppStatusListItemController$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AppStatusListItemController.lambda$onDismissApp$0(appUpdateStatusManager, appUpdateStatus, updatesAdapter, view);
                    }
                }).show();
            }
        }
        updatesAdapter.refreshItems();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onDismissApp$0(AppUpdateStatusManager appUpdateStatusManager, AppUpdateStatusManager.AppUpdateStatus appUpdateStatus, UpdatesAdapter updatesAdapter, View view) {
        appUpdateStatusManager.addApk(appUpdateStatus.app, appUpdateStatus.apk, appUpdateStatus.status, appUpdateStatus.intent);
        updatesAdapter.refreshItems();
    }
}
