package kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UByte.kt */
/* JADX INFO: loaded from: classes.dex */
public final class UByte implements Comparable {
    public static final Companion Companion = new Companion(null);
    private final byte data;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UByte m2648boximpl(byte b) {
        return new UByte(b);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static byte m2649constructorimpl(byte b) {
        return b;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2650equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).m2653unboximpl();
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2651hashCodeimpl(byte b) {
        return b;
    }

    public boolean equals(Object obj) {
        return m2650equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m2651hashCodeimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ byte m2653unboximpl() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return Intrinsics.compare(m2653unboximpl() & 255, ((UByte) obj).m2653unboximpl() & 255);
    }

    private /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    /* JADX INFO: compiled from: UByte.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2652toStringimpl(byte b) {
        return String.valueOf(b & 255);
    }

    public String toString() {
        return m2652toStringimpl(this.data);
    }
}
