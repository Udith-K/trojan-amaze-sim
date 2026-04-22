package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: PackageFragmentProviderImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class PackageFragmentProviderImpl implements PackageFragmentProviderOptimized {
    private final Collection packageFragments;

    public PackageFragmentProviderImpl(Collection packageFragments) {
        Intrinsics.checkNotNullParameter(packageFragments, "packageFragments");
        this.packageFragments = packageFragments;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public void collectPackageFragments(FqName fqName, Collection packageFragments) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(packageFragments, "packageFragments");
        for (Object obj : this.packageFragments) {
            if (Intrinsics.areEqual(((PackageFragmentDescriptor) obj).getFqName(), fqName)) {
                packageFragments.add(obj);
            }
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public boolean isEmpty(FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Collection collection = this.packageFragments;
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return true;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((PackageFragmentDescriptor) it.next()).getFqName(), fqName)) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    public List getPackageFragments(FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Collection collection = this.packageFragments;
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (Intrinsics.areEqual(((PackageFragmentDescriptor) obj).getFqName(), fqName)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    public Collection getSubPackagesOf(final FqName fqName, Function1 nameFilter) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        Intrinsics.checkNotNullParameter(nameFilter, "nameFilter");
        return SequencesKt.toList(SequencesKt.filter(SequencesKt.map(CollectionsKt.asSequence(this.packageFragments), new Function1() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl$$Lambda$0
            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return PackageFragmentProviderImpl.getSubPackagesOf$lambda$3((PackageFragmentDescriptor) obj);
            }
        }), new Function1(fqName) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl$$Lambda$1
            private final FqName arg$0;

            {
                this.arg$0 = fqName;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return Boolean.valueOf(PackageFragmentProviderImpl.getSubPackagesOf$lambda$4(this.arg$0, (FqName) obj));
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName getSubPackagesOf$lambda$3(PackageFragmentDescriptor it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getFqName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getSubPackagesOf$lambda$4(FqName fqName, FqName it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !it.isRoot() && Intrinsics.areEqual(it.parent(), fqName);
    }
}
