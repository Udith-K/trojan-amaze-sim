package androidx.compose.foundation.text;

import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.input.ImeAction;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KeyboardActionRunner.kt */
/* JADX INFO: loaded from: classes.dex */
public final class KeyboardActionRunner implements KeyboardActionScope {
    public FocusManager focusManager;
    public KeyboardActions keyboardActions;
    private final SoftwareKeyboardController keyboardController;

    public KeyboardActionRunner(SoftwareKeyboardController softwareKeyboardController) {
        this.keyboardController = softwareKeyboardController;
    }

    public final KeyboardActions getKeyboardActions() {
        KeyboardActions keyboardActions = this.keyboardActions;
        if (keyboardActions != null) {
            return keyboardActions;
        }
        Intrinsics.throwUninitializedPropertyAccessException("keyboardActions");
        return null;
    }

    public final void setKeyboardActions(KeyboardActions keyboardActions) {
        this.keyboardActions = keyboardActions;
    }

    public final FocusManager getFocusManager() {
        FocusManager focusManager = this.focusManager;
        if (focusManager != null) {
            return focusManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("focusManager");
        return null;
    }

    public final void setFocusManager(FocusManager focusManager) {
        this.focusManager = focusManager;
    }

    /* JADX INFO: renamed from: runAction-KlQnJC8, reason: not valid java name */
    public final void m387runActionKlQnJC8(int i) {
        Function1 onSend;
        ImeAction.Companion companion = ImeAction.Companion;
        Unit unit = null;
        if (ImeAction.m2190equalsimpl0(i, companion.m2195getDoneeUduSuo())) {
            onSend = getKeyboardActions().getOnDone();
        } else if (ImeAction.m2190equalsimpl0(i, companion.m2196getGoeUduSuo())) {
            onSend = getKeyboardActions().getOnGo();
        } else if (ImeAction.m2190equalsimpl0(i, companion.m2197getNexteUduSuo())) {
            onSend = getKeyboardActions().getOnNext();
        } else if (ImeAction.m2190equalsimpl0(i, companion.m2199getPreviouseUduSuo())) {
            onSend = getKeyboardActions().getOnPrevious();
        } else if (ImeAction.m2190equalsimpl0(i, companion.m2200getSearcheUduSuo())) {
            onSend = getKeyboardActions().getOnSearch();
        } else if (ImeAction.m2190equalsimpl0(i, companion.m2201getSendeUduSuo())) {
            onSend = getKeyboardActions().getOnSend();
        } else {
            if (!(ImeAction.m2190equalsimpl0(i, companion.m2194getDefaulteUduSuo()) ? true : ImeAction.m2190equalsimpl0(i, companion.m2198getNoneeUduSuo()))) {
                throw new IllegalStateException("invalid ImeAction");
            }
            onSend = null;
        }
        if (onSend != null) {
            onSend.invoke(this);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            m386defaultKeyboardActionKlQnJC8(i);
        }
    }

    /* JADX INFO: renamed from: defaultKeyboardAction-KlQnJC8, reason: not valid java name */
    public void m386defaultKeyboardActionKlQnJC8(int i) {
        ImeAction.Companion companion = ImeAction.Companion;
        if (ImeAction.m2190equalsimpl0(i, companion.m2197getNexteUduSuo())) {
            getFocusManager().mo1105moveFocus3ESFkO8(FocusDirection.Companion.m1099getNextdhqQ8s());
            return;
        }
        if (ImeAction.m2190equalsimpl0(i, companion.m2199getPreviouseUduSuo())) {
            getFocusManager().mo1105moveFocus3ESFkO8(FocusDirection.Companion.m1100getPreviousdhqQ8s());
            return;
        }
        if (!ImeAction.m2190equalsimpl0(i, companion.m2195getDoneeUduSuo())) {
            if (ImeAction.m2190equalsimpl0(i, companion.m2196getGoeUduSuo()) ? true : ImeAction.m2190equalsimpl0(i, companion.m2200getSearcheUduSuo()) ? true : ImeAction.m2190equalsimpl0(i, companion.m2201getSendeUduSuo()) ? true : ImeAction.m2190equalsimpl0(i, companion.m2194getDefaulteUduSuo())) {
                return;
            }
            ImeAction.m2190equalsimpl0(i, companion.m2198getNoneeUduSuo());
        } else {
            SoftwareKeyboardController softwareKeyboardController = this.keyboardController;
            if (softwareKeyboardController != null) {
                softwareKeyboardController.hide();
            }
        }
    }
}
