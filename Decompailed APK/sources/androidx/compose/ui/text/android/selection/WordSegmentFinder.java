package androidx.compose.ui.text.android.selection;

/* JADX INFO: compiled from: SegmentFinder.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class WordSegmentFinder implements SegmentFinder {
    private final CharSequence text;
    private final WordIterator wordIterator;

    public WordSegmentFinder(CharSequence charSequence, WordIterator wordIterator) {
        this.text = charSequence;
        this.wordIterator = wordIterator;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int previousStartBoundary(int i) {
        do {
            i = this.wordIterator.prevBoundary(i);
            if (i == -1) {
                return -1;
            }
        } while (Character.isWhitespace(this.text.charAt(i)));
        return i;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int previousEndBoundary(int i) {
        do {
            i = this.wordIterator.prevBoundary(i);
            if (i == -1 || i == 0) {
                return -1;
            }
        } while (Character.isWhitespace(this.text.charAt(i - 1)));
        return i;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int nextStartBoundary(int i) {
        do {
            i = this.wordIterator.nextBoundary(i);
            if (i == -1 || i == this.text.length()) {
                return -1;
            }
        } while (Character.isWhitespace(this.text.charAt(i)));
        return i;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int nextEndBoundary(int i) {
        do {
            i = this.wordIterator.nextBoundary(i);
            if (i == -1) {
                return -1;
            }
        } while (Character.isWhitespace(this.text.charAt(i - 1)));
        return i;
    }
}
