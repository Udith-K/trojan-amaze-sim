package org.fdroid.fdroid.views.repos;

import androidx.compose.material.icons.Icons$AutoMirrored$Filled;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.res.StringResources_androidKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.fdroid.fdroid.R;

/* JADX INFO: compiled from: AddRepoIntroScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$AddRepoIntroScreenKt {
    public static final ComposableSingletons$AddRepoIntroScreenKt INSTANCE = new ComposableSingletons$AddRepoIntroScreenKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2 f50lambda1 = ComposableLambdaKt.composableLambdaInstance(2031892277, false, new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoIntroScreenKt$lambda-1$1
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
                ComposerKt.traceEventStart(2031892277, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoIntroScreenKt.lambda-1.<anonymous> (AddRepoIntroScreen.kt:96)");
            }
            IconKt.m691Iconww6aTOc(ArrowBackKt.getArrowBack(Icons$AutoMirrored$Filled.INSTANCE), StringResources_androidKt.stringResource(R.string.back, composer, 0), (Modifier) null, 0L, composer, 0, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2 f51lambda2 = ComposableLambdaKt.composableLambdaInstance(518708409, false, ComposableSingletons$AddRepoIntroScreenKt$lambda2$1.INSTANCE);

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function2 f52lambda3 = ComposableLambdaKt.composableLambdaInstance(817502263, false, ComposableSingletons$AddRepoIntroScreenKt$lambda3$1.INSTANCE);

    /* JADX INFO: renamed from: getLambda-1$app_fullRelease, reason: not valid java name */
    public final Function2 m3190getLambda1$app_fullRelease() {
        return f50lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_fullRelease, reason: not valid java name */
    public final Function2 m3191getLambda2$app_fullRelease() {
        return f51lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_fullRelease, reason: not valid java name */
    public final Function2 m3192getLambda3$app_fullRelease() {
        return f52lambda3;
    }
}
