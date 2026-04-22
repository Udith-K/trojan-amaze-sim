package kotlin.reflect.jvm.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.JvmFunctionSignature;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;

/* JADX INFO: compiled from: RuntimeTypeMapper.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class JvmFunctionSignature {
    public /* synthetic */ JvmFunctionSignature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String asString();

    private JvmFunctionSignature() {
    }

    /* JADX INFO: compiled from: RuntimeTypeMapper.kt */
    public static final class KotlinFunction extends JvmFunctionSignature {
        private final String _signature;
        private final JvmMemberSignature.Method signature;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KotlinFunction(JvmMemberSignature.Method signature) {
            super(null);
            Intrinsics.checkNotNullParameter(signature, "signature");
            this.signature = signature;
            this._signature = signature.asString();
        }

        public final String getMethodName() {
            return this.signature.getName();
        }

        public final String getMethodDesc() {
            return this.signature.getDesc();
        }

        @Override // kotlin.reflect.jvm.internal.JvmFunctionSignature
        public String asString() {
            return this._signature;
        }
    }

    /* JADX INFO: compiled from: RuntimeTypeMapper.kt */
    public static final class KotlinConstructor extends JvmFunctionSignature {
        private final String _signature;
        private final JvmMemberSignature.Method signature;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KotlinConstructor(JvmMemberSignature.Method signature) {
            super(null);
            Intrinsics.checkNotNullParameter(signature, "signature");
            this.signature = signature;
            this._signature = signature.asString();
        }

        public final String getConstructorDesc() {
            return this.signature.getDesc();
        }

        @Override // kotlin.reflect.jvm.internal.JvmFunctionSignature
        public String asString() {
            return this._signature;
        }
    }

    /* JADX INFO: compiled from: RuntimeTypeMapper.kt */
    public static final class JavaMethod extends JvmFunctionSignature {
        private final Method method;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JavaMethod(Method method) {
            super(null);
            Intrinsics.checkNotNullParameter(method, "method");
            this.method = method;
        }

        public final Method getMethod() {
            return this.method;
        }

        @Override // kotlin.reflect.jvm.internal.JvmFunctionSignature
        public String asString() {
            return RuntimeTypeMapperKt.getSignature(this.method);
        }
    }

    /* JADX INFO: compiled from: RuntimeTypeMapper.kt */
    public static final class JavaConstructor extends JvmFunctionSignature {
        private final Constructor constructor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JavaConstructor(Constructor constructor) {
            super(null);
            Intrinsics.checkNotNullParameter(constructor, "constructor");
            this.constructor = constructor;
        }

        public final Constructor getConstructor() {
            return this.constructor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence asString$lambda$0(Class cls) {
            Intrinsics.checkNotNull(cls);
            return ReflectClassUtilKt.getDesc(cls);
        }

        @Override // kotlin.reflect.jvm.internal.JvmFunctionSignature
        public String asString() {
            Class<?>[] parameterTypes = this.constructor.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
            return ArraysKt.joinToString$default(parameterTypes, "", "<init>(", ")V", 0, (CharSequence) null, new Function1() { // from class: kotlin.reflect.jvm.internal.JvmFunctionSignature$JavaConstructor$$Lambda$0
                @Override // kotlin.jvm.functions.Function1
                public Object invoke(Object obj) {
                    return JvmFunctionSignature.JavaConstructor.asString$lambda$0((Class) obj);
                }
            }, 24, (Object) null);
        }
    }

    /* JADX INFO: compiled from: RuntimeTypeMapper.kt */
    public static final class FakeJavaAnnotationConstructor extends JvmFunctionSignature {
        private final Class jClass;
        private final List methods;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FakeJavaAnnotationConstructor(Class jClass) {
            super(null);
            Intrinsics.checkNotNullParameter(jClass, "jClass");
            this.jClass = jClass;
            Method[] declaredMethods = jClass.getDeclaredMethods();
            Intrinsics.checkNotNullExpressionValue(declaredMethods, "getDeclaredMethods(...)");
            this.methods = ArraysKt.sortedWith(declaredMethods, new Comparator() { // from class: kotlin.reflect.jvm.internal.JvmFunctionSignature$FakeJavaAnnotationConstructor$special$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ComparisonsKt.compareValues(((Method) obj).getName(), ((Method) obj2).getName());
                }
            });
        }

        public final List getMethods() {
            return this.methods;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence asString$lambda$1(Method method) {
            Class<?> returnType = method.getReturnType();
            Intrinsics.checkNotNullExpressionValue(returnType, "getReturnType(...)");
            return ReflectClassUtilKt.getDesc(returnType);
        }

        @Override // kotlin.reflect.jvm.internal.JvmFunctionSignature
        public String asString() {
            return CollectionsKt.joinToString$default(this.methods, "", "<init>(", ")V", 0, null, new Function1() { // from class: kotlin.reflect.jvm.internal.JvmFunctionSignature$FakeJavaAnnotationConstructor$$Lambda$0
                @Override // kotlin.jvm.functions.Function1
                public Object invoke(Object obj) {
                    return JvmFunctionSignature.FakeJavaAnnotationConstructor.asString$lambda$1((Method) obj);
                }
            }, 24, null);
        }
    }
}
