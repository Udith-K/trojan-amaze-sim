package androidx.compose.ui.modifier;

import androidx.compose.ui.internal.InlineClassHelperKt;

/* JADX INFO: compiled from: ModifierLocalModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BackwardsCompatLocalMap extends ModifierLocalMap {
    private ModifierLocalProvider element;

    public final void setElement(ModifierLocalProvider modifierLocalProvider) {
        this.element = modifierLocalProvider;
    }

    public BackwardsCompatLocalMap(ModifierLocalProvider modifierLocalProvider) {
        super(null);
        this.element = modifierLocalProvider;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    public Object get$ui_release(ModifierLocal modifierLocal) {
        if (!(modifierLocal == this.element.getKey())) {
            InlineClassHelperKt.throwIllegalStateException("Check failed.");
        }
        return this.element.getValue();
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalMap
    public boolean contains$ui_release(ModifierLocal modifierLocal) {
        return modifierLocal == this.element.getKey();
    }
}
