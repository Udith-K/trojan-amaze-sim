package androidx.compose.foundation.text;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: TextLinkScope.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextRangeLayoutMeasureResult {
    private final int height;
    private final Function0 place;
    private final int width;

    public TextRangeLayoutMeasureResult(int i, int i2, Function0 function0) {
        this.width = i;
        this.height = i2;
        this.place = function0;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final Function0 getPlace() {
        return this.place;
    }
}
