package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NestedScrollNode.kt */
/* JADX INFO: loaded from: classes.dex */
public final class NestedScrollNode extends Modifier.Node implements TraversableNode, NestedScrollConnection {
    private NestedScrollConnection connection;
    private NestedScrollDispatcher resolvedDispatcher;
    private final Object traverseKey;

    public NestedScrollNode(NestedScrollConnection nestedScrollConnection, NestedScrollDispatcher nestedScrollDispatcher) {
        this.connection = nestedScrollConnection;
        this.resolvedDispatcher = nestedScrollDispatcher == null ? new NestedScrollDispatcher() : nestedScrollDispatcher;
        this.traverseKey = "androidx.compose.ui.input.nestedscroll.NestedScrollNode";
    }

    public final NestedScrollNode getParentNestedScrollNode$ui_release() {
        if (isAttached()) {
            return (NestedScrollNode) TraversableNodeKt.findNearestAncestor(this);
        }
        return null;
    }

    private final NestedScrollConnection getParentConnection() {
        if (isAttached()) {
            return getParentNestedScrollNode$ui_release();
        }
        return null;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return this.traverseKey;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CoroutineScope getNestedCoroutineScope() {
        CoroutineScope scope$ui_release;
        NestedScrollNode parentNestedScrollNode$ui_release = getParentNestedScrollNode$ui_release();
        if ((parentNestedScrollNode$ui_release == null || (scope$ui_release = parentNestedScrollNode$ui_release.getNestedCoroutineScope()) == null) && (scope$ui_release = this.resolvedDispatcher.getScope$ui_release()) == null) {
            throw new IllegalStateException("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
        }
        return scope$ui_release;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo219onPreScrollOzD1aCk(long j, int i) {
        NestedScrollConnection parentConnection = getParentConnection();
        long jMo219onPreScrollOzD1aCk = parentConnection != null ? parentConnection.mo219onPreScrollOzD1aCk(j, i) : Offset.Companion.m1171getZeroF1C5BW0();
        return Offset.m1164plusMKHz9U(jMo219onPreScrollOzD1aCk, this.connection.mo219onPreScrollOzD1aCk(Offset.m1163minusMKHz9U(j, jMo219onPreScrollOzD1aCk), i));
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo217onPostScrollDzOQY0M(long j, long j2, int i) {
        long jM1171getZeroF1C5BW0;
        long jMo217onPostScrollDzOQY0M = this.connection.mo217onPostScrollDzOQY0M(j, j2, i);
        NestedScrollConnection parentConnection = getParentConnection();
        if (parentConnection != null) {
            jM1171getZeroF1C5BW0 = parentConnection.mo217onPostScrollDzOQY0M(Offset.m1164plusMKHz9U(j, jMo217onPostScrollDzOQY0M), Offset.m1163minusMKHz9U(j2, jMo217onPostScrollDzOQY0M), i);
        } else {
            jM1171getZeroF1C5BW0 = Offset.Companion.m1171getZeroF1C5BW0();
        }
        return Offset.m1164plusMKHz9U(jMo217onPostScrollDzOQY0M, jM1171getZeroF1C5BW0);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object mo218onPreFlingQWom1Mo(long r9, kotlin.coroutines.Continuation r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1
            if (r0 == 0) goto L13
            r0 = r11
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1 r0 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1 r0 = new androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPreFling$1
            r0.<init>(r8, r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            long r9 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L7d
        L2e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L36:
            long r9 = r0.J$0
            java.lang.Object r2 = r0.L$0
            androidx.compose.ui.input.nestedscroll.NestedScrollNode r2 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode) r2
            kotlin.ResultKt.throwOnFailure(r11)
            goto L57
        L40:
            kotlin.ResultKt.throwOnFailure(r11)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r11 = r8.getParentConnection()
            if (r11 == 0) goto L61
            r0.L$0 = r8
            r0.J$0 = r9
            r0.label = r4
            java.lang.Object r11 = r11.mo218onPreFlingQWom1Mo(r9, r0)
            if (r11 != r1) goto L56
            return r1
        L56:
            r2 = r8
        L57:
            androidx.compose.ui.unit.Velocity r11 = (androidx.compose.ui.unit.Velocity) r11
            long r4 = r11.m2523unboximpl()
        L5d:
            r6 = r9
            r9 = r4
            r4 = r6
            goto L69
        L61:
            androidx.compose.ui.unit.Velocity$Companion r11 = androidx.compose.ui.unit.Velocity.Companion
            long r4 = r11.m2524getZero9UxMQ8M()
            r2 = r8
            goto L5d
        L69:
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r11 = r2.connection
            long r4 = androidx.compose.ui.unit.Velocity.m2519minusAH228Gc(r4, r9)
            r2 = 0
            r0.L$0 = r2
            r0.J$0 = r9
            r0.label = r3
            java.lang.Object r11 = r11.mo218onPreFlingQWom1Mo(r4, r0)
            if (r11 != r1) goto L7d
            return r1
        L7d:
            androidx.compose.ui.unit.Velocity r11 = (androidx.compose.ui.unit.Velocity) r11
            long r0 = r11.m2523unboximpl()
            long r9 = androidx.compose.ui.unit.Velocity.m2520plusAH228Gc(r9, r0)
            androidx.compose.ui.unit.Velocity r9 = androidx.compose.ui.unit.Velocity.m2510boximpl(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollNode.mo218onPreFlingQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object mo216onPostFlingRZ2iAVY(long r16, long r18, kotlin.coroutines.Continuation r20) {
        /*
            r15 = this;
            r0 = r15
            r1 = r20
            boolean r2 = r1 instanceof androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1
            if (r2 == 0) goto L16
            r2 = r1
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1 r2 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L16
            int r3 = r3 - r4
            r2.label = r3
            goto L1b
        L16:
            androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1 r2 = new androidx.compose.ui.input.nestedscroll.NestedScrollNode$onPostFling$1
            r2.<init>(r15, r1)
        L1b:
            java.lang.Object r1 = r2.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r2.label
            r10 = 2
            r4 = 1
            if (r3 == 0) goto L47
            if (r3 == r4) goto L39
            if (r3 != r10) goto L31
            long r2 = r2.J$0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L8c
        L31:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L39:
            long r3 = r2.J$1
            long r5 = r2.J$0
            java.lang.Object r7 = r2.L$0
            androidx.compose.ui.input.nestedscroll.NestedScrollNode r7 = (androidx.compose.ui.input.nestedscroll.NestedScrollNode) r7
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = r3
            r11 = r5
            goto L65
        L47:
            kotlin.ResultKt.throwOnFailure(r1)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r3 = r0.connection
            r2.L$0 = r0
            r11 = r16
            r2.J$0 = r11
            r13 = r18
            r2.J$1 = r13
            r2.label = r4
            r4 = r16
            r6 = r18
            r8 = r2
            java.lang.Object r1 = r3.mo216onPostFlingRZ2iAVY(r4, r6, r8)
            if (r1 != r9) goto L64
            return r9
        L64:
            r7 = r0
        L65:
            androidx.compose.ui.unit.Velocity r1 = (androidx.compose.ui.unit.Velocity) r1
            long r4 = r1.m2523unboximpl()
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r3 = r7.getParentConnection()
            if (r3 == 0) goto L94
            long r6 = androidx.compose.ui.unit.Velocity.m2520plusAH228Gc(r11, r4)
            long r11 = androidx.compose.ui.unit.Velocity.m2519minusAH228Gc(r13, r4)
            r1 = 0
            r2.L$0 = r1
            r2.J$0 = r4
            r2.label = r10
            r13 = r4
            r4 = r6
            r6 = r11
            r8 = r2
            java.lang.Object r1 = r3.mo216onPostFlingRZ2iAVY(r4, r6, r8)
            if (r1 != r9) goto L8b
            return r9
        L8b:
            r2 = r13
        L8c:
            androidx.compose.ui.unit.Velocity r1 = (androidx.compose.ui.unit.Velocity) r1
            long r4 = r1.m2523unboximpl()
            r13 = r2
            goto L9b
        L94:
            r13 = r4
            androidx.compose.ui.unit.Velocity$Companion r1 = androidx.compose.ui.unit.Velocity.Companion
            long r4 = r1.m2524getZero9UxMQ8M()
        L9b:
            long r1 = androidx.compose.ui.unit.Velocity.m2520plusAH228Gc(r13, r4)
            androidx.compose.ui.unit.Velocity r1 = androidx.compose.ui.unit.Velocity.m2510boximpl(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.NestedScrollNode.mo216onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        updateDispatcherFields();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        resetDispatcherFields();
    }

    private final void updateDispatcherFields() {
        this.resolvedDispatcher.setNestedScrollNode$ui_release(this);
        this.resolvedDispatcher.setCalculateNestedScrollScope$ui_release(new Function0() { // from class: androidx.compose.ui.input.nestedscroll.NestedScrollNode.updateDispatcherFields.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CoroutineScope invoke() {
                return NestedScrollNode.this.getNestedCoroutineScope();
            }
        });
        this.resolvedDispatcher.setScope$ui_release(getCoroutineScope());
    }

    private final void resetDispatcherFields() {
        if (this.resolvedDispatcher.getNestedScrollNode$ui_release() == this) {
            this.resolvedDispatcher.setNestedScrollNode$ui_release(null);
        }
    }
}
