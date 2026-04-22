package org.fdroid.fdroid.views.appdetails;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.bouncycastle.asn1.eac.EACTags;
import org.fdroid.database.App;
import org.fdroid.database.AppDao;
import org.fdroid.database.AppPrefs;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.AppUpdateStatusManager;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.index.RepoManager;

/* JADX INFO: compiled from: AppDetailsViewModel.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010'\u001a\u00020(2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\b\u0010)\u001a\u00020(H\u0014J\u0010\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020#H\u0007J\u0010\u0010,\u001a\u00020(2\u0006\u0010+\u001a\u00020#H\u0007J\u0016\u00101\u001a\u00020(2\u0006\u0010\u001c\u001a\u00020\u001dH\u0082@¢\u0006\u0002\u00102J\b\u00103\u001a\u00020(H\u0002J\u0010\u00104\u001a\u00020(2\u0006\u0010+\u001a\u00020#H\u0002J\u0006\u00105\u001a\u000206J\u000e\u00107\u001a\u0002062\u0006\u00108\u001a\u00020#J\u0006\u00109\u001a\u000206R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bR\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000bR\u0018\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u0018\u0010\u0019\u001a\n \u0017*\u0004\u0018\u00010\u001a0\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u001e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0004\n\u0002\u0010$R\u0016\u0010%\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020!0.X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0.X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lorg/fdroid/fdroid/views/appdetails/AppDetailsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "_app", "Landroidx/lifecycle/MutableLiveData;", "Lorg/fdroid/database/App;", "Landroidx/lifecycle/LiveData;", "getApp", "()Landroidx/lifecycle/LiveData;", "_versions", "", "Lorg/fdroid/database/AppVersion;", "versions", "getVersions", "_appData", "Lorg/fdroid/fdroid/views/appdetails/AppData;", "appData", "getAppData", "db", "Lorg/fdroid/database/FDroidDatabase;", "kotlin.jvm.PlatformType", "Lorg/fdroid/database/FDroidDatabase;", "repoManager", "Lorg/fdroid/index/RepoManager;", "Lorg/fdroid/index/RepoManager;", "packageName", "", "appLiveData", "versionsLiveData", "appPrefsLiveData", "Lorg/fdroid/database/AppPrefs;", "preferredRepoId", "", "Ljava/lang/Long;", "repos", "Lorg/fdroid/database/Repository;", "loadApp", "", "onCleared", "selectRepo", "repoId", "setPreferredRepo", "onAppChanged", "Landroidx/lifecycle/Observer;", "onAppPrefsChanged", "onVersionsChanged", "loadRepos", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryToPublishAppData", "resetVersionsLiveData", "ignoreAllUpdates", "Lkotlinx/coroutines/Job;", "ignoreVersionCodeUpdate", "versionCode", "toggleBetaReleaseChannel", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AppDetailsViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private final MutableLiveData _app;
    private final MutableLiveData _appData;
    private final MutableLiveData _versions;
    private final LiveData app;
    private final LiveData appData;
    private LiveData appLiveData;
    private LiveData appPrefsLiveData;
    private final FDroidDatabase db;
    private final Observer onAppChanged;
    private final Observer onAppPrefsChanged;
    private final Observer onVersionsChanged;
    private String packageName;
    private Long preferredRepoId;
    private final RepoManager repoManager;
    private List<Repository> repos;
    private final LiveData versions;
    private LiveData versionsLiveData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppDetailsViewModel(Application app) {
        super(app);
        Intrinsics.checkNotNullParameter(app, "app");
        MutableLiveData mutableLiveData = new MutableLiveData();
        this._app = mutableLiveData;
        this.app = mutableLiveData;
        MutableLiveData mutableLiveData2 = new MutableLiveData();
        this._versions = mutableLiveData2;
        this.versions = mutableLiveData2;
        MutableLiveData mutableLiveData3 = new MutableLiveData();
        this._appData = mutableLiveData3;
        this.appData = mutableLiveData3;
        this.db = DBHelper.getDb(app.getApplicationContext());
        this.repoManager = FDroidApp.getRepoManager(app.getApplicationContext());
        this.onAppChanged = new Observer() { // from class: org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AppDetailsViewModel.onAppChanged$lambda$3(this.f$0, (App) obj);
            }
        };
        this.onAppPrefsChanged = new Observer() { // from class: org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AppDetailsViewModel.onAppPrefsChanged$lambda$4(this.f$0, (AppPrefs) obj);
            }
        };
        this.onVersionsChanged = new Observer() { // from class: org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AppDetailsViewModel.onVersionsChanged$lambda$5(this.f$0, (List) obj);
            }
        };
    }

    public final LiveData getApp() {
        return this.app;
    }

    public final LiveData getVersions() {
        return this.versions;
    }

    public final LiveData getAppData() {
        return this.appData;
    }

    public final void loadApp(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        if (Intrinsics.areEqual(this.packageName, packageName)) {
            return;
        }
        String str = this.packageName;
        if (str != null && !Intrinsics.areEqual(str, packageName)) {
            throw new IllegalStateException(new Function0() { // from class: org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppDetailsViewModel.loadApp$lambda$0();
                }
            }.toString());
        }
        this.packageName = packageName;
        LiveData liveData = this.appLiveData;
        if (liveData != null) {
            liveData.removeObserver(this.onAppChanged);
        }
        LiveData app = this.db.getAppDao().getApp(packageName);
        app.observeForever(this.onAppChanged);
        this.appLiveData = app;
        if (this.repoManager.getRepositories().size() > 2) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass3(packageName, null), 3, null);
        }
        LiveData appPrefs = this.db.getAppPrefsDao().getAppPrefs(packageName);
        appPrefs.observeForever(this.onAppPrefsChanged);
        this.appPrefsLiveData = appPrefs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String loadApp$lambda$0() {
        return "Called loadApp() with different packageName.";
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$loadApp$3, reason: invalid class name */
    /* JADX INFO: compiled from: AppDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$loadApp$3", f = "AppDetailsViewModel.kt", l = {EACTags.ADDRESS}, m = "invokeSuspend")
    static final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ String $packageName;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(String str, Continuation continuation) {
            super(2, continuation);
            this.$packageName = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return AppDetailsViewModel.this.new AnonymousClass3(this.$packageName, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AppDetailsViewModel appDetailsViewModel = AppDetailsViewModel.this;
                String str = this.$packageName;
                this.label = 1;
                if (appDetailsViewModel.loadRepos(str, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        LiveData liveData = this.appLiveData;
        if (liveData != null) {
            liveData.removeObserver(this.onAppChanged);
        }
        LiveData liveData2 = this.appPrefsLiveData;
        if (liveData2 != null) {
            liveData2.removeObserver(this.onAppPrefsChanged);
        }
        LiveData liveData3 = this.versionsLiveData;
        if (liveData3 != null) {
            liveData3.removeObserver(this.onVersionsChanged);
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$selectRepo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AppDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$selectRepo$1", f = "AppDetailsViewModel.kt", l = {}, m = "invokeSuspend")
    static final class C02041 extends SuspendLambda implements Function2 {
        final /* synthetic */ long $repoId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02041(long j, Continuation continuation) {
            super(2, continuation);
            this.$repoId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return AppDetailsViewModel.this.new C02041(this.$repoId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                MutableLiveData mutableLiveData = AppDetailsViewModel.this._app;
                AppDao appDao = AppDetailsViewModel.this.db.getAppDao();
                long j = this.$repoId;
                String str = AppDetailsViewModel.this.packageName;
                if (str == null) {
                    throw new IllegalStateException("");
                }
                mutableLiveData.postValue(appDao.getApp(j, str));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void selectRepo(long repoId) {
        LiveData liveData = this.appLiveData;
        if (liveData != null) {
            liveData.removeObserver(this.onAppChanged);
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C02041(repoId, null), 2, null);
        tryToPublishAppData();
        resetVersionsLiveData(repoId);
    }

    public final void setPreferredRepo(long repoId) {
        RepoManager repoManager = this.repoManager;
        String str = this.packageName;
        if (str == null) {
            throw new IllegalStateException("");
        }
        repoManager.setPreferredRepoId(str, repoId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onAppChanged$lambda$3(AppDetailsViewModel appDetailsViewModel, App app) {
        if (appDetailsViewModel._app.getValue() == null && app != null) {
            appDetailsViewModel.preferredRepoId = Long.valueOf(app.getRepoId());
            appDetailsViewModel.resetVersionsLiveData(app.getRepoId());
            appDetailsViewModel.tryToPublishAppData();
        }
        appDetailsViewModel._app.setValue(app);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onAppPrefsChanged$lambda$4(AppDetailsViewModel appDetailsViewModel, AppPrefs appPrefs) {
        Intrinsics.checkNotNullParameter(appPrefs, "appPrefs");
        if (appPrefs.getPreferredRepoId() != null) {
            appDetailsViewModel.preferredRepoId = appPrefs.getPreferredRepoId();
        }
        appDetailsViewModel.tryToPublishAppData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onVersionsChanged$lambda$5(AppDetailsViewModel appDetailsViewModel, List versions) {
        Intrinsics.checkNotNullParameter(versions, "versions");
        appDetailsViewModel._versions.setValue(versions);
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$loadRepos$2, reason: invalid class name */
    /* JADX INFO: compiled from: AppDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$loadRepos$2", f = "AppDetailsViewModel.kt", l = {}, m = "invokeSuspend")
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ String $packageName;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation continuation) {
            super(2, continuation);
            this.$packageName = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return AppDetailsViewModel.this.new AnonymousClass2(this.$packageName, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AppDetailsViewModel appDetailsViewModel = AppDetailsViewModel.this;
            List<Long> repositoryIdsForApp = appDetailsViewModel.db.getAppDao().getRepositoryIdsForApp(this.$packageName);
            AppDetailsViewModel appDetailsViewModel2 = AppDetailsViewModel.this;
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = repositoryIdsForApp.iterator();
            while (it.hasNext()) {
                Repository repository = appDetailsViewModel2.repoManager.getRepository(((Number) it.next()).longValue());
                if (repository != null) {
                    arrayList.add(repository);
                }
            }
            appDetailsViewModel.repos = arrayList;
            AppDetailsViewModel.this.tryToPublishAppData();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadRepos(String str, Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void tryToPublishAppData() {
        AppPrefs appPrefs;
        Long l;
        LiveData liveData = this.appPrefsLiveData;
        if (liveData == null || (appPrefs = (AppPrefs) liveData.getValue()) == null || (l = this.preferredRepoId) == null) {
            return;
        }
        long jLongValue = l.longValue();
        List<Repository> listEmptyList = this.repos;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        this._appData.postValue(new AppData(appPrefs, jLongValue, listEmptyList));
    }

    private final void resetVersionsLiveData(long repoId) {
        LiveData liveData = this.versionsLiveData;
        if (liveData != null) {
            liveData.removeObserver(this.onVersionsChanged);
        }
        String str = this.packageName;
        if (str == null) {
            throw new IllegalStateException("packageName not initialized");
        }
        LiveData appVersions = this.db.getVersionDao().getAppVersions(repoId, str);
        appVersions.observeForever(this.onVersionsChanged);
        this.versionsLiveData = appVersions;
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$ignoreAllUpdates$1, reason: invalid class name */
    /* JADX INFO: compiled from: AppDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$ignoreAllUpdates$1", f = "AppDetailsViewModel.kt", l = {}, m = "invokeSuspend")
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return AppDetailsViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AppPrefs appPrefs;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                LiveData liveData = AppDetailsViewModel.this.appPrefsLiveData;
                if (liveData != null && (appPrefs = (AppPrefs) liveData.getValue()) != null) {
                    AppDetailsViewModel.this.db.getAppPrefsDao().update(appPrefs.toggleIgnoreAllUpdates());
                    AppUpdateStatusManager.getInstance(AppDetailsViewModel.this.getApplication()).checkForUpdates();
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Job ignoreAllUpdates() {
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$ignoreVersionCodeUpdate$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AppDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$ignoreVersionCodeUpdate$1", f = "AppDetailsViewModel.kt", l = {}, m = "invokeSuspend")
    static final class C02031 extends SuspendLambda implements Function2 {
        final /* synthetic */ long $versionCode;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02031(long j, Continuation continuation) {
            super(2, continuation);
            this.$versionCode = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return AppDetailsViewModel.this.new C02031(this.$versionCode, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AppPrefs appPrefs;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                LiveData liveData = AppDetailsViewModel.this.appPrefsLiveData;
                if (liveData != null && (appPrefs = (AppPrefs) liveData.getValue()) != null) {
                    AppDetailsViewModel.this.db.getAppPrefsDao().update(appPrefs.toggleIgnoreVersionCodeUpdate(this.$versionCode));
                    AppUpdateStatusManager.getInstance(AppDetailsViewModel.this.getApplication()).checkForUpdates();
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Job ignoreVersionCodeUpdate(long versionCode) {
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C02031(versionCode, null), 2, null);
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$toggleBetaReleaseChannel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AppDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.appdetails.AppDetailsViewModel$toggleBetaReleaseChannel$1", f = "AppDetailsViewModel.kt", l = {}, m = "invokeSuspend")
    static final class C02051 extends SuspendLambda implements Function2 {
        int label;

        C02051(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return AppDetailsViewModel.this.new C02051(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AppPrefs appPrefs;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                LiveData liveData = AppDetailsViewModel.this.appPrefsLiveData;
                if (liveData != null && (appPrefs = (AppPrefs) liveData.getValue()) != null) {
                    AppDetailsViewModel.this.db.getAppPrefsDao().update(appPrefs.toggleReleaseChannel("Beta"));
                    AppUpdateStatusManager.getInstance(AppDetailsViewModel.this.getApplication()).checkForUpdates();
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Job toggleBetaReleaseChannel() {
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C02051(null), 2, null);
    }
}
