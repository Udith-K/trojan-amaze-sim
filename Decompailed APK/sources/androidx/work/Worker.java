package androidx.work;

import android.content.Context;
import androidx.work.ListenableWorker;
import ch.qos.logback.core.CoreConstants;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Worker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH'J\b\u0010\t\u001a\u00020\nH\u0017J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0016J\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\f¨\u0006\u000e"}, d2 = {"Landroidx/work/Worker;", "Landroidx/work/ListenableWorker;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "getForegroundInfo", "Landroidx/work/ForegroundInfo;", "getForegroundInfoAsync", "Lcom/google/common/util/concurrent/ListenableFuture;", "startWork", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class Worker extends ListenableWorker {
    public abstract ListenableWorker.Result doWork();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Worker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture startWork() {
        Executor backgroundExecutor = getBackgroundExecutor();
        Intrinsics.checkNotNullExpressionValue(backgroundExecutor, "backgroundExecutor");
        return WorkerKt.future(backgroundExecutor, new Function0() { // from class: androidx.work.Worker.startWork.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ListenableWorker.Result invoke() {
                return Worker.this.doWork();
            }
        });
    }

    @Override // androidx.work.ListenableWorker
    public ListenableFuture getForegroundInfoAsync() {
        Executor backgroundExecutor = getBackgroundExecutor();
        Intrinsics.checkNotNullExpressionValue(backgroundExecutor, "backgroundExecutor");
        return WorkerKt.future(backgroundExecutor, new Function0() { // from class: androidx.work.Worker.getForegroundInfoAsync.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final ForegroundInfo invoke() {
                return Worker.this.getForegroundInfo();
            }
        });
    }

    public ForegroundInfo getForegroundInfo() {
        throw new IllegalStateException("Expedited WorkRequests require a Worker to provide an implementation for `getForegroundInfo()`");
    }
}
