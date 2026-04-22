package androidx.compose.runtime.saveable;

import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import java.util.Arrays;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RememberSaveable.kt */
/* JADX INFO: loaded from: classes.dex */
final class SaveableHolder implements SaverScope, RememberObserver {
    private SaveableStateRegistry.Entry entry;
    private Object[] inputs;
    private String key;
    private SaveableStateRegistry registry;
    private Saver saver;
    private Object value;
    private final Function0 valueProvider = new Function0() { // from class: androidx.compose.runtime.saveable.SaveableHolder$valueProvider$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Saver saver = this.this$0.saver;
            SaveableHolder saveableHolder = this.this$0;
            Object obj = saveableHolder.value;
            if (obj != null) {
                return saver.save(saveableHolder, obj);
            }
            throw new IllegalArgumentException("Value should be initialized");
        }
    };

    public SaveableHolder(Saver saver, SaveableStateRegistry saveableStateRegistry, String str, Object obj, Object[] objArr) {
        this.saver = saver;
        this.registry = saveableStateRegistry;
        this.key = str;
        this.value = obj;
        this.inputs = objArr;
    }

    public final void update(Saver saver, SaveableStateRegistry saveableStateRegistry, String str, Object obj, Object[] objArr) {
        boolean z;
        boolean z2 = true;
        if (this.registry != saveableStateRegistry) {
            this.registry = saveableStateRegistry;
            z = true;
        } else {
            z = false;
        }
        if (Intrinsics.areEqual(this.key, str)) {
            z2 = z;
        } else {
            this.key = str;
        }
        this.saver = saver;
        this.value = obj;
        this.inputs = objArr;
        SaveableStateRegistry.Entry entry = this.entry;
        if (entry == null || !z2) {
            return;
        }
        if (entry != null) {
            entry.unregister();
        }
        this.entry = null;
        register();
    }

    private final void register() {
        SaveableStateRegistry saveableStateRegistry = this.registry;
        if (this.entry == null) {
            if (saveableStateRegistry != null) {
                RememberSaveableKt.requireCanBeSaved(saveableStateRegistry, this.valueProvider.invoke());
                this.entry = saveableStateRegistry.registerProvider(this.key, this.valueProvider);
                return;
            }
            return;
        }
        throw new IllegalArgumentException(("entry(" + this.entry + ") is not null").toString());
    }

    @Override // androidx.compose.runtime.saveable.SaverScope
    public boolean canBeSaved(Object obj) {
        SaveableStateRegistry saveableStateRegistry = this.registry;
        return saveableStateRegistry == null || saveableStateRegistry.canBeSaved(obj);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        register();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        SaveableStateRegistry.Entry entry = this.entry;
        if (entry != null) {
            entry.unregister();
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        SaveableStateRegistry.Entry entry = this.entry;
        if (entry != null) {
            entry.unregister();
        }
    }

    public final Object getValueIfInputsDidntChange(Object[] objArr) {
        if (Arrays.equals(objArr, this.inputs)) {
            return this.value;
        }
        return null;
    }
}
