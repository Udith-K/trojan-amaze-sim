package androidx.compose.ui.platform;

import androidx.compose.ui.text.input.TextInputService;

/* JADX INFO: compiled from: SoftwareKeyboardController.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DelegatingSoftwareKeyboardController implements SoftwareKeyboardController {
    private final TextInputService textInputService;

    public DelegatingSoftwareKeyboardController(TextInputService textInputService) {
        this.textInputService = textInputService;
    }

    @Override // androidx.compose.ui.platform.SoftwareKeyboardController
    public void show() {
        this.textInputService.showSoftwareKeyboard();
    }

    @Override // androidx.compose.ui.platform.SoftwareKeyboardController
    public void hide() {
        this.textInputService.hideSoftwareKeyboard();
    }
}
