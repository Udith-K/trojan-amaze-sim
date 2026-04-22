package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: FlowLiveData.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FlowLiveDataConversions {
    public static /* synthetic */ LiveData asLiveData$default(Flow flow, CoroutineContext coroutineContext, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            j = 5000;
        }
        return asLiveData(flow, coroutineContext, j);
    }

    /* JADX INFO: renamed from: androidx.lifecycle.FlowLiveDataConversions$asLiveData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: FlowLiveData.kt */
    static final class C01641 extends SuspendLambda implements Function2 {
        final /* synthetic */ Flow $this_asLiveData;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01641(Flow flow, Continuation continuation) {
            super(2, continuation);
            this.$this_asLiveData = flow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            C01641 c01641 = new C01641(this.$this_asLiveData, continuation);
            c01641.L$0 = obj;
            return c01641;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(LiveDataScope liveDataScope, Continuation continuation) {
            return ((C01641) create(liveDataScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final LiveDataScope liveDataScope = (LiveDataScope) this.L$0;
                Flow flow = this.$this_asLiveData;
                FlowCollector flowCollector = new FlowCollector() { // from class: androidx.lifecycle.FlowLiveDataConversions.asLiveData.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Object objEmit = liveDataScope.emit(obj2, continuation);
                        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flow.collect(flowCollector, this) == coroutine_suspended) {
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

    public static final LiveData asLiveData(Flow flow, CoroutineContext context, long j) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        LiveData liveData = CoroutineLiveDataKt.liveData(context, j, new C01641(flow, null));
        if (flow instanceof StateFlow) {
            if (ArchTaskExecutor.getInstance().isMainThread()) {
                liveData.setValue(((StateFlow) flow).getValue());
            } else {
                liveData.postValue(((StateFlow) flow).getValue());
            }
        }
        return liveData;
    }

    /* JADX INFO: renamed from: androidx.lifecycle.FlowLiveDataConversions$asFlow$1, reason: invalid class name */
    /* JADX INFO: compiled from: FlowLiveData.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ LiveData $this_asFlow;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LiveData liveData, Continuation continuation) {
            super(2, continuation);
            this.$this_asFlow = liveData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_asFlow, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope producerScope, Continuation continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:28:0x008a A[RETURN] */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v1, types: [androidx.lifecycle.Observer] */
        /* JADX WARN: Type inference failed for: r1v13 */
        /* JADX WARN: Type inference failed for: r1v14 */
        /* JADX WARN: Type inference failed for: r1v6 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                if (r1 == 0) goto L40
                if (r1 == r5) goto L38
                if (r1 == r4) goto L30
                if (r1 == r3) goto L26
                if (r1 == r2) goto L1d
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L1d:
                java.lang.Object r0 = r9.L$0
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                kotlin.ResultKt.throwOnFailure(r10)
                goto Lb2
            L26:
                java.lang.Object r1 = r9.L$0
                androidx.lifecycle.Observer r1 = (androidx.lifecycle.Observer) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L2e
                goto L8b
            L2e:
                r10 = move-exception
                goto L91
            L30:
                java.lang.Object r1 = r9.L$0
                androidx.lifecycle.Observer r1 = (androidx.lifecycle.Observer) r1
                kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L2e
                goto L80
            L38:
                java.lang.Object r1 = r9.L$0
                androidx.lifecycle.Observer r1 = (androidx.lifecycle.Observer) r1
                kotlin.ResultKt.throwOnFailure(r10)
                goto L66
            L40:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
                androidx.lifecycle.FlowLiveDataConversions$asFlow$1$$ExternalSyntheticLambda0 r1 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$$ExternalSyntheticLambda0
                r1.<init>()
                kotlinx.coroutines.MainCoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getMain()
                kotlinx.coroutines.MainCoroutineDispatcher r10 = r10.getImmediate()
                androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1 r7 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1
                androidx.lifecycle.LiveData r8 = r9.$this_asFlow
                r7.<init>(r8, r1, r6)
                r9.L$0 = r1
                r9.label = r5
                java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r10, r7, r9)
                if (r10 != r0) goto L66
                return r0
            L66:
                kotlinx.coroutines.MainCoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.lang.Throwable -> L2e
                kotlinx.coroutines.MainCoroutineDispatcher r10 = r10.getImmediate()     // Catch: java.lang.Throwable -> L2e
                androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2 r5 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2     // Catch: java.lang.Throwable -> L2e
                androidx.lifecycle.LiveData r7 = r9.$this_asFlow     // Catch: java.lang.Throwable -> L2e
                r5.<init>(r7, r1, r6)     // Catch: java.lang.Throwable -> L2e
                r9.L$0 = r1     // Catch: java.lang.Throwable -> L2e
                r9.label = r4     // Catch: java.lang.Throwable -> L2e
                java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r10, r5, r9)     // Catch: java.lang.Throwable -> L2e
                if (r10 != r0) goto L80
                return r0
            L80:
                r9.L$0 = r1     // Catch: java.lang.Throwable -> L2e
                r9.label = r3     // Catch: java.lang.Throwable -> L2e
                java.lang.Object r10 = kotlinx.coroutines.DelayKt.awaitCancellation(r9)     // Catch: java.lang.Throwable -> L2e
                if (r10 != r0) goto L8b
                return r0
            L8b:
                kotlin.KotlinNothingValueException r10 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> L2e
                r10.<init>()     // Catch: java.lang.Throwable -> L2e
                throw r10     // Catch: java.lang.Throwable -> L2e
            L91:
                kotlinx.coroutines.MainCoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getMain()
                kotlinx.coroutines.MainCoroutineDispatcher r3 = r3.getImmediate()
                kotlinx.coroutines.NonCancellable r4 = kotlinx.coroutines.NonCancellable.INSTANCE
                kotlin.coroutines.CoroutineContext r3 = r3.plus(r4)
                androidx.lifecycle.FlowLiveDataConversions$asFlow$1$3 r4 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$3
                androidx.lifecycle.LiveData r5 = r9.$this_asFlow
                r4.<init>(r5, r1, r6)
                r9.L$0 = r10
                r9.label = r2
                java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r3, r4, r9)
                if (r1 != r0) goto Lb1
                return r0
            Lb1:
                r0 = r10
            Lb2:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.FlowLiveDataConversions.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: FlowLiveData.kt */
        static final class C00371 extends SuspendLambda implements Function2 {
            final /* synthetic */ Observer $observer;
            final /* synthetic */ LiveData $this_asFlow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00371(LiveData liveData, Observer observer, Continuation continuation) {
                super(2, continuation);
                this.$this_asFlow = liveData;
                this.$observer = observer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00371(this.$this_asFlow, this.$observer, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((C00371) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$this_asFlow.observeForever(this.$observer);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: FlowLiveData.kt */
        static final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ Observer $observer;
            final /* synthetic */ LiveData $this_asFlow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(LiveData liveData, Observer observer, Continuation continuation) {
                super(2, continuation);
                this.$this_asFlow = liveData;
                this.$observer = observer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$this_asFlow, this.$observer, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$this_asFlow.observeForever(this.$observer);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$3, reason: invalid class name */
        /* JADX INFO: compiled from: FlowLiveData.kt */
        static final class AnonymousClass3 extends SuspendLambda implements Function2 {
            final /* synthetic */ Observer $observer;
            final /* synthetic */ LiveData $this_asFlow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(LiveData liveData, Observer observer, Continuation continuation) {
                super(2, continuation);
                this.$this_asFlow = liveData;
                this.$observer = observer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.$this_asFlow, this.$observer, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$this_asFlow.removeObserver(this.$observer);
                return Unit.INSTANCE;
            }
        }
    }

    public static final Flow asFlow(LiveData liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        return FlowKt.conflate(FlowKt.callbackFlow(new AnonymousClass1(liveData, null)));
    }
}
