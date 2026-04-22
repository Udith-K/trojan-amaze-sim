package org.fdroid.fdroid.views.repos;

import android.content.Context;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.DeleteKt;
import androidx.compose.material.icons.filled.DnsKt;
import androidx.compose.material.icons.filled.EditKt;
import androidx.compose.material.icons.filled.FingerprintKt;
import androidx.compose.material.icons.filled.PublicKt;
import androidx.compose.material.icons.filled.SettingsKt;
import androidx.compose.material.icons.filled.ShareKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.core.os.LocaleListCompat;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.fdroid.database.Repository;
import org.fdroid.download.Mirror;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.UtilsKt;
import org.fdroid.fdroid.compose.ComposeUtils;
import org.fdroid.fdroid.compose.FDroidExpandableRowKt;
import org.fdroid.fdroid.compose.FDroidSwitchRowKt;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.fdroid.ui.theme.ThemeKt;
import org.fdroid.fdroid.views.repos.RepoDetailsScreenKt;

/* JADX INFO: compiled from: RepoDetailsScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\b*\u0001+\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n\u001a+\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0003¢\u0006\u0002\u0010\u000e\u001a#\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0003¢\u0006\u0002\u0010\u0013\u001a\u0015\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0011H\u0003¢\u0006\u0002\u0010\u0016\u001aM\u0010\u0017\u001a\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u001cj\b\u0012\u0004\u0012\u00020\u0011`\u001d2\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00010\u001fH\u0003¢\u0006\u0002\u0010!\u001au\u0010\"\u001a\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u001cj\b\u0012\u0004\u0012\u00020\u0011`\u001d2\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00010\u001f2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010$H\u0003¢\u0006\u0002\u0010&\u001a)\u0010'\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00010$H\u0003¢\u0006\u0002\u0010)\u001a\r\u0010-\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010.\u001a\r\u0010/\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010.\u001a\r\u00100\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010.\u001a\r\u00101\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010.\"\u0010\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0004\n\u0002\u0010,¨\u00062"}, d2 = {"RepoDetailsScreen", "", "repo", "Lorg/fdroid/database/Repository;", "archiveState", "Lorg/fdroid/fdroid/views/repos/ArchiveState;", "numberOfApps", "", "callbacks", "Lorg/fdroid/fdroid/views/repos/RepoDetailsScreenCallbacks;", "(Lorg/fdroid/database/Repository;Lorg/fdroid/fdroid/views/repos/ArchiveState;ILorg/fdroid/fdroid/views/repos/RepoDetailsScreenCallbacks;Landroidx/compose/runtime/Composer;I)V", "GeneralInfoCard", "onShowAppsClicked", "Lkotlin/Function0;", "(Lorg/fdroid/database/Repository;ILkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "BasicAuthCard", "username", "", "onEditCredentialsClicked", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "FingerprintExpandable", BonjourPeer.FINGERPRINT, "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "OfficialMirrors", "mirrors", "", "Lorg/fdroid/download/Mirror;", "disabledMirrors", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "setMirrorEnabled", "Lkotlin/Function2;", "", "(Ljava/util/List;Ljava/util/HashSet;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "UserMirrors", "onShareMirror", "Lkotlin/Function1;", "onDeleteMirror", "(Ljava/util/List;Ljava/util/HashSet;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "SettingsRow", "onToggleArchiveClicked", "(Lorg/fdroid/fdroid/views/repos/ArchiveState;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "emptyCallbacks", "org/fdroid/fdroid/views/repos/RepoDetailsScreenKt$emptyCallbacks$1", "Lorg/fdroid/fdroid/views/repos/RepoDetailsScreenKt$emptyCallbacks$1;", "RepoDetailsScreenPreview", "(Landroidx/compose/runtime/Composer;I)V", "BasicAuthCardPreview", "OfficialMirrorsPreview", "UserMirrorsPreview", "app_fullRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RepoDetailsScreenKt {
    private static final RepoDetailsScreenKt$emptyCallbacks$1 emptyCallbacks = new RepoDetailsScreenCallbacks() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$emptyCallbacks$1
        @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
        public void onBackClicked() {
        }

        @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
        public void onDeleteClicked() {
        }

        @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
        public void onDeleteMirror(Mirror mirror) {
            Intrinsics.checkNotNullParameter(mirror, "mirror");
        }

        @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
        public void onEditCredentialsClicked() {
        }

        @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
        public void onInfoClicked() {
        }

        @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
        public void onShareClicked() {
        }

        @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
        public void onShareMirror(Mirror mirror) {
            Intrinsics.checkNotNullParameter(mirror, "mirror");
        }

        @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
        public void onShowAppsClicked() {
        }

        @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
        public void onShowQrCodeClicked() {
        }

        @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
        public void onToggleArchiveClicked(boolean enabled) {
        }

        @Override // org.fdroid.fdroid.views.repos.RepoDetailsScreenCallbacks
        public void setMirrorEnabled(Mirror mirror, boolean enabled) {
            Intrinsics.checkNotNullParameter(mirror, "mirror");
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicAuthCard$lambda$2(String str, Function0 function0, int i, Composer composer, int i2) {
        BasicAuthCard(str, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BasicAuthCardPreview$lambda$8(int i, Composer composer, int i2) {
        BasicAuthCardPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FingerprintExpandable$lambda$3(String str, int i, Composer composer, int i2) {
        FingerprintExpandable(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit GeneralInfoCard$lambda$1(Repository repository, int i, Function0 function0, int i2, Composer composer, int i3) {
        GeneralInfoCard(repository, i, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit OfficialMirrors$lambda$4(List list, HashSet hashSet, Function2 function2, int i, Composer composer, int i2) {
        OfficialMirrors(list, hashSet, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit OfficialMirrorsPreview$lambda$9(int i, Composer composer, int i2) {
        OfficialMirrorsPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoDetailsScreen$lambda$0(Repository repository, ArchiveState archiveState, int i, RepoDetailsScreenCallbacks repoDetailsScreenCallbacks, int i2, Composer composer, int i3) {
        RepoDetailsScreen(repository, archiveState, i, repoDetailsScreenCallbacks, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoDetailsScreenPreview$lambda$7(int i, Composer composer, int i2) {
        RepoDetailsScreenPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsRow$lambda$6(ArchiveState archiveState, Function1 function1, int i, Composer composer, int i2) {
        SettingsRow(archiveState, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UserMirrors$lambda$5(List list, HashSet hashSet, Function2 function2, Function1 function1, Function1 function12, int i, Composer composer, int i2) {
        UserMirrors(list, hashSet, function2, function1, function12, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UserMirrorsPreview$lambda$10(int i, Composer composer, int i2) {
        UserMirrorsPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void RepoDetailsScreen(final Repository repo, final ArchiveState archiveState, final int i, final RepoDetailsScreenCallbacks callbacks, Composer composer, final int i2) {
        int i3;
        Composer composer2;
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(archiveState, "archiveState");
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        Composer composerStartRestartGroup = composer.startRestartGroup(1532775597);
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(repo) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(archiveState) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(i) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= (i2 & PKIFailureInfo.certConfirmed) == 0 ? composerStartRestartGroup.changed(callbacks) : composerStartRestartGroup.changedInstance(callbacks) ? 2048 : 1024;
        }
        if ((i3 & 1171) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1532775597, i3, -1, "org.fdroid.fdroid.views.repos.RepoDetailsScreen (RepoDetailsScreen.kt:88)");
            }
            final List<Mirror> allOfficialMirrors = repo.getAllOfficialMirrors();
            final List<Mirror> allUserMirrors = repo.getAllUserMirrors();
            final HashSet hashSet = CollectionsKt.toHashSet(repo.getDisabledMirrors());
            composer2 = composerStartRestartGroup;
            ScaffoldKt.m729ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(-2047239311, true, new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt.RepoDetailsScreen.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i4) {
                    if ((i4 & 3) != 2 || !composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2047239311, i4, -1, "org.fdroid.fdroid.views.repos.RepoDetailsScreen.<anonymous> (RepoDetailsScreen.kt:94)");
                        }
                        Function2 function2M3197getLambda1$app_fullRelease = ComposableSingletons$RepoDetailsScreenKt.INSTANCE.m3197getLambda1$app_fullRelease();
                        final RepoDetailsScreenCallbacks repoDetailsScreenCallbacks = callbacks;
                        ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1004542187, true, new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt.RepoDetailsScreen.1.1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer4, int i5) {
                                if ((i5 & 3) == 2 && composer4.getSkipping()) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1004542187, i5, -1, "org.fdroid.fdroid.views.repos.RepoDetailsScreen.<anonymous>.<anonymous> (RepoDetailsScreen.kt:96)");
                                }
                                RepoDetailsScreenCallbacks repoDetailsScreenCallbacks2 = repoDetailsScreenCallbacks;
                                composer4.startReplaceGroup(-2030141893);
                                boolean zChangedInstance = composer4.changedInstance(repoDetailsScreenCallbacks2);
                                Object objRememberedValue = composer4.rememberedValue();
                                if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                                    objRememberedValue = new RepoDetailsScreenKt$RepoDetailsScreen$1$1$1$1(repoDetailsScreenCallbacks2);
                                    composer4.updateRememberedValue(objRememberedValue);
                                }
                                composer4.endReplaceGroup();
                                IconButtonKt.IconButton((Function0) ((KFunction) objRememberedValue), null, false, null, null, ComposableSingletons$RepoDetailsScreenKt.INSTANCE.m3199getLambda2$app_fullRelease(), composer4, 196608, 30);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer3, 54);
                        final RepoDetailsScreenCallbacks repoDetailsScreenCallbacks2 = callbacks;
                        AppBarKt.m599TopAppBarGHTll3U(function2M3197getLambda1$app_fullRelease, null, composableLambdaRememberComposableLambda, ComposableLambdaKt.rememberComposableLambda(470185250, true, new Function3() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt.RepoDetailsScreen.1.2
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                                invoke((RowScope) obj, (Composer) obj2, ((Number) obj3).intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(RowScope TopAppBar, Composer composer4, int i5) {
                                Intrinsics.checkNotNullParameter(TopAppBar, "$this$TopAppBar");
                                if ((i5 & 17) == 16 && composer4.getSkipping()) {
                                    composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(470185250, i5, -1, "org.fdroid.fdroid.views.repos.RepoDetailsScreen.<anonymous>.<anonymous> (RepoDetailsScreen.kt:104)");
                                }
                                RepoDetailsScreenCallbacks repoDetailsScreenCallbacks3 = repoDetailsScreenCallbacks2;
                                composer4.startReplaceGroup(-2030131908);
                                boolean zChangedInstance = composer4.changedInstance(repoDetailsScreenCallbacks3);
                                Object objRememberedValue = composer4.rememberedValue();
                                if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                                    objRememberedValue = new RepoDetailsScreenKt$RepoDetailsScreen$1$2$1$1(repoDetailsScreenCallbacks3);
                                    composer4.updateRememberedValue(objRememberedValue);
                                }
                                composer4.endReplaceGroup();
                                ComposableSingletons$RepoDetailsScreenKt composableSingletons$RepoDetailsScreenKt = ComposableSingletons$RepoDetailsScreenKt.INSTANCE;
                                IconButtonKt.IconButton((Function0) ((KFunction) objRememberedValue), null, false, null, null, composableSingletons$RepoDetailsScreenKt.m3200getLambda3$app_fullRelease(), composer4, 196608, 30);
                                RepoDetailsScreenCallbacks repoDetailsScreenCallbacks4 = repoDetailsScreenCallbacks2;
                                composer4.startReplaceGroup(-2030126367);
                                boolean zChangedInstance2 = composer4.changedInstance(repoDetailsScreenCallbacks4);
                                Object objRememberedValue2 = composer4.rememberedValue();
                                if (zChangedInstance2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                                    objRememberedValue2 = new RepoDetailsScreenKt$RepoDetailsScreen$1$2$2$1(repoDetailsScreenCallbacks4);
                                    composer4.updateRememberedValue(objRememberedValue2);
                                }
                                composer4.endReplaceGroup();
                                IconButtonKt.IconButton((Function0) ((KFunction) objRememberedValue2), null, false, null, null, composableSingletons$RepoDetailsScreenKt.m3201getLambda4$app_fullRelease(), composer4, 196608, 30);
                                RepoDetailsScreenCallbacks repoDetailsScreenCallbacks5 = repoDetailsScreenCallbacks2;
                                composer4.startReplaceGroup(-2030120579);
                                boolean zChangedInstance3 = composer4.changedInstance(repoDetailsScreenCallbacks5);
                                Object objRememberedValue3 = composer4.rememberedValue();
                                if (zChangedInstance3 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                                    objRememberedValue3 = new RepoDetailsScreenKt$RepoDetailsScreen$1$2$3$1(repoDetailsScreenCallbacks5);
                                    composer4.updateRememberedValue(objRememberedValue3);
                                }
                                composer4.endReplaceGroup();
                                IconButtonKt.IconButton((Function0) ((KFunction) objRememberedValue3), null, false, null, null, composableSingletons$RepoDetailsScreenKt.m3202getLambda5$app_fullRelease(), composer4, 196608, 30);
                                RepoDetailsScreenCallbacks repoDetailsScreenCallbacks6 = repoDetailsScreenCallbacks2;
                                composer4.startReplaceGroup(-2030115301);
                                boolean zChangedInstance4 = composer4.changedInstance(repoDetailsScreenCallbacks6);
                                Object objRememberedValue4 = composer4.rememberedValue();
                                if (zChangedInstance4 || objRememberedValue4 == Composer.Companion.getEmpty()) {
                                    objRememberedValue4 = new RepoDetailsScreenKt$RepoDetailsScreen$1$2$4$1(repoDetailsScreenCallbacks6);
                                    composer4.updateRememberedValue(objRememberedValue4);
                                }
                                composer4.endReplaceGroup();
                                IconButtonKt.IconButton((Function0) ((KFunction) objRememberedValue4), null, false, null, null, composableSingletons$RepoDetailsScreenKt.m3203getLambda6$app_fullRelease(), composer4, 196608, 30);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer3, 54), 0.0f, null, null, null, composer3, 3462, 242);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer3.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(-854744644, true, new Function3() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt.RepoDetailsScreen.2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    invoke((PaddingValues) obj, (Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(PaddingValues paddingContent, Composer composer3, int i4) {
                    int i5;
                    int i6;
                    Intrinsics.checkNotNullParameter(paddingContent, "paddingContent");
                    if ((i4 & 6) == 0) {
                        i5 = i4 | (composer3.changed(paddingContent) ? 4 : 2);
                    } else {
                        i5 = i4;
                    }
                    if ((i5 & 19) == 18 && composer3.getSkipping()) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-854744644, i5, -1, "org.fdroid.fdroid.views.repos.RepoDetailsScreen.<anonymous> (RepoDetailsScreen.kt:118)");
                    }
                    Modifier.Companion companion = Modifier.Companion;
                    float f = 16;
                    Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(PaddingKt.m263paddingVpY3zN4$default(PaddingKt.padding(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), UtilsKt.m2961copycKdBLrg(paddingContent, null, null, null, Dp.m2414boximpl(Dp.m2416constructorimpl(0)), composer3, (i5 & 14) | 24576, 7)), Dp.m2416constructorimpl(f), 0.0f, 2, null), ScrollKt.rememberScrollState(0, composer3, 0, 1), false, null, false, 14, null);
                    Repository repository = repo;
                    int i7 = i;
                    RepoDetailsScreenCallbacks repoDetailsScreenCallbacks = callbacks;
                    List<Mirror> list = allOfficialMirrors;
                    HashSet<String> hashSet2 = hashSet;
                    List<Mirror> list2 = allUserMirrors;
                    ArchiveState archiveState2 = archiveState;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer3, 0);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierVerticalScroll$default);
                    ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
                    Function0 constructor = companion2.getConstructor();
                    if (!(composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor);
                    } else {
                        composer3.useNode();
                    }
                    Composer composerM1035constructorimpl = Updater.m1035constructorimpl(composer3);
                    Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyColumnMeasurePolicy, companion2.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    SpacerKt.Spacer(SizeKt.m275height3ABfNKs(companion, Dp.m2416constructorimpl(f)), composer3, 6);
                    composer3.startReplaceGroup(-2030095233);
                    boolean zChangedInstance = composer3.changedInstance(repoDetailsScreenCallbacks);
                    Object objRememberedValue = composer3.rememberedValue();
                    if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new RepoDetailsScreenKt$RepoDetailsScreen$2$1$1$1(repoDetailsScreenCallbacks);
                        composer3.updateRememberedValue(objRememberedValue);
                    }
                    composer3.endReplaceGroup();
                    RepoDetailsScreenKt.GeneralInfoCard(repository, i7, (Function0) ((KFunction) objRememberedValue), composer3, 0);
                    String username = repository.getUsername();
                    composer3.startReplaceGroup(-2030093226);
                    if (username == null) {
                        i6 = 0;
                    } else {
                        composer3.startReplaceGroup(-2030092558);
                        if (StringsKt.isBlank(username)) {
                            i6 = 0;
                        } else {
                            SpacerKt.Spacer(SizeKt.m275height3ABfNKs(companion, Dp.m2416constructorimpl(f)), composer3, 6);
                            composer3.startReplaceGroup(-655396091);
                            boolean zChangedInstance2 = composer3.changedInstance(repoDetailsScreenCallbacks);
                            Object objRememberedValue2 = composer3.rememberedValue();
                            if (zChangedInstance2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                                objRememberedValue2 = new RepoDetailsScreenKt$RepoDetailsScreen$2$1$2$1$1(repoDetailsScreenCallbacks);
                                composer3.updateRememberedValue(objRememberedValue2);
                            }
                            composer3.endReplaceGroup();
                            i6 = 0;
                            RepoDetailsScreenKt.BasicAuthCard(username, (Function0) ((KFunction) objRememberedValue2), composer3, 0);
                        }
                        composer3.endReplaceGroup();
                    }
                    composer3.endReplaceGroup();
                    String fingerprint = repository.getFingerprint();
                    composer3.startReplaceGroup(-2030085632);
                    if (fingerprint != null) {
                        RepoDetailsScreenKt.FingerprintExpandable(fingerprint, composer3, i6);
                    }
                    composer3.endReplaceGroup();
                    composer3.startReplaceGroup(-2030073395);
                    if (list.size() > 2) {
                        composer3.startReplaceGroup(-2030067042);
                        boolean zChangedInstance3 = composer3.changedInstance(repoDetailsScreenCallbacks);
                        Object objRememberedValue3 = composer3.rememberedValue();
                        if (zChangedInstance3 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                            objRememberedValue3 = new RepoDetailsScreenKt$RepoDetailsScreen$2$1$4$1(repoDetailsScreenCallbacks);
                            composer3.updateRememberedValue(objRememberedValue3);
                        }
                        composer3.endReplaceGroup();
                        RepoDetailsScreenKt.OfficialMirrors(list, hashSet2, (Function2) ((KFunction) objRememberedValue3), composer3, 0);
                    }
                    composer3.endReplaceGroup();
                    composer3.startReplaceGroup(-2030064349);
                    if (!list2.isEmpty()) {
                        composer3.startReplaceGroup(-2030058370);
                        boolean zChangedInstance4 = composer3.changedInstance(repoDetailsScreenCallbacks);
                        Object objRememberedValue4 = composer3.rememberedValue();
                        if (zChangedInstance4 || objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new RepoDetailsScreenKt$RepoDetailsScreen$2$1$5$1(repoDetailsScreenCallbacks);
                            composer3.updateRememberedValue(objRememberedValue4);
                        }
                        composer3.endReplaceGroup();
                        Function2 function2 = (Function2) ((KFunction) objRememberedValue4);
                        composer3.startReplaceGroup(-2030056293);
                        boolean zChangedInstance5 = composer3.changedInstance(repoDetailsScreenCallbacks);
                        Object objRememberedValue5 = composer3.rememberedValue();
                        if (zChangedInstance5 || objRememberedValue5 == Composer.Companion.getEmpty()) {
                            objRememberedValue5 = new RepoDetailsScreenKt$RepoDetailsScreen$2$1$6$1(repoDetailsScreenCallbacks);
                            composer3.updateRememberedValue(objRememberedValue5);
                        }
                        composer3.endReplaceGroup();
                        Function1 function1 = (Function1) ((KFunction) objRememberedValue5);
                        composer3.startReplaceGroup(-2030054276);
                        boolean zChangedInstance6 = composer3.changedInstance(repoDetailsScreenCallbacks);
                        Object objRememberedValue6 = composer3.rememberedValue();
                        if (zChangedInstance6 || objRememberedValue6 == Composer.Companion.getEmpty()) {
                            objRememberedValue6 = new RepoDetailsScreenKt$RepoDetailsScreen$2$1$7$1(repoDetailsScreenCallbacks);
                            composer3.updateRememberedValue(objRememberedValue6);
                        }
                        composer3.endReplaceGroup();
                        RepoDetailsScreenKt.UserMirrors(list2, hashSet2, function2, function1, (Function1) ((KFunction) objRememberedValue6), composer3, 0);
                    }
                    composer3.endReplaceGroup();
                    composer3.startReplaceGroup(-2030049500);
                    boolean zChangedInstance7 = composer3.changedInstance(repoDetailsScreenCallbacks);
                    Object objRememberedValue7 = composer3.rememberedValue();
                    if (zChangedInstance7 || objRememberedValue7 == Composer.Companion.getEmpty()) {
                        objRememberedValue7 = new RepoDetailsScreenKt$RepoDetailsScreen$2$1$8$1(repoDetailsScreenCallbacks);
                        composer3.updateRememberedValue(objRememberedValue7);
                    }
                    composer3.endReplaceGroup();
                    RepoDetailsScreenKt.SettingsRow(archiveState2, (Function1) ((KFunction) objRememberedValue7), composer3, 0);
                    SpacerKt.Spacer(SizeKt.m275height3ABfNKs(companion, Dp.m2416constructorimpl(Dp.m2416constructorimpl(f) + paddingContent.mo252calculateBottomPaddingD9Ej5fM())), composer3, 0);
                    composer3.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoDetailsScreenKt.RepoDetailsScreen$lambda$0(repo, archiveState, i, callbacks, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void GeneralInfoCard(final Repository repository, final int i, final Function0 function0, Composer composer, final int i2) {
        int i3;
        String strAsRelativeTimeString;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(69157736);
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(repository) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(i) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if ((i3 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(69157736, i3, -1, "org.fdroid.fdroid.views.repos.GeneralInfoCard (RepoDetailsScreen.kt:172)");
            }
            final LocaleListCompat localeListCompat = LocaleListCompat.getDefault();
            Intrinsics.checkNotNullExpressionValue(localeListCompat, "getDefault(...)");
            String description = repository.getDescription(localeListCompat);
            final String strReplace$default = description != null ? StringsKt.replace$default(description, "\n", " ", false, 4, (Object) null) : null;
            composerStartRestartGroup.startReplaceGroup(-1191364791);
            if (repository.getTimestamp() < 0) {
                strAsRelativeTimeString = StringResources_androidKt.stringResource(R.string.repositories_last_update_never, composerStartRestartGroup, 0);
            } else {
                strAsRelativeTimeString = UtilsKt.asRelativeTimeString(repository.getTimestamp());
            }
            composerStartRestartGroup.endReplaceGroup();
            final String strStringResource = StringResources_androidKt.stringResource(R.string.repo_last_update_index, new Object[]{strAsRelativeTimeString}, composerStartRestartGroup, 0);
            Long lastUpdated = repository.getLastUpdated();
            String strAsRelativeTimeString2 = lastUpdated != null ? UtilsKt.asRelativeTimeString(lastUpdated.longValue()) : null;
            composerStartRestartGroup.startReplaceGroup(-1191356166);
            if (strAsRelativeTimeString2 == null) {
                strAsRelativeTimeString2 = StringResources_androidKt.stringResource(R.string.repositories_last_update_never, composerStartRestartGroup, 0);
            }
            composerStartRestartGroup.endReplaceGroup();
            final String strStringResource2 = StringResources_androidKt.stringResource(R.string.repo_last_update_check, new Object[]{strAsRelativeTimeString2}, composerStartRestartGroup, 0);
            composer2 = composerStartRestartGroup;
            CardKt.ElevatedCard(null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-625181149, true, new Function3() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt.GeneralInfoCard.1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    invoke((ColumnScope) obj, (Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(ColumnScope ElevatedCard, Composer composer3, int i4) {
                    Intrinsics.checkNotNullParameter(ElevatedCard, "$this$ElevatedCard");
                    if ((i4 & 17) == 16 && composer3.getSkipping()) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-625181149, i4, -1, "org.fdroid.fdroid.views.repos.GeneralInfoCard.<anonymous> (RepoDetailsScreen.kt:188)");
                    }
                    Arrangement arrangement = Arrangement.INSTANCE;
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM245spacedBy0680j_4 = arrangement.m245spacedBy0680j_4(Dp.m2416constructorimpl(8));
                    Modifier.Companion companion = Modifier.Companion;
                    float f = 16;
                    Modifier modifierM261padding3ABfNKs = PaddingKt.m261padding3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m2416constructorimpl(f));
                    Repository repository2 = repository;
                    Function0 function02 = function0;
                    String str = strReplace$default;
                    LocaleListCompat localeListCompat2 = localeListCompat;
                    int i5 = i;
                    String str2 = strStringResource;
                    String str3 = strStringResource2;
                    Alignment.Companion companion2 = Alignment.Companion;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM245spacedBy0680j_4, companion2.getStart(), composer3, 6);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierM261padding3ABfNKs);
                    ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
                    Function0 constructor = companion3.getConstructor();
                    if (!(composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor);
                    } else {
                        composer3.useNode();
                    }
                    Composer composerM1035constructorimpl = Updater.m1035constructorimpl(composer3);
                    Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyColumnMeasurePolicy, companion3.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.m245spacedBy0680j_4(Dp.m2416constructorimpl(f)), companion2.getTop(), composer3, 6);
                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, companion);
                    Function0 constructor2 = companion3.getConstructor();
                    if (!(composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor2);
                    } else {
                        composer3.useNode();
                    }
                    Composer composerM1035constructorimpl2 = Updater.m1035constructorimpl(composer3);
                    Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyRowMeasurePolicy, companion3.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    RepoIconComposableKt.RepoIcon(repository2, SizeKt.m281size3ABfNKs(companion, Dp.m2416constructorimpl(48)), composer3, 48, 0);
                    MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(arrangement.getTop(), companion2.getStart(), composer3, 48);
                    int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap3 = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer3, companion);
                    Function0 constructor3 = companion3.getConstructor();
                    if (!(composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor3);
                    } else {
                        composer3.useNode();
                    }
                    Composer composerM1035constructorimpl3 = Updater.m1035constructorimpl(composer3);
                    Updater.m1036setimpl(composerM1035constructorimpl3, measurePolicyColumnMeasurePolicy2, companion3.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl3, currentCompositionLocalMap3, companion3.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash3 = companion3.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                        composerM1035constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM1035constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl3, modifierMaterializeModifier3, companion3.getSetModifier());
                    String name = repository2.getName(localeListCompat2);
                    if (name == null) {
                        name = "Unknown Repository";
                    }
                    String str4 = name;
                    FontWeight bold = FontWeight.Companion.getBold();
                    MaterialTheme materialTheme = MaterialTheme.INSTANCE;
                    int i6 = MaterialTheme.$stable;
                    TextKt.m772Text4IGK_g(str4, null, 0L, 0L, null, bold, null, 0L, null, null, 0L, 0, false, 1, 0, null, materialTheme.getTypography(composer3, i6).getHeadlineSmall(), composer3, 196608, 3072, 57310);
                    TextKt.m772Text4IGK_g(StringsKt.replaceFirst$default(repository2.getAddress(), "https://", "", false, 4, null), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer3, i6).getBodyMedium(), composer3, 0, 0, 65534);
                    TextKt.m772Text4IGK_g(StringResources_androidKt.pluralStringResource(R.plurals.repo_num_apps_text, i5, new Object[]{Integer.valueOf(i5)}, composer3, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer3, i6).getBodySmall(), composer3, 0, 0, 65534);
                    TextKt.m772Text4IGK_g(str2, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer3, i6).getBodySmall(), composer3, 0, 0, 65534);
                    TextKt.m772Text4IGK_g(str3, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer3, i6).getBodySmall(), composer3, 0, 0, 65534);
                    composer3.endNode();
                    composer3.endNode();
                    composer3.startReplaceGroup(-819955370);
                    if (repository2.getEnabled()) {
                        ComposeUtils.INSTANCE.FDroidButton(StringResources_androidKt.stringResource(R.string.repo_num_apps_button, composer3, 0), function02, columnScopeInstance.align(companion, companion2.getEnd()), null, composer3, 24576, 8);
                    }
                    composer3.endReplaceGroup();
                    composer3.startReplaceGroup(-819948492);
                    if (str != null && (!StringsKt.isBlank(str))) {
                        TextKt.m772Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer3, i6).getBodyMedium(), composer3, 0, 0, 65534);
                    }
                    composer3.endReplaceGroup();
                    composer3.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composer2, 24576, 15);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoDetailsScreenKt.GeneralInfoCard$lambda$1(repository, i, function0, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void BasicAuthCard(final String str, final Function0 function0, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1013372668);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1013372668, i2, -1, "org.fdroid.fdroid.views.repos.BasicAuthCard (RepoDetailsScreen.kt:248)");
            }
            CardKt.ElevatedCard(null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1243775359, true, new Function3() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt.BasicAuthCard.1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    invoke((ColumnScope) obj, (Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(ColumnScope ElevatedCard, Composer composer2, int i3) {
                    Intrinsics.checkNotNullParameter(ElevatedCard, "$this$ElevatedCard");
                    if ((i3 & 17) == 16 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1243775359, i3, -1, "org.fdroid.fdroid.views.repos.BasicAuthCard.<anonymous> (RepoDetailsScreen.kt:250)");
                    }
                    Modifier.Companion companion = Modifier.Companion;
                    Modifier modifierM261padding3ABfNKs = PaddingKt.m261padding3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m2416constructorimpl(16));
                    Function0 function02 = function0;
                    String str2 = str;
                    Arrangement arrangement = Arrangement.INSTANCE;
                    Arrangement.Vertical top = arrangement.getTop();
                    Alignment.Companion companion2 = Alignment.Companion;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(top, companion2.getStart(), composer2, 0);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM261padding3ABfNKs);
                    ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
                    Function0 constructor = companion3.getConstructor();
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM1035constructorimpl = Updater.m1035constructorimpl(composer2);
                    Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyColumnMeasurePolicy, companion3.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    String strStringResource = StringResources_androidKt.stringResource(R.string.repo_basic_auth_title, composer2, 0);
                    MaterialTheme materialTheme = MaterialTheme.INSTANCE;
                    int i4 = MaterialTheme.$stable;
                    TextKt.m772Text4IGK_g(strStringResource, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer2, i4).getBodySmall(), composer2, 0, 0, 65534);
                    SpacerKt.Spacer(SizeKt.m275height3ABfNKs(companion, Dp.m2416constructorimpl(4)), composer2, 6);
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.getStart(), companion2.getTop(), composer2, 0);
                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, companion);
                    Function0 constructor2 = companion3.getConstructor();
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor2);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM1035constructorimpl2 = Updater.m1035constructorimpl(composer2);
                    Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyRowMeasurePolicy, companion3.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    Modifier modifierWeight$default = RowScope.CC.weight$default(rowScopeInstance, companion, 1.0f, false, 2, null);
                    MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(arrangement.getTop(), companion2.getStart(), composer2, 0);
                    int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, modifierWeight$default);
                    Function0 constructor3 = companion3.getConstructor();
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor3);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM1035constructorimpl3 = Updater.m1035constructorimpl(composer2);
                    Updater.m1036setimpl(composerM1035constructorimpl3, measurePolicyColumnMeasurePolicy2, companion3.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl3, currentCompositionLocalMap3, companion3.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash3 = companion3.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                        composerM1035constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM1035constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl3, modifierMaterializeModifier3, companion3.getSetModifier());
                    TextKt.m772Text4IGK_g(StringResources_androidKt.stringResource(R.string.repo_basic_auth_username, new Object[]{str2}, composer2, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer2, i4).getBodyMedium(), composer2, 0, 0, 65534);
                    TextKt.m772Text4IGK_g(StringResources_androidKt.stringResource(R.string.repo_basic_auth_password, composer2, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, materialTheme.getTypography(composer2, i4).getBodyMedium(), composer2, 0, 0, 65534);
                    composer2.endNode();
                    ComposeUtils.INSTANCE.m2969FDroidOutlineButtonyrwZFoE(StringResources_androidKt.stringResource(R.string.repo_basic_auth_edit, composer2, 0), function02, rowScopeInstance.align(companion, companion2.getBottom()), EditKt.getEdit(Icons.INSTANCE.getDefault()), 0L, composer2, 196608, 16);
                    composer2.endNode();
                    composer2.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 24576, 15);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoDetailsScreenKt.BasicAuthCard$lambda$2(str, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void FingerprintExpandable(final String str, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(829815458);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(829815458, i2, -1, "org.fdroid.fdroid.views.repos.FingerprintExpandable (RepoDetailsScreen.kt:287)");
            }
            FDroidExpandableRowKt.FDroidExpandableRow(StringResources_androidKt.stringResource(R.string.repo_fingerprint, composerStartRestartGroup, 0), FingerprintKt.getFingerprint(Icons.INSTANCE.getDefault()), false, ComposableLambdaKt.rememberComposableLambda(1347674756, true, new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt.FingerprintExpandable.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    if ((i3 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1347674756, i3, -1, "org.fdroid.fdroid.views.repos.FingerprintExpandable.<anonymous> (RepoDetailsScreen.kt:292)");
                        }
                        String fingerprint = Utils.formatFingerprint((Context) composer2.consume(AndroidCompositionLocals_androidKt.getLocalContext()), str);
                        Intrinsics.checkNotNullExpressionValue(fingerprint, "formatFingerprint(...)");
                        TextKt.m772Text4IGK_g(fingerprint, null, 0L, 0L, null, null, FontFamily.Companion.getMonospace(), 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getBodyMedium(), composer2, 0, 0, 65470);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 3072, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoDetailsScreenKt.FingerprintExpandable$lambda$3(str, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void OfficialMirrors(final List<Mirror> list, final HashSet<String> hashSet, final Function2 function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1816630128);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(list) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(hashSet) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1816630128, i2, -1, "org.fdroid.fdroid.views.repos.OfficialMirrors (RepoDetailsScreen.kt:305)");
            }
            FDroidExpandableRowKt.FDroidExpandableRow(StringResources_androidKt.stringResource(R.string.repo_official_mirrors, composerStartRestartGroup, 0), PublicKt.getPublic(Icons.INSTANCE.getDefault()), false, ComposableLambdaKt.rememberComposableLambda(706119890, true, new C02131(list, hashSet, function2), composerStartRestartGroup, 54), composerStartRestartGroup, 3072, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoDetailsScreenKt.OfficialMirrors$lambda$4(list, hashSet, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$OfficialMirrors$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoDetailsScreen.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02131 implements Function2 {
        final /* synthetic */ HashSet<String> $disabledMirrors;
        final /* synthetic */ List<Mirror> $mirrors;
        final /* synthetic */ Function2 $setMirrorEnabled;

        C02131(List<Mirror> list, HashSet<String> hashSet, Function2 function2) {
            this.$mirrors = list;
            this.$disabledMirrors = hashSet;
            this.$setMirrorEnabled = function2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            final String flagEmoji;
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(706119890, i, -1, "org.fdroid.fdroid.views.repos.OfficialMirrors.<anonymous> (RepoDetailsScreen.kt:310)");
                }
                List<Mirror> list = this.$mirrors;
                HashSet<String> hashSet = this.$disabledMirrors;
                final Function2 function2 = this.$setMirrorEnabled;
                Modifier.Companion companion = Modifier.Companion;
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, 0);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, companion);
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
                Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyColumnMeasurePolicy, companion2.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                composer.startReplaceGroup(-1879210742);
                int i2 = 0;
                for (Object obj : list) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    final Mirror mirror = (Mirror) obj;
                    if (mirror.isOnion()) {
                        flagEmoji = "🧅";
                    } else {
                        String countryCode = mirror.getCountryCode();
                        if (countryCode == null || (flagEmoji = UtilsKt.getFlagEmoji(countryCode)) == null) {
                            flagEmoji = "";
                        }
                    }
                    String baseUrl = mirror.getBaseUrl();
                    ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1239738048, true, new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$OfficialMirrors$1$1$1$1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                            invoke((Composer) obj2, ((Number) obj3).intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i4) {
                            if ((i4 & 3) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1239738048, i4, -1, "org.fdroid.fdroid.views.repos.OfficialMirrors.<anonymous>.<anonymous>.<anonymous>.<anonymous> (RepoDetailsScreen.kt:320)");
                                }
                                TextKt.m772Text4IGK_g(flagEmoji, SizeKt.m285width3ABfNKs(Modifier.Companion, Dp.m2416constructorimpl(20)), 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 48, 0, 131068);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }, composer, 54);
                    boolean z = !hashSet.contains(mirror.getBaseUrl());
                    composer.startReplaceGroup(-1891232230);
                    boolean zChanged = composer.changed(function2) | composer.changedInstance(mirror);
                    Object objRememberedValue = composer.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$OfficialMirrors$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return RepoDetailsScreenKt.C02131.invoke$lambda$3$lambda$2$lambda$1$lambda$0(function2, mirror, ((Boolean) obj2).booleanValue());
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    composer.endReplaceGroup();
                    int i4 = i2;
                    FDroidSwitchRowKt.m2972FDroidSwitchRowHYR8e34(baseUrl, composableLambdaRememberComposableLambda, z, (Function1) objRememberedValue, false, 0.0f, composer, 48, 48);
                    composer.startReplaceGroup(-1879190017);
                    if (i4 < list.size() - 1) {
                        DividerKt.m673HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer, 0, 7);
                    }
                    composer.endReplaceGroup();
                    i2 = i3;
                }
                composer.endReplaceGroup();
                composer.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$3$lambda$2$lambda$1$lambda$0(Function2 function2, Mirror mirror, boolean z) {
            function2.invoke(mirror, Boolean.valueOf(z));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void UserMirrors(final List<Mirror> list, final HashSet<String> hashSet, final Function2 function2, final Function1 function1, final Function1 function12, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(135770230);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(list) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(hashSet) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function12) ? 16384 : 8192;
        }
        if ((i2 & 9363) != 9362 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(135770230, i2, -1, "org.fdroid.fdroid.views.repos.UserMirrors (RepoDetailsScreen.kt:341)");
            }
            FDroidExpandableRowKt.FDroidExpandableRow(StringResources_androidKt.stringResource(R.string.repo_user_mirrors, composerStartRestartGroup, 0), DnsKt.getDns(Icons.INSTANCE.getDefault()), false, ComposableLambdaKt.rememberComposableLambda(-597450280, true, new C02161(list, hashSet, function2, function1, function12), composerStartRestartGroup, 54), composerStartRestartGroup, 3072, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoDetailsScreenKt.UserMirrors$lambda$5(list, hashSet, function2, function1, function12, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$UserMirrors$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoDetailsScreen.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02161 implements Function2 {
        final /* synthetic */ HashSet<String> $disabledMirrors;
        final /* synthetic */ List<Mirror> $mirrors;
        final /* synthetic */ Function1 $onDeleteMirror;
        final /* synthetic */ Function1 $onShareMirror;
        final /* synthetic */ Function2 $setMirrorEnabled;

        C02161(List<Mirror> list, HashSet<String> hashSet, Function2 function2, Function1 function1, Function1 function12) {
            this.$mirrors = list;
            this.$disabledMirrors = hashSet;
            this.$setMirrorEnabled = function2;
            this.$onShareMirror = function1;
            this.$onDeleteMirror = function12;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-597450280, i, -1, "org.fdroid.fdroid.views.repos.UserMirrors.<anonymous> (RepoDetailsScreen.kt:346)");
                }
                List<Mirror> list = this.$mirrors;
                HashSet<String> hashSet = this.$disabledMirrors;
                final Function2 function2 = this.$setMirrorEnabled;
                final Function1 function1 = this.$onShareMirror;
                Function1 function12 = this.$onDeleteMirror;
                Modifier.Companion companion = Modifier.Companion;
                int i2 = 0;
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, 0);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, companion);
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
                Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyColumnMeasurePolicy, companion2.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                composer.startReplaceGroup(1542143509);
                int i3 = 0;
                for (Object obj : list) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    final Mirror mirror = (Mirror) obj;
                    String baseUrl = mirror.getBaseUrl();
                    boolean z = !hashSet.contains(mirror.getBaseUrl());
                    composer.startReplaceGroup(-409653798);
                    boolean zChanged = composer.changed(function2) | composer.changedInstance(mirror);
                    Object objRememberedValue = composer.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$UserMirrors$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return RepoDetailsScreenKt.C02161.invoke$lambda$8$lambda$7$lambda$1$lambda$0(function2, mirror, ((Boolean) obj2).booleanValue());
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    composer.endReplaceGroup();
                    final Function1 function13 = function12;
                    int i5 = i3;
                    int i6 = i2;
                    FDroidSwitchRowKt.m2972FDroidSwitchRowHYR8e34(baseUrl, null, z, (Function1) objRememberedValue, false, 0.0f, composer, 0, 50);
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM245spacedBy0680j_4 = Arrangement.INSTANCE.m245spacedBy0680j_4(Dp.m2416constructorimpl(16));
                    Modifier.Companion companion3 = Modifier.Companion;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM245spacedBy0680j_4, Alignment.Companion.getTop(), composer, 6);
                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, i6);
                    CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, companion3);
                    ComposeUiNode.Companion companion4 = ComposeUiNode.Companion;
                    Function0 constructor2 = companion4.getConstructor();
                    if (!(composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer.startReusableNode();
                    if (composer.getInserting()) {
                        composer.createNode(constructor2);
                    } else {
                        composer.useNode();
                    }
                    Composer composerM1035constructorimpl2 = Updater.m1035constructorimpl(composer);
                    Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyRowMeasurePolicy, companion4.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion4.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash2 = companion4.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion4.getSetModifier());
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    ComposeUtils composeUtils = ComposeUtils.INSTANCE;
                    String strStringResource = StringResources_androidKt.stringResource(R.string.menu_share, composer, i6);
                    Icons icons = Icons.INSTANCE;
                    ImageVector share = ShareKt.getShare(icons.getDefault());
                    composer.startReplaceGroup(-490393822);
                    boolean zChanged2 = composer.changed(function1) | composer.changedInstance(mirror);
                    Object objRememberedValue2 = composer.rememberedValue();
                    if (zChanged2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$UserMirrors$1$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RepoDetailsScreenKt.C02161.invoke$lambda$8$lambda$7$lambda$6$lambda$3$lambda$2(function1, mirror);
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue2);
                    }
                    composer.endReplaceGroup();
                    composeUtils.m2969FDroidOutlineButtonyrwZFoE(strStringResource, (Function0) objRememberedValue2, null, share, 0L, composer, 196608, 20);
                    String strStringResource2 = StringResources_androidKt.stringResource(R.string.delete, composer, i6);
                    ImageVector delete = DeleteKt.getDelete(icons.getDefault());
                    long jM1307getRed0d7_KjU = Color.Companion.m1307getRed0d7_KjU();
                    composer.startReplaceGroup(-490386045);
                    boolean zChanged3 = composer.changed(function13) | composer.changedInstance(mirror);
                    Object objRememberedValue3 = composer.rememberedValue();
                    if (zChanged3 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$UserMirrors$1$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RepoDetailsScreenKt.C02161.invoke$lambda$8$lambda$7$lambda$6$lambda$5$lambda$4(function13, mirror);
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue3);
                    }
                    composer.endReplaceGroup();
                    composeUtils.m2969FDroidOutlineButtonyrwZFoE(strStringResource2, (Function0) objRememberedValue3, null, delete, jM1307getRed0d7_KjU, composer, 221184, 4);
                    composer.endNode();
                    composer.startReplaceGroup(1542172511);
                    if (i5 < list.size() - 1) {
                        DividerKt.m673HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer, 0, 7);
                    }
                    composer.endReplaceGroup();
                    i2 = i6;
                    i3 = i4;
                    function12 = function13;
                }
                composer.endReplaceGroup();
                composer.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$8$lambda$7$lambda$1$lambda$0(Function2 function2, Mirror mirror, boolean z) {
            function2.invoke(mirror, Boolean.valueOf(z));
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$8$lambda$7$lambda$6$lambda$3$lambda$2(Function1 function1, Mirror mirror) {
            function1.invoke(mirror);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$8$lambda$7$lambda$6$lambda$5$lambda$4(Function1 function1, Mirror mirror) {
            function1.invoke(mirror);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SettingsRow(final ArchiveState archiveState, final Function1 function1, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1637586582);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(archiveState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1637586582, i2, -1, "org.fdroid.fdroid.views.repos.SettingsRow (RepoDetailsScreen.kt:378)");
            }
            FDroidExpandableRowKt.FDroidExpandableRow(StringResources_androidKt.stringResource(R.string.menu_settings, composerStartRestartGroup, 0), SettingsKt.getSettings(Icons.INSTANCE.getDefault()), false, ComposableLambdaKt.rememberComposableLambda(-1720677128, true, new C02151(archiveState, function1), composerStartRestartGroup, 54), composerStartRestartGroup, 3072, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoDetailsScreenKt.SettingsRow$lambda$6(archiveState, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$SettingsRow$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoDetailsScreen.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02151 implements Function2 {
        final /* synthetic */ ArchiveState $archiveState;
        final /* synthetic */ Function1 $onToggleArchiveClicked;

        C02151(ArchiveState archiveState, Function1 function1) {
            this.$archiveState = archiveState;
            this.$onToggleArchiveClicked = function1;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1720677128, i, -1, "org.fdroid.fdroid.views.repos.SettingsRow.<anonymous> (RepoDetailsScreen.kt:383)");
                }
                ArchiveState archiveState = this.$archiveState;
                if (archiveState == ArchiveState.UNKNOWN) {
                    composer.startReplaceGroup(-911196354);
                    Modifier.Companion companion = Modifier.Companion;
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
                    final Function1 function1 = this.$onToggleArchiveClicked;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, 0);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierFillMaxWidth$default);
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
                    Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyColumnMeasurePolicy, companion2.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    TextKt.m772Text4IGK_g(StringResources_androidKt.stringResource(R.string.repo_archive_unknown, composer, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
                    SpacerKt.Spacer(SizeKt.m275height3ABfNKs(companion, Dp.m2416constructorimpl(8)), composer, 6);
                    ComposeUtils composeUtils = ComposeUtils.INSTANCE;
                    String strStringResource = StringResources_androidKt.stringResource(R.string.repo_archive_check, composer, 0);
                    composer.startReplaceGroup(1175732030);
                    boolean zChanged = composer.changed(function1);
                    Object objRememberedValue = composer.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$SettingsRow$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RepoDetailsScreenKt.C02151.invoke$lambda$2$lambda$1$lambda$0(function1);
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    composer.endReplaceGroup();
                    composeUtils.m2969FDroidOutlineButtonyrwZFoE(strStringResource, (Function0) objRememberedValue, SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), null, 0L, composer, 196992, 24);
                    composer.endNode();
                    composer.endReplaceGroup();
                } else if (archiveState == ArchiveState.LOADING) {
                    composer.startReplaceGroup(-910679119);
                    ProgressIndicatorKt.m717LinearProgressIndicatorrIrjwxo(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, null), 0L, 0L, 0, 0.0f, composer, 6, 30);
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(-910554654);
                    FDroidSwitchRowKt.m2972FDroidSwitchRowHYR8e34(StringResources_androidKt.stringResource(R.string.repo_archive_toggle_description, composer, 0), null, this.$archiveState == ArchiveState.ENABLED, this.$onToggleArchiveClicked, true, 0.0f, composer, 24576, 34);
                    composer.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$2$lambda$1$lambda$0(Function1 function1) {
            function1.invoke(Boolean.TRUE);
            return Unit.INSTANCE;
        }
    }

    public static final void RepoDetailsScreenPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-307636477);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-307636477, i, -1, "org.fdroid.fdroid.views.repos.RepoDetailsScreenPreview (RepoDetailsScreen.kt:433)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$RepoDetailsScreenKt.INSTANCE.m3204getLambda7$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoDetailsScreenKt.RepoDetailsScreenPreview$lambda$7(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void BasicAuthCardPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1371964135);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1371964135, i, -1, "org.fdroid.fdroid.views.repos.BasicAuthCardPreview (RepoDetailsScreen.kt:446)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$RepoDetailsScreenKt.INSTANCE.m3205getLambda8$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoDetailsScreenKt.BasicAuthCardPreview$lambda$8(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void OfficialMirrorsPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(2140339446);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2140339446, i, -1, "org.fdroid.fdroid.views.repos.OfficialMirrorsPreview (RepoDetailsScreen.kt:455)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$RepoDetailsScreenKt.INSTANCE.m3206getLambda9$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoDetailsScreenKt.OfficialMirrorsPreview$lambda$9(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void UserMirrorsPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1059067094);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1059067094, i, -1, "org.fdroid.fdroid.views.repos.UserMirrorsPreview (RepoDetailsScreen.kt:467)");
            }
            ThemeKt.FDroidContent(false, true, ComposableSingletons$RepoDetailsScreenKt.INSTANCE.m3198getLambda10$app_fullRelease(), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.repos.RepoDetailsScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoDetailsScreenKt.UserMirrorsPreview$lambda$10(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
