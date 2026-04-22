package kotlin.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TypesJVM.kt */
/* JADX INFO: loaded from: classes2.dex */
final class GenericArrayTypeImpl implements GenericArrayType, Type {
    private final Type elementType;

    public GenericArrayTypeImpl(Type elementType) {
        Intrinsics.checkNotNullParameter(elementType, "elementType");
        this.elementType = elementType;
    }

    @Override // java.lang.reflect.GenericArrayType
    public Type getGenericComponentType() {
        return this.elementType;
    }

    @Override // java.lang.reflect.Type
    public String getTypeName() {
        return TypesJVMKt.typeToString(this.elementType) + "[]";
    }

    public boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && Intrinsics.areEqual(getGenericComponentType(), ((GenericArrayType) obj).getGenericComponentType());
    }

    public int hashCode() {
        return getGenericComponentType().hashCode();
    }

    public String toString() {
        return getTypeName();
    }
}
