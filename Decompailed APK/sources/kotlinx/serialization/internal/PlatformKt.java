package kotlinx.serialization.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Polymorphic;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: Platform.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class PlatformKt {
    public static final KSerializer compiledSerializerImpl(KClass kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return constructSerializerForGivenTypeArgs(kClass, new KSerializer[0]);
    }

    public static final Object[] toNativeArrayImpl(ArrayList arrayList, KClass eClass) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        Intrinsics.checkNotNullParameter(eClass, "eClass");
        Object objNewInstance = Array.newInstance((Class<?>) JvmClassMappingKt.getJavaClass(eClass), arrayList.size());
        Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type kotlin.Array<E of kotlinx.serialization.internal.PlatformKt.toNativeArrayImpl>");
        Object[] array = arrayList.toArray((Object[]) objNewInstance);
        Intrinsics.checkNotNullExpressionValue(array, "toArray(java.lang.reflec….java, size) as Array<E>)");
        return array;
    }

    public static final Void platformSpecificSerializerNotRegistered(KClass kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Platform_commonKt.serializerNotRegistered(kClass);
        throw new KotlinNothingValueException();
    }

    public static final KSerializer constructSerializerForGivenTypeArgs(KClass kClass, KSerializer... args) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(args, "args");
        return constructSerializerForGivenTypeArgs(JvmClassMappingKt.getJavaClass(kClass), (KSerializer[]) Arrays.copyOf(args, args.length));
    }

    public static final KSerializer constructSerializerForGivenTypeArgs(Class cls, KSerializer... args) throws IllegalAccessException, InvocationTargetException {
        Object obj;
        Field field;
        KSerializer kSerializerInterfaceSerializer;
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(args, "args");
        if (cls.isEnum() && isNotAnnotated(cls)) {
            return createEnumSerializer(cls);
        }
        if (cls.isInterface() && (kSerializerInterfaceSerializer = interfaceSerializer(cls)) != null) {
            return kSerializerInterfaceSerializer;
        }
        KSerializer kSerializerInvokeSerializerOnCompanion = invokeSerializerOnCompanion(cls, (KSerializer[]) Arrays.copyOf(args, args.length));
        if (kSerializerInvokeSerializerOnCompanion != null) {
            return kSerializerInvokeSerializerOnCompanion;
        }
        KSerializer kSerializerFindObjectSerializer = findObjectSerializer(cls);
        if (kSerializerFindObjectSerializer != null) {
            return kSerializerFindObjectSerializer;
        }
        try {
            Class<?>[] declaredClasses = cls.getDeclaredClasses();
            Intrinsics.checkNotNullExpressionValue(declaredClasses, "declaredClasses");
            int length = declaredClasses.length;
            int i = 0;
            Class<?> cls2 = null;
            boolean z = false;
            while (true) {
                if (i < length) {
                    Class<?> cls3 = declaredClasses[i];
                    if (Intrinsics.areEqual(cls3.getSimpleName(), "$serializer")) {
                        if (z) {
                            break;
                        }
                        z = true;
                        cls2 = cls3;
                    }
                    i++;
                } else if (!z) {
                }
            }
            cls2 = null;
            obj = (cls2 == null || (field = cls2.getField("INSTANCE")) == null) ? null : field.get(null);
        } catch (NoSuchFieldException unused) {
        }
        KSerializer kSerializer = obj instanceof KSerializer ? (KSerializer) obj : null;
        if (kSerializer != null) {
            return kSerializer;
        }
        if (isPolymorphicSerializer(cls)) {
            return new PolymorphicSerializer(JvmClassMappingKt.getKotlinClass(cls));
        }
        return null;
    }

    private static final boolean isNotAnnotated(Class cls) {
        return cls.getAnnotation(Serializable.class) == null && cls.getAnnotation(Polymorphic.class) == null;
    }

    private static final boolean isPolymorphicSerializer(Class cls) {
        if (cls.getAnnotation(Polymorphic.class) != null) {
            return true;
        }
        Serializable serializable = (Serializable) cls.getAnnotation(Serializable.class);
        return serializable != null && Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(serializable.with()), Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class));
    }

    private static final KSerializer interfaceSerializer(Class cls) {
        Serializable serializable = (Serializable) cls.getAnnotation(Serializable.class);
        if (serializable == null || Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(serializable.with()), Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class))) {
            return new PolymorphicSerializer(JvmClassMappingKt.getKotlinClass(cls));
        }
        return null;
    }

    private static final KSerializer invokeSerializerOnCompanion(Class cls, KSerializer... kSerializerArr) throws IllegalAccessException, InvocationTargetException {
        Class[] clsArr;
        Object objCompanionOrNull = companionOrNull(cls);
        if (objCompanionOrNull == null) {
            return null;
        }
        try {
            if (kSerializerArr.length == 0) {
                clsArr = new Class[0];
            } else {
                int length = kSerializerArr.length;
                Class[] clsArr2 = new Class[length];
                for (int i = 0; i < length; i++) {
                    clsArr2[i] = KSerializer.class;
                }
                clsArr = clsArr2;
            }
            Object objInvoke = objCompanionOrNull.getClass().getDeclaredMethod("serializer", (Class[]) Arrays.copyOf(clsArr, clsArr.length)).invoke(objCompanionOrNull, Arrays.copyOf(kSerializerArr, kSerializerArr.length));
            if (objInvoke instanceof KSerializer) {
                return (KSerializer) objInvoke;
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                throw e;
            }
            String message = cause.getMessage();
            if (message == null) {
                message = e.getMessage();
            }
            throw new InvocationTargetException(cause, message);
        }
    }

    private static final Object companionOrNull(Class cls) {
        try {
            Field declaredField = cls.getDeclaredField("Companion");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static final KSerializer createEnumSerializer(Class cls) {
        Object[] enumConstants = cls.getEnumConstants();
        String canonicalName = cls.getCanonicalName();
        Intrinsics.checkNotNullExpressionValue(canonicalName, "canonicalName");
        Intrinsics.checkNotNull(enumConstants, "null cannot be cast to non-null type kotlin.Array<out kotlin.Enum<*>>");
        return new EnumSerializer(canonicalName, (Enum[]) enumConstants);
    }

    private static final KSerializer findObjectSerializer(Class cls) throws IllegalAccessException, InvocationTargetException {
        Field[] declaredFields = cls.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(declaredFields, "declaredFields");
        int length = declaredFields.length;
        int i = 0;
        Field field = null;
        int i2 = 0;
        boolean z = false;
        while (true) {
            if (i2 >= length) {
                if (!z) {
                    break;
                }
            } else {
                Field field2 = declaredFields[i2];
                if (Intrinsics.areEqual(field2.getName(), "INSTANCE") && Intrinsics.areEqual(field2.getType(), cls) && Modifier.isStatic(field2.getModifiers())) {
                    if (z) {
                        break;
                    }
                    z = true;
                    field = field2;
                }
                i2++;
            }
        }
        field = null;
        if (field == null) {
            return null;
        }
        Object obj = field.get(null);
        Method[] methods = cls.getMethods();
        Intrinsics.checkNotNullExpressionValue(methods, "methods");
        int length2 = methods.length;
        Method method = null;
        boolean z2 = false;
        while (true) {
            if (i >= length2) {
                if (!z2) {
                    break;
                }
            } else {
                Method method2 = methods[i];
                if (Intrinsics.areEqual(method2.getName(), "serializer")) {
                    Class<?>[] parameterTypes = method2.getParameterTypes();
                    Intrinsics.checkNotNullExpressionValue(parameterTypes, "it.parameterTypes");
                    if (parameterTypes.length == 0 && Intrinsics.areEqual(method2.getReturnType(), KSerializer.class)) {
                        if (z2) {
                            break;
                        }
                        method = method2;
                        z2 = true;
                    }
                }
                i++;
            }
        }
        method = null;
        if (method == null) {
            return null;
        }
        Object objInvoke = method.invoke(obj, null);
        if (objInvoke instanceof KSerializer) {
            return (KSerializer) objInvoke;
        }
        return null;
    }

    public static final boolean isInstanceOf(Object obj, KClass kclass) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        Intrinsics.checkNotNullParameter(kclass, "kclass");
        return JvmClassMappingKt.getJavaObjectType(kclass).isInstance(obj);
    }

    public static final boolean isReferenceArray(KClass rootClass) {
        Intrinsics.checkNotNullParameter(rootClass, "rootClass");
        return JvmClassMappingKt.getJavaClass(rootClass).isArray();
    }
}
