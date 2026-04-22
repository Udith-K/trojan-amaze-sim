package androidx.compose.ui.unit;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TextUnit.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextUnitType {
    private final long type;
    public static final Companion Companion = new Companion(null);
    private static final long Unspecified = m2501constructorimpl(0);
    private static final long Sp = m2501constructorimpl(4294967296L);
    private static final long Em = m2501constructorimpl(8589934592L);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TextUnitType m2500boximpl(long j) {
        return new TextUnitType(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2501constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2502equalsimpl(long j, Object obj) {
        return (obj instanceof TextUnitType) && j == ((TextUnitType) obj).m2506unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2503equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2504hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m2502equalsimpl(this.type, obj);
    }

    public int hashCode() {
        return m2504hashCodeimpl(this.type);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2506unboximpl() {
        return this.type;
    }

    private /* synthetic */ TextUnitType(long j) {
        this.type = j;
    }

    public String toString() {
        return m2505toStringimpl(this.type);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2505toStringimpl(long j) {
        return m2503equalsimpl0(j, Unspecified) ? "Unspecified" : m2503equalsimpl0(j, Sp) ? "Sp" : m2503equalsimpl0(j, Em) ? "Em" : "Invalid";
    }

    /* JADX INFO: compiled from: TextUnit.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getUnspecified-UIouoOA, reason: not valid java name */
        public final long m2509getUnspecifiedUIouoOA() {
            return TextUnitType.Unspecified;
        }

        /* JADX INFO: renamed from: getSp-UIouoOA, reason: not valid java name */
        public final long m2508getSpUIouoOA() {
            return TextUnitType.Sp;
        }

        /* JADX INFO: renamed from: getEm-UIouoOA, reason: not valid java name */
        public final long m2507getEmUIouoOA() {
            return TextUnitType.Em;
        }
    }
}
