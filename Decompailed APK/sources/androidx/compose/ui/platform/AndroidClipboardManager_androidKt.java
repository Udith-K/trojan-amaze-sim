package androidx.compose.ui.platform;

import android.text.Annotation;
import android.text.SpannableString;
import android.text.Spanned;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.SpanStyle;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidClipboardManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidClipboardManager_androidKt {
    public static final AnnotatedString convertToAnnotatedString(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        if (!(charSequence instanceof Spanned)) {
            return new AnnotatedString(charSequence.toString(), null, null, 6, null);
        }
        Spanned spanned = (Spanned) charSequence;
        int i = 0;
        Annotation[] annotationArr = (Annotation[]) spanned.getSpans(0, charSequence.length(), Annotation.class);
        ArrayList arrayList = new ArrayList();
        int lastIndex = ArraysKt.getLastIndex(annotationArr);
        if (lastIndex >= 0) {
            while (true) {
                Annotation annotation = annotationArr[i];
                if (Intrinsics.areEqual(annotation.getKey(), "androidx.compose.text.SpanStyle")) {
                    arrayList.add(new AnnotatedString.Range(new DecodeHelper(annotation.getValue()).decodeSpanStyle(), spanned.getSpanStart(annotation), spanned.getSpanEnd(annotation)));
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return new AnnotatedString(charSequence.toString(), arrayList, null, 4, null);
    }

    public static final CharSequence convertToCharSequence(AnnotatedString annotatedString) {
        if (annotatedString.getSpanStyles().isEmpty()) {
            return annotatedString.getText();
        }
        SpannableString spannableString = new SpannableString(annotatedString.getText());
        EncodeHelper encodeHelper = new EncodeHelper();
        List spanStyles = annotatedString.getSpanStyles();
        int size = spanStyles.size();
        for (int i = 0; i < size; i++) {
            AnnotatedString.Range range = (AnnotatedString.Range) spanStyles.get(i);
            SpanStyle spanStyle = (SpanStyle) range.component1();
            int iComponent2 = range.component2();
            int iComponent3 = range.component3();
            encodeHelper.reset();
            encodeHelper.encode(spanStyle);
            spannableString.setSpan(new Annotation("androidx.compose.text.SpanStyle", encodeHelper.encodedString()), iComponent2, iComponent3, 33);
        }
        return spannableString;
    }
}
