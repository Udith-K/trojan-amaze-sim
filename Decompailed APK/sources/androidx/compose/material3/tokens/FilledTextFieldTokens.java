package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: FilledTextFieldTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FilledTextFieldTokens {
    private static final ColorSchemeKeyTokens ActiveIndicatorColor;
    private static final float ActiveIndicatorHeight;
    private static final ColorSchemeKeyTokens CaretColor;
    private static final ColorSchemeKeyTokens ContainerColor;
    private static final ShapeKeyTokens ContainerShape;
    private static final ColorSchemeKeyTokens DisabledActiveIndicatorColor;
    private static final float DisabledActiveIndicatorHeight;
    private static final float DisabledActiveIndicatorOpacity;
    private static final ColorSchemeKeyTokens DisabledContainerColor;
    private static final float DisabledContainerOpacity;
    private static final ColorSchemeKeyTokens DisabledInputColor;
    private static final float DisabledInputOpacity;
    private static final ColorSchemeKeyTokens DisabledLabelColor;
    private static final float DisabledLabelOpacity;
    private static final ColorSchemeKeyTokens DisabledLeadingIconColor;
    private static final float DisabledLeadingIconOpacity;
    private static final ColorSchemeKeyTokens DisabledSupportingColor;
    private static final float DisabledSupportingOpacity;
    private static final ColorSchemeKeyTokens DisabledTrailingIconColor;
    private static final float DisabledTrailingIconOpacity;
    private static final ColorSchemeKeyTokens ErrorActiveIndicatorColor;
    private static final ColorSchemeKeyTokens ErrorFocusActiveIndicatorColor;
    private static final ColorSchemeKeyTokens ErrorFocusCaretColor;
    private static final ColorSchemeKeyTokens ErrorFocusInputColor;
    private static final ColorSchemeKeyTokens ErrorFocusLabelColor;
    private static final ColorSchemeKeyTokens ErrorFocusLeadingIconColor;
    private static final ColorSchemeKeyTokens ErrorFocusSupportingColor;
    private static final ColorSchemeKeyTokens ErrorFocusTrailingIconColor;
    private static final ColorSchemeKeyTokens ErrorHoverActiveIndicatorColor;
    private static final ColorSchemeKeyTokens ErrorHoverInputColor;
    private static final ColorSchemeKeyTokens ErrorHoverLabelColor;
    private static final ColorSchemeKeyTokens ErrorHoverLeadingIconColor;
    private static final ColorSchemeKeyTokens ErrorHoverSupportingColor;
    private static final ColorSchemeKeyTokens ErrorHoverTrailingIconColor;
    private static final ColorSchemeKeyTokens ErrorInputColor;
    private static final ColorSchemeKeyTokens ErrorLabelColor;
    private static final ColorSchemeKeyTokens ErrorLeadingIconColor;
    private static final ColorSchemeKeyTokens ErrorSupportingColor;
    private static final ColorSchemeKeyTokens ErrorTrailingIconColor;
    private static final ColorSchemeKeyTokens FocusActiveIndicatorColor;
    private static final float FocusActiveIndicatorHeight;
    private static final ColorSchemeKeyTokens FocusInputColor;
    private static final ColorSchemeKeyTokens FocusLabelColor;
    private static final ColorSchemeKeyTokens FocusLeadingIconColor;
    private static final ColorSchemeKeyTokens FocusSupportingColor;
    private static final ColorSchemeKeyTokens FocusTrailingIconColor;
    private static final ColorSchemeKeyTokens HoverActiveIndicatorColor;
    private static final float HoverActiveIndicatorHeight;
    private static final ColorSchemeKeyTokens HoverInputColor;
    private static final ColorSchemeKeyTokens HoverLabelColor;
    private static final ColorSchemeKeyTokens HoverLeadingIconColor;
    private static final ColorSchemeKeyTokens HoverSupportingColor;
    private static final ColorSchemeKeyTokens HoverTrailingIconColor;
    public static final FilledTextFieldTokens INSTANCE = new FilledTextFieldTokens();
    private static final ColorSchemeKeyTokens InputColor;
    private static final TypographyKeyTokens InputFont;
    private static final ColorSchemeKeyTokens InputPlaceholderColor;
    private static final ColorSchemeKeyTokens InputPrefixColor;
    private static final ColorSchemeKeyTokens InputSuffixColor;
    private static final ColorSchemeKeyTokens LabelColor;
    private static final TypographyKeyTokens LabelFont;
    private static final ColorSchemeKeyTokens LeadingIconColor;
    private static final float LeadingIconSize;
    private static final ColorSchemeKeyTokens SupportingColor;
    private static final TypographyKeyTokens SupportingFont;
    private static final ColorSchemeKeyTokens TrailingIconColor;
    private static final float TrailingIconSize;

    private FilledTextFieldTokens() {
    }

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurfaceVariant;
        ActiveIndicatorColor = colorSchemeKeyTokens;
        float f = (float) 1.0d;
        ActiveIndicatorHeight = Dp.m2416constructorimpl(f);
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.Primary;
        CaretColor = colorSchemeKeyTokens2;
        ContainerColor = ColorSchemeKeyTokens.SurfaceContainerHighest;
        ContainerShape = ShapeKeyTokens.CornerExtraSmallTop;
        ColorSchemeKeyTokens colorSchemeKeyTokens3 = ColorSchemeKeyTokens.OnSurface;
        DisabledActiveIndicatorColor = colorSchemeKeyTokens3;
        DisabledActiveIndicatorHeight = Dp.m2416constructorimpl(f);
        DisabledActiveIndicatorOpacity = 0.38f;
        DisabledContainerColor = colorSchemeKeyTokens3;
        DisabledContainerOpacity = 0.04f;
        DisabledInputColor = colorSchemeKeyTokens3;
        DisabledInputOpacity = 0.38f;
        DisabledLabelColor = colorSchemeKeyTokens3;
        DisabledLabelOpacity = 0.38f;
        DisabledLeadingIconColor = colorSchemeKeyTokens3;
        DisabledLeadingIconOpacity = 0.38f;
        DisabledSupportingColor = colorSchemeKeyTokens3;
        DisabledSupportingOpacity = 0.38f;
        DisabledTrailingIconColor = colorSchemeKeyTokens3;
        DisabledTrailingIconOpacity = 0.38f;
        ColorSchemeKeyTokens colorSchemeKeyTokens4 = ColorSchemeKeyTokens.Error;
        ErrorActiveIndicatorColor = colorSchemeKeyTokens4;
        ErrorFocusActiveIndicatorColor = colorSchemeKeyTokens4;
        ErrorFocusCaretColor = colorSchemeKeyTokens4;
        ErrorFocusInputColor = colorSchemeKeyTokens3;
        ErrorFocusLabelColor = colorSchemeKeyTokens4;
        ErrorFocusLeadingIconColor = colorSchemeKeyTokens;
        ErrorFocusSupportingColor = colorSchemeKeyTokens4;
        ErrorFocusTrailingIconColor = colorSchemeKeyTokens4;
        ColorSchemeKeyTokens colorSchemeKeyTokens5 = ColorSchemeKeyTokens.OnErrorContainer;
        ErrorHoverActiveIndicatorColor = colorSchemeKeyTokens5;
        ErrorHoverInputColor = colorSchemeKeyTokens3;
        ErrorHoverLabelColor = colorSchemeKeyTokens5;
        ErrorHoverLeadingIconColor = colorSchemeKeyTokens;
        ErrorHoverSupportingColor = colorSchemeKeyTokens4;
        ErrorHoverTrailingIconColor = colorSchemeKeyTokens5;
        ErrorInputColor = colorSchemeKeyTokens3;
        ErrorLabelColor = colorSchemeKeyTokens4;
        ErrorLeadingIconColor = colorSchemeKeyTokens;
        ErrorSupportingColor = colorSchemeKeyTokens4;
        ErrorTrailingIconColor = colorSchemeKeyTokens4;
        FocusActiveIndicatorColor = colorSchemeKeyTokens2;
        FocusActiveIndicatorHeight = Dp.m2416constructorimpl((float) 2.0d);
        FocusInputColor = colorSchemeKeyTokens3;
        FocusLabelColor = colorSchemeKeyTokens2;
        FocusLeadingIconColor = colorSchemeKeyTokens;
        FocusSupportingColor = colorSchemeKeyTokens;
        FocusTrailingIconColor = colorSchemeKeyTokens;
        HoverActiveIndicatorColor = colorSchemeKeyTokens3;
        HoverActiveIndicatorHeight = Dp.m2416constructorimpl(f);
        HoverInputColor = colorSchemeKeyTokens3;
        HoverLabelColor = colorSchemeKeyTokens;
        HoverLeadingIconColor = colorSchemeKeyTokens;
        HoverSupportingColor = colorSchemeKeyTokens;
        HoverTrailingIconColor = colorSchemeKeyTokens;
        InputColor = colorSchemeKeyTokens3;
        TypographyKeyTokens typographyKeyTokens = TypographyKeyTokens.BodyLarge;
        InputFont = typographyKeyTokens;
        InputPlaceholderColor = colorSchemeKeyTokens;
        InputPrefixColor = colorSchemeKeyTokens;
        InputSuffixColor = colorSchemeKeyTokens;
        LabelColor = colorSchemeKeyTokens;
        LabelFont = typographyKeyTokens;
        LeadingIconColor = colorSchemeKeyTokens;
        float f2 = (float) 24.0d;
        LeadingIconSize = Dp.m2416constructorimpl(f2);
        SupportingColor = colorSchemeKeyTokens;
        SupportingFont = TypographyKeyTokens.BodySmall;
        TrailingIconColor = colorSchemeKeyTokens;
        TrailingIconSize = Dp.m2416constructorimpl(f2);
    }

    public final ColorSchemeKeyTokens getActiveIndicatorColor() {
        return ActiveIndicatorColor;
    }

    public final ColorSchemeKeyTokens getCaretColor() {
        return CaretColor;
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    public final ColorSchemeKeyTokens getDisabledActiveIndicatorColor() {
        return DisabledActiveIndicatorColor;
    }

    public final float getDisabledActiveIndicatorOpacity() {
        return DisabledActiveIndicatorOpacity;
    }

    public final ColorSchemeKeyTokens getDisabledInputColor() {
        return DisabledInputColor;
    }

    public final float getDisabledInputOpacity() {
        return DisabledInputOpacity;
    }

    public final ColorSchemeKeyTokens getDisabledLabelColor() {
        return DisabledLabelColor;
    }

    public final float getDisabledLabelOpacity() {
        return DisabledLabelOpacity;
    }

    public final ColorSchemeKeyTokens getDisabledLeadingIconColor() {
        return DisabledLeadingIconColor;
    }

    public final float getDisabledLeadingIconOpacity() {
        return DisabledLeadingIconOpacity;
    }

    public final ColorSchemeKeyTokens getDisabledSupportingColor() {
        return DisabledSupportingColor;
    }

    public final float getDisabledSupportingOpacity() {
        return DisabledSupportingOpacity;
    }

    public final ColorSchemeKeyTokens getDisabledTrailingIconColor() {
        return DisabledTrailingIconColor;
    }

    public final float getDisabledTrailingIconOpacity() {
        return DisabledTrailingIconOpacity;
    }

    public final ColorSchemeKeyTokens getErrorActiveIndicatorColor() {
        return ErrorActiveIndicatorColor;
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

    public final ColorSchemeKeyTokens getErrorSupportingColor() {
        return ErrorSupportingColor;
    }

    public final ColorSchemeKeyTokens getErrorTrailingIconColor() {
        return ErrorTrailingIconColor;
    }

    public final ColorSchemeKeyTokens getFocusActiveIndicatorColor() {
        return FocusActiveIndicatorColor;
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

    public final ColorSchemeKeyTokens getSupportingColor() {
        return SupportingColor;
    }

    public final ColorSchemeKeyTokens getTrailingIconColor() {
        return TrailingIconColor;
    }
}
