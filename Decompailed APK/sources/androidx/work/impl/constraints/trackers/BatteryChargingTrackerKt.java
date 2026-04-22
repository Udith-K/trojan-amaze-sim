package androidx.work.impl.constraints.trackers;

import androidx.work.Logger;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BatteryChargingTracker.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BatteryChargingTrackerKt {
    private static final String TAG;

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("BatteryChrgTracker");
        Intrinsics.checkNotNullExpressionValue(strTagWithPrefix, "tagWithPrefix(\"BatteryChrgTracker\")");
        TAG = strTagWithPrefix;
    }
}
