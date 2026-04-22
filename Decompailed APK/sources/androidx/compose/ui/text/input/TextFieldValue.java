package androidx.compose.ui.text.input;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.NonNullValueClassSaver;
import androidx.compose.ui.text.SaversKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import ch.qos.logback.core.CoreConstants;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldValue.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextFieldValue {
    public static final Companion Companion = new Companion(null);
    private static final Saver Saver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.input.TextFieldValue$Companion$Saver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SaverScope saverScope, TextFieldValue textFieldValue) {
            return CollectionsKt.arrayListOf(SaversKt.save(textFieldValue.getAnnotatedString(), SaversKt.getAnnotatedStringSaver(), saverScope), SaversKt.save(TextRange.m2105boximpl(textFieldValue.m2239getSelectiond9O1mEE()), SaversKt.getSaver(TextRange.Companion), saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.input.TextFieldValue$Companion$Saver$2
        @Override // kotlin.jvm.functions.Function1
        public final TextFieldValue invoke(Object obj) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
            List list = (List) obj;
            Object obj2 = list.get(0);
            Saver annotatedStringSaver = SaversKt.getAnnotatedStringSaver();
            Boolean bool = Boolean.FALSE;
            TextRange textRange = null;
            AnnotatedString annotatedString = ((!Intrinsics.areEqual(obj2, bool) || (annotatedStringSaver instanceof NonNullValueClassSaver)) && obj2 != null) ? (AnnotatedString) annotatedStringSaver.restore(obj2) : null;
            Intrinsics.checkNotNull(annotatedString);
            Object obj3 = list.get(1);
            Saver saver = SaversKt.getSaver(TextRange.Companion);
            if ((!Intrinsics.areEqual(obj3, bool) || (saver instanceof NonNullValueClassSaver)) && obj3 != null) {
                textRange = (TextRange) saver.restore(obj3);
            }
            Intrinsics.checkNotNull(textRange);
            return new TextFieldValue(annotatedString, textRange.m2121unboximpl(), (TextRange) null, 4, (DefaultConstructorMarker) null);
        }
    });
    private final AnnotatedString annotatedString;
    private final TextRange composition;
    private final long selection;

    public /* synthetic */ TextFieldValue(AnnotatedString annotatedString, long j, TextRange textRange, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, j, textRange);
    }

    public /* synthetic */ TextFieldValue(String str, long j, TextRange textRange, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, textRange);
    }

    private TextFieldValue(AnnotatedString annotatedString, long j, TextRange textRange) {
        this.annotatedString = annotatedString;
        this.selection = TextRangeKt.m2123coerceIn8ffj60Q(j, 0, getText().length());
        this.composition = textRange != null ? TextRange.m2105boximpl(TextRangeKt.m2123coerceIn8ffj60Q(textRange.m2121unboximpl(), 0, getText().length())) : null;
    }

    public final AnnotatedString getAnnotatedString() {
        return this.annotatedString;
    }

    public /* synthetic */ TextFieldValue(AnnotatedString annotatedString, long j, TextRange textRange, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, (i & 2) != 0 ? TextRange.Companion.m2122getZerod9O1mEE() : j, (i & 4) != 0 ? null : textRange, (DefaultConstructorMarker) null);
    }

    public /* synthetic */ TextFieldValue(String str, long j, TextRange textRange, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? TextRange.Companion.m2122getZerod9O1mEE() : j, (i & 4) != 0 ? null : textRange, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private TextFieldValue(String str, long j, TextRange textRange) {
        DefaultConstructorMarker defaultConstructorMarker = null;
        this(new AnnotatedString(str, null, null, 6, defaultConstructorMarker), j, textRange, defaultConstructorMarker);
    }

    public final String getText() {
        return this.annotatedString.getText();
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE, reason: not valid java name */
    public final long m2239getSelectiond9O1mEE() {
        return this.selection;
    }

    /* JADX INFO: renamed from: getComposition-MzsxiRA, reason: not valid java name */
    public final TextRange m2238getCompositionMzsxiRA() {
        return this.composition;
    }

    /* JADX INFO: renamed from: copy-3r_uNRQ$default, reason: not valid java name */
    public static /* synthetic */ TextFieldValue m2236copy3r_uNRQ$default(TextFieldValue textFieldValue, AnnotatedString annotatedString, long j, TextRange textRange, int i, Object obj) {
        if ((i & 1) != 0) {
            annotatedString = textFieldValue.annotatedString;
        }
        if ((i & 2) != 0) {
            j = textFieldValue.selection;
        }
        if ((i & 4) != 0) {
            textRange = textFieldValue.composition;
        }
        return textFieldValue.m2237copy3r_uNRQ(annotatedString, j, textRange);
    }

    /* JADX INFO: renamed from: copy-3r_uNRQ, reason: not valid java name */
    public final TextFieldValue m2237copy3r_uNRQ(AnnotatedString annotatedString, long j, TextRange textRange) {
        return new TextFieldValue(annotatedString, j, textRange, (DefaultConstructorMarker) null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextFieldValue)) {
            return false;
        }
        TextFieldValue textFieldValue = (TextFieldValue) obj;
        return TextRange.m2110equalsimpl0(this.selection, textFieldValue.selection) && Intrinsics.areEqual(this.composition, textFieldValue.composition) && Intrinsics.areEqual(this.annotatedString, textFieldValue.annotatedString);
    }

    public int hashCode() {
        int iHashCode = ((this.annotatedString.hashCode() * 31) + TextRange.m2118hashCodeimpl(this.selection)) * 31;
        TextRange textRange = this.composition;
        return iHashCode + (textRange != null ? TextRange.m2118hashCodeimpl(textRange.m2121unboximpl()) : 0);
    }

    public String toString() {
        return "TextFieldValue(text='" + ((Object) this.annotatedString) + "', selection=" + ((Object) TextRange.m2120toStringimpl(this.selection)) + ", composition=" + this.composition + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: TextFieldValue.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
