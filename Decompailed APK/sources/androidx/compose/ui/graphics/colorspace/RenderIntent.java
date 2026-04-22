package androidx.compose.ui.graphics.colorspace;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: RenderIntent.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RenderIntent {
    public static final Companion Companion = new Companion(null);
    private static final int Perceptual = m1453constructorimpl(0);
    private static final int Relative = m1453constructorimpl(1);
    private static final int Saturation = m1453constructorimpl(2);
    private static final int Absolute = m1453constructorimpl(3);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1453constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1454equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: compiled from: RenderIntent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getPerceptual-uksYyKA, reason: not valid java name */
        public final int m1456getPerceptualuksYyKA() {
            return RenderIntent.Perceptual;
        }

        /* JADX INFO: renamed from: getRelative-uksYyKA, reason: not valid java name */
        public final int m1457getRelativeuksYyKA() {
            return RenderIntent.Relative;
        }

        /* JADX INFO: renamed from: getAbsolute-uksYyKA, reason: not valid java name */
        public final int m1455getAbsoluteuksYyKA() {
            return RenderIntent.Absolute;
        }
    }
}
