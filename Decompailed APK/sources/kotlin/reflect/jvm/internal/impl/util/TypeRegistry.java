package kotlin.reflect.jvm.internal.impl.util;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ArrayMapOwner.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class TypeRegistry {
    private final ConcurrentHashMap idPerType = new ConcurrentHashMap();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public abstract int customComputeIfAbsent(ConcurrentHashMap concurrentHashMap, String str, Function1 function1);

    public final NullableArrayMapAccessor generateNullableAccessor(KClass kClass) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        return new NullableArrayMapAccessor(getId(kClass));
    }

    public final int getId(KClass kClass) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        String qualifiedName = kClass.getQualifiedName();
        Intrinsics.checkNotNull(qualifiedName);
        return getId(qualifiedName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getId$lambda$0(TypeRegistry typeRegistry, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return typeRegistry.idCounter.getAndIncrement();
    }

    public final int getId(String keyQualifiedName) {
        Intrinsics.checkNotNullParameter(keyQualifiedName, "keyQualifiedName");
        return customComputeIfAbsent(this.idPerType, keyQualifiedName, new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.util.TypeRegistry$$Lambda$0
            private final TypeRegistry arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return Integer.valueOf(TypeRegistry.getId$lambda$0(this.arg$0, (String) obj));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Collection getIndices() {
        Collection collectionValues = this.idPerType.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return collectionValues;
    }
}
