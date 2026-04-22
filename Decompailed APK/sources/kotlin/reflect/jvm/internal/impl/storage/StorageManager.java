package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: StorageManager.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface StorageManager {
    Object compute(Function0 function0);

    CacheWithNotNullValues createCacheWithNotNullValues();

    CacheWithNullableValues createCacheWithNullableValues();

    NotNullLazyValue createLazyValue(Function0 function0);

    NotNullLazyValue createLazyValueWithPostCompute(Function0 function0, Function1 function1, Function1 function12);

    MemoizedFunctionToNotNull createMemoizedFunction(Function1 function1);

    MemoizedFunctionToNullable createMemoizedFunctionWithNullableValues(Function1 function1);

    NullableLazyValue createNullableLazyValue(Function0 function0);

    NotNullLazyValue createRecursionTolerantLazyValue(Function0 function0, Object obj);
}
