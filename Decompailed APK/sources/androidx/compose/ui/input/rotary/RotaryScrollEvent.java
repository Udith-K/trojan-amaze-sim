package androidx.compose.ui.input.rotary;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: RotaryScrollEvent.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class RotaryScrollEvent {
    private final float horizontalScrollPixels;
    private final int inputDeviceId;
    private final long uptimeMillis;
    private final float verticalScrollPixels;

    public RotaryScrollEvent(float f, float f2, long j, int i) {
        this.verticalScrollPixels = f;
        this.horizontalScrollPixels = f2;
        this.uptimeMillis = j;
        this.inputDeviceId = i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof RotaryScrollEvent) {
            RotaryScrollEvent rotaryScrollEvent = (RotaryScrollEvent) obj;
            if (rotaryScrollEvent.verticalScrollPixels == this.verticalScrollPixels && rotaryScrollEvent.horizontalScrollPixels == this.horizontalScrollPixels && rotaryScrollEvent.uptimeMillis == this.uptimeMillis && rotaryScrollEvent.inputDeviceId == this.inputDeviceId) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((Float.floatToIntBits(this.verticalScrollPixels) * 31) + Float.floatToIntBits(this.horizontalScrollPixels)) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.uptimeMillis)) * 31) + this.inputDeviceId;
    }

    public String toString() {
        return "RotaryScrollEvent(verticalScrollPixels=" + this.verticalScrollPixels + ",horizontalScrollPixels=" + this.horizontalScrollPixels + ",uptimeMillis=" + this.uptimeMillis + ",deviceId=" + this.inputDeviceId + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
