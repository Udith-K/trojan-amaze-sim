package org.acra.builder;

import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReportBuilder.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ReportBuilder {
    private final Map customData = new HashMap();
    private Throwable exception;
    private boolean isEndApplication;
    private boolean isSendSilently;
    private String message;
    private Thread uncaughtExceptionThread;

    public final String getMessage() {
        return this.message;
    }

    public final Thread getUncaughtExceptionThread() {
        return this.uncaughtExceptionThread;
    }

    public final Throwable getException() {
        return this.exception;
    }

    public final boolean isSendSilently() {
        return this.isSendSilently;
    }

    public final boolean isEndApplication() {
        return this.isEndApplication;
    }

    public final ReportBuilder uncaughtExceptionThread(Thread thread) {
        this.uncaughtExceptionThread = thread;
        return this;
    }

    public final ReportBuilder exception(Throwable th) {
        this.exception = th;
        return this;
    }

    public final ReportBuilder customData(Map customData) {
        Intrinsics.checkNotNullParameter(customData, "customData");
        this.customData.putAll(customData);
        return this;
    }

    public final Map getCustomData() {
        return new HashMap(this.customData);
    }

    public final ReportBuilder endApplication() {
        this.isEndApplication = true;
        return this;
    }

    public final void build(ReportExecutor reportExecutor) {
        Intrinsics.checkNotNullParameter(reportExecutor, "reportExecutor");
        if (this.message == null && this.exception == null) {
            this.message = "Report requested by developer";
        }
        reportExecutor.execute(this);
    }
}
