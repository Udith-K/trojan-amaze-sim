package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: TopAppBarMediumTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TopAppBarMediumTokens {
    private static final ColorSchemeKeyTokens HeadlineColor;
    private static final TypographyKeyTokens HeadlineFont;
    private static final ColorSchemeKeyTokens LeadingIconColor;
    private static final float LeadingIconSize;
    private static final ColorSchemeKeyTokens TrailingIconColor;
    private static final float TrailingIconSize;
    public static final TopAppBarMediumTokens INSTANCE = new TopAppBarMediumTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.Surface;
    private static final float ContainerElevation = ElevationTokens.INSTANCE.m868getLevel0D9Ej5fM();
    private static final float ContainerHeight = Dp.m2416constructorimpl((float) 112.0d);
    private static final ShapeKeyTokens ContainerShape = ShapeKeyTokens.CornerNone;
    private static final ColorSchemeKeyTokens ContainerSurfaceTintLayerColor = ColorSchemeKeyTokens.SurfaceTint;

    private TopAppBarMediumTokens() {
    }

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurface;
        HeadlineColor = colorSchemeKeyTokens;
        HeadlineFont = TypographyKeyTokens.HeadlineSmall;
        LeadingIconColor = colorSchemeKeyTokens;
        float f = (float) 24.0d;
        LeadingIconSize = Dp.m2416constructorimpl(f);
        TrailingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant;
        TrailingIconSize = Dp.m2416constructorimpl(f);
    }

    /* JADX INFO: renamed from: getContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m960getContainerHeightD9Ej5fM() {
        return ContainerHeight;
    }
}
