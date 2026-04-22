package androidx.compose.ui.unit;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.fontscaling.FontScaleConverter;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidDensity.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class DensityWithConverter implements Density {
    private final FontScaleConverter converter;
    private final float density;
    private final float fontScale;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DensityWithConverter)) {
            return false;
        }
        DensityWithConverter densityWithConverter = (DensityWithConverter) obj;
        return Float.compare(this.density, densityWithConverter.density) == 0 && Float.compare(this.fontScale, densityWithConverter.fontScale) == 0 && Intrinsics.areEqual(this.converter, densityWithConverter.converter);
    }

    public int hashCode() {
        return (((Float.floatToIntBits(this.density) * 31) + Float.floatToIntBits(this.fontScale)) * 31) + this.converter.hashCode();
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public /* synthetic */ int mo204roundToPx0680j_4(float f) {
        return Density.CC.m2406$default$roundToPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo206toDpu2uoSUM(float f) {
        return Dp.m2416constructorimpl(f / getDensity());
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo207toDpu2uoSUM(int i) {
        return Dp.m2416constructorimpl(i / getDensity());
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public /* synthetic */ long mo208toDpSizekrfVVM(long j) {
        return Density.CC.m2409$default$toDpSizekrfVVM(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public /* synthetic */ float mo209toPxR2X_6o(long j) {
        return Density.CC.m2410$default$toPxR2X_6o(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public /* synthetic */ float mo210toPx0680j_4(float f) {
        return Density.CC.m2411$default$toPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public /* synthetic */ long mo211toSizeXkaWNTQ(long j) {
        return Density.CC.m2412$default$toSizeXkaWNTQ(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public /* synthetic */ long mo213toSpkPz2Gy4(float f) {
        return mo212toSp0xMU5do(mo206toDpu2uoSUM(f));
    }

    public String toString() {
        return "DensityWithConverter(density=" + this.density + ", fontScale=" + this.fontScale + ", converter=" + this.converter + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public DensityWithConverter(float f, float f2, FontScaleConverter fontScaleConverter) {
        this.density = f;
        this.fontScale = f2;
        this.converter = fontScaleConverter;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.density;
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.fontScale;
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public long mo212toSp0xMU5do(float f) {
        return TextUnitKt.getSp(this.converter.convertDpToSp(f));
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public float mo205toDpGaN1DYA(long j) {
        if (!TextUnitType.m2503equalsimpl0(TextUnit.m2489getTypeUIouoOA(j), TextUnitType.Companion.m2508getSpUIouoOA())) {
            throw new IllegalStateException("Only Sp can convert to Px");
        }
        return Dp.m2416constructorimpl(this.converter.convertSpToDp(TextUnit.m2490getValueimpl(j)));
    }
}
