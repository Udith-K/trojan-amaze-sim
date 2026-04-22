package androidx.work;

import android.content.Context;
import androidx.work.impl.WorkManagerImpl;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: WorkManager.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class WorkManager {
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: compiled from: WorkManager.kt */
    public enum UpdateResult {
        NOT_APPLIED,
        APPLIED_IMMEDIATELY,
        APPLIED_FOR_NEXT_RUN
    }

    public static WorkManager getInstance(Context context) {
        return Companion.getInstance(context);
    }

    public abstract Operation cancelUniqueWork(String str);

    public abstract Operation enqueue(List list);

    public abstract Operation enqueueUniquePeriodicWork(String str, ExistingPeriodicWorkPolicy existingPeriodicWorkPolicy, PeriodicWorkRequest periodicWorkRequest);

    public abstract Operation enqueueUniqueWork(String str, ExistingWorkPolicy existingWorkPolicy, List list);

    public abstract Flow getWorkInfosForUniqueWorkFlow(String str);

    /* JADX INFO: compiled from: WorkManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public WorkManager getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(context);
            Intrinsics.checkNotNullExpressionValue(workManagerImpl, "getInstance(context)");
            return workManagerImpl;
        }
    }

    public final Operation enqueue(WorkRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return enqueue(CollectionsKt.listOf(request));
    }

    public Operation enqueueUniqueWork(String uniqueWorkName, ExistingWorkPolicy existingWorkPolicy, OneTimeWorkRequest request) {
        Intrinsics.checkNotNullParameter(uniqueWorkName, "uniqueWorkName");
        Intrinsics.checkNotNullParameter(existingWorkPolicy, "existingWorkPolicy");
        Intrinsics.checkNotNullParameter(request, "request");
        return enqueueUniqueWork(uniqueWorkName, existingWorkPolicy, CollectionsKt.listOf(request));
    }
}
