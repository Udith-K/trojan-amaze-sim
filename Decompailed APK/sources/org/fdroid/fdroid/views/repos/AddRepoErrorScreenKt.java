package org.fdroid.fdroid.views.repos;

import android.content.Context;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ErrorKt;
import androidx.compose.material3.MaterialTheme;
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
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.InspectionModeKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.ui.theme.ThemeKt;
import org.fdroid.repo.AddRepoError;

/* JADX INFO: compiled from: AddRepoErrorScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a\r\u0010\u0007\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\b\u001a\r\u0010\t\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\b\u001a\r\u0010\n\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\b\u001a\r\u0010\u000b\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\b\u001a\r\u0010\f\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\b¨\u0006\r"}, d2 = {"AddRepoErrorScreen", "", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "state", "Lorg/fdroid/repo/AddRepoError;", "(Landroidx/compose/foundation/layout/PaddingValues;Lorg/fdroid/repo/AddRepoError;Landroidx/compose/runtime/Composer;I)V", "AddRepoErrorInvalidFingerprintPreview", "(Landroidx/compose/runtime/Composer;I)V", "AddRepoErrorIoErrorPreview", "AddRepoErrorInvalidIndexPreview", "AddRepoErrorUnknownSourcesPreview", "AddRepoErrorArchivePreview", "app_fullRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AddRepoErrorScreenKt {

    /* JADX INFO: compiled from: AddRepoErrorScreen.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AddRepoError.ErrorType.values().length];
            try {
                iArr[AddRepoError.ErrorType.INVALID_FINGERPRINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AddRepoError.ErrorType.UNKNOWN_SOURCES_DISALLOWED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AddRepoError.ErrorType.INVALID_INDEX.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AddRepoError.ErrorType.IO_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[AddRepoError.ErrorType.IS_ARCHIVE_REPO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoErrorArchivePreview$lambda$6(int i, Composer composer, int i2) {
        AddRepoErrorArchivePreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoErrorInvalidFingerprintPreview$lambda$2(int i, Composer composer, int i2) {
        AddRepoErrorInvalidFingerprintPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoErrorInvalidIndexPreview$lambda$4(int i, Composer composer, int i2) {
        AddRepoErrorInvalidIndexPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoErrorIoErrorPreview$lambda$3(int i, Composer composer, int i2) {
        AddRepoErrorIoErrorPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoErrorScreen$lambda$1(PaddingValues paddingValues, AddRepoError addRepoError, int i, Composer composer, int i2) {
        AddRepoErrorScreen(paddingValues, addRepoError, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddRepoErrorUnknownSourcesPreview$lambda$5(int i, Composer composer, int i2) {
        AddRepoErrorUnknownSourcesPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void AddRepoErrorScreen(final PaddingValues paddingValues, final AddRepoError state, Composer composer, final int i) {
        int i2;
        String strStringResource;
        Composer composer2;
        Intrinsics.checkNotNullParameter(paddingValues, "paddingValues");
        Intrinsics.checkNotNullParameter(state, "state");
        Composer composerStartRestartGroup = composer.startRestartGroup(734084327);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(paddingValues) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(state) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(734084327, i2, -1, "org.fdroid.fdroid.views.repos.AddRepoErrorScreen (AddRepoErrorScreen.kt:36)");
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
            ImageVector error = ErrorKt.getError(Icons.INSTANCE.getDefault());
            ColorFilter.Companion companion4 = ColorFilter.Companion;
            MaterialTheme materialTheme = MaterialTheme.INSTANCE;
            int i3 = MaterialTheme.$stable;
            ImageKt.Image(error, (String) null, SizeKt.m281size3ABfNKs(companion2, Dp.m2416constructorimpl(48)), (Alignment) null, (ContentScale) null, 0.0f, ColorFilter.Companion.m1311tintxETnrds$default(companion4, materialTheme.getColorScheme(composerStartRestartGroup, i3).m620getError0d7_KjU(), 0, 2, null), composerStartRestartGroup, 432, 56);
            int i4 = WhenMappings.$EnumSwitchMapping$0[state.getErrorType().ordinal()];
            if (i4 == 1) {
                composerStartRestartGroup.startReplaceGroup(-1271658785);
                strStringResource = StringResources_androidKt.stringResource(R.string.bad_fingerprint, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else if (i4 == 2) {
                composerStartRestartGroup.startReplaceGroup(-766626955);
                if (((Boolean) composerStartRestartGroup.consume(InspectionModeKt.getLocalInspectionMode())).booleanValue()) {
                    composerStartRestartGroup.startReplaceGroup(-766581850);
                    strStringResource = StringResources_androidKt.stringResource(R.string.has_disallow_install_unknown_sources, composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-766475551);
                    strStringResource = ManageReposActivity.getDisallowInstallUnknownSourcesErrorMessage((Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext()));
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
            } else if (i4 == 3) {
                composerStartRestartGroup.startReplaceGroup(-1271646244);
                strStringResource = StringResources_androidKt.stringResource(R.string.repo_invalid, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else if (i4 == 4) {
                composerStartRestartGroup.startReplaceGroup(-1271644259);
                strStringResource = StringResources_androidKt.stringResource(R.string.repo_io_error, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                if (i4 != 5) {
                    composerStartRestartGroup.startReplaceGroup(-1271660101);
                    composerStartRestartGroup.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
                }
                composerStartRestartGroup.startReplaceGroup(-1271642007);
                strStringResource = StringResources_androidKt.stringResource(R.string.repo_error_adding_archive, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            }
            String str = strStringResource;
            Intrinsics.checkNotNull(str);
            TextKt.m772Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, TextAlign.m2333boximpl(TextAlign.Companion.m2340getCentere0LSkKk()), 0L, 0, false, 0, 0, null, materialTheme.getTypography(composerStartRestartGroup, i3).getHeadlineSmall(), composerStartRestartGroup, 0, 0, 65022);
            composerStartRestartGroup.startReplaceGroup(-1271634774);
            if (state.getException() != null) {
                composer2 = composerStartRestartGroup;
                TextKt.m772Text4IGK_g(String.valueOf(state.getException()), null, materialTheme.getColorScheme(composerStartRestartGroup, i3).m633getOnSurfaceVariant0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composerStartRestartGroup, i3).getBodyLarge(), composer2, 0, 0, 65530);
            } else {
                composer2 = composerStartRestartGroup;
            }
            composer2.endReplaceGroup();
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoErrorScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AddRepoErrorScreenKt.AddRepoErrorScreen$lambda$1(paddingValues, state, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AddRepoErrorInvalidFingerprintPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1752043056);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1752043056, i, -1, "org.fdroid.fdroid.views.repos.AddRepoErrorInvalidFingerprintPreview (AddRepoErrorScreen.kt:80)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$AddRepoErrorScreenKt.INSTANCE.m3185getLambda1$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoErrorScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AddRepoErrorScreenKt.AddRepoErrorInvalidFingerprintPreview$lambda$2(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AddRepoErrorIoErrorPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(961254175);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(961254175, i, -1, "org.fdroid.fdroid.views.repos.AddRepoErrorIoErrorPreview (AddRepoErrorScreen.kt:88)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$AddRepoErrorScreenKt.INSTANCE.m3186getLambda2$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoErrorScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AddRepoErrorScreenKt.AddRepoErrorIoErrorPreview$lambda$3(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AddRepoErrorInvalidIndexPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1840611938);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1840611938, i, -1, "org.fdroid.fdroid.views.repos.AddRepoErrorInvalidIndexPreview (AddRepoErrorScreen.kt:96)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$AddRepoErrorScreenKt.INSTANCE.m3187getLambda3$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoErrorScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AddRepoErrorScreenKt.AddRepoErrorInvalidIndexPreview$lambda$4(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AddRepoErrorUnknownSourcesPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1686221807);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1686221807, i, -1, "org.fdroid.fdroid.views.repos.AddRepoErrorUnknownSourcesPreview (AddRepoErrorScreen.kt:107)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$AddRepoErrorScreenKt.INSTANCE.m3188getLambda4$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoErrorScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AddRepoErrorScreenKt.AddRepoErrorUnknownSourcesPreview$lambda$5(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void AddRepoErrorArchivePreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-744487073);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-744487073, i, -1, "org.fdroid.fdroid.views.repos.AddRepoErrorArchivePreview (AddRepoErrorScreen.kt:115)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$AddRepoErrorScreenKt.INSTANCE.m3189getLambda5$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.AddRepoErrorScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AddRepoErrorScreenKt.AddRepoErrorArchivePreview$lambda$6(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
