package androidx.compose.foundation.text;

import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.ParagraphKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TextFieldDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextFieldDelegateKt {
    private static final String EmptyTextReplacement = StringsKt.repeat("H", 10);

    public static final String getEmptyTextReplacement() {
        return EmptyTextReplacement;
    }

    public static /* synthetic */ long computeSizeForDefaultText$default(TextStyle textStyle, Density density, FontFamily.Resolver resolver, String str, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            str = EmptyTextReplacement;
        }
        if ((i2 & 16) != 0) {
            i = 1;
        }
        return computeSizeForDefaultText(textStyle, density, resolver, str, i);
    }

    public static final long computeSizeForDefaultText(TextStyle textStyle, Density density, FontFamily.Resolver resolver, String str, int i) {
        Paragraph paragraphM2061ParagraphUdtVg6A = ParagraphKt.m2061ParagraphUdtVg6A(str, textStyle, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null), density, resolver, (64 & 32) != 0 ? CollectionsKt.emptyList() : CollectionsKt.emptyList(), (64 & 64) != 0 ? CollectionsKt.emptyList() : null, (64 & 128) != 0 ? Integer.MAX_VALUE : i, (64 & 256) != 0 ? false : false);
        return IntSizeKt.IntSize(TextDelegateKt.ceilToIntPx(paragraphM2061ParagraphUdtVg6A.getMinIntrinsicWidth()), TextDelegateKt.ceilToIntPx(paragraphM2061ParagraphUdtVg6A.getHeight()));
    }
}
