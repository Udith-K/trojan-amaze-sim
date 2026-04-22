package androidx.compose.ui.text.font;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FontSynthesis.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FontSynthesis {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int None = m2162constructorimpl(0);
    private static final int All = m2162constructorimpl(1);
    private static final int Weight = m2162constructorimpl(2);
    private static final int Style = m2162constructorimpl(3);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FontSynthesis m2161boximpl(int i) {
        return new FontSynthesis(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2162constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2163equalsimpl(int i, Object obj) {
        return (obj instanceof FontSynthesis) && i == ((FontSynthesis) obj).m2167unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2164equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2165hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m2163equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2165hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2167unboximpl() {
        return this.value;
    }

    private /* synthetic */ FontSynthesis(int i) {
        this.value = i;
    }

    public String toString() {
        return m2166toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2166toStringimpl(int i) {
        return m2164equalsimpl0(i, None) ? "None" : m2164equalsimpl0(i, All) ? "All" : m2164equalsimpl0(i, Weight) ? "Weight" : m2164equalsimpl0(i, Style) ? "Style" : "Invalid";
    }

    /* JADX INFO: compiled from: FontSynthesis.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getNone-GVVA2EU, reason: not valid java name */
        public final int m2169getNoneGVVA2EU() {
            return FontSynthesis.None;
        }

        /* JADX INFO: renamed from: getAll-GVVA2EU, reason: not valid java name */
        public final int m2168getAllGVVA2EU() {
            return FontSynthesis.All;
        }

        /* JADX INFO: renamed from: getWeight-GVVA2EU, reason: not valid java name */
        public final int m2171getWeightGVVA2EU() {
            return FontSynthesis.Weight;
        }

        /* JADX INFO: renamed from: getStyle-GVVA2EU, reason: not valid java name */
        public final int m2170getStyleGVVA2EU() {
            return FontSynthesis.Style;
        }
    }
}
