package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.util.AttributeKey;
import io.ktor.util.pipeline.InvalidPhaseException;
import io.ktor.util.pipeline.PipelinePhase;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpCallValidator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpCallValidator {
    public static final Companion Companion = new Companion(null);
    private static final AttributeKey key = new AttributeKey("HttpResponseValidator");
    private final List callExceptionHandlers;
    private final boolean expectSuccess;
    private final List responseValidators;

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpCallValidator$processException$1, reason: invalid class name */
    /* JADX INFO: compiled from: HttpCallValidator.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpCallValidator.this.processException(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpCallValidator$validateResponse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HttpCallValidator.kt */
    static final class C01821 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01821(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpCallValidator.this.validateResponse(null, this);
        }
    }

    public HttpCallValidator(List responseValidators, List callExceptionHandlers, boolean z) {
        Intrinsics.checkNotNullParameter(responseValidators, "responseValidators");
        Intrinsics.checkNotNullParameter(callExceptionHandlers, "callExceptionHandlers");
        this.responseValidators = responseValidators;
        this.callExceptionHandlers = callExceptionHandlers;
        this.expectSuccess = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object validateResponse(io.ktor.client.statement.HttpResponse r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.client.plugins.HttpCallValidator.C01821
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.client.plugins.HttpCallValidator$validateResponse$1 r0 = (io.ktor.client.plugins.HttpCallValidator.C01821) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.HttpCallValidator$validateResponse$1 r0 = new io.ktor.client.plugins.HttpCallValidator$validateResponse$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r7 = r0.L$1
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.client.statement.HttpResponse r2 = (io.ktor.client.statement.HttpResponse) r2
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r2
            goto L6a
        L32:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3a:
            kotlin.ResultKt.throwOnFailure(r8)
            org.slf4j.Logger r8 = io.ktor.client.plugins.HttpCallValidatorKt.access$getLOGGER$p()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Validating response for request "
            r2.append(r4)
            io.ktor.client.call.HttpClientCall r4 = r7.getCall()
            io.ktor.client.request.HttpRequest r4 = r4.getRequest()
            io.ktor.http.Url r4 = r4.getUrl()
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r8.trace(r2)
            java.util.List r8 = r6.responseValidators
            java.util.Iterator r8 = r8.iterator()
            r5 = r8
            r8 = r7
            r7 = r5
        L6a:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L83
            java.lang.Object r2 = r7.next()
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r8
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r2 = r2.invoke(r8, r0)
            if (r2 != r1) goto L6a
            return r1
        L83:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpCallValidator.validateResponse(io.ktor.client.statement.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processException(java.lang.Throwable r5, io.ktor.client.request.HttpRequest r6, kotlin.coroutines.Continuation r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof io.ktor.client.plugins.HttpCallValidator.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.client.plugins.HttpCallValidator$processException$1 r0 = (io.ktor.client.plugins.HttpCallValidator.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.HttpCallValidator$processException$1 r0 = new io.ktor.client.plugins.HttpCallValidator$processException$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            if (r1 == 0) goto L3f
            r5 = 1
            if (r1 == r5) goto L27
            r5 = 2
            if (r1 != r5) goto L37
        L27:
            java.lang.Object r5 = r0.L$2
            java.util.Iterator r5 = (java.util.Iterator) r5
            java.lang.Object r6 = r0.L$1
            io.ktor.client.request.HttpRequest r6 = (io.ktor.client.request.HttpRequest) r6
            java.lang.Object r6 = r0.L$0
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L6c
        L37:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3f:
            kotlin.ResultKt.throwOnFailure(r7)
            org.slf4j.Logger r7 = io.ktor.client.plugins.HttpCallValidatorKt.access$getLOGGER$p()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Processing exception "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r5 = " for request "
            r0.append(r5)
            io.ktor.http.Url r5 = r6.getUrl()
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r7.trace(r5)
            java.util.List r5 = r4.callExceptionHandlers
            java.util.Iterator r5 = r5.iterator()
        L6c:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L7a
            java.lang.Object r6 = r5.next()
            androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(r6)
            goto L6c
        L7a:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpCallValidator.processException(java.lang.Throwable, io.ktor.client.request.HttpRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: compiled from: HttpCallValidator.kt */
    public static final class Config {
        private final List responseValidators = new ArrayList();
        private final List responseExceptionHandlers = new ArrayList();
        private boolean expectSuccess = true;

        public final List getResponseValidators$ktor_client_core() {
            return this.responseValidators;
        }

        public final List getResponseExceptionHandlers$ktor_client_core() {
            return this.responseExceptionHandlers;
        }

        public final boolean getExpectSuccess() {
            return this.expectSuccess;
        }

        public final void setExpectSuccess(boolean z) {
            this.expectSuccess = z;
        }

        public final void validateResponse(Function2 block) {
            Intrinsics.checkNotNullParameter(block, "block");
            this.responseValidators.add(block);
        }
    }

    /* JADX INFO: compiled from: HttpCallValidator.kt */
    public static final class Companion implements HttpClientPlugin {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey getKey() {
            return HttpCallValidator.key;
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpCallValidator prepare(Function1 block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Config config = new Config();
            block.invoke(config);
            return new HttpCallValidator(CollectionsKt.reversed(config.getResponseValidators$ktor_client_core()), CollectionsKt.reversed(config.getResponseExceptionHandlers$ktor_client_core()), config.getExpectSuccess());
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpCallValidator plugin, HttpClient scope) throws InvalidPhaseException {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            scope.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getBefore(), new HttpCallValidator$Companion$install$1(plugin, null));
            PipelinePhase pipelinePhase = new PipelinePhase("BeforeReceive");
            scope.getResponsePipeline().insertPhaseBefore(HttpResponsePipeline.Phases.getReceive(), pipelinePhase);
            scope.getResponsePipeline().intercept(pipelinePhase, new HttpCallValidator$Companion$install$2(plugin, null));
            ((HttpSend) HttpClientPluginKt.plugin(scope, HttpSend.Plugin)).intercept(new HttpCallValidator$Companion$install$3(plugin, null));
        }
    }
}
