package kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: UInt.kt */
/* JADX INFO: loaded from: classes.dex */
public final class UInt implements Comparable {
    public static final Companion Companion = new Companion(null);
    private final int data;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UInt m2670boximpl(int i) {
        return new UInt(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2671constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2672equalsimpl(int i, Object obj) {
        return (obj instanceof UInt) && i == ((UInt) obj).m2675unboximpl();
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2673hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m2672equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m2673hashCodeimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2675unboximpl() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return UnsignedKt.uintCompare(m2675unboximpl(), ((UInt) obj).m2675unboximpl());
    }

    private /* synthetic */ UInt(int i) {
        this.data = i;
    }

    /* JADX INFO: compiled from: UInt.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2674toStringimpl(int i) {
        return String.valueOf(((long) i) & BodyPartID.bodyIdMax);
    }

    public String toString() {
        return m2674toStringimpl(this.data);
    }
}
