package androidx.compose.ui.text.style;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Hyphens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Hyphens {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int None = m2278constructorimpl(1);
    private static final int Auto = m2278constructorimpl(2);
    private static final int Unspecified = m2278constructorimpl(Integer.MIN_VALUE);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Hyphens m2277boximpl(int i) {
        return new Hyphens(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m2278constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2279equalsimpl(int i, Object obj) {
        return (obj instanceof Hyphens) && i == ((Hyphens) obj).m2283unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2280equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2281hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m2279equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2281hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2283unboximpl() {
        return this.value;
    }

    /* JADX INFO: compiled from: Hyphens.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getNone-vmbZdU8, reason: not valid java name */
        public final int m2285getNonevmbZdU8() {
            return Hyphens.None;
        }

        /* JADX INFO: renamed from: getAuto-vmbZdU8, reason: not valid java name */
        public final int m2284getAutovmbZdU8() {
            return Hyphens.Auto;
        }

        /* JADX INFO: renamed from: getUnspecified-vmbZdU8, reason: not valid java name */
        public final int m2286getUnspecifiedvmbZdU8() {
            return Hyphens.Unspecified;
        }
    }

    private /* synthetic */ Hyphens(int i) {
        this.value = i;
    }

    public String toString() {
        return m2282toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2282toStringimpl(int i) {
        return m2280equalsimpl0(i, None) ? "Hyphens.None" : m2280equalsimpl0(i, Auto) ? "Hyphens.Auto" : m2280equalsimpl0(i, Unspecified) ? "Hyphens.Unspecified" : "Invalid";
    }
}
