package androidx.compose.material3;

import androidx.compose.material3.tokens.FabPrimaryLargeTokens;
import androidx.compose.material3.tokens.FabPrimaryTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Shape;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FloatingActionButtonDefaults {
    public static final FloatingActionButtonDefaults INSTANCE = new FloatingActionButtonDefaults();
    private static final float LargeIconSize = FabPrimaryLargeTokens.INSTANCE.m873getIconSizeD9Ej5fM();

    private FloatingActionButtonDefaults() {
    }

    public final Shape getShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-53247565, i, -1, "androidx.compose.material3.FloatingActionButtonDefaults.<get-shape> (FloatingActionButton.kt:410)");
        }
        Shape value = ShapesKt.getValue(FabPrimaryTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final long getContainerColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1855656391, i, -1, "androidx.compose.material3.FloatingActionButtonDefaults.<get-containerColor> (FloatingActionButton.kt:426)");
        }
        long value = ColorSchemeKt.getValue(FabPrimaryTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: elevation-xZ9-QkE, reason: not valid java name */
    public final FloatingActionButtonElevation m679elevationxZ9QkE(float f, float f2, float f3, float f4, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = FabPrimaryTokens.INSTANCE.m874getContainerElevationD9Ej5fM();
        }
        if ((i2 & 2) != 0) {
            f2 = FabPrimaryTokens.INSTANCE.m879getPressedContainerElevationD9Ej5fM();
        }
        float f5 = f2;
        if ((i2 & 4) != 0) {
            f3 = FabPrimaryTokens.INSTANCE.m877getFocusContainerElevationD9Ej5fM();
        }
        float f6 = f3;
        if ((i2 & 8) != 0) {
            f4 = FabPrimaryTokens.INSTANCE.m878getHoverContainerElevationD9Ej5fM();
        }
        float f7 = f4;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-241106249, i, -1, "androidx.compose.material3.FloatingActionButtonDefaults.elevation (FloatingActionButton.kt:446)");
        }
        FloatingActionButtonElevation floatingActionButtonElevation = new FloatingActionButtonElevation(f, f5, f6, f7, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return floatingActionButtonElevation;
    }
}
