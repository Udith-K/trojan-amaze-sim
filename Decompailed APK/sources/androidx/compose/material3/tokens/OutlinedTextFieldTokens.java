package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: OutlinedTextFieldTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class OutlinedTextFieldTokens {
    private static final ColorSchemeKeyTokens CaretColor;
    private static final float ContainerHeight;
    private static final ShapeKeyTokens ContainerShape;
    private static final ColorSchemeKeyTokens DisabledInputColor;
    private static final ColorSchemeKeyTokens DisabledLabelColor;
    private static final ColorSchemeKeyTokens DisabledLeadingIconColor;
    private static final ColorSchemeKeyTokens DisabledOutlineColor;
    private static final float DisabledOutlineWidth;
    private static final ColorSchemeKeyTokens DisabledSupportingColor;
    private static final ColorSchemeKeyTokens DisabledTrailingIconColor;
    private static final ColorSchemeKeyTokens ErrorFocusCaretColor;
    private static final ColorSchemeKeyTokens ErrorFocusInputColor;
    private static final ColorSchemeKeyTokens ErrorFocusLabelColor;
    private static final ColorSchemeKeyTokens ErrorFocusLeadingIconColor;
    private static final ColorSchemeKeyTokens ErrorFocusOutlineColor;
    private static final ColorSchemeKeyTokens ErrorFocusSupportingColor;
    private static final ColorSchemeKeyTokens ErrorFocusTrailingIconColor;
    private static final ColorSchemeKeyTokens ErrorHoverInputColor;
    private static final ColorSchemeKeyTokens ErrorHoverLabelColor;
    private static final ColorSchemeKeyTokens ErrorHoverLeadingIconColor;
    private static final ColorSchemeKeyTokens ErrorHoverOutlineColor;
    private static final ColorSchemeKeyTokens ErrorHoverSupportingColor;
    private static final ColorSchemeKeyTokens ErrorHoverTrailingIconColor;
    private static final ColorSchemeKeyTokens ErrorInputColor;
    private static final ColorSchemeKeyTokens ErrorLabelColor;
    private static final ColorSchemeKeyTokens ErrorLeadingIconColor;
    private static final ColorSchemeKeyTokens ErrorOutlineColor;
    private static final ColorSchemeKeyTokens ErrorSupportingColor;
    private static final ColorSchemeKeyTokens ErrorTrailingIconColor;
    private static final ColorSchemeKeyTokens FocusInputColor;
    private static final ColorSchemeKeyTokens FocusLabelColor;
    private static final ColorSchemeKeyTokens FocusLeadingIconColor;
    private static final ColorSchemeKeyTokens FocusOutlineColor;
    private static final float FocusOutlineWidth;
    private static final ColorSchemeKeyTokens FocusSupportingColor;
    private static final ColorSchemeKeyTokens FocusTrailingIconColor;
    private static final ColorSchemeKeyTokens HoverInputColor;
    private static final ColorSchemeKeyTokens HoverLabelColor;
    private static final ColorSchemeKeyTokens HoverLeadingIconColor;
    private static final ColorSchemeKeyTokens HoverOutlineColor;
    private static final float HoverOutlineWidth;
    private static final ColorSchemeKeyTokens HoverSupportingColor;
    private static final ColorSchemeKeyTokens HoverTrailingIconColor;
    public static final OutlinedTextFieldTokens INSTANCE = new OutlinedTextFieldTokens();
    private static final ColorSchemeKeyTokens InputColor;
    private static final TypographyKeyTokens InputFont;
    private static final ColorSchemeKeyTokens InputPlaceholderColor;
    private static final ColorSchemeKeyTokens InputPrefixColor;
    private static final ColorSchemeKeyTokens InputSuffixColor;
    private static final ColorSchemeKeyTokens LabelColor;
    private static final TypographyKeyTokens LabelFont;
    private static final ColorSchemeKeyTokens LeadingIconColor;
    private static final float LeadingIconSize;
    private static final ColorSchemeKeyTokens OutlineColor;
    private static final float OutlineWidth;
    private static final ColorSchemeKeyTokens SupportingColor;
    private static final TypographyKeyTokens SupportingFont;
    private static final ColorSchemeKeyTokens TrailingIconColor;
    private static final float TrailingIconSize;

    private OutlinedTextFieldTokens() {
    }

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.Primary;
        CaretColor = colorSchemeKeyTokens;
        ContainerHeight = Dp.m2416constructorimpl((float) 56.0d);
        ContainerShape = ShapeKeyTokens.CornerExtraSmall;
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurface;
        DisabledInputColor = colorSchemeKeyTokens2;
        DisabledLabelColor = colorSchemeKeyTokens2;
        DisabledLeadingIconColor = colorSchemeKeyTokens2;
        DisabledOutlineColor = colorSchemeKeyTokens2;
        float f = (float) 1.0d;
        DisabledOutlineWidth = Dp.m2416constructorimpl(f);
        DisabledSupportingColor = colorSchemeKeyTokens2;
        DisabledTrailingIconColor = colorSchemeKeyTokens2;
        ColorSchemeKeyTokens colorSchemeKeyTokens3 = ColorSchemeKeyTokens.Error;
        ErrorFocusCaretColor = colorSchemeKeyTokens3;
        ErrorFocusInputColor = colorSchemeKeyTokens2;
        ErrorFocusLabelColor = colorSchemeKeyTokens3;
        ColorSchemeKeyTokens colorSchemeKeyTokens4 = ColorSchemeKeyTokens.OnSurfaceVariant;
        ErrorFocusLeadingIconColor = colorSchemeKeyTokens4;
        ErrorFocusOutlineColor = colorSchemeKeyTokens3;
        ErrorFocusSupportingColor = colorSchemeKeyTokens3;
        ErrorFocusTrailingIconColor = colorSchemeKeyTokens3;
        ErrorHoverInputColor = colorSchemeKeyTokens2;
        ColorSchemeKeyTokens colorSchemeKeyTokens5 = ColorSchemeKeyTokens.OnErrorContainer;
        ErrorHoverLabelColor = colorSchemeKeyTokens5;
        ErrorHoverLeadingIconColor = colorSchemeKeyTokens4;
        ErrorHoverOutlineColor = colorSchemeKeyTokens5;
        ErrorHoverSupportingColor = colorSchemeKeyTokens3;
        ErrorHoverTrailingIconColor = colorSchemeKeyTokens5;
        ErrorInputColor = colorSchemeKeyTokens2;
        ErrorLabelColor = colorSchemeKeyTokens3;
        ErrorLeadingIconColor = colorSchemeKeyTokens4;
        ErrorOutlineColor = colorSchemeKeyTokens3;
        ErrorSupportingColor = colorSchemeKeyTokens3;
        ErrorTrailingIconColor = colorSchemeKeyTokens3;
        FocusInputColor = colorSchemeKeyTokens2;
        FocusLabelColor = colorSchemeKeyTokens;
        FocusLeadingIconColor = colorSchemeKeyTokens4;
        FocusOutlineColor = colorSchemeKeyTokens;
        FocusOutlineWidth = Dp.m2416constructorimpl((float) 2.0d);
        FocusSupportingColor = colorSchemeKeyTokens4;
        FocusTrailingIconColor = colorSchemeKeyTokens4;
        HoverInputColor = colorSchemeKeyTokens2;
        HoverLabelColor = colorSchemeKeyTokens2;
        HoverLeadingIconColor = colorSchemeKeyTokens4;
        HoverOutlineColor = colorSchemeKeyTokens2;
        HoverOutlineWidth = Dp.m2416constructorimpl(f);
        HoverSupportingColor = colorSchemeKeyTokens4;
        HoverTrailingIconColor = colorSchemeKeyTokens4;
        InputColor = colorSchemeKeyTokens2;
        TypographyKeyTokens typographyKeyTokens = TypographyKeyTokens.BodyLarge;
        InputFont = typographyKeyTokens;
        InputPlaceholderColor = colorSchemeKeyTokens4;
        InputPrefixColor = colorSchemeKeyTokens4;
        InputSuffixColor = colorSchemeKeyTokens4;
        LabelColor = colorSchemeKeyTokens4;
        LabelFont = typographyKeyTokens;
        LeadingIconColor = colorSchemeKeyTokens4;
        float f2 = (float) 24.0d;
        LeadingIconSize = Dp.m2416constructorimpl(f2);
        OutlineColor = ColorSchemeKeyTokens.Outline;
        OutlineWidth = Dp.m2416constructorimpl(f);
        SupportingColor = colorSchemeKeyTokens4;
        SupportingFont = TypographyKeyTokens.BodySmall;
        TrailingIconColor = colorSchemeKeyTokens4;
        TrailingIconSize = Dp.m2416constructorimpl(f2);
    }

    public final ColorSchemeKeyTokens getCaretColor() {
        return CaretColor;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    public final ColorSchemeKeyTokens getDisabledInputColor() {
        return DisabledInputColor;
    }

    public final ColorSchemeKeyTokens getDisabledLabelColor() {
        return DisabledLabelColor;
    }

    public final ColorSchemeKeyTokens getDisabledLeadingIconColor() {
        return DisabledLeadingIconColor;
    }

    public final ColorSchemeKeyTokens getDisabledOutlineColor() {
        return DisabledOutlineColor;
    }

    public final ColorSchemeKeyTokens getDisabledSupportingColor() {
        return DisabledSupportingColor;
    }

    public final ColorSchemeKeyTokens getDisabledTrailingIconColor() {
        return DisabledTrailingIconColor;
    }

    public final ColorSchemeKeyTokens getErrorFocusCaretColor() {
        return ErrorFocusCaretColor;
    }

    public final ColorSchemeKeyTokens getErrorInputColor() {
        return ErrorInputColor;
    }

    public final ColorSchemeKeyTokens getErrorLabelColor() {
        return ErrorLabelColor;
    }

    public final ColorSchemeKeyTokens getErrorLeadingIconColor() {
        return ErrorLeadingIconColor;
    }

    public final ColorSchemeKeyTokens getErrorOutlineColor() {
        return ErrorOutlineColor;
    }

    public final ColorSchemeKeyTokens getErrorSupportingColor() {
        return ErrorSupportingColor;
    }

    public final ColorSchemeKeyTokens getErrorTrailingIconColor() {
        return ErrorTrailingIconColor;
    }

    public final ColorSchemeKeyTokens getFocusInputColor() {
        return FocusInputColor;
    }

    public final ColorSchemeKeyTokens getFocusLabelColor() {
        return FocusLabelColor;
    }

    public final ColorSchemeKeyTokens getFocusLeadingIconColor() {
        return FocusLeadingIconColor;
    }

    public final ColorSchemeKeyTokens getFocusOutlineColor() {
        return FocusOutlineColor;
    }

    public final ColorSchemeKeyTokens getFocusSupportingColor() {
        return FocusSupportingColor;
    }

    public final ColorSchemeKeyTokens getFocusTrailingIconColor() {
        return FocusTrailingIconColor;
    }

    public final ColorSchemeKeyTokens getInputColor() {
        return InputColor;
    }

    public final ColorSchemeKeyTokens getInputPlaceholderColor() {
        return InputPlaceholderColor;
    }

    public final ColorSchemeKeyTokens getInputPrefixColor() {
        return InputPrefixColor;
    }

    public final ColorSchemeKeyTokens getInputSuffixColor() {
        return InputSuffixColor;
    }

    public final ColorSchemeKeyTokens getLabelColor() {
        return LabelColor;
    }

    public final ColorSchemeKeyTokens getLeadingIconColor() {
        return LeadingIconColor;
    }

    public final ColorSchemeKeyTokens getOutlineColor() {
        return OutlineColor;
    }

    public final ColorSchemeKeyTokens getSupportingColor() {
        return SupportingColor;
    }

    public final ColorSchemeKeyTokens getTrailingIconColor() {
        return TrailingIconColor;
    }
}
