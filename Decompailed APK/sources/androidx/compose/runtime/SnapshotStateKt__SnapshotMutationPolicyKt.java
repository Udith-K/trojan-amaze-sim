package androidx.compose.runtime;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SnapshotMutationPolicy.kt */
/* JADX INFO: loaded from: classes.dex */
abstract /* synthetic */ class SnapshotStateKt__SnapshotMutationPolicyKt {
    public static final SnapshotMutationPolicy referentialEqualityPolicy() {
        ReferentialEqualityPolicy referentialEqualityPolicy = ReferentialEqualityPolicy.INSTANCE;
        Intrinsics.checkNotNull(referentialEqualityPolicy, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutationPolicy<T of androidx.compose.runtime.SnapshotStateKt__SnapshotMutationPolicyKt.referentialEqualityPolicy>");
        return referentialEqualityPolicy;
    }

    public static final SnapshotMutationPolicy structuralEqualityPolicy() {
        StructuralEqualityPolicy structuralEqualityPolicy = StructuralEqualityPolicy.INSTANCE;
        Intrinsics.checkNotNull(structuralEqualityPolicy, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutationPolicy<T of androidx.compose.runtime.SnapshotStateKt__SnapshotMutationPolicyKt.structuralEqualityPolicy>");
        return structuralEqualityPolicy;
    }

    public static final SnapshotMutationPolicy neverEqualPolicy() {
        NeverEqualPolicy neverEqualPolicy = NeverEqualPolicy.INSTANCE;
        Intrinsics.checkNotNull(neverEqualPolicy, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutationPolicy<T of androidx.compose.runtime.SnapshotStateKt__SnapshotMutationPolicyKt.neverEqualPolicy>");
        return neverEqualPolicy;
    }
}
