package kotlin.reflect.jvm.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KTypeBase;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KProperty;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.jvm.KTypesJvm;
import kotlin.reflect.jvm.internal.ReflectProperties;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* JADX INFO: compiled from: KTypeImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class KTypeImpl implements KTypeBase {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KTypeImpl.class), "classifier", "getClassifier()Lkotlin/reflect/KClassifier;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KTypeImpl.class), "arguments", "getArguments()Ljava/util/List;"))};
    private final ReflectProperties.LazySoftVal arguments$delegate;
    private final ReflectProperties.LazySoftVal classifier$delegate;
    private final ReflectProperties.LazySoftVal computeJavaType;
    private final KotlinType type;

    /* JADX INFO: compiled from: KTypeImpl.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.IN_VARIANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Variance.OUT_VARIANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public KTypeImpl(KotlinType type, final Function0 function0) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        ReflectProperties.LazySoftVal lazySoftValLazySoft = null;
        ReflectProperties.LazySoftVal lazySoftVal = function0 instanceof ReflectProperties.LazySoftVal ? (ReflectProperties.LazySoftVal) function0 : null;
        if (lazySoftVal != null) {
            lazySoftValLazySoft = lazySoftVal;
        } else if (function0 != null) {
            lazySoftValLazySoft = ReflectProperties.lazySoft(function0);
        }
        this.computeJavaType = lazySoftValLazySoft;
        this.classifier$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KTypeImpl$$Lambda$0
            private final KTypeImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KTypeImpl.classifier_delegate$lambda$0(this.arg$0);
            }
        });
        this.arguments$delegate = ReflectProperties.lazySoft(new Function0(this, function0) { // from class: kotlin.reflect.jvm.internal.KTypeImpl$$Lambda$1
            private final KTypeImpl arg$0;
            private final Function0 arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = function0;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KTypeImpl.arguments_delegate$lambda$5(this.arg$0, this.arg$1);
            }
        });
    }

    public /* synthetic */ KTypeImpl(KotlinType kotlinType, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kotlinType, (i & 2) != 0 ? null : function0);
    }

    public final KotlinType getType() {
        return this.type;
    }

    @Override // kotlin.jvm.internal.KTypeBase
    public Type getJavaType() {
        ReflectProperties.LazySoftVal lazySoftVal = this.computeJavaType;
        if (lazySoftVal != null) {
            return (Type) lazySoftVal.invoke();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KClassifier classifier_delegate$lambda$0(KTypeImpl kTypeImpl) {
        return kTypeImpl.convert(kTypeImpl.type);
    }

    @Override // kotlin.reflect.KType
    public KClassifier getClassifier() {
        return (KClassifier) this.classifier$delegate.getValue(this, $$delegatedProperties[0]);
    }

    private final KClassifier convert(KotlinType kotlinType) {
        KotlinType type;
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = kotlinType.getConstructor().mo2781getDeclarationDescriptor();
        if (classifierDescriptorMo2781getDeclarationDescriptor instanceof ClassDescriptor) {
            Class javaClass = UtilKt.toJavaClass((ClassDescriptor) classifierDescriptorMo2781getDeclarationDescriptor);
            if (javaClass == null) {
                return null;
            }
            if (javaClass.isArray()) {
                TypeProjection typeProjection = (TypeProjection) CollectionsKt.singleOrNull(kotlinType.getArguments());
                if (typeProjection == null || (type = typeProjection.getType()) == null) {
                    return new KClassImpl(javaClass);
                }
                KClassifier kClassifierConvert = convert(type);
                if (kClassifierConvert == null) {
                    throw new KotlinReflectionInternalError("Cannot determine classifier for array element type: " + this);
                }
                return new KClassImpl(UtilKt.createArrayType(JvmClassMappingKt.getJavaClass(KTypesJvm.getJvmErasure(kClassifierConvert))));
            }
            if (!TypeUtils.isNullableType(kotlinType)) {
                Class primitiveByWrapper = ReflectClassUtilKt.getPrimitiveByWrapper(javaClass);
                if (primitiveByWrapper != null) {
                    javaClass = primitiveByWrapper;
                }
                return new KClassImpl(javaClass);
            }
            return new KClassImpl(javaClass);
        }
        if (classifierDescriptorMo2781getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return new KTypeParameterImpl(null, (TypeParameterDescriptor) classifierDescriptorMo2781getDeclarationDescriptor);
        }
        if (!(classifierDescriptorMo2781getDeclarationDescriptor instanceof TypeAliasDescriptor)) {
            return null;
        }
        throw new NotImplementedError("An operation is not implemented: Type alias classifiers are not yet supported");
    }

    @Override // kotlin.reflect.KType
    public List getArguments() {
        Object value = this.arguments$delegate.getValue(this, $$delegatedProperties[1]);
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (List) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List arguments_delegate$lambda$5(final KTypeImpl kTypeImpl, Function0 function0) {
        KTypeProjection kTypeProjectionInvariant;
        List arguments = kTypeImpl.type.getArguments();
        if (arguments.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(kTypeImpl) { // from class: kotlin.reflect.jvm.internal.KTypeImpl$$Lambda$2
            private final KTypeImpl arg$0;

            {
                this.arg$0 = kTypeImpl;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KTypeImpl.arguments_delegate$lambda$5$lambda$1(this.arg$0);
            }
        });
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        final int i = 0;
        for (Object obj : arguments) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TypeProjection typeProjection = (TypeProjection) obj;
            if (typeProjection.isStarProjection()) {
                kTypeProjectionInvariant = KTypeProjection.Companion.getSTAR();
            } else {
                KotlinType type = typeProjection.getType();
                Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                KTypeImpl kTypeImpl2 = new KTypeImpl(type, function0 == null ? null : new Function0(kTypeImpl, i, lazy) { // from class: kotlin.reflect.jvm.internal.KTypeImpl$$Lambda$3
                    private final KTypeImpl arg$0;
                    private final int arg$1;
                    private final Lazy arg$2;

                    {
                        this.arg$0 = kTypeImpl;
                        this.arg$1 = i;
                        this.arg$2 = lazy;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return KTypeImpl.arguments_delegate$lambda$5$lambda$4$lambda$3(this.arg$0, this.arg$1, this.arg$2);
                    }
                });
                int i3 = WhenMappings.$EnumSwitchMapping$0[typeProjection.getProjectionKind().ordinal()];
                if (i3 == 1) {
                    kTypeProjectionInvariant = KTypeProjection.Companion.invariant(kTypeImpl2);
                } else if (i3 == 2) {
                    kTypeProjectionInvariant = KTypeProjection.Companion.contravariant(kTypeImpl2);
                } else {
                    if (i3 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    kTypeProjectionInvariant = KTypeProjection.Companion.covariant(kTypeImpl2);
                }
            }
            arrayList.add(kTypeProjectionInvariant);
            i = i2;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List arguments_delegate$lambda$5$lambda$1(KTypeImpl kTypeImpl) {
        Type javaType = kTypeImpl.getJavaType();
        Intrinsics.checkNotNull(javaType);
        return ReflectClassUtilKt.getParameterizedTypeArguments(javaType);
    }

    private static final List arguments_delegate$lambda$5$lambda$2(Lazy lazy) {
        return (List) lazy.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Type arguments_delegate$lambda$5$lambda$4$lambda$3(KTypeImpl kTypeImpl, int i, Lazy lazy) {
        Type type;
        Type javaType = kTypeImpl.getJavaType();
        if (javaType instanceof Class) {
            Class cls = (Class) javaType;
            Class componentType = cls.isArray() ? cls.getComponentType() : Object.class;
            Intrinsics.checkNotNull(componentType);
            return componentType;
        }
        if (javaType instanceof GenericArrayType) {
            if (i != 0) {
                throw new KotlinReflectionInternalError("Array type has been queried for a non-0th argument: " + kTypeImpl);
            }
            Type genericComponentType = ((GenericArrayType) javaType).getGenericComponentType();
            Intrinsics.checkNotNull(genericComponentType);
            return genericComponentType;
        }
        if (javaType instanceof ParameterizedType) {
            Type type2 = (Type) arguments_delegate$lambda$5$lambda$2(lazy).get(i);
            if (!(type2 instanceof WildcardType)) {
                return type2;
            }
            WildcardType wildcardType = (WildcardType) type2;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Intrinsics.checkNotNullExpressionValue(lowerBounds, "getLowerBounds(...)");
            Type type3 = (Type) ArraysKt.firstOrNull(lowerBounds);
            if (type3 == null) {
                Type[] upperBounds = wildcardType.getUpperBounds();
                Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
                type = (Type) ArraysKt.first(upperBounds);
            } else {
                type = type3;
            }
            Intrinsics.checkNotNull(type);
            return type;
        }
        throw new KotlinReflectionInternalError("Non-generic type has been queried for arguments: " + kTypeImpl);
    }

    @Override // kotlin.reflect.KType
    public boolean isMarkedNullable() {
        return this.type.isMarkedNullable();
    }

    public boolean equals(Object obj) {
        if (obj instanceof KTypeImpl) {
            KTypeImpl kTypeImpl = (KTypeImpl) obj;
            if (Intrinsics.areEqual(this.type, kTypeImpl.type) && Intrinsics.areEqual(getClassifier(), kTypeImpl.getClassifier()) && Intrinsics.areEqual(getArguments(), kTypeImpl.getArguments())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = this.type.hashCode() * 31;
        KClassifier classifier = getClassifier();
        return ((iHashCode + (classifier != null ? classifier.hashCode() : 0)) * 31) + getArguments().hashCode();
    }

    public String toString() {
        return ReflectionObjectRenderer.INSTANCE.renderType(this.type);
    }
}
