package androidx.compose.foundation.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextDelegate {
    public static final Companion Companion = new Companion(null);
    private final Density density;
    private final FontFamily.Resolver fontFamilyResolver;
    private LayoutDirection intrinsicsLayoutDirection;
    private final int maxLines;
    private final int minLines;
    private final int overflow;
    private MultiParagraphIntrinsics paragraphIntrinsics;
    private final List placeholders;
    private final boolean softWrap;
    private final TextStyle style;
    private final AnnotatedString text;

    public /* synthetic */ TextDelegate(AnnotatedString annotatedString, TextStyle textStyle, int i, int i2, boolean z, int i3, Density density, FontFamily.Resolver resolver, List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, i, i2, z, i3, density, resolver, list);
    }

    private TextDelegate(AnnotatedString annotatedString, TextStyle textStyle, int i, int i2, boolean z, int i3, Density density, FontFamily.Resolver resolver, List list) {
        this.text = annotatedString;
        this.style = textStyle;
        this.maxLines = i;
        this.minLines = i2;
        this.softWrap = z;
        this.overflow = i3;
        this.density = density;
        this.fontFamilyResolver = resolver;
        this.placeholders = list;
        if (i <= 0) {
            throw new IllegalArgumentException("no maxLines");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("no minLines");
        }
        if (i2 > i) {
            throw new IllegalArgumentException("minLines greater than maxLines");
        }
    }

    public final AnnotatedString getText() {
        return this.text;
    }

    public final TextStyle getStyle() {
        return this.style;
    }

    public final int getMaxLines() {
        return this.maxLines;
    }

    public final int getMinLines() {
        return this.minLines;
    }

    public final boolean getSoftWrap() {
        return this.softWrap;
    }

    public /* synthetic */ TextDelegate(AnnotatedString annotatedString, TextStyle textStyle, int i, int i2, boolean z, int i3, Density density, FontFamily.Resolver resolver, List list, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, (i4 & 4) != 0 ? Integer.MAX_VALUE : i, (i4 & 8) != 0 ? 1 : i2, (i4 & 16) != 0 ? true : z, (i4 & 32) != 0 ? TextOverflow.Companion.m2375getClipgIe3tQ8() : i3, density, resolver, (i4 & 256) != 0 ? CollectionsKt.emptyList() : list, null);
    }

    /* JADX INFO: renamed from: getOverflow-gIe3tQ8, reason: not valid java name */
    public final int m429getOverflowgIe3tQ8() {
        return this.overflow;
    }

    public final Density getDensity() {
        return this.density;
    }

    public final FontFamily.Resolver getFontFamilyResolver() {
        return this.fontFamilyResolver;
    }

    public final List getPlaceholders() {
        return this.placeholders;
    }

    private final MultiParagraphIntrinsics getNonNullIntrinsics() {
        MultiParagraphIntrinsics multiParagraphIntrinsics = this.paragraphIntrinsics;
        if (multiParagraphIntrinsics != null) {
            return multiParagraphIntrinsics;
        }
        throw new IllegalStateException("layoutIntrinsics must be called first");
    }

    public final int getMaxIntrinsicWidth() {
        return TextDelegateKt.ceilToIntPx(getNonNullIntrinsics().getMaxIntrinsicWidth());
    }

    public final void layoutIntrinsics(LayoutDirection layoutDirection) {
        MultiParagraphIntrinsics multiParagraphIntrinsics = this.paragraphIntrinsics;
        if (multiParagraphIntrinsics == null || layoutDirection != this.intrinsicsLayoutDirection || multiParagraphIntrinsics.getHasStaleResolvedFonts()) {
            this.intrinsicsLayoutDirection = layoutDirection;
            multiParagraphIntrinsics = new MultiParagraphIntrinsics(this.text, TextStyleKt.resolveDefaults(this.style, layoutDirection), this.placeholders, this.density, this.fontFamilyResolver);
        }
        this.paragraphIntrinsics = multiParagraphIntrinsics;
    }

    /* JADX INFO: renamed from: layoutText-K40F9xA, reason: not valid java name */
    private final MultiParagraph m428layoutTextK40F9xA(long j, LayoutDirection layoutDirection) {
        layoutIntrinsics(layoutDirection);
        int iM2391getMinWidthimpl = Constraints.m2391getMinWidthimpl(j);
        int iM2389getMaxWidthimpl = ((this.softWrap || TextOverflow.m2372equalsimpl0(this.overflow, TextOverflow.Companion.m2376getEllipsisgIe3tQ8())) && Constraints.m2385getHasBoundedWidthimpl(j)) ? Constraints.m2389getMaxWidthimpl(j) : Integer.MAX_VALUE;
        int i = (this.softWrap || !TextOverflow.m2372equalsimpl0(this.overflow, TextOverflow.Companion.m2376getEllipsisgIe3tQ8())) ? this.maxLines : 1;
        if (iM2391getMinWidthimpl != iM2389getMaxWidthimpl) {
            iM2389getMaxWidthimpl = RangesKt.coerceIn(getMaxIntrinsicWidth(), iM2391getMinWidthimpl, iM2389getMaxWidthimpl);
        }
        return new MultiParagraph(getNonNullIntrinsics(), Constraints.Companion.m2397fitPrioritizingWidthZbe2FdA(0, iM2389getMaxWidthimpl, 0, Constraints.m2388getMaxHeightimpl(j)), i, TextOverflow.m2372equalsimpl0(this.overflow, TextOverflow.Companion.m2376getEllipsisgIe3tQ8()), null);
    }

    /* JADX INFO: renamed from: layout-NN6Ew-U, reason: not valid java name */
    public final TextLayoutResult m430layoutNN6EwU(long j, LayoutDirection layoutDirection, TextLayoutResult textLayoutResult) {
        if (textLayoutResult != null && TextLayoutHelperKt.m457canReuse7_7YC6M(textLayoutResult, this.text, this.style, this.placeholders, this.maxLines, this.softWrap, this.overflow, this.density, layoutDirection, this.fontFamilyResolver, j)) {
            return textLayoutResult.m2101copyO0kMr_c(new TextLayoutInput(textLayoutResult.getLayoutInput().getText(), this.style, textLayoutResult.getLayoutInput().getPlaceholders(), textLayoutResult.getLayoutInput().getMaxLines(), textLayoutResult.getLayoutInput().getSoftWrap(), textLayoutResult.getLayoutInput().m2099getOverflowgIe3tQ8(), textLayoutResult.getLayoutInput().getDensity(), textLayoutResult.getLayoutInput().getLayoutDirection(), textLayoutResult.getLayoutInput().getFontFamilyResolver(), j, (DefaultConstructorMarker) null), ConstraintsKt.m2400constrain4WqzIAM(j, IntSizeKt.IntSize(TextDelegateKt.ceilToIntPx(textLayoutResult.getMultiParagraph().getWidth()), TextDelegateKt.ceilToIntPx(textLayoutResult.getMultiParagraph().getHeight()))));
        }
        MultiParagraph multiParagraphM428layoutTextK40F9xA = m428layoutTextK40F9xA(j, layoutDirection);
        return new TextLayoutResult(new TextLayoutInput(this.text, this.style, this.placeholders, this.maxLines, this.softWrap, this.overflow, this.density, layoutDirection, this.fontFamilyResolver, j, (DefaultConstructorMarker) null), multiParagraphM428layoutTextK40F9xA, ConstraintsKt.m2400constrain4WqzIAM(j, IntSizeKt.IntSize(TextDelegateKt.ceilToIntPx(multiParagraphM428layoutTextK40F9xA.getWidth()), TextDelegateKt.ceilToIntPx(multiParagraphM428layoutTextK40F9xA.getHeight()))), null);
    }

    /* JADX INFO: compiled from: TextDelegate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
