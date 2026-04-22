package org.acra.builder;

import android.content.Context;
import android.os.Debug;
import android.os.Looper;
import android.os.StrictMode;
import java.io.File;
import java.lang.Thread;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.config.CoreConfiguration;
import org.acra.config.ReportingAdministrator;
import org.acra.data.CrashReportData;
import org.acra.data.CrashReportDataFactory;
import org.acra.file.CrashReportPersister;
import org.acra.file.ReportLocator;
import org.acra.interaction.ReportInteractionExecutor;
import org.acra.log.ACRALog;
import org.acra.scheduler.SchedulerStarter;
import org.acra.util.ProcessFinisher;
import org.acra.util.ToastSender;

/* JADX INFO: compiled from: ReportExecutor.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ReportExecutor {
    private final CoreConfiguration config;
    private final Context context;
    private final CrashReportDataFactory crashReportDataFactory;
    private final Thread.UncaughtExceptionHandler defaultExceptionHandler;
    private boolean isEnabled;
    private final LastActivityManager lastActivityManager;
    private final ProcessFinisher processFinisher;
    private final List reportingAdministrators;
    private final SchedulerStarter schedulerStarter;

    private final void saveCrashReportFile(File file, CrashReportData crashReportData) {
        try {
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Writing crash report file " + file);
            }
            new CrashReportPersister().store(crashReportData, file);
        } catch (Exception e) {
            ACRA.log.e(ACRA.LOG_TAG, "An error occurred while writing the report file...", e);
        }
    }

    public ReportExecutor(Context context, CoreConfiguration config, CrashReportDataFactory crashReportDataFactory, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, ProcessFinisher processFinisher, SchedulerStarter schedulerStarter, LastActivityManager lastActivityManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(crashReportDataFactory, "crashReportDataFactory");
        Intrinsics.checkNotNullParameter(processFinisher, "processFinisher");
        Intrinsics.checkNotNullParameter(schedulerStarter, "schedulerStarter");
        Intrinsics.checkNotNullParameter(lastActivityManager, "lastActivityManager");
        this.context = context;
        this.config = config;
        this.crashReportDataFactory = crashReportDataFactory;
        this.defaultExceptionHandler = uncaughtExceptionHandler;
        this.processFinisher = processFinisher;
        this.schedulerStarter = schedulerStarter;
        this.lastActivityManager = lastActivityManager;
        this.reportingAdministrators = config.getPluginLoader().loadEnabled(config, ReportingAdministrator.class);
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public final void handReportToDefaultExceptionHandler(Thread t, Throwable e) {
        Intrinsics.checkNotNullParameter(t, "t");
        Intrinsics.checkNotNullParameter(e, "e");
        if (this.defaultExceptionHandler != null) {
            ACRA.log.i(ACRA.LOG_TAG, "ACRA is disabled for " + this.context.getPackageName() + " - forwarding uncaught Exception on to default ExceptionHandler");
            this.defaultExceptionHandler.uncaughtException(t, e);
            return;
        }
        ACRALog aCRALog = ACRA.log;
        String str = ACRA.LOG_TAG;
        aCRALog.e(str, "ACRA is disabled for " + this.context.getPackageName() + " - no default ExceptionHandler");
        ACRA.log.e(str, "ACRA caught a " + e.getClass().getSimpleName() + " for " + this.context.getPackageName(), e);
    }

    public final void execute(ReportBuilder reportBuilder) {
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        if (!this.isEnabled) {
            ACRA.log.w(ACRA.LOG_TAG, "ACRA is disabled. Report not sent.");
            return;
        }
        CrashReportData crashReportDataCreateCrashData = null;
        ReportingAdministrator reportingAdministrator = null;
        for (ReportingAdministrator reportingAdministrator2 : this.reportingAdministrators) {
            try {
                if (!reportingAdministrator2.shouldStartCollecting(this.context, this.config, reportBuilder)) {
                    reportingAdministrator = reportingAdministrator2;
                }
            } catch (Exception e) {
                ACRA.log.w(ACRA.LOG_TAG, "ReportingAdministrator " + reportingAdministrator2.getClass().getName() + " threw exception", e);
            }
        }
        if (reportingAdministrator == null) {
            crashReportDataCreateCrashData = this.crashReportDataFactory.createCrashData(reportBuilder);
            for (ReportingAdministrator reportingAdministrator3 : this.reportingAdministrators) {
                try {
                    if (!reportingAdministrator3.shouldSendReport(this.context, this.config, crashReportDataCreateCrashData)) {
                        reportingAdministrator = reportingAdministrator3;
                    }
                } catch (Exception e2) {
                    ACRA.log.w(ACRA.LOG_TAG, "ReportingAdministrator " + reportingAdministrator3.getClass().getName() + " threw exception", e2);
                }
            }
        } else if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Not collecting crash report because of ReportingAdministrator " + reportingAdministrator.getClass().getName());
        }
        boolean z = true;
        if (reportBuilder.isEndApplication()) {
            boolean z2 = true;
            for (ReportingAdministrator reportingAdministrator4 : this.reportingAdministrators) {
                try {
                    if (!reportingAdministrator4.shouldFinishActivity(this.context, this.config, this.lastActivityManager)) {
                        z2 = false;
                    }
                } catch (Exception e3) {
                    ACRA.log.w(ACRA.LOG_TAG, "ReportingAdministrator " + reportingAdministrator4.getClass().getName() + " threw exception", e3);
                }
            }
            if (z2) {
                this.processFinisher.finishLastActivity(reportBuilder.getUncaughtExceptionThread());
            }
        }
        if (reportingAdministrator == null) {
            StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
            Intrinsics.checkNotNull(crashReportDataCreateCrashData);
            File reportFileName = getReportFileName(crashReportDataCreateCrashData);
            saveCrashReportFile(reportFileName, crashReportDataCreateCrashData);
            ReportInteractionExecutor reportInteractionExecutor = new ReportInteractionExecutor(this.context, this.config);
            if (reportBuilder.isSendSilently()) {
                sendReport(reportFileName, reportInteractionExecutor.hasInteractions());
            } else if (reportInteractionExecutor.performInteractions(reportFileName)) {
                sendReport(reportFileName, false);
            }
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
        } else {
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Not sending crash report because of ReportingAdministrator " + reportingAdministrator.getClass().getName());
            }
            try {
                reportingAdministrator.notifyReportDropped(this.context, this.config);
            } catch (Exception e4) {
                ACRA.log.w(ACRA.LOG_TAG, "ReportingAdministrator " + reportingAdministrator.getClass().getName() + " threw exeption", e4);
            }
        }
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Wait for Interactions + worker ended. Kill Application ? " + reportBuilder.isEndApplication());
        }
        if (reportBuilder.isEndApplication()) {
            for (ReportingAdministrator reportingAdministrator5 : this.reportingAdministrators) {
                try {
                    if (!reportingAdministrator5.shouldKillApplication(this.context, this.config, reportBuilder, crashReportDataCreateCrashData)) {
                        z = false;
                    }
                } catch (Exception e5) {
                    ACRA.log.w(ACRA.LOG_TAG, "ReportingAdministrator " + reportingAdministrator5.getClass().getName() + " threw exception", e5);
                }
            }
            if (z) {
                if (Debug.isDebuggerConnected()) {
                    final String str = "Warning: Acra may behave differently with a debugger attached";
                    new Thread(new Runnable() { // from class: org.acra.builder.ReportExecutor$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            ReportExecutor.execute$lambda$12(this.f$0, str);
                        }
                    }).start();
                    ACRA.log.w(ACRA.LOG_TAG, "Warning: Acra may behave differently with a debugger attached");
                } else {
                    Thread uncaughtExceptionThread = reportBuilder.getUncaughtExceptionThread();
                    Throwable exception = reportBuilder.getException();
                    if (exception == null) {
                        exception = new RuntimeException();
                    }
                    endApplication(uncaughtExceptionThread, exception);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$12(ReportExecutor reportExecutor, String str) {
        Looper.prepare();
        ToastSender.sendToast(reportExecutor.context, str, 1);
        Looper.loop();
    }

    private final void endApplication(Thread thread, Throwable th) {
        boolean alsoReportToAndroidFramework = this.config.getAlsoReportToAndroidFramework();
        if (thread != null && alsoReportToAndroidFramework && this.defaultExceptionHandler != null) {
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Handing Exception on to default ExceptionHandler");
            }
            this.defaultExceptionHandler.uncaughtException(thread, th);
            return;
        }
        this.processFinisher.endApplication();
    }

    private final void sendReport(File file, boolean z) {
        if (!this.isEnabled) {
            ACRA.log.w(ACRA.LOG_TAG, "Would be sending reports, but ACRA is disabled");
        } else {
            this.schedulerStarter.scheduleReports(file, z);
        }
    }

    private final File getReportFileName(CrashReportData crashReportData) {
        String string = crashReportData.getString(ReportField.USER_CRASH_DATE);
        String string2 = crashReportData.getString(ReportField.IS_SILENT);
        return new File(new ReportLocator(this.context).getUnapprovedFolder(), string + ((string2 == null || !Boolean.parseBoolean(string2)) ? "" : ACRAConstants.SILENT_SUFFIX) + ".stacktrace");
    }
}
