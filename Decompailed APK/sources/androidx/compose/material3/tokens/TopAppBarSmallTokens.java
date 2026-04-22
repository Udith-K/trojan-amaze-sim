package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: TopAppBarSmallTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TopAppBarSmallTokens {
    private static final float ContainerElevation;
    private static final float ContainerHeight;
    private static final ShapeKeyTokens ContainerShape;
    private static final ColorSchemeKeyTokens HeadlineColor;
    private static final TypographyKeyTokens HeadlineFont;
    private static final ColorSchemeKeyTokens LeadingIconColor;
    private static final float LeadingIconSize;
    private static final ColorSchemeKeyTokens OnScrollContainerColor;
    private static final float OnScrollContainerElevation;
    private static final ColorSchemeKeyTokens TrailingIconColor;
    private static final float TrailingIconSize;
    public static final TopAppBarSmallTokens INSTANCE = new TopAppBarSmallTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.Surface;

    private TopAppBarSmallTokens() {
    }

    static {
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        ContainerElevation = elevationTokens.m868getLevel0D9Ej5fM();
        ContainerHeight = Dp.m2416constructorimpl((float) 64.0d);
        ContainerShape = ShapeKeyTokens.CornerNone;
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurface;
        HeadlineColor = colorSchemeKeyTokens;
        HeadlineFont = TypographyKeyTokens.TitleLarge;
        LeadingIconColor = colorSchemeKeyTokens;
        float f = (float) 24.0d;
        LeadingIconSize = Dp.m2416constructorimpl(f);
        OnScrollContainerColor = ColorSchemeKeyTokens.SurfaceContainer;
        OnScrollContainerElevation = elevationTokens.m870getLevel2D9Ej5fM();
        TrailingIconColor = ColorSchemeKeyTokens.OnSurfaceVariant;
        TrailingIconSize = Dp.m2416constructorimpl(f);
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m961getContainerHeightD9Ej5fM() {
        return ContainerHeight;
    }

    public final ColorSchemeKeyTokens getHeadlineColor() {
        return HeadlineColor;
    }

    public final TypographyKeyTokens getHeadlineFont() {
        return HeadlineFont;
    }

    public final ColorSchemeKeyTokens getLeadingIconColor() {
        return LeadingIconColor;
    }

    public final ColorSchemeKeyTokens getOnScrollContainerColor() {
        return OnScrollContainerColor;
    }

    public final ColorSchemeKeyTokens getTrailingIconColor() {
        return TrailingIconColor;
    }
}
