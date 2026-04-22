package kotlinx.coroutines.sync;

import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: Mutex.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface Mutex {
    Object lock(Object obj, Continuation continuation);

    void unlock(Object obj);

    /* JADX INFO: compiled from: Mutex.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ Object lock$default(Mutex mutex, Object obj, Continuation continuation, int i, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lock");
            }
            if ((i & 1) != 0) {
                obj = null;
            }
            return mutex.lock(obj, continuation);
        }

        public static /* synthetic */ void unlock$default(Mutex mutex, Object obj, int i, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: unlock");
            }
            if ((i & 1) != 0) {
                obj = null;
            }
            mutex.unlock(obj);
        }
    }
}
