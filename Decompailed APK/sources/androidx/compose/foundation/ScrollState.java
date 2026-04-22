package androidx.compose.foundation;

import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.runtime.snapshots.Snapshot;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Scroll.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ScrollState implements ScrollableState {
    public static final Companion Companion = new Companion(null);
    private static final Saver Saver = SaverKt.Saver(new Function2() { // from class: androidx.compose.foundation.ScrollState$Companion$Saver$1
        @Override // kotlin.jvm.functions.Function2
        public final Integer invoke(SaverScope saverScope, ScrollState scrollState) {
            return Integer.valueOf(scrollState.getValue());
        }
    }, new Function1() { // from class: androidx.compose.foundation.ScrollState$Companion$Saver$2
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final ScrollState invoke(int i) {
            return new ScrollState(i);
        }
    });
    private float accumulator;
    private final MutableIntState value$delegate;
    private final MutableIntState viewportSize$delegate = SnapshotIntStateKt.mutableIntStateOf(0);
    private final MutableInteractionSource internalInteractionSource = InteractionSourceKt.MutableInteractionSource();
    private MutableIntState _maxValueState = SnapshotIntStateKt.mutableIntStateOf(Integer.MAX_VALUE);
    private final ScrollableState scrollableState = ScrollableStateKt.ScrollableState(new Function1() { // from class: androidx.compose.foundation.ScrollState$scrollableState$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).floatValue());
        }

        public final Float invoke(float f) {
            float value = this.this$0.getValue() + f + this.this$0.accumulator;
            float fCoerceIn = RangesKt.coerceIn(value, 0.0f, this.this$0.getMaxValue());
            boolean z = value == fCoerceIn;
            float value2 = fCoerceIn - this.this$0.getValue();
            int iRound = Math.round(value2);
            ScrollState scrollState = this.this$0;
            scrollState.setValue(scrollState.getValue() + iRound);
            this.this$0.accumulator = value2 - iRound;
            if (!z) {
                f = value2;
            }
            return Float.valueOf(f);
        }
    });
    private final State canScrollForward$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.ScrollState$canScrollForward$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(this.this$0.getValue() < this.this$0.getMaxValue());
        }
    });
    private final State canScrollBackward$delegate = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.ScrollState$canScrollBackward$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(this.this$0.getValue() > 0);
        }
    });

    public ScrollState(int i) {
        this.value$delegate = SnapshotIntStateKt.mutableIntStateOf(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setValue(int i) {
        this.value$delegate.setIntValue(i);
    }

    public final int getValue() {
        return this.value$delegate.getIntValue();
    }

    public final int getMaxValue() {
        return this._maxValueState.getIntValue();
    }

    public final void setMaxValue$foundation_release(int i) {
        this._maxValueState.setIntValue(i);
        Snapshot.Companion companion = Snapshot.Companion;
        Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            if (getValue() > i) {
                setValue(i);
            }
            Unit unit = Unit.INSTANCE;
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
        } catch (Throwable th) {
            companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
            throw th;
        }
    }

    public final void setViewportSize$foundation_release(int i) {
        this.viewportSize$delegate.setIntValue(i);
    }

    public final MutableInteractionSource getInternalInteractionSource$foundation_release() {
        return this.internalInteractionSource;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public Object scroll(MutatePriority mutatePriority, Function2 function2, Continuation continuation) {
        Object objScroll = this.scrollableState.scroll(mutatePriority, function2, continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public float dispatchRawDelta(float f) {
        return this.scrollableState.dispatchRawDelta(f);
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean isScrollInProgress() {
        return this.scrollableState.isScrollInProgress();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getCanScrollForward() {
        return ((Boolean) this.canScrollForward$delegate.getValue()).booleanValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public boolean getCanScrollBackward() {
        return ((Boolean) this.canScrollBackward$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: compiled from: Scroll.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver getSaver() {
            return ScrollState.Saver;
        }
    }
}
