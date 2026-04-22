package kotlin.reflect.jvm.internal.impl.incremental;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* JADX INFO: compiled from: utils.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class UtilsKt {
    public static final void record(LookupTracker lookupTracker, LookupLocation from, ClassDescriptor scopeOwner, Name name) {
        Intrinsics.checkNotNullParameter(lookupTracker, "<this>");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(scopeOwner, "scopeOwner");
        Intrinsics.checkNotNullParameter(name, "name");
        if (lookupTracker == LookupTracker.DO_NOTHING.INSTANCE) {
            return;
        }
        from.getLocation();
    }

    public static final void record(LookupTracker lookupTracker, LookupLocation from, PackageFragmentDescriptor scopeOwner, Name name) {
        Intrinsics.checkNotNullParameter(lookupTracker, "<this>");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(scopeOwner, "scopeOwner");
        Intrinsics.checkNotNullParameter(name, "name");
        String strAsString = scopeOwner.getFqName().asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "asString(...)");
        String strAsString2 = name.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString2, "asString(...)");
        recordPackageLookup(lookupTracker, from, strAsString, strAsString2);
    }

    public static final void recordPackageLookup(LookupTracker lookupTracker, LookupLocation from, String packageFqName, String name) {
        Intrinsics.checkNotNullParameter(lookupTracker, "<this>");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
        Intrinsics.checkNotNullParameter(name, "name");
        if (lookupTracker == LookupTracker.DO_NOTHING.INSTANCE) {
            return;
        }
        from.getLocation();
    }
}
