package kotlin;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ULong.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ULong implements Comparable {
    public static final Companion Companion = new Companion(null);
    private final long data;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ULong m2692boximpl(long j) {
        return new ULong(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m2693constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2694equalsimpl(long j, Object obj) {
        return (obj instanceof ULong) && j == ((ULong) obj).m2698unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2695equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2696hashCodeimpl(long j) {
        return LongObjectMap$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m2694equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m2696hashCodeimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m2698unboximpl() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return UnsignedKt.ulongCompare(m2698unboximpl(), ((ULong) obj).m2698unboximpl());
    }

    private /* synthetic */ ULong(long j) {
        this.data = j;
    }

    /* JADX INFO: compiled from: ULong.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2697toStringimpl(long j) {
        return UnsignedKt.ulongToString(j, 10);
    }

    public String toString() {
        return m2697toStringimpl(this.data);
    }
}
