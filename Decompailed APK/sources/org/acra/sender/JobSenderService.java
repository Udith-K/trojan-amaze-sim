package org.acra.sender;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Bundle;
import android.os.PersistableBundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.acra.config.CoreConfiguration;
import org.acra.util.IOUtils;

/* JADX INFO: compiled from: JobSenderService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"Lorg/acra/sender/JobSenderService;", "Landroid/app/job/JobService;", "<init>", "()V", "onStartJob", "", "params", "Landroid/app/job/JobParameters;", "onStopJob", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class JobSenderService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters params) {
        Intrinsics.checkNotNullParameter(params, "params");
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters params) {
        Intrinsics.checkNotNullParameter(params, "params");
        final PersistableBundle extras = params.getExtras();
        Intrinsics.checkNotNullExpressionValue(extras, "getExtras(...)");
        final CoreConfiguration coreConfiguration = (CoreConfiguration) IOUtils.INSTANCE.deserialize(CoreConfiguration.class, extras.getString("acraConfig"));
        if (coreConfiguration == null) {
            return true;
        }
        new Thread(new Runnable() { // from class: org.acra.sender.JobSenderService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                JobSenderService.onStartJob$lambda$0(this.f$0, coreConfiguration, extras, params);
            }
        }).start();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStartJob$lambda$0(JobSenderService jobSenderService, CoreConfiguration coreConfiguration, PersistableBundle persistableBundle, JobParameters jobParameters) {
        new SendingConductor(jobSenderService, coreConfiguration).sendReports(false, new Bundle(persistableBundle));
        jobSenderService.jobFinished(jobParameters, false);
    }
}
