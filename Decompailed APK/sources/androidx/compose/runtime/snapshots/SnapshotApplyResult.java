package androidx.compose.runtime.snapshots;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SnapshotApplyResult {
    public /* synthetic */ SnapshotApplyResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private SnapshotApplyResult() {
    }

    /* JADX INFO: compiled from: Snapshot.kt */
    public static final class Success extends SnapshotApplyResult {
        public static final Success INSTANCE = new Success();

        private Success() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: Snapshot.kt */
    public static final class Failure extends SnapshotApplyResult {
        private final Snapshot snapshot;

        public Failure(Snapshot snapshot) {
            super(null);
            this.snapshot = snapshot;
        }
    }
}
