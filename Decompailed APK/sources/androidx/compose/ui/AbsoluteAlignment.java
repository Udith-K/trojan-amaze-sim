package androidx.compose.ui;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAbsoluteAlignment;

/* JADX INFO: compiled from: Alignment.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AbsoluteAlignment {
    public static final AbsoluteAlignment INSTANCE = new AbsoluteAlignment();
    private static final Alignment TopLeft = new BiasAbsoluteAlignment(-1.0f, -1.0f);
    private static final Alignment TopRight = new BiasAbsoluteAlignment(1.0f, -1.0f);
    private static final Alignment CenterLeft = new BiasAbsoluteAlignment(-1.0f, 0.0f);
    private static final Alignment CenterRight = new BiasAbsoluteAlignment(1.0f, 0.0f);
    private static final Alignment BottomLeft = new BiasAbsoluteAlignment(-1.0f, 1.0f);
    private static final Alignment BottomRight = new BiasAbsoluteAlignment(1.0f, 1.0f);
    private static final Alignment.Horizontal Left = new BiasAbsoluteAlignment.Horizontal(-1.0f);
    private static final Alignment.Horizontal Right = new BiasAbsoluteAlignment.Horizontal(1.0f);

    private AbsoluteAlignment() {
    }

    public final Alignment getTopLeft() {
        return TopLeft;
    }

    public final Alignment getTopRight() {
        return TopRight;
    }

    public final Alignment.Horizontal getLeft() {
        return Left;
    }

    public final Alignment.Horizontal getRight() {
        return Right;
    }
}
