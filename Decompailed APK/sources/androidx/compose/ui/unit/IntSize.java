package androidx.compose.ui.unit;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: IntSize.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IntSize {
    public static final Companion Companion = new Companion(null);
    private static final long Zero = m2472constructorimpl(0);
    private final long packedValue;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ IntSize m2471boximpl(long j) {
        return new IntSize(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2472constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2473equalsimpl(long j, Object obj) {
        return (obj instanceof IntSize) && j == ((IntSize) obj).m2479unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2474equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getHeight-impl, reason: not valid java name */
    public static final int m2475getHeightimpl(long j) {
        return (int) (j & BodyPartID.bodyIdMax);
    }

    /* JADX INFO: renamed from: getWidth-impl, reason: not valid java name */
    public static final int m2476getWidthimpl(long j) {
        return (int) (j >> 32);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2477hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m2473equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m2477hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2479unboximpl() {
        return this.packedValue;
    }

    private /* synthetic */ IntSize(long j) {
        this.packedValue = j;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2478toStringimpl(long j) {
        return m2476getWidthimpl(j) + " x " + m2475getHeightimpl(j);
    }

    public String toString() {
        return m2478toStringimpl(this.packedValue);
    }

    /* JADX INFO: compiled from: IntSize.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getZero-YbymL2g, reason: not valid java name */
        public final long m2480getZeroYbymL2g() {
            return IntSize.Zero;
        }
    }
}
