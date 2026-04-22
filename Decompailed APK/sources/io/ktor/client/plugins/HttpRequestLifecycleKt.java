package io.ktor.client.plugins;

import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import org.slf4j.Logger;

/* JADX INFO: compiled from: HttpRequestLifecycle.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpRequestLifecycleKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpRequestLifecycle");

    /* JADX INFO: Access modifiers changed from: private */
    public static final void attachToClientEngineJob(final CompletableJob completableJob, Job job) {
        final DisposableHandle disposableHandleInvokeOnCompletion = job.invokeOnCompletion(new Function1() { // from class: io.ktor.client.plugins.HttpRequestLifecycleKt$attachToClientEngineJob$handler$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                if (th != null) {
                    HttpRequestLifecycleKt.LOGGER.trace("Cancelling request because engine Job failed with error: " + th);
                    JobKt.cancel(completableJob, "Engine failed", th);
                    return;
                }
                HttpRequestLifecycleKt.LOGGER.trace("Cancelling request because engine Job completed");
                completableJob.complete();
            }
        });
        completableJob.invokeOnCompletion(new Function1() { // from class: io.ktor.client.plugins.HttpRequestLifecycleKt.attachToClientEngineJob.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                disposableHandleInvokeOnCompletion.dispose();
            }
        });
    }
}
