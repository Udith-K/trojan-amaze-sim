package io.ktor.utils.io.jvm.javaio;

import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Blocking.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InputAdapter$loop$1 extends BlockingAdapter {
    final /* synthetic */ InputAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    InputAdapter$loop$1(Job job, InputAdapter inputAdapter) {
        super(job);
        this.this$0 = inputAdapter;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0083 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0081 -> B:25:0x0084). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // io.ktor.utils.io.jvm.javaio.BlockingAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object loop(kotlin.coroutines.Continuation r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1$loop$1
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1$loop$1 r0 = (io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1$loop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1$loop$1 r0 = new io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1$loop$1
            r0.<init>(r9, r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L44
            if (r2 == r4) goto L38
            if (r2 != r3) goto L30
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1 r2 = (io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L84
        L30:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L38:
            java.lang.Object r2 = r0.L$1
            io.ktor.utils.io.jvm.javaio.BlockingAdapter r2 = (io.ktor.utils.io.jvm.javaio.BlockingAdapter) r2
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1 r2 = (io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L61
        L44:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = 0
            r2 = r9
        L49:
            r2.result = r10
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r4
            java.lang.Object r10 = io.ktor.utils.io.jvm.javaio.BlockingAdapter.access$rendezvousBlock(r2, r0)
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r10 != r5) goto L5e
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L5e:
            if (r10 != r1) goto L61
            return r1
        L61:
            java.lang.String r5 = "null cannot be cast to non-null type kotlin.ByteArray"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10, r5)
            byte[] r10 = (byte[]) r10
            io.ktor.utils.io.jvm.javaio.InputAdapter r5 = r2.this$0
            io.ktor.utils.io.ByteReadChannel r5 = io.ktor.utils.io.jvm.javaio.InputAdapter.access$getChannel$p(r5)
            int r6 = r2.getOffset()
            int r7 = r2.getLength()
            r0.L$0 = r2
            r8 = 0
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r10 = r5.readAvailable(r10, r6, r7, r0)
            if (r10 != r1) goto L84
            return r1
        L84:
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
            r5 = -1
            if (r10 != r5) goto L49
            io.ktor.utils.io.jvm.javaio.InputAdapter r0 = r2.this$0
            kotlinx.coroutines.CompletableJob r0 = io.ktor.utils.io.jvm.javaio.InputAdapter.access$getContext$p(r0)
            r0.complete()
            r2.finish(r10)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1.loop(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
