package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: IconButtonTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IconButtonTokens {
    private static final ColorSchemeKeyTokens SelectedFocusIconColor;
    private static final ColorSchemeKeyTokens SelectedHoverIconColor;
    private static final ColorSchemeKeyTokens SelectedIconColor;
    private static final ColorSchemeKeyTokens SelectedPressedIconColor;
    private static final ShapeKeyTokens StateLayerShape;
    private static final float StateLayerSize;
    private static final ColorSchemeKeyTokens UnselectedFocusIconColor;
    private static final ColorSchemeKeyTokens UnselectedHoverIconColor;
    private static final ColorSchemeKeyTokens UnselectedIconColor;
    private static final ColorSchemeKeyTokens UnselectedPressedIconColor;
    public static final IconButtonTokens INSTANCE = new IconButtonTokens();
    private static final ColorSchemeKeyTokens DisabledIconColor = ColorSchemeKeyTokens.OnSurface;
    private static final float IconSize = Dp.m2416constructorimpl((float) 24.0d);

    private IconButtonTokens() {
    }

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.Primary;
        SelectedFocusIconColor = colorSchemeKeyTokens;
        SelectedHoverIconColor = colorSchemeKeyTokens;
        SelectedIconColor = colorSchemeKeyTokens;
        SelectedPressedIconColor = colorSchemeKeyTokens;
        StateLayerShape = ShapeKeyTokens.CornerFull;
        StateLayerSize = Dp.m2416constructorimpl((float) 40.0d);
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurfaceVariant;
        UnselectedFocusIconColor = colorSchemeKeyTokens2;
        UnselectedHoverIconColor = colorSchemeKeyTokens2;
        UnselectedIconColor = colorSchemeKeyTokens2;
        UnselectedPressedIconColor = colorSchemeKeyTokens2;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m892getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final ShapeKeyTokens getStateLayerShape() {
        return StateLayerShape;
    }

    /* JADX INFO: renamed from: getStateLayerSize-D9Ej5fM, reason: not valid java name */
    public final float m893getStateLayerSizeD9Ej5fM() {
        return StateLayerSize;
    }
}
