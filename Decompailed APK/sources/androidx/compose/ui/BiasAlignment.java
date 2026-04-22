package androidx.compose.ui;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: Alignment.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BiasAlignment implements Alignment {
    private final float horizontalBias;
    private final float verticalBias;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BiasAlignment)) {
            return false;
        }
        BiasAlignment biasAlignment = (BiasAlignment) obj;
        return Float.compare(this.horizontalBias, biasAlignment.horizontalBias) == 0 && Float.compare(this.verticalBias, biasAlignment.verticalBias) == 0;
    }

    public int hashCode() {
        return (Float.floatToIntBits(this.horizontalBias) * 31) + Float.floatToIntBits(this.verticalBias);
    }

    public String toString() {
        return "BiasAlignment(horizontalBias=" + this.horizontalBias + ", verticalBias=" + this.verticalBias + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public BiasAlignment(float f, float f2) {
        this.horizontalBias = f;
        this.verticalBias = f2;
    }

    @Override // androidx.compose.ui.Alignment
    /* JADX INFO: renamed from: align-KFBX0sM */
    public long mo1066alignKFBX0sM(long j, long j2, LayoutDirection layoutDirection) {
        float f;
        float fM2476getWidthimpl = (IntSize.m2476getWidthimpl(j2) - IntSize.m2476getWidthimpl(j)) / 2.0f;
        float fM2475getHeightimpl = (IntSize.m2475getHeightimpl(j2) - IntSize.m2475getHeightimpl(j)) / 2.0f;
        if (layoutDirection == LayoutDirection.Ltr) {
            f = this.horizontalBias;
        } else {
            f = (-1) * this.horizontalBias;
        }
        float f2 = 1;
        return IntOffsetKt.IntOffset(Math.round(fM2476getWidthimpl * (f + f2)), Math.round(fM2475getHeightimpl * (f2 + this.verticalBias)));
    }

    /* JADX INFO: compiled from: Alignment.kt */
    public static final class Horizontal implements Alignment.Horizontal {
        private final float bias;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Horizontal) && Float.compare(this.bias, ((Horizontal) obj).bias) == 0;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.bias);
        }

        public String toString() {
            return "Horizontal(bias=" + this.bias + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public Horizontal(float f) {
            this.bias = f;
        }

        @Override // androidx.compose.ui.Alignment.Horizontal
        public int align(int i, int i2, LayoutDirection layoutDirection) {
            return Math.round(((i2 - i) / 2.0f) * (1 + (layoutDirection == LayoutDirection.Ltr ? this.bias : (-1) * this.bias)));
        }
    }

    /* JADX INFO: compiled from: Alignment.kt */
    public static final class Vertical implements Alignment.Vertical {
        private final float bias;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Vertical) && Float.compare(this.bias, ((Vertical) obj).bias) == 0;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.bias);
        }

        public String toString() {
            return "Vertical(bias=" + this.bias + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public Vertical(float f) {
            this.bias = f;
        }

        @Override // androidx.compose.ui.Alignment.Vertical
        public int align(int i, int i2) {
            return Math.round(((i2 - i) / 2.0f) * (1 + this.bias));
        }
    }
}
