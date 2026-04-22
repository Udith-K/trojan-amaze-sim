package androidx.compose.foundation.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextDelegateKt {
    public static final int ceilToIntPx(float f) {
        return Math.round((float) Math.ceil(f));
    }

    /* JADX INFO: renamed from: updateTextDelegate-rm0N8CA, reason: not valid java name */
    public static final TextDelegate m431updateTextDelegaterm0N8CA(TextDelegate textDelegate, AnnotatedString annotatedString, TextStyle textStyle, Density density, FontFamily.Resolver resolver, boolean z, int i, int i2, int i3, List list) {
        if (Intrinsics.areEqual(textDelegate.getText(), annotatedString) && Intrinsics.areEqual(textDelegate.getStyle(), textStyle) && textDelegate.getSoftWrap() == z) {
            if (TextOverflow.m2372equalsimpl0(textDelegate.m429getOverflowgIe3tQ8(), i)) {
                if (textDelegate.getMaxLines() == i2) {
                    if (textDelegate.getMinLines() == i3 && Intrinsics.areEqual(textDelegate.getDensity(), density)) {
                        if (Intrinsics.areEqual(textDelegate.getPlaceholders(), list) && textDelegate.getFontFamilyResolver() == resolver) {
                            return textDelegate;
                        }
                        return new TextDelegate(annotatedString, textStyle, i2, i3, z, i, density, resolver, list, null);
                    }
                    return new TextDelegate(annotatedString, textStyle, i2, i3, z, i, density, resolver, list, null);
                }
                return new TextDelegate(annotatedString, textStyle, i2, i3, z, i, density, resolver, list, null);
            }
            return new TextDelegate(annotatedString, textStyle, i2, i3, z, i, density, resolver, list, null);
        }
        return new TextDelegate(annotatedString, textStyle, i2, i3, z, i, density, resolver, list, null);
    }
}
