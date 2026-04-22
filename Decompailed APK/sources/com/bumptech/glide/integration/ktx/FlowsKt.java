package com.bumptech.glide.integration.ktx;

import com.bumptech.glide.GlideIntegrationKt;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.Util;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: Flows.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FlowsKt {
    public static final Flow flowResolvable(RequestBuilder requestBuilder, ResolvableGlideSize size) {
        Intrinsics.checkNotNullParameter(requestBuilder, "<this>");
        Intrinsics.checkNotNullParameter(size, "size");
        return flow(requestBuilder, size);
    }

    /* JADX INFO: renamed from: com.bumptech.glide.integration.ktx.FlowsKt$flow$2, reason: invalid class name */
    /* JADX INFO: compiled from: Flows.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ RequestBuilder $requestBuilder;
        final /* synthetic */ RequestManager $requestManager;
        final /* synthetic */ ResolvableGlideSize $size;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ResolvableGlideSize resolvableGlideSize, RequestBuilder requestBuilder, RequestManager requestManager, Continuation continuation) {
            super(2, continuation);
            this.$size = resolvableGlideSize;
            this.$requestBuilder = requestBuilder;
            this.$requestManager = requestManager;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$size, this.$requestBuilder, this.$requestManager, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope producerScope, Continuation continuation) {
            return ((AnonymousClass2) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope = (ProducerScope) this.L$0;
                final FlowTarget flowTarget = new FlowTarget(producerScope, this.$size);
                GlideIntegrationKt.intoDirect(this.$requestBuilder, flowTarget);
                final RequestManager requestManager = this.$requestManager;
                Function0 function0 = new Function0() { // from class: com.bumptech.glide.integration.ktx.FlowsKt.flow.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m2591invoke();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m2591invoke() {
                        requestManager.clear(flowTarget);
                    }
                };
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, function0, this) == coroutine_suspended) {
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

    private static final Flow flow(RequestBuilder requestBuilder, ResolvableGlideSize resolvableGlideSize) {
        return FlowKt.callbackFlow(new AnonymousClass2(resolvableGlideSize, requestBuilder, GlideIntegrationKt.requestManager(requestBuilder), null));
    }

    public static final boolean isValidGlideDimension(int i) {
        return Util.isValidDimension(i);
    }
}
