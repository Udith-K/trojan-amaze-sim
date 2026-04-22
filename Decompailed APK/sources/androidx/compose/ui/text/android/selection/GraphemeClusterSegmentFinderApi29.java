package androidx.compose.ui.text.android.selection;

import android.text.TextPaint;

/* JADX INFO: compiled from: SegmentFinder.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class GraphemeClusterSegmentFinderApi29 extends GraphemeClusterSegmentFinder {
    private final CharSequence text;
    private final TextPaint textPaint;

    public GraphemeClusterSegmentFinderApi29(CharSequence charSequence, TextPaint textPaint) {
        this.text = charSequence;
        this.textPaint = textPaint;
    }

    @Override // androidx.compose.ui.text.android.selection.GraphemeClusterSegmentFinder
    public int previous(int i) {
        TextPaint textPaint = this.textPaint;
        CharSequence charSequence = this.text;
        return textPaint.getTextRunCursor(charSequence, 0, charSequence.length(), false, i, 2);
    }

    @Override // androidx.compose.ui.text.android.selection.GraphemeClusterSegmentFinder
    public int next(int i) {
        TextPaint textPaint = this.textPaint;
        CharSequence charSequence = this.text;
        return textPaint.getTextRunCursor(charSequence, 0, charSequence.length(), false, i, 0);
    }
}
