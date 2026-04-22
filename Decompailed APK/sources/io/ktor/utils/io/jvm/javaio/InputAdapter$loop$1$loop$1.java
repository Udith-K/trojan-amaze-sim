package io.ktor.utils.io.jvm.javaio;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: Blocking.kt */
/* JADX INFO: loaded from: classes.dex */
final class InputAdapter$loop$1$loop$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ InputAdapter$loop$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    InputAdapter$loop$1$loop$1(InputAdapter$loop$1 inputAdapter$loop$1, Continuation continuation) {
        super(continuation);
        this.this$0 = inputAdapter$loop$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.loop(this);
    }
}
