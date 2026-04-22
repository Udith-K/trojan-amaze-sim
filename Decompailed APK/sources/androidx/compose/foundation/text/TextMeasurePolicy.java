package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: BasicText.kt */
/* JADX INFO: loaded from: classes.dex */
final class TextMeasurePolicy implements MeasurePolicy {
    private final Function0 placements;
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

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo21measure3p2s80s(MeasureScope measureScope, List list, long j) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (!(((Measurable) obj).getParentData() instanceof TextRangeLayoutModifier)) {
                arrayList.add(obj);
            }
        }
        List list2 = (List) this.placements.invoke();
        final ArrayList arrayList2 = null;
        if (list2 != null) {
            ArrayList arrayList3 = new ArrayList(list2.size());
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Rect rect = (Rect) list2.get(i2);
                Pair pair = rect != null ? new Pair(((Measurable) arrayList.get(i2)).mo1743measureBRTryo0(ConstraintsKt.Constraints$default(0, (int) Math.floor(rect.getWidth()), 0, (int) Math.floor(rect.getHeight()), 5, null)), IntOffset.m2451boximpl(IntOffsetKt.IntOffset(Math.round(rect.getLeft()), Math.round(rect.getTop())))) : null;
                if (pair != null) {
                    arrayList3.add(pair);
                }
            }
            arrayList2 = arrayList3;
        }
        ArrayList arrayList4 = new ArrayList(list.size());
        int size3 = list.size();
        for (int i3 = 0; i3 < size3; i3++) {
            Object obj2 = list.get(i3);
            if (((Measurable) obj2).getParentData() instanceof TextRangeLayoutModifier) {
                arrayList4.add(obj2);
            }
        }
        final List listMeasureWithTextRangeMeasureConstraints = BasicTextKt.measureWithTextRangeMeasureConstraints(arrayList4, this.shouldMeasureLinks);
        return MeasureScope.CC.layout$default(measureScope, Constraints.m2389getMaxWidthimpl(j), Constraints.m2388getMaxHeightimpl(j), null, new Function1() { // from class: androidx.compose.foundation.text.TextMeasurePolicy$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj3) {
                invoke((Placeable.PlacementScope) obj3);
                return Unit.INSTANCE;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                List list3 = arrayList2;
                if (list3 != null) {
                    int size4 = list3.size();
                    for (int i4 = 0; i4 < size4; i4++) {
                        Pair pair2 = (Pair) list3.get(i4);
                        Placeable.PlacementScope.m1765place70tqf50$default(placementScope, (Placeable) pair2.component1(), ((IntOffset) pair2.component2()).m2463unboximpl(), 0.0f, 2, null);
                    }
                }
                List list4 = listMeasureWithTextRangeMeasureConstraints;
                if (list4 != null) {
                    int size5 = list4.size();
                    for (int i5 = 0; i5 < size5; i5++) {
                        Pair pair3 = (Pair) list4.get(i5);
                        Placeable placeable = (Placeable) pair3.component1();
                        Function0 function0 = (Function0) pair3.component2();
                        Placeable.PlacementScope.m1765place70tqf50$default(placementScope, placeable, function0 != null ? ((IntOffset) function0.invoke()).m2463unboximpl() : IntOffset.Companion.m2464getZeronOccac(), 0.0f, 2, null);
                    }
                }
            }
        }, 4, null);
    }

    public TextMeasurePolicy(Function0 function0, Function0 function02) {
        this.shouldMeasureLinks = function0;
        this.placements = function02;
    }
}
