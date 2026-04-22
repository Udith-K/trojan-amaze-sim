package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;

/* JADX INFO: compiled from: Density.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Density extends FontScaling {
    float getDensity();

    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    int mo204roundToPx0680j_4(float f);

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    float mo206toDpu2uoSUM(float f);

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    float mo207toDpu2uoSUM(int i);

    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    long mo208toDpSizekrfVVM(long j);

    /* JADX INFO: renamed from: toPx--R2X_6o */
    float mo209toPxR2X_6o(long j);

    /* JADX INFO: renamed from: toPx-0680j_4 */
    float mo210toPx0680j_4(float f);

    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    long mo211toSizeXkaWNTQ(long j);

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    long mo213toSpkPz2Gy4(float f);

    /* JADX INFO: renamed from: androidx.compose.ui.unit.Density$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Density.kt */
    public abstract /* synthetic */ class CC {
        /* JADX INFO: renamed from: $default$toPx-0680j_4, reason: not valid java name */
        public static float m2411$default$toPx0680j_4(Density density, float f) {
            return f * density.getDensity();
        }

        /* JADX INFO: renamed from: $default$roundToPx-0680j_4, reason: not valid java name */
        public static int m2406$default$roundToPx0680j_4(Density density, float f) {
            float fMo210toPx0680j_4 = density.mo210toPx0680j_4(f);
            if (Float.isInfinite(fMo210toPx0680j_4)) {
                return Integer.MAX_VALUE;
            }
            return Math.round(fMo210toPx0680j_4);
        }

        /* JADX INFO: renamed from: $default$toPx--R2X_6o, reason: not valid java name */
        public static float m2410$default$toPxR2X_6o(Density density, long j) {
            if (!TextUnitType.m2503equalsimpl0(TextUnit.m2489getTypeUIouoOA(j), TextUnitType.Companion.m2508getSpUIouoOA())) {
                throw new IllegalStateException("Only Sp can convert to Px");
            }
            return density.mo210toPx0680j_4(density.mo205toDpGaN1DYA(j));
        }

        /* JADX INFO: renamed from: $default$toSize-XkaWNTQ, reason: not valid java name */
        public static long m2412$default$toSizeXkaWNTQ(Density density, long j) {
            if (j != 9205357640488583168L) {
                return SizeKt.Size(density.mo210toPx0680j_4(DpSize.m2443getWidthD9Ej5fM(j)), density.mo210toPx0680j_4(DpSize.m2442getHeightD9Ej5fM(j)));
            }
            return Size.Companion.m1201getUnspecifiedNHjbRc();
        }

        /* JADX INFO: renamed from: $default$toDpSize-k-rfVVM, reason: not valid java name */
        public static long m2409$default$toDpSizekrfVVM(Density density, long j) {
            if (j != 9205357640488583168L) {
                return DpKt.m2427DpSizeYgX7TsA(density.mo206toDpu2uoSUM(Size.m1196getWidthimpl(j)), density.mo206toDpu2uoSUM(Size.m1194getHeightimpl(j)));
            }
            return DpSize.Companion.m2447getUnspecifiedMYxV2XQ();
        }
    }
}
