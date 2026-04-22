package androidx.compose.material3;

import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AppBarKt {
    private static final float BottomAppBarHorizontalPadding;
    private static final float BottomAppBarVerticalPadding;
    private static final float FABHorizontalPadding;
    private static final float FABVerticalPadding;
    private static final float LargeTitleBottomPadding;
    private static final float MediumTitleBottomPadding;
    private static final float TopAppBarHorizontalPadding;
    private static final float TopAppBarTitleInset;
    private static final CubicBezierEasing TopTitleAlphaEasing;

    private static final long SingleRowTopAppBar_cJHQLPU$lambda$10(State state) {
        return ((Color) state.getValue()).m1304unboximpl();
    }

    private static final float SingleRowTopAppBar_cJHQLPU$lambda$9(State state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:144:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010d  */
    /* JADX INFO: renamed from: TopAppBar-GHTll3U, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m599TopAppBarGHTll3U(final kotlin.jvm.functions.Function2 r24, androidx.compose.ui.Modifier r25, kotlin.jvm.functions.Function2 r26, kotlin.jvm.functions.Function3 r27, float r28, androidx.compose.foundation.layout.WindowInsets r29, androidx.compose.material3.TopAppBarColors r30, androidx.compose.material3.TopAppBarScrollBehavior r31, androidx.compose.runtime.Composer r32, final int r33, final int r34) {
        /*
            Method dump skipped, instruction units count: 534
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.m599TopAppBarGHTll3U(kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, float, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.TopAppBarColors, androidx.compose.material3.TopAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:174:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0111  */
    /* JADX INFO: renamed from: SingleRowTopAppBar-cJHQLPU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m598SingleRowTopAppBarcJHQLPU(androidx.compose.ui.Modifier r33, final kotlin.jvm.functions.Function2 r34, final androidx.compose.ui.text.TextStyle r35, final boolean r36, final kotlin.jvm.functions.Function2 r37, final kotlin.jvm.functions.Function3 r38, final float r39, final androidx.compose.foundation.layout.WindowInsets r40, final androidx.compose.material3.TopAppBarColors r41, final androidx.compose.material3.TopAppBarScrollBehavior r42, androidx.compose.runtime.Composer r43, final int r44, final int r45) {
        /*
            Method dump skipped, instruction units count: 740
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.m598SingleRowTopAppBarcJHQLPU(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.ui.text.TextStyle, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, float, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.TopAppBarColors, androidx.compose.material3.TopAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: TopAppBarLayout-kXwM9vE, reason: not valid java name */
    public static final void m600TopAppBarLayoutkXwM9vE(final Modifier modifier, final ScrolledOffset scrolledOffset, final long j, final long j2, final long j3, final Function2 function2, final TextStyle textStyle, final float f, final Arrangement.Vertical vertical, final Arrangement.Horizontal horizontal, final int i, final boolean z, final Function2 function22, final Function2 function23, Composer composer, final int i2, final int i3) {
        int i4;
        int i5;
        Composer composerStartRestartGroup = composer.startRestartGroup(-742442296);
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= (i2 & 64) == 0 ? composerStartRestartGroup.changed(scrolledOffset) : composerStartRestartGroup.changedInstance(scrolledOffset) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i4 |= composerStartRestartGroup.changed(j) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changed(j2) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i4 |= composerStartRestartGroup.changed(j3) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function2) ? PKIFailureInfo.unsupportedVersion : PKIFailureInfo.notAuthorized;
        }
        if ((i2 & 1572864) == 0) {
            i4 |= composerStartRestartGroup.changed(textStyle) ? PKIFailureInfo.badCertTemplate : PKIFailureInfo.signerNotTrusted;
        }
        if ((i2 & 12582912) == 0) {
            i4 |= composerStartRestartGroup.changed(f) ? 8388608 : 4194304;
        }
        if ((i2 & 100663296) == 0) {
            i4 |= composerStartRestartGroup.changed(vertical) ? 67108864 : 33554432;
        }
        if ((805306368 & i2) == 0) {
            i4 |= composerStartRestartGroup.changed(horizontal) ? PKIFailureInfo.duplicateCertReq : 268435456;
        }
        if ((i3 & 6) == 0) {
            i5 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i3;
        } else {
            i5 = i3;
        }
        if ((i3 & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i3 & 384) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function22) ? 256 : 128;
        }
        if ((i3 & 3072) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function23) ? 2048 : 1024;
        }
        if ((306783379 & i4) == 306783378 && (i5 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-742442296, i4, i5, "androidx.compose.material3.TopAppBarLayout (AppBar.kt:2134)");
            }
            boolean z2 = ((i4 & 112) == 32 || ((i4 & 64) != 0 && composerStartRestartGroup.changedInstance(scrolledOffset))) | ((1879048192 & i4) == 536870912) | ((234881024 & i4) == 67108864) | ((i5 & 14) == 4);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.AppBarKt$TopAppBarLayout$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i6) {
                        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i6);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i6) {
                        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i6);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i6) {
                        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i6);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i6) {
                        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i6);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo21measure3p2s80s(final MeasureScope measureScope, List list, final long j4) {
                        int iCoerceAtLeast;
                        int iM2388getMaxHeightimpl;
                        int size = list.size();
                        for (int i6 = 0; i6 < size; i6++) {
                            Measurable measurable = (Measurable) list.get(i6);
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "navigationIcon")) {
                                final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(Constraints.m2381copyZbe2FdA$default(j4, 0, 0, 0, 0, 14, null));
                                int size2 = list.size();
                                for (int i7 = 0; i7 < size2; i7++) {
                                    Measurable measurable2 = (Measurable) list.get(i7);
                                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "actionIcons")) {
                                        final Placeable placeableMo1743measureBRTryo02 = measurable2.mo1743measureBRTryo0(Constraints.m2381copyZbe2FdA$default(j4, 0, 0, 0, 0, 14, null));
                                        if (Constraints.m2389getMaxWidthimpl(j4) == Integer.MAX_VALUE) {
                                            iCoerceAtLeast = Constraints.m2389getMaxWidthimpl(j4);
                                        } else {
                                            iCoerceAtLeast = RangesKt.coerceAtLeast((Constraints.m2389getMaxWidthimpl(j4) - placeableMo1743measureBRTryo0.getWidth()) - placeableMo1743measureBRTryo02.getWidth(), 0);
                                        }
                                        int i8 = iCoerceAtLeast;
                                        int size3 = list.size();
                                        for (int i9 = 0; i9 < size3; i9++) {
                                            Measurable measurable3 = (Measurable) list.get(i9);
                                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), "title")) {
                                                final Placeable placeableMo1743measureBRTryo03 = measurable3.mo1743measureBRTryo0(Constraints.m2381copyZbe2FdA$default(j4, 0, i8, 0, 0, 12, null));
                                                final int i10 = placeableMo1743measureBRTryo03.get(AlignmentLineKt.getLastBaseline()) != Integer.MIN_VALUE ? placeableMo1743measureBRTryo03.get(AlignmentLineKt.getLastBaseline()) : 0;
                                                float fOffset = scrolledOffset.offset();
                                                int iRoundToInt = Float.isNaN(fOffset) ? 0 : MathKt.roundToInt(fOffset);
                                                if (Constraints.m2388getMaxHeightimpl(j4) == Integer.MAX_VALUE) {
                                                    iM2388getMaxHeightimpl = Constraints.m2388getMaxHeightimpl(j4);
                                                } else {
                                                    iM2388getMaxHeightimpl = Constraints.m2388getMaxHeightimpl(j4) + iRoundToInt;
                                                }
                                                final int i11 = iM2388getMaxHeightimpl;
                                                int iM2389getMaxWidthimpl = Constraints.m2389getMaxWidthimpl(j4);
                                                final Arrangement.Horizontal horizontal2 = horizontal;
                                                final Arrangement.Vertical vertical2 = vertical;
                                                final int i12 = i;
                                                return MeasureScope.CC.layout$default(measureScope, iM2389getMaxWidthimpl, i11, null, new Function1() { // from class: androidx.compose.material3.AppBarKt$TopAppBarLayout$2$1.1
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
                                                        int iMax;
                                                        int height;
                                                        int height2;
                                                        int iM2389getMaxWidthimpl2;
                                                        Placeable placeable = placeableMo1743measureBRTryo0;
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, (i11 - placeable.getHeight()) / 2, 0.0f, 4, null);
                                                        Placeable placeable2 = placeableMo1743measureBRTryo03;
                                                        Arrangement.Horizontal horizontal3 = horizontal2;
                                                        Arrangement arrangement = Arrangement.INSTANCE;
                                                        if (Intrinsics.areEqual(horizontal3, arrangement.getCenter())) {
                                                            iMax = (Constraints.m2389getMaxWidthimpl(j4) - placeableMo1743measureBRTryo03.getWidth()) / 2;
                                                            if (iMax < placeableMo1743measureBRTryo0.getWidth()) {
                                                                iM2389getMaxWidthimpl2 = placeableMo1743measureBRTryo0.getWidth() - iMax;
                                                            } else if (placeableMo1743measureBRTryo03.getWidth() + iMax > Constraints.m2389getMaxWidthimpl(j4) - placeableMo1743measureBRTryo02.getWidth()) {
                                                                iM2389getMaxWidthimpl2 = (Constraints.m2389getMaxWidthimpl(j4) - placeableMo1743measureBRTryo02.getWidth()) - (placeableMo1743measureBRTryo03.getWidth() + iMax);
                                                            }
                                                            iMax += iM2389getMaxWidthimpl2;
                                                        } else if (!Intrinsics.areEqual(horizontal3, arrangement.getEnd())) {
                                                            iMax = Math.max(measureScope.mo204roundToPx0680j_4(AppBarKt.TopAppBarTitleInset), placeableMo1743measureBRTryo0.getWidth());
                                                        } else {
                                                            iMax = (Constraints.m2389getMaxWidthimpl(j4) - placeableMo1743measureBRTryo03.getWidth()) - placeableMo1743measureBRTryo02.getWidth();
                                                        }
                                                        int i13 = iMax;
                                                        Arrangement.Vertical vertical3 = vertical2;
                                                        if (!Intrinsics.areEqual(vertical3, arrangement.getCenter())) {
                                                            if (Intrinsics.areEqual(vertical3, arrangement.getBottom())) {
                                                                int i14 = i12;
                                                                if (i14 == 0) {
                                                                    height2 = i11 - placeableMo1743measureBRTryo03.getHeight();
                                                                } else {
                                                                    int height3 = i14 - (placeableMo1743measureBRTryo03.getHeight() - i10);
                                                                    int height4 = placeableMo1743measureBRTryo03.getHeight() + height3;
                                                                    if (height4 > Constraints.m2388getMaxHeightimpl(j4)) {
                                                                        height3 -= height4 - Constraints.m2388getMaxHeightimpl(j4);
                                                                    }
                                                                    height = (i11 - placeableMo1743measureBRTryo03.getHeight()) - Math.max(0, height3);
                                                                }
                                                            } else {
                                                                height = 0;
                                                            }
                                                            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i13, height, 0.0f, 4, null);
                                                            Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo1743measureBRTryo02, Constraints.m2389getMaxWidthimpl(j4) - placeableMo1743measureBRTryo02.getWidth(), (i11 - placeableMo1743measureBRTryo02.getHeight()) / 2, 0.0f, 4, null);
                                                        }
                                                        height2 = (i11 - placeableMo1743measureBRTryo03.getHeight()) / 2;
                                                        height = height2;
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i13, height, 0.0f, 4, null);
                                                        Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo1743measureBRTryo02, Constraints.m2389getMaxWidthimpl(j4) - placeableMo1743measureBRTryo02.getWidth(), (i11 - placeableMo1743measureBRTryo02.getHeight()) / 2, 0.0f, 4, null);
                                                    }
                                                }, 4, null);
                                            }
                                        }
                                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                    }
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
            ComposeUiNode.Companion companion = ComposeUiNode.Companion;
            Function0 constructor = companion.getConstructor();
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM1035constructorimpl = Updater.m1035constructorimpl(composerStartRestartGroup);
            Updater.m1036setimpl(composerM1035constructorimpl, measurePolicy, companion.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            Modifier.Companion companion2 = Modifier.Companion;
            Modifier modifierLayoutId = LayoutIdKt.layoutId(companion2, "navigationIcon");
            float f2 = TopAppBarHorizontalPadding;
            Modifier modifierM265paddingqDBjuR0$default = PaddingKt.m265paddingqDBjuR0$default(modifierLayoutId, f2, 0.0f, 0.0f, 0.0f, 14, null);
            Alignment.Companion companion3 = Alignment.Companion;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM265paddingqDBjuR0$default);
            Function0 constructor2 = companion.getConstructor();
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM1035constructorimpl2 = Updater.m1035constructorimpl(composerStartRestartGroup);
            Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = companion.getSetCompositeKeyHash();
            if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ProvidedValue providedValueProvides = ContentColorKt.getLocalContentColor().provides(Color.m1290boximpl(j));
            int i6 = ProvidedValue.$stable;
            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, function22, composerStartRestartGroup, ((i5 >> 3) & 112) | i6);
            composerStartRestartGroup.endNode();
            Modifier modifierM1334graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m1334graphicsLayerAp8cVGQ$default(PaddingKt.m263paddingVpY3zN4$default(LayoutIdKt.layoutId(companion2, "title"), f2, 0.0f, 2, null).then(z ? SemanticsModifierKt.clearAndSetSemantics(companion2, new Function1() { // from class: androidx.compose.material3.AppBarKt$TopAppBarLayout$1$2
                public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((SemanticsPropertyReceiver) obj);
                    return Unit.INSTANCE;
                }
            }) : companion2), 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131067, null);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1334graphicsLayerAp8cVGQ$default);
            Function0 constructor3 = companion.getConstructor();
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM1035constructorimpl3 = Updater.m1035constructorimpl(composerStartRestartGroup);
            Updater.m1036setimpl(composerM1035constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, companion.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl3, currentCompositionLocalMap3, companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash3 = companion.getSetCompositeKeyHash();
            if (composerM1035constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM1035constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM1035constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m1036setimpl(composerM1035constructorimpl3, modifierMaterializeModifier3, companion.getSetModifier());
            int i7 = i4 >> 9;
            ProvideContentColorTextStyleKt.m782ProvideContentColorTextStyle3JVO9M(j2, textStyle, function2, composerStartRestartGroup, ((i4 >> 15) & 112) | (i7 & 14) | (i7 & 896));
            composerStartRestartGroup.endNode();
            Modifier modifierM265paddingqDBjuR0$default2 = PaddingKt.m265paddingqDBjuR0$default(LayoutIdKt.layoutId(companion2, "actionIcons"), 0.0f, 0.0f, f2, 0.0f, 11, null);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM265paddingqDBjuR0$default2);
            Function0 constructor4 = companion.getConstructor();
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor4);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM1035constructorimpl4 = Updater.m1035constructorimpl(composerStartRestartGroup);
            Updater.m1036setimpl(composerM1035constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy3, companion.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl4, currentCompositionLocalMap4, companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash4 = companion.getSetCompositeKeyHash();
            if (composerM1035constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM1035constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM1035constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m1036setimpl(composerM1035constructorimpl4, modifierMaterializeModifier4, companion.getSetModifier());
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m1290boximpl(j3)), function23, composerStartRestartGroup, ((i5 >> 6) & 112) | i6);
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$TopAppBarLayout$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i8) {
                    AppBarKt.m600TopAppBarLayoutkXwM9vE(modifier, scrolledOffset, j, j2, j3, function2, textStyle, f, vertical, horizontal, i, z, function22, function23, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
                }
            });
        }
    }

    static {
        float f = 16;
        float f2 = 12;
        float fM2416constructorimpl = Dp.m2416constructorimpl(Dp.m2416constructorimpl(f) - Dp.m2416constructorimpl(f2));
        BottomAppBarHorizontalPadding = fM2416constructorimpl;
        float fM2416constructorimpl2 = Dp.m2416constructorimpl(Dp.m2416constructorimpl(f) - Dp.m2416constructorimpl(f2));
        BottomAppBarVerticalPadding = fM2416constructorimpl2;
        FABHorizontalPadding = Dp.m2416constructorimpl(Dp.m2416constructorimpl(f) - fM2416constructorimpl);
        FABVerticalPadding = Dp.m2416constructorimpl(Dp.m2416constructorimpl(f2) - fM2416constructorimpl2);
        TopTitleAlphaEasing = new CubicBezierEasing(0.8f, 0.0f, 0.8f, 0.15f);
        MediumTitleBottomPadding = Dp.m2416constructorimpl(24);
        LargeTitleBottomPadding = Dp.m2416constructorimpl(28);
        float fM2416constructorimpl3 = Dp.m2416constructorimpl(4);
        TopAppBarHorizontalPadding = fM2416constructorimpl3;
        TopAppBarTitleInset = Dp.m2416constructorimpl(Dp.m2416constructorimpl(f) - fM2416constructorimpl3);
    }
}
