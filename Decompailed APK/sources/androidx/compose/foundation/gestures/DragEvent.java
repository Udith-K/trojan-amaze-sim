package androidx.compose.foundation.gestures;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Draggable.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DragEvent {
    public /* synthetic */ DragEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: Draggable.kt */
    public static final class DragStarted extends DragEvent {
        private final long startPoint;

        public /* synthetic */ DragStarted(long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(j);
        }

        private DragStarted(long j) {
            super(null);
            this.startPoint = j;
        }

        /* JADX INFO: renamed from: getStartPoint-F1C5BW0, reason: not valid java name */
        public final long m177getStartPointF1C5BW0() {
            return this.startPoint;
        }
    }

    private DragEvent() {
    }

    /* JADX INFO: compiled from: Draggable.kt */
    public static final class DragStopped extends DragEvent {
        private final long velocity;

        public /* synthetic */ DragStopped(long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(j);
        }

        private DragStopped(long j) {
            super(null);
            this.velocity = j;
        }

        /* JADX INFO: renamed from: getVelocity-9UxMQ8M, reason: not valid java name */
        public final long m178getVelocity9UxMQ8M() {
            return this.velocity;
        }
    }

    /* JADX INFO: compiled from: Draggable.kt */
    public static final class DragCancelled extends DragEvent {
        public static final DragCancelled INSTANCE = new DragCancelled();

        private DragCancelled() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: Draggable.kt */
    public static final class DragDelta extends DragEvent {
        private final long delta;

        public /* synthetic */ DragDelta(long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(j);
        }

        private DragDelta(long j) {
            super(null);
            this.delta = j;
        }

        /* JADX INFO: renamed from: getDelta-F1C5BW0, reason: not valid java name */
        public final long m176getDeltaF1C5BW0() {
            return this.delta;
        }
    }
}
