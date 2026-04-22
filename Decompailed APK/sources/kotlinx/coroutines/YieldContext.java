package kotlinx.coroutines;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Unconfined.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class YieldContext extends AbstractCoroutineContextElement {
    public static final Key Key = new Key(null);
    public boolean dispatcherWasUnconfined;

    /* JADX INFO: compiled from: Unconfined.kt */
    public static final class Key implements CoroutineContext.Key {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Key() {
        }
    }

    public YieldContext() {
        super(Key);
    }
}
