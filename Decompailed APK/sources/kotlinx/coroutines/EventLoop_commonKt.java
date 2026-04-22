package kotlinx.coroutines;

import kotlinx.coroutines.internal.Symbol;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: EventLoop.common.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class EventLoop_commonKt {
    private static final Symbol DISPOSED_TASK = new Symbol("REMOVED_TASK");
    private static final Symbol CLOSED_EMPTY = new Symbol("CLOSED_EMPTY");

    public static final long delayToNanos(long j) {
        if (j <= 0) {
            return 0L;
        }
        return j >= 9223372036854L ? Preferences.UPDATE_INTERVAL_DISABLED : 1000000 * j;
    }
}
