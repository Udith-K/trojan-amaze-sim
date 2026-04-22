package androidx.compose.ui.graphics;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PaintingStyle.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PaintingStyle {
    public static final Companion Companion = new Companion(null);
    private static final int Fill = m1379constructorimpl(0);
    private static final int Stroke = m1379constructorimpl(1);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1379constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1380equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: compiled from: PaintingStyle.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getFill-TiuSbCo, reason: not valid java name */
        public final int m1381getFillTiuSbCo() {
            return PaintingStyle.Fill;
        }

        /* JADX INFO: renamed from: getStroke-TiuSbCo, reason: not valid java name */
        public final int m1382getStrokeTiuSbCo() {
            return PaintingStyle.Stroke;
        }
    }
}
