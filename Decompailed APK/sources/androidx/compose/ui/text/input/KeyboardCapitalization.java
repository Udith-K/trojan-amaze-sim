package androidx.compose.ui.text.input;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: KeyboardCapitalization.kt */
/* JADX INFO: loaded from: classes.dex */
public final class KeyboardCapitalization {
    private final int value;
    public static final Companion Companion = new Companion(null);
    private static final int Unspecified = m2208constructorimpl(-1);
    private static final int None = m2208constructorimpl(0);
    private static final int Characters = m2208constructorimpl(1);
    private static final int Words = m2208constructorimpl(2);
    private static final int Sentences = m2208constructorimpl(3);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ KeyboardCapitalization m2207boximpl(int i) {
        return new KeyboardCapitalization(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m2208constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2209equalsimpl(int i, Object obj) {
        return (obj instanceof KeyboardCapitalization) && i == ((KeyboardCapitalization) obj).m2213unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2210equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2211hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m2209equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2211hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2213unboximpl() {
        return this.value;
    }

    private /* synthetic */ KeyboardCapitalization(int i) {
        this.value = i;
    }

    public String toString() {
        return m2212toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2212toStringimpl(int i) {
        return m2210equalsimpl0(i, Unspecified) ? "Unspecified" : m2210equalsimpl0(i, None) ? "None" : m2210equalsimpl0(i, Characters) ? "Characters" : m2210equalsimpl0(i, Words) ? "Words" : m2210equalsimpl0(i, Sentences) ? "Sentences" : "Invalid";
    }

    /* JADX INFO: compiled from: KeyboardCapitalization.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getUnspecified-IUNYP9k, reason: not valid java name */
        public final int m2217getUnspecifiedIUNYP9k() {
            return KeyboardCapitalization.Unspecified;
        }

        /* JADX INFO: renamed from: getNone-IUNYP9k, reason: not valid java name */
        public final int m2215getNoneIUNYP9k() {
            return KeyboardCapitalization.None;
        }

        /* JADX INFO: renamed from: getCharacters-IUNYP9k, reason: not valid java name */
        public final int m2214getCharactersIUNYP9k() {
            return KeyboardCapitalization.Characters;
        }

        /* JADX INFO: renamed from: getWords-IUNYP9k, reason: not valid java name */
        public final int m2218getWordsIUNYP9k() {
            return KeyboardCapitalization.Words;
        }

        /* JADX INFO: renamed from: getSentences-IUNYP9k, reason: not valid java name */
        public final int m2216getSentencesIUNYP9k() {
            return KeyboardCapitalization.Sentences;
        }
    }
}
