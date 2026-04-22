package androidx.compose.foundation.text;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
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
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnnotatedStringResolveInlineContent.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AnnotatedStringResolveInlineContentKt {
    private static final Pair EmptyInlineContent = new Pair(CollectionsKt.emptyList(), CollectionsKt.emptyList());

    public static final Pair resolveInlineContent(AnnotatedString annotatedString, Map map) {
        if (map == null || map.isEmpty()) {
            return EmptyInlineContent;
        }
        List stringAnnotations = annotatedString.getStringAnnotations("androidx.compose.foundation.text.inlineContent", 0, annotatedString.getText().length());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int size = stringAnnotations.size();
        for (int i = 0; i < size; i++) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(map.get(((AnnotatedString.Range) stringAnnotations.get(i)).getItem()));
        }
        return new Pair(arrayList, arrayList2);
    }

    public static final boolean hasInlineContent(AnnotatedString annotatedString) {
        return annotatedString.hasStringAnnotations("androidx.compose.foundation.text.inlineContent", 0, annotatedString.getText().length());
    }

    public static final void InlineChildren(final AnnotatedString annotatedString, final List list, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1794596951);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(annotatedString) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(list) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1794596951, i2, -1, "androidx.compose.foundation.text.InlineChildren (AnnotatedStringResolveInlineContent.kt:73)");
            }
            int size = list.size();
            int i3 = 0;
            int i4 = 0;
            while (i4 < size) {
                AnnotatedString.Range range = (AnnotatedString.Range) list.get(i4);
                Function3 function3 = (Function3) range.component1();
                int iComponent2 = range.component2();
                int iComponent3 = range.component3();
                AnnotatedStringResolveInlineContentKt$InlineChildren$1$2 annotatedStringResolveInlineContentKt$InlineChildren$1$2 = new MeasurePolicy() { // from class: androidx.compose.foundation.text.AnnotatedStringResolveInlineContentKt$InlineChildren$1$2
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list2, int i5) {
                        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list2, i5);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list2, int i5) {
                        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list2, i5);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list2, int i5) {
                        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list2, i5);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list2, int i5) {
                        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list2, i5);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo21measure3p2s80s(MeasureScope measureScope, List list2, long j) {
                        final ArrayList arrayList = new ArrayList(list2.size());
                        int size2 = list2.size();
                        for (int i5 = 0; i5 < size2; i5++) {
                            arrayList.add(((Measurable) list2.get(i5)).mo1743measureBRTryo0(j));
                        }
                        return MeasureScope.CC.layout$default(measureScope, Constraints.m2389getMaxWidthimpl(j), Constraints.m2388getMaxHeightimpl(j), null, new Function1() { // from class: androidx.compose.foundation.text.AnnotatedStringResolveInlineContentKt$InlineChildren$1$2.1
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
                                List list3 = arrayList;
                                int size3 = list3.size();
                                for (int i6 = 0; i6 < size3; i6++) {
                                    Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) list3.get(i6), 0, 0, 0.0f, 4, null);
                                }
                            }
                        }, 4, null);
                    }
                };
                Modifier.Companion companion = Modifier.Companion;
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, i3);
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
                Function0 constructor = companion2.getConstructor();
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
                Updater.m1036setimpl(composerM1035constructorimpl, annotatedStringResolveInlineContentKt$InlineChildren$1$2, companion2.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                function3.invoke(annotatedString.subSequence(iComponent2, iComponent3).getText(), composerStartRestartGroup, 0);
                composerStartRestartGroup.endNode();
                i4++;
                i3 = 0;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.AnnotatedStringResolveInlineContentKt.InlineChildren.2
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
                    AnnotatedStringResolveInlineContentKt.InlineChildren(annotatedString, list, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }
}
