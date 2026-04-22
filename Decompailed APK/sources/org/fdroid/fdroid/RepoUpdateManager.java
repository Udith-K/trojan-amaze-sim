package org.fdroid.fdroid;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.core.os.LocaleListCompat;
import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.LiveData;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.Action;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.fdroid.CompatibilityCheckerImpl;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.net.DownloaderFactory;
import org.fdroid.fdroid.work.RepoUpdateWorker;
import org.fdroid.index.IndexUpdateListener;
import org.fdroid.index.IndexUpdateResult;
import org.fdroid.index.RepoManager;
import org.fdroid.index.RepoUpdater;
import org.fdroid.index.RepoUriBuilder;
import org.fdroid.index.TempFileProvider;
import org.fdroid.index.v1.IndexV1Updater;

/* JADX INFO: compiled from: RepoUpdateManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\b\u0010)\u001a\u00020*H\u0007J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0018H\u0007J&\u0010.\u001a\u00020*2\u001c\u0010/\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u000202\u0012\b\u0012\u000603j\u0002`40100H\u0002J \u00105\u001a\u00020*2\u0006\u00106\u001a\u0002022\u0006\u00107\u001a\u00020\u00182\u0006\u00108\u001a\u00020\u0018H\u0016J \u00109\u001a\u00020*2\u0006\u00106\u001a\u0002022\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010%\u001a\n '*\u0004\u0018\u00010&0&X\u0082\u0004¢\u0006\u0004\n\u0002\u0010(¨\u0006="}, d2 = {"Lorg/fdroid/fdroid/RepoUpdateManager;", "Lorg/fdroid/index/IndexUpdateListener;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "db", "Lorg/fdroid/database/FDroidDatabase;", "repoManager", "Lorg/fdroid/index/RepoManager;", "notificationManager", "Lorg/fdroid/fdroid/NotificationManager;", "forceIndexV1", "", "<init>", "(Landroid/content/Context;Lorg/fdroid/database/FDroidDatabase;Lorg/fdroid/index/RepoManager;Lorg/fdroid/fdroid/NotificationManager;Z)V", "_isUpdating", "Lkotlinx/coroutines/flow/MutableStateFlow;", "isUpdating", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "isUpdatingLiveData", "Landroidx/lifecycle/LiveData;", "()Landroidx/lifecycle/LiveData;", "nextUpdateFlow", "Lkotlinx/coroutines/flow/Flow;", "", "getNextUpdateFlow", "()Lkotlinx/coroutines/flow/Flow;", "nextUpdateLiveData", "getNextUpdateLiveData", "uriBuilder", "Lorg/fdroid/index/RepoUriBuilder;", "compatibilityChecker", "Lorg/fdroid/CompatibilityCheckerImpl;", "repoUpdater", "Lorg/fdroid/index/RepoUpdater;", "indexV1Updater", "Lorg/fdroid/index/v1/IndexV1Updater;", "fdroidPrefs", "Lorg/fdroid/fdroid/Preferences;", "kotlin.jvm.PlatformType", "Lorg/fdroid/fdroid/Preferences;", "updateRepos", "", "updateRepo", "Lorg/fdroid/index/IndexUpdateResult;", "repoId", "showRepoErrors", "repoErrors", "", "Lkotlin/Pair;", "Lorg/fdroid/database/Repository;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onDownloadProgress", "repo", "bytesRead", "totalBytes", "onUpdateProgress", "appsProcessed", "", "totalApps", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RepoUpdateManager implements IndexUpdateListener {
    public static final int $stable = 8;
    private final MutableStateFlow _isUpdating;
    private final CompatibilityCheckerImpl compatibilityChecker;
    private final Context context;
    private final FDroidDatabase db;
    private final Preferences fdroidPrefs;
    private final IndexV1Updater indexV1Updater;
    private final StateFlow isUpdating;
    private final LiveData isUpdatingLiveData;
    private final Flow nextUpdateFlow;
    private final LiveData nextUpdateLiveData;
    private final NotificationManager notificationManager;
    private final RepoManager repoManager;
    private final RepoUpdater repoUpdater;
    private final RepoUriBuilder uriBuilder;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RepoUpdateManager(Context context, FDroidDatabase db) {
        this(context, db, null, null, false, 28, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(db, "db");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RepoUpdateManager(Context context, FDroidDatabase db, RepoManager repoManager) {
        this(context, db, repoManager, null, false, 24, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(repoManager, "repoManager");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RepoUpdateManager(Context context, FDroidDatabase db, RepoManager repoManager, NotificationManager notificationManager) {
        this(context, db, repoManager, notificationManager, false, 16, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(repoManager, "repoManager");
        Intrinsics.checkNotNullParameter(notificationManager, "notificationManager");
    }

    public RepoUpdateManager(Context context, FDroidDatabase db, RepoManager repoManager, NotificationManager notificationManager, boolean z) {
        IndexV1Updater indexV1Updater;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(repoManager, "repoManager");
        Intrinsics.checkNotNullParameter(notificationManager, "notificationManager");
        this.context = context;
        this.db = db;
        this.repoManager = repoManager;
        this.notificationManager = notificationManager;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._isUpdating = MutableStateFlow;
        this.isUpdating = FlowKt.asStateFlow(MutableStateFlow);
        this.isUpdatingLiveData = FlowLiveDataConversions.asLiveData$default(MutableStateFlow, null, 0L, 3, null);
        final Flow autoUpdateWorkInfo = RepoUpdateWorker.INSTANCE.getAutoUpdateWorkInfo(context);
        Flow flow = new Flow() { // from class: org.fdroid.fdroid.RepoUpdateManager$special$$inlined$map$1

            /* JADX INFO: renamed from: org.fdroid.fdroid.RepoUpdateManager$special$$inlined$map$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", Action.VALUE_ATTRIBUTE, "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 0, 0})
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: org.fdroid.fdroid.RepoUpdateManager$special$$inlined$map$1$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "org.fdroid.fdroid.RepoUpdateManager$special$$inlined$map$1$2", f = "RepoUpdateManager.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof org.fdroid.fdroid.RepoUpdateManager$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        org.fdroid.fdroid.RepoUpdateManager$special$$inlined$map$1$2$1 r0 = (org.fdroid.fdroid.RepoUpdateManager$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        org.fdroid.fdroid.RepoUpdateManager$special$$inlined$map$1$2$1 r0 = new org.fdroid.fdroid.RepoUpdateManager$special$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L51
                    L29:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L31:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow
                        androidx.work.WorkInfo r7 = (androidx.work.WorkInfo) r7
                        if (r7 == 0) goto L3f
                        long r4 = r7.getNextScheduleTimeMillis()
                        goto L44
                    L3f:
                        r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                    L44:
                        java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)
                        r0.label = r3
                        java.lang.Object r7 = r8.emit(r7, r0)
                        if (r7 != r1) goto L51
                        return r1
                    L51:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.RepoUpdateManager$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = autoUpdateWorkInfo.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
        this.nextUpdateFlow = flow;
        this.nextUpdateLiveData = FlowLiveDataConversions.asLiveData$default(flow, null, 0L, 3, null);
        RepoUriBuilder repoUriBuilder = new RepoUriBuilder() { // from class: org.fdroid.fdroid.RepoUpdateManager$$ExternalSyntheticLambda0
            @Override // org.fdroid.index.RepoUriBuilder
            public final Uri getUri(Repository repository, String[] strArr) {
                return RepoUpdateManager.uriBuilder$lambda$1(repository, strArr);
            }
        };
        this.uriBuilder = repoUriBuilder;
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
        CompatibilityCheckerImpl compatibilityCheckerImpl = new CompatibilityCheckerImpl(packageManager, Preferences.get().forceTouchApps(), 0, null, 12, null);
        this.compatibilityChecker = compatibilityCheckerImpl;
        File cacheDir = context.getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "getCacheDir(...)");
        FDroidDatabase db2 = DBHelper.getDb(context);
        Intrinsics.checkNotNullExpressionValue(db2, "getDb(...)");
        DownloaderFactory INSTANCE = DownloaderFactory.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(INSTANCE, "INSTANCE");
        this.repoUpdater = new RepoUpdater(cacheDir, db2, INSTANCE, repoUriBuilder, compatibilityCheckerImpl, this);
        if (z) {
            TempFileProvider tempFileProvider = new TempFileProvider() { // from class: org.fdroid.fdroid.RepoUpdateManager$$ExternalSyntheticLambda1
                @Override // org.fdroid.index.TempFileProvider
                public final File createTempFile() {
                    return RepoUpdateManager.indexV1Updater$lambda$2(this.f$0);
                }
            };
            Intrinsics.checkNotNullExpressionValue(INSTANCE, "INSTANCE");
            indexV1Updater = new IndexV1Updater(db, tempFileProvider, INSTANCE, repoUriBuilder, compatibilityCheckerImpl, this);
        } else {
            indexV1Updater = null;
        }
        this.indexV1Updater = indexV1Updater;
        this.fdroidPrefs = Preferences.get();
    }

    public /* synthetic */ RepoUpdateManager(Context context, FDroidDatabase fDroidDatabase, RepoManager repoManager, NotificationManager notificationManager, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, fDroidDatabase, (i & 4) != 0 ? FDroidApp.getRepoManager(context) : repoManager, (i & 8) != 0 ? new NotificationManager(context) : notificationManager, (i & 16) != 0 ? Preferences.get().isForceOldIndexEnabled() : z);
    }

    /* JADX INFO: renamed from: isUpdating, reason: from getter */
    public final StateFlow getIsUpdating() {
        return this.isUpdating;
    }

    /* JADX INFO: renamed from: isUpdatingLiveData, reason: from getter */
    public final LiveData getIsUpdatingLiveData() {
        return this.isUpdatingLiveData;
    }

    public final Flow getNextUpdateFlow() {
        return this.nextUpdateFlow;
    }

    public final LiveData getNextUpdateLiveData() {
        return this.nextUpdateLiveData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Uri uriBuilder$lambda$1(Repository repository, String[] pathElements) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(pathElements, "pathElements");
        Uri uri = Utils.getUri(Utils.getRepoAddress(repository), (String[]) Arrays.copyOf(pathElements, pathElements.length));
        Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
        return uri;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final File indexV1Updater$lambda$2(RepoUpdateManager repoUpdateManager) throws IOException {
        File fileCreateTempFile = File.createTempFile("dl-", "", repoUpdateManager.context.getCacheDir());
        Intrinsics.checkNotNullExpressionValue(fileCreateTempFile, "createTempFile(...)");
        return fileCreateTempFile;
    }

    public final void updateRepos() {
        IndexUpdateResult indexUpdateResultUpdate;
        if (((Boolean) this.isUpdating.getValue()).booleanValue()) {
            Log.w(RepoUpdateManagerKt.TAG, "Already updating repositories: updateRepos()");
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.fdroidPrefs.getLastUpdateCheck();
        if (jCurrentTimeMillis < 15000) {
            Log.i(RepoUpdateManagerKt.TAG, "Not updating, only " + jCurrentTimeMillis + " ms since last check.");
            return;
        }
        this._isUpdating.setValue(Boolean.TRUE);
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (Repository repository : this.db.getRepositoryDao().getRepositories()) {
                if (repository.getEnabled()) {
                    if (this.fdroidPrefs.isUpdateNotificationEnabled()) {
                        String string = this.context.getString(R.string.status_connecting_to_repo, repository.getAddress());
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        NotificationManager.showUpdateRepoNotification$default(this.notificationManager, string, false, null, 4, null);
                    }
                    IndexV1Updater indexV1Updater = this.indexV1Updater;
                    if (indexV1Updater == null || (indexUpdateResultUpdate = indexV1Updater.update(repository)) == null) {
                        indexUpdateResultUpdate = this.repoUpdater.update(repository);
                    }
                    if (indexUpdateResultUpdate instanceof IndexUpdateResult.Processed) {
                        z = true;
                    } else if (indexUpdateResultUpdate instanceof IndexUpdateResult.Error) {
                        Log.e(RepoUpdateManagerKt.TAG, "Error updating repository " + repository.getAddress(), ((IndexUpdateResult.Error) indexUpdateResultUpdate).getE());
                        arrayList.add(new Pair(repository, ((IndexUpdateResult.Error) indexUpdateResultUpdate).getE()));
                    }
                }
            }
            this.db.getRepositoryDao().walCheckpoint();
            this.fdroidPrefs.setLastUpdateCheck(System.currentTimeMillis());
            if (!arrayList.isEmpty()) {
                showRepoErrors(arrayList);
            }
            if (z) {
                AppUpdateStatusManager appUpdateStatusManager = AppUpdateStatusManager.getInstance(this.context);
                if (this.fdroidPrefs.isAutoDownloadEnabled() && this.fdroidPrefs.isBackgroundDownloadAllowed()) {
                    appUpdateStatusManager.checkForUpdatesAndInstall();
                } else {
                    appUpdateStatusManager.checkForUpdates();
                }
            }
            this.notificationManager.cancelUpdateRepoNotification();
            this._isUpdating.setValue(Boolean.FALSE);
        } catch (Throwable th) {
            this.notificationManager.cancelUpdateRepoNotification();
            this._isUpdating.setValue(Boolean.FALSE);
            throw th;
        }
    }

    public final IndexUpdateResult updateRepo(long repoId) {
        IndexUpdateResult indexUpdateResultUpdate;
        if (((Boolean) this.isUpdating.getValue()).booleanValue()) {
            Log.w(RepoUpdateManagerKt.TAG, "Already updating repositories: updateRepo(" + repoId + ")");
        }
        Repository repository = this.repoManager.getRepository(repoId);
        if (repository == null) {
            return IndexUpdateResult.NotFound.INSTANCE;
        }
        this._isUpdating.setValue(Boolean.TRUE);
        try {
            if (this.fdroidPrefs.isUpdateNotificationEnabled()) {
                String string = this.context.getString(R.string.status_connecting_to_repo, repository.getAddress());
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                NotificationManager.showUpdateRepoNotification$default(this.notificationManager, string, false, null, 4, null);
            }
            IndexV1Updater indexV1Updater = this.indexV1Updater;
            if (indexV1Updater == null || (indexUpdateResultUpdate = indexV1Updater.update(repository)) == null) {
                indexUpdateResultUpdate = this.repoUpdater.update(repository);
            }
            this.notificationManager.cancelUpdateRepoNotification();
            this._isUpdating.setValue(Boolean.FALSE);
            this.db.getRepositoryDao().walCheckpoint();
            return indexUpdateResultUpdate;
        } catch (Throwable th) {
            this.notificationManager.cancelUpdateRepoNotification();
            this._isUpdating.setValue(Boolean.FALSE);
            this.db.getRepositoryDao().walCheckpoint();
            throw th;
        }
    }

    private final void showRepoErrors(List<? extends Pair> repoErrors) {
        final StringBuilder sb = new StringBuilder();
        for (Pair pair : repoErrors) {
            Repository repository = (Repository) pair.component1();
            Exception exc = (Exception) pair.component2();
            if (sb.length() > 0) {
                sb.append('\n');
            }
            Throwable cause = exc.getCause();
            LocaleListCompat locales = App.getLocales();
            Intrinsics.checkNotNullExpressionValue(locales, "getLocales(...)");
            String name = repository.getName(locales);
            if (name == null) {
                name = repository.getAddress();
            }
            if (cause == null) {
                sb.append(name + ": " + exc.getLocalizedMessage());
            } else {
                sb.append(name + ": " + exc.getLocalizedMessage() + " " + cause.getLocalizedMessage());
            }
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: org.fdroid.fdroid.RepoUpdateManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                RepoUpdateManager.showRepoErrors$lambda$4(this.f$0, sb);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showRepoErrors$lambda$4(RepoUpdateManager repoUpdateManager, StringBuilder sb) {
        Utils.showToastFromService(repoUpdateManager.context, sb.toString(), 1);
    }

    @Override // org.fdroid.index.IndexUpdateListener
    public void onDownloadProgress(Repository repo, long bytesRead, long totalBytes) {
        String string;
        Intrinsics.checkNotNullParameter(repo, "repo");
        Log.d(RepoUpdateManagerKt.TAG, "Downloading " + repo.getAddress() + " (" + bytesRead + "/" + totalBytes + ")");
        if (this.fdroidPrefs.isUpdateNotificationEnabled()) {
            int percent = totalBytes > 0 ? Utils.getPercent(bytesRead, totalBytes) : -1;
            String friendlySize = Utils.getFriendlySize(bytesRead);
            if (totalBytes == -1) {
                string = this.context.getString(R.string.status_download_unknown_size, repo.getAddress(), friendlySize);
                Intrinsics.checkNotNull(string);
            } else {
                string = this.context.getString(R.string.status_download, repo.getAddress(), friendlySize, Utils.getFriendlySize(totalBytes), Integer.valueOf(percent));
                Intrinsics.checkNotNull(string);
            }
            NotificationManager.showUpdateRepoNotification$default(this.notificationManager, string, false, Integer.valueOf(percent), 2, null);
        }
    }

    @Override // org.fdroid.index.IndexUpdateListener
    public void onUpdateProgress(Repository repo, int appsProcessed, int totalApps) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Log.d(RepoUpdateManagerKt.TAG, "Committing " + repo.getAddress() + " (" + appsProcessed + "/" + totalApps + ")");
        if (this.fdroidPrefs.isUpdateNotificationEnabled()) {
            if (totalApps > 0) {
                NotificationManager notificationManager = this.notificationManager;
                String string = this.context.getString(R.string.status_inserting_x_apps, Integer.valueOf(appsProcessed), Integer.valueOf(totalApps), repo.getAddress());
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                NotificationManager.showUpdateRepoNotification$default(notificationManager, string, false, Integer.valueOf(Utils.getPercent(appsProcessed, totalApps)), 2, null);
                return;
            }
            NotificationManager notificationManager2 = this.notificationManager;
            String string2 = this.context.getString(R.string.status_inserting_apps);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            NotificationManager.showUpdateRepoNotification$default(notificationManager2, string2, false, null, 6, null);
        }
    }
}
