package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Overscroll.kt */
/* JADX INFO: loaded from: classes.dex */
public interface OverscrollEffect {
    /* JADX INFO: renamed from: applyToFling-BMRW4eQ */
    Object mo92applyToFlingBMRW4eQ(long j, Function2 function2, Continuation continuation);

    /* JADX INFO: renamed from: applyToScroll-Rhakbz0 */
    long mo93applyToScrollRhakbz0(long j, int i, Function1 function1);

    Modifier getEffectModifier();

    boolean isInProgress();
}
