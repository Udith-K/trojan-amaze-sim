package androidx.work.impl.constraints.trackers;

import androidx.work.Logger;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BroadcastReceiverConstraintTracker.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BroadcastReceiverConstraintTrackerKt {
    private static final String TAG;

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("BrdcstRcvrCnstrntTrckr");
        Intrinsics.checkNotNullExpressionValue(strTagWithPrefix, "tagWithPrefix(\"BrdcstRcvrCnstrntTrckr\")");
        TAG = strTagWithPrefix;
    }
}
