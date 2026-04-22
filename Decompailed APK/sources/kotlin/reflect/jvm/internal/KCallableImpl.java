package kotlin.reflect.jvm.internal;

import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.KVisibility;
import kotlin.reflect.full.IllegalCallableAccessException;
import kotlin.reflect.jvm.KTypesJvm;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.reflect.jvm.internal.ReflectProperties;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.ValueClassAwareCallerKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;

/* JADX INFO: compiled from: KCallableImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class KCallableImpl implements KCallable, KTypeParameterOwnerImpl {
    private final ReflectProperties.LazySoftVal _absentArguments;
    private final ReflectProperties.LazySoftVal _annotations;
    private final ReflectProperties.LazySoftVal _parameters;
    private final ReflectProperties.LazySoftVal _returnType;
    private final ReflectProperties.LazySoftVal _typeParameters;
    private final Lazy parametersNeedMFVCFlattening;

    /* JADX INFO: Access modifiers changed from: private */
    public static final ParameterDescriptor _parameters$lambda$5$lambda$1(ReceiverParameterDescriptor receiverParameterDescriptor) {
        return receiverParameterDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ParameterDescriptor _parameters$lambda$5$lambda$2(ReceiverParameterDescriptor receiverParameterDescriptor) {
        return receiverParameterDescriptor;
    }

    public abstract Caller getCaller();

    public abstract KDeclarationContainerImpl getContainer();

    public abstract Caller getDefaultCaller();

    public abstract CallableMemberDescriptor getDescriptor();

    public abstract boolean isBound();

    public KCallableImpl() {
        ReflectProperties.LazySoftVal lazySoftValLazySoft = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KCallableImpl$$Lambda$0
            private final KCallableImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KCallableImpl._annotations$lambda$0(this.arg$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft, "lazySoft(...)");
        this._annotations = lazySoftValLazySoft;
        ReflectProperties.LazySoftVal lazySoftValLazySoft2 = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KCallableImpl$$Lambda$1
            private final KCallableImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KCallableImpl._parameters$lambda$5(this.arg$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft2, "lazySoft(...)");
        this._parameters = lazySoftValLazySoft2;
        ReflectProperties.LazySoftVal lazySoftValLazySoft3 = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KCallableImpl$$Lambda$2
            private final KCallableImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KCallableImpl._returnType$lambda$7(this.arg$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft3, "lazySoft(...)");
        this._returnType = lazySoftValLazySoft3;
        ReflectProperties.LazySoftVal lazySoftValLazySoft4 = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KCallableImpl$$Lambda$3
            private final KCallableImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KCallableImpl._typeParameters$lambda$9(this.arg$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft4, "lazySoft(...)");
        this._typeParameters = lazySoftValLazySoft4;
        ReflectProperties.LazySoftVal lazySoftValLazySoft5 = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KCallableImpl$$Lambda$4
            private final KCallableImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KCallableImpl._absentArguments$lambda$14(this.arg$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft5, "lazySoft(...)");
        this._absentArguments = lazySoftValLazySoft5;
        this.parametersNeedMFVCFlattening = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KCallableImpl$$Lambda$5
            private final KCallableImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return Boolean.valueOf(KCallableImpl.parametersNeedMFVCFlattening$lambda$20(this.arg$0));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List _annotations$lambda$0(KCallableImpl kCallableImpl) {
        return UtilKt.computeAnnotations(kCallableImpl.getDescriptor());
    }

    @Override // kotlin.reflect.KAnnotatedElement
    public List getAnnotations() {
        Object objInvoke = this._annotations.invoke();
        Intrinsics.checkNotNullExpressionValue(objInvoke, "invoke(...)");
        return (List) objInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList _parameters$lambda$5(KCallableImpl kCallableImpl) {
        int i;
        final CallableMemberDescriptor descriptor = kCallableImpl.getDescriptor();
        ArrayList arrayList = new ArrayList();
        final int i2 = 0;
        if (kCallableImpl.isBound()) {
            i = 0;
        } else {
            final ReceiverParameterDescriptor instanceReceiverParameter = UtilKt.getInstanceReceiverParameter(descriptor);
            if (instanceReceiverParameter != null) {
                arrayList.add(new KParameterImpl(kCallableImpl, 0, KParameter.Kind.INSTANCE, new Function0(instanceReceiverParameter) { // from class: kotlin.reflect.jvm.internal.KCallableImpl$$Lambda$6
                    private final ReceiverParameterDescriptor arg$0;

                    {
                        this.arg$0 = instanceReceiverParameter;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return KCallableImpl._parameters$lambda$5$lambda$1(this.arg$0);
                    }
                }));
                i = 1;
            } else {
                i = 0;
            }
            final ReceiverParameterDescriptor extensionReceiverParameter = descriptor.getExtensionReceiverParameter();
            if (extensionReceiverParameter != null) {
                arrayList.add(new KParameterImpl(kCallableImpl, i, KParameter.Kind.EXTENSION_RECEIVER, new Function0(extensionReceiverParameter) { // from class: kotlin.reflect.jvm.internal.KCallableImpl$$Lambda$7
                    private final ReceiverParameterDescriptor arg$0;

                    {
                        this.arg$0 = extensionReceiverParameter;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return KCallableImpl._parameters$lambda$5$lambda$2(this.arg$0);
                    }
                }));
                i++;
            }
        }
        int size = descriptor.getValueParameters().size();
        while (i2 < size) {
            arrayList.add(new KParameterImpl(kCallableImpl, i, KParameter.Kind.VALUE, new Function0(descriptor, i2) { // from class: kotlin.reflect.jvm.internal.KCallableImpl$$Lambda$8
                private final CallableMemberDescriptor arg$0;
                private final int arg$1;

                {
                    this.arg$0 = descriptor;
                    this.arg$1 = i2;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KCallableImpl._parameters$lambda$5$lambda$3(this.arg$0, this.arg$1);
                }
            }));
            i2++;
            i++;
        }
        if (kCallableImpl.isAnnotationConstructor() && (descriptor instanceof JavaCallableMemberDescriptor) && arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: kotlin.reflect.jvm.internal.KCallableImpl$_parameters$lambda$5$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ComparisonsKt.compareValues(((KParameter) obj).getName(), ((KParameter) obj2).getName());
                }
            });
        }
        arrayList.trimToSize();
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ParameterDescriptor _parameters$lambda$5$lambda$3(CallableMemberDescriptor callableMemberDescriptor, int i) {
        Object obj = callableMemberDescriptor.getValueParameters().get(i);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (ParameterDescriptor) obj;
    }

    @Override // kotlin.reflect.KCallable
    public List getParameters() {
        Object objInvoke = this._parameters.invoke();
        Intrinsics.checkNotNullExpressionValue(objInvoke, "invoke(...)");
        return (List) objInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KTypeImpl _returnType$lambda$7(final KCallableImpl kCallableImpl) {
        KotlinType returnType = kCallableImpl.getDescriptor().getReturnType();
        Intrinsics.checkNotNull(returnType);
        return new KTypeImpl(returnType, new Function0(kCallableImpl) { // from class: kotlin.reflect.jvm.internal.KCallableImpl$$Lambda$9
            private final KCallableImpl arg$0;

            {
                this.arg$0 = kCallableImpl;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KCallableImpl._returnType$lambda$7$lambda$6(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Type _returnType$lambda$7$lambda$6(KCallableImpl kCallableImpl) {
        Type typeExtractContinuationArgument = kCallableImpl.extractContinuationArgument();
        return typeExtractContinuationArgument == null ? kCallableImpl.getCaller().getReturnType() : typeExtractContinuationArgument;
    }

    @Override // kotlin.reflect.KCallable
    public KType getReturnType() {
        Object objInvoke = this._returnType.invoke();
        Intrinsics.checkNotNullExpressionValue(objInvoke, "invoke(...)");
        return (KType) objInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List _typeParameters$lambda$9(KCallableImpl kCallableImpl) {
        List<TypeParameterDescriptor> typeParameters = kCallableImpl.getDescriptor().getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(typeParameters, "getTypeParameters(...)");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        for (TypeParameterDescriptor typeParameterDescriptor : typeParameters) {
            Intrinsics.checkNotNull(typeParameterDescriptor);
            arrayList.add(new KTypeParameterImpl(kCallableImpl, typeParameterDescriptor));
        }
        return arrayList;
    }

    @Override // kotlin.reflect.KCallable
    public List getTypeParameters() {
        Object objInvoke = this._typeParameters.invoke();
        Intrinsics.checkNotNullExpressionValue(objInvoke, "invoke(...)");
        return (List) objInvoke;
    }

    @Override // kotlin.reflect.KCallable
    public KVisibility getVisibility() {
        DescriptorVisibility visibility = getDescriptor().getVisibility();
        Intrinsics.checkNotNullExpressionValue(visibility, "getVisibility(...)");
        return UtilKt.toKVisibility(visibility);
    }

    @Override // kotlin.reflect.KCallable
    public boolean isFinal() {
        return getDescriptor().getModality() == Modality.FINAL;
    }

    @Override // kotlin.reflect.KCallable
    public boolean isOpen() {
        return getDescriptor().getModality() == Modality.OPEN;
    }

    @Override // kotlin.reflect.KCallable
    public boolean isAbstract() {
        return getDescriptor().getModality() == Modality.ABSTRACT;
    }

    protected final boolean isAnnotationConstructor() {
        return Intrinsics.areEqual(getName(), "<init>") && getContainer().getJClass().isAnnotation();
    }

    @Override // kotlin.reflect.KCallable
    public Object call(Object... args) throws IllegalCallableAccessException {
        Intrinsics.checkNotNullParameter(args, "args");
        try {
            return getCaller().call(args);
        } catch (IllegalAccessException e) {
            throw new IllegalCallableAccessException(e);
        }
    }

    @Override // kotlin.reflect.KCallable
    public Object callBy(Map args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return isAnnotationConstructor() ? callAnnotationConstructor(args) : callDefaultMethod$kotlin_reflection(args, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object[] _absentArguments$lambda$14(KCallableImpl kCallableImpl) {
        int parameterTypeSize;
        List<KParameter> parameters = kCallableImpl.getParameters();
        int size = parameters.size() + (kCallableImpl.isSuspend() ? 1 : 0);
        if (((Boolean) kCallableImpl.parametersNeedMFVCFlattening.getValue()).booleanValue()) {
            parameterTypeSize = 0;
            for (KParameter kParameter : parameters) {
                parameterTypeSize += kParameter.getKind() == KParameter.Kind.VALUE ? kCallableImpl.getParameterTypeSize(kParameter) : 0;
            }
        } else if (parameters.isEmpty()) {
            parameterTypeSize = 0;
        } else {
            Iterator it = parameters.iterator();
            parameterTypeSize = 0;
            while (it.hasNext()) {
                if (((KParameter) it.next()).getKind() == KParameter.Kind.VALUE && (parameterTypeSize = parameterTypeSize + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        int i = (parameterTypeSize + 31) / 32;
        Object[] objArr = new Object[size + i + 1];
        for (KParameter kParameter2 : parameters) {
            if (kParameter2.isOptional() && !UtilKt.isInlineClassType(kParameter2.getType())) {
                objArr[kParameter2.getIndex()] = UtilKt.defaultPrimitiveValue(ReflectJvmMapping.getJavaType(kParameter2.getType()));
            } else if (kParameter2.isVararg()) {
                objArr[kParameter2.getIndex()] = kCallableImpl.defaultEmptyArray(kParameter2.getType());
            }
        }
        for (int i2 = 0; i2 < i; i2++) {
            objArr[size + i2] = 0;
        }
        return objArr;
    }

    private final Object[] getAbsentArguments() {
        return (Object[]) ((Object[]) this._absentArguments.invoke()).clone();
    }

    public final Object callDefaultMethod$kotlin_reflection(Map args, Continuation continuation) throws IllegalCallableAccessException {
        Intrinsics.checkNotNullParameter(args, "args");
        List<KParameter> parameters = getParameters();
        boolean z = false;
        if (!parameters.isEmpty()) {
            int size = parameters.size() + (isSuspend() ? 1 : 0);
            Object[] absentArguments = getAbsentArguments();
            if (isSuspend()) {
                absentArguments[parameters.size()] = continuation;
            }
            boolean zBooleanValue = ((Boolean) this.parametersNeedMFVCFlattening.getValue()).booleanValue();
            int i = 0;
            for (KParameter kParameter : parameters) {
                int parameterTypeSize = zBooleanValue ? getParameterTypeSize(kParameter) : 1;
                if (args.containsKey(kParameter)) {
                    absentArguments[kParameter.getIndex()] = args.get(kParameter);
                } else if (kParameter.isOptional()) {
                    if (zBooleanValue) {
                        int i2 = i + parameterTypeSize;
                        for (int i3 = i; i3 < i2; i3++) {
                            int i4 = (i3 / 32) + size;
                            Object obj = absentArguments[i4];
                            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                            absentArguments[i4] = Integer.valueOf(((Integer) obj).intValue() | (1 << (i3 % 32)));
                        }
                    } else {
                        int i5 = (i / 32) + size;
                        Object obj2 = absentArguments[i5];
                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
                        absentArguments[i5] = Integer.valueOf(((Integer) obj2).intValue() | (1 << (i % 32)));
                    }
                    z = true;
                } else if (!kParameter.isVararg()) {
                    throw new IllegalArgumentException("No argument provided for a required parameter: " + kParameter);
                }
                if (kParameter.getKind() == KParameter.Kind.VALUE) {
                    i += parameterTypeSize;
                }
            }
            if (!z) {
                try {
                    Caller caller = getCaller();
                    Object[] objArrCopyOf = Arrays.copyOf(absentArguments, size);
                    Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
                    return caller.call(objArrCopyOf);
                } catch (IllegalAccessException e) {
                    throw new IllegalCallableAccessException(e);
                }
            }
            Caller defaultCaller = getDefaultCaller();
            if (defaultCaller == null) {
                throw new KotlinReflectionInternalError("This callable does not support a default call: " + getDescriptor());
            }
            try {
                return defaultCaller.call(absentArguments);
            } catch (IllegalAccessException e2) {
                throw new IllegalCallableAccessException(e2);
            }
        }
        try {
            return getCaller().call(isSuspend() ? new Continuation[]{continuation} : new Continuation[0]);
        } catch (IllegalAccessException e3) {
            throw new IllegalCallableAccessException(e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean parametersNeedMFVCFlattening$lambda$20(KCallableImpl kCallableImpl) {
        List parameters = kCallableImpl.getParameters();
        if ((parameters instanceof Collection) && parameters.isEmpty()) {
            return false;
        }
        Iterator it = parameters.iterator();
        while (it.hasNext()) {
            if (UtilKt.getNeedsMultiFieldValueClassFlattening(((KParameter) it.next()).getType())) {
                return true;
            }
        }
        return false;
    }

    private final int getParameterTypeSize(KParameter kParameter) {
        if (!((Boolean) this.parametersNeedMFVCFlattening.getValue()).booleanValue()) {
            throw new IllegalArgumentException("Check if parametersNeedMFVCFlattening is true before");
        }
        if (!UtilKt.getNeedsMultiFieldValueClassFlattening(kParameter.getType())) {
            return 1;
        }
        KType type = kParameter.getType();
        Intrinsics.checkNotNull(type, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
        List mfvcUnboxMethods = ValueClassAwareCallerKt.getMfvcUnboxMethods(TypeSubstitutionKt.asSimpleType(((KTypeImpl) type).getType()));
        Intrinsics.checkNotNull(mfvcUnboxMethods);
        return mfvcUnboxMethods.size();
    }

    private final Object callAnnotationConstructor(Map map) throws IllegalCallableAccessException {
        Object objDefaultEmptyArray;
        List<KParameter> parameters = getParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        for (KParameter kParameter : parameters) {
            if (map.containsKey(kParameter)) {
                objDefaultEmptyArray = map.get(kParameter);
                if (objDefaultEmptyArray == null) {
                    throw new IllegalArgumentException("Annotation argument value cannot be null (" + kParameter + CoreConstants.RIGHT_PARENTHESIS_CHAR);
                }
            } else if (kParameter.isOptional()) {
                objDefaultEmptyArray = null;
            } else {
                if (!kParameter.isVararg()) {
                    throw new IllegalArgumentException("No argument provided for a required parameter: " + kParameter);
                }
                objDefaultEmptyArray = defaultEmptyArray(kParameter.getType());
            }
            arrayList.add(objDefaultEmptyArray);
        }
        Caller defaultCaller = getDefaultCaller();
        if (defaultCaller == null) {
            throw new KotlinReflectionInternalError("This callable does not support a default call: " + getDescriptor());
        }
        try {
            return defaultCaller.call(arrayList.toArray(new Object[0]));
        } catch (IllegalAccessException e) {
            throw new IllegalCallableAccessException(e);
        }
    }

    private final Object defaultEmptyArray(KType kType) {
        Class javaClass = JvmClassMappingKt.getJavaClass(KTypesJvm.getJvmErasure(kType));
        if (javaClass.isArray()) {
            Object objNewInstance = Array.newInstance(javaClass.getComponentType(), 0);
            Intrinsics.checkNotNullExpressionValue(objNewInstance, "run(...)");
            return objNewInstance;
        }
        throw new KotlinReflectionInternalError("Cannot instantiate the default empty array of type " + javaClass.getSimpleName() + ", because it is not an array type");
    }

    private final Type extractContinuationArgument() {
        Type[] lowerBounds;
        if (!isSuspend()) {
            return null;
        }
        Object objLastOrNull = CollectionsKt.lastOrNull(getCaller().getParameterTypes());
        ParameterizedType parameterizedType = objLastOrNull instanceof ParameterizedType ? (ParameterizedType) objLastOrNull : null;
        if (!Intrinsics.areEqual(parameterizedType != null ? parameterizedType.getRawType() : null, Continuation.class)) {
            return null;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Intrinsics.checkNotNullExpressionValue(actualTypeArguments, "getActualTypeArguments(...)");
        Object objSingle = ArraysKt.single(actualTypeArguments);
        WildcardType wildcardType = objSingle instanceof WildcardType ? (WildcardType) objSingle : null;
        if (wildcardType == null || (lowerBounds = wildcardType.getLowerBounds()) == null) {
            return null;
        }
        return (Type) ArraysKt.first(lowerBounds);
    }
}
