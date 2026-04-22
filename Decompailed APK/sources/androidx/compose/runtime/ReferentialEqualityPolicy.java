package androidx.compose.runtime;

import androidx.compose.runtime.SnapshotMutationPolicy;

/* JADX INFO: compiled from: SnapshotMutationPolicy.kt */
/* JADX INFO: loaded from: classes.dex */
final class ReferentialEqualityPolicy implements SnapshotMutationPolicy {
    public static final ReferentialEqualityPolicy INSTANCE = new ReferentialEqualityPolicy();

    @Override // androidx.compose.runtime.SnapshotMutationPolicy
    public boolean equivalent(Object obj, Object obj2) {
        return obj == obj2;
    }

    @Override // androidx.compose.runtime.SnapshotMutationPolicy
    public /* synthetic */ Object merge(Object obj, Object obj2, Object obj3) {
        return SnapshotMutationPolicy.CC.$default$merge(this, obj, obj2, obj3);
    }

    private ReferentialEqualityPolicy() {
    }

    public String toString() {
        return "ReferentialEqualityPolicy";
    }
}
