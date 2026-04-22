package io.ktor.utils.io;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: ByteReadChannelJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ByteReadChannel {
    public static final Companion Companion = Companion.$$INSTANCE;

    boolean cancel(Throwable th);

    int getAvailableForRead();

    Throwable getClosedCause();

    boolean isClosedForRead();

    Object readAvailable(ChunkBuffer chunkBuffer, Continuation continuation);

    Object readAvailable(byte[] bArr, int i, int i2, Continuation continuation);

    Object readRemaining(long j, Continuation continuation);

    /* JADX INFO: compiled from: ByteReadChannelJVM.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ Object readRemaining$default(ByteReadChannel byteReadChannel, long j, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readRemaining");
            }
            if ((i & 1) != 0) {
                j = Preferences.UPDATE_INTERVAL_DISABLED;
            }
            return byteReadChannel.readRemaining(j, continuation);
        }
    }

    /* JADX INFO: compiled from: ByteReadChannelJVM.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Lazy Empty$delegate = LazyKt.lazy(new Function0() { // from class: io.ktor.utils.io.ByteReadChannel$Companion$Empty$2
            @Override // kotlin.jvm.functions.Function0
            public final ByteChannel invoke() {
                ByteChannel byteChannelByteChannel$default = ByteChannelKt.ByteChannel$default(false, 1, null);
                ByteWriteChannelKt.close(byteChannelByteChannel$default);
                return byteChannelByteChannel$default;
            }
        });

        private Companion() {
        }

        public final ByteReadChannel getEmpty() {
            return (ByteReadChannel) Empty$delegate.getValue();
        }
    }
}
