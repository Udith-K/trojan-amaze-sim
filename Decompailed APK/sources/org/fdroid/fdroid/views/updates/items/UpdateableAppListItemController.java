package org.fdroid.fdroid.views.updates.items;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.core.util.Supplier;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.google.android.material.snackbar.Snackbar;
import org.fdroid.database.AppPrefs;
import org.fdroid.database.AppPrefsDao;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.views.apps.AppListItemController;
import org.fdroid.fdroid.views.apps.AppListItemState;
import org.fdroid.fdroid.views.updates.UpdatesAdapter;
import org.fdroid.fdroid.views.updates.items.UpdateableAppListItemController;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateableAppListItemController extends AppListItemController {
    @Override // org.fdroid.fdroid.views.apps.AppListItemController
    public boolean canDismiss() {
        return true;
    }

    UpdateableAppListItemController(AppCompatActivity appCompatActivity, View view) {
        super(appCompatActivity, view);
    }

    @Override // org.fdroid.fdroid.views.apps.AppListItemController
    protected AppListItemState getCurrentViewState(App app, AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        return new AppListItemState(app).setShowInstallButton(true);
    }

    @Override // org.fdroid.fdroid.views.apps.AppListItemController
    protected void onDismissApp(App app, UpdatesAdapter updatesAdapter) {
        AppPrefsDao appPrefsDao = DBHelper.getDb(this.activity).getAppPrefsDao();
        LiveData appPrefs = appPrefsDao.getAppPrefs(app.packageName);
        appPrefs.observe(this.activity, new AnonymousClass1(app, appPrefsDao, appPrefs));
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.updates.items.UpdateableAppListItemController$1, reason: invalid class name */
    class AnonymousClass1 implements Observer {
        final /* synthetic */ App val$app;
        final /* synthetic */ AppPrefsDao val$appPrefsDao;
        final /* synthetic */ LiveData val$liveData;

        AnonymousClass1(App app, AppPrefsDao appPrefsDao, LiveData liveData) {
            this.val$app = app;
            this.val$appPrefsDao = appPrefsDao;
            this.val$liveData = liveData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(final AppPrefs appPrefs) {
            final App app = this.val$app;
            final AppPrefsDao appPrefsDao = this.val$appPrefsDao;
            Supplier supplier = new Supplier() { // from class: org.fdroid.fdroid.views.updates.items.UpdateableAppListItemController$1$$ExternalSyntheticLambda0
                @Override // androidx.core.util.Supplier
                public final Object get() {
                    return UpdateableAppListItemController.AnonymousClass1.lambda$onChanged$0(appPrefs, app, appPrefsDao);
                }
            };
            final AppPrefsDao appPrefsDao2 = this.val$appPrefsDao;
            Utils.runOffUiThread(supplier, new Consumer() { // from class: org.fdroid.fdroid.views.updates.items.UpdateableAppListItemController$1$$ExternalSyntheticLambda1
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$onChanged$1(appPrefsDao2, (AppPrefs) obj);
                }
            });
            this.val$liveData.removeObserver(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ AppPrefs lambda$onChanged$0(AppPrefs appPrefs, App app, AppPrefsDao appPrefsDao) {
            AppPrefs appPrefs2 = appPrefs.toggleIgnoreVersionCodeUpdate(app.autoInstallVersionCode);
            appPrefsDao.update(appPrefs2);
            return appPrefs2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onChanged$1(AppPrefsDao appPrefsDao, AppPrefs appPrefs) {
            UpdateableAppListItemController.this.showUndoSnackBar(appPrefsDao, appPrefs);
            AppUpdateStatusManager.getInstance(((AppListItemController) UpdateableAppListItemController.this).activity).checkForUpdates();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUndoSnackBar(final AppPrefsDao appPrefsDao, final AppPrefs appPrefs) {
        Snackbar.make(this.itemView, R.string.app_list__dismiss_app_update, 0).setAction(R.string.undo, new View.OnClickListener() { // from class: org.fdroid.fdroid.views.updates.items.UpdateableAppListItemController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showUndoSnackBar$2(appPrefs, appPrefsDao, view);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showUndoSnackBar$2(final AppPrefs appPrefs, final AppPrefsDao appPrefsDao, View view) {
        Utils.runOffUiThread(new Supplier() { // from class: org.fdroid.fdroid.views.updates.items.UpdateableAppListItemController$$ExternalSyntheticLambda1
            @Override // androidx.core.util.Supplier
            public final Object get() {
                return UpdateableAppListItemController.lambda$showUndoSnackBar$0(appPrefs, appPrefsDao);
            }
        }, new Consumer() { // from class: org.fdroid.fdroid.views.updates.items.UpdateableAppListItemController$$ExternalSyntheticLambda2
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$showUndoSnackBar$1((Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$showUndoSnackBar$0(AppPrefs appPrefs, AppPrefsDao appPrefsDao) {
        appPrefsDao.update(appPrefs.toggleIgnoreVersionCodeUpdate(0L));
        return Boolean.TRUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showUndoSnackBar$1(Boolean bool) {
        AppUpdateStatusManager.getInstance(this.activity).checkForUpdates();
    }
}
