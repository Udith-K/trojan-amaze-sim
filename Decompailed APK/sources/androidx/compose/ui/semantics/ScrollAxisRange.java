package androidx.compose.ui.semantics;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: SemanticsProperties.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ScrollAxisRange {
    private final Function0 maxValue;
    private final boolean reverseScrolling;
    private final Function0 value;

    public ScrollAxisRange(Function0 function0, Function0 function02, boolean z) {
        this.value = function0;
        this.maxValue = function02;
        this.reverseScrolling = z;
    }

    public final Function0 getValue() {
        return this.value;
    }

    public final Function0 getMaxValue() {
        return this.maxValue;
    }

    public final boolean getReverseScrolling() {
        return this.reverseScrolling;
    }

    public String toString() {
        return "ScrollAxisRange(value=" + ((Number) this.value.invoke()).floatValue() + ", maxValue=" + ((Number) this.maxValue.invoke()).floatValue() + ", reverseScrolling=" + this.reverseScrolling + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
