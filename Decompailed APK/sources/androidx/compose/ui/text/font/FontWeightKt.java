package androidx.compose.ui.text.font;

import androidx.compose.ui.util.MathHelpersKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: FontWeight.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FontWeightKt {
    public static final FontWeight lerp(FontWeight fontWeight, FontWeight fontWeight2, float f) {
        return new FontWeight(RangesKt.coerceIn(MathHelpersKt.lerp(fontWeight.getWeight(), fontWeight2.getWeight(), f), 1, 1000));
    }
}
