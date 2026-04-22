package androidx.compose.ui.input.pointer;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PointerEvent.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PointerType {
    public static final Companion Companion = new Companion(null);
    private static final int Unknown = m1700constructorimpl(0);
    private static final int Touch = m1700constructorimpl(1);
    private static final int Mouse = m1700constructorimpl(2);
    private static final int Stylus = m1700constructorimpl(3);
    private static final int Eraser = m1700constructorimpl(4);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m1700constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1701equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1702hashCodeimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1703toStringimpl(int i) {
        if (i == 1) {
            return "Touch";
        }
        if (i == 2) {
            return "Mouse";
        }
        if (i == 3) {
            return "Stylus";
        }
        if (i == 4) {
            return "Eraser";
        }
        return "Unknown";
    }

    /* JADX INFO: compiled from: PointerEvent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getUnknown-T8wyACA, reason: not valid java name */
        public final int m1708getUnknownT8wyACA() {
            return PointerType.Unknown;
        }

        /* JADX INFO: renamed from: getTouch-T8wyACA, reason: not valid java name */
        public final int m1707getTouchT8wyACA() {
            return PointerType.Touch;
        }

        /* JADX INFO: renamed from: getMouse-T8wyACA, reason: not valid java name */
        public final int m1705getMouseT8wyACA() {
            return PointerType.Mouse;
        }

        /* JADX INFO: renamed from: getStylus-T8wyACA, reason: not valid java name */
        public final int m1706getStylusT8wyACA() {
            return PointerType.Stylus;
        }

        /* JADX INFO: renamed from: getEraser-T8wyACA, reason: not valid java name */
        public final int m1704getEraserT8wyACA() {
            return PointerType.Eraser;
        }
    }
}
