package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: FilledCardTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FilledCardTokens {
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
    public static final FilledCardTokens INSTANCE = new FilledCardTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.SurfaceContainerHighest;

    private FilledCardTokens() {
    }

    static {
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        ContainerElevation = elevationTokens.m868getLevel0D9Ej5fM();
        ContainerShape = ShapeKeyTokens.CornerMedium;
        DisabledContainerColor = ColorSchemeKeyTokens.SurfaceVariant;
        DisabledContainerElevation = elevationTokens.m868getLevel0D9Ej5fM();
        DisabledContainerOpacity = 0.38f;
        DraggedContainerElevation = elevationTokens.m871getLevel3D9Ej5fM();
        FocusContainerElevation = elevationTokens.m868getLevel0D9Ej5fM();
        FocusIndicatorColor = ColorSchemeKeyTokens.Secondary;
        HoverContainerElevation = elevationTokens.m869getLevel1D9Ej5fM();
        IconColor = ColorSchemeKeyTokens.Primary;
        IconSize = Dp.m2416constructorimpl((float) 24.0d);
        PressedContainerElevation = elevationTokens.m868getLevel0D9Ej5fM();
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m886getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    public final ColorSchemeKeyTokens getDisabledContainerColor() {
        return DisabledContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m887getDisabledContainerElevationD9Ej5fM() {
        return DisabledContainerElevation;
    }

    public final float getDisabledContainerOpacity() {
        return DisabledContainerOpacity;
    }

    /* JADX INFO: renamed from: getDraggedContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m888getDraggedContainerElevationD9Ej5fM() {
        return DraggedContainerElevation;
    }

    /* JADX INFO: renamed from: getFocusContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m889getFocusContainerElevationD9Ej5fM() {
        return FocusContainerElevation;
    }

    /* JADX INFO: renamed from: getHoverContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m890getHoverContainerElevationD9Ej5fM() {
        return HoverContainerElevation;
    }

    /* JADX INFO: renamed from: getPressedContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m891getPressedContainerElevationD9Ej5fM() {
        return PressedContainerElevation;
    }
}
