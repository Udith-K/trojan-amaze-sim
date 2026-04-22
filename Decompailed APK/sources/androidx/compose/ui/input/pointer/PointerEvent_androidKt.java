package androidx.compose.ui.input.pointer;

/* JADX INFO: compiled from: PointerEvent.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PointerEvent_androidKt {
    /* JADX INFO: renamed from: isPrimaryPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m1668isPrimaryPressedaHzCxE(int i) {
        return (i & 33) != 0;
    }

    /* JADX INFO: renamed from: isSecondaryPressed-aHzCx-E, reason: not valid java name */
    public static final boolean m1669isSecondaryPressedaHzCxE(int i) {
        return (i & 66) != 0;
    }

    public static final int EmptyPointerKeyboardModifiers() {
        return PointerKeyboardModifiers.m1695constructorimpl(0);
    }
}
