package androidx.compose.foundation.text.input.internal;

import android.os.CancellationSignal;
import android.view.inputmethod.DeleteGesture;
import android.view.inputmethod.DeleteRangeGesture;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InsertGesture;
import android.view.inputmethod.JoinOrSplitGesture;
import android.view.inputmethod.PreviewableHandwritingGesture;
import android.view.inputmethod.RemoveSpaceGesture;
import android.view.inputmethod.SelectGesture;
import android.view.inputmethod.SelectRangeGesture;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.input.TextHighlightType;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextGranularity;
import androidx.compose.ui.text.TextInclusionStrategy;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteSurroundingTextCommand;
import androidx.compose.ui.text.input.SetSelectionCommand;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

/* JADX INFO: compiled from: HandwritingGesture.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HandwritingGestureApi34 {
    public static final HandwritingGestureApi34 INSTANCE = new HandwritingGestureApi34();

    private HandwritingGestureApi34() {
    }

    public final int performHandwritingGesture$foundation_release(TransformedTextFieldState transformedTextFieldState, HandwritingGesture handwritingGesture, TextLayoutState textLayoutState, ViewConfiguration viewConfiguration) {
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline8.m(handwritingGesture)) {
            return performSelectGesture(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline9.m(handwritingGesture), textLayoutState);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline10.m(handwritingGesture)) {
            return performDeleteGesture(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline11.m(handwritingGesture), textLayoutState);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline12.m(handwritingGesture)) {
            return performSelectRangeGesture(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline13.m(handwritingGesture), textLayoutState);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline14.m(handwritingGesture)) {
            return performDeleteRangeGesture(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline15.m(handwritingGesture), textLayoutState);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline22.m(handwritingGesture)) {
            return performJoinOrSplitGesture(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline23.m(handwritingGesture), textLayoutState, viewConfiguration);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline18.m(handwritingGesture)) {
            return performInsertGesture(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline19.m(handwritingGesture), textLayoutState, viewConfiguration);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline20.m(handwritingGesture)) {
            return performRemoveSpaceGesture(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline21.m(handwritingGesture), textLayoutState, viewConfiguration);
        }
        return 2;
    }

    public final boolean previewHandwritingGesture$foundation_release(final TransformedTextFieldState transformedTextFieldState, PreviewableHandwritingGesture previewableHandwritingGesture, TextLayoutState textLayoutState, CancellationSignal cancellationSignal) {
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline8.m(previewableHandwritingGesture)) {
            previewSelectGesture(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline9.m(previewableHandwritingGesture), textLayoutState);
        } else if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline10.m(previewableHandwritingGesture)) {
            previewDeleteGesture(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline11.m(previewableHandwritingGesture), textLayoutState);
        } else if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline12.m(previewableHandwritingGesture)) {
            previewSelectRangeGesture(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline13.m(previewableHandwritingGesture), textLayoutState);
        } else {
            if (!HandwritingGestureApi34$$ExternalSyntheticApiModelOutline14.m(previewableHandwritingGesture)) {
                return false;
            }
            previewDeleteRangeGesture(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline15.m(previewableHandwritingGesture), textLayoutState);
        }
        if (cancellationSignal == null) {
            return true;
        }
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener(transformedTextFieldState) { // from class: androidx.compose.foundation.text.input.internal.HandwritingGestureApi34$$ExternalSyntheticLambda32
            @Override // android.os.CancellationSignal.OnCancelListener
            public final void onCancel() {
                HandwritingGestureApi34.previewHandwritingGesture$lambda$1(null);
            }
        });
        return true;
    }

    private final int performSelectGesture(TransformedTextFieldState transformedTextFieldState, SelectGesture selectGesture, TextLayoutState textLayoutState) {
        if (TextRange.m2111getCollapsedimpl(HandwritingGesture_androidKt.m500getRangeForScreenRectOH9lIzo(textLayoutState, RectHelper_androidKt.toComposeRect(selectGesture.getSelectionArea()), m482toTextGranularityNUwxegE(selectGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()))) {
            return INSTANCE.fallback(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(selectGesture));
        }
        throw null;
    }

    private final void previewSelectGesture(TransformedTextFieldState transformedTextFieldState, SelectGesture selectGesture, TextLayoutState textLayoutState) {
        m478highlightRangeXJREzCE(transformedTextFieldState, HandwritingGesture_androidKt.m500getRangeForScreenRectOH9lIzo(textLayoutState, RectHelper_androidKt.toComposeRect(selectGesture.getSelectionArea()), m482toTextGranularityNUwxegE(selectGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()), TextHighlightType.Companion.m472getHandwritingSelectPreviewsxJuwY());
    }

    private final int performDeleteGesture(TransformedTextFieldState transformedTextFieldState, DeleteGesture deleteGesture, TextLayoutState textLayoutState) {
        int iM482toTextGranularityNUwxegE = m482toTextGranularityNUwxegE(deleteGesture.getGranularity());
        long jM500getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m500getRangeForScreenRectOH9lIzo(textLayoutState, RectHelper_androidKt.toComposeRect(deleteGesture.getDeletionArea()), iM482toTextGranularityNUwxegE, TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.m2111getCollapsedimpl(jM500getRangeForScreenRectOH9lIzo)) {
            return INSTANCE.fallback(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(deleteGesture));
        }
        m479performDeletionSbBc2M(transformedTextFieldState, jM500getRangeForScreenRectOH9lIzo, TextGranularity.m2095equalsimpl0(iM482toTextGranularityNUwxegE, TextGranularity.Companion.m2097getWordDRrd7Zo()));
        return 1;
    }

    private final void previewDeleteGesture(TransformedTextFieldState transformedTextFieldState, DeleteGesture deleteGesture, TextLayoutState textLayoutState) {
        m478highlightRangeXJREzCE(transformedTextFieldState, HandwritingGesture_androidKt.m500getRangeForScreenRectOH9lIzo(textLayoutState, RectHelper_androidKt.toComposeRect(deleteGesture.getDeletionArea()), m482toTextGranularityNUwxegE(deleteGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()), TextHighlightType.Companion.m471getHandwritingDeletePreviewsxJuwY());
    }

    private final int performSelectRangeGesture(TransformedTextFieldState transformedTextFieldState, SelectRangeGesture selectRangeGesture, TextLayoutState textLayoutState) {
        if (TextRange.m2111getCollapsedimpl(HandwritingGesture_androidKt.m502getRangeForScreenRectsO048IG0(textLayoutState, RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionStartArea()), RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionEndArea()), m482toTextGranularityNUwxegE(selectRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()))) {
            return INSTANCE.fallback(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(selectRangeGesture));
        }
        throw null;
    }

    private final void previewSelectRangeGesture(TransformedTextFieldState transformedTextFieldState, SelectRangeGesture selectRangeGesture, TextLayoutState textLayoutState) {
        m478highlightRangeXJREzCE(transformedTextFieldState, HandwritingGesture_androidKt.m502getRangeForScreenRectsO048IG0(textLayoutState, RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionStartArea()), RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionEndArea()), m482toTextGranularityNUwxegE(selectRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()), TextHighlightType.Companion.m472getHandwritingSelectPreviewsxJuwY());
    }

    private final int performDeleteRangeGesture(TransformedTextFieldState transformedTextFieldState, DeleteRangeGesture deleteRangeGesture, TextLayoutState textLayoutState) {
        int iM482toTextGranularityNUwxegE = m482toTextGranularityNUwxegE(deleteRangeGesture.getGranularity());
        long jM502getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m502getRangeForScreenRectsO048IG0(textLayoutState, RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionStartArea()), RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionEndArea()), iM482toTextGranularityNUwxegE, TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.m2111getCollapsedimpl(jM502getRangeForScreenRectsO048IG0)) {
            return INSTANCE.fallback(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(deleteRangeGesture));
        }
        m479performDeletionSbBc2M(transformedTextFieldState, jM502getRangeForScreenRectsO048IG0, TextGranularity.m2095equalsimpl0(iM482toTextGranularityNUwxegE, TextGranularity.Companion.m2097getWordDRrd7Zo()));
        return 1;
    }

    private final void previewDeleteRangeGesture(TransformedTextFieldState transformedTextFieldState, DeleteRangeGesture deleteRangeGesture, TextLayoutState textLayoutState) {
        m478highlightRangeXJREzCE(transformedTextFieldState, HandwritingGesture_androidKt.m502getRangeForScreenRectsO048IG0(textLayoutState, RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionStartArea()), RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionEndArea()), m482toTextGranularityNUwxegE(deleteRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()), TextHighlightType.Companion.m471getHandwritingDeletePreviewsxJuwY());
    }

    private final int performJoinOrSplitGesture(TransformedTextFieldState transformedTextFieldState, JoinOrSplitGesture joinOrSplitGesture, TextLayoutState textLayoutState, ViewConfiguration viewConfiguration) {
        throw null;
    }

    private final int performInsertGesture(TransformedTextFieldState transformedTextFieldState, InsertGesture insertGesture, TextLayoutState textLayoutState, ViewConfiguration viewConfiguration) {
        int iM495getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m495getOffsetForHandwritingGestured4ec7I(textLayoutState, HandwritingGesture_androidKt.toOffset(insertGesture.getInsertionPoint()), viewConfiguration);
        if (iM495getOffsetForHandwritingGestured4ec7I == -1) {
            return fallback(transformedTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(insertGesture));
        }
        TransformedTextFieldState.m505replaceTextM8tDOmk$default(transformedTextFieldState, insertGesture.getTextToInsert(), TextRangeKt.TextRange(iM495getOffsetForHandwritingGestured4ec7I), null, false, 12, null);
        return 1;
    }

    private final int performRemoveSpaceGesture(TransformedTextFieldState transformedTextFieldState, RemoveSpaceGesture removeSpaceGesture, TextLayoutState textLayoutState, ViewConfiguration viewConfiguration) {
        throw null;
    }

    private final int fallback(TransformedTextFieldState transformedTextFieldState, HandwritingGesture handwritingGesture) {
        TransformedTextFieldState.access$getTextFieldState$p(transformedTextFieldState);
        TransformedTextFieldState.access$getInputTransformation$p(transformedTextFieldState);
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void previewHandwritingGesture$lambda$1(TransformedTextFieldState transformedTextFieldState) {
        TransformedTextFieldState.access$getTextFieldState$p(transformedTextFieldState);
        TransformedTextFieldState.access$getInputTransformation$p(transformedTextFieldState);
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        throw null;
    }

    /* JADX INFO: renamed from: performDeletion-Sb-Bc2M, reason: not valid java name */
    private final void m479performDeletionSbBc2M(TransformedTextFieldState transformedTextFieldState, long j, boolean z) {
        if (z) {
            throw null;
        }
        TransformedTextFieldState.m505replaceTextM8tDOmk$default(transformedTextFieldState, "", j, null, false, 12, null);
    }

    /* JADX INFO: renamed from: highlightRange-XJREzCE, reason: not valid java name */
    private final void m478highlightRangeXJREzCE(TransformedTextFieldState transformedTextFieldState, long j, int i) {
        if (TextRange.m2111getCollapsedimpl(j)) {
            TransformedTextFieldState.access$getTextFieldState$p(transformedTextFieldState);
            TransformedTextFieldState.access$getInputTransformation$p(transformedTextFieldState);
            TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
            throw null;
        }
        throw null;
    }

    public final int performHandwritingGesture$foundation_release(LegacyTextFieldState legacyTextFieldState, HandwritingGesture handwritingGesture, TextFieldSelectionManager textFieldSelectionManager, ViewConfiguration viewConfiguration, Function1 function1) {
        TextLayoutResult value;
        TextLayoutInput layoutInput;
        AnnotatedString untransformedText = legacyTextFieldState.getUntransformedText();
        if (untransformedText == null) {
            return 3;
        }
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        if (!Intrinsics.areEqual(untransformedText, (layoutResult == null || (value = layoutResult.getValue()) == null || (layoutInput = value.getLayoutInput()) == null) ? null : layoutInput.getText())) {
            return 3;
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline8.m(handwritingGesture)) {
            return performSelectGesture(legacyTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline9.m(handwritingGesture), textFieldSelectionManager, function1);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline10.m(handwritingGesture)) {
            return performDeleteGesture(legacyTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline11.m(handwritingGesture), untransformedText, function1);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline12.m(handwritingGesture)) {
            return performSelectRangeGesture(legacyTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline13.m(handwritingGesture), textFieldSelectionManager, function1);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline14.m(handwritingGesture)) {
            return performDeleteRangeGesture(legacyTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline15.m(handwritingGesture), untransformedText, function1);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline22.m(handwritingGesture)) {
            return performJoinOrSplitGesture(legacyTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline23.m(handwritingGesture), untransformedText, viewConfiguration, function1);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline18.m(handwritingGesture)) {
            return performInsertGesture(legacyTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline19.m(handwritingGesture), viewConfiguration, function1);
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline20.m(handwritingGesture)) {
            return performRemoveSpaceGesture(legacyTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline21.m(handwritingGesture), untransformedText, viewConfiguration, function1);
        }
        return 2;
    }

    public final boolean previewHandwritingGesture$foundation_release(LegacyTextFieldState legacyTextFieldState, PreviewableHandwritingGesture previewableHandwritingGesture, final TextFieldSelectionManager textFieldSelectionManager, CancellationSignal cancellationSignal) {
        TextLayoutResult value;
        TextLayoutInput layoutInput;
        AnnotatedString untransformedText = legacyTextFieldState.getUntransformedText();
        if (untransformedText == null) {
            return false;
        }
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        if (!Intrinsics.areEqual(untransformedText, (layoutResult == null || (value = layoutResult.getValue()) == null || (layoutInput = value.getLayoutInput()) == null) ? null : layoutInput.getText())) {
            return false;
        }
        if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline8.m(previewableHandwritingGesture)) {
            previewSelectGesture(legacyTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline9.m(previewableHandwritingGesture), textFieldSelectionManager);
        } else if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline10.m(previewableHandwritingGesture)) {
            previewDeleteGesture(legacyTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline11.m(previewableHandwritingGesture), textFieldSelectionManager);
        } else if (HandwritingGestureApi34$$ExternalSyntheticApiModelOutline12.m(previewableHandwritingGesture)) {
            previewSelectRangeGesture(legacyTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline13.m(previewableHandwritingGesture), textFieldSelectionManager);
        } else {
            if (!HandwritingGestureApi34$$ExternalSyntheticApiModelOutline14.m(previewableHandwritingGesture)) {
                return false;
            }
            previewDeleteRangeGesture(legacyTextFieldState, HandwritingGestureApi34$$ExternalSyntheticApiModelOutline15.m(previewableHandwritingGesture), textFieldSelectionManager);
        }
        if (cancellationSignal == null) {
            return true;
        }
        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.compose.foundation.text.input.internal.HandwritingGestureApi34$$ExternalSyntheticLambda31
            @Override // android.os.CancellationSignal.OnCancelListener
            public final void onCancel() {
                HandwritingGestureApi34.previewHandwritingGesture$lambda$9(textFieldSelectionManager);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void previewHandwritingGesture$lambda$9(TextFieldSelectionManager textFieldSelectionManager) {
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.clearPreviewHighlight$foundation_release();
        }
    }

    private final int performSelectGesture(LegacyTextFieldState legacyTextFieldState, SelectGesture selectGesture, TextFieldSelectionManager textFieldSelectionManager, Function1 function1) {
        long jM499getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m499getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectGesture.getSelectionArea()), m482toTextGranularityNUwxegE(selectGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.m2111getCollapsedimpl(jM499getRangeForScreenRectOH9lIzo)) {
            return INSTANCE.fallbackOnLegacyTextField(HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(selectGesture), function1);
        }
        m481performSelectionOnLegacyTextField8ffj60Q(jM499getRangeForScreenRectOH9lIzo, textFieldSelectionManager, function1);
        return 1;
    }

    private final void previewSelectGesture(LegacyTextFieldState legacyTextFieldState, SelectGesture selectGesture, TextFieldSelectionManager textFieldSelectionManager) {
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.m560setSelectionPreviewHighlight5zctL8$foundation_release(HandwritingGesture_androidKt.m499getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectGesture.getSelectionArea()), m482toTextGranularityNUwxegE(selectGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()));
        }
    }

    private final int performDeleteGesture(LegacyTextFieldState legacyTextFieldState, DeleteGesture deleteGesture, AnnotatedString annotatedString, Function1 function1) {
        int iM482toTextGranularityNUwxegE = m482toTextGranularityNUwxegE(deleteGesture.getGranularity());
        long jM499getRangeForScreenRectOH9lIzo = HandwritingGesture_androidKt.m499getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteGesture.getDeletionArea()), iM482toTextGranularityNUwxegE, TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.m2111getCollapsedimpl(jM499getRangeForScreenRectOH9lIzo)) {
            return INSTANCE.fallbackOnLegacyTextField(HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(deleteGesture), function1);
        }
        m480performDeletionOnLegacyTextFieldvJH6DeI(jM499getRangeForScreenRectOH9lIzo, annotatedString, TextGranularity.m2095equalsimpl0(iM482toTextGranularityNUwxegE, TextGranularity.Companion.m2097getWordDRrd7Zo()), function1);
        return 1;
    }

    private final void previewDeleteGesture(LegacyTextFieldState legacyTextFieldState, DeleteGesture deleteGesture, TextFieldSelectionManager textFieldSelectionManager) {
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.m559setDeletionPreviewHighlight5zctL8$foundation_release(HandwritingGesture_androidKt.m499getRangeForScreenRectOH9lIzo(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteGesture.getDeletionArea()), m482toTextGranularityNUwxegE(deleteGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()));
        }
    }

    private final int performSelectRangeGesture(LegacyTextFieldState legacyTextFieldState, SelectRangeGesture selectRangeGesture, TextFieldSelectionManager textFieldSelectionManager, Function1 function1) {
        long jM501getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m501getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionStartArea()), RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionEndArea()), m482toTextGranularityNUwxegE(selectRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.m2111getCollapsedimpl(jM501getRangeForScreenRectsO048IG0)) {
            return INSTANCE.fallbackOnLegacyTextField(HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(selectRangeGesture), function1);
        }
        m481performSelectionOnLegacyTextField8ffj60Q(jM501getRangeForScreenRectsO048IG0, textFieldSelectionManager, function1);
        return 1;
    }

    private final void previewSelectRangeGesture(LegacyTextFieldState legacyTextFieldState, SelectRangeGesture selectRangeGesture, TextFieldSelectionManager textFieldSelectionManager) {
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.m560setSelectionPreviewHighlight5zctL8$foundation_release(HandwritingGesture_androidKt.m501getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionStartArea()), RectHelper_androidKt.toComposeRect(selectRangeGesture.getSelectionEndArea()), m482toTextGranularityNUwxegE(selectRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()));
        }
    }

    private final int performDeleteRangeGesture(LegacyTextFieldState legacyTextFieldState, DeleteRangeGesture deleteRangeGesture, AnnotatedString annotatedString, Function1 function1) {
        int iM482toTextGranularityNUwxegE = m482toTextGranularityNUwxegE(deleteRangeGesture.getGranularity());
        long jM501getRangeForScreenRectsO048IG0 = HandwritingGesture_androidKt.m501getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionStartArea()), RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionEndArea()), iM482toTextGranularityNUwxegE, TextInclusionStrategy.Companion.getContainsCenter());
        if (TextRange.m2111getCollapsedimpl(jM501getRangeForScreenRectsO048IG0)) {
            return INSTANCE.fallbackOnLegacyTextField(HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(deleteRangeGesture), function1);
        }
        m480performDeletionOnLegacyTextFieldvJH6DeI(jM501getRangeForScreenRectsO048IG0, annotatedString, TextGranularity.m2095equalsimpl0(iM482toTextGranularityNUwxegE, TextGranularity.Companion.m2097getWordDRrd7Zo()), function1);
        return 1;
    }

    private final void previewDeleteRangeGesture(LegacyTextFieldState legacyTextFieldState, DeleteRangeGesture deleteRangeGesture, TextFieldSelectionManager textFieldSelectionManager) {
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.m559setDeletionPreviewHighlight5zctL8$foundation_release(HandwritingGesture_androidKt.m501getRangeForScreenRectsO048IG0(legacyTextFieldState, RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionStartArea()), RectHelper_androidKt.toComposeRect(deleteRangeGesture.getDeletionEndArea()), m482toTextGranularityNUwxegE(deleteRangeGesture.getGranularity()), TextInclusionStrategy.Companion.getContainsCenter()));
        }
    }

    private final int performJoinOrSplitGesture(LegacyTextFieldState legacyTextFieldState, JoinOrSplitGesture joinOrSplitGesture, AnnotatedString annotatedString, ViewConfiguration viewConfiguration, Function1 function1) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        if (viewConfiguration != null) {
            int iM494getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m494getOffsetForHandwritingGestured4ec7I(legacyTextFieldState, HandwritingGesture_androidKt.toOffset(joinOrSplitGesture.getJoinOrSplitPoint()), viewConfiguration);
            if (iM494getOffsetForHandwritingGestured4ec7I != -1 && ((layoutResult = legacyTextFieldState.getLayoutResult()) == null || (value = layoutResult.getValue()) == null || !HandwritingGesture_androidKt.isBiDiBoundary(value, iM494getOffsetForHandwritingGestured4ec7I))) {
                long jRangeOfWhitespaces = HandwritingGesture_androidKt.rangeOfWhitespaces(annotatedString, iM494getOffsetForHandwritingGestured4ec7I);
                if (TextRange.m2111getCollapsedimpl(jRangeOfWhitespaces)) {
                    performInsertionOnLegacyTextField(TextRange.m2117getStartimpl(jRangeOfWhitespaces), " ", function1);
                } else {
                    m480performDeletionOnLegacyTextFieldvJH6DeI(jRangeOfWhitespaces, annotatedString, false, function1);
                }
                return 1;
            }
            return fallbackOnLegacyTextField(HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(joinOrSplitGesture), function1);
        }
        return fallbackOnLegacyTextField(HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(joinOrSplitGesture), function1);
    }

    private final int performInsertGesture(LegacyTextFieldState legacyTextFieldState, InsertGesture insertGesture, ViewConfiguration viewConfiguration, Function1 function1) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        if (viewConfiguration != null) {
            int iM494getOffsetForHandwritingGestured4ec7I = HandwritingGesture_androidKt.m494getOffsetForHandwritingGestured4ec7I(legacyTextFieldState, HandwritingGesture_androidKt.toOffset(insertGesture.getInsertionPoint()), viewConfiguration);
            if (iM494getOffsetForHandwritingGestured4ec7I == -1 || ((layoutResult = legacyTextFieldState.getLayoutResult()) != null && (value = layoutResult.getValue()) != null && HandwritingGesture_androidKt.isBiDiBoundary(value, iM494getOffsetForHandwritingGestured4ec7I))) {
                return fallbackOnLegacyTextField(HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(insertGesture), function1);
            }
            performInsertionOnLegacyTextField(iM494getOffsetForHandwritingGestured4ec7I, insertGesture.getTextToInsert(), function1);
            return 1;
        }
        return fallbackOnLegacyTextField(HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(insertGesture), function1);
    }

    private final int performRemoveSpaceGesture(LegacyTextFieldState legacyTextFieldState, RemoveSpaceGesture removeSpaceGesture, AnnotatedString annotatedString, ViewConfiguration viewConfiguration, Function1 function1) {
        TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
        long jM497getRangeForRemoveSpaceGesture5iVPX68 = HandwritingGesture_androidKt.m497getRangeForRemoveSpaceGesture5iVPX68(layoutResult != null ? layoutResult.getValue() : null, HandwritingGesture_androidKt.toOffset(removeSpaceGesture.getStartPoint()), HandwritingGesture_androidKt.toOffset(removeSpaceGesture.getEndPoint()), legacyTextFieldState.getLayoutCoordinates(), viewConfiguration);
        if (TextRange.m2111getCollapsedimpl(jM497getRangeForRemoveSpaceGesture5iVPX68)) {
            return INSTANCE.fallbackOnLegacyTextField(HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(removeSpaceGesture), function1);
        }
        final Ref$IntRef ref$IntRef = new Ref$IntRef();
        ref$IntRef.element = -1;
        final Ref$IntRef ref$IntRef2 = new Ref$IntRef();
        ref$IntRef2.element = -1;
        String strReplace = new Regex("\\s+").replace(TextRangeKt.m2124substringFDrldGo(annotatedString, jM497getRangeForRemoveSpaceGesture5iVPX68), new Function1() { // from class: androidx.compose.foundation.text.input.internal.HandwritingGestureApi34$performRemoveSpaceGesture$newText$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(MatchResult matchResult) {
                Ref$IntRef ref$IntRef3 = ref$IntRef;
                if (ref$IntRef3.element == -1) {
                    ref$IntRef3.element = matchResult.getRange().getFirst();
                }
                ref$IntRef2.element = matchResult.getRange().getLast() + 1;
                return "";
            }
        });
        if (ref$IntRef.element == -1 || ref$IntRef2.element == -1) {
            return fallbackOnLegacyTextField(HandwritingGestureApi34$$ExternalSyntheticApiModelOutline7.m(removeSpaceGesture), function1);
        }
        int iM2117getStartimpl = TextRange.m2117getStartimpl(jM497getRangeForRemoveSpaceGesture5iVPX68) + ref$IntRef.element;
        int iM2117getStartimpl2 = TextRange.m2117getStartimpl(jM497getRangeForRemoveSpaceGesture5iVPX68) + ref$IntRef2.element;
        String strSubstring = strReplace.substring(ref$IntRef.element, strReplace.length() - (TextRange.m2113getLengthimpl(jM497getRangeForRemoveSpaceGesture5iVPX68) - ref$IntRef2.element));
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        function1.invoke(HandwritingGesture_androidKt.compoundEditCommand(new SetSelectionCommand(iM2117getStartimpl, iM2117getStartimpl2), new CommitTextCommand(strSubstring, 1)));
        return 1;
    }

    private final void performInsertionOnLegacyTextField(int i, String str, Function1 function1) {
        function1.invoke(HandwritingGesture_androidKt.compoundEditCommand(new SetSelectionCommand(i, i), new CommitTextCommand(str, 1)));
    }

    /* JADX INFO: renamed from: performSelectionOnLegacyTextField-8ffj60Q, reason: not valid java name */
    private final void m481performSelectionOnLegacyTextField8ffj60Q(long j, TextFieldSelectionManager textFieldSelectionManager, Function1 function1) {
        function1.invoke(new SetSelectionCommand(TextRange.m2117getStartimpl(j), TextRange.m2112getEndimpl(j)));
        if (textFieldSelectionManager != null) {
            textFieldSelectionManager.enterSelectionMode$foundation_release(true);
        }
    }

    /* JADX INFO: renamed from: performDeletionOnLegacyTextField-vJH6DeI, reason: not valid java name */
    private final void m480performDeletionOnLegacyTextFieldvJH6DeI(long j, AnnotatedString annotatedString, boolean z, Function1 function1) {
        if (z) {
            j = HandwritingGesture_androidKt.m491adjustHandwritingDeleteGestureRange72CqOWE(j, annotatedString);
        }
        function1.invoke(HandwritingGesture_androidKt.compoundEditCommand(new SetSelectionCommand(TextRange.m2112getEndimpl(j), TextRange.m2112getEndimpl(j)), new DeleteSurroundingTextCommand(TextRange.m2113getLengthimpl(j), 0)));
    }

    private final int fallbackOnLegacyTextField(HandwritingGesture handwritingGesture, Function1 function1) {
        String fallbackText = handwritingGesture.getFallbackText();
        if (fallbackText == null) {
            return 3;
        }
        function1.invoke(new CommitTextCommand(fallbackText, 1));
        return 5;
    }

    /* JADX INFO: renamed from: toTextGranularity-NUwxegE, reason: not valid java name */
    private final int m482toTextGranularityNUwxegE(int i) {
        if (i == 1) {
            return TextGranularity.Companion.m2097getWordDRrd7Zo();
        }
        if (i == 2) {
            return TextGranularity.Companion.m2096getCharacterDRrd7Zo();
        }
        return TextGranularity.Companion.m2096getCharacterDRrd7Zo();
    }
}
