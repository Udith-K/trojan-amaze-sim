package org.fdroid.fdroid.views.appdetails;

import androidx.core.util.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.database.Repository;

/* JADX INFO: compiled from: RepoChooser.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class RepoChooserKt$setContentRepoChooser$1$1$1$1 extends FunctionReferenceImpl implements Function1 {
    RepoChooserKt$setContentRepoChooser$1$1$1$1(Object obj) {
        super(1, obj, Consumer.class, "accept", "accept(Ljava/lang/Object;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Repository) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Repository p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((Consumer) this.receiver).accept(p0);
    }
}
