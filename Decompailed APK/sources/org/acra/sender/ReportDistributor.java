package org.acra.sender;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;
import org.acra.config.DefaultRetryPolicy;
import org.acra.config.RetryPolicy;
import org.acra.data.CrashReportData;
import org.acra.file.CrashReportPersister;
import org.acra.util.IOUtils;
import org.acra.util.InstanceCreator;
import org.json.JSONException;

/* JADX INFO: compiled from: ReportDistributor.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ReportDistributor {
    private final CoreConfiguration config;
    private final Context context;
    private final Bundle extras;
    private final List reportSenders;

    public final boolean distribute(File reportFile) {
        Intrinsics.checkNotNullParameter(reportFile, "reportFile");
        ACRA.log.i(ACRA.LOG_TAG, "Sending report " + reportFile);
        try {
            sendCrashReport(new CrashReportPersister().load(reportFile));
            IOUtils.deleteFile(reportFile);
            return true;
        } catch (IOException e) {
            ACRA.log.e(ACRA.LOG_TAG, "Failed to send crash reports for " + reportFile, e);
            IOUtils.deleteFile(reportFile);
            return false;
        } catch (RuntimeException e2) {
            ACRA.log.e(ACRA.LOG_TAG, "Failed to send crash reports for " + reportFile, e2);
            IOUtils.deleteFile(reportFile);
            return false;
        } catch (ReportSenderException e3) {
            ACRA.log.e(ACRA.LOG_TAG, "Failed to send crash reports for " + reportFile, e3);
            return false;
        } catch (JSONException e4) {
            ACRA.log.e(ACRA.LOG_TAG, "Failed to send crash reports for " + reportFile, e4);
            IOUtils.deleteFile(reportFile);
            return false;
        }
    }

    public ReportDistributor(Context context, CoreConfiguration config, List reportSenders, Bundle extras) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportSenders, "reportSenders");
        Intrinsics.checkNotNullParameter(extras, "extras");
        this.context = context;
        this.config = config;
        this.reportSenders = reportSenders;
        this.extras = extras;
    }

    private final void sendCrashReport(CrashReportData crashReportData) throws ReportSenderException {
        if (isDebuggable() && !this.config.getSendReportsInDevMode()) {
            ACRA.log.i(ACRA.LOG_TAG, "Not sending report because dev mode was detected and sendReportsInDevMode was false");
            return;
        }
        LinkedList linkedList = new LinkedList();
        for (ReportSender reportSender : this.reportSenders) {
            try {
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "Sending report using " + reportSender.getClass().getName());
                }
                reportSender.send(this.context, crashReportData, this.extras);
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "Sent report using " + reportSender.getClass().getName());
                }
            } catch (ReportSenderException e) {
                linkedList.add(new RetryPolicy.FailedSender(reportSender, e));
            }
        }
        if (linkedList.isEmpty()) {
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Report was sent by all senders");
            }
        } else {
            if (((RetryPolicy) InstanceCreator.create(this.config.getRetryPolicyClass(), new Function0() { // from class: org.acra.sender.ReportDistributor$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ReportDistributor.sendCrashReport$lambda$8();
                }
            })).shouldRetrySend(this.reportSenders, linkedList)) {
                throw new ReportSenderException("Policy marked this task as incomplete. ACRA will try to send this report again.", ((RetryPolicy.FailedSender) linkedList.get(0)).getException());
            }
            ACRA.log.w(ACRA.LOG_TAG, "ReportSenders of classes [" + CollectionsKt.joinToString$default(linkedList, null, null, null, 0, null, new Function1() { // from class: org.acra.sender.ReportDistributor$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ReportDistributor.sendCrashReport$lambda$11$lambda$9((RetryPolicy.FailedSender) obj);
                }
            }, 31, null) + "] failed, but Policy marked this task as complete. ACRA will not send this report again.\nSuppressed:\n" + CollectionsKt.joinToString$default(linkedList, "\n", null, null, 0, null, new Function1() { // from class: org.acra.sender.ReportDistributor$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ReportDistributor.sendCrashReport$lambda$11$lambda$10((RetryPolicy.FailedSender) obj);
                }
            }, 30, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetryPolicy sendCrashReport$lambda$8() {
        return new DefaultRetryPolicy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence sendCrashReport$lambda$11$lambda$9(RetryPolicy.FailedSender it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String name = it.getSender().getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return name;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence sendCrashReport$lambda$11$lambda$10(RetryPolicy.FailedSender it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ExceptionsKt.stackTraceToString(it.getException());
    }

    private final boolean isDebuggable() {
        try {
            return (this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 0).flags & 2) > 0;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
