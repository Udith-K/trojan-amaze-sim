package androidx.compose.foundation;

import android.content.Context;
import android.os.Build;
import android.widget.EdgeEffect;
import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: AndroidOverscroll.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidEdgeEffectOverscrollEffect implements OverscrollEffect {
    private long containerSize;
    private final EdgeEffectWrapper edgeEffectWrapper;
    private final Modifier effectModifier;
    private boolean invalidationEnabled;
    private PointerId pointerId;
    private Offset pointerPosition;
    private final MutableState redrawSignal;
    private boolean scrollCycleInProgress;

    public AndroidEdgeEffectOverscrollEffect(Context context, OverscrollConfiguration overscrollConfiguration) {
        Modifier drawGlowOverscrollModifier;
        EdgeEffectWrapper edgeEffectWrapper = new EdgeEffectWrapper(context, ColorKt.m1316toArgb8_81llA(overscrollConfiguration.m141getGlowColor0d7_KjU()));
        this.edgeEffectWrapper = edgeEffectWrapper;
        Unit unit = Unit.INSTANCE;
        this.redrawSignal = SnapshotStateKt.mutableStateOf(unit, SnapshotStateKt.neverEqualPolicy());
        this.invalidationEnabled = true;
        this.containerSize = Size.Companion.m1202getZeroNHjbRc();
        Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(Modifier.Companion, unit, new AndroidEdgeEffectOverscrollEffect$effectModifier$1(this, null));
        if (Build.VERSION.SDK_INT >= 31) {
            drawGlowOverscrollModifier = new DrawStretchOverscrollModifier(this, edgeEffectWrapper, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1() { // from class: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$special$$inlined$debugInspectorInfo$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                    invoke((InspectorInfo) null);
                    return Unit.INSTANCE;
                }

                public final void invoke(InspectorInfo inspectorInfo) {
                    throw null;
                }
            } : InspectableValueKt.getNoInspectorInfo());
        } else {
            drawGlowOverscrollModifier = new DrawGlowOverscrollModifier(this, edgeEffectWrapper, overscrollConfiguration, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1() { // from class: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$special$$inlined$debugInspectorInfo$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                    invoke((InspectorInfo) null);
                    return Unit.INSTANCE;
                }

                public final void invoke(InspectorInfo inspectorInfo) {
                    throw null;
                }
            } : InspectableValueKt.getNoInspectorInfo());
        }
        this.effectModifier = modifierPointerInput.then(drawGlowOverscrollModifier);
    }

    public final MutableState getRedrawSignal$foundation_release() {
        return this.redrawSignal;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x011b A[ADDED_TO_REGION] */
    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToScroll-Rhakbz0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long mo93applyToScrollRhakbz0(long r11, int r13, kotlin.jvm.functions.Function1 r14) {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect.mo93applyToScrollRhakbz0(long, int, kotlin.jvm.functions.Function1):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToFling-BMRW4eQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object mo92applyToFlingBMRW4eQ(long r11, kotlin.jvm.functions.Function2 r13, kotlin.coroutines.Continuation r14) {
        /*
            Method dump skipped, instruction units count: 442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect.mo92applyToFlingBMRW4eQ(long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public boolean isInProgress() {
        EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
        EdgeEffect edgeEffect = edgeEffectWrapper.topEffect;
        if (edgeEffect != null && EdgeEffectCompat.INSTANCE.getDistanceCompat(edgeEffect) != 0.0f) {
            return true;
        }
        EdgeEffect edgeEffect2 = edgeEffectWrapper.bottomEffect;
        if (edgeEffect2 != null && EdgeEffectCompat.INSTANCE.getDistanceCompat(edgeEffect2) != 0.0f) {
            return true;
        }
        EdgeEffect edgeEffect3 = edgeEffectWrapper.leftEffect;
        if (edgeEffect3 != null && EdgeEffectCompat.INSTANCE.getDistanceCompat(edgeEffect3) != 0.0f) {
            return true;
        }
        EdgeEffect edgeEffect4 = edgeEffectWrapper.rightEffect;
        return (edgeEffect4 == null || EdgeEffectCompat.INSTANCE.getDistanceCompat(edgeEffect4) == 0.0f) ? false : true;
    }

    private final boolean stopOverscrollAnimation() {
        boolean z;
        if (this.edgeEffectWrapper.isLeftStretched()) {
            m88pullLeftk4lQ0M(Offset.Companion.m1171getZeroF1C5BW0());
            z = true;
        } else {
            z = false;
        }
        if (this.edgeEffectWrapper.isRightStretched()) {
            m89pullRightk4lQ0M(Offset.Companion.m1171getZeroF1C5BW0());
            z = true;
        }
        if (this.edgeEffectWrapper.isTopStretched()) {
            m90pullTopk4lQ0M(Offset.Companion.m1171getZeroF1C5BW0());
            z = true;
        }
        if (!this.edgeEffectWrapper.isBottomStretched()) {
            return z;
        }
        m87pullBottomk4lQ0M(Offset.Companion.m1171getZeroF1C5BW0());
        return true;
    }

    /* JADX INFO: renamed from: updateSize-uvyYCjk$foundation_release, reason: not valid java name */
    public final void m95updateSizeuvyYCjk$foundation_release(long j) {
        boolean zM1193equalsimpl0 = Size.m1193equalsimpl0(this.containerSize, Size.Companion.m1202getZeroNHjbRc());
        boolean zM1193equalsimpl02 = Size.m1193equalsimpl0(j, this.containerSize);
        this.containerSize = j;
        if (!zM1193equalsimpl02) {
            this.edgeEffectWrapper.m132setSizeozmzZPI(IntSizeKt.IntSize(MathKt.roundToInt(Size.m1196getWidthimpl(j)), MathKt.roundToInt(Size.m1194getHeightimpl(j))));
        }
        if (zM1193equalsimpl0 || zM1193equalsimpl02) {
            return;
        }
        invalidateOverscroll$foundation_release();
        animateToRelease();
    }

    /* JADX INFO: renamed from: displacement-F1C5BW0$foundation_release, reason: not valid java name */
    public final long m94displacementF1C5BW0$foundation_release() {
        Offset offset = this.pointerPosition;
        long jM1168unboximpl = offset != null ? offset.m1168unboximpl() : SizeKt.m1203getCenteruvyYCjk(this.containerSize);
        return OffsetKt.Offset(Offset.m1159getXimpl(jM1168unboximpl) / Size.m1196getWidthimpl(this.containerSize), Offset.m1160getYimpl(jM1168unboximpl) / Size.m1194getHeightimpl(this.containerSize));
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public Modifier getEffectModifier() {
        return this.effectModifier;
    }

    public final void invalidateOverscroll$foundation_release() {
        if (this.invalidationEnabled) {
            this.redrawSignal.setValue(Unit.INSTANCE);
        }
    }

    private final void animateToRelease() {
        boolean zIsFinished;
        EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
        EdgeEffect edgeEffect = edgeEffectWrapper.topEffect;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = edgeEffect.isFinished();
        } else {
            zIsFinished = false;
        }
        EdgeEffect edgeEffect2 = edgeEffectWrapper.bottomEffect;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished = edgeEffect2.isFinished() || zIsFinished;
        }
        EdgeEffect edgeEffect3 = edgeEffectWrapper.leftEffect;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished = edgeEffect3.isFinished() || zIsFinished;
        }
        EdgeEffect edgeEffect4 = edgeEffectWrapper.rightEffect;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished = edgeEffect4.isFinished() || zIsFinished;
        }
        if (zIsFinished) {
            invalidateOverscroll$foundation_release();
        }
    }

    /* JADX INFO: renamed from: releaseOppositeOverscroll-k-4lQ0M, reason: not valid java name */
    private final boolean m91releaseOppositeOverscrollk4lQ0M(long j) {
        boolean z;
        boolean z2 = true;
        if (!this.edgeEffectWrapper.isLeftAnimating() || Offset.m1159getXimpl(j) >= 0.0f) {
            z = false;
        } else {
            EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateLeftEffect(), Offset.m1159getXimpl(j));
            z = !this.edgeEffectWrapper.isLeftAnimating();
        }
        if (this.edgeEffectWrapper.isRightAnimating() && Offset.m1159getXimpl(j) > 0.0f) {
            EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateRightEffect(), Offset.m1159getXimpl(j));
            z = z || !this.edgeEffectWrapper.isRightAnimating();
        }
        if (this.edgeEffectWrapper.isTopAnimating() && Offset.m1160getYimpl(j) < 0.0f) {
            EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateTopEffect(), Offset.m1160getYimpl(j));
            z = z || !this.edgeEffectWrapper.isTopAnimating();
        }
        if (!this.edgeEffectWrapper.isBottomAnimating() || Offset.m1160getYimpl(j) <= 0.0f) {
            return z;
        }
        EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateBottomEffect(), Offset.m1160getYimpl(j));
        if (!z && this.edgeEffectWrapper.isBottomAnimating()) {
            z2 = false;
        }
        return z2;
    }

    /* JADX INFO: renamed from: pullTop-k-4lQ0M, reason: not valid java name */
    private final float m90pullTopk4lQ0M(long j) {
        float fM1159getXimpl = Offset.m1159getXimpl(m94displacementF1C5BW0$foundation_release());
        float fM1160getYimpl = Offset.m1160getYimpl(j) / Size.m1194getHeightimpl(this.containerSize);
        EdgeEffect orCreateTopEffect = this.edgeEffectWrapper.getOrCreateTopEffect();
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(orCreateTopEffect) == 0.0f ? edgeEffectCompat.onPullDistanceCompat(orCreateTopEffect, fM1160getYimpl, fM1159getXimpl) * Size.m1194getHeightimpl(this.containerSize) : Offset.m1160getYimpl(j);
    }

    /* JADX INFO: renamed from: pullBottom-k-4lQ0M, reason: not valid java name */
    private final float m87pullBottomk4lQ0M(long j) {
        float fM1159getXimpl = Offset.m1159getXimpl(m94displacementF1C5BW0$foundation_release());
        float fM1160getYimpl = Offset.m1160getYimpl(j) / Size.m1194getHeightimpl(this.containerSize);
        EdgeEffect orCreateBottomEffect = this.edgeEffectWrapper.getOrCreateBottomEffect();
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(orCreateBottomEffect) == 0.0f ? (-edgeEffectCompat.onPullDistanceCompat(orCreateBottomEffect, -fM1160getYimpl, 1 - fM1159getXimpl)) * Size.m1194getHeightimpl(this.containerSize) : Offset.m1160getYimpl(j);
    }

    /* JADX INFO: renamed from: pullLeft-k-4lQ0M, reason: not valid java name */
    private final float m88pullLeftk4lQ0M(long j) {
        float fM1160getYimpl = Offset.m1160getYimpl(m94displacementF1C5BW0$foundation_release());
        float fM1159getXimpl = Offset.m1159getXimpl(j) / Size.m1196getWidthimpl(this.containerSize);
        EdgeEffect orCreateLeftEffect = this.edgeEffectWrapper.getOrCreateLeftEffect();
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(orCreateLeftEffect) == 0.0f ? edgeEffectCompat.onPullDistanceCompat(orCreateLeftEffect, fM1159getXimpl, 1 - fM1160getYimpl) * Size.m1196getWidthimpl(this.containerSize) : Offset.m1159getXimpl(j);
    }

    /* JADX INFO: renamed from: pullRight-k-4lQ0M, reason: not valid java name */
    private final float m89pullRightk4lQ0M(long j) {
        float fM1160getYimpl = Offset.m1160getYimpl(m94displacementF1C5BW0$foundation_release());
        float fM1159getXimpl = Offset.m1159getXimpl(j) / Size.m1196getWidthimpl(this.containerSize);
        EdgeEffect orCreateRightEffect = this.edgeEffectWrapper.getOrCreateRightEffect();
        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
        return edgeEffectCompat.getDistanceCompat(orCreateRightEffect) == 0.0f ? (-edgeEffectCompat.onPullDistanceCompat(orCreateRightEffect, -fM1159getXimpl, fM1160getYimpl)) * Size.m1196getWidthimpl(this.containerSize) : Offset.m1159getXimpl(j);
    }
}
