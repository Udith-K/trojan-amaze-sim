package kotlin.reflect.jvm.internal;

import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.full.IllegalPropertyDelegateAccessException;
import kotlin.reflect.jvm.KCallablesJvm;
import kotlin.reflect.jvm.internal.JvmPropertySignature;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.ReflectProperties;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.ValueClassAwareCallerKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.DescriptorsJvmAbiUtil;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;

/* JADX INFO: compiled from: KPropertyImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class KPropertyImpl extends KCallableImpl implements KProperty {
    public static final Companion Companion = new Companion(null);
    private static final Object EXTENSION_PROPERTY_DELEGATE = new Object();
    private final ReflectProperties.LazySoftVal _descriptor;
    private final Lazy _javaField;
    private final KDeclarationContainerImpl container;
    private final String name;
    private final Object rawBoundReceiver;
    private final String signature;

    public abstract Getter getGetter();

    @Override // kotlin.reflect.KCallable, kotlin.reflect.KFunction
    public boolean isSuspend() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.KCallableImpl
    public KDeclarationContainerImpl getContainer() {
        return this.container;
    }

    @Override // kotlin.reflect.KCallable
    public String getName() {
        return this.name;
    }

    public final String getSignature() {
        return this.signature;
    }

    private KPropertyImpl(KDeclarationContainerImpl kDeclarationContainerImpl, String str, String str2, PropertyDescriptor propertyDescriptor, Object obj) {
        this.container = kDeclarationContainerImpl;
        this.name = str;
        this.signature = str2;
        this.rawBoundReceiver = obj;
        this._javaField = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KPropertyImpl$$Lambda$0
            private final KPropertyImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KPropertyImpl._javaField$lambda$2(this.arg$0);
            }
        });
        ReflectProperties.LazySoftVal lazySoftValLazySoft = ReflectProperties.lazySoft(propertyDescriptor, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KPropertyImpl$$Lambda$1
            private final KPropertyImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KPropertyImpl._descriptor$lambda$5(this.arg$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft, "lazySoft(...)");
        this._descriptor = lazySoftValLazySoft;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KPropertyImpl(KDeclarationContainerImpl container, String name, String signature, Object obj) {
        this(container, name, signature, null, obj);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public KPropertyImpl(KDeclarationContainerImpl container, PropertyDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        String strAsString = descriptor.getName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        this(container, strAsString, RuntimeTypeMapper.INSTANCE.mapPropertySignature(descriptor).asString(), descriptor, CallableReference.NO_RECEIVER);
    }

    public final Object getBoundReceiver() {
        return ValueClassAwareCallerKt.coerceToExpectedReceiverType(this.rawBoundReceiver, getDescriptor());
    }

    @Override // kotlin.reflect.jvm.internal.KCallableImpl
    public boolean isBound() {
        return this.rawBoundReceiver != CallableReference.NO_RECEIVER;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Field _javaField$lambda$2(KPropertyImpl kPropertyImpl) {
        Class<?> enclosingClass;
        JvmPropertySignature jvmPropertySignatureMapPropertySignature = RuntimeTypeMapper.INSTANCE.mapPropertySignature(kPropertyImpl.getDescriptor());
        if (jvmPropertySignatureMapPropertySignature instanceof JvmPropertySignature.KotlinProperty) {
            JvmPropertySignature.KotlinProperty kotlinProperty = (JvmPropertySignature.KotlinProperty) jvmPropertySignatureMapPropertySignature;
            PropertyDescriptor descriptor = kotlinProperty.getDescriptor();
            JvmMemberSignature.Field jvmFieldSignature$default = JvmProtoBufUtil.getJvmFieldSignature$default(JvmProtoBufUtil.INSTANCE, kotlinProperty.getProto(), kotlinProperty.getNameResolver(), kotlinProperty.getTypeTable(), false, 8, null);
            if (jvmFieldSignature$default == null) {
                return null;
            }
            if (DescriptorsJvmAbiUtil.isPropertyWithBackingFieldInOuterClass(descriptor) || JvmProtoBufUtil.isMovedFromInterfaceCompanion(kotlinProperty.getProto())) {
                enclosingClass = kPropertyImpl.getContainer().getJClass().getEnclosingClass();
            } else {
                DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
                enclosingClass = containingDeclaration instanceof ClassDescriptor ? UtilKt.toJavaClass((ClassDescriptor) containingDeclaration) : kPropertyImpl.getContainer().getJClass();
            }
            if (enclosingClass == null) {
                return null;
            }
            try {
                return enclosingClass.getDeclaredField(jvmFieldSignature$default.getName());
            } catch (NoSuchFieldException unused) {
                return null;
            }
        }
        if (jvmPropertySignatureMapPropertySignature instanceof JvmPropertySignature.JavaField) {
            return ((JvmPropertySignature.JavaField) jvmPropertySignatureMapPropertySignature).getField();
        }
        if ((jvmPropertySignatureMapPropertySignature instanceof JvmPropertySignature.JavaMethodProperty) || (jvmPropertySignatureMapPropertySignature instanceof JvmPropertySignature.MappedKotlinProperty)) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final Field getJavaField() {
        return (Field) this._javaField.getValue();
    }

    protected final Member computeDelegateSource() {
        if (!getDescriptor().isDelegated()) {
            return null;
        }
        JvmPropertySignature jvmPropertySignatureMapPropertySignature = RuntimeTypeMapper.INSTANCE.mapPropertySignature(getDescriptor());
        if (jvmPropertySignatureMapPropertySignature instanceof JvmPropertySignature.KotlinProperty) {
            JvmPropertySignature.KotlinProperty kotlinProperty = (JvmPropertySignature.KotlinProperty) jvmPropertySignatureMapPropertySignature;
            if (kotlinProperty.getSignature().hasDelegateMethod()) {
                JvmProtoBuf.JvmMethodSignature delegateMethod = kotlinProperty.getSignature().getDelegateMethod();
                if (!delegateMethod.hasName() || !delegateMethod.hasDesc()) {
                    return null;
                }
                return getContainer().findMethodBySignature(kotlinProperty.getNameResolver().getString(delegateMethod.getName()), kotlinProperty.getNameResolver().getString(delegateMethod.getDesc()));
            }
        }
        return getJavaField();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final Object getDelegateImpl(Member member, Object obj, Object obj2) throws IllegalPropertyDelegateAccessException {
        try {
            Object obj3 = EXTENSION_PROPERTY_DELEGATE;
            if ((obj == obj3 || obj2 == obj3) && getDescriptor().getExtensionReceiverParameter() == null) {
                throw new RuntimeException(CoreConstants.SINGLE_QUOTE_CHAR + this + "' is not an extension property and thus getExtensionDelegate() is not going to work, use getDelegate() instead");
            }
            Object boundReceiver = isBound() ? getBoundReceiver() : obj;
            if (boundReceiver == obj3) {
                boundReceiver = null;
            }
            if (!isBound()) {
                obj = obj2;
            }
            if (obj == obj3) {
                obj = null;
            }
            AccessibleObject accessibleObject = member instanceof AccessibleObject ? (AccessibleObject) member : null;
            if (accessibleObject != null) {
                accessibleObject.setAccessible(KCallablesJvm.isAccessible(this));
            }
            if (member == 0) {
                return null;
            }
            if (member instanceof Field) {
                return ((Field) member).get(boundReceiver);
            }
            if (!(member instanceof Method)) {
                throw new AssertionError("delegate field/method " + member + " neither field nor method");
            }
            int length = ((Method) member).getParameterTypes().length;
            if (length == 0) {
                return ((Method) member).invoke(null, null);
            }
            if (length == 1) {
                Method method = (Method) member;
                if (boundReceiver == null) {
                    Class<?> cls = ((Method) member).getParameterTypes()[0];
                    Intrinsics.checkNotNullExpressionValue(cls, "get(...)");
                    boundReceiver = UtilKt.defaultPrimitiveValue(cls);
                }
                return method.invoke(null, boundReceiver);
            }
            if (length == 2) {
                Method method2 = (Method) member;
                if (obj == null) {
                    Class<?> cls2 = ((Method) member).getParameterTypes()[1];
                    Intrinsics.checkNotNullExpressionValue(cls2, "get(...)");
                    obj = UtilKt.defaultPrimitiveValue(cls2);
                }
                return method2.invoke(null, boundReceiver, obj);
            }
            throw new AssertionError("delegate method " + member + " should take 0, 1, or 2 parameters");
        } catch (IllegalAccessException e) {
            throw new IllegalPropertyDelegateAccessException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PropertyDescriptor _descriptor$lambda$5(KPropertyImpl kPropertyImpl) {
        return kPropertyImpl.getContainer().findPropertyDescriptor(kPropertyImpl.getName(), kPropertyImpl.signature);
    }

    @Override // kotlin.reflect.jvm.internal.KCallableImpl
    public PropertyDescriptor getDescriptor() {
        Object objInvoke = this._descriptor.invoke();
        Intrinsics.checkNotNullExpressionValue(objInvoke, "invoke(...)");
        return (PropertyDescriptor) objInvoke;
    }

    @Override // kotlin.reflect.jvm.internal.KCallableImpl
    public Caller getCaller() {
        return getGetter().getCaller();
    }

    @Override // kotlin.reflect.jvm.internal.KCallableImpl
    public Caller getDefaultCaller() {
        return getGetter().getDefaultCaller();
    }

    public boolean equals(Object obj) {
        KPropertyImpl kPropertyImplAsKPropertyImpl = UtilKt.asKPropertyImpl(obj);
        return kPropertyImplAsKPropertyImpl != null && Intrinsics.areEqual(getContainer(), kPropertyImplAsKPropertyImpl.getContainer()) && Intrinsics.areEqual(getName(), kPropertyImplAsKPropertyImpl.getName()) && Intrinsics.areEqual(this.signature, kPropertyImplAsKPropertyImpl.signature) && Intrinsics.areEqual(this.rawBoundReceiver, kPropertyImplAsKPropertyImpl.rawBoundReceiver);
    }

    public int hashCode() {
        return (((getContainer().hashCode() * 31) + getName().hashCode()) * 31) + this.signature.hashCode();
    }

    public String toString() {
        return ReflectionObjectRenderer.INSTANCE.renderProperty(getDescriptor());
    }

    /* JADX INFO: compiled from: KPropertyImpl.kt */
    public static abstract class Accessor extends KCallableImpl implements KFunction, KProperty.Accessor {
        @Override // kotlin.reflect.jvm.internal.KCallableImpl
        public Caller getDefaultCaller() {
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.KCallableImpl
        public abstract PropertyAccessorDescriptor getDescriptor();

        public abstract KPropertyImpl getProperty();

        @Override // kotlin.reflect.jvm.internal.KCallableImpl
        public KDeclarationContainerImpl getContainer() {
            return getProperty().getContainer();
        }

        @Override // kotlin.reflect.jvm.internal.KCallableImpl
        public boolean isBound() {
            return getProperty().isBound();
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
    }

    /* JADX INFO: compiled from: KPropertyImpl.kt */
    public static abstract class Getter extends Accessor implements KProperty.Getter {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Getter.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertyGetterDescriptor;"))};
        private final ReflectProperties.LazySoftVal descriptor$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KPropertyImpl$Getter$$Lambda$0
            private final KPropertyImpl.Getter arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KPropertyImpl.Getter.descriptor_delegate$lambda$0(this.arg$0);
            }
        });
        private final Lazy caller$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KPropertyImpl$Getter$$Lambda$1
            private final KPropertyImpl.Getter arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KPropertyImpl.Getter.caller_delegate$lambda$1(this.arg$0);
            }
        });

        @Override // kotlin.reflect.KCallable
        public String getName() {
            return "<get-" + getProperty().getName() + '>';
        }

        @Override // kotlin.reflect.jvm.internal.KPropertyImpl.Accessor, kotlin.reflect.jvm.internal.KCallableImpl
        public PropertyGetterDescriptor getDescriptor() {
            Object value = this.descriptor$delegate.getValue(this, $$delegatedProperties[0]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (PropertyGetterDescriptor) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final PropertyGetterDescriptor descriptor_delegate$lambda$0(Getter getter) {
            PropertyGetterDescriptor getter2 = getter.getProperty().getDescriptor().getGetter();
            if (getter2 != null) {
                return getter2;
            }
            PropertyGetterDescriptorImpl propertyGetterDescriptorImplCreateDefaultGetter = DescriptorFactory.createDefaultGetter(getter.getProperty().getDescriptor(), Annotations.Companion.getEMPTY());
            Intrinsics.checkNotNullExpressionValue(propertyGetterDescriptorImplCreateDefaultGetter, "createDefaultGetter(...)");
            return propertyGetterDescriptorImplCreateDefaultGetter;
        }

        @Override // kotlin.reflect.jvm.internal.KCallableImpl
        public Caller getCaller() {
            return (Caller) this.caller$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Caller caller_delegate$lambda$1(Getter getter) {
            return KPropertyImplKt.computeCallerForAccessor(getter, true);
        }

        public String toString() {
            return "getter of " + getProperty();
        }

        public boolean equals(Object obj) {
            return (obj instanceof Getter) && Intrinsics.areEqual(getProperty(), ((Getter) obj).getProperty());
        }

        public int hashCode() {
            return getProperty().hashCode();
        }
    }

    /* JADX INFO: compiled from: KPropertyImpl.kt */
    public static abstract class Setter extends Accessor implements KMutableProperty.Setter {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Setter.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertySetterDescriptor;"))};
        private final ReflectProperties.LazySoftVal descriptor$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KPropertyImpl$Setter$$Lambda$0
            private final KPropertyImpl.Setter arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KPropertyImpl.Setter.descriptor_delegate$lambda$0(this.arg$0);
            }
        });
        private final Lazy caller$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KPropertyImpl$Setter$$Lambda$1
            private final KPropertyImpl.Setter arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KPropertyImpl.Setter.caller_delegate$lambda$1(this.arg$0);
            }
        });

        @Override // kotlin.reflect.KCallable
        public String getName() {
            return "<set-" + getProperty().getName() + '>';
        }

        @Override // kotlin.reflect.jvm.internal.KPropertyImpl.Accessor, kotlin.reflect.jvm.internal.KCallableImpl
        public PropertySetterDescriptor getDescriptor() {
            Object value = this.descriptor$delegate.getValue(this, $$delegatedProperties[0]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (PropertySetterDescriptor) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final PropertySetterDescriptor descriptor_delegate$lambda$0(Setter setter) {
            PropertySetterDescriptor setter2 = setter.getProperty().getDescriptor().getSetter();
            if (setter2 != null) {
                return setter2;
            }
            PropertyDescriptor descriptor = setter.getProperty().getDescriptor();
            Annotations.Companion companion = Annotations.Companion;
            PropertySetterDescriptorImpl propertySetterDescriptorImplCreateDefaultSetter = DescriptorFactory.createDefaultSetter(descriptor, companion.getEMPTY(), companion.getEMPTY());
            Intrinsics.checkNotNullExpressionValue(propertySetterDescriptorImplCreateDefaultSetter, "createDefaultSetter(...)");
            return propertySetterDescriptorImplCreateDefaultSetter;
        }

        @Override // kotlin.reflect.jvm.internal.KCallableImpl
        public Caller getCaller() {
            return (Caller) this.caller$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Caller caller_delegate$lambda$1(Setter setter) {
            return KPropertyImplKt.computeCallerForAccessor(setter, false);
        }

        public String toString() {
            return "setter of " + getProperty();
        }

        public boolean equals(Object obj) {
            return (obj instanceof Setter) && Intrinsics.areEqual(getProperty(), ((Setter) obj).getProperty());
        }

        public int hashCode() {
            return getProperty().hashCode();
        }
    }

    /* JADX INFO: compiled from: KPropertyImpl.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
