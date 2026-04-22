package androidx.compose.material.ripple;

import android.R;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: RippleHostView.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class RippleHostView extends View {
    private Boolean bounded;
    private Long lastRippleStateChangeTimeMillis;
    private Function0 onInvalidateRipple;
    private Runnable resetRippleRunnable;
    private UnprojectedRipple ripple;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final int[] PressedState = {R.attr.state_pressed, R.attr.state_enabled};
    private static final int[] RestingState = new int[0];

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public void refreshDrawableState() {
    }

    public RippleHostView(Context context) {
        super(context);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    private final void createRipple(boolean z) {
        UnprojectedRipple unprojectedRipple = new UnprojectedRipple(z);
        setBackground(unprojectedRipple);
        this.ripple = unprojectedRipple;
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Function0 function0 = this.onInvalidateRipple;
        if (function0 != null) {
            function0.invoke();
        }
    }

    /* JADX INFO: renamed from: addRipple-KOepWvA, reason: not valid java name */
    public final void m584addRippleKOepWvA(PressInteraction.Press press, boolean z, long j, int i, long j2, float f, Function0 function0) {
        if (this.ripple == null || !Intrinsics.areEqual(Boolean.valueOf(z), this.bounded)) {
            createRipple(z);
            this.bounded = Boolean.valueOf(z);
        }
        UnprojectedRipple unprojectedRipple = this.ripple;
        Intrinsics.checkNotNull(unprojectedRipple);
        this.onInvalidateRipple = function0;
        unprojectedRipple.trySetRadius(i);
        m585setRippleProperties07v42R4(j, j2, f);
        if (z) {
            unprojectedRipple.setHotspot(Offset.m1159getXimpl(press.m244getPressPositionF1C5BW0()), Offset.m1160getYimpl(press.m244getPressPositionF1C5BW0()));
        } else {
            unprojectedRipple.setHotspot(unprojectedRipple.getBounds().centerX(), unprojectedRipple.getBounds().centerY());
        }
        setRippleState(true);
    }

    public final void removeRipple() {
        setRippleState(false);
    }

    /* JADX INFO: renamed from: setRippleProperties-07v42R4, reason: not valid java name */
    public final void m585setRippleProperties07v42R4(long j, long j2, float f) {
        UnprojectedRipple unprojectedRipple = this.ripple;
        if (unprojectedRipple == null) {
            return;
        }
        unprojectedRipple.m596setColorDxMtmZc(j2, f);
        Rect rect = new Rect(0, 0, MathKt.roundToInt(Size.m1196getWidthimpl(j)), MathKt.roundToInt(Size.m1194getHeightimpl(j)));
        setLeft(rect.left);
        setTop(rect.top);
        setRight(rect.right);
        setBottom(rect.bottom);
        unprojectedRipple.setBounds(rect);
    }

    public final void disposeRipple() {
        this.onInvalidateRipple = null;
        Runnable runnable = this.resetRippleRunnable;
        if (runnable != null) {
            removeCallbacks(runnable);
            Runnable runnable2 = this.resetRippleRunnable;
            Intrinsics.checkNotNull(runnable2);
            runnable2.run();
        } else {
            UnprojectedRipple unprojectedRipple = this.ripple;
            if (unprojectedRipple != null) {
                unprojectedRipple.setState(RestingState);
            }
        }
        UnprojectedRipple unprojectedRipple2 = this.ripple;
        if (unprojectedRipple2 == null) {
            return;
        }
        unprojectedRipple2.setVisible(false, false);
        unscheduleDrawable(unprojectedRipple2);
    }

    private final void setRippleState(boolean z) {
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        Runnable runnable = this.resetRippleRunnable;
        if (runnable != null) {
            removeCallbacks(runnable);
            runnable.run();
        }
        Long l = this.lastRippleStateChangeTimeMillis;
        long jLongValue = jCurrentAnimationTimeMillis - (l != null ? l.longValue() : 0L);
        if (!z && jLongValue < 5) {
            Runnable runnable2 = new Runnable() { // from class: androidx.compose.material.ripple.RippleHostView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RippleHostView.setRippleState$lambda$2(this.f$0);
                }
            };
            this.resetRippleRunnable = runnable2;
            postDelayed(runnable2, 50L);
        } else {
            int[] iArr = z ? PressedState : RestingState;
            UnprojectedRipple unprojectedRipple = this.ripple;
            if (unprojectedRipple != null) {
                unprojectedRipple.setState(iArr);
            }
        }
        this.lastRippleStateChangeTimeMillis = Long.valueOf(jCurrentAnimationTimeMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRippleState$lambda$2(RippleHostView rippleHostView) {
        UnprojectedRipple unprojectedRipple = rippleHostView.ripple;
        if (unprojectedRipple != null) {
            unprojectedRipple.setState(RestingState);
        }
        rippleHostView.resetRippleRunnable = null;
    }

    /* JADX INFO: compiled from: RippleHostView.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
