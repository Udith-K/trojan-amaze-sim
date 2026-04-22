package org.fdroid.fdroid.views.repos;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.download.Mirror;

/* JADX INFO: renamed from: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt$lambda-9$1, reason: invalid class name */
/* JADX INFO: compiled from: RepoDetailsScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class ComposableSingletons$RepoDetailsScreenKt$lambda9$1 implements Function2 {
    public static final ComposableSingletons$RepoDetailsScreenKt$lambda9$1 INSTANCE = new ComposableSingletons$RepoDetailsScreenKt$lambda9$1();

    ComposableSingletons$RepoDetailsScreenKt$lambda9$1() {
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if ((i & 3) != 2 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1983056832, i, -1, "org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt.lambda-9.<anonymous> (RepoDetailsScreen.kt:457)");
            }
            List listListOf = CollectionsKt.listOf(new Mirror("https://mirror.example.com/fdroid/repo", null, false, 6, null));
            HashSet hashSet = new HashSet();
            composer.startReplaceGroup(-2119941026);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function2() { // from class: org.fdroid.fdroid.views.repos.ComposableSingletons$RepoDetailsScreenKt$lambda-9$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ComposableSingletons$RepoDetailsScreenKt$lambda9$1.invoke$lambda$1$lambda$0((Mirror) obj, ((Boolean) obj2).booleanValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceGroup();
            RepoDetailsScreenKt.OfficialMirrors(listListOf, hashSet, (Function2) objRememberedValue, composer, 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
                return;
            }
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invoke$lambda$1$lambda$0(Mirror mirror, boolean z) {
        Intrinsics.checkNotNullParameter(mirror, "<unused var>");
        return Unit.INSTANCE;
    }
}
