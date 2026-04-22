package androidx.compose.ui.input.pointer;

import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: PointerEvent.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PointerKeyboardModifiers {
    private final int packedValue;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ PointerKeyboardModifiers m1694boximpl(int i) {
        return new PointerKeyboardModifiers(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1695constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1696equalsimpl(int i, Object obj) {
        return (obj instanceof PointerKeyboardModifiers) && i == ((PointerKeyboardModifiers) obj).m1699unboximpl();
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1697hashCodeimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1698toStringimpl(int i) {
        return "PointerKeyboardModifiers(packedValue=" + i + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public boolean equals(Object obj) {
        return m1696equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m1697hashCodeimpl(this.packedValue);
    }

    public String toString() {
        return m1698toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m1699unboximpl() {
        return this.packedValue;
    }

    private /* synthetic */ PointerKeyboardModifiers(int i) {
        this.packedValue = i;
    }
}
