package io.ktor.util;

import java.util.Collections;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CollectionsJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CollectionsJvmKt {
    public static final Set unmodifiable(Set set) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        Set setUnmodifiableSet = Collections.unmodifiableSet(set);
        Intrinsics.checkNotNullExpressionValue(setUnmodifiableSet, "unmodifiableSet(this)");
        return setUnmodifiableSet;
    }
}
