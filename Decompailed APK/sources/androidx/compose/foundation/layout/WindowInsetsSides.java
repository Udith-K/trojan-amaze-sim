package androidx.compose.foundation.layout;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WindowInsets.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class WindowInsetsSides {
    private static final int AllowLeftInLtr;
    private static final int AllowLeftInRtl;
    private static final int AllowRightInLtr;
    private static final int AllowRightInRtl;
    private static final int Bottom;
    public static final Companion Companion = new Companion(null);
    private static final int End;
    private static final int Horizontal;
    private static final int Left;
    private static final int Right;
    private static final int Start;
    private static final int Top;
    private static final int Vertical;

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m294constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m295equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hasAny-bkgdKaI$foundation_layout_release, reason: not valid java name */
    public static final boolean m296hasAnybkgdKaI$foundation_layout_release(int i, int i2) {
        return (i & i2) != 0;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m297hashCodeimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: plus-gK_yJZ4, reason: not valid java name */
    public static final int m298plusgK_yJZ4(int i, int i2) {
        return m294constructorimpl(i | i2);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m299toStringimpl(int i) {
        return "WindowInsetsSides(" + m300valueToStringimpl(i) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: renamed from: valueToString-impl, reason: not valid java name */
    private static final String m300valueToStringimpl(int i) {
        StringBuilder sb = new StringBuilder();
        int i2 = Start;
        if ((i & i2) == i2) {
            valueToString_impl$lambda$0$appendPlus(sb, "Start");
        }
        int i3 = Left;
        if ((i & i3) == i3) {
            valueToString_impl$lambda$0$appendPlus(sb, "Left");
        }
        int i4 = Top;
        if ((i & i4) == i4) {
            valueToString_impl$lambda$0$appendPlus(sb, "Top");
        }
        int i5 = End;
        if ((i & i5) == i5) {
            valueToString_impl$lambda$0$appendPlus(sb, "End");
        }
        int i6 = Right;
        if ((i & i6) == i6) {
            valueToString_impl$lambda$0$appendPlus(sb, "Right");
        }
        int i7 = Bottom;
        if ((i & i7) == i7) {
            valueToString_impl$lambda$0$appendPlus(sb, "Bottom");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private static final void valueToString_impl$lambda$0$appendPlus(StringBuilder sb, String str) {
        if (sb.length() > 0) {
            sb.append('+');
        }
        sb.append(str);
    }

    /* JADX INFO: compiled from: WindowInsets.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getAllowLeftInLtr-JoeWqyM$foundation_layout_release, reason: not valid java name */
        public final int m301getAllowLeftInLtrJoeWqyM$foundation_layout_release() {
            return WindowInsetsSides.AllowLeftInLtr;
        }

        /* JADX INFO: renamed from: getAllowRightInLtr-JoeWqyM$foundation_layout_release, reason: not valid java name */
        public final int m303getAllowRightInLtrJoeWqyM$foundation_layout_release() {
            return WindowInsetsSides.AllowRightInLtr;
        }

        /* JADX INFO: renamed from: getAllowLeftInRtl-JoeWqyM$foundation_layout_release, reason: not valid java name */
        public final int m302getAllowLeftInRtlJoeWqyM$foundation_layout_release() {
            return WindowInsetsSides.AllowLeftInRtl;
        }

        /* JADX INFO: renamed from: getAllowRightInRtl-JoeWqyM$foundation_layout_release, reason: not valid java name */
        public final int m304getAllowRightInRtlJoeWqyM$foundation_layout_release() {
            return WindowInsetsSides.AllowRightInRtl;
        }

        /* JADX INFO: renamed from: getTop-JoeWqyM, reason: not valid java name */
        public final int m307getTopJoeWqyM() {
            return WindowInsetsSides.Top;
        }

        /* JADX INFO: renamed from: getBottom-JoeWqyM, reason: not valid java name */
        public final int m305getBottomJoeWqyM() {
            return WindowInsetsSides.Bottom;
        }

        /* JADX INFO: renamed from: getHorizontal-JoeWqyM, reason: not valid java name */
        public final int m306getHorizontalJoeWqyM() {
            return WindowInsetsSides.Horizontal;
        }
    }

    static {
        int iM294constructorimpl = m294constructorimpl(8);
        AllowLeftInLtr = iM294constructorimpl;
        int iM294constructorimpl2 = m294constructorimpl(4);
        AllowRightInLtr = iM294constructorimpl2;
        int iM294constructorimpl3 = m294constructorimpl(2);
        AllowLeftInRtl = iM294constructorimpl3;
        int iM294constructorimpl4 = m294constructorimpl(1);
        AllowRightInRtl = iM294constructorimpl4;
        Start = m298plusgK_yJZ4(iM294constructorimpl, iM294constructorimpl4);
        End = m298plusgK_yJZ4(iM294constructorimpl2, iM294constructorimpl3);
        int iM294constructorimpl5 = m294constructorimpl(16);
        Top = iM294constructorimpl5;
        int iM294constructorimpl6 = m294constructorimpl(32);
        Bottom = iM294constructorimpl6;
        int iM298plusgK_yJZ4 = m298plusgK_yJZ4(iM294constructorimpl, iM294constructorimpl3);
        Left = iM298plusgK_yJZ4;
        int iM298plusgK_yJZ42 = m298plusgK_yJZ4(iM294constructorimpl2, iM294constructorimpl4);
        Right = iM298plusgK_yJZ42;
        Horizontal = m298plusgK_yJZ4(iM298plusgK_yJZ4, iM298plusgK_yJZ42);
        Vertical = m298plusgK_yJZ4(iM294constructorimpl5, iM294constructorimpl6);
    }
}
