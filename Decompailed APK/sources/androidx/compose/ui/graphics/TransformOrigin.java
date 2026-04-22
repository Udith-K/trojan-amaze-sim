package androidx.compose.ui.graphics;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: TransformOrigin.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TransformOrigin {
    private final long packedValue;
    public static final Companion Companion = new Companion(null);
    private static final long Center = TransformOriginKt.TransformOrigin(0.5f, 0.5f);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TransformOrigin m1425boximpl(long j) {
        return new TransformOrigin(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1426constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1427equalsimpl(long j, Object obj) {
        return (obj instanceof TransformOrigin) && j == ((TransformOrigin) obj).m1433unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1428equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1431hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1432toStringimpl(long j) {
        return "TransformOrigin(packedValue=" + j + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public boolean equals(Object obj) {
        return m1427equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m1431hashCodeimpl(this.packedValue);
    }

    public String toString() {
        return m1432toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m1433unboximpl() {
        return this.packedValue;
    }

    /* JADX INFO: renamed from: getPivotFractionX-impl, reason: not valid java name */
    public static final float m1429getPivotFractionXimpl(long j) {
        return Float.intBitsToFloat((int) (j >> 32));
    }

    /* JADX INFO: renamed from: getPivotFractionY-impl, reason: not valid java name */
    public static final float m1430getPivotFractionYimpl(long j) {
        return Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax));
    }

    private /* synthetic */ TransformOrigin(long j) {
        this.packedValue = j;
    }

    /* JADX INFO: compiled from: TransformOrigin.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getCenter-SzJe1aQ, reason: not valid java name */
        public final long m1434getCenterSzJe1aQ() {
            return TransformOrigin.Center;
        }
    }
}
