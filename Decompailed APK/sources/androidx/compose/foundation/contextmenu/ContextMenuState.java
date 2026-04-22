package androidx.compose.foundation.contextmenu;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContextMenuState.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ContextMenuState {
    private final MutableState status$delegate;

    public ContextMenuState(Status status) {
        this.status$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(status, null, 2, null);
    }

    public /* synthetic */ ContextMenuState(Status status, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Status.Closed.INSTANCE : status);
    }

    public final Status getStatus() {
        return (Status) this.status$delegate.getValue();
    }

    public final void setStatus(Status status) {
        this.status$delegate.setValue(status);
    }

    public String toString() {
        return "ContextMenuState(status=" + getStatus() + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public int hashCode() {
        return getStatus().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ContextMenuState) {
            return Intrinsics.areEqual(((ContextMenuState) obj).getStatus(), getStatus());
        }
        return false;
    }

    /* JADX INFO: compiled from: ContextMenuState.android.kt */
    public static abstract class Status {
        public /* synthetic */ Status(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Status() {
        }

        /* JADX INFO: compiled from: ContextMenuState.android.kt */
        public static final class Open extends Status {
            private final long offset;

            public /* synthetic */ Open(long j, DefaultConstructorMarker defaultConstructorMarker) {
                this(j);
            }

            /* JADX INFO: renamed from: getOffset-F1C5BW0, reason: not valid java name */
            public final long m161getOffsetF1C5BW0() {
                return this.offset;
            }

            private Open(long j) {
                super(null);
                this.offset = j;
                if (!OffsetKt.m1173isSpecifiedk4lQ0M(j)) {
                    throw new IllegalStateException("ContextMenuState.Status should never be open with an unspecified offset. Use ContextMenuState.Status.Closed instead.");
                }
            }

            public String toString() {
                return "Open(offset=" + ((Object) Offset.m1166toStringimpl(this.offset)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
            }

            public int hashCode() {
                return Offset.m1161hashCodeimpl(this.offset);
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof Open) {
                    return Offset.m1156equalsimpl0(this.offset, ((Open) obj).offset);
                }
                return false;
            }
        }

        /* JADX INFO: compiled from: ContextMenuState.android.kt */
        public static final class Closed extends Status {
            public static final Closed INSTANCE = new Closed();

            private Closed() {
                super(null);
            }

            public String toString() {
                return "Closed";
            }
        }
    }
}
