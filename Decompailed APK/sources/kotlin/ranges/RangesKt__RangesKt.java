package kotlin.ranges;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class RangesKt__RangesKt {
    public static ClosedFloatingPointRange rangeTo(float f, float f2) {
        return new ClosedFloatRange(f, f2);
    }

    public static final void checkStepIsPositive(boolean z, Number step) {
        Intrinsics.checkNotNullParameter(step, "step");
        if (z) {
            return;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + step + CoreConstants.DOT);
    }
}
