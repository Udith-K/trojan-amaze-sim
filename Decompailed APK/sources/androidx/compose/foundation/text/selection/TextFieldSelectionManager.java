package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.HandleState;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextDelegate;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldCursorKt;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.UndoManager;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextFieldValueKt;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextFieldSelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextFieldSelectionManager {
    private ClipboardManager clipboardManager;
    private final MutableState currentDragPosition$delegate;
    private Integer dragBeginOffsetInText;
    private long dragBeginPosition;
    private long dragTotalDistance;
    private final MutableState draggingHandle$delegate;
    private final MutableState editable$delegate;
    private final MutableState enabled$delegate;
    private FocusRequester focusRequester;
    private HapticFeedback hapticFeedBack;
    private final MouseSelectionObserver mouseSelectionObserver;
    private TextFieldValue oldValue;
    private int previousRawDragOffset;
    private SelectionLayout previousSelectionLayout;
    private LegacyTextFieldState state;
    private TextToolbar textToolbar;
    private final TextDragObserver touchSelectionObserver;
    private final UndoManager undoManager;
    private OffsetMapping offsetMapping = ValidatingOffsetMappingKt.getValidatingEmptyOffsetMappingIdentity();
    private Function1 onValueChange = new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$onValueChange$1
        public final void invoke(TextFieldValue textFieldValue) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((TextFieldValue) obj);
            return Unit.INSTANCE;
        }
    };
    private final MutableState value$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue((String) null, 0, (TextRange) null, 7, (DefaultConstructorMarker) null), null, 2, null);
    private VisualTransformation visualTransformation = VisualTransformation.Companion.getNone();

    public TextFieldSelectionManager(UndoManager undoManager) {
        this.undoManager = undoManager;
        Boolean bool = Boolean.TRUE;
        this.editable$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.enabled$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        Offset.Companion companion = Offset.Companion;
        this.dragBeginPosition = companion.m1171getZeroF1C5BW0();
        this.dragTotalDistance = companion.m1171getZeroF1C5BW0();
        this.draggingHandle$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.currentDragPosition$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.previousRawDragOffset = -1;
        this.oldValue = new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null);
        this.touchSelectionObserver = new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$touchSelectionObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo433onDownk4lQ0M(long j) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-k-4lQ0M */
            public void mo435onStartk4lQ0M(long j) {
                TextLayoutResultProxy layoutResult;
                TextLayoutResultProxy layoutResult2;
                if (this.this$0.getEnabled() && this.this$0.getDraggingHandle() == null) {
                    this.this$0.setDraggingHandle(Handle.SelectionEnd);
                    this.this$0.previousRawDragOffset = -1;
                    this.this$0.hideSelectionToolbar$foundation_release();
                    LegacyTextFieldState state$foundation_release = this.this$0.getState$foundation_release();
                    if (state$foundation_release == null || (layoutResult2 = state$foundation_release.getLayoutResult()) == null || !layoutResult2.m461isPositionOnTextk4lQ0M(j)) {
                        LegacyTextFieldState state$foundation_release2 = this.this$0.getState$foundation_release();
                        if (state$foundation_release2 != null && (layoutResult = state$foundation_release2.getLayoutResult()) != null) {
                            TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                            int iTransformedToOriginal = textFieldSelectionManager.getOffsetMapping$foundation_release().transformedToOriginal(TextLayoutResultProxy.m459getOffsetForPosition3MmeM6k$default(layoutResult, j, false, 2, null));
                            TextFieldValue textFieldValueM551createTextFieldValueFDrldGo = textFieldSelectionManager.m551createTextFieldValueFDrldGo(textFieldSelectionManager.getValue$foundation_release().getAnnotatedString(), TextRangeKt.TextRange(iTransformedToOriginal, iTransformedToOriginal));
                            textFieldSelectionManager.enterSelectionMode$foundation_release(false);
                            HapticFeedback hapticFeedBack = textFieldSelectionManager.getHapticFeedBack();
                            if (hapticFeedBack != null) {
                                hapticFeedBack.mo1578performHapticFeedbackCdsT49E(HapticFeedbackType.Companion.m1582getTextHandleMove5zf0vsI());
                            }
                            textFieldSelectionManager.getOnValueChange$foundation_release().invoke(textFieldValueM551createTextFieldValueFDrldGo);
                        }
                    } else {
                        if (this.this$0.getValue$foundation_release().getText().length() == 0) {
                            return;
                        }
                        this.this$0.enterSelectionMode$foundation_release(false);
                        TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                        this.this$0.dragBeginOffsetInText = Integer.valueOf(TextRange.m2117getStartimpl(textFieldSelectionManager2.m554updateSelection8UEBfa8(TextFieldValue.m2236copy3r_uNRQ$default(textFieldSelectionManager2.getValue$foundation_release(), null, TextRange.Companion.m2122getZerod9O1mEE(), null, 5, null), j, true, false, SelectionAdjustment.Companion.getWord(), true)));
                    }
                    this.this$0.setHandleState(HandleState.None);
                    this.this$0.dragBeginPosition = j;
                    TextFieldSelectionManager textFieldSelectionManager3 = this.this$0;
                    textFieldSelectionManager3.m553setCurrentDragPosition_kEHs6E(Offset.m1150boximpl(textFieldSelectionManager3.dragBeginPosition));
                    this.this$0.dragTotalDistance = Offset.Companion.m1171getZeroF1C5BW0();
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:20:0x00b6  */
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void mo434onDragk4lQ0M(long r10) {
                /*
                    Method dump skipped, instruction units count: 264
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager$touchSelectionObserver$1.mo434onDragk4lQ0M(long):void");
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                onEnd();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                onEnd();
            }

            private final void onEnd() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m553setCurrentDragPosition_kEHs6E(null);
                this.this$0.updateFloatingToolbar(true);
                this.this$0.dragBeginOffsetInText = null;
                boolean zM2111getCollapsedimpl = TextRange.m2111getCollapsedimpl(this.this$0.getValue$foundation_release().m2239getSelectiond9O1mEE());
                this.this$0.setHandleState(zM2111getCollapsedimpl ? HandleState.Cursor : HandleState.Selection);
                LegacyTextFieldState state$foundation_release = this.this$0.getState$foundation_release();
                if (state$foundation_release != null) {
                    state$foundation_release.setShowSelectionHandleStart(!zM2111getCollapsedimpl && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this.this$0, true));
                }
                LegacyTextFieldState state$foundation_release2 = this.this$0.getState$foundation_release();
                if (state$foundation_release2 != null) {
                    state$foundation_release2.setShowSelectionHandleEnd(!zM2111getCollapsedimpl && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this.this$0, false));
                }
                LegacyTextFieldState state$foundation_release3 = this.this$0.getState$foundation_release();
                if (state$foundation_release3 == null) {
                    return;
                }
                state$foundation_release3.setShowCursorHandle(zM2111getCollapsedimpl && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this.this$0, true));
            }
        };
        this.mouseSelectionObserver = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$mouseSelectionObserver$1
            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            public void onDragDone() {
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtend-k-4lQ0M */
            public boolean mo536onExtendk4lQ0M(long j) {
                LegacyTextFieldState state$foundation_release = this.this$0.getState$foundation_release();
                if (state$foundation_release == null || state$foundation_release.getLayoutResult() == null || !this.this$0.getEnabled()) {
                    return false;
                }
                this.this$0.previousRawDragOffset = -1;
                updateMouseSelection(this.this$0.getValue$foundation_release(), j, false, SelectionAdjustment.Companion.getNone());
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M */
            public boolean mo537onExtendDragk4lQ0M(long j) {
                LegacyTextFieldState state$foundation_release;
                if (!this.this$0.getEnabled() || this.this$0.getValue$foundation_release().getText().length() == 0 || (state$foundation_release = this.this$0.getState$foundation_release()) == null || state$foundation_release.getLayoutResult() == null) {
                    return false;
                }
                updateMouseSelection(this.this$0.getValue$foundation_release(), j, false, SelectionAdjustment.Companion.getNone());
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k */
            public boolean mo538onStart3MmeM6k(long j, SelectionAdjustment selectionAdjustment) {
                LegacyTextFieldState state$foundation_release;
                if (!this.this$0.getEnabled() || this.this$0.getValue$foundation_release().getText().length() == 0 || (state$foundation_release = this.this$0.getState$foundation_release()) == null || state$foundation_release.getLayoutResult() == null) {
                    return false;
                }
                FocusRequester focusRequester = this.this$0.getFocusRequester();
                if (focusRequester != null) {
                    focusRequester.requestFocus();
                }
                this.this$0.dragBeginPosition = j;
                this.this$0.previousRawDragOffset = -1;
                TextFieldSelectionManager.enterSelectionMode$foundation_release$default(this.this$0, false, 1, null);
                updateMouseSelection(this.this$0.getValue$foundation_release(), this.this$0.dragBeginPosition, true, selectionAdjustment);
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onDrag-3MmeM6k */
            public boolean mo535onDrag3MmeM6k(long j, SelectionAdjustment selectionAdjustment) {
                LegacyTextFieldState state$foundation_release;
                if (!this.this$0.getEnabled() || this.this$0.getValue$foundation_release().getText().length() == 0 || (state$foundation_release = this.this$0.getState$foundation_release()) == null || state$foundation_release.getLayoutResult() == null) {
                    return false;
                }
                updateMouseSelection(this.this$0.getValue$foundation_release(), j, false, selectionAdjustment);
                return true;
            }

            public final void updateMouseSelection(TextFieldValue textFieldValue, long j, boolean z, SelectionAdjustment selectionAdjustment) {
                this.this$0.setHandleState(TextRange.m2111getCollapsedimpl(this.this$0.m554updateSelection8UEBfa8(textFieldValue, j, z, false, selectionAdjustment, false)) ? HandleState.Cursor : HandleState.Selection);
            }
        };
    }

    public final OffsetMapping getOffsetMapping$foundation_release() {
        return this.offsetMapping;
    }

    public final void setOffsetMapping$foundation_release(OffsetMapping offsetMapping) {
        this.offsetMapping = offsetMapping;
    }

    public final Function1 getOnValueChange$foundation_release() {
        return this.onValueChange;
    }

    public final void setOnValueChange$foundation_release(Function1 function1) {
        this.onValueChange = function1;
    }

    public final LegacyTextFieldState getState$foundation_release() {
        return this.state;
    }

    public final void setState$foundation_release(LegacyTextFieldState legacyTextFieldState) {
        this.state = legacyTextFieldState;
    }

    public final TextFieldValue getValue$foundation_release() {
        return (TextFieldValue) this.value$delegate.getValue();
    }

    public final void setValue$foundation_release(TextFieldValue textFieldValue) {
        this.value$delegate.setValue(textFieldValue);
    }

    public final AnnotatedString getTransformedText$foundation_release() {
        TextDelegate textDelegate;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState == null || (textDelegate = legacyTextFieldState.getTextDelegate()) == null) {
            return null;
        }
        return textDelegate.getText();
    }

    public final VisualTransformation getVisualTransformation$foundation_release() {
        return this.visualTransformation;
    }

    public final void setVisualTransformation$foundation_release(VisualTransformation visualTransformation) {
        this.visualTransformation = visualTransformation;
    }

    public final ClipboardManager getClipboardManager$foundation_release() {
        return this.clipboardManager;
    }

    public final void setClipboardManager$foundation_release(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    public final void setTextToolbar(TextToolbar textToolbar) {
        this.textToolbar = textToolbar;
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    public final boolean getEditable() {
        return ((Boolean) this.editable$delegate.getValue()).booleanValue();
    }

    public final void setEditable(boolean z) {
        this.editable$delegate.setValue(Boolean.valueOf(z));
    }

    public final boolean getEnabled() {
        return ((Boolean) this.enabled$delegate.getValue()).booleanValue();
    }

    public final void setEnabled(boolean z) {
        this.enabled$delegate.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDraggingHandle(Handle handle) {
        this.draggingHandle$delegate.setValue(handle);
    }

    public final Handle getDraggingHandle() {
        return (Handle) this.draggingHandle$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCurrentDragPosition-_kEHs6E, reason: not valid java name */
    public final void m553setCurrentDragPosition_kEHs6E(Offset offset) {
        this.currentDragPosition$delegate.setValue(offset);
    }

    /* JADX INFO: renamed from: getCurrentDragPosition-_m7T9-E, reason: not valid java name */
    public final Offset m556getCurrentDragPosition_m7T9E() {
        return (Offset) this.currentDragPosition$delegate.getValue();
    }

    public final TextDragObserver getTouchSelectionObserver$foundation_release() {
        return this.touchSelectionObserver;
    }

    public final MouseSelectionObserver getMouseSelectionObserver$foundation_release() {
        return this.mouseSelectionObserver;
    }

    public final TextDragObserver handleDragObserver$foundation_release(final boolean z) {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$handleDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-k-4lQ0M */
            public void mo435onStartk4lQ0M(long j) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo433onDownk4lQ0M(long j) {
                TextLayoutResultProxy layoutResult;
                this.this$0.setDraggingHandle(z ? Handle.SelectionStart : Handle.SelectionEnd);
                long jM540getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m540getAdjustedCoordinatesk4lQ0M(this.this$0.m558getHandlePositiontuRUvjQ$foundation_release(z));
                LegacyTextFieldState state$foundation_release = this.this$0.getState$foundation_release();
                if (state$foundation_release == null || (layoutResult = state$foundation_release.getLayoutResult()) == null) {
                    return;
                }
                long jM463translateInnerToDecorationCoordinatesMKHz9U$foundation_release = layoutResult.m463translateInnerToDecorationCoordinatesMKHz9U$foundation_release(jM540getAdjustedCoordinatesk4lQ0M);
                this.this$0.dragBeginPosition = jM463translateInnerToDecorationCoordinatesMKHz9U$foundation_release;
                this.this$0.m553setCurrentDragPosition_kEHs6E(Offset.m1150boximpl(jM463translateInnerToDecorationCoordinatesMKHz9U$foundation_release));
                this.this$0.dragTotalDistance = Offset.Companion.m1171getZeroF1C5BW0();
                this.this$0.previousRawDragOffset = -1;
                LegacyTextFieldState state$foundation_release2 = this.this$0.getState$foundation_release();
                if (state$foundation_release2 != null) {
                    state$foundation_release2.setInTouchMode(true);
                }
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m553setCurrentDragPosition_kEHs6E(null);
                this.this$0.updateFloatingToolbar(true);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo434onDragk4lQ0M(long j) {
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                textFieldSelectionManager.dragTotalDistance = Offset.m1164plusMKHz9U(textFieldSelectionManager.dragTotalDistance, j);
                TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                textFieldSelectionManager2.m553setCurrentDragPosition_kEHs6E(Offset.m1150boximpl(Offset.m1164plusMKHz9U(textFieldSelectionManager2.dragBeginPosition, this.this$0.dragTotalDistance)));
                TextFieldSelectionManager textFieldSelectionManager3 = this.this$0;
                TextFieldValue value$foundation_release = textFieldSelectionManager3.getValue$foundation_release();
                Offset offsetM556getCurrentDragPosition_m7T9E = this.this$0.m556getCurrentDragPosition_m7T9E();
                Intrinsics.checkNotNull(offsetM556getCurrentDragPosition_m7T9E);
                textFieldSelectionManager3.m554updateSelection8UEBfa8(value$foundation_release, offsetM556getCurrentDragPosition_m7T9E.m1168unboximpl(), false, z, SelectionAdjustment.Companion.getCharacterWithWordAccelerate(), true);
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m553setCurrentDragPosition_kEHs6E(null);
                this.this$0.updateFloatingToolbar(true);
            }
        };
    }

    public final TextDragObserver cursorDragObserver$foundation_release() {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$cursorDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo433onDownk4lQ0M(long j) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m553setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-k-4lQ0M */
            public void mo435onStartk4lQ0M(long j) {
                TextLayoutResultProxy layoutResult;
                long jM540getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m540getAdjustedCoordinatesk4lQ0M(this.this$0.m558getHandlePositiontuRUvjQ$foundation_release(true));
                LegacyTextFieldState state$foundation_release = this.this$0.getState$foundation_release();
                if (state$foundation_release == null || (layoutResult = state$foundation_release.getLayoutResult()) == null) {
                    return;
                }
                long jM463translateInnerToDecorationCoordinatesMKHz9U$foundation_release = layoutResult.m463translateInnerToDecorationCoordinatesMKHz9U$foundation_release(jM540getAdjustedCoordinatesk4lQ0M);
                this.this$0.dragBeginPosition = jM463translateInnerToDecorationCoordinatesMKHz9U$foundation_release;
                this.this$0.m553setCurrentDragPosition_kEHs6E(Offset.m1150boximpl(jM463translateInnerToDecorationCoordinatesMKHz9U$foundation_release));
                this.this$0.dragTotalDistance = Offset.Companion.m1171getZeroF1C5BW0();
                this.this$0.setDraggingHandle(Handle.Cursor);
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo434onDragk4lQ0M(long j) {
                TextLayoutResultProxy layoutResult;
                HapticFeedback hapticFeedBack;
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                textFieldSelectionManager.dragTotalDistance = Offset.m1164plusMKHz9U(textFieldSelectionManager.dragTotalDistance, j);
                LegacyTextFieldState state$foundation_release = this.this$0.getState$foundation_release();
                if (state$foundation_release == null || (layoutResult = state$foundation_release.getLayoutResult()) == null) {
                    return;
                }
                TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                textFieldSelectionManager2.m553setCurrentDragPosition_kEHs6E(Offset.m1150boximpl(Offset.m1164plusMKHz9U(textFieldSelectionManager2.dragBeginPosition, textFieldSelectionManager2.dragTotalDistance)));
                OffsetMapping offsetMapping$foundation_release = textFieldSelectionManager2.getOffsetMapping$foundation_release();
                Offset offsetM556getCurrentDragPosition_m7T9E = textFieldSelectionManager2.m556getCurrentDragPosition_m7T9E();
                Intrinsics.checkNotNull(offsetM556getCurrentDragPosition_m7T9E);
                int iTransformedToOriginal = offsetMapping$foundation_release.transformedToOriginal(TextLayoutResultProxy.m459getOffsetForPosition3MmeM6k$default(layoutResult, offsetM556getCurrentDragPosition_m7T9E.m1168unboximpl(), false, 2, null));
                long jTextRange = TextRangeKt.TextRange(iTransformedToOriginal, iTransformedToOriginal);
                if (TextRange.m2110equalsimpl0(jTextRange, textFieldSelectionManager2.getValue$foundation_release().m2239getSelectiond9O1mEE())) {
                    return;
                }
                LegacyTextFieldState state$foundation_release2 = textFieldSelectionManager2.getState$foundation_release();
                if ((state$foundation_release2 == null || state$foundation_release2.isInTouchMode()) && (hapticFeedBack = textFieldSelectionManager2.getHapticFeedBack()) != null) {
                    hapticFeedBack.mo1578performHapticFeedbackCdsT49E(HapticFeedbackType.Companion.m1582getTextHandleMove5zf0vsI());
                }
                textFieldSelectionManager2.getOnValueChange$foundation_release().invoke(textFieldSelectionManager2.m551createTextFieldValueFDrldGo(textFieldSelectionManager2.getValue$foundation_release().getAnnotatedString(), jTextRange));
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m553setCurrentDragPosition_kEHs6E(null);
            }
        };
    }

    public static /* synthetic */ void enterSelectionMode$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textFieldSelectionManager.enterSelectionMode$foundation_release(z);
    }

    public final void enterSelectionMode$foundation_release(boolean z) {
        FocusRequester focusRequester;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null && !legacyTextFieldState.getHasFocus() && (focusRequester = this.focusRequester) != null) {
            focusRequester.requestFocus();
        }
        this.oldValue = getValue$foundation_release();
        updateFloatingToolbar(z);
        setHandleState(HandleState.Selection);
    }

    public final void exitSelectionMode$foundation_release() {
        updateFloatingToolbar(false);
        setHandleState(HandleState.None);
    }

    /* JADX INFO: renamed from: deselect-_kEHs6E$foundation_release$default, reason: not valid java name */
    public static /* synthetic */ void m552deselect_kEHs6E$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, Offset offset, int i, Object obj) {
        if ((i & 1) != 0) {
            offset = null;
        }
        textFieldSelectionManager.m555deselect_kEHs6E$foundation_release(offset);
    }

    /* JADX INFO: renamed from: deselect-_kEHs6E$foundation_release, reason: not valid java name */
    public final void m555deselect_kEHs6E$foundation_release(Offset offset) {
        int iM2114getMaximpl;
        if (!TextRange.m2111getCollapsedimpl(getValue$foundation_release().m2239getSelectiond9O1mEE())) {
            LegacyTextFieldState legacyTextFieldState = this.state;
            TextLayoutResultProxy layoutResult = legacyTextFieldState != null ? legacyTextFieldState.getLayoutResult() : null;
            if (offset != null && layoutResult != null) {
                iM2114getMaximpl = this.offsetMapping.transformedToOriginal(TextLayoutResultProxy.m459getOffsetForPosition3MmeM6k$default(layoutResult, offset.m1168unboximpl(), false, 2, null));
            } else {
                iM2114getMaximpl = TextRange.m2114getMaximpl(getValue$foundation_release().m2239getSelectiond9O1mEE());
            }
            this.onValueChange.invoke(TextFieldValue.m2236copy3r_uNRQ$default(getValue$foundation_release(), null, TextRangeKt.TextRange(iM2114getMaximpl), null, 5, null));
        }
        setHandleState((offset == null || getValue$foundation_release().getText().length() <= 0) ? HandleState.None : HandleState.Cursor);
        updateFloatingToolbar(false);
    }

    /* JADX INFO: renamed from: setSelectionPreviewHighlight-5zc-tL8$foundation_release, reason: not valid java name */
    public final void m560setSelectionPreviewHighlight5zctL8$foundation_release(long j) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m397setSelectionPreviewHighlightRange5zctL8(j);
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 != null) {
            legacyTextFieldState2.m395setDeletionPreviewHighlightRange5zctL8(TextRange.Companion.m2122getZerod9O1mEE());
        }
        if (TextRange.m2111getCollapsedimpl(j)) {
            return;
        }
        exitSelectionMode$foundation_release();
    }

    /* JADX INFO: renamed from: setDeletionPreviewHighlight-5zc-tL8$foundation_release, reason: not valid java name */
    public final void m559setDeletionPreviewHighlight5zctL8$foundation_release(long j) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m395setDeletionPreviewHighlightRange5zctL8(j);
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 != null) {
            legacyTextFieldState2.m397setSelectionPreviewHighlightRange5zctL8(TextRange.Companion.m2122getZerod9O1mEE());
        }
        if (TextRange.m2111getCollapsedimpl(j)) {
            return;
        }
        exitSelectionMode$foundation_release();
    }

    public final void clearPreviewHighlight$foundation_release() {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m395setDeletionPreviewHighlightRange5zctL8(TextRange.Companion.m2122getZerod9O1mEE());
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 == null) {
            return;
        }
        legacyTextFieldState2.m397setSelectionPreviewHighlightRange5zctL8(TextRange.Companion.m2122getZerod9O1mEE());
    }

    public static /* synthetic */ void copy$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textFieldSelectionManager.copy$foundation_release(z);
    }

    public final void copy$foundation_release(boolean z) {
        if (TextRange.m2111getCollapsedimpl(getValue$foundation_release().m2239getSelectiond9O1mEE())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(TextFieldValueKt.getSelectedText(getValue$foundation_release()));
        }
        if (z) {
            int iM2114getMaximpl = TextRange.m2114getMaximpl(getValue$foundation_release().m2239getSelectiond9O1mEE());
            this.onValueChange.invoke(m551createTextFieldValueFDrldGo(getValue$foundation_release().getAnnotatedString(), TextRangeKt.TextRange(iM2114getMaximpl, iM2114getMaximpl)));
            setHandleState(HandleState.None);
        }
    }

    public final void paste$foundation_release() {
        AnnotatedString text;
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager == null || (text = clipboardManager.getText()) == null) {
            return;
        }
        AnnotatedString annotatedStringPlus = TextFieldValueKt.getTextBeforeSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()).plus(text).plus(TextFieldValueKt.getTextAfterSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()));
        int iM2115getMinimpl = TextRange.m2115getMinimpl(getValue$foundation_release().m2239getSelectiond9O1mEE()) + text.length();
        this.onValueChange.invoke(m551createTextFieldValueFDrldGo(annotatedStringPlus, TextRangeKt.TextRange(iM2115getMinimpl, iM2115getMinimpl)));
        setHandleState(HandleState.None);
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
    }

    public final void cut$foundation_release() {
        if (TextRange.m2111getCollapsedimpl(getValue$foundation_release().m2239getSelectiond9O1mEE())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(TextFieldValueKt.getSelectedText(getValue$foundation_release()));
        }
        AnnotatedString annotatedStringPlus = TextFieldValueKt.getTextBeforeSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()).plus(TextFieldValueKt.getTextAfterSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()));
        int iM2115getMinimpl = TextRange.m2115getMinimpl(getValue$foundation_release().m2239getSelectiond9O1mEE());
        this.onValueChange.invoke(m551createTextFieldValueFDrldGo(annotatedStringPlus, TextRangeKt.TextRange(iM2115getMinimpl, iM2115getMinimpl)));
        setHandleState(HandleState.None);
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
    }

    public final void selectAll$foundation_release() {
        TextFieldValue textFieldValueM551createTextFieldValueFDrldGo = m551createTextFieldValueFDrldGo(getValue$foundation_release().getAnnotatedString(), TextRangeKt.TextRange(0, getValue$foundation_release().getText().length()));
        this.onValueChange.invoke(textFieldValueM551createTextFieldValueFDrldGo);
        this.oldValue = TextFieldValue.m2236copy3r_uNRQ$default(this.oldValue, null, textFieldValueM551createTextFieldValueFDrldGo.m2239getSelectiond9O1mEE(), null, 5, null);
        enterSelectionMode$foundation_release(true);
    }

    /* JADX INFO: renamed from: getHandlePosition-tuRUvjQ$foundation_release, reason: not valid java name */
    public final long m558getHandlePositiontuRUvjQ$foundation_release(boolean z) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState == null || (layoutResult = legacyTextFieldState.getLayoutResult()) == null || (value = layoutResult.getValue()) == null) {
            return Offset.Companion.m1170getUnspecifiedF1C5BW0();
        }
        AnnotatedString transformedText$foundation_release = getTransformedText$foundation_release();
        if (transformedText$foundation_release == null) {
            return Offset.Companion.m1170getUnspecifiedF1C5BW0();
        }
        if (!Intrinsics.areEqual(transformedText$foundation_release.getText(), value.getLayoutInput().getText().getText())) {
            return Offset.Companion.m1170getUnspecifiedF1C5BW0();
        }
        long jM2239getSelectiond9O1mEE = getValue$foundation_release().m2239getSelectiond9O1mEE();
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(value, this.offsetMapping.originalToTransformed(z ? TextRange.m2117getStartimpl(jM2239getSelectiond9O1mEE) : TextRange.m2112getEndimpl(jM2239getSelectiond9O1mEE)), z, TextRange.m2116getReversedimpl(getValue$foundation_release().m2239getSelectiond9O1mEE()));
    }

    /* JADX INFO: renamed from: getCursorPosition-tuRUvjQ$foundation_release, reason: not valid java name */
    public final long m557getCursorPositiontuRUvjQ$foundation_release(Density density) {
        int iOriginalToTransformed = this.offsetMapping.originalToTransformed(TextRange.m2117getStartimpl(getValue$foundation_release().m2239getSelectiond9O1mEE()));
        LegacyTextFieldState legacyTextFieldState = this.state;
        TextLayoutResultProxy layoutResult = legacyTextFieldState != null ? legacyTextFieldState.getLayoutResult() : null;
        Intrinsics.checkNotNull(layoutResult);
        TextLayoutResult value = layoutResult.getValue();
        Rect cursorRect = value.getCursorRect(RangesKt.coerceIn(iOriginalToTransformed, 0, value.getLayoutInput().getText().length()));
        return OffsetKt.Offset(cursorRect.getLeft() + (density.mo210toPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness()) / 2), cursorRect.getBottom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFloatingToolbar(boolean z) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.setShowFloatingToolbar(z);
        }
        if (z) {
            showSelectionToolbar$foundation_release();
        } else {
            hideSelectionToolbar$foundation_release();
        }
    }

    public final void showSelectionToolbar$foundation_release() {
        ClipboardManager clipboardManager;
        if (getEnabled()) {
            LegacyTextFieldState legacyTextFieldState = this.state;
            if (legacyTextFieldState == null || legacyTextFieldState.isInTouchMode()) {
                Function0 function0 = !TextRange.m2111getCollapsedimpl(getValue$foundation_release().m2239getSelectiond9O1mEE()) ? new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$copy$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m561invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m561invoke() {
                        TextFieldSelectionManager.copy$foundation_release$default(this.this$0, false, 1, null);
                        this.this$0.hideSelectionToolbar$foundation_release();
                    }
                } : null;
                Function0 function02 = (TextRange.m2111getCollapsedimpl(getValue$foundation_release().m2239getSelectiond9O1mEE()) || !getEditable()) ? null : new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$cut$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m562invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m562invoke() {
                        this.this$0.cut$foundation_release();
                        this.this$0.hideSelectionToolbar$foundation_release();
                    }
                };
                Function0 function03 = (getEditable() && (clipboardManager = this.clipboardManager) != null && clipboardManager.hasText()) ? new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$paste$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m563invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m563invoke() {
                        this.this$0.paste$foundation_release();
                        this.this$0.hideSelectionToolbar$foundation_release();
                    }
                } : null;
                Function0 function04 = TextRange.m2113getLengthimpl(getValue$foundation_release().m2239getSelectiond9O1mEE()) != getValue$foundation_release().getText().length() ? new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbar$selectAll$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m564invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m564invoke() {
                        this.this$0.selectAll$foundation_release();
                    }
                } : null;
                TextToolbar textToolbar = this.textToolbar;
                if (textToolbar != null) {
                    textToolbar.showMenu(getContentRect(), function0, function03, function02, function04);
                }
            }
        }
    }

    public final void hideSelectionToolbar$foundation_release() {
        TextToolbar textToolbar;
        TextToolbar textToolbar2 = this.textToolbar;
        if ((textToolbar2 != null ? textToolbar2.getStatus() : null) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
            return;
        }
        textToolbar.hide();
    }

    public final boolean isTextChanged$foundation_release() {
        return !Intrinsics.areEqual(this.oldValue.getText(), getValue$foundation_release().getText());
    }

    private final Rect getContentRect() {
        float fM1160getYimpl;
        LayoutCoordinates layoutCoordinates;
        TextLayoutResult value;
        Rect cursorRect;
        LayoutCoordinates layoutCoordinates2;
        TextLayoutResult value2;
        Rect cursorRect2;
        LayoutCoordinates layoutCoordinates3;
        LayoutCoordinates layoutCoordinates4;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            if (legacyTextFieldState.isLayoutResultStale()) {
                legacyTextFieldState = null;
            }
            if (legacyTextFieldState != null) {
                int iOriginalToTransformed = this.offsetMapping.originalToTransformed(TextRange.m2117getStartimpl(getValue$foundation_release().m2239getSelectiond9O1mEE()));
                int iOriginalToTransformed2 = this.offsetMapping.originalToTransformed(TextRange.m2112getEndimpl(getValue$foundation_release().m2239getSelectiond9O1mEE()));
                LegacyTextFieldState legacyTextFieldState2 = this.state;
                long jM1171getZeroF1C5BW0 = (legacyTextFieldState2 == null || (layoutCoordinates4 = legacyTextFieldState2.getLayoutCoordinates()) == null) ? Offset.Companion.m1171getZeroF1C5BW0() : layoutCoordinates4.mo1748localToRootMKHz9U(m558getHandlePositiontuRUvjQ$foundation_release(true));
                LegacyTextFieldState legacyTextFieldState3 = this.state;
                long jM1171getZeroF1C5BW02 = (legacyTextFieldState3 == null || (layoutCoordinates3 = legacyTextFieldState3.getLayoutCoordinates()) == null) ? Offset.Companion.m1171getZeroF1C5BW0() : layoutCoordinates3.mo1748localToRootMKHz9U(m558getHandlePositiontuRUvjQ$foundation_release(false));
                LegacyTextFieldState legacyTextFieldState4 = this.state;
                float fM1160getYimpl2 = 0.0f;
                if (legacyTextFieldState4 == null || (layoutCoordinates2 = legacyTextFieldState4.getLayoutCoordinates()) == null) {
                    fM1160getYimpl = 0.0f;
                } else {
                    TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
                    fM1160getYimpl = Offset.m1160getYimpl(layoutCoordinates2.mo1748localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult == null || (value2 = layoutResult.getValue()) == null || (cursorRect2 = value2.getCursorRect(iOriginalToTransformed)) == null) ? 0.0f : cursorRect2.getTop())));
                }
                LegacyTextFieldState legacyTextFieldState5 = this.state;
                if (legacyTextFieldState5 != null && (layoutCoordinates = legacyTextFieldState5.getLayoutCoordinates()) != null) {
                    TextLayoutResultProxy layoutResult2 = legacyTextFieldState.getLayoutResult();
                    fM1160getYimpl2 = Offset.m1160getYimpl(layoutCoordinates.mo1748localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult2 == null || (value = layoutResult2.getValue()) == null || (cursorRect = value.getCursorRect(iOriginalToTransformed2)) == null) ? 0.0f : cursorRect.getTop())));
                }
                return new Rect(Math.min(Offset.m1159getXimpl(jM1171getZeroF1C5BW0), Offset.m1159getXimpl(jM1171getZeroF1C5BW02)), Math.min(fM1160getYimpl, fM1160getYimpl2), Math.max(Offset.m1159getXimpl(jM1171getZeroF1C5BW0), Offset.m1159getXimpl(jM1171getZeroF1C5BW02)), Math.max(Offset.m1160getYimpl(jM1171getZeroF1C5BW0), Offset.m1160getYimpl(jM1171getZeroF1C5BW02)) + (Dp.m2416constructorimpl(25) * legacyTextFieldState.getTextDelegate().getDensity().getDensity()));
            }
        }
        return Rect.Companion.getZero();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSelection-8UEBfa8, reason: not valid java name */
    public final long m554updateSelection8UEBfa8(TextFieldValue textFieldValue, long j, boolean z, boolean z2, SelectionAdjustment selectionAdjustment, boolean z3) {
        TextLayoutResultProxy layoutResult;
        HapticFeedback hapticFeedback;
        int i;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState == null || (layoutResult = legacyTextFieldState.getLayoutResult()) == null) {
            return TextRange.Companion.m2122getZerod9O1mEE();
        }
        long jTextRange = TextRangeKt.TextRange(this.offsetMapping.originalToTransformed(TextRange.m2117getStartimpl(textFieldValue.m2239getSelectiond9O1mEE())), this.offsetMapping.originalToTransformed(TextRange.m2112getEndimpl(textFieldValue.m2239getSelectiond9O1mEE())));
        boolean z4 = false;
        int iM460getOffsetForPosition3MmeM6k = layoutResult.m460getOffsetForPosition3MmeM6k(j, false);
        int iM2117getStartimpl = (z2 || z) ? iM460getOffsetForPosition3MmeM6k : TextRange.m2117getStartimpl(jTextRange);
        int iM2112getEndimpl = (!z2 || z) ? iM460getOffsetForPosition3MmeM6k : TextRange.m2112getEndimpl(jTextRange);
        SelectionLayout selectionLayout = this.previousSelectionLayout;
        int i2 = -1;
        if (!z && selectionLayout != null && (i = this.previousRawDragOffset) != -1) {
            i2 = i;
        }
        SelectionLayout selectionLayoutM541getTextFieldSelectionLayoutRcvTLA = SelectionLayoutKt.m541getTextFieldSelectionLayoutRcvTLA(layoutResult.getValue(), iM2117getStartimpl, iM2112getEndimpl, i2, jTextRange, z, z2);
        if (!selectionLayoutM541getTextFieldSelectionLayoutRcvTLA.shouldRecomputeSelection(selectionLayout)) {
            return textFieldValue.m2239getSelectiond9O1mEE();
        }
        this.previousSelectionLayout = selectionLayoutM541getTextFieldSelectionLayoutRcvTLA;
        this.previousRawDragOffset = iM460getOffsetForPosition3MmeM6k;
        Selection selectionAdjust = selectionAdjustment.adjust(selectionLayoutM541getTextFieldSelectionLayoutRcvTLA);
        long jTextRange2 = TextRangeKt.TextRange(this.offsetMapping.transformedToOriginal(selectionAdjust.getStart().getOffset()), this.offsetMapping.transformedToOriginal(selectionAdjust.getEnd().getOffset()));
        if (TextRange.m2110equalsimpl0(jTextRange2, textFieldValue.m2239getSelectiond9O1mEE())) {
            return textFieldValue.m2239getSelectiond9O1mEE();
        }
        boolean z5 = TextRange.m2116getReversedimpl(jTextRange2) != TextRange.m2116getReversedimpl(textFieldValue.m2239getSelectiond9O1mEE()) && TextRange.m2110equalsimpl0(TextRangeKt.TextRange(TextRange.m2112getEndimpl(jTextRange2), TextRange.m2117getStartimpl(jTextRange2)), textFieldValue.m2239getSelectiond9O1mEE());
        boolean z6 = TextRange.m2111getCollapsedimpl(jTextRange2) && TextRange.m2111getCollapsedimpl(textFieldValue.m2239getSelectiond9O1mEE());
        if (z3 && textFieldValue.getText().length() > 0 && !z5 && !z6 && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.mo1578performHapticFeedbackCdsT49E(HapticFeedbackType.Companion.m1582getTextHandleMove5zf0vsI());
        }
        this.onValueChange.invoke(m551createTextFieldValueFDrldGo(textFieldValue.getAnnotatedString(), jTextRange2));
        if (!z3) {
            updateFloatingToolbar(!TextRange.m2111getCollapsedimpl(jTextRange2));
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 != null) {
            legacyTextFieldState2.setInTouchMode(z3);
        }
        LegacyTextFieldState legacyTextFieldState3 = this.state;
        if (legacyTextFieldState3 != null) {
            legacyTextFieldState3.setShowSelectionHandleStart(!TextRange.m2111getCollapsedimpl(jTextRange2) && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, true));
        }
        LegacyTextFieldState legacyTextFieldState4 = this.state;
        if (legacyTextFieldState4 != null) {
            legacyTextFieldState4.setShowSelectionHandleEnd(!TextRange.m2111getCollapsedimpl(jTextRange2) && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, false));
        }
        LegacyTextFieldState legacyTextFieldState5 = this.state;
        if (legacyTextFieldState5 != null) {
            if (TextRange.m2111getCollapsedimpl(jTextRange2) && TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, true)) {
                z4 = true;
            }
            legacyTextFieldState5.setShowCursorHandle(z4);
        }
        return jTextRange2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setHandleState(HandleState handleState) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            if (legacyTextFieldState.getHandleState() == handleState) {
                legacyTextFieldState = null;
            }
            if (legacyTextFieldState != null) {
                legacyTextFieldState.setHandleState(handleState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createTextFieldValue-FDrldGo, reason: not valid java name */
    public final TextFieldValue m551createTextFieldValueFDrldGo(AnnotatedString annotatedString, long j) {
        return new TextFieldValue(annotatedString, j, (TextRange) null, 4, (DefaultConstructorMarker) null);
    }
}
