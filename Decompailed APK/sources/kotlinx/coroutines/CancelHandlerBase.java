package kotlinx.coroutines;

import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: CompletionHandler.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class CancelHandlerBase implements Function1 {
    public abstract void invoke(Throwable th);
}
