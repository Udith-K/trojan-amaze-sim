package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionTypeKind;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionTypeKindExtractor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.BuiltInAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: functionTypes.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class FunctionTypesKt {
    public static final FunctionTypeKind getFunctionTypeKind(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = kotlinType.getConstructor().mo2781getDeclarationDescriptor();
        if (classifierDescriptorMo2781getDeclarationDescriptor != null) {
            return getFunctionTypeKind(classifierDescriptorMo2781getDeclarationDescriptor);
        }
        return null;
    }

    public static final boolean isFunctionType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return Intrinsics.areEqual(getFunctionTypeKind(kotlinType), FunctionTypeKind.Function.INSTANCE);
    }

    public static final boolean isSuspendFunctionType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return Intrinsics.areEqual(getFunctionTypeKind(kotlinType), FunctionTypeKind.SuspendFunction.INSTANCE);
    }

    public static final boolean isBuiltinFunctionalType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = kotlinType.getConstructor().mo2781getDeclarationDescriptor();
        return classifierDescriptorMo2781getDeclarationDescriptor != null && isBuiltinFunctionalClassDescriptor(classifierDescriptorMo2781getDeclarationDescriptor);
    }

    public static final boolean isBuiltinFunctionalClassDescriptor(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        FunctionTypeKind functionTypeKind = getFunctionTypeKind(declarationDescriptor);
        return Intrinsics.areEqual(functionTypeKind, FunctionTypeKind.Function.INSTANCE) || Intrinsics.areEqual(functionTypeKind, FunctionTypeKind.SuspendFunction.INSTANCE);
    }

    public static final boolean isBuiltinExtensionFunctionalType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        return isBuiltinFunctionalType(kotlinType) && isTypeAnnotatedWithExtensionFunctionType(kotlinType);
    }

    private static final boolean isTypeAnnotatedWithExtensionFunctionType(KotlinType kotlinType) {
        return kotlinType.getAnnotations().mo2748findAnnotation(StandardNames.FqNames.extensionFunctionType) != null;
    }

    public static final FunctionTypeKind getFunctionTypeKind(DeclarationDescriptor declarationDescriptor) {
        Intrinsics.checkNotNullParameter(declarationDescriptor, "<this>");
        if ((declarationDescriptor instanceof ClassDescriptor) && KotlinBuiltIns.isUnderKotlinPackage(declarationDescriptor)) {
            return getFunctionTypeKind(DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor));
        }
        return null;
    }

    private static final FunctionTypeKind getFunctionTypeKind(FqNameUnsafe fqNameUnsafe) {
        if (!fqNameUnsafe.isSafe() || fqNameUnsafe.isRoot()) {
            return null;
        }
        FunctionTypeKindExtractor functionTypeKindExtractor = FunctionTypeKindExtractor.Companion.getDefault();
        FqName fqNameParent = fqNameUnsafe.toSafe().parent();
        Intrinsics.checkNotNullExpressionValue(fqNameParent, "parent(...)");
        String strAsString = fqNameUnsafe.shortName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        return functionTypeKindExtractor.getFunctionalClassKind(fqNameParent, strAsString);
    }

    public static final int contextFunctionTypeParamsCount(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        AnnotationDescriptor annotationDescriptorMo2748findAnnotation = kotlinType.getAnnotations().mo2748findAnnotation(StandardNames.FqNames.contextFunctionTypeParams);
        if (annotationDescriptorMo2748findAnnotation == null) {
            return 0;
        }
        ConstantValue constantValue = (ConstantValue) MapsKt.getValue(annotationDescriptorMo2748findAnnotation.getAllValueArguments(), StandardNames.CONTEXT_FUNCTION_TYPE_PARAMETER_COUNT_NAME);
        Intrinsics.checkNotNull(constantValue, "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.constants.IntValue");
        return ((Number) ((IntValue) constantValue).getValue()).intValue();
    }

    public static final KotlinType getReceiverTypeFromFunctionType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        isBuiltinFunctionalType(kotlinType);
        if (!isTypeAnnotatedWithExtensionFunctionType(kotlinType)) {
            return null;
        }
        return ((TypeProjection) kotlinType.getArguments().get(contextFunctionTypeParamsCount(kotlinType))).getType();
    }

    public static final List getContextReceiverTypesFromFunctionType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        isBuiltinFunctionalType(kotlinType);
        int iContextFunctionTypeParamsCount = contextFunctionTypeParamsCount(kotlinType);
        if (iContextFunctionTypeParamsCount == 0) {
            return CollectionsKt.emptyList();
        }
        List listSubList = kotlinType.getArguments().subList(0, iContextFunctionTypeParamsCount);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSubList, 10));
        Iterator it = listSubList.iterator();
        while (it.hasNext()) {
            arrayList.add(((TypeProjection) it.next()).getType());
        }
        return arrayList;
    }

    public static final KotlinType getReturnTypeFromFunctionType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        isBuiltinFunctionalType(kotlinType);
        KotlinType type = ((TypeProjection) CollectionsKt.last(kotlinType.getArguments())).getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        return type;
    }

    public static final List getValueParameterTypesFromFunctionType(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        isBuiltinFunctionalType(kotlinType);
        return kotlinType.getArguments().subList(contextFunctionTypeParamsCount(kotlinType) + (isBuiltinExtensionFunctionalType(kotlinType) ? 1 : 0), r0.size() - 1);
    }

    public static final Name extractParameterNameFromFunctionTypeArgument(KotlinType kotlinType) {
        String str;
        Intrinsics.checkNotNullParameter(kotlinType, "<this>");
        AnnotationDescriptor annotationDescriptorMo2748findAnnotation = kotlinType.getAnnotations().mo2748findAnnotation(StandardNames.FqNames.parameterName);
        if (annotationDescriptorMo2748findAnnotation == null) {
            return null;
        }
        Object objSingleOrNull = CollectionsKt.singleOrNull(annotationDescriptorMo2748findAnnotation.getAllValueArguments().values());
        StringValue stringValue = objSingleOrNull instanceof StringValue ? (StringValue) objSingleOrNull : null;
        if (stringValue != null && (str = (String) stringValue.getValue()) != null) {
            if (!Name.isValidIdentifier(str)) {
                str = null;
            }
            if (str != null) {
                return Name.identifier(str);
            }
        }
        return null;
    }

    public static final List getFunctionTypeArgumentProjections(KotlinType kotlinType, List contextReceiverTypes, List parameterTypes, List list, KotlinType returnType, KotlinBuiltIns builtIns) {
        Name name;
        Intrinsics.checkNotNullParameter(contextReceiverTypes, "contextReceiverTypes");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        int i = 0;
        ArrayList arrayList = new ArrayList(parameterTypes.size() + contextReceiverTypes.size() + (kotlinType != null ? 1 : 0) + 1);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextReceiverTypes, 10));
        Iterator it = contextReceiverTypes.iterator();
        while (it.hasNext()) {
            arrayList2.add(TypeUtilsKt.asTypeProjection((KotlinType) it.next()));
        }
        arrayList.addAll(arrayList2);
        kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList, kotlinType != null ? TypeUtilsKt.asTypeProjection(kotlinType) : null);
        for (Object obj : parameterTypes) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KotlinType kotlinTypeReplaceAnnotations = (KotlinType) obj;
            if (list == null || (name = (Name) list.get(i)) == null || name.isSpecial()) {
                name = null;
            }
            if (name != null) {
                FqName fqName = StandardNames.FqNames.parameterName;
                Name name2 = StandardNames.NAME;
                String strAsString = name.asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
                kotlinTypeReplaceAnnotations = TypeUtilsKt.replaceAnnotations(kotlinTypeReplaceAnnotations, Annotations.Companion.create(CollectionsKt.plus(kotlinTypeReplaceAnnotations.getAnnotations(), new BuiltInAnnotationDescriptor(builtIns, fqName, MapsKt.mapOf(TuplesKt.to(name2, new StringValue(strAsString))), false, 8, null))));
            }
            arrayList.add(TypeUtilsKt.asTypeProjection(kotlinTypeReplaceAnnotations));
            i = i2;
        }
        arrayList.add(TypeUtilsKt.asTypeProjection(returnType));
        return arrayList;
    }

    public static final SimpleType createFunctionType(KotlinBuiltIns builtIns, Annotations annotations, KotlinType kotlinType, List contextReceiverTypes, List parameterTypes, List list, KotlinType returnType, boolean z) {
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(contextReceiverTypes, "contextReceiverTypes");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(returnType, "returnType");
        List functionTypeArgumentProjections = getFunctionTypeArgumentProjections(kotlinType, contextReceiverTypes, parameterTypes, list, returnType, builtIns);
        ClassDescriptor functionDescriptor = getFunctionDescriptor(builtIns, parameterTypes.size() + contextReceiverTypes.size() + (kotlinType == null ? 0 : 1), z);
        if (kotlinType != null) {
            annotations = withExtensionFunctionAnnotation(annotations, builtIns);
        }
        if (!contextReceiverTypes.isEmpty()) {
            annotations = withContextReceiversFunctionAnnotation(annotations, builtIns, contextReceiverTypes.size());
        }
        return KotlinTypeFactory.simpleNotNullType(TypeAttributesKt.toDefaultAttributes(annotations), functionDescriptor, functionTypeArgumentProjections);
    }

    public static final Annotations withExtensionFunctionAnnotation(Annotations annotations, KotlinBuiltIns builtIns) {
        Intrinsics.checkNotNullParameter(annotations, "<this>");
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        FqName fqName = StandardNames.FqNames.extensionFunctionType;
        if (annotations.hasAnnotation(fqName)) {
            return annotations;
        }
        return Annotations.Companion.create(CollectionsKt.plus(annotations, new BuiltInAnnotationDescriptor(builtIns, fqName, MapsKt.emptyMap(), false, 8, null)));
    }

    public static final Annotations withContextReceiversFunctionAnnotation(Annotations annotations, KotlinBuiltIns builtIns, int i) {
        Intrinsics.checkNotNullParameter(annotations, "<this>");
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        FqName fqName = StandardNames.FqNames.contextFunctionTypeParams;
        if (annotations.hasAnnotation(fqName)) {
            return annotations;
        }
        return Annotations.Companion.create(CollectionsKt.plus(annotations, new BuiltInAnnotationDescriptor(builtIns, fqName, MapsKt.mapOf(TuplesKt.to(StandardNames.CONTEXT_FUNCTION_TYPE_PARAMETER_COUNT_NAME, new IntValue(i))), false, 8, null)));
    }

    public static final ClassDescriptor getFunctionDescriptor(KotlinBuiltIns builtIns, int i, boolean z) {
        Intrinsics.checkNotNullParameter(builtIns, "builtIns");
        ClassDescriptor suspendFunction = z ? builtIns.getSuspendFunction(i) : builtIns.getFunction(i);
        Intrinsics.checkNotNull(suspendFunction);
        return suspendFunction;
    }
}
