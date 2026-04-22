package androidx.lifecycle;

import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: CoroutineLiveData.kt */
/* JADX INFO: loaded from: classes.dex */
public interface LiveDataScope {
    Object emit(Object obj, Continuation continuation);
}
