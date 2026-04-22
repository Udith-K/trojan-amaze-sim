package androidx.compose.ui.graphics;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PathFillType.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PathFillType {
    public static final Companion Companion = new Companion(null);
    private static final int NonZero = m1384constructorimpl(0);
    private static final int EvenOdd = m1384constructorimpl(1);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1384constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1385equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1386hashCodeimpl(int i) {
        return i;
    }

    /* JADX INFO: compiled from: PathFillType.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getNonZero-Rg-k1Os, reason: not valid java name */
        public final int m1388getNonZeroRgk1Os() {
            return PathFillType.NonZero;
        }

        /* JADX INFO: renamed from: getEvenOdd-Rg-k1Os, reason: not valid java name */
        public final int m1387getEvenOddRgk1Os() {
            return PathFillType.EvenOdd;
        }
    }
}
