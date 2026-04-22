package androidx.work.impl.utils;

import androidx.core.util.Consumer;
import androidx.work.Logger;
import androidx.work.WorkerExceptionInfo;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorkerExceptionUtils.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class WorkerExceptionUtilsKt {
    public static final void safeAccept(Consumer consumer, WorkerExceptionInfo info2, String tag) {
        Intrinsics.checkNotNullParameter(consumer, "<this>");
        Intrinsics.checkNotNullParameter(info2, "info");
        Intrinsics.checkNotNullParameter(tag, "tag");
        try {
            consumer.accept(info2);
        } catch (Throwable th) {
            Logger.get().error(tag, "Exception handler threw an exception", th);
        }
    }
}
