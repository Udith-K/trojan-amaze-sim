package androidx.compose.ui.input;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: InputModeManager.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InputMode {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int Touch = m1586constructorimpl(1);
    private static final int Keyboard = m1586constructorimpl(2);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ InputMode m1585boximpl(int i) {
        return new InputMode(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1586constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1587equalsimpl(int i, Object obj) {
        return (obj instanceof InputMode) && i == ((InputMode) obj).m1591unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1588equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1589hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m1587equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m1589hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m1591unboximpl() {
        return this.value;
    }

    private /* synthetic */ InputMode(int i) {
        this.value = i;
    }

    public String toString() {
        return m1590toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1590toStringimpl(int i) {
        return m1588equalsimpl0(i, Touch) ? "Touch" : m1588equalsimpl0(i, Keyboard) ? "Keyboard" : "Error";
    }

    /* JADX INFO: compiled from: InputModeManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getTouch-aOaMEAU, reason: not valid java name */
        public final int m1593getTouchaOaMEAU() {
            return InputMode.Touch;
        }

        /* JADX INFO: renamed from: getKeyboard-aOaMEAU, reason: not valid java name */
        public final int m1592getKeyboardaOaMEAU() {
            return InputMode.Keyboard;
        }
    }
}
