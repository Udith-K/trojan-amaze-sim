package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;

/* JADX INFO: compiled from: CoroutineExceptionHandler.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface CoroutineExceptionHandler extends CoroutineContext.Element {
    public static final Key Key = Key.$$INSTANCE;

    void handleException(CoroutineContext coroutineContext, Throwable th);

    /* JADX INFO: compiled from: CoroutineExceptionHandler.kt */
    public static final class Key implements CoroutineContext.Key {
        static final /* synthetic */ Key $$INSTANCE = new Key();

        private Key() {
        }
    }
}
