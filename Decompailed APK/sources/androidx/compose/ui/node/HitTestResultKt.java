package androidx.compose.ui.node;

import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: HitTestResult.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HitTestResultKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final long DistanceAndInLayer(float f, boolean z) {
        return DistanceAndInLayer.m1797constructorimpl(((z ? 1L : 0L) & BodyPartID.bodyIdMax) | (((long) Float.floatToIntBits(f)) << 32));
    }
}
