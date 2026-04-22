package org.fdroid.download;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: HttpManager.kt */
/* JADX INFO: loaded from: classes2.dex */
final class HttpManager$getBytes$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpManager$getBytes$1(HttpManager httpManager, Continuation continuation) {
        super(continuation);
        this.this$0 = httpManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getBytes$download_release(null, null, this);
    }
}
