package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.graphics.Path;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Outline.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Outline {
    public /* synthetic */ Outline(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Rect getBounds();

    private Outline() {
    }

    /* JADX INFO: compiled from: Outline.kt */
    public static final class Rectangle extends Outline {
        private final Rect rect;

        public Rectangle(Rect rect) {
            super(null);
            this.rect = rect;
        }

        public final Rect getRect() {
            return this.rect;
        }

        @Override // androidx.compose.ui.graphics.Outline
        public Rect getBounds() {
            return this.rect;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Rectangle) && Intrinsics.areEqual(this.rect, ((Rectangle) obj).rect);
        }

        public int hashCode() {
            return this.rect.hashCode();
        }
    }

    /* JADX INFO: compiled from: Outline.kt */
    public static final class Rounded extends Outline {
        private final RoundRect roundRect;
        private final Path roundRectPath;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Rounded(RoundRect roundRect) {
            super(0 == true ? 1 : 0);
            Path path = null;
            this.roundRect = roundRect;
            if (!RoundRectKt.isSimple(roundRect)) {
                Path Path = AndroidPath_androidKt.Path();
                Path.CC.addRoundRect$default(Path, roundRect, null, 2, null);
                path = Path;
            }
            this.roundRectPath = path;
        }

        public final RoundRect getRoundRect() {
            return this.roundRect;
        }

        public final Path getRoundRectPath$ui_graphics_release() {
            return this.roundRectPath;
        }

        @Override // androidx.compose.ui.graphics.Outline
        public Rect getBounds() {
            return RoundRectKt.getBoundingRect(this.roundRect);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Rounded) && Intrinsics.areEqual(this.roundRect, ((Rounded) obj).roundRect);
        }

        public int hashCode() {
            return this.roundRect.hashCode();
        }
    }

    /* JADX INFO: compiled from: Outline.kt */
    public static final class Generic extends Outline {
        private final Path path;

        public Generic(Path path) {
            super(null);
            this.path = path;
        }

        public final Path getPath() {
            return this.path;
        }

        @Override // androidx.compose.ui.graphics.Outline
        public Rect getBounds() {
            return this.path.getBounds();
        }
    }
}
