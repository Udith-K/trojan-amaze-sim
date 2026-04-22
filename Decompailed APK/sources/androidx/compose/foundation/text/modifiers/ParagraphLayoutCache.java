package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.foundation.text.modifiers.MinLinesConstrainer;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.ParagraphIntrinsicsKt;
import androidx.compose.ui.text.ParagraphKt;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import ch.qos.logback.core.CoreConstants;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParagraphLayoutCache.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ParagraphLayoutCache {
    private int cachedIntrinsicHeight;
    private int cachedIntrinsicHeightInputWidth;
    private Density density;
    private boolean didOverflow;
    private FontFamily.Resolver fontFamilyResolver;
    private LayoutDirection intrinsicsLayoutDirection;
    private long lastDensity;
    private long layoutSize;
    private MinLinesConstrainer mMinLinesConstrainer;
    private int maxLines;
    private int minLines;
    private int overflow;
    private Paragraph paragraph;
    private ParagraphIntrinsics paragraphIntrinsics;
    private long prevConstraints;
    private boolean softWrap;
    private TextStyle style;
    private String text;

    public /* synthetic */ ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, i, z, i2, i3);
    }

    private ParagraphLayoutCache(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3) {
        this.text = str;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        this.lastDensity = InlineDensity.Companion.m513getUnspecifiedL26CHvs();
        this.layoutSize = IntSizeKt.IntSize(0, 0);
        this.prevConstraints = Constraints.Companion.m2398fixedJhjzzOo(0, 0);
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

    public final Unit getObserveFontChanges$foundation_release() {
        ParagraphIntrinsics paragraphIntrinsics = this.paragraphIntrinsics;
        if (paragraphIntrinsics != null) {
            paragraphIntrinsics.getHasStaleResolvedFonts();
        }
        return Unit.INSTANCE;
    }

    public final Paragraph getParagraph$foundation_release() {
        return this.paragraph;
    }

    public final boolean getDidOverflow$foundation_release() {
        return this.didOverflow;
    }

    /* JADX INFO: renamed from: getLayoutSize-YbymL2g$foundation_release, reason: not valid java name */
    public final long m525getLayoutSizeYbymL2g$foundation_release() {
        return this.layoutSize;
    }

    /* JADX INFO: renamed from: layoutWithConstraints-K40F9xA, reason: not valid java name */
    public final boolean m526layoutWithConstraintsK40F9xA(long j, LayoutDirection layoutDirection) {
        boolean z = true;
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
        boolean z2 = false;
        if (!m524newLayoutWillBeDifferentK40F9xA(j, layoutDirection)) {
            if (!Constraints.m2383equalsimpl0(j, this.prevConstraints)) {
                Paragraph paragraph = this.paragraph;
                Intrinsics.checkNotNull(paragraph);
                this.layoutSize = ConstraintsKt.m2400constrain4WqzIAM(j, IntSizeKt.IntSize(TextDelegateKt.ceilToIntPx(Math.min(paragraph.getMaxIntrinsicWidth(), paragraph.getWidth())), TextDelegateKt.ceilToIntPx(paragraph.getHeight())));
                if (TextOverflow.m2372equalsimpl0(this.overflow, TextOverflow.Companion.m2377getVisiblegIe3tQ8()) || (IntSize.m2476getWidthimpl(r3) >= paragraph.getWidth() && IntSize.m2475getHeightimpl(r3) >= paragraph.getHeight())) {
                    z = false;
                }
                this.didOverflow = z;
                this.prevConstraints = j;
            }
            return false;
        }
        Paragraph paragraphM523layoutTextK40F9xA = m523layoutTextK40F9xA(j, layoutDirection);
        this.prevConstraints = j;
        this.layoutSize = ConstraintsKt.m2400constrain4WqzIAM(j, IntSizeKt.IntSize(TextDelegateKt.ceilToIntPx(paragraphM523layoutTextK40F9xA.getWidth()), TextDelegateKt.ceilToIntPx(paragraphM523layoutTextK40F9xA.getHeight())));
        if (!TextOverflow.m2372equalsimpl0(this.overflow, TextOverflow.Companion.m2377getVisiblegIe3tQ8()) && (IntSize.m2476getWidthimpl(r9) < paragraphM523layoutTextK40F9xA.getWidth() || IntSize.m2475getHeightimpl(r9) < paragraphM523layoutTextK40F9xA.getHeight())) {
            z2 = true;
        }
        this.didOverflow = z2;
        this.paragraph = paragraphM523layoutTextK40F9xA;
        return true;
    }

    public final int intrinsicHeight(int i, LayoutDirection layoutDirection) {
        int i2 = this.cachedIntrinsicHeightInputWidth;
        int i3 = this.cachedIntrinsicHeight;
        if (i == i2 && i2 != -1) {
            return i3;
        }
        int iCeilToIntPx = TextDelegateKt.ceilToIntPx(m523layoutTextK40F9xA(ConstraintsKt.Constraints(0, i, 0, Integer.MAX_VALUE), layoutDirection).getHeight());
        this.cachedIntrinsicHeightInputWidth = i;
        this.cachedIntrinsicHeight = iCeilToIntPx;
        return iCeilToIntPx;
    }

    /* JADX INFO: renamed from: update-L6sJoHM, reason: not valid java name */
    public final void m527updateL6sJoHM(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3) {
        this.text = str;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        markDirty();
    }

    private final ParagraphIntrinsics setLayoutDirection(LayoutDirection layoutDirection) {
        ParagraphIntrinsics paragraphIntrinsicsParagraphIntrinsics$default = this.paragraphIntrinsics;
        if (paragraphIntrinsicsParagraphIntrinsics$default == null || layoutDirection != this.intrinsicsLayoutDirection || paragraphIntrinsicsParagraphIntrinsics$default.getHasStaleResolvedFonts()) {
            this.intrinsicsLayoutDirection = layoutDirection;
            String str = this.text;
            TextStyle textStyleResolveDefaults = TextStyleKt.resolveDefaults(this.style, layoutDirection);
            Density density = this.density;
            Intrinsics.checkNotNull(density);
            paragraphIntrinsicsParagraphIntrinsics$default = ParagraphIntrinsicsKt.ParagraphIntrinsics$default(str, textStyleResolveDefaults, null, null, density, this.fontFamilyResolver, 12, null);
        }
        this.paragraphIntrinsics = paragraphIntrinsicsParagraphIntrinsics$default;
        return paragraphIntrinsicsParagraphIntrinsics$default;
    }

    /* JADX INFO: renamed from: layoutText-K40F9xA, reason: not valid java name */
    private final Paragraph m523layoutTextK40F9xA(long j, LayoutDirection layoutDirection) {
        ParagraphIntrinsics layoutDirection2 = setLayoutDirection(layoutDirection);
        return ParagraphKt.m2063Paragraph_EkL_Y(layoutDirection2, LayoutUtilsKt.m514finalConstraintstfFHcEY(j, this.softWrap, this.overflow, layoutDirection2.getMaxIntrinsicWidth()), LayoutUtilsKt.m515finalMaxLinesxdlQI24(this.softWrap, this.overflow, this.maxLines), TextOverflow.m2372equalsimpl0(this.overflow, TextOverflow.Companion.m2376getEllipsisgIe3tQ8()));
    }

    /* JADX INFO: renamed from: newLayoutWillBeDifferent-K40F9xA, reason: not valid java name */
    private final boolean m524newLayoutWillBeDifferentK40F9xA(long j, LayoutDirection layoutDirection) {
        ParagraphIntrinsics paragraphIntrinsics;
        Paragraph paragraph = this.paragraph;
        if (paragraph == null || (paragraphIntrinsics = this.paragraphIntrinsics) == null || paragraphIntrinsics.getHasStaleResolvedFonts() || layoutDirection != this.intrinsicsLayoutDirection) {
            return true;
        }
        if (Constraints.m2383equalsimpl0(j, this.prevConstraints)) {
            return false;
        }
        return Constraints.m2389getMaxWidthimpl(j) != Constraints.m2389getMaxWidthimpl(this.prevConstraints) || ((float) Constraints.m2388getMaxHeightimpl(j)) < paragraph.getHeight() || paragraph.getDidExceedMaxLines();
    }

    private final void markDirty() {
        this.paragraph = null;
        this.paragraphIntrinsics = null;
        this.intrinsicsLayoutDirection = null;
        this.cachedIntrinsicHeightInputWidth = -1;
        this.cachedIntrinsicHeight = -1;
        this.prevConstraints = Constraints.Companion.m2398fixedJhjzzOo(0, 0);
        this.layoutSize = IntSizeKt.IntSize(0, 0);
        this.didOverflow = false;
    }

    public final TextLayoutResult slowCreateTextLayoutResultOrNull(TextStyle textStyle) {
        Density density;
        LayoutDirection layoutDirection = this.intrinsicsLayoutDirection;
        if (layoutDirection == null || (density = this.density) == null) {
            return null;
        }
        AnnotatedString annotatedString = new AnnotatedString(this.text, null, null, 6, null);
        if (this.paragraph == null || this.paragraphIntrinsics == null) {
            return null;
        }
        long jM2381copyZbe2FdA$default = Constraints.m2381copyZbe2FdA$default(this.prevConstraints, 0, 0, 0, 0, 10, null);
        return new TextLayoutResult(new TextLayoutInput(annotatedString, textStyle, CollectionsKt.emptyList(), this.maxLines, this.softWrap, this.overflow, density, layoutDirection, this.fontFamilyResolver, jM2381copyZbe2FdA$default, (DefaultConstructorMarker) null), new MultiParagraph(new MultiParagraphIntrinsics(annotatedString, textStyle, CollectionsKt.emptyList(), density, this.fontFamilyResolver), jM2381copyZbe2FdA$default, this.maxLines, TextOverflow.m2372equalsimpl0(this.overflow, TextOverflow.Companion.m2376getEllipsisgIe3tQ8()), null), this.layoutSize, null);
    }

    public final int minIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMinIntrinsicWidth());
    }

    public final int maxIntrinsicWidth(LayoutDirection layoutDirection) {
        return TextDelegateKt.ceilToIntPx(setLayoutDirection(layoutDirection).getMaxIntrinsicWidth());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ParagraphLayoutCache(paragraph=");
        sb.append(this.paragraph != null ? "<paragraph>" : "null");
        sb.append(", lastDensity=");
        sb.append((Object) InlineDensity.m512toStringimpl(this.lastDensity));
        sb.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
        return sb.toString();
    }
}
