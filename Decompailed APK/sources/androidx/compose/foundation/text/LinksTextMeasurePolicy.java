package androidx.compose.foundation.text;

import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: BasicText.kt */
/* JADX INFO: loaded from: classes.dex */
final class LinksTextMeasurePolicy implements MeasurePolicy {
    private final Function0 shouldMeasureLinks;

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i);
    }

    public LinksTextMeasurePolicy(Function0 function0) {
        this.shouldMeasureLinks = function0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo21measure3p2s80s(MeasureScope measureScope, final List list, long j) {
        return MeasureScope.CC.layout$default(measureScope, Constraints.m2389getMaxWidthimpl(j), Constraints.m2388getMaxHeightimpl(j), null, new Function1() { // from class: androidx.compose.foundation.text.LinksTextMeasurePolicy$measure$1
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
                List listMeasureWithTextRangeMeasureConstraints = BasicTextKt.measureWithTextRangeMeasureConstraints(list, this.shouldMeasureLinks);
                if (listMeasureWithTextRangeMeasureConstraints != null) {
                    int size = listMeasureWithTextRangeMeasureConstraints.size();
                    for (int i = 0; i < size; i++) {
                        Pair pair = (Pair) listMeasureWithTextRangeMeasureConstraints.get(i);
                        Placeable placeable = (Placeable) pair.component1();
                        Function0 function0 = (Function0) pair.component2();
                        Placeable.PlacementScope.m1765place70tqf50$default(placementScope, placeable, function0 != null ? ((IntOffset) function0.invoke()).m2463unboximpl() : IntOffset.Companion.m2464getZeronOccac(), 0.0f, 2, null);
                    }
                }
            }
        }, 4, null);
    }
}
