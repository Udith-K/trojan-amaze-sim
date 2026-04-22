package androidx.compose.ui.graphics;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PathOperation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PathOperation {
    public static final Companion Companion = new Companion(null);
    private static final int Difference = m1389constructorimpl(0);
    private static final int Intersect = m1389constructorimpl(1);
    private static final int Union = m1389constructorimpl(2);
    private static final int Xor = m1389constructorimpl(3);
    private static final int ReverseDifference = m1389constructorimpl(4);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1389constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1390equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: compiled from: PathOperation.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getDifference-b3I0S0c, reason: not valid java name */
        public final int m1391getDifferenceb3I0S0c() {
            return PathOperation.Difference;
        }

        /* JADX INFO: renamed from: getIntersect-b3I0S0c, reason: not valid java name */
        public final int m1392getIntersectb3I0S0c() {
            return PathOperation.Intersect;
        }

        /* JADX INFO: renamed from: getUnion-b3I0S0c, reason: not valid java name */
        public final int m1394getUnionb3I0S0c() {
            return PathOperation.Union;
        }

        /* JADX INFO: renamed from: getReverseDifference-b3I0S0c, reason: not valid java name */
        public final int m1393getReverseDifferenceb3I0S0c() {
            return PathOperation.ReverseDifference;
        }
    }
}
