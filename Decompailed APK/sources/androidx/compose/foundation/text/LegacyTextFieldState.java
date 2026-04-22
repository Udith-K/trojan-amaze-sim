package androidx.compose.foundation.text;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoreTextField.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LegacyTextFieldState {
    private LayoutCoordinates _layoutCoordinates;
    private final MutableState deletionPreviewHighlightRange$delegate;
    private final MutableState handleState$delegate;
    private final MutableState hasFocus$delegate;
    private final Paint highlightPaint;
    private TextInputSession inputSession;
    private final MutableState isInTouchMode$delegate;
    private boolean isLayoutResultStale;
    private final KeyboardActionRunner keyboardActionRunner;
    private final SoftwareKeyboardController keyboardController;
    private final MutableState layoutResultState;
    private final MutableState minHeightForSingleLineField$delegate;
    private final Function1 onImeActionPerformed;
    private final Function1 onValueChange;
    private Function1 onValueChangeOriginal;
    private final EditProcessor processor = new EditProcessor();
    private final RecomposeScope recomposeScope;
    private long selectionBackgroundColor;
    private final MutableState selectionPreviewHighlightRange$delegate;
    private final MutableState showCursorHandle$delegate;
    private final MutableState showFloatingToolbar$delegate;
    private final MutableState showSelectionHandleEnd$delegate;
    private final MutableState showSelectionHandleStart$delegate;
    private TextDelegate textDelegate;
    private AnnotatedString untransformedText;

    public LegacyTextFieldState(TextDelegate textDelegate, RecomposeScope recomposeScope, SoftwareKeyboardController softwareKeyboardController) {
        this.textDelegate = textDelegate;
        this.recomposeScope = recomposeScope;
        this.keyboardController = softwareKeyboardController;
        Boolean bool = Boolean.FALSE;
        this.hasFocus$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.minHeightForSingleLineField$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Dp.m2414boximpl(Dp.m2416constructorimpl(0)), null, 2, null);
        this.layoutResultState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.handleState$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(HandleState.None, null, 2, null);
        this.showFloatingToolbar$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.showSelectionHandleStart$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.showSelectionHandleEnd$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.showCursorHandle$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.isLayoutResultStale = true;
        this.isInTouchMode$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
        this.keyboardActionRunner = new KeyboardActionRunner(softwareKeyboardController);
        this.onValueChangeOriginal = new Function1() { // from class: androidx.compose.foundation.text.LegacyTextFieldState$onValueChangeOriginal$1
            public final void invoke(TextFieldValue textFieldValue) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((TextFieldValue) obj);
                return Unit.INSTANCE;
            }
        };
        this.onValueChange = new Function1() { // from class: androidx.compose.foundation.text.LegacyTextFieldState$onValueChange$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((TextFieldValue) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(TextFieldValue textFieldValue) {
                String text = textFieldValue.getText();
                AnnotatedString untransformedText = this.this$0.getUntransformedText();
                if (!Intrinsics.areEqual(text, untransformedText != null ? untransformedText.getText() : null)) {
                    this.this$0.setHandleState(HandleState.None);
                }
                LegacyTextFieldState legacyTextFieldState = this.this$0;
                TextRange.Companion companion = TextRange.Companion;
                legacyTextFieldState.m397setSelectionPreviewHighlightRange5zctL8(companion.m2122getZerod9O1mEE());
                this.this$0.m395setDeletionPreviewHighlightRange5zctL8(companion.m2122getZerod9O1mEE());
                this.this$0.onValueChangeOriginal.invoke(textFieldValue);
                this.this$0.getRecomposeScope().invalidate();
            }
        };
        this.onImeActionPerformed = new Function1() { // from class: androidx.compose.foundation.text.LegacyTextFieldState$onImeActionPerformed$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m399invokeKlQnJC8(((ImeAction) obj).m2193unboximpl());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-KlQnJC8, reason: not valid java name */
            public final void m399invokeKlQnJC8(int i) {
                this.this$0.keyboardActionRunner.m387runActionKlQnJC8(i);
            }
        };
        this.highlightPaint = AndroidPaint_androidKt.Paint();
        this.selectionBackgroundColor = Color.Companion.m1309getUnspecified0d7_KjU();
        TextRange.Companion companion = TextRange.Companion;
        this.selectionPreviewHighlightRange$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TextRange.m2105boximpl(companion.m2122getZerod9O1mEE()), null, 2, null);
        this.deletionPreviewHighlightRange$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TextRange.m2105boximpl(companion.m2122getZerod9O1mEE()), null, 2, null);
    }

    public final TextDelegate getTextDelegate() {
        return this.textDelegate;
    }

    public final RecomposeScope getRecomposeScope() {
        return this.recomposeScope;
    }

    public final SoftwareKeyboardController getKeyboardController() {
        return this.keyboardController;
    }

    public final EditProcessor getProcessor() {
        return this.processor;
    }

    public final TextInputSession getInputSession() {
        return this.inputSession;
    }

    public final void setInputSession(TextInputSession textInputSession) {
        this.inputSession = textInputSession;
    }

    public final boolean getHasFocus() {
        return ((Boolean) this.hasFocus$delegate.getValue()).booleanValue();
    }

    public final void setHasFocus(boolean z) {
        this.hasFocus$delegate.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: getMinHeightForSingleLineField-D9Ej5fM, reason: not valid java name */
    public final float m392getMinHeightForSingleLineFieldD9Ej5fM() {
        return ((Dp) this.minHeightForSingleLineField$delegate.getValue()).m2422unboximpl();
    }

    /* JADX INFO: renamed from: setMinHeightForSingleLineField-0680j_4, reason: not valid java name */
    public final void m396setMinHeightForSingleLineField0680j_4(float f) {
        this.minHeightForSingleLineField$delegate.setValue(Dp.m2414boximpl(f));
    }

    public final LayoutCoordinates getLayoutCoordinates() {
        LayoutCoordinates layoutCoordinates = this._layoutCoordinates;
        if (layoutCoordinates == null || !layoutCoordinates.isAttached()) {
            return null;
        }
        return layoutCoordinates;
    }

    public final void setLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        this._layoutCoordinates = layoutCoordinates;
    }

    public final TextLayoutResultProxy getLayoutResult() {
        return (TextLayoutResultProxy) this.layoutResultState.getValue();
    }

    public final void setLayoutResult(TextLayoutResultProxy textLayoutResultProxy) {
        this.layoutResultState.setValue(textLayoutResultProxy);
        this.isLayoutResultStale = false;
    }

    public final AnnotatedString getUntransformedText() {
        return this.untransformedText;
    }

    public final HandleState getHandleState() {
        return (HandleState) this.handleState$delegate.getValue();
    }

    public final void setHandleState(HandleState handleState) {
        this.handleState$delegate.setValue(handleState);
    }

    public final boolean getShowFloatingToolbar() {
        return ((Boolean) this.showFloatingToolbar$delegate.getValue()).booleanValue();
    }

    public final void setShowFloatingToolbar(boolean z) {
        this.showFloatingToolbar$delegate.setValue(Boolean.valueOf(z));
    }

    public final boolean getShowSelectionHandleStart() {
        return ((Boolean) this.showSelectionHandleStart$delegate.getValue()).booleanValue();
    }

    public final void setShowSelectionHandleStart(boolean z) {
        this.showSelectionHandleStart$delegate.setValue(Boolean.valueOf(z));
    }

    public final boolean getShowSelectionHandleEnd() {
        return ((Boolean) this.showSelectionHandleEnd$delegate.getValue()).booleanValue();
    }

    public final void setShowSelectionHandleEnd(boolean z) {
        this.showSelectionHandleEnd$delegate.setValue(Boolean.valueOf(z));
    }

    public final boolean getShowCursorHandle() {
        return ((Boolean) this.showCursorHandle$delegate.getValue()).booleanValue();
    }

    public final void setShowCursorHandle(boolean z) {
        this.showCursorHandle$delegate.setValue(Boolean.valueOf(z));
    }

    public final boolean isLayoutResultStale() {
        return this.isLayoutResultStale;
    }

    public final boolean isInTouchMode() {
        return ((Boolean) this.isInTouchMode$delegate.getValue()).booleanValue();
    }

    public final void setInTouchMode(boolean z) {
        this.isInTouchMode$delegate.setValue(Boolean.valueOf(z));
    }

    public final Function1 getOnValueChange() {
        return this.onValueChange;
    }

    public final Function1 getOnImeActionPerformed() {
        return this.onImeActionPerformed;
    }

    public final Paint getHighlightPaint() {
        return this.highlightPaint;
    }

    /* JADX INFO: renamed from: getSelectionBackgroundColor-0d7_KjU, reason: not valid java name */
    public final long m393getSelectionBackgroundColor0d7_KjU() {
        return this.selectionBackgroundColor;
    }

    /* JADX INFO: renamed from: getSelectionPreviewHighlightRange-d9O1mEE, reason: not valid java name */
    public final long m394getSelectionPreviewHighlightRanged9O1mEE() {
        return ((TextRange) this.selectionPreviewHighlightRange$delegate.getValue()).m2121unboximpl();
    }

    /* JADX INFO: renamed from: setSelectionPreviewHighlightRange-5zc-tL8, reason: not valid java name */
    public final void m397setSelectionPreviewHighlightRange5zctL8(long j) {
        this.selectionPreviewHighlightRange$delegate.setValue(TextRange.m2105boximpl(j));
    }

    /* JADX INFO: renamed from: getDeletionPreviewHighlightRange-d9O1mEE, reason: not valid java name */
    public final long m391getDeletionPreviewHighlightRanged9O1mEE() {
        return ((TextRange) this.deletionPreviewHighlightRange$delegate.getValue()).m2121unboximpl();
    }

    /* JADX INFO: renamed from: setDeletionPreviewHighlightRange-5zc-tL8, reason: not valid java name */
    public final void m395setDeletionPreviewHighlightRange5zctL8(long j) {
        this.deletionPreviewHighlightRange$delegate.setValue(TextRange.m2105boximpl(j));
    }

    public final boolean hasHighlight() {
        return (TextRange.m2111getCollapsedimpl(m394getSelectionPreviewHighlightRanged9O1mEE()) && TextRange.m2111getCollapsedimpl(m391getDeletionPreviewHighlightRanged9O1mEE())) ? false : true;
    }

    /* JADX INFO: renamed from: update-fnh65Uc, reason: not valid java name */
    public final void m398updatefnh65Uc(AnnotatedString annotatedString, AnnotatedString annotatedString2, TextStyle textStyle, boolean z, Density density, FontFamily.Resolver resolver, Function1 function1, KeyboardActions keyboardActions, FocusManager focusManager, long j) {
        this.onValueChangeOriginal = function1;
        this.selectionBackgroundColor = j;
        KeyboardActionRunner keyboardActionRunner = this.keyboardActionRunner;
        keyboardActionRunner.setKeyboardActions(keyboardActions);
        keyboardActionRunner.setFocusManager(focusManager);
        this.untransformedText = annotatedString;
        TextDelegate textDelegateM431updateTextDelegaterm0N8CA = TextDelegateKt.m431updateTextDelegaterm0N8CA(this.textDelegate, annotatedString2, textStyle, density, resolver, (448 & 32) != 0 ? true : z, (448 & 64) != 0 ? TextOverflow.Companion.m2375getClipgIe3tQ8() : 0, (448 & 128) != 0 ? Integer.MAX_VALUE : 0, (448 & 256) != 0 ? 1 : 0, CollectionsKt.emptyList());
        if (this.textDelegate != textDelegateM431updateTextDelegaterm0N8CA) {
            this.isLayoutResultStale = true;
        }
        this.textDelegate = textDelegateM431updateTextDelegaterm0N8CA;
    }
}
