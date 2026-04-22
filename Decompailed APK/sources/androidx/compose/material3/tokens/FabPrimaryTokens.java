package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: FabPrimaryTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FabPrimaryTokens {
    private static final float ContainerElevation;
    private static final float ContainerHeight;
    private static final ShapeKeyTokens ContainerShape;
    private static final float ContainerWidth;
    private static final float FocusContainerElevation;
    private static final ColorSchemeKeyTokens FocusIconColor;
    private static final float HoverContainerElevation;
    private static final ColorSchemeKeyTokens HoverIconColor;
    private static final ColorSchemeKeyTokens IconColor;
    private static final float IconSize;
    private static final float LoweredContainerElevation;
    private static final float LoweredFocusContainerElevation;
    private static final float LoweredHoverContainerElevation;
    private static final float LoweredPressedContainerElevation;
    private static final float PressedContainerElevation;
    private static final ColorSchemeKeyTokens PressedIconColor;
    public static final FabPrimaryTokens INSTANCE = new FabPrimaryTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.PrimaryContainer;

    private FabPrimaryTokens() {
    }

    static {
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        ContainerElevation = elevationTokens.m871getLevel3D9Ej5fM();
        float f = (float) 56.0d;
        ContainerHeight = Dp.m2416constructorimpl(f);
        ContainerShape = ShapeKeyTokens.CornerLarge;
        ContainerWidth = Dp.m2416constructorimpl(f);
        FocusContainerElevation = elevationTokens.m871getLevel3D9Ej5fM();
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnPrimaryContainer;
        FocusIconColor = colorSchemeKeyTokens;
        HoverContainerElevation = elevationTokens.m872getLevel4D9Ej5fM();
        HoverIconColor = colorSchemeKeyTokens;
        IconColor = colorSchemeKeyTokens;
        IconSize = Dp.m2416constructorimpl((float) 24.0d);
        LoweredContainerElevation = elevationTokens.m869getLevel1D9Ej5fM();
        LoweredFocusContainerElevation = elevationTokens.m869getLevel1D9Ej5fM();
        LoweredHoverContainerElevation = elevationTokens.m870getLevel2D9Ej5fM();
        LoweredPressedContainerElevation = elevationTokens.m869getLevel1D9Ej5fM();
        PressedContainerElevation = elevationTokens.m871getLevel3D9Ej5fM();
        PressedIconColor = colorSchemeKeyTokens;
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m874getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    /* JADX INFO: renamed from: getContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m875getContainerHeightD9Ej5fM() {
        return ContainerHeight;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    /* JADX INFO: renamed from: getContainerWidth-D9Ej5fM, reason: not valid java name */
    public final float m876getContainerWidthD9Ej5fM() {
        return ContainerWidth;
    }

    /* JADX INFO: renamed from: getFocusContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m877getFocusContainerElevationD9Ej5fM() {
        return FocusContainerElevation;
    }

    /* JADX INFO: renamed from: getHoverContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m878getHoverContainerElevationD9Ej5fM() {
        return HoverContainerElevation;
    }

    /* JADX INFO: renamed from: getPressedContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m879getPressedContainerElevationD9Ej5fM() {
        return PressedContainerElevation;
    }
}
