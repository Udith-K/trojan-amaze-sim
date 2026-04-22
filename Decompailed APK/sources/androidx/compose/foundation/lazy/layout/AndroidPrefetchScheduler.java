package androidx.compose.foundation.lazy.layout;

import android.view.Choreographer;
import android.view.View;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.collection.MutableVector;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PrefetchScheduler.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidPrefetchScheduler implements PrefetchScheduler, RememberObserver, Runnable, Choreographer.FrameCallback {
    private static long frameIntervalNs;
    private long frameStartTimeNanos;
    private boolean isActive;
    private boolean prefetchScheduled;
    private final View view;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private final MutableVector prefetchRequests = new MutableVector(new PrefetchRequest[16], 0);
    private final Choreographer choreographer = Choreographer.getInstance();

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
    }

    public AndroidPrefetchScheduler(View view) {
        this.view = view;
        Companion.calculateFrameIntervalIfNeeded(view);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.prefetchRequests.isEmpty() || !this.prefetchScheduled || !this.isActive || this.view.getWindowVisibility() != 0) {
            this.prefetchScheduled = false;
            return;
        }
        PrefetchRequestScopeImpl prefetchRequestScopeImpl = new PrefetchRequestScopeImpl(this.frameStartTimeNanos + frameIntervalNs);
        boolean z = false;
        while (this.prefetchRequests.isNotEmpty() && !z) {
            if (prefetchRequestScopeImpl.availableTimeNanos() <= 0 || ((PrefetchRequest) this.prefetchRequests.getContent()[0]).execute(prefetchRequestScopeImpl)) {
                z = true;
            } else {
                this.prefetchRequests.removeAt(0);
            }
        }
        if (z) {
            this.choreographer.postFrameCallback(this);
        } else {
            this.prefetchScheduled = false;
        }
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        if (this.isActive) {
            this.frameStartTimeNanos = j;
            this.view.post(this);
        }
    }

    @Override // androidx.compose.foundation.lazy.layout.PrefetchScheduler
    public void schedulePrefetch(PrefetchRequest prefetchRequest) {
        this.prefetchRequests.add(prefetchRequest);
        if (this.prefetchScheduled) {
            return;
        }
        this.prefetchScheduled = true;
        this.view.post(this);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        this.isActive = true;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        this.isActive = false;
        this.view.removeCallbacks(this);
        this.choreographer.removeFrameCallback(this);
    }

    /* JADX INFO: compiled from: PrefetchScheduler.android.kt */
    public static final class PrefetchRequestScopeImpl implements PrefetchRequestScope {
        private final long nextFrameTimeNs;

        public PrefetchRequestScopeImpl(long j) {
            this.nextFrameTimeNs = j;
        }

        @Override // androidx.compose.foundation.lazy.layout.PrefetchRequestScope
        public long availableTimeNanos() {
            return Math.max(0L, this.nextFrameTimeNs - System.nanoTime());
        }
    }

    /* JADX INFO: compiled from: PrefetchScheduler.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void calculateFrameIntervalIfNeeded(android.view.View r5) {
            /*
                r4 = this;
                long r0 = androidx.compose.foundation.lazy.layout.AndroidPrefetchScheduler.access$getFrameIntervalNs$cp()
                r2 = 0
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r0 != 0) goto L2c
                android.view.Display r0 = r5.getDisplay()
                boolean r5 = r5.isInEditMode()
                if (r5 != 0) goto L21
                if (r0 == 0) goto L21
                float r5 = r0.getRefreshRate()
                r0 = 1106247680(0x41f00000, float:30.0)
                int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r0 < 0) goto L21
                goto L23
            L21:
                r5 = 1114636288(0x42700000, float:60.0)
            L23:
                r0 = 1000000000(0x3b9aca00, float:0.0047237873)
                float r0 = (float) r0
                float r0 = r0 / r5
                long r0 = (long) r0
                androidx.compose.foundation.lazy.layout.AndroidPrefetchScheduler.access$setFrameIntervalNs$cp(r0)
            L2c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.AndroidPrefetchScheduler.Companion.calculateFrameIntervalIfNeeded(android.view.View):void");
        }
    }
}
