package androidx.work.impl.background.systemjob;

import android.app.job.JobScheduler;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JobSchedulerExt.kt */
/* JADX INFO: loaded from: classes.dex */
final class JobScheduler34 {
    public static final JobScheduler34 INSTANCE = new JobScheduler34();

    private JobScheduler34() {
    }

    public final JobScheduler forNamespace(JobScheduler jobScheduler) {
        Intrinsics.checkNotNullParameter(jobScheduler, "jobScheduler");
        JobScheduler jobSchedulerForNamespace = jobScheduler.forNamespace("androidx.work.systemjobscheduler");
        Intrinsics.checkNotNullExpressionValue(jobSchedulerForNamespace, "jobScheduler.forNamespace(WORKMANAGER_NAMESPACE)");
        return jobSchedulerForNamespace;
    }
}
