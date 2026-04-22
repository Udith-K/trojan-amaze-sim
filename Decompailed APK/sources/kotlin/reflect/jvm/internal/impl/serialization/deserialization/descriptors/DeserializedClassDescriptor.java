package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DeserializedDeclarationsFromSupertypeConflictDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.InlineClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ScopesHolderForClass;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ReceiverParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Modality;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Visibility;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.CliSealedClassInheritorsProvider;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.StaticScopeForKotlinEnum;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ContextClassReceiver;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlagsUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ValueClassUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* JADX INFO: compiled from: DeserializedClassDescriptor.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class DeserializedClassDescriptor extends AbstractClassDescriptor implements DeclarationDescriptor {
    private final Annotations annotations;
    private final DeserializationContext c;
    private final ClassId classId;
    private final ProtoBuf$Class classProto;
    private final NullableLazyValue companionObjectDescriptor;
    private final NotNullLazyValue constructors;
    private final DeclarationDescriptor containingDeclaration;
    private final EnumEntryClassDescriptors enumEntries;
    private final boolean hasEnumEntriesMetadataFlag;
    private final ClassKind kind;
    private final ScopesHolderForClass memberScopeHolder;
    private final BinaryVersion metadataVersion;
    private final Modality modality;
    private final NullableLazyValue primaryConstructor;
    private final NotNullLazyValue sealedSubclasses;
    private final SourceElement sourceElement;
    private final MemberScopeImpl staticScope;
    private final ProtoContainer.Class thisAsProtoContainer;
    private final DeserializedClassTypeConstructor typeConstructor;
    private final NullableLazyValue valueClassRepresentation;
    private final DescriptorVisibility visibility;

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    public final ProtoBuf$Class getClassProto() {
        return this.classProto;
    }

    public final BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeserializedClassDescriptor(DeserializationContext outerContext, ProtoBuf$Class classProto, NameResolver nameResolver, BinaryVersion metadataVersion, SourceElement sourceElement) {
        MemberScopeImpl staticScopeForKotlinEnum;
        Annotations nonEmptyDeserializedAnnotations;
        super(outerContext.getStorageManager(), NameResolverUtilKt.getClassId(nameResolver, classProto.getFqName()).getShortClassName());
        Intrinsics.checkNotNullParameter(outerContext, "outerContext");
        Intrinsics.checkNotNullParameter(classProto, "classProto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(metadataVersion, "metadataVersion");
        Intrinsics.checkNotNullParameter(sourceElement, "sourceElement");
        this.classProto = classProto;
        this.metadataVersion = metadataVersion;
        this.sourceElement = sourceElement;
        this.classId = NameResolverUtilKt.getClassId(nameResolver, classProto.getFqName());
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        this.modality = protoEnumFlags.modality((ProtoBuf$Modality) Flags.MODALITY.get(classProto.getFlags()));
        this.visibility = ProtoEnumFlagsUtilsKt.descriptorVisibility(protoEnumFlags, (ProtoBuf$Visibility) Flags.VISIBILITY.get(classProto.getFlags()));
        ClassKind classKind = protoEnumFlags.classKind((ProtoBuf$Class.Kind) Flags.CLASS_KIND.get(classProto.getFlags()));
        this.kind = classKind;
        List typeParameterList = classProto.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(typeParameterList, "getTypeParameterList(...)");
        ProtoBuf$TypeTable typeTable = classProto.getTypeTable();
        Intrinsics.checkNotNullExpressionValue(typeTable, "getTypeTable(...)");
        TypeTable typeTable2 = new TypeTable(typeTable);
        VersionRequirementTable.Companion companion = VersionRequirementTable.Companion;
        ProtoBuf$VersionRequirementTable versionRequirementTable = classProto.getVersionRequirementTable();
        Intrinsics.checkNotNullExpressionValue(versionRequirementTable, "getVersionRequirementTable(...)");
        DeserializationContext deserializationContextChildContext = outerContext.childContext(this, typeParameterList, nameResolver, typeTable2, companion.create(versionRequirementTable), metadataVersion);
        this.c = deserializationContextChildContext;
        Boolean bool = Flags.HAS_ENUM_ENTRIES.get(classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "get(...)");
        boolean zBooleanValue = bool.booleanValue();
        this.hasEnumEntriesMetadataFlag = zBooleanValue;
        ClassKind classKind2 = ClassKind.ENUM_CLASS;
        if (classKind == classKind2) {
            staticScopeForKotlinEnum = new StaticScopeForKotlinEnum(deserializationContextChildContext.getStorageManager(), this, zBooleanValue || Intrinsics.areEqual(deserializationContextChildContext.getComponents().getEnumEntriesDeserializationSupport().canSynthesizeEnumEntries(), Boolean.TRUE));
        } else {
            staticScopeForKotlinEnum = MemberScope.Empty.INSTANCE;
        }
        this.staticScope = staticScopeForKotlinEnum;
        this.typeConstructor = new DeserializedClassTypeConstructor();
        this.memberScopeHolder = ScopesHolderForClass.Companion.create(this, deserializationContextChildContext.getStorageManager(), deserializationContextChildContext.getComponents().getKotlinTypeChecker().getKotlinTypeRefiner(), new DeserializedClassDescriptor$memberScopeHolder$1(this));
        this.enumEntries = classKind == classKind2 ? new EnumEntryClassDescriptors() : null;
        DeclarationDescriptor containingDeclaration = outerContext.getContainingDeclaration();
        this.containingDeclaration = containingDeclaration;
        this.primaryConstructor = deserializationContextChildContext.getStorageManager().createNullableLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$$Lambda$0
            private final DeserializedClassDescriptor arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.arg$0.computePrimaryConstructor();
            }
        });
        this.constructors = deserializationContextChildContext.getStorageManager().createLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$$Lambda$1
            private final DeserializedClassDescriptor arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.arg$0.computeConstructors();
            }
        });
        this.companionObjectDescriptor = deserializationContextChildContext.getStorageManager().createNullableLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$$Lambda$2
            private final DeserializedClassDescriptor arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.arg$0.computeCompanionObjectDescriptor();
            }
        });
        this.sealedSubclasses = deserializationContextChildContext.getStorageManager().createLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$$Lambda$3
            private final DeserializedClassDescriptor arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.arg$0.computeSubclassesForSealedClass();
            }
        });
        this.valueClassRepresentation = deserializationContextChildContext.getStorageManager().createNullableLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$$Lambda$4
            private final DeserializedClassDescriptor arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.arg$0.computeValueClassRepresentation();
            }
        });
        NameResolver nameResolver2 = deserializationContextChildContext.getNameResolver();
        TypeTable typeTable3 = deserializationContextChildContext.getTypeTable();
        DeserializedClassDescriptor deserializedClassDescriptor = containingDeclaration instanceof DeserializedClassDescriptor ? (DeserializedClassDescriptor) containingDeclaration : null;
        this.thisAsProtoContainer = new ProtoContainer.Class(classProto, nameResolver2, typeTable3, sourceElement, deserializedClassDescriptor != null ? deserializedClassDescriptor.thisAsProtoContainer : null);
        if (!Flags.HAS_ANNOTATIONS.get(classProto.getFlags()).booleanValue()) {
            nonEmptyDeserializedAnnotations = Annotations.Companion.getEMPTY();
        } else {
            nonEmptyDeserializedAnnotations = new NonEmptyDeserializedAnnotations(deserializationContextChildContext.getStorageManager(), new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$$Lambda$5
                private final DeserializedClassDescriptor arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return DeserializedClassDescriptor.annotations$lambda$5(this.arg$0);
                }
            });
        }
        this.annotations = nonEmptyDeserializedAnnotations;
    }

    public final DeserializationContext getC() {
        return this.c;
    }

    private final DeserializedClassMemberScope getMemberScope() {
        return (DeserializedClassMemberScope) this.memberScopeHolder.getScope(this.c.getComponents().getKotlinTypeChecker().getKotlinTypeRefiner());
    }

    public final ProtoContainer.Class getThisAsProtoContainer$deserialization() {
        return this.thisAsProtoContainer;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return this.annotations;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List annotations$lambda$5(DeserializedClassDescriptor deserializedClassDescriptor) {
        return CollectionsKt.toList(deserializedClassDescriptor.c.getComponents().getAnnotationAndConstantLoader().loadClassAnnotations(deserializedClassDescriptor.thisAsProtoContainer));
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassKind getKind() {
        return this.kind;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public Modality getModality() {
        return this.modality;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility
    public DescriptorVisibility getVisibility() {
        return this.visibility;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        Boolean bool = Flags.IS_INNER.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "get(...)");
        return bool.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isData() {
        Boolean bool = Flags.IS_DATA.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "get(...)");
        return bool.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isInline() {
        return Flags.IS_VALUE_CLASS.get(this.classProto.getFlags()).booleanValue() && this.metadataVersion.isAtMost(1, 4, 1);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        Boolean bool = Flags.IS_EXPECT_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "get(...)");
        return bool.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        Boolean bool = Flags.IS_EXTERNAL_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "get(...)");
        return bool.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isFun() {
        Boolean bool = Flags.IS_FUN_INTERFACE.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "get(...)");
        return bool.booleanValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isValue() {
        return Flags.IS_VALUE_CLASS.get(this.classProto.getFlags()).booleanValue() && this.metadataVersion.isAtLeast(1, 4, 2);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    protected MemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return this.memberScopeHolder.getScope(kotlinTypeRefiner);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScopeImpl getStaticScope() {
        return this.staticScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isCompanionObject() {
        return Flags.CLASS_KIND.get(this.classProto.getFlags()) == ProtoBuf$Class.Kind.COMPANION_OBJECT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassConstructorDescriptor computePrimaryConstructor() {
        Object next;
        if (this.kind.isSingleton()) {
            ClassConstructorDescriptorImpl classConstructorDescriptorImplCreatePrimaryConstructorForObject = DescriptorFactory.createPrimaryConstructorForObject(this, SourceElement.NO_SOURCE);
            classConstructorDescriptorImplCreatePrimaryConstructorForObject.setReturnType(getDefaultType());
            return classConstructorDescriptorImplCreatePrimaryConstructorForObject;
        }
        List constructorList = this.classProto.getConstructorList();
        Intrinsics.checkNotNullExpressionValue(constructorList, "getConstructorList(...)");
        Iterator it = constructorList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (!Flags.IS_SECONDARY.get(((ProtoBuf$Constructor) next).getFlags()).booleanValue()) {
                break;
            }
        }
        ProtoBuf$Constructor protoBuf$Constructor = (ProtoBuf$Constructor) next;
        if (protoBuf$Constructor != null) {
            return this.c.getMemberDeserializer().loadConstructor(protoBuf$Constructor, true);
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    /* JADX INFO: renamed from: getUnsubstitutedPrimaryConstructor */
    public ClassConstructorDescriptor mo2747getUnsubstitutedPrimaryConstructor() {
        return (ClassConstructorDescriptor) this.primaryConstructor.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection computeConstructors() {
        return CollectionsKt.plus((Collection) CollectionsKt.plus((Collection) computeSecondaryConstructors(), (Iterable) CollectionsKt.listOfNotNull(mo2747getUnsubstitutedPrimaryConstructor())), (Iterable) this.c.getComponents().getAdditionalClassPartsProvider().getConstructors(this));
    }

    private final List computeSecondaryConstructors() {
        List constructorList = this.classProto.getConstructorList();
        Intrinsics.checkNotNullExpressionValue(constructorList, "getConstructorList(...)");
        ArrayList<ProtoBuf$Constructor> arrayList = new ArrayList();
        for (Object obj : constructorList) {
            Boolean bool = Flags.IS_SECONDARY.get(((ProtoBuf$Constructor) obj).getFlags());
            Intrinsics.checkNotNullExpressionValue(bool, "get(...)");
            if (bool.booleanValue()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (ProtoBuf$Constructor protoBuf$Constructor : arrayList) {
            MemberDeserializer memberDeserializer = this.c.getMemberDeserializer();
            Intrinsics.checkNotNull(protoBuf$Constructor);
            arrayList2.add(memberDeserializer.loadConstructor(protoBuf$Constructor, false));
        }
        return arrayList2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getConstructors() {
        return (Collection) this.constructors.invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List getContextReceivers() {
        List listContextReceiverTypes = ProtoTypeTableUtilKt.contextReceiverTypes(this.classProto, this.c.getTypeTable());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listContextReceiverTypes, 10));
        Iterator it = listContextReceiverTypes.iterator();
        while (it.hasNext()) {
            arrayList.add(new ReceiverParameterDescriptorImpl(getThisAsReceiverParameter(), new ContextClassReceiver(this, this.c.getTypeDeserializer().type((ProtoBuf$Type) it.next()), null, null), Annotations.Companion.getEMPTY()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassDescriptor computeCompanionObjectDescriptor() {
        if (!this.classProto.hasCompanionObjectName()) {
            return null;
        }
        ClassifierDescriptor classifierDescriptorMo2783getContributedClassifier = getMemberScope().mo2783getContributedClassifier(NameResolverUtilKt.getName(this.c.getNameResolver(), this.classProto.getCompanionObjectName()), NoLookupLocation.FROM_DESERIALIZATION);
        if (classifierDescriptorMo2783getContributedClassifier instanceof ClassDescriptor) {
            return (ClassDescriptor) classifierDescriptorMo2783getContributedClassifier;
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    /* JADX INFO: renamed from: getCompanionObjectDescriptor */
    public ClassDescriptor mo2746getCompanionObjectDescriptor() {
        return (ClassDescriptor) this.companionObjectDescriptor.invoke();
    }

    public final boolean hasNestedClass$deserialization(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getMemberScope().getClassNames$deserialization().contains(name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collection computeSubclassesForSealedClass() {
        if (this.modality != Modality.SEALED) {
            return CollectionsKt.emptyList();
        }
        List<Integer> sealedSubclassFqNameList = this.classProto.getSealedSubclassFqNameList();
        Intrinsics.checkNotNull(sealedSubclassFqNameList);
        if (sealedSubclassFqNameList.isEmpty()) {
            return CliSealedClassInheritorsProvider.INSTANCE.computeSealedSubclasses(this, false);
        }
        ArrayList arrayList = new ArrayList();
        for (Integer num : sealedSubclassFqNameList) {
            DeserializationComponents components = this.c.getComponents();
            NameResolver nameResolver = this.c.getNameResolver();
            Intrinsics.checkNotNull(num);
            ClassDescriptor classDescriptorDeserializeClass = components.deserializeClass(NameResolverUtilKt.getClassId(nameResolver, num.intValue()));
            if (classDescriptorDeserializeClass != null) {
                arrayList.add(classDescriptorDeserializeClass);
            }
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getSealedSubclasses() {
        return (Collection) this.sealedSubclasses.invoke();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ValueClassRepresentation getValueClassRepresentation() {
        return (ValueClassRepresentation) this.valueClassRepresentation.invoke();
    }

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$computeValueClassRepresentation$1, reason: invalid class name */
    /* JADX INFO: compiled from: DeserializedClassDescriptor.kt */
    /* synthetic */ class AnonymousClass1 extends FunctionReference implements Function1 {
        AnonymousClass1(Object obj) {
            super(1, obj);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(Intrinsics.Kotlin.class);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "simpleType";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "computeValueClassRepresentation$simpleType(Lorg/jetbrains/kotlin/serialization/deserialization/TypeDeserializer;Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;)Lorg/jetbrains/kotlin/types/SimpleType;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final SimpleType invoke(ProtoBuf$Type p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return TypeDeserializer.simpleType$default((TypeDeserializer) this.receiver, p0, false, 2, null);
        }
    }

    /* JADX INFO: renamed from: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$computeValueClassRepresentation$2, reason: invalid class name */
    /* JADX INFO: compiled from: DeserializedClassDescriptor.kt */
    /* synthetic */ class AnonymousClass2 extends FunctionReference implements Function1 {
        AnonymousClass2(Object obj) {
            super(1, obj);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(DeserializedClassDescriptor.class);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "getValueClassPropertyType";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "getValueClassPropertyType(Lorg/jetbrains/kotlin/name/Name;)Lorg/jetbrains/kotlin/types/SimpleType;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final SimpleType invoke(Name p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ((DeserializedClassDescriptor) this.receiver).getValueClassPropertyType(p0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ValueClassRepresentation computeValueClassRepresentation() {
        if (!isInline() && !isValue()) {
            return null;
        }
        ValueClassRepresentation valueClassRepresentationLoadValueClassRepresentation = ValueClassUtilKt.loadValueClassRepresentation(this.classProto, this.c.getNameResolver(), this.c.getTypeTable(), new AnonymousClass1(this.c.getTypeDeserializer()), new AnonymousClass2(this));
        if (valueClassRepresentationLoadValueClassRepresentation != null) {
            return valueClassRepresentationLoadValueClassRepresentation;
        }
        if (this.metadataVersion.isAtLeast(1, 5, 1)) {
            return null;
        }
        ClassConstructorDescriptor classConstructorDescriptorMo2747getUnsubstitutedPrimaryConstructor = mo2747getUnsubstitutedPrimaryConstructor();
        if (classConstructorDescriptorMo2747getUnsubstitutedPrimaryConstructor == null) {
            throw new IllegalStateException(("Inline class has no primary constructor: " + this).toString());
        }
        List valueParameters = classConstructorDescriptorMo2747getUnsubstitutedPrimaryConstructor.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "getValueParameters(...)");
        Name name = ((ValueParameterDescriptor) CollectionsKt.first(valueParameters)).getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        SimpleType valueClassPropertyType = getValueClassPropertyType(name);
        if (valueClassPropertyType == null) {
            throw new IllegalStateException(("Value class has no underlying property: " + this).toString());
        }
        return new InlineClassRepresentation(name, valueClassPropertyType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SimpleType getValueClassPropertyType(Name name) {
        Iterator it = getMemberScope().getContributedVariables(name, NoLookupLocation.FROM_DESERIALIZATION).iterator();
        boolean z = false;
        Object obj = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
            } else {
                Object next = it.next();
                if (((PropertyDescriptor) next).getExtensionReceiverParameter() == null) {
                    if (z) {
                        break;
                    }
                    z = true;
                    obj = next;
                }
            }
        }
        obj = null;
        PropertyDescriptor propertyDescriptor = (PropertyDescriptor) obj;
        return (SimpleType) (propertyDescriptor != null ? propertyDescriptor.getType() : null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("deserialized ");
        sb.append(isExpect() ? "expect " : "");
        sb.append("class ");
        sb.append(getName());
        return sb.toString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    public SourceElement getSource() {
        return this.sourceElement;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public List getDeclaredTypeParameters() {
        return this.c.getTypeDeserializer().getOwnTypeParameters();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DeserializedClassDescriptor.kt */
    final class DeserializedClassTypeConstructor extends AbstractClassTypeConstructor {
        private final NotNullLazyValue parameters;

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return true;
        }

        public DeserializedClassTypeConstructor() {
            super(DeserializedClassDescriptor.this.getC().getStorageManager());
            this.parameters = DeserializedClassDescriptor.this.getC().getStorageManager().createLazyValue(new Function0(DeserializedClassDescriptor.this) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassTypeConstructor$$Lambda$0
                private final DeserializedClassDescriptor arg$0;

                {
                    this.arg$0 = deserializedClassDescriptor;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return TypeParameterUtilsKt.computeConstructorTypeParameters(this.arg$0);
                }
            });
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected Collection computeSupertypes() {
            String strAsString;
            FqName fqNameAsSingleFqName;
            List listSupertypes = ProtoTypeTableUtilKt.supertypes(DeserializedClassDescriptor.this.getClassProto(), DeserializedClassDescriptor.this.getC().getTypeTable());
            DeserializedClassDescriptor deserializedClassDescriptor = DeserializedClassDescriptor.this;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSupertypes, 10));
            Iterator it = listSupertypes.iterator();
            while (it.hasNext()) {
                arrayList.add(deserializedClassDescriptor.getC().getTypeDeserializer().type((ProtoBuf$Type) it.next()));
            }
            List listPlus = CollectionsKt.plus((Collection) arrayList, (Iterable) DeserializedClassDescriptor.this.getC().getComponents().getAdditionalClassPartsProvider().getSupertypes(DeserializedClassDescriptor.this));
            ArrayList<NotFoundClasses.MockClassDescriptor> arrayList2 = new ArrayList();
            Iterator it2 = listPlus.iterator();
            while (it2.hasNext()) {
                ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = ((KotlinType) it2.next()).getConstructor().mo2781getDeclarationDescriptor();
                NotFoundClasses.MockClassDescriptor mockClassDescriptor = classifierDescriptorMo2781getDeclarationDescriptor instanceof NotFoundClasses.MockClassDescriptor ? (NotFoundClasses.MockClassDescriptor) classifierDescriptorMo2781getDeclarationDescriptor : null;
                if (mockClassDescriptor != null) {
                    arrayList2.add(mockClassDescriptor);
                }
            }
            if (!arrayList2.isEmpty()) {
                ErrorReporter errorReporter = DeserializedClassDescriptor.this.getC().getComponents().getErrorReporter();
                DeserializedClassDescriptor deserializedClassDescriptor2 = DeserializedClassDescriptor.this;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                for (NotFoundClasses.MockClassDescriptor mockClassDescriptor2 : arrayList2) {
                    ClassId classId = DescriptorUtilsKt.getClassId(mockClassDescriptor2);
                    if (classId == null || (fqNameAsSingleFqName = classId.asSingleFqName()) == null || (strAsString = fqNameAsSingleFqName.asString()) == null) {
                        strAsString = mockClassDescriptor2.getName().asString();
                        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
                    }
                    arrayList3.add(strAsString);
                }
                errorReporter.reportIncompleteHierarchy(deserializedClassDescriptor2, arrayList3);
            }
            return CollectionsKt.toList(listPlus);
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public List getParameters() {
            return (List) this.parameters.invoke();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.ClassifierBasedTypeConstructor, kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        /* JADX INFO: renamed from: getDeclarationDescriptor */
        public DeserializedClassDescriptor mo2781getDeclarationDescriptor() {
            return DeserializedClassDescriptor.this;
        }

        public String toString() {
            String string = DeserializedClassDescriptor.this.getName().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected SupertypeLoopChecker getSupertypeLoopChecker() {
            return SupertypeLoopChecker.EMPTY.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DeserializedClassDescriptor.kt */
    final class DeserializedClassMemberScope extends DeserializedMemberScope {
        private final NotNullLazyValue allDescriptors;
        private final KotlinTypeRefiner kotlinTypeRefiner;
        private final NotNullLazyValue refinedSupertypes;
        final /* synthetic */ DeserializedClassDescriptor this$0;

        /* JADX INFO: Access modifiers changed from: private */
        public static final List _init_$lambda$1$lambda$0(List list) {
            return list;
        }

        public DeserializedClassMemberScope(DeserializedClassDescriptor deserializedClassDescriptor, KotlinTypeRefiner kotlinTypeRefiner) {
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
            this.this$0 = deserializedClassDescriptor;
            DeserializationContext c = deserializedClassDescriptor.getC();
            List functionList = deserializedClassDescriptor.getClassProto().getFunctionList();
            Intrinsics.checkNotNullExpressionValue(functionList, "getFunctionList(...)");
            List propertyList = deserializedClassDescriptor.getClassProto().getPropertyList();
            Intrinsics.checkNotNullExpressionValue(propertyList, "getPropertyList(...)");
            List typeAliasList = deserializedClassDescriptor.getClassProto().getTypeAliasList();
            Intrinsics.checkNotNullExpressionValue(typeAliasList, "getTypeAliasList(...)");
            List nestedClassNameList = deserializedClassDescriptor.getClassProto().getNestedClassNameList();
            Intrinsics.checkNotNullExpressionValue(nestedClassNameList, "getNestedClassNameList(...)");
            NameResolver nameResolver = deserializedClassDescriptor.getC().getNameResolver();
            final ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(nestedClassNameList, 10));
            Iterator it = nestedClassNameList.iterator();
            while (it.hasNext()) {
                arrayList.add(NameResolverUtilKt.getName(nameResolver, ((Number) it.next()).intValue()));
            }
            super(c, functionList, propertyList, typeAliasList, new Function0(arrayList) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$$Lambda$0
                private final List arg$0;

                {
                    this.arg$0 = arrayList;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return DeserializedClassDescriptor.DeserializedClassMemberScope._init_$lambda$1$lambda$0(this.arg$0);
                }
            });
            this.kotlinTypeRefiner = kotlinTypeRefiner;
            this.allDescriptors = getC().getStorageManager().createLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$$Lambda$1
                private final DeserializedClassDescriptor.DeserializedClassMemberScope arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return DeserializedClassDescriptor.DeserializedClassMemberScope.allDescriptors$lambda$2(this.arg$0);
                }
            });
            this.refinedSupertypes = getC().getStorageManager().createLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$$Lambda$2
                private final DeserializedClassDescriptor.DeserializedClassMemberScope arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return DeserializedClassDescriptor.DeserializedClassMemberScope.refinedSupertypes$lambda$3(this.arg$0);
                }
            });
        }

        private final DeserializedClassDescriptor getClassDescriptor() {
            return this.this$0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection allDescriptors$lambda$2(DeserializedClassMemberScope deserializedClassMemberScope) {
            return deserializedClassMemberScope.computeDescriptors(DescriptorKindFilter.ALL, MemberScope.Companion.getALL_NAME_FILTER(), NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Collection refinedSupertypes$lambda$3(DeserializedClassMemberScope deserializedClassMemberScope) {
            return deserializedClassMemberScope.kotlinTypeRefiner.refineSupertypes(deserializedClassMemberScope.getClassDescriptor());
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        public Collection getContributedDescriptors(DescriptorKindFilter kindFilter, Function1 nameFilter) {
            Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
            Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
            return (Collection) this.allDescriptors.invoke();
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        public Collection getContributedFunctions(Name name, LookupLocation location) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            recordLookup(name, location);
            return super.getContributedFunctions(name, location);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        public Collection getContributedVariables(Name name, LookupLocation location) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            recordLookup(name, location);
            return super.getContributedVariables(name, location);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected boolean isDeclaredFunctionAvailable(SimpleFunctionDescriptor function) {
            Intrinsics.checkNotNullParameter(function, "function");
            return getC().getComponents().getPlatformDependentDeclarationFilter().isFunctionAvailable(this.this$0, function);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void computeNonDeclaredFunctions(Name name, List functions) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(functions, "functions");
            ArrayList arrayList = new ArrayList();
            Iterator it = ((Collection) this.refinedSupertypes.invoke()).iterator();
            while (it.hasNext()) {
                arrayList.addAll(((KotlinType) it.next()).getMemberScope().getContributedFunctions(name, NoLookupLocation.FOR_ALREADY_TRACKED));
            }
            functions.addAll(getC().getComponents().getAdditionalClassPartsProvider().getFunctions(name, this.this$0));
            generateFakeOverrides(name, arrayList, functions);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void computeNonDeclaredProperties(Name name, List descriptors) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(descriptors, "descriptors");
            ArrayList arrayList = new ArrayList();
            Iterator it = ((Collection) this.refinedSupertypes.invoke()).iterator();
            while (it.hasNext()) {
                arrayList.addAll(((KotlinType) it.next()).getMemberScope().getContributedVariables(name, NoLookupLocation.FOR_ALREADY_TRACKED));
            }
            generateFakeOverrides(name, arrayList, descriptors);
        }

        private final void generateFakeOverrides(Name name, Collection collection, final List list) {
            getC().getComponents().getKotlinTypeChecker().getOverridingUtil().generateOverridesInFunctionGroup(name, collection, new ArrayList(list), getClassDescriptor(), new NonReportingOverrideStrategy() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$DeserializedClassMemberScope$generateFakeOverrides$1
                @Override // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
                public void addFakeOverride(CallableMemberDescriptor fakeOverride) {
                    Intrinsics.checkNotNullParameter(fakeOverride, "fakeOverride");
                    OverridingUtil.resolveUnknownVisibilityForMember(fakeOverride, null);
                    list.add(fakeOverride);
                }

                @Override // kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy
                protected void conflict(CallableMemberDescriptor fromSuper, CallableMemberDescriptor fromCurrent) {
                    Intrinsics.checkNotNullParameter(fromSuper, "fromSuper");
                    Intrinsics.checkNotNullParameter(fromCurrent, "fromCurrent");
                    if (fromCurrent instanceof FunctionDescriptorImpl) {
                        ((FunctionDescriptorImpl) fromCurrent).putInUserDataMap(DeserializedDeclarationsFromSupertypeConflictDataKey.INSTANCE, fromSuper);
                    }
                }
            });
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected Set getNonDeclaredFunctionNames() {
            List listMo2782getSupertypes = getClassDescriptor().typeConstructor.mo2782getSupertypes();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = listMo2782getSupertypes.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(linkedHashSet, ((KotlinType) it.next()).getMemberScope().getFunctionNames());
            }
            linkedHashSet.addAll(getC().getComponents().getAdditionalClassPartsProvider().getFunctionsNames(this.this$0));
            return linkedHashSet;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected Set getNonDeclaredVariableNames() {
            List listMo2782getSupertypes = getClassDescriptor().typeConstructor.mo2782getSupertypes();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = listMo2782getSupertypes.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(linkedHashSet, ((KotlinType) it.next()).getMemberScope().getVariableNames());
            }
            return linkedHashSet;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected Set getNonDeclaredClassifierNames() {
            List listMo2782getSupertypes = getClassDescriptor().typeConstructor.mo2782getSupertypes();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = listMo2782getSupertypes.iterator();
            while (it.hasNext()) {
                Set classifierNames = ((KotlinType) it.next()).getMemberScope().getClassifierNames();
                if (classifierNames == null) {
                    return null;
                }
                CollectionsKt.addAll(linkedHashSet, classifierNames);
            }
            return linkedHashSet;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        /* JADX INFO: renamed from: getContributedClassifier */
        public ClassifierDescriptor mo2783getContributedClassifier(Name name, LookupLocation location) {
            ClassDescriptor classDescriptorFindEnumEntry;
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            recordLookup(name, location);
            EnumEntryClassDescriptors enumEntryClassDescriptors = getClassDescriptor().enumEntries;
            return (enumEntryClassDescriptors == null || (classDescriptorFindEnumEntry = enumEntryClassDescriptors.findEnumEntry(name)) == null) ? super.mo2783getContributedClassifier(name, location) : classDescriptorFindEnumEntry;
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected ClassId createClassId(Name name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return this.this$0.classId.createNestedClassId(name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void addEnumEntryDescriptors(Collection result, Function1 nameFilter) {
            Intrinsics.checkNotNullParameter(result, "result");
            Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
            EnumEntryClassDescriptors enumEntryClassDescriptors = getClassDescriptor().enumEntries;
            Collection collectionAll = enumEntryClassDescriptors != null ? enumEntryClassDescriptors.all() : null;
            if (collectionAll == null) {
                collectionAll = CollectionsKt.emptyList();
            }
            result.addAll(collectionAll);
        }

        public void recordLookup(Name name, LookupLocation location) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            UtilsKt.record(getC().getComponents().getLookupTracker(), location, getClassDescriptor(), name);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DeserializedClassDescriptor.kt */
    final class EnumEntryClassDescriptors {
        private final MemoizedFunctionToNullable enumEntryByName;
        private final Map enumEntryProtos;
        private final NotNullLazyValue enumMemberNames;

        public EnumEntryClassDescriptors() {
            List enumEntryList = DeserializedClassDescriptor.this.getClassProto().getEnumEntryList();
            Intrinsics.checkNotNullExpressionValue(enumEntryList, "getEnumEntryList(...)");
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(enumEntryList, 10)), 16));
            for (Object obj : enumEntryList) {
                linkedHashMap.put(NameResolverUtilKt.getName(DeserializedClassDescriptor.this.getC().getNameResolver(), ((ProtoBuf$EnumEntry) obj).getName()), obj);
            }
            this.enumEntryProtos = linkedHashMap;
            StorageManager storageManager = DeserializedClassDescriptor.this.getC().getStorageManager();
            final DeserializedClassDescriptor deserializedClassDescriptor = DeserializedClassDescriptor.this;
            this.enumEntryByName = storageManager.createMemoizedFunctionWithNullableValues(new Function1(this, deserializedClassDescriptor) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$EnumEntryClassDescriptors$$Lambda$0
                private final DeserializedClassDescriptor.EnumEntryClassDescriptors arg$0;
                private final DeserializedClassDescriptor arg$1;

                {
                    this.arg$0 = this;
                    this.arg$1 = deserializedClassDescriptor;
                }

                @Override // kotlin.jvm.functions.Function1
                public Object invoke(Object obj2) {
                    return DeserializedClassDescriptor.EnumEntryClassDescriptors.enumEntryByName$lambda$3(this.arg$0, this.arg$1, (Name) obj2);
                }
            });
            this.enumMemberNames = DeserializedClassDescriptor.this.getC().getStorageManager().createLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$EnumEntryClassDescriptors$$Lambda$1
                private final DeserializedClassDescriptor.EnumEntryClassDescriptors arg$0;

                {
                    this.arg$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.arg$0.computeEnumMemberNames();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ClassDescriptor enumEntryByName$lambda$3(EnumEntryClassDescriptors enumEntryClassDescriptors, final DeserializedClassDescriptor deserializedClassDescriptor, Name name) {
            Intrinsics.checkNotNullParameter(name, "name");
            final ProtoBuf$EnumEntry protoBuf$EnumEntry = (ProtoBuf$EnumEntry) enumEntryClassDescriptors.enumEntryProtos.get(name);
            if (protoBuf$EnumEntry != null) {
                return EnumEntrySyntheticClassDescriptor.create(deserializedClassDescriptor.getC().getStorageManager(), deserializedClassDescriptor, name, enumEntryClassDescriptors.enumMemberNames, new DeserializedAnnotations(deserializedClassDescriptor.getC().getStorageManager(), new Function0(deserializedClassDescriptor, protoBuf$EnumEntry) { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor$EnumEntryClassDescriptors$$Lambda$2
                    private final DeserializedClassDescriptor arg$0;
                    private final ProtoBuf$EnumEntry arg$1;

                    {
                        this.arg$0 = deserializedClassDescriptor;
                        this.arg$1 = protoBuf$EnumEntry;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return DeserializedClassDescriptor.EnumEntryClassDescriptors.enumEntryByName$lambda$3$lambda$2$lambda$1(this.arg$0, this.arg$1);
                    }
                }), SourceElement.NO_SOURCE);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List enumEntryByName$lambda$3$lambda$2$lambda$1(DeserializedClassDescriptor deserializedClassDescriptor, ProtoBuf$EnumEntry protoBuf$EnumEntry) {
            return CollectionsKt.toList(deserializedClassDescriptor.getC().getComponents().getAnnotationAndConstantLoader().loadEnumEntryAnnotations(deserializedClassDescriptor.getThisAsProtoContainer$deserialization(), protoBuf$EnumEntry));
        }

        public final ClassDescriptor findEnumEntry(Name name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return (ClassDescriptor) this.enumEntryByName.invoke(name);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Set computeEnumMemberNames() {
            HashSet hashSet = new HashSet();
            Iterator it = DeserializedClassDescriptor.this.getTypeConstructor().mo2782getSupertypes().iterator();
            while (it.hasNext()) {
                for (DeclarationDescriptor declarationDescriptor : ResolutionScope.DefaultImpls.getContributedDescriptors$default(((KotlinType) it.next()).getMemberScope(), null, null, 3, null)) {
                    if ((declarationDescriptor instanceof SimpleFunctionDescriptor) || (declarationDescriptor instanceof PropertyDescriptor)) {
                        hashSet.add(((CallableMemberDescriptor) declarationDescriptor).getName());
                    }
                }
            }
            List functionList = DeserializedClassDescriptor.this.getClassProto().getFunctionList();
            Intrinsics.checkNotNullExpressionValue(functionList, "getFunctionList(...)");
            DeserializedClassDescriptor deserializedClassDescriptor = DeserializedClassDescriptor.this;
            Iterator it2 = functionList.iterator();
            while (it2.hasNext()) {
                hashSet.add(NameResolverUtilKt.getName(deserializedClassDescriptor.getC().getNameResolver(), ((ProtoBuf$Function) it2.next()).getName()));
            }
            List propertyList = DeserializedClassDescriptor.this.getClassProto().getPropertyList();
            Intrinsics.checkNotNullExpressionValue(propertyList, "getPropertyList(...)");
            DeserializedClassDescriptor deserializedClassDescriptor2 = DeserializedClassDescriptor.this;
            Iterator it3 = propertyList.iterator();
            while (it3.hasNext()) {
                hashSet.add(NameResolverUtilKt.getName(deserializedClassDescriptor2.getC().getNameResolver(), ((ProtoBuf$Property) it3.next()).getName()));
            }
            return SetsKt.plus((Set) hashSet, (Iterable) hashSet);
        }

        public final Collection all() {
            Set setKeySet = this.enumEntryProtos.keySet();
            ArrayList arrayList = new ArrayList();
            Iterator it = setKeySet.iterator();
            while (it.hasNext()) {
                ClassDescriptor classDescriptorFindEnumEntry = findEnumEntry((Name) it.next());
                if (classDescriptorFindEnumEntry != null) {
                    arrayList.add(classDescriptorFindEnumEntry);
                }
            }
            return arrayList;
        }
    }
}
