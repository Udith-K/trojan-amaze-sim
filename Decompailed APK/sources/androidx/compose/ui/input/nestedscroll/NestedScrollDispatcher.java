package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.geometry.Offset;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NestedScrollModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public final class NestedScrollDispatcher {
    private Function0 calculateNestedScrollScope = new Function0() { // from class: androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$calculateNestedScrollScope$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final CoroutineScope invoke() {
            return this.this$0.getScope$ui_release();
        }
    };
    private NestedScrollNode nestedScrollNode;
    private CoroutineScope scope;

    public final NestedScrollNode getNestedScrollNode$ui_release() {
        return this.nestedScrollNode;
    }

    public final void setNestedScrollNode$ui_release(NestedScrollNode nestedScrollNode) {
        this.nestedScrollNode = nestedScrollNode;
    }

    public final void setCalculateNestedScrollScope$ui_release(Function0 function0) {
        this.calculateNestedScrollScope = function0;
    }

    public final CoroutineScope getScope$ui_release() {
        return this.scope;
    }

    public final void setScope$ui_release(CoroutineScope coroutineScope) {
        this.scope = coroutineScope;
    }

    public final CoroutineScope getCoroutineScope() {
        CoroutineScope coroutineScope = (CoroutineScope) this.calculateNestedScrollScope.invoke();
        if (coroutineScope != null) {
            return coroutineScope;
        }
        throw new IllegalStateException("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
    }

    public final NestedScrollConnection getParent$ui_release() {
        NestedScrollNode nestedScrollNode = this.nestedScrollNode;
        if (nestedScrollNode != null) {
            return nestedScrollNode.getParentNestedScrollNode$ui_release();
        }
        return null;
    }

    /* JADX INFO: renamed from: dispatchPreScroll-OzD1aCk, reason: not valid java name */
    public final long m1639dispatchPreScrollOzD1aCk(long j, int i) {
        NestedScrollConnection parent$ui_release = getParent$ui_release();
        return parent$ui_release != null ? parent$ui_release.mo219onPreScrollOzD1aCk(j, i) : Offset.Companion.m1171getZeroF1C5BW0();
    }

    /* JADX INFO: renamed from: dispatchPostScroll-DzOQY0M, reason: not valid java name */
    public final long m1637dispatchPostScrollDzOQY0M(long j, long j2, int i) {
        NestedScrollConnection parent$ui_release = getParent$ui_release();
        return parent$ui_release != null ? parent$ui_release.mo217onPostScrollDzOQY0M(j, j2, i) : Offset.Companion.m1171getZeroF1C5BW0();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: dispatchPreFling-QWom1Mo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m1638dispatchPreFlingQWom1Mo(long r5, kotlin.coroutines.Continuation r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPreFling$1
            if (r0 == 0) goto L13
            r0 = r7
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPreFling$1 r0 = (androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPreFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPreFling$1 r0 = new androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPreFling$1
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r7)
            goto L43
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r7 = r4.getParent$ui_release()
            if (r7 == 0) goto L4a
            r0.label = r3
            java.lang.Object r7 = r7.mo218onPreFlingQWom1Mo(r5, r0)
            if (r7 != r1) goto L43
            return r1
        L43:
            androidx.compose.ui.unit.Velocity r7 = (androidx.compose.ui.unit.Velocity) r7
            long r5 = r7.m2523unboximpl()
            goto L50
        L4a:
            androidx.compose.ui.unit.Velocity$Companion r5 = androidx.compose.ui.unit.Velocity.Companion
            long r5 = r5.m2524getZero9UxMQ8M()
        L50:
            androidx.compose.ui.unit.Velocity r5 = androidx.compose.ui.unit.Velocity.m2510boximpl(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher.m1638dispatchPreFlingQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /* JADX INFO: renamed from: dispatchPostFling-RZ2iAVY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m1636dispatchPostFlingRZ2iAVY(long r8, long r10, kotlin.coroutines.Continuation r12) {
        /*
            r7 = this;
            boolean r0 = r12 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPostFling$1
            if (r0 == 0) goto L14
            r0 = r12
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPostFling$1 r0 = (androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPostFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPostFling$1 r0 = new androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher$dispatchPostFling$1
            r0.<init>(r7, r12)
            goto L12
        L1a:
            java.lang.Object r12 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            kotlin.ResultKt.throwOnFailure(r12)
            goto L47
        L2b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L33:
            kotlin.ResultKt.throwOnFailure(r12)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r1 = r7.getParent$ui_release()
            if (r1 == 0) goto L4e
            r6.label = r2
            r2 = r8
            r4 = r10
            java.lang.Object r12 = r1.mo216onPostFlingRZ2iAVY(r2, r4, r6)
            if (r12 != r0) goto L47
            return r0
        L47:
            androidx.compose.ui.unit.Velocity r12 = (androidx.compose.ui.unit.Velocity) r12
            long r8 = r12.m2523unboximpl()
            goto L54
        L4e:
            androidx.compose.ui.unit.Velocity$Companion r8 = androidx.compose.ui.unit.Velocity.Companion
            long r8 = r8.m2524getZero9UxMQ8M()
        L54:
            androidx.compose.ui.unit.Velocity r8 = androidx.compose.ui.unit.Velocity.m2510boximpl(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher.m1636dispatchPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
