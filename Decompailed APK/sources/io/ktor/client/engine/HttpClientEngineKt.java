package io.ktor.client.engine;

import io.ktor.client.request.HttpRequestData;
import io.ktor.http.HttpHeaders;
import io.ktor.http.UnsafeHeaderException;
import io.ktor.util.AttributeKey;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: compiled from: HttpClientEngine.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpClientEngineKt {
    private static final CoroutineName CALL_COROUTINE = new CoroutineName("call-context");
    private static final AttributeKey CLIENT_CONFIG = new AttributeKey("client-config");

    public static final AttributeKey getCLIENT_CONFIG() {
        return CLIENT_CONFIG;
    }

    public static final Object createCallContext(HttpClientEngine httpClientEngine, Job job, Continuation continuation) {
        final CompletableJob completableJobJob = JobKt.Job(job);
        CoroutineContext coroutineContextPlus = httpClientEngine.getCoroutineContext().plus(completableJobJob).plus(CALL_COROUTINE);
        Job job2 = (Job) continuation.getContext().get(Job.Key);
        if (job2 != null) {
            final DisposableHandle disposableHandleInvokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job2, true, false, new Function1() { // from class: io.ktor.client.engine.UtilsKt$attachToUserJob$cleanupHandler$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Throwable) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th) {
                    if (th == null) {
                        return;
                    }
                    completableJobJob.cancel(new CancellationException(th.getMessage()));
                }
            }, 2, null);
            completableJobJob.invokeOnCompletion(new Function1() { // from class: io.ktor.client.engine.UtilsKt$attachToUserJob$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Throwable) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th) {
                    disposableHandleInvokeOnCompletion$default.dispose();
                }
            });
        }
        return coroutineContextPlus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateHeaders(HttpRequestData httpRequestData) {
        Set setNames = httpRequestData.getHeaders().names();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setNames) {
            if (HttpHeaders.INSTANCE.getUnsafeHeadersList().contains((String) obj)) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            throw new UnsafeHeaderException(arrayList.toString());
        }
    }
}
