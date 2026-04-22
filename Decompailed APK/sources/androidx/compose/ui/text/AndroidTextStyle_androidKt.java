package androidx.compose.ui.text;

/* JADX INFO: compiled from: AndroidTextStyle.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidTextStyle_androidKt {
    public static final PlatformSpanStyle lerp(PlatformSpanStyle platformSpanStyle, PlatformSpanStyle platformSpanStyle2, float f) {
        return platformSpanStyle;
    }

    public static final PlatformTextStyle createPlatformTextStyle(PlatformSpanStyle platformSpanStyle, PlatformParagraphStyle platformParagraphStyle) {
        return new PlatformTextStyle(platformSpanStyle, platformParagraphStyle);
    }

    public static final PlatformParagraphStyle lerp(PlatformParagraphStyle platformParagraphStyle, PlatformParagraphStyle platformParagraphStyle2, float f) {
        return platformParagraphStyle.getIncludeFontPadding() == platformParagraphStyle2.getIncludeFontPadding() ? platformParagraphStyle : new PlatformParagraphStyle(((EmojiSupportMatch) SpanStyleKt.lerpDiscrete(EmojiSupportMatch.m2037boximpl(platformParagraphStyle.m2072getEmojiSupportMatch_3YsG6Y()), EmojiSupportMatch.m2037boximpl(platformParagraphStyle2.m2072getEmojiSupportMatch_3YsG6Y()), f)).m2043unboximpl(), ((Boolean) SpanStyleKt.lerpDiscrete(Boolean.valueOf(platformParagraphStyle.getIncludeFontPadding()), Boolean.valueOf(platformParagraphStyle2.getIncludeFontPadding()), f)).booleanValue(), null);
    }
}
