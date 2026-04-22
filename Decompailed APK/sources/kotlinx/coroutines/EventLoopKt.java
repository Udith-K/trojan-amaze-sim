package kotlinx.coroutines;

import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: EventLoop.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class EventLoopKt {
    public static final EventLoop createEventLoop() {
        return new BlockingEventLoop(Thread.currentThread());
    }

    public static final long processNextEventInCurrentThread() {
        EventLoop eventLoopCurrentOrNull$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
        return eventLoopCurrentOrNull$kotlinx_coroutines_core != null ? eventLoopCurrentOrNull$kotlinx_coroutines_core.processNextEvent() : Preferences.UPDATE_INTERVAL_DISABLED;
    }
}
