package androidx.compose.ui.text.input;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GapBuffer.kt */
/* JADX INFO: loaded from: classes.dex */
final class GapBuffer {
    private char[] buffer;
    private int capacity;
    private int gapEnd;
    private int gapStart;

    public GapBuffer(char[] cArr, int i, int i2) {
        this.capacity = cArr.length;
        this.buffer = cArr;
        this.gapStart = i;
        this.gapEnd = i2;
    }

    private final int gapLength() {
        return this.gapEnd - this.gapStart;
    }

    public final char get(int i) {
        int i2 = this.gapStart;
        if (i < i2) {
            return this.buffer[i];
        }
        return this.buffer[(i - i2) + this.gapEnd];
    }

    private final void makeSureAvailableSpace(int i) {
        if (i <= gapLength()) {
            return;
        }
        int iGapLength = i - gapLength();
        int i2 = this.capacity;
        do {
            i2 *= 2;
        } while (i2 - this.capacity < iGapLength);
        char[] cArr = new char[i2];
        ArraysKt.copyInto(this.buffer, cArr, 0, 0, this.gapStart);
        int i3 = this.capacity;
        int i4 = this.gapEnd;
        int i5 = i3 - i4;
        int i6 = i2 - i5;
        ArraysKt.copyInto(this.buffer, cArr, i6, i4, i5 + i4);
        this.buffer = cArr;
        this.capacity = i2;
        this.gapEnd = i6;
    }

    private final void delete(int i, int i2) {
        int i3 = this.gapStart;
        if (i < i3 && i2 <= i3) {
            int i4 = i3 - i2;
            char[] cArr = this.buffer;
            ArraysKt.copyInto(cArr, cArr, this.gapEnd - i4, i2, i3);
            this.gapStart = i;
            this.gapEnd -= i4;
            return;
        }
        if (i < i3 && i2 >= i3) {
            this.gapEnd = i2 + gapLength();
            this.gapStart = i;
            return;
        }
        int iGapLength = i + gapLength();
        int iGapLength2 = i2 + gapLength();
        int i5 = this.gapEnd;
        char[] cArr2 = this.buffer;
        ArraysKt.copyInto(cArr2, cArr2, this.gapStart, i5, iGapLength);
        this.gapStart += iGapLength - i5;
        this.gapEnd = iGapLength2;
    }

    public final void replace(int i, int i2, String str) {
        makeSureAvailableSpace(str.length() - (i2 - i));
        delete(i, i2);
        GapBufferKt.toCharArray(str, this.buffer, this.gapStart);
        this.gapStart += str.length();
    }

    public final void append(StringBuilder sb) {
        sb.append(this.buffer, 0, this.gapStart);
        Intrinsics.checkNotNullExpressionValue(sb, "this.append(value, start…x, endIndex - startIndex)");
        char[] cArr = this.buffer;
        int i = this.gapEnd;
        sb.append(cArr, i, this.capacity - i);
        Intrinsics.checkNotNullExpressionValue(sb, "this.append(value, start…x, endIndex - startIndex)");
    }

    public final int length() {
        return this.capacity - gapLength();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) sb);
        return sb.toString();
    }
}
