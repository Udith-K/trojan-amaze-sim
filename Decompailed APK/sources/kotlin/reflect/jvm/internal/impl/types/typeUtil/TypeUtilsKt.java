package kotlin.reflect.jvm.internal.impl.types.typeUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorType;

/* JADX INFO: compiled from: TypeUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class TypeUtilsKt {
    public static final boolean hasTypeParameterRecursiveBounds(TypeParameterDescriptor typeParameter) {
        Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
        return hasTypeParameterRecursiveBounds$default(typeParameter, null, null, 6, null);
    }

    public static final KotlinBuiltIns getBuiltIns(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        KotlinBuiltIns builtIns = kotlinType.getConstructor().getBuiltIns();
        Intrinsics.checkNotNullExpressionValue(builtIns, "getBuiltIns(...)");
        return builtIns;
    }

    public static final KotlinType makeNullable(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        KotlinType kotlinTypeMakeNullable = TypeUtils.makeNullable(kotlinType);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNullable, "makeNullable(...)");
        return kotlinTypeMakeNullable;
    }

    public static final KotlinType makeNotNullable(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        KotlinType kotlinTypeMakeNotNullable = TypeUtils.makeNotNullable(kotlinType);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNotNullable, "makeNotNullable(...)");
        return kotlinTypeMakeNotNullable;
    }

    public static final boolean isNothing(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return KotlinBuiltIns.isNothing(kotlinType);
    }

    public static final boolean isBoolean(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return KotlinBuiltIns.isBoolean(kotlinType);
    }

    public static final boolean isTypeParameter(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return TypeUtils.isTypeParameter(kotlinType);
    }

    public static final boolean containsTypeParameter(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return TypeUtils.contains(kotlinType, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt$$Lambda$0
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return TypeUtilsKt.containsTypeParameter$lambda$0((UnwrappedType) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean containsTypeParameter$lambda$0(UnwrappedType unwrappedType) {
        return Boolean.valueOf(TypeUtils.isTypeParameter(unwrappedType));
    }

    public static final boolean isSubtypeOf(KotlinType kotlinType, KotlinType superType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return KotlinTypeChecker.DEFAULT.isSubtypeOf(kotlinType, superType);
    }

    public static final KotlinType replaceAnnotations(KotlinType kotlinType, Annotations newAnnotations) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        Intrinsics.checkNotNullParameter(newAnnotations, "newAnnotations");
        return (kotlinType.getAnnotations().isEmpty() && newAnnotations.isEmpty()) ? kotlinType : kotlinType.unwrap().replaceAttributes(TypeAttributesKt.replaceAnnotations(kotlinType.getAttributes(), newAnnotations));
    }

    public static final TypeProjection createProjection(KotlinType type, Variance projectionKind, TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(projectionKind, "projectionKind");
        if ((typeParameterDescriptor != null ? typeParameterDescriptor.getVariance() : null) == projectionKind) {
            projectionKind = Variance.INVARIANT;
        }
        return new TypeProjectionImpl(projectionKind, type);
    }

    public static final TypeProjection asTypeProjection(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return new TypeProjectionImpl(kotlinType);
    }

    public static final boolean contains(KotlinType kotlinType, Function1 predicate) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        return TypeUtils.contains(kotlinType, predicate);
    }

    public static final Set extractTypeParametersFromUpperBounds(KotlinType kotlinType, Set set) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        extractTypeParametersFromUpperBounds(kotlinType, kotlinType, linkedHashSet, set);
        return linkedHashSet;
    }

    private static final void extractTypeParametersFromUpperBounds(KotlinType kotlinType, KotlinType kotlinType2, Set set, Set set2) {
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = kotlinType.getConstructor().mo2781getDeclarationDescriptor();
        if (classifierDescriptorMo2781getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            if (!Intrinsics.areEqual(kotlinType.getConstructor(), kotlinType2.getConstructor())) {
                set.add(classifierDescriptorMo2781getDeclarationDescriptor);
                return;
            }
            for (KotlinType kotlinType3 : ((TypeParameterDescriptor) classifierDescriptorMo2781getDeclarationDescriptor).getUpperBounds()) {
                Intrinsics.checkNotNull(kotlinType3);
                extractTypeParametersFromUpperBounds(kotlinType3, kotlinType2, set, set2);
            }
            return;
        }
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor2 = kotlinType.getConstructor().mo2781getDeclarationDescriptor();
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters = classifierDescriptorMo2781getDeclarationDescriptor2 instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) classifierDescriptorMo2781getDeclarationDescriptor2 : null;
        List declaredTypeParameters = classifierDescriptorWithTypeParameters != null ? classifierDescriptorWithTypeParameters.getDeclaredTypeParameters() : null;
        int i = 0;
        for (TypeProjection typeProjection : kotlinType.getArguments()) {
            int i2 = i + 1;
            TypeParameterDescriptor typeParameterDescriptor = declaredTypeParameters != null ? (TypeParameterDescriptor) CollectionsKt.getOrNull(declaredTypeParameters, i) : null;
            if ((typeParameterDescriptor == null || set2 == null || !set2.contains(typeParameterDescriptor)) && !typeProjection.isStarProjection() && !CollectionsKt.contains(set, typeProjection.getType().getConstructor().mo2781getDeclarationDescriptor()) && !Intrinsics.areEqual(typeProjection.getType().getConstructor(), kotlinType2.getConstructor())) {
                KotlinType type = typeProjection.getType();
                Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                extractTypeParametersFromUpperBounds(type, kotlinType2, set, set2);
            }
            i = i2;
        }
    }

    public static /* synthetic */ boolean hasTypeParameterRecursiveBounds$default(TypeParameterDescriptor typeParameterDescriptor, TypeConstructor typeConstructor, Set set, int i, Object obj) {
        if ((i & 2) != 0) {
            typeConstructor = null;
        }
        if ((i & 4) != 0) {
            set = null;
        }
        return hasTypeParameterRecursiveBounds(typeParameterDescriptor, typeConstructor, set);
    }

    public static final boolean hasTypeParameterRecursiveBounds(TypeParameterDescriptor typeParameter, TypeConstructor typeConstructor, Set set) {
        Intrinsics.checkNotNullParameter(typeParameter, "typeParameter");
        List<KotlinType> upperBounds = typeParameter.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
        if ((upperBounds instanceof Collection) && upperBounds.isEmpty()) {
            return false;
        }
        for (KotlinType kotlinType : upperBounds) {
            Intrinsics.checkNotNull(kotlinType);
            if (containsSelfTypeParameter(kotlinType, typeParameter.getDefaultType().getConstructor(), set) && (typeConstructor == null || Intrinsics.areEqual(kotlinType.getConstructor(), typeConstructor))) {
                return true;
            }
        }
        return false;
    }

    private static final boolean containsSelfTypeParameter(KotlinType kotlinType, TypeConstructor typeConstructor, Set set) {
        boolean zContainsSelfTypeParameter;
        if (Intrinsics.areEqual(kotlinType.getConstructor(), typeConstructor)) {
            return true;
        }
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = kotlinType.getConstructor().mo2781getDeclarationDescriptor();
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters = classifierDescriptorMo2781getDeclarationDescriptor instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) classifierDescriptorMo2781getDeclarationDescriptor : null;
        List declaredTypeParameters = classifierDescriptorWithTypeParameters != null ? classifierDescriptorWithTypeParameters.getDeclaredTypeParameters() : null;
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(kotlinType.getArguments());
        if (!(iterableWithIndex instanceof Collection) || !((Collection) iterableWithIndex).isEmpty()) {
            for (IndexedValue indexedValue : iterableWithIndex) {
                int iComponent1 = indexedValue.component1();
                TypeProjection typeProjection = (TypeProjection) indexedValue.component2();
                TypeParameterDescriptor typeParameterDescriptor = declaredTypeParameters != null ? (TypeParameterDescriptor) CollectionsKt.getOrNull(declaredTypeParameters, iComponent1) : null;
                if ((typeParameterDescriptor == null || set == null || !set.contains(typeParameterDescriptor)) && !typeProjection.isStarProjection()) {
                    KotlinType type = typeProjection.getType();
                    Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                    zContainsSelfTypeParameter = containsSelfTypeParameter(type, typeConstructor, set);
                } else {
                    zContainsSelfTypeParameter = false;
                }
                if (zContainsSelfTypeParameter) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean containsTypeAliasParameters(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return contains(kotlinType, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt$$Lambda$2
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return Boolean.valueOf(TypeUtilsKt.containsTypeAliasParameters$lambda$13((UnwrappedType) obj));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsTypeAliasParameters$lambda$13(UnwrappedType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = it.getConstructor().mo2781getDeclarationDescriptor();
        if (classifierDescriptorMo2781getDeclarationDescriptor != null) {
            return isTypeAliasParameter(classifierDescriptorMo2781getDeclarationDescriptor);
        }
        return false;
    }

    public static final boolean isTypeAliasParameter(ClassifierDescriptor classifierDescriptor) {
        Intrinsics.checkNotNullParameter(classifierDescriptor, "<this>");
        return (classifierDescriptor instanceof TypeParameterDescriptor) && (((TypeParameterDescriptor) classifierDescriptor).getContainingDeclaration() instanceof TypeAliasDescriptor);
    }

    public static final boolean requiresTypeAliasExpansion(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return contains(kotlinType, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt$$Lambda$4
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return Boolean.valueOf(TypeUtilsKt.requiresTypeAliasExpansion$lambda$16((UnwrappedType) obj));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean requiresTypeAliasExpansion$lambda$16(UnwrappedType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = it.getConstructor().mo2781getDeclarationDescriptor();
        if (classifierDescriptorMo2781getDeclarationDescriptor != null) {
            return (classifierDescriptorMo2781getDeclarationDescriptor instanceof TypeAliasDescriptor) || (classifierDescriptorMo2781getDeclarationDescriptor instanceof TypeParameterDescriptor);
        }
        return false;
    }

    public static final KotlinType getRepresentativeUpperBound(TypeParameterDescriptor typeParameterDescriptor) {
        Object obj;
        Intrinsics.checkNotNullParameter(typeParameterDescriptor, "<this>");
        List upperBounds = typeParameterDescriptor.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
        upperBounds.isEmpty();
        List upperBounds2 = typeParameterDescriptor.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds2, "getUpperBounds(...)");
        Iterator it = upperBounds2.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = ((KotlinType) next).getConstructor().mo2781getDeclarationDescriptor();
            ClassDescriptor classDescriptor = classifierDescriptorMo2781getDeclarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) classifierDescriptorMo2781getDeclarationDescriptor : null;
            if (classDescriptor != null && classDescriptor.getKind() != ClassKind.INTERFACE && classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
                obj = next;
                break;
            }
        }
        KotlinType kotlinType = (KotlinType) obj;
        if (kotlinType != null) {
            return kotlinType;
        }
        List upperBounds3 = typeParameterDescriptor.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds3, "getUpperBounds(...)");
        Object objFirst = CollectionsKt.first(upperBounds3);
        Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
        return (KotlinType) objFirst;
    }

    public static final boolean isUnresolvedType(KotlinType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return (type instanceof ErrorType) && ((ErrorType) type).getKind().isUnresolved();
    }

    public static final KotlinType replaceArgumentsWithStarProjections(KotlinType kotlinType) {
        UnwrappedType unwrappedTypeReplace$default;
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        UnwrappedType unwrappedTypeUnwrap = kotlinType.unwrap();
        if (unwrappedTypeUnwrap instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedTypeUnwrap;
            SimpleType lowerBound = flexibleType.getLowerBound();
            if (!lowerBound.getConstructor().getParameters().isEmpty() && lowerBound.getConstructor().mo2781getDeclarationDescriptor() != null) {
                List parameters = lowerBound.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
                Iterator it = parameters.iterator();
                while (it.hasNext()) {
                    arrayList.add(new StarProjectionImpl((TypeParameterDescriptor) it.next()));
                }
                lowerBound = TypeSubstitutionKt.replace$default(lowerBound, arrayList, null, 2, null);
            }
            SimpleType upperBound = flexibleType.getUpperBound();
            if (!upperBound.getConstructor().getParameters().isEmpty() && upperBound.getConstructor().mo2781getDeclarationDescriptor() != null) {
                List parameters2 = upperBound.getConstructor().getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters2, "getParameters(...)");
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters2, 10));
                Iterator it2 = parameters2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new StarProjectionImpl((TypeParameterDescriptor) it2.next()));
                }
                upperBound = TypeSubstitutionKt.replace$default(upperBound, arrayList2, null, 2, null);
            }
            unwrappedTypeReplace$default = KotlinTypeFactory.flexibleType(lowerBound, upperBound);
        } else {
            if (!(unwrappedTypeUnwrap instanceof SimpleType)) {
                throw new NoWhenBranchMatchedException();
            }
            SimpleType simpleType = (SimpleType) unwrappedTypeUnwrap;
            boolean zIsEmpty = simpleType.getConstructor().getParameters().isEmpty();
            unwrappedTypeReplace$default = simpleType;
            if (!zIsEmpty) {
                ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = simpleType.getConstructor().mo2781getDeclarationDescriptor();
                unwrappedTypeReplace$default = simpleType;
                if (classifierDescriptorMo2781getDeclarationDescriptor != null) {
                    List parameters3 = simpleType.getConstructor().getParameters();
                    Intrinsics.checkNotNullExpressionValue(parameters3, "getParameters(...)");
                    ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters3, 10));
                    Iterator it3 = parameters3.iterator();
                    while (it3.hasNext()) {
                        arrayList3.add(new StarProjectionImpl((TypeParameterDescriptor) it3.next()));
                    }
                    unwrappedTypeReplace$default = TypeSubstitutionKt.replace$default(simpleType, arrayList3, null, 2, null);
                }
            }
        }
        return TypeWithEnhancementKt.inheritEnhancement(unwrappedTypeReplace$default, unwrappedTypeUnwrap);
    }

    public static final boolean isStubType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        if (!(kotlinType instanceof DefinitelyNotNullType)) {
            return false;
        }
        ((DefinitelyNotNullType) kotlinType).getOriginal();
        return false;
    }

    public static final boolean isStubTypeForBuilderInference(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        if (!(kotlinType instanceof DefinitelyNotNullType)) {
            return false;
        }
        ((DefinitelyNotNullType) kotlinType).getOriginal();
        return false;
    }
}
