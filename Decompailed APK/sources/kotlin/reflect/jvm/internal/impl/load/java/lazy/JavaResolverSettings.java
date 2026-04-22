package kotlin.reflect.jvm.internal.impl.load.java.lazy;

/* JADX INFO: compiled from: context.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface JavaResolverSettings {
    boolean getCorrectNullabilityForNotNullTypeParameter();

    boolean getEnhancePrimitiveArrays();

    boolean getIgnoreNullabilityForErasedValueParameters();

    boolean getTypeEnhancementImprovementsInStrictMode();

    /* JADX INFO: compiled from: context.kt */
    public static final class Default implements JavaResolverSettings {
        public static final Default INSTANCE = new Default();

        @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings
        public boolean getCorrectNullabilityForNotNullTypeParameter() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings
        public boolean getEnhancePrimitiveArrays() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings
        public boolean getIgnoreNullabilityForErasedValueParameters() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings
        public boolean getTypeEnhancementImprovementsInStrictMode() {
            return false;
        }

        private Default() {
        }
    }
}
