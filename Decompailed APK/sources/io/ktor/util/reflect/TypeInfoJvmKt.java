package io.ktor.util.reflect;

import java.lang.reflect.Type;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* JADX INFO: compiled from: TypeInfoJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TypeInfoJvmKt {
    public static final TypeInfo typeInfoImpl(Type reifiedType, KClass kClass, KType kType) {
        Intrinsics.checkNotNullParameter(reifiedType, "reifiedType");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        return new TypeInfo(kClass, reifiedType, kType);
    }

    public static final boolean instanceOf(Object obj, KClass type) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        return JvmClassMappingKt.getJavaClass(type).isInstance(obj);
    }
}
