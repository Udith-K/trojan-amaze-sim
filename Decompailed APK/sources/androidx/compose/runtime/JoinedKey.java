package androidx.compose.runtime;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JoinedKey.kt */
/* JADX INFO: loaded from: classes.dex */
public final class JoinedKey {
    private final Object left;
    private final Object right;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JoinedKey)) {
            return false;
        }
        JoinedKey joinedKey = (JoinedKey) obj;
        return Intrinsics.areEqual(this.left, joinedKey.left) && Intrinsics.areEqual(this.right, joinedKey.right);
    }

    public String toString() {
        return "JoinedKey(left=" + this.left + ", right=" + this.right + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public JoinedKey(Object obj, Object obj2) {
        this.left = obj;
        this.right = obj2;
    }

    public int hashCode() {
        return (hashCodeOf(this.left) * 31) + hashCodeOf(this.right);
    }

    private final int hashCodeOf(Object obj) {
        if (obj instanceof Enum) {
            return ((Enum) obj).ordinal();
        }
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
