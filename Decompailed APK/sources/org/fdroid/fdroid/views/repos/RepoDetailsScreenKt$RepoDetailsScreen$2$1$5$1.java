package org.fdroid.fdroid.views.repos;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.download.Mirror;

/* JADX INFO: compiled from: RepoDetailsScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class RepoDetailsScreenKt$RepoDetailsScreen$2$1$5$1 extends FunctionReferenceImpl implements Function2 {
    RepoDetailsScreenKt$RepoDetailsScreen$2$1$5$1(Object obj) {
        super(2, obj, RepoDetailsScreenCallbacks.class, "setMirrorEnabled", "setMirrorEnabled(Lorg/fdroid/download/Mirror;Z)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Mirror) obj, ((Boolean) obj2).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Mirror p0, boolean z) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((RepoDetailsScreenCallbacks) this.receiver).setMirrorEnabled(p0, z);
    }
}
