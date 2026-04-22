package io.ktor.client.engine;

import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: HttpClientEngineBase.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpClientEngineBase_jvmKt {
    public static final CoroutineDispatcher ioDispatcher() {
        return Dispatchers.getIO();
    }
}
