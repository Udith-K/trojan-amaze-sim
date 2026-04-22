package androidx.compose.ui.modifier;

import androidx.compose.ui.Modifier;

/* JADX INFO: compiled from: ModifierLocalConsumer.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ModifierLocalConsumer extends Modifier.Element {
    void onModifierLocalsUpdated(ModifierLocalReadScope modifierLocalReadScope);
}
