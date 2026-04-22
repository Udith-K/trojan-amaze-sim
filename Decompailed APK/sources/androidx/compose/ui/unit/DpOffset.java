package androidx.compose.ui.unit;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: Dp.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DpOffset {
    private final long packedValue;
    public static final Companion Companion = new Companion(null);
    private static final long Zero = m2430constructorimpl(0);
    private static final long Unspecified = m2430constructorimpl(9205357640488583168L);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ DpOffset m2429boximpl(long j) {
        return new DpOffset(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2430constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2431equalsimpl(long j, Object obj) {
        return (obj instanceof DpOffset) && j == ((DpOffset) obj).m2437unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2432equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2435hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m2431equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m2435hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2437unboximpl() {
        return this.packedValue;
    }

    /* JADX INFO: renamed from: getX-D9Ej5fM, reason: not valid java name */
    public static final float m2433getXD9Ej5fM(long j) {
        return Dp.m2416constructorimpl(Float.intBitsToFloat((int) (j >> 32)));
    }

    /* JADX INFO: renamed from: getY-D9Ej5fM, reason: not valid java name */
    public static final float m2434getYD9Ej5fM(long j) {
        return Dp.m2416constructorimpl(Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax)));
    }

    private /* synthetic */ DpOffset(long j) {
        this.packedValue = j;
    }

    public String toString() {
        return m2436toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2436toStringimpl(long j) {
        if (j != 9205357640488583168L) {
            return CoreConstants.LEFT_PARENTHESIS_CHAR + ((Object) Dp.m2420toStringimpl(m2433getXD9Ej5fM(j))) + ", " + ((Object) Dp.m2420toStringimpl(m2434getYD9Ej5fM(j))) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }
        return "DpOffset.Unspecified";
    }

    /* JADX INFO: compiled from: Dp.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
