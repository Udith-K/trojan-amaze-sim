package io.ktor.client.utils;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: ByteChannelUtils.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ByteChannelUtilsKt {

    /* JADX INFO: renamed from: io.ktor.client.utils.ByteChannelUtilsKt$observable$1, reason: invalid class name */
    /* JADX INFO: compiled from: ByteChannelUtils.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Long $contentLength;
        final /* synthetic */ Function3 $listener;
        final /* synthetic */ ByteReadChannel $this_observable;
        int I$0;
        long J$0;
        long J$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Long l, ByteReadChannel byteReadChannel, Function3 function3, Continuation continuation) {
            super(2, continuation);
            this.$contentLength = l;
            this.$this_observable = byteReadChannel;
            this.$listener = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$contentLength, this.$this_observable, this.$listener, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(WriterScope writerScope, Continuation continuation) {
            return ((AnonymousClass1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00eb A[Catch: all -> 0x0022, TryCatch #1 {all -> 0x0022, blocks: (B:9:0x001d, B:63:0x01a4, B:39:0x00e5, B:41:0x00eb, B:44:0x0104, B:56:0x0172, B:60:0x0185), top: B:70:0x000c }] */
        /* JADX WARN: Removed duplicated region for block: B:46:0x012a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:47:0x012b  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x015d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:52:0x015e  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0172 A[Catch: all -> 0x0022, TRY_ENTER, TryCatch #1 {all -> 0x0022, blocks: (B:9:0x001d, B:63:0x01a4, B:39:0x00e5, B:41:0x00eb, B:44:0x0104, B:56:0x0172, B:60:0x0185), top: B:70:0x000c }] */
        /* JADX WARN: Type inference failed for: r2v0, types: [int] */
        /* JADX WARN: Type inference failed for: r2v1 */
        /* JADX WARN: Type inference failed for: r2v15, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v17 */
        /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v20 */
        /* JADX WARN: Type inference failed for: r2v23 */
        /* JADX WARN: Type inference failed for: r2v27 */
        /* JADX WARN: Type inference failed for: r2v28 */
        /* JADX WARN: Type inference failed for: r2v29 */
        /* JADX WARN: Type inference failed for: r2v30 */
        /* JADX WARN: Type inference failed for: r2v5 */
        /* JADX WARN: Type inference failed for: r2v9 */
        /* JADX WARN: Type inference failed for: r3v1, types: [io.ktor.utils.io.pool.ObjectPool] */
        /* JADX WARN: Type inference failed for: r3v10, types: [io.ktor.utils.io.pool.ObjectPool] */
        /* JADX WARN: Type inference failed for: r3v11 */
        /* JADX WARN: Type inference failed for: r3v12 */
        /* JADX WARN: Type inference failed for: r3v14 */
        /* JADX WARN: Type inference failed for: r3v17 */
        /* JADX WARN: Type inference failed for: r3v18 */
        /* JADX WARN: Type inference failed for: r3v2, types: [io.ktor.utils.io.pool.ObjectPool] */
        /* JADX WARN: Type inference failed for: r3v21 */
        /* JADX WARN: Type inference failed for: r3v22 */
        /* JADX WARN: Type inference failed for: r3v23 */
        /* JADX WARN: Type inference failed for: r3v24 */
        /* JADX WARN: Type inference failed for: r3v25 */
        /* JADX WARN: Type inference failed for: r3v26 */
        /* JADX WARN: Type inference failed for: r3v27 */
        /* JADX WARN: Type inference failed for: r3v4 */
        /* JADX WARN: Type inference failed for: r3v5 */
        /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v9 */
        /* JADX WARN: Type inference failed for: r6v10 */
        /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v5 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x015e -> B:53:0x016b). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 432
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.utils.ByteChannelUtilsKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final ByteReadChannel observable(ByteReadChannel byteReadChannel, CoroutineContext context, Long l, Function3 listener) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        return CoroutinesKt.writer(GlobalScope.INSTANCE, context, true, new AnonymousClass1(l, byteReadChannel, listener, null)).getChannel();
    }
}
