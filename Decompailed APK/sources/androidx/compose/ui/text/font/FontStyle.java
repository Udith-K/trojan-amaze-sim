package androidx.compose.ui.text.font;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FontStyle.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FontStyle {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int Normal = m2153constructorimpl(0);
    private static final int Italic = m2153constructorimpl(1);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FontStyle m2152boximpl(int i) {
        return new FontStyle(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2153constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2154equalsimpl(int i, Object obj) {
        return (obj instanceof FontStyle) && i == ((FontStyle) obj).m2158unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2155equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2156hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m2154equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2156hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2158unboximpl() {
        return this.value;
    }

    private /* synthetic */ FontStyle(int i) {
        this.value = i;
    }

    public String toString() {
        return m2157toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2157toStringimpl(int i) {
        return m2155equalsimpl0(i, Normal) ? "Normal" : m2155equalsimpl0(i, Italic) ? "Italic" : "Invalid";
    }

    /* JADX INFO: compiled from: FontStyle.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getNormal-_-LCdwA, reason: not valid java name */
        public final int m2160getNormal_LCdwA() {
            return FontStyle.Normal;
        }

        /* JADX INFO: renamed from: getItalic-_-LCdwA, reason: not valid java name */
        public final int m2159getItalic_LCdwA() {
            return FontStyle.Italic;
        }
    }
}
