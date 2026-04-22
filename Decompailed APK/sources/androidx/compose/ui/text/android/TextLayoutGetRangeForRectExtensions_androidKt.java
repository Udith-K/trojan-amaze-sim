package androidx.compose.ui.text.android;

import android.graphics.RectF;
import android.text.Layout;
import androidx.compose.ui.text.android.LayoutHelper;
import androidx.compose.ui.text.android.selection.SegmentFinder;
import androidx.compose.ui.text.android.selection.SegmentFinder_androidKt;
import androidx.compose.ui.text.android.selection.WordSegmentFinder;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextLayoutGetRangeForRectExtensions.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextLayoutGetRangeForRectExtensions_androidKt {
    public static final int[] getRangeForRect(TextLayout textLayout, Layout layout, LayoutHelper layoutHelper, RectF rectF, int i, Function2 function2) {
        SegmentFinder segmentFinderCreateGraphemeClusterSegmentFinder;
        int i2;
        int i3;
        if (i == 1) {
            segmentFinderCreateGraphemeClusterSegmentFinder = new WordSegmentFinder(textLayout.getText(), textLayout.getWordIterator());
        } else {
            segmentFinderCreateGraphemeClusterSegmentFinder = SegmentFinder_androidKt.createGraphemeClusterSegmentFinder(textLayout.getText(), textLayout.getTextPaint());
        }
        SegmentFinder segmentFinder = segmentFinderCreateGraphemeClusterSegmentFinder;
        int lineForVertical = layout.getLineForVertical((int) rectF.top);
        if (rectF.top > textLayout.getLineBottom(lineForVertical) && (lineForVertical = lineForVertical + 1) >= textLayout.getLineCount()) {
            return null;
        }
        int i4 = lineForVertical;
        int lineForVertical2 = layout.getLineForVertical((int) rectF.bottom);
        if (lineForVertical2 == 0 && rectF.bottom < textLayout.getLineTop(0)) {
            return null;
        }
        int startOrEndOffsetForRectWithinLine = getStartOrEndOffsetForRectWithinLine(textLayout, layout, layoutHelper, i4, rectF, segmentFinder, function2, true);
        while (true) {
            i2 = i4;
            i3 = startOrEndOffsetForRectWithinLine;
            if (i3 != -1 || i2 >= lineForVertical2) {
                break;
            }
            i4 = i2 + 1;
            startOrEndOffsetForRectWithinLine = getStartOrEndOffsetForRectWithinLine(textLayout, layout, layoutHelper, i4, rectF, segmentFinder, function2, true);
        }
        if (i3 == -1) {
            return null;
        }
        int startOrEndOffsetForRectWithinLine2 = getStartOrEndOffsetForRectWithinLine(textLayout, layout, layoutHelper, lineForVertical2, rectF, segmentFinder, function2, false);
        while (startOrEndOffsetForRectWithinLine2 == -1 && i2 < lineForVertical2) {
            lineForVertical2--;
            startOrEndOffsetForRectWithinLine2 = getStartOrEndOffsetForRectWithinLine(textLayout, layout, layoutHelper, lineForVertical2, rectF, segmentFinder, function2, false);
        }
        if (startOrEndOffsetForRectWithinLine2 == -1) {
            return null;
        }
        return new int[]{segmentFinder.previousStartBoundary(i3 + 1), segmentFinder.nextEndBoundary(startOrEndOffsetForRectWithinLine2 - 1)};
    }

    private static final int getStartOrEndOffsetForRectWithinLine(TextLayout textLayout, Layout layout, LayoutHelper layoutHelper, int i, RectF rectF, SegmentFinder segmentFinder, Function2 function2, boolean z) {
        IntProgression intProgressionDownTo;
        float characterLeftBounds;
        float characterRightBounds;
        int i2;
        LayoutHelper.BidiRun[] bidiRunArr;
        int i3;
        int endOffsetForRectWithinRun;
        int lineTop = layout.getLineTop(i);
        int lineBottom = layout.getLineBottom(i);
        int lineStart = layout.getLineStart(i);
        int lineEnd = layout.getLineEnd(i);
        if (lineStart == lineEnd) {
            return -1;
        }
        float[] fArr = new float[(lineEnd - lineStart) * 2];
        textLayout.fillLineHorizontalBounds$ui_text_release(i, fArr);
        LayoutHelper.BidiRun[] lineBidiRuns$ui_text_release = layoutHelper.getLineBidiRuns$ui_text_release(i);
        if (z) {
            intProgressionDownTo = ArraysKt.getIndices(lineBidiRuns$ui_text_release);
        } else {
            intProgressionDownTo = RangesKt.downTo(ArraysKt.getLastIndex(lineBidiRuns$ui_text_release), 0);
        }
        int first = intProgressionDownTo.getFirst();
        int last = intProgressionDownTo.getLast();
        int step = intProgressionDownTo.getStep();
        if ((step <= 0 || first > last) && (step >= 0 || last > first)) {
            return -1;
        }
        int i4 = first;
        while (true) {
            LayoutHelper.BidiRun bidiRun = lineBidiRuns$ui_text_release[i4];
            if (bidiRun.isRtl()) {
                characterLeftBounds = getCharacterLeftBounds(bidiRun.getEnd() - 1, lineStart, fArr);
            } else {
                characterLeftBounds = getCharacterLeftBounds(bidiRun.getStart(), lineStart, fArr);
            }
            float f = characterLeftBounds;
            if (bidiRun.isRtl()) {
                characterRightBounds = getCharacterRightBounds(bidiRun.getStart(), lineStart, fArr);
            } else {
                characterRightBounds = getCharacterRightBounds(bidiRun.getEnd() - 1, lineStart, fArr);
            }
            float f2 = characterRightBounds;
            if (z) {
                i2 = i4;
                bidiRunArr = lineBidiRuns$ui_text_release;
                i3 = last;
                endOffsetForRectWithinRun = getStartOffsetForRectWithinRun(bidiRun, rectF, lineStart, lineTop, lineBottom, f, f2, fArr, segmentFinder, function2);
            } else {
                i2 = i4;
                bidiRunArr = lineBidiRuns$ui_text_release;
                i3 = last;
                endOffsetForRectWithinRun = getEndOffsetForRectWithinRun(bidiRun, rectF, lineStart, lineTop, lineBottom, f, f2, fArr, segmentFinder, function2);
            }
            if (endOffsetForRectWithinRun >= 0) {
                return endOffsetForRectWithinRun;
            }
            if (i2 == i3) {
                return -1;
            }
            i4 = i2 + step;
            last = i3;
            lineBidiRuns$ui_text_release = bidiRunArr;
        }
    }

    private static final int getStartOffsetForRectWithinRun(LayoutHelper.BidiRun bidiRun, RectF rectF, int i, int i2, int i3, float f, float f2, float[] fArr, SegmentFinder segmentFinder, Function2 function2) {
        int start;
        int iPreviousStartBoundary;
        float characterLeftBounds;
        float characterRightBounds;
        if (!horizontalOverlap(rectF, f, f2)) {
            return -1;
        }
        if ((!bidiRun.isRtl() && rectF.left <= f) || (bidiRun.isRtl() && rectF.right >= f2)) {
            start = bidiRun.getStart();
        } else {
            start = bidiRun.getStart();
            int end = bidiRun.getEnd();
            while (end - start > 1) {
                int i4 = (end + start) / 2;
                float characterLeftBounds2 = getCharacterLeftBounds(i4, i, fArr);
                if ((bidiRun.isRtl() || characterLeftBounds2 <= rectF.left) && (!bidiRun.isRtl() || characterLeftBounds2 >= rectF.right)) {
                    start = i4;
                } else {
                    end = i4;
                }
            }
            if (bidiRun.isRtl()) {
                start = end;
            }
        }
        int iNextEndBoundary = segmentFinder.nextEndBoundary(start);
        if (iNextEndBoundary == -1 || (iPreviousStartBoundary = segmentFinder.previousStartBoundary(iNextEndBoundary)) >= bidiRun.getEnd()) {
            return -1;
        }
        int iCoerceAtLeast = RangesKt.coerceAtLeast(iPreviousStartBoundary, bidiRun.getStart());
        int iCoerceAtMost = RangesKt.coerceAtMost(iNextEndBoundary, bidiRun.getEnd());
        RectF rectF2 = new RectF(0.0f, i2, 0.0f, i3);
        while (true) {
            if (bidiRun.isRtl()) {
                characterLeftBounds = getCharacterLeftBounds(iCoerceAtMost - 1, i, fArr);
            } else {
                characterLeftBounds = getCharacterLeftBounds(iCoerceAtLeast, i, fArr);
            }
            rectF2.left = characterLeftBounds;
            if (bidiRun.isRtl()) {
                characterRightBounds = getCharacterRightBounds(iCoerceAtLeast, i, fArr);
            } else {
                characterRightBounds = getCharacterRightBounds(iCoerceAtMost - 1, i, fArr);
            }
            rectF2.right = characterRightBounds;
            if (((Boolean) function2.invoke(rectF2, rectF)).booleanValue()) {
                return iCoerceAtLeast;
            }
            iCoerceAtLeast = segmentFinder.nextStartBoundary(iCoerceAtLeast);
            if (iCoerceAtLeast == -1 || iCoerceAtLeast >= bidiRun.getEnd()) {
                break;
            }
            iCoerceAtMost = RangesKt.coerceAtMost(segmentFinder.nextEndBoundary(iCoerceAtLeast), bidiRun.getEnd());
        }
        return -1;
    }

    private static final int getEndOffsetForRectWithinRun(LayoutHelper.BidiRun bidiRun, RectF rectF, int i, int i2, int i3, float f, float f2, float[] fArr, SegmentFinder segmentFinder, Function2 function2) {
        int start;
        int iNextEndBoundary;
        float characterLeftBounds;
        float characterRightBounds;
        if (!horizontalOverlap(rectF, f, f2)) {
            return -1;
        }
        if ((!bidiRun.isRtl() && rectF.right >= f2) || (bidiRun.isRtl() && rectF.left <= f)) {
            start = bidiRun.getEnd() - 1;
        } else {
            start = bidiRun.getStart();
            int end = bidiRun.getEnd();
            while (end - start > 1) {
                int i4 = (end + start) / 2;
                float characterLeftBounds2 = getCharacterLeftBounds(i4, i, fArr);
                if ((bidiRun.isRtl() || characterLeftBounds2 <= rectF.right) && (!bidiRun.isRtl() || characterLeftBounds2 >= rectF.left)) {
                    start = i4;
                } else {
                    end = i4;
                }
            }
            if (bidiRun.isRtl()) {
                start = end;
            }
        }
        int iPreviousStartBoundary = segmentFinder.previousStartBoundary(start + 1);
        if (iPreviousStartBoundary == -1 || (iNextEndBoundary = segmentFinder.nextEndBoundary(iPreviousStartBoundary)) <= bidiRun.getStart()) {
            return -1;
        }
        int iCoerceAtLeast = RangesKt.coerceAtLeast(iPreviousStartBoundary, bidiRun.getStart());
        int iCoerceAtMost = RangesKt.coerceAtMost(iNextEndBoundary, bidiRun.getEnd());
        RectF rectF2 = new RectF(0.0f, i2, 0.0f, i3);
        while (true) {
            if (bidiRun.isRtl()) {
                characterLeftBounds = getCharacterLeftBounds(iCoerceAtMost - 1, i, fArr);
            } else {
                characterLeftBounds = getCharacterLeftBounds(iCoerceAtLeast, i, fArr);
            }
            rectF2.left = characterLeftBounds;
            if (bidiRun.isRtl()) {
                characterRightBounds = getCharacterRightBounds(iCoerceAtLeast, i, fArr);
            } else {
                characterRightBounds = getCharacterRightBounds(iCoerceAtMost - 1, i, fArr);
            }
            rectF2.right = characterRightBounds;
            if (((Boolean) function2.invoke(rectF2, rectF)).booleanValue()) {
                return iCoerceAtMost;
            }
            iCoerceAtMost = segmentFinder.previousEndBoundary(iCoerceAtMost);
            if (iCoerceAtMost == -1 || iCoerceAtMost <= bidiRun.getStart()) {
                break;
            }
            iCoerceAtLeast = RangesKt.coerceAtLeast(segmentFinder.previousStartBoundary(iCoerceAtMost), bidiRun.getStart());
        }
        return -1;
    }

    private static final float getCharacterLeftBounds(int i, int i2, float[] fArr) {
        return fArr[(i - i2) * 2];
    }

    private static final float getCharacterRightBounds(int i, int i2, float[] fArr) {
        return fArr[((i - i2) * 2) + 1];
    }

    private static final boolean horizontalOverlap(RectF rectF, float f, float f2) {
        return f2 >= rectF.left && f <= rectF.right;
    }
}
