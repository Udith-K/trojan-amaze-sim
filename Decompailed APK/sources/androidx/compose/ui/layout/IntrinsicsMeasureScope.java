package androidx.compose.ui.layout;

import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Layout.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IntrinsicsMeasureScope implements MeasureScope, IntrinsicMeasureScope {
    private final /* synthetic */ IntrinsicMeasureScope $$delegate_0;
    private final LayoutDirection layoutDirection;

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.$$delegate_0.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.$$delegate_0.getFontScale();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public boolean isLookingAhead() {
        return this.$$delegate_0.isLookingAhead();
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public /* synthetic */ MeasureResult layout(int i, int i2, Map map, Function1 function1) {
        return layout(i, i2, map, null, function1);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public int mo204roundToPx0680j_4(float f) {
        return this.$$delegate_0.mo204roundToPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public float mo205toDpGaN1DYA(long j) {
        return this.$$delegate_0.mo205toDpGaN1DYA(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo206toDpu2uoSUM(float f) {
        return this.$$delegate_0.mo206toDpu2uoSUM(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo207toDpu2uoSUM(int i) {
        return this.$$delegate_0.mo207toDpu2uoSUM(i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public long mo208toDpSizekrfVVM(long j) {
        return this.$$delegate_0.mo208toDpSizekrfVVM(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public float mo209toPxR2X_6o(long j) {
        return this.$$delegate_0.mo209toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public float mo210toPx0680j_4(float f) {
        return this.$$delegate_0.mo210toPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public long mo211toSizeXkaWNTQ(long j) {
        return this.$$delegate_0.mo211toSizeXkaWNTQ(j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public long mo212toSp0xMU5do(float f) {
        return this.$$delegate_0.mo212toSp0xMU5do(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public long mo213toSpkPz2Gy4(float f) {
        return this.$$delegate_0.mo213toSpkPz2Gy4(f);
    }

    public IntrinsicsMeasureScope(IntrinsicMeasureScope intrinsicMeasureScope, LayoutDirection layoutDirection) {
        this.layoutDirection = layoutDirection;
        this.$$delegate_0 = intrinsicMeasureScope;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public MeasureResult layout(int i, int i2, final Map map, final Function1 function1, Function1 function12) {
        boolean z = false;
        final int iCoerceAtLeast = RangesKt.coerceAtLeast(i, 0);
        final int iCoerceAtLeast2 = RangesKt.coerceAtLeast(i2, 0);
        if ((iCoerceAtLeast & (-16777216)) == 0 && ((-16777216) & iCoerceAtLeast2) == 0) {
            z = true;
        }
        if (!z) {
            InlineClassHelperKt.throwIllegalStateException("Size(" + iCoerceAtLeast + " x " + iCoerceAtLeast2 + ") is out of range. Each dimension must be between 0 and 16777215.");
        }
        return new MeasureResult() { // from class: androidx.compose.ui.layout.IntrinsicsMeasureScope.layout.1
            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getWidth() {
                return iCoerceAtLeast;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getHeight() {
                return iCoerceAtLeast2;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Map getAlignmentLines() {
                return map;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Function1 getRulers() {
                return function1;
            }
        };
    }
}
