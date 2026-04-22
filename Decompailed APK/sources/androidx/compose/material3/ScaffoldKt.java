package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.material3.FabPosition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: Scaffold.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ScaffoldKt {
    private static final float FabSpacing = Dp.m2416constructorimpl(16);

    /* JADX WARN: Removed duplicated region for block: B:100:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:198:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x010c  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: Scaffold-TvnljyQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m729ScaffoldTvnljyQ(androidx.compose.ui.Modifier r29, kotlin.jvm.functions.Function2 r30, kotlin.jvm.functions.Function2 r31, kotlin.jvm.functions.Function2 r32, kotlin.jvm.functions.Function2 r33, int r34, long r35, long r37, androidx.compose.foundation.layout.WindowInsets r39, final kotlin.jvm.functions.Function3 r40, androidx.compose.runtime.Composer r41, final int r42, final int r43) {
        /*
            Method dump skipped, instruction units count: 738
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ScaffoldKt.m729ScaffoldTvnljyQ(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, int, long, long, androidx.compose.foundation.layout.WindowInsets, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ScaffoldLayout-FMILGgc, reason: not valid java name */
    public static final void m730ScaffoldLayoutFMILGgc(final int i, final Function2 function2, final Function3 function3, final Function2 function22, final Function2 function23, final WindowInsets windowInsets, final Function2 function24, Composer composer, final int i2) {
        int i3;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-975511942);
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function22) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function23) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(windowInsets) ? 131072 : PKIFailureInfo.notAuthorized;
        }
        if ((i2 & 1572864) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function24) ? PKIFailureInfo.badCertTemplate : PKIFailureInfo.signerNotTrusted;
        }
        if ((i3 & 599187) != 599186 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-975511942, i3, -1, "androidx.compose.material3.ScaffoldLayout (Scaffold.kt:138)");
            }
            boolean z = ((i3 & 112) == 32) | ((i3 & 7168) == 2048) | ((458752 & i3) == 131072) | ((57344 & i3) == 16384) | ((i3 & 14) == 4) | ((3670016 & i3) == 1048576) | ((i3 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.Companion.getEmpty()) {
                i4 = 1;
                objRememberedValue = new Function2() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        return m732invoke0kLqBqw((SubcomposeMeasureScope) obj, ((Constraints) obj2).m2395unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
                    public final MeasureResult m732invoke0kLqBqw(final SubcomposeMeasureScope subcomposeMeasureScope, long j) {
                        Object obj;
                        Object obj2;
                        Object obj3;
                        FabPlacement fabPlacement;
                        Object obj4;
                        Integer numValueOf;
                        int height;
                        int bottom;
                        Object obj5;
                        Object obj6;
                        int iMo204roundToPx0680j_4;
                        int iMo204roundToPx0680j_42;
                        final int iM2389getMaxWidthimpl = Constraints.m2389getMaxWidthimpl(j);
                        final int iM2388getMaxHeightimpl = Constraints.m2388getMaxHeightimpl(j);
                        long jM2381copyZbe2FdA$default = Constraints.m2381copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
                        List listSubcompose = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.TopBar, function2);
                        final ArrayList arrayList = new ArrayList(listSubcompose.size());
                        int size = listSubcompose.size();
                        for (int i5 = 0; i5 < size; i5++) {
                            arrayList.add(((Measurable) listSubcompose.get(i5)).mo1743measureBRTryo0(jM2381copyZbe2FdA$default));
                        }
                        if (arrayList.isEmpty()) {
                            obj = null;
                        } else {
                            obj = arrayList.get(0);
                            int height2 = ((Placeable) obj).getHeight();
                            int lastIndex = CollectionsKt.getLastIndex(arrayList);
                            if (1 <= lastIndex) {
                                int i6 = 1;
                                while (true) {
                                    Object obj7 = arrayList.get(i6);
                                    int height3 = ((Placeable) obj7).getHeight();
                                    if (height2 < height3) {
                                        obj = obj7;
                                        height2 = height3;
                                    }
                                    if (i6 == lastIndex) {
                                        break;
                                    }
                                    i6++;
                                }
                            }
                        }
                        Placeable placeable = (Placeable) obj;
                        final int height4 = placeable != null ? placeable.getHeight() : 0;
                        List listSubcompose2 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Snackbar, function22);
                        WindowInsets windowInsets2 = windowInsets;
                        final ArrayList arrayList2 = new ArrayList(listSubcompose2.size());
                        int size2 = listSubcompose2.size();
                        for (int i7 = 0; i7 < size2; i7++) {
                            arrayList2.add(((Measurable) listSubcompose2.get(i7)).mo1743measureBRTryo0(ConstraintsKt.m2404offsetNN6EwU(jM2381copyZbe2FdA$default, (-windowInsets2.getLeft(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection())) - windowInsets2.getRight(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection()), -windowInsets2.getBottom(subcomposeMeasureScope))));
                        }
                        if (arrayList2.isEmpty()) {
                            obj2 = null;
                        } else {
                            obj2 = arrayList2.get(0);
                            int height5 = ((Placeable) obj2).getHeight();
                            int lastIndex2 = CollectionsKt.getLastIndex(arrayList2);
                            if (1 <= lastIndex2) {
                                Object obj8 = obj2;
                                int i8 = height5;
                                int i9 = 1;
                                while (true) {
                                    Object obj9 = arrayList2.get(i9);
                                    int height6 = ((Placeable) obj9).getHeight();
                                    if (i8 < height6) {
                                        obj8 = obj9;
                                        i8 = height6;
                                    }
                                    if (i9 == lastIndex2) {
                                        break;
                                    }
                                    i9++;
                                }
                                obj2 = obj8;
                            }
                        }
                        Placeable placeable2 = (Placeable) obj2;
                        int height7 = placeable2 != null ? placeable2.getHeight() : 0;
                        if (arrayList2.isEmpty()) {
                            obj3 = null;
                        } else {
                            obj3 = arrayList2.get(0);
                            int width = ((Placeable) obj3).getWidth();
                            int lastIndex3 = CollectionsKt.getLastIndex(arrayList2);
                            if (1 <= lastIndex3) {
                                Object obj10 = obj3;
                                int i10 = width;
                                int i11 = 1;
                                while (true) {
                                    Object obj11 = arrayList2.get(i11);
                                    int width2 = ((Placeable) obj11).getWidth();
                                    if (i10 < width2) {
                                        obj10 = obj11;
                                        i10 = width2;
                                    }
                                    if (i11 == lastIndex3) {
                                        break;
                                    }
                                    i11++;
                                }
                                obj3 = obj10;
                            }
                        }
                        Placeable placeable3 = (Placeable) obj3;
                        int width3 = placeable3 != null ? placeable3.getWidth() : 0;
                        List listSubcompose3 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Fab, function23);
                        WindowInsets windowInsets3 = windowInsets;
                        final ArrayList arrayList3 = new ArrayList(listSubcompose3.size());
                        int size3 = listSubcompose3.size();
                        int i12 = 0;
                        while (i12 < size3) {
                            List list = listSubcompose3;
                            int i13 = size3;
                            WindowInsets windowInsets4 = windowInsets3;
                            Placeable placeableMo1743measureBRTryo0 = ((Measurable) listSubcompose3.get(i12)).mo1743measureBRTryo0(ConstraintsKt.m2404offsetNN6EwU(jM2381copyZbe2FdA$default, (-windowInsets3.getLeft(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection())) - windowInsets3.getRight(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection()), -windowInsets3.getBottom(subcomposeMeasureScope)));
                            if (placeableMo1743measureBRTryo0.getHeight() == 0 || placeableMo1743measureBRTryo0.getWidth() == 0) {
                                placeableMo1743measureBRTryo0 = null;
                            }
                            if (placeableMo1743measureBRTryo0 != null) {
                                arrayList3.add(placeableMo1743measureBRTryo0);
                            }
                            i12++;
                            windowInsets3 = windowInsets4;
                            listSubcompose3 = list;
                            size3 = i13;
                        }
                        if (arrayList3.isEmpty()) {
                            fabPlacement = null;
                        } else {
                            if (arrayList3.isEmpty()) {
                                obj5 = null;
                            } else {
                                obj5 = arrayList3.get(0);
                                int width4 = ((Placeable) obj5).getWidth();
                                int lastIndex4 = CollectionsKt.getLastIndex(arrayList3);
                                if (1 <= lastIndex4) {
                                    Object obj12 = obj5;
                                    int i14 = width4;
                                    int i15 = 1;
                                    while (true) {
                                        Object obj13 = arrayList3.get(i15);
                                        int width5 = ((Placeable) obj13).getWidth();
                                        if (i14 < width5) {
                                            obj12 = obj13;
                                            i14 = width5;
                                        }
                                        if (i15 == lastIndex4) {
                                            break;
                                        }
                                        i15++;
                                    }
                                    obj5 = obj12;
                                }
                            }
                            Intrinsics.checkNotNull(obj5);
                            int width6 = ((Placeable) obj5).getWidth();
                            if (arrayList3.isEmpty()) {
                                obj6 = null;
                            } else {
                                obj6 = arrayList3.get(0);
                                int height8 = ((Placeable) obj6).getHeight();
                                int lastIndex5 = CollectionsKt.getLastIndex(arrayList3);
                                if (1 <= lastIndex5) {
                                    Object obj14 = obj6;
                                    int i16 = height8;
                                    int i17 = 1;
                                    while (true) {
                                        Object obj15 = arrayList3.get(i17);
                                        Object obj16 = obj14;
                                        int height9 = ((Placeable) obj15).getHeight();
                                        if (i16 < height9) {
                                            i16 = height9;
                                            obj14 = obj15;
                                        } else {
                                            obj14 = obj16;
                                        }
                                        if (i17 == lastIndex5) {
                                            break;
                                        }
                                        i17++;
                                    }
                                    obj6 = obj14;
                                }
                            }
                            Intrinsics.checkNotNull(obj6);
                            int height10 = ((Placeable) obj6).getHeight();
                            int i18 = i;
                            FabPosition.Companion companion = FabPosition.Companion;
                            if (!FabPosition.m675equalsimpl0(i18, companion.m678getStartERTFSPs())) {
                                if (!(FabPosition.m675equalsimpl0(i18, companion.m676getEndERTFSPs()) ? true : FabPosition.m675equalsimpl0(i18, companion.m677getEndOverlayERTFSPs()))) {
                                    iMo204roundToPx0680j_4 = (iM2389getMaxWidthimpl - width6) / 2;
                                } else if (subcomposeMeasureScope.getLayoutDirection() == LayoutDirection.Ltr) {
                                    iMo204roundToPx0680j_42 = subcomposeMeasureScope.mo204roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                    iMo204roundToPx0680j_4 = (iM2389getMaxWidthimpl - iMo204roundToPx0680j_42) - width6;
                                } else {
                                    iMo204roundToPx0680j_4 = subcomposeMeasureScope.mo204roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                }
                                fabPlacement = new FabPlacement(iMo204roundToPx0680j_4, width6, height10);
                            } else if (subcomposeMeasureScope.getLayoutDirection() == LayoutDirection.Ltr) {
                                iMo204roundToPx0680j_4 = subcomposeMeasureScope.mo204roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                fabPlacement = new FabPlacement(iMo204roundToPx0680j_4, width6, height10);
                            } else {
                                iMo204roundToPx0680j_42 = subcomposeMeasureScope.mo204roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                iMo204roundToPx0680j_4 = (iM2389getMaxWidthimpl - iMo204roundToPx0680j_42) - width6;
                                fabPlacement = new FabPlacement(iMo204roundToPx0680j_4, width6, height10);
                            }
                        }
                        ScaffoldLayoutContent scaffoldLayoutContent = ScaffoldLayoutContent.BottomBar;
                        final Function2 function25 = function24;
                        List listSubcompose4 = subcomposeMeasureScope.subcompose(scaffoldLayoutContent, ComposableLambdaKt.composableLambdaInstance(-2146438447, true, new Function2() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1$bottomBarPlaceables$1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj17, Object obj18) {
                                invoke((Composer) obj17, ((Number) obj18).intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i19) {
                                if ((i19 & 3) == 2 && composer2.getSkipping()) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-2146438447, i19, -1, "androidx.compose.material3.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:209)");
                                }
                                function25.invoke(composer2, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                        final ArrayList arrayList4 = new ArrayList(listSubcompose4.size());
                        int size4 = listSubcompose4.size();
                        for (int i19 = 0; i19 < size4; i19++) {
                            arrayList4.add(((Measurable) listSubcompose4.get(i19)).mo1743measureBRTryo0(jM2381copyZbe2FdA$default));
                        }
                        if (arrayList4.isEmpty()) {
                            obj4 = null;
                        } else {
                            obj4 = arrayList4.get(0);
                            int height11 = ((Placeable) obj4).getHeight();
                            int lastIndex6 = CollectionsKt.getLastIndex(arrayList4);
                            if (1 <= lastIndex6) {
                                int i20 = 1;
                                while (true) {
                                    Object obj17 = arrayList4.get(i20);
                                    int height12 = ((Placeable) obj17).getHeight();
                                    if (height11 < height12) {
                                        height11 = height12;
                                        obj4 = obj17;
                                    }
                                    if (i20 == lastIndex6) {
                                        break;
                                    }
                                    i20++;
                                }
                            }
                        }
                        Placeable placeable4 = (Placeable) obj4;
                        Integer numValueOf2 = placeable4 != null ? Integer.valueOf(placeable4.getHeight()) : null;
                        if (fabPlacement != null) {
                            int i21 = i;
                            WindowInsets windowInsets5 = windowInsets;
                            if (numValueOf2 == null || FabPosition.m675equalsimpl0(i21, FabPosition.Companion.m677getEndOverlayERTFSPs())) {
                                height = fabPlacement.getHeight() + subcomposeMeasureScope.mo204roundToPx0680j_4(ScaffoldKt.FabSpacing);
                                bottom = windowInsets5.getBottom(subcomposeMeasureScope);
                            } else {
                                height = numValueOf2.intValue() + fabPlacement.getHeight();
                                bottom = subcomposeMeasureScope.mo204roundToPx0680j_4(ScaffoldKt.FabSpacing);
                            }
                            numValueOf = Integer.valueOf(height + bottom);
                        } else {
                            numValueOf = null;
                        }
                        int iIntValue = height7 != 0 ? height7 + (numValueOf != null ? numValueOf.intValue() : numValueOf2 != null ? numValueOf2.intValue() : windowInsets.getBottom(subcomposeMeasureScope)) : 0;
                        ScaffoldLayoutContent scaffoldLayoutContent2 = ScaffoldLayoutContent.MainContent;
                        final WindowInsets windowInsets6 = windowInsets;
                        final Function3 function32 = function3;
                        final int i22 = width3;
                        final Integer num = numValueOf2;
                        List listSubcompose5 = subcomposeMeasureScope.subcompose(scaffoldLayoutContent2, ComposableLambdaKt.composableLambdaInstance(-1213360416, true, new Function2() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1$bodyContentPlaceables$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj18, Object obj19) {
                                invoke((Composer) obj18, ((Number) obj19).intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer2, int i23) {
                                float fMo207toDpu2uoSUM;
                                float fMo252calculateBottomPaddingD9Ej5fM;
                                Integer num2;
                                if ((i23 & 3) != 2 || !composer2.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1213360416, i23, -1, "androidx.compose.material3.ScaffoldLayout.<anonymous>.<anonymous>.<anonymous> (Scaffold.kt:238)");
                                    }
                                    PaddingValues paddingValuesAsPaddingValues = WindowInsetsKt.asPaddingValues(windowInsets6, subcomposeMeasureScope);
                                    if (arrayList.isEmpty()) {
                                        fMo207toDpu2uoSUM = paddingValuesAsPaddingValues.mo255calculateTopPaddingD9Ej5fM();
                                    } else {
                                        fMo207toDpu2uoSUM = subcomposeMeasureScope.mo207toDpu2uoSUM(height4);
                                    }
                                    if (arrayList4.isEmpty() || (num2 = num) == null) {
                                        fMo252calculateBottomPaddingD9Ej5fM = paddingValuesAsPaddingValues.mo252calculateBottomPaddingD9Ej5fM();
                                    } else {
                                        fMo252calculateBottomPaddingD9Ej5fM = subcomposeMeasureScope.mo207toDpu2uoSUM(num2.intValue());
                                    }
                                    function32.invoke(PaddingKt.m260PaddingValuesa9UjIt4(PaddingKt.calculateStartPadding(paddingValuesAsPaddingValues, subcomposeMeasureScope.getLayoutDirection()), fMo207toDpu2uoSUM, PaddingKt.calculateEndPadding(paddingValuesAsPaddingValues, subcomposeMeasureScope.getLayoutDirection()), fMo252calculateBottomPaddingD9Ej5fM), composer2, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer2.skipToGroupEnd();
                            }
                        }));
                        final ArrayList arrayList5 = new ArrayList(listSubcompose5.size());
                        int size5 = listSubcompose5.size();
                        for (int i23 = 0; i23 < size5; i23++) {
                            arrayList5.add(((Measurable) listSubcompose5.get(i23)).mo1743measureBRTryo0(jM2381copyZbe2FdA$default));
                        }
                        final WindowInsets windowInsets7 = windowInsets;
                        final FabPlacement fabPlacement2 = fabPlacement;
                        final int i24 = iIntValue;
                        final Integer num2 = numValueOf2;
                        final Integer num3 = numValueOf;
                        return MeasureScope.CC.layout$default(subcomposeMeasureScope, iM2389getMaxWidthimpl, iM2388getMaxHeightimpl, null, new Function1() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj18) {
                                invoke((Placeable.PlacementScope) obj18);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Placeable.PlacementScope placementScope) {
                                List list2 = arrayList5;
                                int size6 = list2.size();
                                for (int i25 = 0; i25 < size6; i25++) {
                                    Placeable.PlacementScope.place$default(placementScope, (Placeable) list2.get(i25), 0, 0, 0.0f, 4, null);
                                }
                                List list3 = arrayList;
                                int size7 = list3.size();
                                for (int i26 = 0; i26 < size7; i26++) {
                                    Placeable.PlacementScope.place$default(placementScope, (Placeable) list3.get(i26), 0, 0, 0.0f, 4, null);
                                }
                                List list4 = arrayList2;
                                int i27 = iM2389getMaxWidthimpl;
                                int i28 = i22;
                                WindowInsets windowInsets8 = windowInsets7;
                                SubcomposeMeasureScope subcomposeMeasureScope2 = subcomposeMeasureScope;
                                int i29 = iM2388getMaxHeightimpl;
                                int i30 = i24;
                                int size8 = list4.size();
                                for (int i31 = 0; i31 < size8; i31++) {
                                    Placeable.PlacementScope.place$default(placementScope, (Placeable) list4.get(i31), ((i27 - i28) / 2) + windowInsets8.getLeft(subcomposeMeasureScope2, subcomposeMeasureScope2.getLayoutDirection()), i29 - i30, 0.0f, 4, null);
                                }
                                List list5 = arrayList4;
                                int i32 = iM2388getMaxHeightimpl;
                                Integer num4 = num2;
                                int size9 = list5.size();
                                for (int i33 = 0; i33 < size9; i33++) {
                                    Placeable.PlacementScope.place$default(placementScope, (Placeable) list5.get(i33), 0, i32 - (num4 != null ? num4.intValue() : 0), 0.0f, 4, null);
                                }
                                FabPlacement fabPlacement3 = fabPlacement2;
                                if (fabPlacement3 != null) {
                                    List list6 = arrayList3;
                                    int i34 = iM2388getMaxHeightimpl;
                                    Integer num5 = num3;
                                    int size10 = list6.size();
                                    for (int i35 = 0; i35 < size10; i35++) {
                                        Placeable placeable5 = (Placeable) list6.get(i35);
                                        int left = fabPlacement3.getLeft();
                                        Intrinsics.checkNotNull(num5);
                                        Placeable.PlacementScope.place$default(placementScope, placeable5, left, i34 - num5.intValue(), 0.0f, 4, null);
                                    }
                                }
                            }
                        }, 4, null);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                i4 = 1;
            }
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) objRememberedValue, composerStartRestartGroup, 0, i4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i5) {
                    ScaffoldKt.m730ScaffoldLayoutFMILGgc(i, function2, function3, function22, function23, windowInsets, function24, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                }
            });
        }
    }
}
