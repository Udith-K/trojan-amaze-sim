package androidx.compose.foundation.text.selection;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.foundation.text.Handle;
import androidx.compose.ui.geometry.Offset;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SelectionHandles.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SelectionHandleInfo {
    private final SelectionHandleAnchor anchor;
    private final Handle handle;
    private final long position;
    private final boolean visible;

    public /* synthetic */ SelectionHandleInfo(Handle handle, long j, SelectionHandleAnchor selectionHandleAnchor, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(handle, j, selectionHandleAnchor, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SelectionHandleInfo)) {
            return false;
        }
        SelectionHandleInfo selectionHandleInfo = (SelectionHandleInfo) obj;
        return this.handle == selectionHandleInfo.handle && Offset.m1156equalsimpl0(this.position, selectionHandleInfo.position) && this.anchor == selectionHandleInfo.anchor && this.visible == selectionHandleInfo.visible;
    }

    public int hashCode() {
        return (((((this.handle.hashCode() * 31) + Offset.m1161hashCodeimpl(this.position)) * 31) + this.anchor.hashCode()) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.visible);
    }

    public String toString() {
        return "SelectionHandleInfo(handle=" + this.handle + ", position=" + ((Object) Offset.m1166toStringimpl(this.position)) + ", anchor=" + this.anchor + ", visible=" + this.visible + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    private SelectionHandleInfo(Handle handle, long j, SelectionHandleAnchor selectionHandleAnchor, boolean z) {
        this.handle = handle;
        this.position = j;
        this.anchor = selectionHandleAnchor;
        this.visible = z;
    }
}
