package androidx.compose.ui.input.pointer;

import androidx.compose.ui.Modifier;

/* JADX INFO: compiled from: PointerIcon.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PointerIconKt {
    public static /* synthetic */ Modifier pointerHoverIcon$default(Modifier modifier, PointerIcon pointerIcon, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return pointerHoverIcon(modifier, pointerIcon, z);
    }

    public static final Modifier pointerHoverIcon(Modifier modifier, PointerIcon pointerIcon, boolean z) {
        return modifier.then(new PointerHoverIconModifierElement(pointerIcon, z));
    }
}
