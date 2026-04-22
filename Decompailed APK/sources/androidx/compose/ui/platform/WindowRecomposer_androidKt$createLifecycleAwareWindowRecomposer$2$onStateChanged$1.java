package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.runtime.Recomposer;
import androidx.compose.ui.platform.WindowRecomposer_androidKt;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: WindowRecomposer.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Recomposer $recomposer;
    final /* synthetic */ WindowRecomposer_androidKt.AnonymousClass2 $self;
    final /* synthetic */ LifecycleOwner $source;
    final /* synthetic */ Ref$ObjectRef $systemDurationScaleSettingConsumer;
    final /* synthetic */ View $this_createLifecycleAwareWindowRecomposer;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1(Ref$ObjectRef ref$ObjectRef, Recomposer recomposer, LifecycleOwner lifecycleOwner, WindowRecomposer_androidKt.AnonymousClass2 anonymousClass2, View view, Continuation continuation) {
        super(2, continuation);
        this.$systemDurationScaleSettingConsumer = ref$ObjectRef;
        this.$recomposer = recomposer;
        this.$source = lifecycleOwner;
        this.$self = anonymousClass2;
        this.$this_createLifecycleAwareWindowRecomposer = view;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 = new WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1(this.$systemDurationScaleSettingConsumer, this.$recomposer, this.$source, this.$self, this.$this_createLifecycleAwareWindowRecomposer, continuation);
        windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.L$0 = obj;
        return windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0083  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.Throwable {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L1f
            if (r1 != r2) goto L17
            java.lang.Object r0 = r11.L$0
            kotlinx.coroutines.Job r0 = (kotlinx.coroutines.Job) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L14
            goto L6a
        L14:
            r12 = move-exception
            goto L81
        L17:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L1f:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            r4 = r12
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            kotlin.jvm.internal.Ref$ObjectRef r12 = r11.$systemDurationScaleSettingConsumer     // Catch: java.lang.Throwable -> L58
            java.lang.Object r12 = r12.element     // Catch: java.lang.Throwable -> L58
            androidx.compose.ui.platform.MotionDurationScaleImpl r12 = (androidx.compose.ui.platform.MotionDurationScaleImpl) r12     // Catch: java.lang.Throwable -> L58
            if (r12 == 0) goto L5b
            android.view.View r1 = r11.$this_createLifecycleAwareWindowRecomposer     // Catch: java.lang.Throwable -> L58
            android.content.Context r1 = r1.getContext()     // Catch: java.lang.Throwable -> L58
            android.content.Context r1 = r1.getApplicationContext()     // Catch: java.lang.Throwable -> L58
            kotlinx.coroutines.flow.StateFlow r1 = androidx.compose.ui.platform.WindowRecomposer_androidKt.access$getAnimationScaleFlowFor(r1)     // Catch: java.lang.Throwable -> L58
            java.lang.Object r5 = r1.getValue()     // Catch: java.lang.Throwable -> L58
            java.lang.Number r5 = (java.lang.Number) r5     // Catch: java.lang.Throwable -> L58
            float r5 = r5.floatValue()     // Catch: java.lang.Throwable -> L58
            r12.setScaleFactor(r5)     // Catch: java.lang.Throwable -> L58
            androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1$1$1 r7 = new androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1$1$1     // Catch: java.lang.Throwable -> L58
            r7.<init>(r1, r12, r3)     // Catch: java.lang.Throwable -> L58
            r8 = 3
            r9 = 0
            r5 = 0
            r6 = 0
            kotlinx.coroutines.Job r12 = kotlinx.coroutines.BuildersKt.launch$default(r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L58
            goto L5c
        L58:
            r12 = move-exception
            r0 = r3
            goto L81
        L5b:
            r12 = r3
        L5c:
            androidx.compose.runtime.Recomposer r1 = r11.$recomposer     // Catch: java.lang.Throwable -> L7d
            r11.L$0 = r12     // Catch: java.lang.Throwable -> L7d
            r11.label = r2     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r1 = r1.runRecomposeAndApplyChanges(r11)     // Catch: java.lang.Throwable -> L7d
            if (r1 != r0) goto L69
            return r0
        L69:
            r0 = r12
        L6a:
            if (r0 == 0) goto L6f
            kotlinx.coroutines.Job.DefaultImpls.cancel$default(r0, r3, r2, r3)
        L6f:
            androidx.lifecycle.LifecycleOwner r12 = r11.$source
            androidx.lifecycle.Lifecycle r12 = r12.getLifecycle()
            androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2 r0 = r11.$self
            r12.removeObserver(r0)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L7d:
            r0 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
        L81:
            if (r0 == 0) goto L86
            kotlinx.coroutines.Job.DefaultImpls.cancel$default(r0, r3, r2, r3)
        L86:
            androidx.lifecycle.LifecycleOwner r0 = r11.$source
            androidx.lifecycle.Lifecycle r0 = r0.getLifecycle()
            androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2 r1 = r11.$self
            r0.removeObserver(r1)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
