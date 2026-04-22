package androidx.work.impl.constraints.trackers;

import androidx.work.Logger;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConstraintTracker.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintTrackerKt {
    private static final String TAG;

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("ConstraintTracker");
        Intrinsics.checkNotNullExpressionValue(strTagWithPrefix, "tagWithPrefix(\"ConstraintTracker\")");
        TAG = strTagWithPrefix;
    }
}
