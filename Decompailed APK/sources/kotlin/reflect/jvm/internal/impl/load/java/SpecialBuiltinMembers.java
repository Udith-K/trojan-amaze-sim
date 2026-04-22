package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedure;

/* JADX INFO: compiled from: specialBuiltinMembers.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class SpecialBuiltinMembers {
    public static final CallableMemberDescriptor getOverriddenBuiltinWithDifferentJvmName(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "<this>");
        if (!SpecialGenericSignatures.Companion.getORIGINAL_SHORT_NAMES().contains(callableMemberDescriptor.getName()) && !BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES().contains(DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor).getName())) {
            return null;
        }
        if ((callableMemberDescriptor instanceof PropertyDescriptor) || (callableMemberDescriptor instanceof PropertyAccessorDescriptor)) {
            return DescriptorUtilsKt.firstOverridden$default(callableMemberDescriptor, false, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers$$Lambda$0
                @Override // kotlin.jvm.functions.Function1
                public Object invoke(Object obj) {
                    return Boolean.valueOf(SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName$lambda$0((CallableMemberDescriptor) obj));
                }
            }, 1, null);
        }
        if (callableMemberDescriptor instanceof SimpleFunctionDescriptor) {
            return DescriptorUtilsKt.firstOverridden$default(callableMemberDescriptor, false, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers$$Lambda$1
                @Override // kotlin.jvm.functions.Function1
                public Object invoke(Object obj) {
                    return Boolean.valueOf(SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName$lambda$1((CallableMemberDescriptor) obj));
                }
            }, 1, null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getOverriddenBuiltinWithDifferentJvmName$lambda$0(CallableMemberDescriptor it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ClassicBuiltinSpecialProperties.INSTANCE.hasBuiltinSpecialPropertyFqName(DescriptorUtilsKt.getPropertyIfAccessor(it));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getOverriddenBuiltinWithDifferentJvmName$lambda$1(CallableMemberDescriptor it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return BuiltinMethodsWithDifferentJvmName.INSTANCE.isBuiltinFunctionWithDifferentNameInJvm((SimpleFunctionDescriptor) it);
    }

    public static final boolean doesOverrideBuiltinWithDifferentJvmName(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "<this>");
        return getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor) != null;
    }

    public static final CallableMemberDescriptor getOverriddenSpecialBuiltin(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "<this>");
        CallableMemberDescriptor overriddenBuiltinWithDifferentJvmName = getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor);
        if (overriddenBuiltinWithDifferentJvmName != null) {
            return overriddenBuiltinWithDifferentJvmName;
        }
        BuiltinMethodsWithSpecialGenericSignature builtinMethodsWithSpecialGenericSignature = BuiltinMethodsWithSpecialGenericSignature.INSTANCE;
        Name name = callableMemberDescriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        if (builtinMethodsWithSpecialGenericSignature.getSameAsBuiltinMethodWithErasedValueParameters(name)) {
            return DescriptorUtilsKt.firstOverridden$default(callableMemberDescriptor, false, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers$$Lambda$2
                @Override // kotlin.jvm.functions.Function1
                public Object invoke(Object obj) {
                    return Boolean.valueOf(SpecialBuiltinMembers.getOverriddenSpecialBuiltin$lambda$3((CallableMemberDescriptor) obj));
                }
            }, 1, null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getOverriddenSpecialBuiltin$lambda$3(CallableMemberDescriptor it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return KotlinBuiltIns.isBuiltIn(it) && BuiltinMethodsWithSpecialGenericSignature.getSpecialSignatureInfo(it) != null;
    }

    public static final String getJvmMethodNameIfSpecial(CallableMemberDescriptor callableMemberDescriptor) {
        CallableMemberDescriptor propertyIfAccessor;
        Name jvmName;
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "callableMemberDescriptor");
        CallableMemberDescriptor overriddenBuiltinThatAffectsJvmName = getOverriddenBuiltinThatAffectsJvmName(callableMemberDescriptor);
        if (overriddenBuiltinThatAffectsJvmName == null || (propertyIfAccessor = DescriptorUtilsKt.getPropertyIfAccessor(overriddenBuiltinThatAffectsJvmName)) == null) {
            return null;
        }
        if (propertyIfAccessor instanceof PropertyDescriptor) {
            return ClassicBuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName(propertyIfAccessor);
        }
        if (!(propertyIfAccessor instanceof SimpleFunctionDescriptor) || (jvmName = BuiltinMethodsWithDifferentJvmName.INSTANCE.getJvmName((SimpleFunctionDescriptor) propertyIfAccessor)) == null) {
            return null;
        }
        return jvmName.asString();
    }

    private static final CallableMemberDescriptor getOverriddenBuiltinThatAffectsJvmName(CallableMemberDescriptor callableMemberDescriptor) {
        if (KotlinBuiltIns.isBuiltIn(callableMemberDescriptor)) {
            return getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor);
        }
        return null;
    }

    public static final boolean hasRealKotlinSuperClassWithOverrideOf(ClassDescriptor classDescriptor, CallableDescriptor specialCallableDescriptor) {
        Intrinsics.checkNotNullParameter(classDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(specialCallableDescriptor, "specialCallableDescriptor");
        DeclarationDescriptor containingDeclaration = specialCallableDescriptor.getContainingDeclaration();
        Intrinsics.checkNotNull(containingDeclaration, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        SimpleType defaultType = ((ClassDescriptor) containingDeclaration).getDefaultType();
        Intrinsics.checkNotNullExpressionValue(defaultType, "getDefaultType(...)");
        for (ClassDescriptor superClassDescriptor = DescriptorUtils.getSuperClassDescriptor(classDescriptor); superClassDescriptor != null; superClassDescriptor = DescriptorUtils.getSuperClassDescriptor(superClassDescriptor)) {
            if (!(superClassDescriptor instanceof JavaClassDescriptor) && TypeCheckingProcedure.findCorrespondingSupertype(superClassDescriptor.getDefaultType(), defaultType) != null) {
                return !KotlinBuiltIns.isBuiltIn(superClassDescriptor);
            }
        }
        return false;
    }

    public static final boolean isFromJava(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "<this>");
        return DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor).getContainingDeclaration() instanceof JavaClassDescriptor;
    }

    public static final boolean isFromJavaOrBuiltins(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "<this>");
        return isFromJava(callableMemberDescriptor) || KotlinBuiltIns.isBuiltIn(callableMemberDescriptor);
    }
}
