package androidx.compose.animation;

import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: AnimatedVisibility.kt */
/* JADX INFO: loaded from: classes.dex */
final class AnimatedEnterExitMeasurePolicy implements MeasurePolicy {
    private boolean hasLookaheadOccurred;
    private final AnimatedVisibilityScopeImpl scope;

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult mo21measure3p2s80s(MeasureScope measureScope, List list, long j) {
        Object obj;
        final ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(((Measurable) list.get(i)).mo1743measureBRTryo0(j));
        }
        Object obj2 = null;
        if (arrayList.isEmpty()) {
            obj = null;
        } else {
            obj = arrayList.get(0);
            int width = ((Placeable) obj).getWidth();
            int lastIndex = CollectionsKt.getLastIndex(arrayList);
            if (1 <= lastIndex) {
                int i2 = 1;
                while (true) {
                    Object obj3 = arrayList.get(i2);
                    int width2 = ((Placeable) obj3).getWidth();
                    if (width < width2) {
                        obj = obj3;
                        width = width2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        Placeable placeable = (Placeable) obj;
        int width3 = placeable != null ? placeable.getWidth() : 0;
        if (!arrayList.isEmpty()) {
            Object obj4 = arrayList.get(0);
            int height = ((Placeable) obj4).getHeight();
            int lastIndex2 = CollectionsKt.getLastIndex(arrayList);
            if (1 <= lastIndex2) {
                int i3 = 1;
                while (true) {
                    Object obj5 = arrayList.get(i3);
                    int height2 = ((Placeable) obj5).getHeight();
                    if (height < height2) {
                        obj4 = obj5;
                        height = height2;
                    }
                    if (i3 == lastIndex2) {
                        break;
                    }
                    i3++;
                }
            }
            obj2 = obj4;
        }
        Placeable placeable2 = (Placeable) obj2;
        int height3 = placeable2 != null ? placeable2.getHeight() : 0;
        if (measureScope.isLookingAhead()) {
            this.hasLookaheadOccurred = true;
            this.scope.getTargetSize$animation_release().setValue(IntSize.m2471boximpl(IntSizeKt.IntSize(width3, height3)));
        } else if (!this.hasLookaheadOccurred) {
            this.scope.getTargetSize$animation_release().setValue(IntSize.m2471boximpl(IntSizeKt.IntSize(width3, height3)));
        }
        return MeasureScope.CC.layout$default(measureScope, width3, height3, null, new Function1() { // from class: androidx.compose.animation.AnimatedEnterExitMeasurePolicy$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj6) {
                invoke((Placeable.PlacementScope) obj6);
                return Unit.INSTANCE;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                List list2 = arrayList;
                int size2 = list2.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    Placeable.PlacementScope.place$default(placementScope, (Placeable) list2.get(i4), 0, 0, 0.0f, 4, null);
                }
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            numValueOf = Integer.valueOf(((IntrinsicMeasurable) list.get(0)).maxIntrinsicHeight(i));
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(((IntrinsicMeasurable) list.get(i2)).maxIntrinsicHeight(i));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            numValueOf = Integer.valueOf(((IntrinsicMeasurable) list.get(0)).maxIntrinsicWidth(i));
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(((IntrinsicMeasurable) list.get(i2)).maxIntrinsicWidth(i));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            numValueOf = Integer.valueOf(((IntrinsicMeasurable) list.get(0)).minIntrinsicHeight(i));
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(((IntrinsicMeasurable) list.get(i2)).minIntrinsicHeight(i));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        Integer numValueOf;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            numValueOf = Integer.valueOf(((IntrinsicMeasurable) list.get(0)).minIntrinsicWidth(i));
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(((IntrinsicMeasurable) list.get(i2)).minIntrinsicWidth(i));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return 0;
    }

    public AnimatedEnterExitMeasurePolicy(AnimatedVisibilityScopeImpl animatedVisibilityScopeImpl) {
        this.scope = animatedVisibilityScopeImpl;
    }
}
