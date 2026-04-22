package androidx.compose.foundation.text.selection;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: SelectionMagnifier.kt */
/* JADX INFO: loaded from: classes.dex */
final class SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Animatable $animatable;
    final /* synthetic */ State $targetValue$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1(State state, Animatable animatable, Continuation continuation) {
        super(2, continuation);
        this.$targetValue$delegate = state;
        this.$animatable = animatable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1 selectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1 = new SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1(this.$targetValue$delegate, this.$animatable, continuation);
        selectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1.L$0 = obj;
        return selectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            final State state = this.$targetValue$delegate;
            Flow flowSnapshotFlow = SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    return Offset.m1150boximpl(m545invokeF1C5BW0());
                }

                /* JADX INFO: renamed from: invoke-F1C5BW0, reason: not valid java name */
                public final long m545invokeF1C5BW0() {
                    return SelectionMagnifierKt.rememberAnimatedMagnifierPosition$lambda$1(state);
                }
            });
            final Animatable animatable = this.$animatable;
            FlowCollector flowCollector = new FlowCollector() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1.2
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return m546emit3MmeM6k(((Offset) obj2).m1168unboximpl(), continuation);
                }

                /* JADX INFO: renamed from: emit-3MmeM6k, reason: not valid java name */
                public final Object m546emit3MmeM6k(long j, Continuation continuation) {
                    if (OffsetKt.m1173isSpecifiedk4lQ0M(((Offset) animatable.getValue()).m1168unboximpl()) && OffsetKt.m1173isSpecifiedk4lQ0M(j) && Offset.m1160getYimpl(((Offset) animatable.getValue()).m1168unboximpl()) != Offset.m1160getYimpl(j)) {
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(animatable, j, null), 3, null);
                        return Unit.INSTANCE;
                    }
                    Object objSnapTo = animatable.snapTo(Offset.m1150boximpl(j), continuation);
                    return objSnapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapTo : Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1$2$1, reason: invalid class name */
                /* JADX INFO: compiled from: SelectionMagnifier.kt */
                static final class AnonymousClass1 extends SuspendLambda implements Function2 {
                    final /* synthetic */ Animatable $animatable;
                    final /* synthetic */ long $targetValue;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(Animatable animatable, long j, Continuation continuation) {
                        super(2, continuation);
                        this.$animatable = animatable;
                        this.$targetValue = j;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        return new AnonymousClass1(this.$animatable, this.$targetValue, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            Animatable animatable = this.$animatable;
                            Offset offsetM1150boximpl = Offset.m1150boximpl(this.$targetValue);
                            SpringSpec magnifierSpringSpec = SelectionMagnifierKt.getMagnifierSpringSpec();
                            this.label = 1;
                            if (Animatable.animateTo$default(animatable, offsetM1150boximpl, magnifierSpringSpec, null, null, this, 12, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        return Unit.INSTANCE;
                    }
                }
            };
            this.label = 1;
            if (flowSnapshotFlow.collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
