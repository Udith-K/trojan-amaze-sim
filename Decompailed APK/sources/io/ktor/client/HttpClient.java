package io.ktor.client;

import ch.qos.logback.core.CoreConstants;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.engine.HttpClientEngineConfig;
import io.ktor.client.plugins.BodyProgress;
import io.ktor.client.plugins.DefaultResponseValidationKt;
import io.ktor.client.plugins.DefaultTransformKt;
import io.ktor.client.plugins.HttpCallValidator;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.client.plugins.HttpPlainText;
import io.ktor.client.plugins.HttpRedirect;
import io.ktor.client.plugins.HttpRequestLifecycle;
import io.ktor.client.plugins.HttpSend;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.request.HttpSendPipeline;
import io.ktor.client.statement.HttpReceivePipeline;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.client.utils.ClientEventsKt;
import io.ktor.client.utils.HttpResponseReceiveFail;
import io.ktor.events.Events;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.pipeline.PipelineContext;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: compiled from: HttpClient.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpClient implements CoroutineScope, Closeable {
    private static final /* synthetic */ AtomicIntegerFieldUpdater closed$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClient.class, "closed");
    private final Attributes attributes;
    private final CompletableJob clientJob;
    private volatile /* synthetic */ int closed;
    private final HttpClientConfig config;
    private final CoroutineContext coroutineContext;
    private final HttpClientEngine engine;
    private final HttpClientEngineConfig engineConfig;
    private boolean manageEngine;
    private final Events monitor;
    private final HttpReceivePipeline receivePipeline;
    private final HttpRequestPipeline requestPipeline;
    private final HttpResponsePipeline responsePipeline;
    private final HttpSendPipeline sendPipeline;
    private final HttpClientConfig userConfig;

    public HttpClient(HttpClientEngine engine, HttpClientConfig userConfig) {
        Intrinsics.checkNotNullParameter(engine, "engine");
        Intrinsics.checkNotNullParameter(userConfig, "userConfig");
        this.engine = engine;
        this.userConfig = userConfig;
        this.closed = 0;
        CompletableJob completableJobJob = JobKt.Job((Job) engine.getCoroutineContext().get(Job.Key));
        this.clientJob = completableJobJob;
        this.coroutineContext = engine.getCoroutineContext().plus(completableJobJob);
        this.requestPipeline = new HttpRequestPipeline(userConfig.getDevelopmentMode());
        HttpResponsePipeline httpResponsePipeline = new HttpResponsePipeline(userConfig.getDevelopmentMode());
        this.responsePipeline = httpResponsePipeline;
        HttpSendPipeline httpSendPipeline = new HttpSendPipeline(userConfig.getDevelopmentMode());
        this.sendPipeline = httpSendPipeline;
        this.receivePipeline = new HttpReceivePipeline(userConfig.getDevelopmentMode());
        this.attributes = AttributesJvmKt.Attributes(true);
        this.engineConfig = engine.getConfig();
        this.monitor = new Events();
        HttpClientConfig httpClientConfig = new HttpClientConfig();
        this.config = httpClientConfig;
        if (this.manageEngine) {
            completableJobJob.invokeOnCompletion(new Function1() { // from class: io.ktor.client.HttpClient.1
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
                        CoroutineScopeKt.cancel$default(HttpClient.this.getEngine(), null, 1, null);
                    }
                }
            });
        }
        engine.install(this);
        httpSendPipeline.intercept(HttpSendPipeline.Phases.getReceive(), new AnonymousClass2(null));
        HttpClientConfig.install$default(httpClientConfig, HttpRequestLifecycle.Plugin, null, 2, null);
        HttpClientConfig.install$default(httpClientConfig, BodyProgress.Plugin, null, 2, null);
        if (userConfig.getUseDefaultTransformers()) {
            httpClientConfig.install("DefaultTransformers", new Function1() { // from class: io.ktor.client.HttpClient$3$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((HttpClient) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(HttpClient install) {
                    Intrinsics.checkNotNullParameter(install, "$this$install");
                    DefaultTransformKt.defaultTransformers(install);
                }
            });
        }
        HttpClientConfig.install$default(httpClientConfig, HttpSend.Plugin, null, 2, null);
        HttpClientConfig.install$default(httpClientConfig, HttpCallValidator.Companion, null, 2, null);
        if (userConfig.getFollowRedirects()) {
            HttpClientConfig.install$default(httpClientConfig, HttpRedirect.Plugin, null, 2, null);
        }
        httpClientConfig.plusAssign(userConfig);
        if (userConfig.getUseDefaultTransformers()) {
            HttpClientConfig.install$default(httpClientConfig, HttpPlainText.Plugin, null, 2, null);
        }
        DefaultResponseValidationKt.addDefaultResponseValidation(httpClientConfig);
        httpClientConfig.install(this);
        httpResponsePipeline.intercept(HttpResponsePipeline.Phases.getReceive(), new AnonymousClass4(null));
    }

    public final HttpClientEngine getEngine() {
        return this.engine;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HttpClient(HttpClientEngine engine, HttpClientConfig userConfig, boolean z) {
        this(engine, userConfig);
        Intrinsics.checkNotNullParameter(engine, "engine");
        Intrinsics.checkNotNullParameter(userConfig, "userConfig");
        this.manageEngine = z;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final HttpRequestPipeline getRequestPipeline() {
        return this.requestPipeline;
    }

    public final HttpResponsePipeline getResponsePipeline() {
        return this.responsePipeline;
    }

    public final HttpSendPipeline getSendPipeline() {
        return this.sendPipeline;
    }

    public final HttpReceivePipeline getReceivePipeline() {
        return this.receivePipeline;
    }

    public final Attributes getAttributes() {
        return this.attributes;
    }

    public final Events getMonitor() {
        return this.monitor;
    }

    public final HttpClientConfig getConfig$ktor_client_core() {
        return this.config;
    }

    /* JADX INFO: renamed from: io.ktor.client.HttpClient$2, reason: invalid class name */
    /* JADX INFO: compiled from: HttpClient.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass2(Continuation continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(PipelineContext pipelineContext, Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = HttpClient.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = pipelineContext;
            anonymousClass2.L$1 = obj;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object obj2;
            PipelineContext pipelineContext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
                obj2 = this.L$1;
                if (!(obj2 instanceof HttpClientCall)) {
                    throw new IllegalStateException(("Error: HttpClientCall expected, but found " + obj2 + CoreConstants.LEFT_PARENTHESIS_CHAR + Reflection.getOrCreateKotlinClass(obj2.getClass()) + ").").toString());
                }
                HttpReceivePipeline receivePipeline = HttpClient.this.getReceivePipeline();
                Unit unit = Unit.INSTANCE;
                HttpResponse response = ((HttpClientCall) obj2).getResponse();
                this.L$0 = pipelineContext2;
                this.L$1 = obj2;
                this.label = 1;
                Object objExecute = receivePipeline.execute(unit, response, this);
                if (objExecute == coroutine_suspended) {
                    return coroutine_suspended;
                }
                pipelineContext = pipelineContext2;
                obj = objExecute;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                obj2 = this.L$1;
                pipelineContext = (PipelineContext) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            ((HttpClientCall) obj2).setResponse$ktor_client_core((HttpResponse) obj);
            this.L$0 = null;
            this.L$1 = null;
            this.label = 2;
            if (pipelineContext.proceedWith(obj2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.HttpClient$4, reason: invalid class name */
    /* JADX INFO: compiled from: HttpClient.kt */
    static final class AnonymousClass4 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(PipelineContext pipelineContext, HttpResponseContainer httpResponseContainer, Continuation continuation) {
            AnonymousClass4 anonymousClass4 = HttpClient.this.new AnonymousClass4(continuation);
            anonymousClass4.L$0 = pipelineContext;
            return anonymousClass4.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            PipelineContext pipelineContext;
            Throwable th;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                pipelineContext = (PipelineContext) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    HttpClient.this.getMonitor().raise(ClientEventsKt.getHttpResponseReceiveFailed(), new HttpResponseReceiveFail(((HttpClientCall) pipelineContext.getContext()).getResponse(), th));
                    throw th;
                }
            }
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
            try {
                this.L$0 = pipelineContext2;
                this.label = 1;
                if (pipelineContext2.proceed(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            } catch (Throwable th3) {
                pipelineContext = pipelineContext2;
                th = th3;
                HttpClient.this.getMonitor().raise(ClientEventsKt.getHttpResponseReceiveFailed(), new HttpResponseReceiveFail(((HttpClientCall) pipelineContext.getContext()).getResponse(), th));
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object execute$ktor_client_core(io.ktor.client.request.HttpRequestBuilder r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.client.HttpClient$execute$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.HttpClient$execute$1 r0 = (io.ktor.client.HttpClient$execute$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.HttpClient$execute$1 r0 = new io.ktor.client.HttpClient$execute$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4c
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            io.ktor.events.Events r6 = r4.monitor
            io.ktor.events.EventDefinition r2 = io.ktor.client.utils.ClientEventsKt.getHttpRequestCreated()
            r6.raise(r2, r5)
            io.ktor.client.request.HttpRequestPipeline r6 = r4.requestPipeline
            java.lang.Object r2 = r5.getBody()
            r0.label = r3
            java.lang.Object r6 = r6.execute(r5, r2, r0)
            if (r6 != r1) goto L4c
            return r1
        L4c:
            java.lang.String r5 = "null cannot be cast to non-null type io.ktor.client.call.HttpClientCall"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r5)
            io.ktor.client.call.HttpClientCall r6 = (io.ktor.client.call.HttpClientCall) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.HttpClient.execute$ktor_client_core(io.ktor.client.request.HttpRequestBuilder, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (closed$FU.compareAndSet(this, 0, 1)) {
            Attributes attributes = (Attributes) this.attributes.get(HttpClientPluginKt.getPLUGIN_INSTALLED_LIST());
            for (AttributeKey attributeKey : attributes.getAllKeys()) {
                Intrinsics.checkNotNull(attributeKey, "null cannot be cast to non-null type io.ktor.util.AttributeKey<kotlin.Any>");
                Object obj = attributes.get(attributeKey);
                if (obj instanceof Closeable) {
                    ((Closeable) obj).close();
                }
            }
            this.clientJob.complete();
            if (this.manageEngine) {
                this.engine.close();
            }
        }
    }

    public String toString() {
        return "HttpClient[" + this.engine + ']';
    }
}
