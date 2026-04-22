package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.text.AnnotatedString;
import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnnotatedString.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AnnotatedString implements CharSequence {
    public static final Companion Companion = new Companion(null);
    private static final Saver Saver = SaversKt.getAnnotatedStringSaver();
    private final List annotations;
    private final List paragraphStylesOrNull;
    private final List spanStylesOrNull;
    private final String text;

    public AnnotatedString(String str, List list, List list2, List list3) {
        List listSortedWith;
        this.text = str;
        this.spanStylesOrNull = list;
        this.paragraphStylesOrNull = list2;
        this.annotations = list3;
        if (list2 == null || (listSortedWith = CollectionsKt.sortedWith(list2, new Comparator() { // from class: androidx.compose.ui.text.AnnotatedString$special$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((AnnotatedString.Range) obj).getStart()), Integer.valueOf(((AnnotatedString.Range) obj2).getStart()));
            }
        })) == null) {
            return;
        }
        int size = listSortedWith.size();
        int end = -1;
        for (int i = 0; i < size; i++) {
            Range range = (Range) listSortedWith.get(i);
            if (range.getStart() < end) {
                throw new IllegalArgumentException("ParagraphStyle should not overlap");
            }
            if (range.getEnd() > this.text.length()) {
                throw new IllegalArgumentException(("ParagraphStyle range [" + range.getStart() + ", " + range.getEnd() + ") is out of boundary").toString());
            }
            end = range.getEnd();
        }
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ char charAt(int i) {
        return get(i);
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ int length() {
        return getLength();
    }

    public /* synthetic */ AnnotatedString(String str, List list, List list2, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : list, (i & 4) != 0 ? null : list2, (i & 8) != 0 ? null : list3);
    }

    public final String getText() {
        return this.text;
    }

    public final List getSpanStylesOrNull$ui_text_release() {
        return this.spanStylesOrNull;
    }

    public final List getParagraphStylesOrNull$ui_text_release() {
        return this.paragraphStylesOrNull;
    }

    public final List getAnnotations$ui_text_release() {
        return this.annotations;
    }

    public final List getSpanStyles() {
        List list = this.spanStylesOrNull;
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public final List getParagraphStyles() {
        List list = this.paragraphStylesOrNull;
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public /* synthetic */ AnnotatedString(String str, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public AnnotatedString(String str, List list, List list2) {
        this(str, list.isEmpty() ? null : list, list2.isEmpty() ? null : list2, null);
    }

    public int getLength() {
        return this.text.length();
    }

    public char get(int i) {
        return this.text.charAt(i);
    }

    @Override // java.lang.CharSequence
    public AnnotatedString subSequence(int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException(("start (" + i + ") should be less or equal to end (" + i2 + CoreConstants.RIGHT_PARENTHESIS_CHAR).toString());
        }
        if (i == 0 && i2 == this.text.length()) {
            return this;
        }
        String strSubstring = this.text.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return new AnnotatedString(strSubstring, AnnotatedStringKt.filterRanges(this.spanStylesOrNull, i, i2), AnnotatedStringKt.filterRanges(this.paragraphStylesOrNull, i, i2), AnnotatedStringKt.filterRanges(this.annotations, i, i2));
    }

    /* JADX INFO: renamed from: subSequence-5zc-tL8, reason: not valid java name */
    public final AnnotatedString m2036subSequence5zctL8(long j) {
        return subSequence(TextRange.m2115getMinimpl(j), TextRange.m2114getMaximpl(j));
    }

    public final AnnotatedString plus(AnnotatedString annotatedString) {
        Builder builder = new Builder(this);
        builder.append(annotatedString);
        return builder.toAnnotatedString();
    }

    public final List getStringAnnotations(String str, int i, int i2) {
        List listEmptyList;
        List list = this.annotations;
        if (list == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            listEmptyList = new ArrayList(list.size());
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                Object obj = list.get(i3);
                Range range = (Range) obj;
                if ((range.getItem() instanceof String) && Intrinsics.areEqual(str, range.getTag()) && AnnotatedStringKt.intersect(i, i2, range.getStart(), range.getEnd())) {
                    listEmptyList.add(obj);
                }
            }
        }
        Intrinsics.checkNotNull(listEmptyList, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.ui.text.AnnotatedString.Range<kotlin.String>>");
        return listEmptyList;
    }

    public final boolean hasStringAnnotations(String str, int i, int i2) {
        List list = this.annotations;
        if (list == null) {
            return false;
        }
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            Range range = (Range) list.get(i3);
            if ((range.getItem() instanceof String) && Intrinsics.areEqual(str, range.getTag()) && AnnotatedStringKt.intersect(i, i2, range.getStart(), range.getEnd())) {
                return true;
            }
        }
        return false;
    }

    public final List getTtsAnnotations(int i, int i2) {
        List listEmptyList;
        List list = this.annotations;
        if (list == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            listEmptyList = new ArrayList(list.size());
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                Object obj = list.get(i3);
                Range range = (Range) obj;
                if ((range.getItem() instanceof TtsAnnotation) && AnnotatedStringKt.intersect(i, i2, range.getStart(), range.getEnd())) {
                    listEmptyList.add(obj);
                }
            }
        }
        Intrinsics.checkNotNull(listEmptyList, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.TtsAnnotation>>");
        return listEmptyList;
    }

    public final List getUrlAnnotations(int i, int i2) {
        List listEmptyList;
        List list = this.annotations;
        if (list == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            listEmptyList = new ArrayList(list.size());
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                Object obj = list.get(i3);
                Range range = (Range) obj;
                if ((range.getItem() instanceof UrlAnnotation) && AnnotatedStringKt.intersect(i, i2, range.getStart(), range.getEnd())) {
                    listEmptyList.add(obj);
                }
            }
        }
        Intrinsics.checkNotNull(listEmptyList, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.UrlAnnotation>>");
        return listEmptyList;
    }

    public final List getLinkAnnotations(int i, int i2) {
        List listEmptyList;
        List list = this.annotations;
        if (list == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            listEmptyList = new ArrayList(list.size());
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                Object obj = list.get(i3);
                Range range = (Range) obj;
                if ((range.getItem() instanceof LinkAnnotation) && AnnotatedStringKt.intersect(i, i2, range.getStart(), range.getEnd())) {
                    listEmptyList.add(obj);
                }
            }
        }
        Intrinsics.checkNotNull(listEmptyList, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.LinkAnnotation>>");
        return listEmptyList;
    }

    public final boolean hasLinkAnnotations(int i, int i2) {
        List list = this.annotations;
        if (list == null) {
            return false;
        }
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            Range range = (Range) list.get(i3);
            if ((range.getItem() instanceof LinkAnnotation) && AnnotatedStringKt.intersect(i, i2, range.getStart(), range.getEnd())) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnnotatedString)) {
            return false;
        }
        AnnotatedString annotatedString = (AnnotatedString) obj;
        return Intrinsics.areEqual(this.text, annotatedString.text) && Intrinsics.areEqual(this.spanStylesOrNull, annotatedString.spanStylesOrNull) && Intrinsics.areEqual(this.paragraphStylesOrNull, annotatedString.paragraphStylesOrNull) && Intrinsics.areEqual(this.annotations, annotatedString.annotations);
    }

    public int hashCode() {
        int iHashCode = this.text.hashCode() * 31;
        List list = this.spanStylesOrNull;
        int iHashCode2 = (iHashCode + (list != null ? list.hashCode() : 0)) * 31;
        List list2 = this.paragraphStylesOrNull;
        int iHashCode3 = (iHashCode2 + (list2 != null ? list2.hashCode() : 0)) * 31;
        List list3 = this.annotations;
        return iHashCode3 + (list3 != null ? list3.hashCode() : 0);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.text;
    }

    public final boolean hasEqualAnnotations(AnnotatedString annotatedString) {
        return Intrinsics.areEqual(this.annotations, annotatedString.annotations);
    }

    /* JADX INFO: compiled from: AnnotatedString.kt */
    public static final class Range {
        private final int end;
        private final Object item;
        private final int start;
        private final String tag;

        public static /* synthetic */ Range copy$default(Range range, Object obj, int i, int i2, String str, int i3, Object obj2) {
            if ((i3 & 1) != 0) {
                obj = range.item;
            }
            if ((i3 & 2) != 0) {
                i = range.start;
            }
            if ((i3 & 4) != 0) {
                i2 = range.end;
            }
            if ((i3 & 8) != 0) {
                str = range.tag;
            }
            return range.copy(obj, i, i2, str);
        }

        public final Object component1() {
            return this.item;
        }

        public final int component2() {
            return this.start;
        }

        public final int component3() {
            return this.end;
        }

        public final Range copy(Object obj, int i, int i2, String str) {
            return new Range(obj, i, i2, str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Range)) {
                return false;
            }
            Range range = (Range) obj;
            return Intrinsics.areEqual(this.item, range.item) && this.start == range.start && this.end == range.end && Intrinsics.areEqual(this.tag, range.tag);
        }

        public int hashCode() {
            Object obj = this.item;
            return ((((((obj == null ? 0 : obj.hashCode()) * 31) + this.start) * 31) + this.end) * 31) + this.tag.hashCode();
        }

        public String toString() {
            return "Range(item=" + this.item + ", start=" + this.start + ", end=" + this.end + ", tag=" + this.tag + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public Range(Object obj, int i, int i2, String str) {
            this.item = obj;
            this.start = i;
            this.end = i2;
            this.tag = str;
            if (i > i2) {
                throw new IllegalArgumentException("Reversed range is not supported");
            }
        }

        public final int getEnd() {
            return this.end;
        }

        public final Object getItem() {
            return this.item;
        }

        public final int getStart() {
            return this.start;
        }

        public final String getTag() {
            return this.tag;
        }

        public Range(Object obj, int i, int i2) {
            this(obj, i, i2, "");
        }
    }

    /* JADX INFO: compiled from: AnnotatedString.kt */
    public static final class Builder implements Appendable {
        private final List annotations;
        private final List paragraphStyles;
        private final List spanStyles;
        private final List styleStack;
        private final StringBuilder text;

        public Builder(int i) {
            this.text = new StringBuilder(i);
            this.spanStyles = new ArrayList();
            this.paragraphStyles = new ArrayList();
            this.annotations = new ArrayList();
            this.styleStack = new ArrayList();
        }

        public /* synthetic */ Builder(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 16 : i);
        }

        /* JADX INFO: compiled from: AnnotatedString.kt */
        private static final class MutableRange {
            private int end;
            private final Object item;
            private final int start;
            private final String tag;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof MutableRange)) {
                    return false;
                }
                MutableRange mutableRange = (MutableRange) obj;
                return Intrinsics.areEqual(this.item, mutableRange.item) && this.start == mutableRange.start && this.end == mutableRange.end && Intrinsics.areEqual(this.tag, mutableRange.tag);
            }

            public int hashCode() {
                Object obj = this.item;
                return ((((((obj == null ? 0 : obj.hashCode()) * 31) + this.start) * 31) + this.end) * 31) + this.tag.hashCode();
            }

            public String toString() {
                return "MutableRange(item=" + this.item + ", start=" + this.start + ", end=" + this.end + ", tag=" + this.tag + CoreConstants.RIGHT_PARENTHESIS_CHAR;
            }

            public MutableRange(Object obj, int i, int i2, String str) {
                this.item = obj;
                this.start = i;
                this.end = i2;
                this.tag = str;
            }

            public /* synthetic */ MutableRange(Object obj, int i, int i2, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(obj, i, (i3 & 4) != 0 ? Integer.MIN_VALUE : i2, (i3 & 8) != 0 ? "" : str);
            }

            public final Range toRange(int i) {
                int i2 = this.end;
                if (i2 != Integer.MIN_VALUE) {
                    i = i2;
                }
                if (i == Integer.MIN_VALUE) {
                    throw new IllegalStateException("Item.end should be set first");
                }
                return new Range(this.item, this.start, i, this.tag);
            }
        }

        public Builder(AnnotatedString annotatedString) {
            this(0, 1, null);
            append(annotatedString);
        }

        public final void append(String str) {
            this.text.append(str);
        }

        @Override // java.lang.Appendable
        public Builder append(CharSequence charSequence) {
            if (charSequence instanceof AnnotatedString) {
                append((AnnotatedString) charSequence);
            } else {
                this.text.append(charSequence);
            }
            return this;
        }

        @Override // java.lang.Appendable
        public Builder append(CharSequence charSequence, int i, int i2) {
            if (charSequence instanceof AnnotatedString) {
                append((AnnotatedString) charSequence, i, i2);
            } else {
                this.text.append(charSequence, i, i2);
            }
            return this;
        }

        @Override // java.lang.Appendable
        public Builder append(char c) {
            this.text.append(c);
            return this;
        }

        public final void append(AnnotatedString annotatedString) {
            int length = this.text.length();
            this.text.append(annotatedString.getText());
            List spanStylesOrNull$ui_text_release = annotatedString.getSpanStylesOrNull$ui_text_release();
            if (spanStylesOrNull$ui_text_release != null) {
                int size = spanStylesOrNull$ui_text_release.size();
                for (int i = 0; i < size; i++) {
                    Range range = (Range) spanStylesOrNull$ui_text_release.get(i);
                    addStyle((SpanStyle) range.getItem(), range.getStart() + length, range.getEnd() + length);
                }
            }
            List paragraphStylesOrNull$ui_text_release = annotatedString.getParagraphStylesOrNull$ui_text_release();
            if (paragraphStylesOrNull$ui_text_release != null) {
                int size2 = paragraphStylesOrNull$ui_text_release.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    Range range2 = (Range) paragraphStylesOrNull$ui_text_release.get(i2);
                    addStyle((ParagraphStyle) range2.getItem(), range2.getStart() + length, range2.getEnd() + length);
                }
            }
            List annotations$ui_text_release = annotatedString.getAnnotations$ui_text_release();
            if (annotations$ui_text_release != null) {
                int size3 = annotations$ui_text_release.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    Range range3 = (Range) annotations$ui_text_release.get(i3);
                    this.annotations.add(new MutableRange(range3.getItem(), range3.getStart() + length, range3.getEnd() + length, range3.getTag()));
                }
            }
        }

        public final void append(AnnotatedString annotatedString, int i, int i2) {
            int length = this.text.length();
            this.text.append((CharSequence) annotatedString.getText(), i, i2);
            List localSpanStyles = AnnotatedStringKt.getLocalSpanStyles(annotatedString, i, i2);
            if (localSpanStyles != null) {
                int size = localSpanStyles.size();
                for (int i3 = 0; i3 < size; i3++) {
                    Range range = (Range) localSpanStyles.get(i3);
                    addStyle((SpanStyle) range.getItem(), range.getStart() + length, range.getEnd() + length);
                }
            }
            List localParagraphStyles = AnnotatedStringKt.getLocalParagraphStyles(annotatedString, i, i2);
            if (localParagraphStyles != null) {
                int size2 = localParagraphStyles.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    Range range2 = (Range) localParagraphStyles.get(i4);
                    addStyle((ParagraphStyle) range2.getItem(), range2.getStart() + length, range2.getEnd() + length);
                }
            }
            List localAnnotations = AnnotatedStringKt.getLocalAnnotations(annotatedString, i, i2);
            if (localAnnotations != null) {
                int size3 = localAnnotations.size();
                for (int i5 = 0; i5 < size3; i5++) {
                    Range range3 = (Range) localAnnotations.get(i5);
                    this.annotations.add(new MutableRange(range3.getItem(), range3.getStart() + length, range3.getEnd() + length, range3.getTag()));
                }
            }
        }

        public final void addStyle(SpanStyle spanStyle, int i, int i2) {
            this.spanStyles.add(new MutableRange(spanStyle, i, i2, null, 8, null));
        }

        public final void addStyle(ParagraphStyle paragraphStyle, int i, int i2) {
            this.paragraphStyles.add(new MutableRange(paragraphStyle, i, i2, null, 8, null));
        }

        public final int pushStyle(SpanStyle spanStyle) {
            MutableRange mutableRange = new MutableRange(spanStyle, this.text.length(), 0, null, 12, null);
            this.styleStack.add(mutableRange);
            this.spanStyles.add(mutableRange);
            return this.styleStack.size() - 1;
        }

        public final AnnotatedString toAnnotatedString() {
            String string = this.text.toString();
            List list = this.spanStyles;
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(((MutableRange) list.get(i)).toRange(this.text.length()));
            }
            if (arrayList.isEmpty()) {
                arrayList = null;
            }
            List list2 = this.paragraphStyles;
            ArrayList arrayList2 = new ArrayList(list2.size());
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                arrayList2.add(((MutableRange) list2.get(i2)).toRange(this.text.length()));
            }
            if (arrayList2.isEmpty()) {
                arrayList2 = null;
            }
            List list3 = this.annotations;
            ArrayList arrayList3 = new ArrayList(list3.size());
            int size3 = list3.size();
            for (int i3 = 0; i3 < size3; i3++) {
                arrayList3.add(((MutableRange) list3.get(i3)).toRange(this.text.length()));
            }
            return new AnnotatedString(string, arrayList, arrayList2, arrayList3.isEmpty() ? null : arrayList3);
        }
    }

    /* JADX INFO: compiled from: AnnotatedString.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
