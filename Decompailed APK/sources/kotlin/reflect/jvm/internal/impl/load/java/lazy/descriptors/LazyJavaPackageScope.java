package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageScope;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.load.java.structure.LightClassOriginKind;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinderKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.utils.DeserializationHelpersKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;

/* JADX INFO: compiled from: LazyJavaPackageScope.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class LazyJavaPackageScope extends LazyJavaStaticScope {
    private final MemoizedFunctionToNullable classes;
    private final JavaPackage jPackage;
    private final NullableLazyValue knownClassNamesInPackage;
    private final LazyJavaPackageFragment ownerDescriptor;

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredFunctions(Collection result, Name name) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(name, "name");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public LazyJavaPackageFragment getOwnerDescriptor() {
        return this.ownerDescriptor;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaPackageScope(final LazyJavaResolverContext c, JavaPackage jPackage, LazyJavaPackageFragment ownerDescriptor) {
        super(c);
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(jPackage, "jPackage");
        Intrinsics.checkNotNullParameter(ownerDescriptor, "ownerDescriptor");
        this.jPackage = jPackage;
        this.ownerDescriptor = ownerDescriptor;
        this.knownClassNamesInPackage = c.getStorageManager().createNullableLazyValue(new Function0(c, this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageScope$$Lambda$0
            private final LazyJavaResolverContext arg$0;
            private final LazyJavaPackageScope arg$1;

            {
                this.arg$0 = c;
                this.arg$1 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public Object invoke() {
                return LazyJavaPackageScope.knownClassNamesInPackage$lambda$0(this.arg$0, this.arg$1);
            }
        });
        this.classes = c.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1(this, c) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageScope$$Lambda$1
            private final LazyJavaPackageScope arg$0;
            private final LazyJavaResolverContext arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = c;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return LazyJavaPackageScope.classes$lambda$1(this.arg$0, this.arg$1, (LazyJavaPackageScope.FindClassRequest) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set knownClassNamesInPackage$lambda$0(LazyJavaResolverContext lazyJavaResolverContext, LazyJavaPackageScope lazyJavaPackageScope) {
        return lazyJavaResolverContext.getComponents().getFinder().knownClassNamesInPackage(lazyJavaPackageScope.getOwnerDescriptor().getFqName());
    }

    private final JvmMetadataVersion getJvmMetadataVersion() {
        return DeserializationHelpersKt.jvmMetadataVersionOrDefault(getC().getComponents().getDeserializedDescriptorResolver().getComponents().getConfiguration());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClassDescriptor classes$lambda$1(LazyJavaPackageScope lazyJavaPackageScope, LazyJavaResolverContext lazyJavaResolverContext, FindClassRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        ClassId classId = new ClassId(lazyJavaPackageScope.getOwnerDescriptor().getFqName(), request.getName());
        KotlinClassFinder.Result resultFindKotlinClassOrContent = request.getJavaClass() != null ? lazyJavaResolverContext.getComponents().getKotlinClassFinder().findKotlinClassOrContent(request.getJavaClass(), lazyJavaPackageScope.getJvmMetadataVersion()) : lazyJavaResolverContext.getComponents().getKotlinClassFinder().findKotlinClassOrContent(classId, lazyJavaPackageScope.getJvmMetadataVersion());
        KotlinJvmBinaryClass kotlinJvmBinaryClass = resultFindKotlinClassOrContent != null ? resultFindKotlinClassOrContent.toKotlinJvmBinaryClass() : null;
        ClassId classId2 = kotlinJvmBinaryClass != null ? kotlinJvmBinaryClass.getClassId() : null;
        if (classId2 != null && (classId2.isNestedClass() || classId2.isLocal())) {
            return null;
        }
        KotlinClassLookupResult kotlinClassLookupResultResolveKotlinBinaryClass = lazyJavaPackageScope.resolveKotlinBinaryClass(kotlinJvmBinaryClass);
        if (kotlinClassLookupResultResolveKotlinBinaryClass instanceof KotlinClassLookupResult.Found) {
            return ((KotlinClassLookupResult.Found) kotlinClassLookupResultResolveKotlinBinaryClass).getDescriptor();
        }
        if (kotlinClassLookupResultResolveKotlinBinaryClass instanceof KotlinClassLookupResult.SyntheticClass) {
            return null;
        }
        if (!(kotlinClassLookupResultResolveKotlinBinaryClass instanceof KotlinClassLookupResult.NotFound)) {
            throw new NoWhenBranchMatchedException();
        }
        JavaClass javaClass = request.getJavaClass();
        if (javaClass == null) {
            javaClass = lazyJavaResolverContext.getComponents().getFinder().findClass(new JavaClassFinder.Request(classId, null, null, 4, null));
        }
        JavaClass javaClass2 = javaClass;
        if ((javaClass2 != null ? javaClass2.getLightClassOriginKind() : null) != LightClassOriginKind.BINARY) {
            FqName fqName = javaClass2 != null ? javaClass2.getFqName() : null;
            if (fqName == null || fqName.isRoot() || !Intrinsics.areEqual(fqName.parent(), lazyJavaPackageScope.getOwnerDescriptor().getFqName())) {
                return null;
            }
            LazyJavaClassDescriptor lazyJavaClassDescriptor = new LazyJavaClassDescriptor(lazyJavaResolverContext, lazyJavaPackageScope.getOwnerDescriptor(), javaClass2, null, 8, null);
            lazyJavaResolverContext.getComponents().getJavaClassesTracker().reportClass(lazyJavaClassDescriptor);
            return lazyJavaClassDescriptor;
        }
        throw new IllegalStateException("Couldn't find kotlin binary class for light class created by kotlin binary file\nJavaClass: " + javaClass2 + "\nClassId: " + classId + "\nfindKotlinClass(JavaClass) = " + KotlinClassFinderKt.findKotlinClass(lazyJavaResolverContext.getComponents().getKotlinClassFinder(), javaClass2, lazyJavaPackageScope.getJvmMetadataVersion()) + "\nfindKotlinClass(ClassId) = " + KotlinClassFinderKt.findKotlinClass(lazyJavaResolverContext.getComponents().getKotlinClassFinder(), classId, lazyJavaPackageScope.getJvmMetadataVersion()) + '\n');
    }

    /* JADX INFO: compiled from: LazyJavaPackageScope.kt */
    private static abstract class KotlinClassLookupResult {
        public /* synthetic */ KotlinClassLookupResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: compiled from: LazyJavaPackageScope.kt */
        public static final class Found extends KotlinClassLookupResult {
            private final ClassDescriptor descriptor;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Found(ClassDescriptor descriptor) {
                super(null);
                Intrinsics.checkNotNullParameter(descriptor, "descriptor");
                this.descriptor = descriptor;
            }

            public final ClassDescriptor getDescriptor() {
                return this.descriptor;
            }
        }

        private KotlinClassLookupResult() {
        }

        /* JADX INFO: compiled from: LazyJavaPackageScope.kt */
        public static final class NotFound extends KotlinClassLookupResult {
            public static final NotFound INSTANCE = new NotFound();

            private NotFound() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: LazyJavaPackageScope.kt */
        public static final class SyntheticClass extends KotlinClassLookupResult {
            public static final SyntheticClass INSTANCE = new SyntheticClass();

            private SyntheticClass() {
                super(null);
            }
        }
    }

    private final KotlinClassLookupResult resolveKotlinBinaryClass(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        if (kotlinJvmBinaryClass == null) {
            return KotlinClassLookupResult.NotFound.INSTANCE;
        }
        if (kotlinJvmBinaryClass.getClassHeader().getKind() == KotlinClassHeader.Kind.CLASS) {
            ClassDescriptor classDescriptorResolveClass = getC().getComponents().getDeserializedDescriptorResolver().resolveClass(kotlinJvmBinaryClass);
            return classDescriptorResolveClass != null ? new KotlinClassLookupResult.Found(classDescriptorResolveClass) : KotlinClassLookupResult.NotFound.INSTANCE;
        }
        return KotlinClassLookupResult.SyntheticClass.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: LazyJavaPackageScope.kt */
    static final class FindClassRequest {
        private final JavaClass javaClass;
        private final Name name;

        public FindClassRequest(Name name, JavaClass javaClass) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
            this.javaClass = javaClass;
        }

        public final JavaClass getJavaClass() {
            return this.javaClass;
        }

        public final Name getName() {
            return this.name;
        }

        public boolean equals(Object obj) {
            return (obj instanceof FindClassRequest) && Intrinsics.areEqual(this.name, ((FindClassRequest) obj).name);
        }

        public int hashCode() {
            return this.name.hashCode();
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    /* JADX INFO: renamed from: getContributedClassifier */
    public ClassDescriptor mo2783getContributedClassifier(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        return findClassifier(name, null);
    }

    private final ClassDescriptor findClassifier(Name name, JavaClass javaClass) {
        if (!SpecialNames.INSTANCE.isSafeIdentifier(name)) {
            return null;
        }
        Set set = (Set) this.knownClassNamesInPackage.invoke();
        if (javaClass != null || set == null || set.contains(name.asString())) {
            return (ClassDescriptor) this.classes.invoke(new FindClassRequest(name, javaClass));
        }
        return null;
    }

    public final ClassDescriptor findClassifierByJavaClass$descriptors_jvm(JavaClass javaClass) {
        Intrinsics.checkNotNullParameter(javaClass, "javaClass");
        return findClassifier(javaClass.getName(), javaClass);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedVariables(Name name, LookupLocation location) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(location, "location");
        return CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected DeclaredMemberIndex computeMemberIndex() {
        return DeclaredMemberIndex.Empty.INSTANCE;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set computeClassNames(DescriptorKindFilter kindFilter, Function1 function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        if (!kindFilter.acceptsKinds(DescriptorKindFilter.Companion.getNON_SINGLETON_CLASSIFIERS_MASK())) {
            return SetsKt.emptySet();
        }
        Set set = (Set) this.knownClassNamesInPackage.invoke();
        if (set == null) {
            JavaPackage javaPackage = this.jPackage;
            if (function1 == null) {
                function1 = FunctionsKt.alwaysTrue();
            }
            Collection<JavaClass> classes = javaPackage.getClasses(function1);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (JavaClass javaClass : classes) {
                Name name = javaClass.getLightClassOriginKind() == LightClassOriginKind.SOURCE ? null : javaClass.getName();
                if (name != null) {
                    linkedHashSet.add(name);
                }
            }
            return linkedHashSet;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(Name.identifier((String) it.next()));
        }
        return hashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set computeFunctionNames(DescriptorKindFilter kindFilter, Function1 function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        return SetsKt.emptySet();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set computePropertyNames(DescriptorKindFilter kindFilter, Function1 function1) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        return SetsKt.emptySet();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public Collection getContributedDescriptors(DescriptorKindFilter kindFilter, Function1 nameFilter) {
        Intrinsics.checkNotNullParameter(kindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        DescriptorKindFilter.Companion companion = DescriptorKindFilter.Companion;
        if (!kindFilter.acceptsKinds(companion.getNON_SINGLETON_CLASSIFIERS_MASK() | companion.getCLASSIFIERS_MASK())) {
            return CollectionsKt.emptyList();
        }
        Iterable iterable = (Iterable) getAllDescriptors().invoke();
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterable) {
            DeclarationDescriptor declarationDescriptor = (DeclarationDescriptor) obj;
            if (declarationDescriptor instanceof ClassDescriptor) {
                Name name = ((ClassDescriptor) declarationDescriptor).getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                if (((Boolean) nameFilter.invoke(name)).booleanValue()) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }
}
