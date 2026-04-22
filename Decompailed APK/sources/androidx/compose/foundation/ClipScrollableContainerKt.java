package androidx.compose.foundation;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;

/* JADX INFO: compiled from: ClipScrollableContainer.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ClipScrollableContainerKt {
    private static final Modifier HorizontalScrollableClipModifier;
    private static final float MaxSupportedElevation = Dp.m2416constructorimpl(30);
    private static final Modifier VerticalScrollableClipModifier;

    public static final Modifier clipScrollableContainer(Modifier modifier, Orientation orientation) {
        Modifier modifier2;
        if (orientation == Orientation.Vertical) {
            modifier2 = VerticalScrollableClipModifier;
        } else {
            modifier2 = HorizontalScrollableClipModifier;
        }
        return modifier.then(modifier2);
    }

    public static final float getMaxSupportedElevation() {
        return MaxSupportedElevation;
    }

    static {
        Modifier.Companion companion = Modifier.Companion;
        HorizontalScrollableClipModifier = ClipKt.clip(companion, new Shape() { // from class: androidx.compose.foundation.ClipScrollableContainerKt$HorizontalScrollableClipModifier$1
            @Override // androidx.compose.ui.graphics.Shape
            /* JADX INFO: renamed from: createOutline-Pq9zytI, reason: not valid java name */
            public Outline mo125createOutlinePq9zytI(long j, LayoutDirection layoutDirection, Density density) {
                float fMo204roundToPx0680j_4 = density.mo204roundToPx0680j_4(ClipScrollableContainerKt.getMaxSupportedElevation());
                return new Outline.Rectangle(new Rect(0.0f, -fMo204roundToPx0680j_4, Size.m1196getWidthimpl(j), Size.m1194getHeightimpl(j) + fMo204roundToPx0680j_4));
            }
        });
        VerticalScrollableClipModifier = ClipKt.clip(companion, new Shape() { // from class: androidx.compose.foundation.ClipScrollableContainerKt$VerticalScrollableClipModifier$1
            @Override // androidx.compose.ui.graphics.Shape
            /* JADX INFO: renamed from: createOutline-Pq9zytI */
            public Outline mo125createOutlinePq9zytI(long j, LayoutDirection layoutDirection, Density density) {
                float fMo204roundToPx0680j_4 = density.mo204roundToPx0680j_4(ClipScrollableContainerKt.getMaxSupportedElevation());
                return new Outline.Rectangle(new Rect(-fMo204roundToPx0680j_4, 0.0f, Size.m1196getWidthimpl(j) + fMo204roundToPx0680j_4, Size.m1194getHeightimpl(j)));
            }
        });
    }
}
