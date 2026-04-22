package androidx.compose.ui.text.input;

import android.graphics.Rect;
import android.view.Choreographer;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.input.pointer.PositionCalculator;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: TextInputServiceAndroid.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextInputServiceAndroid implements PlatformTextInputService {
    private final Lazy baseInputConnection$delegate;
    private final CursorAnchorInfoController cursorAnchorInfoController;
    private boolean editorHasFocus;
    private Rect focusedRect;
    private Runnable frameCallback;
    private List ics;
    private ImeOptions imeOptions;
    private final Executor inputCommandProcessorExecutor;
    private final InputMethodManager inputMethodManager;
    private Function1 onEditCommand;
    private Function1 onImeActionPerformed;
    private TextFieldValue state;
    private final MutableVector textInputCommandQueue;
    private final View view;

    /* JADX INFO: compiled from: TextInputServiceAndroid.android.kt */
    private enum TextInputCommand {
        StartInput,
        StopInput,
        ShowKeyboard,
        HideKeyboard
    }

    /* JADX INFO: compiled from: TextInputServiceAndroid.android.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TextInputCommand.values().length];
            try {
                iArr[TextInputCommand.StartInput.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextInputCommand.StopInput.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TextInputCommand.ShowKeyboard.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TextInputCommand.HideKeyboard.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public TextInputServiceAndroid(View view, PositionCalculator positionCalculator, InputMethodManager inputMethodManager, Executor executor) {
        this.view = view;
        this.inputMethodManager = inputMethodManager;
        this.inputCommandProcessorExecutor = executor;
        this.onEditCommand = new Function1() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid$onEditCommand$1
            public final void invoke(List list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((List) obj);
                return Unit.INSTANCE;
            }
        };
        this.onImeActionPerformed = new Function1() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid$onImeActionPerformed$1
            /* JADX INFO: renamed from: invoke-KlQnJC8, reason: not valid java name */
            public final void m2241invokeKlQnJC8(int i) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m2241invokeKlQnJC8(((ImeAction) obj).m2193unboximpl());
                return Unit.INSTANCE;
            }
        };
        this.state = new TextFieldValue("", TextRange.Companion.m2122getZerod9O1mEE(), (TextRange) null, 4, (DefaultConstructorMarker) null);
        this.imeOptions = ImeOptions.Companion.getDefault();
        this.ics = new ArrayList();
        this.baseInputConnection$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid$baseInputConnection$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final BaseInputConnection invoke() {
                return new BaseInputConnection(this.this$0.getView(), false);
            }
        });
        this.cursorAnchorInfoController = new CursorAnchorInfoController(positionCalculator, inputMethodManager);
        this.textInputCommandQueue = new MutableVector(new TextInputCommand[16], 0);
    }

    public final View getView() {
        return this.view;
    }

    public /* synthetic */ TextInputServiceAndroid(View view, PositionCalculator positionCalculator, InputMethodManager inputMethodManager, Executor executor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(view, positionCalculator, inputMethodManager, (i & 8) != 0 ? TextInputServiceAndroid_androidKt.asExecutor(Choreographer.getInstance()) : executor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BaseInputConnection getBaseInputConnection() {
        return (BaseInputConnection) this.baseInputConnection$delegate.getValue();
    }

    public TextInputServiceAndroid(View view, PositionCalculator positionCalculator) {
        this(view, positionCalculator, new InputMethodManagerImpl(view), null, 8, null);
    }

    public final InputConnection createInputConnection(EditorInfo editorInfo) {
        if (!this.editorHasFocus) {
            return null;
        }
        TextInputServiceAndroid_androidKt.update(editorInfo, this.imeOptions, this.state);
        TextInputServiceAndroid_androidKt.updateWithEmojiCompat(editorInfo);
        RecordingInputConnection recordingInputConnection = new RecordingInputConnection(this.state, new InputEventCallback2() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid.createInputConnection.1
            @Override // androidx.compose.ui.text.input.InputEventCallback2
            public void onEditCommands(List list) {
                TextInputServiceAndroid.this.onEditCommand.invoke(list);
            }

            @Override // androidx.compose.ui.text.input.InputEventCallback2
            /* JADX INFO: renamed from: onImeAction-KlQnJC8 */
            public void mo2206onImeActionKlQnJC8(int i) {
                TextInputServiceAndroid.this.onImeActionPerformed.invoke(ImeAction.m2187boximpl(i));
            }

            @Override // androidx.compose.ui.text.input.InputEventCallback2
            public void onKeyEvent(KeyEvent keyEvent) {
                TextInputServiceAndroid.this.getBaseInputConnection().sendKeyEvent(keyEvent);
            }

            @Override // androidx.compose.ui.text.input.InputEventCallback2
            public void onRequestCursorAnchorInfo(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
                TextInputServiceAndroid.this.cursorAnchorInfoController.requestUpdate(z, z2, z3, z4, z5, z6);
            }

            @Override // androidx.compose.ui.text.input.InputEventCallback2
            public void onConnectionClosed(RecordingInputConnection recordingInputConnection2) {
                int size = TextInputServiceAndroid.this.ics.size();
                for (int i = 0; i < size; i++) {
                    if (Intrinsics.areEqual(((WeakReference) TextInputServiceAndroid.this.ics.get(i)).get(), recordingInputConnection2)) {
                        TextInputServiceAndroid.this.ics.remove(i);
                        return;
                    }
                }
            }
        }, this.imeOptions.getAutoCorrect());
        this.ics.add(new WeakReference(recordingInputConnection));
        return recordingInputConnection;
    }

    public final boolean isEditorFocused() {
        return this.editorHasFocus;
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void startInput(TextFieldValue textFieldValue, ImeOptions imeOptions, Function1 function1, Function1 function12) {
        this.editorHasFocus = true;
        this.state = textFieldValue;
        this.imeOptions = imeOptions;
        this.onEditCommand = function1;
        this.onImeActionPerformed = function12;
        sendInputCommand(TextInputCommand.StartInput);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void startInput() {
        sendInputCommand(TextInputCommand.StartInput);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void stopInput() {
        this.editorHasFocus = false;
        this.onEditCommand = new Function1() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid.stopInput.1
            public final void invoke(List list) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((List) obj);
                return Unit.INSTANCE;
            }
        };
        this.onImeActionPerformed = new Function1() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid.stopInput.2
            /* JADX INFO: renamed from: invoke-KlQnJC8, reason: not valid java name */
            public final void m2242invokeKlQnJC8(int i) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m2242invokeKlQnJC8(((ImeAction) obj).m2193unboximpl());
                return Unit.INSTANCE;
            }
        };
        this.focusedRect = null;
        sendInputCommand(TextInputCommand.StopInput);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void showSoftwareKeyboard() {
        sendInputCommand(TextInputCommand.ShowKeyboard);
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void hideSoftwareKeyboard() {
        sendInputCommand(TextInputCommand.HideKeyboard);
    }

    private final void sendInputCommand(TextInputCommand textInputCommand) {
        this.textInputCommandQueue.add(textInputCommand);
        if (this.frameCallback == null) {
            Runnable runnable = new Runnable() { // from class: androidx.compose.ui.text.input.TextInputServiceAndroid$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    TextInputServiceAndroid.sendInputCommand$lambda$1(this.f$0);
                }
            };
            this.inputCommandProcessorExecutor.execute(runnable);
            this.frameCallback = runnable;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendInputCommand$lambda$1(TextInputServiceAndroid textInputServiceAndroid) {
        textInputServiceAndroid.frameCallback = null;
        textInputServiceAndroid.processInputCommands();
    }

    private final void processInputCommands() {
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
        MutableVector mutableVector = this.textInputCommandQueue;
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                processInputCommands$applyToState((TextInputCommand) content[i], ref$ObjectRef, ref$ObjectRef2);
                i++;
            } while (i < size);
        }
        this.textInputCommandQueue.clear();
        if (Intrinsics.areEqual(ref$ObjectRef.element, Boolean.TRUE)) {
            restartInputImmediately();
        }
        Boolean bool = (Boolean) ref$ObjectRef2.element;
        if (bool != null) {
            setKeyboardVisibleImmediately(bool.booleanValue());
        }
        if (Intrinsics.areEqual(ref$ObjectRef.element, Boolean.FALSE)) {
            restartInputImmediately();
        }
    }

    private static final void processInputCommands$applyToState(TextInputCommand textInputCommand, Ref$ObjectRef ref$ObjectRef, Ref$ObjectRef ref$ObjectRef2) {
        int i = WhenMappings.$EnumSwitchMapping$0[textInputCommand.ordinal()];
        if (i == 1) {
            Boolean bool = Boolean.TRUE;
            ref$ObjectRef.element = bool;
            ref$ObjectRef2.element = bool;
        } else if (i == 2) {
            Boolean bool2 = Boolean.FALSE;
            ref$ObjectRef.element = bool2;
            ref$ObjectRef2.element = bool2;
        } else if ((i == 3 || i == 4) && !Intrinsics.areEqual(ref$ObjectRef.element, Boolean.FALSE)) {
            ref$ObjectRef2.element = Boolean.valueOf(textInputCommand == TextInputCommand.ShowKeyboard);
        }
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void updateState(TextFieldValue textFieldValue, TextFieldValue textFieldValue2) {
        boolean z = (TextRange.m2110equalsimpl0(this.state.m2239getSelectiond9O1mEE(), textFieldValue2.m2239getSelectiond9O1mEE()) && Intrinsics.areEqual(this.state.m2238getCompositionMzsxiRA(), textFieldValue2.m2238getCompositionMzsxiRA())) ? false : true;
        this.state = textFieldValue2;
        int size = this.ics.size();
        for (int i = 0; i < size; i++) {
            RecordingInputConnection recordingInputConnection = (RecordingInputConnection) ((WeakReference) this.ics.get(i)).get();
            if (recordingInputConnection != null) {
                recordingInputConnection.setMTextFieldValue$ui_release(textFieldValue2);
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

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void notifyFocusedRect(androidx.compose.ui.geometry.Rect rect) {
        Rect rect2;
        this.focusedRect = new Rect(MathKt.roundToInt(rect.getLeft()), MathKt.roundToInt(rect.getTop()), MathKt.roundToInt(rect.getRight()), MathKt.roundToInt(rect.getBottom()));
        if (!this.ics.isEmpty() || (rect2 = this.focusedRect) == null) {
            return;
        }
        this.view.requestRectangleOnScreen(new Rect(rect2));
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public void updateTextLayoutResult(TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Function1 function1, androidx.compose.ui.geometry.Rect rect, androidx.compose.ui.geometry.Rect rect2) {
        this.cursorAnchorInfoController.updateTextLayoutResult(textFieldValue, offsetMapping, textLayoutResult, function1, rect, rect2);
    }

    private final void restartInputImmediately() {
        this.inputMethodManager.restartInput();
    }

    private final void setKeyboardVisibleImmediately(boolean z) {
        if (z) {
            this.inputMethodManager.showSoftInput();
        } else {
            this.inputMethodManager.hideSoftInput();
        }
    }
}
