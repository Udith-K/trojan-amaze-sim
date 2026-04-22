package androidx.compose.animation.core;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: InternalMutatorMutex.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MutatorMutex {
    private final AtomicReference currentMutator = new AtomicReference(null);
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, null);

    /* JADX INFO: compiled from: InternalMutatorMutex.kt */
    private static final class Mutator {
        private final Job job;
        private final MutatePriority priority;

        public Mutator(MutatePriority mutatePriority, Job job) {
            this.priority = mutatePriority;
            this.job = job;
        }

        public final boolean canInterrupt(Mutator mutator) {
            return this.priority.compareTo(mutator.priority) >= 0;
        }

        public final void cancel() {
            this.job.cancel(new MutationInterruptedException());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void tryMutateOrCancel(Mutator mutator) {
        Mutator mutator2;
        do {
            mutator2 = (Mutator) this.currentMutator.get();
            if (mutator2 != null && !mutator.canInterrupt(mutator2)) {
                throw new CancellationException("Current mutation had a higher priority");
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.currentMutator, mutator2, mutator));
        if (mutator2 != null) {
            mutator2.cancel();
        }
    }

    public static /* synthetic */ Object mutate$default(MutatorMutex mutatorMutex, MutatePriority mutatePriority, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return mutatorMutex.mutate(mutatePriority, function1, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.MutatorMutex$mutate$2, reason: invalid class name */
    /* JADX INFO: compiled from: InternalMutatorMutex.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function1 $block;
        final /* synthetic */ MutatePriority $priority;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MutatorMutex this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(MutatePriority mutatePriority, MutatorMutex mutatorMutex, Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$priority = mutatePriority;
            this.this$0 = mutatorMutex;
            this.$block = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$priority, this.this$0, this.$block, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [int, kotlinx.coroutines.sync.Mutex] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Mutex mutex;
            Function1 function1;
            Mutator mutator;
            MutatorMutex mutatorMutex;
            Mutator mutator2;
            Throwable th;
            MutatorMutex mutatorMutex2;
            Mutex mutex2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r1 = this.label;
            try {
                try {
                    if (r1 == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        MutatePriority mutatePriority = this.$priority;
                        CoroutineContext.Element element = coroutineScope.getCoroutineContext().get(Job.Key);
                        Intrinsics.checkNotNull(element);
                        Mutator mutator3 = new Mutator(mutatePriority, (Job) element);
                        this.this$0.tryMutateOrCancel(mutator3);
                        mutex = this.this$0.mutex;
                        Function1 function12 = this.$block;
                        MutatorMutex mutatorMutex3 = this.this$0;
                        this.L$0 = mutator3;
                        this.L$1 = mutex;
                        this.L$2 = function12;
                        this.L$3 = mutatorMutex3;
                        this.label = 1;
                        if (mutex.lock(null, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        function1 = function12;
                        mutator = mutator3;
                        mutatorMutex = mutatorMutex3;
                    } else {
                        if (r1 != 1) {
                            if (r1 != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            mutatorMutex2 = (MutatorMutex) this.L$2;
                            mutex2 = (Mutex) this.L$1;
                            mutator2 = (Mutator) this.L$0;
                            try {
                                ResultKt.throwOnFailure(obj);
                                MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                                mutex2.unlock(null);
                                return obj;
                            } catch (Throwable th2) {
                                th = th2;
                                MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                                throw th;
                            }
                        }
                        mutatorMutex = (MutatorMutex) this.L$3;
                        function1 = (Function1) this.L$2;
                        Mutex mutex3 = (Mutex) this.L$1;
                        mutator = (Mutator) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        mutex = mutex3;
                    }
                    this.L$0 = mutator;
                    this.L$1 = mutex;
                    this.L$2 = mutatorMutex;
                    this.L$3 = null;
                    this.label = 2;
                    Object objInvoke = function1.invoke(this);
                    if (objInvoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mutatorMutex2 = mutatorMutex;
                    mutex2 = mutex;
                    obj = objInvoke;
                    mutator2 = mutator;
                    MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                    mutex2.unlock(null);
                    return obj;
                } catch (Throwable th3) {
                    mutator2 = mutator;
                    th = th3;
                    mutatorMutex2 = mutatorMutex;
                    MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(mutatorMutex2.currentMutator, mutator2, null);
                    throw th;
                }
            } catch (Throwable th4) {
                r1.unlock(null);
                throw th4;
            }
        }
    }

    public final Object mutate(MutatePriority mutatePriority, Function1 function1, Continuation continuation) {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(mutatePriority, this, function1, null), continuation);
    }
}
