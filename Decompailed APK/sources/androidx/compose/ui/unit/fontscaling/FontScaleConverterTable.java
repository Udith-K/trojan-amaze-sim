package androidx.compose.ui.unit.fontscaling;

import ch.qos.logback.core.CoreConstants;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FontScaleConverterTable.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FontScaleConverterTable implements FontScaleConverter {
    private final float[] mFromSpValues;
    private final float[] mToDpValues;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public FontScaleConverterTable(float[] fArr, float[] fArr2) {
        if (fArr.length != fArr2.length || fArr.length == 0) {
            throw new IllegalArgumentException("Array lengths must match and be nonzero");
        }
        this.mFromSpValues = fArr;
        this.mToDpValues = fArr2;
    }

    @Override // androidx.compose.ui.unit.fontscaling.FontScaleConverter
    public float convertDpToSp(float f) {
        return Companion.lookupAndInterpolate(f, this.mToDpValues, this.mFromSpValues);
    }

    @Override // androidx.compose.ui.unit.fontscaling.FontScaleConverter
    public float convertSpToDp(float f) {
        return Companion.lookupAndInterpolate(f, this.mFromSpValues, this.mToDpValues);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FontScaleConverterTable)) {
            return false;
        }
        FontScaleConverterTable fontScaleConverterTable = (FontScaleConverterTable) obj;
        return Arrays.equals(this.mFromSpValues, fontScaleConverterTable.mFromSpValues) && Arrays.equals(this.mToDpValues, fontScaleConverterTable.mToDpValues);
    }

    public int hashCode() {
        return (Arrays.hashCode(this.mFromSpValues) * 31) + Arrays.hashCode(this.mToDpValues);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontScaleConverter{fromSpValues=");
        String string = Arrays.toString(this.mFromSpValues);
        Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
        sb.append(string);
        sb.append(", toDpValues=");
        String string2 = Arrays.toString(this.mToDpValues);
        Intrinsics.checkNotNullExpressionValue(string2, "toString(this)");
        sb.append(string2);
        sb.append(CoreConstants.CURLY_RIGHT);
        return sb.toString();
    }

    /* JADX INFO: compiled from: FontScaleConverterTable.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float lookupAndInterpolate(float f, float[] fArr, float[] fArr2) {
            float f2;
            float f3;
            float f4;
            float fConstrainedMap;
            float fAbs = Math.abs(f);
            float fSignum = Math.signum(f);
            int iBinarySearch = Arrays.binarySearch(fArr, fAbs);
            if (iBinarySearch >= 0) {
                fConstrainedMap = fArr2[iBinarySearch];
            } else {
                int i = -(iBinarySearch + 1);
                int i2 = i - 1;
                float f5 = 0.0f;
                if (i2 >= fArr.length - 1) {
                    float f6 = fArr[fArr.length - 1];
                    float f7 = fArr2[fArr.length - 1];
                    if (f6 == 0.0f) {
                        return 0.0f;
                    }
                    return f * (f7 / f6);
                }
                if (i2 == -1) {
                    float f8 = fArr[0];
                    f4 = fArr2[0];
                    f3 = f8;
                    f2 = 0.0f;
                } else {
                    float f9 = fArr[i2];
                    float f10 = fArr[i];
                    f2 = fArr2[i2];
                    f5 = f9;
                    f3 = f10;
                    f4 = fArr2[i];
                }
                fConstrainedMap = MathUtils.INSTANCE.constrainedMap(f2, f4, f5, f3, fAbs);
            }
            return fSignum * fConstrainedMap;
        }
    }
}
