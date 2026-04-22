package kotlin.jvm.internal;

import ch.qos.logback.classic.pattern.CallerDataConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.core.CoreConstants;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;

/* JADX INFO: compiled from: TypeReference.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class TypeReference implements KType {
    public static final Companion Companion = new Companion(null);
    private final List arguments;
    private final KClassifier classifier;
    private final int flags;
    private final KType platformTypeUpperBound;

    /* JADX INFO: compiled from: TypeReference.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KVariance.values().length];
            try {
                iArr[KVariance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KVariance.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KVariance.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public TypeReference(KClassifier classifier, List arguments, KType kType, int i) {
        Intrinsics.checkNotNullParameter(classifier, "classifier");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        this.classifier = classifier;
        this.arguments = arguments;
        this.platformTypeUpperBound = kType;
        this.flags = i;
    }

    @Override // kotlin.reflect.KType
    public KClassifier getClassifier() {
        return this.classifier;
    }

    @Override // kotlin.reflect.KType
    public List getArguments() {
        return this.arguments;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TypeReference(KClassifier classifier, List arguments, boolean z) {
        this(classifier, arguments, null, z ? 1 : 0);
        Intrinsics.checkNotNullParameter(classifier, "classifier");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
    }

    @Override // kotlin.reflect.KType
    public boolean isMarkedNullable() {
        return (this.flags & 1) != 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TypeReference) {
            TypeReference typeReference = (TypeReference) obj;
            if (Intrinsics.areEqual(getClassifier(), typeReference.getClassifier()) && Intrinsics.areEqual(getArguments(), typeReference.getArguments()) && Intrinsics.areEqual(this.platformTypeUpperBound, typeReference.platformTypeUpperBound) && this.flags == typeReference.flags) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((getClassifier().hashCode() * 31) + getArguments().hashCode()) * 31) + this.flags;
    }

    public String toString() {
        return asString(false) + " (Kotlin reflection is not available)";
    }

    private final String asString(boolean z) {
        String name;
        KClassifier classifier = getClassifier();
        KClass kClass = classifier instanceof KClass ? (KClass) classifier : null;
        Class javaClass = kClass != null ? JvmClassMappingKt.getJavaClass(kClass) : null;
        if (javaClass == null) {
            name = getClassifier().toString();
        } else if ((this.flags & 4) != 0) {
            name = "kotlin.Nothing";
        } else if (javaClass.isArray()) {
            name = getArrayClassName(javaClass);
        } else if (z && javaClass.isPrimitive()) {
            KClassifier classifier2 = getClassifier();
            Intrinsics.checkNotNull(classifier2, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
            name = JvmClassMappingKt.getJavaObjectType((KClass) classifier2).getName();
        } else {
            name = javaClass.getName();
        }
        String str = name + (getArguments().isEmpty() ? "" : CollectionsKt.joinToString$default(getArguments(), ", ", "<", ">", 0, null, new Function1() { // from class: kotlin.jvm.internal.TypeReference$asString$args$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(KTypeProjection it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return this.this$0.asString(it);
            }
        }, 24, null)) + (isMarkedNullable() ? CallerData.NA : "");
        KType kType = this.platformTypeUpperBound;
        if (!(kType instanceof TypeReference)) {
            return str;
        }
        String strAsString = ((TypeReference) kType).asString(true);
        if (Intrinsics.areEqual(strAsString, str)) {
            return str;
        }
        if (Intrinsics.areEqual(strAsString, str + '?')) {
            return str + '!';
        }
        return CoreConstants.LEFT_PARENTHESIS_CHAR + str + CallerDataConverter.DEFAULT_RANGE_DELIMITER + strAsString + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    private final String getArrayClassName(Class cls) {
        return Intrinsics.areEqual(cls, boolean[].class) ? "kotlin.BooleanArray" : Intrinsics.areEqual(cls, char[].class) ? "kotlin.CharArray" : Intrinsics.areEqual(cls, byte[].class) ? "kotlin.ByteArray" : Intrinsics.areEqual(cls, short[].class) ? "kotlin.ShortArray" : Intrinsics.areEqual(cls, int[].class) ? "kotlin.IntArray" : Intrinsics.areEqual(cls, float[].class) ? "kotlin.FloatArray" : Intrinsics.areEqual(cls, long[].class) ? "kotlin.LongArray" : Intrinsics.areEqual(cls, double[].class) ? "kotlin.DoubleArray" : "kotlin.Array";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String asString(KTypeProjection kTypeProjection) {
        String strValueOf;
        if (kTypeProjection.getVariance() == null) {
            return "*";
        }
        KType type = kTypeProjection.getType();
        TypeReference typeReference = type instanceof TypeReference ? (TypeReference) type : null;
        if (typeReference == null || (strValueOf = typeReference.asString(true)) == null) {
            strValueOf = String.valueOf(kTypeProjection.getType());
        }
        int i = WhenMappings.$EnumSwitchMapping$0[kTypeProjection.getVariance().ordinal()];
        if (i == 1) {
            return strValueOf;
        }
        if (i == 2) {
            return "in " + strValueOf;
        }
        if (i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        return "out " + strValueOf;
    }

    /* JADX INFO: compiled from: TypeReference.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
