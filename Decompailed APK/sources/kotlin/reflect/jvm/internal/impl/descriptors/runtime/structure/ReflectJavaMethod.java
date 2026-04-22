package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;

/* JADX INFO: compiled from: ReflectJavaMethod.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ReflectJavaMethod extends ReflectJavaMember implements JavaMethod {
    private final Method member;

    public ReflectJavaMethod(Method member) {
        Intrinsics.checkNotNullParameter(member, "member");
        this.member = member;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaMember
    public Method getMember() {
        return this.member;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    public List getValueParameters() {
        Type[] genericParameterTypes = getMember().getGenericParameterTypes();
        Intrinsics.checkNotNullExpressionValue(genericParameterTypes, "getGenericParameterTypes(...)");
        Annotation[][] parameterAnnotations = getMember().getParameterAnnotations();
        Intrinsics.checkNotNullExpressionValue(parameterAnnotations, "getParameterAnnotations(...)");
        return getValueParameters(genericParameterTypes, parameterAnnotations, getMember().isVarArgs());
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    public ReflectJavaType getReturnType() {
        ReflectJavaType.Factory factory = ReflectJavaType.Factory;
        Type genericReturnType = getMember().getGenericReturnType();
        Intrinsics.checkNotNullExpressionValue(genericReturnType, "getGenericReturnType(...)");
        return factory.create(genericReturnType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    public JavaAnnotationArgument getAnnotationParameterDefaultValue() {
        Object defaultValue = getMember().getDefaultValue();
        if (defaultValue != null) {
            return ReflectJavaAnnotationArgument.Factory.create(defaultValue, null);
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner
    public List getTypeParameters() {
        TypeVariable<Method>[] typeParameters = getMember().getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(typeParameters, "getTypeParameters(...)");
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable<Method> typeVariable : typeParameters) {
            arrayList.add(new ReflectJavaTypeParameter(typeVariable));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    public boolean getHasAnnotationParameterDefaultValue() {
        return getAnnotationParameterDefaultValue() != null;
    }
}
