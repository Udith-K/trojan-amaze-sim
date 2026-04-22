package io.ktor.util;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: CoroutinesUtils.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CoroutinesUtilsKt {
    public static /* synthetic */ CoroutineContext SilentSupervisor$default(Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return SilentSupervisor(job);
    }

    public static final CoroutineContext SilentSupervisor(Job job) {
        return SupervisorKt.SupervisorJob(job).plus(new CoroutinesUtilsKt$SilentSupervisor$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key));
    }
}
