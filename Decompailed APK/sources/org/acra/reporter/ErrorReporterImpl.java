package org.acra.reporter;

import android.app.Application;
import android.content.SharedPreferences;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.ErrorReporter;
import org.acra.builder.LastActivityManager;
import org.acra.builder.ReportBuilder;
import org.acra.builder.ReportExecutor;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportDataFactory;
import org.acra.log.ACRALog;
import org.acra.prefs.SharedPreferencesFactory;
import org.acra.scheduler.SchedulerStarter;
import org.acra.startup.StartupProcessorExecutor;
import org.acra.util.ProcessFinisher;

/* JADX INFO: compiled from: ErrorReporterImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ErrorReporterImpl implements Thread.UncaughtExceptionHandler, SharedPreferences.OnSharedPreferenceChangeListener, ErrorReporter {
    private final Application context;
    private final Map customData;
    private final Thread.UncaughtExceptionHandler defaultExceptionHandler;
    private final ReportExecutor reportExecutor;
    private final SchedulerStarter schedulerStarter;
    private final boolean supportedAndroidVersion;

    public ErrorReporterImpl(Application context, CoreConfiguration config, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.context = context;
        this.supportedAndroidVersion = z2;
        this.customData = new HashMap();
        CrashReportDataFactory crashReportDataFactory = new CrashReportDataFactory(context, config);
        crashReportDataFactory.collectStartUp();
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.defaultExceptionHandler = defaultUncaughtExceptionHandler;
        Thread.setDefaultUncaughtExceptionHandler(this);
        LastActivityManager lastActivityManager = new LastActivityManager(context);
        ProcessFinisher processFinisher = new ProcessFinisher(context, config, lastActivityManager);
        SchedulerStarter schedulerStarter = new SchedulerStarter(context, config);
        this.schedulerStarter = schedulerStarter;
        ReportExecutor reportExecutor = new ReportExecutor(context, config, crashReportDataFactory, defaultUncaughtExceptionHandler, processFinisher, schedulerStarter, lastActivityManager);
        this.reportExecutor = reportExecutor;
        reportExecutor.setEnabled(z);
        if (z3) {
            new StartupProcessorExecutor(context, config, schedulerStarter).processReports(z);
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread t, Throwable e) {
        Intrinsics.checkNotNullParameter(t, "t");
        Intrinsics.checkNotNullParameter(e, "e");
        if (!this.reportExecutor.isEnabled()) {
            this.reportExecutor.handReportToDefaultExceptionHandler(t, e);
            return;
        }
        try {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            aCRALog.e(str, "ACRA caught a " + e.getClass().getSimpleName() + " for " + this.context.getPackageName(), e);
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(str, "Building report");
            }
            new ReportBuilder().uncaughtExceptionThread(t).exception(e).customData(this.customData).endApplication().build(this.reportExecutor);
        } catch (Exception e2) {
            ACRA.log.e(ACRA.LOG_TAG, "ACRA failed to capture the error - handing off to native error reporter", e2);
            this.reportExecutor.handReportToDefaultExceptionHandler(t, e);
        }
    }

    public void setEnabled(boolean z) {
        if (this.supportedAndroidVersion) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            String str2 = z ? "enabled" : "disabled";
            aCRALog.i(str, "ACRA is " + str2 + " for " + this.context.getPackageName());
            this.reportExecutor.setEnabled(z);
            return;
        }
        ACRA.log.w(ACRA.LOG_TAG, "ACRA requires ICS or greater. ACRA is disabled and will NOT catch crashes or send messages.");
    }

    @Override // org.acra.ErrorReporter
    public void handleException(Throwable th, boolean z) {
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.exception(th).customData(this.customData);
        if (z) {
            reportBuilder.endApplication();
        }
        reportBuilder.build(this.reportExecutor);
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        if (Intrinsics.areEqual("acra.disable", str) || Intrinsics.areEqual("acra.enable", str)) {
            setEnabled(SharedPreferencesFactory.Companion.shouldEnableACRA(sharedPreferences));
        }
    }

    public final void unregister() {
        Thread.setDefaultUncaughtExceptionHandler(this.defaultExceptionHandler);
    }
}
