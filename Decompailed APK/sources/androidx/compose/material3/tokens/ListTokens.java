package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: ListTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ListTokens {
    private static final float DividerLeadingSpace;
    private static final float DividerTrailingSpace;
    private static final float ListItemContainerElevation;
    private static final ShapeKeyTokens ListItemContainerShape;
    private static final ColorSchemeKeyTokens ListItemDisabledLabelTextColor;
    private static final float ListItemDisabledLabelTextOpacity;
    private static final ColorSchemeKeyTokens ListItemDisabledLeadingIconColor;
    private static final float ListItemDisabledLeadingIconOpacity;
    private static final ColorSchemeKeyTokens ListItemDisabledTrailingIconColor;
    private static final float ListItemDisabledTrailingIconOpacity;
    private static final float ListItemDraggedContainerElevation;
    private static final ColorSchemeKeyTokens ListItemDraggedLabelTextColor;
    private static final ColorSchemeKeyTokens ListItemDraggedLeadingIconColor;
    private static final ColorSchemeKeyTokens ListItemDraggedTrailingIconColor;
    private static final ColorSchemeKeyTokens ListItemFocusLabelTextColor;
    private static final ColorSchemeKeyTokens ListItemFocusLeadingIconColor;
    private static final ColorSchemeKeyTokens ListItemFocusTrailingIconColor;
    private static final ColorSchemeKeyTokens ListItemHoverLabelTextColor;
    private static final ColorSchemeKeyTokens ListItemHoverLeadingIconColor;
    private static final ColorSchemeKeyTokens ListItemHoverTrailingIconColor;
    private static final ColorSchemeKeyTokens ListItemLabelTextColor;
    private static final TypographyKeyTokens ListItemLabelTextFont;
    private static final float ListItemLargeLeadingVideoHeight;
    private static final ColorSchemeKeyTokens ListItemLeadingAvatarColor;
    private static final ColorSchemeKeyTokens ListItemLeadingAvatarLabelColor;
    private static final TypographyKeyTokens ListItemLeadingAvatarLabelFont;
    private static final ShapeKeyTokens ListItemLeadingAvatarShape;
    private static final float ListItemLeadingAvatarSize;
    private static final ColorSchemeKeyTokens ListItemLeadingIconColor;
    private static final float ListItemLeadingIconSize;
    private static final float ListItemLeadingImageHeight;
    private static final ShapeKeyTokens ListItemLeadingImageShape;
    private static final float ListItemLeadingImageWidth;
    private static final float ListItemLeadingSpace;
    private static final ShapeKeyTokens ListItemLeadingVideoShape;
    private static final float ListItemLeadingVideoWidth;
    private static final float ListItemOneLineContainerHeight;
    private static final ColorSchemeKeyTokens ListItemOverlineColor;
    private static final TypographyKeyTokens ListItemOverlineFont;
    private static final ColorSchemeKeyTokens ListItemPressedLabelTextColor;
    private static final ColorSchemeKeyTokens ListItemPressedLeadingIconColor;
    private static final ColorSchemeKeyTokens ListItemPressedTrailingIconColor;
    private static final ColorSchemeKeyTokens ListItemSelectedTrailingIconColor;
    private static final float ListItemSmallLeadingVideoHeight;
    private static final ColorSchemeKeyTokens ListItemSupportingTextColor;
    private static final TypographyKeyTokens ListItemSupportingTextFont;
    private static final float ListItemThreeLineContainerHeight;
    private static final ColorSchemeKeyTokens ListItemTrailingIconColor;
    private static final float ListItemTrailingIconSize;
    private static final float ListItemTrailingSpace;
    private static final ColorSchemeKeyTokens ListItemTrailingSupportingTextColor;
    private static final TypographyKeyTokens ListItemTrailingSupportingTextFont;
    private static final float ListItemTwoLineContainerHeight;
    private static final ColorSchemeKeyTokens ListItemUnselectedTrailingIconColor;
    public static final ListTokens INSTANCE = new ListTokens();
    private static final ColorSchemeKeyTokens FocusIndicatorColor = ColorSchemeKeyTokens.Secondary;
    private static final ColorSchemeKeyTokens ListItemContainerColor = ColorSchemeKeyTokens.Surface;

    private ListTokens() {
    }

    public final ColorSchemeKeyTokens getListItemDisabledLabelTextColor() {
        return ListItemDisabledLabelTextColor;
    }

    public final float getListItemDisabledLabelTextOpacity() {
        return ListItemDisabledLabelTextOpacity;
    }

    public final ColorSchemeKeyTokens getListItemDisabledLeadingIconColor() {
        return ListItemDisabledLeadingIconColor;
    }

    public final float getListItemDisabledLeadingIconOpacity() {
        return ListItemDisabledLeadingIconOpacity;
    }

    public final ColorSchemeKeyTokens getListItemDisabledTrailingIconColor() {
        return ListItemDisabledTrailingIconColor;
    }

    public final float getListItemDisabledTrailingIconOpacity() {
        return ListItemDisabledTrailingIconOpacity;
    }

    public final ColorSchemeKeyTokens getListItemLabelTextColor() {
        return ListItemLabelTextColor;
    }

    public final ColorSchemeKeyTokens getListItemLeadingIconColor() {
        return ListItemLeadingIconColor;
    }

    /* JADX INFO: renamed from: getListItemLeadingIconSize-D9Ej5fM, reason: not valid java name */
    public final float m894getListItemLeadingIconSizeD9Ej5fM() {
        return ListItemLeadingIconSize;
    }

    public final ColorSchemeKeyTokens getListItemTrailingIconColor() {
        return ListItemTrailingIconColor;
    }

    /* JADX INFO: renamed from: getListItemTrailingIconSize-D9Ej5fM, reason: not valid java name */
    public final float m895getListItemTrailingIconSizeD9Ej5fM() {
        return ListItemTrailingIconSize;
    }

    static {
        float f = (float) 16.0d;
        DividerLeadingSpace = Dp.m2416constructorimpl(f);
        DividerTrailingSpace = Dp.m2416constructorimpl(f);
        ElevationTokens elevationTokens = ElevationTokens.INSTANCE;
        ListItemContainerElevation = elevationTokens.m868getLevel0D9Ej5fM();
        ShapeKeyTokens shapeKeyTokens = ShapeKeyTokens.CornerNone;
        ListItemContainerShape = shapeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurface;
        ListItemDisabledLabelTextColor = colorSchemeKeyTokens;
        ListItemDisabledLabelTextOpacity = 0.38f;
        ListItemDisabledLeadingIconColor = colorSchemeKeyTokens;
        ListItemDisabledLeadingIconOpacity = 0.38f;
        ListItemDisabledTrailingIconColor = colorSchemeKeyTokens;
        ListItemDisabledTrailingIconOpacity = 0.38f;
        ListItemDraggedContainerElevation = elevationTokens.m872getLevel4D9Ej5fM();
        ListItemDraggedLabelTextColor = colorSchemeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurfaceVariant;
        ListItemDraggedLeadingIconColor = colorSchemeKeyTokens2;
        ListItemDraggedTrailingIconColor = colorSchemeKeyTokens2;
        ListItemFocusLabelTextColor = colorSchemeKeyTokens;
        ListItemFocusLeadingIconColor = colorSchemeKeyTokens2;
        ListItemFocusTrailingIconColor = colorSchemeKeyTokens2;
        ListItemHoverLabelTextColor = colorSchemeKeyTokens;
        ListItemHoverLeadingIconColor = colorSchemeKeyTokens2;
        ListItemHoverTrailingIconColor = colorSchemeKeyTokens2;
        ListItemLabelTextColor = colorSchemeKeyTokens;
        ListItemLabelTextFont = TypographyKeyTokens.BodyLarge;
        ListItemLargeLeadingVideoHeight = Dp.m2416constructorimpl((float) 69.0d);
        ListItemLeadingAvatarColor = ColorSchemeKeyTokens.PrimaryContainer;
        ListItemLeadingAvatarLabelColor = ColorSchemeKeyTokens.OnPrimaryContainer;
        ListItemLeadingAvatarLabelFont = TypographyKeyTokens.TitleMedium;
        ListItemLeadingAvatarShape = ShapeKeyTokens.CornerFull;
        ListItemLeadingAvatarSize = Dp.m2416constructorimpl((float) 40.0d);
        ListItemLeadingIconColor = colorSchemeKeyTokens2;
        float f2 = (float) 24.0d;
        ListItemLeadingIconSize = Dp.m2416constructorimpl(f2);
        float f3 = (float) 56.0d;
        ListItemLeadingImageHeight = Dp.m2416constructorimpl(f3);
        ListItemLeadingImageShape = shapeKeyTokens;
        ListItemLeadingImageWidth = Dp.m2416constructorimpl(f3);
        ListItemLeadingSpace = Dp.m2416constructorimpl(f);
        ListItemLeadingVideoShape = shapeKeyTokens;
        ListItemLeadingVideoWidth = Dp.m2416constructorimpl((float) 100.0d);
        ListItemOneLineContainerHeight = Dp.m2416constructorimpl(f3);
        ListItemOverlineColor = colorSchemeKeyTokens2;
        TypographyKeyTokens typographyKeyTokens = TypographyKeyTokens.LabelSmall;
        ListItemOverlineFont = typographyKeyTokens;
        ListItemPressedLabelTextColor = colorSchemeKeyTokens;
        ListItemPressedLeadingIconColor = colorSchemeKeyTokens2;
        ListItemPressedTrailingIconColor = colorSchemeKeyTokens2;
        ListItemSelectedTrailingIconColor = ColorSchemeKeyTokens.Primary;
        ListItemSmallLeadingVideoHeight = Dp.m2416constructorimpl(f3);
        ListItemSupportingTextColor = colorSchemeKeyTokens2;
        ListItemSupportingTextFont = TypographyKeyTokens.BodyMedium;
        ListItemThreeLineContainerHeight = Dp.m2416constructorimpl((float) 88.0d);
        ListItemTrailingIconColor = colorSchemeKeyTokens2;
        ListItemTrailingIconSize = Dp.m2416constructorimpl(f2);
        ListItemTrailingSpace = Dp.m2416constructorimpl(f);
        ListItemTrailingSupportingTextColor = colorSchemeKeyTokens2;
        ListItemTrailingSupportingTextFont = typographyKeyTokens;
        ListItemTwoLineContainerHeight = Dp.m2416constructorimpl((float) 72.0d);
        ListItemUnselectedTrailingIconColor = colorSchemeKeyTokens;
    }
}
