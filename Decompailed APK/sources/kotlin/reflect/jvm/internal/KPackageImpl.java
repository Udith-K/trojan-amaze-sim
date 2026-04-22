package kotlin.reflect.jvm.internal;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.Action;
import java.util.Collection;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.KDeclarationContainerImpl;
import kotlin.reflect.jvm.internal.KPackageImpl;
import kotlin.reflect.jvm.internal.ReflectProperties;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: KPackageImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class KPackageImpl extends KDeclarationContainerImpl {
    private final Lazy data;
    private final Class jClass;

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class getJClass() {
        return this.jClass;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: KPackageImpl.kt */
    final class Data extends KDeclarationContainerImpl.Data {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "kotlinClass", "getKotlinClass()Lorg/jetbrains/kotlin/descriptors/runtime/components/ReflectKotlinClass;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), Action.SCOPE_ATTRIBUTE, "getScope()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "members", "getMembers()Ljava/util/Collection;"))};
        private final ReflectProperties.LazySoftVal kotlinClass$delegate;
        private final ReflectProperties.LazySoftVal members$delegate;
        private final Lazy metadata$delegate;
        private final Lazy multifileFacade$delegate;
        private final ReflectProperties.LazySoftVal scope$delegate;

        public Data() {
            super();
            this.kotlinClass$delegate = ReflectProperties.lazySoft(new Function0(KPackageImpl.this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$Data$$Lambda$0
                private final KPackageImpl arg$0;

                {
                    this.arg$0 = kPackageImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KPackageImpl.Data.kotlinClass_delegate$lambda$0(this.arg$0);
                }
            });
            this.scope$delegate = ReflectProperties.lazySoft(new Function0(this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$Data$$Lambda$1
                private final KPackageImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KPackageImpl.Data.scope_delegate$lambda$1(this.arg$0);
                }
            });
            LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
            this.multifileFacade$delegate = LazyKt.lazy(lazyThreadSafetyMode, new Function0(this, KPackageImpl.this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$Data$$Lambda$2
                private final KPackageImpl.Data arg$0;
                private final KPackageImpl arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = kPackageImpl;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KPackageImpl.Data.multifileFacade_delegate$lambda$2(this.arg$0, this.arg$1);
                }
            });
            this.metadata$delegate = LazyKt.lazy(lazyThreadSafetyMode, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$Data$$Lambda$3
                private final KPackageImpl.Data arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KPackageImpl.Data.metadata_delegate$lambda$4(this.arg$0);
                }
            });
            this.members$delegate = ReflectProperties.lazySoft(new Function0(KPackageImpl.this, this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$Data$$Lambda$4
                private final KPackageImpl arg$0;
                private final KPackageImpl.Data arg$1;

                {
                    this.arg$0 = kPackageImpl;
                    this.arg$1 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return KPackageImpl.Data.members_delegate$lambda$5(this.arg$0, this.arg$1);
                }
            });
        }

        private final ReflectKotlinClass getKotlinClass() {
            return (ReflectKotlinClass) this.kotlinClass$delegate.getValue(this, $$delegatedProperties[0]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ReflectKotlinClass kotlinClass_delegate$lambda$0(KPackageImpl kPackageImpl) {
            return ReflectKotlinClass.Factory.create(kPackageImpl.getJClass());
        }

        public final MemberScope getScope() {
            Object value = this.scope$delegate.getValue(this, $$delegatedProperties[1]);
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            return (MemberScope) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final MemberScope scope_delegate$lambda$1(Data data) {
            ReflectKotlinClass kotlinClass = data.getKotlinClass();
            if (kotlinClass != null) {
                return data.getModuleData().getPackagePartScopeCache().getPackagePartScope(kotlinClass);
            }
            return MemberScope.Empty.INSTANCE;
        }

        public final Class getMultifileFacade() {
            return (Class) this.multifileFacade$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Class multifileFacade_delegate$lambda$2(Data data, KPackageImpl kPackageImpl) {
            KotlinClassHeader classHeader;
            ReflectKotlinClass kotlinClass = data.getKotlinClass();
            String multifileClassName = (kotlinClass == null || (classHeader = kotlinClass.getClassHeader()) == null) ? null : classHeader.getMultifileClassName();
            if (multifileClassName == null || multifileClassName.length() <= 0) {
                return null;
            }
            return kPackageImpl.getJClass().getClassLoader().loadClass(StringsKt.replace$default(multifileClassName, '/', CoreConstants.DOT, false, 4, (Object) null));
        }

        public final Triple getMetadata() {
            return (Triple) this.metadata$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Triple metadata_delegate$lambda$4(Data data) {
            KotlinClassHeader classHeader;
            ReflectKotlinClass kotlinClass = data.getKotlinClass();
            if (kotlinClass == null || (classHeader = kotlinClass.getClassHeader()) == null) {
                return null;
            }
            String[] data2 = classHeader.getData();
            String[] strings = classHeader.getStrings();
            if (data2 == null || strings == null) {
                return null;
            }
            Pair packageDataFrom = JvmProtoBufUtil.readPackageDataFrom(data2, strings);
            return new Triple((JvmNameResolver) packageDataFrom.component1(), (ProtoBuf$Package) packageDataFrom.component2(), classHeader.getMetadataVersion());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection members_delegate$lambda$5(KPackageImpl kPackageImpl, Data data) {
            return kPackageImpl.getMembers(data.getScope(), KDeclarationContainerImpl.MemberBelonginess.DECLARED);
        }
    }

    public KPackageImpl(Class jClass) {
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        this.jClass = jClass;
        this.data = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0(this) { // from class: kotlin.reflect.jvm.internal.KPackageImpl$$Lambda$0
            private final KPackageImpl arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return KPackageImpl.data$lambda$0(this.arg$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Data data$lambda$0(KPackageImpl kPackageImpl) {
        return kPackageImpl.new Data();
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    protected Class getMethodOwner() {
        Class multifileFacade = ((Data) this.data.getValue()).getMultifileFacade();
        return multifileFacade == null ? getJClass() : multifileFacade;
    }

    private final MemberScope getScope() {
        return ((Data) this.data.getValue()).getScope();
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getConstructorDescriptors() {
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getProperties(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getScope().getContributedVariables(name, NoLookupLocation.FROM_REFLECTION);
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getFunctions(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getScope().getContributedFunctions(name, NoLookupLocation.FROM_REFLECTION);
    }

    @Override // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public PropertyDescriptor getLocalProperty(int i) {
        Triple metadata = ((Data) this.data.getValue()).getMetadata();
        if (metadata == null) {
            return null;
        }
        JvmNameResolver jvmNameResolver = (JvmNameResolver) metadata.component1();
        ProtoBuf$Package protoBuf$Package = (ProtoBuf$Package) metadata.component2();
        JvmMetadataVersion jvmMetadataVersion = (JvmMetadataVersion) metadata.component3();
        GeneratedMessageLite.GeneratedExtension packageLocalVariable = JvmProtoBuf.packageLocalVariable;
        Intrinsics.checkNotNullExpressionValue(packageLocalVariable, "packageLocalVariable");
        ProtoBuf$Property protoBuf$Property = (ProtoBuf$Property) ProtoBufUtilKt.getExtensionOrNull(protoBuf$Package, packageLocalVariable, i);
        if (protoBuf$Property == null) {
            return null;
        }
        Class jClass = getJClass();
        ProtoBuf$TypeTable typeTable = protoBuf$Package.getTypeTable();
        Intrinsics.checkNotNullExpressionValue(typeTable, "getTypeTable(...)");
        return (PropertyDescriptor) UtilKt.deserializeToDescriptor(jClass, protoBuf$Property, jvmNameResolver, new TypeTable(typeTable), jvmMetadataVersion, KPackageImpl$getLocalProperty$1$1$1.INSTANCE);
    }

    public boolean equals(Object obj) {
        return (obj instanceof KPackageImpl) && Intrinsics.areEqual(getJClass(), ((KPackageImpl) obj).getJClass());
    }

    public int hashCode() {
        return getJClass().hashCode();
    }

    public String toString() {
        return "file class " + ReflectClassUtilKt.getClassId(getJClass()).asSingleFqName();
    }
}
