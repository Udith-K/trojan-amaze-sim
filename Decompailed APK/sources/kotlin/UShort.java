package kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UShort.kt */
/* JADX INFO: loaded from: classes.dex */
public final class UShort implements Comparable {
    public static final Companion Companion = new Companion(null);
    private final short data;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UShort m2715boximpl(short s) {
        return new UShort(s);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static short m2716constructorimpl(short s) {
        return s;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2717equalsimpl(short s, Object obj) {
        return (obj instanceof UShort) && s == ((UShort) obj).m2720unboximpl();
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2718hashCodeimpl(short s) {
        return s;
    }

    public boolean equals(Object obj) {
        return m2717equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m2718hashCodeimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ short m2720unboximpl() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return Intrinsics.compare(m2720unboximpl() & 65535, ((UShort) obj).m2720unboximpl() & 65535);
    }

    private /* synthetic */ UShort(short s) {
        this.data = s;
    }

    /* JADX INFO: compiled from: UShort.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2719toStringimpl(short s) {
        return String.valueOf(s & 65535);
    }

    public String toString() {
        return m2719toStringimpl(this.data);
    }
}
