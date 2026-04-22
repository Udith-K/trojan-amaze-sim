package kotlin.reflect.jvm.internal.calls;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;

/* JADX INFO: compiled from: AnnotationConstructorCaller.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AnnotationConstructorCallerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Object transformKotlinToJvm(Object obj, Class cls) {
        if (obj instanceof Class) {
            return null;
        }
        if (obj instanceof KClass) {
            obj = JvmClassMappingKt.getJavaClass((KClass) obj);
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (objArr instanceof Class[]) {
                return null;
            }
            if (objArr instanceof KClass[]) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.reflect.KClass<*>>");
                KClass[] kClassArr = (KClass[]) obj;
                ArrayList arrayList = new ArrayList(kClassArr.length);
                for (KClass kClass : kClassArr) {
                    arrayList.add(JvmClassMappingKt.getJavaClass(kClass));
                }
                obj = arrayList.toArray(new Class[0]);
            } else {
                obj = objArr;
            }
        }
        if (cls.isInstance(obj)) {
            return obj;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void throwIllegalArgumentType(int i, String str, Class cls) {
        KClass orCreateKotlinClass;
        String qualifiedName;
        if (Intrinsics.areEqual(cls, Class.class)) {
            orCreateKotlinClass = Reflection.getOrCreateKotlinClass(KClass.class);
        } else {
            orCreateKotlinClass = (cls.isArray() && Intrinsics.areEqual(cls.getComponentType(), Class.class)) ? Reflection.getOrCreateKotlinClass(KClass[].class) : JvmClassMappingKt.getKotlinClass(cls);
        }
        if (Intrinsics.areEqual(orCreateKotlinClass.getQualifiedName(), Reflection.getOrCreateKotlinClass(Object[].class).getQualifiedName())) {
            StringBuilder sb = new StringBuilder();
            sb.append(orCreateKotlinClass.getQualifiedName());
            sb.append('<');
            Class<?> componentType = JvmClassMappingKt.getJavaClass(orCreateKotlinClass).getComponentType();
            Intrinsics.checkNotNullExpressionValue(componentType, "getComponentType(...)");
            sb.append(JvmClassMappingKt.getKotlinClass(componentType).getQualifiedName());
            sb.append('>');
            qualifiedName = sb.toString();
        } else {
            qualifiedName = orCreateKotlinClass.getQualifiedName();
        }
        throw new IllegalArgumentException("Argument #" + i + ' ' + str + " is not of the required type " + qualifiedName);
    }

    public static /* synthetic */ Object createAnnotationInstance$default(Class cls, Map map, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            Set setKeySet = map.keySet();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setKeySet, 10));
            Iterator it = setKeySet.iterator();
            while (it.hasNext()) {
                arrayList.add(cls.getDeclaredMethod((String) it.next(), null));
            }
            list = arrayList;
        }
        return createAnnotationInstance(cls, map, list);
    }

    private static final boolean createAnnotationInstance$equals(Class cls, List list, Map map, Object obj) throws IllegalAccessException, InvocationTargetException {
        boolean zAreEqual;
        boolean z;
        KClass annotationClass;
        Annotation annotation = obj instanceof Annotation ? (Annotation) obj : null;
        if (!Intrinsics.areEqual((annotation == null || (annotationClass = JvmClassMappingKt.getAnnotationClass(annotation)) == null) ? null : JvmClassMappingKt.getJavaClass(annotationClass), cls)) {
            return false;
        }
        if ((list instanceof Collection) && list.isEmpty()) {
            z = true;
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Method method = (Method) it.next();
                Object obj2 = map.get(method.getName());
                Object objInvoke = method.invoke(obj, null);
                if (obj2 instanceof boolean[]) {
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.BooleanArray");
                    zAreEqual = Arrays.equals((boolean[]) obj2, (boolean[]) objInvoke);
                } else if (obj2 instanceof char[]) {
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.CharArray");
                    zAreEqual = Arrays.equals((char[]) obj2, (char[]) objInvoke);
                } else if (obj2 instanceof byte[]) {
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.ByteArray");
                    zAreEqual = Arrays.equals((byte[]) obj2, (byte[]) objInvoke);
                } else if (obj2 instanceof short[]) {
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.ShortArray");
                    zAreEqual = Arrays.equals((short[]) obj2, (short[]) objInvoke);
                } else if (obj2 instanceof int[]) {
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.IntArray");
                    zAreEqual = Arrays.equals((int[]) obj2, (int[]) objInvoke);
                } else if (obj2 instanceof float[]) {
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.FloatArray");
                    zAreEqual = Arrays.equals((float[]) obj2, (float[]) objInvoke);
                } else if (obj2 instanceof long[]) {
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.LongArray");
                    zAreEqual = Arrays.equals((long[]) obj2, (long[]) objInvoke);
                } else if (obj2 instanceof double[]) {
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.DoubleArray");
                    zAreEqual = Arrays.equals((double[]) obj2, (double[]) objInvoke);
                } else if (obj2 instanceof Object[]) {
                    Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.Array<*>");
                    zAreEqual = Arrays.equals((Object[]) obj2, (Object[]) objInvoke);
                } else {
                    zAreEqual = Intrinsics.areEqual(obj2, objInvoke);
                }
                if (!zAreEqual) {
                    z = false;
                    break;
                }
            }
            z = true;
        }
        return z;
    }

    public static final Object createAnnotationInstance(final Class annotationClass, final Map values, final List methods) {
        Intrinsics.checkNotNullParameter(annotationClass, "annotationClass");
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(methods, "methods");
        final Lazy lazy = LazyKt.lazy(new Function0(values) { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$$Lambda$0
            private final Map arg$0;

            {
                this.arg$0 = values;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return Integer.valueOf(AnnotationConstructorCallerKt.createAnnotationInstance$lambda$3(this.arg$0));
            }
        });
        final Lazy lazy2 = LazyKt.lazy(new Function0(annotationClass, values) { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$$Lambda$1
            private final Class arg$0;
            private final Map arg$1;

            {
                this.arg$0 = annotationClass;
                this.arg$1 = values;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return AnnotationConstructorCallerKt.createAnnotationInstance$lambda$7(this.arg$0, this.arg$1);
            }
        });
        Object objNewProxyInstance = Proxy.newProxyInstance(annotationClass.getClassLoader(), new Class[]{annotationClass}, new InvocationHandler(annotationClass, values, lazy2, lazy, methods) { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$$Lambda$2
            private final Class arg$0;
            private final Map arg$1;
            private final Lazy arg$2;
            private final Lazy arg$3;
            private final List arg$4;

            {
                this.arg$0 = annotationClass;
                this.arg$1 = values;
                this.arg$2 = lazy2;
                this.arg$3 = lazy;
                this.arg$4 = methods;
            }

            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                return AnnotationConstructorCallerKt.createAnnotationInstance$lambda$9(this.arg$0, this.arg$1, this.arg$2, this.arg$3, this.arg$4, obj, method, objArr);
            }
        });
        Intrinsics.checkNotNull(objNewProxyInstance, "null cannot be cast to non-null type T of kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance");
        return objNewProxyInstance;
    }

    private static final int createAnnotationInstance$lambda$4(Lazy lazy) {
        return ((Number) lazy.getValue()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int createAnnotationInstance$lambda$3(Map map) {
        int iHashCode;
        int iHashCode2 = 0;
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof boolean[]) {
                iHashCode = Arrays.hashCode((boolean[]) value);
            } else if (value instanceof char[]) {
                iHashCode = Arrays.hashCode((char[]) value);
            } else if (value instanceof byte[]) {
                iHashCode = Arrays.hashCode((byte[]) value);
            } else if (value instanceof short[]) {
                iHashCode = Arrays.hashCode((short[]) value);
            } else if (value instanceof int[]) {
                iHashCode = Arrays.hashCode((int[]) value);
            } else if (value instanceof float[]) {
                iHashCode = Arrays.hashCode((float[]) value);
            } else if (value instanceof long[]) {
                iHashCode = Arrays.hashCode((long[]) value);
            } else if (value instanceof double[]) {
                iHashCode = Arrays.hashCode((double[]) value);
            } else {
                iHashCode = value instanceof Object[] ? Arrays.hashCode((Object[]) value) : value.hashCode();
            }
            iHashCode2 += iHashCode ^ (str.hashCode() * 127);
        }
        return iHashCode2;
    }

    private static final String createAnnotationInstance$lambda$8(Lazy lazy) {
        return (String) lazy.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String createAnnotationInstance$lambda$7(Class cls, Map map) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        sb.append(cls.getCanonicalName());
        CollectionsKt___CollectionsKt.joinTo(map.entrySet(), sb, (124 & 2) != 0 ? ", " : ", ", (124 & 4) != 0 ? "" : "(", (124 & 8) == 0 ? ")" : "", (124 & 16) != 0 ? -1 : 0, (124 & 32) != 0 ? "..." : null, (124 & 64) != 0 ? null : new Function1() { // from class: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt$$Lambda$3
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return AnnotationConstructorCallerKt.createAnnotationInstance$lambda$7$lambda$6$lambda$5((Map.Entry) obj);
            }
        });
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence createAnnotationInstance$lambda$7$lambda$6$lambda$5(Map.Entry entry) {
        String string;
        Intrinsics.checkNotNullParameter(entry, "entry");
        String str = (String) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof boolean[]) {
            string = Arrays.toString((boolean[]) value);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        } else if (value instanceof char[]) {
            string = Arrays.toString((char[]) value);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        } else if (value instanceof byte[]) {
            string = Arrays.toString((byte[]) value);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        } else if (value instanceof short[]) {
            string = Arrays.toString((short[]) value);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        } else if (value instanceof int[]) {
            string = Arrays.toString((int[]) value);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        } else if (value instanceof float[]) {
            string = Arrays.toString((float[]) value);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        } else if (value instanceof long[]) {
            string = Arrays.toString((long[]) value);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        } else if (value instanceof double[]) {
            string = Arrays.toString((double[]) value);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        } else if (value instanceof Object[]) {
            string = Arrays.toString((Object[]) value);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        } else {
            string = value.toString();
        }
        return str + '=' + string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object createAnnotationInstance$lambda$9(Class cls, Map map, Lazy lazy, Lazy lazy2, List list, Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        if (name != null) {
            int iHashCode = name.hashCode();
            if (iHashCode != -1776922004) {
                if (iHashCode != 147696667) {
                    if (iHashCode == 1444986633 && name.equals("annotationType")) {
                        return cls;
                    }
                } else if (name.equals("hashCode")) {
                    return Integer.valueOf(createAnnotationInstance$lambda$4(lazy2));
                }
            } else if (name.equals("toString")) {
                return createAnnotationInstance$lambda$8(lazy);
            }
        }
        if (Intrinsics.areEqual(name, "equals") && objArr != null && objArr.length == 1) {
            return Boolean.valueOf(createAnnotationInstance$equals(cls, list, map, ArraysKt.single(objArr)));
        }
        if (map.containsKey(name)) {
            return map.get(name);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Method is not supported: ");
        sb.append(method);
        sb.append(" (args: ");
        if (objArr == null) {
            objArr = new Object[0];
        }
        sb.append(ArraysKt.toList(objArr));
        sb.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
        throw new KotlinReflectionInternalError(sb.toString());
    }
}
