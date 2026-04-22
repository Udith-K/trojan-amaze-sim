package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import ch.qos.logback.core.CoreConstants;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Column.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ColumnMeasurePolicy implements MeasurePolicy, RowColumnMeasurePolicy {
    private final Alignment.Horizontal horizontalAlignment;
    private final Arrangement.Vertical verticalArrangement;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ColumnMeasurePolicy)) {
            return false;
        }
        ColumnMeasurePolicy columnMeasurePolicy = (ColumnMeasurePolicy) obj;
        return Intrinsics.areEqual(this.verticalArrangement, columnMeasurePolicy.verticalArrangement) && Intrinsics.areEqual(this.horizontalAlignment, columnMeasurePolicy.horizontalAlignment);
    }

    public int hashCode() {
        return (this.verticalArrangement.hashCode() * 31) + this.horizontalAlignment.hashCode();
    }

    public String toString() {
        return "ColumnMeasurePolicy(verticalArrangement=" + this.verticalArrangement + ", horizontalAlignment=" + this.horizontalAlignment + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public ColumnMeasurePolicy(Arrangement.Vertical vertical, Alignment.Horizontal horizontal) {
        this.verticalArrangement = vertical;
        this.horizontalAlignment = horizontal;
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public int mainAxisSize(Placeable placeable) {
        return placeable.getHeight();
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public int crossAxisSize(Placeable placeable) {
        return placeable.getWidth();
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public void populateMainAxisPositions(int i, int[] iArr, int[] iArr2, MeasureScope measureScope) {
        this.verticalArrangement.arrange(measureScope, i, iArr, iArr2);
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    public MeasureResult placeHelper(final Placeable[] placeableArr, final MeasureScope measureScope, final int i, final int[] iArr, int i2, final int i3, int[] iArr2, int i4, int i5, int i6) {
        return MeasureScope.CC.layout$default(measureScope, i3, i2, null, new Function1() { // from class: androidx.compose.foundation.layout.ColumnMeasurePolicy$placeHelper$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                Placeable[] placeableArr2 = placeableArr;
                ColumnMeasurePolicy columnMeasurePolicy = this;
                int i7 = i3;
                int i8 = i;
                MeasureScope measureScope2 = measureScope;
                int[] iArr3 = iArr;
                int length = placeableArr2.length;
                int i9 = 0;
                int i10 = 0;
                while (i9 < length) {
                    Placeable placeable = placeableArr2[i9];
                    Intrinsics.checkNotNull(placeable);
                    Placeable.PlacementScope.place$default(placementScope, placeable, columnMeasurePolicy.getCrossAxisPosition(placeable, RowColumnImplKt.getRowColumnParentData(placeable), i7, i8, measureScope2.getLayoutDirection()), iArr3[i10], 0.0f, 4, null);
                    i9++;
                    i10++;
                }
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCrossAxisPosition(Placeable placeable, RowColumnParentData rowColumnParentData, int i, int i2, LayoutDirection layoutDirection) {
        CrossAxisAlignment crossAxisAlignment = rowColumnParentData != null ? rowColumnParentData.getCrossAxisAlignment() : null;
        if (crossAxisAlignment != null) {
            return crossAxisAlignment.align$foundation_layout_release(i - placeable.getWidth(), layoutDirection, placeable, i2);
        }
        return this.horizontalAlignment.align(0, i - placeable.getWidth(), layoutDirection);
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    /* JADX INFO: renamed from: createConstraints-xF2OJ5Q, reason: not valid java name */
    public long mo250createConstraintsxF2OJ5Q(int i, int i2, int i3, int i4, boolean z) {
        return ColumnKt.createColumnConstraints(z, i, i2, i3, i4);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo21measure3p2s80s(MeasureScope measureScope, List list, long j) {
        return RowColumnMeasurePolicyKt.measure(this, Constraints.m2390getMinHeightimpl(j), Constraints.m2391getMinWidthimpl(j), Constraints.m2388getMaxHeightimpl(j), Constraints.m2389getMaxWidthimpl(j), measureScope.mo204roundToPx0680j_4(this.verticalArrangement.mo247getSpacingD9Ej5fM()), measureScope, list, new Placeable[list.size()], 0, list.size(), (3072 & 1024) != 0 ? null : null, (3072 & 2048) != 0 ? 0 : 0);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return IntrinsicMeasureBlocks.INSTANCE.VerticalMinWidth(list, i, intrinsicMeasureScope.mo204roundToPx0680j_4(this.verticalArrangement.mo247getSpacingD9Ej5fM()));
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return IntrinsicMeasureBlocks.INSTANCE.VerticalMinHeight(list, i, intrinsicMeasureScope.mo204roundToPx0680j_4(this.verticalArrangement.mo247getSpacingD9Ej5fM()));
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return IntrinsicMeasureBlocks.INSTANCE.VerticalMaxWidth(list, i, intrinsicMeasureScope.mo204roundToPx0680j_4(this.verticalArrangement.mo247getSpacingD9Ej5fM()));
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return IntrinsicMeasureBlocks.INSTANCE.VerticalMaxHeight(list, i, intrinsicMeasureScope.mo204roundToPx0680j_4(this.verticalArrangement.mo247getSpacingD9Ej5fM()));
    }
}
