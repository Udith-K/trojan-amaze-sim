package androidx.compose.runtime;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;

/* JADX INFO: compiled from: Effects.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LaunchedEffectImpl implements RememberObserver {
    private Job job;
    private final CoroutineScope scope;
    private final Function2 task;

    public LaunchedEffectImpl(CoroutineContext coroutineContext, Function2 function2) {
        this.task = function2;
        this.scope = CoroutineScopeKt.CoroutineScope(coroutineContext);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        Job job = this.job;
        if (job != null) {
            JobKt__JobKt.cancel$default(job, "Old job was still running!", null, 2, null);
        }
        this.job = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, this.task, 3, null);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        Job job = this.job;
        if (job != null) {
            job.cancel(new LeftCompositionCancellationException());
        }
        this.job = null;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        Job job = this.job;
        if (job != null) {
            job.cancel(new LeftCompositionCancellationException());
        }
        this.job = null;
    }
}
