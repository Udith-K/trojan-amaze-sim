package androidx.compose.material3.tokens;

/* JADX INFO: compiled from: MenuTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MenuTokens {
    private static final ColorSchemeKeyTokens ListItemSelectedLabelTextColor;
    private static final ColorSchemeKeyTokens ListItemSelectedLeadingTrailingIconColor;
    private static final ColorSchemeKeyTokens MenuListItemLeadingIconColor;
    public static final MenuTokens INSTANCE = new MenuTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.SurfaceContainer;
    private static final float ContainerElevation = ElevationTokens.INSTANCE.m870getLevel2D9Ej5fM();
    private static final ShapeKeyTokens ContainerShape = ShapeKeyTokens.CornerExtraSmall;
    private static final ColorSchemeKeyTokens FocusIndicatorColor = ColorSchemeKeyTokens.Secondary;
    private static final ColorSchemeKeyTokens ListItemSelectedContainerColor = ColorSchemeKeyTokens.SecondaryContainer;

    private MenuTokens() {
    }

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSecondaryContainer;
        ListItemSelectedLabelTextColor = colorSchemeKeyTokens;
        ListItemSelectedLeadingTrailingIconColor = colorSchemeKeyTokens;
        MenuListItemLeadingIconColor = colorSchemeKeyTokens;
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m896getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }
}
