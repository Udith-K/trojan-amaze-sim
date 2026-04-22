package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextField.kt */
/* JADX INFO: loaded from: classes.dex */
final class TextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i, Function2 function2) {
        Object obj;
        int iSubstractConstraintSafely;
        int iIntValue;
        Object obj2;
        int iIntValue2;
        Object obj3;
        Object obj4;
        int i2;
        Object obj5;
        int iIntValue3;
        Object obj6;
        Object obj7;
        int size = list.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                obj = null;
                break;
            }
            obj = list.get(i3);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj), "Leading")) {
                break;
            }
            i3++;
        }
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) obj;
        if (intrinsicMeasurable != null) {
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(i, intrinsicMeasurable.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue = ((Number) function2.invoke(intrinsicMeasurable, Integer.valueOf(i))).intValue();
        } else {
            iSubstractConstraintSafely = i;
            iIntValue = 0;
        }
        int size2 = list.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = list.get(i4);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj2), "Trailing")) {
                break;
            }
            i4++;
        }
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) obj2;
        if (intrinsicMeasurable2 != null) {
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable2.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue2 = ((Number) function2.invoke(intrinsicMeasurable2, Integer.valueOf(i))).intValue();
        } else {
            iIntValue2 = 0;
        }
        int size3 = list.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size3) {
                obj3 = null;
                break;
            }
            obj3 = list.get(i5);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj3), "Label")) {
                break;
            }
            i5++;
        }
        Object obj8 = (IntrinsicMeasurable) obj3;
        int iIntValue4 = obj8 != null ? ((Number) function2.invoke(obj8, Integer.valueOf(iSubstractConstraintSafely))).intValue() : 0;
        int size4 = list.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size4) {
                obj4 = null;
                break;
            }
            obj4 = list.get(i6);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj4), "Prefix")) {
                break;
            }
            i6++;
        }
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) obj4;
        if (intrinsicMeasurable3 != null) {
            int iIntValue5 = ((Number) function2.invoke(intrinsicMeasurable3, Integer.valueOf(iSubstractConstraintSafely))).intValue();
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable3.maxIntrinsicWidth(Integer.MAX_VALUE));
            i2 = iIntValue5;
        } else {
            i2 = 0;
        }
        int size5 = list.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size5) {
                obj5 = null;
                break;
            }
            obj5 = list.get(i7);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj5), "Suffix")) {
                break;
            }
            i7++;
        }
        IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) obj5;
        if (intrinsicMeasurable4 != null) {
            iIntValue3 = ((Number) function2.invoke(intrinsicMeasurable4, Integer.valueOf(iSubstractConstraintSafely))).intValue();
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable4.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            iIntValue3 = 0;
        }
        int size6 = list.size();
        for (int i8 = 0; i8 < size6; i8++) {
            Object obj9 = list.get(i8);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj9), "TextField")) {
                int iIntValue6 = ((Number) function2.invoke(obj9, Integer.valueOf(iSubstractConstraintSafely))).intValue();
                int size7 = list.size();
                int i9 = 0;
                while (true) {
                    if (i9 >= size7) {
                        obj6 = null;
                        break;
                    }
                    obj6 = list.get(i9);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj6), "Hint")) {
                        break;
                    }
                    i9++;
                }
                Object obj10 = (IntrinsicMeasurable) obj6;
                int iIntValue7 = obj10 != null ? ((Number) function2.invoke(obj10, Integer.valueOf(iSubstractConstraintSafely))).intValue() : 0;
                int size8 = list.size();
                int i10 = 0;
                while (true) {
                    if (i10 >= size8) {
                        obj7 = null;
                        break;
                    }
                    obj7 = list.get(i10);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj7), "Supporting")) {
                        break;
                    }
                    i10++;
                }
                Object obj11 = (IntrinsicMeasurable) obj7;
                return TextFieldKt.m770calculateHeightmKXJcVc(iIntValue6, iIntValue4, iIntValue, iIntValue2, i2, iIntValue3, iIntValue7, obj11 != null ? ((Number) function2.invoke(obj11, Integer.valueOf(i))).intValue() : 0, this.animationProgress, TextFieldImplKt.getZeroConstraints(), intrinsicMeasureScope.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicWidth(List list, int i, Function2 function2) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Object obj7 = list.get(i2);
            if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj7), "TextField")) {
                int iIntValue = ((Number) function2.invoke(obj7, Integer.valueOf(i))).intValue();
                int size2 = list.size();
                int i3 = 0;
                while (true) {
                    obj = null;
                    if (i3 >= size2) {
                        obj2 = null;
                        break;
                    }
                    obj2 = list.get(i3);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj2), "Label")) {
                        break;
                    }
                    i3++;
                }
                IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) obj2;
                int iIntValue2 = intrinsicMeasurable != null ? ((Number) function2.invoke(intrinsicMeasurable, Integer.valueOf(i))).intValue() : 0;
                int size3 = list.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size3) {
                        obj3 = null;
                        break;
                    }
                    obj3 = list.get(i4);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj3), "Trailing")) {
                        break;
                    }
                    i4++;
                }
                IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) obj3;
                int iIntValue3 = intrinsicMeasurable2 != null ? ((Number) function2.invoke(intrinsicMeasurable2, Integer.valueOf(i))).intValue() : 0;
                int size4 = list.size();
                int i5 = 0;
                while (true) {
                    if (i5 >= size4) {
                        obj4 = null;
                        break;
                    }
                    obj4 = list.get(i5);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj4), "Prefix")) {
                        break;
                    }
                    i5++;
                }
                IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) obj4;
                int iIntValue4 = intrinsicMeasurable3 != null ? ((Number) function2.invoke(intrinsicMeasurable3, Integer.valueOf(i))).intValue() : 0;
                int size5 = list.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size5) {
                        obj5 = null;
                        break;
                    }
                    obj5 = list.get(i6);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj5), "Suffix")) {
                        break;
                    }
                    i6++;
                }
                IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) obj5;
                int iIntValue5 = intrinsicMeasurable4 != null ? ((Number) function2.invoke(intrinsicMeasurable4, Integer.valueOf(i))).intValue() : 0;
                int size6 = list.size();
                int i7 = 0;
                while (true) {
                    if (i7 >= size6) {
                        obj6 = null;
                        break;
                    }
                    obj6 = list.get(i7);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj6), "Leading")) {
                        break;
                    }
                    i7++;
                }
                IntrinsicMeasurable intrinsicMeasurable5 = (IntrinsicMeasurable) obj6;
                int iIntValue6 = intrinsicMeasurable5 != null ? ((Number) function2.invoke(intrinsicMeasurable5, Integer.valueOf(i))).intValue() : 0;
                int size7 = list.size();
                int i8 = 0;
                while (true) {
                    if (i8 >= size7) {
                        break;
                    }
                    Object obj8 = list.get(i8);
                    if (Intrinsics.areEqual(TextFieldImplKt.getLayoutId((IntrinsicMeasurable) obj8), "Hint")) {
                        obj = obj8;
                        break;
                    }
                    i8++;
                }
                IntrinsicMeasurable intrinsicMeasurable6 = (IntrinsicMeasurable) obj;
                return TextFieldKt.m771calculateWidthyeHjK3Y(iIntValue6, iIntValue3, iIntValue4, iIntValue5, iIntValue, iIntValue2, intrinsicMeasurable6 != null ? ((Number) function2.invoke(intrinsicMeasurable6, Integer.valueOf(i))).intValue() : 0, TextFieldImplKt.getZeroConstraints());
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public TextFieldMeasurePolicy(boolean z, float f, PaddingValues paddingValues) {
        this.singleLine = z;
        this.animationProgress = f;
        this.paddingValues = paddingValues;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo21measure3p2s80s(final MeasureScope measureScope, List list, long j) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        List list2 = list;
        final int iMo204roundToPx0680j_4 = measureScope.mo204roundToPx0680j_4(this.paddingValues.mo255calculateTopPaddingD9Ej5fM());
        int iMo204roundToPx0680j_42 = measureScope.mo204roundToPx0680j_4(this.paddingValues.mo252calculateBottomPaddingD9Ej5fM());
        long jM2381copyZbe2FdA$default = Constraints.m2381copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                obj = null;
                break;
            }
            obj = list2.get(i);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj), "Leading")) {
                break;
            }
            i++;
        }
        Measurable measurable = (Measurable) obj;
        final Placeable placeableMo1743measureBRTryo0 = measurable != null ? measurable.mo1743measureBRTryo0(jM2381copyZbe2FdA$default) : null;
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo1743measureBRTryo0);
        int iMax = Math.max(0, TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo0));
        int size2 = list.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = list2.get(i2);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj2), "Trailing")) {
                break;
            }
            i2++;
        }
        Measurable measurable2 = (Measurable) obj2;
        Placeable placeableMo1743measureBRTryo02 = measurable2 != null ? measurable2.mo1743measureBRTryo0(ConstraintsKt.m2405offsetNN6EwU$default(jM2381copyZbe2FdA$default, -iWidthOrZero, 0, 2, null)) : null;
        int iWidthOrZero2 = iWidthOrZero + TextFieldImplKt.widthOrZero(placeableMo1743measureBRTryo02);
        int iMax2 = Math.max(iMax, TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo02));
        int size3 = list.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size3) {
                obj3 = null;
                break;
            }
            obj3 = list2.get(i3);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj3), "Prefix")) {
                break;
            }
            i3++;
        }
        Measurable measurable3 = (Measurable) obj3;
        final Placeable placeableMo1743measureBRTryo03 = measurable3 != null ? measurable3.mo1743measureBRTryo0(ConstraintsKt.m2405offsetNN6EwU$default(jM2381copyZbe2FdA$default, -iWidthOrZero2, 0, 2, null)) : null;
        int iWidthOrZero3 = iWidthOrZero2 + TextFieldImplKt.widthOrZero(placeableMo1743measureBRTryo03);
        int iMax3 = Math.max(iMax2, TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo03));
        int size4 = list.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size4) {
                obj4 = null;
                break;
            }
            obj4 = list2.get(i4);
            int i5 = size4;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj4), "Suffix")) {
                break;
            }
            i4++;
            size4 = i5;
        }
        Measurable measurable4 = (Measurable) obj4;
        Placeable placeableMo1743measureBRTryo04 = measurable4 != null ? measurable4.mo1743measureBRTryo0(ConstraintsKt.m2405offsetNN6EwU$default(jM2381copyZbe2FdA$default, -iWidthOrZero3, 0, 2, null)) : null;
        int iWidthOrZero4 = iWidthOrZero3 + TextFieldImplKt.widthOrZero(placeableMo1743measureBRTryo04);
        int iMax4 = Math.max(iMax3, TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo04));
        int i6 = -iWidthOrZero4;
        long jM2404offsetNN6EwU = ConstraintsKt.m2404offsetNN6EwU(jM2381copyZbe2FdA$default, i6, -iMo204roundToPx0680j_42);
        int size5 = list.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size5) {
                obj5 = null;
                break;
            }
            Object obj8 = list2.get(i7);
            int i8 = size5;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj8), "Label")) {
                obj5 = obj8;
                break;
            }
            i7++;
            size5 = i8;
        }
        Measurable measurable5 = (Measurable) obj5;
        Placeable placeableMo1743measureBRTryo05 = measurable5 != null ? measurable5.mo1743measureBRTryo0(jM2404offsetNN6EwU) : null;
        int size6 = list.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size6) {
                obj6 = null;
                break;
            }
            obj6 = list2.get(i9);
            int i10 = size6;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj6), "Supporting")) {
                break;
            }
            i9++;
            size6 = i10;
        }
        Measurable measurable6 = (Measurable) obj6;
        int iMinIntrinsicHeight = measurable6 != null ? measurable6.minIntrinsicHeight(Constraints.m2391getMinWidthimpl(j)) : 0;
        int iHeightOrZero = TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo05) + iMo204roundToPx0680j_4;
        long jM2404offsetNN6EwU2 = ConstraintsKt.m2404offsetNN6EwU(Constraints.m2381copyZbe2FdA$default(j, 0, 0, 0, 0, 11, null), i6, ((-iHeightOrZero) - iMo204roundToPx0680j_42) - iMinIntrinsicHeight);
        int size7 = list.size();
        int i11 = 0;
        while (i11 < size7) {
            int i12 = size7;
            Measurable measurable7 = (Measurable) list2.get(i11);
            int i13 = i11;
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable7), "TextField")) {
                final Placeable placeableMo1743measureBRTryo06 = measurable7.mo1743measureBRTryo0(jM2404offsetNN6EwU2);
                long jM2381copyZbe2FdA$default2 = Constraints.m2381copyZbe2FdA$default(jM2404offsetNN6EwU2, 0, 0, 0, 0, 14, null);
                int size8 = list.size();
                int i14 = 0;
                while (true) {
                    if (i14 >= size8) {
                        obj7 = null;
                        break;
                    }
                    obj7 = list2.get(i14);
                    int i15 = size8;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj7), "Hint")) {
                        break;
                    }
                    i14++;
                    list2 = list;
                    size8 = i15;
                }
                Measurable measurable8 = (Measurable) obj7;
                Placeable placeableMo1743measureBRTryo07 = measurable8 != null ? measurable8.mo1743measureBRTryo0(jM2381copyZbe2FdA$default2) : null;
                int iMax5 = Math.max(iMax4, Math.max(TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo06), TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo07)) + iHeightOrZero + iMo204roundToPx0680j_42);
                final int iM771calculateWidthyeHjK3Y = TextFieldKt.m771calculateWidthyeHjK3Y(TextFieldImplKt.widthOrZero(placeableMo1743measureBRTryo0), TextFieldImplKt.widthOrZero(placeableMo1743measureBRTryo02), TextFieldImplKt.widthOrZero(placeableMo1743measureBRTryo03), TextFieldImplKt.widthOrZero(placeableMo1743measureBRTryo04), placeableMo1743measureBRTryo06.getWidth(), TextFieldImplKt.widthOrZero(placeableMo1743measureBRTryo05), TextFieldImplKt.widthOrZero(placeableMo1743measureBRTryo07), j);
                Placeable placeableMo1743measureBRTryo08 = measurable6 != null ? measurable6.mo1743measureBRTryo0(Constraints.m2381copyZbe2FdA$default(ConstraintsKt.m2405offsetNN6EwU$default(jM2381copyZbe2FdA$default, 0, -iMax5, 1, null), 0, iM771calculateWidthyeHjK3Y, 0, 0, 9, null)) : null;
                int iHeightOrZero2 = TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo08);
                final int iM770calculateHeightmKXJcVc = TextFieldKt.m770calculateHeightmKXJcVc(placeableMo1743measureBRTryo06.getHeight(), TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo05), TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo0), TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo02), TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo03), TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo04), TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo07), TextFieldImplKt.heightOrZero(placeableMo1743measureBRTryo08), this.animationProgress, j, measureScope.getDensity(), this.paddingValues);
                int i16 = iM770calculateHeightmKXJcVc - iHeightOrZero2;
                int size9 = list.size();
                for (int i17 = 0; i17 < size9; i17++) {
                    Measurable measurable9 = (Measurable) list.get(i17);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable9), "Container")) {
                        final Placeable placeableMo1743measureBRTryo09 = measurable9.mo1743measureBRTryo0(ConstraintsKt.Constraints(iM771calculateWidthyeHjK3Y != Integer.MAX_VALUE ? iM771calculateWidthyeHjK3Y : 0, iM771calculateWidthyeHjK3Y, i16 != Integer.MAX_VALUE ? i16 : 0, i16));
                        final Placeable placeable = placeableMo1743measureBRTryo05;
                        final Placeable placeable2 = placeableMo1743measureBRTryo07;
                        final Placeable placeable3 = placeableMo1743measureBRTryo02;
                        final Placeable placeable4 = placeableMo1743measureBRTryo04;
                        final Placeable placeable5 = placeableMo1743measureBRTryo08;
                        return MeasureScope.CC.layout$default(measureScope, iM771calculateWidthyeHjK3Y, iM770calculateHeightmKXJcVc, null, new Function1() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$measure$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj9) {
                                invoke((Placeable.PlacementScope) obj9);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Placeable.PlacementScope placementScope) {
                                Placeable placeable6 = placeable;
                                if (placeable6 == null) {
                                    TextFieldKt.placeWithoutLabel(placementScope, iM771calculateWidthyeHjK3Y, iM770calculateHeightmKXJcVc, placeableMo1743measureBRTryo06, placeable2, placeableMo1743measureBRTryo0, placeable3, placeableMo1743measureBRTryo03, placeable4, placeableMo1743measureBRTryo09, placeable5, this.singleLine, measureScope.getDensity(), this.paddingValues);
                                    return;
                                }
                                int i18 = iM771calculateWidthyeHjK3Y;
                                int i19 = iM770calculateHeightmKXJcVc;
                                Placeable placeable7 = placeableMo1743measureBRTryo06;
                                Placeable placeable8 = placeable2;
                                Placeable placeable9 = placeableMo1743measureBRTryo0;
                                Placeable placeable10 = placeable3;
                                Placeable placeable11 = placeableMo1743measureBRTryo03;
                                Placeable placeable12 = placeable4;
                                Placeable placeable13 = placeableMo1743measureBRTryo09;
                                Placeable placeable14 = placeable5;
                                boolean z = this.singleLine;
                                int i20 = iMo204roundToPx0680j_4;
                                TextFieldKt.placeWithLabel(placementScope, i18, i19, placeable7, placeable6, placeable8, placeable9, placeable10, placeable11, placeable12, placeable13, placeable14, z, i20, placeable.getHeight() + i20, this.animationProgress, measureScope.getDensity());
                            }
                        }, 4, null);
                    }
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            i11 = i13 + 1;
            size7 = i12;
            list2 = list;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.maxIntrinsicHeight.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.maxIntrinsicHeight(i2));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.minIntrinsicHeight.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicHeight(i2));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicWidth(list, i, new Function2() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.maxIntrinsicWidth.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.maxIntrinsicWidth(i2));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicWidth(list, i, new Function2() { // from class: androidx.compose.material3.TextFieldMeasurePolicy.minIntrinsicWidth.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
            }

            public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i2) {
                return Integer.valueOf(intrinsicMeasurable.minIntrinsicWidth(i2));
            }
        });
    }
}
