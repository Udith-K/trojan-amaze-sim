package androidx.compose.ui.text;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: EmojiSupportMatch.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class EmojiSupportMatch {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int Default = m2038constructorimpl(0);
    private static final int None = m2038constructorimpl(1);
    private static final int All = m2038constructorimpl(2);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ EmojiSupportMatch m2037boximpl(int i) {
        return new EmojiSupportMatch(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m2038constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2039equalsimpl(int i, Object obj) {
        return (obj instanceof EmojiSupportMatch) && i == ((EmojiSupportMatch) obj).m2043unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2040equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2041hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m2039equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2041hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2043unboximpl() {
        return this.value;
    }

    private /* synthetic */ EmojiSupportMatch(int i) {
        this.value = i;
    }

    public String toString() {
        return m2042toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2042toStringimpl(int i) {
        if (i == Default) {
            return "EmojiSupportMatch.Default";
        }
        if (i == None) {
            return "EmojiSupportMatch.None";
        }
        if (i == All) {
            return "EmojiSupportMatch.All";
        }
        return "Invalid(value=" + i + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: EmojiSupportMatch.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getDefault-_3YsG6Y, reason: not valid java name */
        public final int m2045getDefault_3YsG6Y() {
            return EmojiSupportMatch.Default;
        }

        /* JADX INFO: renamed from: getNone-_3YsG6Y, reason: not valid java name */
        public final int m2046getNone_3YsG6Y() {
            return EmojiSupportMatch.None;
        }

        /* JADX INFO: renamed from: getAll-_3YsG6Y, reason: not valid java name */
        public final int m2044getAll_3YsG6Y() {
            return EmojiSupportMatch.All;
        }
    }
}
