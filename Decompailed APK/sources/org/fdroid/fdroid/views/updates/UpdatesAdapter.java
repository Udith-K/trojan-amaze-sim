package org.fdroid.fdroid.views.updates;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.core.util.Supplier;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import org.fdroid.database.DbUpdateChecker;
import org.fdroid.database.UpdatableApp;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.views.updates.items.AppStatus;
import org.fdroid.fdroid.views.updates.items.AppUpdateData;
import org.fdroid.fdroid.views.updates.items.KnownVulnApp;
import org.fdroid.fdroid.views.updates.items.UpdateableApp;
import org.fdroid.fdroid.views.updates.items.UpdateableAppsHeader;

/* JADX INFO: loaded from: classes2.dex */
public class UpdatesAdapter extends RecyclerView.Adapter {
    private static final boolean DEFAULT_SHOW_ALL_UPDATEABLE_APPS = false;
    private static final String PREF_SHOW_ALL_UPDATEABLE_APPS = "showAllUpdateableApps";
    private final AppCompatActivity activity;
    private final List<AppStatus> appsToShowStatus;
    private final AdapterDelegatesManager delegatesManager;
    private Disposable disposable;
    private final List<AppUpdateData> items;
    private final List<KnownVulnApp> knownVulnApps;
    private final SharedPreferences preferences;
    private final BroadcastReceiver receiverAppStatusChanges;
    private boolean showAllUpdateableApps;
    private final DbUpdateChecker updateChecker;
    private final List<UpdateableApp> updateableApps;

    UpdatesAdapter(AppCompatActivity appCompatActivity) {
        AdapterDelegatesManager adapterDelegatesManager = new AdapterDelegatesManager();
        this.delegatesManager = adapterDelegatesManager;
        this.items = new ArrayList();
        this.appsToShowStatus = new ArrayList();
        this.updateableApps = new ArrayList();
        this.knownVulnApps = new ArrayList();
        this.receiverAppStatusChanges = new BroadcastReceiver() { // from class: org.fdroid.fdroid.views.updates.UpdatesAdapter.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                }
                String action = intent.getAction();
                action.hashCode();
                switch (action) {
                    case "org.fdroid.fdroid.installer.appstatus.listchange":
                        UpdatesAdapter.this.onManyAppStatusesChanged(intent.getStringExtra(AppUpdateStatusManager.EXTRA_REASON_FOR_CHANGE));
                        break;
                    case "org.fdroid.fdroid.installer.appstatus.appchange.add":
                        UpdatesAdapter.this.onAppStatusAdded();
                        break;
                    case "org.fdroid.fdroid.installer.appstatus.appchange.change":
                        if (intent.getBooleanExtra(AppUpdateStatusManager.EXTRA_IS_STATUS_UPDATE, false)) {
                            UpdatesAdapter.this.refreshItems();
                            break;
                        }
                        break;
                    case "org.fdroid.fdroid.installer.appstatus.appchange.remove":
                        UpdatesAdapter.this.onAppStatusRemoved();
                        break;
                }
            }
        };
        this.activity = appCompatActivity;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(appCompatActivity);
        this.preferences = defaultSharedPreferences;
        this.showAllUpdateableApps = defaultSharedPreferences.getBoolean(PREF_SHOW_ALL_UPDATEABLE_APPS, false);
        adapterDelegatesManager.addDelegate(new AppStatus.Delegate(appCompatActivity)).addDelegate(new UpdateableApp.Delegate(appCompatActivity)).addDelegate(new UpdateableAppsHeader.Delegate(appCompatActivity)).addDelegate(new KnownVulnApp.Delegate(appCompatActivity, new Runnable() { // from class: org.fdroid.fdroid.views.updates.UpdatesAdapter$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.loadUpdatableApps();
            }
        }));
        this.updateChecker = new DbUpdateChecker(DBHelper.getDb(appCompatActivity), appCompatActivity.getPackageManager());
        loadUpdatableApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadUpdatableApps() {
        final List<String> backendReleaseChannels = Preferences.get().getBackendReleaseChannels();
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.disposable = Utils.runOffUiThread(new Supplier() { // from class: org.fdroid.fdroid.views.updates.UpdatesAdapter$$ExternalSyntheticLambda0
            @Override // androidx.core.util.Supplier
            public final Object get() {
                return this.f$0.lambda$loadUpdatableApps$0(backendReleaseChannels);
            }
        }, new Consumer() { // from class: org.fdroid.fdroid.views.updates.UpdatesAdapter$$ExternalSyntheticLambda1
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                this.f$0.onCanUpdateLoadFinished((List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$loadUpdatableApps$0(List list) {
        return this.updateChecker.getUpdatableApps(list, true, true);
    }

    public boolean canViewAllUpdateableApps() {
        return this.showAllUpdateableApps;
    }

    public void toggleAllUpdateableApps() {
        this.showAllUpdateableApps = !this.showAllUpdateableApps;
        this.preferences.edit().putBoolean(PREF_SHOW_ALL_UPDATEABLE_APPS, this.showAllUpdateableApps).apply();
        refreshItems();
    }

    private boolean shouldShowStatus(AppUpdateStatusManager.AppUpdateStatus appUpdateStatus) {
        AppUpdateStatusManager.Status status = appUpdateStatus.status;
        return status == AppUpdateStatusManager.Status.PendingInstall || status == AppUpdateStatusManager.Status.Downloading || status == AppUpdateStatusManager.Status.Installing || status == AppUpdateStatusManager.Status.Installed || status == AppUpdateStatusManager.Status.ReadyToInstall;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCanUpdateLoadFinished(List<UpdatableApp> list) {
        this.updateableApps.clear();
        this.knownVulnApps.clear();
        for (UpdatableApp updatableApp : list) {
            App app = new App(updatableApp);
            Apk apk = new Apk(updatableApp.getUpdate(), FDroidApp.getRepoManager(this.activity).getRepository(updatableApp.getUpdate().getRepoId()));
            if (updatableApp.getHasKnownVulnerability()) {
                app.installedApk = apk;
                this.knownVulnApps.add(new KnownVulnApp(this.activity, app, apk));
            } else {
                this.updateableApps.add(new UpdateableApp(this.activity, app, apk));
            }
        }
        refreshItems();
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void refreshItems() {
        populateAppStatuses();
        populateItems();
        notifyDataSetChanged();
    }

    private void populateAppStatuses() {
        this.appsToShowStatus.clear();
        for (AppUpdateStatusManager.AppUpdateStatus appUpdateStatus : AppUpdateStatusManager.getInstance(this.activity).getAll()) {
            if (shouldShowStatus(appUpdateStatus)) {
                this.appsToShowStatus.add(new AppStatus(this.activity, appUpdateStatus));
            }
        }
        Collections.sort(this.appsToShowStatus, new Comparator() { // from class: org.fdroid.fdroid.views.updates.UpdatesAdapter$$ExternalSyntheticLambda2
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return UpdatesAdapter.lambda$populateAppStatuses$1((AppStatus) obj, (AppStatus) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$populateAppStatuses$1(AppStatus appStatus, AppStatus appStatus2) {
        return appStatus.status.app.name.compareTo(appStatus2.status.app.name);
    }

    private void populateItems() {
        this.items.clear();
        HashSet hashSet = new HashSet(this.appsToShowStatus.size());
        for (AppStatus appStatus : this.appsToShowStatus) {
            this.items.add(appStatus);
            hashSet.add(appStatus.status.getCanonicalUrl());
        }
        ArrayList arrayList = new ArrayList(this.updateableApps.size());
        for (UpdateableApp updateableApp : this.updateableApps) {
            if (!hashSet.contains(updateableApp.apk.getCanonicalUrl())) {
                arrayList.add(updateableApp);
            }
        }
        if (arrayList.size() > 0) {
            this.items.add(new UpdateableAppsHeader(this.activity, this, arrayList));
            if (this.showAllUpdateableApps) {
                this.items.addAll(arrayList);
            }
        }
        this.items.addAll(this.knownVulnApps);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.delegatesManager.getItemViewType(this.items, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.delegatesManager.onCreateViewHolder(viewGroup, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        this.delegatesManager.onBindViewHolder(this.items, i, viewHolder);
    }

    void setIsActive() {
        loadUpdatableApps();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppUpdateStatusManager.BROADCAST_APPSTATUS_ADDED);
        intentFilter.addAction(AppUpdateStatusManager.BROADCAST_APPSTATUS_REMOVED);
        intentFilter.addAction(AppUpdateStatusManager.BROADCAST_APPSTATUS_CHANGED);
        intentFilter.addAction(AppUpdateStatusManager.BROADCAST_APPSTATUS_LIST_CHANGED);
        LocalBroadcastManager.getInstance(this.activity).registerReceiver(this.receiverAppStatusChanges, intentFilter);
    }

    void stopListeningForStatusUpdates() {
        LocalBroadcastManager.getInstance(this.activity).unregisterReceiver(this.receiverAppStatusChanges);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onManyAppStatusesChanged(String str) {
        str.hashCode();
        if (str.equals(AppUpdateStatusManager.REASON_UPDATES_AVAILABLE)) {
            onUpdateableAppsChanged();
        } else if (str.equals(AppUpdateStatusManager.REASON_READY_TO_INSTALL)) {
            onFoundAppsReadyToInstall();
        }
    }

    private void onUpdateableAppsChanged() {
        loadUpdatableApps();
    }

    private void onFoundAppsReadyToInstall() {
        refreshItems();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAppStatusAdded() {
        refreshItems();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAppStatusRemoved() {
        loadUpdatableApps();
    }
}
