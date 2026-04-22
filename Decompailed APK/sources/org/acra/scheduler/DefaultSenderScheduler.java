package org.acra.scheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import kotlin.jvm.internal.Intrinsics;
import org.acra.config.CoreConfiguration;
import org.acra.sender.JobSenderService;
import org.acra.sender.ReportSender;
import org.acra.sender.SendingConductor;
import org.acra.util.BundleKt;
import org.acra.util.IOUtils;

/* JADX INFO: compiled from: DefaultSenderScheduler.kt */
/* JADX INFO: loaded from: classes2.dex */
public class DefaultSenderScheduler implements SenderScheduler {
    private final CoreConfiguration config;
    private final Context context;

    protected final void configureExtras(Bundle extras) {
        Intrinsics.checkNotNullParameter(extras, "extras");
    }

    public DefaultSenderScheduler(Context context, CoreConfiguration config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.context = context;
        this.config = config;
    }

    @Override // org.acra.scheduler.SenderScheduler
    public void scheduleReportSending(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("acraConfig", IOUtils.INSTANCE.serialize(this.config));
        bundle.putBoolean("onlySendSilentReports", z);
        configureExtras(bundle);
        ReportSender.Companion companion = ReportSender.Companion;
        if (companion.hasBackgroundSenders(this.context, this.config)) {
            Object systemService = this.context.getSystemService("jobscheduler");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.job.JobScheduler");
            JobInfo.Builder extras = new JobInfo.Builder(0, new ComponentName(this.context, (Class<?>) JobSenderService.class)).setExtras(BundleKt.toPersistableBundle(bundle));
            Intrinsics.checkNotNull(extras);
            configureJob(extras);
            ((JobScheduler) systemService).schedule(extras.build());
        }
        if (companion.hasForegroundSenders(this.context, this.config)) {
            new SendingConductor(this.context, this.config).sendReports(true, bundle);
        }
    }

    protected void configureJob(JobInfo.Builder job) {
        Intrinsics.checkNotNullParameter(job, "job");
        job.setOverrideDeadline(0L);
    }
}
