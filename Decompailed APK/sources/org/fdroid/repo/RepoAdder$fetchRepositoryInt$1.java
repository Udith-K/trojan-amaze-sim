package org.fdroid.repo;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: RepoAdder.kt */
/* JADX INFO: loaded from: classes2.dex */
final class RepoAdder$fetchRepositoryInt$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RepoAdder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RepoAdder$fetchRepositoryInt$1(RepoAdder repoAdder, Continuation continuation) {
        super(continuation);
        this.this$0 = repoAdder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.fetchRepositoryInt$database_release(null, null, this);
    }
}
