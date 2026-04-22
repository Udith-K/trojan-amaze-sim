package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$BooleanRef;

/* JADX INFO: compiled from: SelectionLayout.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SelectionLayoutKt {
    /* JADX INFO: renamed from: getTextFieldSelectionLayout-RcvT-LA, reason: not valid java name */
    public static final SelectionLayout m541getTextFieldSelectionLayoutRcvTLA(TextLayoutResult textLayoutResult, int i, int i2, int i3, long j, boolean z, boolean z2) {
        return new SingleSelectionLayout(z2, 1, 1, z ? null : new Selection(new Selection.AnchorInfo(getTextDirectionForOffset(textLayoutResult, TextRange.m2117getStartimpl(j)), TextRange.m2117getStartimpl(j), 1L), new Selection.AnchorInfo(getTextDirectionForOffset(textLayoutResult, TextRange.m2112getEndimpl(j)), TextRange.m2112getEndimpl(j), 1L), TextRange.m2116getReversedimpl(j)), new SelectableInfo(1L, 1, i, i2, i3, textLayoutResult));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ResolvedTextDirection getTextDirectionForOffset(TextLayoutResult textLayoutResult, int i) {
        return isOffsetAnEmptyLine(textLayoutResult, i) ? textLayoutResult.getParagraphDirection(i) : textLayoutResult.getBidiRunDirection(i);
    }

    private static final boolean isOffsetAnEmptyLine(TextLayoutResult textLayoutResult, int i) {
        if (textLayoutResult.getLayoutInput().getText().length() == 0) {
            return true;
        }
        int lineForOffset = textLayoutResult.getLineForOffset(i);
        return (i == 0 || lineForOffset != textLayoutResult.getLineForOffset(i + (-1))) && (i == textLayoutResult.getLayoutInput().getText().length() || lineForOffset != textLayoutResult.getLineForOffset(i + 1));
    }

    public static final boolean isCollapsed(Selection selection, SelectionLayout selectionLayout) {
        if (selection == null || selectionLayout == null) {
            return true;
        }
        if (selection.getStart().getSelectableId() == selection.getEnd().getSelectableId()) {
            return selection.getStart().getOffset() == selection.getEnd().getOffset();
        }
        if ((selection.getHandlesCrossed() ? selection.getStart() : selection.getEnd()).getOffset() != 0) {
            return false;
        }
        if (selectionLayout.getFirstInfo().getTextLength() != (selection.getHandlesCrossed() ? selection.getEnd() : selection.getStart()).getOffset()) {
            return false;
        }
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        ref$BooleanRef.element = true;
        selectionLayout.forEachMiddleInfo(new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionLayoutKt.isCollapsed.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SelectableInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(SelectableInfo selectableInfo) {
                if (selectableInfo.getInputText().length() > 0) {
                    ref$BooleanRef.element = false;
                }
            }
        });
        return ref$BooleanRef.element;
    }
}
