package org.fdroid.fdroid.views.repos;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;
import org.fdroid.fdroid.ui.theme.ThemeKt;

/* JADX INFO: compiled from: RepoProgressScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a\r\u0010\u0007\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"RepoProgressScreen", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", TextBundle.TEXT_ENTRY, "", "(Landroidx/compose/foundation/layout/PaddingValues;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "FetchingRepoScreenPreview", "(Landroidx/compose/runtime/Composer;I)V", "app_fullRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RepoProgressScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FetchingRepoScreenPreview$lambda$2(int i, Composer composer, int i2) {
        FetchingRepoScreenPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoProgressScreen$lambda$1(PaddingValues paddingValues, String str, int i, Composer composer, int i2) {
        RepoProgressScreen(paddingValues, str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void RepoProgressScreen(final PaddingValues paddingValues, final String text, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
        Intrinsics.checkNotNullParameter(text, "text");
        Composer composerStartRestartGroup = composer.startRestartGroup(691060111);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(paddingValues) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(text) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(691060111, i2, -1, "org.fdroid.fdroid.views.repos.RepoProgressScreen (RepoProgressScreen.kt:22)");
            }
            Arrangement arrangement = Arrangement.INSTANCE;
            float f = 16;
            float fM2416constructorimpl = Dp.m2416constructorimpl(f);
            Alignment.Companion companion = Alignment.Companion;
            Arrangement.Vertical verticalM246spacedByD5KLDUw = arrangement.m246spacedByD5KLDUw(fM2416constructorimpl, companion.getCenterVertically());
            Alignment.Horizontal centerHorizontally = companion.getCenterHorizontally();
            Modifier.Companion companion2 = Modifier.Companion;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(PaddingKt.padding(PaddingKt.m261padding3ABfNKs(companion2, Dp.m2416constructorimpl(f)), paddingValues), 0.0f, 1, null);
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM246spacedByD5KLDUw, centerHorizontally, composerStartRestartGroup, 54);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxSize$default);
            ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
            Function0 constructor = companion3.getConstructor();
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
            Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyColumnMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion3.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m772Text4IGK_g(text, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getHeadlineSmall(), composer2, (i2 >> 3) & 14, 0, 65534);
            ProgressIndicatorKt.m716CircularProgressIndicatorLxG7B9w(SizeKt.m281size3ABfNKs(companion2, Dp.m2416constructorimpl(64)), 0L, 0.0f, 0L, 0, composer2, 6, 30);
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoProgressScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoProgressScreenKt.RepoProgressScreen$lambda$1(paddingValues, text, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void FetchingRepoScreenPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1123846634);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1123846634, i, -1, "org.fdroid.fdroid.views.repos.FetchingRepoScreenPreview (RepoProgressScreen.kt:41)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$RepoProgressScreenKt.INSTANCE.m3211getLambda1$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoProgressScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoProgressScreenKt.FetchingRepoScreenPreview$lambda$2(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
