package kotlin.reflect.jvm.internal;

import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.KDeclarationContainerImpl;
import kotlin.reflect.jvm.internal.ReflectProperties;
import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMapping;
import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMappingUtilsKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: KClassImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class KClassImpl extends KDeclarationContainerImpl implements KClass, KClassifierImpl, KTypeParameterOwnerImpl {
    private final Lazy data;
    private final Class jClass;

    /* JADX INFO: compiled from: KClassImpl.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KotlinClassHeader.Kind.values().length];
            try {
                iArr[KotlinClassHeader.Kind.FILE_FACADE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KotlinClassHeader.Kind.MULTIFILE_CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KotlinClassHeader.Kind.MULTIFILE_CLASS_PART.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[KotlinClassHeader.Kind.SYNTHETIC_CLASS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[KotlinClassHeader.Kind.UNKNOWN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[KotlinClassHeader.Kind.CLASS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class getJClass() {
        return this.jClass;
    }

    /* JADX INFO: compiled from: KClassImpl.kt */
    public final class Data extends KDeclarationContainerImpl.Data {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "annotations", "getAnnotations()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "simpleName", "getSimpleName()Ljava/lang/String;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "qualifiedName", "getQualifiedName()Ljava/lang/String;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "constructors", "getConstructors()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "nestedClasses", "getNestedClasses()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "typeParameters", "getTypeParameters()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "supertypes", "getSupertypes()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "sealedSubclasses", "getSealedSubclasses()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "declaredNonStaticMembers", "getDeclaredNonStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "declaredStaticMembers", "getDeclaredStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "inheritedNonStaticMembers", "getInheritedNonStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "inheritedStaticMembers", "getInheritedStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "allNonStaticMembers", "getAllNonStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "allStaticMembers", "getAllStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "declaredMembers", "getDeclaredMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "allMembers", "getAllMembers()Ljava/util/Collection;"))};
        private final ReflectProperties.LazySoftVal allMembers$delegate;
        private final ReflectProperties.LazySoftVal allNonStaticMembers$delegate;
        private final ReflectProperties.LazySoftVal allStaticMembers$delegate;
        private final ReflectProperties.LazySoftVal annotations$delegate;
        private final ReflectProperties.LazySoftVal constructors$delegate;
        private final ReflectProperties.LazySoftVal declaredMembers$delegate;
        private final ReflectProperties.LazySoftVal declaredNonStaticMembers$delegate;
        private final ReflectProperties.LazySoftVal declaredStaticMembers$delegate;
        private final ReflectProperties.LazySoftVal descriptor$delegate;
        private final ReflectProperties.LazySoftVal inheritedNonStaticMembers$delegate;
        private final ReflectProperties.LazySoftVal inheritedStaticMembers$delegate;
        private final ReflectProperties.LazySoftVal nestedClasses$delegate;
        private final Lazy objectInstance$delegate;
        private final ReflectProperties.LazySoftVal qualifiedName$delegate;
        private final ReflectProperties.LazySoftVal sealedSubclasses$delegate;
        private final ReflectProperties.LazySoftVal simpleName$delegate;
        private final ReflectProperties.LazySoftVal supertypes$delegate;
        private final ReflectProperties.LazySoftVal typeParameters$delegate;

        public Data() {
            super();
            this.descriptor$delegate = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$0
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.descriptor_delegate$lambda$0(this.arg$0);
                }
            });
            this.annotations$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$1
                private final KClassImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.annotations_delegate$lambda$1(this.arg$0);
                }
            });
            this.simpleName$delegate = ReflectProperties.lazySoft(new Function0(KClassImpl.this, this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$2
                private final KClassImpl arg$0;
                private final KClassImpl.Data arg$1;

                {
                    this.arg$0 = kClassImpl;
                    this.arg$1 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.simpleName_delegate$lambda$2(this.arg$0, this.arg$1);
                }
            });
            this.qualifiedName$delegate = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$3
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.qualifiedName_delegate$lambda$3(this.arg$0);
                }
            });
            this.constructors$delegate = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$4
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.constructors_delegate$lambda$7(this.arg$0);
                }
            });
            this.nestedClasses$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$5
                private final KClassImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.nestedClasses_delegate$lambda$10(this.arg$0);
                }
            });
            this.objectInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this, KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$6
                private final KClassImpl.Data arg$0;
                private final KClassImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.objectInstance_delegate$lambda$11(this.arg$0, this.arg$1);
                }
            });
            this.typeParameters$delegate = ReflectProperties.lazySoft(new Function0(this, KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$7
                private final KClassImpl.Data arg$0;
                private final KClassImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.typeParameters_delegate$lambda$13(this.arg$0, this.arg$1);
                }
            });
            this.supertypes$delegate = ReflectProperties.lazySoft(new Function0(this, KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$8
                private final KClassImpl.Data arg$0;
                private final KClassImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.supertypes_delegate$lambda$18(this.arg$0, this.arg$1);
                }
            });
            this.sealedSubclasses$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$9
                private final KClassImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.sealedSubclasses_delegate$lambda$21(this.arg$0);
                }
            });
            this.declaredNonStaticMembers$delegate = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$10
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.declaredNonStaticMembers_delegate$lambda$22(this.arg$0);
                }
            });
            this.declaredStaticMembers$delegate = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$11
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.declaredStaticMembers_delegate$lambda$23(this.arg$0);
                }
            });
            this.inheritedNonStaticMembers$delegate = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$12
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.inheritedNonStaticMembers_delegate$lambda$24(this.arg$0);
                }
            });
            this.inheritedStaticMembers$delegate = ReflectProperties.lazySoft(new Function0(KClassImpl.this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$13
                private final KClassImpl arg$0;

                {
                    this.arg$0 = kClassImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.inheritedStaticMembers_delegate$lambda$25(this.arg$0);
                }
            });
            this.allNonStaticMembers$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$14
                private final KClassImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.allNonStaticMembers_delegate$lambda$26(this.arg$0);
                }
            });
            this.allStaticMembers$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$15
                private final KClassImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.allStaticMembers_delegate$lambda$27(this.arg$0);
                }
            });
            this.declaredMembers$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$16
                private final KClassImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.declaredMembers_delegate$lambda$28(this.arg$0);
                }
            });
            this.allMembers$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$17
                private final KClassImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KClassImpl.Data.allMembers_delegate$lambda$29(this.arg$0);
                }
            });
        }

        public final ClassDescriptor getDescriptor() {
            Object value = this.descriptor$delegate.getValue(this, $$delegatedProperties[0]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (ClassDescriptor) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ClassDescriptor descriptor_delegate$lambda$0(KClassImpl kClassImpl) {
            ClassDescriptor classDescriptorFindClassAcrossModuleDependencies;
            ClassId classId = kClassImpl.getClassId();
            RuntimeModuleData moduleData = ((Data) kClassImpl.getData().getValue()).getModuleData();
            ModuleDescriptor module = moduleData.getModule();
            if (classId.isLocal() && kClassImpl.getJClass().isAnnotationPresent(Metadata.class)) {
                classDescriptorFindClassAcrossModuleDependencies = moduleData.getDeserialization().deserializeClass(classId);
            } else {
                classDescriptorFindClassAcrossModuleDependencies = FindClassInModuleKt.findClassAcrossModuleDependencies(module, classId);
            }
            return classDescriptorFindClassAcrossModuleDependencies == null ? kClassImpl.createSyntheticClassOrFail(classId, moduleData) : classDescriptorFindClassAcrossModuleDependencies;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List annotations_delegate$lambda$1(Data data) {
            return UtilKt.computeAnnotations(data.getDescriptor());
        }

        public final String getSimpleName() {
            return (String) this.simpleName$delegate.getValue(this, $$delegatedProperties[2]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String simpleName_delegate$lambda$2(KClassImpl kClassImpl, Data data) {
            if (kClassImpl.getJClass().isAnonymousClass()) {
                return null;
            }
            ClassId classId = kClassImpl.getClassId();
            if (classId.isLocal()) {
                return data.calculateLocalClassName(kClassImpl.getJClass());
            }
            String strAsString = classId.getShortClassName().asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
            return strAsString;
        }

        public final String getQualifiedName() {
            return (String) this.qualifiedName$delegate.getValue(this, $$delegatedProperties[3]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String qualifiedName_delegate$lambda$3(KClassImpl kClassImpl) {
            if (kClassImpl.getJClass().isAnonymousClass()) {
                return null;
            }
            ClassId classId = kClassImpl.getClassId();
            if (classId.isLocal()) {
                return null;
            }
            return classId.asSingleFqName().asString();
        }

        private final String calculateLocalClassName(Class cls) {
            String simpleName = cls.getSimpleName();
            Method enclosingMethod = cls.getEnclosingMethod();
            if (enclosingMethod != null) {
                Intrinsics.checkNotNull(simpleName);
                return StringsKt.substringAfter$default(simpleName, enclosingMethod.getName() + CoreConstants.DOLLAR, (String) null, 2, (Object) null);
            }
            Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
            if (enclosingConstructor != null) {
                Intrinsics.checkNotNull(simpleName);
                return StringsKt.substringAfter$default(simpleName, enclosingConstructor.getName() + CoreConstants.DOLLAR, (String) null, 2, (Object) null);
            }
            Intrinsics.checkNotNull(simpleName);
            return StringsKt.substringAfter$default(simpleName, CoreConstants.DOLLAR, (String) null, 2, (Object) null);
        }

        public final Collection getConstructors() {
            Object value = this.constructors$delegate.getValue(this, $$delegatedProperties[4]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List constructors_delegate$lambda$7(KClassImpl kClassImpl) {
            Collection constructorDescriptors = kClassImpl.getConstructorDescriptors();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(constructorDescriptors, 10));
            Iterator it = constructorDescriptors.iterator();
            while (it.hasNext()) {
                arrayList.add(new KFunctionImpl(kClassImpl, (ConstructorDescriptor) it.next()));
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List nestedClasses_delegate$lambda$10(Data data) {
            Collection contributedDescriptors$default = ResolutionScope.DefaultImpls.getContributedDescriptors$default(data.getDescriptor().getUnsubstitutedInnerClassesScope(), null, null, 3, null);
            ArrayList<DeclarationDescriptor> arrayList = new ArrayList();
            for (Object obj : contributedDescriptors$default) {
                if (!DescriptorUtils.isEnumEntry((DeclarationDescriptor) obj)) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (DeclarationDescriptor declarationDescriptor : arrayList) {
                ClassDescriptor classDescriptor = declarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) declarationDescriptor : null;
                Class javaClass = classDescriptor != null ? UtilKt.toJavaClass(classDescriptor) : null;
                KClassImpl kClassImpl = javaClass != null ? new KClassImpl(javaClass) : null;
                if (kClassImpl != null) {
                    arrayList2.add(kClassImpl);
                }
            }
            return arrayList2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Object objectInstance_delegate$lambda$11(Data data, KClassImpl kClassImpl) throws IllegalAccessException, NoSuchFieldException {
            Field declaredField;
            ClassDescriptor descriptor = data.getDescriptor();
            if (descriptor.getKind() != ClassKind.OBJECT) {
                return null;
            }
            if (descriptor.isCompanionObject() && !CompanionObjectMappingUtilsKt.isMappedIntrinsicCompanionObject(CompanionObjectMapping.INSTANCE, descriptor)) {
                declaredField = kClassImpl.getJClass().getEnclosingClass().getDeclaredField(descriptor.getName().asString());
            } else {
                declaredField = kClassImpl.getJClass().getDeclaredField("INSTANCE");
            }
            Object obj = declaredField.get(null);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of kotlin.reflect.jvm.internal.KClassImpl.Data.objectInstance_delegate$lambda$11");
            return obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List typeParameters_delegate$lambda$13(Data data, KClassImpl kClassImpl) {
            List<TypeParameterDescriptor> declaredTypeParameters = data.getDescriptor().getDeclaredTypeParameters();
            Intrinsics.checkNotNullExpressionValue(declaredTypeParameters, "getDeclaredTypeParameters(...)");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(declaredTypeParameters, 10));
            for (TypeParameterDescriptor typeParameterDescriptor : declaredTypeParameters) {
                Intrinsics.checkNotNull(typeParameterDescriptor);
                arrayList.add(new KTypeParameterImpl(kClassImpl, typeParameterDescriptor));
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List supertypes_delegate$lambda$18(final Data data, final KClassImpl kClassImpl) {
            Collection<KotlinType> collectionMo2782getSupertypes = data.getDescriptor().getTypeConstructor().mo2782getSupertypes();
            Intrinsics.checkNotNullExpressionValue(collectionMo2782getSupertypes, "getSupertypes(...)");
            ArrayList arrayList = new ArrayList(collectionMo2782getSupertypes.size());
            for (final KotlinType kotlinType : collectionMo2782getSupertypes) {
                Intrinsics.checkNotNull(kotlinType);
                arrayList.add(new KTypeImpl(kotlinType, new Function0(kotlinType, data, kClassImpl) { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$18
                    private final KotlinType arg$0;
                    private final KClassImpl.Data arg$1;
                    private final KClassImpl arg$2;

                    {
                        this.arg$0 = kotlinType;
                        this.arg$1 = data;
                        this.arg$2 = kClassImpl;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return KClassImpl.Data.supertypes_delegate$lambda$18$lambda$15$lambda$14(this.arg$0, this.arg$1, this.arg$2);
                    }
                }));
            }
            if (!KotlinBuiltIns.isSpecialClassWithNoSupertypes(data.getDescriptor())) {
                if (arrayList.isEmpty()) {
                    SimpleType anyType = DescriptorUtilsKt.getBuiltIns(data.getDescriptor()).getAnyType();
                    Intrinsics.checkNotNullExpressionValue(anyType, "getAnyType(...)");
                    arrayList.add(new KTypeImpl(anyType, new Function0() { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$19
                        @Override // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return KClassImpl.Data.supertypes_delegate$lambda$18$lambda$17();
                        }
                    }));
                } else {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ClassKind kind = DescriptorUtils.getClassDescriptorForType(((KTypeImpl) it.next()).getType()).getKind();
                        Intrinsics.checkNotNullExpressionValue(kind, "getKind(...)");
                        if (kind != ClassKind.INTERFACE && kind != ClassKind.ANNOTATION_CLASS) {
                            break;
                        }
                    }
                    SimpleType anyType2 = DescriptorUtilsKt.getBuiltIns(data.getDescriptor()).getAnyType();
                    Intrinsics.checkNotNullExpressionValue(anyType2, "getAnyType(...)");
                    arrayList.add(new KTypeImpl(anyType2, new Function0() { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$$Lambda$19
                        @Override // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return KClassImpl.Data.supertypes_delegate$lambda$18$lambda$17();
                        }
                    }));
                }
            }
            return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Type supertypes_delegate$lambda$18$lambda$15$lambda$14(KotlinType kotlinType, Data data, KClassImpl kClassImpl) {
            ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = kotlinType.getConstructor().mo2781getDeclarationDescriptor();
            if (!(classifierDescriptorMo2781getDeclarationDescriptor instanceof ClassDescriptor)) {
                throw new KotlinReflectionInternalError("Supertype not a class: " + classifierDescriptorMo2781getDeclarationDescriptor);
            }
            Class javaClass = UtilKt.toJavaClass((ClassDescriptor) classifierDescriptorMo2781getDeclarationDescriptor);
            if (javaClass == null) {
                throw new KotlinReflectionInternalError("Unsupported superclass of " + data + ": " + classifierDescriptorMo2781getDeclarationDescriptor);
            }
            if (Intrinsics.areEqual(kClassImpl.getJClass().getSuperclass(), javaClass)) {
                Type genericSuperclass = kClassImpl.getJClass().getGenericSuperclass();
                Intrinsics.checkNotNull(genericSuperclass);
                return genericSuperclass;
            }
            Class<?>[] interfaces = kClassImpl.getJClass().getInterfaces();
            Intrinsics.checkNotNullExpressionValue(interfaces, "getInterfaces(...)");
            int iIndexOf = ArraysKt.indexOf(interfaces, javaClass);
            if (iIndexOf >= 0) {
                Type type = kClassImpl.getJClass().getGenericInterfaces()[iIndexOf];
                Intrinsics.checkNotNull(type);
                return type;
            }
            throw new KotlinReflectionInternalError("No superclass of " + data + " in Java reflection for " + classifierDescriptorMo2781getDeclarationDescriptor);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Type supertypes_delegate$lambda$18$lambda$17() {
            return Object.class;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List sealedSubclasses_delegate$lambda$21(Data data) {
            Collection<ClassDescriptor> sealedSubclasses = data.getDescriptor().getSealedSubclasses();
            Intrinsics.checkNotNullExpressionValue(sealedSubclasses, "getSealedSubclasses(...)");
            ArrayList arrayList = new ArrayList();
            for (ClassDescriptor classDescriptor : sealedSubclasses) {
                Intrinsics.checkNotNull(classDescriptor, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                Class javaClass = UtilKt.toJavaClass(classDescriptor);
                KClassImpl kClassImpl = javaClass != null ? new KClassImpl(javaClass) : null;
                if (kClassImpl != null) {
                    arrayList.add(kClassImpl);
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection declaredNonStaticMembers_delegate$lambda$22(KClassImpl kClassImpl) {
            return kClassImpl.getMembers(kClassImpl.getMemberScope$kotlin_reflection(), KDeclarationContainerImpl.MemberBelonginess.DECLARED);
        }

        public final Collection getDeclaredNonStaticMembers() {
            Object value = this.declaredNonStaticMembers$delegate.getValue(this, $$delegatedProperties[9]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection declaredStaticMembers_delegate$lambda$23(KClassImpl kClassImpl) {
            return kClassImpl.getMembers(kClassImpl.getStaticScope$kotlin_reflection(), KDeclarationContainerImpl.MemberBelonginess.DECLARED);
        }

        private final Collection getDeclaredStaticMembers() {
            Object value = this.declaredStaticMembers$delegate.getValue(this, $$delegatedProperties[10]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        private final Collection getInheritedNonStaticMembers() {
            Object value = this.inheritedNonStaticMembers$delegate.getValue(this, $$delegatedProperties[11]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection inheritedNonStaticMembers_delegate$lambda$24(KClassImpl kClassImpl) {
            return kClassImpl.getMembers(kClassImpl.getMemberScope$kotlin_reflection(), KDeclarationContainerImpl.MemberBelonginess.INHERITED);
        }

        private final Collection getInheritedStaticMembers() {
            Object value = this.inheritedStaticMembers$delegate.getValue(this, $$delegatedProperties[12]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection inheritedStaticMembers_delegate$lambda$25(KClassImpl kClassImpl) {
            return kClassImpl.getMembers(kClassImpl.getStaticScope$kotlin_reflection(), KDeclarationContainerImpl.MemberBelonginess.INHERITED);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List allNonStaticMembers_delegate$lambda$26(Data data) {
            return CollectionsKt.plus(data.getDeclaredNonStaticMembers(), (Iterable) data.getInheritedNonStaticMembers());
        }

        public final Collection getAllNonStaticMembers() {
            Object value = this.allNonStaticMembers$delegate.getValue(this, $$delegatedProperties[13]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List allStaticMembers_delegate$lambda$27(Data data) {
            return CollectionsKt.plus(data.getDeclaredStaticMembers(), (Iterable) data.getInheritedStaticMembers());
        }

        public final Collection getAllStaticMembers() {
            Object value = this.allStaticMembers$delegate.getValue(this, $$delegatedProperties[14]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (Collection) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List declaredMembers_delegate$lambda$28(Data data) {
            return CollectionsKt.plus(data.getDeclaredNonStaticMembers(), (Iterable) data.getDeclaredStaticMembers());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List allMembers_delegate$lambda$29(Data data) {
            return CollectionsKt.plus(data.getAllNonStaticMembers(), (Iterable) data.getAllStaticMembers());
        }
    }

    public KClassImpl(Class jClass) {
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        this.jClass = jClass;
        this.data = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KClassImpl$$Lambda$0
            private final KClassImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KClassImpl.data$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Data data$lambda$0(KClassImpl kClassImpl) {
        return kClassImpl.new Data();
    }

    public final Lazy getData() {
        return this.data;
    }

    @Override // kotlin.reflect.jvm.internal.KClassifierImpl
    public ClassDescriptor getDescriptor() {
        return ((Data) this.data.getValue()).getDescriptor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassId getClassId() {
        return RuntimeTypeMapper.INSTANCE.mapJvmClassToKotlinClassId(getJClass());
    }

    public final MemberScope getMemberScope$kotlin_reflection() {
        return getDescriptor().getDefaultType().getMemberScope();
    }

    public final MemberScope getStaticScope$kotlin_reflection() {
        MemberScope staticScope = getDescriptor().getStaticScope();
        Intrinsics.checkNotNullExpressionValue(staticScope, "getStaticScope(...)");
        return staticScope;
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getConstructorDescriptors() {
        ClassDescriptor descriptor = getDescriptor();
        if (descriptor.getKind() == ClassKind.INTERFACE || descriptor.getKind() == ClassKind.OBJECT) {
            return CollectionsKt.emptyList();
        }
        Collection constructors = descriptor.getConstructors();
        Intrinsics.checkNotNullExpressionValue(constructors, "getConstructors(...)");
        return constructors;
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getProperties(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        MemberScope memberScope$kotlin_reflection = getMemberScope$kotlin_reflection();
        NoLookupLocation noLookupLocation = NoLookupLocation.FROM_REFLECTION;
        return CollectionsKt.plus(memberScope$kotlin_reflection.getContributedVariables(name, noLookupLocation), (Iterable) getStaticScope$kotlin_reflection().getContributedVariables(name, noLookupLocation));
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getFunctions(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        MemberScope memberScope$kotlin_reflection = getMemberScope$kotlin_reflection();
        NoLookupLocation noLookupLocation = NoLookupLocation.FROM_REFLECTION;
        return CollectionsKt.plus(memberScope$kotlin_reflection.getContributedFunctions(name, noLookupLocation), (Iterable) getStaticScope$kotlin_reflection().getContributedFunctions(name, noLookupLocation));
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public PropertyDescriptor getLocalProperty(int i) {
        Class<?> declaringClass;
        if (Intrinsics.areEqual(getJClass().getSimpleName(), "DefaultImpls") && (declaringClass = getJClass().getDeclaringClass()) != null && declaringClass.isInterface()) {
            KClass kotlinClass = JvmClassMappingKt.getKotlinClass(declaringClass);
            Intrinsics.checkNotNull(kotlinClass, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>");
            return ((KClassImpl) kotlinClass).getLocalProperty(i);
        }
        ClassDescriptor descriptor = getDescriptor();
        DeserializedClassDescriptor deserializedClassDescriptor = descriptor instanceof DeserializedClassDescriptor ? (DeserializedClassDescriptor) descriptor : null;
        if (deserializedClassDescriptor == null) {
            return null;
        }
        ProtoBuf$Class classProto = deserializedClassDescriptor.getClassProto();
        GeneratedMessageLite.GeneratedExtension classLocalVariable = JvmProtoBuf.classLocalVariable;
        Intrinsics.checkNotNullExpressionValue(classLocalVariable, "classLocalVariable");
        ProtoBuf$Property protoBuf$Property = (ProtoBuf$Property) ProtoBufUtilKt.getExtensionOrNull(classProto, classLocalVariable, i);
        if (protoBuf$Property != null) {
            return (PropertyDescriptor) UtilKt.deserializeToDescriptor(getJClass(), protoBuf$Property, deserializedClassDescriptor.getC().getNameResolver(), deserializedClassDescriptor.getC().getTypeTable(), deserializedClassDescriptor.getMetadataVersion(), KClassImpl$getLocalProperty$2$1$1.INSTANCE);
        }
        return null;
    }

    @Override // kotlin.reflect.KClass
    public String getSimpleName() {
        return ((Data) this.data.getValue()).getSimpleName();
    }

    @Override // kotlin.reflect.KClass
    public String getQualifiedName() {
        return ((Data) this.data.getValue()).getQualifiedName();
    }

    public Collection getConstructors() {
        return ((Data) this.data.getValue()).getConstructors();
    }

    @Override // kotlin.reflect.KClass
    public boolean isInstance(Object obj) {
        Integer functionClassArity = ReflectClassUtilKt.getFunctionClassArity(getJClass());
        if (functionClassArity != null) {
            return TypeIntrinsics.isFunctionOfArity(obj, functionClassArity.intValue());
        }
        Class wrapperByPrimitive = ReflectClassUtilKt.getWrapperByPrimitive(getJClass());
        if (wrapperByPrimitive == null) {
            wrapperByPrimitive = getJClass();
        }
        return wrapperByPrimitive.isInstance(obj);
    }

    @Override // kotlin.reflect.KClass
    public boolean isValue() {
        return getDescriptor().isValue();
    }

    public boolean equals(Object obj) {
        return (obj instanceof KClassImpl) && Intrinsics.areEqual(JvmClassMappingKt.getJavaObjectType(this), JvmClassMappingKt.getJavaObjectType((KClass) obj));
    }

    public int hashCode() {
        return JvmClassMappingKt.getJavaObjectType(this).hashCode();
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("class ");
        ClassId classId = getClassId();
        FqName packageFqName = classId.getPackageFqName();
        if (packageFqName.isRoot()) {
            str = "";
        } else {
            str = packageFqName.asString() + CoreConstants.DOT;
        }
        String strAsString = classId.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        sb.append(str + StringsKt.replace$default(strAsString, CoreConstants.DOT, CoreConstants.DOLLAR, false, 4, (Object) null));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassDescriptor createSyntheticClassOrFail(ClassId classId, RuntimeModuleData runtimeModuleData) throws InvocationTargetException {
        KotlinClassHeader classHeader;
        if (getJClass().isSynthetic()) {
            return createSyntheticClass(classId, runtimeModuleData);
        }
        ReflectKotlinClass reflectKotlinClassCreate = ReflectKotlinClass.Factory.create(getJClass());
        KotlinClassHeader.Kind kind = (reflectKotlinClassCreate == null || (classHeader = reflectKotlinClassCreate.getClassHeader()) == null) ? null : classHeader.getKind();
        switch (kind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
            case -1:
            case 6:
                throw new KotlinReflectionInternalError("Unresolved class: " + getJClass() + " (kind = " + kind + CoreConstants.RIGHT_PARENTHESIS_CHAR);
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
            case 2:
            case 3:
            case 4:
                return createSyntheticClass(classId, runtimeModuleData);
            case 5:
                throw new KotlinReflectionInternalError("Unknown class: " + getJClass() + " (kind = " + kind + CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }
    }

    private final ClassDescriptor createSyntheticClass(ClassId classId, RuntimeModuleData runtimeModuleData) {
        final ClassDescriptorImpl classDescriptorImpl = new ClassDescriptorImpl(new EmptyPackageFragmentDescriptor(runtimeModuleData.getModule(), classId.getPackageFqName()), classId.getShortClassName(), Modality.FINAL, ClassKind.CLASS, CollectionsKt.listOf(runtimeModuleData.getModule().getBuiltIns().getAny().getDefaultType()), SourceElement.NO_SOURCE, false, runtimeModuleData.getDeserialization().getStorageManager());
        final StorageManager storageManager = runtimeModuleData.getDeserialization().getStorageManager();
        classDescriptorImpl.initialize(new GivenFunctionsMemberScope(classDescriptorImpl, storageManager) { // from class: kotlin.reflect.jvm.internal.KClassImpl$createSyntheticClass$1$1
            @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope
            protected List computeDeclaredFunctions() {
                return CollectionsKt.emptyList();
            }
        }, SetsKt.emptySet(), null);
        return classDescriptorImpl;
    }
}
