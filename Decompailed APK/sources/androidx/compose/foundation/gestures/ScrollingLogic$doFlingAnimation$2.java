package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$LongRef;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
final class ScrollingLogic$doFlingAnimation$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ long $available;
    final /* synthetic */ Ref$LongRef $result;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScrollingLogic$doFlingAnimation$2(ScrollingLogic scrollingLogic, Ref$LongRef ref$LongRef, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = scrollingLogic;
        this.$result = ref$LongRef;
        this.$available = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ScrollingLogic$doFlingAnimation$2 scrollingLogic$doFlingAnimation$2 = new ScrollingLogic$doFlingAnimation$2(this.this$0, this.$result, this.$available, continuation);
        scrollingLogic$doFlingAnimation$2.L$0 = obj;
        return scrollingLogic$doFlingAnimation$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(NestedScrollScope nestedScrollScope, Continuation continuation) {
        return ((ScrollingLogic$doFlingAnimation$2) create(nestedScrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ScrollingLogic scrollingLogic;
        Ref$LongRef ref$LongRef;
        ScrollingLogic scrollingLogic2;
        long j;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final NestedScrollScope nestedScrollScope = (NestedScrollScope) this.L$0;
            final ScrollingLogic scrollingLogic3 = this.this$0;
            ScrollScope scrollScope = new ScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2$reverseScope$1
                @Override // androidx.compose.foundation.gestures.ScrollScope
                public float scrollBy(float f) {
                    ScrollingLogic scrollingLogic4 = scrollingLogic3;
                    return scrollingLogic4.reverseIfNeeded(scrollingLogic4.m235toFloatk4lQ0M(nestedScrollScope.mo203scrollByWithOverscrollOzD1aCk(scrollingLogic4.m233reverseIfNeededMKHz9U(scrollingLogic4.m236toOffsettuRUvjQ(f)), NestedScrollSource.Companion.m1642getSideEffectWNlRxjI())));
                }
            };
            scrollingLogic = this.this$0;
            Ref$LongRef ref$LongRef2 = this.$result;
            long j2 = this.$available;
            FlingBehavior flingBehavior = scrollingLogic.flingBehavior;
            long j3 = ref$LongRef2.element;
            float fReverseIfNeeded = scrollingLogic.reverseIfNeeded(scrollingLogic.m228toFloatTH1AsA0(j2));
            this.L$0 = scrollingLogic;
            this.L$1 = scrollingLogic;
            this.L$2 = ref$LongRef2;
            this.J$0 = j3;
            this.label = 1;
            Object objPerformFling = flingBehavior.performFling(scrollScope, fReverseIfNeeded, this);
            if (objPerformFling == coroutine_suspended) {
                return coroutine_suspended;
            }
            ref$LongRef = ref$LongRef2;
            obj = objPerformFling;
            scrollingLogic2 = scrollingLogic;
            j = j3;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j = this.J$0;
            ref$LongRef = (Ref$LongRef) this.L$2;
            scrollingLogic = (ScrollingLogic) this.L$1;
            scrollingLogic2 = (ScrollingLogic) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        ref$LongRef.element = scrollingLogic.m229updateQWom1Mo(j, scrollingLogic2.reverseIfNeeded(((Number) obj).floatValue()));
        return Unit.INSTANCE;
    }
}
