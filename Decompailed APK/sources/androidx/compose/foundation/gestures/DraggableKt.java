package androidx.compose.foundation.gestures;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: Draggable.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DraggableKt {
    private static final Function3 NoOpOnDragStarted = new DraggableKt$NoOpOnDragStarted$1(null);
    private static final Function3 NoOpOnDragStopped = new DraggableKt$NoOpOnDragStopped$1(null);

    public static final DraggableState DraggableState(Function1 function1) {
        return new DefaultDraggableState(function1);
    }

    public static final DraggableState rememberDraggableState(Function1 function1, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-183245213, i, -1, "androidx.compose.foundation.gestures.rememberDraggableState (Draggable.kt:135)");
        }
        final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composer, i & 14);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = DraggableState(new Function1() { // from class: androidx.compose.foundation.gestures.DraggableKt$rememberDraggableState$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Number) obj).floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(float f) {
                    ((Function1) stateRememberUpdatedState.getValue()).invoke(Float.valueOf(f));
                }
            });
            composer.updateRememberedValue(objRememberedValue);
        }
        DraggableState draggableState = (DraggableState) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return draggableState;
    }

    public static final Modifier draggable(Modifier modifier, DraggableState draggableState, Orientation orientation, boolean z, MutableInteractionSource mutableInteractionSource, boolean z2, Function3 function3, Function3 function32, boolean z3) {
        return modifier.then(new DraggableElement(draggableState, orientation, z, mutableInteractionSource, z2, function3, function32, z3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toFloat-3MmeM6k, reason: not valid java name */
    public static final float m194toFloat3MmeM6k(long j, Orientation orientation) {
        return orientation == Orientation.Vertical ? Offset.m1160getYimpl(j) : Offset.m1159getXimpl(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toFloat-sF-c-tU, reason: not valid java name */
    public static final float m195toFloatsFctU(long j, Orientation orientation) {
        return orientation == Orientation.Vertical ? Velocity.m2517getYimpl(j) : Velocity.m2516getXimpl(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toValidVelocity-TH1AsA0, reason: not valid java name */
    public static final long m196toValidVelocityTH1AsA0(long j) {
        return VelocityKt.Velocity(Float.isNaN(Velocity.m2516getXimpl(j)) ? 0.0f : Velocity.m2516getXimpl(j), Float.isNaN(Velocity.m2517getYimpl(j)) ? 0.0f : Velocity.m2517getYimpl(j));
    }
}
