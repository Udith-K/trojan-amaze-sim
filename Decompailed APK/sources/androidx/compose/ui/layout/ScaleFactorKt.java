package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: ScaleFactor.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ScaleFactorKt {
    public static final long ScaleFactor(float f, float f2) {
        return ScaleFactor.m1777constructorimpl((((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax) | (Float.floatToRawIntBits(f) << 32));
    }

    /* JADX INFO: renamed from: times-UQTWf7w, reason: not valid java name */
    public static final long m1782timesUQTWf7w(long j, long j2) {
        return SizeKt.Size(Size.m1196getWidthimpl(j) * ScaleFactor.m1779getScaleXimpl(j2), Size.m1194getHeightimpl(j) * ScaleFactor.m1780getScaleYimpl(j2));
    }

    /* JADX INFO: renamed from: times-m-w2e94, reason: not valid java name */
    public static final long m1783timesmw2e94(long j, long j2) {
        return m1782timesUQTWf7w(j2, j);
    }
}
