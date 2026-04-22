package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.layout.BeyondBoundsLayoutKt;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.KotlinNothingValueException;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref$ObjectRef;

/* JADX INFO: compiled from: LazyLayoutBeyondBoundsModifierLocal.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyLayoutBeyondBoundsModifierLocal implements ModifierLocalProvider, BeyondBoundsLayout {
    public static final Companion Companion = new Companion(null);
    private static final LazyLayoutBeyondBoundsModifierLocal$Companion$emptyBeyondBoundsScope$1 emptyBeyondBoundsScope = new BeyondBoundsLayout.BeyondBoundsScope() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocal$Companion$emptyBeyondBoundsScope$1
        private final boolean hasMoreContent;

        @Override // androidx.compose.ui.layout.BeyondBoundsLayout.BeyondBoundsScope
        public boolean getHasMoreContent() {
            return this.hasMoreContent;
        }
    };
    private final LazyLayoutBeyondBoundsInfo beyondBoundsInfo;
    private final LayoutDirection layoutDirection;
    private final Orientation orientation;
    private final boolean reverseLayout;
    private final LazyLayoutBeyondBoundsState state;

    /* JADX INFO: compiled from: LazyLayoutBeyondBoundsModifierLocal.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ boolean all(Function1 function1) {
        return ((Boolean) function1.invoke(this)).booleanValue();
    }

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ Object foldIn(Object obj, Function2 function2) {
        return function2.invoke(obj, this);
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalProvider
    public BeyondBoundsLayout getValue() {
        return this;
    }

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ Modifier then(Modifier modifier) {
        return Modifier.CC.$default$then(this, modifier);
    }

    public LazyLayoutBeyondBoundsModifierLocal(LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsState, LazyLayoutBeyondBoundsInfo lazyLayoutBeyondBoundsInfo, boolean z, LayoutDirection layoutDirection, Orientation orientation) {
        this.state = lazyLayoutBeyondBoundsState;
        this.beyondBoundsInfo = lazyLayoutBeyondBoundsInfo;
        this.reverseLayout = z;
        this.layoutDirection = layoutDirection;
        this.orientation = orientation;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalProvider
    public ProvidableModifierLocal getKey() {
        return BeyondBoundsLayoutKt.getModifierLocalBeyondBoundsLayout();
    }

    /* JADX INFO: compiled from: LazyLayoutBeyondBoundsModifierLocal.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // androidx.compose.ui.layout.BeyondBoundsLayout
    /* JADX INFO: renamed from: layout-o7g1Pn8, reason: not valid java name */
    public Object mo330layouto7g1Pn8(final int i, Function1 function1) {
        int firstPlacedIndex;
        if (this.state.getItemCount() <= 0 || !this.state.getHasVisibleItems()) {
            return function1.invoke(emptyBeyondBoundsScope);
        }
        if (m328isForward4vf7U8o(i)) {
            firstPlacedIndex = this.state.getLastPlacedIndex();
        } else {
            firstPlacedIndex = this.state.getFirstPlacedIndex();
        }
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = this.beyondBoundsInfo.addInterval(firstPlacedIndex, firstPlacedIndex);
        Object objInvoke = null;
        while (objInvoke == null && m327hasMoreContentFR3nfPY((LazyLayoutBeyondBoundsInfo.Interval) ref$ObjectRef.element, i)) {
            LazyLayoutBeyondBoundsInfo.Interval intervalM326addNextIntervalFR3nfPY = m326addNextIntervalFR3nfPY((LazyLayoutBeyondBoundsInfo.Interval) ref$ObjectRef.element, i);
            this.beyondBoundsInfo.removeInterval((LazyLayoutBeyondBoundsInfo.Interval) ref$ObjectRef.element);
            ref$ObjectRef.element = intervalM326addNextIntervalFR3nfPY;
            this.state.remeasure();
            objInvoke = function1.invoke(new BeyondBoundsLayout.BeyondBoundsScope() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocal$layout$2
                @Override // androidx.compose.ui.layout.BeyondBoundsLayout.BeyondBoundsScope
                public boolean getHasMoreContent() {
                    return this.this$0.m327hasMoreContentFR3nfPY((LazyLayoutBeyondBoundsInfo.Interval) ref$ObjectRef.element, i);
                }
            });
        }
        this.beyondBoundsInfo.removeInterval((LazyLayoutBeyondBoundsInfo.Interval) ref$ObjectRef.element);
        this.state.remeasure();
        return objInvoke;
    }

    /* JADX INFO: renamed from: isForward-4vf7U8o, reason: not valid java name */
    private final boolean m328isForward4vf7U8o(int i) {
        BeyondBoundsLayout.LayoutDirection.Companion companion = BeyondBoundsLayout.LayoutDirection.Companion;
        if (BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1729getBeforehoxUOeE())) {
            return false;
        }
        if (!BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1728getAfterhoxUOeE())) {
            if (BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1727getAbovehoxUOeE())) {
                return this.reverseLayout;
            }
            if (BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1730getBelowhoxUOeE())) {
                if (this.reverseLayout) {
                    return false;
                }
            } else if (BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1731getLefthoxUOeE())) {
                int i2 = WhenMappings.$EnumSwitchMapping$0[this.layoutDirection.ordinal()];
                if (i2 == 1) {
                    return this.reverseLayout;
                }
                if (i2 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                if (this.reverseLayout) {
                    return false;
                }
            } else {
                if (!BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1732getRighthoxUOeE())) {
                    LazyLayoutBeyondBoundsModifierLocalKt.unsupportedDirection();
                    throw new KotlinNothingValueException();
                }
                int i3 = WhenMappings.$EnumSwitchMapping$0[this.layoutDirection.ordinal()];
                if (i3 != 1) {
                    if (i3 == 2) {
                        return this.reverseLayout;
                    }
                    throw new NoWhenBranchMatchedException();
                }
                if (this.reverseLayout) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: addNextInterval-FR3nfPY, reason: not valid java name */
    private final LazyLayoutBeyondBoundsInfo.Interval m326addNextIntervalFR3nfPY(LazyLayoutBeyondBoundsInfo.Interval interval, int i) {
        int start = interval.getStart();
        int end = interval.getEnd();
        if (m328isForward4vf7U8o(i)) {
            end++;
        } else {
            start--;
        }
        return this.beyondBoundsInfo.addInterval(start, end);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hasMoreContent-FR3nfPY, reason: not valid java name */
    public final boolean m327hasMoreContentFR3nfPY(LazyLayoutBeyondBoundsInfo.Interval interval, int i) {
        if (m329isOppositeToOrientation4vf7U8o(i)) {
            return false;
        }
        if (m328isForward4vf7U8o(i)) {
            if (interval.getEnd() >= this.state.getItemCount() - 1) {
                return false;
            }
        } else if (interval.getStart() <= 0) {
            return false;
        }
        return true;
    }

    /* JADX INFO: renamed from: isOppositeToOrientation-4vf7U8o, reason: not valid java name */
    private final boolean m329isOppositeToOrientation4vf7U8o(int i) {
        BeyondBoundsLayout.LayoutDirection.Companion companion = BeyondBoundsLayout.LayoutDirection.Companion;
        if (!(BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1727getAbovehoxUOeE()) ? true : BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1730getBelowhoxUOeE()))) {
            if (!(BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1731getLefthoxUOeE()) ? true : BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1732getRighthoxUOeE()))) {
                if (!(BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1729getBeforehoxUOeE()) ? true : BeyondBoundsLayout.LayoutDirection.m1726equalsimpl0(i, companion.m1728getAfterhoxUOeE()))) {
                    LazyLayoutBeyondBoundsModifierLocalKt.unsupportedDirection();
                    throw new KotlinNothingValueException();
                }
            } else if (this.orientation == Orientation.Vertical) {
                return true;
            }
        } else if (this.orientation == Orientation.Horizontal) {
            return true;
        }
        return false;
    }
}
