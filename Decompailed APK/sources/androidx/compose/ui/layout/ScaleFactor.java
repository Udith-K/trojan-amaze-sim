package androidx.compose.ui.layout;

import androidx.compose.ui.internal.InlineClassHelperKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: ScaleFactor.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ScaleFactor {
    public static final Companion Companion = new Companion(null);
    private static final long Unspecified = ScaleFactorKt.ScaleFactor(Float.NaN, Float.NaN);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1777constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1778equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getScaleX-impl, reason: not valid java name */
    public static final float m1779getScaleXimpl(long j) {
        if (!(j != Unspecified)) {
            InlineClassHelperKt.throwIllegalStateException("ScaleFactor is unspecified");
        }
        return Float.intBitsToFloat((int) (j >> 32));
    }

    /* JADX INFO: renamed from: getScaleY-impl, reason: not valid java name */
    public static final float m1780getScaleYimpl(long j) {
        if (!(j != Unspecified)) {
            InlineClassHelperKt.throwIllegalStateException("ScaleFactor is unspecified");
        }
        return Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: compiled from: ScaleFactor.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getUnspecified-_hLwfpc, reason: not valid java name */
        public final long m1781getUnspecified_hLwfpc() {
            return ScaleFactor.Unspecified;
        }
    }
}
