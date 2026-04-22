package org.fdroid.fdroid.views.repos;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.repo.None;

/* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoIntroScreenKt$lambda-3$1, reason: invalid class name */
/* JADX INFO: compiled from: AddRepoIntroScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class ComposableSingletons$AddRepoIntroScreenKt$lambda3$1 implements Function2 {
    public static final ComposableSingletons$AddRepoIntroScreenKt$lambda3$1 INSTANCE = new ComposableSingletons$AddRepoIntroScreenKt$lambda3$1();

    ComposableSingletons$AddRepoIntroScreenKt$lambda3$1() {
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(817502263, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoIntroScreenKt.lambda-3.<anonymous> (AddRepoIntroScreen.kt:247)");
        }
        None none = None.INSTANCE;
        composer.startReplaceGroup(2068683606);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new Function1() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoIntroScreenKt$lambda-3$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ComposableSingletons$AddRepoIntroScreenKt$lambda3$1.invoke$lambda$1$lambda$0((String) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Function1 function1 = (Function1) objRememberedValue;
        composer.endReplaceGroup();
        composer.startReplaceGroup(2068683734);
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new Function0() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoIntroScreenKt$lambda-3$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Function0 function0 = (Function0) objRememberedValue2;
        composer.endReplaceGroup();
        composer.startReplaceGroup(2068683862);
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new Function0() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$AddRepoIntroScreenKt$lambda-3$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        composer.endReplaceGroup();
        AddRepoIntroScreenKt.AddRepoIntroScreen(none, function1, function0, (Function0) objRememberedValue3, composer, 3504);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
