package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.LayoutDirection;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RowColumnImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CrossAxisAlignment {
    public static final Companion Companion = new Companion(null);
    private static final CrossAxisAlignment Center = CenterCrossAxisAlignment.INSTANCE;
    private static final CrossAxisAlignment Start = StartCrossAxisAlignment.INSTANCE;
    private static final CrossAxisAlignment End = EndCrossAxisAlignment.INSTANCE;

    public /* synthetic */ CrossAxisAlignment(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract int align$foundation_layout_release(int i, LayoutDirection layoutDirection, Placeable placeable, int i2);

    public Integer calculateAlignmentLinePosition$foundation_layout_release(Placeable placeable) {
        return null;
    }

    public boolean isRelative$foundation_layout_release() {
        return false;
    }

    private CrossAxisAlignment() {
    }

    /* JADX INFO: compiled from: RowColumnImpl.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CrossAxisAlignment vertical$foundation_layout_release(Alignment.Vertical vertical) {
            return new VerticalCrossAxisAlignment(vertical);
        }

        public final CrossAxisAlignment horizontal$foundation_layout_release(Alignment.Horizontal horizontal) {
            return new HorizontalCrossAxisAlignment(horizontal);
        }
    }

    /* JADX INFO: compiled from: RowColumnImpl.kt */
    private static final class CenterCrossAxisAlignment extends CrossAxisAlignment {
        public static final CenterCrossAxisAlignment INSTANCE = new CenterCrossAxisAlignment();

        private CenterCrossAxisAlignment() {
            super(null);
        }

        @Override // androidx.compose.foundation.layout.CrossAxisAlignment
        public int align$foundation_layout_release(int i, LayoutDirection layoutDirection, Placeable placeable, int i2) {
            return i / 2;
        }
    }

    /* JADX INFO: compiled from: RowColumnImpl.kt */
    private static final class StartCrossAxisAlignment extends CrossAxisAlignment {
        public static final StartCrossAxisAlignment INSTANCE = new StartCrossAxisAlignment();

        private StartCrossAxisAlignment() {
            super(null);
        }

        @Override // androidx.compose.foundation.layout.CrossAxisAlignment
        public int align$foundation_layout_release(int i, LayoutDirection layoutDirection, Placeable placeable, int i2) {
            if (layoutDirection == LayoutDirection.Ltr) {
                return 0;
            }
            return i;
        }
    }

    /* JADX INFO: compiled from: RowColumnImpl.kt */
    private static final class EndCrossAxisAlignment extends CrossAxisAlignment {
        public static final EndCrossAxisAlignment INSTANCE = new EndCrossAxisAlignment();

        private EndCrossAxisAlignment() {
            super(null);
        }

        @Override // androidx.compose.foundation.layout.CrossAxisAlignment
        public int align$foundation_layout_release(int i, LayoutDirection layoutDirection, Placeable placeable, int i2) {
            if (layoutDirection == LayoutDirection.Ltr) {
                return i;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: RowColumnImpl.kt */
    private static final class VerticalCrossAxisAlignment extends CrossAxisAlignment {
        private final Alignment.Vertical vertical;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof VerticalCrossAxisAlignment) && Intrinsics.areEqual(this.vertical, ((VerticalCrossAxisAlignment) obj).vertical);
        }

        public int hashCode() {
            return this.vertical.hashCode();
        }

        public String toString() {
            return "VerticalCrossAxisAlignment(vertical=" + this.vertical + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public VerticalCrossAxisAlignment(Alignment.Vertical vertical) {
            super(null);
            this.vertical = vertical;
        }

        @Override // androidx.compose.foundation.layout.CrossAxisAlignment
        public int align$foundation_layout_release(int i, LayoutDirection layoutDirection, Placeable placeable, int i2) {
            return this.vertical.align(0, i);
        }
    }

    /* JADX INFO: compiled from: RowColumnImpl.kt */
    private static final class HorizontalCrossAxisAlignment extends CrossAxisAlignment {
        private final Alignment.Horizontal horizontal;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof HorizontalCrossAxisAlignment) && Intrinsics.areEqual(this.horizontal, ((HorizontalCrossAxisAlignment) obj).horizontal);
        }

        public int hashCode() {
            return this.horizontal.hashCode();
        }

        public String toString() {
            return "HorizontalCrossAxisAlignment(horizontal=" + this.horizontal + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public HorizontalCrossAxisAlignment(Alignment.Horizontal horizontal) {
            super(null);
            this.horizontal = horizontal;
        }

        @Override // androidx.compose.foundation.layout.CrossAxisAlignment
        public int align$foundation_layout_release(int i, LayoutDirection layoutDirection, Placeable placeable, int i2) {
            return this.horizontal.align(0, i, layoutDirection);
        }
    }
}
