package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: SyntheticJavaPartsProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface SyntheticJavaPartsProvider {
    public static final Companion Companion = Companion.$$INSTANCE;

    void generateConstructors(ClassDescriptor classDescriptor, List list, LazyJavaResolverContext lazyJavaResolverContext);

    void generateMethods(ClassDescriptor classDescriptor, Name name, Collection collection, LazyJavaResolverContext lazyJavaResolverContext);

    void generateNestedClass(ClassDescriptor classDescriptor, Name name, List list, LazyJavaResolverContext lazyJavaResolverContext);

    void generateStaticFunctions(ClassDescriptor classDescriptor, Name name, Collection collection, LazyJavaResolverContext lazyJavaResolverContext);

    List getMethodNames(ClassDescriptor classDescriptor, LazyJavaResolverContext lazyJavaResolverContext);

    List getNestedClassNames(ClassDescriptor classDescriptor, LazyJavaResolverContext lazyJavaResolverContext);

    List getStaticFunctionNames(ClassDescriptor classDescriptor, LazyJavaResolverContext lazyJavaResolverContext);

    PropertyDescriptorImpl modifyField(ClassDescriptor classDescriptor, PropertyDescriptorImpl propertyDescriptorImpl, LazyJavaResolverContext lazyJavaResolverContext);

    /* JADX INFO: compiled from: SyntheticJavaPartsProvider.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final CompositeSyntheticJavaPartsProvider EMPTY = new CompositeSyntheticJavaPartsProvider(CollectionsKt.emptyList());

        private Companion() {
        }

        public final CompositeSyntheticJavaPartsProvider getEMPTY() {
            return EMPTY;
        }
    }
}
