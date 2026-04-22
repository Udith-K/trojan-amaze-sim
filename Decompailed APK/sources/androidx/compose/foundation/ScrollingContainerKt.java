package androidx.compose.foundation;

import androidx.compose.foundation.gestures.BringIntoViewSpec;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.LayoutDirection;

/* JADX INFO: compiled from: ScrollingContainer.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ScrollingContainerKt {
    public static final Modifier scrollingContainer(Modifier modifier, ScrollableState scrollableState, Orientation orientation, boolean z, boolean z2, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, BringIntoViewSpec bringIntoViewSpec, Composer composer, int i, int i2) {
        BringIntoViewSpec bringIntoViewSpec2 = (i2 & 64) != 0 ? null : bringIntoViewSpec;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1677817431, i, -1, "androidx.compose.foundation.scrollingContainer (ScrollingContainer.kt:40)");
        }
        ScrollableDefaults scrollableDefaults = ScrollableDefaults.INSTANCE;
        OverscrollEffect overscrollEffect = scrollableDefaults.overscrollEffect(composer, 6);
        Modifier modifierScrollable = ScrollableKt.scrollable(OverscrollKt.overscroll(ClipScrollableContainerKt.clipScrollableContainer(modifier, orientation), overscrollEffect), scrollableState, orientation, overscrollEffect, z, scrollableDefaults.reverseDirection((LayoutDirection) composer.consume(CompositionLocalsKt.getLocalLayoutDirection()), orientation, z2), flingBehavior, mutableInteractionSource, bringIntoViewSpec2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return modifierScrollable;
    }
}
