package androidx.compose.ui.text.style;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: BaselineShift.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BaselineShift {
    private final float multiplier;
    public static final Companion Companion = new Companion(null);
    private static final float Superscript = m2268constructorimpl(0.5f);
    private static final float Subscript = m2268constructorimpl(-0.5f);
    private static final float None = m2268constructorimpl(0.0f);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ BaselineShift m2267boximpl(float f) {
        return new BaselineShift(f);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static float m2268constructorimpl(float f) {
        return f;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2269equalsimpl(float f, Object obj) {
        return (obj instanceof BaselineShift) && Float.compare(f, ((BaselineShift) obj).m2273unboximpl()) == 0;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2270equalsimpl0(float f, float f2) {
        return Float.compare(f, f2) == 0;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2271hashCodeimpl(float f) {
        return Float.floatToIntBits(f);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2272toStringimpl(float f) {
        return "BaselineShift(multiplier=" + f + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public boolean equals(Object obj) {
        return m2269equalsimpl(this.multiplier, obj);
    }

    public int hashCode() {
        return m2271hashCodeimpl(this.multiplier);
    }

    public String toString() {
        return m2272toStringimpl(this.multiplier);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ float m2273unboximpl() {
        return this.multiplier;
    }

    /* JADX INFO: compiled from: BaselineShift.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getNone-y9eOQZs, reason: not valid java name */
        public final float m2274getNoney9eOQZs() {
            return BaselineShift.None;
        }
    }

    private /* synthetic */ BaselineShift(float f) {
        this.multiplier = f;
    }
}
