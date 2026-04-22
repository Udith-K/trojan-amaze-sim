package org.fdroid.fdroid.views.repos;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.unit.Dp;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.fdroid.repo.AddRepoError;

/* JADX INFO: compiled from: AddRepoErrorScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$AddRepoErrorScreenKt {
    public static final ComposableSingletons$AddRepoErrorScreenKt INSTANCE = new ComposableSingletons$AddRepoErrorScreenKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2 f45lambda1 = ComposableLambdaKt.composableLambdaInstance(385102202, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoErrorScreenKt$lambda-1$1
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
                ComposerKt.traceEventStart(385102202, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoErrorScreenKt.lambda-1.<anonymous> (AddRepoErrorScreen.kt:82)");
            }
            AddRepoErrorScreenKt.AddRepoErrorScreen(PaddingKt.m257PaddingValues0680j_4(Dp.m2416constructorimpl(0)), new AddRepoError(AddRepoError.ErrorType.INVALID_FINGERPRINT, null, 2, null), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2 f46lambda2 = ComposableLambdaKt.composableLambdaInstance(-1510303211, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoErrorScreenKt$lambda-2$1
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
                ComposerKt.traceEventStart(-1510303211, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoErrorScreenKt.lambda-2.<anonymous> (AddRepoErrorScreen.kt:90)");
            }
            AddRepoErrorScreenKt.AddRepoErrorScreen(PaddingKt.m257PaddingValues0680j_4(Dp.m2416constructorimpl(0)), new AddRepoError(AddRepoError.ErrorType.IO_ERROR, new IOException("foo bar")), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function2 f47lambda3 = ComposableLambdaKt.composableLambdaInstance(-1680383896, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoErrorScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1680383896, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoErrorScreenKt.lambda-3.<anonymous> (AddRepoErrorScreen.kt:98)");
                }
                AddRepoErrorScreenKt.AddRepoErrorScreen(PaddingKt.m257PaddingValues0680j_4(Dp.m2416constructorimpl(0)), new AddRepoError(AddRepoError.ErrorType.INVALID_INDEX, new RuntimeException("foo bar")), composer, 6);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function2 f48lambda4 = ComposableLambdaKt.composableLambdaInstance(1969071195, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoErrorScreenKt$lambda-4$1
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
                ComposerKt.traceEventStart(1969071195, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoErrorScreenKt.lambda-4.<anonymous> (AddRepoErrorScreen.kt:109)");
            }
            AddRepoErrorScreenKt.AddRepoErrorScreen(PaddingKt.m257PaddingValues0680j_4(Dp.m2416constructorimpl(0)), new AddRepoError(AddRepoError.ErrorType.UNKNOWN_SOURCES_DISALLOWED, null, 2, null), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-5, reason: not valid java name */
    public static Function2 f49lambda5 = ComposableLambdaKt.composableLambdaInstance(1078922837, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoErrorScreenKt$lambda-5$1
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
                ComposerKt.traceEventStart(1078922837, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoErrorScreenKt.lambda-5.<anonymous> (AddRepoErrorScreen.kt:117)");
            }
            AddRepoErrorScreenKt.AddRepoErrorScreen(PaddingKt.m257PaddingValues0680j_4(Dp.m2416constructorimpl(0)), new AddRepoError(AddRepoError.ErrorType.IS_ARCHIVE_REPO, null, 2, null), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_fullRelease, reason: not valid java name */
    public final Function2 m3185getLambda1$app_fullRelease() {
        return f45lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_fullRelease, reason: not valid java name */
    public final Function2 m3186getLambda2$app_fullRelease() {
        return f46lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_fullRelease, reason: not valid java name */
    public final Function2 m3187getLambda3$app_fullRelease() {
        return f47lambda3;
    }

    /* JADX INFO: renamed from: getLambda-4$app_fullRelease, reason: not valid java name */
    public final Function2 m3188getLambda4$app_fullRelease() {
        return f48lambda4;
    }

    /* JADX INFO: renamed from: getLambda-5$app_fullRelease, reason: not valid java name */
    public final Function2 m3189getLambda5$app_fullRelease() {
        return f49lambda5;
    }
}
