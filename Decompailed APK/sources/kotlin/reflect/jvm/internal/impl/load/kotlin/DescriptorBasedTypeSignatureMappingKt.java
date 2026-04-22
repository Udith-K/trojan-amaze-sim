package kotlin.reflect.jvm.internal.impl.load.kotlin;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ExpandedTypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.SimpleClassicTypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: descriptorBasedTypeSignatureMapping.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class DescriptorBasedTypeSignatureMappingKt {
    public static /* synthetic */ Object mapType$default(KotlinType kotlinType, JvmTypeFactory jvmTypeFactory, TypeMappingMode typeMappingMode, TypeMappingConfiguration typeMappingConfiguration, JvmDescriptorTypeWriter jvmDescriptorTypeWriter, Function3 function3, int i, Object obj) {
        if ((i & 32) != 0) {
            function3 = FunctionsKt.getDO_NOTHING_3();
        }
        return mapType(kotlinType, jvmTypeFactory, typeMappingMode, typeMappingConfiguration, jvmDescriptorTypeWriter, function3);
    }

    public static final Object mapType(KotlinType kotlinType, JvmTypeFactory factory, TypeMappingMode mode, TypeMappingConfiguration typeMappingConfiguration, JvmDescriptorTypeWriter jvmDescriptorTypeWriter, Function3 writeGenericType) {
        Object objCreateObjectType;
        KotlinType kotlinType2;
        Object objMapType;
        Intrinsics.checkNotNullParameter(kotlinType, "kotlinType");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(typeMappingConfiguration, "typeMappingConfiguration");
        Intrinsics.checkNotNullParameter(writeGenericType, "writeGenericType");
        KotlinType kotlinTypePreprocessType = typeMappingConfiguration.preprocessType(kotlinType);
        if (kotlinTypePreprocessType != null) {
            return mapType(kotlinTypePreprocessType, factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
        }
        if (FunctionTypesKt.isSuspendFunctionType(kotlinType)) {
            return mapType(SuspendFunctionTypesKt.transformSuspendFunctionToRuntimeFunctionType(kotlinType), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
        }
        SimpleClassicTypeSystemContext simpleClassicTypeSystemContext = SimpleClassicTypeSystemContext.INSTANCE;
        Object objMapBuiltInType = TypeSignatureMappingKt.mapBuiltInType(simpleClassicTypeSystemContext, kotlinType, factory, mode);
        if (objMapBuiltInType != null) {
            Object objBoxTypeIfNeeded = TypeSignatureMappingKt.boxTypeIfNeeded(factory, objMapBuiltInType, mode.getNeedPrimitiveBoxing());
            writeGenericType.invoke(kotlinType, objBoxTypeIfNeeded, mode);
            return objBoxTypeIfNeeded;
        }
        TypeConstructor constructor = kotlinType.getConstructor();
        if (constructor instanceof IntersectionTypeConstructor) {
            IntersectionTypeConstructor intersectionTypeConstructor = (IntersectionTypeConstructor) constructor;
            KotlinType alternativeType = intersectionTypeConstructor.getAlternativeType();
            if (alternativeType == null) {
                alternativeType = typeMappingConfiguration.commonSupertype(intersectionTypeConstructor.mo2782getSupertypes());
            }
            return mapType(TypeUtilsKt.replaceArgumentsWithStarProjections(alternativeType), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
        }
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = constructor.mo2781getDeclarationDescriptor();
        if (classifierDescriptorMo2781getDeclarationDescriptor == null) {
            throw new UnsupportedOperationException("no descriptor for type constructor of " + kotlinType);
        }
        if (ErrorUtils.isError(classifierDescriptorMo2781getDeclarationDescriptor)) {
            Object objCreateObjectType2 = factory.createObjectType("error/NonExistentClass");
            typeMappingConfiguration.processErrorType(kotlinType, (ClassDescriptor) classifierDescriptorMo2781getDeclarationDescriptor);
            return objCreateObjectType2;
        }
        boolean z = classifierDescriptorMo2781getDeclarationDescriptor instanceof ClassDescriptor;
        if (z && KotlinBuiltIns.isArray(kotlinType)) {
            if (kotlinType.getArguments().size() != 1) {
                throw new UnsupportedOperationException("arrays must have one type argument");
            }
            TypeProjection typeProjection = (TypeProjection) kotlinType.getArguments().get(0);
            KotlinType type = typeProjection.getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            if (typeProjection.getProjectionKind() == Variance.IN_VARIANCE) {
                objMapType = factory.createObjectType("java/lang/Object");
            } else {
                Variance projectionKind = typeProjection.getProjectionKind();
                Intrinsics.checkNotNullExpressionValue(projectionKind, "getProjectionKind(...)");
                objMapType = mapType(type, factory, mode.toGenericArgumentMode(projectionKind, true), typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
            }
            return factory.createFromString('[' + factory.toString(objMapType));
        }
        if (z) {
            if (InlineClassesUtilsKt.isInlineClass(classifierDescriptorMo2781getDeclarationDescriptor) && !mode.getNeedInlineClassWrapping() && (kotlinType2 = (KotlinType) ExpandedTypeUtilsKt.computeExpandedTypeForInlineClass(simpleClassicTypeSystemContext, kotlinType)) != null) {
                return mapType(kotlinType2, factory, mode.wrapInlineClassesMode(), typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
            }
            if (mode.isForAnnotationParameter() && KotlinBuiltIns.isKClass((ClassDescriptor) classifierDescriptorMo2781getDeclarationDescriptor)) {
                objCreateObjectType = factory.getJavaLangClassType();
            } else {
                ClassDescriptor classDescriptor = (ClassDescriptor) classifierDescriptorMo2781getDeclarationDescriptor;
                ClassDescriptor original = classDescriptor.getOriginal();
                Intrinsics.checkNotNullExpressionValue(original, "getOriginal(...)");
                Object predefinedTypeForClass = typeMappingConfiguration.getPredefinedTypeForClass(original);
                if (predefinedTypeForClass == null) {
                    if (classDescriptor.getKind() == ClassKind.ENUM_ENTRY) {
                        DeclarationDescriptor containingDeclaration = classDescriptor.getContainingDeclaration();
                        Intrinsics.checkNotNull(containingDeclaration, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                        classDescriptor = (ClassDescriptor) containingDeclaration;
                    }
                    ClassDescriptor original2 = classDescriptor.getOriginal();
                    Intrinsics.checkNotNullExpressionValue(original2, "getOriginal(...)");
                    objCreateObjectType = factory.createObjectType(computeInternalName(original2, typeMappingConfiguration));
                } else {
                    objCreateObjectType = predefinedTypeForClass;
                }
            }
            writeGenericType.invoke(kotlinType, objCreateObjectType, mode);
            return objCreateObjectType;
        }
        if (classifierDescriptorMo2781getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            KotlinType representativeUpperBound = TypeUtilsKt.getRepresentativeUpperBound((TypeParameterDescriptor) classifierDescriptorMo2781getDeclarationDescriptor);
            if (kotlinType.isMarkedNullable()) {
                representativeUpperBound = TypeUtilsKt.makeNullable(representativeUpperBound);
            }
            return mapType(representativeUpperBound, factory, mode, typeMappingConfiguration, null, FunctionsKt.getDO_NOTHING_3());
        }
        if ((classifierDescriptorMo2781getDeclarationDescriptor instanceof TypeAliasDescriptor) && mode.getMapTypeAliases()) {
            return mapType(((TypeAliasDescriptor) classifierDescriptorMo2781getDeclarationDescriptor).getExpandedType(), factory, mode, typeMappingConfiguration, jvmDescriptorTypeWriter, writeGenericType);
        }
        throw new UnsupportedOperationException("Unknown type " + kotlinType);
    }

    public static final boolean hasVoidReturnType(CallableDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (descriptor instanceof ConstructorDescriptor) {
            return true;
        }
        KotlinType returnType = descriptor.getReturnType();
        Intrinsics.checkNotNull(returnType);
        if (KotlinBuiltIns.isUnit(returnType)) {
            KotlinType returnType2 = descriptor.getReturnType();
            Intrinsics.checkNotNull(returnType2);
            if (!TypeUtils.isNullableType(returnType2) && !(descriptor instanceof PropertyGetterDescriptor)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ String computeInternalName$default(ClassDescriptor classDescriptor, TypeMappingConfiguration typeMappingConfiguration, int i, Object obj) {
        if ((i & 2) != 0) {
            typeMappingConfiguration = TypeMappingConfigurationImpl.INSTANCE;
        }
        return computeInternalName(classDescriptor, typeMappingConfiguration);
    }

    public static final String computeInternalName(ClassDescriptor klass, TypeMappingConfiguration typeMappingConfiguration) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        Intrinsics.checkNotNullParameter(typeMappingConfiguration, "typeMappingConfiguration");
        String predefinedFullInternalNameForClass = typeMappingConfiguration.getPredefinedFullInternalNameForClass(klass);
        if (predefinedFullInternalNameForClass != null) {
            return predefinedFullInternalNameForClass;
        }
        DeclarationDescriptor containingDeclaration = klass.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "getContainingDeclaration(...)");
        String identifier = SpecialNames.safeIdentifier(klass.getName()).getIdentifier();
        Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            FqName fqName = ((PackageFragmentDescriptor) containingDeclaration).getFqName();
            if (fqName.isRoot()) {
                return identifier;
            }
            StringBuilder sb = new StringBuilder();
            String strAsString = fqName.asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
            sb.append(StringsKt.replace$default(strAsString, CoreConstants.DOT, '/', false, 4, (Object) null));
            sb.append('/');
            sb.append(identifier);
            return sb.toString();
        }
        ClassDescriptor classDescriptor = containingDeclaration instanceof ClassDescriptor ? (ClassDescriptor) containingDeclaration : null;
        if (classDescriptor == null) {
            throw new IllegalArgumentException("Unexpected container: " + containingDeclaration + " for " + klass);
        }
        String predefinedInternalNameForClass = typeMappingConfiguration.getPredefinedInternalNameForClass(classDescriptor);
        if (predefinedInternalNameForClass == null) {
            predefinedInternalNameForClass = computeInternalName(classDescriptor, typeMappingConfiguration);
        }
        return predefinedInternalNameForClass + CoreConstants.DOLLAR + identifier;
    }
}
