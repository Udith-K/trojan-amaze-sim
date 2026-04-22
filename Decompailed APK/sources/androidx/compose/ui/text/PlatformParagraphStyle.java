package androidx.compose.ui.text;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AndroidTextStyle.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PlatformParagraphStyle {
    public static final Companion Companion = new Companion(null);
    private static final PlatformParagraphStyle Default = new PlatformParagraphStyle();
    private final int emojiSupportMatch;
    private final boolean includeFontPadding;

    public /* synthetic */ PlatformParagraphStyle(int i, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, z);
    }

    public final PlatformParagraphStyle merge(PlatformParagraphStyle platformParagraphStyle) {
        return platformParagraphStyle == null ? this : platformParagraphStyle;
    }

    /* JADX INFO: compiled from: AndroidTextStyle.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PlatformParagraphStyle getDefault() {
            return PlatformParagraphStyle.Default;
        }
    }

    public final boolean getIncludeFontPadding() {
        return this.includeFontPadding;
    }

    /* JADX INFO: renamed from: getEmojiSupportMatch-_3YsG6Y, reason: not valid java name */
    public final int m2072getEmojiSupportMatch_3YsG6Y() {
        return this.emojiSupportMatch;
    }

    public PlatformParagraphStyle(boolean z) {
        this.includeFontPadding = z;
        this.emojiSupportMatch = EmojiSupportMatch.Companion.m2045getDefault_3YsG6Y();
    }

    private PlatformParagraphStyle(int i, boolean z) {
        this.includeFontPadding = z;
        this.emojiSupportMatch = i;
    }

    public PlatformParagraphStyle() {
        this(EmojiSupportMatch.Companion.m2045getDefault_3YsG6Y(), false, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlatformParagraphStyle)) {
            return false;
        }
        PlatformParagraphStyle platformParagraphStyle = (PlatformParagraphStyle) obj;
        return this.includeFontPadding == platformParagraphStyle.includeFontPadding && EmojiSupportMatch.m2040equalsimpl0(this.emojiSupportMatch, platformParagraphStyle.emojiSupportMatch);
    }

    public int hashCode() {
        return (ChangeSize$$ExternalSyntheticBackport0.m(this.includeFontPadding) * 31) + EmojiSupportMatch.m2041hashCodeimpl(this.emojiSupportMatch);
    }

    public String toString() {
        return "PlatformParagraphStyle(includeFontPadding=" + this.includeFontPadding + ", emojiSupportMatch=" + ((Object) EmojiSupportMatch.m2042toStringimpl(this.emojiSupportMatch)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
