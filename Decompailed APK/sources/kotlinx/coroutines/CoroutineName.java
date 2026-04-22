package kotlinx.coroutines;

import ch.qos.logback.core.CoreConstants;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoroutineName.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CoroutineName extends AbstractCoroutineContextElement {
    public static final Key Key = new Key(null);
    private final String name;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CoroutineName) && Intrinsics.areEqual(this.name, ((CoroutineName) obj).name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public final String getName() {
        return this.name;
    }

    public CoroutineName(String str) {
        super(Key);
        this.name = str;
    }

    /* JADX INFO: compiled from: CoroutineName.kt */
    public static final class Key implements CoroutineContext.Key {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Key() {
        }
    }

    public String toString() {
        return "CoroutineName(" + this.name + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
