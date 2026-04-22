package androidx.compose.ui.graphics;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: GraphicsLayerModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CompositingStrategy {
    public static final Companion Companion = new Companion(null);
    private static final int Auto = m1318constructorimpl(0);
    private static final int Offscreen = m1318constructorimpl(1);
    private static final int ModulateAlpha = m1318constructorimpl(2);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1318constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1319equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1320hashCodeimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1321toStringimpl(int i) {
        return "CompositingStrategy(value=" + i + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: GraphicsLayerModifier.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getAuto--NrFUSI, reason: not valid java name */
        public final int m1322getAutoNrFUSI() {
            return CompositingStrategy.Auto;
        }

        /* JADX INFO: renamed from: getOffscreen--NrFUSI, reason: not valid java name */
        public final int m1324getOffscreenNrFUSI() {
            return CompositingStrategy.Offscreen;
        }

        /* JADX INFO: renamed from: getModulateAlpha--NrFUSI, reason: not valid java name */
        public final int m1323getModulateAlphaNrFUSI() {
            return CompositingStrategy.ModulateAlpha;
        }
    }
}
