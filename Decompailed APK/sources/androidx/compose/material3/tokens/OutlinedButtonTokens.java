package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: OutlinedButtonTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class OutlinedButtonTokens {
    private static final ColorSchemeKeyTokens DisabledIconColor;
    private static final ColorSchemeKeyTokens DisabledLabelTextColor;
    private static final ColorSchemeKeyTokens DisabledOutlineColor;
    private static final ColorSchemeKeyTokens FocusIconColor;
    private static final ColorSchemeKeyTokens FocusLabelTextColor;
    private static final ColorSchemeKeyTokens FocusOutlineColor;
    private static final ColorSchemeKeyTokens HoverIconColor;
    private static final ColorSchemeKeyTokens HoverLabelTextColor;
    private static final ColorSchemeKeyTokens HoverOutlineColor;
    private static final ColorSchemeKeyTokens IconColor;
    private static final float IconSize;
    private static final ColorSchemeKeyTokens LabelTextColor;
    private static final TypographyKeyTokens LabelTextFont;
    private static final ColorSchemeKeyTokens OutlineColor;
    private static final float OutlineWidth;
    private static final ColorSchemeKeyTokens PressedIconColor;
    private static final ColorSchemeKeyTokens PressedLabelTextColor;
    private static final ColorSchemeKeyTokens PressedOutlineColor;
    public static final OutlinedButtonTokens INSTANCE = new OutlinedButtonTokens();
    private static final float ContainerHeight = Dp.m2416constructorimpl((float) 40.0d);
    private static final ShapeKeyTokens ContainerShape = ShapeKeyTokens.CornerFull;

    private OutlinedButtonTokens() {
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    public final ColorSchemeKeyTokens getDisabledLabelTextColor() {
        return DisabledLabelTextColor;
    }

    public final ColorSchemeKeyTokens getLabelTextColor() {
        return LabelTextColor;
    }

    public final ColorSchemeKeyTokens getOutlineColor() {
        return OutlineColor;
    }

    /* JADX INFO: renamed from: getOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m897getOutlineWidthD9Ej5fM() {
        return OutlineWidth;
    }

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurface;
        DisabledLabelTextColor = colorSchemeKeyTokens;
        DisabledOutlineColor = colorSchemeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.Primary;
        FocusLabelTextColor = colorSchemeKeyTokens2;
        FocusOutlineColor = colorSchemeKeyTokens2;
        HoverLabelTextColor = colorSchemeKeyTokens2;
        ColorSchemeKeyTokens colorSchemeKeyTokens3 = ColorSchemeKeyTokens.Outline;
        HoverOutlineColor = colorSchemeKeyTokens3;
        LabelTextColor = colorSchemeKeyTokens2;
        LabelTextFont = TypographyKeyTokens.LabelLarge;
        OutlineColor = colorSchemeKeyTokens3;
        OutlineWidth = Dp.m2416constructorimpl((float) 1.0d);
        PressedLabelTextColor = colorSchemeKeyTokens2;
        PressedOutlineColor = colorSchemeKeyTokens3;
        DisabledIconColor = colorSchemeKeyTokens;
        FocusIconColor = colorSchemeKeyTokens2;
        HoverIconColor = colorSchemeKeyTokens2;
        IconColor = colorSchemeKeyTokens2;
        IconSize = Dp.m2416constructorimpl((float) 18.0d);
        PressedIconColor = colorSchemeKeyTokens2;
    }
}
