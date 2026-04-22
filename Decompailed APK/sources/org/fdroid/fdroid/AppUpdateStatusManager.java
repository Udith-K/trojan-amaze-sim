package org.fdroid.fdroid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.core.util.Consumer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.fdroid.database.DbUpdateChecker;
import org.fdroid.database.Repository;
import org.fdroid.database.UpdatableApp;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.installer.ErrorDialogActivity;
import org.fdroid.fdroid.installer.InstallManagerService;
import org.fdroid.fdroid.net.DownloaderService;
import org.fdroid.fdroid.views.AppDetailsActivity;
import org.fdroid.index.RepoManager;

/* JADX INFO: loaded from: classes2.dex */
public final class AppUpdateStatusManager {
    public static final String BROADCAST_APPSTATUS_ADDED = "org.fdroid.fdroid.installer.appstatus.appchange.add";
    public static final String BROADCAST_APPSTATUS_CHANGED = "org.fdroid.fdroid.installer.appstatus.appchange.change";
    public static final String BROADCAST_APPSTATUS_LIST_CHANGED = "org.fdroid.fdroid.installer.appstatus.listchange";
    public static final String BROADCAST_APPSTATUS_REMOVED = "org.fdroid.fdroid.installer.appstatus.appchange.remove";
    public static final String EXTRA_IS_STATUS_UPDATE = "isstatusupdate";
    public static final String EXTRA_REASON_FOR_CHANGE = "reason";
    public static final String EXTRA_STATUS = "status";
    private static final String LOGTAG = "AppUpdateStatusManager";
    private static final String REASON_CLEAR_ALL_INSTALLED = "clearallinstalled";
    private static final String REASON_CLEAR_ALL_UPDATES = "clearallupdates";
    public static final String REASON_READY_TO_INSTALL = "readytoinstall";
    private static final String REASON_REPO_DISABLED = "repodisabled";
    public static final String REASON_UPDATES_AVAILABLE = "updatesavailable";
    public static final String TAG = "AppUpdateStatusManager";
    private static volatile AppUpdateStatusManager instance;
    private final Context context;
    private boolean isBatchUpdating;
    private final LocalBroadcastManager localBroadcastManager;
    private final RepoManager repoManager;
    private final DbUpdateChecker updateChecker;
    private final MutableLiveData numUpdatableApps = new MutableLiveData();
    private final HashMap<String, AppUpdateStatus> appMapping = new HashMap<>();
    private Disposable disposable = Utils.runOffUiThread(new AppUpdateStatusManager$$ExternalSyntheticLambda0(this), new Consumer() { // from class: org.fdroid.fdroid.AppUpdateStatusManager$$ExternalSyntheticLambda1
        @Override // androidx.core.util.Consumer
        public final void accept(Object obj) {
            this.f$0.addUpdatableAppsNoNotify((List) obj);
        }
    });

    public enum Status {
        PendingInstall,
        DownloadInterrupted,
        DownloadCancelled,
        UpdateAvailable,
        Downloading,
        ReadyToInstall,
        Installing,
        Installed,
        InstallError
    }

    public static AppUpdateStatusManager getInstance(Context context) {
        if (instance == null) {
            instance = new AppUpdateStatusManager(context.getApplicationContext());
        }
        return instance;
    }

    public static class AppUpdateStatus implements Parcelable {
        public static final Parcelable.Creator<AppUpdateStatus> CREATOR = new Parcelable.Creator<AppUpdateStatus>() { // from class: org.fdroid.fdroid.AppUpdateStatusManager.AppUpdateStatus.1
            @Override // android.os.Parcelable.Creator
            public AppUpdateStatus createFromParcel(Parcel parcel) {
                return new AppUpdateStatus(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public AppUpdateStatus[] newArray(int i) {
                return new AppUpdateStatus[i];
            }
        };
        public final Apk apk;
        public final App app;
        public String errorText;
        public PendingIntent intent;
        public long progressCurrent;
        public long progressMax;
        public Status status;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        AppUpdateStatus(App app, Apk apk, Status status, PendingIntent pendingIntent) {
            this.app = app;
            this.apk = apk;
            this.status = status;
            this.intent = pendingIntent;
        }

        public String getCanonicalUrl() {
            return this.apk.getCanonicalUrl();
        }

        public String toString() {
            return this.app.packageName + " [Status: " + this.status + ", Progress: " + this.progressCurrent + " / " + this.progressMax + ']';
        }

        protected AppUpdateStatus(Parcel parcel) {
            this.app = (App) parcel.readParcelable(getClass().getClassLoader());
            this.apk = (Apk) parcel.readParcelable(getClass().getClassLoader());
            this.intent = (PendingIntent) parcel.readParcelable(getClass().getClassLoader());
            this.status = (Status) parcel.readSerializable();
            this.progressCurrent = parcel.readLong();
            this.progressMax = parcel.readLong();
            this.errorText = parcel.readString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.app, 0);
            parcel.writeParcelable(this.apk, 0);
            parcel.writeParcelable(this.intent, 0);
            parcel.writeSerializable(this.status);
            parcel.writeLong(this.progressCurrent);
            parcel.writeLong(this.progressMax);
            parcel.writeString(this.errorText);
        }

        public AppUpdateStatus copy() {
            AppUpdateStatus appUpdateStatus = new AppUpdateStatus(this.app, this.apk, this.status, this.intent);
            appUpdateStatus.errorText = this.errorText;
            appUpdateStatus.progressCurrent = this.progressCurrent;
            appUpdateStatus.progressMax = this.progressMax;
            return appUpdateStatus;
        }

        public boolean equals(Object obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AppUpdateStatus appUpdateStatus = (AppUpdateStatus) obj;
            return this.progressCurrent == appUpdateStatus.progressCurrent && this.progressMax == appUpdateStatus.progressMax && Objects.equals(this.app, appUpdateStatus.app) && Objects.equals(this.apk, appUpdateStatus.apk) && this.status == appUpdateStatus.status && Objects.equals(this.intent, appUpdateStatus.intent) && Objects.equals(this.errorText, appUpdateStatus.errorText);
        }
    }

    private AppUpdateStatusManager(Context context) {
        this.context = context;
        this.localBroadcastManager = LocalBroadcastManager.getInstance(context.getApplicationContext());
        this.repoManager = FDroidApp.getRepoManager(context);
        this.updateChecker = new DbUpdateChecker(DBHelper.getDb(context), context.getPackageManager());
    }

    public void removeAllByRepo(long j) {
        Iterator<AppUpdateStatus> it = getAll().iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (it.next().apk.repoId == j) {
                it.remove();
                z = true;
            }
        }
        if (z) {
            notifyChange(REASON_REPO_DISABLED);
        }
    }

    public AppUpdateStatus get(String str) {
        AppUpdateStatus appUpdateStatus;
        synchronized (this.appMapping) {
            appUpdateStatus = this.appMapping.get(str);
        }
        return appUpdateStatus;
    }

    public Collection<AppUpdateStatus> getAll() {
        Collection<AppUpdateStatus> collectionValues;
        synchronized (this.appMapping) {
            collectionValues = this.appMapping.values();
        }
        return collectionValues;
    }

    public Collection<AppUpdateStatus> getByPackageName(String str) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.appMapping) {
            try {
                for (AppUpdateStatus appUpdateStatus : this.appMapping.values()) {
                    if (appUpdateStatus.apk.packageName.equals(str)) {
                        arrayList.add(appUpdateStatus);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return arrayList;
    }

    public String getInstallableVersion(String str) {
        for (AppUpdateStatus appUpdateStatus : getByPackageName(str)) {
            Status status = appUpdateStatus.status;
            if (status != Status.DownloadInterrupted && status != Status.Installed && status != Status.InstallError) {
                return appUpdateStatus.apk.versionName;
            }
        }
        return null;
    }

    public LiveData getNumUpdatableApps() {
        return this.numUpdatableApps;
    }

    private void setNumUpdatableApps(int i) {
        this.numUpdatableApps.postValue(Integer.valueOf(i));
    }

    private void updateApkInternal(AppUpdateStatus appUpdateStatus, Status status, PendingIntent pendingIntent) {
        String apkPath = appUpdateStatus.apk.getApkPath();
        if (status == Status.UpdateAvailable && appUpdateStatus.status.ordinal() > status.ordinal()) {
            Utils.debugLog("AppUpdateStatusManager", "Not updating APK " + apkPath + " state to " + status.name());
            return;
        }
        Utils.debugLog("AppUpdateStatusManager", "Update APK " + apkPath + " state to " + status.name());
        boolean z = appUpdateStatus.status != status;
        appUpdateStatus.status = status;
        appUpdateStatus.intent = pendingIntent;
        setEntryContentIntentIfEmpty(appUpdateStatus);
        notifyChange(appUpdateStatus, z);
        if (status == Status.Installed) {
            checkForUpdates();
        }
    }

    private void addApkInternal(App app, Apk apk, Status status, PendingIntent pendingIntent) {
        Utils.debugLog("AppUpdateStatusManager", "Add APK " + apk.getApkPath() + " with state " + status.name());
        AppUpdateStatus appUpdateStatusCreateAppEntry = createAppEntry(app, apk, status, pendingIntent);
        setEntryContentIntentIfEmpty(appUpdateStatusCreateAppEntry);
        this.appMapping.put(appUpdateStatusCreateAppEntry.getCanonicalUrl(), appUpdateStatusCreateAppEntry);
        notifyAdd(appUpdateStatusCreateAppEntry);
    }

    private void notifyChange(String str) {
        if (this.isBatchUpdating) {
            return;
        }
        Intent intent = new Intent(BROADCAST_APPSTATUS_LIST_CHANGED);
        intent.putExtra(EXTRA_REASON_FOR_CHANGE, str);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    private void notifyAdd(AppUpdateStatus appUpdateStatus) {
        if (this.isBatchUpdating) {
            return;
        }
        Intent intent = new Intent(BROADCAST_APPSTATUS_ADDED);
        intent.putExtra(DownloaderService.EXTRA_CANONICAL_URL, appUpdateStatus.getCanonicalUrl());
        intent.putExtra(EXTRA_STATUS, appUpdateStatus.copy());
        this.localBroadcastManager.sendBroadcast(intent);
    }

    private void notifyChange(AppUpdateStatus appUpdateStatus, boolean z) {
        if (this.isBatchUpdating) {
            return;
        }
        Intent intent = new Intent(BROADCAST_APPSTATUS_CHANGED);
        intent.putExtra(DownloaderService.EXTRA_CANONICAL_URL, appUpdateStatus.getCanonicalUrl());
        intent.putExtra(EXTRA_STATUS, appUpdateStatus.copy());
        intent.putExtra(EXTRA_IS_STATUS_UPDATE, z);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    private void notifyRemove(AppUpdateStatus appUpdateStatus) {
        if (this.isBatchUpdating) {
            return;
        }
        Intent intent = new Intent(BROADCAST_APPSTATUS_REMOVED);
        intent.putExtra(DownloaderService.EXTRA_CANONICAL_URL, appUpdateStatus.getCanonicalUrl());
        intent.putExtra(EXTRA_STATUS, appUpdateStatus.copy());
        this.localBroadcastManager.sendBroadcast(intent);
    }

    private AppUpdateStatus createAppEntry(App app, Apk apk, Status status, PendingIntent pendingIntent) {
        AppUpdateStatus appUpdateStatus;
        synchronized (this.appMapping) {
            appUpdateStatus = new AppUpdateStatus(app, apk, status, pendingIntent);
            this.appMapping.put(apk.getCanonicalUrl(), appUpdateStatus);
        }
        return appUpdateStatus;
    }

    public void checkForUpdates() {
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.disposable = Utils.runOffUiThread(new AppUpdateStatusManager$$ExternalSyntheticLambda0(this), new Consumer() { // from class: org.fdroid.fdroid.AppUpdateStatusManager$$ExternalSyntheticLambda2
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                this.f$0.addUpdatableApps((List) obj);
            }
        });
    }

    public void checkForUpdatesAndInstall() {
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.disposable = Utils.runOffUiThread(new AppUpdateStatusManager$$ExternalSyntheticLambda0(this), new Consumer() { // from class: org.fdroid.fdroid.AppUpdateStatusManager$$ExternalSyntheticLambda3
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$checkForUpdatesAndInstall$0((List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkForUpdatesAndInstall$0(List list) {
        addUpdatableApps(list);
        downloadUpdates(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<UpdatableApp> getUpdatableApps() {
        return this.updateChecker.getUpdatableApps(Preferences.get().getBackendReleaseChannels(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUpdatableApps(List<UpdatableApp> list) {
        if (list == null) {
            return;
        }
        if (list.size() > 0) {
            startBatchUpdates();
            for (UpdatableApp updatableApp : list) {
                addApk(new App(updatableApp), new Apk(updatableApp.getUpdate(), this.repoManager.getRepository(updatableApp.getUpdate().getRepoId())), Status.UpdateAvailable, null);
            }
            endBatchUpdates(Status.UpdateAvailable);
        }
        setNumUpdatableApps(list.size());
    }

    private void downloadUpdates(List<UpdatableApp> list) {
        String packageName = this.context.getPackageName();
        App app = null;
        Apk apk = null;
        for (UpdatableApp updatableApp : list) {
            Repository repository = this.repoManager.getRepository(updatableApp.getUpdate().getRepoId());
            if (repository != null) {
                if (TextUtils.equals(packageName, updatableApp.getUpdate().getPackageName())) {
                    app = new App(updatableApp);
                    apk = new Apk(updatableApp.getUpdate(), repository);
                } else {
                    InstallManagerService.queue(this.context, new App(updatableApp), new Apk(updatableApp.getUpdate(), repository));
                }
            }
        }
        if (app != null) {
            InstallManagerService.queue(this.context, app, apk);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUpdatableAppsNoNotify(List<UpdatableApp> list) {
        synchronized (this.appMapping) {
            try {
                this.isBatchUpdating = true;
                try {
                    int i = 0;
                    for (UpdatableApp updatableApp : list) {
                        Repository repository = this.repoManager.getRepository(updatableApp.getUpdate().getRepoId());
                        if (repository != null) {
                            addApk(new App(updatableApp), new Apk(updatableApp.getUpdate(), repository), Status.UpdateAvailable, null);
                            i++;
                        }
                    }
                    setNumUpdatableApps(i);
                    this.isBatchUpdating = false;
                } catch (Throwable th) {
                    this.isBatchUpdating = false;
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public void addApk(App app, Apk apk, Status status, PendingIntent pendingIntent) {
        if (apk == null) {
            return;
        }
        synchronized (this.appMapping) {
            try {
                AppUpdateStatus appUpdateStatus = this.appMapping.get(apk.getCanonicalUrl());
                if (appUpdateStatus != null) {
                    updateApkInternal(appUpdateStatus, status, pendingIntent);
                } else if (app != null) {
                    addApkInternal(app, apk, status, pendingIntent);
                } else {
                    Utils.debugLog("AppUpdateStatusManager", "Found no entry for " + apk.packageName + " and app was null.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void updateApk(String str, Status status, PendingIntent pendingIntent) {
        synchronized (this.appMapping) {
            try {
                AppUpdateStatus appUpdateStatus = this.appMapping.get(str);
                if (appUpdateStatus != null) {
                    updateApkInternal(appUpdateStatus, status, pendingIntent);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public App getApp(String str) {
        synchronized (this.appMapping) {
            try {
                AppUpdateStatus appUpdateStatus = this.appMapping.get(str);
                if (appUpdateStatus == null) {
                    return null;
                }
                return appUpdateStatus.app;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Apk getApk(String str) {
        synchronized (this.appMapping) {
            try {
                AppUpdateStatus appUpdateStatus = this.appMapping.get(str);
                if (appUpdateStatus == null) {
                    return null;
                }
                return appUpdateStatus.apk;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void removeApk(String str) {
        synchronized (this.appMapping) {
            try {
                AppUpdateStatus appUpdateStatusRemove = this.appMapping.remove(str);
                if (appUpdateStatusRemove != null) {
                    Utils.debugLog("AppUpdateStatusManager", "Remove APK " + appUpdateStatusRemove.apk.getApkPath());
                    notifyRemove(appUpdateStatusRemove);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void refreshApk(String str) {
        synchronized (this.appMapping) {
            try {
                AppUpdateStatus appUpdateStatus = this.appMapping.get(str);
                if (appUpdateStatus != null) {
                    Utils.debugLog("AppUpdateStatusManager", "Refresh APK " + appUpdateStatus.apk.getApkPath());
                    notifyChange(appUpdateStatus, true);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void updateApkProgress(String str, long j, long j2) {
        synchronized (this.appMapping) {
            try {
                AppUpdateStatus appUpdateStatus = this.appMapping.get(str);
                if (appUpdateStatus != null) {
                    appUpdateStatus.progressMax = j;
                    appUpdateStatus.progressCurrent = j2;
                    notifyChange(appUpdateStatus, false);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setDownloadError(String str, String str2) {
        synchronized (this.appMapping) {
            try {
                AppUpdateStatus appUpdateStatus = this.appMapping.get(str);
                if (appUpdateStatus != null) {
                    appUpdateStatus.status = Status.DownloadInterrupted;
                    appUpdateStatus.errorText = str2;
                    appUpdateStatus.intent = null;
                    notifyChange(appUpdateStatus, true);
                    removeApk(str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setDownloadCancelled(String str) {
        synchronized (this.appMapping) {
            try {
                AppUpdateStatus appUpdateStatus = this.appMapping.get(str);
                if (appUpdateStatus != null) {
                    appUpdateStatus.status = Status.DownloadCancelled;
                    appUpdateStatus.errorText = null;
                    appUpdateStatus.intent = null;
                    notifyChange(appUpdateStatus, true);
                    removeApk(str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setApkError(App app, Apk apk, String str) {
        synchronized (this.appMapping) {
            try {
                AppUpdateStatus appUpdateStatusCreateAppEntry = this.appMapping.get(apk.getCanonicalUrl());
                if (appUpdateStatusCreateAppEntry == null) {
                    appUpdateStatusCreateAppEntry = createAppEntry(app, apk, Status.InstallError, null);
                }
                appUpdateStatusCreateAppEntry.status = Status.InstallError;
                appUpdateStatusCreateAppEntry.errorText = str;
                appUpdateStatusCreateAppEntry.intent = getAppErrorIntent(appUpdateStatusCreateAppEntry);
                notifyChange(appUpdateStatusCreateAppEntry, false);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void startBatchUpdates() {
        synchronized (this.appMapping) {
            this.isBatchUpdating = true;
        }
    }

    private void endBatchUpdates(Status status) {
        String str;
        synchronized (this.appMapping) {
            try {
                this.isBatchUpdating = false;
                if (status == Status.ReadyToInstall) {
                    str = REASON_READY_TO_INSTALL;
                } else {
                    str = status == Status.UpdateAvailable ? REASON_UPDATES_AVAILABLE : null;
                }
                notifyChange(str);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void clearAllUpdates() {
        synchronized (this.appMapping) {
            try {
                Iterator<Map.Entry<String, AppUpdateStatus>> it = this.appMapping.entrySet().iterator();
                while (it.hasNext()) {
                    if (it.next().getValue().status != Status.Installed) {
                        it.remove();
                    }
                }
                notifyChange(REASON_CLEAR_ALL_UPDATES);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void clearAllInstalled() {
        synchronized (this.appMapping) {
            try {
                Iterator<Map.Entry<String, AppUpdateStatus>> it = this.appMapping.entrySet().iterator();
                while (it.hasNext()) {
                    if (it.next().getValue().status == Status.Installed) {
                        it.remove();
                    }
                }
                notifyChange(REASON_CLEAR_ALL_INSTALLED);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void setEntryContentIntentIfEmpty(AppUpdateStatus appUpdateStatus) {
        if (appUpdateStatus.intent != null) {
            return;
        }
        int i = AnonymousClass1.$SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[appUpdateStatus.status.ordinal()];
        if (i == 1 || i == 2) {
            appUpdateStatus.intent = getAppDetailsIntent(appUpdateStatus.apk);
            return;
        }
        if (i == 3) {
            appUpdateStatus.intent = getAppErrorIntent(appUpdateStatus);
            return;
        }
        if (i != 4) {
            return;
        }
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(appUpdateStatus.app.packageName);
        if (launchIntentForPackage != null) {
            appUpdateStatus.intent = PendingIntent.getActivity(this.context, 0, launchIntentForPackage, 67108864);
        } else {
            appUpdateStatus.intent = getAppDetailsIntent(appUpdateStatus.apk);
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.AppUpdateStatusManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status;

        static {
            int[] iArr = new int[Status.values().length];
            $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status = iArr;
            try {
                iArr[Status.UpdateAvailable.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[Status.ReadyToInstall.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[Status.InstallError.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$AppUpdateStatusManager$Status[Status.Installed.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private PendingIntent getAppDetailsIntent(Apk apk) {
        return TaskStackBuilder.create(this.context).addParentStack(AppDetailsActivity.class).addNextIntent(new Intent(this.context, (Class<?>) AppDetailsActivity.class).putExtra(AppDetailsActivity.EXTRA_APPID, apk.packageName)).getPendingIntent(apk.packageName.hashCode(), 201326592);
    }

    private PendingIntent getAppErrorIntent(AppUpdateStatus appUpdateStatus) {
        return PendingIntent.getActivity(this.context, 0, new Intent(this.context, (Class<?>) ErrorDialogActivity.class).putExtra("title", String.format(this.context.getString(R.string.install_error_notify_title), appUpdateStatus.app.name)).putExtra(ErrorDialogActivity.EXTRA_MESSAGE, appUpdateStatus.errorText), 201326592);
    }
}
