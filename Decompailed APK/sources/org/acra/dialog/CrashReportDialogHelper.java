package org.acra.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import java.io.File;
import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.file.BulkReportDeleter;
import org.acra.file.CrashReportPersister;
import org.acra.interaction.DialogInteraction;
import org.acra.scheduler.SchedulerStarter;
import org.json.JSONException;

/* JADX INFO: compiled from: CrashReportDialogHelper.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CrashReportDialogHelper {
    private final CoreConfiguration config;
    private final Context context;
    private final Lazy reportData$delegate;
    private final File reportFile;

    public CrashReportDialogHelper(Context context, Intent intent) {
        Object serializableExtra;
        Object serializableExtra2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        this.context = context;
        int i = Build.VERSION.SDK_INT;
        if (i >= 33) {
            serializableExtra = intent.getSerializableExtra(DialogInteraction.EXTRA_REPORT_CONFIG, CoreConfiguration.class);
        } else {
            Object serializableExtra3 = intent.getSerializableExtra(DialogInteraction.EXTRA_REPORT_CONFIG);
            serializableExtra = (CoreConfiguration) (serializableExtra3 instanceof CoreConfiguration ? serializableExtra3 : null);
        }
        CoreConfiguration coreConfiguration = (CoreConfiguration) serializableExtra;
        if (i >= 33) {
            serializableExtra2 = intent.getSerializableExtra(DialogInteraction.EXTRA_REPORT_FILE, File.class);
        } else {
            Object serializableExtra4 = intent.getSerializableExtra(DialogInteraction.EXTRA_REPORT_FILE);
            serializableExtra2 = (File) (serializableExtra4 instanceof File ? serializableExtra4 : null);
        }
        File file = (File) serializableExtra2;
        if (coreConfiguration != null && file != null) {
            this.config = coreConfiguration;
            this.reportFile = file;
            this.reportData$delegate = LazyKt.lazy(new Function0() { // from class: org.acra.dialog.CrashReportDialogHelper$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CrashReportDialogHelper.reportData_delegate$lambda$1(this.f$0);
                }
            });
            return;
        }
        ACRA.log.e(ACRA.LOG_TAG, "Illegal or incomplete call of " + CrashReportDialogHelper.class.getSimpleName());
        throw new IllegalArgumentException();
    }

    public final CoreConfiguration getConfig() {
        return this.config;
    }

    public final CrashReportData getReportData() {
        return (CrashReportData) this.reportData$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CrashReportData reportData_delegate$lambda$1(CrashReportDialogHelper crashReportDialogHelper) throws IOException {
        try {
            return new CrashReportPersister().load(crashReportDialogHelper.reportFile);
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void cancelReports$lambda$2(CrashReportDialogHelper crashReportDialogHelper) {
        new BulkReportDeleter(crashReportDialogHelper.context).deleteReports(false, 0);
    }

    public final void cancelReports() {
        new Thread(new Runnable() { // from class: org.acra.dialog.CrashReportDialogHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CrashReportDialogHelper.cancelReports$lambda$2(this.f$0);
            }
        }).start();
    }

    public final void sendCrash(final String str, final String str2) {
        new Thread(new Runnable() { // from class: org.acra.dialog.CrashReportDialogHelper$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                CrashReportDialogHelper.sendCrash$lambda$6(this.f$0, str, str2);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendCrash$lambda$6(CrashReportDialogHelper crashReportDialogHelper, String str, String str2) {
        try {
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Add user comment to " + crashReportDialogHelper.reportFile);
            }
            CrashReportData reportData = crashReportDialogHelper.getReportData();
            ReportField reportField = ReportField.USER_COMMENT;
            if (str == null) {
                str = "";
            }
            reportData.put(reportField, str);
            ReportField reportField2 = ReportField.USER_EMAIL;
            if (str2 == null) {
                str2 = "";
            }
            reportData.put(reportField2, str2);
            new CrashReportPersister().store(reportData, crashReportDialogHelper.reportFile);
        } catch (IOException e) {
            ACRA.log.w(ACRA.LOG_TAG, "User comment not added: ", e);
        } catch (JSONException e2) {
            ACRA.log.w(ACRA.LOG_TAG, "User comment not added: ", e2);
        }
        new SchedulerStarter(crashReportDialogHelper.context, crashReportDialogHelper.config).scheduleReports(crashReportDialogHelper.reportFile, false);
    }
}
