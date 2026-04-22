package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import ch.qos.logback.core.CoreConstants;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextLayoutResult.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextLayoutResult {
    public static final int $stable = 8;
    private final float firstBaseline;
    private final float lastBaseline;
    private final TextLayoutInput layoutInput;
    private final MultiParagraph multiParagraph;
    private final List placeholderRects;
    private final long size;

    public /* synthetic */ TextLayoutResult(TextLayoutInput textLayoutInput, MultiParagraph multiParagraph, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(textLayoutInput, multiParagraph, j);
    }

    private TextLayoutResult(TextLayoutInput textLayoutInput, MultiParagraph multiParagraph, long j) {
        this.layoutInput = textLayoutInput;
        this.multiParagraph = multiParagraph;
        this.size = j;
        this.firstBaseline = multiParagraph.getFirstBaseline();
        this.lastBaseline = multiParagraph.getLastBaseline();
        this.placeholderRects = multiParagraph.getPlaceholderRects();
    }

    public final TextLayoutInput getLayoutInput() {
        return this.layoutInput;
    }

    public final MultiParagraph getMultiParagraph() {
        return this.multiParagraph;
    }

    /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name */
    public final long m2103getSizeYbymL2g() {
        return this.size;
    }

    public final float getFirstBaseline() {
        return this.firstBaseline;
    }

    public final float getLastBaseline() {
        return this.lastBaseline;
    }

    public final boolean getDidOverflowHeight() {
        return this.multiParagraph.getDidExceedMaxLines() || ((float) IntSize.m2475getHeightimpl(this.size)) < this.multiParagraph.getHeight();
    }

    public final boolean getDidOverflowWidth() {
        return ((float) IntSize.m2476getWidthimpl(this.size)) < this.multiParagraph.getWidth();
    }

    public final boolean getHasVisualOverflow() {
        return getDidOverflowWidth() || getDidOverflowHeight();
    }

    public final List getPlaceholderRects() {
        return this.placeholderRects;
    }

    public final int getLineCount() {
        return this.multiParagraph.getLineCount();
    }

    public final int getLineStart(int i) {
        return this.multiParagraph.getLineStart(i);
    }

    public static /* synthetic */ int getLineEnd$default(TextLayoutResult textLayoutResult, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return textLayoutResult.getLineEnd(i, z);
    }

    public final int getLineEnd(int i, boolean z) {
        return this.multiParagraph.getLineEnd(i, z);
    }

    public final float getLineTop(int i) {
        return this.multiParagraph.getLineTop(i);
    }

    public final float getLineBottom(int i) {
        return this.multiParagraph.getLineBottom(i);
    }

    public final float getLineLeft(int i) {
        return this.multiParagraph.getLineLeft(i);
    }

    public final float getLineRight(int i) {
        return this.multiParagraph.getLineRight(i);
    }

    public final int getLineForOffset(int i) {
        return this.multiParagraph.getLineForOffset(i);
    }

    public final int getLineForVerticalPosition(float f) {
        return this.multiParagraph.getLineForVerticalPosition(f);
    }

    public final float getHorizontalPosition(int i, boolean z) {
        return this.multiParagraph.getHorizontalPosition(i, z);
    }

    public final ResolvedTextDirection getParagraphDirection(int i) {
        return this.multiParagraph.getParagraphDirection(i);
    }

    public final ResolvedTextDirection getBidiRunDirection(int i) {
        return this.multiParagraph.getBidiRunDirection(i);
    }

    /* JADX INFO: renamed from: getOffsetForPosition-k-4lQ0M, reason: not valid java name */
    public final int m2102getOffsetForPositionk4lQ0M(long j) {
        return this.multiParagraph.m2050getOffsetForPositionk4lQ0M(j);
    }

    public final Rect getBoundingBox(int i) {
        return this.multiParagraph.getBoundingBox(i);
    }

    /* JADX INFO: renamed from: getWordBoundary--jx7JFs, reason: not valid java name */
    public final long m2104getWordBoundaryjx7JFs(int i) {
        return this.multiParagraph.m2052getWordBoundaryjx7JFs(i);
    }

    public final Rect getCursorRect(int i) {
        return this.multiParagraph.getCursorRect(i);
    }

    public final Path getPathForRange(int i, int i2) {
        return this.multiParagraph.getPathForRange(i, i2);
    }

    /* JADX INFO: renamed from: copy-O0kMr_c$default, reason: not valid java name */
    public static /* synthetic */ TextLayoutResult m2100copyO0kMr_c$default(TextLayoutResult textLayoutResult, TextLayoutInput textLayoutInput, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            textLayoutInput = textLayoutResult.layoutInput;
        }
        if ((i & 2) != 0) {
            j = textLayoutResult.size;
        }
        return textLayoutResult.m2101copyO0kMr_c(textLayoutInput, j);
    }

    /* JADX INFO: renamed from: copy-O0kMr_c, reason: not valid java name */
    public final TextLayoutResult m2101copyO0kMr_c(TextLayoutInput textLayoutInput, long j) {
        return new TextLayoutResult(textLayoutInput, this.multiParagraph, j, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextLayoutResult)) {
            return false;
        }
        TextLayoutResult textLayoutResult = (TextLayoutResult) obj;
        return Intrinsics.areEqual(this.layoutInput, textLayoutResult.layoutInput) && Intrinsics.areEqual(this.multiParagraph, textLayoutResult.multiParagraph) && IntSize.m2474equalsimpl0(this.size, textLayoutResult.size) && this.firstBaseline == textLayoutResult.firstBaseline && this.lastBaseline == textLayoutResult.lastBaseline && Intrinsics.areEqual(this.placeholderRects, textLayoutResult.placeholderRects);
    }

    public int hashCode() {
        return (((((((((this.layoutInput.hashCode() * 31) + this.multiParagraph.hashCode()) * 31) + IntSize.m2477hashCodeimpl(this.size)) * 31) + Float.floatToIntBits(this.firstBaseline)) * 31) + Float.floatToIntBits(this.lastBaseline)) * 31) + this.placeholderRects.hashCode();
    }

    public String toString() {
        return "TextLayoutResult(layoutInput=" + this.layoutInput + ", multiParagraph=" + this.multiParagraph + ", size=" + ((Object) IntSize.m2478toStringimpl(this.size)) + ", firstBaseline=" + this.firstBaseline + ", lastBaseline=" + this.lastBaseline + ", placeholderRects=" + this.placeholderRects + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
