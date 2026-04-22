package androidx.compose.ui.input.pointer;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PointerIcon.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidPointerIconType implements PointerIcon {
    private final int type;

    public AndroidPointerIconType(int i) {
        this.type = i;
    }

    public final int getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(AndroidPointerIconType.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.AndroidPointerIconType");
        return this.type == ((AndroidPointerIconType) obj).type;
    }

    public int hashCode() {
        return this.type;
    }

    public String toString() {
        return "AndroidPointerIcon(type=" + this.type + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
