package androidx.compose.ui.text.style;

import androidx.compose.ui.util.ListUtilsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TextDecoration.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextDecoration {
    private final int mask;
    public static final Companion Companion = new Companion(null);
    private static final TextDecoration None = new TextDecoration(0);
    private static final TextDecoration Underline = new TextDecoration(1);
    private static final TextDecoration LineThrough = new TextDecoration(2);

    public TextDecoration(int i) {
        this.mask = i;
    }

    public final int getMask() {
        return this.mask;
    }

    /* JADX INFO: compiled from: TextDecoration.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TextDecoration getNone() {
            return TextDecoration.None;
        }

        public final TextDecoration getUnderline() {
            return TextDecoration.Underline;
        }

        public final TextDecoration getLineThrough() {
            return TextDecoration.LineThrough;
        }

        public final TextDecoration combine(List list) {
            Integer numValueOf = 0;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                numValueOf = Integer.valueOf(numValueOf.intValue() | ((TextDecoration) list.get(i)).getMask());
            }
            return new TextDecoration(numValueOf.intValue());
        }
    }

    public final boolean contains(TextDecoration textDecoration) {
        int i = this.mask;
        return (textDecoration.mask | i) == i;
    }

    public String toString() {
        if (this.mask == 0) {
            return "TextDecoration.None";
        }
        ArrayList arrayList = new ArrayList();
        if ((this.mask & Underline.mask) != 0) {
            arrayList.add("Underline");
        }
        if ((this.mask & LineThrough.mask) != 0) {
            arrayList.add("LineThrough");
        }
        if (arrayList.size() == 1) {
            return "TextDecoration." + ((String) arrayList.get(0));
        }
        return "TextDecoration[" + ListUtilsKt.fastJoinToString$default(arrayList, ", ", null, null, 0, null, null, 62, null) + ']';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TextDecoration) && this.mask == ((TextDecoration) obj).mask;
    }

    public int hashCode() {
        return this.mask;
    }
}
