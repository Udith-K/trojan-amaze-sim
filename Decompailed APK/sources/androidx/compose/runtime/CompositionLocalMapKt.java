package androidx.compose.runtime;

import androidx.compose.runtime.internal.PersistentCompositionLocalHashMap;
import androidx.compose.runtime.internal.PersistentCompositionLocalMapKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompositionLocalMap.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CompositionLocalMapKt {
    public static final boolean contains(PersistentCompositionLocalMap persistentCompositionLocalMap, CompositionLocal compositionLocal) {
        Intrinsics.checkNotNull(compositionLocal, "null cannot be cast to non-null type androidx.compose.runtime.CompositionLocal<kotlin.Any?>");
        return persistentCompositionLocalMap.containsKey(compositionLocal);
    }

    public static final Object read(PersistentCompositionLocalMap persistentCompositionLocalMap, CompositionLocal compositionLocal) {
        Intrinsics.checkNotNull(compositionLocal, "null cannot be cast to non-null type androidx.compose.runtime.CompositionLocal<kotlin.Any?>");
        Object defaultValueHolder$runtime_release = persistentCompositionLocalMap.get((Object) compositionLocal);
        if (defaultValueHolder$runtime_release == null) {
            defaultValueHolder$runtime_release = compositionLocal.getDefaultValueHolder$runtime_release();
        }
        return ((ValueHolder) defaultValueHolder$runtime_release).readValue(persistentCompositionLocalMap);
    }

    public static /* synthetic */ PersistentCompositionLocalMap updateCompositionMap$default(ProvidedValue[] providedValueArr, PersistentCompositionLocalMap persistentCompositionLocalMap, PersistentCompositionLocalMap persistentCompositionLocalMap2, int i, Object obj) {
        if ((i & 4) != 0) {
            persistentCompositionLocalMap2 = PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf();
        }
        return updateCompositionMap(providedValueArr, persistentCompositionLocalMap, persistentCompositionLocalMap2);
    }

    public static final PersistentCompositionLocalMap updateCompositionMap(ProvidedValue[] providedValueArr, PersistentCompositionLocalMap persistentCompositionLocalMap, PersistentCompositionLocalMap persistentCompositionLocalMap2) {
        PersistentCompositionLocalHashMap.Builder builder = PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf().builder();
        for (ProvidedValue providedValue : providedValueArr) {
            CompositionLocal compositionLocal = providedValue.getCompositionLocal();
            Intrinsics.checkNotNull(compositionLocal, "null cannot be cast to non-null type androidx.compose.runtime.ProvidableCompositionLocal<kotlin.Any?>");
            ProvidableCompositionLocal providableCompositionLocal = (ProvidableCompositionLocal) compositionLocal;
            if (providedValue.getCanOverride() || !contains(persistentCompositionLocalMap, providableCompositionLocal)) {
                ValueHolder valueHolder = (ValueHolder) persistentCompositionLocalMap2.get((Object) providableCompositionLocal);
                Intrinsics.checkNotNull(providedValue, "null cannot be cast to non-null type androidx.compose.runtime.ProvidedValue<kotlin.Any?>");
                builder.put(providableCompositionLocal, providableCompositionLocal.updatedStateOf$runtime_release(providedValue, valueHolder));
            }
        }
        return builder.build();
    }
}
