package org.acra.scheduler;

import android.content.Context;
import java.io.File;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;
import org.acra.file.ReportLocator;

/* JADX INFO: compiled from: SchedulerStarter.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class SchedulerStarter {
    private final ReportLocator locator;
    private final SenderScheduler senderScheduler;

    public final void scheduleReports(File file, boolean z) {
        if (file != null) {
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Mark " + file.getName() + " as approved.");
            }
            File file2 = new File(this.locator.getApprovedFolder(), file.getName());
            if (!file.renameTo(file2)) {
                ACRA.log.w(ACRA.LOG_TAG, "Could not rename approved report from " + file + " to " + file2);
            }
        }
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Schedule report sending");
        }
        this.senderScheduler.scheduleReportSending(z);
    }

    public SchedulerStarter(Context context, CoreConfiguration config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.locator = new ReportLocator(context);
        List listLoadEnabled = config.getPluginLoader().loadEnabled(config, SenderSchedulerFactory.class);
        if (listLoadEnabled.isEmpty()) {
            this.senderScheduler = new DefaultSenderScheduler(context, config);
            return;
        }
        SenderScheduler senderSchedulerCreate = ((SenderSchedulerFactory) listLoadEnabled.get(0)).create(context, config);
        this.senderScheduler = senderSchedulerCreate;
        if (listLoadEnabled.size() > 1) {
            ACRA.log.w(ACRA.LOG_TAG, "More than one SenderScheduler found. Will use only " + senderSchedulerCreate.getClass().getSimpleName());
        }
    }
}
