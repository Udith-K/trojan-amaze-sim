package androidx.compose.ui.text;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidTextStyle.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PlatformTextStyle {
    private final PlatformParagraphStyle paragraphStyle;
    private final PlatformSpanStyle spanStyle;

    public final PlatformSpanStyle getSpanStyle() {
        return this.spanStyle;
    }

    public final PlatformParagraphStyle getParagraphStyle() {
        return this.paragraphStyle;
    }

    public PlatformTextStyle(PlatformSpanStyle platformSpanStyle, PlatformParagraphStyle platformParagraphStyle) {
        this.spanStyle = platformSpanStyle;
        this.paragraphStyle = platformParagraphStyle;
    }

    public PlatformTextStyle(boolean z) {
        this(null, new PlatformParagraphStyle(z));
    }

    public int hashCode() {
        PlatformSpanStyle platformSpanStyle = this.spanStyle;
        int iHashCode = (platformSpanStyle != null ? platformSpanStyle.hashCode() : 0) * 31;
        PlatformParagraphStyle platformParagraphStyle = this.paragraphStyle;
        return iHashCode + (platformParagraphStyle != null ? platformParagraphStyle.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlatformTextStyle)) {
            return false;
        }
        PlatformTextStyle platformTextStyle = (PlatformTextStyle) obj;
        return Intrinsics.areEqual(this.paragraphStyle, platformTextStyle.paragraphStyle) && Intrinsics.areEqual(this.spanStyle, platformTextStyle.spanStyle);
    }

    public String toString() {
        return "PlatformTextStyle(spanStyle=" + this.spanStyle + ", paragraphSyle=" + this.paragraphStyle + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
