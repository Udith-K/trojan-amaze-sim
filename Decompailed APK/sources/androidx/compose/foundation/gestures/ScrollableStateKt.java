package androidx.compose.foundation.gestures;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ScrollableState.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ScrollableStateKt {
    public static final ScrollableState ScrollableState(Function1 function1) {
        return new DefaultScrollableState(function1);
    }

    public static final ScrollableState rememberScrollableState(Function1 function1, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-180460798, i, -1, "androidx.compose.foundation.gestures.rememberScrollableState (ScrollableState.kt:161)");
        }
        final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composer, i & 14);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = ScrollableState(new Function1() { // from class: androidx.compose.foundation.gestures.ScrollableStateKt$rememberScrollableState$1$1
                {
                    super(1);
                }

                public final Float invoke(float f) {
                    return (Float) ((Function1) stateRememberUpdatedState.getValue()).invoke(Float.valueOf(f));
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke(((Number) obj).floatValue());
                }
            });
            composer.updateRememberedValue(objRememberedValue);
        }
        ScrollableState scrollableState = (ScrollableState) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return scrollableState;
    }
}
