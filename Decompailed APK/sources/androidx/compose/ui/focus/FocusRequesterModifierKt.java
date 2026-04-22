package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;

/* JADX INFO: compiled from: FocusRequesterModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FocusRequesterModifierKt {
    public static final Modifier focusRequester(Modifier modifier, FocusRequester focusRequester) {
        return modifier.then(new FocusRequesterElement(focusRequester));
    }
}
