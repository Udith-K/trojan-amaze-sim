package androidx.compose.ui.node;

/* JADX INFO: compiled from: MyersDiff.kt */
/* JADX INFO: loaded from: classes.dex */
abstract class Snake {
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int[] m1907constructorimpl(int[] iArr) {
        return iArr;
    }

    /* JADX INFO: renamed from: getStartX-impl, reason: not valid java name */
    public static final int m1913getStartXimpl(int[] iArr) {
        return iArr[0];
    }

    /* JADX INFO: renamed from: getStartY-impl, reason: not valid java name */
    public static final int m1914getStartYimpl(int[] iArr) {
        return iArr[1];
    }

    /* JADX INFO: renamed from: getEndX-impl, reason: not valid java name */
    public static final int m1909getEndXimpl(int[] iArr) {
        return iArr[2];
    }

    /* JADX INFO: renamed from: getEndY-impl, reason: not valid java name */
    public static final int m1910getEndYimpl(int[] iArr) {
        return iArr[3];
    }

    /* JADX INFO: renamed from: getReverse-impl, reason: not valid java name */
    public static final boolean m1912getReverseimpl(int[] iArr) {
        return iArr[4] != 0;
    }

    /* JADX INFO: renamed from: getDiagonalSize-impl, reason: not valid java name */
    public static final int m1908getDiagonalSizeimpl(int[] iArr) {
        return Math.min(m1909getEndXimpl(iArr) - m1913getStartXimpl(iArr), m1910getEndYimpl(iArr) - m1914getStartYimpl(iArr));
    }

    /* JADX INFO: renamed from: getHasAdditionOrRemoval-impl, reason: not valid java name */
    private static final boolean m1911getHasAdditionOrRemovalimpl(int[] iArr) {
        return m1910getEndYimpl(iArr) - m1914getStartYimpl(iArr) != m1909getEndXimpl(iArr) - m1913getStartXimpl(iArr);
    }

    /* JADX INFO: renamed from: isAddition-impl, reason: not valid java name */
    private static final boolean m1915isAdditionimpl(int[] iArr) {
        return m1910getEndYimpl(iArr) - m1914getStartYimpl(iArr) > m1909getEndXimpl(iArr) - m1913getStartXimpl(iArr);
    }

    /* JADX INFO: renamed from: addDiagonalToStack-impl, reason: not valid java name */
    public static final void m1906addDiagonalToStackimpl(int[] iArr, IntStack intStack) {
        if (m1911getHasAdditionOrRemovalimpl(iArr)) {
            if (m1912getReverseimpl(iArr)) {
                intStack.pushDiagonal(m1913getStartXimpl(iArr), m1914getStartYimpl(iArr), m1908getDiagonalSizeimpl(iArr));
                return;
            } else if (m1915isAdditionimpl(iArr)) {
                intStack.pushDiagonal(m1913getStartXimpl(iArr), m1914getStartYimpl(iArr) + 1, m1908getDiagonalSizeimpl(iArr));
                return;
            } else {
                intStack.pushDiagonal(m1913getStartXimpl(iArr) + 1, m1914getStartYimpl(iArr), m1908getDiagonalSizeimpl(iArr));
                return;
            }
        }
        intStack.pushDiagonal(m1913getStartXimpl(iArr), m1914getStartYimpl(iArr), m1909getEndXimpl(iArr) - m1913getStartXimpl(iArr));
    }
}
