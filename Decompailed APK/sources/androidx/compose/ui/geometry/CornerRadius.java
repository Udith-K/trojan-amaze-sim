package androidx.compose.ui.geometry;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: CornerRadius.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CornerRadius {
    public static final Companion Companion = new Companion(null);
    private static final long Zero = CornerRadiusKt.CornerRadius$default(0.0f, 0.0f, 2, null);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1143constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1144equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1147hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    /* JADX INFO: renamed from: getX-impl, reason: not valid java name */
    public static final float m1145getXimpl(long j) {
        return Float.intBitsToFloat((int) (j >> 32));
    }

    /* JADX INFO: renamed from: getY-impl, reason: not valid java name */
    public static final float m1146getYimpl(long j) {
        return Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: compiled from: CornerRadius.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getZero-kKHJgLs, reason: not valid java name */
        public final long m1149getZerokKHJgLs() {
            return CornerRadius.Zero;
        }
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1148toStringimpl(long j) {
        if (m1145getXimpl(j) == m1146getYimpl(j)) {
            return "CornerRadius.circular(" + GeometryUtilsKt.toStringAsFixed(m1145getXimpl(j), 1) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }
        return "CornerRadius.elliptical(" + GeometryUtilsKt.toStringAsFixed(m1145getXimpl(j), 1) + ", " + GeometryUtilsKt.toStringAsFixed(m1146getYimpl(j), 1) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
