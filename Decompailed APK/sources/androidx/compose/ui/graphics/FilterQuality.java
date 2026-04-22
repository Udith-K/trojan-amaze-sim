package androidx.compose.ui.graphics;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FilterQuality.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FilterQuality {
    public static final Companion Companion = new Companion(null);
    private static final int None = m1325constructorimpl(0);
    private static final int Low = m1325constructorimpl(1);
    private static final int Medium = m1325constructorimpl(2);
    private static final int High = m1325constructorimpl(3);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1325constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1326equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1327hashCodeimpl(int i) {
        return i;
    }

    /* JADX INFO: compiled from: FilterQuality.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getNone-f-v9h1I, reason: not valid java name */
        public final int m1330getNonefv9h1I() {
            return FilterQuality.None;
        }

        /* JADX INFO: renamed from: getLow-f-v9h1I, reason: not valid java name */
        public final int m1329getLowfv9h1I() {
            return FilterQuality.Low;
        }
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1328toStringimpl(int i) {
        return m1326equalsimpl0(i, None) ? "None" : m1326equalsimpl0(i, Low) ? "Low" : m1326equalsimpl0(i, Medium) ? "Medium" : m1326equalsimpl0(i, High) ? "High" : "Unknown";
    }
}
