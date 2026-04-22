package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Overscroll.kt */
/* JADX INFO: loaded from: classes.dex */
public final class NoOpOverscrollEffect implements OverscrollEffect {
    public static final NoOpOverscrollEffect INSTANCE = new NoOpOverscrollEffect();

    @Override // androidx.compose.foundation.OverscrollEffect
    public boolean isInProgress() {
        return false;
    }

    private NoOpOverscrollEffect() {
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToScroll-Rhakbz0 */
    public long mo93applyToScrollRhakbz0(long j, int i, Function1 function1) {
        return ((Offset) function1.invoke(Offset.m1150boximpl(j))).m1168unboximpl();
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToFling-BMRW4eQ */
    public Object mo92applyToFlingBMRW4eQ(long j, Function2 function2, Continuation continuation) {
        Object objInvoke = function2.invoke(Velocity.m2510boximpl(j), continuation);
        return objInvoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvoke : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public Modifier getEffectModifier() {
        return Modifier.Companion;
    }
}
