package androidx.compose.ui.unit;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Velocity.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Velocity {
    public static final Companion Companion = new Companion(null);
    private static final long Zero = m2511constructorimpl(0);
    private final long packedValue;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Velocity m2510boximpl(long j) {
        return new Velocity(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2511constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2514equalsimpl(long j, Object obj) {
        return (obj instanceof Velocity) && j == ((Velocity) obj).m2523unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2515equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2518hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m2514equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m2518hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2523unboximpl() {
        return this.packedValue;
    }

    /* JADX INFO: renamed from: copy-OhffZ5M$default, reason: not valid java name */
    public static /* synthetic */ long m2513copyOhffZ5M$default(long j, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Float.intBitsToFloat((int) (j >> 32));
        }
        if ((i & 2) != 0) {
            f2 = Float.intBitsToFloat((int) (BodyPartID.bodyIdMax & j));
        }
        return m2512copyOhffZ5M(j, f, f2);
    }

    /* JADX INFO: renamed from: getX-impl, reason: not valid java name */
    public static final float m2516getXimpl(long j) {
        return Float.intBitsToFloat((int) (j >> 32));
    }

    /* JADX INFO: renamed from: getY-impl, reason: not valid java name */
    public static final float m2517getYimpl(long j) {
        return Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: minus-AH228Gc, reason: not valid java name */
    public static final long m2519minusAH228Gc(long j, long j2) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) - Float.intBitsToFloat((int) (j2 >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax)) - Float.intBitsToFloat((int) (j2 & BodyPartID.bodyIdMax));
        return m2511constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: plus-AH228Gc, reason: not valid java name */
    public static final long m2520plusAH228Gc(long j, long j2) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) + Float.intBitsToFloat((int) (j2 >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax)) + Float.intBitsToFloat((int) (j2 & BodyPartID.bodyIdMax));
        return m2511constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: times-adjELrA, reason: not valid java name */
    public static final long m2521timesadjELrA(long j, float f) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) * f;
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax)) * f;
        return m2511constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & BodyPartID.bodyIdMax));
    }

    private /* synthetic */ Velocity(long j) {
        this.packedValue = j;
    }

    /* JADX INFO: renamed from: copy-OhffZ5M, reason: not valid java name */
    public static final long m2512copyOhffZ5M(long j, float f, float f2) {
        return m2511constructorimpl((((long) Float.floatToRawIntBits(f)) << 32) | (((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: compiled from: Velocity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getZero-9UxMQ8M, reason: not valid java name */
        public final long m2524getZero9UxMQ8M() {
            return Velocity.Zero;
        }
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2522toStringimpl(long j) {
        return CoreConstants.LEFT_PARENTHESIS_CHAR + m2516getXimpl(j) + ", " + m2517getYimpl(j) + ") px/sec";
    }

    public String toString() {
        return m2522toStringimpl(this.packedValue);
    }
}
