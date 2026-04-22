package androidx.compose.ui.text;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TextGranularity.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextGranularity {
    public static final Companion Companion = new Companion(null);
    private static final int Character = m2094constructorimpl(0);
    private static final int Word = m2094constructorimpl(1);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m2094constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2095equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: compiled from: TextGranularity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getCharacter-DRrd7Zo, reason: not valid java name */
        public final int m2096getCharacterDRrd7Zo() {
            return TextGranularity.Character;
        }

        /* JADX INFO: renamed from: getWord-DRrd7Zo, reason: not valid java name */
        public final int m2097getWordDRrd7Zo() {
            return TextGranularity.Word;
        }
    }
}
