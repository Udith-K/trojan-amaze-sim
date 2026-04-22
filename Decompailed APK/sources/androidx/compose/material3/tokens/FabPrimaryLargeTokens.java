package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: FabPrimaryLargeTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FabPrimaryLargeTokens {
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
    public static final FabPrimaryLargeTokens INSTANCE = new FabPrimaryLargeTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.PrimaryContainer;

    private FabPrimaryLargeTokens() {
    }

    static {
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        ContainerElevation = elevationTokens.m871getLevel3D9Ej5fM();
        float f = (float) 96.0d;
        ContainerHeight = Dp.m2416constructorimpl(f);
        ContainerShape = ShapeKeyTokens.CornerExtraLarge;
        ContainerWidth = Dp.m2416constructorimpl(f);
        FocusContainerElevation = elevationTokens.m871getLevel3D9Ej5fM();
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnPrimaryContainer;
        FocusIconColor = colorSchemeKeyTokens;
        HoverContainerElevation = elevationTokens.m872getLevel4D9Ej5fM();
        HoverIconColor = colorSchemeKeyTokens;
        IconColor = colorSchemeKeyTokens;
        IconSize = Dp.m2416constructorimpl((float) 36.0d);
        LoweredContainerElevation = elevationTokens.m869getLevel1D9Ej5fM();
        LoweredFocusContainerElevation = elevationTokens.m869getLevel1D9Ej5fM();
        LoweredHoverContainerElevation = elevationTokens.m870getLevel2D9Ej5fM();
        LoweredPressedContainerElevation = elevationTokens.m869getLevel1D9Ej5fM();
        PressedContainerElevation = elevationTokens.m871getLevel3D9Ej5fM();
        PressedIconColor = colorSchemeKeyTokens;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m873getIconSizeD9Ej5fM() {
        return IconSize;
    }
}
