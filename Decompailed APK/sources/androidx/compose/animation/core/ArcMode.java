package androidx.compose.animation.core;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: VectorizedAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ArcMode {
    public static final Companion Companion = new Companion(null);
    private static final int ArcAbove = m48constructorimpl(5);
    private static final int ArcBelow = m48constructorimpl(4);
    private static final int ArcLinear = m48constructorimpl(0);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m48constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m49equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m50hashCodeimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m51toStringimpl(int i) {
        return "ArcMode(value=" + i + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: VectorizedAnimationSpec.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getArcLinear--9T-Mq4, reason: not valid java name */
        public final int m52getArcLinear9TMq4() {
            return ArcMode.ArcLinear;
        }
    }
}
