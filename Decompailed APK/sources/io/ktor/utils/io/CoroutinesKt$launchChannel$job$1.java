package io.ktor.utils.io;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes.dex */
final class CoroutinesKt$launchChannel$job$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $attachJob;
    final /* synthetic */ Function2 $block;
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ CoroutineDispatcher $dispatcher;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoroutinesKt$launchChannel$job$1(boolean z, ByteChannel byteChannel, Function2 function2, CoroutineDispatcher coroutineDispatcher, Continuation continuation) {
        super(2, continuation);
        this.$attachJob = z;
        this.$channel = byteChannel;
        this.$block = function2;
        this.$dispatcher = coroutineDispatcher;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CoroutinesKt$launchChannel$job$1 coroutinesKt$launchChannel$job$1 = new CoroutinesKt$launchChannel$job$1(this.$attachJob, this.$channel, this.$block, this.$dispatcher, continuation);
        coroutinesKt$launchChannel$job$1.L$0 = obj;
        return coroutinesKt$launchChannel$job$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((CoroutinesKt$launchChannel$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                if (this.$attachJob) {
                    ByteChannel byteChannel = this.$channel;
                    CoroutineContext.Element element = coroutineScope.getCoroutineContext().get(Job.Key);
                    Intrinsics.checkNotNull(element);
                    byteChannel.attachJob((Job) element);
                }
                ChannelScope channelScope = new ChannelScope(coroutineScope, this.$channel);
                Function2 function2 = this.$block;
                this.label = 1;
                if (function2.invoke(channelScope, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
        } catch (Throwable th) {
            if (!Intrinsics.areEqual(this.$dispatcher, Dispatchers.getUnconfined()) && this.$dispatcher != null) {
                throw th;
            }
            this.$channel.cancel(th);
        }
        return Unit.INSTANCE;
    }
}
