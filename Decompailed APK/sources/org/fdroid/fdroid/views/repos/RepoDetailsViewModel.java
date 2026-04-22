package org.fdroid.fdroid.views.repos;

import android.app.Application;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder;
import ch.qos.logback.core.joran.action.Action;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.fdroid.database.AppDao;
import org.fdroid.database.Repository;
import org.fdroid.download.Mirror;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.UtilsKt;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.net.DnsCache;
import org.fdroid.fdroid.work.RepoUpdateWorker;
import org.fdroid.index.RepoManager;

/* JADX INFO: compiled from: RepoDetailsViewModel.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 22\u00020\u0001:\u00012B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020\u001fJ\u0016\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%J\u0016\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)2\u0006\u0010 \u001a\u00020!J\u000e\u0010*\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)J\f\u0010+\u001a\u00020\u001b*\u00020\u0005H\u0002J\f\u0010,\u001a\u00020\u001b*\u00020!H\u0002J\u0018\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u000200H\u0086@¢\u0006\u0002\u00101R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u00063"}, d2 = {"Lorg/fdroid/fdroid/views/repos/RepoDetailsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "initialRepo", "Lorg/fdroid/database/Repository;", "<init>", "(Landroid/app/Application;Lorg/fdroid/database/Repository;)V", "repoId", "", "repoManager", "Lorg/fdroid/index/RepoManager;", "kotlin.jvm.PlatformType", "Lorg/fdroid/index/RepoManager;", "appDao", "Lorg/fdroid/database/AppDao;", "repoFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getRepoFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "numberAppsFlow", "Lkotlinx/coroutines/flow/Flow;", "", "getNumberAppsFlow", "()Lkotlinx/coroutines/flow/Flow;", "archiveStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lorg/fdroid/fdroid/views/repos/ArchiveState;", "getArchiveStateFlow", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "setArchiveRepoEnabled", "", "enabled", "", "deleteRepository", "updateUsernameAndPassword", "username", "", "password", "setMirrorEnabled", "mirror", "Lorg/fdroid/download/Mirror;", "deleteUserMirror", "archiveState", "toArchiveState", "generateQrCode", "Landroid/graphics/Bitmap;", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "(Landroidx/appcompat/app/AppCompatActivity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RepoDetailsViewModel extends AndroidViewModel {
    private static final ViewModelProvider.Factory Factory;
    private static final String TAG = "RepoDetailsViewModel";
    private final AppDao appDao;
    private final MutableStateFlow archiveStateFlow;
    private final Flow numberAppsFlow;
    private final StateFlow repoFlow;
    private final long repoId;
    private final RepoManager repoManager;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final CreationExtras.Key APP_KEY = new CreationExtras.Key() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$Companion$APP_KEY$1
    };
    private static final CreationExtras.Key REPO_KEY = new CreationExtras.Key() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$Companion$REPO_KEY$1
    };

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RepoDetailsViewModel(Application app, Repository initialRepo) {
        super(app);
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(initialRepo, "initialRepo");
        this.repoId = initialRepo.getRepoId();
        RepoManager repoManager = FDroidApp.getRepoManager(app);
        this.repoManager = repoManager;
        this.appDao = DBHelper.getDb(app).getAppDao();
        final StateFlow repositoriesState = repoManager.getRepositoriesState();
        final StateFlow stateFlowStateIn = FlowKt.stateIn(new Flow() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$1

            /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", Action.VALUE_ATTRIBUTE, "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 0, 0})
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ RepoDetailsViewModel this$0;

                /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$1$2", f = "RepoDetailsViewModel.kt", l = {223}, m = "emit")
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

                public AnonymousClass2(FlowCollector flowCollector, RepoDetailsViewModel repoDetailsViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = repoDetailsViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                    /*
                        r8 = this;
                        boolean r0 = r10 instanceof org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r10
                        org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$1$2$1 r0 = (org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$1$2$1 r0 = new org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r10)
                    L18:
                        java.lang.Object r10 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        kotlin.ResultKt.throwOnFailure(r10)
                        goto L62
                    L29:
                        java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                        java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                        r9.<init>(r10)
                        throw r9
                    L31:
                        kotlin.ResultKt.throwOnFailure(r10)
                        kotlinx.coroutines.flow.FlowCollector r10 = r8.$this_unsafeFlow
                        java.util.List r9 = (java.util.List) r9
                        java.util.Iterator r9 = r9.iterator()
                    L3c:
                        boolean r2 = r9.hasNext()
                        if (r2 == 0) goto L58
                        java.lang.Object r2 = r9.next()
                        r4 = r2
                        org.fdroid.database.Repository r4 = (org.fdroid.database.Repository) r4
                        long r4 = r4.getRepoId()
                        org.fdroid.fdroid.views.repos.RepoDetailsViewModel r6 = r8.this$0
                        long r6 = org.fdroid.fdroid.views.repos.RepoDetailsViewModel.access$getRepoId$p(r6)
                        int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                        if (r4 != 0) goto L3c
                        goto L59
                    L58:
                        r2 = 0
                    L59:
                        r0.label = r3
                        java.lang.Object r9 = r10.emit(r2, r0)
                        if (r9 != r1) goto L62
                        return r1
                    L62:
                        kotlin.Unit r9 = kotlin.Unit.INSTANCE
                        return r9
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = repositoriesState.collect(new AnonymousClass2(flowCollector, this), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, ViewModelKt.getViewModelScope(this), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 5000L, 0L, 2, null), initialRepo);
        this.repoFlow = stateFlowStateIn;
        this.numberAppsFlow = FlowKt.distinctUntilChanged(FlowKt.flowOn(new Flow() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$2

            /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$2$2, reason: invalid class name */
            /* JADX INFO: compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", Action.VALUE_ATTRIBUTE, "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 0, 0})
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ RepoDetailsViewModel this$0;

                /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$2$2", f = "RepoDetailsViewModel.kt", l = {223}, m = "emit")
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

                public AnonymousClass2(FlowCollector flowCollector, RepoDetailsViewModel repoDetailsViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = repoDetailsViewModel;
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
                        boolean r0 = r8 instanceof org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$2$2$1 r0 = (org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$2$2$1 r0 = new org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$2$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L57
                    L29:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L31:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow
                        org.fdroid.database.Repository r7 = (org.fdroid.database.Repository) r7
                        if (r7 == 0) goto L49
                        org.fdroid.fdroid.views.repos.RepoDetailsViewModel r2 = r6.this$0
                        org.fdroid.database.AppDao r2 = org.fdroid.fdroid.views.repos.RepoDetailsViewModel.access$getAppDao$p(r2)
                        long r4 = r7.getRepoId()
                        int r7 = r2.getNumberOfAppsInRepository(r4)
                        goto L4a
                    L49:
                        r7 = 0
                    L4a:
                        java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
                        r0.label = r3
                        java.lang.Object r7 = r8.emit(r7, r0)
                        if (r7 != r1) goto L57
                        return r1
                    L57:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = stateFlowStateIn.collect(new AnonymousClass2(flowCollector, this), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, Dispatchers.getIO()));
        this.archiveStateFlow = StateFlowKt.MutableStateFlow(archiveState(initialRepo));
    }

    /* JADX INFO: compiled from: RepoDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/fdroid/fdroid/views/repos/RepoDetailsViewModel$Companion;", "", "<init>", "()V", "TAG", "", "APP_KEY", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "Landroid/app/Application;", "getAPP_KEY", "()Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "REPO_KEY", "Lorg/fdroid/database/Repository;", "getREPO_KEY", "Factory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CreationExtras.Key getAPP_KEY() {
            return RepoDetailsViewModel.APP_KEY;
        }

        public final CreationExtras.Key getREPO_KEY() {
            return RepoDetailsViewModel.REPO_KEY;
        }

        public final ViewModelProvider.Factory getFactory() {
            return RepoDetailsViewModel.Factory;
        }
    }

    static {
        InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder = new InitializerViewModelFactoryBuilder();
        initializerViewModelFactoryBuilder.addInitializer(Reflection.getOrCreateKotlinClass(RepoDetailsViewModel.class), new Function1() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RepoDetailsViewModel.Factory$lambda$6$lambda$5((CreationExtras) obj);
            }
        });
        Factory = initializerViewModelFactoryBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RepoDetailsViewModel Factory$lambda$6$lambda$5(CreationExtras initializer) {
        Intrinsics.checkNotNullParameter(initializer, "$this$initializer");
        Object obj = initializer.get(APP_KEY);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type android.app.Application");
        Object obj2 = initializer.get(REPO_KEY);
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type org.fdroid.database.Repository");
        return new RepoDetailsViewModel((Application) obj, (Repository) obj2);
    }

    public final StateFlow getRepoFlow() {
        return this.repoFlow;
    }

    public final Flow getNumberAppsFlow() {
        return this.numberAppsFlow;
    }

    public final MutableStateFlow getArchiveStateFlow() {
        return this.archiveStateFlow;
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$setArchiveRepoEnabled$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.repos.RepoDetailsViewModel$setArchiveRepoEnabled$1", f = "RepoDetailsViewModel.kt", l = {87, 89, 90, 91, 96, 97}, m = "invokeSuspend")
    static final class C02171 extends SuspendLambda implements Function2 {
        final /* synthetic */ boolean $enabled;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02171(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$enabled = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return RepoDetailsViewModel.this.new C02171(this.$enabled, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02171) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0089 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00a7 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00a8  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00c8 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:48:0x00fe A[RETURN] */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v15 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                Method dump skipped, instruction units count: 276
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.views.repos.RepoDetailsViewModel.C02171.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$setArchiveRepoEnabled$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: RepoDetailsViewModel.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "org.fdroid.fdroid.views.repos.RepoDetailsViewModel$setArchiveRepoEnabled$1$1", f = "RepoDetailsViewModel.kt", l = {}, m = "invokeSuspend")
        static final class C00731 extends SuspendLambda implements Function2 {
            final /* synthetic */ Long $repoId;
            int label;
            final /* synthetic */ RepoDetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00731(RepoDetailsViewModel repoDetailsViewModel, Long l, Continuation continuation) {
                super(2, continuation);
                this.this$0 = repoDetailsViewModel;
                this.$repoId = l;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00731(this.this$0, this.$repoId, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((C00731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                RepoUpdateWorker.INSTANCE.updateNow(this.this$0.getApplication(), this.$repoId.longValue());
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$setArchiveRepoEnabled$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: RepoDetailsViewModel.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "org.fdroid.fdroid.views.repos.RepoDetailsViewModel$setArchiveRepoEnabled$1$2", f = "RepoDetailsViewModel.kt", l = {}, m = "invokeSuspend")
        static final class AnonymousClass2 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ RepoDetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(RepoDetailsViewModel repoDetailsViewModel, Continuation continuation) {
                super(2, continuation);
                this.this$0 = repoDetailsViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, continuation);
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
                Toast.makeText(this.this$0.getApplication(), R.string.repo_archive_failed, 0).show();
                return Unit.INSTANCE;
            }
        }
    }

    public final void setArchiveRepoEnabled(boolean enabled) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C02171(enabled, null), 2, null);
    }

    public final void deleteRepository() {
        List listEmptyList;
        DnsCache dnsCache = DnsCache.get();
        Repository repository = (Repository) this.repoFlow.getValue();
        if (repository == null || (listEmptyList = Repository.getAllMirrors$default(repository, false, 1, null)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        Iterator it = listEmptyList.iterator();
        while (it.hasNext()) {
            dnsCache.remove(((Mirror) it.next()).getUrl().getHost());
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass2(null), 2, null);
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$deleteRepository$2, reason: invalid class name */
    /* JADX INFO: compiled from: RepoDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.repos.RepoDetailsViewModel$deleteRepository$2", f = "RepoDetailsViewModel.kt", l = {}, m = "invokeSuspend")
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return RepoDetailsViewModel.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                RepoDetailsViewModel.this.repoManager.deleteRepository(RepoDetailsViewModel.this.repoId);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$updateUsernameAndPassword$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.repos.RepoDetailsViewModel$updateUsernameAndPassword$1", f = "RepoDetailsViewModel.kt", l = {}, m = "invokeSuspend")
    static final class C02191 extends SuspendLambda implements Function2 {
        final /* synthetic */ String $password;
        final /* synthetic */ String $username;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02191(String str, String str2, Continuation continuation) {
            super(2, continuation);
            this.$username = str;
            this.$password = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return RepoDetailsViewModel.this.new C02191(this.$username, this.$password, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                RepoDetailsViewModel.this.repoManager.updateUsernameAndPassword(RepoDetailsViewModel.this.repoId, this.$username, this.$password);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void updateUsernameAndPassword(String username, String password) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C02191(username, password, null), 2, null);
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$setMirrorEnabled$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.repos.RepoDetailsViewModel$setMirrorEnabled$1", f = "RepoDetailsViewModel.kt", l = {}, m = "invokeSuspend")
    static final class C02181 extends SuspendLambda implements Function2 {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ Mirror $mirror;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02181(Mirror mirror, boolean z, Continuation continuation) {
            super(2, continuation);
            this.$mirror = mirror;
            this.$enabled = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return RepoDetailsViewModel.this.new C02181(this.$mirror, this.$enabled, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C02181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                RepoDetailsViewModel.this.repoManager.setMirrorEnabled(RepoDetailsViewModel.this.repoId, this.$mirror, this.$enabled);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void setMirrorEnabled(Mirror mirror, boolean enabled) {
        Intrinsics.checkNotNullParameter(mirror, "mirror");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C02181(mirror, enabled, null), 2, null);
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsViewModel$deleteUserMirror$1, reason: invalid class name */
    /* JADX INFO: compiled from: RepoDetailsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.views.repos.RepoDetailsViewModel$deleteUserMirror$1", f = "RepoDetailsViewModel.kt", l = {}, m = "invokeSuspend")
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Mirror $mirror;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Mirror mirror, Continuation continuation) {
            super(2, continuation);
            this.$mirror = mirror;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return RepoDetailsViewModel.this.new AnonymousClass1(this.$mirror, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                RepoDetailsViewModel.this.repoManager.deleteUserMirror(RepoDetailsViewModel.this.repoId, this.$mirror);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void deleteUserMirror(Mirror mirror) {
        Intrinsics.checkNotNullParameter(mirror, "mirror");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(mirror, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArchiveState archiveState(Repository repository) {
        Object next;
        Iterator<T> it = this.repoManager.getRepositories().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Repository repository2 = (Repository) next;
            if (repository2.isArchiveRepo() && Intrinsics.areEqual(repository2.getCertificate(), repository.getCertificate())) {
                break;
            }
        }
        Repository repository3 = (Repository) next;
        Boolean boolValueOf = repository3 != null ? Boolean.valueOf(repository3.getEnabled()) : null;
        if (Intrinsics.areEqual(boolValueOf, Boolean.TRUE)) {
            return ArchiveState.ENABLED;
        }
        if (Intrinsics.areEqual(boolValueOf, Boolean.FALSE)) {
            return ArchiveState.DISABLED;
        }
        if (boolValueOf != null) {
            throw new NoWhenBranchMatchedException();
        }
        return ArchiveState.UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArchiveState toArchiveState(boolean z) {
        return z ? ArchiveState.ENABLED : ArchiveState.DISABLED;
    }

    public final Object generateQrCode(AppCompatActivity appCompatActivity, Continuation continuation) {
        Repository repository = (Repository) this.repoFlow.getValue();
        if (repository == null || StringsKt.startsWith$default(repository.getAddress(), "content://", false, 2, (Object) null) || StringsKt.startsWith$default(repository.getAddress(), "file://", false, 2, (Object) null)) {
            return null;
        }
        return UtilsKt.generateQrBitmapKt(appCompatActivity, repository.getShareUri(), continuation);
    }
}
