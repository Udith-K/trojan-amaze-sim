package androidx.compose.ui.scrollcapture;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsProperties;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ComposeScrollCaptureCallback.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class ComposeScrollCaptureCallback$scrollTracker$1 extends SuspendLambda implements Function2 {
    /* synthetic */ float F$0;
    boolean Z$0;
    int label;
    final /* synthetic */ ComposeScrollCaptureCallback this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ComposeScrollCaptureCallback$scrollTracker$1(ComposeScrollCaptureCallback composeScrollCaptureCallback, Continuation continuation) {
        super(2, continuation);
        this.this$0 = composeScrollCaptureCallback;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ComposeScrollCaptureCallback$scrollTracker$1 composeScrollCaptureCallback$scrollTracker$1 = new ComposeScrollCaptureCallback$scrollTracker$1(this.this$0, continuation);
        composeScrollCaptureCallback$scrollTracker$1.F$0 = ((Number) obj).floatValue();
        return composeScrollCaptureCallback$scrollTracker$1;
    }

    public final Object invoke(float f, Continuation continuation) {
        return ((ComposeScrollCaptureCallback$scrollTracker$1) create(Float.valueOf(f), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Number) obj).floatValue(), (Continuation) obj2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            float f = this.F$0;
            Function2 scrollCaptureScrollByAction = ScrollCapture_androidKt.getScrollCaptureScrollByAction(this.this$0.node);
            if (scrollCaptureScrollByAction != null) {
                boolean reverseScrolling = ((ScrollAxisRange) this.this$0.node.getUnmergedConfig$ui_release().get(SemanticsProperties.INSTANCE.getVerticalScrollAxisRange())).getReverseScrolling();
                if (reverseScrolling) {
                    f = -f;
                }
                Offset offsetM1150boximpl = Offset.m1150boximpl(OffsetKt.Offset(0.0f, f));
                this.Z$0 = reverseScrolling;
                this.label = 1;
                obj = scrollCaptureScrollByAction.invoke(offsetM1150boximpl, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                z = reverseScrolling;
            } else {
                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
                throw new KotlinNothingValueException();
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            z = this.Z$0;
            ResultKt.throwOnFailure(obj);
        }
        float fM1160getYimpl = Offset.m1160getYimpl(((Offset) obj).m1168unboximpl());
        if (z) {
            fM1160getYimpl = -fM1160getYimpl;
        }
        return Boxing.boxFloat(fM1160getYimpl);
    }
}
