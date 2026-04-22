package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

/* JADX INFO: compiled from: StarProjectionImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class StarProjectionImplKt {
    private static final KotlinType buildStarProjectionTypeByTypeParameters(final List list, List list2, KotlinBuiltIns kotlinBuiltIns) {
        KotlinType kotlinTypeSubstitute = TypeSubstitutor.create(new TypeConstructorSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.StarProjectionImplKt.buildStarProjectionTypeByTypeParameters.1
            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
            public TypeProjection get(TypeConstructor key) {
                Intrinsics.checkNotNullParameter(key, "key");
                if (!list.contains(key)) {
                    return null;
                }
                ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = key.mo2781getDeclarationDescriptor();
                Intrinsics.checkNotNull(classifierDescriptorMo2781getDeclarationDescriptor, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
                return TypeUtils.makeStarProjection((TypeParameterDescriptor) classifierDescriptorMo2781getDeclarationDescriptor);
            }
        }).substitute((KotlinType) CollectionsKt.first(list2), Variance.OUT_VARIANCE);
        if (kotlinTypeSubstitute != null) {
            return kotlinTypeSubstitute;
        }
        SimpleType defaultBound = kotlinBuiltIns.getDefaultBound();
        Intrinsics.checkNotNullExpressionValue(defaultBound, "getDefaultBound(...)");
        return defaultBound;
    }

    public static final KotlinType starProjectionType(TypeParameterDescriptor typeParameterDescriptor) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor, "<this>");
        DeclarationDescriptor containingDeclaration = typeParameterDescriptor.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "getContainingDeclaration(...)");
        if (containingDeclaration instanceof ClassifierDescriptorWithTypeParameters) {
            List parameters = ((ClassifierDescriptorWithTypeParameters) containingDeclaration).getTypeConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
            Iterator it = parameters.iterator();
            while (it.hasNext()) {
                TypeConstructor typeConstructor = ((TypeParameterDescriptor) it.next()).getTypeConstructor();
                Intrinsics.checkNotNullExpressionValue(typeConstructor, "getTypeConstructor(...)");
                arrayList.add(typeConstructor);
            }
            List upperBounds = typeParameterDescriptor.getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
            return buildStarProjectionTypeByTypeParameters(arrayList, upperBounds, DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor));
        }
        if (!(containingDeclaration instanceof FunctionDescriptor)) {
            throw new IllegalArgumentException("Unsupported descriptor type to build star projection type based on type parameters of it");
        }
        List typeParameters = ((FunctionDescriptor) containingDeclaration).getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(typeParameters, "getTypeParameters(...)");
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator it2 = typeParameters.iterator();
        while (it2.hasNext()) {
            TypeConstructor typeConstructor2 = ((TypeParameterDescriptor) it2.next()).getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor2, "getTypeConstructor(...)");
            arrayList2.add(typeConstructor2);
        }
        List upperBounds2 = typeParameterDescriptor.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(upperBounds2, "getUpperBounds(...)");
        return buildStarProjectionTypeByTypeParameters(arrayList2, upperBounds2, DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor));
    }
}
