package androidx.compose.ui.text.android.selection;

import java.text.BreakIterator;

/* JADX INFO: compiled from: SegmentFinder.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class GraphemeClusterSegmentFinderUnderApi29 extends GraphemeClusterSegmentFinder {
    private final BreakIterator breakIterator;
    private final CharSequence text;

    public GraphemeClusterSegmentFinderUnderApi29(CharSequence charSequence) {
        this.text = charSequence;
        BreakIterator characterInstance = BreakIterator.getCharacterInstance();
        characterInstance.setText(charSequence.toString());
        this.breakIterator = characterInstance;
    }

    @Override // androidx.compose.ui.text.android.selection.GraphemeClusterSegmentFinder
    public int previous(int i) {
        return this.breakIterator.preceding(i);
    }

    @Override // androidx.compose.ui.text.android.selection.GraphemeClusterSegmentFinder
    public int next(int i) {
        return this.breakIterator.following(i);
    }
}
