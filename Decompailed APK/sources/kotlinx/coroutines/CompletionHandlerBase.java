package kotlinx.coroutines;

import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

/* JADX INFO: compiled from: CompletionHandler.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class CompletionHandlerBase extends LockFreeLinkedListNode implements Function1 {
    public abstract void invoke(Throwable th);
}
