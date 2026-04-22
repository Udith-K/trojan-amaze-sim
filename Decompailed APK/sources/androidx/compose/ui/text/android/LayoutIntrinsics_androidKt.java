package androidx.compose.ui.text.android;

import android.text.Layout;
import android.text.Spanned;
import android.text.TextPaint;
import androidx.compose.ui.text.android.style.LetterSpacingSpanEm;
import androidx.compose.ui.text.android.style.LetterSpacingSpanPx;
import java.text.BreakIterator;
import java.util.Comparator;
import java.util.PriorityQueue;
import kotlin.Pair;

/* JADX INFO: compiled from: LayoutIntrinsics.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LayoutIntrinsics_androidKt {
    public static final float minIntrinsicWidth(CharSequence charSequence, TextPaint textPaint) {
        BreakIterator lineInstance = BreakIterator.getLineInstance(textPaint.getTextLocale());
        int i = 0;
        lineInstance.setText(new CharSequenceCharacterIterator(charSequence, 0, charSequence.length()));
        PriorityQueue<Pair> priorityQueue = new PriorityQueue(10, new Comparator() { // from class: androidx.compose.ui.text.android.LayoutIntrinsics_androidKt$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return LayoutIntrinsics_androidKt.minIntrinsicWidth$lambda$0((Pair) obj, (Pair) obj2);
            }
        });
        int next = lineInstance.next();
        while (true) {
            int i2 = i;
            i = next;
            if (i == -1) {
                break;
            }
            if (priorityQueue.size() < 10) {
                priorityQueue.add(new Pair(Integer.valueOf(i2), Integer.valueOf(i)));
            } else {
                Pair pair = (Pair) priorityQueue.peek();
                if (pair != null && ((Number) pair.getSecond()).intValue() - ((Number) pair.getFirst()).intValue() < i - i2) {
                    priorityQueue.poll();
                    priorityQueue.add(new Pair(Integer.valueOf(i2), Integer.valueOf(i)));
                }
            }
            next = lineInstance.next();
        }
        float fMax = 0.0f;
        for (Pair pair2 : priorityQueue) {
            fMax = Math.max(fMax, Layout.getDesiredWidth(charSequence, ((Number) pair2.component1()).intValue(), ((Number) pair2.component2()).intValue(), textPaint));
        }
        return fMax;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int minIntrinsicWidth$lambda$0(Pair pair, Pair pair2) {
        return (((Number) pair.getSecond()).intValue() - ((Number) pair.getFirst()).intValue()) - (((Number) pair2.getSecond()).intValue() - ((Number) pair2.getFirst()).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldIncreaseMaxIntrinsic(float f, CharSequence charSequence, TextPaint textPaint) {
        if (f != 0.0f) {
            if (charSequence instanceof Spanned) {
                Spanned spanned = (Spanned) charSequence;
                if (!SpannedExtensions_androidKt.hasSpan(spanned, LetterSpacingSpanPx.class) && !SpannedExtensions_androidKt.hasSpan(spanned, LetterSpacingSpanEm.class)) {
                }
                return true;
            }
            if (textPaint.getLetterSpacing() != 0.0f) {
                return true;
            }
        }
        return false;
    }
}
