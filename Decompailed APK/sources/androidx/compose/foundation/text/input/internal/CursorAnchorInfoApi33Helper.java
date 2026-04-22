package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.RectHelper_androidKt;

/* JADX INFO: compiled from: LegacyCursorAnchorInfoBuilder.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CursorAnchorInfoApi33Helper {
    public static final CursorAnchorInfoApi33Helper INSTANCE = new CursorAnchorInfoApi33Helper();

    private CursorAnchorInfoApi33Helper() {
    }

    public static final CursorAnchorInfo.Builder setEditorBoundsInfo(CursorAnchorInfo.Builder builder, Rect rect) {
        return builder.setEditorBoundsInfo(CursorAnchorInfoApi33Helper$$ExternalSyntheticApiModelOutline0.m().setEditorBounds(RectHelper_androidKt.toAndroidRectF(rect)).setHandwritingBounds(RectHelper_androidKt.toAndroidRectF(rect)).build());
    }
}
