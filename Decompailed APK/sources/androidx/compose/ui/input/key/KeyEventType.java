package androidx.compose.ui.input.key;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: KeyEvent.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class KeyEventType {
    public static final Companion Companion = new Companion(null);
    private static final int Unknown = m1621constructorimpl(0);
    private static final int KeyUp = m1621constructorimpl(1);
    private static final int KeyDown = m1621constructorimpl(2);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1621constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1622equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: compiled from: KeyEvent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getUnknown-CS__XNY, reason: not valid java name */
        public final int m1625getUnknownCS__XNY() {
            return KeyEventType.Unknown;
        }

        /* JADX INFO: renamed from: getKeyUp-CS__XNY, reason: not valid java name */
        public final int m1624getKeyUpCS__XNY() {
            return KeyEventType.KeyUp;
        }

        /* JADX INFO: renamed from: getKeyDown-CS__XNY, reason: not valid java name */
        public final int m1623getKeyDownCS__XNY() {
            return KeyEventType.KeyDown;
        }
    }
}
