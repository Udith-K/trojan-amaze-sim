package org.fdroid.fdroid.views.repos;

import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsSizeKt;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RepoPreviewScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$RepoPreviewScreenKt {
    public static final ComposableSingletons$RepoPreviewScreenKt INSTANCE = new ComposableSingletons$RepoPreviewScreenKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function3 f63lambda1 = ComposableLambdaKt.composableLambdaInstance(639195634, false, new Function3() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoPreviewScreenKt$lambda-1$1
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
                ComposerKt.traceEventStart(639195634, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$RepoPreviewScreenKt.lambda-1.<anonymous> (RepoPreviewScreen.kt:100)");
            }
            SpacerKt.Spacer(WindowInsetsSizeKt.windowInsetsBottomHeight(Modifier.Companion, WindowInsets_androidKt.getSystemBars(WindowInsets.Companion, composer, 6)), composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_fullRelease, reason: not valid java name */
    public final Function3 m3210getLambda1$app_fullRelease() {
        return f63lambda1;
    }
}
