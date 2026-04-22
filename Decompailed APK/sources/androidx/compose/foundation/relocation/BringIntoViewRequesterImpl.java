package androidx.compose.foundation.relocation;

import androidx.compose.runtime.collection.MutableVector;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: BringIntoViewRequester.kt */
/* JADX INFO: loaded from: classes.dex */
final class BringIntoViewRequesterImpl implements BringIntoViewRequester {
    private final MutableVector modifiers = new MutableVector(new BringIntoViewRequesterNode[16], 0);

    /* JADX INFO: renamed from: androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1, reason: invalid class name */
    /* JADX INFO: compiled from: BringIntoViewRequester.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BringIntoViewRequesterImpl.this.bringIntoView(null, this);
        }
    }

    public final MutableVector getModifiers() {
        return this.modifiers;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0068, code lost:
    
        if (r8 < r2) goto L17;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0064 -> B:20:0x0067). Please report as a decompilation issue!!! */
    @Override // androidx.compose.foundation.relocation.BringIntoViewRequester
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object bringIntoView(androidx.compose.ui.geometry.Rect r8, kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof androidx.compose.foundation.relocation.BringIntoViewRequesterImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1 r0 = (androidx.compose.foundation.relocation.BringIntoViewRequesterImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1 r0 = new androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3e
            if (r2 != r3) goto L36
            int r8 = r0.I$1
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$1
            java.lang.Object[] r4 = (java.lang.Object[]) r4
            java.lang.Object r5 = r0.L$0
            androidx.compose.ui.geometry.Rect r5 = (androidx.compose.ui.geometry.Rect) r5
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r5
            goto L67
        L36:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3e:
            kotlin.ResultKt.throwOnFailure(r9)
            androidx.compose.runtime.collection.MutableVector r9 = r7.modifiers
            int r2 = r9.getSize()
            if (r2 <= 0) goto L6a
            java.lang.Object[] r9 = r9.getContent()
            r4 = 0
            r6 = r9
            r9 = r8
            r8 = r4
            r4 = r6
        L52:
            r5 = r4[r8]
            androidx.compose.foundation.relocation.BringIntoViewRequesterNode r5 = (androidx.compose.foundation.relocation.BringIntoViewRequesterNode) r5
            r0.L$0 = r9
            r0.L$1 = r4
            r0.I$0 = r2
            r0.I$1 = r8
            r0.label = r3
            java.lang.Object r5 = androidx.compose.foundation.relocation.ScrollIntoView.scrollIntoView(r5, r9, r0)
            if (r5 != r1) goto L67
            return r1
        L67:
            int r8 = r8 + r3
            if (r8 < r2) goto L52
        L6a:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.relocation.BringIntoViewRequesterImpl.bringIntoView(androidx.compose.ui.geometry.Rect, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
