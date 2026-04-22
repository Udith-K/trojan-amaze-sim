package org.fdroid.fdroid.views.repos;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: RepoDetailsScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class RepoDetailsScreenKt$RepoDetailsScreen$2$1$8$1 extends FunctionReferenceImpl implements Function1 {
    RepoDetailsScreenKt$RepoDetailsScreen$2$1$8$1(Object obj) {
        super(1, obj, RepoDetailsScreenCallbacks.class, "onToggleArchiveClicked", "onToggleArchiveClicked(Z)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Boolean) obj).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(boolean z) {
        ((RepoDetailsScreenCallbacks) this.receiver).onToggleArchiveClicked(z);
    }
}
