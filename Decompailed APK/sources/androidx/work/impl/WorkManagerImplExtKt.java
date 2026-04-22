package androidx.work.impl;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.R$bool;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.greedy.GreedyScheduler;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: WorkManagerImplExt.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class WorkManagerImplExtKt {
    public static final WorkManagerImpl createWorkManager(Context context, Configuration configuration) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return createWorkManager$default(context, configuration, null, null, null, null, null, 124, null);
    }

    public static /* synthetic */ WorkManagerImpl createWorkManager$default(Context context, Configuration configuration, TaskExecutor taskExecutor, WorkDatabase workDatabase, Trackers trackers, Processor processor, Function6 function6, int i, Object obj) {
        WorkDatabase workDatabaseCreate;
        Trackers trackers2;
        TaskExecutor workManagerTaskExecutor = (i & 4) != 0 ? new WorkManagerTaskExecutor(configuration.getTaskExecutor()) : taskExecutor;
        if ((i & 8) != 0) {
            WorkDatabase.Companion companion = WorkDatabase.INSTANCE;
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
            SerialExecutor serialTaskExecutor = workManagerTaskExecutor.getSerialTaskExecutor();
            Intrinsics.checkNotNullExpressionValue(serialTaskExecutor, "workTaskExecutor.serialTaskExecutor");
            workDatabaseCreate = companion.create(applicationContext, serialTaskExecutor, configuration.getClock(), context.getResources().getBoolean(R$bool.workmanager_test_configuration));
        } else {
            workDatabaseCreate = workDatabase;
        }
        if ((i & 16) != 0) {
            Context applicationContext2 = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "context.applicationContext");
            trackers2 = new Trackers(applicationContext2, workManagerTaskExecutor, null, null, null, null, 60, null);
        } else {
            trackers2 = trackers;
        }
        return createWorkManager(context, configuration, workManagerTaskExecutor, workDatabaseCreate, trackers2, (i & 32) != 0 ? new Processor(context.getApplicationContext(), configuration, workManagerTaskExecutor, workDatabaseCreate) : processor, (i & 64) != 0 ? WorkManagerImplExtKt$WorkManagerImpl$1.INSTANCE : function6);
    }

    public static final WorkManagerImpl createWorkManager(Context context, Configuration configuration, TaskExecutor workTaskExecutor, WorkDatabase workDatabase, Trackers trackers, Processor processor, Function6 schedulersCreator) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(workTaskExecutor, "workTaskExecutor");
        Intrinsics.checkNotNullParameter(workDatabase, "workDatabase");
        Intrinsics.checkNotNullParameter(trackers, "trackers");
        Intrinsics.checkNotNullParameter(processor, "processor");
        Intrinsics.checkNotNullParameter(schedulersCreator, "schedulersCreator");
        return new WorkManagerImpl(context.getApplicationContext(), configuration, workTaskExecutor, workDatabase, (List) schedulersCreator.invoke(context, configuration, workTaskExecutor, workDatabase, trackers, processor), processor, trackers);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List createSchedulers(Context context, Configuration configuration, TaskExecutor taskExecutor, WorkDatabase workDatabase, Trackers trackers, Processor processor) {
        Scheduler schedulerCreateBestAvailableBackgroundScheduler = Schedulers.createBestAvailableBackgroundScheduler(context, workDatabase, configuration);
        Intrinsics.checkNotNullExpressionValue(schedulerCreateBestAvailableBackgroundScheduler, "createBestAvailableBackg…kDatabase, configuration)");
        return CollectionsKt.listOf((Object[]) new Scheduler[]{schedulerCreateBestAvailableBackgroundScheduler, new GreedyScheduler(context, configuration, trackers, processor, new WorkLauncherImpl(processor, taskExecutor), taskExecutor)});
    }

    public static final CoroutineScope createWorkManagerScope(TaskExecutor taskExecutor) {
        Intrinsics.checkNotNullParameter(taskExecutor, "taskExecutor");
        CoroutineDispatcher taskCoroutineDispatcher = taskExecutor.getTaskCoroutineDispatcher();
        Intrinsics.checkNotNullExpressionValue(taskCoroutineDispatcher, "taskExecutor.taskCoroutineDispatcher");
        return CoroutineScopeKt.CoroutineScope(taskCoroutineDispatcher);
    }
}
