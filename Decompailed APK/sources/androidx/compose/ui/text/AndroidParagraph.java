package androidx.compose.ui.text;

import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.selection.WordBoundary_androidKt;
import androidx.compose.ui.text.android.selection.WordIterator;
import androidx.compose.ui.text.android.style.PlaceholderSpan;
import androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.compose.ui.text.platform.style.ShaderBrushSpan;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: AndroidParagraph.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidParagraph implements Paragraph {
    private final CharSequence charSequence;
    private final long constraints;
    private final boolean ellipsis;
    private final TextLayout layout;
    private final int maxLines;
    private final AndroidParagraphIntrinsics paragraphIntrinsics;
    private final List placeholderRects;

    /* JADX INFO: compiled from: AndroidParagraph.android.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ResolvedTextDirection.values().length];
            try {
                iArr[ResolvedTextDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ResolvedTextDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ AndroidParagraph(AndroidParagraphIntrinsics androidParagraphIntrinsics, int i, boolean z, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(androidParagraphIntrinsics, i, z, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AndroidParagraph(AndroidParagraphIntrinsics androidParagraphIntrinsics, int i, boolean z, long j) {
        CharSequence charSequence$ui_text_release;
        List listEmptyList;
        Rect rect;
        float horizontalPosition;
        float lineBaseline;
        int heightPx;
        float lineTop;
        float heightPx2;
        float lineBaseline2;
        this.paragraphIntrinsics = androidParagraphIntrinsics;
        this.maxLines = i;
        this.ellipsis = z;
        this.constraints = j;
        if (Constraints.m2390getMinHeightimpl(j) != 0 || Constraints.m2391getMinWidthimpl(j) != 0) {
            throw new IllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.");
        }
        if (i < 1) {
            throw new IllegalArgumentException("maxLines should be greater than 0");
        }
        TextStyle style = androidParagraphIntrinsics.getStyle();
        if (AndroidParagraph_androidKt.shouldAttachIndentationFixSpan(style, z)) {
            charSequence$ui_text_release = AndroidParagraph_androidKt.attachIndentationFixSpan(androidParagraphIntrinsics.getCharSequence$ui_text_release());
        } else {
            charSequence$ui_text_release = androidParagraphIntrinsics.getCharSequence$ui_text_release();
        }
        this.charSequence = charSequence$ui_text_release;
        int iM2030toLayoutAlignaXe7zB0 = AndroidParagraph_androidKt.m2030toLayoutAlignaXe7zB0(style.m2138getTextAligne0LSkKk());
        boolean zM2336equalsimpl0 = TextAlign.m2336equalsimpl0(style.m2138getTextAligne0LSkKk(), TextAlign.Companion.m2342getJustifye0LSkKk());
        int iM2032toLayoutHyphenationFrequency3fSNIE = AndroidParagraph_androidKt.m2032toLayoutHyphenationFrequency3fSNIE(style.getParagraphStyle$ui_text_release().m2066getHyphensvmbZdU8());
        int iM2031toLayoutBreakStrategyxImikfE = AndroidParagraph_androidKt.m2031toLayoutBreakStrategyxImikfE(LineBreak.m2291getStrategyfcGXIks(style.m2136getLineBreakrAG3T2k()));
        int iM2033toLayoutLineBreakStylehpcqdu8 = AndroidParagraph_androidKt.m2033toLayoutLineBreakStylehpcqdu8(LineBreak.m2292getStrictnessusljTpc(style.m2136getLineBreakrAG3T2k()));
        int iM2034toLayoutLineBreakWordStylewPN0Rpw = AndroidParagraph_androidKt.m2034toLayoutLineBreakWordStylewPN0Rpw(LineBreak.m2293getWordBreakjp8hJ3c(style.m2136getLineBreakrAG3T2k()));
        TextUtils.TruncateAt truncateAt = z ? TextUtils.TruncateAt.END : null;
        TextLayout textLayoutConstructTextLayout = constructTextLayout(iM2030toLayoutAlignaXe7zB0, zM2336equalsimpl0 ? 1 : 0, truncateAt, i, iM2032toLayoutHyphenationFrequency3fSNIE, iM2031toLayoutBreakStrategyxImikfE, iM2033toLayoutLineBreakStylehpcqdu8, iM2034toLayoutLineBreakWordStylewPN0Rpw);
        if (z && textLayoutConstructTextLayout.getHeight() > Constraints.m2388getMaxHeightimpl(j) && i > 1) {
            int iNumberOfLinesThatFitMaxHeight = AndroidParagraph_androidKt.numberOfLinesThatFitMaxHeight(textLayoutConstructTextLayout, Constraints.m2388getMaxHeightimpl(j));
            if (iNumberOfLinesThatFitMaxHeight >= 0 && iNumberOfLinesThatFitMaxHeight != i) {
                textLayoutConstructTextLayout = constructTextLayout(iM2030toLayoutAlignaXe7zB0, zM2336equalsimpl0 ? 1 : 0, truncateAt, RangesKt.coerceAtLeast(iNumberOfLinesThatFitMaxHeight, 1), iM2032toLayoutHyphenationFrequency3fSNIE, iM2031toLayoutBreakStrategyxImikfE, iM2033toLayoutLineBreakStylehpcqdu8, iM2034toLayoutLineBreakWordStylewPN0Rpw);
            }
            this.layout = textLayoutConstructTextLayout;
        } else {
            this.layout = textLayoutConstructTextLayout;
        }
        getTextPaint$ui_text_release().m2252setBrush12SF9DM(style.getBrush(), SizeKt.Size(getWidth(), getHeight()), style.getAlpha());
        ShaderBrushSpan[] shaderBrushSpans = getShaderBrushSpans(this.layout);
        if (shaderBrushSpans != null) {
            Iterator it = ArrayIteratorKt.iterator(shaderBrushSpans);
            while (it.hasNext()) {
                ((ShaderBrushSpan) it.next()).m2266setSizeuvyYCjk(SizeKt.Size(getWidth(), getHeight()));
            }
        }
        CharSequence charSequence = this.charSequence;
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            Object[] spans = spanned.getSpans(0, charSequence.length(), PlaceholderSpan.class);
            ArrayList arrayList = new ArrayList(spans.length);
            for (Object obj : spans) {
                PlaceholderSpan placeholderSpan = (PlaceholderSpan) obj;
                int spanStart = spanned.getSpanStart(placeholderSpan);
                int spanEnd = spanned.getSpanEnd(placeholderSpan);
                int lineForOffset = this.layout.getLineForOffset(spanStart);
                byte b = lineForOffset >= this.maxLines;
                byte b2 = this.layout.getLineEllipsisCount(lineForOffset) > 0 && spanEnd > this.layout.getLineEllipsisOffset(lineForOffset);
                byte b3 = spanEnd > this.layout.getLineEnd(lineForOffset);
                if (b2 == true || b3 == true || b == true) {
                    rect = null;
                } else {
                    int i2 = WhenMappings.$EnumSwitchMapping$0[getBidiRunDirection(spanStart).ordinal()];
                    if (i2 == 1) {
                        horizontalPosition = getHorizontalPosition(spanStart, true);
                    } else {
                        if (i2 != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        horizontalPosition = getHorizontalPosition(spanStart, true) - placeholderSpan.getWidthPx();
                    }
                    float widthPx = placeholderSpan.getWidthPx() + horizontalPosition;
                    TextLayout textLayout = this.layout;
                    switch (placeholderSpan.getVerticalAlign()) {
                        case 0:
                            lineBaseline = textLayout.getLineBaseline(lineForOffset);
                            heightPx = placeholderSpan.getHeightPx();
                            lineTop = lineBaseline - heightPx;
                            rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                            break;
                        case 1:
                            lineTop = textLayout.getLineTop(lineForOffset);
                            rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                            break;
                        case 2:
                            lineBaseline = textLayout.getLineBottom(lineForOffset);
                            heightPx = placeholderSpan.getHeightPx();
                            lineTop = lineBaseline - heightPx;
                            rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                            break;
                        case 3:
                            lineTop = ((textLayout.getLineTop(lineForOffset) + textLayout.getLineBottom(lineForOffset)) - placeholderSpan.getHeightPx()) / 2;
                            rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                            break;
                        case 4:
                            heightPx2 = placeholderSpan.getFontMetrics().ascent;
                            lineBaseline2 = textLayout.getLineBaseline(lineForOffset);
                            lineTop = heightPx2 + lineBaseline2;
                            rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                            break;
                        case 5:
                            lineTop = (placeholderSpan.getFontMetrics().descent + textLayout.getLineBaseline(lineForOffset)) - placeholderSpan.getHeightPx();
                            rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                            break;
                        case 6:
                            Paint.FontMetricsInt fontMetrics = placeholderSpan.getFontMetrics();
                            heightPx2 = ((fontMetrics.ascent + fontMetrics.descent) - placeholderSpan.getHeightPx()) / 2;
                            lineBaseline2 = textLayout.getLineBaseline(lineForOffset);
                            lineTop = heightPx2 + lineBaseline2;
                            rect = new Rect(horizontalPosition, lineTop, widthPx, placeholderSpan.getHeightPx() + lineTop);
                            break;
                        default:
                            throw new IllegalStateException("unexpected verticalAlignment");
                    }
                }
                arrayList.add(rect);
            }
            listEmptyList = arrayList;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        this.placeholderRects = listEmptyList;
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getWidth() {
        return Constraints.m2389getMaxWidthimpl(this.constraints);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getHeight() {
        return this.layout.getHeight();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getMaxIntrinsicWidth() {
        return this.paragraphIntrinsics.getMaxIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getMinIntrinsicWidth() {
        return this.paragraphIntrinsics.getMinIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getFirstBaseline() {
        return getLineBaseline(0);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLastBaseline() {
        return getLineBaseline(getLineCount() - 1);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public boolean getDidExceedMaxLines() {
        return this.layout.getDidExceedMaxLines();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineCount() {
        return this.layout.getLineCount();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public List getPlaceholderRects() {
        return this.placeholderRects;
    }

    public final AndroidTextPaint getTextPaint$ui_text_release() {
        return this.paragraphIntrinsics.getTextPaint$ui_text_release();
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineForVerticalPosition(float f) {
        return this.layout.getLineForVertical((int) f);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: getOffsetForPosition-k-4lQ0M, reason: not valid java name */
    public int mo2019getOffsetForPositionk4lQ0M(long j) {
        return this.layout.getOffsetForHorizontal(this.layout.getLineForVertical((int) Offset.m1160getYimpl(j)), Offset.m1159getXimpl(j));
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: getRangeForRect-8-6BmAI, reason: not valid java name */
    public long mo2020getRangeForRect86BmAI(Rect rect, int i, final TextInclusionStrategy textInclusionStrategy) {
        int[] rangeForRect = this.layout.getRangeForRect(RectHelper_androidKt.toAndroidRectF(rect), AndroidParagraph_androidKt.m2035toLayoutTextGranularityduNsdkg(i), new Function2() { // from class: androidx.compose.ui.text.AndroidParagraph$getRangeForRect$range$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(RectF rectF, RectF rectF2) {
                return Boolean.valueOf(textInclusionStrategy.isIncluded(RectHelper_androidKt.toComposeRect(rectF), RectHelper_androidKt.toComposeRect(rectF2)));
            }
        });
        if (rangeForRect == null) {
            return TextRange.Companion.m2122getZerod9O1mEE();
        }
        return TextRangeKt.TextRange(rangeForRect[0], rangeForRect[1]);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Rect getBoundingBox(int i) {
        if (i < 0 || i >= this.charSequence.length()) {
            throw new IllegalArgumentException(("offset(" + i + ") is out of bounds [0," + this.charSequence.length() + CoreConstants.RIGHT_PARENTHESIS_CHAR).toString());
        }
        RectF boundingBox = this.layout.getBoundingBox(i);
        return new Rect(boundingBox.left, boundingBox.top, boundingBox.right, boundingBox.bottom);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: fillBoundingBoxes-8ffj60Q, reason: not valid java name */
    public void mo2018fillBoundingBoxes8ffj60Q(long j, float[] fArr, int i) {
        this.layout.fillBoundingBoxes(TextRange.m2115getMinimpl(j), TextRange.m2114getMaximpl(j), fArr, i);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Path getPathForRange(int i, int i2) {
        if (i < 0 || i > i2 || i2 > this.charSequence.length()) {
            throw new IllegalArgumentException(("start(" + i + ") or end(" + i2 + ") is out of range [0.." + this.charSequence.length() + "], or start > end!").toString());
        }
        android.graphics.Path path = new android.graphics.Path();
        this.layout.getSelectionPath(i, i2, path);
        return AndroidPath_androidKt.asComposePath(path);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public Rect getCursorRect(int i) {
        if (i < 0 || i > this.charSequence.length()) {
            throw new IllegalArgumentException(("offset(" + i + ") is out of bounds [0," + this.charSequence.length() + ']').toString());
        }
        float primaryHorizontal$default = TextLayout.getPrimaryHorizontal$default(this.layout, i, false, 2, null);
        int lineForOffset = this.layout.getLineForOffset(i);
        return new Rect(primaryHorizontal$default, this.layout.getLineTop(lineForOffset), primaryHorizontal$default, this.layout.getLineBottom(lineForOffset));
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: getWordBoundary--jx7JFs, reason: not valid java name */
    public long mo2021getWordBoundaryjx7JFs(int i) {
        WordIterator wordIterator = this.layout.getWordIterator();
        return TextRangeKt.TextRange(WordBoundary_androidKt.getWordStart(wordIterator, i), WordBoundary_androidKt.getWordEnd(wordIterator, i));
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineLeft(int i) {
        return this.layout.getLineLeft(i);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineRight(int i) {
        return this.layout.getLineRight(i);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineTop(int i) {
        return this.layout.getLineTop(i);
    }

    public float getLineBaseline(int i) {
        return this.layout.getLineBaseline(i);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getLineBottom(int i) {
        return this.layout.getLineBottom(i);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineStart(int i) {
        return this.layout.getLineStart(i);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineEnd(int i, boolean z) {
        if (z) {
            return this.layout.getLineVisibleEnd(i);
        }
        return this.layout.getLineEnd(i);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public int getLineForOffset(int i) {
        return this.layout.getLineForOffset(i);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public float getHorizontalPosition(int i, boolean z) {
        if (z) {
            return TextLayout.getPrimaryHorizontal$default(this.layout, i, false, 2, null);
        }
        return TextLayout.getSecondaryHorizontal$default(this.layout, i, false, 2, null);
    }

    @Override // androidx.compose.ui.text.Paragraph
    public ResolvedTextDirection getParagraphDirection(int i) {
        return this.layout.getParagraphDirection(this.layout.getLineForOffset(i)) == 1 ? ResolvedTextDirection.Ltr : ResolvedTextDirection.Rtl;
    }

    @Override // androidx.compose.ui.text.Paragraph
    public ResolvedTextDirection getBidiRunDirection(int i) {
        if (this.layout.isRtlCharAt(i)) {
            return ResolvedTextDirection.Rtl;
        }
        return ResolvedTextDirection.Ltr;
    }

    private final ShaderBrushSpan[] getShaderBrushSpans(TextLayout textLayout) {
        if (!(textLayout.getText() instanceof Spanned)) {
            return null;
        }
        CharSequence text = textLayout.getText();
        Intrinsics.checkNotNull(text, "null cannot be cast to non-null type android.text.Spanned");
        if (!hasSpan((Spanned) text, ShaderBrushSpan.class)) {
            return null;
        }
        CharSequence text2 = textLayout.getText();
        Intrinsics.checkNotNull(text2, "null cannot be cast to non-null type android.text.Spanned");
        return (ShaderBrushSpan[]) ((Spanned) text2).getSpans(0, textLayout.getText().length(), ShaderBrushSpan.class);
    }

    private final boolean hasSpan(Spanned spanned, Class cls) {
        return spanned.nextSpanTransition(-1, spanned.length(), cls) != spanned.length();
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: paint-LG529CI, reason: not valid java name */
    public void mo2022paintLG529CI(Canvas canvas, long j, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i) {
        int iM2250getBlendMode0nO6VwU = getTextPaint$ui_text_release().m2250getBlendMode0nO6VwU();
        AndroidTextPaint textPaint$ui_text_release = getTextPaint$ui_text_release();
        textPaint$ui_text_release.m2253setColor8_81llA(j);
        textPaint$ui_text_release.setShadow(shadow);
        textPaint$ui_text_release.setTextDecoration(textDecoration);
        textPaint$ui_text_release.setDrawStyle(drawStyle);
        textPaint$ui_text_release.m2251setBlendModes9anfk8(i);
        paint(canvas);
        getTextPaint$ui_text_release().m2251setBlendModes9anfk8(iM2250getBlendMode0nO6VwU);
    }

    @Override // androidx.compose.ui.text.Paragraph
    /* JADX INFO: renamed from: paint-hn5TExg, reason: not valid java name */
    public void mo2023painthn5TExg(Canvas canvas, Brush brush, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle, int i) {
        int iM2250getBlendMode0nO6VwU = getTextPaint$ui_text_release().m2250getBlendMode0nO6VwU();
        AndroidTextPaint textPaint$ui_text_release = getTextPaint$ui_text_release();
        textPaint$ui_text_release.m2252setBrush12SF9DM(brush, SizeKt.Size(getWidth(), getHeight()), f);
        textPaint$ui_text_release.setShadow(shadow);
        textPaint$ui_text_release.setTextDecoration(textDecoration);
        textPaint$ui_text_release.setDrawStyle(drawStyle);
        textPaint$ui_text_release.m2251setBlendModes9anfk8(i);
        paint(canvas);
        getTextPaint$ui_text_release().m2251setBlendModes9anfk8(iM2250getBlendMode0nO6VwU);
    }

    private final void paint(Canvas canvas) {
        android.graphics.Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        if (getDidExceedMaxLines()) {
            nativeCanvas.save();
            nativeCanvas.clipRect(0.0f, 0.0f, getWidth(), getHeight());
        }
        this.layout.paint(nativeCanvas);
        if (getDidExceedMaxLines()) {
            nativeCanvas.restore();
        }
    }

    private final TextLayout constructTextLayout(int i, int i2, TextUtils.TruncateAt truncateAt, int i3, int i4, int i5, int i6, int i7) {
        return new TextLayout(this.charSequence, getWidth(), getTextPaint$ui_text_release(), i, truncateAt, this.paragraphIntrinsics.getTextDirectionHeuristic$ui_text_release(), 1.0f, 0.0f, AndroidParagraphHelper_androidKt.isIncludeFontPaddingEnabled(this.paragraphIntrinsics.getStyle()), true, i3, i5, i6, i7, i4, i2, null, null, this.paragraphIntrinsics.getLayoutIntrinsics$ui_text_release(), 196736, null);
    }
}
