package org.fdroid.fdroid.views.apps;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Outline;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.core.util.Pair;
import androidx.core.util.Supplier;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.fdroid.database.AppVersion;
import org.fdroid.database.DbUpdateChecker;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.data.SanitizedFile;
import org.fdroid.fdroid.installer.ApkCache;
import org.fdroid.fdroid.installer.InstallManagerService;
import org.fdroid.fdroid.installer.Installer;
import org.fdroid.fdroid.installer.InstallerFactory;
import org.fdroid.fdroid.views.AppDetailsActivity;
import org.fdroid.fdroid.views.updates.UpdatesAdapter;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AppListItemController extends RecyclerView.ViewHolder {
    private static final String TAG = "AppListItemController";
    private static Preferences prefs;
    private final Button actionButton;
    protected final AppCompatActivity activity;
    private final ImageButton cancelButton;
    private final CheckBox checkBox;
    private Apk currentApk;
    private App currentApp;
    private AppUpdateStatusManager.AppUpdateStatus currentStatus;
    private Disposable disposable;
    private final ImageView icon;
    private final ImageView installButton;
    private final TextView name;
    private final View.OnClickListener onAppClicked;
    private final View.OnClickListener onCancelDownload;
    private final View.OnClickListener onSecondaryButtonClicked;
    private final BroadcastReceiver onStatusChanged;
    private final LinearProgressIndicator progressBar;
    private final Button secondaryButton;
    private final TextView secondaryStatus;
    private final View.OnClickListener selectInstalledAppListener;
    private final TextView status;

    /* JADX INFO: Access modifiers changed from: private */
    public void onSecondaryButtonPressed() {
    }

    public boolean canDismiss() {
        return false;
    }

    protected void onDismissApp(App app, UpdatesAdapter updatesAdapter) {
    }

    public AppListItemController(final AppCompatActivity appCompatActivity, View view) {
        super(view);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: org.fdroid.fdroid.views.apps.AppListItemController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (AppListItemController.this.currentApp == null) {
                    return;
                }
                Intent intent = new Intent(AppListItemController.this.activity, (Class<?>) AppDetailsActivity.class);
                intent.putExtra(AppDetailsActivity.EXTRA_APPID, AppListItemController.this.currentApp.packageName);
                ContextCompat.startActivity(AppListItemController.this.activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(AppListItemController.this.activity, Pair.create(AppListItemController.this.icon, AppListItemController.this.activity.getString(R.string.transition_app_item_icon))).toBundle());
            }
        };
        this.onAppClicked = onClickListener;
        this.onStatusChanged = new BroadcastReceiver() { // from class: org.fdroid.fdroid.views.apps.AppListItemController.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                AppUpdateStatusManager.AppUpdateStatus appUpdateStatus = (AppUpdateStatusManager.AppUpdateStatus) intent.getParcelableExtra(AppUpdateStatusManager.EXTRA_STATUS);
                if (AppListItemController.this.currentApp == null || !TextUtils.equals(appUpdateStatus.app.packageName, AppListItemController.this.currentApp.packageName)) {
                    return;
                }
                if (AppListItemController.this.installButton == null && AppListItemController.this.progressBar == null) {
                    return;
                }
                AppListItemController appListItemController = AppListItemController.this;
                appListItemController.updateAppStatus(appListItemController.currentApp, appUpdateStatus);
            }
        };
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: org.fdroid.fdroid.views.apps.AppListItemController.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (AppListItemController.this.currentApp == null) {
                    return;
                }
                if (AppListItemController.this.secondaryButton != null) {
                    AppListItemController.this.secondaryButton.setEnabled(false);
                }
                AppListItemController.this.onSecondaryButtonPressed();
            }
        };
        this.onSecondaryButtonClicked = onClickListener2;
        View.OnClickListener onClickListener3 = new View.OnClickListener() { // from class: org.fdroid.fdroid.views.apps.AppListItemController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$new$4(view2);
            }
        };
        this.onCancelDownload = onClickListener3;
        this.selectInstalledAppListener = new View.OnClickListener() { // from class: org.fdroid.fdroid.views.apps.AppListItemController.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Set<String> panicTmpSelectedSet = AppListItemController.prefs.getPanicTmpSelectedSet();
                AppListItemController.this.checkBox.toggle();
                if (AppListItemController.this.checkBox.isChecked()) {
                    panicTmpSelectedSet.add(AppListItemController.this.currentApp.packageName);
                } else {
                    panicTmpSelectedSet.remove(AppListItemController.this.currentApp.packageName);
                }
                AppListItemController.prefs.setPanicTmpSelectedSet(panicTmpSelectedSet);
            }
        };
        this.activity = appCompatActivity;
        if (prefs == null) {
            prefs = Preferences.get();
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.install);
        this.installButton = imageView;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.apps.AppListItemController$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$new$0(view2);
                }
            });
            imageView.setOutlineProvider(new ViewOutlineProvider() { // from class: org.fdroid.fdroid.views.apps.AppListItemController.1
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view2, Outline outline) {
                    float f = appCompatActivity.getResources().getDisplayMetrics().density;
                    int i = (int) (8.0f * f);
                    int i2 = (int) (f * 9.0f);
                    outline.setOval(i, i2, AppListItemController.this.installButton.getWidth() - i, AppListItemController.this.installButton.getHeight() - i2);
                }
            });
        }
        this.icon = (ImageView) view.findViewById(R.id.icon);
        this.name = (TextView) view.findViewById(R.id.app_name);
        this.status = (TextView) view.findViewById(R.id.status);
        this.secondaryStatus = (TextView) view.findViewById(R.id.secondary_status);
        this.progressBar = (LinearProgressIndicator) view.findViewById(R.id.progress_bar);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.cancel_button);
        this.cancelButton = imageButton;
        Button button = (Button) view.findViewById(R.id.action_button);
        this.actionButton = button;
        Button button2 = (Button) view.findViewById(R.id.secondary_button);
        this.secondaryButton = button2;
        this.checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        if (button != null) {
            button.setEnabled(true);
            button.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.apps.AppListItemController$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$new$1(view2);
                }
            });
        }
        if (button2 != null) {
            button2.setOnClickListener(onClickListener2);
        }
        if (imageButton != null) {
            imageButton.setOnClickListener(onClickListener3);
        }
        view.setOnClickListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        onActionButtonPressed(this.currentApp, this.currentApk);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(View view) {
        this.actionButton.setEnabled(false);
        onActionButtonPressed(this.currentApp, this.currentApk);
    }

    protected final AppUpdateStatusManager.AppUpdateStatus getCurrentStatus() {
        return this.currentStatus;
    }

    public void bindModel(App app, Apk apk, AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        this.currentApp = app;
        this.currentApk = apk;
        Button button = this.actionButton;
        if (button != null) {
            button.setEnabled(true);
        }
        Utils.setIconFromRepoOrPM(app, this.icon, this.activity);
        if (appUpdateStatus == null) {
            Iterator<AppUpdateStatusManager.AppUpdateStatus> it = AppUpdateStatusManager.getInstance(this.activity).getByPackageName(app.packageName).iterator();
            if (it.hasNext()) {
                appUpdateStatus = it.next();
            }
        }
        updateAppStatus(app, appUpdateStatus);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this.activity.getApplicationContext());
        localBroadcastManager.unregisterReceiver(this.onStatusChanged);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppUpdateStatusManager.BROADCAST_APPSTATUS_ADDED);
        intentFilter.addAction(AppUpdateStatusManager.BROADCAST_APPSTATUS_REMOVED);
        intentFilter.addAction(AppUpdateStatusManager.BROADCAST_APPSTATUS_CHANGED);
        localBroadcastManager.registerReceiver(this.onStatusChanged, intentFilter);
    }

    void hideInstallButton() {
        ImageView imageView = this.installButton;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    public final void onDismiss(UpdatesAdapter updatesAdapter) {
        if (this.currentApp == null || !canDismiss()) {
            return;
        }
        onDismissApp(this.currentApp, updatesAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAppStatus(App app, AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        this.currentStatus = appUpdateStatus;
        AppListItemState currentViewState = getCurrentViewState(app, appUpdateStatus);
        this.name.setText(currentViewState.getMainText());
        if (this.actionButton != null) {
            if (currentViewState.shouldShowActionButton()) {
                this.actionButton.setVisibility(0);
                this.actionButton.setEnabled(true);
                this.actionButton.setText(currentViewState.getActionButtonText());
            } else {
                this.actionButton.setVisibility(8);
            }
        }
        if (this.secondaryButton != null) {
            if (currentViewState.shouldShowSecondaryButton()) {
                this.secondaryButton.setVisibility(0);
                this.secondaryButton.setEnabled(true);
                this.secondaryButton.setText(currentViewState.getSecondaryButtonText());
            } else {
                this.secondaryButton.setVisibility(8);
            }
        }
        if (this.progressBar != null) {
            if (currentViewState.showProgress()) {
                if (currentViewState.isProgressIndeterminate()) {
                    if (!this.progressBar.isIndeterminate()) {
                        this.progressBar.hide();
                        this.progressBar.setIndeterminate(true);
                    }
                } else {
                    this.progressBar.setProgressCompat(Utils.getPercent(currentViewState.getProgressCurrent(), currentViewState.getProgressMax()), true);
                }
                this.progressBar.show();
            } else {
                this.progressBar.hide();
            }
        }
        if (this.cancelButton != null) {
            if (currentViewState.showProgress()) {
                this.cancelButton.setVisibility(0);
            } else {
                this.cancelButton.setVisibility(8);
            }
        }
        if (this.installButton != null) {
            if (currentViewState.shouldShowActionButton()) {
                this.installButton.setVisibility(8);
            } else if (currentViewState.showProgress()) {
                this.installButton.setEnabled(false);
                this.installButton.setVisibility(0);
                this.installButton.setImageDrawable(ContextCompat.getDrawable(this.activity, R.drawable.ic_download_progress));
                this.installButton.setImageLevel(currentViewState.getProgressMax() <= 0 ? 0 : (int) ((currentViewState.getProgressCurrent() / currentViewState.getProgressMax()) * 360.0f));
            } else if (currentViewState.shouldShowInstall()) {
                this.installButton.setEnabled(true);
                this.installButton.setVisibility(0);
                this.installButton.setImageDrawable(ContextCompat.getDrawable(this.activity, R.drawable.ic_download));
            } else {
                this.installButton.setVisibility(8);
            }
        }
        if (this.status != null) {
            CharSequence statusText = currentViewState.getStatusText();
            if (statusText == null) {
                this.status.setVisibility(8);
            } else {
                this.status.setVisibility(0);
                this.status.setText(statusText);
            }
        }
        if (this.secondaryStatus != null) {
            CharSequence secondaryStatusText = currentViewState.getSecondaryStatusText();
            if (secondaryStatusText == null) {
                this.secondaryStatus.setVisibility(8);
            } else {
                this.secondaryStatus.setVisibility(0);
                this.secondaryStatus.setText(secondaryStatusText);
            }
        }
        if (this.checkBox != null) {
            if (currentViewState.shouldShowCheckBox()) {
                this.itemView.setOnClickListener(this.selectInstalledAppListener);
                this.checkBox.setChecked(currentViewState.isCheckBoxChecked());
                this.checkBox.setVisibility(0);
                this.status.setVisibility(8);
                this.secondaryStatus.setVisibility(8);
                return;
            }
            this.checkBox.setVisibility(8);
        }
    }

    protected AppListItemState getCurrentViewState(App app, AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        if (appUpdateStatus == null) {
            return getViewStateDefault(app);
        }
        int i = AnonymousClass7.$SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[appUpdateStatus.status.ordinal()];
        if (i == 1) {
            return getViewStateReadyToInstall(app);
        }
        if (i == 2 || i == 3) {
            return getViewStateDownloading(app, appUpdateStatus);
        }
        if (i == 4) {
            return getViewStateInstalling(app);
        }
        if (i == 5) {
            return getViewStateInstalled(app);
        }
        return getViewStateDefault(app);
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.apps.AppListItemController$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status;

        static {
            int[] iArr = new int[AppUpdateStatusManager.Status.values().length];
            $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status = iArr;
            try {
                iArr[AppUpdateStatusManager.Status.ReadyToInstall.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.PendingInstall.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.Downloading.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.Installing.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[AppUpdateStatusManager.Status.Installed.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private AppListItemState getViewStateInstalling(App app) {
        return new AppListItemState(app).setMainText(this.activity.getString(R.string.app_list__name__downloading_in_progress, app.name)).showActionButton(null).setStatusText(this.activity.getString(R.string.notification_content_single_installing, app.name));
    }

    private AppListItemState getViewStateInstalled(App app) {
        AppListItemState statusText = new AppListItemState(app).setMainText(this.activity.getString(R.string.app_list__name__successfully_installed, app.name)).setStatusText(this.activity.getString(R.string.notification_content_single_installed));
        if (this.activity.getPackageManager().getLaunchIntentForPackage(app.packageName) != null) {
            Utils.debugLog(TAG, "Not showing 'Open' button for " + app.packageName + " because no intent.");
            statusText.showActionButton(this.activity.getString(R.string.menu_launch));
        }
        return statusText;
    }

    private AppListItemState getViewStateDownloading(App app, AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        return new AppListItemState(app).setMainText(this.activity.getString(R.string.app_list__name__downloading_in_progress, app.name)).setProgress(Utils.bytesToKb(appUpdateStatus.progressCurrent), Utils.bytesToKb(appUpdateStatus.progressMax));
    }

    private AppListItemState getViewStateReadyToInstall(App app) {
        int i;
        if (app.isInstalled(this.activity.getApplicationContext())) {
            i = R.string.app__install_downloaded_update;
        } else {
            i = R.string.menu_install;
        }
        return new AppListItemState(app).setMainText(app.name).showActionButton(this.activity.getString(i)).setStatusText(this.activity.getString(R.string.app_list_download_ready));
    }

    private AppListItemState getViewStateDefault(App app) {
        return new AppListItemState(app);
    }

    protected void onActionButtonPressed(final App app, Apk apk) {
        if (app == null) {
            return;
        }
        AppUpdateStatusManager.AppUpdateStatus appUpdateStatus = this.currentStatus;
        if (appUpdateStatus != null && appUpdateStatus.status == AppUpdateStatusManager.Status.Installed) {
            Intent launchIntentForPackage = this.activity.getPackageManager().getLaunchIntentForPackage(app.packageName);
            if (launchIntentForPackage != null) {
                try {
                    this.activity.startActivity(launchIntentForPackage);
                } catch (SecurityException e) {
                    Log.e(TAG, "Error starting app launch intent: ", e);
                    Toast.makeText(this.activity, R.string.app_error_open, 0).show();
                }
                AppUpdateStatusManager.getInstance(this.activity).removeApk(this.currentStatus.getCanonicalUrl());
                return;
            }
            return;
        }
        final LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this.activity);
        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: org.fdroid.fdroid.views.apps.AppListItemController.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (Installer.ACTION_INSTALL_USER_INTERACTION.equals(intent.getAction())) {
                    localBroadcastManager.unregisterReceiver(this);
                    try {
                        ((PendingIntent) intent.getParcelableExtra(Installer.EXTRA_USER_INTERACTION_PI)).send();
                    } catch (PendingIntent.CanceledException e2) {
                        Log.e(AppListItemController.TAG, "Error starting pending intent: ", e2);
                    }
                }
            }
        };
        AppUpdateStatusManager.AppUpdateStatus appUpdateStatus2 = this.currentStatus;
        if (appUpdateStatus2 != null && appUpdateStatus2.status == AppUpdateStatusManager.Status.ReadyToInstall) {
            String canonicalUrl = appUpdateStatus2.apk.getCanonicalUrl();
            SanitizedFile apkDownloadPath = ApkCache.getApkDownloadPath(this.activity, canonicalUrl);
            Utils.debugLog(TAG, "skip download, we have already downloaded " + this.currentStatus.apk.getCanonicalUrl() + " to " + apkDownloadPath);
            Uri uri = Uri.parse(canonicalUrl);
            localBroadcastManager.registerReceiver(broadcastReceiver, Installer.getInstallInteractionIntentFilter(uri));
            AppCompatActivity appCompatActivity = this.activity;
            AppUpdateStatusManager.AppUpdateStatus appUpdateStatus3 = this.currentStatus;
            InstallerFactory.create(appCompatActivity, appUpdateStatus3.app, appUpdateStatus3.apk).installPackage(Uri.parse(apkDownloadPath.toURI().toString()), uri);
            return;
        }
        final DbUpdateChecker dbUpdateChecker = new DbUpdateChecker(DBHelper.getDb(this.activity), this.activity.getPackageManager());
        final List<String> backendReleaseChannels = Preferences.get().getBackendReleaseChannels();
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.disposable = Utils.runOffUiThread(new Supplier() { // from class: org.fdroid.fdroid.views.apps.AppListItemController$$ExternalSyntheticLambda3
            @Override // androidx.core.util.Supplier
            public final Object get() {
                return this.f$0.lambda$onActionButtonPressed$2(dbUpdateChecker, app, backendReleaseChannels);
            }
        }, new Consumer() { // from class: org.fdroid.fdroid.views.apps.AppListItemController$$ExternalSyntheticLambda4
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onActionButtonPressed$3(localBroadcastManager, broadcastReceiver, app, (Apk) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Apk lambda$onActionButtonPressed$2(DbUpdateChecker dbUpdateChecker, App app, List list) throws PackageManager.NameNotFoundException {
        AppVersion suggestedVersion = dbUpdateChecker.getSuggestedVersion(app.packageName, app.preferredSigner, list, true);
        if (suggestedVersion == null) {
            return new Apk();
        }
        Repository repository = FDroidApp.getRepoManager(this.activity).getRepository(suggestedVersion.getRepoId());
        if (repository == null) {
            return new Apk();
        }
        return new Apk(suggestedVersion, repository);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onActionButtonPressed$3(LocalBroadcastManager localBroadcastManager, BroadcastReceiver broadcastReceiver, App app, Apk apk) {
        if (apk.packageName == null) {
            Toast.makeText(this.activity, R.string.app_list_no_suggested_version, 0).show();
        } else {
            localBroadcastManager.registerReceiver(broadcastReceiver, Installer.getInstallInteractionIntentFilter(Uri.parse(apk.getCanonicalUrl())));
            InstallManagerService.queue(this.activity, app, apk);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$4(View view) {
        cancelDownload();
    }

    protected final void cancelDownload() {
        AppUpdateStatusManager.AppUpdateStatus appUpdateStatus = this.currentStatus;
        if (appUpdateStatus == null || appUpdateStatus.status != AppUpdateStatusManager.Status.Downloading) {
            return;
        }
        InstallManagerService.cancel(this.activity, appUpdateStatus.getCanonicalUrl());
    }
}
