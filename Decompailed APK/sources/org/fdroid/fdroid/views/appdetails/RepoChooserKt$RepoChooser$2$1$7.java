package org.fdroid.fdroid.views.appdetails;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.database.Repository;

/* JADX INFO: compiled from: RepoChooser.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class RepoChooserKt$RepoChooser$2$1$7 implements Function3 {
    final /* synthetic */ MutableState $expanded$delegate;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Function1 $onRepoChanged;
    final /* synthetic */ long $preferredRepoId;
    final /* synthetic */ List<Repository> $repos;

    RepoChooserKt$RepoChooser$2$1$7(List<Repository> list, long j, Modifier modifier, Function1 function1, MutableState mutableState) {
        this.$repos = list;
        this.$preferredRepoId = j;
        this.$modifier = modifier;
        this.$onRepoChanged = function1;
        this.$expanded$delegate = mutableState;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((ColumnScope) obj, (Composer) obj2, ((Number) obj3).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(ColumnScope DropdownMenu, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(DropdownMenu, "$this$DropdownMenu");
        if ((i & 17) != 16 || !composer.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1094875901, i, -1, "org.fdroid.fdroid.views.appdetails.RepoChooser.<anonymous>.<anonymous>.<anonymous> (RepoChooser.kt:142)");
            }
            long j = this.$preferredRepoId;
            Modifier modifier = this.$modifier;
            final Function1 function1 = this.$onRepoChanged;
            final MutableState mutableState = this.$expanded$delegate;
            for (final Repository repository : this.$repos) {
                boolean z = repository.getRepoId() == j;
                composer.startReplaceGroup(-2088509638);
                boolean zChanged = composer.changed(function1) | composer.changedInstance(repository);
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: org.fdroid.fdroid.views.appdetails.RepoChooserKt$RepoChooser$2$1$7$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RepoChooserKt$RepoChooser$2$1$7.invoke$lambda$2$lambda$1$lambda$0(function1, repository, mutableState);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceGroup();
                RepoChooserKt.RepoMenuItem(repository, z, modifier, (Function0) objRememberedValue, composer, 0, 0);
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
    public static final Unit invoke$lambda$2$lambda$1$lambda$0(Function1 function1, Repository repository, MutableState mutableState) {
        function1.invoke(repository);
        RepoChooserKt.RepoChooser$lambda$3(mutableState, false);
        return Unit.INSTANCE;
    }
}
