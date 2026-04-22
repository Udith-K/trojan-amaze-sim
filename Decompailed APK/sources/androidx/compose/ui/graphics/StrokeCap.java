package androidx.compose.ui.graphics;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: StrokeCap.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StrokeCap {
    public static final Companion Companion = new Companion(null);
    private static final int Butt = m1411constructorimpl(0);
    private static final int Round = m1411constructorimpl(1);
    private static final int Square = m1411constructorimpl(2);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1411constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1412equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1413hashCodeimpl(int i) {
        return i;
    }

    /* JADX INFO: compiled from: StrokeCap.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getButt-KaPHkGw, reason: not valid java name */
        public final int m1415getButtKaPHkGw() {
            return StrokeCap.Butt;
        }

        /* JADX INFO: renamed from: getRound-KaPHkGw, reason: not valid java name */
        public final int m1416getRoundKaPHkGw() {
            return StrokeCap.Round;
        }

        /* JADX INFO: renamed from: getSquare-KaPHkGw, reason: not valid java name */
        public final int m1417getSquareKaPHkGw() {
            return StrokeCap.Square;
        }
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1414toStringimpl(int i) {
        return m1412equalsimpl0(i, Butt) ? "Butt" : m1412equalsimpl0(i, Round) ? "Round" : m1412equalsimpl0(i, Square) ? "Square" : "Unknown";
    }
}
