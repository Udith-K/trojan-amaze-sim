package androidx.compose.ui.text.style;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TextGeometricTransform.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextGeometricTransform {
    public static final Companion Companion = new Companion(null);
    private static final TextGeometricTransform None = new TextGeometricTransform(1.0f, 0.0f);
    private final float scaleX;
    private final float skewX;

    public TextGeometricTransform(float f, float f2) {
        this.scaleX = f;
        this.skewX = f2;
    }

    public final float getScaleX() {
        return this.scaleX;
    }

    public final float getSkewX() {
        return this.skewX;
    }

    /* JADX INFO: compiled from: TextGeometricTransform.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TextGeometricTransform getNone$ui_text_release() {
            return TextGeometricTransform.None;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextGeometricTransform)) {
            return false;
        }
        TextGeometricTransform textGeometricTransform = (TextGeometricTransform) obj;
        return this.scaleX == textGeometricTransform.scaleX && this.skewX == textGeometricTransform.skewX;
    }

    public int hashCode() {
        return (Float.floatToIntBits(this.scaleX) * 31) + Float.floatToIntBits(this.skewX);
    }

    public String toString() {
        return "TextGeometricTransform(scaleX=" + this.scaleX + ", skewX=" + this.skewX + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
