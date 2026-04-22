package androidx.compose.runtime;

import androidx.compose.runtime.internal.ThreadMap;
import androidx.compose.runtime.internal.ThreadMap_jvmKt;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;

/* JADX INFO: compiled from: ActualJvm.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SnapshotThreadLocal {
    private Object mainThreadValue;
    private final AtomicReference map = new AtomicReference(ThreadMap_jvmKt.getEmptyThreadMap());
    private final Object writeMutex = new Object();

    public final Object get() {
        long id = Thread.currentThread().getId();
        if (id == ActualAndroid_androidKt.getMainThreadId()) {
            return this.mainThreadValue;
        }
        return ((ThreadMap) this.map.get()).get(id);
    }

    public final void set(Object obj) {
        long id = Thread.currentThread().getId();
        if (id == ActualAndroid_androidKt.getMainThreadId()) {
            this.mainThreadValue = obj;
            return;
        }
        synchronized (this.writeMutex) {
            ThreadMap threadMap = (ThreadMap) this.map.get();
            if (threadMap.trySet(id, obj)) {
                return;
            }
            this.map.set(threadMap.newWith(id, obj));
            Unit unit = Unit.INSTANCE;
        }
    }
}
