package androidx.compose.ui.text.style;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TextOverflow.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextOverflow {
    public static final Companion Companion = new Companion(null);
    private static final int Clip = m2371constructorimpl(1);
    private static final int Ellipsis = m2371constructorimpl(2);
    private static final int Visible = m2371constructorimpl(3);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2371constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2372equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2373hashCodeimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2374toStringimpl(int i) {
        return m2372equalsimpl0(i, Clip) ? "Clip" : m2372equalsimpl0(i, Ellipsis) ? "Ellipsis" : m2372equalsimpl0(i, Visible) ? "Visible" : "Invalid";
    }

    /* JADX INFO: compiled from: TextOverflow.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getClip-gIe3tQ8, reason: not valid java name */
        public final int m2375getClipgIe3tQ8() {
            return TextOverflow.Clip;
        }

        /* JADX INFO: renamed from: getEllipsis-gIe3tQ8, reason: not valid java name */
        public final int m2376getEllipsisgIe3tQ8() {
            return TextOverflow.Ellipsis;
        }

        /* JADX INFO: renamed from: getVisible-gIe3tQ8, reason: not valid java name */
        public final int m2377getVisiblegIe3tQ8() {
            return TextOverflow.Visible;
        }
    }
}
