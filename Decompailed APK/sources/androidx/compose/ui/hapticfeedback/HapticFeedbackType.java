package androidx.compose.ui.hapticfeedback;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: HapticFeedbackType.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HapticFeedbackType {
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1579constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1580equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: compiled from: HapticFeedbackType.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getLongPress-5zf0vsI, reason: not valid java name */
        public final int m1581getLongPress5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m1583getLongPress5zf0vsI();
        }

        /* JADX INFO: renamed from: getTextHandleMove-5zf0vsI, reason: not valid java name */
        public final int m1582getTextHandleMove5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m1584getTextHandleMove5zf0vsI();
        }
    }
}
