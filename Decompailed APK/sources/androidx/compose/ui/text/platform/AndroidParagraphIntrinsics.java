package androidx.compose.ui.text.platform;

import android.graphics.Typeface;
import androidx.compose.runtime.State;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.android.LayoutIntrinsics;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.TypefaceResult;
import androidx.compose.ui.text.platform.extensions.TextPaintExtensions_androidKt;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidParagraphIntrinsics.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidParagraphIntrinsics implements ParagraphIntrinsics {
    private final CharSequence charSequence;
    private final Density density;
    private final boolean emojiCompatProcessed;
    private final FontFamily.Resolver fontFamilyResolver;
    private final LayoutIntrinsics layoutIntrinsics;
    private final List placeholders;
    private TypefaceDirtyTrackerLinkedList resolvedTypefaces;
    private final List spanStyles;
    private final TextStyle style;
    private final String text;
    private final int textDirectionHeuristic;
    private final AndroidTextPaint textPaint;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.List] */
    public AndroidParagraphIntrinsics(String str, TextStyle textStyle, List list, List list2, FontFamily.Resolver resolver, Density density) {
        AnnotatedString.Range range;
        this.text = str;
        this.style = textStyle;
        this.spanStyles = list;
        this.placeholders = list2;
        this.fontFamilyResolver = resolver;
        this.density = density;
        AndroidTextPaint androidTextPaint = new AndroidTextPaint(1, density.getDensity());
        this.textPaint = androidTextPaint;
        this.emojiCompatProcessed = !AndroidParagraphIntrinsics_androidKt.getHasEmojiCompat(textStyle) ? false : ((Boolean) EmojiCompatStatus.INSTANCE.getFontLoaded().getValue()).booleanValue();
        this.textDirectionHeuristic = AndroidParagraphIntrinsics_androidKt.m2247resolveTextDirectionHeuristicsHklW4sA(textStyle.m2139getTextDirections_7Xco(), textStyle.getLocaleList());
        Function4 function4 = new Function4() { // from class: androidx.compose.ui.text.platform.AndroidParagraphIntrinsics$resolveTypeface$1
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return m2246invokeDPcqOEQ((FontFamily) obj, (FontWeight) obj2, ((FontStyle) obj3).m2158unboximpl(), ((FontSynthesis) obj4).m2167unboximpl());
            }

            /* JADX INFO: renamed from: invoke-DPcqOEQ, reason: not valid java name */
            public final Typeface m2246invokeDPcqOEQ(FontFamily fontFamily, FontWeight fontWeight, int i, int i2) {
                State stateMo2150resolveDPcqOEQ = this.this$0.getFontFamilyResolver().mo2150resolveDPcqOEQ(fontFamily, fontWeight, i, i2);
                if (!(stateMo2150resolveDPcqOEQ instanceof TypefaceResult.Immutable)) {
                    TypefaceDirtyTrackerLinkedList typefaceDirtyTrackerLinkedList = new TypefaceDirtyTrackerLinkedList(stateMo2150resolveDPcqOEQ, this.this$0.resolvedTypefaces);
                    this.this$0.resolvedTypefaces = typefaceDirtyTrackerLinkedList;
                    return typefaceDirtyTrackerLinkedList.getTypeface();
                }
                Object value = stateMo2150resolveDPcqOEQ.getValue();
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type android.graphics.Typeface");
                return (Typeface) value;
            }
        };
        TextPaintExtensions_androidKt.setTextMotion(androidTextPaint, textStyle.getTextMotion());
        SpanStyle spanStyleApplySpanStyle = TextPaintExtensions_androidKt.applySpanStyle(androidTextPaint, textStyle.toSpanStyle(), function4, density, !list.isEmpty());
        if (spanStyleApplySpanStyle != null) {
            int size = list.size() + 1;
            list = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    range = new AnnotatedString.Range(spanStyleApplySpanStyle, 0, this.text.length());
                } else {
                    range = (AnnotatedString.Range) this.spanStyles.get(i - 1);
                }
                list.add(range);
            }
        }
        CharSequence charSequenceCreateCharSequence = AndroidParagraphHelper_androidKt.createCharSequence(this.text, this.textPaint.getTextSize(), this.style, list, this.placeholders, this.density, function4, this.emojiCompatProcessed);
        this.charSequence = charSequenceCreateCharSequence;
        this.layoutIntrinsics = new LayoutIntrinsics(charSequenceCreateCharSequence, this.textPaint, this.textDirectionHeuristic);
    }

    public final TextStyle getStyle() {
        return this.style;
    }

    public final FontFamily.Resolver getFontFamilyResolver() {
        return this.fontFamilyResolver;
    }

    public final AndroidTextPaint getTextPaint$ui_text_release() {
        return this.textPaint;
    }

    public final CharSequence getCharSequence$ui_text_release() {
        return this.charSequence;
    }

    public final LayoutIntrinsics getLayoutIntrinsics$ui_text_release() {
        return this.layoutIntrinsics;
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public float getMaxIntrinsicWidth() {
        return this.layoutIntrinsics.getMaxIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public float getMinIntrinsicWidth() {
        return this.layoutIntrinsics.getMinIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public boolean getHasStaleResolvedFonts() {
        TypefaceDirtyTrackerLinkedList typefaceDirtyTrackerLinkedList = this.resolvedTypefaces;
        return (typefaceDirtyTrackerLinkedList != null ? typefaceDirtyTrackerLinkedList.isStaleResolvedFont() : false) || (!this.emojiCompatProcessed && AndroidParagraphIntrinsics_androidKt.getHasEmojiCompat(this.style) && ((Boolean) EmojiCompatStatus.INSTANCE.getFontLoaded().getValue()).booleanValue());
    }

    public final int getTextDirectionHeuristic$ui_text_release() {
        return this.textDirectionHeuristic;
    }
}
