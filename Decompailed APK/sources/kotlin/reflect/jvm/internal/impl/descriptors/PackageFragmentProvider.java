package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: PackageFragmentProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface PackageFragmentProvider {
    List getPackageFragments(FqName fqName);

    Collection getSubPackagesOf(FqName fqName, Function1 function1);
}
