package androidx.lifecycle;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CoroutineLiveData.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CoroutineLiveDataKt {
    public static final LiveData liveData(CoroutineContext context, long j, Function2 block) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(block, "block");
        return new CoroutineLiveData(context, j, block);
    }
}
