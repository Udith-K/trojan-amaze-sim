package androidx.compose.ui.text.style;

import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: compiled from: LineBreak.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LineBreak_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int packBytes(int i, int i2, int i3) {
        return i | (i2 << 8) | (i3 << 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int unpackByte1(int i) {
        return i & GF2Field.MASK;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int unpackByte2(int i) {
        return (i >> 8) & GF2Field.MASK;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int unpackByte3(int i) {
        return (i >> 16) & GF2Field.MASK;
    }
}
