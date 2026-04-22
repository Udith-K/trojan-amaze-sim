package androidx.compose.ui.input.pointer;

/* JADX INFO: compiled from: PointerInputEventProcessor.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ProcessResult {
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1712constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: getAnyMovementConsumed-impl, reason: not valid java name */
    public static final boolean m1713getAnyMovementConsumedimpl(int i) {
        return (i & 2) != 0;
    }

    /* JADX INFO: renamed from: getDispatchedToAPointerInputModifier-impl, reason: not valid java name */
    public static final boolean m1714getDispatchedToAPointerInputModifierimpl(int i) {
        return (i & 1) != 0;
    }
}
