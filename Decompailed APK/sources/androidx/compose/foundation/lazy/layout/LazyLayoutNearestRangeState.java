package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LazyLayoutNearestRangeState.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyLayoutNearestRangeState implements State {
    private static final Companion Companion = new Companion(null);
    private final int extraItemCount;
    private int lastFirstVisibleItem;
    private final int slidingWindowSize;
    private final MutableState value$delegate;

    public LazyLayoutNearestRangeState(int i, int i2, int i3) {
        this.slidingWindowSize = i2;
        this.extraItemCount = i3;
        this.value$delegate = SnapshotStateKt.mutableStateOf(Companion.calculateNearestItemsRange(i, i2, i3), SnapshotStateKt.structuralEqualityPolicy());
        this.lastFirstVisibleItem = i;
    }

    private void setValue(IntRange intRange) {
        this.value$delegate.setValue(intRange);
    }

    @Override // androidx.compose.runtime.State
    public IntRange getValue() {
        return (IntRange) this.value$delegate.getValue();
    }

    public final void update(int i) {
        if (i != this.lastFirstVisibleItem) {
            this.lastFirstVisibleItem = i;
            setValue(Companion.calculateNearestItemsRange(i, this.slidingWindowSize, this.extraItemCount));
        }
    }

    /* JADX INFO: compiled from: LazyLayoutNearestRangeState.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final IntRange calculateNearestItemsRange(int i, int i2, int i3) {
            int i4 = (i / i2) * i2;
            return RangesKt.until(Math.max(i4 - i3, 0), i4 + i2 + i3);
        }
    }
}
