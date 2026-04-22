package org.fdroid.index;

import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.LiveData;
import ch.qos.logback.core.CoreConstants;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.fdroid.database.AppPrefs;
import org.fdroid.database.AppPrefsDao;
import org.fdroid.database.AppPrefsDaoInt;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.Repository;
import org.fdroid.database.RepositoryDao;
import org.fdroid.database.RepositoryDaoInt;
import org.fdroid.download.DownloaderFactory;
import org.fdroid.download.HttpManager;
import org.fdroid.download.Mirror;
import org.fdroid.index.RepoManager;
import org.fdroid.repo.RepoAdder;
import org.fdroid.repo.RepoUriGetter;

/* JADX INFO: compiled from: RepoManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B=\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010+\u001a\u0004\u0018\u00010\u001b2\u0006\u0010,\u001a\u00020-J\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aJ\u0018\u0010/\u001a\u0002002\u0006\u0010,\u001a\u00020-2\u0006\u00101\u001a\u000202H\u0007J\u0010\u00103\u001a\u0002002\u0006\u0010,\u001a\u00020-H\u0007J\u001c\u00104\u001a\u0002002\u0006\u00105\u001a\u0002062\n\b\u0002\u00107\u001a\u0004\u0018\u000108H\u0007J\b\u00109\u001a\u000200H\u0007J\b\u0010:\u001a\u000200H\u0007J\u0018\u0010;\u001a\u0002002\u0006\u0010<\u001a\u0002062\u0006\u0010,\u001a\u00020-H\u0007J\u0018\u0010=\u001a\u0002002\u0006\u0010>\u001a\u00020\u001b2\u0006\u0010?\u001a\u00020\u001bH\u0007J,\u0010@\u001a\u0004\u0018\u00010-2\u0006\u0010A\u001a\u00020\u001b2\u0006\u00101\u001a\u0002022\n\b\u0002\u00107\u001a\u0004\u0018\u000108H\u0087@¢\u0006\u0002\u0010BJ\u0012\u0010C\u001a\u0002022\b\u0010D\u001a\u0004\u0018\u00010EH\u0007J$\u0010F\u001a\u0002002\u0006\u0010,\u001a\u00020-2\b\u0010G\u001a\u0004\u0018\u0001062\b\u0010H\u001a\u0004\u0018\u000106H\u0007J \u0010I\u001a\u0002002\u0006\u0010,\u001a\u00020-2\u0006\u0010J\u001a\u00020K2\u0006\u00101\u001a\u000202H\u0007J\u0018\u0010L\u001a\u0002002\u0006\u0010,\u001a\u00020-2\u0006\u0010J\u001a\u00020KH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u001d¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020%0!¢\u0006\b\n\u0000\u001a\u0004\b(\u0010#R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lorg/fdroid/index/RepoManager;", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "db", "Lorg/fdroid/database/FDroidDatabase;", "downloaderFactory", "Lorg/fdroid/download/DownloaderFactory;", "httpManager", "Lorg/fdroid/download/HttpManager;", "repoUriBuilder", "Lorg/fdroid/index/RepoUriBuilder;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Landroid/content/Context;Lorg/fdroid/database/FDroidDatabase;Lorg/fdroid/download/DownloaderFactory;Lorg/fdroid/download/HttpManager;Lorg/fdroid/index/RepoUriBuilder;Lkotlin/coroutines/CoroutineContext;)V", "repositoryDao", "Lorg/fdroid/database/RepositoryDaoInt;", "appPrefsDao", "Lorg/fdroid/database/AppPrefsDaoInt;", "tempFileProvider", "Lorg/fdroid/index/TempFileProvider;", "repoAdder", "Lorg/fdroid/repo/RepoAdder;", "_repositoriesState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lorg/fdroid/database/Repository;", "repositoriesState", "Lkotlinx/coroutines/flow/StateFlow;", "getRepositoriesState", "()Lkotlinx/coroutines/flow/StateFlow;", "liveRepositories", "Landroidx/lifecycle/LiveData;", "getLiveRepositories", "()Landroidx/lifecycle/LiveData;", "addRepoState", "Lorg/fdroid/repo/AddRepoState;", "getAddRepoState", "liveAddRepoState", "getLiveAddRepoState", "repoCountDownLatch", "Ljava/util/concurrent/CountDownLatch;", "getRepository", "repoId", "", "getRepositories", "setRepositoryEnabled", "", "enabled", "", "deleteRepository", "fetchRepositoryPreview", "url", "", "proxy", "Ljava/net/Proxy;", "addFetchedRepository", "abortAddingRepository", "setPreferredRepoId", "packageName", "reorderRepositories", "repoToReorder", "repoTarget", "setArchiveRepoEnabled", "repository", "(Lorg/fdroid/database/Repository;ZLjava/net/Proxy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSwapUri", "uri", "Landroid/net/Uri;", "updateUsernameAndPassword", "username", "password", "setMirrorEnabled", "mirror", "Lorg/fdroid/download/Mirror;", "deleteUserMirror", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RepoManager {
    private final MutableStateFlow _repositoriesState;
    private final StateFlow addRepoState;
    private final AppPrefsDaoInt appPrefsDao;
    private final CoroutineContext coroutineContext;
    private final FDroidDatabase db;
    private final LiveData liveAddRepoState;
    private final LiveData liveRepositories;
    private final RepoAdder repoAdder;
    private final CountDownLatch repoCountDownLatch;
    private final StateFlow repositoriesState;
    private final RepositoryDaoInt repositoryDao;
    private final TempFileProvider tempFileProvider;

    /* JADX INFO: renamed from: org.fdroid.index.RepoManager$setArchiveRepoEnabled$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoManager.kt */
    static final class C02271 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02271(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RepoManager.this.setArchiveRepoEnabled(null, false, null, this);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RepoManager(Context context, FDroidDatabase db, DownloaderFactory downloaderFactory, HttpManager httpManager) {
        this(context, db, downloaderFactory, httpManager, null, null, 48, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(downloaderFactory, "downloaderFactory");
        Intrinsics.checkNotNullParameter(httpManager, "httpManager");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RepoManager(Context context, FDroidDatabase db, DownloaderFactory downloaderFactory, HttpManager httpManager, RepoUriBuilder repoUriBuilder) {
        this(context, db, downloaderFactory, httpManager, repoUriBuilder, null, 32, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(downloaderFactory, "downloaderFactory");
        Intrinsics.checkNotNullParameter(httpManager, "httpManager");
        Intrinsics.checkNotNullParameter(repoUriBuilder, "repoUriBuilder");
    }

    public final void fetchRepositoryPreview(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        fetchRepositoryPreview$default(this, url, null, 2, null);
    }

    public RepoManager(final Context context, FDroidDatabase db, DownloaderFactory downloaderFactory, HttpManager httpManager, RepoUriBuilder repoUriBuilder, CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(downloaderFactory, "downloaderFactory");
        Intrinsics.checkNotNullParameter(httpManager, "httpManager");
        Intrinsics.checkNotNullParameter(repoUriBuilder, "repoUriBuilder");
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        this.db = db;
        this.coroutineContext = coroutineContext;
        RepositoryDao repositoryDao = db.getRepositoryDao();
        Intrinsics.checkNotNull(repositoryDao, "null cannot be cast to non-null type org.fdroid.database.RepositoryDaoInt");
        this.repositoryDao = (RepositoryDaoInt) repositoryDao;
        AppPrefsDao appPrefsDao = db.getAppPrefsDao();
        Intrinsics.checkNotNull(appPrefsDao, "null cannot be cast to non-null type org.fdroid.database.AppPrefsDaoInt");
        this.appPrefsDao = (AppPrefsDaoInt) appPrefsDao;
        TempFileProvider tempFileProvider = new TempFileProvider() { // from class: org.fdroid.index.RepoManager$$ExternalSyntheticLambda0
            @Override // org.fdroid.index.TempFileProvider
            public final File createTempFile() {
                return RepoManager.tempFileProvider$lambda$0(context);
            }
        };
        this.tempFileProvider = tempFileProvider;
        RepoAdder repoAdder = new RepoAdder(context, db, tempFileProvider, downloaderFactory, httpManager, null, repoUriBuilder, coroutineContext, 32, null);
        this.repoAdder = repoAdder;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this._repositoriesState = MutableStateFlow;
        this.repositoriesState = FlowKt.asStateFlow(MutableStateFlow);
        this.liveRepositories = FlowLiveDataConversions.asLiveData$default(MutableStateFlow, null, 0L, 3, null);
        this.addRepoState = FlowKt.asStateFlow(repoAdder.getAddRepoState());
        this.liveAddRepoState = FlowLiveDataConversions.asLiveData$default(repoAdder.getAddRepoState(), null, 0L, 3, null);
        this.repoCountDownLatch = new CountDownLatch(1);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, coroutineContext, null, new AnonymousClass1(null), 2, null);
    }

    public /* synthetic */ RepoManager(Context context, FDroidDatabase fDroidDatabase, DownloaderFactory downloaderFactory, HttpManager httpManager, RepoUriBuilder repoUriBuilder, CoroutineContext coroutineContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, fDroidDatabase, downloaderFactory, httpManager, (i & 16) != 0 ? IndexUpdaterKt.getDefaultRepoUriBuilder() : repoUriBuilder, (i & 32) != 0 ? Dispatchers.getIO() : coroutineContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final File tempFileProvider$lambda$0(Context context) throws IOException {
        File fileCreateTempFile = File.createTempFile("dl-", "", context.getCacheDir());
        Intrinsics.checkNotNullExpressionValue(fileCreateTempFile, "createTempFile(...)");
        return fileCreateTempFile;
    }

    public final StateFlow getRepositoriesState() {
        return this.repositoriesState;
    }

    public final LiveData getLiveRepositories() {
        return this.liveRepositories;
    }

    public final StateFlow getAddRepoState() {
        return this.addRepoState;
    }

    public final LiveData getLiveAddRepoState() {
        return this.liveAddRepoState;
    }

    /* JADX INFO: renamed from: org.fdroid.index.RepoManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: RepoManager.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return RepoManager.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                RepoManager.this._repositoriesState.setValue(RepoManager.this.repositoryDao.getRepositories());
                RepoManager.this.repoCountDownLatch.countDown();
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C00741 c00741 = new C00741(RepoManager.this, null);
                this.label = 1;
                if (BuildersKt.withContext(main, c00741, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: org.fdroid.index.RepoManager$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: RepoManager.kt */
        static final class C00741 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ RepoManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00741(RepoManager repoManager, Continuation continuation) {
                super(2, continuation);
                this.this$0 = repoManager;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00741(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((C00741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    LiveData liveRepositories = this.this$0.db.getRepositoryDao().getLiveRepositories();
                    final RepoManager repoManager = this.this$0;
                    liveRepositories.observeForever(new RepoManager$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: org.fdroid.index.RepoManager$1$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return RepoManager.AnonymousClass1.C00741.invokeSuspend$lambda$0(repoManager, (List) obj2);
                        }
                    }));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final Unit invokeSuspend$lambda$0(RepoManager repoManager, List list) {
                repoManager._repositoriesState.setValue(list);
                return Unit.INSTANCE;
            }
        }
    }

    public final Repository getRepository(long repoId) {
        Object next;
        this.repoCountDownLatch.await();
        Iterator it = ((Iterable) this.repositoriesState.getValue()).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((Repository) next).getRepoId() == repoId) {
                break;
            }
        }
        return (Repository) next;
    }

    public final List<Repository> getRepositories() throws InterruptedException {
        this.repoCountDownLatch.await();
        return (List) this.repositoriesState.getValue();
    }

    public final void setRepositoryEnabled(final long repoId, boolean enabled) {
        if (enabled) {
            this.repositoryDao.setRepositoryEnabled(repoId, true);
        } else {
            this.db.runInTransaction(new Runnable() { // from class: org.fdroid.index.RepoManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    RepoManager.setRepositoryEnabled$lambda$2(this.f$0, repoId);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRepositoryEnabled$lambda$2(RepoManager repoManager, long j) {
        Repository repository = repoManager.repositoryDao.getRepository(j);
        if (repository == null) {
            return;
        }
        Long archiveRepoId = repoManager.repositoryDao.getArchiveRepoId(repository.getCertificate());
        if (archiveRepoId != null) {
            repoManager.repositoryDao.setRepositoryEnabled(archiveRepoId.longValue(), false);
        }
        repoManager.repositoryDao.setRepositoryEnabled(j, false);
    }

    public final void deleteRepository(final long repoId) {
        this.db.runInTransaction(new Runnable() { // from class: org.fdroid.index.RepoManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                RepoManager.deleteRepository$lambda$4(this.f$0, repoId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteRepository$lambda$4(RepoManager repoManager, long j) {
        Repository repository = repoManager.repositoryDao.getRepository(j);
        if (repository == null) {
            return;
        }
        Long archiveRepoId = repoManager.repositoryDao.getArchiveRepoId(repository.getCertificate());
        if (archiveRepoId != null) {
            repoManager.repositoryDao.deleteRepository(archiveRepoId.longValue());
        }
        repoManager.repositoryDao.deleteRepository(j);
        MutableStateFlow mutableStateFlow = repoManager._repositoriesState;
        Iterable iterable = (Iterable) mutableStateFlow.getValue();
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterable) {
            if (((Repository) obj).getRepoId() == j) {
                arrayList.add(obj);
            }
        }
        mutableStateFlow.setValue(arrayList);
    }

    public static /* synthetic */ void fetchRepositoryPreview$default(RepoManager repoManager, String str, Proxy proxy, int i, Object obj) {
        if ((i & 2) != 0) {
            proxy = null;
        }
        repoManager.fetchRepositoryPreview(str, proxy);
    }

    public final void fetchRepositoryPreview(String url, Proxy proxy) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.repoAdder.fetchRepository$database_release(url, proxy);
    }

    /* JADX INFO: renamed from: org.fdroid.index.RepoManager$addFetchedRepository$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoManager.kt */
    static final class C02251 extends SuspendLambda implements Function2 {
        int label;

        C02251(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return RepoManager.this.new C02251(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02251) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Repository repositoryAddFetchedRepository$database_release = RepoManager.this.repoAdder.addFetchedRepository$database_release();
                if (repositoryAddFetchedRepository$database_release != null) {
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    C00751 c00751 = new C00751(RepoManager.this, repositoryAddFetchedRepository$database_release, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main, c00751, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: org.fdroid.index.RepoManager$addFetchedRepository$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: RepoManager.kt */
        static final class C00751 extends SuspendLambda implements Function2 {
            final /* synthetic */ Repository $addedRepo;
            int label;
            final /* synthetic */ RepoManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00751(RepoManager repoManager, Repository repository, Continuation continuation) {
                super(2, continuation);
                this.this$0 = repoManager;
                this.$addedRepo = repository;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00751(this.this$0, this.$addedRepo, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((C00751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableStateFlow mutableStateFlow = this.this$0._repositoriesState;
                    List mutableList = CollectionsKt.toMutableList((Collection) this.this$0._repositoriesState.getValue());
                    mutableList.add(this.$addedRepo);
                    mutableStateFlow.setValue(mutableList);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void addFetchedRepository() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.coroutineContext, null, new C02251(null), 2, null);
    }

    public final void abortAddingRepository() {
        this.repoAdder.abortAddingRepo$database_release();
    }

    /* JADX INFO: renamed from: org.fdroid.index.RepoManager$setPreferredRepoId$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoManager.kt */
    static final class C02281 extends SuspendLambda implements Function2 {
        final /* synthetic */ String $packageName;
        final /* synthetic */ long $repoId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02281(String str, long j, Continuation continuation) {
            super(2, continuation);
            this.$packageName = str;
            this.$repoId = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return RepoManager.this.new C02281(this.$packageName, this.$repoId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02281) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FDroidDatabase fDroidDatabase = RepoManager.this.db;
                final RepoManager repoManager = RepoManager.this;
                final String str = this.$packageName;
                final long j = this.$repoId;
                fDroidDatabase.runInTransaction(new Runnable() { // from class: org.fdroid.index.RepoManager$setPreferredRepoId$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        RepoManager.C02281.invokeSuspend$lambda$0(repoManager, str, j);
                    }
                });
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(RepoManager repoManager, String str, long j) {
            AppPrefs appPrefsOrNull = repoManager.appPrefsDao.getAppPrefsOrNull(str);
            if (appPrefsOrNull == null) {
                appPrefsOrNull = new AppPrefs(str, 0L, null, null, 14, null);
            }
            repoManager.appPrefsDao.update(AppPrefs.copy$default(appPrefsOrNull, null, 0L, Long.valueOf(j), null, 11, null));
        }
    }

    public final void setPreferredRepoId(String packageName, long repoId) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.coroutineContext, null, new C02281(packageName, repoId, null), 2, null);
    }

    /* JADX INFO: renamed from: org.fdroid.index.RepoManager$reorderRepositories$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoManager.kt */
    static final class C02261 extends SuspendLambda implements Function2 {
        final /* synthetic */ Repository $repoTarget;
        final /* synthetic */ Repository $repoToReorder;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02261(Repository repository, Repository repository2, Continuation continuation) {
            super(2, continuation);
            this.$repoToReorder = repository;
            this.$repoTarget = repository2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return RepoManager.this.new C02261(this.$repoToReorder, this.$repoTarget, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02261) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                RepoManager.this.repositoryDao.reorderRepositories(this.$repoToReorder, this.$repoTarget);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void reorderRepositories(Repository repoToReorder, Repository repoTarget) {
        Intrinsics.checkNotNullParameter(repoToReorder, "repoToReorder");
        Intrinsics.checkNotNullParameter(repoTarget, "repoTarget");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.coroutineContext, null, new C02261(repoToReorder, repoTarget, null), 2, null);
    }

    public static /* synthetic */ Object setArchiveRepoEnabled$default(RepoManager repoManager, Repository repository, boolean z, Proxy proxy, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            proxy = null;
        }
        return repoManager.setArchiveRepoEnabled(repository, z, proxy, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object setArchiveRepoEnabled(org.fdroid.database.Repository r6, boolean r7, java.net.Proxy r8, kotlin.coroutines.Continuation r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof org.fdroid.index.RepoManager.C02271
            if (r0 == 0) goto L13
            r0 = r9
            org.fdroid.index.RepoManager$setArchiveRepoEnabled$1 r0 = (org.fdroid.index.RepoManager.C02271) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            org.fdroid.index.RepoManager$setArchiveRepoEnabled$1 r0 = new org.fdroid.index.RepoManager$setArchiveRepoEnabled$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r6 = r0.L$0
            java.lang.Long r6 = (java.lang.Long) r6
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.util.concurrent.CancellationException -> L2d
            goto L58
        L2d:
            r7 = move-exception
            goto L5d
        L2f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L37:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.String r9 = r6.getCertificate()
            org.fdroid.database.RepositoryDaoInt r2 = r5.repositoryDao
            java.lang.Long r9 = r2.getArchiveRepoId(r9)
            if (r7 == 0) goto L76
            if (r9 != 0) goto L6c
            org.fdroid.repo.RepoAdder r7 = r5.repoAdder     // Catch: java.util.concurrent.CancellationException -> L5b
            r0.L$0 = r9     // Catch: java.util.concurrent.CancellationException -> L5b
            r0.label = r3     // Catch: java.util.concurrent.CancellationException -> L5b
            java.lang.Object r6 = r7.addArchiveRepo$database_release(r6, r8, r0)     // Catch: java.util.concurrent.CancellationException -> L5b
            if (r6 != r1) goto L55
            return r1
        L55:
            r4 = r9
            r9 = r6
            r6 = r4
        L58:
            java.lang.Long r9 = (java.lang.Long) r9     // Catch: java.util.concurrent.CancellationException -> L2d
            goto L82
        L5b:
            r7 = move-exception
            r6 = r9
        L5d:
            java.lang.String r8 = r7.getMessage()
            java.lang.String r9 = "expected"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r9)
            if (r8 == 0) goto L6b
            r9 = r6
            goto L82
        L6b:
            throw r7
        L6c:
            org.fdroid.database.RepositoryDaoInt r6 = r5.repositoryDao
            long r7 = r9.longValue()
            r6.setRepositoryEnabled(r7, r3)
            goto L82
        L76:
            if (r9 == 0) goto L82
            org.fdroid.database.RepositoryDaoInt r6 = r5.repositoryDao
            long r7 = r9.longValue()
            r0 = 0
            r6.setRepositoryEnabled(r7, r0)
        L82:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.index.RepoManager.setArchiveRepoEnabled(org.fdroid.database.Repository, boolean, java.net.Proxy, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean isSwapUri(Uri uri) {
        return uri != null && RepoUriGetter.INSTANCE.isSwapUri(uri);
    }

    public final void updateUsernameAndPassword(long repoId, String username, String password) {
        this.repositoryDao.updateUsernameAndPassword(repoId, username, password);
    }

    public final void setMirrorEnabled(final long repoId, final Mirror mirror, final boolean enabled) {
        Intrinsics.checkNotNullParameter(mirror, "mirror");
        final Repository repository = this.repositoryDao.getRepository(repoId);
        if (repository == null) {
            return;
        }
        this.db.runInTransaction(new Runnable() { // from class: org.fdroid.index.RepoManager$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                RepoManager.setMirrorEnabled$lambda$5(repository, enabled, mirror, this, repoId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setMirrorEnabled$lambda$5(Repository repository, boolean z, Mirror mirror, RepoManager repoManager, long j) {
        List<String> mutableList = CollectionsKt.toMutableList((Collection) repository.getDisabledMirrors());
        if (z) {
            if (mutableList.contains(mirror.getBaseUrl())) {
                mutableList.remove(mirror.getBaseUrl());
                repoManager.repositoryDao.updateDisabledMirrors(j, mutableList);
                return;
            }
            return;
        }
        if (mutableList.contains(mirror.getBaseUrl())) {
            return;
        }
        mutableList.add(mirror.getBaseUrl());
        if (mutableList.size() == Repository.getAllMirrors$default(repository, false, 1, null).size()) {
            mutableList.remove(repository.getAddress());
        }
        repoManager.repositoryDao.updateDisabledMirrors(j, mutableList);
    }

    public final void deleteUserMirror(final long repoId, final Mirror mirror) {
        Intrinsics.checkNotNullParameter(mirror, "mirror");
        final Repository repository = this.repositoryDao.getRepository(repoId);
        if (repository == null) {
            return;
        }
        this.db.runInTransaction(new Runnable() { // from class: org.fdroid.index.RepoManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                RepoManager.deleteUserMirror$lambda$6(repository, mirror, this, repoId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteUserMirror$lambda$6(Repository repository, Mirror mirror, RepoManager repoManager, long j) {
        List<String> mutableList = CollectionsKt.toMutableList((Collection) repository.getUserMirrors());
        if (mutableList.contains(mirror.getBaseUrl())) {
            mutableList.remove(mirror.getBaseUrl());
            repoManager.repositoryDao.updateUserMirrors(j, mutableList);
        }
    }
}
