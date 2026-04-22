package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.foundation.text.modifiers.MinLinesConstrainer;
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
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiParagraphLayoutCache.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MultiParagraphLayoutCache {
    private int cachedIntrinsicHeight;
    private int cachedIntrinsicHeightInputWidth;
    private Density density;
    private FontFamily.Resolver fontFamilyResolver;
    private LayoutDirection intrinsicsLayoutDirection;
    private long lastDensity;
    private TextLayoutResult layoutCache;
    private MinLinesConstrainer mMinLinesConstrainer;
    private int maxLines;
    private int minLines;
    private int overflow;
    private MultiParagraphIntrinsics paragraphIntrinsics;
    private List placeholders;
    private boolean softWrap;
    private TextStyle style;
    private AnnotatedString text;

    public /* synthetic */ MultiParagraphLayoutCache(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, resolver, i, z, i2, i3, list);
    }

    private MultiParagraphLayoutCache(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, List list) {
        this.text = annotatedString;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        this.placeholders = list;
        this.lastDensity = InlineDensity.Companion.m513getUnspecifiedL26CHvs();
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
    }

    public final Density getDensity$foundation_release() {
        return this.density;
    }

    public final void setDensity$foundation_release(Density density) {
        Density density2 = this.density;
        long jM508constructorimpl = density != null ? InlineDensity.m508constructorimpl(density) : InlineDensity.Companion.m513getUnspecifiedL26CHvs();
        if (density2 == null) {
            this.density = density;
            this.lastDensity = jM508constructorimpl;
        } else if (density == null || !InlineDensity.m509equalsimpl0(this.lastDensity, jM508constructorimpl)) {
            this.density = density;
            this.lastDensity = jM508constructorimpl;
            markDirty();
        }
    }

    public final TextLayoutResult getTextLayoutResult() {
        TextLayoutResult textLayoutResult = this.layoutCache;
        if (textLayoutResult != null) {
            return textLayoutResult;
        }
        throw new IllegalStateException("You must call layoutWithConstraints first");
    }

    public final TextLayoutResult getLayoutOrNull() {
        return this.layoutCache;
    }

    /* JADX INFO: renamed from: layoutWithConstraints-K40F9xA, reason: not valid java name */
    public final boolean m521layoutWithConstraintsK40F9xA(long j, LayoutDirection layoutDirection) {
        if (this.minLines > 1) {
            MinLinesConstrainer.Companion companion = MinLinesConstrainer.Companion;
            MinLinesConstrainer minLinesConstrainer = this.mMinLinesConstrainer;
            TextStyle textStyle = this.style;
            Density density = this.density;
            Intrinsics.checkNotNull(density);
            MinLinesConstrainer minLinesConstrainerFrom = companion.from(minLinesConstrainer, layoutDirection, textStyle, density, this.fontFamilyResolver);
            this.mMinLinesConstrainer = minLinesConstrainerFrom;
            j = minLinesConstrainerFrom.m517coerceMinLinesOh53vG4$foundation_release(j, this.minLines);
        }
        if (!m519newLayoutWillBeDifferentVKLhPVY(this.layoutCache, j, layoutDirection)) {
            TextLayoutResult textLayoutResult = this.layoutCache;
            Intrinsics.checkNotNull(textLayoutResult);
            if (Constraints.m2383equalsimpl0(j, textLayoutResult.getLayoutInput().m2098getConstraintsmsEJaDk())) {
                return false;
            }
            TextLayoutResult textLayoutResult2 = this.layoutCache;
            Intrinsics.checkNotNull(textLayoutResult2);
            this.layoutCache = m520textLayoutResultVKLhPVY(layoutDirection, j, textLayoutResult2.getMultiParagraph());
            return true;
        }
        this.layoutCache = m520textLayoutResultVKLhPVY(layoutDirection, j, m518layoutTextK40F9xA(j, layoutDirection));
        return true;
    }

    /* JADX INFO: renamed from: textLayoutResult-VKLhPVY, reason: not valid java name */
    private final TextLayoutResult m520textLayoutResultVKLhPVY(LayoutDirection layoutDirection, long j, MultiParagraph multiParagraph) {
        float fMin = Math.min(multiParagraph.getIntrinsics().getMaxIntrinsicWidth(), multiParagraph.getWidth());
        AnnotatedString annotatedString = this.text;
        TextStyle textStyle = this.style;
        List listEmptyList = this.placeholders;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List list = listEmptyList;
        int i = this.maxLines;
        boolean z = this.softWrap;
        int i2 = this.overflow;
        Density density = this.density;
        Intrinsics.checkNotNull(density);
        return new TextLayoutResult(new TextLayoutInput(annotatedString, textStyle, list, i, z, i2, density, layoutDirection, this.fontFamilyResolver, j, (DefaultConstructorMarker) null), multiParagraph, ConstraintsKt.m2400constrain4WqzIAM(j, IntSizeKt.IntSize(TextDelegateKt.ceilToIntPx(fMin), TextDelegateKt.ceilToIntPx(multiParagraph.getHeight()))), null);
    }

    public final int intrinsicHeight(int i, LayoutDirection layoutDirection) {
        int i2 = this.cachedIntrinsicHeightInputWidth;
        int i3 = this.cachedIntrinsicHeight;
        if (i == i2 && i2 != -1) {
            return i3;
        }
        int iCeilToIntPx = TextDelegateKt.ceilToIntPx(m518layoutTextK40F9xA(ConstraintsKt.Constraints(0, i, 0, Integer.MAX_VALUE), layoutDirection).getHeight());
        this.cachedIntrinsicHeightInputWidth = i;
        this.cachedIntrinsicHeight = iCeilToIntPx;
        return iCeilToIntPx;
    }

    /* JADX INFO: renamed from: update-ZNqEYIc, reason: not valid java name */
    public final void m522updateZNqEYIc(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, List list) {
        this.text = annotatedString;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        this.placeholders = list;
        markDirty();
    }

    private final MultiParagraphIntrinsics setLayoutDirection(LayoutDirection layoutDirection) {
        MultiParagraphIntrinsics multiParagraphIntrinsics = this.paragraphIntrinsics;
        if (multiParagraphIntrinsics == null || layoutDirection != this.intrinsicsLayoutDirection || multiParagraphIntrinsics.getHasStaleResolvedFonts()) {
            this.intrinsicsLayoutDirection = layoutDirection;
            AnnotatedString annotatedString = this.text;
            TextStyle textStyleResolveDefaults = TextStyleKt.resolveDefaults(this.style, layoutDirection);
            Density density = this.density;
            Intrinsics.checkNotNull(density);
            FontFamily.Resolver resolver = this.fontFamilyResolver;
            List listEmptyList = this.placeholders;
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            multiParagraphIntrinsics = new MultiParagraphIntrinsics(annotatedString, textStyleResolveDefaults, listEmptyList, density, resolver);
        }
        this.paragraphIntrinsics = multiParagraphIntrinsics;
        return multiParagraphIntrinsics;
    }

    /* JADX INFO: renamed from: layoutText-K40F9xA, reason: not valid java name */
    private final MultiParagraph m518layoutTextK40F9xA(long j, LayoutDirection layoutDirection) {
        MultiParagraphIntrinsics layoutDirection2 = setLayoutDirection(layoutDirection);
        return new MultiParagraph(layoutDirection2, LayoutUtilsKt.m514finalConstraintstfFHcEY(j, this.softWrap, this.overflow, layoutDirection2.getMaxIntrinsicWidth()), LayoutUtilsKt.m515finalMaxLinesxdlQI24(this.softWrap, this.overflow, this.maxLines), TextOverflow.m2372equalsimpl0(this.overflow, TextOverflow.Companion.m2376getEllipsisgIe3tQ8()), null);
    }

    /* JADX INFO: renamed from: newLayoutWillBeDifferent-VKLhPVY, reason: not valid java name */
    private final boolean m519newLayoutWillBeDifferentVKLhPVY(TextLayoutResult textLayoutResult, long j, LayoutDirection layoutDirection) {
        if (textLayoutResult == null || textLayoutResult.getMultiParagraph().getIntrinsics().getHasStaleResolvedFonts() || layoutDirection != textLayoutResult.getLayoutInput().getLayoutDirection()) {
            return true;
        }
        if (Constraints.m2383equalsimpl0(j, textLayoutResult.getLayoutInput().m2098getConstraintsmsEJaDk())) {
            return false;
        }
        return Constraints.m2389getMaxWidthimpl(j) != Constraints.m2389getMaxWidthimpl(textLayoutResult.getLayoutInput().m2098getConstraintsmsEJaDk()) || ((float) Constraints.m2388getMaxHeightimpl(j)) < textLayoutResult.getMultiParagraph().getHeight() || textLayoutResult.getMultiParagraph().getDidExceedMaxLines();
    }

    private final void markDirty() {
        this.paragraphIntrinsics = null;
        this.layoutCache = null;
        this.cachedIntrinsicHeight = -1;
        this.cachedIntrinsicHeightInputWidth = -1;
    }

    public final int maxIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMaxIntrinsicWidth());
    }

    public final int minIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMinIntrinsicWidth());
    }
}
