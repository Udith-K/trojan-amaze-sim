package androidx.compose.runtime.saveable;

import androidx.compose.runtime.Composer;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SaveableStateHolder.kt */
/* JADX INFO: loaded from: classes.dex */
public interface SaveableStateHolder {
    void SaveableStateProvider(Object obj, Function2 function2, Composer composer, int i);

    void removeState(Object obj);
}
