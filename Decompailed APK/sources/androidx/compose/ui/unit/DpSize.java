package androidx.compose.ui.unit;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Dp.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DpSize {
    private final long packedValue;
    public static final Companion Companion = new Companion(null);
    private static final long Zero = m2439constructorimpl(0);
    private static final long Unspecified = m2439constructorimpl(9205357640488583168L);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ DpSize m2438boximpl(long j) {
        return new DpSize(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2439constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2440equalsimpl(long j, Object obj) {
        return (obj instanceof DpSize) && j == ((DpSize) obj).m2446unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2441equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2444hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m2440equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m2444hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2446unboximpl() {
        return this.packedValue;
    }

    /* JADX INFO: renamed from: getHeight-D9Ej5fM, reason: not valid java name */
    public static final float m2442getHeightD9Ej5fM(long j) {
        return Dp.m2416constructorimpl(Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax)));
    }

    /* JADX INFO: renamed from: getWidth-D9Ej5fM, reason: not valid java name */
    public static final float m2443getWidthD9Ej5fM(long j) {
        return Dp.m2416constructorimpl(Float.intBitsToFloat((int) (j >> 32)));
    }

    private /* synthetic */ DpSize(long j) {
        this.packedValue = j;
    }

    public String toString() {
        return m2445toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2445toStringimpl(long j) {
        if (j != 9205357640488583168L) {
            return ((Object) Dp.m2420toStringimpl(m2443getWidthD9Ej5fM(j))) + " x " + ((Object) Dp.m2420toStringimpl(m2442getHeightD9Ej5fM(j)));
        }
        return "DpSize.Unspecified";
    }

    /* JADX INFO: compiled from: Dp.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getZero-MYxV2XQ, reason: not valid java name */
        public final long m2448getZeroMYxV2XQ() {
            return DpSize.Zero;
        }

        /* JADX INFO: renamed from: getUnspecified-MYxV2XQ, reason: not valid java name */
        public final long m2447getUnspecifiedMYxV2XQ() {
            return DpSize.Unspecified;
        }
    }
}
