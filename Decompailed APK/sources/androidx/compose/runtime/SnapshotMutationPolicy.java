package androidx.compose.runtime;

/* JADX INFO: compiled from: SnapshotMutationPolicy.kt */
/* JADX INFO: loaded from: classes.dex */
public interface SnapshotMutationPolicy {
    boolean equivalent(Object obj, Object obj2);

    Object merge(Object obj, Object obj2, Object obj3);

    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotMutationPolicy$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotMutationPolicy.kt */
    public abstract /* synthetic */ class CC {
        public static Object $default$merge(SnapshotMutationPolicy snapshotMutationPolicy, Object obj, Object obj2, Object obj3) {
            return null;
        }
    }
}
