package androidx.compose.ui.node;

/* JADX INFO: compiled from: MyersDiff.kt */
/* JADX INFO: loaded from: classes.dex */
abstract class CenteredArray {
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int[] m1790constructorimpl(int[] iArr) {
        return iArr;
    }

    /* JADX INFO: renamed from: getMid-impl, reason: not valid java name */
    private static final int m1792getMidimpl(int[] iArr) {
        return iArr.length / 2;
    }

    /* JADX INFO: renamed from: get-impl, reason: not valid java name */
    public static final int m1791getimpl(int[] iArr, int i) {
        return iArr[i + m1792getMidimpl(iArr)];
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final void m1793setimpl(int[] iArr, int i, int i2) {
        iArr[i + m1792getMidimpl(iArr)] = i2;
    }
}
