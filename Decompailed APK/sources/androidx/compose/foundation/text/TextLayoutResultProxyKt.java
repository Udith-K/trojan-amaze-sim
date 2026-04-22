package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;

/* JADX INFO: compiled from: TextLayoutResultProxy.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextLayoutResultProxyKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: coerceIn-3MmeM6k, reason: not valid java name */
    public static final long m465coerceIn3MmeM6k(long j, Rect rect) {
        float right;
        float bottom;
        if (Offset.m1159getXimpl(j) < rect.getLeft()) {
            right = rect.getLeft();
        } else {
            right = Offset.m1159getXimpl(j) > rect.getRight() ? rect.getRight() : Offset.m1159getXimpl(j);
        }
        if (Offset.m1160getYimpl(j) < rect.getTop()) {
            bottom = rect.getTop();
        } else {
            bottom = Offset.m1160getYimpl(j) > rect.getBottom() ? rect.getBottom() : Offset.m1160getYimpl(j);
        }
        return OffsetKt.Offset(right, bottom);
    }
}
