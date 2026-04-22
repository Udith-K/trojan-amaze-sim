package kotlin.reflect.jvm;

import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.calls.Caller;

/* JADX INFO: compiled from: KCallablesJvm.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class KCallablesJvm {
    public static final boolean isAccessible(KCallable kCallable) {
        Caller defaultCaller;
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        if (kCallable instanceof KMutableProperty) {
            KProperty kProperty = (KProperty) kCallable;
            Field javaField = ReflectJvmMapping.getJavaField(kProperty);
            if (!(javaField != null ? javaField.isAccessible() : true)) {
                return false;
            }
            Method javaGetter = ReflectJvmMapping.getJavaGetter(kProperty);
            if (!(javaGetter != null ? javaGetter.isAccessible() : true)) {
                return false;
            }
            Method javaSetter = ReflectJvmMapping.getJavaSetter((KMutableProperty) kCallable);
            if (!(javaSetter != null ? javaSetter.isAccessible() : true)) {
                return false;
            }
        } else if (kCallable instanceof KProperty) {
            KProperty kProperty2 = (KProperty) kCallable;
            Field javaField2 = ReflectJvmMapping.getJavaField(kProperty2);
            if (!(javaField2 != null ? javaField2.isAccessible() : true)) {
                return false;
            }
            Method javaGetter2 = ReflectJvmMapping.getJavaGetter(kProperty2);
            if (!(javaGetter2 != null ? javaGetter2.isAccessible() : true)) {
                return false;
            }
        } else if (kCallable instanceof KProperty.Getter) {
            Field javaField3 = ReflectJvmMapping.getJavaField(((KProperty.Getter) kCallable).getProperty());
            if (!(javaField3 != null ? javaField3.isAccessible() : true)) {
                return false;
            }
            Method javaMethod = ReflectJvmMapping.getJavaMethod((KFunction) kCallable);
            if (!(javaMethod != null ? javaMethod.isAccessible() : true)) {
                return false;
            }
        } else if (kCallable instanceof KMutableProperty.Setter) {
            Field javaField4 = ReflectJvmMapping.getJavaField(((KMutableProperty.Setter) kCallable).getProperty());
            if (!(javaField4 != null ? javaField4.isAccessible() : true)) {
                return false;
            }
            Method javaMethod2 = ReflectJvmMapping.getJavaMethod((KFunction) kCallable);
            if (!(javaMethod2 != null ? javaMethod2.isAccessible() : true)) {
                return false;
            }
        } else if (kCallable instanceof KFunction) {
            KFunction kFunction = (KFunction) kCallable;
            Method javaMethod3 = ReflectJvmMapping.getJavaMethod(kFunction);
            if (!(javaMethod3 != null ? javaMethod3.isAccessible() : true)) {
                return false;
            }
            KCallableImpl kCallableImplAsKCallableImpl = UtilKt.asKCallableImpl(kCallable);
            Member memberMo2745getMember = (kCallableImplAsKCallableImpl == null || (defaultCaller = kCallableImplAsKCallableImpl.getDefaultCaller()) == null) ? null : defaultCaller.mo2745getMember();
            AccessibleObject accessibleObject = memberMo2745getMember instanceof AccessibleObject ? (AccessibleObject) memberMo2745getMember : null;
            if (!(accessibleObject != null ? accessibleObject.isAccessible() : true)) {
                return false;
            }
            Constructor javaConstructor = ReflectJvmMapping.getJavaConstructor(kFunction);
            if (!(javaConstructor != null ? javaConstructor.isAccessible() : true)) {
                return false;
            }
        } else {
            throw new UnsupportedOperationException("Unknown callable: " + kCallable + " (" + kCallable.getClass() + CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }
        return true;
    }
}
