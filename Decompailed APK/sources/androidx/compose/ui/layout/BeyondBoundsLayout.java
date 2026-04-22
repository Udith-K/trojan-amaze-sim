package androidx.compose.ui.layout;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: BeyondBoundsLayout.kt */
/* JADX INFO: loaded from: classes.dex */
public interface BeyondBoundsLayout {

    /* JADX INFO: compiled from: BeyondBoundsLayout.kt */
    public interface BeyondBoundsScope {
        boolean getHasMoreContent();
    }

    /* JADX INFO: renamed from: layout-o7g1Pn8 */
    Object mo330layouto7g1Pn8(int i, Function1 function1);

    /* JADX INFO: compiled from: BeyondBoundsLayout.kt */
    public static final class LayoutDirection {
        public static final Companion Companion = new Companion(null);
        private static final int Before = m1725constructorimpl(1);
        private static final int After = m1725constructorimpl(2);
        private static final int Left = m1725constructorimpl(3);
        private static final int Right = m1725constructorimpl(4);
        private static final int Above = m1725constructorimpl(5);
        private static final int Below = m1725constructorimpl(6);

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static int m1725constructorimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m1726equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* JADX INFO: compiled from: BeyondBoundsLayout.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getBefore-hoxUOeE, reason: not valid java name */
            public final int m1729getBeforehoxUOeE() {
                return LayoutDirection.Before;
            }

            /* JADX INFO: renamed from: getAfter-hoxUOeE, reason: not valid java name */
            public final int m1728getAfterhoxUOeE() {
                return LayoutDirection.After;
            }

            /* JADX INFO: renamed from: getLeft-hoxUOeE, reason: not valid java name */
            public final int m1731getLefthoxUOeE() {
                return LayoutDirection.Left;
            }

            /* JADX INFO: renamed from: getRight-hoxUOeE, reason: not valid java name */
            public final int m1732getRighthoxUOeE() {
                return LayoutDirection.Right;
            }

            /* JADX INFO: renamed from: getAbove-hoxUOeE, reason: not valid java name */
            public final int m1727getAbovehoxUOeE() {
                return LayoutDirection.Above;
            }

            /* JADX INFO: renamed from: getBelow-hoxUOeE, reason: not valid java name */
            public final int m1730getBelowhoxUOeE() {
                return LayoutDirection.Below;
            }
        }
    }
}
