package org.fdroid.fdroid.views.repos;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.download.Mirror;

/* JADX INFO: compiled from: RepoDetailsScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class RepoDetailsScreenKt$RepoDetailsScreen$2$1$7$1 extends FunctionReferenceImpl implements Function1 {
    RepoDetailsScreenKt$RepoDetailsScreen$2$1$7$1(Object obj) {
        super(1, obj, RepoDetailsScreenCallbacks.class, "onDeleteMirror", "onDeleteMirror(Lorg/fdroid/download/Mirror;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Mirror) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Mirror p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((RepoDetailsScreenCallbacks) this.receiver).onDeleteMirror(p0);
    }
}
