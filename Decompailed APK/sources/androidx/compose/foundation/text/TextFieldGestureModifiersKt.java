package androidx.compose.foundation.text;

import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: TextFieldGestureModifiers.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextFieldGestureModifiersKt {
    public static final Modifier textFieldFocusModifier(Modifier modifier, boolean z, FocusRequester focusRequester, MutableInteractionSource mutableInteractionSource, Function1 function1) {
        return FocusableKt.focusable(FocusChangedModifierKt.onFocusChanged(FocusRequesterModifierKt.focusRequester(modifier, focusRequester), function1), z, mutableInteractionSource);
    }
}
