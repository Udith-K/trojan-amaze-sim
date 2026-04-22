package io.ktor.client.plugins;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpPlainText.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpPlainText$Plugin$install$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ HttpPlainText $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpPlainText$Plugin$install$1(HttpPlainText httpPlainText, Continuation continuation) {
        super(3, continuation);
        this.$plugin = httpPlainText;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext pipelineContext, Object obj, Continuation continuation) {
        HttpPlainText$Plugin$install$1 httpPlainText$Plugin$install$1 = new HttpPlainText$Plugin$install$1(this.$plugin, continuation);
        httpPlainText$Plugin$install$1.L$0 = pipelineContext;
        httpPlainText$Plugin$install$1.L$1 = obj;
        return httpPlainText$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            Object obj2 = this.L$1;
            this.$plugin.addCharsetHeaders$ktor_client_core((HttpRequestBuilder) pipelineContext.getContext());
            if (!(obj2 instanceof String)) {
                return Unit.INSTANCE;
            }
            ContentType contentType = HttpMessagePropertiesKt.contentType((HttpMessageBuilder) pipelineContext.getContext());
            if (contentType == null || Intrinsics.areEqual(contentType.getContentType(), ContentType.Text.INSTANCE.getPlain().getContentType())) {
                Object objWrapContent = this.$plugin.wrapContent((HttpRequestBuilder) pipelineContext.getContext(), (String) obj2, contentType);
                this.L$0 = null;
                this.label = 1;
                if (pipelineContext.proceedWith(objWrapContent, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                return Unit.INSTANCE;
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
