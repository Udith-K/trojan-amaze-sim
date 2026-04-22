package io.ktor.util;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;

/* JADX INFO: compiled from: CoroutineExceptionHandler.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CoroutinesUtilsKt$SilentSupervisor$$inlined$CoroutineExceptionHandler$1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void handleException(CoroutineContext coroutineContext, Throwable th) {
    }

    public CoroutinesUtilsKt$SilentSupervisor$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key key) {
        super(key);
    }
}
