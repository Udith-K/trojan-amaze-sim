package androidx.compose.ui.modifier;

import androidx.compose.ui.Modifier;

/* JADX INFO: compiled from: ModifierLocalProvider.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ModifierLocalProvider extends Modifier.Element {
    ProvidableModifierLocal getKey();

    Object getValue();
}
