package org.fdroid.fdroid.views.appdetails;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.ComposeView;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.core.os.LocaleListCompat;
import androidx.core.util.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.ui.theme.ThemeKt;
import org.fdroid.fdroid.views.appdetails.RepoChooserKt;
import org.fdroid.index.IndexFormatVersion;

/* JADX INFO: compiled from: RepoChooser.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aH\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u000b\u001a]\u0010\r\u001a\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u000e2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a5\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u000f\u001a\u00020\u00102\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u0017H\u0003¢\u0006\u0002\u0010\u0018\u001a\u001d\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0003¢\u0006\u0002\u0010\u001b\u001a\r\u0010\u001c\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u001d\u001a\r\u0010\u001e\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u001d\u001a\r\u0010\u001f\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u001d¨\u0006 ²\u0006\n\u0010!\u001a\u00020\u0015X\u008a\u008e\u0002"}, d2 = {"setContentRepoChooser", "", "composeView", "Landroidx/compose/ui/platform/ComposeView;", "repos", "", "Lorg/fdroid/database/Repository;", "currentRepoId", "", "preferredRepoId", "onRepoChanged", "Landroidx/core/util/Consumer;", "onPreferredRepoChanged", "RepoChooser", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "(Ljava/util/List;JJLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "RepoMenuItem", "repo", "isPreferred", "", "onClick", "Lkotlin/Function0;", "(Lorg/fdroid/database/Repository;ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "getRepoString", "Landroidx/compose/ui/text/AnnotatedString;", "(Lorg/fdroid/database/Repository;ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/text/AnnotatedString;", "RepoChooserSingleRepoPreview", "(Landroidx/compose/runtime/Composer;I)V", "RepoChooserPreview", "RepoChooserNightPreview", "app_fullRelease", "expanded"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RepoChooserKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoChooser$lambda$0(List list, long j, long j2, Function1 function1, Function1 function12, Modifier modifier, int i, int i2, Composer composer, int i3) {
        RepoChooser(list, j, j2, function1, function12, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoChooser$lambda$16(List list, long j, long j2, Function1 function1, Function1 function12, Modifier modifier, int i, int i2, Composer composer, int i3) {
        RepoChooser(list, j, j2, function1, function12, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoChooserNightPreview$lambda$21(int i, Composer composer, int i2) {
        RepoChooserNightPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoChooserPreview$lambda$20(int i, Composer composer, int i2) {
        RepoChooserPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoChooserSingleRepoPreview$lambda$19(int i, Composer composer, int i2) {
        RepoChooserSingleRepoPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoMenuItem$lambda$17(Repository repository, boolean z, Modifier modifier, Function0 function0, int i, int i2, Composer composer, int i3) {
        RepoMenuItem(repository, z, modifier, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void setContentRepoChooser(ComposeView composeView, final List<Repository> repos, final long j, final long j2, final Consumer onRepoChanged, final Consumer onPreferredRepoChanged) {
        Intrinsics.checkNotNullParameter(composeView, "composeView");
        Intrinsics.checkNotNullParameter(repos, "repos");
        Intrinsics.checkNotNullParameter(onRepoChanged, "onRepoChanged");
        Intrinsics.checkNotNullParameter(onPreferredRepoChanged, "onPreferredRepoChanged");
        composeView.setContent(ComposableLambdaKt.composableLambdaInstance(-34211087, true, new Function2() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt.setContentRepoChooser.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i) {
                if ((i & 3) != 2 || !composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-34211087, i, -1, "org.fdroid.fdroid.views.appdetails.setContentRepoChooser.<anonymous> (RepoChooser.kt:55)");
                    }
                    final List<Repository> list = repos;
                    final long j3 = j;
                    final long j4 = j2;
                    final Consumer consumer = onRepoChanged;
                    final Consumer consumer2 = onPreferredRepoChanged;
                    ThemeKt.FDroidContent(false, false, ComposableLambdaKt.rememberComposableLambda(-1541427525, true, new Function2() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt.setContentRepoChooser.1.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i2) {
                            if ((i2 & 3) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1541427525, i2, -1, "org.fdroid.fdroid.views.appdetails.setContentRepoChooser.<anonymous>.<anonymous> (RepoChooser.kt:56)");
                                }
                                List<Repository> list2 = list;
                                long j5 = j3;
                                long j6 = j4;
                                Consumer consumer3 = consumer;
                                composer2.startReplaceGroup(637167746);
                                boolean zChangedInstance = composer2.changedInstance(consumer3);
                                Object objRememberedValue = composer2.rememberedValue();
                                if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                                    objRememberedValue = new RepoChooserKt$setContentRepoChooser$1$1$1$1(consumer3);
                                    composer2.updateRememberedValue(objRememberedValue);
                                }
                                composer2.endReplaceGroup();
                                Function1 function1 = (Function1) ((KFunction) objRememberedValue);
                                Consumer consumer4 = consumer2;
                                composer2.startReplaceGroup(637169803);
                                boolean zChangedInstance2 = composer2.changedInstance(consumer4);
                                Object objRememberedValue2 = composer2.rememberedValue();
                                if (zChangedInstance2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                                    objRememberedValue2 = new RepoChooserKt$setContentRepoChooser$1$1$2$1(consumer4);
                                    composer2.updateRememberedValue(objRememberedValue2);
                                }
                                composer2.endReplaceGroup();
                                RepoChooserKt.RepoChooser(list2, j5, j6, function1, (Function1) ((KFunction) objRememberedValue2), BackgroundKt.m97backgroundbw27NRU$default(Modifier.Companion, MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).m648getSurfaceContainerLow0d7_KjU(), null, 2, null), composer2, 0, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }, composer, 54), composer, 384, 3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer.skipToGroupEnd();
            }
        }));
    }

    /* JADX WARN: Removed duplicated region for block: B:168:0x04f7  */
    /* JADX WARN: Removed duplicated region for block: B:176:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0119  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void RepoChooser(final java.util.List<org.fdroid.database.Repository> r112, final long r113, final long r115, final kotlin.jvm.functions.Function1 r117, final kotlin.jvm.functions.Function1 r118, androidx.compose.ui.Modifier r119, androidx.compose.runtime.Composer r120, final int r121, final int r122) {
        /*
            Method dump skipped, instruction units count: 1303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.views.appdetails.RepoChooserKt.RepoChooser(java.util.List, long, long, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean RepoChooser$lambda$2(MutableState mutableState) {
        return ((Boolean) mutableState.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RepoChooser$lambda$3(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoChooser$lambda$15$lambda$12$lambda$9$lambda$8(TextFieldValue it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoChooser$lambda$15$lambda$12$lambda$7$lambda$6$lambda$5(MutableState mutableState) {
        RepoChooser$lambda$3(mutableState, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoChooser$lambda$15$lambda$12$lambda$11$lambda$10(MutableState mutableState) {
        RepoChooser$lambda$3(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit RepoChooser$lambda$15$lambda$14$lambda$13(Function1 function1, Repository repository) {
        function1.invoke(Long.valueOf(repository.getRepoId()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void RepoMenuItem(final org.fdroid.database.Repository r19, final boolean r20, androidx.compose.ui.Modifier r21, final kotlin.jvm.functions.Function0 r22, androidx.compose.runtime.Composer r23, final int r24, final int r25) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.views.appdetails.RepoChooserKt.RepoMenuItem(org.fdroid.database.Repository, boolean, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnnotatedString getRepoString(Repository repository, boolean z, Composer composer, int i) {
        composer.startReplaceGroup(-2007149080);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2007149080, i, -1, "org.fdroid.fdroid.views.appdetails.getRepoString (RepoChooser.kt:188)");
        }
        AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, null);
        LocaleListCompat localeListCompat = LocaleListCompat.getDefault();
        Intrinsics.checkNotNullExpressionValue(localeListCompat, "getDefault(...)");
        String name = repository.getName(localeListCompat);
        if (name == null) {
            name = "Unknown Repository";
        }
        builder.append(name);
        composer.startReplaceGroup(-839144080);
        if (z) {
            builder.append(" ");
            builder.pushStyle(new SpanStyle(0L, 0L, FontWeight.Companion.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, null, 65531, null));
            builder.append(" ");
            builder.append(StringResources_androidKt.stringResource(R.string.app_details_repository_preferred, composer, 0));
        }
        composer.endReplaceGroup();
        AnnotatedString annotatedString = builder.toAnnotatedString();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return annotatedString;
    }

    public static final void RepoChooserSingleRepoPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1842411059);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1842411059, i, -1, "org.fdroid.fdroid.views.appdetails.RepoChooserSingleRepoPreview (RepoChooser.kt:200)");
            }
            ThemeKt.FDroidContent(false, true, ComposableLambdaKt.rememberComposableLambda(-629146327, true, new C02071(new Repository(1L, "1", 1L, IndexFormatVersion.TWO, "null", 1L, 1, 1L, null, null, 768, null)), composerStartRestartGroup, 54), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoChooserKt.RepoChooserSingleRepoPreview$lambda$19(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.appdetails.RepoChooserKt$RepoChooserSingleRepoPreview$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoChooser.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02071 implements Function2 {
        final /* synthetic */ Repository $repo1;

        C02071(Repository repository) {
            this.$repo1 = repository;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$1$lambda$0(Repository it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$3$lambda$2(long j) {
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-629146327, i, -1, "org.fdroid.fdroid.views.appdetails.RepoChooserSingleRepoPreview.<anonymous> (RepoChooser.kt:203)");
            }
            List listListOf = CollectionsKt.listOf(this.$repo1);
            composer.startReplaceGroup(91361993);
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt$RepoChooserSingleRepoPreview$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RepoChooserKt.C02071.invoke$lambda$1$lambda$0((Repository) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function1 function1 = (Function1) objRememberedValue;
            composer.endReplaceGroup();
            composer.startReplaceGroup(91362121);
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt$RepoChooserSingleRepoPreview$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RepoChooserKt.C02071.invoke$lambda$3$lambda$2(((Long) obj).longValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceGroup();
            RepoChooserKt.RepoChooser(listListOf, 1L, 1L, function1, (Function1) objRememberedValue2, null, composer, 28080, 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static final void RepoChooserPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1041903111);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1041903111, i, -1, "org.fdroid.fdroid.views.appdetails.RepoChooserPreview (RepoChooser.kt:209)");
            }
            IndexFormatVersion indexFormatVersion = IndexFormatVersion.TWO;
            ThemeKt.FDroidContent(false, true, ComposableLambdaKt.rememberComposableLambda(-1708329873, true, new C02061(new Repository(1L, "1", 1L, indexFormatVersion, "null", 1L, 1, 1L, null, null, 768, null), new Repository(2L, "2", 2L, indexFormatVersion, "null", 2L, 2, 2L, null, null, 768, null), new Repository(3L, "2", 3L, indexFormatVersion, "null", 3L, 3, 3L, null, null, 768, null)), composerStartRestartGroup, 54), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoChooserKt.RepoChooserPreview$lambda$20(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.appdetails.RepoChooserKt$RepoChooserPreview$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RepoChooser.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C02061 implements Function2 {
        final /* synthetic */ Repository $repo1;
        final /* synthetic */ Repository $repo2;
        final /* synthetic */ Repository $repo3;

        C02061(Repository repository, Repository repository2, Repository repository3) {
            this.$repo1 = repository;
            this.$repo2 = repository2;
            this.$repo3 = repository3;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$1$lambda$0(Repository it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$3$lambda$2(long j) {
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1708329873, i, -1, "org.fdroid.fdroid.views.appdetails.RepoChooserPreview.<anonymous> (RepoChooser.kt:214)");
            }
            List listListOf = CollectionsKt.listOf((Object[]) new Repository[]{this.$repo1, this.$repo2, this.$repo3});
            composer.startReplaceGroup(269895715);
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt$RepoChooserPreview$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RepoChooserKt.C02061.invoke$lambda$1$lambda$0((Repository) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function1 function1 = (Function1) objRememberedValue;
            composer.endReplaceGroup();
            composer.startReplaceGroup(269895843);
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt$RepoChooserPreview$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RepoChooserKt.C02061.invoke$lambda$3$lambda$2(((Long) obj).longValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceGroup();
            RepoChooserKt.RepoChooser(listListOf, 1L, 1L, function1, (Function1) objRememberedValue2, null, composer, 28080, 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static final void RepoChooserNightPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(560426203);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(560426203, i, -1, "org.fdroid.fdroid.views.appdetails.RepoChooserNightPreview (RepoChooser.kt:220)");
            }
            IndexFormatVersion indexFormatVersion = IndexFormatVersion.TWO;
            ThemeKt.FDroidContent(false, true, ComposableLambdaKt.rememberComposableLambda(731997221, true, new AnonymousClass1(new Repository(1L, "1", 1L, indexFormatVersion, "null", 1L, 1, 1L, null, null, 768, null), new Repository(2L, "2", 2L, indexFormatVersion, "null", 2L, 2, 2L, null, null, 768, null), new Repository(3L, "2", 3L, indexFormatVersion, "null", 3L, 3, 3L, null, null, 768, null)), composerStartRestartGroup, 54), composerStartRestartGroup, 432, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RepoChooserKt.RepoChooserNightPreview$lambda$21(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.views.appdetails.RepoChooserKt$RepoChooserNightPreview$1, reason: invalid class name */
    /* JADX INFO: compiled from: RepoChooser.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function2 {
        final /* synthetic */ Repository $repo1;
        final /* synthetic */ Repository $repo2;
        final /* synthetic */ Repository $repo3;

        AnonymousClass1(Repository repository, Repository repository2, Repository repository3) {
            this.$repo1 = repository;
            this.$repo2 = repository2;
            this.$repo3 = repository3;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$1$lambda$0(Repository it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$3$lambda$2(long j) {
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(731997221, i, -1, "org.fdroid.fdroid.views.appdetails.RepoChooserNightPreview.<anonymous> (RepoChooser.kt:225)");
            }
            List listListOf = CollectionsKt.listOf((Object[]) new Repository[]{this.$repo1, this.$repo2, this.$repo3});
            composer.startReplaceGroup(1982018839);
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt$RepoChooserNightPreview$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RepoChooserKt.AnonymousClass1.invoke$lambda$1$lambda$0((Repository) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function1 function1 = (Function1) objRememberedValue;
            composer.endReplaceGroup();
            composer.startReplaceGroup(1982018967);
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt$RepoChooserNightPreview$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RepoChooserKt.AnonymousClass1.invoke$lambda$3$lambda$2(((Long) obj).longValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceGroup();
            RepoChooserKt.RepoChooser(listListOf, 1L, 2L, function1, (Function1) objRememberedValue2, null, composer, 28080, 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }
}
