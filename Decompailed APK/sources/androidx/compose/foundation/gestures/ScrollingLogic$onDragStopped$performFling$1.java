package androidx.compose.foundation.gestures;

import androidx.compose.ui.unit.Velocity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
final class ScrollingLogic$onDragStopped$performFling$1 extends SuspendLambda implements Function2 {
    /* synthetic */ long J$0;
    long J$1;
    int label;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScrollingLogic$onDragStopped$performFling$1(ScrollingLogic scrollingLogic, Continuation continuation) {
        super(2, continuation);
        this.this$0 = scrollingLogic;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ScrollingLogic$onDragStopped$performFling$1 scrollingLogic$onDragStopped$performFling$1 = new ScrollingLogic$onDragStopped$performFling$1(this.this$0, continuation);
        scrollingLogic$onDragStopped$performFling$1.J$0 = ((Velocity) obj).m2523unboximpl();
        return scrollingLogic$onDragStopped$performFling$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m237invokesFctU(((Velocity) obj).m2523unboximpl(), (Continuation) obj2);
    }

    /* JADX INFO: renamed from: invoke-sF-c-tU, reason: not valid java name */
    public final Object m237invokesFctU(long j, Continuation continuation) {
        return ((ScrollingLogic$onDragStopped$performFling$1) create(Velocity.m2510boximpl(j), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0087 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0088  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r13.label
            r1 = 3
            r2 = 2
            r3 = 1
            if (r0 == 0) goto L35
            if (r0 == r3) goto L2e
            if (r0 == r2) goto L24
            if (r0 != r1) goto L1c
            long r0 = r13.J$1
            long r2 = r13.J$0
            kotlin.ResultKt.throwOnFailure(r14)
            r9 = r0
            r0 = r14
            goto L89
        L1c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L24:
            long r2 = r13.J$1
            long r4 = r13.J$0
            kotlin.ResultKt.throwOnFailure(r14)
            r0 = r14
            r7 = r4
            goto L68
        L2e:
            long r3 = r13.J$0
            kotlin.ResultKt.throwOnFailure(r14)
            r0 = r14
            goto L4c
        L35:
            kotlin.ResultKt.throwOnFailure(r14)
            long r4 = r13.J$0
            androidx.compose.foundation.gestures.ScrollingLogic r0 = r13.this$0
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r0 = androidx.compose.foundation.gestures.ScrollingLogic.access$getNestedScrollDispatcher$p(r0)
            r13.J$0 = r4
            r13.label = r3
            java.lang.Object r0 = r0.m1638dispatchPreFlingQWom1Mo(r4, r13)
            if (r0 != r6) goto L4b
            return r6
        L4b:
            r3 = r4
        L4c:
            androidx.compose.ui.unit.Velocity r0 = (androidx.compose.ui.unit.Velocity) r0
            long r7 = r0.m2523unboximpl()
            long r7 = androidx.compose.ui.unit.Velocity.m2519minusAH228Gc(r3, r7)
            androidx.compose.foundation.gestures.ScrollingLogic r0 = r13.this$0
            r13.J$0 = r3
            r13.J$1 = r7
            r13.label = r2
            java.lang.Object r0 = r0.m230doFlingAnimationQWom1Mo(r7, r13)
            if (r0 != r6) goto L65
            return r6
        L65:
            r11 = r3
            r2 = r7
            r7 = r11
        L68:
            androidx.compose.ui.unit.Velocity r0 = (androidx.compose.ui.unit.Velocity) r0
            long r9 = r0.m2523unboximpl()
            androidx.compose.foundation.gestures.ScrollingLogic r0 = r13.this$0
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher r0 = androidx.compose.foundation.gestures.ScrollingLogic.access$getNestedScrollDispatcher$p(r0)
            long r2 = androidx.compose.ui.unit.Velocity.m2519minusAH228Gc(r2, r9)
            r13.J$0 = r7
            r13.J$1 = r9
            r13.label = r1
            r1 = r2
            r3 = r9
            r5 = r13
            java.lang.Object r0 = r0.m1636dispatchPostFlingRZ2iAVY(r1, r3, r5)
            if (r0 != r6) goto L88
            return r6
        L88:
            r2 = r7
        L89:
            androidx.compose.ui.unit.Velocity r0 = (androidx.compose.ui.unit.Velocity) r0
            long r0 = r0.m2523unboximpl()
            long r0 = androidx.compose.ui.unit.Velocity.m2519minusAH228Gc(r9, r0)
            long r0 = androidx.compose.ui.unit.Velocity.m2519minusAH228Gc(r2, r0)
            androidx.compose.ui.unit.Velocity r0 = androidx.compose.ui.unit.Velocity.m2510boximpl(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollingLogic$onDragStopped$performFling$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
