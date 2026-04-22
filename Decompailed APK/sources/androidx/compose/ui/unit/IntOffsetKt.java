package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: IntOffset.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class IntOffsetKt {
    public static final long IntOffset(int i, int i2) {
        return IntOffset.m2452constructorimpl((((long) i2) & BodyPartID.bodyIdMax) | (((long) i) << 32));
    }

    /* JADX INFO: renamed from: plus-Nv-tHpc, reason: not valid java name */
    public static final long m2466plusNvtHpc(long j, long j2) {
        return OffsetKt.Offset(Offset.m1159getXimpl(j) + IntOffset.m2457getXimpl(j2), Offset.m1160getYimpl(j) + IntOffset.m2458getYimpl(j2));
    }

    /* JADX INFO: renamed from: minus-Nv-tHpc, reason: not valid java name */
    public static final long m2465minusNvtHpc(long j, long j2) {
        return OffsetKt.Offset(Offset.m1159getXimpl(j) - IntOffset.m2457getXimpl(j2), Offset.m1160getYimpl(j) - IntOffset.m2458getYimpl(j2));
    }

    /* JADX INFO: renamed from: round-k-4lQ0M, reason: not valid java name */
    public static final long m2467roundk4lQ0M(long j) {
        int iRound = Math.round(Offset.m1159getXimpl(j));
        return IntOffset.m2452constructorimpl((((long) Math.round(Offset.m1160getYimpl(j))) & BodyPartID.bodyIdMax) | (((long) iRound) << 32));
    }
}
