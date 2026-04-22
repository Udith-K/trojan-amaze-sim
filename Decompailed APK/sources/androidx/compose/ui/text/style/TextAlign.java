package androidx.compose.ui.text.style;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TextAlign.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextAlign {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int Left = m2334constructorimpl(1);
    private static final int Right = m2334constructorimpl(2);
    private static final int Center = m2334constructorimpl(3);
    private static final int Justify = m2334constructorimpl(4);
    private static final int Start = m2334constructorimpl(5);
    private static final int End = m2334constructorimpl(6);
    private static final int Unspecified = m2334constructorimpl(Integer.MIN_VALUE);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TextAlign m2333boximpl(int i) {
        return new TextAlign(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2334constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2335equalsimpl(int i, Object obj) {
        return (obj instanceof TextAlign) && i == ((TextAlign) obj).m2339unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2336equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2337hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m2335equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2337hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2339unboximpl() {
        return this.value;
    }

    private /* synthetic */ TextAlign(int i) {
        this.value = i;
    }

    public String toString() {
        return m2338toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2338toStringimpl(int i) {
        return m2336equalsimpl0(i, Left) ? "Left" : m2336equalsimpl0(i, Right) ? "Right" : m2336equalsimpl0(i, Center) ? "Center" : m2336equalsimpl0(i, Justify) ? "Justify" : m2336equalsimpl0(i, Start) ? "Start" : m2336equalsimpl0(i, End) ? "End" : m2336equalsimpl0(i, Unspecified) ? "Unspecified" : "Invalid";
    }

    /* JADX INFO: compiled from: TextAlign.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getLeft-e0LSkKk, reason: not valid java name */
        public final int m2343getLefte0LSkKk() {
            return TextAlign.Left;
        }

        /* JADX INFO: renamed from: getRight-e0LSkKk, reason: not valid java name */
        public final int m2344getRighte0LSkKk() {
            return TextAlign.Right;
        }

        /* JADX INFO: renamed from: getCenter-e0LSkKk, reason: not valid java name */
        public final int m2340getCentere0LSkKk() {
            return TextAlign.Center;
        }

        /* JADX INFO: renamed from: getJustify-e0LSkKk, reason: not valid java name */
        public final int m2342getJustifye0LSkKk() {
            return TextAlign.Justify;
        }

        /* JADX INFO: renamed from: getStart-e0LSkKk, reason: not valid java name */
        public final int m2345getStarte0LSkKk() {
            return TextAlign.Start;
        }

        /* JADX INFO: renamed from: getEnd-e0LSkKk, reason: not valid java name */
        public final int m2341getEnde0LSkKk() {
            return TextAlign.End;
        }

        /* JADX INFO: renamed from: getUnspecified-e0LSkKk, reason: not valid java name */
        public final int m2346getUnspecifiede0LSkKk() {
            return TextAlign.Unspecified;
        }
    }
}
