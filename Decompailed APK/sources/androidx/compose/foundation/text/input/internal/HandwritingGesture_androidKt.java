package androidx.compose.foundation.text.input.internal;

import android.graphics.PointF;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.TextGranularity;
import androidx.compose.ui.text.TextInclusionStrategy;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.EditingBuffer;

/* JADX INFO: compiled from: HandwritingGesture.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HandwritingGesture_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: adjustHandwritingDeleteGestureRange-72CqOWE, reason: not valid java name */
    public static final long m491adjustHandwritingDeleteGestureRange72CqOWE(long j, CharSequence charSequence) {
        int iM2117getStartimpl = TextRange.m2117getStartimpl(j);
        int iM2112getEndimpl = TextRange.m2112getEndimpl(j);
        int iCodePointBefore = iM2117getStartimpl > 0 ? Character.codePointBefore(charSequence, iM2117getStartimpl) : 10;
        int iCodePointAt = iM2112getEndimpl < charSequence.length() ? Character.codePointAt(charSequence, iM2112getEndimpl) : 10;
        if (isWhitespaceExceptNewline(iCodePointBefore) && (isWhitespace(iCodePointAt) || isPunctuation(iCodePointAt))) {
            do {
                iM2117getStartimpl -= Character.charCount(iCodePointBefore);
                if (iM2117getStartimpl == 0) {
                    break;
                }
                iCodePointBefore = Character.codePointBefore(charSequence, iM2117getStartimpl);
            } while (isWhitespaceExceptNewline(iCodePointBefore));
            return TextRangeKt.TextRange(iM2117getStartimpl, iM2112getEndimpl);
        }
        if (!isWhitespaceExceptNewline(iCodePointAt)) {
            return j;
        }
        if (!isWhitespace(iCodePointBefore) && !isPunctuation(iCodePointBefore)) {
            return j;
        }
        do {
            iM2112getEndimpl += Character.charCount(iCodePointAt);
            if (iM2112getEndimpl == charSequence.length()) {
                break;
            }
            iCodePointAt = Character.codePointAt(charSequence, iM2112getEndimpl);
        } while (isWhitespaceExceptNewline(iCodePointAt));
        return TextRangeKt.TextRange(iM2117getStartimpl, iM2112getEndimpl);
    }

    private static final boolean isNewline(int i) {
        int type = Character.getType(i);
        return type == 14 || type == 13 || i == 10;
    }

    private static final boolean isWhitespace(int i) {
        return Character.isWhitespace(i) || i == 160;
    }

    private static final boolean isWhitespaceExceptNewline(int i) {
        return isWhitespace(i) && !isNewline(i);
    }

    private static final boolean isPunctuation(int i) {
        int type = Character.getType(i);
        return type == 23 || type == 20 || type == 22 || type == 30 || type == 29 || type == 24 || type == 21;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long toOffset(PointF pointF) {
        return OffsetKt.Offset(pointF.x, pointF.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getRangeForScreenRect-OH9lIzo, reason: not valid java name */
    public static final long m500getRangeForScreenRectOH9lIzo(TextLayoutState textLayoutState, Rect rect, int i, TextInclusionStrategy textInclusionStrategy) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getRangeForScreenRects-O048IG0, reason: not valid java name */
    public static final long m502getRangeForScreenRectsO048IG0(TextLayoutState textLayoutState, Rect rect, Rect rect2, int i, TextInclusionStrategy textInclusionStrategy) {
        long jM500getRangeForScreenRectOH9lIzo = m500getRangeForScreenRectOH9lIzo(textLayoutState, rect, i, textInclusionStrategy);
        if (TextRange.m2111getCollapsedimpl(jM500getRangeForScreenRectOH9lIzo)) {
            return TextRange.Companion.m2122getZerod9O1mEE();
        }
        long jM500getRangeForScreenRectOH9lIzo2 = m500getRangeForScreenRectOH9lIzo(textLayoutState, rect2, i, textInclusionStrategy);
        return TextRange.m2111getCollapsedimpl(jM500getRangeForScreenRectOH9lIzo2) ? TextRange.Companion.m2122getZerod9O1mEE() : m492enclosurepWDy79M(jM500getRangeForScreenRectOH9lIzo, jM500getRangeForScreenRectOH9lIzo2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getRangeForScreenRect-OH9lIzo, reason: not valid java name */
    public static final long m499getRangeForScreenRectOH9lIzo(LegacyTextFieldState legacyTextFieldState, Rect rect, int i, TextInclusionStrategy textInclusionStrategy) {
        TextLayoutResult value;
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        return m498getRangeForScreenRectO048IG0((layoutResult == null || (value = layoutResult.getValue()) == null) ? null : value.getMultiParagraph(), rect, legacyTextFieldState.getLayoutCoordinates(), i, textInclusionStrategy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getRangeForScreenRects-O048IG0, reason: not valid java name */
    public static final long m501getRangeForScreenRectsO048IG0(LegacyTextFieldState legacyTextFieldState, Rect rect, Rect rect2, int i, TextInclusionStrategy textInclusionStrategy) {
        long jM499getRangeForScreenRectOH9lIzo = m499getRangeForScreenRectOH9lIzo(legacyTextFieldState, rect, i, textInclusionStrategy);
        if (TextRange.m2111getCollapsedimpl(jM499getRangeForScreenRectOH9lIzo)) {
            return TextRange.Companion.m2122getZerod9O1mEE();
        }
        long jM499getRangeForScreenRectOH9lIzo2 = m499getRangeForScreenRectOH9lIzo(legacyTextFieldState, rect2, i, textInclusionStrategy);
        return TextRange.m2111getCollapsedimpl(jM499getRangeForScreenRectOH9lIzo2) ? TextRange.Companion.m2122getZerod9O1mEE() : m492enclosurepWDy79M(jM499getRangeForScreenRectOH9lIzo, jM499getRangeForScreenRectOH9lIzo2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long rangeOfWhitespaces(CharSequence charSequence, int i) {
        int iCharCount = i;
        while (iCharCount > 0) {
            int iCodePointBefore = CodepointHelpers_jvmKt.codePointBefore(charSequence, iCharCount);
            if (!isWhitespace(iCodePointBefore)) {
                break;
            }
            iCharCount -= Character.charCount(iCodePointBefore);
        }
        while (i < charSequence.length()) {
            int iCodePointAt = CodepointHelpers_jvmKt.codePointAt(charSequence, i);
            if (!isWhitespace(iCodePointAt)) {
                break;
            }
            i += CodepointHelpers_jvmKt.charCount(iCodePointAt);
        }
        return TextRangeKt.TextRange(iCharCount, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getOffsetForHandwritingGesture-d-4ec7I, reason: not valid java name */
    public static final int m495getOffsetForHandwritingGestured4ec7I(TextLayoutState textLayoutState, long j, ViewConfiguration viewConfiguration) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getOffsetForHandwritingGesture-d-4ec7I, reason: not valid java name */
    public static final int m494getOffsetForHandwritingGestured4ec7I(LegacyTextFieldState legacyTextFieldState, long j, ViewConfiguration viewConfiguration) {
        TextLayoutResult value;
        MultiParagraph multiParagraph;
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        if (layoutResult == null || (value = layoutResult.getValue()) == null || (multiParagraph = value.getMultiParagraph()) == null) {
            return -1;
        }
        return m496getOffsetForHandwritingGestureubNVwUQ(multiParagraph, j, legacyTextFieldState.getLayoutCoordinates(), viewConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isBiDiBoundary(TextLayoutResult textLayoutResult, int i) {
        int lineForOffset = textLayoutResult.getLineForOffset(i);
        return (i == textLayoutResult.getLineStart(lineForOffset) || i == TextLayoutResult.getLineEnd$default(textLayoutResult, lineForOffset, false, 2, null)) ? textLayoutResult.getParagraphDirection(i) != textLayoutResult.getBidiRunDirection(i) : textLayoutResult.getBidiRunDirection(i) != textLayoutResult.getBidiRunDirection(i - 1);
    }

    /* JADX INFO: renamed from: getRangeForScreenRect-O048IG0, reason: not valid java name */
    private static final long m498getRangeForScreenRectO048IG0(MultiParagraph multiParagraph, Rect rect, LayoutCoordinates layoutCoordinates, int i, TextInclusionStrategy textInclusionStrategy) {
        if (multiParagraph == null || layoutCoordinates == null) {
            return TextRange.Companion.m2122getZerod9O1mEE();
        }
        return multiParagraph.m2051getRangeForRect86BmAI(rect.m1181translatek4lQ0M(layoutCoordinates.mo1750screenToLocalMKHz9U(Offset.Companion.m1171getZeroF1C5BW0())), i, textInclusionStrategy);
    }

    /* JADX INFO: renamed from: getOffsetForHandwritingGesture-ubNVwUQ, reason: not valid java name */
    private static final int m496getOffsetForHandwritingGestureubNVwUQ(MultiParagraph multiParagraph, long j, LayoutCoordinates layoutCoordinates, ViewConfiguration viewConfiguration) {
        long jMo1750screenToLocalMKHz9U;
        int iM493getLineForHandwritingGestured4ec7I;
        if (layoutCoordinates == null || (iM493getLineForHandwritingGestured4ec7I = m493getLineForHandwritingGestured4ec7I(multiParagraph, (jMo1750screenToLocalMKHz9U = layoutCoordinates.mo1750screenToLocalMKHz9U(j)), viewConfiguration)) == -1) {
            return -1;
        }
        return multiParagraph.m2050getOffsetForPositionk4lQ0M(Offset.m1153copydBAh8RU$default(jMo1750screenToLocalMKHz9U, 0.0f, (multiParagraph.getLineTop(iM493getLineForHandwritingGestured4ec7I) + multiParagraph.getLineBottom(iM493getLineForHandwritingGestured4ec7I)) / 2.0f, 1, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getRangeForRemoveSpaceGesture-5iVPX68, reason: not valid java name */
    public static final long m497getRangeForRemoveSpaceGesture5iVPX68(TextLayoutResult textLayoutResult, long j, long j2, LayoutCoordinates layoutCoordinates, ViewConfiguration viewConfiguration) {
        if (textLayoutResult == null || layoutCoordinates == null) {
            return TextRange.Companion.m2122getZerod9O1mEE();
        }
        long jMo1750screenToLocalMKHz9U = layoutCoordinates.mo1750screenToLocalMKHz9U(j);
        long jMo1750screenToLocalMKHz9U2 = layoutCoordinates.mo1750screenToLocalMKHz9U(j2);
        int iM493getLineForHandwritingGestured4ec7I = m493getLineForHandwritingGestured4ec7I(textLayoutResult.getMultiParagraph(), jMo1750screenToLocalMKHz9U, viewConfiguration);
        int iM493getLineForHandwritingGestured4ec7I2 = m493getLineForHandwritingGestured4ec7I(textLayoutResult.getMultiParagraph(), jMo1750screenToLocalMKHz9U2, viewConfiguration);
        if (iM493getLineForHandwritingGestured4ec7I != -1) {
            if (iM493getLineForHandwritingGestured4ec7I2 != -1) {
                iM493getLineForHandwritingGestured4ec7I = Math.min(iM493getLineForHandwritingGestured4ec7I, iM493getLineForHandwritingGestured4ec7I2);
            }
            iM493getLineForHandwritingGestured4ec7I2 = iM493getLineForHandwritingGestured4ec7I;
        } else if (iM493getLineForHandwritingGestured4ec7I2 == -1) {
            return TextRange.Companion.m2122getZerod9O1mEE();
        }
        float lineTop = (textLayoutResult.getLineTop(iM493getLineForHandwritingGestured4ec7I2) + textLayoutResult.getLineBottom(iM493getLineForHandwritingGestured4ec7I2)) / 2;
        return textLayoutResult.getMultiParagraph().m2051getRangeForRect86BmAI(new Rect(Math.min(Offset.m1159getXimpl(jMo1750screenToLocalMKHz9U), Offset.m1159getXimpl(jMo1750screenToLocalMKHz9U2)), lineTop - 0.1f, Math.max(Offset.m1159getXimpl(jMo1750screenToLocalMKHz9U), Offset.m1159getXimpl(jMo1750screenToLocalMKHz9U2)), lineTop + 0.1f), TextGranularity.Companion.m2096getCharacterDRrd7Zo(), TextInclusionStrategy.Companion.getAnyOverlap());
    }

    /* JADX INFO: renamed from: getLineForHandwritingGesture-d-4ec7I, reason: not valid java name */
    private static final int m493getLineForHandwritingGestured4ec7I(MultiParagraph multiParagraph, long j, ViewConfiguration viewConfiguration) {
        float handwritingGestureLineMargin = viewConfiguration != null ? viewConfiguration.getHandwritingGestureLineMargin() : 0.0f;
        int lineForVerticalPosition = multiParagraph.getLineForVerticalPosition(Offset.m1160getYimpl(j));
        if (Offset.m1160getYimpl(j) < multiParagraph.getLineTop(lineForVerticalPosition) - handwritingGestureLineMargin || Offset.m1160getYimpl(j) > multiParagraph.getLineBottom(lineForVerticalPosition) + handwritingGestureLineMargin || Offset.m1159getXimpl(j) < (-handwritingGestureLineMargin) || Offset.m1159getXimpl(j) > multiParagraph.getWidth() + handwritingGestureLineMargin) {
            return -1;
        }
        return lineForVerticalPosition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditCommand compoundEditCommand(final EditCommand... editCommandArr) {
        return new EditCommand() { // from class: androidx.compose.foundation.text.input.internal.HandwritingGesture_androidKt.compoundEditCommand.1
            @Override // androidx.compose.ui.text.input.EditCommand
            public void applyTo(EditingBuffer editingBuffer) {
                for (EditCommand editCommand : editCommandArr) {
                    editCommand.applyTo(editingBuffer);
                }
            }
        };
    }

    /* JADX INFO: renamed from: enclosure-pWDy79M, reason: not valid java name */
    private static final long m492enclosurepWDy79M(long j, long j2) {
        return TextRangeKt.TextRange(Math.min(TextRange.m2117getStartimpl(j), TextRange.m2117getStartimpl(j)), Math.max(TextRange.m2112getEndimpl(j2), TextRange.m2112getEndimpl(j2)));
    }
}
