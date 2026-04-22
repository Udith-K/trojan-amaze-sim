package androidx.compose.animation.core;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: InfiniteTransition.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InfiniteTransition {
    public static final int $stable = 8;
    private final String label;
    private final MutableVector _animations = new MutableVector(new TransitionAnimationState[16], 0);
    private final MutableState refreshChildNeeded$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
    private long startTimeNanos = Long.MIN_VALUE;
    private final MutableState isRunning$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);

    public InfiniteTransition(String str) {
        this.label = str;
    }

    /* JADX INFO: compiled from: InfiniteTransition.kt */
    public final class TransitionAnimationState implements State {
        private TargetBasedAnimation animation;
        private AnimationSpec animationSpec;
        private Object initialValue;
        private boolean isFinished;
        private final String label;
        private long playTimeNanosOffset;
        private boolean startOnTheNextFrame;
        private Object targetValue;
        private final TwoWayConverter typeConverter;
        private final MutableState value$delegate;

        public TransitionAnimationState(Object obj, Object obj2, TwoWayConverter twoWayConverter, AnimationSpec animationSpec, String str) {
            this.initialValue = obj;
            this.targetValue = obj2;
            this.typeConverter = twoWayConverter;
            this.label = str;
            this.value$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(obj, null, 2, null);
            this.animationSpec = animationSpec;
            this.animation = new TargetBasedAnimation(this.animationSpec, twoWayConverter, this.initialValue, this.targetValue, null, 16, null);
        }

        public final Object getInitialValue$animation_core_release() {
            return this.initialValue;
        }

        public final Object getTargetValue$animation_core_release() {
            return this.targetValue;
        }

        @Override // androidx.compose.runtime.State
        public Object getValue() {
            return this.value$delegate.getValue();
        }

        public void setValue$animation_core_release(Object obj) {
            this.value$delegate.setValue(obj);
        }

        public final boolean isFinished$animation_core_release() {
            return this.isFinished;
        }

        public final void updateValues$animation_core_release(Object obj, Object obj2, AnimationSpec animationSpec) {
            this.initialValue = obj;
            this.targetValue = obj2;
            this.animationSpec = animationSpec;
            this.animation = new TargetBasedAnimation(animationSpec, this.typeConverter, obj, obj2, null, 16, null);
            InfiniteTransition.this.setRefreshChildNeeded(true);
            this.isFinished = false;
            this.startOnTheNextFrame = true;
        }

        public final void onPlayTimeChanged$animation_core_release(long j) {
            InfiniteTransition.this.setRefreshChildNeeded(false);
            if (this.startOnTheNextFrame) {
                this.startOnTheNextFrame = false;
                this.playTimeNanosOffset = j;
            }
            long j2 = j - this.playTimeNanosOffset;
            setValue$animation_core_release(this.animation.getValueFromNanos(j2));
            this.isFinished = this.animation.isFinishedFromNanos(j2);
        }

        public final void skipToEnd$animation_core_release() {
            setValue$animation_core_release(this.animation.getTargetValue());
            this.startOnTheNextFrame = true;
        }

        public final void reset$animation_core_release() {
            this.startOnTheNextFrame = true;
        }
    }

    private final boolean getRefreshChildNeeded() {
        return ((Boolean) this.refreshChildNeeded$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRefreshChildNeeded(boolean z) {
        this.refreshChildNeeded$delegate.setValue(Boolean.valueOf(z));
    }

    private final boolean isRunning() {
        return ((Boolean) this.isRunning$delegate.getValue()).booleanValue();
    }

    private final void setRunning(boolean z) {
        this.isRunning$delegate.setValue(Boolean.valueOf(z));
    }

    public final void addAnimation$animation_core_release(TransitionAnimationState transitionAnimationState) {
        this._animations.add(transitionAnimationState);
        setRefreshChildNeeded(true);
    }

    public final void removeAnimation$animation_core_release(TransitionAnimationState transitionAnimationState) {
        this._animations.remove(transitionAnimationState);
    }

    public final void run$animation_core_release(Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-318043801);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(this) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-318043801, i2, -1, "androidx.compose.animation.core.InfiniteTransition.run (InfiniteTransition.kt:171)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            if (isRunning() || getRefreshChildNeeded()) {
                composerStartRestartGroup.startReplaceGroup(1719915818);
                boolean zChangedInstance = composerStartRestartGroup.changedInstance(this);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new InfiniteTransition$run$1$1(mutableState, this, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(this, (Function2) objRememberedValue2, composerStartRestartGroup, i2 & 14);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1721436120);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.animation.core.InfiniteTransition$run$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    this.$tmp2_rcvr.run$animation_core_release(composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFrame(long j) {
        boolean z;
        MutableVector mutableVector = this._animations;
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            z = true;
            int i = 0;
            do {
                TransitionAnimationState transitionAnimationState = (TransitionAnimationState) content[i];
                if (!transitionAnimationState.isFinished$animation_core_release()) {
                    transitionAnimationState.onPlayTimeChanged$animation_core_release(j);
                }
                if (!transitionAnimationState.isFinished$animation_core_release()) {
                    z = false;
                }
                i++;
            } while (i < size);
        } else {
            z = true;
        }
        setRunning(!z);
    }
}
