package androidx.compose.foundation.text.input.internal;

import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.input.internal.LegacyPlatformTextInputServiceAdapter;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.platform.PlatformTextInputMethodRequest;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: LegacyPlatformTextInputServiceAdapter.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LegacyTextInputMethodRequest implements PlatformTextInputMethodRequest {
    private final LegacyCursorAnchorInfoController cursorAnchorInfoController;
    private Rect focusedRect;
    private final InputMethodManager inputMethodManager;
    private LegacyTextFieldState legacyTextFieldState;
    private TextFieldSelectionManager textFieldSelectionManager;
    private final View view;
    private ViewConfiguration viewConfiguration;
    private Function1 onEditCommand = new Function1() { // from class: androidx.compose.foundation.text.input.internal.LegacyTextInputMethodRequest$onEditCommand$1
        public final void invoke(List list) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((List) obj);
            return Unit.INSTANCE;
        }
    };
    private Function1 onImeActionPerformed = new Function1() { // from class: androidx.compose.foundation.text.input.internal.LegacyTextInputMethodRequest$onImeActionPerformed$1
        /* JADX INFO: renamed from: invoke-KlQnJC8, reason: not valid java name */
        public final void m504invokeKlQnJC8(int i) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m504invokeKlQnJC8(((ImeAction) obj).m2193unboximpl());
            return Unit.INSTANCE;
        }
    };
    private TextFieldValue state = new TextFieldValue("", TextRange.Companion.m2122getZerod9O1mEE(), (TextRange) null, 4, (DefaultConstructorMarker) null);
    private ImeOptions imeOptions = ImeOptions.Companion.getDefault();
    private List ics = new ArrayList();
    private final Lazy baseInputConnection$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.compose.foundation.text.input.internal.LegacyTextInputMethodRequest$baseInputConnection$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final BaseInputConnection invoke() {
            return new BaseInputConnection(this.this$0.getView(), false);
        }
    });

    public LegacyTextInputMethodRequest(View view, Function1 function1, InputMethodManager inputMethodManager) {
        this.view = view;
        this.inputMethodManager = inputMethodManager;
        this.cursorAnchorInfoController = new LegacyCursorAnchorInfoController(function1, inputMethodManager);
    }

    public final View getView() {
        return this.view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BaseInputConnection getBaseInputConnection() {
        return (BaseInputConnection) this.baseInputConnection$delegate.getValue();
    }

    public final void startInput(TextFieldValue textFieldValue, LegacyPlatformTextInputServiceAdapter.LegacyPlatformTextInputNode legacyPlatformTextInputNode, ImeOptions imeOptions, Function1 function1, Function1 function12) {
        this.state = textFieldValue;
        this.imeOptions = imeOptions;
        this.onEditCommand = function1;
        this.onImeActionPerformed = function12;
        this.legacyTextFieldState = legacyPlatformTextInputNode != null ? legacyPlatformTextInputNode.getLegacyTextFieldState() : null;
        this.textFieldSelectionManager = legacyPlatformTextInputNode != null ? legacyPlatformTextInputNode.getTextFieldSelectionManager() : null;
        this.viewConfiguration = legacyPlatformTextInputNode != null ? legacyPlatformTextInputNode.getViewConfiguration() : null;
    }

    @Override // androidx.compose.ui.platform.PlatformTextInputMethodRequest
    public RecordingInputConnection createInputConnection(EditorInfo editorInfo) {
        EditorInfo_androidKt.m476updatepLxbY9I$default(editorInfo, this.state.getText(), this.state.m2239getSelectiond9O1mEE(), this.imeOptions, null, 8, null);
        LegacyPlatformTextInputServiceAdapter_androidKt.updateWithEmojiCompat(editorInfo);
        RecordingInputConnection recordingInputConnection = new RecordingInputConnection(this.state, new InputEventCallback2() { // from class: androidx.compose.foundation.text.input.internal.LegacyTextInputMethodRequest.createInputConnection.1
            @Override // androidx.compose.foundation.text.input.internal.InputEventCallback2
            public void onEditCommands(List list) {
                LegacyTextInputMethodRequest.this.onEditCommand.invoke(list);
            }

            @Override // androidx.compose.foundation.text.input.internal.InputEventCallback2
            /* JADX INFO: renamed from: onImeAction-KlQnJC8 */
            public void mo503onImeActionKlQnJC8(int i) {
                LegacyTextInputMethodRequest.this.onImeActionPerformed.invoke(ImeAction.m2187boximpl(i));
            }

            @Override // androidx.compose.foundation.text.input.internal.InputEventCallback2
            public void onKeyEvent(KeyEvent keyEvent) {
                LegacyTextInputMethodRequest.this.getBaseInputConnection().sendKeyEvent(keyEvent);
            }

            @Override // androidx.compose.foundation.text.input.internal.InputEventCallback2
            public void onRequestCursorAnchorInfo(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
                LegacyTextInputMethodRequest.this.cursorAnchorInfoController.requestUpdate(z, z2, z3, z4, z5, z6);
            }

            @Override // androidx.compose.foundation.text.input.internal.InputEventCallback2
            public void onConnectionClosed(RecordingInputConnection recordingInputConnection2) {
                int size = LegacyTextInputMethodRequest.this.ics.size();
                for (int i = 0; i < size; i++) {
                    if (Intrinsics.areEqual(((WeakReference) LegacyTextInputMethodRequest.this.ics.get(i)).get(), recordingInputConnection2)) {
                        LegacyTextInputMethodRequest.this.ics.remove(i);
                        return;
                    }
                }
            }
        }, this.imeOptions.getAutoCorrect(), this.legacyTextFieldState, this.textFieldSelectionManager, this.viewConfiguration);
        this.ics.add(new WeakReference(recordingInputConnection));
        return recordingInputConnection;
    }

    public final void updateState(TextFieldValue textFieldValue, TextFieldValue textFieldValue2) {
        boolean z = (TextRange.m2110equalsimpl0(this.state.m2239getSelectiond9O1mEE(), textFieldValue2.m2239getSelectiond9O1mEE()) && Intrinsics.areEqual(this.state.m2238getCompositionMzsxiRA(), textFieldValue2.m2238getCompositionMzsxiRA())) ? false : true;
        this.state = textFieldValue2;
        int size = this.ics.size();
        for (int i = 0; i < size; i++) {
            RecordingInputConnection recordingInputConnection = (RecordingInputConnection) ((WeakReference) this.ics.get(i)).get();
            if (recordingInputConnection != null) {
                recordingInputConnection.setTextFieldValue$foundation_release(textFieldValue2);
            }
        }
        this.cursorAnchorInfoController.invalidate();
        if (Intrinsics.areEqual(textFieldValue, textFieldValue2)) {
            if (z) {
                InputMethodManager inputMethodManager = this.inputMethodManager;
                int iM2115getMinimpl = TextRange.m2115getMinimpl(textFieldValue2.m2239getSelectiond9O1mEE());
                int iM2114getMaximpl = TextRange.m2114getMaximpl(textFieldValue2.m2239getSelectiond9O1mEE());
                TextRange textRangeM2238getCompositionMzsxiRA = this.state.m2238getCompositionMzsxiRA();
                int iM2115getMinimpl2 = textRangeM2238getCompositionMzsxiRA != null ? TextRange.m2115getMinimpl(textRangeM2238getCompositionMzsxiRA.m2121unboximpl()) : -1;
                TextRange textRangeM2238getCompositionMzsxiRA2 = this.state.m2238getCompositionMzsxiRA();
                inputMethodManager.updateSelection(iM2115getMinimpl, iM2114getMaximpl, iM2115getMinimpl2, textRangeM2238getCompositionMzsxiRA2 != null ? TextRange.m2114getMaximpl(textRangeM2238getCompositionMzsxiRA2.m2121unboximpl()) : -1);
                return;
            }
            return;
        }
        if (textFieldValue != null && (!Intrinsics.areEqual(textFieldValue.getText(), textFieldValue2.getText()) || (TextRange.m2110equalsimpl0(textFieldValue.m2239getSelectiond9O1mEE(), textFieldValue2.m2239getSelectiond9O1mEE()) && !Intrinsics.areEqual(textFieldValue.m2238getCompositionMzsxiRA(), textFieldValue2.m2238getCompositionMzsxiRA())))) {
            restartInputImmediately();
            return;
        }
        int size2 = this.ics.size();
        for (int i2 = 0; i2 < size2; i2++) {
            RecordingInputConnection recordingInputConnection2 = (RecordingInputConnection) ((WeakReference) this.ics.get(i2)).get();
            if (recordingInputConnection2 != null) {
                recordingInputConnection2.updateInputState(this.state, this.inputMethodManager);
            }
        }
    }

    public final void notifyFocusedRect(androidx.compose.ui.geometry.Rect rect) {
        Rect rect2;
        this.focusedRect = new Rect(MathKt.roundToInt(rect.getLeft()), MathKt.roundToInt(rect.getTop()), MathKt.roundToInt(rect.getRight()), MathKt.roundToInt(rect.getBottom()));
        if (!this.ics.isEmpty() || (rect2 = this.focusedRect) == null) {
            return;
        }
        this.view.requestRectangleOnScreen(new Rect(rect2));
    }

    public final void updateTextLayoutResult(TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, androidx.compose.ui.geometry.Rect rect, androidx.compose.ui.geometry.Rect rect2) {
        this.cursorAnchorInfoController.updateTextLayoutResult(textFieldValue, offsetMapping, textLayoutResult, rect, rect2);
    }

    private final void restartInputImmediately() {
        this.inputMethodManager.restartInput();
    }
}
