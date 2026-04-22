package androidx.compose.ui.text.input;

import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: TextInputService.kt */
/* JADX INFO: loaded from: classes.dex */
public class TextInputService {
    private final AtomicReference _currentInputSession = new AtomicReference(null);
    private final PlatformTextInputService platformTextInputService;

    public TextInputService(PlatformTextInputService platformTextInputService) {
        this.platformTextInputService = platformTextInputService;
    }

    public final TextInputSession getCurrentInputSession$ui_text_release() {
        return (TextInputSession) this._currentInputSession.get();
    }

    public TextInputSession startInput(TextFieldValue textFieldValue, ImeOptions imeOptions, Function1 function1, Function1 function12) {
        this.platformTextInputService.startInput(textFieldValue, imeOptions, function1, function12);
        TextInputSession textInputSession = new TextInputSession(this, this.platformTextInputService);
        this._currentInputSession.set(textInputSession);
        return textInputSession;
    }

    public final void startInput() {
        this.platformTextInputService.startInput();
        this._currentInputSession.set(new TextInputSession(this, this.platformTextInputService));
    }

    public void stopInput(TextInputSession textInputSession) {
        if (MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this._currentInputSession, textInputSession, null)) {
            this.platformTextInputService.stopInput();
        }
    }

    public final void stopInput() {
        this.platformTextInputService.stopInput();
    }

    public final void showSoftwareKeyboard() {
        if (getCurrentInputSession$ui_text_release() != null) {
            this.platformTextInputService.showSoftwareKeyboard();
        }
    }

    public final void hideSoftwareKeyboard() {
        this.platformTextInputService.hideSoftwareKeyboard();
    }
}
