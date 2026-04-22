package org.acra.config;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

/* JADX INFO: compiled from: RetryPolicy.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface RetryPolicy {
    boolean shouldRetrySend(List list, List list2);

    /* JADX INFO: compiled from: RetryPolicy.kt */
    public static final class FailedSender {
        private final ReportSenderException exception;
        private final ReportSender sender;

        public FailedSender(ReportSender sender, ReportSenderException exception) {
            Intrinsics.checkNotNullParameter(sender, "sender");
            Intrinsics.checkNotNullParameter(exception, "exception");
            this.sender = sender;
            this.exception = exception;
        }

        public final ReportSenderException getException() {
            return this.exception;
        }

        public final ReportSender getSender() {
            return this.sender;
        }
    }
}
