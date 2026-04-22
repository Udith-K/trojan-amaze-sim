package androidx.compose.animation;

import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;

/* JADX INFO: compiled from: AnimationModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AnimationModifierKt {
    private static final long InvalidSize = IntSizeKt.IntSize(Integer.MIN_VALUE, Integer.MIN_VALUE);

    public static final long getInvalidSize() {
        return InvalidSize;
    }

    /* JADX INFO: renamed from: isValid-ozmzZPI, reason: not valid java name */
    public static final boolean m24isValidozmzZPI(long j) {
        return !IntSize.m2474equalsimpl0(j, InvalidSize);
    }
}
