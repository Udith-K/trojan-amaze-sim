package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: ElevatedCardTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ElevatedCardTokens {
    private static final float ContainerElevation;
    private static final ShapeKeyTokens ContainerShape;
    private static final ColorSchemeKeyTokens DisabledContainerColor;
    private static final float DisabledContainerElevation;
    private static final float DisabledContainerOpacity;
    private static final float DraggedContainerElevation;
    private static final float FocusContainerElevation;
    private static final ColorSchemeKeyTokens FocusIndicatorColor;
    private static final float HoverContainerElevation;
    private static final ColorSchemeKeyTokens IconColor;
    private static final float IconSize;
    private static final float PressedContainerElevation;
    public static final ElevatedCardTokens INSTANCE = new ElevatedCardTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.SurfaceContainerLow;

    private ElevatedCardTokens() {
    }

    static {
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        ContainerElevation = elevationTokens.m869getLevel1D9Ej5fM();
        ContainerShape = ShapeKeyTokens.CornerMedium;
        DisabledContainerColor = ColorSchemeKeyTokens.Surface;
        DisabledContainerElevation = elevationTokens.m869getLevel1D9Ej5fM();
        DisabledContainerOpacity = 0.38f;
        DraggedContainerElevation = elevationTokens.m872getLevel4D9Ej5fM();
        FocusContainerElevation = elevationTokens.m869getLevel1D9Ej5fM();
        FocusIndicatorColor = ColorSchemeKeyTokens.Secondary;
        HoverContainerElevation = elevationTokens.m870getLevel2D9Ej5fM();
        IconColor = ColorSchemeKeyTokens.Primary;
        IconSize = Dp.m2416constructorimpl((float) 24.0d);
        PressedContainerElevation = elevationTokens.m869getLevel1D9Ej5fM();
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m862getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    public final ColorSchemeKeyTokens getDisabledContainerColor() {
        return DisabledContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m863getDisabledContainerElevationD9Ej5fM() {
        return DisabledContainerElevation;
    }

    public final float getDisabledContainerOpacity() {
        return DisabledContainerOpacity;
    }

    /* JADX INFO: renamed from: getDraggedContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m864getDraggedContainerElevationD9Ej5fM() {
        return DraggedContainerElevation;
    }

    /* JADX INFO: renamed from: getFocusContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m865getFocusContainerElevationD9Ej5fM() {
        return FocusContainerElevation;
    }

    /* JADX INFO: renamed from: getHoverContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m866getHoverContainerElevationD9Ej5fM() {
        return HoverContainerElevation;
    }

    /* JADX INFO: renamed from: getPressedContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m867getPressedContainerElevationD9Ej5fM() {
        return PressedContainerElevation;
    }
}
