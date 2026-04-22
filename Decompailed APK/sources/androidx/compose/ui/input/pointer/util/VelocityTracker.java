package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.util.VelocityTracker1D;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: VelocityTracker.kt */
/* JADX INFO: loaded from: classes.dex */
public final class VelocityTracker {
    private long currentPointerPositionAccumulator;
    private long lastMoveEventTimeStamp;
    private final VelocityTracker1D.Strategy strategy;
    private final VelocityTracker1D xVelocityTracker;
    private final VelocityTracker1D yVelocityTracker;

    public VelocityTracker() {
        VelocityTracker1D.Strategy strategy;
        if (VelocityTrackerKt.getVelocityTrackerStrategyUseImpulse()) {
            strategy = VelocityTracker1D.Strategy.Impulse;
        } else {
            strategy = VelocityTracker1D.Strategy.Lsq2;
        }
        this.strategy = strategy;
        boolean z = false;
        int i = 1;
        DefaultConstructorMarker defaultConstructorMarker = null;
        this.xVelocityTracker = new VelocityTracker1D(z, strategy, i, defaultConstructorMarker);
        this.yVelocityTracker = new VelocityTracker1D(z, strategy, i, defaultConstructorMarker);
        this.currentPointerPositionAccumulator = Offset.Companion.m1171getZeroF1C5BW0();
    }

    /* JADX INFO: renamed from: getCurrentPointerPositionAccumulator-F1C5BW0$ui_release, reason: not valid java name */
    public final long m1722getCurrentPointerPositionAccumulatorF1C5BW0$ui_release() {
        return this.currentPointerPositionAccumulator;
    }

    /* JADX INFO: renamed from: setCurrentPointerPositionAccumulator-k-4lQ0M$ui_release, reason: not valid java name */
    public final void m1723setCurrentPointerPositionAccumulatork4lQ0M$ui_release(long j) {
        this.currentPointerPositionAccumulator = j;
    }

    public final long getLastMoveEventTimeStamp$ui_release() {
        return this.lastMoveEventTimeStamp;
    }

    public final void setLastMoveEventTimeStamp$ui_release(long j) {
        this.lastMoveEventTimeStamp = j;
    }

    /* JADX INFO: renamed from: addPosition-Uv8p0NA, reason: not valid java name */
    public final void m1720addPositionUv8p0NA(long j, long j2) {
        this.xVelocityTracker.addDataPoint(j, Offset.m1159getXimpl(j2));
        this.yVelocityTracker.addDataPoint(j, Offset.m1160getYimpl(j2));
    }

    /* JADX INFO: renamed from: calculateVelocity-AH228Gc, reason: not valid java name */
    public final long m1721calculateVelocityAH228Gc(long j) {
        if (!(Velocity.m2516getXimpl(j) > 0.0f && Velocity.m2517getYimpl(j) > 0.0f)) {
            InlineClassHelperKt.throwIllegalStateException("maximumVelocity should be a positive value. You specified=" + ((Object) Velocity.m2522toStringimpl(j)));
        }
        return VelocityKt.Velocity(this.xVelocityTracker.calculateVelocity(Velocity.m2516getXimpl(j)), this.yVelocityTracker.calculateVelocity(Velocity.m2517getYimpl(j)));
    }

    public final void resetTracking() {
        this.xVelocityTracker.resetTracking();
        this.yVelocityTracker.resetTracking();
        this.lastMoveEventTimeStamp = 0L;
    }
}
