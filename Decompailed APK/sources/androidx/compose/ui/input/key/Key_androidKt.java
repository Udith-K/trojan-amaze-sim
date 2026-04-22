package androidx.compose.ui.input.key;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Key.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Key_androidKt {
    /* JADX INFO: renamed from: getNativeKeyCode-YVgTNJs, reason: not valid java name */
    public static final int m1632getNativeKeyCodeYVgTNJs(long j) {
        return (int) (j >> 32);
    }

    public static final long Key(int i) {
        return Key.m1597constructorimpl((((long) i) << 32) | (((long) 0) & BodyPartID.bodyIdMax));
    }
}
