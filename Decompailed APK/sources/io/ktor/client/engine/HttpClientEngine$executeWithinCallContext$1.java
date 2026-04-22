package io.ktor.client.engine;

import io.ktor.client.engine.HttpClientEngine;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: HttpClientEngine.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpClientEngine$executeWithinCallContext$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    HttpClientEngine$executeWithinCallContext$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return HttpClientEngine.DefaultImpls.executeWithinCallContext(null, null, this);
    }
}
