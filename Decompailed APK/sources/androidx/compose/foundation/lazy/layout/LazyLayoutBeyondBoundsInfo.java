package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.collection.MutableVector;
import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: LazyLayoutBeyondBoundsInfo.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyLayoutBeyondBoundsInfo {
    public static final int $stable = MutableVector.$stable;
    private final MutableVector beyondBoundsItems = new MutableVector(new Interval[16], 0);

    public final Interval addInterval(int i, int i2) {
        Interval interval = new Interval(i, i2);
        this.beyondBoundsItems.add(interval);
        return interval;
    }

    public final void removeInterval(Interval interval) {
        this.beyondBoundsItems.remove(interval);
    }

    public final boolean hasIntervals() {
        return this.beyondBoundsItems.isNotEmpty();
    }

    public final int getStart() {
        int start = ((Interval) this.beyondBoundsItems.first()).getStart();
        MutableVector mutableVector = this.beyondBoundsItems;
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                Interval interval = (Interval) content[i];
                if (interval.getStart() < start) {
                    start = interval.getStart();
                }
                i++;
            } while (i < size);
        }
        if (start >= 0) {
            return start;
        }
        throw new IllegalArgumentException("negative minIndex");
    }

    public final int getEnd() {
        int end = ((Interval) this.beyondBoundsItems.first()).getEnd();
        MutableVector mutableVector = this.beyondBoundsItems;
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                Interval interval = (Interval) content[i];
                if (interval.getEnd() > end) {
                    end = interval.getEnd();
                }
                i++;
            } while (i < size);
        }
        return end;
    }

    /* JADX INFO: compiled from: LazyLayoutBeyondBoundsInfo.kt */
    public static final class Interval {
        private final int end;
        private final int start;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Interval)) {
                return false;
            }
            Interval interval = (Interval) obj;
            return this.start == interval.start && this.end == interval.end;
        }

        public int hashCode() {
            return (this.start * 31) + this.end;
        }

        public String toString() {
            return "Interval(start=" + this.start + ", end=" + this.end + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public Interval(int i, int i2) {
            this.start = i;
            this.end = i2;
            if (i < 0) {
                throw new IllegalArgumentException("negative start index");
            }
            if (i2 < i) {
                throw new IllegalArgumentException("end index greater than start");
            }
        }

        public final int getStart() {
            return this.start;
        }

        public final int getEnd() {
            return this.end;
        }
    }
}
