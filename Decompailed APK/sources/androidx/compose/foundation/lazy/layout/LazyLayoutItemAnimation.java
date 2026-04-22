package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyLayoutItemAnimation {
    private final CoroutineScope coroutineScope;
    private FiniteAnimationSpec fadeInSpec;
    private FiniteAnimationSpec fadeOutSpec;
    private long finalOffset;
    private final GraphicsContext graphicsContext;
    private final MutableState isAppearanceAnimationInProgress$delegate;
    private final MutableState isDisappearanceAnimationFinished$delegate;
    private final MutableState isDisappearanceAnimationInProgress$delegate;
    private final MutableState isPlacementAnimationInProgress$delegate;
    private boolean isRunningMovingAwayAnimation;
    private GraphicsLayer layer;
    private long lookaheadOffset;
    private final Function0 onLayerPropertyChanged;
    private final MutableState placementDelta$delegate;
    private final Animatable placementDeltaAnimation;
    private FiniteAnimationSpec placementSpec;
    private long rawOffset;
    private final Animatable visibilityAnimation;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final long NotInitialized = IntOffsetKt.IntOffset(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public LazyLayoutItemAnimation(CoroutineScope coroutineScope, GraphicsContext graphicsContext, Function0 function0) {
        this.coroutineScope = coroutineScope;
        this.graphicsContext = graphicsContext;
        this.onLayerPropertyChanged = function0;
        Boolean bool = Boolean.FALSE;
        this.isPlacementAnimationInProgress$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.isAppearanceAnimationInProgress$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.isDisappearanceAnimationInProgress$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.isDisappearanceAnimationFinished$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        long j = NotInitialized;
        this.rawOffset = j;
        IntOffset.Companion companion = IntOffset.Companion;
        this.finalOffset = companion.m2464getZeronOccac();
        this.layer = graphicsContext != null ? graphicsContext.createGraphicsLayer() : null;
        this.placementDeltaAnimation = new Animatable(IntOffset.m2451boximpl(companion.m2464getZeronOccac()), VectorConvertersKt.getVectorConverter(companion), null, null, 12, null);
        this.visibilityAnimation = new Animatable(Float.valueOf(1.0f), VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), null, null, 12, null);
        this.placementDelta$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntOffset.m2451boximpl(companion.m2464getZeronOccac()), null, 2, null);
        this.lookaheadOffset = j;
    }

    public final void setFadeInSpec(FiniteAnimationSpec finiteAnimationSpec) {
        this.fadeInSpec = finiteAnimationSpec;
    }

    public final void setPlacementSpec(FiniteAnimationSpec finiteAnimationSpec) {
        this.placementSpec = finiteAnimationSpec;
    }

    public final void setFadeOutSpec(FiniteAnimationSpec finiteAnimationSpec) {
        this.fadeOutSpec = finiteAnimationSpec;
    }

    public final boolean isRunningMovingAwayAnimation() {
        return this.isRunningMovingAwayAnimation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPlacementAnimationInProgress(boolean z) {
        this.isPlacementAnimationInProgress$delegate.setValue(Boolean.valueOf(z));
    }

    public final boolean isPlacementAnimationInProgress() {
        return ((Boolean) this.isPlacementAnimationInProgress$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAppearanceAnimationInProgress(boolean z) {
        this.isAppearanceAnimationInProgress$delegate.setValue(Boolean.valueOf(z));
    }

    public final boolean isAppearanceAnimationInProgress() {
        return ((Boolean) this.isAppearanceAnimationInProgress$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDisappearanceAnimationInProgress(boolean z) {
        this.isDisappearanceAnimationInProgress$delegate.setValue(Boolean.valueOf(z));
    }

    public final boolean isDisappearanceAnimationInProgress() {
        return ((Boolean) this.isDisappearanceAnimationInProgress$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDisappearanceAnimationFinished(boolean z) {
        this.isDisappearanceAnimationFinished$delegate.setValue(Boolean.valueOf(z));
    }

    public final boolean isDisappearanceAnimationFinished() {
        return ((Boolean) this.isDisappearanceAnimationFinished$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: getRawOffset-nOcc-ac, reason: not valid java name */
    public final long m337getRawOffsetnOccac() {
        return this.rawOffset;
    }

    /* JADX INFO: renamed from: setRawOffset--gyyYBs, reason: not valid java name */
    public final void m340setRawOffsetgyyYBs(long j) {
        this.rawOffset = j;
    }

    /* JADX INFO: renamed from: getFinalOffset-nOcc-ac, reason: not valid java name */
    public final long m334getFinalOffsetnOccac() {
        return this.finalOffset;
    }

    /* JADX INFO: renamed from: setFinalOffset--gyyYBs, reason: not valid java name */
    public final void m338setFinalOffsetgyyYBs(long j) {
        this.finalOffset = j;
    }

    public final GraphicsLayer getLayer() {
        return this.layer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setPlacementDelta--gyyYBs, reason: not valid java name */
    public final void m332setPlacementDeltagyyYBs(long j) {
        this.placementDelta$delegate.setValue(IntOffset.m2451boximpl(j));
    }

    /* JADX INFO: renamed from: getPlacementDelta-nOcc-ac, reason: not valid java name */
    public final long m336getPlacementDeltanOccac() {
        return ((IntOffset) this.placementDelta$delegate.getValue()).m2463unboximpl();
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$cancelPlacementAnimation$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
    static final class C01091 extends SuspendLambda implements Function2 {
        int label;

        C01091(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return LazyLayoutItemAnimation.this.new C01091(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01091) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Animatable animatable = LazyLayoutItemAnimation.this.placementDeltaAnimation;
                IntOffset intOffsetM2451boximpl = IntOffset.m2451boximpl(IntOffset.Companion.m2464getZeronOccac());
                this.label = 1;
                if (animatable.snapTo(intOffsetM2451boximpl, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            LazyLayoutItemAnimation.this.m332setPlacementDeltagyyYBs(IntOffset.Companion.m2464getZeronOccac());
            LazyLayoutItemAnimation.this.setPlacementAnimationInProgress(false);
            return Unit.INSTANCE;
        }
    }

    public final void cancelPlacementAnimation() {
        if (isPlacementAnimationInProgress()) {
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C01091(null), 3, null);
        }
    }

    /* JADX INFO: renamed from: getLookaheadOffset-nOcc-ac, reason: not valid java name */
    public final long m335getLookaheadOffsetnOccac() {
        return this.lookaheadOffset;
    }

    /* JADX INFO: renamed from: setLookaheadOffset--gyyYBs, reason: not valid java name */
    public final void m339setLookaheadOffsetgyyYBs(long j) {
        this.lookaheadOffset = j;
    }

    /* JADX INFO: renamed from: animatePlacementDelta-ar5cAso, reason: not valid java name */
    public final void m333animatePlacementDeltaar5cAso(long j, boolean z) {
        FiniteAnimationSpec finiteAnimationSpec = this.placementSpec;
        if (finiteAnimationSpec == null) {
            return;
        }
        long jM2460minusqkQi6aY = IntOffset.m2460minusqkQi6aY(m336getPlacementDeltanOccac(), j);
        m332setPlacementDeltagyyYBs(jM2460minusqkQi6aY);
        setPlacementAnimationInProgress(true);
        this.isRunningMovingAwayAnimation = z;
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new LazyLayoutItemAnimation$animatePlacementDelta$1(this, finiteAnimationSpec, jM2460minusqkQi6aY, null), 3, null);
    }

    public final void animateAppearance() {
        GraphicsLayer graphicsLayer = this.layer;
        FiniteAnimationSpec finiteAnimationSpec = this.fadeInSpec;
        if (isAppearanceAnimationInProgress() || finiteAnimationSpec == null || graphicsLayer == null) {
            if (isDisappearanceAnimationInProgress()) {
                if (graphicsLayer != null) {
                    graphicsLayer.setAlpha(1.0f);
                }
                BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(null), 3, null);
                return;
            }
            return;
        }
        setAppearanceAnimationInProgress(true);
        boolean zIsDisappearanceAnimationInProgress = isDisappearanceAnimationInProgress();
        boolean z = !zIsDisappearanceAnimationInProgress;
        if (!zIsDisappearanceAnimationInProgress) {
            graphicsLayer.setAlpha(0.0f);
        }
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass2(z, this, finiteAnimationSpec, graphicsLayer, null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateAppearance$1, reason: invalid class name */
    /* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return LazyLayoutItemAnimation.this.new AnonymousClass1(continuation);
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
                Animatable animatable = LazyLayoutItemAnimation.this.visibilityAnimation;
                Float fBoxFloat = Boxing.boxFloat(1.0f);
                this.label = 1;
                if (animatable.snapTo(fBoxFloat, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateAppearance$2, reason: invalid class name */
    /* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ GraphicsLayer $layer;
        final /* synthetic */ boolean $shouldResetValue;
        final /* synthetic */ FiniteAnimationSpec $spec;
        int label;
        final /* synthetic */ LazyLayoutItemAnimation this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(boolean z, LazyLayoutItemAnimation lazyLayoutItemAnimation, FiniteAnimationSpec finiteAnimationSpec, GraphicsLayer graphicsLayer, Continuation continuation) {
            super(2, continuation);
            this.$shouldResetValue = z;
            this.this$0 = lazyLayoutItemAnimation;
            this.$spec = finiteAnimationSpec;
            this.$layer = graphicsLayer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$shouldResetValue, this.this$0, this.$spec, this.$layer, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.$shouldResetValue) {
                        Animatable animatable = this.this$0.visibilityAnimation;
                        Float fBoxFloat = Boxing.boxFloat(0.0f);
                        this.label = 1;
                        if (animatable.snapTo(fBoxFloat, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        this.this$0.setAppearanceAnimationInProgress(false);
                        return Unit.INSTANCE;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Animatable animatable2 = this.this$0.visibilityAnimation;
                Float fBoxFloat2 = Boxing.boxFloat(1.0f);
                FiniteAnimationSpec finiteAnimationSpec = this.$spec;
                final GraphicsLayer graphicsLayer = this.$layer;
                final LazyLayoutItemAnimation lazyLayoutItemAnimation = this.this$0;
                Function1 function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.animateAppearance.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                        invoke((Animatable) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Animatable animatable3) {
                        graphicsLayer.setAlpha(((Number) animatable3.getValue()).floatValue());
                        lazyLayoutItemAnimation.onLayerPropertyChanged.invoke();
                    }
                };
                this.label = 2;
                if (Animatable.animateTo$default(animatable2, fBoxFloat2, finiteAnimationSpec, null, function1, this, 4, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                this.this$0.setAppearanceAnimationInProgress(false);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                this.this$0.setAppearanceAnimationInProgress(false);
                throw th;
            }
        }
    }

    public final void animateDisappearance() {
        GraphicsLayer graphicsLayer = this.layer;
        FiniteAnimationSpec finiteAnimationSpec = this.fadeOutSpec;
        if (graphicsLayer == null || isDisappearanceAnimationInProgress() || finiteAnimationSpec == null) {
            return;
        }
        setDisappearanceAnimationInProgress(true);
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C01081(finiteAnimationSpec, graphicsLayer, null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animateDisappearance$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
    static final class C01081 extends SuspendLambda implements Function2 {
        final /* synthetic */ GraphicsLayer $layer;
        final /* synthetic */ FiniteAnimationSpec $spec;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01081(FiniteAnimationSpec finiteAnimationSpec, GraphicsLayer graphicsLayer, Continuation continuation) {
            super(2, continuation);
            this.$spec = finiteAnimationSpec;
            this.$layer = graphicsLayer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return LazyLayoutItemAnimation.this.new C01081(this.$spec, this.$layer, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Animatable animatable = LazyLayoutItemAnimation.this.visibilityAnimation;
                    Float fBoxFloat = Boxing.boxFloat(0.0f);
                    FiniteAnimationSpec finiteAnimationSpec = this.$spec;
                    final GraphicsLayer graphicsLayer = this.$layer;
                    final LazyLayoutItemAnimation lazyLayoutItemAnimation = LazyLayoutItemAnimation.this;
                    Function1 function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.animateDisappearance.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                            invoke((Animatable) obj2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Animatable animatable2) {
                            graphicsLayer.setAlpha(((Number) animatable2.getValue()).floatValue());
                            lazyLayoutItemAnimation.onLayerPropertyChanged.invoke();
                        }
                    };
                    this.label = 1;
                    if (Animatable.animateTo$default(animatable, fBoxFloat, finiteAnimationSpec, null, function1, this, 4, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                LazyLayoutItemAnimation.this.setDisappearanceAnimationFinished(true);
                LazyLayoutItemAnimation.this.setDisappearanceAnimationInProgress(false);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                LazyLayoutItemAnimation.this.setDisappearanceAnimationInProgress(false);
                throw th;
            }
        }
    }

    public final void release() {
        GraphicsContext graphicsContext;
        if (isPlacementAnimationInProgress()) {
            setPlacementAnimationInProgress(false);
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C01101(null), 3, null);
        }
        if (isAppearanceAnimationInProgress()) {
            setAppearanceAnimationInProgress(false);
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C01112(null), 3, null);
        }
        if (isDisappearanceAnimationInProgress()) {
            setDisappearanceAnimationInProgress(false);
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass3(null), 3, null);
        }
        this.isRunningMovingAwayAnimation = false;
        m332setPlacementDeltagyyYBs(IntOffset.Companion.m2464getZeronOccac());
        this.rawOffset = NotInitialized;
        GraphicsLayer graphicsLayer = this.layer;
        if (graphicsLayer != null && (graphicsContext = this.graphicsContext) != null) {
            graphicsContext.releaseGraphicsLayer(graphicsLayer);
        }
        this.layer = null;
        this.fadeInSpec = null;
        this.fadeOutSpec = null;
        this.placementSpec = null;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$release$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
    static final class C01101 extends SuspendLambda implements Function2 {
        int label;

        C01101(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return LazyLayoutItemAnimation.this.new C01101(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01101) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Animatable animatable = LazyLayoutItemAnimation.this.placementDeltaAnimation;
                this.label = 1;
                if (animatable.stop(this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$release$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
    static final class C01112 extends SuspendLambda implements Function2 {
        int label;

        C01112(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return LazyLayoutItemAnimation.this.new C01112(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01112) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Animatable animatable = LazyLayoutItemAnimation.this.visibilityAnimation;
                this.label = 1;
                if (animatable.stop(this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$release$3, reason: invalid class name */
    /* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
    static final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int label;

        AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return LazyLayoutItemAnimation.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Animatable animatable = LazyLayoutItemAnimation.this.visibilityAnimation;
                this.label = 1;
                if (animatable.stop(this) == coroutine_suspended) {
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

    /* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getNotInitialized-nOcc-ac, reason: not valid java name */
        public final long m341getNotInitializednOccac() {
            return LazyLayoutItemAnimation.NotInitialized;
        }
    }
}
