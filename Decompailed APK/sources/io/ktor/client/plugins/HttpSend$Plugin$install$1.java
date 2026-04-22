package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpSend;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.content.NullBody;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HttpSend.kt */
/* JADX INFO: loaded from: classes.dex */
final class HttpSend$Plugin$install$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ HttpSend $plugin;
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpSend$Plugin$install$1(HttpSend httpSend, HttpClient httpClient, Continuation continuation) {
        super(3, continuation);
        this.$plugin = httpSend;
        this.$scope = httpClient;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(PipelineContext pipelineContext, Object obj, Continuation continuation) {
        HttpSend$Plugin$install$1 httpSend$Plugin$install$1 = new HttpSend$Plugin$install$1(this.$plugin, this.$scope, continuation);
        httpSend$Plugin$install$1.L$0 = pipelineContext;
        httpSend$Plugin$install$1.L$1 = obj;
        return httpSend$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        PipelineContext pipelineContext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            pipelineContext = (PipelineContext) this.L$0;
            Object obj2 = this.L$1;
            if (!(obj2 instanceof OutgoingContent)) {
                throw new IllegalStateException(StringsKt.trimMargin$default("\n|Fail to prepare request body for sending. \n|The body type is: " + Reflection.getOrCreateKotlinClass(obj2.getClass()) + ", with Content-Type: " + HttpMessagePropertiesKt.contentType((HttpMessageBuilder) pipelineContext.getContext()) + ".\n|\n|If you expect serialized body, please check that you have installed the corresponding plugin(like `ContentNegotiation`) and set `Content-Type` header.", null, 1, null).toString());
            }
            HttpRequestBuilder httpRequestBuilder = (HttpRequestBuilder) pipelineContext.getContext();
            if (obj2 == null) {
                httpRequestBuilder.setBody(NullBody.INSTANCE);
                KType kTypeTypeOf = Reflection.typeOf(OutgoingContent.class);
                httpRequestBuilder.setBodyType(TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(kTypeTypeOf), Reflection.getOrCreateKotlinClass(OutgoingContent.class), kTypeTypeOf));
            } else if (obj2 instanceof OutgoingContent) {
                httpRequestBuilder.setBody(obj2);
                httpRequestBuilder.setBodyType(null);
            } else {
                httpRequestBuilder.setBody(obj2);
                KType kTypeTypeOf2 = Reflection.typeOf(OutgoingContent.class);
                httpRequestBuilder.setBodyType(TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(kTypeTypeOf2), Reflection.getOrCreateKotlinClass(OutgoingContent.class), kTypeTypeOf2));
            }
            HttpSend.DefaultSender defaultSender = new HttpSend.DefaultSender(this.$plugin.maxSendCount, this.$scope);
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            ref$ObjectRef.element = defaultSender;
            IntProgression intProgressionDownTo = RangesKt.downTo(CollectionsKt.getLastIndex(this.$plugin.interceptors), 0);
            HttpSend httpSend = this.$plugin;
            Iterator it = intProgressionDownTo.iterator();
            while (it.hasNext()) {
                ref$ObjectRef.element = new HttpSend.InterceptedSender((Function3) httpSend.interceptors.get(((IntIterator) it).nextInt()), (Sender) ref$ObjectRef.element);
            }
            Sender sender = (Sender) ref$ObjectRef.element;
            HttpRequestBuilder httpRequestBuilder2 = (HttpRequestBuilder) pipelineContext.getContext();
            this.L$0 = pipelineContext;
            this.label = 1;
            obj = sender.execute(httpRequestBuilder2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            pipelineContext = (PipelineContext) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        this.L$0 = null;
        this.label = 2;
        if (pipelineContext.proceedWith((HttpClientCall) obj, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
