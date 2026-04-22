package io.ktor.client.plugins;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.ByteReadPacket;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: HttpPlainText.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpPlainText$Plugin$install$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ HttpPlainText $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpPlainText$Plugin$install$2(HttpPlainText httpPlainText, Continuation continuation) {
        super(3, continuation);
        this.$plugin = httpPlainText;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext pipelineContext, HttpResponseContainer httpResponseContainer, Continuation continuation) {
        HttpPlainText$Plugin$install$2 httpPlainText$Plugin$install$2 = new HttpPlainText$Plugin$install$2(this.$plugin, continuation);
        httpPlainText$Plugin$install$2.L$0 = pipelineContext;
        httpPlainText$Plugin$install$2.L$1 = httpResponseContainer;
        return httpPlainText$Plugin$install$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        PipelineContext pipelineContext;
        TypeInfo typeInfo;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
            HttpResponseContainer httpResponseContainer = (HttpResponseContainer) this.L$1;
            TypeInfo typeInfoComponent1 = httpResponseContainer.component1();
            Object objComponent2 = httpResponseContainer.component2();
            if (!Intrinsics.areEqual(typeInfoComponent1.getType(), Reflection.getOrCreateKotlinClass(String.class)) || !(objComponent2 instanceof ByteReadChannel)) {
                return Unit.INSTANCE;
            }
            this.L$0 = pipelineContext2;
            this.L$1 = typeInfoComponent1;
            this.label = 1;
            Object remaining$default = ByteReadChannel.DefaultImpls.readRemaining$default((ByteReadChannel) objComponent2, 0L, this, 1, null);
            if (remaining$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            pipelineContext = pipelineContext2;
            obj = remaining$default;
            typeInfo = typeInfoComponent1;
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            typeInfo = (TypeInfo) this.L$1;
            pipelineContext = (PipelineContext) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        HttpResponseContainer httpResponseContainer2 = new HttpResponseContainer(typeInfo, this.$plugin.read$ktor_client_core((HttpClientCall) pipelineContext.getContext(), (ByteReadPacket) obj));
        this.L$0 = null;
        this.L$1 = null;
        this.label = 2;
        if (pipelineContext.proceedWith(httpResponseContainer2, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
