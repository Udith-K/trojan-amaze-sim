package androidx.compose.ui.unit;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import androidx.compose.ui.unit.TextUnitType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: TextUnit.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextUnit {
    public static final Companion Companion = new Companion(null);
    private static final TextUnitType[] TextUnitTypes;
    private static final long Unspecified;
    private final long packedValue;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TextUnit m2484boximpl(long j) {
        return new TextUnit(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2485constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2486equalsimpl(long j, Object obj) {
        return (obj instanceof TextUnit) && j == ((TextUnit) obj).m2493unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2487equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getRawType-impl, reason: not valid java name */
    public static final long m2488getRawTypeimpl(long j) {
        return j & 1095216660480L;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2491hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m2486equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m2491hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2493unboximpl() {
        return this.packedValue;
    }

    /* JADX INFO: renamed from: getValue-impl, reason: not valid java name */
    public static final float m2490getValueimpl(long j) {
        return Float.intBitsToFloat((int) (j & BodyPartID.bodyIdMax));
    }

    private /* synthetic */ TextUnit(long j) {
        this.packedValue = j;
    }

    public String toString() {
        return m2492toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2492toStringimpl(long j) {
        long jM2489getTypeUIouoOA = m2489getTypeUIouoOA(j);
        TextUnitType.Companion companion = TextUnitType.Companion;
        if (TextUnitType.m2503equalsimpl0(jM2489getTypeUIouoOA, companion.m2509getUnspecifiedUIouoOA())) {
            return "Unspecified";
        }
        if (TextUnitType.m2503equalsimpl0(jM2489getTypeUIouoOA, companion.m2508getSpUIouoOA())) {
            return m2490getValueimpl(j) + ".sp";
        }
        if (!TextUnitType.m2503equalsimpl0(jM2489getTypeUIouoOA, companion.m2507getEmUIouoOA())) {
            return "Invalid";
        }
        return m2490getValueimpl(j) + ".em";
    }

    /* JADX INFO: compiled from: TextUnit.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getUnspecified-XSAIIZE, reason: not valid java name */
        public final long m2494getUnspecifiedXSAIIZE() {
            return TextUnit.Unspecified;
        }
    }

    static {
        TextUnitType.Companion companion = TextUnitType.Companion;
        TextUnitTypes = new TextUnitType[]{TextUnitType.m2500boximpl(companion.m2509getUnspecifiedUIouoOA()), TextUnitType.m2500boximpl(companion.m2508getSpUIouoOA()), TextUnitType.m2500boximpl(companion.m2507getEmUIouoOA())};
        Unspecified = TextUnitKt.pack(0L, Float.NaN);
    }

    /* JADX INFO: renamed from: getType-UIouoOA, reason: not valid java name */
    public static final long m2489getTypeUIouoOA(long j) {
        return TextUnitTypes[(int) (m2488getRawTypeimpl(j) >>> 32)].m2506unboximpl();
    }
}
