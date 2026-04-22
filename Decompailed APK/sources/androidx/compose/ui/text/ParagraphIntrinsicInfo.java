package androidx.compose.ui.text;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiParagraphIntrinsics.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ParagraphIntrinsicInfo {
    private final int endIndex;
    private final ParagraphIntrinsics intrinsics;
    private final int startIndex;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ParagraphIntrinsicInfo)) {
            return false;
        }
        ParagraphIntrinsicInfo paragraphIntrinsicInfo = (ParagraphIntrinsicInfo) obj;
        return Intrinsics.areEqual(this.intrinsics, paragraphIntrinsicInfo.intrinsics) && this.startIndex == paragraphIntrinsicInfo.startIndex && this.endIndex == paragraphIntrinsicInfo.endIndex;
    }

    public int hashCode() {
        return (((this.intrinsics.hashCode() * 31) + this.startIndex) * 31) + this.endIndex;
    }

    public String toString() {
        return "ParagraphIntrinsicInfo(intrinsics=" + this.intrinsics + ", startIndex=" + this.startIndex + ", endIndex=" + this.endIndex + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public ParagraphIntrinsicInfo(ParagraphIntrinsics paragraphIntrinsics, int i, int i2) {
        this.intrinsics = paragraphIntrinsics;
        this.startIndex = i;
        this.endIndex = i2;
    }

    public final ParagraphIntrinsics getIntrinsics() {
        return this.intrinsics;
    }

    public final int getStartIndex() {
        return this.startIndex;
    }

    public final int getEndIndex() {
        return this.endIndex;
    }
}
