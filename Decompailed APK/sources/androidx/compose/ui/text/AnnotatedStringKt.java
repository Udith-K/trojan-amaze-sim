package androidx.compose.ui.text;

import androidx.compose.ui.text.AnnotatedString;
import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: AnnotatedString.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AnnotatedStringKt {
    private static final AnnotatedString EmptyAnnotatedString = new AnnotatedString("", null, null, 6, null);

    public static final boolean contains(int i, int i2, int i3, int i4) {
        if (i > i3 || i4 > i2) {
            return false;
        }
        if (i2 == i4) {
            if ((i3 == i4) != (i == i2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List filterRanges(List list, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException(("start (" + i + ") should be less than or equal to end (" + i2 + CoreConstants.RIGHT_PARENTHESIS_CHAR).toString());
        }
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            AnnotatedString.Range range = (AnnotatedString.Range) obj;
            if (intersect(i, i2, range.getStart(), range.getEnd())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i4 = 0; i4 < size2; i4++) {
            AnnotatedString.Range range2 = (AnnotatedString.Range) arrayList.get(i4);
            arrayList2.add(new AnnotatedString.Range(range2.getItem(), Math.max(i, range2.getStart()) - i, Math.min(i2, range2.getEnd()) - i, range2.getTag()));
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        return arrayList2;
    }

    public static final List normalizedParagraphStyles(AnnotatedString annotatedString, ParagraphStyle paragraphStyle) {
        int length = annotatedString.getText().length();
        List paragraphStylesOrNull$ui_text_release = annotatedString.getParagraphStylesOrNull$ui_text_release();
        if (paragraphStylesOrNull$ui_text_release == null) {
            paragraphStylesOrNull$ui_text_release = CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = paragraphStylesOrNull$ui_text_release.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            AnnotatedString.Range range = (AnnotatedString.Range) paragraphStylesOrNull$ui_text_release.get(i);
            ParagraphStyle paragraphStyle2 = (ParagraphStyle) range.component1();
            int iComponent2 = range.component2();
            int iComponent3 = range.component3();
            if (iComponent2 != i2) {
                arrayList.add(new AnnotatedString.Range(paragraphStyle, i2, iComponent2));
            }
            arrayList.add(new AnnotatedString.Range(paragraphStyle.merge(paragraphStyle2), iComponent2, iComponent3));
            i++;
            i2 = iComponent3;
        }
        if (i2 != length) {
            arrayList.add(new AnnotatedString.Range(paragraphStyle, i2, length));
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new AnnotatedString.Range(paragraphStyle, 0, 0));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getLocalSpanStyles(AnnotatedString annotatedString, int i, int i2) {
        List spanStylesOrNull$ui_text_release;
        if (i == i2 || (spanStylesOrNull$ui_text_release = annotatedString.getSpanStylesOrNull$ui_text_release()) == null) {
            return null;
        }
        if (i == 0 && i2 >= annotatedString.getText().length()) {
            return spanStylesOrNull$ui_text_release;
        }
        ArrayList arrayList = new ArrayList(spanStylesOrNull$ui_text_release.size());
        int size = spanStylesOrNull$ui_text_release.size();
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = spanStylesOrNull$ui_text_release.get(i3);
            AnnotatedString.Range range = (AnnotatedString.Range) obj;
            if (intersect(i, i2, range.getStart(), range.getEnd())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i4 = 0; i4 < size2; i4++) {
            AnnotatedString.Range range2 = (AnnotatedString.Range) arrayList.get(i4);
            arrayList2.add(new AnnotatedString.Range(range2.getItem(), RangesKt.coerceIn(range2.getStart(), i, i2) - i, RangesKt.coerceIn(range2.getEnd(), i, i2) - i));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getLocalParagraphStyles(AnnotatedString annotatedString, int i, int i2) {
        List paragraphStylesOrNull$ui_text_release;
        if (i == i2 || (paragraphStylesOrNull$ui_text_release = annotatedString.getParagraphStylesOrNull$ui_text_release()) == null) {
            return null;
        }
        if (i == 0 && i2 >= annotatedString.getText().length()) {
            return paragraphStylesOrNull$ui_text_release;
        }
        ArrayList arrayList = new ArrayList(paragraphStylesOrNull$ui_text_release.size());
        int size = paragraphStylesOrNull$ui_text_release.size();
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = paragraphStylesOrNull$ui_text_release.get(i3);
            AnnotatedString.Range range = (AnnotatedString.Range) obj;
            if (intersect(i, i2, range.getStart(), range.getEnd())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i4 = 0; i4 < size2; i4++) {
            AnnotatedString.Range range2 = (AnnotatedString.Range) arrayList.get(i4);
            arrayList2.add(new AnnotatedString.Range(range2.getItem(), RangesKt.coerceIn(range2.getStart(), i, i2) - i, RangesKt.coerceIn(range2.getEnd(), i, i2) - i));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getLocalAnnotations(AnnotatedString annotatedString, int i, int i2) {
        List annotations$ui_text_release;
        if (i == i2 || (annotations$ui_text_release = annotatedString.getAnnotations$ui_text_release()) == null) {
            return null;
        }
        if (i == 0 && i2 >= annotatedString.getText().length()) {
            return annotations$ui_text_release;
        }
        ArrayList arrayList = new ArrayList(annotations$ui_text_release.size());
        int size = annotations$ui_text_release.size();
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = annotations$ui_text_release.get(i3);
            AnnotatedString.Range range = (AnnotatedString.Range) obj;
            if (intersect(i, i2, range.getStart(), range.getEnd())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i4 = 0; i4 < size2; i4++) {
            AnnotatedString.Range range2 = (AnnotatedString.Range) arrayList.get(i4);
            arrayList2.add(new AnnotatedString.Range(range2.getItem(), RangesKt.coerceIn(range2.getStart(), i, i2) - i, RangesKt.coerceIn(range2.getEnd(), i, i2) - i, range2.getTag()));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnnotatedString substringWithoutParagraphStyles(AnnotatedString annotatedString, int i, int i2) {
        String strSubstring;
        if (i != i2) {
            strSubstring = annotatedString.getText().substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        } else {
            strSubstring = "";
        }
        return new AnnotatedString(strSubstring, getLocalSpanStyles(annotatedString, i, i2), null, null, 12, null);
    }

    public static final boolean intersect(int i, int i2, int i3, int i4) {
        return Math.max(i, i3) < Math.min(i2, i4) || contains(i, i2, i3, i4) || contains(i3, i4, i, i2);
    }

    public static final AnnotatedString emptyAnnotatedString() {
        return EmptyAnnotatedString;
    }
}
