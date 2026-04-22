package androidx.work.impl.constraints.trackers;

import androidx.work.Logger;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StorageNotLowTracker.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StorageNotLowTrackerKt {
    private static final String TAG;

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("StorageNotLowTracker");
        Intrinsics.checkNotNullExpressionValue(strTagWithPrefix, "tagWithPrefix(\"StorageNotLowTracker\")");
        TAG = strTagWithPrefix;
    }
}
