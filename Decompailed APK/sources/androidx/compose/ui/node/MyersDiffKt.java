package androidx.compose.ui.node;

/* JADX INFO: compiled from: MyersDiff.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class MyersDiffKt {
    private static final IntStack calculateDiff(int i, int i2, DiffCallback diffCallback) {
        int i3 = ((i + i2) + 1) / 2;
        IntStack intStack = new IntStack(i3 * 3);
        IntStack intStack2 = new IntStack(i3 * 4);
        intStack2.pushRange(0, i, 0, i2);
        int i4 = (i3 * 2) + 1;
        int[] iArrM1790constructorimpl = CenteredArray.m1790constructorimpl(new int[i4]);
        int[] iArrM1790constructorimpl2 = CenteredArray.m1790constructorimpl(new int[i4]);
        int[] iArrM1907constructorimpl = Snake.m1907constructorimpl(new int[5]);
        while (intStack2.isNotEmpty()) {
            int iPop = intStack2.pop();
            int iPop2 = intStack2.pop();
            int iPop3 = intStack2.pop();
            int iPop4 = intStack2.pop();
            int[] iArr = iArrM1790constructorimpl;
            int[] iArr2 = iArrM1790constructorimpl2;
            if (m1856midPointq5eDKzI(iPop4, iPop3, iPop2, iPop, diffCallback, iArrM1790constructorimpl, iArrM1790constructorimpl2, iArrM1907constructorimpl)) {
                if (Snake.m1908getDiagonalSizeimpl(iArrM1907constructorimpl) > 0) {
                    Snake.m1906addDiagonalToStackimpl(iArrM1907constructorimpl, intStack);
                }
                intStack2.pushRange(iPop4, Snake.m1913getStartXimpl(iArrM1907constructorimpl), iPop2, Snake.m1914getStartYimpl(iArrM1907constructorimpl));
                intStack2.pushRange(Snake.m1909getEndXimpl(iArrM1907constructorimpl), iPop3, Snake.m1910getEndYimpl(iArrM1907constructorimpl), iPop);
            }
            iArrM1790constructorimpl = iArr;
            iArrM1790constructorimpl2 = iArr2;
        }
        intStack.sortDiagonals();
        intStack.pushDiagonal(i, i2, 0);
        return intStack;
    }

    private static final void applyDiff(IntStack intStack, DiffCallback diffCallback) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < intStack.getSize()) {
            int i4 = i + 2;
            int i5 = intStack.get(i) - intStack.get(i4);
            int i6 = intStack.get(i + 1) - intStack.get(i4);
            int i7 = intStack.get(i4);
            i += 3;
            while (i2 < i5) {
                diffCallback.remove(i3, i2);
                i2++;
            }
            while (i3 < i6) {
                diffCallback.insert(i3);
                i3++;
            }
            while (true) {
                int i8 = i7 - 1;
                if (i7 > 0) {
                    diffCallback.same(i2, i3);
                    i2++;
                    i3++;
                    i7 = i8;
                }
            }
        }
    }

    public static final void executeDiff(int i, int i2, DiffCallback diffCallback) {
        applyDiff(calculateDiff(i, i2, diffCallback), diffCallback);
    }

    /* JADX INFO: renamed from: midPoint-q5eDKzI, reason: not valid java name */
    private static final boolean m1856midPointq5eDKzI(int i, int i2, int i3, int i4, DiffCallback diffCallback, int[] iArr, int[] iArr2, int[] iArr3) {
        int i5 = i2 - i;
        int i6 = i4 - i3;
        if (i5 >= 1 && i6 >= 1) {
            int i7 = ((i5 + i6) + 1) / 2;
            CenteredArray.m1793setimpl(iArr, 1, i);
            CenteredArray.m1793setimpl(iArr2, 1, i2);
            int i8 = 0;
            while (i8 < i7) {
                int i9 = i8;
                if (m1855forward4l5_RBY(i, i2, i3, i4, diffCallback, iArr, iArr2, i8, iArr3) || m1854backward4l5_RBY(i, i2, i3, i4, diffCallback, iArr, iArr2, i9, iArr3)) {
                    return true;
                }
                i8 = i9 + 1;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: forward-4l5_RBY, reason: not valid java name */
    private static final boolean m1855forward4l5_RBY(int i, int i2, int i3, int i4, DiffCallback diffCallback, int[] iArr, int[] iArr2, int i5, int[] iArr3) {
        int iM1791getimpl;
        int i6;
        int i7;
        int i8 = (i2 - i) - (i4 - i3);
        boolean z = Math.abs(i8) % 2 == 1;
        int i9 = -i5;
        for (int i10 = i9; i10 <= i5; i10 += 2) {
            if (i10 == i9 || (i10 != i5 && CenteredArray.m1791getimpl(iArr, i10 + 1) > CenteredArray.m1791getimpl(iArr, i10 - 1))) {
                iM1791getimpl = CenteredArray.m1791getimpl(iArr, i10 + 1);
                i6 = iM1791getimpl;
            } else {
                iM1791getimpl = CenteredArray.m1791getimpl(iArr, i10 - 1);
                i6 = iM1791getimpl + 1;
            }
            int i11 = (i3 + (i6 - i)) - i10;
            int i12 = (i5 == 0 || i6 != iM1791getimpl) ? i11 : i11 - 1;
            while (i6 < i2 && i11 < i4) {
                if (!diffCallback.areItemsTheSame(i6, i11)) {
                    break;
                }
                i6++;
                i11++;
            }
            CenteredArray.m1793setimpl(iArr, i10, i6);
            if (z && (i7 = i8 - i10) >= i9 + 1 && i7 <= i5 - 1) {
                if (CenteredArray.m1791getimpl(iArr2, i7) <= i6) {
                    fillSnake(iM1791getimpl, i12, i6, i11, false, iArr3);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: backward-4l5_RBY, reason: not valid java name */
    private static final boolean m1854backward4l5_RBY(int i, int i2, int i3, int i4, DiffCallback diffCallback, int[] iArr, int[] iArr2, int i5, int[] iArr3) {
        int iM1791getimpl;
        int i6;
        int i7;
        int i8 = (i2 - i) - (i4 - i3);
        boolean z = i8 % 2 == 0;
        int i9 = -i5;
        for (int i10 = i9; i10 <= i5; i10 += 2) {
            if (i10 == i9 || (i10 != i5 && CenteredArray.m1791getimpl(iArr2, i10 + 1) < CenteredArray.m1791getimpl(iArr2, i10 - 1))) {
                iM1791getimpl = CenteredArray.m1791getimpl(iArr2, i10 + 1);
                i6 = iM1791getimpl;
            } else {
                iM1791getimpl = CenteredArray.m1791getimpl(iArr2, i10 - 1);
                i6 = iM1791getimpl - 1;
            }
            int i11 = i4 - ((i2 - i6) - i10);
            int i12 = (i5 == 0 || i6 != iM1791getimpl) ? i11 : i11 + 1;
            while (i6 > i && i11 > i3) {
                if (!diffCallback.areItemsTheSame(i6 - 1, i11 - 1)) {
                    break;
                }
                i6--;
                i11--;
            }
            CenteredArray.m1793setimpl(iArr2, i10, i6);
            if (z && (i7 = i8 - i10) >= i9 && i7 <= i5) {
                if (CenteredArray.m1791getimpl(iArr, i7) >= i6) {
                    fillSnake(i6, i11, iM1791getimpl, i12, true, iArr3);
                    return true;
                }
            }
        }
        return false;
    }

    public static final void fillSnake(int i, int i2, int i3, int i4, boolean z, int[] iArr) {
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = i3;
        iArr[3] = i4;
        iArr[4] = z ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void swap(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }
}
