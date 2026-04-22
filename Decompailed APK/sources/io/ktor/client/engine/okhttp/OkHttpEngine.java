package io.ktor.client.engine.okhttp;

import io.ktor.client.engine.HttpClientEngineBase;
import io.ktor.client.engine.HttpClientEngineCapability;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.plugins.websocket.WebSocketCapability;
import io.ktor.client.request.HttpResponseData;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.CacheKt;
import io.ktor.util.CoroutinesUtilsKt;
import io.ktor.util.date.GMTDate;
import java.net.Proxy;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* JADX INFO: compiled from: OkHttpEngine.kt */
/* JADX INFO: loaded from: classes.dex */
public final class OkHttpEngine extends HttpClientEngineBase {
    private static final Companion Companion = new Companion(null);
    private static final Lazy okHttpClientPrototype$delegate = LazyKt.lazy(new Function0() { // from class: io.ktor.client.engine.okhttp.OkHttpEngine$Companion$okHttpClientPrototype$2
        @Override // kotlin.jvm.functions.Function0
        public final OkHttpClient invoke() {
            return new OkHttpClient.Builder().build();
        }
    });
    private final Map clientCache;
    private final OkHttpConfig config;
    private final CoroutineContext coroutineContext;
    private final CoroutineContext requestsJob;
    private final Set supportedCapabilities;

    /* JADX INFO: renamed from: io.ktor.client.engine.okhttp.OkHttpEngine$execute$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OkHttpEngine.kt */
    static final class C01781 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01781(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OkHttpEngine.this.execute(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OkHttpEngine.kt */
    static final class C01791 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C01791(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OkHttpEngine.this.executeHttpRequest(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.engine.okhttp.OkHttpEngine$executeWebSocketRequest$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OkHttpEngine.kt */
    static final class C01801 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C01801(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OkHttpEngine.this.executeWebSocketRequest(null, null, null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkHttpEngine(OkHttpConfig config) {
        super("ktor-okhttp");
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
        this.supportedCapabilities = SetsKt.setOf((Object[]) new HttpClientEngineCapability[]{HttpTimeout.Plugin, WebSocketCapability.INSTANCE});
        this.clientCache = CacheKt.createLRUCache(new OkHttpEngine$clientCache$1(this), new Function1() { // from class: io.ktor.client.engine.okhttp.OkHttpEngine$clientCache$2
            public final void invoke(OkHttpClient it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((OkHttpClient) obj);
                return Unit.INSTANCE;
            }
        }, getConfig().getClientCacheSize());
        CoroutineContext.Element element = super.getCoroutineContext().get(Job.Key);
        Intrinsics.checkNotNull(element);
        CoroutineContext coroutineContextSilentSupervisor = CoroutinesUtilsKt.SilentSupervisor((Job) element);
        this.requestsJob = coroutineContextSilentSupervisor;
        this.coroutineContext = super.getCoroutineContext().plus(coroutineContextSilentSupervisor);
        BuildersKt.launch(GlobalScope.INSTANCE, super.getCoroutineContext(), CoroutineStart.ATOMIC, new AnonymousClass1(null));
    }

    @Override // io.ktor.client.engine.HttpClientEngine
    public OkHttpConfig getConfig() {
        return this.config;
    }

    @Override // io.ktor.client.engine.HttpClientEngineBase, io.ktor.client.engine.HttpClientEngine
    public Set getSupportedCapabilities() {
        return this.supportedCapabilities;
    }

    @Override // io.ktor.client.engine.HttpClientEngineBase, kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    /* JADX INFO: renamed from: io.ktor.client.engine.okhttp.OkHttpEngine$1, reason: invalid class name */
    /* JADX INFO: compiled from: OkHttpEngine.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return OkHttpEngine.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Iterator it;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineContext.Element element = OkHttpEngine.this.requestsJob.get(Job.Key);
                    Intrinsics.checkNotNull(element);
                    this.label = 1;
                    if (((Job) element).join(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                while (it.hasNext()) {
                    OkHttpClient okHttpClient = (OkHttpClient) ((Map.Entry) it.next()).getValue();
                    okHttpClient.connectionPool().evictAll();
                    okHttpClient.dispatcher().executorService().shutdown();
                }
                return Unit.INSTANCE;
            } finally {
                it = OkHttpEngine.this.clientCache.entrySet().iterator();
                while (it.hasNext()) {
                    OkHttpClient okHttpClient2 = (OkHttpClient) ((Map.Entry) it.next()).getValue();
                    okHttpClient2.connectionPool().evictAll();
                    okHttpClient2.dispatcher().executorService().shutdown();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    @Override // io.ktor.client.engine.HttpClientEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object execute(io.ktor.client.request.HttpRequestData r10, kotlin.coroutines.Continuation r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof io.ktor.client.engine.okhttp.OkHttpEngine.C01781
            if (r0 == 0) goto L14
            r0 = r11
            io.ktor.client.engine.okhttp.OkHttpEngine$execute$1 r0 = (io.ktor.client.engine.okhttp.OkHttpEngine.C01781) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            io.ktor.client.engine.okhttp.OkHttpEngine$execute$1 r0 = new io.ktor.client.engine.okhttp.OkHttpEngine$execute$1
            r0.<init>(r11)
            goto L12
        L1a:
            java.lang.Object r11 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L4a
            if (r1 == r4) goto L3d
            if (r1 == r3) goto L39
            if (r1 != r2) goto L31
            kotlin.ResultKt.throwOnFailure(r11)
            goto L97
        L31:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L39:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L87
        L3d:
            java.lang.Object r10 = r6.L$1
            io.ktor.client.request.HttpRequestData r10 = (io.ktor.client.request.HttpRequestData) r10
            java.lang.Object r1 = r6.L$0
            io.ktor.client.engine.okhttp.OkHttpEngine r1 = (io.ktor.client.engine.okhttp.OkHttpEngine) r1
            kotlin.ResultKt.throwOnFailure(r11)
        L48:
            r5 = r10
            goto L5c
        L4a:
            kotlin.ResultKt.throwOnFailure(r11)
            r6.L$0 = r9
            r6.L$1 = r10
            r6.label = r4
            java.lang.Object r11 = io.ktor.client.engine.UtilsKt.callContext(r6)
            if (r11 != r0) goto L5a
            return r0
        L5a:
            r1 = r9
            goto L48
        L5c:
            r4 = r11
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            okhttp3.Request r10 = io.ktor.client.engine.okhttp.OkHttpEngineKt.access$convertToOkHttpRequest(r5, r4)
            java.util.Map r11 = r1.clientCache
            io.ktor.client.plugins.HttpTimeout$Plugin r7 = io.ktor.client.plugins.HttpTimeout.Plugin
            java.lang.Object r7 = r5.getCapabilityOrNull(r7)
            java.lang.Object r11 = r11.get(r7)
            okhttp3.OkHttpClient r11 = (okhttp3.OkHttpClient) r11
            if (r11 == 0) goto L98
            boolean r7 = io.ktor.client.request.HttpRequestKt.isUpgradeRequest(r5)
            r8 = 0
            if (r7 == 0) goto L88
            r6.L$0 = r8
            r6.L$1 = r8
            r6.label = r3
            java.lang.Object r11 = r1.executeWebSocketRequest(r11, r10, r4, r6)
            if (r11 != r0) goto L87
            return r0
        L87:
            return r11
        L88:
            r6.L$0 = r8
            r6.L$1 = r8
            r6.label = r2
            r2 = r11
            r3 = r10
            java.lang.Object r11 = r1.executeHttpRequest(r2, r3, r4, r5, r6)
            if (r11 != r0) goto L97
            return r0
        L97:
            return r11
        L98:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "OkHttpClient can't be constructed because HttpTimeout plugin is not installed"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.okhttp.OkHttpEngine.execute(io.ktor.client.request.HttpRequestData, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // io.ktor.client.engine.HttpClientEngineBase, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        CoroutineContext.Element element = this.requestsJob.get(Job.Key);
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type kotlinx.coroutines.CompletableJob");
        ((CompletableJob) element).complete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object executeWebSocketRequest(okhttp3.OkHttpClient r6, okhttp3.Request r7, kotlin.coroutines.CoroutineContext r8, kotlin.coroutines.Continuation r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.client.engine.okhttp.OkHttpEngine.C01801
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.client.engine.okhttp.OkHttpEngine$executeWebSocketRequest$1 r0 = (io.ktor.client.engine.okhttp.OkHttpEngine.C01801) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.engine.okhttp.OkHttpEngine$executeWebSocketRequest$1 r0 = new io.ktor.client.engine.okhttp.OkHttpEngine$executeWebSocketRequest$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L41
            if (r2 != r3) goto L39
            java.lang.Object r6 = r0.L$3
            io.ktor.client.engine.okhttp.OkHttpWebsocketSession r6 = (io.ktor.client.engine.okhttp.OkHttpWebsocketSession) r6
            java.lang.Object r7 = r0.L$2
            io.ktor.util.date.GMTDate r7 = (io.ktor.util.date.GMTDate) r7
            java.lang.Object r8 = r0.L$1
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            java.lang.Object r0 = r0.L$0
            io.ktor.client.engine.okhttp.OkHttpEngine r0 = (io.ktor.client.engine.okhttp.OkHttpEngine) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L75
        L39:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L41:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = 0
            io.ktor.util.date.GMTDate r9 = io.ktor.util.date.DateJvmKt.GMTDate$default(r9, r3, r9)
            io.ktor.client.engine.okhttp.OkHttpWebsocketSession r2 = new io.ktor.client.engine.okhttp.OkHttpWebsocketSession
            io.ktor.client.engine.okhttp.OkHttpConfig r4 = r5.getConfig()
            okhttp3.WebSocket$Factory r4 = r4.getWebSocketFactory()
            if (r4 != 0) goto L56
            r4 = r6
        L56:
            r2.<init>(r6, r4, r7, r8)
            r2.start()
            kotlinx.coroutines.CompletableDeferred r6 = r2.getOriginResponse$ktor_client_okhttp()
            r0.L$0 = r5
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r2
            r0.label = r3
            java.lang.Object r6 = r6.await(r0)
            if (r6 != r1) goto L71
            return r1
        L71:
            r0 = r5
            r7 = r9
            r9 = r6
            r6 = r2
        L75:
            okhttp3.Response r9 = (okhttp3.Response) r9
            io.ktor.client.request.HttpResponseData r6 = r0.buildResponseData(r9, r7, r6, r8)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.okhttp.OkHttpEngine.executeWebSocketRequest(okhttp3.OkHttpClient, okhttp3.Request, kotlin.coroutines.CoroutineContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object executeHttpRequest(okhttp3.OkHttpClient r6, okhttp3.Request r7, kotlin.coroutines.CoroutineContext r8, io.ktor.client.request.HttpRequestData r9, kotlin.coroutines.Continuation r10) {
        /*
            r5 = this;
            boolean r0 = r10 instanceof io.ktor.client.engine.okhttp.OkHttpEngine.C01791
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$1 r0 = (io.ktor.client.engine.okhttp.OkHttpEngine.C01791) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$1 r0 = new io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L43
            if (r2 != r3) goto L3b
            java.lang.Object r6 = r0.L$3
            io.ktor.util.date.GMTDate r6 = (io.ktor.util.date.GMTDate) r6
            java.lang.Object r7 = r0.L$2
            r9 = r7
            io.ktor.client.request.HttpRequestData r9 = (io.ktor.client.request.HttpRequestData) r9
            java.lang.Object r7 = r0.L$1
            r8 = r7
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            java.lang.Object r7 = r0.L$0
            io.ktor.client.engine.okhttp.OkHttpEngine r7 = (io.ktor.client.engine.okhttp.OkHttpEngine) r7
            kotlin.ResultKt.throwOnFailure(r10)
            goto L60
        L3b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L43:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = 0
            io.ktor.util.date.GMTDate r10 = io.ktor.util.date.DateJvmKt.GMTDate$default(r10, r3, r10)
            r0.L$0 = r5
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r10
            r0.label = r3
            java.lang.Object r6 = io.ktor.client.engine.okhttp.OkUtilsKt.execute(r6, r7, r9, r0)
            if (r6 != r1) goto L5c
            return r1
        L5c:
            r7 = r5
            r4 = r10
            r10 = r6
            r6 = r4
        L60:
            okhttp3.Response r10 = (okhttp3.Response) r10
            okhttp3.ResponseBody r0 = r10.body()
            kotlinx.coroutines.Job$Key r1 = kotlinx.coroutines.Job.Key
            kotlin.coroutines.CoroutineContext$Element r1 = r8.get(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$2 r2 = new io.ktor.client.engine.okhttp.OkHttpEngine$executeHttpRequest$2
            r2.<init>()
            r1.invokeOnCompletion(r2)
            if (r0 == 0) goto L87
            okio.BufferedSource r0 = r0.source()
            if (r0 == 0) goto L87
            io.ktor.utils.io.ByteReadChannel r9 = io.ktor.client.engine.okhttp.OkHttpEngineKt.access$toChannel(r0, r8, r9)
            if (r9 != 0) goto L8d
        L87:
            io.ktor.utils.io.ByteReadChannel$Companion r9 = io.ktor.utils.io.ByteReadChannel.Companion
            io.ktor.utils.io.ByteReadChannel r9 = r9.getEmpty()
        L8d:
            io.ktor.client.request.HttpResponseData r6 = r7.buildResponseData(r10, r6, r9, r8)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.okhttp.OkHttpEngine.executeHttpRequest(okhttp3.OkHttpClient, okhttp3.Request, kotlin.coroutines.CoroutineContext, io.ktor.client.request.HttpRequestData, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final HttpResponseData buildResponseData(Response response, GMTDate gMTDate, Object obj, CoroutineContext coroutineContext) {
        return new HttpResponseData(new HttpStatusCode(response.code(), response.message()), gMTDate, OkUtilsKt.fromOkHttp(response.headers()), OkUtilsKt.fromOkHttp(response.protocol()), obj, coroutineContext);
    }

    /* JADX INFO: compiled from: OkHttpEngine.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OkHttpClient getOkHttpClientPrototype() {
            return (OkHttpClient) OkHttpEngine.okHttpClientPrototype$delegate.getValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OkHttpClient createOkHttpClient(HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration) {
        OkHttpClient preconfigured = getConfig().getPreconfigured();
        if (preconfigured == null) {
            preconfigured = Companion.getOkHttpClientPrototype();
        }
        OkHttpClient.Builder builderNewBuilder = preconfigured.newBuilder();
        builderNewBuilder.dispatcher(new Dispatcher());
        getConfig().getConfig$ktor_client_okhttp().invoke(builderNewBuilder);
        Proxy proxy = getConfig().getProxy();
        if (proxy != null) {
            builderNewBuilder.proxy(proxy);
        }
        if (httpTimeoutCapabilityConfiguration != null) {
            OkHttpEngineKt.setupTimeoutAttributes(builderNewBuilder, httpTimeoutCapabilityConfiguration);
        }
        return builderNewBuilder.build();
    }
}
