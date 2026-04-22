package androidx.compose.runtime.saveable;

import java.util.Map;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: SaveableStateRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
public interface SaveableStateRegistry {

    /* JADX INFO: compiled from: SaveableStateRegistry.kt */
    public interface Entry {
        void unregister();
    }

    boolean canBeSaved(Object obj);

    Object consumeRestored(String str);

    Map performSave();

    Entry registerProvider(String str, Function0 function0);
}
