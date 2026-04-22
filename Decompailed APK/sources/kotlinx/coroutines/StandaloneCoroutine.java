package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;

/* JADX INFO: compiled from: Builders.common.kt */
/* JADX INFO: loaded from: classes2.dex */
class StandaloneCoroutine extends AbstractCoroutine {
    public StandaloneCoroutine(CoroutineContext coroutineContext, boolean z) {
        super(coroutineContext, true, z);
    }

    @Override // kotlinx.coroutines.JobSupport
    protected boolean handleJobException(Throwable th) {
        CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), th);
        return true;
    }
}
