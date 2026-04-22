package androidx.compose.ui.text.platform;

import androidx.compose.ui.text.EmojiSupportMatch;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.PlatformParagraphStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.unit.Density;
import androidx.core.text.TextUtilsCompat;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: AndroidParagraphIntrinsics.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidParagraphIntrinsics_androidKt {
    /* JADX INFO: renamed from: resolveTextDirectionHeuristics-HklW4sA, reason: not valid java name */
    public static final int m2247resolveTextDirectionHeuristicsHklW4sA(int i, LocaleList localeList) {
        Locale platformLocale;
        TextDirection.Companion companion = TextDirection.Companion;
        if (TextDirection.m2350equalsimpl0(i, companion.m2355getContentOrLtrs_7Xco())) {
            return 2;
        }
        if (!TextDirection.m2350equalsimpl0(i, companion.m2356getContentOrRtls_7Xco())) {
            if (TextDirection.m2350equalsimpl0(i, companion.m2357getLtrs_7Xco())) {
                return 0;
            }
            if (TextDirection.m2350equalsimpl0(i, companion.m2358getRtls_7Xco())) {
                return 1;
            }
            if (TextDirection.m2350equalsimpl0(i, companion.m2354getContents_7Xco()) ? true : TextDirection.m2350equalsimpl0(i, companion.m2359getUnspecifieds_7Xco())) {
                if (localeList == null || (platformLocale = localeList.get(0).getPlatformLocale()) == null) {
                    platformLocale = Locale.getDefault();
                }
                int layoutDirectionFromLocale = TextUtilsCompat.getLayoutDirectionFromLocale(platformLocale);
                if (layoutDirectionFromLocale == 0 || layoutDirectionFromLocale != 1) {
                    return 2;
                }
            } else {
                throw new IllegalStateException("Invalid TextDirection.");
            }
        }
        return 3;
    }

    public static final ParagraphIntrinsics ActualParagraphIntrinsics(String str, TextStyle textStyle, List list, List list2, Density density, FontFamily.Resolver resolver) {
        return new AndroidParagraphIntrinsics(str, textStyle, list, list2, resolver, density);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getHasEmojiCompat(TextStyle textStyle) {
        PlatformParagraphStyle paragraphStyle;
        PlatformTextStyle platformStyle = textStyle.getPlatformStyle();
        return !(((platformStyle == null || (paragraphStyle = platformStyle.getParagraphStyle()) == null) ? null : EmojiSupportMatch.m2037boximpl(paragraphStyle.m2072getEmojiSupportMatch_3YsG6Y())) == null ? false : EmojiSupportMatch.m2040equalsimpl0(r1.m2043unboximpl(), EmojiSupportMatch.Companion.m2046getNone_3YsG6Y()));
    }
}
