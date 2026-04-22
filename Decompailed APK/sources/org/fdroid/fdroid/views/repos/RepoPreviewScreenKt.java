package org.fdroid.fdroid.views.repos;

import android.content.Context;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material3.CardKt;
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
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.InspectionModeKt;
import androidx.compose.ui.res.ColorResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum;
import androidx.compose.ui.unit.Dp;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.os.LocaleListCompat;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.integration.compose.GlideImageKt;
import com.bumptech.glide.request.BaseRequestOptions;
import com.google.accompanist.drawablepainter.DrawablePainterKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.fdroid.database.MinimalApp;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.compose.ComposeUtils;
import org.fdroid.fdroid.compose.ComposeUtilsKt;
import org.fdroid.fdroid.ui.theme.ThemeKt;
import org.fdroid.fdroid.views.repos.RepoPreviewScreenKt;
import org.fdroid.index.v2.FileV2;
import org.fdroid.repo.FetchResult;
import org.fdroid.repo.Fetching;

/* JADX INFO: compiled from: RepoPreviewScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a+\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0002\u0010\f\u001a)\u0010\r\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0002\u0010\u0013\u001a\r\u0010\u0014\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0015\u001a\r\u0010\u0016\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0015\u001a\r\u0010\u0017\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0015\u001a\r\u0010\u0018\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0015\u001a\r\u0010\u0019\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0015¨\u0006\u001a"}, d2 = {"RepoPreviewScreen", "", "state", "Lorg/fdroid/repo/Fetching;", "modifier", "Landroidx/compose/ui/Modifier;", "onAddRepo", "Lkotlin/Function0;", "(Lorg/fdroid/repo/Fetching;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "RepoPreviewHeader", "localeList", "Landroidx/core/os/LocaleListCompat;", "(Lorg/fdroid/repo/Fetching;Lkotlin/jvm/functions/Function0;Landroidx/core/os/LocaleListCompat;Landroidx/compose/runtime/Composer;I)V", "RepoPreviewApp", "Landroidx/compose/foundation/lazy/LazyItemScope;", "repo", "Lorg/fdroid/database/Repository;", "app", "Lorg/fdroid/database/MinimalApp;", "(Landroidx/compose/foundation/lazy/LazyItemScope;Lorg/fdroid/database/Repository;Lorg/fdroid/database/MinimalApp;Landroidx/core/os/LocaleListCompat;Landroidx/compose/runtime/Composer;I)V", "RepoPreviewScreenFetchingPreview", "(Landroidx/compose/runtime/Composer;I)V", "RepoPreviewScreenNewMirrorPreview", "RepoPreviewScreenNewRepoAndNewMirrorPreview", "RepoPreviewScreenExistingRepoPreview", "RepoPreviewScreenExistingMirrorPreview", "app_fullRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RepoPreviewScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoPreviewApp$lambda$14(LazyItemScope lazyItemScope, Repository repository, MinimalApp minimalApp, LocaleListCompat localeListCompat, int i, Composer composer, int i2) {
        RepoPreviewApp(lazyItemScope, repository, minimalApp, localeListCompat, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoPreviewHeader$lambda$13(Fetching fetching, Function0 function0, LocaleListCompat localeListCompat, int i, Composer composer, int i2) {
        RepoPreviewHeader(fetching, function0, localeListCompat, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoPreviewScreen$lambda$4(Fetching fetching, Modifier modifier, Function0 function0, int i, int i2, Composer composer, int i3) {
        RepoPreviewScreen(fetching, modifier, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoPreviewScreenExistingMirrorPreview$lambda$19(int i, Composer composer, int i2) {
        RepoPreviewScreenExistingMirrorPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoPreviewScreenExistingRepoPreview$lambda$18(int i, Composer composer, int i2) {
        RepoPreviewScreenExistingRepoPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoPreviewScreenFetchingPreview$lambda$15(int i, Composer composer, int i2) {
        RepoPreviewScreenFetchingPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoPreviewScreenNewMirrorPreview$lambda$16(int i, Composer composer, int i2) {
        RepoPreviewScreenNewMirrorPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoPreviewScreenNewRepoAndNewMirrorPreview$lambda$17(int i, Composer composer, int i2) {
        RepoPreviewScreenNewRepoAndNewMirrorPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void RepoPreviewScreen(final org.fdroid.repo.Fetching r18, androidx.compose.ui.Modifier r19, final kotlin.jvm.functions.Function0 r20, androidx.compose.runtime.Composer r21, final int r22, final int r23) {
        /*
            Method dump skipped, instruction units count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt.RepoPreviewScreen(org.fdroid.repo.Fetching, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoPreviewScreen$lambda$3$lambda$2(final Fetching fetching, final Function0 function0, final LocaleListCompat localeListCompat, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.CC.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1025264508, true, new Function3() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreen$1$1$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                invoke((LazyItemScope) obj, (Composer) obj2, ((Number) obj3).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope item, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(item, "$this$item");
                if ((i & 17) == 16 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1025264508, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreen.<anonymous>.<anonymous>.<anonymous> (RepoPreviewScreen.kt:73)");
                }
                RepoPreviewScreenKt.RepoPreviewHeader(fetching, function0, localeListCompat, composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }), 3, null);
        if (fetching.getFetchResult() == null || (fetching.getFetchResult() instanceof FetchResult.IsNewRepository) || (fetching.getFetchResult() instanceof FetchResult.IsNewRepoAndNewMirror)) {
            LazyListScope.CC.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-2043144439, true, new Function3() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreen$1$1$2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    invoke((LazyItemScope) obj, (Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope item, Composer composer, int i) {
                    Intrinsics.checkNotNullParameter(item, "$this$item");
                    if ((i & 17) == 16 && composer.getSkipping()) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2043144439, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreen.<anonymous>.<anonymous>.<anonymous> (RepoPreviewScreen.kt:80)");
                    }
                    Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                    float f = 8;
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM245spacedBy0680j_4 = Arrangement.INSTANCE.m245spacedBy0680j_4(Dp.m2416constructorimpl(f));
                    Modifier.Companion companion = Modifier.Companion;
                    Modifier modifierM265paddingqDBjuR0$default = PaddingKt.m265paddingqDBjuR0$default(companion, 0.0f, Dp.m2416constructorimpl(f), 0.0f, 0.0f, 13, null);
                    Fetching fetching2 = fetching;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM245spacedBy0680j_4, centerVertically, composer, 54);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM265paddingqDBjuR0$default);
                    ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
                    Function0 constructor = companion2.getConstructor();
                    if (!(composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer.startReusableNode();
                    if (composer.getInserting()) {
                        composer.createNode(constructor);
                    } else {
                        composer.useNode();
                    }
                    Composer composerM1035constructorimpl = Updater.m1035constructorimpl(composer);
                    Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    String strStringResource = StringResources_androidKt.stringResource(R.string.repo_preview_included_apps, composer, 0);
                    MaterialTheme materialTheme = MaterialTheme.INSTANCE;
                    int i2 = MaterialTheme.$stable;
                    TextKt.m772Text4IGK_g(strStringResource, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer, i2).getBodyLarge(), composer, 0, 0, 65534);
                    TextKt.m772Text4IGK_g(String.valueOf(fetching2.getApps().size()), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer, i2).getBodyLarge(), composer, 0, 0, 65534);
                    composer.startReplaceGroup(1156925551);
                    if (!fetching2.getDone()) {
                        ProgressIndicatorKt.m717LinearProgressIndicatorrIrjwxo(RowScope.CC.weight$default(rowScopeInstance, companion, 1.0f, false, 2, null), 0L, 0L, 0, 0.0f, composer, 0, 30);
                    }
                    composer.endReplaceGroup();
                    composer.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }), 3, null);
            final List<MinimalApp> apps = fetching.getApps();
            final Function1 function1 = new Function1() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepoPreviewScreenKt.RepoPreviewScreen$lambda$3$lambda$2$lambda$0((MinimalApp) obj);
                }
            };
            final RepoPreviewScreenKt$RepoPreviewScreen$lambda$3$lambda$2$$inlined$items$default$1 repoPreviewScreenKt$RepoPreviewScreen$lambda$3$lambda$2$$inlined$items$default$1 = new Function1() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreen$lambda$3$lambda$2$$inlined$items$default$1
                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(MinimalApp minimalApp) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke((MinimalApp) obj);
                }
            };
            LazyColumn.items(apps.size(), new Function1() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreen$lambda$3$lambda$2$$inlined$items$default$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke(((Number) obj).intValue());
                }

                public final Object invoke(int i) {
                    return function1.invoke(apps.get(i));
                }
            }, new Function1() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreen$lambda$3$lambda$2$$inlined$items$default$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke(((Number) obj).intValue());
                }

                public final Object invoke(int i) {
                    return repoPreviewScreenKt$RepoPreviewScreen$lambda$3$lambda$2$$inlined$items$default$1.invoke(apps.get(i));
                }
            }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreen$lambda$3$lambda$2$$inlined$items$default$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
                    int i3;
                    if ((i2 & 6) == 0) {
                        i3 = (composer.changed(lazyItemScope) ? 4 : 2) | i2;
                    } else {
                        i3 = i2;
                    }
                    if ((i2 & 48) == 0) {
                        i3 |= composer.changed(i) ? 32 : 16;
                    }
                    if ((i3 & 147) == 146 && composer.getSkipping()) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-632812321, i3, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                    }
                    MinimalApp minimalApp = (MinimalApp) apps.get(i);
                    composer.startReplaceGroup(1443117876);
                    Repository receivedRepo = fetching.getReceivedRepo();
                    if (receivedRepo != null) {
                        RepoPreviewScreenKt.RepoPreviewApp(lazyItemScope, receivedRepo, minimalApp, localeListCompat, composer, i3 & 14);
                        composer.endReplaceGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException("no repo");
                }
            }));
            LazyListScope.CC.item$default(LazyColumn, null, null, ComposableSingletons$RepoPreviewScreenKt.INSTANCE.m3210getLambda1$app_fullRelease(), 3, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object RepoPreviewScreen$lambda$3$lambda$2$lambda$0(MinimalApp it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getPackageName();
    }

    public static final void RepoPreviewHeader(final Fetching state, final Function0 onAddRepo, final LocaleListCompat localeList, Composer composer, final int i) {
        int i2;
        String strStringResource;
        Function0 function0;
        String strStringResource2;
        String str;
        Composer composer2;
        Modifier.Companion companion;
        MaterialTheme materialTheme;
        Function0 function02;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(onAddRepo, "onAddRepo");
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        Composer composerStartRestartGroup = composer.startRestartGroup(-2115582612);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(state) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onAddRepo) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(localeList) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2115582612, i2, -1, "org.fdroid.fdroid.views.repos.RepoPreviewHeader (RepoPreviewScreen.kt:111)");
            }
            Repository receivedRepo = state.getReceivedRepo();
            if (receivedRepo == null) {
                throw new IllegalStateException("repo was null");
            }
            boolean zBooleanValue = ((Boolean) composerStartRestartGroup.consume(InspectionModeKt.getLocalInspectionMode())).booleanValue();
            final Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            FetchResult fetchResult = state.getFetchResult();
            if (fetchResult instanceof FetchResult.IsNewRepository) {
                composerStartRestartGroup.startReplaceGroup(294258391);
                strStringResource = StringResources_androidKt.stringResource(R.string.repo_add_new_title, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else if (fetchResult instanceof FetchResult.IsNewRepoAndNewMirror) {
                composerStartRestartGroup.startReplaceGroup(294260957);
                strStringResource = StringResources_androidKt.stringResource(R.string.repo_add_repo_and_mirror, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else if (fetchResult instanceof FetchResult.IsNewMirror) {
                composerStartRestartGroup.startReplaceGroup(294263380);
                strStringResource = StringResources_androidKt.stringResource(R.string.repo_add_mirror, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                if (!(fetchResult instanceof FetchResult.IsExistingRepository) && !(fetchResult instanceof FetchResult.IsExistingMirror)) {
                    composerStartRestartGroup.startReplaceGroup(294268289);
                    composerStartRestartGroup.endReplaceGroup();
                    throw new IllegalStateException(("Unexpected fetch state: " + state.getFetchResult()).toString());
                }
                composerStartRestartGroup.startReplaceGroup(294266483);
                strStringResource = StringResources_androidKt.stringResource(R.string.repo_view_repo, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            }
            String str2 = strStringResource;
            final FetchResult fetchResult2 = state.getFetchResult();
            if ((fetchResult2 instanceof FetchResult.IsNewRepository) || (fetchResult2 instanceof FetchResult.IsNewRepoAndNewMirror) || (fetchResult2 instanceof FetchResult.IsNewMirror)) {
                composerStartRestartGroup.startReplaceGroup(294274773);
                composerStartRestartGroup.endReplaceGroup();
                function0 = onAddRepo;
            } else {
                if (fetchResult2 instanceof FetchResult.IsExistingRepository) {
                    composerStartRestartGroup.startReplaceGroup(532693795);
                    composerStartRestartGroup.startReplaceGroup(294278759);
                    boolean zChangedInstance = composerStartRestartGroup.changedInstance(fetchResult2) | composerStartRestartGroup.changedInstance(context);
                    Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RepoPreviewScreenKt.RepoPreviewHeader$lambda$6$lambda$5(fetchResult2, context);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    function02 = (Function0) objRememberedValue;
                    composerStartRestartGroup.endReplaceGroup();
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    if (!(fetchResult2 instanceof FetchResult.IsExistingMirror)) {
                        composerStartRestartGroup.startReplaceGroup(294289281);
                        composerStartRestartGroup.endReplaceGroup();
                        throw new IllegalStateException(("Unexpected fetch state: " + state.getFetchResult()).toString());
                    }
                    composerStartRestartGroup.startReplaceGroup(532872355);
                    composerStartRestartGroup.startReplaceGroup(294284519);
                    boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(fetchResult2) | composerStartRestartGroup.changedInstance(context);
                    Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RepoPreviewScreenKt.RepoPreviewHeader$lambda$8$lambda$7(fetchResult2, context);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function02 = (Function0) objRememberedValue2;
                    composerStartRestartGroup.endReplaceGroup();
                    composerStartRestartGroup.endReplaceGroup();
                }
                function0 = function02;
            }
            FetchResult fetchResult3 = state.getFetchResult();
            if (fetchResult3 instanceof FetchResult.IsNewRepository) {
                composerStartRestartGroup.startReplaceGroup(533179409);
                composerStartRestartGroup.endReplaceGroup();
                str = null;
            } else {
                if (fetchResult3 instanceof FetchResult.IsNewRepoAndNewMirror) {
                    composerStartRestartGroup.startReplaceGroup(294295412);
                    strStringResource2 = StringResources_androidKt.stringResource(R.string.repo_and_mirror_add_both_info, new Object[]{state.getFetchUrl()}, composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (fetchResult3 instanceof FetchResult.IsNewMirror) {
                    composerStartRestartGroup.startReplaceGroup(294299593);
                    strStringResource2 = StringResources_androidKt.stringResource(R.string.repo_mirror_add_info, new Object[]{state.getFetchUrl()}, composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (fetchResult3 instanceof FetchResult.IsExistingRepository) {
                    composerStartRestartGroup.startReplaceGroup(294302672);
                    strStringResource2 = StringResources_androidKt.stringResource(R.string.repo_exists, composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    if (!(fetchResult3 instanceof FetchResult.IsExistingMirror)) {
                        composerStartRestartGroup.startReplaceGroup(294307297);
                        composerStartRestartGroup.endReplaceGroup();
                        throw new IllegalStateException(("Unexpected fetch state: " + state.getFetchResult()).toString());
                    }
                    composerStartRestartGroup.startReplaceGroup(294304871);
                    strStringResource2 = StringResources_androidKt.stringResource(R.string.repo_mirror_exists, new Object[]{state.getFetchUrl()}, composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                }
                str = strStringResource2;
            }
            Arrangement arrangement = Arrangement.INSTANCE;
            float f = 16;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM245spacedBy0680j_4 = arrangement.m245spacedBy0680j_4(Dp.m2416constructorimpl(f));
            Modifier.Companion companion2 = Modifier.Companion;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null);
            Alignment.Companion companion3 = Alignment.Companion;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM245spacedBy0680j_4, companion3.getStart(), composerStartRestartGroup, 6);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default);
            ComposeUiNode.Companion companion4 = ComposeUiNode.Companion;
            Function0 constructor = companion4.getConstructor();
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
            Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyColumnMeasurePolicy, companion4.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion4.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion4.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion4.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.m245spacedBy0680j_4(Dp.m2416constructorimpl(f)), companion3.getCenterVertically(), composerStartRestartGroup, 54);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
            Function0 constructor2 = companion4.getConstructor();
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
            Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyRowMeasurePolicy, companion4.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion4.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = companion4.getSetCompositeKeyHash();
            if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion4.getSetModifier());
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            RepoIconComposableKt.RepoIcon(receivedRepo, SizeKt.m281size3ABfNKs(companion2, Dp.m2416constructorimpl(48)), composerStartRestartGroup, 48, 0);
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(arrangement.getTop(), companion3.getStart(), composerStartRestartGroup, 48);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
            Function0 constructor3 = companion4.getConstructor();
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
            Updater.m1036setimpl(composerM1035constructorimpl3, measurePolicyColumnMeasurePolicy2, companion4.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl3, currentCompositionLocalMap3, companion4.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash3 = companion4.getSetCompositeKeyHash();
            if (composerM1035constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM1035constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM1035constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m1036setimpl(composerM1035constructorimpl3, modifierMaterializeModifier3, companion4.getSetModifier());
            String name = receivedRepo.getName(localeList);
            if (name == null) {
                name = "Unknown Repository";
            }
            FontWeight bold = FontWeight.Companion.getBold();
            MaterialTheme materialTheme2 = MaterialTheme.INSTANCE;
            int i3 = MaterialTheme.$stable;
            TextKt.m772Text4IGK_g(name, null, 0L, 0L, null, bold, null, 0L, null, null, 0L, 0, false, 1, 0, null, materialTheme2.getTypography(composerStartRestartGroup, i3).getBodyLarge(), composerStartRestartGroup, 196608, 3072, 57310);
            composer2 = composerStartRestartGroup;
            TextKt.m772Text4IGK_g(StringsKt.replaceFirst$default(receivedRepo.getAddress(), "https://", "", false, 4, null), null, materialTheme2.getColorScheme(composer2, i3).m633getOnSurfaceVariant0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme2.getTypography(composer2, i3).getBodyMedium(), composer2, 0, 0, 65530);
            String lastUpdated = Utils.formatLastUpdated(((Context) composer2.consume(AndroidCompositionLocals_androidKt.getLocalContext())).getResources(), receivedRepo.getTimestamp());
            Intrinsics.checkNotNullExpressionValue(lastUpdated, "formatLastUpdated(...)");
            TextKt.m772Text4IGK_g(lastUpdated, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme2.getTypography(composer2, i3).getBodyMedium(), composer2, 0, 0, 65534);
            composer2.endNode();
            composer2.endNode();
            composer2.startReplaceGroup(511535264);
            if (str != null) {
                Modifier modifierM97backgroundbw27NRU$default = BackgroundKt.m97backgroundbw27NRU$default(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), ComposeUtilsKt.colorAttribute(R.attr.warning, composer2, 0), null, 2, null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getCenter(), false);
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer2, modifierM97backgroundbw27NRU$default);
                Function0 constructor4 = companion4.getConstructor();
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor4);
                } else {
                    composer2.useNode();
                }
                Composer composerM1035constructorimpl4 = Updater.m1035constructorimpl(composer2);
                Updater.m1036setimpl(composerM1035constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy, companion4.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl4, currentCompositionLocalMap4, companion4.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash4 = companion4.getSetCompositeKeyHash();
                if (composerM1035constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    composerM1035constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composerM1035constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.m1036setimpl(composerM1035constructorimpl4, modifierMaterializeModifier4, companion4.getSetModifier());
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                materialTheme = materialTheme2;
                companion = companion2;
                TextKt.m772Text4IGK_g(str, PaddingKt.m261padding3ABfNKs(companion2, Dp.m2416constructorimpl(8)), ColorResources_androidKt.colorResource(android.R.color.white, composer2, 6), 0L, null, null, null, 0L, null, TextAlign.m2333boximpl(TextAlign.Companion.m2340getCentere0LSkKk()), 0L, 0, false, 0, 0, null, materialTheme2.getTypography(composer2, i3).getBodyLarge(), composer2, 48, 0, 65016);
                composer2.endNode();
            } else {
                companion = companion2;
                materialTheme = materialTheme2;
            }
            composer2.endReplaceGroup();
            ComposeUtils.INSTANCE.FDroidButton(str2, function0, columnScopeInstance.align(companion, companion3.getEnd()), null, composer2, 24576, 8);
            String strJoinToString$default = zBooleanValue ? SequencesKt.joinToString$default(new LoremIpsum(42).getValues(), " ", null, null, 0, null, null, 62, null) : receivedRepo.getDescription(localeList);
            composer2.startReplaceGroup(511562331);
            if (strJoinToString$default != null) {
                TextKt.m772Text4IGK_g(StringsKt.replace$default(strJoinToString$default, "\n", " ", false, 4, (Object) null), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer2, i3).getBodyMedium(), composer2, 0, 0, 65534);
            }
            composer2.endReplaceGroup();
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoPreviewScreenKt.RepoPreviewHeader$lambda$13(state, onAddRepo, localeList, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoPreviewHeader$lambda$6$lambda$5(FetchResult fetchResult, Context context) {
        RepoDetailsActivity.INSTANCE.launch(context, ((FetchResult.IsExistingRepository) fetchResult).getExistingRepoId());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoPreviewHeader$lambda$8$lambda$7(FetchResult fetchResult, Context context) {
        RepoDetailsActivity.INSTANCE.launch(context, ((FetchResult.IsExistingMirror) fetchResult).getExistingRepoId());
        return Unit.INSTANCE;
    }

    public static final void RepoPreviewApp(final LazyItemScope lazyItemScope, final Repository repo, final MinimalApp app, final LocaleListCompat localeList, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(lazyItemScope, "<this>");
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        Composer composerStartRestartGroup = composer.startRestartGroup(427688889);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(lazyItemScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(repo) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(app) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(localeList) ? 2048 : 1024;
        }
        if ((i2 & 1171) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(427688889, i2, -1, "org.fdroid.fdroid.views.repos.RepoPreviewApp (RepoPreviewScreen.kt:225)");
            }
            CardKt.ElevatedCard(SizeKt.fillMaxWidth$default(LazyItemScope.CC.animateItem$default(lazyItemScope, Modifier.Companion, null, null, null, 7, null), 0.0f, 1, null), null, null, null, ComposableLambdaKt.rememberComposableLambda(1293741086, true, new AnonymousClass1(((Boolean) composerStartRestartGroup.consume(InspectionModeKt.getLocalInspectionMode())).booleanValue(), repo, app, localeList), composerStartRestartGroup, 54), composerStartRestartGroup, 24576, 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoPreviewScreenKt.RepoPreviewApp$lambda$14(lazyItemScope, repo, app, localeList, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewApp$1, reason: invalid class name */
    /* JADX INFO: compiled from: RepoPreviewScreen.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function3 {
        final /* synthetic */ MinimalApp $app;
        final /* synthetic */ boolean $isDevPreview;
        final /* synthetic */ LocaleListCompat $localeList;
        final /* synthetic */ Repository $repo;

        AnonymousClass1(boolean z, Repository repository, MinimalApp minimalApp, LocaleListCompat localeListCompat) {
            this.$isDevPreview = z;
            this.$repo = repository;
            this.$app = minimalApp;
            this.$localeList = localeListCompat;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((ColumnScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(ColumnScope ElevatedCard, Composer composer, int i) {
            MinimalApp minimalApp;
            Modifier.Companion companion;
            Arrangement arrangement;
            Composer composer2;
            Intrinsics.checkNotNullParameter(ElevatedCard, "$this$ElevatedCard");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1293741086, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewApp.<anonymous> (RepoPreviewScreen.kt:232)");
            }
            Arrangement arrangement2 = Arrangement.INSTANCE;
            float f = 8;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM245spacedBy0680j_4 = arrangement2.m245spacedBy0680j_4(Dp.m2416constructorimpl(f));
            Modifier.Companion companion2 = Modifier.Companion;
            Modifier modifierM261padding3ABfNKs = PaddingKt.m261padding3ABfNKs(companion2, Dp.m2416constructorimpl(f));
            boolean z = this.$isDevPreview;
            Repository repository = this.$repo;
            MinimalApp minimalApp2 = this.$app;
            LocaleListCompat localeListCompat = this.$localeList;
            Alignment.Companion companion3 = Alignment.Companion;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM245spacedBy0680j_4, companion3.getTop(), composer, 6);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM261padding3ABfNKs);
            ComposeUiNode.Companion companion4 = ComposeUiNode.Companion;
            Function0 constructor = companion4.getConstructor();
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM1035constructorimpl = Updater.m1035constructorimpl(composer);
            Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyRowMeasurePolicy, companion4.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion4.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion4.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion4.getSetModifier());
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            if (z) {
                composer.startReplaceGroup(816034164);
                ImageKt.Image(DrawablePainterKt.rememberDrawablePainter(ResourcesCompat.getDrawable(((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext())).getResources(), R.drawable.ic_launcher, null), composer, 0), (String) null, SizeKt.m281size3ABfNKs(companion2, Dp.m2416constructorimpl(38)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer, 432, 120);
                composer.endReplaceGroup();
                minimalApp = minimalApp2;
                companion = companion2;
                arrangement = arrangement2;
                composer2 = composer;
            } else {
                composer.startReplaceGroup(816043213);
                Object glideModel = Utils.getGlideModel(repository, minimalApp2.getIcon(localeListCompat));
                Modifier modifierM281size3ABfNKs = SizeKt.m281size3ABfNKs(companion2, Dp.m2416constructorimpl(38));
                composer.startReplaceGroup(816049041);
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewApp$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return RepoPreviewScreenKt.AnonymousClass1.invoke$lambda$3$lambda$1$lambda$0((RequestBuilder) obj);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                minimalApp = minimalApp2;
                companion = companion2;
                arrangement = arrangement2;
                composer2 = composer;
                GlideImageKt.GlideImage(glideModel, null, modifierM281size3ABfNKs, null, null, 0.0f, null, null, null, null, (Function1) objRememberedValue, composer, 432, 6, 1016);
                composer.endReplaceGroup();
            }
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(arrangement.getTop(), companion3.getStart(), composer2, 0);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, companion);
            Function0 constructor2 = companion4.getConstructor();
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer2.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM1035constructorimpl2 = Updater.m1035constructorimpl(composer);
            Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyColumnMeasurePolicy, companion4.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion4.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = companion4.getSetCompositeKeyHash();
            if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion4.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            String name = minimalApp.getName();
            if (name == null) {
                name = "Unknown app";
            }
            MaterialTheme materialTheme = MaterialTheme.INSTANCE;
            int i2 = MaterialTheme.$stable;
            TextKt.m772Text4IGK_g(name, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer2, i2).getBodyLarge(), composer, 0, 0, 65534);
            String summary = minimalApp.getSummary();
            if (summary == null) {
                summary = "";
            }
            TextKt.m772Text4IGK_g(summary, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer2, i2).getBodyMedium(), composer, 0, 0, 65534);
            composer.endNode();
            composer.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final RequestBuilder invoke$lambda$3$lambda$1$lambda$0(RequestBuilder it) {
            Intrinsics.checkNotNullParameter(it, "it");
            BaseRequestOptions baseRequestOptionsError = ((RequestBuilder) it.fallback(R.drawable.ic_repo_app_default)).error(R.drawable.ic_repo_app_default);
            Intrinsics.checkNotNullExpressionValue(baseRequestOptionsError, "error(...)");
            return (RequestBuilder) baseRequestOptionsError;
        }
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app1$1] */
    /* JADX WARN: Type inference failed for: r5v0, types: [org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app2$1] */
    /* JADX WARN: Type inference failed for: r6v0, types: [org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app3$1] */
    public static final void RepoPreviewScreenFetchingPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-239945745);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-239945745, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreenFetchingPreview (RepoPreviewScreen.kt:265)");
            }
            ThemeKt.FDroidContent(false, true, ComposableLambdaKt.rememberComposableLambda(-79717703, true, new C02221("https://example.org", FDroidApp.createSwapRepo("https://example.org", "foo bar"), new MinimalApp() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app1$1
                private final long repoId;
                private final String packageName = "org.example";
                private final String name = "App 1 with a long name";
                private final String summary = "Summary of App1 which can also be a bit longer";

                @Override // org.fdroid.database.MinimalApp
                public FileV2 getIcon(LocaleListCompat localeList) {
                    Intrinsics.checkNotNullParameter(localeList, "localeList");
                    return null;
                }

                @Override // org.fdroid.database.MinimalApp
                public long getRepoId() {
                    return this.repoId;
                }

                @Override // org.fdroid.database.MinimalApp
                public String getPackageName() {
                    return this.packageName;
                }

                @Override // org.fdroid.database.MinimalApp
                public String getName() {
                    return this.name;
                }

                @Override // org.fdroid.database.MinimalApp
                public String getSummary() {
                    return this.summary;
                }
            }, new MinimalApp() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app2$1
                private final long repoId;
                private final String packageName = "com.example";
                private final String name = "App 2 with a name that is even longer than the first app";
                private final String summary = "Summary of App2 which can also be a bit longer, even longer than other apps.";

                @Override // org.fdroid.database.MinimalApp
                public FileV2 getIcon(LocaleListCompat localeList) {
                    Intrinsics.checkNotNullParameter(localeList, "localeList");
                    return null;
                }

                @Override // org.fdroid.database.MinimalApp
                public long getRepoId() {
                    return this.repoId;
                }

                @Override // org.fdroid.database.MinimalApp
                public String getPackageName() {
                    return this.packageName;
                }

                @Override // org.fdroid.database.MinimalApp
                public String getName() {
                    return this.name;
                }

                @Override // org.fdroid.database.MinimalApp
                public String getSummary() {
                    return this.summary;
                }
            }, new MinimalApp() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app3$1
                private final long repoId;
                private final String packageName = "net.example";
                private final String name = "App 3";
                private final String summary = "short summary";

                @Override // org.fdroid.database.MinimalApp
                public FileV2 getIcon(LocaleListCompat localeList) {
                    Intrinsics.checkNotNullParameter(localeList, "localeList");
                    return null;
                }

                @Override // org.fdroid.database.MinimalApp
                public long getRepoId() {
                    return this.repoId;
                }

                @Override // org.fdroid.database.MinimalApp
                public String getPackageName() {
                    return this.packageName;
                }

                @Override // org.fdroid.database.MinimalApp
                public String getName() {
                    return this.name;
                }

                @Override // org.fdroid.database.MinimalApp
                public String getSummary() {
                    return this.summary;
                }
            }), composerStartRestartGroup, 54), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoPreviewScreenKt.RepoPreviewScreenFetchingPreview$lambda$15(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoPreviewScreen.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02221 implements Function2 {
        final /* synthetic */ String $address;
        final /* synthetic */ RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app1$1 $app1;
        final /* synthetic */ RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app2$1 $app2;
        final /* synthetic */ RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app3$1 $app3;
        final /* synthetic */ Repository $repo;

        C02221(String str, Repository repository, RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app1$1 repoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app1$1, RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app2$1 repoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app2$1, RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app3$1 repoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app3$1) {
            this.$address = str;
            this.$repo = repository;
            this.$app1 = repoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app1$1;
            this.$app2 = repoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app2$1;
            this.$app3 = repoPreviewScreenKt$RepoPreviewScreenFetchingPreview$app3$1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-79717703, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreenFetchingPreview.<anonymous> (RepoPreviewScreen.kt:293)");
                }
                Fetching fetching = new Fetching(this.$address, this.$repo, CollectionsKt.listOf((Object[]) new MinimalApp[]{this.$app1, this.$app2, this.$app3}), FetchResult.IsNewRepository.INSTANCE, false, 16, null);
                composer.startReplaceGroup(-1279981962);
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenFetchingPreview$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                RepoPreviewScreenKt.RepoPreviewScreen(fetching, null, (Function0) objRememberedValue, composer, 384, 2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    }

    public static final void RepoPreviewScreenNewMirrorPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-292759008);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-292759008, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreenNewMirrorPreview (RepoPreviewScreen.kt:301)");
            }
            ThemeKt.FDroidContent(false, true, ComposableLambdaKt.rememberComposableLambda(379342998, true, new C02231(FDroidApp.createSwapRepo("https://example.org", "foo bar")), composerStartRestartGroup, 54), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoPreviewScreenKt.RepoPreviewScreenNewMirrorPreview$lambda$16(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenNewMirrorPreview$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoPreviewScreen.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02231 implements Function2 {
        final /* synthetic */ Repository $repo;

        C02231(Repository repository) {
            this.$repo = repository;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(379342998, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreenNewMirrorPreview.<anonymous> (RepoPreviewScreen.kt:304)");
                }
                Fetching fetching = new Fetching("https://mirror.example.org", this.$repo, CollectionsKt.emptyList(), new FetchResult.IsNewMirror(0L), false, 16, null);
                composer.startReplaceGroup(404086581);
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenNewMirrorPreview$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                RepoPreviewScreenKt.RepoPreviewScreen(fetching, null, (Function0) objRememberedValue, composer, 384, 2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    }

    public static final void RepoPreviewScreenNewRepoAndNewMirrorPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-884684475);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-884684475, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreenNewRepoAndNewMirrorPreview (RepoPreviewScreen.kt:312)");
            }
            ThemeKt.FDroidContent(false, true, ComposableLambdaKt.rememberComposableLambda(2019763259, true, new C02241(FDroidApp.createSwapRepo("https://example.org", "foo bar")), composerStartRestartGroup, 54), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoPreviewScreenKt.RepoPreviewScreenNewRepoAndNewMirrorPreview$lambda$17(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenNewRepoAndNewMirrorPreview$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoPreviewScreen.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02241 implements Function2 {
        final /* synthetic */ Repository $repo;

        C02241(Repository repository) {
            this.$repo = repository;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2019763259, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreenNewRepoAndNewMirrorPreview.<anonymous> (RepoPreviewScreen.kt:315)");
                }
                Fetching fetching = new Fetching("https://mirror.example.org", this.$repo, CollectionsKt.emptyList(), FetchResult.IsNewRepoAndNewMirror.INSTANCE, false, 16, null);
                composer.startReplaceGroup(1072856826);
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenNewRepoAndNewMirrorPreview$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                RepoPreviewScreenKt.RepoPreviewScreen(fetching, null, (Function0) objRememberedValue, composer, 384, 2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    }

    public static final void RepoPreviewScreenExistingRepoPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-859214630);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-859214630, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreenExistingRepoPreview (RepoPreviewScreen.kt:323)");
            }
            ThemeKt.FDroidContent(false, true, ComposableLambdaKt.rememberComposableLambda(-1405887836, true, new C02211("https://example.org", FDroidApp.createSwapRepo("https://example.org", "foo bar")), composerStartRestartGroup, 54), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoPreviewScreenKt.RepoPreviewScreenExistingRepoPreview$lambda$18(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenExistingRepoPreview$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoPreviewScreen.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02211 implements Function2 {
        final /* synthetic */ String $address;
        final /* synthetic */ Repository $repo;

        C02211(String str, Repository repository) {
            this.$address = str;
            this.$repo = repository;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1405887836, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreenExistingRepoPreview.<anonymous> (RepoPreviewScreen.kt:327)");
                }
                Fetching fetching = new Fetching(this.$address, this.$repo, CollectionsKt.emptyList(), new FetchResult.IsExistingRepository(0L), false, 16, null);
                composer.startReplaceGroup(1268031553);
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenExistingRepoPreview$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                RepoPreviewScreenKt.RepoPreviewScreen(fetching, null, (Function0) objRememberedValue, composer, 384, 2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    }

    public static final void RepoPreviewScreenExistingMirrorPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1808330477);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1808330477, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreenExistingMirrorPreview (RepoPreviewScreen.kt:335)");
            }
            ThemeKt.FDroidContent(false, true, ComposableLambdaKt.rememberComposableLambda(441389623, true, new C02201(FDroidApp.createSwapRepo("https://example.org", "foo bar")), composerStartRestartGroup, 54), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoPreviewScreenKt.RepoPreviewScreenExistingMirrorPreview$lambda$19(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenExistingMirrorPreview$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoPreviewScreen.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02201 implements Function2 {
        final /* synthetic */ Repository $repo;

        C02201(Repository repository) {
            this.$repo = repository;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(441389623, i, -1, "org.fdroid.fdroid.views.repos.RepoPreviewScreenExistingMirrorPreview.<anonymous> (RepoPreviewScreen.kt:338)");
                }
                Fetching fetching = new Fetching("https://mirror.example.org", this.$repo, CollectionsKt.emptyList(), new FetchResult.IsExistingMirror(0L), false, 16, null);
                composer.startReplaceGroup(-488505420);
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.repos.RepoPreviewScreenKt$RepoPreviewScreenExistingMirrorPreview$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                RepoPreviewScreenKt.RepoPreviewScreen(fetching, null, (Function0) objRememberedValue, composer, 384, 2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    }
}
