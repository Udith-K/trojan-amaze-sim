package org.fdroid.fdroid.views.repos;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.Icons$AutoMirrored$Filled;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material.icons.filled.DeleteKt;
import androidx.compose.material.icons.filled.InfoKt;
import androidx.compose.material.icons.filled.QrCodeKt;
import androidx.compose.material.icons.filled.ShareKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.res.StringResources_androidKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.fdroid.database.RepositoryKt;
import org.fdroid.fdroid.R;

/* JADX INFO: compiled from: RepoDetailsScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$RepoDetailsScreenKt {
    public static final ComposableSingletons$RepoDetailsScreenKt INSTANCE = new ComposableSingletons$RepoDetailsScreenKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2 f53lambda1 = ComposableLambdaKt.composableLambdaInstance(790925101, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(790925101, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt.lambda-1.<anonymous> (RepoDetailsScreen.kt:101)");
            }
            TextKt.m772Text4IGK_g(StringResources_androidKt.stringResource(R.string.repo_details, composer, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2 f55lambda2 = ComposableLambdaKt.composableLambdaInstance(-319596498, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-319596498, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt.lambda-2.<anonymous> (RepoDetailsScreen.kt:97)");
            }
            IconKt.m691Iconww6aTOc(ArrowBackKt.getArrowBack(Icons$AutoMirrored$Filled.INSTANCE), StringResources_androidKt.stringResource(R.string.back, composer, 0), (Modifier) null, 0L, composer, 0, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function2 f56lambda3 = ComposableLambdaKt.composableLambdaInstance(-228413121, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-228413121, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt.lambda-3.<anonymous> (RepoDetailsScreen.kt:105)");
            }
            IconKt.m691Iconww6aTOc(ShareKt.getShare(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.share_repository, composer, 0), (Modifier) null, 0L, composer, 0, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function2 f57lambda4 = ComposableLambdaKt.composableLambdaInstance(1598693110, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt$lambda-4$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1598693110, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt.lambda-4.<anonymous> (RepoDetailsScreen.kt:108)");
            }
            IconKt.m691Iconww6aTOc(QrCodeKt.getQrCode(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.show_repository_qr, composer, 0), (Modifier) null, 0L, composer, 0, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-5, reason: not valid java name */
    public static Function2 f58lambda5 = ComposableLambdaKt.composableLambdaInstance(-2048046729, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt$lambda-5$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2048046729, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt.lambda-5.<anonymous> (RepoDetailsScreen.kt:111)");
            }
            IconKt.m691Iconww6aTOc(DeleteKt.getDelete(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.delete, composer, 0), (Modifier) null, 0L, composer, 0, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-6, reason: not valid java name */
    public static Function2 f59lambda6 = ComposableLambdaKt.composableLambdaInstance(-1399819272, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt$lambda-6$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1399819272, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt.lambda-6.<anonymous> (RepoDetailsScreen.kt:114)");
            }
            IconKt.m691Iconww6aTOc(InfoKt.getInfo(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.repo_details, composer, 0), (Modifier) null, 0L, composer, 0, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-7, reason: not valid java name */
    public static Function2 f60lambda7 = ComposableLambdaKt.composableLambdaInstance(1363354573, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt$lambda-7$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1363354573, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt.lambda-7.<anonymous> (RepoDetailsScreen.kt:435)");
                }
                RepoDetailsScreenKt.RepoDetailsScreen(RepositoryKt.getDUMMY_TEST_REPO(), ArchiveState.ENABLED, 42, RepoDetailsScreenKt.emptyCallbacks, composer, 3504);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-8, reason: not valid java name */
    public static Function2 f61lambda8 = ComposableLambdaKt.composableLambdaInstance(742178531, false, ComposableSingletons$RepoDetailsScreenKt$lambda8$1.INSTANCE);

    /* JADX INFO: renamed from: lambda-9, reason: not valid java name */
    public static Function2 f62lambda9 = ComposableLambdaKt.composableLambdaInstance(-1983056832, false, ComposableSingletons$RepoDetailsScreenKt$lambda9$1.INSTANCE);

    /* JADX INFO: renamed from: lambda-10, reason: not valid java name */
    public static Function2 f54lambda10 = ComposableLambdaKt.composableLambdaInstance(1874673952, false, ComposableSingletons$RepoDetailsScreenKt$lambda10$1.INSTANCE);

    /* JADX INFO: renamed from: getLambda-1$app_fullRelease, reason: not valid java name */
    public final Function2 m3197getLambda1$app_fullRelease() {
        return f53lambda1;
    }

    /* JADX INFO: renamed from: getLambda-10$app_fullRelease, reason: not valid java name */
    public final Function2 m3198getLambda10$app_fullRelease() {
        return f54lambda10;
    }

    /* JADX INFO: renamed from: getLambda-2$app_fullRelease, reason: not valid java name */
    public final Function2 m3199getLambda2$app_fullRelease() {
        return f55lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_fullRelease, reason: not valid java name */
    public final Function2 m3200getLambda3$app_fullRelease() {
        return f56lambda3;
    }

    /* JADX INFO: renamed from: getLambda-4$app_fullRelease, reason: not valid java name */
    public final Function2 m3201getLambda4$app_fullRelease() {
        return f57lambda4;
    }

    /* JADX INFO: renamed from: getLambda-5$app_fullRelease, reason: not valid java name */
    public final Function2 m3202getLambda5$app_fullRelease() {
        return f58lambda5;
    }

    /* JADX INFO: renamed from: getLambda-6$app_fullRelease, reason: not valid java name */
    public final Function2 m3203getLambda6$app_fullRelease() {
        return f59lambda6;
    }

    /* JADX INFO: renamed from: getLambda-7$app_fullRelease, reason: not valid java name */
    public final Function2 m3204getLambda7$app_fullRelease() {
        return f60lambda7;
    }

    /* JADX INFO: renamed from: getLambda-8$app_fullRelease, reason: not valid java name */
    public final Function2 m3205getLambda8$app_fullRelease() {
        return f61lambda8;
    }

    /* JADX INFO: renamed from: getLambda-9$app_fullRelease, reason: not valid java name */
    public final Function2 m3206getLambda9$app_fullRelease() {
        return f62lambda9;
    }
}
