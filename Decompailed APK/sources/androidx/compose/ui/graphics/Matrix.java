package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Matrix.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Matrix {
    public static final Companion Companion = new Companion(null);
    private final float[] values;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Matrix m1354boximpl(float[] fArr) {
        return new Matrix(fArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static float[] m1355constructorimpl(float[] fArr) {
        return fArr;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1357equalsimpl(float[] fArr, Object obj) {
        return (obj instanceof Matrix) && Intrinsics.areEqual(fArr, ((Matrix) obj).m1371unboximpl());
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1358hashCodeimpl(float[] fArr) {
        return Arrays.hashCode(fArr);
    }

    public boolean equals(Object obj) {
        return m1357equalsimpl(this.values, obj);
    }

    public int hashCode() {
        return m1358hashCodeimpl(this.values);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ float[] m1371unboximpl() {
        return this.values;
    }

    private /* synthetic */ Matrix(float[] fArr) {
        this.values = fArr;
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ float[] m1356constructorimpl$default(float[] fArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            fArr = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        }
        return m1355constructorimpl(fArr);
    }

    /* JADX INFO: renamed from: scale-impl, reason: not valid java name */
    public static final void m1365scaleimpl(float[] fArr, float f, float f2, float f3) {
        fArr[0] = fArr[0] * f;
        fArr[1] = fArr[1] * f;
        fArr[2] = fArr[2] * f;
        fArr[3] = fArr[3] * f;
        fArr[4] = fArr[4] * f2;
        fArr[5] = fArr[5] * f2;
        fArr[6] = fArr[6] * f2;
        fArr[7] = fArr[7] * f2;
        fArr[8] = fArr[8] * f3;
        fArr[9] = fArr[9] * f3;
        fArr[10] = fArr[10] * f3;
        fArr[11] = fArr[11] * f3;
    }

    /* JADX INFO: renamed from: translate-impl, reason: not valid java name */
    public static final void m1369translateimpl(float[] fArr, float f, float f2, float f3) {
        float f4 = (fArr[0] * f) + (fArr[4] * f2) + (fArr[8] * f3) + fArr[12];
        float f5 = (fArr[1] * f) + (fArr[5] * f2) + (fArr[9] * f3) + fArr[13];
        float f6 = (fArr[2] * f) + (fArr[6] * f2) + (fArr[10] * f3) + fArr[14];
        float f7 = (fArr[3] * f) + (fArr[7] * f2) + (fArr[11] * f3) + fArr[15];
        fArr[12] = f4;
        fArr[13] = f5;
        fArr[14] = f6;
        fArr[15] = f7;
    }

    /* JADX INFO: renamed from: reset-impl, reason: not valid java name */
    public static final void m1361resetimpl(float[] fArr) {
        int i = 0;
        while (i < 4) {
            int i2 = 0;
            while (i2 < 4) {
                fArr[(i2 * 4) + i] = i == i2 ? 1.0f : 0.0f;
                i2++;
            }
            i++;
        }
    }

    /* JADX INFO: renamed from: map-MK-Hz9U, reason: not valid java name */
    public static final long m1359mapMKHz9U(float[] fArr, long j) {
        float fM1159getXimpl = Offset.m1159getXimpl(j);
        float fM1160getYimpl = Offset.m1160getYimpl(j);
        float f = 1 / (((fArr[3] * fM1159getXimpl) + (fArr[7] * fM1160getYimpl)) + fArr[15]);
        if (Float.isInfinite(f) || Float.isNaN(f)) {
            f = 0.0f;
        }
        return OffsetKt.Offset(((fArr[0] * fM1159getXimpl) + (fArr[4] * fM1160getYimpl) + fArr[12]) * f, f * ((fArr[1] * fM1159getXimpl) + (fArr[5] * fM1160getYimpl) + fArr[13]));
    }

    /* JADX INFO: renamed from: map-impl, reason: not valid java name */
    public static final void m1360mapimpl(float[] fArr, MutableRect mutableRect) {
        long jM1359mapMKHz9U = m1359mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getLeft(), mutableRect.getTop()));
        long jM1359mapMKHz9U2 = m1359mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getLeft(), mutableRect.getBottom()));
        long jM1359mapMKHz9U3 = m1359mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getRight(), mutableRect.getTop()));
        long jM1359mapMKHz9U4 = m1359mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getRight(), mutableRect.getBottom()));
        mutableRect.setLeft(Math.min(Math.min(Offset.m1159getXimpl(jM1359mapMKHz9U), Offset.m1159getXimpl(jM1359mapMKHz9U2)), Math.min(Offset.m1159getXimpl(jM1359mapMKHz9U3), Offset.m1159getXimpl(jM1359mapMKHz9U4))));
        mutableRect.setTop(Math.min(Math.min(Offset.m1160getYimpl(jM1359mapMKHz9U), Offset.m1160getYimpl(jM1359mapMKHz9U2)), Math.min(Offset.m1160getYimpl(jM1359mapMKHz9U3), Offset.m1160getYimpl(jM1359mapMKHz9U4))));
        mutableRect.setRight(Math.max(Math.max(Offset.m1159getXimpl(jM1359mapMKHz9U), Offset.m1159getXimpl(jM1359mapMKHz9U2)), Math.max(Offset.m1159getXimpl(jM1359mapMKHz9U3), Offset.m1159getXimpl(jM1359mapMKHz9U4))));
        mutableRect.setBottom(Math.max(Math.max(Offset.m1160getYimpl(jM1359mapMKHz9U), Offset.m1160getYimpl(jM1359mapMKHz9U2)), Math.max(Offset.m1160getYimpl(jM1359mapMKHz9U3), Offset.m1160getYimpl(jM1359mapMKHz9U4))));
    }

    /* JADX INFO: renamed from: timesAssign-58bKbWc, reason: not valid java name */
    public static final void m1367timesAssign58bKbWc(float[] fArr, float[] fArr2) {
        float fM1373dotp89u6pk = MatrixKt.m1373dotp89u6pk(fArr, 0, fArr2, 0);
        float fM1373dotp89u6pk2 = MatrixKt.m1373dotp89u6pk(fArr, 0, fArr2, 1);
        float fM1373dotp89u6pk3 = MatrixKt.m1373dotp89u6pk(fArr, 0, fArr2, 2);
        float fM1373dotp89u6pk4 = MatrixKt.m1373dotp89u6pk(fArr, 0, fArr2, 3);
        float fM1373dotp89u6pk5 = MatrixKt.m1373dotp89u6pk(fArr, 1, fArr2, 0);
        float fM1373dotp89u6pk6 = MatrixKt.m1373dotp89u6pk(fArr, 1, fArr2, 1);
        float fM1373dotp89u6pk7 = MatrixKt.m1373dotp89u6pk(fArr, 1, fArr2, 2);
        float fM1373dotp89u6pk8 = MatrixKt.m1373dotp89u6pk(fArr, 1, fArr2, 3);
        float fM1373dotp89u6pk9 = MatrixKt.m1373dotp89u6pk(fArr, 2, fArr2, 0);
        float fM1373dotp89u6pk10 = MatrixKt.m1373dotp89u6pk(fArr, 2, fArr2, 1);
        float fM1373dotp89u6pk11 = MatrixKt.m1373dotp89u6pk(fArr, 2, fArr2, 2);
        float fM1373dotp89u6pk12 = MatrixKt.m1373dotp89u6pk(fArr, 2, fArr2, 3);
        float fM1373dotp89u6pk13 = MatrixKt.m1373dotp89u6pk(fArr, 3, fArr2, 0);
        float fM1373dotp89u6pk14 = MatrixKt.m1373dotp89u6pk(fArr, 3, fArr2, 1);
        float fM1373dotp89u6pk15 = MatrixKt.m1373dotp89u6pk(fArr, 3, fArr2, 2);
        float fM1373dotp89u6pk16 = MatrixKt.m1373dotp89u6pk(fArr, 3, fArr2, 3);
        fArr[0] = fM1373dotp89u6pk;
        fArr[1] = fM1373dotp89u6pk2;
        fArr[2] = fM1373dotp89u6pk3;
        fArr[3] = fM1373dotp89u6pk4;
        fArr[4] = fM1373dotp89u6pk5;
        fArr[5] = fM1373dotp89u6pk6;
        fArr[6] = fM1373dotp89u6pk7;
        fArr[7] = fM1373dotp89u6pk8;
        fArr[8] = fM1373dotp89u6pk9;
        fArr[9] = fM1373dotp89u6pk10;
        fArr[10] = fM1373dotp89u6pk11;
        fArr[11] = fM1373dotp89u6pk12;
        fArr[12] = fM1373dotp89u6pk13;
        fArr[13] = fM1373dotp89u6pk14;
        fArr[14] = fM1373dotp89u6pk15;
        fArr[15] = fM1373dotp89u6pk16;
    }

    public String toString() {
        return m1368toStringimpl(this.values);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1368toStringimpl(float[] fArr) {
        return StringsKt.trimIndent("\n            |" + fArr[0] + ' ' + fArr[1] + ' ' + fArr[2] + ' ' + fArr[3] + "|\n            |" + fArr[4] + ' ' + fArr[5] + ' ' + fArr[6] + ' ' + fArr[7] + "|\n            |" + fArr[8] + ' ' + fArr[9] + ' ' + fArr[10] + ' ' + fArr[11] + "|\n            |" + fArr[12] + ' ' + fArr[13] + ' ' + fArr[14] + ' ' + fArr[15] + "|\n        ");
    }

    /* JADX INFO: renamed from: rotateX-impl, reason: not valid java name */
    public static final void m1362rotateXimpl(float[] fArr, float f) {
        double d = (((double) f) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[5];
        float f5 = fArr[6];
        float f6 = fArr[9];
        float f7 = fArr[10];
        float f8 = fArr[13];
        float f9 = fArr[14];
        fArr[1] = (f2 * fCos) - (f3 * fSin);
        fArr[2] = (f2 * fSin) + (f3 * fCos);
        fArr[5] = (f4 * fCos) - (f5 * fSin);
        fArr[6] = (f4 * fSin) + (f5 * fCos);
        fArr[9] = (f6 * fCos) - (f7 * fSin);
        fArr[10] = (f6 * fSin) + (f7 * fCos);
        fArr[13] = (f8 * fCos) - (f9 * fSin);
        fArr[14] = (f8 * fSin) + (f9 * fCos);
    }

    /* JADX INFO: renamed from: rotateY-impl, reason: not valid java name */
    public static final void m1363rotateYimpl(float[] fArr, float f) {
        double d = (((double) f) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        float f2 = fArr[0];
        float f3 = fArr[2];
        float f4 = fArr[4];
        float f5 = fArr[6];
        float f6 = fArr[8];
        float f7 = fArr[10];
        float f8 = fArr[12];
        float f9 = fArr[14];
        fArr[0] = (f2 * fCos) + (f3 * fSin);
        fArr[2] = ((-f2) * fSin) + (f3 * fCos);
        fArr[4] = (f4 * fCos) + (f5 * fSin);
        fArr[6] = ((-f4) * fSin) + (f5 * fCos);
        fArr[8] = (f6 * fCos) + (f7 * fSin);
        fArr[10] = ((-f6) * fSin) + (f7 * fCos);
        fArr[12] = (f8 * fCos) + (f9 * fSin);
        fArr[14] = ((-f8) * fSin) + (f9 * fCos);
    }

    /* JADX INFO: renamed from: rotateZ-impl, reason: not valid java name */
    public static final void m1364rotateZimpl(float[] fArr, float f) {
        double d = (((double) f) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        float f2 = fArr[0];
        float f3 = fArr[4];
        float f4 = (fCos * f2) + (fSin * f3);
        float f5 = -fSin;
        float f6 = fArr[1];
        float f7 = fArr[5];
        float f8 = (fCos * f6) + (fSin * f7);
        float f9 = fArr[2];
        float f10 = fArr[6];
        float f11 = (fCos * f9) + (fSin * f10);
        float f12 = fArr[3];
        float f13 = fArr[7];
        fArr[0] = f4;
        fArr[1] = f8;
        fArr[2] = f11;
        fArr[3] = (fCos * f12) + (fSin * f13);
        fArr[4] = (f2 * f5) + (f3 * fCos);
        fArr[5] = (f6 * f5) + (f7 * fCos);
        fArr[6] = (f9 * f5) + (f10 * fCos);
        fArr[7] = (f5 * f12) + (fCos * f13);
    }

    /* JADX INFO: renamed from: scale-impl$default, reason: not valid java name */
    public static /* synthetic */ void m1366scaleimpl$default(float[] fArr, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        if ((i & 2) != 0) {
            f2 = 1.0f;
        }
        if ((i & 4) != 0) {
            f3 = 1.0f;
        }
        m1365scaleimpl(fArr, f, f2, f3);
    }

    /* JADX INFO: renamed from: translate-impl$default, reason: not valid java name */
    public static /* synthetic */ void m1370translateimpl$default(float[] fArr, float f, float f2, float f3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        if ((i & 4) != 0) {
            f3 = 0.0f;
        }
        m1369translateimpl(fArr, f, f2, f3);
    }

    /* JADX INFO: compiled from: Matrix.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
