package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PointerEvent.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HistoricalChange {
    private long originalEventPosition;
    private final long position;
    private final long uptimeMillis;

    public /* synthetic */ HistoricalChange(long j, long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3);
    }

    public /* synthetic */ HistoricalChange(long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2);
    }

    private HistoricalChange(long j, long j2) {
        this.uptimeMillis = j;
        this.position = j2;
        this.originalEventPosition = Offset.Companion.m1171getZeroF1C5BW0();
    }

    public final long getUptimeMillis() {
        return this.uptimeMillis;
    }

    /* JADX INFO: renamed from: getPosition-F1C5BW0, reason: not valid java name */
    public final long m1647getPositionF1C5BW0() {
        return this.position;
    }

    /* JADX INFO: renamed from: getOriginalEventPosition-F1C5BW0$ui_release, reason: not valid java name */
    public final long m1646getOriginalEventPositionF1C5BW0$ui_release() {
        return this.originalEventPosition;
    }

    private HistoricalChange(long j, long j2, long j3) {
        this(j, j2, (DefaultConstructorMarker) null);
        this.originalEventPosition = j3;
    }

    public String toString() {
        return "HistoricalChange(uptimeMillis=" + this.uptimeMillis + ", position=" + ((Object) Offset.m1166toStringimpl(this.position)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
