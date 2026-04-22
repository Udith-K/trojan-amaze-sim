package androidx.compose.foundation.text.input.internal;

import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: CursorAnimationState.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CursorAnimationState {
    private AtomicReference animationJob = new AtomicReference(null);
    private final MutableFloatState cursorAlpha$delegate = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCursorAlpha(float f) {
        this.cursorAlpha$delegate.setFloatValue(f);
    }

    public final float getCursorAlpha() {
        return this.cursorAlpha$delegate.getFloatValue();
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2, reason: invalid class name */
    /* JADX INFO: compiled from: CursorAnimationState.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = CursorAnimationState.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxBoolean(MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(CursorAnimationState.this.animationJob, null, BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.L$0, null, null, new AnonymousClass1((Job) CursorAnimationState.this.animationJob.getAndSet(null), CursorAnimationState.this, null), 3, null)));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.CursorAnimationState$snapToVisibleAndAnimate$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: CursorAnimationState.kt */
        static final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ Job $oldJob;
            int label;
            final /* synthetic */ CursorAnimationState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Job job, CursorAnimationState cursorAnimationState, Continuation continuation) {
                super(2, continuation);
                this.$oldJob = job;
                this.this$0 = cursorAnimationState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.$oldJob, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:23:0x0049 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0057 A[RETURN] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0055 -> B:21:0x003a). Please report as a decompilation issue!!! */
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r9) {
                /*
                    r8 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r8.label
                    r2 = 0
                    r3 = 500(0x1f4, double:2.47E-321)
                    r5 = 3
                    r6 = 2
                    r7 = 1
                    if (r1 == 0) goto L2a
                    if (r1 == r7) goto L26
                    if (r1 == r6) goto L22
                    if (r1 != r5) goto L1a
                    kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L18
                    goto L3a
                L18:
                    r9 = move-exception
                    goto L58
                L1a:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r0)
                    throw r9
                L22:
                    kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L18
                    goto L4a
                L26:
                    kotlin.ResultKt.throwOnFailure(r9)
                    goto L3a
                L2a:
                    kotlin.ResultKt.throwOnFailure(r9)
                    kotlinx.coroutines.Job r9 = r8.$oldJob
                    if (r9 == 0) goto L3a
                    r8.label = r7
                    java.lang.Object r9 = kotlinx.coroutines.JobKt.cancelAndJoin(r9, r8)
                    if (r9 != r0) goto L3a
                    return r0
                L3a:
                    androidx.compose.foundation.text.input.internal.CursorAnimationState r9 = r8.this$0     // Catch: java.lang.Throwable -> L18
                    r1 = 1065353216(0x3f800000, float:1.0)
                    androidx.compose.foundation.text.input.internal.CursorAnimationState.access$setCursorAlpha(r9, r1)     // Catch: java.lang.Throwable -> L18
                    r8.label = r6     // Catch: java.lang.Throwable -> L18
                    java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r3, r8)     // Catch: java.lang.Throwable -> L18
                    if (r9 != r0) goto L4a
                    return r0
                L4a:
                    androidx.compose.foundation.text.input.internal.CursorAnimationState r9 = r8.this$0     // Catch: java.lang.Throwable -> L18
                    androidx.compose.foundation.text.input.internal.CursorAnimationState.access$setCursorAlpha(r9, r2)     // Catch: java.lang.Throwable -> L18
                    r8.label = r5     // Catch: java.lang.Throwable -> L18
                    java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r3, r8)     // Catch: java.lang.Throwable -> L18
                    if (r9 != r0) goto L3a
                    return r0
                L58:
                    androidx.compose.foundation.text.input.internal.CursorAnimationState r0 = r8.this$0
                    androidx.compose.foundation.text.input.internal.CursorAnimationState.access$setCursorAlpha(r0, r2)
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.CursorAnimationState.AnonymousClass2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    public final Object snapToVisibleAndAnimate(Continuation continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }
}
