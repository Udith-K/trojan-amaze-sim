package mu.internal;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ErrorMessageProducer.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ErrorMessageProducer {
    public static final ErrorMessageProducer INSTANCE = new ErrorMessageProducer();

    private ErrorMessageProducer() {
    }

    public final String getErrorLog(Exception e) throws Exception {
        Intrinsics.checkNotNullParameter(e, "e");
        if (System.getProperties().containsKey("kotlin-logging.throwOnMessageError")) {
            throw e;
        }
        return "Log message invocation failed: " + e;
    }
}
