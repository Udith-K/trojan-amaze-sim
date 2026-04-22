package androidx.compose.ui.platform;

import android.view.Choreographer;
import androidx.compose.runtime.MonotonicFrameClock;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* JADX INFO: compiled from: AndroidUiFrameClock.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidUiFrameClock implements MonotonicFrameClock {
    private final Choreographer choreographer;
    private final AndroidUiDispatcher dispatcher;

    @Override // kotlin.coroutines.CoroutineContext.Element
    public /* synthetic */ CoroutineContext.Key getKey() {
        return MonotonicFrameClock.Key;
    }

    public AndroidUiFrameClock(Choreographer choreographer, AndroidUiDispatcher androidUiDispatcher) {
        this.choreographer = choreographer;
        this.dispatcher = androidUiDispatcher;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public Object fold(Object obj, Function2 function2) {
        return MonotonicFrameClock.DefaultImpls.fold(this, obj, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext.Element get(CoroutineContext.Key key) {
        return MonotonicFrameClock.DefaultImpls.get(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key key) {
        return MonotonicFrameClock.DefaultImpls.minusKey(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return MonotonicFrameClock.DefaultImpls.plus(this, coroutineContext);
    }

    public final Choreographer getChoreographer() {
        return this.choreographer;
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock
    public Object withFrameNanos(final Function1 function1, Continuation continuation) {
        final AndroidUiDispatcher androidUiDispatcher = this.dispatcher;
        if (androidUiDispatcher == null) {
            CoroutineContext.Element element = continuation.getContext().get(ContinuationInterceptor.Key);
            androidUiDispatcher = element instanceof AndroidUiDispatcher ? (AndroidUiDispatcher) element : null;
        }
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final Choreographer.FrameCallback frameCallback = new Choreographer.FrameCallback() { // from class: androidx.compose.ui.platform.AndroidUiFrameClock$withFrameNanos$2$callback$1
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                Object objM2639constructorimpl;
                CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
                Function1 function12 = function1;
                try {
                    Result.Companion companion = Result.Companion;
                    objM2639constructorimpl = Result.m2639constructorimpl(function12.invoke(Long.valueOf(j)));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th));
                }
                cancellableContinuation.resumeWith(objM2639constructorimpl);
            }
        };
        if (androidUiDispatcher != null && Intrinsics.areEqual(androidUiDispatcher.getChoreographer(), getChoreographer())) {
            androidUiDispatcher.postFrameCallback$ui_release(frameCallback);
            cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: androidx.compose.ui.platform.AndroidUiFrameClock$withFrameNanos$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Throwable) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th) {
                    androidUiDispatcher.removeFrameCallback$ui_release(frameCallback);
                }
            });
        } else {
            getChoreographer().postFrameCallback(frameCallback);
            cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: androidx.compose.ui.platform.AndroidUiFrameClock$withFrameNanos$2$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Throwable) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th) {
                    this.this$0.getChoreographer().removeFrameCallback(frameCallback);
                }
            });
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
