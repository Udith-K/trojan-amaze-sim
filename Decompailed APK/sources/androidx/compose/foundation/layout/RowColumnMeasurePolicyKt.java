package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.RowColumnMeasurePolicy;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: RowColumnMeasurePolicy.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RowColumnMeasurePolicyKt {
    public static final MeasureResult measure(RowColumnMeasurePolicy rowColumnMeasurePolicy, int i, int i2, int i3, int i4, int i5, MeasureScope measureScope, List list, Placeable[] placeableArr, int i6, int i7, int[] iArr, int i8) throws Throwable {
        int[] iArr2;
        long j;
        long j2;
        String str;
        String str2;
        int i9;
        Integer num;
        int i10;
        int iCoerceIn;
        int i11;
        String str3;
        int i12;
        float f;
        String str4;
        float f2;
        long j3;
        long j4;
        String str5;
        String str6;
        String str7;
        String str8;
        int i13;
        int i14;
        String str9;
        float f3;
        int i15;
        int i16;
        float f4;
        String str10;
        long j5;
        int i17;
        String str11;
        String str12;
        int i18;
        int i19;
        float f5;
        float f6;
        char c;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        long j6;
        int[] iArr3;
        int i26;
        int i27;
        float f7;
        int[] iArr4;
        int i28;
        List list2 = list;
        int i29 = i7;
        int i30 = i29 - i6;
        int i31 = 0;
        int i32 = i6;
        int i33 = 0;
        int i34 = 0;
        int i35 = 0;
        int i36 = 0;
        int[] iArr5 = new int[i30];
        long j7 = i5;
        float f8 = 0.0f;
        int i37 = 0;
        while (true) {
            if (i32 >= i29) {
                break;
            }
            int i38 = i37;
            Measurable measurable = (Measurable) list2.get(i32);
            RowColumnParentData rowColumnParentData = RowColumnImplKt.getRowColumnParentData(measurable);
            float weight = RowColumnImplKt.getWeight(rowColumnParentData);
            i36 = (i36 != 0 || RowColumnImplKt.isRelative(rowColumnParentData)) ? 1 : i31;
            if (weight > 0.0f) {
                i35++;
                i25 = i32;
                j6 = j7;
                iArr4 = iArr5;
                i27 = i30;
                f7 = f8 + weight;
                i28 = i31;
            } else {
                if (i4 != Integer.MAX_VALUE && rowColumnParentData != null) {
                    rowColumnParentData.getFlowLayoutData();
                }
                int i39 = i3 - i34;
                Placeable placeableMo1743measureBRTryo0 = placeableArr[i32];
                if (placeableMo1743measureBRTryo0 == null) {
                    i22 = i39;
                    i23 = i34;
                    i24 = i35;
                    i25 = i32;
                    iArr3 = iArr5;
                    j6 = j7;
                    i26 = i38;
                    i27 = i30;
                    f7 = f8;
                    placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(RowColumnMeasurePolicy.CC.m272createConstraintsxF2OJ5Q$default(rowColumnMeasurePolicy, 0, 0, i3 != Integer.MAX_VALUE ? RangesKt.coerceAtLeast(i39, i31) : Integer.MAX_VALUE, i4, false, 16, null));
                } else {
                    i22 = i39;
                    i23 = i34;
                    i24 = i35;
                    i25 = i32;
                    j6 = j7;
                    iArr3 = iArr5;
                    i26 = i38;
                    i27 = i30;
                    f7 = f8;
                }
                Placeable placeable = placeableMo1743measureBRTryo0;
                int iMainAxisSize = rowColumnMeasurePolicy.mainAxisSize(placeable);
                int iCrossAxisSize = rowColumnMeasurePolicy.crossAxisSize(placeable);
                iArr4 = iArr3;
                iArr4[i25 - i6] = iMainAxisSize;
                i28 = 0;
                int iMin = Math.min(i5, RangesKt.coerceAtLeast(i22 - iMainAxisSize, 0));
                i34 = iMainAxisSize + iMin + i23;
                int iMax = Math.max(i26, iCrossAxisSize);
                placeableArr[i25] = placeable;
                i38 = iMax;
                i33 = iMin;
                i35 = i24;
            }
            i32 = i25 + 1;
            iArr5 = iArr4;
            i31 = i28;
            f8 = f7;
            i30 = i27;
            i37 = i38;
            j7 = j6;
        }
        int i40 = i37;
        long j8 = j7;
        int[] iArr6 = iArr5;
        int i41 = i30;
        float f9 = f8;
        int i42 = i31;
        int i43 = i34;
        int i44 = i35;
        if (i44 == 0) {
            i11 = i43 - i33;
            iArr2 = iArr6;
            iCoerceIn = i42;
            i9 = i;
            i10 = i40;
            num = null;
        } else {
            int i45 = i3 != Integer.MAX_VALUE ? i3 : i;
            iArr2 = iArr6;
            long j9 = ((long) (i44 - 1)) * j8;
            long jCoerceAtLeast = RangesKt.coerceAtLeast(((long) (i45 - i43)) - j9, 0L);
            float f10 = jCoerceAtLeast / f9;
            int i46 = i6;
            long jRound = jCoerceAtLeast;
            while (true) {
                j = jCoerceAtLeast;
                j2 = j9;
                str = "arrangementSpacingPx ";
                str2 = "targetSpace ";
                if (i46 >= i29) {
                    break;
                }
                float weight2 = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData((Measurable) list2.get(i46)));
                float f11 = f10 * weight2;
                try {
                    jRound -= (long) Math.round(f11);
                    i46++;
                    list2 = list;
                    i29 = i7;
                    jCoerceAtLeast = j;
                    j9 = j2;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/297974033 mainAxisMax " + i3 + "mainAxisMin " + i + "targetSpace " + i45 + "arrangementSpacingPx " + j8 + "weightChildrenCount " + i44 + "fixedSpace " + i43 + "arrangementSpacingTotal " + j2 + "remainingToTarget " + j + "totalWeight " + f9 + "weightUnitSpace " + f10 + "itemWeight " + weight2 + "weightedSize " + f11).initCause(e);
                }
            }
            i9 = i;
            float f12 = f9;
            String str13 = "weightedSize ";
            String str14 = "weightUnitSpace ";
            String str15 = "totalWeight ";
            long j10 = j;
            String str16 = "remainingToTarget ";
            long j11 = j2;
            String str17 = "arrangementSpacingTotal ";
            long j12 = j8;
            int i47 = i40;
            int i48 = 0;
            int i49 = i6;
            int i50 = i7;
            while (i49 < i50) {
                if (placeableArr[i49] == null) {
                    Measurable measurable2 = (Measurable) list.get(i49);
                    RowColumnParentData rowColumnParentData2 = RowColumnImplKt.getRowColumnParentData(measurable2);
                    float weight3 = RowColumnImplKt.getWeight(rowColumnParentData2);
                    String str18 = str;
                    int i51 = i45;
                    if (i4 != Integer.MAX_VALUE && rowColumnParentData2 != null) {
                        rowColumnParentData2.getFlowLayoutData();
                    }
                    if (weight3 <= 0.0f) {
                        throw new IllegalStateException("All weights <= 0 should have placeables");
                    }
                    int sign = MathKt.getSign(jRound);
                    String str19 = str2;
                    jRound -= (long) sign;
                    float f13 = f10 * weight3;
                    int iMax2 = Math.max(0, Math.round(f13) + sign);
                    try {
                    } catch (IllegalArgumentException e2) {
                        e = e2;
                        f3 = f13;
                        i15 = i44;
                        i16 = i43;
                        f4 = weight3;
                        str10 = str13;
                        j5 = j11;
                        i17 = i51;
                        str11 = str18;
                        str12 = str19;
                        i18 = sign;
                        i19 = iMax2;
                        f5 = f10;
                        f6 = f12;
                        j3 = j12;
                    }
                    try {
                        if (RowColumnImplKt.getFill(rowColumnParentData2)) {
                            c = 65535;
                            int i52 = iMax2 != Integer.MAX_VALUE ? iMax2 : 0;
                            j5 = j11;
                            str11 = str18;
                            i18 = sign;
                            i19 = iMax2;
                            str12 = str19;
                            int i53 = i52;
                            f3 = f13;
                            i17 = i51;
                            i15 = i44;
                            i16 = i43;
                            f4 = weight3;
                            f5 = f10;
                            str10 = str13;
                            f6 = f12;
                            j3 = j12;
                            Placeable placeableMo1743measureBRTryo02 = measurable2.mo1743measureBRTryo0(rowColumnMeasurePolicy.mo250createConstraintsxF2OJ5Q(i53, 0, i19, i4, true));
                            int iMainAxisSize2 = rowColumnMeasurePolicy.mainAxisSize(placeableMo1743measureBRTryo02);
                            int iCrossAxisSize2 = rowColumnMeasurePolicy.crossAxisSize(placeableMo1743measureBRTryo02);
                            iArr2[i49 - i6] = iMainAxisSize2;
                            i48 += iMainAxisSize2;
                            int iMax3 = Math.max(i47, iCrossAxisSize2);
                            placeableArr[i49] = placeableMo1743measureBRTryo02;
                            i47 = iMax3;
                            str4 = str10;
                            f2 = f6;
                            f = f5;
                            j4 = j10;
                            str5 = str14;
                            str6 = str15;
                            str7 = str16;
                            str8 = str17;
                            j11 = j5;
                            str3 = str11;
                            str9 = str12;
                            i13 = i17;
                            i14 = i15;
                            i12 = i16;
                        } else {
                            c = 65535;
                        }
                        Placeable placeableMo1743measureBRTryo022 = measurable2.mo1743measureBRTryo0(rowColumnMeasurePolicy.mo250createConstraintsxF2OJ5Q(i53, 0, i19, i4, true));
                        int iMainAxisSize22 = rowColumnMeasurePolicy.mainAxisSize(placeableMo1743measureBRTryo022);
                        int iCrossAxisSize22 = rowColumnMeasurePolicy.crossAxisSize(placeableMo1743measureBRTryo022);
                        iArr2[i49 - i6] = iMainAxisSize22;
                        i48 += iMainAxisSize22;
                        int iMax32 = Math.max(i47, iCrossAxisSize22);
                        placeableArr[i49] = placeableMo1743measureBRTryo022;
                        i47 = iMax32;
                        str4 = str10;
                        f2 = f6;
                        f = f5;
                        j4 = j10;
                        str5 = str14;
                        str6 = str15;
                        str7 = str16;
                        str8 = str17;
                        j11 = j5;
                        str3 = str11;
                        str9 = str12;
                        i13 = i17;
                        i14 = i15;
                        i12 = i16;
                    } catch (IllegalArgumentException e3) {
                        e = e3;
                        throw new IllegalArgumentException("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/300280216 mainAxisMax " + i3 + "mainAxisMin " + i9 + str12 + i17 + str11 + j3 + "weightChildrenCount " + i15 + "fixedSpace " + i16 + str17 + j5 + str16 + j10 + str15 + f6 + str14 + f5 + "weight " + f4 + str10 + f3 + "crossAxisDesiredSize " + ((Object) null) + "remainderUnit " + i18 + "childMainAxisSize " + i19).initCause(e);
                    }
                    j5 = j11;
                    str11 = str18;
                    i18 = sign;
                    i19 = iMax2;
                    str12 = str19;
                    int i532 = i52;
                    f3 = f13;
                    i17 = i51;
                    i15 = i44;
                    i16 = i43;
                    f4 = weight3;
                    f5 = f10;
                    str10 = str13;
                    f6 = f12;
                    j3 = j12;
                } else {
                    str3 = str;
                    i12 = i43;
                    f = f10;
                    str4 = str13;
                    f2 = f12;
                    j3 = j12;
                    j4 = j10;
                    str5 = str14;
                    str6 = str15;
                    str7 = str16;
                    str8 = str17;
                    i13 = i45;
                    i14 = i44;
                    str9 = str2;
                }
                i49++;
                str2 = str9;
                i45 = i13;
                str = str3;
                i44 = i14;
                i43 = i12;
                j10 = j4;
                f10 = f;
                str14 = str5;
                str15 = str6;
                str16 = str7;
                str17 = str8;
                i50 = i7;
                j12 = j3;
                f12 = f2;
                str13 = str4;
            }
            int i54 = i43;
            num = null;
            i10 = i47;
            iCoerceIn = RangesKt.coerceIn((int) (((long) i48) + j11), 0, i3 - i54);
            i11 = i54;
        }
        if (i36 != 0) {
            int iMax4 = 0;
            int iMax5 = 0;
            for (int i55 = i6; i55 < i7; i55++) {
                Placeable placeable2 = placeableArr[i55];
                Intrinsics.checkNotNull(placeable2);
                CrossAxisAlignment crossAxisAlignment = RowColumnImplKt.getCrossAxisAlignment(RowColumnImplKt.getRowColumnParentData(placeable2));
                Integer numCalculateAlignmentLinePosition$foundation_layout_release = crossAxisAlignment != null ? crossAxisAlignment.calculateAlignmentLinePosition$foundation_layout_release(placeable2) : num;
                if (numCalculateAlignmentLinePosition$foundation_layout_release != null) {
                    int iIntValue = numCalculateAlignmentLinePosition$foundation_layout_release.intValue();
                    int iCrossAxisSize3 = rowColumnMeasurePolicy.crossAxisSize(placeable2);
                    iMax4 = Math.max(iMax4, iIntValue != Integer.MIN_VALUE ? numCalculateAlignmentLinePosition$foundation_layout_release.intValue() : 0);
                    if (iIntValue == Integer.MIN_VALUE) {
                        iIntValue = iCrossAxisSize3;
                    }
                    iMax5 = Math.max(iMax5, iCrossAxisSize3 - iIntValue);
                }
            }
            i20 = iMax5;
            i21 = iMax4;
        } else {
            i20 = 0;
            i21 = 0;
        }
        int iMax6 = Math.max(RangesKt.coerceAtLeast(i11 + iCoerceIn, 0), i9);
        int iMax7 = Math.max(i10, Math.max(i2, i20 + i21));
        int[] iArr7 = new int[i41];
        for (int i56 = 0; i56 < i41; i56++) {
            iArr7[i56] = 0;
        }
        rowColumnMeasurePolicy.populateMainAxisPositions(iMax6, iArr2, iArr7, measureScope);
        return rowColumnMeasurePolicy.placeHelper(placeableArr, measureScope, i21, iArr7, iMax6, iMax7, iArr, i8, i6, i7);
    }
}
