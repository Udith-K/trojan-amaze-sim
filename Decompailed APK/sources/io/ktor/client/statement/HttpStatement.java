package io.ktor.client.statement;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.SavedCallKt;
import io.ktor.client.engine.HttpClientEngineCapabilityKt;
import io.ktor.client.plugins.HttpClientPlugin;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.client.request.HttpRequestBuilder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpStatement.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpStatement {
    private final HttpRequestBuilder builder;
    private final HttpClient client;

    /* JADX INFO: renamed from: io.ktor.client.statement.HttpStatement$cleanup$1, reason: invalid class name */
    /* JADX INFO: compiled from: HttpStatement.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpStatement.this.cleanup(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.statement.HttpStatement$execute$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HttpStatement.kt */
    static final class C01831 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01831(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpStatement.this.execute(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.statement.HttpStatement$executeUnsafe$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HttpStatement.kt */
    static final class C01841 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C01841(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpStatement.this.executeUnsafe(this);
        }
    }

    public HttpStatement(HttpRequestBuilder builder, HttpClient client) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(client, "client");
        this.builder = builder;
        this.client = client;
        checkCapabilities();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(6:54|(1:(1:(1:(1:(2:14|15)(3:16|17|51))(3:21|22|59))(5:24|55|25|42|(1:44)(2:45|46)))(2:29|30))(3:32|33|(1:35)(1:36))|37|57|38|(1:40)(3:41|42|(0)(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0098, code lost:
    
        r10 = th;
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0095 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object execute(kotlin.jvm.functions.Function2 r10, kotlin.coroutines.Continuation r11) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r11 instanceof io.ktor.client.statement.HttpStatement.C01831
            if (r0 == 0) goto L13
            r0 = r11
            io.ktor.client.statement.HttpStatement$execute$1 r0 = (io.ktor.client.statement.HttpStatement.C01831) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.statement.HttpStatement$execute$1 r0 = new io.ktor.client.statement.HttpStatement$execute$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L66
            if (r2 == r7) goto L5a
            if (r2 == r6) goto L49
            if (r2 == r5) goto L43
            if (r2 == r4) goto L37
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L37:
            java.lang.Object r10 = r0.L$0
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.util.concurrent.CancellationException -> L40
            goto La6
        L40:
            r10 = move-exception
            goto La7
        L43:
            java.lang.Object r10 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.util.concurrent.CancellationException -> L40
            goto L97
        L49:
            java.lang.Object r10 = r0.L$1
            io.ktor.client.statement.HttpResponse r10 = (io.ktor.client.statement.HttpResponse) r10
            java.lang.Object r2 = r0.L$0
            io.ktor.client.statement.HttpStatement r2 = (io.ktor.client.statement.HttpStatement) r2
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L55
            goto L89
        L55:
            r11 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto L99
        L5a:
            java.lang.Object r10 = r0.L$1
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            java.lang.Object r2 = r0.L$0
            io.ktor.client.statement.HttpStatement r2 = (io.ktor.client.statement.HttpStatement) r2
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.util.concurrent.CancellationException -> L40
            goto L77
        L66:
            kotlin.ResultKt.throwOnFailure(r11)
            r0.L$0 = r9     // Catch: java.util.concurrent.CancellationException -> L40
            r0.L$1 = r10     // Catch: java.util.concurrent.CancellationException -> L40
            r0.label = r7     // Catch: java.util.concurrent.CancellationException -> L40
            java.lang.Object r11 = r9.executeUnsafe(r0)     // Catch: java.util.concurrent.CancellationException -> L40
            if (r11 != r1) goto L76
            return r1
        L76:
            r2 = r9
        L77:
            io.ktor.client.statement.HttpResponse r11 = (io.ktor.client.statement.HttpResponse) r11     // Catch: java.util.concurrent.CancellationException -> L40
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L98
            r0.L$1 = r11     // Catch: java.lang.Throwable -> L98
            r0.label = r6     // Catch: java.lang.Throwable -> L98
            java.lang.Object r10 = r10.invoke(r11, r0)     // Catch: java.lang.Throwable -> L98
            if (r10 != r1) goto L86
            return r1
        L86:
            r8 = r11
            r11 = r10
            r10 = r8
        L89:
            r0.L$0 = r11     // Catch: java.util.concurrent.CancellationException -> L40
            r0.L$1 = r3     // Catch: java.util.concurrent.CancellationException -> L40
            r0.label = r5     // Catch: java.util.concurrent.CancellationException -> L40
            java.lang.Object r10 = r2.cleanup(r10, r0)     // Catch: java.util.concurrent.CancellationException -> L40
            if (r10 != r1) goto L96
            return r1
        L96:
            r10 = r11
        L97:
            return r10
        L98:
            r10 = move-exception
        L99:
            r0.L$0 = r10     // Catch: java.util.concurrent.CancellationException -> L40
            r0.L$1 = r3     // Catch: java.util.concurrent.CancellationException -> L40
            r0.label = r4     // Catch: java.util.concurrent.CancellationException -> L40
            java.lang.Object r11 = r2.cleanup(r11, r0)     // Catch: java.util.concurrent.CancellationException -> L40
            if (r11 != r1) goto La6
            return r1
        La6:
            throw r10     // Catch: java.util.concurrent.CancellationException -> L40
        La7:
            java.lang.Throwable r10 = io.ktor.client.utils.ExceptionUtilsJvmKt.unwrapCancellationException(r10)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpStatement.execute(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: io.ktor.client.statement.HttpStatement$execute$4, reason: invalid class name */
    /* JADX INFO: compiled from: HttpStatement.kt */
    static final class AnonymousClass4 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(HttpResponse httpResponse, Continuation continuation) {
            return ((AnonymousClass4) create(httpResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                HttpClientCall call = ((HttpResponse) this.L$0).getCall();
                this.label = 1;
                obj = SavedCallKt.save(call, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return ((HttpClientCall) obj).getResponse();
        }
    }

    public final Object execute(Continuation continuation) {
        return execute(new AnonymousClass4(null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object executeUnsafe(kotlin.coroutines.Continuation r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.client.statement.HttpStatement.C01841
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.client.statement.HttpStatement$executeUnsafe$1 r0 = (io.ktor.client.statement.HttpStatement.C01841) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.statement.HttpStatement$executeUnsafe$1 r0 = new io.ktor.client.statement.HttpStatement$executeUnsafe$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.util.concurrent.CancellationException -> L29
            goto L4c
        L29:
            r5 = move-exception
            goto L53
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L33:
            kotlin.ResultKt.throwOnFailure(r5)
            io.ktor.client.request.HttpRequestBuilder r5 = new io.ktor.client.request.HttpRequestBuilder     // Catch: java.util.concurrent.CancellationException -> L29
            r5.<init>()     // Catch: java.util.concurrent.CancellationException -> L29
            io.ktor.client.request.HttpRequestBuilder r2 = r4.builder     // Catch: java.util.concurrent.CancellationException -> L29
            io.ktor.client.request.HttpRequestBuilder r5 = r5.takeFromWithExecutionContext(r2)     // Catch: java.util.concurrent.CancellationException -> L29
            io.ktor.client.HttpClient r2 = r4.client     // Catch: java.util.concurrent.CancellationException -> L29
            r0.label = r3     // Catch: java.util.concurrent.CancellationException -> L29
            java.lang.Object r5 = r2.execute$ktor_client_core(r5, r0)     // Catch: java.util.concurrent.CancellationException -> L29
            if (r5 != r1) goto L4c
            return r1
        L4c:
            io.ktor.client.call.HttpClientCall r5 = (io.ktor.client.call.HttpClientCall) r5     // Catch: java.util.concurrent.CancellationException -> L29
            io.ktor.client.statement.HttpResponse r5 = r5.getResponse()     // Catch: java.util.concurrent.CancellationException -> L29
            return r5
        L53:
            java.lang.Throwable r5 = io.ktor.client.utils.ExceptionUtilsJvmKt.unwrapCancellationException(r5)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpStatement.executeUnsafe(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object cleanup(io.ktor.client.statement.HttpResponse r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.client.statement.HttpStatement.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.statement.HttpStatement$cleanup$1 r0 = (io.ktor.client.statement.HttpStatement.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.statement.HttpStatement$cleanup$1 r0 = new io.ktor.client.statement.HttpStatement$cleanup$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.CompletableJob r5 = (kotlinx.coroutines.CompletableJob) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5c
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.coroutines.CoroutineContext r6 = r5.getCoroutineContext()
            kotlinx.coroutines.Job$Key r2 = kotlinx.coroutines.Job.Key
            kotlin.coroutines.CoroutineContext$Element r6 = r6.get(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            kotlinx.coroutines.CompletableJob r6 = (kotlinx.coroutines.CompletableJob) r6
            r6.complete()
            io.ktor.utils.io.ByteReadChannel r5 = r5.getContent()     // Catch: java.lang.Throwable -> L51
            io.ktor.utils.io.ByteReadChannelKt.cancel(r5)     // Catch: java.lang.Throwable -> L51
        L51:
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r5 = r6.join(r0)
            if (r5 != r1) goto L5c
            return r1
        L5c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpStatement.cleanup(io.ktor.client.statement.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void checkCapabilities() {
        Set setKeySet;
        Map map = (Map) this.builder.getAttributes().getOrNull(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY());
        if (map == null || (setKeySet = map.keySet()) == null) {
            return;
        }
        ArrayList<HttpClientPlugin> arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            if (obj instanceof HttpClientPlugin) {
                arrayList.add(obj);
            }
        }
        for (HttpClientPlugin httpClientPlugin : arrayList) {
            if (HttpClientPluginKt.pluginOrNull(this.client, httpClientPlugin) == null) {
                throw new IllegalArgumentException(("Consider installing " + httpClientPlugin + " plugin because the request requires it to be installed").toString());
            }
        }
    }

    public String toString() {
        return "HttpStatement[" + this.builder.getUrl() + ']';
    }
}
