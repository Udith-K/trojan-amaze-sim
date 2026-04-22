package io.ktor.utils.io;

/* JADX INFO: compiled from: ByteBufferChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ByteBufferChannelKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Void rethrowClosed(Throwable th) throws Throwable {
        Throwable thTryCopyException;
        try {
            thTryCopyException = ExceptionUtilsJvmKt.tryCopyException(th, th);
        } catch (Throwable unused) {
            thTryCopyException = null;
        }
        if (thTryCopyException == null) {
            throw th;
        }
        throw thTryCopyException;
    }
}
