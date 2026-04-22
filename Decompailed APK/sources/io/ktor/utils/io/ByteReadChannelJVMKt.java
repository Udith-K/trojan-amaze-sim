package io.ktor.utils.io;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: ByteReadChannelJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ByteReadChannelJVMKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteReadChannelJVMKt$copyToImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: ByteReadChannelJVM.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteReadChannelJVMKt.copyToImpl(null, null, 0L, this);
        }
    }

    public static final Object copyTo(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j, Continuation continuation) {
        if (byteReadChannel == byteWriteChannel) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (j == 0) {
            return Boxing.boxLong(0L);
        }
        if ((byteReadChannel instanceof ByteBufferChannel) && (byteWriteChannel instanceof ByteBufferChannel)) {
            return ((ByteBufferChannel) byteWriteChannel).copyDirect$ktor_io((ByteBufferChannel) byteReadChannel, j, null, continuation);
        }
        return copyToImpl(byteReadChannel, byteWriteChannel, j, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008f A[Catch: all -> 0x00f2, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x00f2, blocks: (B:37:0x00e8, B:39:0x00ee, B:25:0x008f), top: B:54:0x00e8 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c4 A[Catch: all -> 0x0048, TRY_LEAVE, TryCatch #2 {all -> 0x0048, blocks: (B:13:0x0040, B:29:0x00bb, B:31:0x00c4, B:46:0x0101, B:20:0x0067), top: B:58:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v3, types: [int] */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v4, types: [int] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00db -> B:35:0x00e4). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object copyToImpl(io.ktor.utils.io.ByteReadChannel r19, io.ktor.utils.io.ByteWriteChannel r20, long r21, kotlin.coroutines.Continuation r23) {
        /*
            Method dump skipped, instruction units count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteReadChannelJVMKt.copyToImpl(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
