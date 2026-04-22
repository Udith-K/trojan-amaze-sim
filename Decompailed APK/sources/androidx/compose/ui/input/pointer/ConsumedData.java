package androidx.compose.ui.input.pointer;

/* JADX INFO: compiled from: PointerEvent.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ConsumedData {
    private boolean downChange;
    private boolean positionChange;

    public ConsumedData(boolean z, boolean z2) {
        this.positionChange = z;
        this.downChange = z2;
    }

    public final boolean getPositionChange() {
        return this.positionChange;
    }

    public final void setPositionChange(boolean z) {
        this.positionChange = z;
    }

    public final boolean getDownChange() {
        return this.downChange;
    }

    public final void setDownChange(boolean z) {
        this.downChange = z;
    }
}
