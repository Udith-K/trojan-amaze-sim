package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* JADX INFO: compiled from: AbstractTypeAliasDescriptor.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractTypeAliasDescriptor extends DeclarationDescriptorNonRootImpl implements TypeAliasDescriptor {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(AbstractTypeAliasDescriptor.class), "constructors", "getConstructors()Ljava/util/Collection;"))};
    private final NotNullLazyValue constructors$delegate;
    private List declaredTypeParametersImpl;
    private final StorageManager storageManager;
    private final AbstractTypeAliasDescriptor$typeConstructor$1 typeConstructor;
    private final DescriptorVisibility visibilityImpl;

    protected abstract List getTypeConstructorTypeParameters();

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return false;
    }

    protected final StorageManager getStorageManager() {
        return this.storageManager;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor$typeConstructor$1] */
    public AbstractTypeAliasDescriptor(StorageManager storageManager, DeclarationDescriptor containingDeclaration, Annotations annotations, Name name, SourceElement sourceElement, DescriptorVisibility visibilityImpl) {
        super(containingDeclaration, annotations, name, sourceElement);
        Intrinsics.checkNotNullParameter(storageManager, "storageManager");
        Intrinsics.checkNotNullParameter(containingDeclaration, "containingDeclaration");
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(sourceElement, "sourceElement");
        Intrinsics.checkNotNullParameter(visibilityImpl, "visibilityImpl");
        this.storageManager = storageManager;
        this.visibilityImpl = visibilityImpl;
        this.constructors$delegate = storageManager.createLazyValue(new Function0(this) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor$$Lambda$0
            private final AbstractTypeAliasDescriptor arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.arg$0.getTypeAliasConstructors();
            }
        });
        this.typeConstructor = new TypeConstructor() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor$typeConstructor$1
            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public boolean isDenotable() {
                return true;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner) {
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            /* JADX INFO: renamed from: getDeclarationDescriptor */
            public TypeAliasDescriptor mo2781getDeclarationDescriptor() {
                return this.this$0;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public List getParameters() {
                return this.this$0.getTypeConstructorTypeParameters();
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            /* JADX INFO: renamed from: getSupertypes */
            public Collection mo2782getSupertypes() {
                Collection collectionMo2782getSupertypes = mo2781getDeclarationDescriptor().getUnderlyingType().getConstructor().mo2782getSupertypes();
                Intrinsics.checkNotNullExpressionValue(collectionMo2782getSupertypes, "getSupertypes(...)");
                return collectionMo2782getSupertypes;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public KotlinBuiltIns getBuiltIns() {
                return DescriptorUtilsKt.getBuiltIns(mo2781getDeclarationDescriptor());
            }

            public String toString() {
                return "[typealias " + mo2781getDeclarationDescriptor().getName().asString() + ']';
            }
        };
    }

    public final void initialize(List declaredTypeParameters) {
        Intrinsics.checkNotNullParameter(declaredTypeParameters, "declaredTypeParameters");
        this.declaredTypeParametersImpl = declaredTypeParameters;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor visitor, Object obj) {
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        return visitor.visitTypeAliasDescriptor(this, obj);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        return TypeUtils.contains(getUnderlyingType(), new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor$$Lambda$1
            private final AbstractTypeAliasDescriptor arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return AbstractTypeAliasDescriptor.isInner$lambda$2(this.arg$0, (UnwrappedType) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Boolean isInner$lambda$2(kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor r1, kotlin.reflect.jvm.internal.impl.types.UnwrappedType r2) {
        /*
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            boolean r0 = kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt.isError(r2)
            if (r0 != 0) goto L23
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r2 = r2.getConstructor()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r2 = r2.mo2781getDeclarationDescriptor()
            boolean r0 = r2 instanceof kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
            if (r0 == 0) goto L23
            kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor) r2
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r2 = r2.getContainingDeclaration()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r1)
            if (r1 != 0) goto L23
            r1 = 1
            goto L24
        L23:
            r1 = 0
        L24:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor.isInner$lambda$2(kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor, kotlin.reflect.jvm.internal.impl.types.UnwrappedType):java.lang.Boolean");
    }

    public final Collection getTypeAliasConstructors() {
        ClassDescriptor classDescriptor = getClassDescriptor();
        if (classDescriptor == null) {
            return CollectionsKt.emptyList();
        }
        Collection<ClassConstructorDescriptor> constructors = classDescriptor.getConstructors();
        Intrinsics.checkNotNullExpressionValue(constructors, "getConstructors(...)");
        ArrayList arrayList = new ArrayList();
        for (ClassConstructorDescriptor classConstructorDescriptor : constructors) {
            TypeAliasConstructorDescriptorImpl.Companion companion = TypeAliasConstructorDescriptorImpl.Companion;
            StorageManager storageManager = this.storageManager;
            Intrinsics.checkNotNull(classConstructorDescriptor);
            TypeAliasConstructorDescriptor typeAliasConstructorDescriptorCreateIfAvailable = companion.createIfAvailable(storageManager, this, classConstructorDescriptor);
            if (typeAliasConstructorDescriptorCreateIfAvailable != null) {
                arrayList.add(typeAliasConstructorDescriptorCreateIfAvailable);
            }
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public List getDeclaredTypeParameters() {
        List list = this.declaredTypeParametersImpl;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("declaredTypeParametersImpl");
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility
    public DescriptorVisibility getVisibility() {
        return this.visibilityImpl;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl
    public String toString() {
        return "typealias " + getName().asString();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public TypeAliasDescriptor getOriginal() {
        DeclarationDescriptorWithSource original = super.getOriginal();
        Intrinsics.checkNotNull(original, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeAliasDescriptor");
        return (TypeAliasDescriptor) original;
    }

    protected final SimpleType computeDefaultType() {
        MemberScope unsubstitutedMemberScope;
        ClassDescriptor classDescriptor = getClassDescriptor();
        if (classDescriptor == null || (unsubstitutedMemberScope = classDescriptor.getUnsubstitutedMemberScope()) == null) {
            unsubstitutedMemberScope = MemberScope.Empty.INSTANCE;
        }
        SimpleType simpleTypeMakeUnsubstitutedType = TypeUtils.makeUnsubstitutedType(this, unsubstitutedMemberScope, new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor$$Lambda$2
            private final AbstractTypeAliasDescriptor arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return AbstractTypeAliasDescriptor.computeDefaultType$lambda$4(this.arg$0, (KotlinTypeRefiner) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(simpleTypeMakeUnsubstitutedType, "makeUnsubstitutedType(...)");
        return simpleTypeMakeUnsubstitutedType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SimpleType computeDefaultType$lambda$4(AbstractTypeAliasDescriptor abstractTypeAliasDescriptor, KotlinTypeRefiner kotlinTypeRefiner) {
        ClassifierDescriptor classifierDescriptorRefineDescriptor = kotlinTypeRefiner.refineDescriptor(abstractTypeAliasDescriptor);
        if (classifierDescriptorRefineDescriptor != null) {
            return classifierDescriptorRefineDescriptor.getDefaultType();
        }
        return null;
    }
}
