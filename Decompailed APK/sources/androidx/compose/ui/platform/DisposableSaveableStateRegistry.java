package androidx.compose.ui.platform;

import androidx.compose.runtime.saveable.SaveableStateRegistry;
import java.util.Map;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: DisposableSaveableStateRegistry.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DisposableSaveableStateRegistry implements SaveableStateRegistry {
    private final /* synthetic */ SaveableStateRegistry $$delegate_0;
    private final Function0 onDispose;

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public boolean canBeSaved(Object obj) {
        return this.$$delegate_0.canBeSaved(obj);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Object consumeRestored(String str) {
        return this.$$delegate_0.consumeRestored(str);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Map performSave() {
        return this.$$delegate_0.performSave();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public SaveableStateRegistry.Entry registerProvider(String str, Function0 function0) {
        return this.$$delegate_0.registerProvider(str, function0);
    }

    public DisposableSaveableStateRegistry(SaveableStateRegistry saveableStateRegistry, Function0 function0) {
        this.onDispose = function0;
        this.$$delegate_0 = saveableStateRegistry;
    }

    public final void dispose() {
        this.onDispose.invoke();
    }
}
