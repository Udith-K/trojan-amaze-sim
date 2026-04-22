package androidx.compose.ui.text.android.selection;

import android.os.Build;
import android.text.TextPaint;

/* JADX INFO: compiled from: SegmentFinder.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SegmentFinder_androidKt {
    public static final SegmentFinder createGraphemeClusterSegmentFinder(CharSequence charSequence, TextPaint textPaint) {
        if (Build.VERSION.SDK_INT >= 29) {
            return new GraphemeClusterSegmentFinderApi29(charSequence, textPaint);
        }
        return new GraphemeClusterSegmentFinderUnderApi29(charSequence);
    }
}
