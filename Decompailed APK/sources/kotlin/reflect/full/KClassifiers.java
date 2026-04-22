package kotlin.reflect.full;

import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.KClassifierImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionBase;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX INFO: compiled from: KClassifiers.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class KClassifiers {

    /* JADX INFO: compiled from: KClassifiers.kt */
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

    public static final KType createType(KClassifier kClassifier, List arguments, boolean z, List annotations) {
        ClassifierDescriptor descriptor;
        Intrinsics.checkNotNullParameter(kClassifier, "<this>");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        KClassifierImpl kClassifierImpl = kClassifier instanceof KClassifierImpl ? (KClassifierImpl) kClassifier : null;
        if (kClassifierImpl == null || (descriptor = kClassifierImpl.getDescriptor()) == null) {
            throw new KotlinReflectionInternalError("Cannot create type for an unsupported classifier: " + kClassifier + " (" + kClassifier.getClass() + CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }
        TypeConstructor typeConstructor = descriptor.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "getTypeConstructor(...)");
        List parameters = typeConstructor.getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
        if (parameters.size() != arguments.size()) {
            throw new IllegalArgumentException("Class declares " + parameters.size() + " type parameters, but " + arguments.size() + " were provided.");
        }
        return new KTypeImpl(createKotlinType(annotations.isEmpty() ? TypeAttributes.Companion.getEmpty() : TypeAttributes.Companion.getEmpty(), typeConstructor, arguments, z), null, 2, null);
    }

    private static final SimpleType createKotlinType(TypeAttributes typeAttributes, TypeConstructor typeConstructor, List list, boolean z) {
        TypeProjectionBase starProjectionImpl;
        List parameters = typeConstructor.getParameters();
        Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KTypeProjection kTypeProjection = (KTypeProjection) obj;
            KTypeImpl kTypeImpl = (KTypeImpl) kTypeProjection.getType();
            KotlinType type = kTypeImpl != null ? kTypeImpl.getType() : null;
            KVariance variance = kTypeProjection.getVariance();
            int i3 = variance == null ? -1 : WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
            if (i3 == -1) {
                Object obj2 = parameters.get(i);
                Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                starProjectionImpl = new StarProjectionImpl((TypeParameterDescriptor) obj2);
            } else if (i3 == 1) {
                Variance variance2 = Variance.INVARIANT;
                Intrinsics.checkNotNull(type);
                starProjectionImpl = new TypeProjectionImpl(variance2, type);
            } else if (i3 == 2) {
                Variance variance3 = Variance.IN_VARIANCE;
                Intrinsics.checkNotNull(type);
                starProjectionImpl = new TypeProjectionImpl(variance3, type);
            } else {
                if (i3 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                Variance variance4 = Variance.OUT_VARIANCE;
                Intrinsics.checkNotNull(type);
                starProjectionImpl = new TypeProjectionImpl(variance4, type);
            }
            arrayList.add(starProjectionImpl);
            i = i2;
        }
        return KotlinTypeFactory.simpleType$default(typeAttributes, typeConstructor, arrayList, z, null, 16, null);
    }
}
