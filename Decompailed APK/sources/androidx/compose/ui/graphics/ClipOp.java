package androidx.compose.ui.graphics;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ClipOp.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ClipOp {
    public static final Companion Companion = new Companion(null);
    private static final int Difference = m1286constructorimpl(0);
    private static final int Intersect = m1286constructorimpl(1);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1286constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1287equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: compiled from: ClipOp.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getDifference-rtfAjoo, reason: not valid java name */
        public final int m1288getDifferencertfAjoo() {
            return ClipOp.Difference;
        }

        /* JADX INFO: renamed from: getIntersect-rtfAjoo, reason: not valid java name */
        public final int m1289getIntersectrtfAjoo() {
            return ClipOp.Intersect;
        }
    }
}
