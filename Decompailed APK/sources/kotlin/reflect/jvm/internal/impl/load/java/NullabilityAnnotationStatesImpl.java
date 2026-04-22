package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNamesUtilKt;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;

/* JADX INFO: compiled from: JavaNullabilityAnnotationSettings.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class NullabilityAnnotationStatesImpl implements NullabilityAnnotationStates {
    private final MemoizedFunctionToNullable cache;
    private final Map states;
    private final LockBasedStorageManager storageManager;

    public NullabilityAnnotationStatesImpl(Map states) {
        Intrinsics.checkNotNullParameter(states, "states");
        this.states = states;
        LockBasedStorageManager lockBasedStorageManager = new LockBasedStorageManager("Java nullability annotation states");
        this.storageManager = lockBasedStorageManager;
        MemoizedFunctionToNullable memoizedFunctionToNullableCreateMemoizedFunctionWithNullableValues = lockBasedStorageManager.createMemoizedFunctionWithNullableValues(new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.NullabilityAnnotationStatesImpl$$Lambda$0
            private final NullabilityAnnotationStatesImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return NullabilityAnnotationStatesImpl.cache$lambda$0(this.arg$0, (FqName) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(memoizedFunctionToNullableCreateMemoizedFunctionWithNullableValues, "createMemoizedFunctionWithNullableValues(...)");
        this.cache = memoizedFunctionToNullableCreateMemoizedFunctionWithNullableValues;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object cache$lambda$0(NullabilityAnnotationStatesImpl nullabilityAnnotationStatesImpl, FqName fqName) {
        Intrinsics.checkNotNull(fqName);
        return FqNamesUtilKt.findValueForMostSpecificFqname(fqName, nullabilityAnnotationStatesImpl.states);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.NullabilityAnnotationStates
    public Object get(FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        return this.cache.invoke(fqName);
    }
}
