package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class RelativeCornerSize implements CornerSize {
    private final float percent;

    private static float getMaxCornerSize(RectF rectF) {
        return Math.min(rectF.width(), rectF.height());
    }

    public RelativeCornerSize(float f) {
        this.percent = f;
    }

    @Override // com.google.android.material.shape.CornerSize
    public float getCornerSize(RectF rectF) {
        return this.percent * getMaxCornerSize(rectF);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RelativeCornerSize) && this.percent == ((RelativeCornerSize) obj).percent;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.percent)});
    }
}
