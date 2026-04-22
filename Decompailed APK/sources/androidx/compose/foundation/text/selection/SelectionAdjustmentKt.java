package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.StringHelpers_androidKt;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: SelectionAdjustment.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SelectionAdjustmentKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Selection.AnchorInfo updateSelectionBoundary(final SelectionLayout selectionLayout, final SelectableInfo selectableInfo, Selection.AnchorInfo anchorInfo) {
        final int rawStartHandleOffset = selectionLayout.isStartHandle() ? selectableInfo.getRawStartHandleOffset() : selectableInfo.getRawEndHandleOffset();
        if ((selectionLayout.isStartHandle() ? selectionLayout.getStartSlot() : selectionLayout.getEndSlot()) != selectableInfo.getSlot()) {
            return selectableInfo.anchorForOffset(rawStartHandleOffset);
        }
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        final Lazy lazy = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustmentKt$updateSelectionBoundary$currentRawLine$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(selectableInfo.getTextLayoutResult().getLineForOffset(rawStartHandleOffset));
            }
        });
        final int rawEndHandleOffset = selectionLayout.isStartHandle() ? selectableInfo.getRawEndHandleOffset() : selectableInfo.getRawStartHandleOffset();
        final int i = rawStartHandleOffset;
        Lazy lazy2 = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustmentKt$updateSelectionBoundary$anchorSnappedToWordBoundary$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Selection.AnchorInfo invoke() {
                return SelectionAdjustmentKt.snapToWordBoundary(selectableInfo, SelectionAdjustmentKt.updateSelectionBoundary$lambda$0(lazy), i, rawEndHandleOffset, selectionLayout.isStartHandle(), selectionLayout.getCrossStatus() == CrossStatus.CROSSED);
            }
        });
        if (selectableInfo.getSelectableId() != anchorInfo.getSelectableId()) {
            return updateSelectionBoundary$lambda$1(lazy2);
        }
        int rawPreviousHandleOffset = selectableInfo.getRawPreviousHandleOffset();
        if (rawStartHandleOffset == rawPreviousHandleOffset) {
            return anchorInfo;
        }
        if (updateSelectionBoundary$lambda$0(lazy) != selectableInfo.getTextLayoutResult().getLineForOffset(rawPreviousHandleOffset)) {
            return updateSelectionBoundary$lambda$1(lazy2);
        }
        int offset = anchorInfo.getOffset();
        long jM2104getWordBoundaryjx7JFs = selectableInfo.getTextLayoutResult().m2104getWordBoundaryjx7JFs(offset);
        if (!isExpanding(selectableInfo, rawStartHandleOffset, selectionLayout.isStartHandle())) {
            return selectableInfo.anchorForOffset(rawStartHandleOffset);
        }
        if (offset == TextRange.m2117getStartimpl(jM2104getWordBoundaryjx7JFs) || offset == TextRange.m2112getEndimpl(jM2104getWordBoundaryjx7JFs)) {
            return updateSelectionBoundary$lambda$1(lazy2);
        }
        return selectableInfo.anchorForOffset(rawStartHandleOffset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int updateSelectionBoundary$lambda$0(Lazy lazy) {
        return ((Number) lazy.getValue()).intValue();
    }

    private static final Selection.AnchorInfo updateSelectionBoundary$lambda$1(Lazy lazy) {
        return (Selection.AnchorInfo) lazy.getValue();
    }

    private static final boolean isExpanding(SelectableInfo selectableInfo, int i, boolean z) {
        if (selectableInfo.getRawPreviousHandleOffset() == -1) {
            return true;
        }
        if (i == selectableInfo.getRawPreviousHandleOffset()) {
            return false;
        }
        if (z ^ (selectableInfo.getRawCrossStatus() == CrossStatus.CROSSED)) {
            if (i < selectableInfo.getRawPreviousHandleOffset()) {
                return true;
            }
        } else if (i > selectableInfo.getRawPreviousHandleOffset()) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Selection.AnchorInfo snapToWordBoundary(SelectableInfo selectableInfo, int i, int i2, int i3, boolean z, boolean z2) {
        int lineStart;
        int lineEnd$default;
        long jM2104getWordBoundaryjx7JFs = selectableInfo.getTextLayoutResult().m2104getWordBoundaryjx7JFs(i2);
        if (selectableInfo.getTextLayoutResult().getLineForOffset(TextRange.m2117getStartimpl(jM2104getWordBoundaryjx7JFs)) == i) {
            lineStart = TextRange.m2117getStartimpl(jM2104getWordBoundaryjx7JFs);
        } else if (i >= selectableInfo.getTextLayoutResult().getLineCount()) {
            lineStart = selectableInfo.getTextLayoutResult().getLineStart(selectableInfo.getTextLayoutResult().getLineCount() - 1);
        } else {
            lineStart = selectableInfo.getTextLayoutResult().getLineStart(i);
        }
        if (selectableInfo.getTextLayoutResult().getLineForOffset(TextRange.m2112getEndimpl(jM2104getWordBoundaryjx7JFs)) == i) {
            lineEnd$default = TextRange.m2112getEndimpl(jM2104getWordBoundaryjx7JFs);
        } else if (i >= selectableInfo.getTextLayoutResult().getLineCount()) {
            lineEnd$default = TextLayoutResult.getLineEnd$default(selectableInfo.getTextLayoutResult(), selectableInfo.getTextLayoutResult().getLineCount() - 1, false, 2, null);
        } else {
            lineEnd$default = TextLayoutResult.getLineEnd$default(selectableInfo.getTextLayoutResult(), i, false, 2, null);
        }
        if (lineStart == i3) {
            return selectableInfo.anchorForOffset(lineEnd$default);
        }
        if (lineEnd$default == i3) {
            return selectableInfo.anchorForOffset(lineStart);
        }
        if (!(z ^ z2) ? i2 >= lineStart : i2 > lineEnd$default) {
            lineStart = lineEnd$default;
        }
        return selectableInfo.anchorForOffset(lineStart);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Selection adjustToBoundaries(SelectionLayout selectionLayout, BoundaryFunction boundaryFunction) {
        boolean z = selectionLayout.getCrossStatus() == CrossStatus.CROSSED;
        return new Selection(anchorOnBoundary(selectionLayout.getStartInfo(), z, true, selectionLayout.getStartSlot(), boundaryFunction), anchorOnBoundary(selectionLayout.getEndInfo(), z, false, selectionLayout.getEndSlot(), boundaryFunction), z);
    }

    private static final Selection.AnchorInfo anchorOnBoundary(SelectableInfo selectableInfo, boolean z, boolean z2, int i, BoundaryFunction boundaryFunction) {
        int rawStartHandleOffset = z2 ? selectableInfo.getRawStartHandleOffset() : selectableInfo.getRawEndHandleOffset();
        if (i != selectableInfo.getSlot()) {
            return selectableInfo.anchorForOffset(rawStartHandleOffset);
        }
        long jMo534getBoundaryfzxv0v0 = boundaryFunction.mo534getBoundaryfzxv0v0(selectableInfo, rawStartHandleOffset);
        return selectableInfo.anchorForOffset(z ^ z2 ? TextRange.m2117getStartimpl(jMo534getBoundaryfzxv0v0) : TextRange.m2112getEndimpl(jMo534getBoundaryfzxv0v0));
    }

    public static final Selection ensureAtLeastOneChar(Selection selection, SelectionLayout selectionLayout) {
        if (SelectionLayoutKt.isCollapsed(selection, selectionLayout)) {
            return (selectionLayout.getSize() > 1 || selectionLayout.getPreviousSelection() == null || selectionLayout.getCurrentInfo().getInputText().length() == 0) ? selection : expandOneChar(selection, selectionLayout);
        }
        return selection;
    }

    private static final Selection expandOneChar(Selection selection, SelectionLayout selectionLayout) {
        int iFindFollowingBreak;
        SelectableInfo currentInfo = selectionLayout.getCurrentInfo();
        String inputText = currentInfo.getInputText();
        int rawStartHandleOffset = currentInfo.getRawStartHandleOffset();
        int length = inputText.length();
        if (rawStartHandleOffset == 0) {
            int iFindFollowingBreak2 = StringHelpers_androidKt.findFollowingBreak(inputText, 0);
            if (selectionLayout.isStartHandle()) {
                return Selection.copy$default(selection, changeOffset(selection.getStart(), currentInfo, iFindFollowingBreak2), null, true, 2, null);
            }
            return Selection.copy$default(selection, null, changeOffset(selection.getEnd(), currentInfo, iFindFollowingBreak2), false, 1, null);
        }
        if (rawStartHandleOffset == length) {
            int iFindPrecedingBreak = StringHelpers_androidKt.findPrecedingBreak(inputText, length);
            if (selectionLayout.isStartHandle()) {
                return Selection.copy$default(selection, changeOffset(selection.getStart(), currentInfo, iFindPrecedingBreak), null, false, 2, null);
            }
            return Selection.copy$default(selection, null, changeOffset(selection.getEnd(), currentInfo, iFindPrecedingBreak), true, 1, null);
        }
        Selection previousSelection = selectionLayout.getPreviousSelection();
        boolean z = previousSelection != null && previousSelection.getHandlesCrossed();
        if (selectionLayout.isStartHandle() ^ z) {
            iFindFollowingBreak = StringHelpers_androidKt.findPrecedingBreak(inputText, rawStartHandleOffset);
        } else {
            iFindFollowingBreak = StringHelpers_androidKt.findFollowingBreak(inputText, rawStartHandleOffset);
        }
        if (selectionLayout.isStartHandle()) {
            return Selection.copy$default(selection, changeOffset(selection.getStart(), currentInfo, iFindFollowingBreak), null, z, 2, null);
        }
        return Selection.copy$default(selection, null, changeOffset(selection.getEnd(), currentInfo, iFindFollowingBreak), z, 1, null);
    }

    private static final Selection.AnchorInfo changeOffset(Selection.AnchorInfo anchorInfo, SelectableInfo selectableInfo, int i) {
        return Selection.AnchorInfo.copy$default(anchorInfo, selectableInfo.getTextLayoutResult().getBidiRunDirection(i), i, 0L, 4, null);
    }
}
