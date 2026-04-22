package io.ktor.client.engine;

import io.ktor.client.HttpClient;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.util.CoroutinesUtilsKt;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: HttpClientEngineBase.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpClientEngineBase implements HttpClientEngine {
    private static final /* synthetic */ AtomicIntegerFieldUpdater closed$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClientEngineBase.class, "closed");
    private volatile /* synthetic */ int closed;
    private final Lazy coroutineContext$delegate;
    private final CoroutineDispatcher dispatcher;
    private final String engineName;

    public HttpClientEngineBase(String engineName) {
        Intrinsics.checkNotNullParameter(engineName, "engineName");
        this.engineName = engineName;
        this.closed = 0;
        this.dispatcher = HttpClientEngineBase_jvmKt.ioDispatcher();
        this.coroutineContext$delegate = LazyKt.lazy(new Function0() { // from class: io.ktor.client.engine.HttpClientEngineBase$coroutineContext$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CoroutineContext invoke() {
                return CoroutinesUtilsKt.SilentSupervisor$default(null, 1, null).plus(this.this$0.getDispatcher()).plus(new CoroutineName(this.this$0.engineName + "-context"));
            }
        });
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    public Set getSupportedCapabilities() {
        return HttpClientEngine.DefaultImpls.getSupportedCapabilities(this);
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    public void install(HttpClient httpClient) {
        HttpClientEngine.DefaultImpls.install(this, httpClient);
    }

    public CoroutineDispatcher getDispatcher() {
        return this.dispatcher;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return (CoroutineContext) this.coroutineContext$delegate.getValue();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (closed$FU.compareAndSet(this, 0, 1)) {
            CoroutineContext.Element element = getCoroutineContext().get(Job.Key);
            CompletableJob completableJob = element instanceof CompletableJob ? (CompletableJob) element : null;
            if (completableJob == null) {
                return;
            }
            completableJob.complete();
        }
    }
}
