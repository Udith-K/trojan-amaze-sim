package org.fdroid.fdroid.views.repos;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: RepoDetailsScreen.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class RepoDetailsScreenKt$RepoDetailsScreen$1$2$3$1 extends FunctionReferenceImpl implements Function0 {
    RepoDetailsScreenKt$RepoDetailsScreen$1$2$3$1(Object obj) {
        super(0, obj, RepoDetailsScreenCallbacks.class, "onDeleteClicked", "onDeleteClicked()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m3226invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m3226invoke() {
        ((RepoDetailsScreenCallbacks) this.receiver).onDeleteClicked();
    }
}
