package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: CompositePackageFragmentProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CompositePackageFragmentProvider implements PackageFragmentProviderOptimized {
    private final String debugName;
    private final List providers;

    public CompositePackageFragmentProvider(List providers, String debugName) {
        Intrinsics.checkNotNullParameter(providers, "providers");
        Intrinsics.checkNotNullParameter(debugName, "debugName");
        this.providers = providers;
        this.debugName = debugName;
        providers.size();
        CollectionsKt.toSet(providers).size();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    public List getPackageFragments(FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        ArrayList arrayList = new ArrayList();
        Iterator it = this.providers.iterator();
        while (it.hasNext()) {
            PackageFragmentProviderKt.collectPackageFragmentsOptimizedIfPossible((PackageFragmentProvider) it.next(), fqName, arrayList);
        }
        return CollectionsKt.toList(arrayList);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public void collectPackageFragments(FqName fqName, Collection packageFragments) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(packageFragments, "packageFragments");
        Iterator it = this.providers.iterator();
        while (it.hasNext()) {
            PackageFragmentProviderKt.collectPackageFragmentsOptimizedIfPossible((PackageFragmentProvider) it.next(), fqName, packageFragments);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public boolean isEmpty(FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        List list = this.providers;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (!PackageFragmentProviderKt.isEmpty((PackageFragmentProvider) it.next(), fqName)) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    public Collection getSubPackagesOf(FqName fqName, Function1 nameFilter) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        HashSet hashSet = new HashSet();
        Iterator it = this.providers.iterator();
        while (it.hasNext()) {
            hashSet.addAll(((PackageFragmentProvider) it.next()).getSubPackagesOf(fqName, nameFilter));
        }
        return hashSet;
    }

    public String toString() {
        return this.debugName;
    }
}
