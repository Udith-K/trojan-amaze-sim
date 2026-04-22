package androidx.compose.ui.hapticfeedback;

/* JADX INFO: compiled from: PlatformHapticFeedback.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PlatformHapticFeedbackType {
    public static final PlatformHapticFeedbackType INSTANCE = new PlatformHapticFeedbackType();
    private static final int LongPress = HapticFeedbackType.m1579constructorimpl(0);
    private static final int TextHandleMove = HapticFeedbackType.m1579constructorimpl(9);

    private PlatformHapticFeedbackType() {
    }

    /* JADX INFO: renamed from: getLongPress-5zf0vsI, reason: not valid java name */
    public final int m1583getLongPress5zf0vsI() {
        return LongPress;
    }

    /* JADX INFO: renamed from: getTextHandleMove-5zf0vsI, reason: not valid java name */
    public final int m1584getTextHandleMove5zf0vsI() {
        return TextHandleMove;
    }
}
