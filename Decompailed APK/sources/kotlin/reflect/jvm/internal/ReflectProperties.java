package kotlin.reflect.jvm.internal;

import java.lang.ref.SoftReference;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ReflectProperties {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initializer", "kotlin/reflect/jvm/internal/ReflectProperties", "lazySoft"));
    }

    public static abstract class Val {
        private static final Object NULL_VALUE = new Object() { // from class: kotlin.reflect.jvm.internal.ReflectProperties.Val.1
        };

        public abstract Object invoke();

        public final Object getValue(Object obj, Object obj2) {
            return invoke();
        }

        protected Object escape(Object obj) {
            return obj == null ? NULL_VALUE : obj;
        }

        protected Object unescape(Object obj) {
            if (obj == NULL_VALUE) {
                return null;
            }
            return obj;
        }
    }

    public static class LazySoftVal extends Val implements Function0 {
        private final Function0 initializer;
        private volatile SoftReference value;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initializer", "kotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal", "<init>"));
        }

        public LazySoftVal(Object obj, Function0 function0) {
            if (function0 == null) {
                $$$reportNull$$$0(0);
            }
            this.value = null;
            this.initializer = function0;
            if (obj != null) {
                this.value = new SoftReference(escape(obj));
            }
        }

        @Override // kotlin.reflect.jvm.internal.ReflectProperties.Val, kotlin.jvm.functions.Function0
        public Object invoke() {
            Object obj;
            SoftReference softReference = this.value;
            if (softReference != null && (obj = softReference.get()) != null) {
                return unescape(obj);
            }
            Object objInvoke = this.initializer.invoke();
            this.value = new SoftReference(escape(objInvoke));
            return objInvoke;
        }
    }

    public static LazySoftVal lazySoft(Object obj, Function0 function0) {
        if (function0 == null) {
            $$$reportNull$$$0(0);
        }
        return new LazySoftVal(obj, function0);
    }

    public static LazySoftVal lazySoft(Function0 function0) {
        if (function0 == null) {
            $$$reportNull$$$0(1);
        }
        return lazySoft(null, function0);
    }
}
