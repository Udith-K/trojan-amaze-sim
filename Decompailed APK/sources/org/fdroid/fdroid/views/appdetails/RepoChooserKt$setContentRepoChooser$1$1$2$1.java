package org.fdroid.fdroid.views.appdetails;

import androidx.core.util.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: RepoChooser.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class RepoChooserKt$setContentRepoChooser$1$1$2$1 extends FunctionReferenceImpl implements Function1 {
    RepoChooserKt$setContentRepoChooser$1$1$2$1(Object obj) {
        super(1, obj, Consumer.class, "accept", "accept(Ljava/lang/Object;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).longValue());
        return Unit.INSTANCE;
    }

    public final void invoke(long j) {
        ((Consumer) this.receiver).accept(Long.valueOf(j));
    }
}
