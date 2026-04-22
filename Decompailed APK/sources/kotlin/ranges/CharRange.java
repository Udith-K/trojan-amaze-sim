package kotlin.ranges;

import ch.qos.logback.classic.pattern.CallerDataConverter;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PrimitiveRanges.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CharRange extends CharProgression implements ClosedRange {
    public static final Companion Companion = new Companion(null);
    private static final CharRange EMPTY = new CharRange(1, 0);

    public CharRange(char c, char c2) {
        super(c, c2, 1);
    }

    public boolean isEmpty() {
        return Intrinsics.compare((int) getFirst(), (int) getLast()) > 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CharRange) {
            if (!isEmpty() || !((CharRange) obj).isEmpty()) {
                CharRange charRange = (CharRange) obj;
                if (getFirst() != charRange.getFirst() || getLast() != charRange.getLast()) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (getFirst() * 31) + getLast();
    }

    public String toString() {
        return getFirst() + CallerDataConverter.DEFAULT_RANGE_DELIMITER + getLast();
    }

    /* JADX INFO: compiled from: PrimitiveRanges.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
