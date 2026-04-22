package androidx.compose.ui.graphics.colorspace;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: ColorModel.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ColorModel {
    private static final long Cmyk;
    public static final Companion Companion = new Companion(null);
    private static final long Lab;
    private static final long Rgb;
    private static final long Xyz;

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m1436constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1437equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getComponentCount-impl, reason: not valid java name */
    public static final int m1438getComponentCountimpl(long j) {
        return (int) (j >> 32);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1439hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    /* JADX INFO: compiled from: ColorModel.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getRgb-xdoWZVw, reason: not valid java name */
        public final long m1442getRgbxdoWZVw() {
            return ColorModel.Rgb;
        }

        /* JADX INFO: renamed from: getXyz-xdoWZVw, reason: not valid java name */
        public final long m1443getXyzxdoWZVw() {
            return ColorModel.Xyz;
        }

        /* JADX INFO: renamed from: getLab-xdoWZVw, reason: not valid java name */
        public final long m1441getLabxdoWZVw() {
            return ColorModel.Lab;
        }
    }

    static {
        long j = 3;
        long j2 = j << 32;
        Rgb = m1436constructorimpl((((long) 0) & BodyPartID.bodyIdMax) | j2);
        Xyz = m1436constructorimpl((((long) 1) & BodyPartID.bodyIdMax) | j2);
        Lab = m1436constructorimpl(j2 | (((long) 2) & BodyPartID.bodyIdMax));
        Cmyk = m1436constructorimpl((j & BodyPartID.bodyIdMax) | (((long) 4) << 32));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1440toStringimpl(long j) {
        return m1437equalsimpl0(j, Rgb) ? "Rgb" : m1437equalsimpl0(j, Xyz) ? "Xyz" : m1437equalsimpl0(j, Lab) ? "Lab" : m1437equalsimpl0(j, Cmyk) ? "Cmyk" : "Unknown";
    }
}
