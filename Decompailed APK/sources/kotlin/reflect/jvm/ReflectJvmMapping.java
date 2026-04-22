package kotlin.reflect.jvm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.calls.Caller;

/* JADX INFO: compiled from: ReflectJvmMapping.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ReflectJvmMapping {
    public static final Field getJavaField(KProperty kProperty) {
        Intrinsics.checkNotNullParameter(kProperty, "<this>");
        KPropertyImpl kPropertyImplAsKPropertyImpl = UtilKt.asKPropertyImpl(kProperty);
        if (kPropertyImplAsKPropertyImpl != null) {
            return kPropertyImplAsKPropertyImpl.getJavaField();
        }
        return null;
    }

    public static final Method getJavaGetter(KProperty kProperty) {
        Intrinsics.checkNotNullParameter(kProperty, "<this>");
        return getJavaMethod(kProperty.getGetter());
    }

    public static final Method getJavaSetter(KMutableProperty kMutableProperty) {
        Intrinsics.checkNotNullParameter(kMutableProperty, "<this>");
        return getJavaMethod(kMutableProperty.getSetter());
    }

    public static final Method getJavaMethod(KFunction kFunction) {
        Caller caller;
        Intrinsics.checkNotNullParameter(kFunction, "<this>");
        KCallableImpl kCallableImplAsKCallableImpl = UtilKt.asKCallableImpl(kFunction);
        Member memberMo2745getMember = (kCallableImplAsKCallableImpl == null || (caller = kCallableImplAsKCallableImpl.getCaller()) == null) ? null : caller.mo2745getMember();
        if (memberMo2745getMember instanceof Method) {
            return (Method) memberMo2745getMember;
        }
        return null;
    }

    public static final Constructor getJavaConstructor(KFunction kFunction) {
        Caller caller;
        Intrinsics.checkNotNullParameter(kFunction, "<this>");
        KCallableImpl kCallableImplAsKCallableImpl = UtilKt.asKCallableImpl(kFunction);
        Member memberMo2745getMember = (kCallableImplAsKCallableImpl == null || (caller = kCallableImplAsKCallableImpl.getCaller()) == null) ? null : caller.mo2745getMember();
        if (memberMo2745getMember instanceof Constructor) {
            return (Constructor) memberMo2745getMember;
        }
        return null;
    }

    public static final Type getJavaType(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        Type javaType = ((KTypeImpl) kType).getJavaType();
        return javaType == null ? TypesJVMKt.getJavaType(kType) : javaType;
    }
}
