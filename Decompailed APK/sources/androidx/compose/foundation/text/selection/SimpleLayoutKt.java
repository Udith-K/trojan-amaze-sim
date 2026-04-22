package androidx.compose.foundation.text.selection;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SimpleLayout.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SimpleLayoutKt {
    public static final void SimpleLayout(final Modifier modifier, final Function2 function2, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2105228848);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i3 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2105228848, i3, -1, "androidx.compose.foundation.text.selection.SimpleLayout (SimpleLayout.kt:31)");
            }
            AnonymousClass1 anonymousClass1 = new MeasurePolicy() { // from class: androidx.compose.foundation.text.selection.SimpleLayoutKt.SimpleLayout.1
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                    return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i5);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                    return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i5);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                    return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i5);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i5) {
                    return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i5);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo21measure3p2s80s(MeasureScope measureScope, List list, long j) {
                    final ArrayList arrayList = new ArrayList(list.size());
                    int size = list.size();
                    Integer numValueOf = 0;
                    for (int i5 = 0; i5 < size; i5++) {
                        arrayList.add(((Measurable) list.get(i5)).mo1743measureBRTryo0(j));
                    }
                    int size2 = arrayList.size();
                    Integer numValueOf2 = numValueOf;
                    for (int i6 = 0; i6 < size2; i6++) {
                        numValueOf2 = Integer.valueOf(Math.max(numValueOf2.intValue(), ((Placeable) arrayList.get(i6)).getWidth()));
                    }
                    int iIntValue = numValueOf2.intValue();
                    int size3 = arrayList.size();
                    for (int i7 = 0; i7 < size3; i7++) {
                        numValueOf = Integer.valueOf(Math.max(numValueOf.intValue(), ((Placeable) arrayList.get(i7)).getHeight()));
                    }
                    return MeasureScope.CC.layout$default(measureScope, iIntValue, numValueOf.intValue(), null, new Function1() { // from class: androidx.compose.foundation.text.selection.SimpleLayoutKt.SimpleLayout.1.1
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
                            List list2 = arrayList;
                            int size4 = list2.size();
                            for (int i8 = 0; i8 < size4; i8++) {
                                Placeable.PlacementScope.place$default(placementScope, (Placeable) list2.get(i8), 0, 0, 0.0f, 4, null);
                            }
                        }
                    }, 4, null);
                }
            };
            int i5 = ((i3 >> 3) & 14) | 384 | ((i3 << 3) & 112);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
            ComposeUiNode.Companion companion = ComposeUiNode.Companion;
            Function0 constructor = companion.getConstructor();
            int i6 = ((i5 << 6) & 896) | 6;
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
            Updater.m1036setimpl(composerM1035constructorimpl, anonymousClass1, companion.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            function2.invoke(composerStartRestartGroup, Integer.valueOf((i6 >> 6) & 14));
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.SimpleLayoutKt.SimpleLayout.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i7) {
                    SimpleLayoutKt.SimpleLayout(modifier, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }
}
