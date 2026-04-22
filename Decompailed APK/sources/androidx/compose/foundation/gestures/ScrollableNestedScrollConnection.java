package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
final class ScrollableNestedScrollConnection implements NestedScrollConnection {
    private boolean enabled;
    private final ScrollingLogic scrollingLogic;

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo, reason: not valid java name */
    public /* synthetic */ Object mo218onPreFlingQWom1Mo(long j, Continuation continuation) {
        return NestedScrollConnection.CC.m1635onPreFlingQWom1Mo$suspendImpl(this, j, continuation);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk, reason: not valid java name */
    public /* synthetic */ long mo219onPreScrollOzD1aCk(long j, int i) {
        return Offset.Companion.m1171getZeroF1C5BW0();
    }

    public ScrollableNestedScrollConnection(ScrollingLogic scrollingLogic, boolean z) {
        this.scrollingLogic = scrollingLogic;
        this.enabled = z;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M, reason: not valid java name */
    public long mo217onPostScrollDzOQY0M(long j, long j2, int i) {
        if (this.enabled) {
            return this.scrollingLogic.m232performRawScrollMKHz9U(j2);
        }
        return Offset.Companion.m1171getZeroF1C5BW0();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object mo216onPostFlingRZ2iAVY(long r3, long r5, kotlin.coroutines.Continuation r7) {
        /*
            r2 = this;
            boolean r3 = r7 instanceof androidx.compose.foundation.gestures.ScrollableNestedScrollConnection$onPostFling$1
            if (r3 == 0) goto L13
            r3 = r7
            androidx.compose.foundation.gestures.ScrollableNestedScrollConnection$onPostFling$1 r3 = (androidx.compose.foundation.gestures.ScrollableNestedScrollConnection$onPostFling$1) r3
            int r4 = r3.label
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r4 & r0
            if (r1 == 0) goto L13
            int r4 = r4 - r0
            r3.label = r4
            goto L18
        L13:
            androidx.compose.foundation.gestures.ScrollableNestedScrollConnection$onPostFling$1 r3 = new androidx.compose.foundation.gestures.ScrollableNestedScrollConnection$onPostFling$1
            r3.<init>(r2, r7)
        L18:
            java.lang.Object r4 = r3.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r3.label
            r1 = 1
            if (r0 == 0) goto L33
            if (r0 != r1) goto L2b
            long r5 = r3.J$0
            kotlin.ResultKt.throwOnFailure(r4)
            goto L47
        L2b:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r4)
            throw r3
        L33:
            kotlin.ResultKt.throwOnFailure(r4)
            boolean r4 = r2.enabled
            if (r4 == 0) goto L52
            androidx.compose.foundation.gestures.ScrollingLogic r4 = r2.scrollingLogic
            r3.J$0 = r5
            r3.label = r1
            java.lang.Object r4 = r4.m230doFlingAnimationQWom1Mo(r5, r3)
            if (r4 != r7) goto L47
            return r7
        L47:
            androidx.compose.ui.unit.Velocity r4 = (androidx.compose.ui.unit.Velocity) r4
            long r3 = r4.m2523unboximpl()
            long r3 = androidx.compose.ui.unit.Velocity.m2519minusAH228Gc(r5, r3)
            goto L58
        L52:
            androidx.compose.ui.unit.Velocity$Companion r3 = androidx.compose.ui.unit.Velocity.Companion
            long r3 = r3.m2524getZero9UxMQ8M()
        L58:
            androidx.compose.ui.unit.Velocity r3 = androidx.compose.ui.unit.Velocity.m2510boximpl(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollableNestedScrollConnection.mo216onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
