package kotlin.collections;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Sets.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SetsKt__SetsKt extends SetsKt__SetsJVMKt {
    public static Set emptySet() {
        return EmptySet.INSTANCE;
    }

    public static Set setOf(Object... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ArraysKt.toSet(elements);
    }

    public static Set mutableSetOf(Object... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return (Set) ArraysKt___ArraysKt.toCollection(elements, new LinkedHashSet(MapsKt.mapCapacity(elements.length)));
    }

    public static LinkedHashSet linkedSetOf(Object... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return (LinkedHashSet) ArraysKt___ArraysKt.toCollection(elements, new LinkedHashSet(MapsKt.mapCapacity(elements.length)));
    }

    public static final Set optimizeReadOnlySet(Set set) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        int size = set.size();
        if (size != 0) {
            return size != 1 ? set : SetsKt.setOf(set.iterator().next());
        }
        return SetsKt.emptySet();
    }
}
