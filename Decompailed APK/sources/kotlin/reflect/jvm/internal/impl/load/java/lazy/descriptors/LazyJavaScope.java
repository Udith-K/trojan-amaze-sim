package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUsage;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

/* JADX INFO: compiled from: LazyJavaScope.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class LazyJavaScope extends MemberScopeImpl {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "functionNamesLazy", "getFunctionNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "propertyNamesLazy", "getPropertyNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "classNamesLazy", "getClassNamesLazy()Ljava/util/Set;"))};
    private final NotNullLazyValue allDescriptors;
    private final LazyJavaResolverContext c;
    private final NotNullLazyValue classNamesLazy$delegate;
    private final MemoizedFunctionToNullable declaredField;
    private final MemoizedFunctionToNotNull declaredFunctions;
    private final NotNullLazyValue declaredMemberIndex;
    private final NotNullLazyValue functionNamesLazy$delegate;
    private final MemoizedFunctionToNotNull functions;
    private final LazyJavaScope mainScope;
    private final MemoizedFunctionToNotNull properties;
    private final NotNullLazyValue propertyNamesLazy$delegate;

    /* JADX INFO: Access modifiers changed from: private */
    public static final CallableDescriptor retainMostSpecificMethods$lambda$6(SimpleFunctionDescriptor selectMostSpecificInEachOverridableGroup) {
        Intrinsics.checkNotNullParameter(selectMostSpecificInEachOverridableGroup, "$this$selectMostSpecificInEachOverridableGroup");
        return selectMostSpecificInEachOverridableGroup;
    }

    protected abstract Set computeClassNames(DescriptorKindFilter descriptorKindFilter, Function1 function1);

    protected abstract Set computeFunctionNames(DescriptorKindFilter descriptorKindFilter, Function1 function1);

    protected void computeImplicitlyDeclaredFunctions(Collection result, Name name) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(name, "name");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract DeclaredMemberIndex computeMemberIndex();

    protected abstract void computeNonDeclaredFunctions(Collection collection, Name name);

    protected abstract void computeNonDeclaredProperties(Name name, Collection collection);

    protected abstract Set computePropertyNames(DescriptorKindFilter descriptorKindFilter, Function1 function1);

    protected abstract ReceiverParameterDescriptor getDispatchReceiverParameter();

    protected abstract DeclarationDescriptor getOwnerDescriptor();

    protected boolean isVisibleAsFunction(JavaMethodDescriptor javaMethodDescriptor) {
        Intrinsics.checkNotNullParameter(javaMethodDescriptor, "<this>");
        return true;
    }

    protected abstract MethodSignatureData resolveMethodSignature(JavaMethod javaMethod, List list, KotlinType kotlinType, List list2);

    public /* synthetic */ LazyJavaScope(LazyJavaResolverContext lazyJavaResolverContext, LazyJavaScope lazyJavaScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lazyJavaResolverContext, (i & 2) != 0 ? null : lazyJavaScope);
    }

    protected final LazyJavaResolverContext getC() {
        return this.c;
    }

    protected final LazyJavaScope getMainScope() {
        return this.mainScope;
    }

    public LazyJavaScope(LazyJavaResolverContext c, LazyJavaScope lazyJavaScope) {
        Intrinsics.checkNotNullParameter(c, "c");
        this.c = c;
        this.mainScope = lazyJavaScope;
        this.allDescriptors = c.getStorageManager().createRecursionTolerantLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$0
            private final LazyJavaScope arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return LazyJavaScope.allDescriptors$lambda$0(this.arg$0);
            }
        }, CollectionsKt.emptyList());
        this.declaredMemberIndex = c.getStorageManager().createLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$1
            private final LazyJavaScope arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.arg$0.computeMemberIndex();
            }
        });
        this.declaredFunctions = c.getStorageManager().createMemoizedFunction(new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$2
            private final LazyJavaScope arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return LazyJavaScope.declaredFunctions$lambda$2(this.arg$0, (Name) obj);
            }
        });
        this.declaredField = c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$3
            private final LazyJavaScope arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return LazyJavaScope.declaredField$lambda$3(this.arg$0, (Name) obj);
            }
        });
        this.functions = c.getStorageManager().createMemoizedFunction(new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$4
            private final LazyJavaScope arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return LazyJavaScope.functions$lambda$4(this.arg$0, (Name) obj);
            }
        });
        this.functionNamesLazy$delegate = c.getStorageManager().createLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$5
            private final LazyJavaScope arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return LazyJavaScope.functionNamesLazy_delegate$lambda$10(this.arg$0);
            }
        });
        this.propertyNamesLazy$delegate = c.getStorageManager().createLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$6
            private final LazyJavaScope arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return LazyJavaScope.propertyNamesLazy_delegate$lambda$11(this.arg$0);
            }
        });
        this.classNamesLazy$delegate = c.getStorageManager().createLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$7
            private final LazyJavaScope arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return LazyJavaScope.classNamesLazy_delegate$lambda$12(this.arg$0);
            }
        });
        this.properties = c.getStorageManager().createMemoizedFunction(new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$8
            private final LazyJavaScope arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return LazyJavaScope.properties$lambda$13(this.arg$0, (Name) obj);
            }
        });
    }

    protected final NotNullLazyValue getAllDescriptors() {
        return this.allDescriptors;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection allDescriptors$lambda$0(LazyJavaScope lazyJavaScope) {
        return lazyJavaScope.computeDescriptors(DescriptorKindFilter.ALL, MemberScope.Companion.getALL_NAME_FILTER());
    }

    protected final NotNullLazyValue getDeclaredMemberIndex() {
        return this.declaredMemberIndex;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection declaredFunctions$lambda$2(LazyJavaScope lazyJavaScope, Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        LazyJavaScope lazyJavaScope2 = lazyJavaScope.mainScope;
        if (lazyJavaScope2 != null) {
            return (Collection) lazyJavaScope2.declaredFunctions.invoke(name);
        }
        ArrayList arrayList = new ArrayList();
        for (JavaMethod javaMethod : ((DeclaredMemberIndex) lazyJavaScope.declaredMemberIndex.invoke()).findMethodsByName(name)) {
            JavaMethodDescriptor javaMethodDescriptorResolveMethodToFunctionDescriptor = lazyJavaScope.resolveMethodToFunctionDescriptor(javaMethod);
            if (lazyJavaScope.isVisibleAsFunction(javaMethodDescriptorResolveMethodToFunctionDescriptor)) {
                lazyJavaScope.c.getComponents().getJavaResolverCache().recordMethod(javaMethod, javaMethodDescriptorResolveMethodToFunctionDescriptor);
                arrayList.add(javaMethodDescriptorResolveMethodToFunctionDescriptor);
            }
        }
        lazyJavaScope.computeImplicitlyDeclaredFunctions(arrayList, name);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PropertyDescriptor declaredField$lambda$3(LazyJavaScope lazyJavaScope, Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        LazyJavaScope lazyJavaScope2 = lazyJavaScope.mainScope;
        if (lazyJavaScope2 != null) {
            return (PropertyDescriptor) lazyJavaScope2.declaredField.invoke(name);
        }
        JavaField javaFieldFindFieldByName = ((DeclaredMemberIndex) lazyJavaScope.declaredMemberIndex.invoke()).findFieldByName(name);
        if (javaFieldFindFieldByName == null || javaFieldFindFieldByName.isEnumEntry()) {
            return null;
        }
        return lazyJavaScope.resolveProperty(javaFieldFindFieldByName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection functions$lambda$4(LazyJavaScope lazyJavaScope, Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        LinkedHashSet linkedHashSet = new LinkedHashSet((Collection) lazyJavaScope.declaredFunctions.invoke(name));
        lazyJavaScope.retainMostSpecificMethods(linkedHashSet);
        lazyJavaScope.computeNonDeclaredFunctions(linkedHashSet, name);
        return CollectionsKt.toList(lazyJavaScope.c.getComponents().getSignatureEnhancement().enhanceSignatures(lazyJavaScope.c, linkedHashSet));
    }

    private final void retainMostSpecificMethods(Set set) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : set) {
            String strComputeJvmDescriptor$default = MethodSignatureMappingKt.computeJvmDescriptor$default((SimpleFunctionDescriptor) obj, false, false, 2, null);
            Object arrayList = linkedHashMap.get(strComputeJvmDescriptor$default);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(strComputeJvmDescriptor$default, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        for (List list : linkedHashMap.values()) {
            if (list.size() != 1) {
                Collection collectionSelectMostSpecificInEachOverridableGroup = OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(list, new Function1() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$9
                    @Override // kotlin.jvm.functions.Function1
                    public Object invoke(Object obj2) {
                        return LazyJavaScope.retainMostSpecificMethods$lambda$6((SimpleFunctionDescriptor) obj2);
                    }
                });
                set.removeAll(list);
                set.addAll(collectionSelectMostSpecificInEachOverridableGroup);
            }
        }
    }

    /* JADX INFO: compiled from: LazyJavaScope.kt */
    protected static final class MethodSignatureData {
        private final List errors;
        private final boolean hasStableParameterNames;
        private final KotlinType receiverType;
        private final KotlinType returnType;
        private final List typeParameters;
        private final List valueParameters;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MethodSignatureData)) {
                return false;
            }
            MethodSignatureData methodSignatureData = (MethodSignatureData) obj;
            return Intrinsics.areEqual(this.returnType, methodSignatureData.returnType) && Intrinsics.areEqual(this.receiverType, methodSignatureData.receiverType) && Intrinsics.areEqual(this.valueParameters, methodSignatureData.valueParameters) && Intrinsics.areEqual(this.typeParameters, methodSignatureData.typeParameters) && this.hasStableParameterNames == methodSignatureData.hasStableParameterNames && Intrinsics.areEqual(this.errors, methodSignatureData.errors);
        }

        public int hashCode() {
            int iHashCode = this.returnType.hashCode() * 31;
            KotlinType kotlinType = this.receiverType;
            return ((((((((iHashCode + (kotlinType == null ? 0 : kotlinType.hashCode())) * 31) + this.valueParameters.hashCode()) * 31) + this.typeParameters.hashCode()) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.hasStableParameterNames)) * 31) + this.errors.hashCode();
        }

        public String toString() {
            return "MethodSignatureData(returnType=" + this.returnType + ", receiverType=" + this.receiverType + ", valueParameters=" + this.valueParameters + ", typeParameters=" + this.typeParameters + ", hasStableParameterNames=" + this.hasStableParameterNames + ", errors=" + this.errors + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public MethodSignatureData(KotlinType returnType, KotlinType kotlinType, List valueParameters, List typeParameters, boolean z, List errors) {
            Intrinsics.checkNotNullParameter(returnType, "returnType");
            Intrinsics.checkNotNullParameter(valueParameters, "valueParameters");
            Intrinsics.checkNotNullParameter(typeParameters, "typeParameters");
            Intrinsics.checkNotNullParameter(errors, "errors");
            this.returnType = returnType;
            this.receiverType = kotlinType;
            this.valueParameters = valueParameters;
            this.typeParameters = typeParameters;
            this.hasStableParameterNames = z;
            this.errors = errors;
        }

        public final KotlinType getReturnType() {
            return this.returnType;
        }

        public final KotlinType getReceiverType() {
            return this.receiverType;
        }

        public final List getValueParameters() {
            return this.valueParameters;
        }

        public final List getTypeParameters() {
            return this.typeParameters;
        }

        public final boolean getHasStableParameterNames() {
            return this.hasStableParameterNames;
        }

        public final List getErrors() {
            return this.errors;
        }
    }

    protected final JavaMethodDescriptor resolveMethodToFunctionDescriptor(JavaMethod method) {
        Map mapEmptyMap;
        Intrinsics.checkNotNullParameter(method, "method");
        JavaMethodDescriptor javaMethodDescriptorCreateJavaMethod = JavaMethodDescriptor.createJavaMethod(getOwnerDescriptor(), LazyJavaAnnotationsKt.resolveAnnotations(this.c, method), method.getName(), this.c.getComponents().getSourceElementFactory().source(method), ((DeclaredMemberIndex) this.declaredMemberIndex.invoke()).findRecordComponentByName(method.getName()) != null && method.getValueParameters().isEmpty());
        Intrinsics.checkNotNullExpressionValue(javaMethodDescriptorCreateJavaMethod, "createJavaMethod(...)");
        LazyJavaResolverContext lazyJavaResolverContextChildForMethod$default = ContextKt.childForMethod$default(this.c, javaMethodDescriptorCreateJavaMethod, method, 0, 4, null);
        List typeParameters = method.getTypeParameters();
        List arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator it = typeParameters.iterator();
        while (it.hasNext()) {
            TypeParameterDescriptor typeParameterDescriptorResolveTypeParameter = lazyJavaResolverContextChildForMethod$default.getTypeParameterResolver().resolveTypeParameter((JavaTypeParameter) it.next());
            Intrinsics.checkNotNull(typeParameterDescriptorResolveTypeParameter);
            arrayList.add(typeParameterDescriptorResolveTypeParameter);
        }
        ResolvedValueParameters resolvedValueParametersResolveValueParameters = resolveValueParameters(lazyJavaResolverContextChildForMethod$default, javaMethodDescriptorCreateJavaMethod, method.getValueParameters());
        MethodSignatureData methodSignatureDataResolveMethodSignature = resolveMethodSignature(method, arrayList, computeMethodReturnType(method, lazyJavaResolverContextChildForMethod$default), resolvedValueParametersResolveValueParameters.getDescriptors());
        KotlinType receiverType = methodSignatureDataResolveMethodSignature.getReceiverType();
        ReceiverParameterDescriptor receiverParameterDescriptorCreateExtensionReceiverParameterForCallable = receiverType != null ? DescriptorFactory.createExtensionReceiverParameterForCallable(javaMethodDescriptorCreateJavaMethod, receiverType, Annotations.Companion.getEMPTY()) : null;
        ReceiverParameterDescriptor dispatchReceiverParameter = getDispatchReceiverParameter();
        List listEmptyList = CollectionsKt.emptyList();
        List typeParameters2 = methodSignatureDataResolveMethodSignature.getTypeParameters();
        List valueParameters = methodSignatureDataResolveMethodSignature.getValueParameters();
        KotlinType returnType = methodSignatureDataResolveMethodSignature.getReturnType();
        Modality modalityConvertFromFlags = Modality.Companion.convertFromFlags(false, method.isAbstract(), true ^ method.isFinal());
        DescriptorVisibility descriptorVisibility = UtilsKt.toDescriptorVisibility(method.getVisibility());
        if (methodSignatureDataResolveMethodSignature.getReceiverType() != null) {
            mapEmptyMap = MapsKt.mapOf(TuplesKt.to(JavaMethodDescriptor.ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER, CollectionsKt.first(resolvedValueParametersResolveValueParameters.getDescriptors())));
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        javaMethodDescriptorCreateJavaMethod.initialize(receiverParameterDescriptorCreateExtensionReceiverParameterForCallable, dispatchReceiverParameter, listEmptyList, typeParameters2, valueParameters, returnType, modalityConvertFromFlags, descriptorVisibility, mapEmptyMap);
        javaMethodDescriptorCreateJavaMethod.setParameterNamesStatus(methodSignatureDataResolveMethodSignature.getHasStableParameterNames(), resolvedValueParametersResolveValueParameters.getHasSynthesizedNames());
        if (!methodSignatureDataResolveMethodSignature.getErrors().isEmpty()) {
            lazyJavaResolverContextChildForMethod$default.getComponents().getSignaturePropagator().reportSignatureErrors(javaMethodDescriptorCreateJavaMethod, methodSignatureDataResolveMethodSignature.getErrors());
        }
        return javaMethodDescriptorCreateJavaMethod;
    }

    protected final KotlinType computeMethodReturnType(JavaMethod method, LazyJavaResolverContext c) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(c, "c");
        return c.getTypeResolver().transformJavaType(method.getReturnType(), JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, method.getContainingClass().isAnnotationType(), false, null, 6, null));
    }

    /* JADX INFO: compiled from: LazyJavaScope.kt */
    protected static final class ResolvedValueParameters {
        private final List descriptors;
        private final boolean hasSynthesizedNames;

        public ResolvedValueParameters(List descriptors, boolean z) {
            Intrinsics.checkNotNullParameter(descriptors, "descriptors");
            this.descriptors = descriptors;
            this.hasSynthesizedNames = z;
        }

        public final List getDescriptors() {
            return this.descriptors;
        }

        public final boolean getHasSynthesizedNames() {
            return this.hasSynthesizedNames;
        }
    }

    protected final ResolvedValueParameters resolveValueParameters(LazyJavaResolverContext lazyJavaResolverContext, FunctionDescriptor function, List jValueParameters) {
        Pair pair;
        Name name;
        LazyJavaResolverContext c = lazyJavaResolverContext;
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(jValueParameters, "jValueParameters");
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(jValueParameters);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterableWithIndex, 10));
        boolean z = false;
        for (IndexedValue indexedValue : iterableWithIndex) {
            int iComponent1 = indexedValue.component1();
            JavaValueParameter javaValueParameter = (JavaValueParameter) indexedValue.component2();
            Annotations annotationsResolveAnnotations = LazyJavaAnnotationsKt.resolveAnnotations(c, javaValueParameter);
            JavaTypeAttributes attributes$default = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, false, null, 7, null);
            if (javaValueParameter.isVararg()) {
                JavaType type = javaValueParameter.getType();
                JavaArrayType javaArrayType = type instanceof JavaArrayType ? (JavaArrayType) type : null;
                if (javaArrayType == null) {
                    throw new AssertionError("Vararg parameter should be an array: " + javaValueParameter);
                }
                KotlinType kotlinTypeTransformArrayType = lazyJavaResolverContext.getTypeResolver().transformArrayType(javaArrayType, attributes$default, true);
                pair = TuplesKt.to(kotlinTypeTransformArrayType, lazyJavaResolverContext.getModule().getBuiltIns().getArrayElementType(kotlinTypeTransformArrayType));
            } else {
                pair = TuplesKt.to(lazyJavaResolverContext.getTypeResolver().transformJavaType(javaValueParameter.getType(), attributes$default), null);
            }
            KotlinType kotlinType = (KotlinType) pair.component1();
            KotlinType kotlinType2 = (KotlinType) pair.component2();
            if (Intrinsics.areEqual(function.getName().asString(), "equals") && jValueParameters.size() == 1 && Intrinsics.areEqual(lazyJavaResolverContext.getModule().getBuiltIns().getNullableAnyType(), kotlinType)) {
                name = Name.identifier("other");
            } else {
                name = javaValueParameter.getName();
                if (name == null) {
                    z = true;
                }
                if (name == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append('p');
                    sb.append(iComponent1);
                    name = Name.identifier(sb.toString());
                    Intrinsics.checkNotNullExpressionValue(name, "identifier(...)");
                }
            }
            boolean z2 = z;
            Name name2 = name;
            Intrinsics.checkNotNull(name2);
            ArrayList arrayList2 = arrayList;
            arrayList2.add(new ValueParameterDescriptorImpl(function, null, iComponent1, annotationsResolveAnnotations, name2, kotlinType, false, false, false, kotlinType2, lazyJavaResolverContext.getComponents().getSourceElementFactory().source(javaValueParameter)));
            arrayList = arrayList2;
            z = z2;
            c = lazyJavaResolverContext;
        }
        return new ResolvedValueParameters(CollectionsKt.toList(arrayList), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set functionNamesLazy_delegate$lambda$10(LazyJavaScope lazyJavaScope) {
        return lazyJavaScope.computeFunctionNames(DescriptorKindFilter.FUNCTIONS, null);
    }

    private final Set getFunctionNamesLazy() {
        return (Set) StorageKt.getValue(this.functionNamesLazy$delegate, this, $$delegatedProperties[0]);
    }

    private final Set getPropertyNamesLazy() {
        return (Set) StorageKt.getValue(this.propertyNamesLazy$delegate, this, $$delegatedProperties[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set propertyNamesLazy_delegate$lambda$11(LazyJavaScope lazyJavaScope) {
        return lazyJavaScope.computePropertyNames(DescriptorKindFilter.VARIABLES, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set classNamesLazy_delegate$lambda$12(LazyJavaScope lazyJavaScope) {
        return lazyJavaScope.computeClassNames(DescriptorKindFilter.CLASSIFIERS, null);
    }

    private final Set getClassNamesLazy() {
        return (Set) StorageKt.getValue(this.classNamesLazy$delegate, this, $$delegatedProperties[2]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getFunctionNames() {
        return getFunctionNamesLazy();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getVariableNames() {
        return getPropertyNamesLazy();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getClassifierNames() {
        return getClassNamesLazy();
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedFunctions(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        return !getFunctionNames().contains(name) ? CollectionsKt.emptyList() : (Collection) this.functions.invoke(name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List properties$lambda$13(LazyJavaScope lazyJavaScope, Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ArrayList arrayList = new ArrayList();
        kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList, lazyJavaScope.declaredField.invoke(name));
        lazyJavaScope.computeNonDeclaredProperties(name, arrayList);
        if (DescriptorUtils.isAnnotationClass(lazyJavaScope.getOwnerDescriptor())) {
            return CollectionsKt.toList(arrayList);
        }
        return CollectionsKt.toList(lazyJavaScope.c.getComponents().getSignatureEnhancement().enhanceSignatures(lazyJavaScope.c, arrayList));
    }

    private final PropertyDescriptor resolveProperty(final JavaField javaField) {
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        PropertyDescriptorImpl propertyDescriptorImplCreatePropertyDescriptor = createPropertyDescriptor(javaField);
        ref$ObjectRef.element = propertyDescriptorImplCreatePropertyDescriptor;
        propertyDescriptorImplCreatePropertyDescriptor.initialize(null, null, null, null);
        ((PropertyDescriptorImpl) ref$ObjectRef.element).setType(getPropertyType(javaField), CollectionsKt.emptyList(), getDispatchReceiverParameter(), null, CollectionsKt.emptyList());
        DeclarationDescriptor ownerDescriptor = getOwnerDescriptor();
        ClassDescriptor classDescriptor = ownerDescriptor instanceof ClassDescriptor ? (ClassDescriptor) ownerDescriptor : null;
        if (classDescriptor != null) {
            ref$ObjectRef.element = this.c.getComponents().getSyntheticPartsProvider().modifyField(classDescriptor, (PropertyDescriptorImpl) ref$ObjectRef.element, this.c);
        }
        Object obj = ref$ObjectRef.element;
        if (DescriptorUtils.shouldRecordInitializerForProperty((VariableDescriptor) obj, ((PropertyDescriptorImpl) obj).getType())) {
            ((PropertyDescriptorImpl) ref$ObjectRef.element).setCompileTimeInitializerFactory(new Function0(this, javaField, ref$ObjectRef) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$10
                private final LazyJavaScope arg$0;
                private final JavaField arg$1;
                private final Ref$ObjectRef arg$2;

                {
                    this.arg$0 = this;
                    this.arg$1 = javaField;
                    this.arg$2 = ref$ObjectRef;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return LazyJavaScope.resolveProperty$lambda$16(this.arg$0, this.arg$1, this.arg$2);
                }
            });
        }
        this.c.getComponents().getJavaResolverCache().recordField(javaField, (PropertyDescriptor) ref$ObjectRef.element);
        return (PropertyDescriptor) ref$ObjectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NullableLazyValue resolveProperty$lambda$16(final LazyJavaScope lazyJavaScope, final JavaField javaField, final Ref$ObjectRef ref$ObjectRef) {
        return lazyJavaScope.c.getStorageManager().createNullableLazyValue(new Function0(lazyJavaScope, javaField, ref$ObjectRef) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope$$Lambda$11
            private final LazyJavaScope arg$0;
            private final JavaField arg$1;
            private final Ref$ObjectRef arg$2;

            {
                this.arg$0 = lazyJavaScope;
                this.arg$1 = javaField;
                this.arg$2 = ref$ObjectRef;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return LazyJavaScope.resolveProperty$lambda$16$lambda$15(this.arg$0, this.arg$1, this.arg$2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConstantValue resolveProperty$lambda$16$lambda$15(LazyJavaScope lazyJavaScope, JavaField javaField, Ref$ObjectRef ref$ObjectRef) {
        return lazyJavaScope.c.getComponents().getJavaPropertyInitializerEvaluator().getInitializerConstant(javaField, (PropertyDescriptor) ref$ObjectRef.element);
    }

    private final PropertyDescriptorImpl createPropertyDescriptor(JavaField javaField) {
        JavaPropertyDescriptor javaPropertyDescriptorCreate = JavaPropertyDescriptor.create(getOwnerDescriptor(), LazyJavaAnnotationsKt.resolveAnnotations(this.c, javaField), Modality.FINAL, UtilsKt.toDescriptorVisibility(javaField.getVisibility()), !javaField.isFinal(), javaField.getName(), this.c.getComponents().getSourceElementFactory().source(javaField), isFinalStatic(javaField));
        Intrinsics.checkNotNullExpressionValue(javaPropertyDescriptorCreate, "create(...)");
        return javaPropertyDescriptorCreate;
    }

    private final boolean isFinalStatic(JavaField javaField) {
        return javaField.isFinal() && javaField.isStatic();
    }

    private final KotlinType getPropertyType(JavaField javaField) {
        KotlinType kotlinTypeTransformJavaType = this.c.getTypeResolver().transformJavaType(javaField.getType(), JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, false, null, 7, null));
        if ((!KotlinBuiltIns.isPrimitiveType(kotlinTypeTransformJavaType) && !KotlinBuiltIns.isString(kotlinTypeTransformJavaType)) || !isFinalStatic(javaField) || !javaField.getHasConstantNotNullInitializer()) {
            return kotlinTypeTransformJavaType;
        }
        KotlinType kotlinTypeMakeNotNullable = TypeUtils.makeNotNullable(kotlinTypeTransformJavaType);
        Intrinsics.checkNotNullExpressionValue(kotlinTypeMakeNotNullable, "makeNotNullable(...)");
        return kotlinTypeMakeNotNullable;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedVariables(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        return !getVariableNames().contains(name) ? CollectionsKt.emptyList() : (Collection) this.properties.invoke(name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public Collection getContributedDescriptors(DescriptorKindFilter kindFilter, Function1 nameFilter) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        return (Collection) this.allDescriptors.invoke();
    }

    protected final List computeDescriptors(DescriptorKindFilter kindFilter, Function1 nameFilter) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        NoLookupLocation noLookupLocation = NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getCLASSIFIERS_MASK())) {
            for (Name name : computeClassNames(kindFilter, nameFilter)) {
                if (((Boolean) nameFilter.invoke(name)).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(linkedHashSet, mo2783getContributedClassifier(name, noLookupLocation));
                }
            }
        }
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getFUNCTIONS_MASK()) && !kindFilter.getExcludes().contains(DescriptorKindExclude.NonExtensions.INSTANCE)) {
            for (Name name2 : computeFunctionNames(kindFilter, nameFilter)) {
                if (((Boolean) nameFilter.invoke(name2)).booleanValue()) {
                    linkedHashSet.addAll(getContributedFunctions(name2, noLookupLocation));
                }
            }
        }
        if (kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getVARIABLES_MASK()) && !kindFilter.getExcludes().contains(DescriptorKindExclude.NonExtensions.INSTANCE)) {
            for (Name name3 : computePropertyNames(kindFilter, nameFilter)) {
                if (((Boolean) nameFilter.invoke(name3)).booleanValue()) {
                    linkedHashSet.addAll(getContributedVariables(name3, noLookupLocation));
                }
            }
        }
        return CollectionsKt.toList(linkedHashSet);
    }

    public String toString() {
        return "Lazy scope for " + getOwnerDescriptor();
    }
}
