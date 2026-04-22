package androidx.compose.ui.text.style;

import androidx.compose.ui.text.SpanStyleKt;

/* JADX INFO: compiled from: TextIndent.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextIndentKt {
    public static final TextIndent lerp(TextIndent textIndent, TextIndent textIndent2, float f) {
        return new TextIndent(SpanStyleKt.m2093lerpTextUnitInheritableC3pnCVY(textIndent.m2362getFirstLineXSAIIZE(), textIndent2.m2362getFirstLineXSAIIZE(), f), SpanStyleKt.m2093lerpTextUnitInheritableC3pnCVY(textIndent.m2363getRestLineXSAIIZE(), textIndent2.m2363getRestLineXSAIIZE(), f), null);
    }
}
