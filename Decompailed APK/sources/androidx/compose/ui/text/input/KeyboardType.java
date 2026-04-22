package androidx.compose.ui.text.input;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: KeyboardType.kt */
/* JADX INFO: loaded from: classes.dex */
public final class KeyboardType {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int Unspecified = m2220constructorimpl(0);
    private static final int Text = m2220constructorimpl(1);
    private static final int Ascii = m2220constructorimpl(2);
    private static final int Number = m2220constructorimpl(3);
    private static final int Phone = m2220constructorimpl(4);
    private static final int Uri = m2220constructorimpl(5);
    private static final int Email = m2220constructorimpl(6);
    private static final int Password = m2220constructorimpl(7);
    private static final int NumberPassword = m2220constructorimpl(8);
    private static final int Decimal = m2220constructorimpl(9);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ KeyboardType m2219boximpl(int i) {
        return new KeyboardType(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m2220constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2221equalsimpl(int i, Object obj) {
        return (obj instanceof KeyboardType) && i == ((KeyboardType) obj).m2225unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2222equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2223hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m2221equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2223hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2225unboximpl() {
        return this.value;
    }

    private /* synthetic */ KeyboardType(int i) {
        this.value = i;
    }

    public String toString() {
        return m2224toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2224toStringimpl(int i) {
        return m2222equalsimpl0(i, Unspecified) ? "Unspecified" : m2222equalsimpl0(i, Text) ? "Text" : m2222equalsimpl0(i, Ascii) ? "Ascii" : m2222equalsimpl0(i, Number) ? "Number" : m2222equalsimpl0(i, Phone) ? "Phone" : m2222equalsimpl0(i, Uri) ? "Uri" : m2222equalsimpl0(i, Email) ? "Email" : m2222equalsimpl0(i, Password) ? "Password" : m2222equalsimpl0(i, NumberPassword) ? "NumberPassword" : m2222equalsimpl0(i, Decimal) ? "Decimal" : "Invalid";
    }

    /* JADX INFO: compiled from: KeyboardType.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getUnspecified-PjHm6EE, reason: not valid java name */
        public final int m2234getUnspecifiedPjHm6EE() {
            return KeyboardType.Unspecified;
        }

        /* JADX INFO: renamed from: getText-PjHm6EE, reason: not valid java name */
        public final int m2233getTextPjHm6EE() {
            return KeyboardType.Text;
        }

        /* JADX INFO: renamed from: getAscii-PjHm6EE, reason: not valid java name */
        public final int m2226getAsciiPjHm6EE() {
            return KeyboardType.Ascii;
        }

        /* JADX INFO: renamed from: getNumber-PjHm6EE, reason: not valid java name */
        public final int m2229getNumberPjHm6EE() {
            return KeyboardType.Number;
        }

        /* JADX INFO: renamed from: getPhone-PjHm6EE, reason: not valid java name */
        public final int m2232getPhonePjHm6EE() {
            return KeyboardType.Phone;
        }

        /* JADX INFO: renamed from: getUri-PjHm6EE, reason: not valid java name */
        public final int m2235getUriPjHm6EE() {
            return KeyboardType.Uri;
        }

        /* JADX INFO: renamed from: getEmail-PjHm6EE, reason: not valid java name */
        public final int m2228getEmailPjHm6EE() {
            return KeyboardType.Email;
        }

        /* JADX INFO: renamed from: getPassword-PjHm6EE, reason: not valid java name */
        public final int m2231getPasswordPjHm6EE() {
            return KeyboardType.Password;
        }

        /* JADX INFO: renamed from: getNumberPassword-PjHm6EE, reason: not valid java name */
        public final int m2230getNumberPasswordPjHm6EE() {
            return KeyboardType.NumberPassword;
        }

        /* JADX INFO: renamed from: getDecimal-PjHm6EE, reason: not valid java name */
        public final int m2227getDecimalPjHm6EE() {
            return KeyboardType.Decimal;
        }
    }
}
