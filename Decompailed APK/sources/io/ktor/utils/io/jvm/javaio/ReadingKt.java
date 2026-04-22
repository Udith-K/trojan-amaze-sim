package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.pool.ByteArrayPoolKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.InputStream;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: Reading.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ReadingKt {
    public static /* synthetic */ ByteReadChannel toByteReadChannelWithArrayPool$default(InputStream inputStream, CoroutineContext coroutineContext, ObjectPool objectPool, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getIO();
        }
        if ((i & 2) != 0) {
            objectPool = ByteArrayPoolKt.getByteArrayPool();
        }
        return toByteReadChannelWithArrayPool(inputStream, coroutineContext, objectPool);
    }

    public static final ByteReadChannel toByteReadChannelWithArrayPool(InputStream inputStream, CoroutineContext context, ObjectPool pool) {
        Intrinsics.checkNotNullParameter(inputStream, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pool, "pool");
        return CoroutinesKt.writer(GlobalScope.INSTANCE, context, true, new ReadingKt$toByteReadChannel$2(pool, inputStream, null)).getChannel();
    }
}
