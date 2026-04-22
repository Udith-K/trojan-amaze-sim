package androidx.compose.runtime;

import android.os.Looper;
import android.util.Log;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: ActualAndroid.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ActualAndroid_androidKt {
    private static final Lazy DefaultMonotonicFrameClock$delegate = LazyKt.lazy(new Function0() { // from class: androidx.compose.runtime.ActualAndroid_androidKt$DefaultMonotonicFrameClock$2
        @Override // kotlin.jvm.functions.Function0
        public final MonotonicFrameClock invoke() {
            return Looper.getMainLooper() != null ? DefaultChoreographerFrameClock.INSTANCE : SdkStubsFallbackFrameClock.INSTANCE;
        }
    });
    private static final long MainThreadId;

    static {
        long id;
        try {
            id = Looper.getMainLooper().getThread().getId();
        } catch (Exception unused) {
            id = -1;
        }
        MainThreadId = id;
    }

    public static final SnapshotMutableState createSnapshotMutableState(Object obj, SnapshotMutationPolicy snapshotMutationPolicy) {
        return new ParcelableSnapshotMutableState(obj, snapshotMutationPolicy);
    }

    public static final MutableIntState createSnapshotMutableIntState(int i) {
        return new ParcelableSnapshotMutableIntState(i);
    }

    public static final MutableLongState createSnapshotMutableLongState(long j) {
        return new ParcelableSnapshotMutableLongState(j);
    }

    public static final MutableFloatState createSnapshotMutableFloatState(float f) {
        return new ParcelableSnapshotMutableFloatState(f);
    }

    public static final void logError(String str, Throwable th) {
        Log.e("ComposeInternal", str, th);
    }

    public static final long getMainThreadId() {
        return MainThreadId;
    }
}
