package androidx.compose.runtime;

import androidx.compose.runtime.SnapshotMutationPolicy;

/* JADX INFO: compiled from: SnapshotMutationPolicy.kt */
/* JADX INFO: loaded from: classes.dex */
final class NeverEqualPolicy implements SnapshotMutationPolicy {
    public static final NeverEqualPolicy INSTANCE = new NeverEqualPolicy();

    @Override // androidx.compose.runtime.SnapshotMutationPolicy
    public boolean equivalent(Object obj, Object obj2) {
        return false;
    }

    @Override // androidx.compose.runtime.SnapshotMutationPolicy
    public /* synthetic */ Object merge(Object obj, Object obj2, Object obj3) {
        return SnapshotMutationPolicy.CC.$default$merge(this, obj, obj2, obj3);
    }

    private NeverEqualPolicy() {
    }

    public String toString() {
        return "NeverEqualPolicy";
    }
}
