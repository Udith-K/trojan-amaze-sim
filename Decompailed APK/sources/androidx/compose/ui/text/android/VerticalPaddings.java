package androidx.compose.ui.text.android;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: TextLayout.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class VerticalPaddings {
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2143constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: getBottomPadding-impl, reason: not valid java name */
    public static final int m2144getBottomPaddingimpl(long j) {
        return (int) (j & BodyPartID.bodyIdMax);
    }

    /* JADX INFO: renamed from: getTopPadding-impl, reason: not valid java name */
    public static final int m2145getTopPaddingimpl(long j) {
        return (int) (j >> 32);
    }
}
