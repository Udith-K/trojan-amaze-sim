package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: dynamicTypes.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class DynamicTypesKt {
    public static final boolean isDynamic(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        kotlinType.unwrap();
        return false;
    }
}
