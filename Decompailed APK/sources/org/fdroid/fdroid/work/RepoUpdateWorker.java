package org.fdroid.fdroid.work;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import androidx.work.Constraints;
import androidx.work.CoroutineWorker;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ForegroundInfo;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.Action;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.NotificationManager;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.RepoUpdateManager;
import org.fdroid.fdroid.net.ConnectivityMonitorService;

/* JADX INFO: compiled from: RepoUpdateWorker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u000e\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u0012H\u0096@¢\u0006\u0002\u0010\u0010R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0014"}, d2 = {"Lorg/fdroid/fdroid/work/RepoUpdateWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "nm", "Lorg/fdroid/fdroid/NotificationManager;", "repoUpdateManager", "Lorg/fdroid/fdroid/RepoUpdateManager;", "kotlin.jvm.PlatformType", "Lorg/fdroid/fdroid/RepoUpdateManager;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForegroundInfo", "Landroidx/work/ForegroundInfo;", "Companion", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RepoUpdateWorker extends CoroutineWorker {
    private static final String UNIQUE_WORK_NAME_AUTO_UPDATE = "autoUpdate";
    private final NotificationManager nm;
    private final RepoUpdateManager repoUpdateManager;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: renamed from: org.fdroid.fdroid.work.RepoUpdateWorker$doWork$1, reason: invalid class name */
    /* JADX INFO: compiled from: RepoUpdateWorker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "org.fdroid.fdroid.work.RepoUpdateWorker", f = "RepoUpdateWorker.kt", l = {134, 134}, m = "doWork")
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RepoUpdateWorker.this.doWork(this);
        }
    }

    public static final void scheduleOrCancel(Context context) {
        INSTANCE.scheduleOrCancel(context);
    }

    public static final void updateNow(Context context) {
        INSTANCE.updateNow(context);
    }

    public static final void updateNow(Context context, long j) {
        INSTANCE.updateNow(context, j);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RepoUpdateWorker(Context appContext, WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.nm = new NotificationManager(appContext);
        this.repoUpdateManager = FDroidApp.getRepoUpdateManager(appContext);
    }

    /* JADX INFO: compiled from: RepoUpdateWorker.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e2\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/fdroid/fdroid/work/RepoUpdateWorker$Companion;", "", "<init>", "()V", "UNIQUE_WORK_NAME_AUTO_UPDATE", "", "updateNow", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "repoId", "", "scheduleOrCancel", "getAutoUpdateWorkInfo", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/work/WorkInfo;", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void updateNow(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            updateNow$default(this, context, 0L, 2, null);
        }

        private Companion() {
        }

        public static /* synthetic */ void updateNow$default(Companion companion, Context context, long j, int i, Object obj) {
            if ((i & 2) != 0) {
                j = -1;
            }
            companion.updateNow(context, j);
        }

        public final void updateNow(Context context, long repoId) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (repoId < 0) {
                CleanCacheWorker.force(context);
            }
            Log.i(RepoUpdateWorkerKt.TAG, "Update repo with ID " + repoId + " now!");
            if (FDroidApp.networkState > 0 && !Preferences.get().isOnDemandDownloadAllowed()) {
                Toast.makeText(context, R.string.updates_disabled_by_settings, 1).show();
                return;
            }
            if (ConnectivityMonitorService.getNetworkState(context) == 0) {
                Toast.makeText(context, R.string.warning_no_internet, 1).show();
                return;
            }
            OneTimeWorkRequest.Builder builder = (OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(RepoUpdateWorker.class).setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST);
            if (repoId >= 0) {
                Pair[] pairArr = {TuplesKt.to("repoId", Long.valueOf(repoId))};
                Data.Builder builder2 = new Data.Builder();
                Pair pair = pairArr[0];
                builder2.put((String) pair.getFirst(), pair.getSecond());
                builder.setInputData(builder2.build());
            }
            WorkManager.Companion.getInstance(context).enqueue((OneTimeWorkRequest) builder.build());
        }

        public final void scheduleOrCancel(Context context) {
            NetworkType networkType;
            Intrinsics.checkNotNullParameter(context, "context");
            Preferences preferences = Preferences.get();
            WorkManager companion = WorkManager.Companion.getInstance(context);
            if (preferences.getUpdateInterval() == Preferences.UPDATE_INTERVAL_DISABLED || (preferences.getOverData() == 0 && preferences.getOverWifi() == 0)) {
                Log.w(RepoUpdateWorkerKt.TAG, "Not scheduling job due to settings!");
                companion.cancelUniqueWork(RepoUpdateWorker.UNIQUE_WORK_NAME_AUTO_UPDATE);
                return;
            }
            Log.i(RepoUpdateWorkerKt.TAG, "scheduleOrCancel: enqueueUniquePeriodicWork");
            if (preferences.getOverData() == 2 && preferences.getOverWifi() == 2) {
                networkType = NetworkType.CONNECTED;
            } else {
                networkType = NetworkType.UNMETERED;
            }
            companion.enqueueUniquePeriodicWork(RepoUpdateWorker.UNIQUE_WORK_NAME_AUTO_UPDATE, ExistingPeriodicWorkPolicy.UPDATE, (PeriodicWorkRequest) ((PeriodicWorkRequest.Builder) new PeriodicWorkRequest.Builder(RepoUpdateWorker.class, preferences.getUpdateInterval(), TimeUnit.MILLISECONDS, 15L, TimeUnit.MINUTES).setConstraints(new Constraints.Builder().setRequiresBatteryNotLow(true).setRequiresStorageNotLow(true).setRequiredNetworkType(networkType).build())).build());
        }

        public final Flow getAutoUpdateWorkInfo(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            final Flow workInfosForUniqueWorkFlow = WorkManager.Companion.getInstance(context).getWorkInfosForUniqueWorkFlow(RepoUpdateWorker.UNIQUE_WORK_NAME_AUTO_UPDATE);
            return new Flow() { // from class: org.fdroid.fdroid.work.RepoUpdateWorker$Companion$getAutoUpdateWorkInfo$$inlined$map$1

                /* JADX INFO: renamed from: org.fdroid.fdroid.work.RepoUpdateWorker$Companion$getAutoUpdateWorkInfo$$inlined$map$1$2, reason: invalid class name */
                /* JADX INFO: compiled from: Emitters.kt */
                @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", Action.VALUE_ATTRIBUTE, "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 0, 0})
                public static final class AnonymousClass2<T> implements FlowCollector {
                    final /* synthetic */ FlowCollector $this_unsafeFlow;

                    /* JADX INFO: renamed from: org.fdroid.fdroid.work.RepoUpdateWorker$Companion$getAutoUpdateWorkInfo$$inlined$map$1$2$1, reason: invalid class name */
                    /* JADX INFO: compiled from: Emitters.kt */
                    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                    @DebugMetadata(c = "org.fdroid.fdroid.work.RepoUpdateWorker$Companion$getAutoUpdateWorkInfo$$inlined$map$1$2", f = "RepoUpdateWorker.kt", l = {223}, m = "emit")
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
                    public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                        /*
                            r4 = this;
                            boolean r0 = r6 instanceof org.fdroid.fdroid.work.RepoUpdateWorker$Companion$getAutoUpdateWorkInfo$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            org.fdroid.fdroid.work.RepoUpdateWorker$Companion$getAutoUpdateWorkInfo$$inlined$map$1$2$1 r0 = (org.fdroid.fdroid.work.RepoUpdateWorker$Companion$getAutoUpdateWorkInfo$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            org.fdroid.fdroid.work.RepoUpdateWorker$Companion$getAutoUpdateWorkInfo$$inlined$map$1$2$1 r0 = new org.fdroid.fdroid.work.RepoUpdateWorker$Companion$getAutoUpdateWorkInfo$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L31
                            if (r2 != r3) goto L29
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L46
                        L29:
                            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                            r5.<init>(r6)
                            throw r5
                        L31:
                            kotlin.ResultKt.throwOnFailure(r6)
                            kotlinx.coroutines.flow.FlowCollector r6 = r4.$this_unsafeFlow
                            java.util.List r5 = (java.util.List) r5
                            r2 = 0
                            java.lang.Object r5 = kotlin.collections.CollectionsKt.getOrNull(r5, r2)
                            r0.label = r3
                            java.lang.Object r5 = r6.emit(r5, r0)
                            if (r5 != r1) goto L46
                            return r1
                        L46:
                            kotlin.Unit r5 = kotlin.Unit.INSTANCE
                            return r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.work.RepoUpdateWorker$Companion$getAutoUpdateWorkInfo$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public Object collect(FlowCollector flowCollector, Continuation continuation) {
                    Object objCollect = workInfosForUniqueWorkFlow.collect(new AnonymousClass2(flowCollector), continuation);
                    return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x006a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.work.CoroutineWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object doWork(kotlin.coroutines.Continuation r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof org.fdroid.fdroid.work.RepoUpdateWorker.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            org.fdroid.fdroid.work.RepoUpdateWorker$doWork$1 r0 = (org.fdroid.fdroid.work.RepoUpdateWorker.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            org.fdroid.fdroid.work.RepoUpdateWorker$doWork$1 r0 = new org.fdroid.fdroid.work.RepoUpdateWorker$doWork$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L49
            if (r2 == r4) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r0 = r0.L$0
            org.fdroid.fdroid.work.RepoUpdateWorker r0 = (org.fdroid.fdroid.work.RepoUpdateWorker) r0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L30
            goto L78
        L30:
            r7 = move-exception
            goto L6f
        L32:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L3a:
            java.lang.Object r2 = r0.L$1
            org.fdroid.fdroid.work.RepoUpdateWorker r2 = (org.fdroid.fdroid.work.RepoUpdateWorker) r2
            java.lang.Object r4 = r0.L$0
            org.fdroid.fdroid.work.RepoUpdateWorker r4 = (org.fdroid.fdroid.work.RepoUpdateWorker) r4
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L46
            goto L5b
        L46:
            r7 = move-exception
            r0 = r4
            goto L6f
        L49:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6     // Catch: java.lang.Exception -> L6d
            r0.L$1 = r6     // Catch: java.lang.Exception -> L6d
            r0.label = r4     // Catch: java.lang.Exception -> L6d
            java.lang.Object r7 = r6.getForegroundInfo(r0)     // Catch: java.lang.Exception -> L6d
            if (r7 != r1) goto L59
            return r1
        L59:
            r2 = r6
            r4 = r2
        L5b:
            androidx.work.ForegroundInfo r7 = (androidx.work.ForegroundInfo) r7     // Catch: java.lang.Exception -> L46
            r0.L$0 = r4     // Catch: java.lang.Exception -> L46
            r5 = 0
            r0.L$1 = r5     // Catch: java.lang.Exception -> L46
            r0.label = r3     // Catch: java.lang.Exception -> L46
            java.lang.Object r7 = r2.setForeground(r7, r0)     // Catch: java.lang.Exception -> L46
            if (r7 != r1) goto L6b
            return r1
        L6b:
            r0 = r4
            goto L78
        L6d:
            r7 = move-exception
            r0 = r6
        L6f:
            java.lang.String r1 = org.fdroid.fdroid.work.RepoUpdateWorkerKt.access$getTAG$p()
            java.lang.String r2 = "Error while running setForeground"
            android.util.Log.e(r1, r2, r7)
        L78:
            androidx.work.Data r7 = r0.getInputData()
            java.lang.String r1 = "repoId"
            r2 = -1
            long r1 = r7.getLong(r1, r2)
            r3 = 0
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 < 0) goto L92
            org.fdroid.fdroid.RepoUpdateManager r7 = r0.repoUpdateManager     // Catch: java.lang.Exception -> L90
            r7.updateRepo(r1)     // Catch: java.lang.Exception -> L90
            goto L97
        L90:
            r7 = move-exception
            goto L9c
        L92:
            org.fdroid.fdroid.RepoUpdateManager r7 = r0.repoUpdateManager     // Catch: java.lang.Exception -> L90
            r7.updateRepos()     // Catch: java.lang.Exception -> L90
        L97:
            androidx.work.ListenableWorker$Result r7 = androidx.work.ListenableWorker.Result.success()     // Catch: java.lang.Exception -> L90
            goto La9
        L9c:
            java.lang.String r0 = org.fdroid.fdroid.work.RepoUpdateWorkerKt.access$getTAG$p()
            java.lang.String r1 = "Error updating repos"
            android.util.Log.e(r0, r1, r7)
            androidx.work.ListenableWorker$Result r7 = androidx.work.ListenableWorker.Result.failure()
        La9:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.work.RepoUpdateWorker.doWork(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.work.CoroutineWorker
    public Object getForegroundInfo(Continuation continuation) {
        Notification notificationBuild = NotificationManager.getRepoUpdateNotification$default(this.nm, null, null, 3, null).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        if (Build.VERSION.SDK_INT >= 29) {
            return new ForegroundInfo(0, notificationBuild, 1);
        }
        return new ForegroundInfo(0, notificationBuild);
    }
}
