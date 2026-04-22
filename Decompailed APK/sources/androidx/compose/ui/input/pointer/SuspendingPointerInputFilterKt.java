package androidx.compose.ui.input.pointer;

import androidx.compose.ui.Modifier;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SuspendingPointerInputFilterKt {
    private static final PointerEvent EmptyPointerEvent = new PointerEvent(CollectionsKt.emptyList());

    public static final Modifier pointerInput(Modifier modifier, Object obj, Function2 function2) {
        return modifier.then(new SuspendPointerInputElement(obj, null, null, function2, 6, null));
    }

    public static final Modifier pointerInput(Modifier modifier, Object obj, Object obj2, Function2 function2) {
        return modifier.then(new SuspendPointerInputElement(obj, obj2, null, function2, 4, null));
    }

    public static final SuspendingPointerInputModifierNode SuspendingPointerInputModifierNode(Function2 function2) {
        return new SuspendingPointerInputModifierNodeImpl(null, null, null, function2);
    }
}
