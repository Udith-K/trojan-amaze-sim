package androidx.compose.foundation.text.modifiers;

import androidx.compose.ui.unit.Density;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: InlineDensity.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class InlineDensity {
    public static final Companion Companion = new Companion(null);
    private static final long Unspecified = m506constructorimpl(Float.NaN, Float.NaN);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static long m507constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m509equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getDensity-impl, reason: not valid java name */
    public static final float m510getDensityimpl(long j) {
        return Float.intBitsToFloat((int) (j >> 32));
    }

    /* JADX INFO: renamed from: getFontScale-impl, reason: not valid java name */
    public static final float m511getFontScaleimpl(long j) {
        return Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax));
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m508constructorimpl(Density density) {
        return m506constructorimpl(density.getDensity(), density.getFontScale());
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m512toStringimpl(long j) {
        return "InlineDensity(density=" + m510getDensityimpl(j) + ", fontScale=" + m511getFontScaleimpl(j) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: InlineDensity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getUnspecified-L26CHvs, reason: not valid java name */
        public final long m513getUnspecifiedL26CHvs() {
            return InlineDensity.Unspecified;
        }
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m506constructorimpl(float f, float f2) {
        return m507constructorimpl((((long) Float.floatToRawIntBits(f2)) & BodyPartID.bodyIdMax) | (Float.floatToRawIntBits(f) << 32));
    }
}
