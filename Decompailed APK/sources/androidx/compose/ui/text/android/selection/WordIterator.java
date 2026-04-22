package androidx.compose.ui.text.android.selection;

import androidx.compose.ui.text.android.CharSequenceCharacterIterator;
import java.text.BreakIterator;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: WordIterator.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class WordIterator {
    private final CharSequence charSequence;
    private final int end;
    private final BreakIterator iterator;
    private final int start;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public WordIterator(CharSequence charSequence, int i, int i2, Locale locale) {
        this.charSequence = charSequence;
        if (i < 0 || i > charSequence.length()) {
            throw new IllegalArgumentException("input start index is outside the CharSequence");
        }
        if (i2 < 0 || i2 > charSequence.length()) {
            throw new IllegalArgumentException("input end index is outside the CharSequence");
        }
        BreakIterator wordInstance = BreakIterator.getWordInstance(locale);
        this.iterator = wordInstance;
        this.start = Math.max(0, i - 50);
        this.end = Math.min(charSequence.length(), i2 + 50);
        wordInstance.setText(new CharSequenceCharacterIterator(charSequence, i, i2));
    }

    public final int nextBoundary(int i) {
        checkOffsetIsValid(i);
        return this.iterator.following(i);
    }

    public final int prevBoundary(int i) {
        checkOffsetIsValid(i);
        return this.iterator.preceding(i);
    }

    public final int getPrevWordBeginningOnTwoWordsBoundary(int i) {
        return getBeginning(i, true);
    }

    public final int getNextWordEndOnTwoWordBoundary(int i) {
        return getEnd(i, true);
    }

    public final int getPunctuationBeginning(int i) {
        checkOffsetIsValid(i);
        while (i != -1 && !isPunctuationStartBoundary(i)) {
            i = prevBoundary(i);
        }
        return i;
    }

    public final int getPunctuationEnd(int i) {
        checkOffsetIsValid(i);
        while (i != -1 && !isPunctuationEndBoundary(i)) {
            i = nextBoundary(i);
        }
        return i;
    }

    public final boolean isAfterPunctuation(int i) {
        int i2 = this.start + 1;
        if (i > this.end || i2 > i) {
            return false;
        }
        return Companion.isPunctuation$ui_text_release(Character.codePointBefore(this.charSequence, i));
    }

    public final boolean isOnPunctuation(int i) {
        int i2 = this.start;
        if (i >= this.end || i2 > i) {
            return false;
        }
        return Companion.isPunctuation$ui_text_release(Character.codePointAt(this.charSequence, i));
    }

    private final int getBeginning(int i, boolean z) {
        checkOffsetIsValid(i);
        if (isOnLetterOrDigit(i)) {
            return (!this.iterator.isBoundary(i) || (isAfterLetterOrDigit(i) && z)) ? this.iterator.preceding(i) : i;
        }
        if (isAfterLetterOrDigit(i)) {
            return this.iterator.preceding(i);
        }
        return -1;
    }

    private final int getEnd(int i, boolean z) {
        checkOffsetIsValid(i);
        if (isAfterLetterOrDigit(i)) {
            return (!this.iterator.isBoundary(i) || (isOnLetterOrDigit(i) && z)) ? this.iterator.following(i) : i;
        }
        if (isOnLetterOrDigit(i)) {
            return this.iterator.following(i);
        }
        return -1;
    }

    private final boolean isPunctuationStartBoundary(int i) {
        return isOnPunctuation(i) && !isAfterPunctuation(i);
    }

    private final boolean isPunctuationEndBoundary(int i) {
        return !isOnPunctuation(i) && isAfterPunctuation(i);
    }

    private final boolean isAfterLetterOrDigit(int i) {
        return i <= this.end && this.start + 1 <= i && Character.isLetterOrDigit(Character.codePointBefore(this.charSequence, i));
    }

    private final boolean isOnLetterOrDigit(int i) {
        return i < this.end && this.start <= i && Character.isLetterOrDigit(Character.codePointAt(this.charSequence, i));
    }

    private final void checkOffsetIsValid(int i) {
        int i2 = this.start;
        if (i > this.end || i2 > i) {
            throw new IllegalArgumentException(("Invalid offset: " + i + ". Valid range is [" + this.start + " , " + this.end + ']').toString());
        }
    }

    /* JADX INFO: compiled from: WordIterator.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isPunctuation$ui_text_release(int i) {
            int type = Character.getType(i);
            return type == 23 || type == 20 || type == 22 || type == 30 || type == 29 || type == 24 || type == 21;
        }
    }
}
