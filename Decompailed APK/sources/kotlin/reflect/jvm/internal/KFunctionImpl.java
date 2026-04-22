package kotlin.reflect.jvm.internal;

import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.FunctionWithAllInvokes;
import kotlin.reflect.jvm.internal.JvmFunctionSignature;
import kotlin.reflect.jvm.internal.ReflectProperties;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.CallerKt;
import kotlin.reflect.jvm.internal.calls.ValueClassAwareCaller;
import kotlin.reflect.jvm.internal.calls.ValueClassAwareCallerKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.InlineClassManglingRulesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: KFunctionImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class KFunctionImpl extends KCallableImpl implements FunctionBase, KFunction, FunctionWithAllInvokes {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KFunctionImpl.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;"))};
    private final Lazy caller$delegate;
    private final KDeclarationContainerImpl container;
    private final Lazy defaultCaller$delegate;
    private final ReflectProperties.LazySoftVal descriptor$delegate;
    private final Object rawBoundReceiver;
    private final String signature;

    @Override // kotlin.jvm.functions.Function0
    public Object invoke() {
        return FunctionWithAllInvokes.DefaultImpls.invoke(this);
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Object obj) {
        return FunctionWithAllInvokes.DefaultImpls.invoke(this, obj);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Object obj, Object obj2) {
        return FunctionWithAllInvokes.DefaultImpls.invoke(this, obj, obj2);
    }

    @Override // kotlin.jvm.functions.Function3
    public Object invoke(Object obj, Object obj2, Object obj3) {
        return FunctionWithAllInvokes.DefaultImpls.invoke(this, obj, obj2, obj3);
    }

    @Override // kotlin.jvm.functions.Function4
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return FunctionWithAllInvokes.DefaultImpls.invoke(this, obj, obj2, obj3, obj4);
    }

    @Override // kotlin.jvm.functions.Function5
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return FunctionWithAllInvokes.DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5);
    }

    @Override // kotlin.jvm.functions.Function6
    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return FunctionWithAllInvokes.DefaultImpls.invoke(this, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // kotlin.reflect.jvm.internal.KCallableImpl
    public KDeclarationContainerImpl getContainer() {
        return this.container;
    }

    /* synthetic */ KFunctionImpl(KDeclarationContainerImpl kDeclarationContainerImpl, String str, String str2, FunctionDescriptor functionDescriptor, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kDeclarationContainerImpl, str, str2, functionDescriptor, (i & 16) != 0 ? CallableReference.NO_RECEIVER : obj);
    }

    private KFunctionImpl(KDeclarationContainerImpl kDeclarationContainerImpl, final String str, String str2, FunctionDescriptor functionDescriptor, Object obj) {
        this.container = kDeclarationContainerImpl;
        this.signature = str2;
        this.rawBoundReceiver = obj;
        this.descriptor$delegate = ReflectProperties.lazySoft(functionDescriptor, new Function0(this, str) { // from class: kotlin.reflect.jvm.internal.KFunctionImpl$$Lambda$0
            private final KFunctionImpl arg$0;
            private final String arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = str;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KFunctionImpl.descriptor_delegate$lambda$0(this.arg$0, this.arg$1);
            }
        });
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.caller$delegate = LazyKt.lazy(lazyThreadSafetyMode, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KFunctionImpl$$Lambda$1
            private final KFunctionImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KFunctionImpl.caller_delegate$lambda$4(this.arg$0);
            }
        });
        this.defaultCaller$delegate = LazyKt.lazy(lazyThreadSafetyMode, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KFunctionImpl$$Lambda$2
            private final KFunctionImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KFunctionImpl.defaultCaller_delegate$lambda$10(this.arg$0);
            }
        });
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KFunctionImpl(KDeclarationContainerImpl container, String name, String signature, Object obj) {
        this(container, name, signature, null, obj);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public KFunctionImpl(KDeclarationContainerImpl container, FunctionDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        String strAsString = descriptor.getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        this(container, strAsString, RuntimeTypeMapper.INSTANCE.mapSignature(descriptor).asString(), descriptor, null, 16, null);
    }

    @Override // kotlin.reflect.jvm.internal.KCallableImpl
    public boolean isBound() {
        return this.rawBoundReceiver != CallableReference.NO_RECEIVER;
    }

    @Override // kotlin.reflect.jvm.internal.KCallableImpl
    public FunctionDescriptor getDescriptor() {
        Object value = this.descriptor$delegate.getValue(this, $$delegatedProperties[0]);
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (FunctionDescriptor) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FunctionDescriptor descriptor_delegate$lambda$0(KFunctionImpl kFunctionImpl, String str) {
        return kFunctionImpl.getContainer().findFunctionDescriptor(str, kFunctionImpl.signature);
    }

    @Override // kotlin.reflect.KCallable
    public String getName() {
        String strAsString = getDescriptor().getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        return strAsString;
    }

    @Override // kotlin.reflect.jvm.internal.KCallableImpl
    public Caller getCaller() {
        return (Caller) this.caller$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Caller caller_delegate$lambda$4(KFunctionImpl kFunctionImpl) {
        Object constructor;
        CallerImpl callerImplCreateInstanceMethodCaller;
        JvmFunctionSignature jvmFunctionSignatureMapSignature = RuntimeTypeMapper.INSTANCE.mapSignature(kFunctionImpl.getDescriptor());
        if (jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.KotlinConstructor) {
            if (kFunctionImpl.isAnnotationConstructor()) {
                Class jClass = kFunctionImpl.getContainer().getJClass();
                List parameters = kFunctionImpl.getParameters();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
                Iterator it = parameters.iterator();
                while (it.hasNext()) {
                    String name = ((KParameter) it.next()).getName();
                    Intrinsics.checkNotNull(name);
                    arrayList.add(name);
                }
                return new AnnotationConstructorCaller(jClass, arrayList, AnnotationConstructorCaller.CallMode.POSITIONAL_CALL, AnnotationConstructorCaller.Origin.KOTLIN, null, 16, null);
            }
            constructor = kFunctionImpl.getContainer().findConstructorBySignature(((JvmFunctionSignature.KotlinConstructor) jvmFunctionSignatureMapSignature).getConstructorDesc());
        } else if (jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.KotlinFunction) {
            FunctionDescriptor descriptor = kFunctionImpl.getDescriptor();
            DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
            Intrinsics.checkNotNullExpressionValue(containingDeclaration, "getContainingDeclaration(...)");
            if (InlineClassesUtilsKt.isMultiFieldValueClass(containingDeclaration) && (descriptor instanceof ConstructorDescriptor) && ((ConstructorDescriptor) descriptor).isPrimary()) {
                FunctionDescriptor descriptor2 = kFunctionImpl.getDescriptor();
                KDeclarationContainerImpl container = kFunctionImpl.getContainer();
                String methodDesc = ((JvmFunctionSignature.KotlinFunction) jvmFunctionSignatureMapSignature).getMethodDesc();
                List valueParameters = kFunctionImpl.getDescriptor().getValueParameters();
                Intrinsics.checkNotNullExpressionValue(valueParameters, "getValueParameters(...)");
                return new ValueClassAwareCaller.MultiFieldValueClassPrimaryConstructorCaller(descriptor2, container, methodDesc, valueParameters);
            }
            JvmFunctionSignature.KotlinFunction kotlinFunction = (JvmFunctionSignature.KotlinFunction) jvmFunctionSignatureMapSignature;
            constructor = kFunctionImpl.getContainer().findMethodBySignature(kotlinFunction.getMethodName(), kotlinFunction.getMethodDesc());
        } else if (jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.JavaMethod) {
            constructor = ((JvmFunctionSignature.JavaMethod) jvmFunctionSignatureMapSignature).getMethod();
            Intrinsics.checkNotNull(constructor, "null cannot be cast to non-null type java.lang.reflect.Member");
        } else {
            if (!(jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.JavaConstructor)) {
                if (!(jvmFunctionSignatureMapSignature instanceof JvmFunctionSignature.FakeJavaAnnotationConstructor)) {
                    throw new NoWhenBranchMatchedException();
                }
                List methods = ((JvmFunctionSignature.FakeJavaAnnotationConstructor) jvmFunctionSignatureMapSignature).getMethods();
                Class jClass2 = kFunctionImpl.getContainer().getJClass();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(methods, 10));
                Iterator it2 = methods.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((Method) it2.next()).getName());
                }
                return new AnnotationConstructorCaller(jClass2, arrayList2, AnnotationConstructorCaller.CallMode.POSITIONAL_CALL, AnnotationConstructorCaller.Origin.JAVA, methods);
            }
            constructor = ((JvmFunctionSignature.JavaConstructor) jvmFunctionSignatureMapSignature).getConstructor();
            Intrinsics.checkNotNull(constructor, "null cannot be cast to non-null type java.lang.reflect.Member");
        }
        if (constructor instanceof Constructor) {
            callerImplCreateInstanceMethodCaller = kFunctionImpl.createConstructorCaller((Constructor) constructor, kFunctionImpl.getDescriptor(), false);
        } else {
            if (!(constructor instanceof Method)) {
                throw new KotlinReflectionInternalError("Could not compute caller for function: " + kFunctionImpl.getDescriptor() + " (member = " + constructor + CoreConstants.RIGHT_PARENTHESIS_CHAR);
            }
            Method method = (Method) constructor;
            callerImplCreateInstanceMethodCaller = !Modifier.isStatic(method.getModifiers()) ? kFunctionImpl.createInstanceMethodCaller(method) : kFunctionImpl.getDescriptor().getAnnotations().mo2748findAnnotation(UtilKt.getJVM_STATIC()) != null ? kFunctionImpl.createJvmStaticInObjectCaller(method) : kFunctionImpl.createStaticMethodCaller(method);
        }
        return ValueClassAwareCallerKt.createValueClassAwareCallerIfNeeded$default(callerImplCreateInstanceMethodCaller, kFunctionImpl.getDescriptor(), false, 2, null);
    }

    @Override // kotlin.reflect.jvm.internal.KCallableImpl
    public Caller getDefaultCaller() {
        return (Caller) this.defaultCaller$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0178  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.reflect.jvm.internal.calls.Caller defaultCaller_delegate$lambda$10(kotlin.reflect.jvm.internal.KFunctionImpl r11) {
        /*
            Method dump skipped, instruction units count: 395
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KFunctionImpl.defaultCaller_delegate$lambda$10(kotlin.reflect.jvm.internal.KFunctionImpl):kotlin.reflect.jvm.internal.calls.Caller");
    }

    private final FunctionDescriptor getFunctionWithDefaultParametersForValueClassOverride(FunctionDescriptor functionDescriptor) {
        Object next;
        List valueParameters = functionDescriptor.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "getValueParameters(...)");
        if (!(valueParameters instanceof Collection) || !valueParameters.isEmpty()) {
            Iterator it = valueParameters.iterator();
            while (it.hasNext()) {
                if (((ValueParameterDescriptor) it.next()).declaresDefaultValue()) {
                    return null;
                }
            }
        }
        DeclarationDescriptor containingDeclaration = functionDescriptor.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "getContainingDeclaration(...)");
        if (!InlineClassesUtilsKt.isValueClass(containingDeclaration)) {
            return null;
        }
        Member memberMo2745getMember = getCaller().mo2745getMember();
        Intrinsics.checkNotNull(memberMo2745getMember);
        if (!Modifier.isStatic(memberMo2745getMember.getModifiers())) {
            return null;
        }
        Iterator it2 = DescriptorUtilsKt.overriddenTreeAsSequence(functionDescriptor, false).iterator();
        loop0: while (true) {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
            List valueParameters2 = ((CallableMemberDescriptor) next).getValueParameters();
            Intrinsics.checkNotNullExpressionValue(valueParameters2, "getValueParameters(...)");
            if (!(valueParameters2 instanceof Collection) || !valueParameters2.isEmpty()) {
                Iterator it3 = valueParameters2.iterator();
                while (it3.hasNext()) {
                    if (((ValueParameterDescriptor) it3.next()).declaresDefaultValue()) {
                        break loop0;
                    }
                }
            }
        }
        if (next instanceof FunctionDescriptor) {
            return (FunctionDescriptor) next;
        }
        return null;
    }

    private final Object getBoundReceiver() {
        return ValueClassAwareCallerKt.coerceToExpectedReceiverType(this.rawBoundReceiver, getDescriptor());
    }

    private final boolean useBoxedBoundReceiver(Method method) {
        KotlinType type;
        ReceiverParameterDescriptor dispatchReceiverParameter = getDescriptor().getDispatchReceiverParameter();
        if (dispatchReceiverParameter != null && (type = dispatchReceiverParameter.getType()) != null && InlineClassesUtilsKt.isInlineClassType(type)) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
            Class cls = (Class) ArraysKt.firstOrNull(parameterTypes);
            if (cls != null && cls.isInterface()) {
                return true;
            }
        }
        return false;
    }

    private final CallerImpl.Method createStaticMethodCaller(Method method) {
        if (isBound()) {
            return new CallerImpl.Method.BoundStatic(method, useBoxedBoundReceiver(method) ? this.rawBoundReceiver : getBoundReceiver());
        }
        return new CallerImpl.Method.Static(method);
    }

    private final CallerImpl.Method createJvmStaticInObjectCaller(Method method) {
        return isBound() ? new CallerImpl.Method.BoundJvmStaticInObject(method) : new CallerImpl.Method.JvmStaticInObject(method);
    }

    private final CallerImpl.Method createInstanceMethodCaller(Method method) {
        return isBound() ? new CallerImpl.Method.BoundInstance(method, getBoundReceiver()) : new CallerImpl.Method.Instance(method);
    }

    private final CallerImpl createConstructorCaller(Constructor constructor, FunctionDescriptor functionDescriptor, boolean z) {
        if (!z && InlineClassManglingRulesKt.shouldHideConstructorDueToValueClassTypeValueParameters(functionDescriptor)) {
            if (isBound()) {
                return new CallerImpl.AccessorForHiddenBoundConstructor(constructor, getBoundReceiver());
            }
            return new CallerImpl.AccessorForHiddenConstructor(constructor);
        }
        if (isBound()) {
            return new CallerImpl.BoundConstructor(constructor, getBoundReceiver());
        }
        return new CallerImpl.Constructor(constructor);
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return CallerKt.getArity(getCaller());
    }

    @Override // kotlin.reflect.KFunction
    public boolean isInline() {
        return getDescriptor().isInline();
    }

    @Override // kotlin.reflect.KFunction
    public boolean isExternal() {
        return getDescriptor().isExternal();
    }

    @Override // kotlin.reflect.KFunction
    public boolean isOperator() {
        return getDescriptor().isOperator();
    }

    @Override // kotlin.reflect.KFunction
    public boolean isInfix() {
        return getDescriptor().isInfix();
    }

    @Override // kotlin.reflect.KCallable, kotlin.reflect.KFunction
    public boolean isSuspend() {
        return getDescriptor().isSuspend();
    }

    public boolean equals(Object obj) {
        KFunctionImpl kFunctionImplAsKFunctionImpl = UtilKt.asKFunctionImpl(obj);
        return kFunctionImplAsKFunctionImpl != null && Intrinsics.areEqual(getContainer(), kFunctionImplAsKFunctionImpl.getContainer()) && Intrinsics.areEqual(getName(), kFunctionImplAsKFunctionImpl.getName()) && Intrinsics.areEqual(this.signature, kFunctionImplAsKFunctionImpl.signature) && Intrinsics.areEqual(this.rawBoundReceiver, kFunctionImplAsKFunctionImpl.rawBoundReceiver);
    }

    public int hashCode() {
        return (((getContainer().hashCode() * 31) + getName().hashCode()) * 31) + this.signature.hashCode();
    }

    public String toString() {
        return ReflectionObjectRenderer.INSTANCE.renderFunction(getDescriptor());
    }
}
