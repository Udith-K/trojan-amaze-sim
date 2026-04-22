package org.acra.config;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultRetryPolicy.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class DefaultRetryPolicy implements RetryPolicy {
    @Override // org.acra.config.RetryPolicy
    public boolean shouldRetrySend(List senders, List failedSenders) {
        Intrinsics.checkNotNullParameter(senders, "senders");
        Intrinsics.checkNotNullParameter(failedSenders, "failedSenders");
        return senders.size() == failedSenders.size() && !senders.isEmpty();
    }
}
