package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ScrollableState.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ScrollableState {
    float dispatchRawDelta(float f);

    boolean getCanScrollBackward();

    boolean getCanScrollForward();

    boolean isScrollInProgress();

    Object scroll(MutatePriority mutatePriority, Function2 function2, Continuation continuation);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableState$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ScrollableState.kt */
    public abstract /* synthetic */ class CC {
        public static /* synthetic */ Object scroll$default(ScrollableState scrollableState, MutatePriority mutatePriority, Function2 function2, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scroll");
            }
            if ((i & 1) != 0) {
                mutatePriority = MutatePriority.Default;
            }
            return scrollableState.scroll(mutatePriority, function2, continuation);
        }

        public static boolean $default$getCanScrollForward(ScrollableState scrollableState) {
            return true;
        }

        public static boolean $default$getCanScrollBackward(ScrollableState scrollableState) {
            return true;
        }
    }
}
