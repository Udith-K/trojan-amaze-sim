package androidx.lifecycle;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: CoroutineLiveData.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LiveDataScopeImpl implements LiveDataScope {
    private final CoroutineContext coroutineContext;
    private CoroutineLiveData target;

    public LiveDataScopeImpl(CoroutineLiveData target, CoroutineContext context) {
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(context, "context");
        this.target = target;
        this.coroutineContext = context.plus(Dispatchers.getMain().getImmediate());
    }

    public final CoroutineLiveData getTarget$lifecycle_livedata_release() {
        return this.target;
    }

    /* JADX INFO: renamed from: androidx.lifecycle.LiveDataScopeImpl$emit$2, reason: invalid class name */
    /* JADX INFO: compiled from: CoroutineLiveData.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ Object $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Object obj, Continuation continuation) {
            super(2, continuation);
            this.$value = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return LiveDataScopeImpl.this.new AnonymousClass2(this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineLiveData target$lifecycle_livedata_release = LiveDataScopeImpl.this.getTarget$lifecycle_livedata_release();
                this.label = 1;
                if (target$lifecycle_livedata_release.clearSource$lifecycle_livedata_release(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            LiveDataScopeImpl.this.getTarget$lifecycle_livedata_release().setValue(this.$value);
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.lifecycle.LiveDataScope
    public Object emit(Object obj, Continuation continuation) {
        Object objWithContext = BuildersKt.withContext(this.coroutineContext, new AnonymousClass2(obj, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }
}
