package androidx.compose.foundation.text;

import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldSize.kt */
/* JADX INFO: loaded from: classes.dex */
final class TextFieldSize {
    private Density density;
    private FontFamily.Resolver fontFamilyResolver;
    private LayoutDirection layoutDirection;
    private long minSize = m454computeMinSizeYbymL2g();
    private TextStyle resolvedStyle;
    private Object typeface;

    public TextFieldSize(LayoutDirection layoutDirection, Density density, FontFamily.Resolver resolver, TextStyle textStyle, Object obj) {
        this.layoutDirection = layoutDirection;
        this.density = density;
        this.fontFamilyResolver = resolver;
        this.resolvedStyle = textStyle;
        this.typeface = obj;
    }

    /* JADX INFO: renamed from: getMinSize-YbymL2g, reason: not valid java name */
    public final long m455getMinSizeYbymL2g() {
        return this.minSize;
    }

    public final void update(LayoutDirection layoutDirection, Density density, FontFamily.Resolver resolver, TextStyle textStyle, Object obj) {
        if (layoutDirection == this.layoutDirection && Intrinsics.areEqual(density, this.density) && Intrinsics.areEqual(resolver, this.fontFamilyResolver) && Intrinsics.areEqual(textStyle, this.resolvedStyle) && Intrinsics.areEqual(obj, this.typeface)) {
            return;
        }
        this.layoutDirection = layoutDirection;
        this.density = density;
        this.fontFamilyResolver = resolver;
        this.resolvedStyle = textStyle;
        this.typeface = obj;
        this.minSize = m454computeMinSizeYbymL2g();
    }

    /* JADX INFO: renamed from: computeMinSize-YbymL2g, reason: not valid java name */
    private final long m454computeMinSizeYbymL2g() {
        return TextFieldDelegateKt.computeSizeForDefaultText$default(this.resolvedStyle, this.density, this.fontFamilyResolver, null, 0, 24, null);
    }
}
