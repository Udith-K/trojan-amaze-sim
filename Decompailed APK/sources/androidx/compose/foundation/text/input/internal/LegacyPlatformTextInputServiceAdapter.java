package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.input.PlatformTextInputService;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: LegacyPlatformTextInputServiceAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LegacyPlatformTextInputServiceAdapter implements PlatformTextInputService {
    private LegacyPlatformTextInputNode textInputModifierNode;

    /* JADX INFO: compiled from: LegacyPlatformTextInputServiceAdapter.kt */
    public interface LegacyPlatformTextInputNode {
        LayoutCoordinates getLayoutCoordinates();

        LegacyTextFieldState getLegacyTextFieldState();

        SoftwareKeyboardController getSoftwareKeyboardController();

        TextFieldSelectionManager getTextFieldSelectionManager();

        ViewConfiguration getViewConfiguration();

        Job launchTextInputSession(Function2 function2);
    }

    public abstract void startStylusHandwriting();

    protected final LegacyPlatformTextInputNode getTextInputModifierNode() {
        return this.textInputModifierNode;
    }

    public final void registerModifier(LegacyPlatformTextInputNode legacyPlatformTextInputNode) {
        if (this.textInputModifierNode != null) {
            throw new IllegalStateException("Expected textInputModifierNode to be null");
        }
        this.textInputModifierNode = legacyPlatformTextInputNode;
    }

    public final void unregisterModifier(LegacyPlatformTextInputNode legacyPlatformTextInputNode) {
        if (this.textInputModifierNode != legacyPlatformTextInputNode) {
            throw new IllegalStateException(("Expected textInputModifierNode to be " + legacyPlatformTextInputNode + " but was " + this.textInputModifierNode).toString());
        }
        this.textInputModifierNode = null;
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void showSoftwareKeyboard() {
        SoftwareKeyboardController softwareKeyboardController;
        LegacyPlatformTextInputNode legacyPlatformTextInputNode = this.textInputModifierNode;
        if (legacyPlatformTextInputNode == null || (softwareKeyboardController = legacyPlatformTextInputNode.getSoftwareKeyboardController()) == null) {
            return;
        }
        softwareKeyboardController.show();
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void hideSoftwareKeyboard() {
        SoftwareKeyboardController softwareKeyboardController;
        LegacyPlatformTextInputNode legacyPlatformTextInputNode = this.textInputModifierNode;
        if (legacyPlatformTextInputNode == null || (softwareKeyboardController = legacyPlatformTextInputNode.getSoftwareKeyboardController()) == null) {
            return;
        }
        softwareKeyboardController.hide();
    }
}
