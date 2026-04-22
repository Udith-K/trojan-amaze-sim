package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.content.ObservableContent;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.statement.HttpReceivePipeline;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.AttributeKey;
import io.ktor.util.pipeline.InvalidPhaseException;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BodyProgress.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BodyProgress {
    public static final Plugin Plugin = new Plugin(null);
    private static final AttributeKey key = new AttributeKey("BodyProgress");

    /* JADX INFO: Access modifiers changed from: private */
    public final void handle(HttpClient httpClient) throws InvalidPhaseException {
        PipelinePhase pipelinePhase = new PipelinePhase("ObservableContent");
        httpClient.getRequestPipeline().insertPhaseAfter(HttpRequestPipeline.Phases.getRender(), pipelinePhase);
        httpClient.getRequestPipeline().intercept(pipelinePhase, new AnonymousClass1(null));
        httpClient.getReceivePipeline().intercept(HttpReceivePipeline.Phases.getAfter(), new AnonymousClass2(null));
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.BodyProgress$handle$1, reason: invalid class name */
    /* JADX INFO: compiled from: BodyProgress.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass1(Continuation continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(PipelineContext pipelineContext, Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.L$0 = pipelineContext;
            anonymousClass1.L$1 = obj;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PipelineContext pipelineContext = (PipelineContext) this.L$0;
                Object obj2 = this.L$1;
                Function3 function3 = (Function3) ((HttpRequestBuilder) pipelineContext.getContext()).getAttributes().getOrNull(BodyProgressKt.UploadProgressListenerAttributeKey);
                if (function3 == null) {
                    return Unit.INSTANCE;
                }
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type io.ktor.http.content.OutgoingContent");
                ObservableContent observableContent = new ObservableContent((OutgoingContent) obj2, ((HttpRequestBuilder) pipelineContext.getContext()).getExecutionContext(), function3);
                this.L$0 = null;
                this.label = 1;
                if (pipelineContext.proceedWith(observableContent, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.BodyProgress$handle$2, reason: invalid class name */
    /* JADX INFO: compiled from: BodyProgress.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass2(Continuation continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(PipelineContext pipelineContext, HttpResponse httpResponse, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = pipelineContext;
            anonymousClass2.L$1 = httpResponse;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PipelineContext pipelineContext = (PipelineContext) this.L$0;
                HttpResponse httpResponse = (HttpResponse) this.L$1;
                Function3 function3 = (Function3) httpResponse.getCall().getRequest().getAttributes().getOrNull(BodyProgressKt.DownloadProgressListenerAttributeKey);
                if (function3 == null) {
                    return Unit.INSTANCE;
                }
                HttpResponse httpResponseWithObservableDownload = BodyProgressKt.withObservableDownload(httpResponse, function3);
                this.L$0 = null;
                this.label = 1;
                if (pipelineContext.proceedWith(httpResponseWithObservableDownload, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: BodyProgress.kt */
    public static final class Plugin implements HttpClientPlugin {
        public /* synthetic */ Plugin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Plugin() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey getKey() {
            return BodyProgress.key;
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public BodyProgress prepare(Function1 block) {
            Intrinsics.checkNotNullParameter(block, "block");
            return new BodyProgress();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(BodyProgress plugin, HttpClient scope) throws InvalidPhaseException {
            Intrinsics.checkNotNullParameter(plugin, "plugin");
            Intrinsics.checkNotNullParameter(scope, "scope");
            plugin.handle(scope);
        }
    }
}
