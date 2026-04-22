package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: EditingBuffer.kt */
/* JADX INFO: loaded from: classes.dex */
public final class EditingBuffer {
    private int compositionEnd;
    private int compositionStart;
    private final PartialGapBuffer gapBuffer;
    private int selectionEnd;
    private int selectionStart;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ EditingBuffer(AnnotatedString annotatedString, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, j);
    }

    private EditingBuffer(AnnotatedString annotatedString, long j) {
        this.gapBuffer = new PartialGapBuffer(annotatedString.getText());
        this.selectionStart = TextRange.m2115getMinimpl(j);
        this.selectionEnd = TextRange.m2114getMaximpl(j);
        this.compositionStart = -1;
        this.compositionEnd = -1;
        int iM2115getMinimpl = TextRange.m2115getMinimpl(j);
        int iM2114getMaximpl = TextRange.m2114getMaximpl(j);
        if (iM2115getMinimpl < 0 || iM2115getMinimpl > annotatedString.length()) {
            throw new IndexOutOfBoundsException("start (" + iM2115getMinimpl + ") offset is outside of text region " + annotatedString.length());
        }
        if (iM2114getMaximpl < 0 || iM2114getMaximpl > annotatedString.length()) {
            throw new IndexOutOfBoundsException("end (" + iM2114getMaximpl + ") offset is outside of text region " + annotatedString.length());
        }
        if (iM2115getMinimpl <= iM2114getMaximpl) {
            return;
        }
        throw new IllegalArgumentException("Do not set reversed range: " + iM2115getMinimpl + " > " + iM2114getMaximpl);
    }

    /* JADX INFO: compiled from: EditingBuffer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final int getSelectionStart$ui_text_release() {
        return this.selectionStart;
    }

    private final void setSelectionStart(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(("Cannot set selectionStart to a negative value: " + i).toString());
        }
        this.selectionStart = i;
    }

    public final int getSelectionEnd$ui_text_release() {
        return this.selectionEnd;
    }

    private final void setSelectionEnd(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(("Cannot set selectionEnd to a negative value: " + i).toString());
        }
        this.selectionEnd = i;
    }

    public final int getCompositionStart$ui_text_release() {
        return this.compositionStart;
    }

    public final int getCompositionEnd$ui_text_release() {
        return this.compositionEnd;
    }

    public final boolean hasComposition$ui_text_release() {
        return this.compositionStart != -1;
    }

    /* JADX INFO: renamed from: getComposition-MzsxiRA$ui_text_release, reason: not valid java name */
    public final TextRange m2184getCompositionMzsxiRA$ui_text_release() {
        if (hasComposition$ui_text_release()) {
            return TextRange.m2105boximpl(TextRangeKt.TextRange(this.compositionStart, this.compositionEnd));
        }
        return null;
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE$ui_text_release, reason: not valid java name */
    public final long m2185getSelectiond9O1mEE$ui_text_release() {
        return TextRangeKt.TextRange(this.selectionStart, this.selectionEnd);
    }

    public final int getCursor$ui_text_release() {
        int i = this.selectionStart;
        int i2 = this.selectionEnd;
        if (i == i2) {
            return i2;
        }
        return -1;
    }

    public final void setCursor$ui_text_release(int i) {
        setSelection$ui_text_release(i, i);
    }

    public final char get$ui_text_release(int i) {
        return this.gapBuffer.get(i);
    }

    public final int getLength$ui_text_release() {
        return this.gapBuffer.getLength();
    }

    public final void replace$ui_text_release(int i, int i2, String str) {
        if (i < 0 || i > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("start (" + i + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (i2 < 0 || i2 > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("end (" + i2 + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (i > i2) {
            throw new IllegalArgumentException("Do not set reversed range: " + i + " > " + i2);
        }
        this.gapBuffer.replace(i, i2, str);
        setSelectionStart(str.length() + i);
        setSelectionEnd(i + str.length());
        this.compositionStart = -1;
        this.compositionEnd = -1;
    }

    public final void delete$ui_text_release(int i, int i2) {
        long jTextRange = TextRangeKt.TextRange(i, i2);
        this.gapBuffer.replace(i, i2, "");
        long jM2186updateRangeAfterDeletepWDy79M = EditingBufferKt.m2186updateRangeAfterDeletepWDy79M(TextRangeKt.TextRange(this.selectionStart, this.selectionEnd), jTextRange);
        setSelectionStart(TextRange.m2115getMinimpl(jM2186updateRangeAfterDeletepWDy79M));
        setSelectionEnd(TextRange.m2114getMaximpl(jM2186updateRangeAfterDeletepWDy79M));
        if (hasComposition$ui_text_release()) {
            long jM2186updateRangeAfterDeletepWDy79M2 = EditingBufferKt.m2186updateRangeAfterDeletepWDy79M(TextRangeKt.TextRange(this.compositionStart, this.compositionEnd), jTextRange);
            if (TextRange.m2111getCollapsedimpl(jM2186updateRangeAfterDeletepWDy79M2)) {
                commitComposition$ui_text_release();
            } else {
                this.compositionStart = TextRange.m2115getMinimpl(jM2186updateRangeAfterDeletepWDy79M2);
                this.compositionEnd = TextRange.m2114getMaximpl(jM2186updateRangeAfterDeletepWDy79M2);
            }
        }
    }

    public final void setSelection$ui_text_release(int i, int i2) {
        if (i < 0 || i > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("start (" + i + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (i2 < 0 || i2 > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("end (" + i2 + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (i > i2) {
            throw new IllegalArgumentException("Do not set reversed range: " + i + " > " + i2);
        }
        setSelectionStart(i);
        setSelectionEnd(i2);
    }

    public final void setComposition$ui_text_release(int i, int i2) {
        if (i < 0 || i > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("start (" + i + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (i2 < 0 || i2 > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("end (" + i2 + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (i >= i2) {
            throw new IllegalArgumentException("Do not set reversed or empty range: " + i + " > " + i2);
        }
        this.compositionStart = i;
        this.compositionEnd = i2;
    }

    public final void commitComposition$ui_text_release() {
        this.compositionStart = -1;
        this.compositionEnd = -1;
    }

    public String toString() {
        return this.gapBuffer.toString();
    }

    public final AnnotatedString toAnnotatedString$ui_text_release() {
        return new AnnotatedString(toString(), null, null, 6, null);
    }
}
