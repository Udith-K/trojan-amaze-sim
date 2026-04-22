package androidx.compose.ui.text.style;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TextDirection.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextDirection {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int Ltr = m2348constructorimpl(1);
    private static final int Rtl = m2348constructorimpl(2);
    private static final int Content = m2348constructorimpl(3);
    private static final int ContentOrLtr = m2348constructorimpl(4);
    private static final int ContentOrRtl = m2348constructorimpl(5);
    private static final int Unspecified = m2348constructorimpl(Integer.MIN_VALUE);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TextDirection m2347boximpl(int i) {
        return new TextDirection(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2348constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2349equalsimpl(int i, Object obj) {
        return (obj instanceof TextDirection) && i == ((TextDirection) obj).m2353unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2350equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2351hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m2349equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2351hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2353unboximpl() {
        return this.value;
    }

    private /* synthetic */ TextDirection(int i) {
        this.value = i;
    }

    public String toString() {
        return m2352toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2352toStringimpl(int i) {
        return m2350equalsimpl0(i, Ltr) ? "Ltr" : m2350equalsimpl0(i, Rtl) ? "Rtl" : m2350equalsimpl0(i, Content) ? "Content" : m2350equalsimpl0(i, ContentOrLtr) ? "ContentOrLtr" : m2350equalsimpl0(i, ContentOrRtl) ? "ContentOrRtl" : m2350equalsimpl0(i, Unspecified) ? "Unspecified" : "Invalid";
    }

    /* JADX INFO: compiled from: TextDirection.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getLtr-s_7X-co, reason: not valid java name */
        public final int m2357getLtrs_7Xco() {
            return TextDirection.Ltr;
        }

        /* JADX INFO: renamed from: getRtl-s_7X-co, reason: not valid java name */
        public final int m2358getRtls_7Xco() {
            return TextDirection.Rtl;
        }

        /* JADX INFO: renamed from: getContent-s_7X-co, reason: not valid java name */
        public final int m2354getContents_7Xco() {
            return TextDirection.Content;
        }

        /* JADX INFO: renamed from: getContentOrLtr-s_7X-co, reason: not valid java name */
        public final int m2355getContentOrLtrs_7Xco() {
            return TextDirection.ContentOrLtr;
        }

        /* JADX INFO: renamed from: getContentOrRtl-s_7X-co, reason: not valid java name */
        public final int m2356getContentOrRtls_7Xco() {
            return TextDirection.ContentOrRtl;
        }

        /* JADX INFO: renamed from: getUnspecified-s_7X-co, reason: not valid java name */
        public final int m2359getUnspecifieds_7Xco() {
            return TextDirection.Unspecified;
        }
    }
}
