package org.fdroid.repo;

import java.net.Proxy;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: RepoAdder.kt */
/* JADX INFO: loaded from: classes2.dex */
final class RepoAdder$fetchRepository$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Proxy $proxy;
    final /* synthetic */ String $url;
    int label;
    final /* synthetic */ RepoAdder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RepoAdder$fetchRepository$1(RepoAdder repoAdder, String str, Proxy proxy, Continuation continuation) {
        super(2, continuation);
        this.this$0 = repoAdder;
        this.$url = str;
        this.$proxy = proxy;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new RepoAdder$fetchRepository$1(this.this$0, this.$url, this.$proxy, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((RepoAdder$fetchRepository$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            RepoAdder repoAdder = this.this$0;
            String str = this.$url;
            Proxy proxy = this.$proxy;
            this.label = 1;
            if (repoAdder.fetchRepositoryInt$database_release(str, proxy, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
