package kotlin.reflect.jvm.internal;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.ReflectProperties;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.ValueClassAwareCaller;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* JADX INFO: compiled from: KParameterImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class KParameterImpl implements KParameter {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KParameterImpl.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/ParameterDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KParameterImpl.class), "annotations", "getAnnotations()Ljava/util/List;"))};
    private final ReflectProperties.LazySoftVal annotations$delegate;
    private final KCallableImpl callable;
    private final ReflectProperties.LazySoftVal descriptor$delegate;
    private final int index;
    private final KParameter.Kind kind;

    public KParameterImpl(KCallableImpl callable, int i, KParameter.Kind kind, Function0 computeDescriptor) {
        Intrinsics.checkNotNullParameter(callable, "callable");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(computeDescriptor, "computeDescriptor");
        this.callable = callable;
        this.index = i;
        this.kind = kind;
        this.descriptor$delegate = ReflectProperties.lazySoft(computeDescriptor);
        this.annotations$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KParameterImpl$$Lambda$0
            private final KParameterImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KParameterImpl.annotations_delegate$lambda$0(this.arg$0);
            }
        });
    }

    public final KCallableImpl getCallable() {
        return this.callable;
    }

    @Override // kotlin.reflect.KParameter
    public int getIndex() {
        return this.index;
    }

    @Override // kotlin.reflect.KParameter
    public KParameter.Kind getKind() {
        return this.kind;
    }

    private final ParameterDescriptor getDescriptor() {
        Object value = this.descriptor$delegate.getValue(this, $$delegatedProperties[0]);
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ParameterDescriptor) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List annotations_delegate$lambda$0(KParameterImpl kParameterImpl) {
        return UtilKt.computeAnnotations(kParameterImpl.getDescriptor());
    }

    @Override // kotlin.reflect.KParameter
    public String getName() {
        ParameterDescriptor descriptor = getDescriptor();
        ValueParameterDescriptor valueParameterDescriptor = descriptor instanceof ValueParameterDescriptor ? (ValueParameterDescriptor) descriptor : null;
        if (valueParameterDescriptor == null || valueParameterDescriptor.getContainingDeclaration().hasSynthesizedParameterNames()) {
            return null;
        }
        Name name = valueParameterDescriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        if (name.isSpecial()) {
            return null;
        }
        return name.asString();
    }

    private final Type compoundType(Type... typeArr) {
        int length = typeArr.length;
        if (length == 0) {
            throw new KotlinReflectionNotSupportedError("Expected at least 1 type for compound type");
        }
        if (length == 1) {
            return (Type) ArraysKt.single(typeArr);
        }
        return new CompoundTypeImpl(typeArr);
    }

    /* JADX INFO: compiled from: KParameterImpl.kt */
    private static final class CompoundTypeImpl implements Type {
        private final int hashCode;
        private final Type[] types;

        public CompoundTypeImpl(Type[] types) {
            Intrinsics.checkNotNullParameter(types, "types");
            this.types = types;
            this.hashCode = Arrays.hashCode(types);
        }

        @Override // java.lang.reflect.Type
        public String getTypeName() {
            return ArraysKt.joinToString$default(this.types, ", ", "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
        }

        public boolean equals(Object obj) {
            return (obj instanceof CompoundTypeImpl) && Arrays.equals(this.types, ((CompoundTypeImpl) obj).types);
        }

        public int hashCode() {
            return this.hashCode;
        }

        public String toString() {
            return getTypeName();
        }
    }

    @Override // kotlin.reflect.KParameter
    public KType getType() {
        KotlinType type = getDescriptor().getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        return new KTypeImpl(type, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KParameterImpl$$Lambda$1
            private final KParameterImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KParameterImpl._get_type_$lambda$1(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Type _get_type_$lambda$1(KParameterImpl kParameterImpl) {
        List listSlice;
        ParameterDescriptor descriptor = kParameterImpl.getDescriptor();
        if ((descriptor instanceof ReceiverParameterDescriptor) && Intrinsics.areEqual(UtilKt.getInstanceReceiverParameter(kParameterImpl.callable.getDescriptor()), descriptor) && kParameterImpl.callable.getDescriptor().getKind() == CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
            DeclarationDescriptor containingDeclaration = kParameterImpl.callable.getDescriptor().getContainingDeclaration();
            Intrinsics.checkNotNull(containingDeclaration, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            Class javaClass = UtilKt.toJavaClass((ClassDescriptor) containingDeclaration);
            if (javaClass != null) {
                return javaClass;
            }
            throw new KotlinReflectionInternalError("Cannot determine receiver Java type of inherited declaration: " + descriptor);
        }
        Caller caller = kParameterImpl.callable.getCaller();
        if (caller instanceof ValueClassAwareCaller) {
            if (kParameterImpl.callable.isBound()) {
                ValueClassAwareCaller valueClassAwareCaller = (ValueClassAwareCaller) caller;
                IntRange realSlicesOfParameters = valueClassAwareCaller.getRealSlicesOfParameters(kParameterImpl.getIndex() + 1);
                int last = valueClassAwareCaller.getRealSlicesOfParameters(0).getLast() + 1;
                listSlice = CollectionsKt.slice(valueClassAwareCaller.getParameterTypes(), new IntRange(realSlicesOfParameters.getFirst() - last, realSlicesOfParameters.getLast() - last));
            } else {
                ValueClassAwareCaller valueClassAwareCaller2 = (ValueClassAwareCaller) caller;
                listSlice = CollectionsKt.slice(valueClassAwareCaller2.getParameterTypes(), valueClassAwareCaller2.getRealSlicesOfParameters(kParameterImpl.getIndex()));
            }
            Type[] typeArr = (Type[]) listSlice.toArray(new Type[0]);
            return kParameterImpl.compoundType((Type[]) Arrays.copyOf(typeArr, typeArr.length));
        }
        if (caller instanceof ValueClassAwareCaller.MultiFieldValueClassPrimaryConstructorCaller) {
            Class[] clsArr = (Class[]) ((Collection) ((ValueClassAwareCaller.MultiFieldValueClassPrimaryConstructorCaller) caller).getOriginalParametersGroups().get(kParameterImpl.getIndex())).toArray(new Class[0]);
            return kParameterImpl.compoundType((Type[]) Arrays.copyOf(clsArr, clsArr.length));
        }
        return (Type) caller.getParameterTypes().get(kParameterImpl.getIndex());
    }

    @Override // kotlin.reflect.KParameter
    public boolean isOptional() {
        ParameterDescriptor descriptor = getDescriptor();
        ValueParameterDescriptor valueParameterDescriptor = descriptor instanceof ValueParameterDescriptor ? (ValueParameterDescriptor) descriptor : null;
        if (valueParameterDescriptor != null) {
            return DescriptorUtilsKt.declaresOrInheritsDefaultValue(valueParameterDescriptor);
        }
        return false;
    }

    @Override // kotlin.reflect.KParameter
    public boolean isVararg() {
        ParameterDescriptor descriptor = getDescriptor();
        return (descriptor instanceof ValueParameterDescriptor) && ((ValueParameterDescriptor) descriptor).getVarargElementType() != null;
    }

    public boolean equals(Object obj) {
        if (obj instanceof KParameterImpl) {
            KParameterImpl kParameterImpl = (KParameterImpl) obj;
            if (Intrinsics.areEqual(this.callable, kParameterImpl.callable) && getIndex() == kParameterImpl.getIndex()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.callable.hashCode() * 31) + getIndex();
    }

    public String toString() {
        return ReflectionObjectRenderer.INSTANCE.renderParameter(this);
    }
}
