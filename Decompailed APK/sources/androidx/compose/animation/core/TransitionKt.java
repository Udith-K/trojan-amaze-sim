package androidx.compose.animation.core;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.Transition.DeferredAnimation;
import androidx.compose.animation.core.Transition.TransitionAnimationState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Transition.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TransitionKt {
    private static final Function1 SeekableTransitionStateTotalDurationChanged = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$SeekableTransitionStateTotalDurationChanged$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
            invoke((SeekableTransitionState) null);
            return Unit.INSTANCE;
        }

        public final void invoke(SeekableTransitionState seekableTransitionState) {
            throw null;
        }
    };
    private static final Lazy SeekableStateObserver$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.compose.animation.core.TransitionKt$SeekableStateObserver$2
        @Override // kotlin.jvm.functions.Function0
        public final SnapshotStateObserver invoke() {
            SnapshotStateObserver snapshotStateObserver = new SnapshotStateObserver(new Function1() { // from class: androidx.compose.animation.core.TransitionKt$SeekableStateObserver$2.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Function0) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Function0 function0) {
                    function0.invoke();
                }
            });
            snapshotStateObserver.start();
            return snapshotStateObserver;
        }
    });

    public static final Transition updateTransition(Object obj, String str, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2029166765, i, -1, "androidx.compose.animation.core.updateTransition (Transition.kt:91)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new Transition(obj, str);
            composer.updateRememberedValue(objRememberedValue);
        }
        final Transition transition = (Transition) objRememberedValue;
        transition.animateTo$animation_core_release(obj, composer, (i & 8) | 48 | (i & 14));
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                    final Transition transition2 = transition;
                    return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            transition2.onDisposed$animation_core_release();
                        }
                    };
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.DisposableEffect(transition, (Function1) objRememberedValue2, composer, 54);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return transition;
    }

    public static final Transition rememberTransition(TransitionState transitionState, String str, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1643203617, i, -1, "androidx.compose.animation.core.rememberTransition (Transition.kt:820)");
        }
        boolean z = (((i & 14) ^ 6) > 4 && composer.changed(transitionState)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Transition(transitionState, str);
            composer.updateRememberedValue(objRememberedValue);
        }
        final Transition transition = (Transition) objRememberedValue;
        composer.startReplaceGroup(1030875195);
        transition.animateTo$animation_core_release(transitionState.getTargetState(), composer, 0);
        composer.endReplaceGroup();
        boolean zChanged = composer.changed(transition);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$rememberTransition$2$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                    final Transition transition2 = transition;
                    return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$rememberTransition$2$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            transition2.onDisposed$animation_core_release();
                        }
                    };
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.DisposableEffect(transition, (Function1) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return transition;
    }

    public static final Transition updateTransition(MutableTransitionState mutableTransitionState, String str, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(882913843, i, -1, "androidx.compose.animation.core.updateTransition (Transition.kt:873)");
        }
        Transition transitionRememberTransition = rememberTransition(mutableTransitionState, str, composer, i & 126, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return transitionRememberTransition;
    }

    public static final Transition.DeferredAnimation createDeferredAnimation(final Transition transition, TwoWayConverter twoWayConverter, String str, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            str = "DeferredAnimation";
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1714122528, i, -1, "androidx.compose.animation.core.createDeferredAnimation (Transition.kt:1779)");
        }
        int i3 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i3 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = transition.new DeferredAnimation(twoWayConverter, str);
            composer.updateRememberedValue(objRememberedValue);
        }
        final Transition.DeferredAnimation deferredAnimation = (Transition.DeferredAnimation) objRememberedValue;
        if ((i3 <= 4 || !composer.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        boolean zChangedInstance = composer.changedInstance(deferredAnimation) | z;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                    final Transition transition2 = transition;
                    final Transition.DeferredAnimation deferredAnimation2 = deferredAnimation;
                    return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            transition2.removeAnimation$animation_core_release(deferredAnimation2);
                        }
                    };
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.DisposableEffect(deferredAnimation, (Function1) objRememberedValue2, composer, 0);
        if (transition.isSeeking()) {
            deferredAnimation.setupSeeking$animation_core_release();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return deferredAnimation;
    }

    public static final Transition createChildTransitionInternal(final Transition transition, Object obj, Object obj2, String str, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-198307638, i, -1, "androidx.compose.animation.core.createChildTransitionInternal (Transition.kt:1825)");
        }
        int i2 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i2 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Transition(new MutableTransitionState(obj), transition, transition.getLabel() + " > " + str);
            composer.updateRememberedValue(objRememberedValue);
        }
        final Transition transition2 = (Transition) objRememberedValue;
        if ((i2 <= 4 || !composer.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        boolean zChanged = composer.changed(transition2) | z;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$createChildTransitionInternal$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                    transition.addTransition$animation_core_release(transition2);
                    final Transition transition3 = transition;
                    final Transition transition4 = transition2;
                    return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createChildTransitionInternal$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            transition3.removeTransition$animation_core_release(transition4);
                        }
                    };
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.DisposableEffect(transition2, (Function1) objRememberedValue2, composer, 0);
        if (transition.isSeeking()) {
            transition2.seek(obj, obj2, transition.getLastSeekedTimeNanos$animation_core_release());
        } else {
            transition2.updateTarget$animation_core_release(obj2);
            transition2.setSeeking$animation_core_release(false);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return transition2;
    }

    public static final State createTransitionAnimation(final Transition transition, Object obj, Object obj2, FiniteAnimationSpec finiteAnimationSpec, TwoWayConverter twoWayConverter, String str, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-304821198, i, -1, "androidx.compose.animation.core.createTransitionAnimation (Transition.kt:1900)");
        }
        int i2 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i2 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            Object transitionAnimationState = transition.new TransitionAnimationState(obj, AnimationStateKt.createZeroVectorFrom(twoWayConverter, obj2), twoWayConverter, str);
            composer.updateRememberedValue(transitionAnimationState);
            objRememberedValue = transitionAnimationState;
        }
        final Transition.TransitionAnimationState transitionAnimationState2 = (Transition.TransitionAnimationState) objRememberedValue;
        if (transition.isSeeking()) {
            transitionAnimationState2.updateInitialAndTargetValue$animation_core_release(obj, obj2, finiteAnimationSpec);
        } else {
            transitionAnimationState2.updateTargetValue$animation_core_release(obj2, finiteAnimationSpec);
        }
        if ((i2 <= 4 || !composer.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        boolean zChanged = composer.changed(transitionAnimationState2) | z;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$createTransitionAnimation$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                    transition.addAnimation$animation_core_release(transitionAnimationState2);
                    final Transition transition2 = transition;
                    final Transition.TransitionAnimationState transitionAnimationState3 = transitionAnimationState2;
                    return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createTransitionAnimation$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public void dispose() {
                            transition2.removeAnimation$animation_core_release(transitionAnimationState3);
                        }
                    };
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.DisposableEffect(transitionAnimationState2, (Function1) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return transitionAnimationState2;
    }
}
