package androidx.compose.ui.text;

import androidx.compose.ui.text.AnnotatedString;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MultiParagraphIntrinsics.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class MultiParagraphIntrinsicsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final List getLocalPlaceholders(List list, int i, int i2) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            AnnotatedString.Range range = (AnnotatedString.Range) obj;
            if (AnnotatedStringKt.intersect(i, i2, range.getStart(), range.getEnd())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i4 = 0; i4 < size2; i4++) {
            AnnotatedString.Range range2 = (AnnotatedString.Range) arrayList.get(i4);
            if (i > range2.getStart() || range2.getEnd() > i2) {
                throw new IllegalArgumentException("placeholder can not overlap with paragraph.");
            }
            arrayList2.add(new AnnotatedString.Range(range2.getItem(), range2.getStart() - i, range2.getEnd() - i));
        }
        return arrayList2;
    }
}
