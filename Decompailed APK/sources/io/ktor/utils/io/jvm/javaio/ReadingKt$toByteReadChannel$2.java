package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.IOException;
import java.io.InputStream;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Reading.kt */
/* JADX INFO: loaded from: classes.dex */
final class ReadingKt$toByteReadChannel$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ ObjectPool $pool;
    final /* synthetic */ InputStream $this_toByteReadChannel;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ReadingKt$toByteReadChannel$2(ObjectPool objectPool, InputStream inputStream, Continuation continuation) {
        super(2, continuation);
        this.$pool = objectPool;
        this.$this_toByteReadChannel = inputStream;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ReadingKt$toByteReadChannel$2 readingKt$toByteReadChannel$2 = new ReadingKt$toByteReadChannel$2(this.$pool, this.$this_toByteReadChannel, continuation);
        readingKt$toByteReadChannel$2.L$0 = obj;
        return readingKt$toByteReadChannel$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WriterScope writerScope, Continuation continuation) {
        return ((ReadingKt$toByteReadChannel$2) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws IOException {
        byte[] bArr;
        WriterScope writerScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            WriterScope writerScope2 = (WriterScope) this.L$0;
            bArr = (byte[]) this.$pool.borrow();
            writerScope = writerScope2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            bArr = (byte[]) this.L$1;
            writerScope = (WriterScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                try {
                    writerScope.getChannel().close(th);
                    this.$pool.recycle(bArr);
                } catch (Throwable th2) {
                    this.$pool.recycle(bArr);
                    this.$this_toByteReadChannel.close();
                    throw th2;
                }
            }
        }
        while (true) {
            int i2 = this.$this_toByteReadChannel.read(bArr, 0, bArr.length);
            if (i2 < 0) {
                this.$pool.recycle(bArr);
                break;
            }
            if (i2 != 0) {
                ByteWriteChannel channel = writerScope.getChannel();
                this.L$0 = writerScope;
                this.L$1 = bArr;
                this.label = 1;
                if (channel.writeFully(bArr, 0, i2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        this.$this_toByteReadChannel.close();
        return Unit.INSTANCE;
    }
}
