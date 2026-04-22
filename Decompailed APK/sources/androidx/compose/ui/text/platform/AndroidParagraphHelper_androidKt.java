package androidx.compose.ui.text.platform;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import androidx.compose.ui.text.EmojiSupportMatch;
import androidx.compose.ui.text.PlatformParagraphStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.platform.extensions.PlaceholderExtensions_androidKt;
import androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.emoji2.text.EmojiCompat;
import java.util.List;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidParagraphHelper.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidParagraphHelper_androidKt {
    private static final AndroidParagraphHelper_androidKt$NoopSpan$1 NoopSpan = new CharacterStyle() { // from class: androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt$NoopSpan$1
        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    public static final CharSequence createCharSequence(String str, float f, TextStyle textStyle, List list, List list2, Density density, Function4 function4, boolean z) {
        Spannable spannableString;
        PlatformParagraphStyle paragraphStyle;
        if (z && EmojiCompat.isConfigured()) {
            PlatformTextStyle platformStyle = textStyle.getPlatformStyle();
            EmojiSupportMatch emojiSupportMatchM2037boximpl = (platformStyle == null || (paragraphStyle = platformStyle.getParagraphStyle()) == null) ? null : EmojiSupportMatch.m2037boximpl(paragraphStyle.m2072getEmojiSupportMatch_3YsG6Y());
            int iM2044getAll_3YsG6Y = EmojiSupportMatch.Companion.m2044getAll_3YsG6Y();
            if (emojiSupportMatchM2037boximpl != null) {
                EmojiSupportMatch.m2040equalsimpl0(emojiSupportMatchM2037boximpl.m2043unboximpl(), iM2044getAll_3YsG6Y);
            }
            EmojiCompat.get();
            str.length();
            throw null;
        }
        if (list.isEmpty() && list2.isEmpty() && Intrinsics.areEqual(textStyle.getTextIndent(), TextIndent.Companion.getNone()) && TextUnitKt.m2498isUnspecifiedR2X_6o(textStyle.m2137getLineHeightXSAIIZE())) {
            return str;
        }
        if (str instanceof Spannable) {
            spannableString = (Spannable) str;
        } else {
            spannableString = new SpannableString(str);
        }
        if (Intrinsics.areEqual(textStyle.getTextDecoration(), TextDecoration.Companion.getUnderline())) {
            SpannableExtensions_androidKt.setSpan(spannableString, NoopSpan, 0, str.length());
        }
        if (isIncludeFontPaddingEnabled(textStyle) && textStyle.getLineHeightStyle() == null) {
            SpannableExtensions_androidKt.m2261setLineHeightr9BaKPg(spannableString, textStyle.m2137getLineHeightXSAIIZE(), f, density);
        } else {
            LineHeightStyle lineHeightStyle = textStyle.getLineHeightStyle();
            if (lineHeightStyle == null) {
                lineHeightStyle = LineHeightStyle.Companion.getDefault();
            }
            SpannableExtensions_androidKt.m2260setLineHeightKmRG4DE(spannableString, textStyle.m2137getLineHeightXSAIIZE(), f, density, lineHeightStyle);
        }
        SpannableExtensions_androidKt.setTextIndent(spannableString, textStyle.getTextIndent(), f, density);
        SpannableExtensions_androidKt.setSpanStyles(spannableString, textStyle, list, density, function4);
        PlaceholderExtensions_androidKt.setPlaceholders(spannableString, list2, density);
        return spannableString;
    }

    public static final boolean isIncludeFontPaddingEnabled(TextStyle textStyle) {
        PlatformParagraphStyle paragraphStyle;
        PlatformTextStyle platformStyle = textStyle.getPlatformStyle();
        if (platformStyle == null || (paragraphStyle = platformStyle.getParagraphStyle()) == null) {
            return false;
        }
        return paragraphStyle.getIncludeFontPadding();
    }
}
