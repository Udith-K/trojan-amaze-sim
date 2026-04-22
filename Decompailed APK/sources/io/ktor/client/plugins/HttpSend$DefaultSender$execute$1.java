package io.ktor.client.plugins;

import io.ktor.client.plugins.HttpSend;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: HttpSend.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpSend$DefaultSender$execute$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpSend.DefaultSender this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpSend$DefaultSender$execute$1(HttpSend.DefaultSender defaultSender, Continuation continuation) {
        super(continuation);
        this.this$0 = defaultSender;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.execute(null, this);
    }
}
