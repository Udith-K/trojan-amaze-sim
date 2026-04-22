package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;

/* JADX INFO: compiled from: EditingBuffer.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class EditingBufferKt {
    /* JADX INFO: renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m2186updateRangeAfterDeletepWDy79M(long j, long j2) {
        int iM2113getLengthimpl;
        int iM2115getMinimpl = TextRange.m2115getMinimpl(j);
        int iM2114getMaximpl = TextRange.m2114getMaximpl(j);
        if (TextRange.m2119intersects5zctL8(j2, j)) {
            if (TextRange.m2107contains5zctL8(j2, j)) {
                iM2115getMinimpl = TextRange.m2115getMinimpl(j2);
                iM2114getMaximpl = iM2115getMinimpl;
            } else {
                if (TextRange.m2107contains5zctL8(j, j2)) {
                    iM2113getLengthimpl = TextRange.m2113getLengthimpl(j2);
                } else if (TextRange.m2108containsimpl(j2, iM2115getMinimpl)) {
                    iM2115getMinimpl = TextRange.m2115getMinimpl(j2);
                    iM2113getLengthimpl = TextRange.m2113getLengthimpl(j2);
                } else {
                    iM2114getMaximpl = TextRange.m2115getMinimpl(j2);
                }
                iM2114getMaximpl -= iM2113getLengthimpl;
            }
        } else if (iM2114getMaximpl > TextRange.m2115getMinimpl(j2)) {
            iM2115getMinimpl -= TextRange.m2113getLengthimpl(j2);
            iM2113getLengthimpl = TextRange.m2113getLengthimpl(j2);
            iM2114getMaximpl -= iM2113getLengthimpl;
        }
        return TextRangeKt.TextRange(iM2115getMinimpl, iM2114getMaximpl);
    }
}
