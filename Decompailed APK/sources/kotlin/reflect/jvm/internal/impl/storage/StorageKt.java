package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: storage.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class StorageKt {
    public static final Object getValue(NotNullLazyValue notNullLazyValue, Object obj, KProperty p) {
        Intrinsics.checkNotNullParameter(notNullLazyValue, "<this>");
        Intrinsics.checkNotNullParameter(p, "p");
        return notNullLazyValue.invoke();
    }

    public static final Object getValue(NullableLazyValue nullableLazyValue, Object obj, KProperty p) {
        Intrinsics.checkNotNullParameter(nullableLazyValue, "<this>");
        Intrinsics.checkNotNullParameter(p, "p");
        return nullableLazyValue.invoke();
    }
}
