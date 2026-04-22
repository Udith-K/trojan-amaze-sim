package kotlin.reflect.jvm.internal.impl.util.collectionUtils;

import java.util.Collection;
import java.util.LinkedHashSet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

/* JADX INFO: compiled from: scopeUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ScopeUtilsKt {
    public static final Collection concat(Collection collection, Collection collection2) {
        Intrinsics.checkNotNullParameter(collection2, "collection");
        if (collection2.isEmpty()) {
            return collection;
        }
        if (collection == null) {
            return collection2;
        }
        if (collection instanceof LinkedHashSet) {
            ((LinkedHashSet) collection).addAll(collection2);
            return collection;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection);
        linkedHashSet.addAll(collection2);
        return linkedHashSet;
    }

    public static final SmartList listOfNonEmptyScopes(Iterable scopes) {
        Intrinsics.checkNotNullParameter(scopes, "scopes");
        SmartList smartList = new SmartList();
        for (Object obj : scopes) {
            MemberScope memberScope = (MemberScope) obj;
            if (memberScope != null && memberScope != MemberScope.Empty.INSTANCE) {
                smartList.add(obj);
            }
        }
        return smartList;
    }
}
