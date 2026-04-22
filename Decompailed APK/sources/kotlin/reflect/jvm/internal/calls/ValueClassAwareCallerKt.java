package kotlin.reflect.jvm.internal.calls;

import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MultiFieldValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ClassMapperLite;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: ValueClassAwareCaller.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ValueClassAwareCallerKt {
    public static final String toJvmDescriptor(ClassifierDescriptor classifierDescriptor) {
        Intrinsics.checkNotNullParameter(classifierDescriptor, "<this>");
        ClassId classId = DescriptorUtilsKt.getClassId(classifierDescriptor);
        Intrinsics.checkNotNull(classId);
        return ClassMapperLite.mapClass(classId.asString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getValueClassUnboxMethods(SimpleType simpleType, CallableMemberDescriptor callableMemberDescriptor) {
        Method inlineClassUnboxMethod;
        List mfvcUnboxMethods = getMfvcUnboxMethods(simpleType);
        if (mfvcUnboxMethods != null) {
            return mfvcUnboxMethods;
        }
        Class inlineClass = toInlineClass(simpleType);
        if (inlineClass == null || (inlineClassUnboxMethod = getInlineClassUnboxMethod(inlineClass, callableMemberDescriptor)) == null) {
            return null;
        }
        return CollectionsKt.listOf(inlineClassUnboxMethod);
    }

    private static final List getMfvcUnboxMethods$getUnboxMethodNameSuffixes(SimpleType simpleType) {
        Collection collectionListOf;
        if (!InlineClassesUtilsKt.needsMfvcFlattening(simpleType)) {
            return null;
        }
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = simpleType.getConstructor().mo2781getDeclarationDescriptor();
        Intrinsics.checkNotNull(classifierDescriptorMo2781getDeclarationDescriptor, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        MultiFieldValueClassRepresentation multiFieldValueClassRepresentation = DescriptorUtilsKt.getMultiFieldValueClassRepresentation((ClassDescriptor) classifierDescriptorMo2781getDeclarationDescriptor);
        Intrinsics.checkNotNull(multiFieldValueClassRepresentation);
        List<Pair> underlyingPropertyNamesToTypes = multiFieldValueClassRepresentation.getUnderlyingPropertyNamesToTypes();
        ArrayList arrayList = new ArrayList();
        for (Pair pair : underlyingPropertyNamesToTypes) {
            Name name = (Name) pair.component1();
            List mfvcUnboxMethods$getUnboxMethodNameSuffixes = getMfvcUnboxMethods$getUnboxMethodNameSuffixes((SimpleType) pair.component2());
            if (mfvcUnboxMethods$getUnboxMethodNameSuffixes == null) {
                collectionListOf = CollectionsKt.listOf(name.getIdentifier());
            } else {
                collectionListOf = new ArrayList(CollectionsKt.collectionSizeOrDefault(mfvcUnboxMethods$getUnboxMethodNameSuffixes, 10));
                Iterator it = mfvcUnboxMethods$getUnboxMethodNameSuffixes.iterator();
                while (it.hasNext()) {
                    collectionListOf.add(name.getIdentifier() + CoreConstants.DASH_CHAR + ((String) it.next()));
                }
            }
            CollectionsKt.addAll(arrayList, collectionListOf);
        }
        return arrayList;
    }

    public static final List getMfvcUnboxMethods(SimpleType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        List mfvcUnboxMethods$getUnboxMethodNameSuffixes = getMfvcUnboxMethods$getUnboxMethodNameSuffixes(TypeSubstitutionKt.asSimpleType(type));
        if (mfvcUnboxMethods$getUnboxMethodNameSuffixes == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(mfvcUnboxMethods$getUnboxMethodNameSuffixes, 10));
        Iterator it = mfvcUnboxMethods$getUnboxMethodNameSuffixes.iterator();
        while (it.hasNext()) {
            arrayList.add("unbox-impl-" + ((String) it.next()));
        }
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = type.getConstructor().mo2781getDeclarationDescriptor();
        Intrinsics.checkNotNull(classifierDescriptorMo2781getDeclarationDescriptor, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        Class javaClass = UtilKt.toJavaClass((ClassDescriptor) classifierDescriptorMo2781getDeclarationDescriptor);
        Intrinsics.checkNotNull(javaClass);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(javaClass.getDeclaredMethod((String) it2.next(), null));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkParametersSize(Caller caller, int i, CallableMemberDescriptor callableMemberDescriptor, boolean z) {
        if (CallerKt.getArity(caller) == i) {
            return;
        }
        throw new KotlinReflectionInternalError("Inconsistent number of parameters in the descriptor and Java reflection object: " + CallerKt.getArity(caller) + " != " + i + "\nCalling: " + callableMemberDescriptor + "\nParameter types: " + caller.getParameterTypes() + ")\nDefault: " + z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List makeKotlinParameterTypes(CallableMemberDescriptor callableMemberDescriptor, Member member, Function1 function1) {
        ArrayList arrayList = new ArrayList();
        ReceiverParameterDescriptor extensionReceiverParameter = callableMemberDescriptor.getExtensionReceiverParameter();
        KotlinType type = extensionReceiverParameter != null ? extensionReceiverParameter.getType() : null;
        if (type != null) {
            arrayList.add(type);
        } else if (callableMemberDescriptor instanceof ConstructorDescriptor) {
            ClassDescriptor constructedClass = ((ConstructorDescriptor) callableMemberDescriptor).getConstructedClass();
            Intrinsics.checkNotNullExpressionValue(constructedClass, "getConstructedClass(...)");
            if (constructedClass.isInner()) {
                DeclarationDescriptor containingDeclaration = constructedClass.getContainingDeclaration();
                Intrinsics.checkNotNull(containingDeclaration, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                arrayList.add(((ClassDescriptor) containingDeclaration).getDefaultType());
            }
        } else {
            DeclarationDescriptor containingDeclaration2 = callableMemberDescriptor.getContainingDeclaration();
            Intrinsics.checkNotNullExpressionValue(containingDeclaration2, "getContainingDeclaration(...)");
            if ((containingDeclaration2 instanceof ClassDescriptor) && ((Boolean) function1.invoke(containingDeclaration2)).booleanValue()) {
                if (member != null && acceptsBoxedReceiverParameter(member)) {
                    SimpleType defaultType = ((ClassDescriptor) containingDeclaration2).getDefaultType();
                    Intrinsics.checkNotNullExpressionValue(defaultType, "getDefaultType(...)");
                    arrayList.add(TypeUtilsKt.makeNullable(defaultType));
                } else {
                    arrayList.add(((ClassDescriptor) containingDeclaration2).getDefaultType());
                }
            }
        }
        List valueParameters = callableMemberDescriptor.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "getValueParameters(...)");
        Iterator it = valueParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((ValueParameterDescriptor) it.next()).getType());
        }
        return arrayList;
    }

    private static final boolean acceptsBoxedReceiverParameter(Member member) {
        if (member.getDeclaringClass() == null) {
            return false;
        }
        return !JvmClassMappingKt.getKotlinClass(r0).isValue();
    }

    public static /* synthetic */ Caller createValueClassAwareCallerIfNeeded$default(Caller caller, CallableMemberDescriptor callableMemberDescriptor, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return createValueClassAwareCallerIfNeeded(caller, callableMemberDescriptor, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.reflect.jvm.internal.calls.Caller createValueClassAwareCallerIfNeeded(kotlin.reflect.jvm.internal.calls.Caller r3, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r4, boolean r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "descriptor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            boolean r0 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isGetterOfUnderlyingPropertyOfValueClass(r4)
            if (r0 != 0) goto L8a
            java.util.List r0 = r4.getContextReceiverParameters()
            java.lang.String r1 = "getContextReceiverParameters(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            boolean r1 = r0 instanceof java.util.Collection
            java.lang.String r2 = "getType(...)"
            if (r1 == 0) goto L26
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L26
            goto L44
        L26:
            java.util.Iterator r0 = r0.iterator()
        L2a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L44
            java.lang.Object r1 = r0.next()
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor) r1
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = r1.getType()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r1 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isValueClassType(r1)
            if (r1 == 0) goto L2a
            goto L8a
        L44:
            java.util.List r0 = r4.getValueParameters()
            java.lang.String r1 = "getValueParameters(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            boolean r1 = r0 instanceof java.util.Collection
            if (r1 == 0) goto L58
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L58
            goto L76
        L58:
            java.util.Iterator r0 = r0.iterator()
        L5c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L76
            java.lang.Object r1 = r0.next()
            kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r1
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = r1.getType()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r1 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isValueClassType(r1)
            if (r1 == 0) goto L5c
            goto L8a
        L76:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r4.getReturnType()
            if (r0 == 0) goto L84
            boolean r0 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isInlineClassType(r0)
            r1 = 1
            if (r0 != r1) goto L84
            goto L8a
        L84:
            boolean r0 = hasValueClassReceiver(r4)
            if (r0 == 0) goto L90
        L8a:
            kotlin.reflect.jvm.internal.calls.ValueClassAwareCaller r0 = new kotlin.reflect.jvm.internal.calls.ValueClassAwareCaller
            r0.<init>(r4, r3, r5)
            r3 = r0
        L90:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.calls.ValueClassAwareCallerKt.createValueClassAwareCallerIfNeeded(kotlin.reflect.jvm.internal.calls.Caller, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, boolean):kotlin.reflect.jvm.internal.calls.Caller");
    }

    private static final boolean hasValueClassReceiver(CallableMemberDescriptor callableMemberDescriptor) {
        KotlinType expectedReceiverType = getExpectedReceiverType(callableMemberDescriptor);
        return expectedReceiverType != null && InlineClassesUtilsKt.isValueClassType(expectedReceiverType);
    }

    public static final Method getInlineClassUnboxMethod(Class cls, CallableMemberDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        try {
            return cls.getDeclaredMethod("unbox-impl", null);
        } catch (NoSuchMethodException unused) {
            throw new KotlinReflectionInternalError("No unbox method found in inline class: " + cls + " (calling " + descriptor + CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Method getBoxMethod(Class cls, CallableMemberDescriptor callableMemberDescriptor) {
        try {
            return cls.getDeclaredMethod("box-impl", getInlineClassUnboxMethod(cls, callableMemberDescriptor).getReturnType());
        } catch (NoSuchMethodException unused) {
            throw new KotlinReflectionInternalError("No box method found in inline class: " + cls + " (calling " + callableMemberDescriptor + CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Class toInlineClass(KotlinType kotlinType) {
        Class inlineClass = toInlineClass(kotlinType.getConstructor().mo2781getDeclarationDescriptor());
        if (inlineClass == null) {
            return null;
        }
        if (!TypeUtils.isNullableType(kotlinType)) {
            return inlineClass;
        }
        KotlinType kotlinTypeUnsubstitutedUnderlyingType = InlineClassesUtilsKt.unsubstitutedUnderlyingType(kotlinType);
        if (kotlinTypeUnsubstitutedUnderlyingType == null || TypeUtils.isNullableType(kotlinTypeUnsubstitutedUnderlyingType) || KotlinBuiltIns.isPrimitiveType(kotlinTypeUnsubstitutedUnderlyingType)) {
            return null;
        }
        return inlineClass;
    }

    public static final Class toInlineClass(DeclarationDescriptor declarationDescriptor) {
        if (!(declarationDescriptor instanceof ClassDescriptor) || !InlineClassesUtilsKt.isInlineClass(declarationDescriptor)) {
            return null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
        Class javaClass = UtilKt.toJavaClass(classDescriptor);
        if (javaClass != null) {
            return javaClass;
        }
        throw new KotlinReflectionInternalError("Class object for the class " + classDescriptor.getName() + " cannot be found (classId=" + DescriptorUtilsKt.getClassId((ClassifierDescriptor) declarationDescriptor) + CoreConstants.RIGHT_PARENTHESIS_CHAR);
    }

    private static final KotlinType getExpectedReceiverType(CallableMemberDescriptor callableMemberDescriptor) {
        ReceiverParameterDescriptor extensionReceiverParameter = callableMemberDescriptor.getExtensionReceiverParameter();
        ReceiverParameterDescriptor dispatchReceiverParameter = callableMemberDescriptor.getDispatchReceiverParameter();
        if (extensionReceiverParameter != null) {
            return extensionReceiverParameter.getType();
        }
        if (dispatchReceiverParameter != null) {
            if (callableMemberDescriptor instanceof ConstructorDescriptor) {
                return dispatchReceiverParameter.getType();
            }
            DeclarationDescriptor containingDeclaration = callableMemberDescriptor.getContainingDeclaration();
            ClassDescriptor classDescriptor = containingDeclaration instanceof ClassDescriptor ? (ClassDescriptor) containingDeclaration : null;
            if (classDescriptor != null) {
                return classDescriptor.getDefaultType();
            }
        }
        return null;
    }

    public static final Object coerceToExpectedReceiverType(Object obj, CallableMemberDescriptor descriptor) {
        KotlinType expectedReceiverType;
        Class inlineClass;
        Method inlineClassUnboxMethod;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return (((descriptor instanceof PropertyDescriptor) && InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass((VariableDescriptor) descriptor)) || (expectedReceiverType = getExpectedReceiverType(descriptor)) == null || (inlineClass = toInlineClass(expectedReceiverType)) == null || (inlineClassUnboxMethod = getInlineClassUnboxMethod(inlineClass, descriptor)) == null) ? obj : inlineClassUnboxMethod.invoke(obj, null);
    }
}
