package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.util.AttributeKey;
import java.util.ArrayList;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpSend.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpSend {
    public static final Plugin Plugin = new Plugin(null);
    private static final AttributeKey key = new AttributeKey("HttpSend");
    private final List interceptors;
    private final int maxSendCount;

    public /* synthetic */ HttpSend(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    private HttpSend(int i) {
        this.maxSendCount = i;
        this.interceptors = new ArrayList();
    }

    /* JADX INFO: compiled from: HttpSend.kt */
    public static final class Config {
        private int maxSendCount = 20;

        public final int getMaxSendCount() {
            return this.maxSendCount;
        }
    }

    public final void intercept(Function3 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.interceptors.add(block);
    }

    /* JADX INFO: compiled from: HttpSend.kt */
    public static final class Plugin implements HttpClientPlugin {
        public /* synthetic */ Plugin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Plugin() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey getKey() {
            return HttpSend.key;
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpSend prepare(Function1 block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Config config = new Config();
            block.invoke(config);
            return new HttpSend(config.getMaxSendCount(), null);
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpSend plugin, HttpClient scope) {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            scope.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getSend(), new HttpSend$Plugin$install$1(plugin, scope, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: HttpSend.kt */
    static final class InterceptedSender implements Sender {
        private final Function3 interceptor;
        private final Sender nextSender;

        public InterceptedSender(Function3 interceptor, Sender nextSender) {
            Intrinsics.checkNotNullParameter(interceptor, "interceptor");
            Intrinsics.checkNotNullParameter(nextSender, "nextSender");
            this.interceptor = interceptor;
            this.nextSender = nextSender;
        }

        @Override // io.ktor.client.plugins.Sender
        public Object execute(HttpRequestBuilder httpRequestBuilder, Continuation continuation) {
            return this.interceptor.invoke(this.nextSender, httpRequestBuilder, continuation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: HttpSend.kt */
    static final class DefaultSender implements Sender {
        private final HttpClient client;
        private HttpClientCall currentCall;
        private final int maxSendCount;
        private int sentCount;

        public DefaultSender(int i, HttpClient client) {
            Intrinsics.checkNotNullParameter(client, "client");
            this.maxSendCount = i;
            this.client = client;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // io.ktor.client.plugins.Sender
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object execute(io.ktor.client.request.HttpRequestBuilder r6, kotlin.coroutines.Continuation r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof io.ktor.client.plugins.HttpSend$DefaultSender$execute$1
                if (r0 == 0) goto L13
                r0 = r7
                io.ktor.client.plugins.HttpSend$DefaultSender$execute$1 r0 = (io.ktor.client.plugins.HttpSend$DefaultSender$execute$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                io.ktor.client.plugins.HttpSend$DefaultSender$execute$1 r0 = new io.ktor.client.plugins.HttpSend$DefaultSender$execute$1
                r0.<init>(r5, r7)
            L18:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 0
                r4 = 1
                if (r2 == 0) goto L36
                if (r2 != r4) goto L2e
                java.lang.Object r6 = r0.L$0
                io.ktor.client.plugins.HttpSend$DefaultSender r6 = (io.ktor.client.plugins.HttpSend.DefaultSender) r6
                kotlin.ResultKt.throwOnFailure(r7)
                goto L5f
            L2e:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L36:
                kotlin.ResultKt.throwOnFailure(r7)
                io.ktor.client.call.HttpClientCall r7 = r5.currentCall
                if (r7 == 0) goto L40
                kotlinx.coroutines.CoroutineScopeKt.cancel$default(r7, r3, r4, r3)
            L40:
                int r7 = r5.sentCount
                int r2 = r5.maxSendCount
                if (r7 >= r2) goto L86
                int r7 = r7 + r4
                r5.sentCount = r7
                io.ktor.client.HttpClient r7 = r5.client
                io.ktor.client.request.HttpSendPipeline r7 = r7.getSendPipeline()
                java.lang.Object r2 = r6.getBody()
                r0.L$0 = r5
                r0.label = r4
                java.lang.Object r7 = r7.execute(r6, r2, r0)
                if (r7 != r1) goto L5e
                return r1
            L5e:
                r6 = r5
            L5f:
                boolean r0 = r7 instanceof io.ktor.client.call.HttpClientCall
                if (r0 == 0) goto L66
                r3 = r7
                io.ktor.client.call.HttpClientCall r3 = (io.ktor.client.call.HttpClientCall) r3
            L66:
                if (r3 == 0) goto L6b
                r6.currentCall = r3
                return r3
            L6b:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Failed to execute send pipeline. Expected [HttpClientCall], but received "
                r0.append(r1)
                r0.append(r7)
                java.lang.String r7 = r0.toString()
                java.lang.String r7 = r7.toString()
                r6.<init>(r7)
                throw r6
            L86:
                io.ktor.client.plugins.SendCountExceedException r6 = new io.ktor.client.plugins.SendCountExceedException
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r0 = "Max send count "
                r7.append(r0)
                int r0 = r5.maxSendCount
                r7.append(r0)
                java.lang.String r0 = " exceeded. Consider increasing the property maxSendCount if more is required."
                r7.append(r0)
                java.lang.String r7 = r7.toString()
                r6.<init>(r7)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpSend.DefaultSender.execute(io.ktor.client.request.HttpRequestBuilder, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
