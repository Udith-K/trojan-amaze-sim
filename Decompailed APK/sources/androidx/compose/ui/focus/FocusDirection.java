package androidx.compose.ui.focus;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FocusDirection.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FocusDirection {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int Next = m1089constructorimpl(1);
    private static final int Previous = m1089constructorimpl(2);
    private static final int Left = m1089constructorimpl(3);
    private static final int Right = m1089constructorimpl(4);
    private static final int Up = m1089constructorimpl(5);
    private static final int Down = m1089constructorimpl(6);
    private static final int Enter = m1089constructorimpl(7);
    private static final int Exit = m1089constructorimpl(8);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FocusDirection m1088boximpl(int i) {
        return new FocusDirection(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1089constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1090equalsimpl(int i, Object obj) {
        return (obj instanceof FocusDirection) && i == ((FocusDirection) obj).m1094unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1091equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1092hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m1090equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m1092hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m1094unboximpl() {
        return this.value;
    }

    private /* synthetic */ FocusDirection(int i) {
        this.value = i;
    }

    public String toString() {
        return m1093toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1093toStringimpl(int i) {
        if (m1091equalsimpl0(i, Next)) {
            return "Next";
        }
        if (m1091equalsimpl0(i, Previous)) {
            return "Previous";
        }
        if (m1091equalsimpl0(i, Left)) {
            return "Left";
        }
        if (m1091equalsimpl0(i, Right)) {
            return "Right";
        }
        if (m1091equalsimpl0(i, Up)) {
            return "Up";
        }
        if (m1091equalsimpl0(i, Down)) {
            return "Down";
        }
        if (m1091equalsimpl0(i, Enter)) {
            return "Enter";
        }
        if (m1091equalsimpl0(i, Exit)) {
            return "Exit";
        }
        return "Invalid FocusDirection";
    }

    /* JADX INFO: compiled from: FocusDirection.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getNext-dhqQ-8s, reason: not valid java name */
        public final int m1099getNextdhqQ8s() {
            return FocusDirection.Next;
        }

        /* JADX INFO: renamed from: getPrevious-dhqQ-8s, reason: not valid java name */
        public final int m1100getPreviousdhqQ8s() {
            return FocusDirection.Previous;
        }

        /* JADX INFO: renamed from: getLeft-dhqQ-8s, reason: not valid java name */
        public final int m1098getLeftdhqQ8s() {
            return FocusDirection.Left;
        }

        /* JADX INFO: renamed from: getRight-dhqQ-8s, reason: not valid java name */
        public final int m1101getRightdhqQ8s() {
            return FocusDirection.Right;
        }

        /* JADX INFO: renamed from: getUp-dhqQ-8s, reason: not valid java name */
        public final int m1102getUpdhqQ8s() {
            return FocusDirection.Up;
        }

        /* JADX INFO: renamed from: getDown-dhqQ-8s, reason: not valid java name */
        public final int m1095getDowndhqQ8s() {
            return FocusDirection.Down;
        }

        /* JADX INFO: renamed from: getEnter-dhqQ-8s, reason: not valid java name */
        public final int m1096getEnterdhqQ8s() {
            return FocusDirection.Enter;
        }

        /* JADX INFO: renamed from: getExit-dhqQ-8s, reason: not valid java name */
        public final int m1097getExitdhqQ8s() {
            return FocusDirection.Exit;
        }
    }
}
