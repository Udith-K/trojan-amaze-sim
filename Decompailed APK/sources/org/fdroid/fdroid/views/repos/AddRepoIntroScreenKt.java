package org.fdroid.fdroid.views.repos;

import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ArrowDropDownKt;
import androidx.compose.material.icons.filled.ArrowDropUpKt;
import androidx.compose.material.icons.filled.QrCodeKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.InspectionModeKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Dp;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.compose.ComposeUtils;
import org.fdroid.fdroid.ui.theme.ThemeKt;
import org.fdroid.repo.AddRepoError;
import org.fdroid.repo.AddRepoState;
import org.fdroid.repo.Added;
import org.fdroid.repo.Adding;
import org.fdroid.repo.FetchResult;
import org.fdroid.repo.Fetching;
import org.fdroid.repo.None;

/* JADX INFO: compiled from: AddRepoIntroScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\u001aE\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007¢\u0006\u0002\u0010\n\u001a)\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u000e\u001a\r\u0010\u000f\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0010\u001a\r\u0010\u0011\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0010¨\u0006\u0012²\u0006\n\u0010\u0013\u001a\u00020\u0014X\u008a\u008e\u0002"}, d2 = {"AddRepoIntroScreen", "", "state", "Lorg/fdroid/repo/AddRepoState;", "onFetchRepo", "Lkotlin/Function1;", "", "onAddRepo", "Lkotlin/Function0;", "onBackClicked", "(Lorg/fdroid/repo/AddRepoState;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "AddRepoIntroContent", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "(Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "AddRepoIntroScreenPreview", "(Landroidx/compose/runtime/Composer;I)V", "AddRepoIntroScreenPreviewNight", "app_fullRelease", "manualExpanded", ""}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AddRepoIntroScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoIntroContent$lambda$16(PaddingValues paddingValues, Function1 function1, int i, Composer composer, int i2) {
        AddRepoIntroContent(paddingValues, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoIntroScreen$lambda$0(AddRepoState addRepoState, Function1 function1, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        AddRepoIntroScreen(addRepoState, function1, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoIntroScreenPreview$lambda$17(int i, Composer composer, int i2) {
        AddRepoIntroScreenPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoIntroScreenPreviewNight$lambda$18(int i, Composer composer, int i2) {
        AddRepoIntroScreenPreviewNight(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final boolean AddRepoIntroContent$lambda$15$lambda$8(MutableState mutableState) {
        return ((Boolean) mutableState.getValue()).booleanValue();
    }

    public static final void AddRepoIntroScreen(final AddRepoState state, final Function1 onFetchRepo, final Function0 onAddRepo, final Function0 onBackClicked, Composer composer, final int i) {
        int i2;
        final String strStringResource;
        Composer composer2;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(onFetchRepo, "onFetchRepo");
        Intrinsics.checkNotNullParameter(onAddRepo, "onAddRepo");
        Intrinsics.checkNotNullParameter(onBackClicked, "onBackClicked");
        Composer composerStartRestartGroup = composer.startRestartGroup(850783860);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(state) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onFetchRepo) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onAddRepo) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onBackClicked) ? 2048 : 1024;
        }
        if ((i2 & 1171) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(850783860, i2, -1, "org.fdroid.fdroid.views.repos.AddRepoIntroScreen (AddRepoIntroScreen.kt:80)");
            }
            if (state instanceof Fetching) {
                composerStartRestartGroup.startReplaceGroup(-2091750287);
                FetchResult fetchResult = ((Fetching) state).getFetchResult();
                if ((fetchResult instanceof FetchResult.IsNewMirror) || (fetchResult instanceof FetchResult.IsExistingMirror)) {
                    composerStartRestartGroup.startReplaceGroup(763811940);
                    strStringResource = StringResources_androidKt.stringResource(R.string.repo_add_mirror, composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(763813927);
                    strStringResource = StringResources_androidKt.stringResource(R.string.repo_add_new_title, composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-2091507061);
                strStringResource = StringResources_androidKt.stringResource(R.string.repo_add_new_title, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            }
            composer2 = composerStartRestartGroup;
            ScaffoldKt.m729ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(278798904, true, new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt.AddRepoIntroScreen.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i3) {
                    if ((i3 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(278798904, i3, -1, "org.fdroid.fdroid.views.repos.AddRepoIntroScreen.<anonymous> (AddRepoIntroScreen.kt:93)");
                        }
                        final String str = strStringResource;
                        ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(620187892, true, new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt.AddRepoIntroScreen.1.1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i4) {
                                if ((i4 & 3) != 2 || !composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(620187892, i4, -1, "org.fdroid.fdroid.views.repos.AddRepoIntroScreen.<anonymous>.<anonymous> (AddRepoIntroScreen.kt:100)");
                                    }
                                    TextKt.m772Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer4, 0, 0, 131070);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer4.skipToGroupEnd();
                            }
                        }, composer3, 54);
                        final Function0 function0 = onBackClicked;
                        AppBarKt.m599TopAppBarGHTll3U(composableLambdaRememberComposableLambda, null, ComposableLambdaKt.rememberComposableLambda(146952242, true, new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt.AddRepoIntroScreen.1.2
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i4) {
                                if ((i4 & 3) != 2 || !composer4.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(146952242, i4, -1, "org.fdroid.fdroid.views.repos.AddRepoIntroScreen.<anonymous>.<anonymous> (AddRepoIntroScreen.kt:95)");
                                    }
                                    IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$AddRepoIntroScreenKt.INSTANCE.m3190getLambda1$app_fullRelease(), composer4, 196608, 30);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer4.skipToGroupEnd();
                            }
                        }, composer3, 54), null, 0.0f, null, null, null, composer3, 390, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(502037059, true, new Function3() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt.AddRepoIntroScreen.2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    invoke((PaddingValues) obj, (Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(PaddingValues paddingValues, Composer composer3, int i3) {
                    Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
                    if ((i3 & 6) == 0) {
                        i3 |= composer3.changed(paddingValues) ? 4 : 2;
                    }
                    if ((i3 & 19) != 18 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(502037059, i3, -1, "org.fdroid.fdroid.views.repos.AddRepoIntroScreen.<anonymous> (AddRepoIntroScreen.kt:106)");
                        }
                        AddRepoState addRepoState = state;
                        if (Intrinsics.areEqual(addRepoState, None.INSTANCE)) {
                            composer3.startReplaceGroup(-686190550);
                            AddRepoIntroScreenKt.AddRepoIntroContent(paddingValues, onFetchRepo, composer3, i3 & 14);
                            composer3.endReplaceGroup();
                        } else if (addRepoState instanceof Fetching) {
                            composer3.startReplaceGroup(203016356);
                            if (((Fetching) state).getReceivedRepo() == null) {
                                composer3.startReplaceGroup(203055664);
                                RepoProgressScreenKt.RepoProgressScreen(paddingValues, StringResources_androidKt.stringResource(R.string.repo_state_fetching, composer3, 0), composer3, i3 & 14);
                                composer3.endReplaceGroup();
                            } else {
                                composer3.startReplaceGroup(203184097);
                                RepoPreviewScreenKt.RepoPreviewScreen((Fetching) state, PaddingKt.m265paddingqDBjuR0$default(Modifier.Companion, 0.0f, paddingValues.mo255calculateTopPaddingD9Ej5fM(), 0.0f, 0.0f, 13, null), onAddRepo, composer3, 0, 0);
                                composer3.endReplaceGroup();
                            }
                            composer3.endReplaceGroup();
                        } else if (Intrinsics.areEqual(addRepoState, Adding.INSTANCE)) {
                            composer3.startReplaceGroup(-686172920);
                            RepoProgressScreenKt.RepoProgressScreen(paddingValues, StringResources_androidKt.stringResource(R.string.repo_state_adding, composer3, 0), composer3, i3 & 14);
                            composer3.endReplaceGroup();
                        } else if (addRepoState instanceof Added) {
                            composer3.startReplaceGroup(-686169686);
                            BoxKt.Box(PaddingKt.padding(Modifier.Companion, paddingValues), composer3, 0);
                            composer3.endReplaceGroup();
                        } else {
                            if (!(addRepoState instanceof AddRepoError)) {
                                composer3.startReplaceGroup(-686190889);
                                composer3.endReplaceGroup();
                                throw new NoWhenBranchMatchedException();
                            }
                            composer3.startReplaceGroup(-686166781);
                            AddRepoErrorScreenKt.AddRepoErrorScreen(paddingValues, (AddRepoError) state, composer3, i3 & 14);
                            composer3.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composer2, 805306416, 509);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AddRepoIntroScreenKt.AddRepoIntroScreen$lambda$0(state, onFetchRepo, onAddRepo, onBackClicked, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final void AddRepoIntroContent(final PaddingValues paddingValues, final Function1 onFetchRepo, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
        Intrinsics.checkNotNullParameter(onFetchRepo, "onFetchRepo");
        Composer composerStartRestartGroup = composer.startRestartGroup(1335287590);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(paddingValues) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onFetchRepo) ? 32 : 16;
        }
        int i3 = i2;
        if ((i3 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1335287590, i3, -1, "org.fdroid.fdroid.views.repos.AddRepoIntroContent (AddRepoIntroScreen.kt:128)");
            }
            Arrangement arrangement = Arrangement.INSTANCE;
            float f = 16;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM245spacedBy0680j_4 = arrangement.m245spacedBy0680j_4(Dp.m2416constructorimpl(f));
            Alignment.Companion companion = Alignment.Companion;
            Alignment.Horizontal centerHorizontally = companion.getCenterHorizontally();
            Modifier.Companion companion2 = Modifier.Companion;
            Modifier modifierPadding = PaddingKt.padding(PaddingKt.m261padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(companion2, 0.0f, 1, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null), Dp.m2416constructorimpl(f)), paddingValues);
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM245spacedBy0680j_4, centerHorizontally, composerStartRestartGroup, 54);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPadding);
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
            String strStringResource = StringResources_androidKt.stringResource(R.string.repo_intro, composerStartRestartGroup, 0);
            MaterialTheme materialTheme = MaterialTheme.INSTANCE;
            int i4 = MaterialTheme.$stable;
            TextKt.m772Text4IGK_g(strStringResource, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composerStartRestartGroup, i4).getBodyLarge(), composerStartRestartGroup, 0, 0, 65534);
            ScanContract scanContract = new ScanContract();
            composerStartRestartGroup.startReplaceGroup(836169027);
            boolean z = (i3 & 112) == 32;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AddRepoIntroScreenKt.AddRepoIntroContent$lambda$15$lambda$2$lambda$1(onFetchRepo, (ScanIntentResult) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceGroup();
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult = ActivityResultRegistryKt.rememberLauncherForActivityResult(scanContract, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            ComposeUtils composeUtils = ComposeUtils.INSTANCE;
            String strStringResource2 = StringResources_androidKt.stringResource(R.string.repo_scan_qr_code, composerStartRestartGroup, 0);
            ImageVector qrCode = QrCodeKt.getQrCode(Icons.Filled.INSTANCE);
            composerStartRestartGroup.startReplaceGroup(836177899);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.Companion.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AddRepoIntroScreenKt.AddRepoIntroContent$lambda$15$lambda$5$lambda$4(managedActivityResultLauncherRememberLauncherForActivityResult);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceGroup();
            composeUtils.FDroidButton(strStringResource2, (Function0) objRememberedValue2, null, qrCode, composerStartRestartGroup, 24576, 4);
            final boolean zBooleanValue = ((Boolean) composerStartRestartGroup.consume(InspectionModeKt.getLocalInspectionMode())).booleanValue();
            Object[] objArr = new Object[0];
            composerStartRestartGroup.startReplaceGroup(836191493);
            boolean zChanged = composerStartRestartGroup.changed(zBooleanValue);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.Companion.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AddRepoIntroScreenKt.AddRepoIntroContent$lambda$15$lambda$7$lambda$6(zBooleanValue);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceGroup();
            final MutableState mutableState = (MutableState) RememberSaveableKt.rememberSaveable(objArr, null, null, (Function0) objRememberedValue3, composerStartRestartGroup, 0, 6);
            Arrangement.HorizontalOrVertical spaceBetween = arrangement.getSpaceBetween();
            Alignment.Vertical centerVertically = companion.getCenterVertically();
            Modifier modifierM277heightInVpY3zN4$default = SizeKt.m277heightInVpY3zN4$default(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), ButtonDefaults.INSTANCE.m610getMinHeightD9Ej5fM(), 0.0f, 2, null);
            composerStartRestartGroup.startReplaceGroup(836200844);
            boolean zChanged2 = composerStartRestartGroup.changed(mutableState);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue4 == Composer.Companion.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AddRepoIntroScreenKt.AddRepoIntroContent$lambda$15$lambda$11$lambda$10(mutableState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            composerStartRestartGroup.endReplaceGroup();
            Modifier modifierM116clickableXHw0xAI$default = ClickableKt.m116clickableXHw0xAI$default(modifierM277heightInVpY3zN4$default, false, null, null, (Function0) objRememberedValue4, 7, null);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, composerStartRestartGroup, 54);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM116clickableXHw0xAI$default);
            Function0 constructor2 = companion3.getConstructor();
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
            Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyRowMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
            if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
            TextKt.m772Text4IGK_g(StringResources_androidKt.stringResource(R.string.repo_enter_url, composerStartRestartGroup, 0), RowScope.CC.weight$default(RowScopeInstance.INSTANCE, companion2, 1.0f, false, 2, null), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composerStartRestartGroup, i4).getBodyMedium(), composerStartRestartGroup, 0, 0, 65532);
            IconKt.m691Iconww6aTOc(AddRepoIntroContent$lambda$15$lambda$8(mutableState) ? ArrowDropUpKt.getArrowDropUp(Icons.INSTANCE.getDefault()) : ArrowDropDownKt.getArrowDropDown(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, composerStartRestartGroup, 48, 12);
            composerStartRestartGroup.endNode();
            composer2 = composerStartRestartGroup;
            composer2.startReplaceGroup(836220149);
            Object objRememberedValue5 = composer2.rememberedValue();
            Composer.Companion companion4 = Composer.Companion;
            if (objRememberedValue5 == companion4.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null), null, 2, null);
                composer2.updateRememberedValue(objRememberedValue5);
            }
            MutableState mutableState2 = (MutableState) objRememberedValue5;
            composer2.endReplaceGroup();
            composer2.startReplaceGroup(836222533);
            Object objRememberedValue6 = composer2.rememberedValue();
            if (objRememberedValue6 == companion4.getEmpty()) {
                objRememberedValue6 = new FocusRequester();
                composer2.updateRememberedValue(objRememberedValue6);
            }
            composer2.endReplaceGroup();
            AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, AddRepoIntroContent$lambda$15$lambda$8(mutableState), null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-397246604, true, new AddRepoIntroScreenKt$AddRepoIntroContent$1$4(mutableState2, onFetchRepo, (FocusRequester) objRememberedValue6), composer2, 54), composer2, 1572870, 30);
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AddRepoIntroScreenKt.AddRepoIntroContent$lambda$16(paddingValues, onFetchRepo, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoIntroContent$lambda$15$lambda$2$lambda$1(Function1 function1, ScanIntentResult scanIntentResult) {
        if (scanIntentResult.getContents() != null) {
            String contents = scanIntentResult.getContents();
            Intrinsics.checkNotNullExpressionValue(contents, "getContents(...)");
            function1.invoke(contents);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoIntroContent$lambda$15$lambda$5$lambda$4(ManagedActivityResultLauncher managedActivityResultLauncher) {
        ScanOptions scanOptions = new ScanOptions();
        scanOptions.setPrompt("");
        scanOptions.setBeepEnabled(true);
        scanOptions.setOrientationLocked(false);
        scanOptions.setDesiredBarcodeFormats("QR_CODE");
        scanOptions.addExtra("SCAN_TYPE", 2);
        managedActivityResultLauncher.launch(scanOptions);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState AddRepoIntroContent$lambda$15$lambda$7$lambda$6(boolean z) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
    }

    private static final void AddRepoIntroContent$lambda$15$lambda$9(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoIntroContent$lambda$15$lambda$11$lambda$10(MutableState mutableState) {
        AddRepoIntroContent$lambda$15$lambda$9(mutableState, !AddRepoIntroContent$lambda$15$lambda$8(mutableState));
        return Unit.INSTANCE;
    }

    public static final void AddRepoIntroScreenPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1014078063);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1014078063, i, -1, "org.fdroid.fdroid.views.repos.AddRepoIntroScreenPreview (AddRepoIntroScreen.kt:237)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$AddRepoIntroScreenKt.INSTANCE.m3191getLambda2$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AddRepoIntroScreenKt.AddRepoIntroScreenPreview$lambda$17(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AddRepoIntroScreenPreviewNight(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1366522945);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1366522945, i, -1, "org.fdroid.fdroid.views.repos.AddRepoIntroScreenPreviewNight (AddRepoIntroScreen.kt:245)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$AddRepoIntroScreenKt.INSTANCE.m3192getLambda3$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoIntroScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AddRepoIntroScreenKt.AddRepoIntroScreenPreviewNight$lambda$18(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
