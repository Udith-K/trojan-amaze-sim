package org.fdroid.fdroid.views.repos;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt$lambda-8$1, reason: invalid class name */
/* JADX INFO: compiled from: RepoDetailsScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class ComposableSingletons$RepoDetailsScreenKt$lambda8$1 implements Function2 {
    public static final ComposableSingletons$RepoDetailsScreenKt$lambda8$1 INSTANCE = new ComposableSingletons$RepoDetailsScreenKt$lambda8$1();

    ComposableSingletons$RepoDetailsScreenKt$lambda8$1() {
    }

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
            ComposerKt.traceEventStart(742178531, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt.lambda-8.<anonymous> (RepoDetailsScreen.kt:449)");
        }
        composer.startReplaceGroup(667465461);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt$lambda-8$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceGroup();
        RepoDetailsScreenKt.BasicAuthCard("username", (Function0) objRememberedValue, composer, 54);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
