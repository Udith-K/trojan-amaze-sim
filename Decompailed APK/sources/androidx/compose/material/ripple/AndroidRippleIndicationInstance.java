package androidx.compose.material.ripple;

import android.view.ViewGroup;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Ripple.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidRippleIndicationInstance extends RippleIndicationInstance implements RememberObserver, RippleHostKey {
    private final boolean bounded;
    private final State color;
    private final MutableState invalidateTick$delegate;
    private final Function0 onInvalidateRipple;
    private final float radius;
    private final State rippleAlpha;
    private RippleContainer rippleContainer;
    private final MutableState rippleHostView$delegate;
    private int rippleRadius;
    private long rippleSize;
    private final ViewGroup view;

    public /* synthetic */ AndroidRippleIndicationInstance(boolean z, float f, State state, State state2, ViewGroup viewGroup, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, f, state, state2, viewGroup);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
    }

    private AndroidRippleIndicationInstance(boolean z, float f, State state, State state2, ViewGroup viewGroup) {
        super(z, state2);
        this.bounded = z;
        this.radius = f;
        this.color = state;
        this.rippleAlpha = state2;
        this.view = viewGroup;
        this.rippleHostView$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.invalidateTick$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
        this.rippleSize = Size.Companion.m1202getZeroNHjbRc();
        this.rippleRadius = -1;
        this.onInvalidateRipple = new Function0() { // from class: androidx.compose.material.ripple.AndroidRippleIndicationInstance$onInvalidateRipple$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m575invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m575invoke() {
                this.this$0.setInvalidateTick(!r0.getInvalidateTick());
            }
        };
    }

    private final RippleHostView getRippleHostView() {
        return (RippleHostView) this.rippleHostView$delegate.getValue();
    }

    private final void setRippleHostView(RippleHostView rippleHostView) {
        this.rippleHostView$delegate.setValue(rippleHostView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getInvalidateTick() {
        return ((Boolean) this.invalidateTick$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setInvalidateTick(boolean z) {
        this.invalidateTick$delegate.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.foundation.IndicationInstance
    public void drawIndication(ContentDrawScope contentDrawScope) throws Throwable {
        int iMo204roundToPx0680j_4;
        this.rippleSize = contentDrawScope.mo1483getSizeNHjbRc();
        if (Float.isNaN(this.radius)) {
            iMo204roundToPx0680j_4 = MathKt.roundToInt(RippleAnimationKt.m581getRippleEndRadiuscSwnlzA(contentDrawScope, this.bounded, contentDrawScope.mo1483getSizeNHjbRc()));
        } else {
            iMo204roundToPx0680j_4 = contentDrawScope.mo204roundToPx0680j_4(this.radius);
        }
        this.rippleRadius = iMo204roundToPx0680j_4;
        long jM1304unboximpl = ((Color) this.color.getValue()).m1304unboximpl();
        float pressedAlpha = ((RippleAlpha) this.rippleAlpha.getValue()).getPressedAlpha();
        contentDrawScope.drawContent();
        m586drawStateLayerH2RKhps(contentDrawScope, this.radius, jM1304unboximpl);
        Canvas canvas = contentDrawScope.getDrawContext().getCanvas();
        getInvalidateTick();
        RippleHostView rippleHostView = getRippleHostView();
        if (rippleHostView != null) {
            rippleHostView.m585setRippleProperties07v42R4(contentDrawScope.mo1483getSizeNHjbRc(), jM1304unboximpl, pressedAlpha);
            rippleHostView.draw(AndroidCanvas_androidKt.getNativeCanvas(canvas));
        }
    }

    @Override // androidx.compose.material.ripple.RippleIndicationInstance
    public void addRipple(PressInteraction.Press press, CoroutineScope coroutineScope) {
        RippleHostView rippleHostView = getOrCreateRippleContainer().getRippleHostView(this);
        rippleHostView.m584addRippleKOepWvA(press, this.bounded, this.rippleSize, this.rippleRadius, ((Color) this.color.getValue()).m1304unboximpl(), ((RippleAlpha) this.rippleAlpha.getValue()).getPressedAlpha(), this.onInvalidateRipple);
        setRippleHostView(rippleHostView);
    }

    @Override // androidx.compose.material.ripple.RippleIndicationInstance
    public void removeRipple(PressInteraction.Press press) {
        RippleHostView rippleHostView = getRippleHostView();
        if (rippleHostView != null) {
            rippleHostView.removeRipple();
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        dispose();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        dispose();
    }

    private final void dispose() {
        RippleContainer rippleContainer = this.rippleContainer;
        if (rippleContainer != null) {
            rippleContainer.disposeRippleIfNeeded(this);
        }
    }

    @Override // androidx.compose.material.ripple.RippleHostKey
    public void onResetRippleHostView() {
        setRippleHostView(null);
    }

    private final RippleContainer getOrCreateRippleContainer() {
        RippleContainer rippleContainer = this.rippleContainer;
        if (rippleContainer != null) {
            Intrinsics.checkNotNull(rippleContainer);
            return rippleContainer;
        }
        RippleContainer rippleContainerCreateAndAttachRippleContainerIfNeeded = Ripple_androidKt.createAndAttachRippleContainerIfNeeded(this.view);
        this.rippleContainer = rippleContainerCreateAndAttachRippleContainerIfNeeded;
        Intrinsics.checkNotNull(rippleContainerCreateAndAttachRippleContainerIfNeeded);
        return rippleContainerCreateAndAttachRippleContainerIfNeeded;
    }
}
