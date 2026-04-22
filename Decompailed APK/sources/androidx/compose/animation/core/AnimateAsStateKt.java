package androidx.compose.animation.core;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: compiled from: AnimateAsState.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AnimateAsStateKt {
    private static final SpringSpec defaultAnimation = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
    private static final SpringSpec dpDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Dp.m2414boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Dp.Companion)), 3, null);
    private static final SpringSpec sizeDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Size.m1190boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Size.Companion)), 3, null);
    private static final SpringSpec offsetDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Offset.m1150boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Offset.Companion)), 3, null);
    private static final SpringSpec rectDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.Companion), 3, null);
    private static final SpringSpec intDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Integer.valueOf(VisibilityThresholdsKt.getVisibilityThreshold(IntCompanionObject.INSTANCE)), 3, null);
    private static final SpringSpec intOffsetDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m2451boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.Companion)), 3, null);
    private static final SpringSpec intSizeDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m2471boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 3, null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1 animateValueAsState$lambda$4(State state) {
        return (Function1) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnimationSpec animateValueAsState$lambda$6(State state) {
        return (AnimationSpec) state.getValue();
    }

    /* JADX INFO: renamed from: animateDpAsState-AjpBEmI, reason: not valid java name */
    public static final State m44animateDpAsStateAjpBEmI(float f, AnimationSpec animationSpec, String str, Function1 function1, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            animationSpec = dpDefaultSpring;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i2 & 4) != 0) {
            str = "DpAnimation";
        }
        String str2 = str;
        if ((i2 & 8) != 0) {
            function1 = null;
        }
        Function1 function12 = function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1407150062, i, -1, "androidx.compose.animation.core.animateDpAsState (AnimateAsState.kt:113)");
        }
        int i3 = i << 6;
        State stateAnimateValueAsState = animateValueAsState(Dp.m2414boximpl(f), VectorConvertersKt.getVectorConverter(Dp.Companion), animationSpec2, null, str2, function12, composer, (i & 14) | ((i << 3) & 896) | (57344 & i3) | (i3 & 458752), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateAnimateValueAsState;
    }

    public static final State animateValueAsState(final Object obj, TwoWayConverter twoWayConverter, AnimationSpec animationSpec, Object obj2, String str, Function1 function1, Composer composer, int i, int i2) {
        AnimationSpec animationSpecSpring;
        if ((i2 & 4) != 0) {
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            animationSpecSpring = (SpringSpec) objRememberedValue;
        } else {
            animationSpecSpring = animationSpec;
        }
        Object obj3 = (i2 & 8) != 0 ? null : obj2;
        String str2 = (i2 & 16) != 0 ? "ValueAnimation" : str;
        Function1 function12 = (i2 & 32) != 0 ? null : function1;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1994373980, i, -1, "androidx.compose.animation.core.animateValueAsState (AnimateAsState.kt:397)");
        }
        Object objRememberedValue2 = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        MutableState mutableState = (MutableState) objRememberedValue2;
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new Animatable(obj, twoWayConverter, obj3, str2);
            composer.updateRememberedValue(objRememberedValue3);
        }
        Animatable animatable = (Animatable) objRememberedValue3;
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function12, composer, (i >> 15) & 14);
        if (obj3 != null && (animationSpecSpring instanceof SpringSpec)) {
            SpringSpec springSpec = (SpringSpec) animationSpecSpring;
            if (!Intrinsics.areEqual(springSpec.getVisibilityThreshold(), obj3)) {
                animationSpecSpring = AnimationSpecKt.spring(springSpec.getDampingRatio(), springSpec.getStiffness(), obj3);
            }
        }
        State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(animationSpecSpring, composer, 0);
        Object objRememberedValue4 = composer.rememberedValue();
        if (objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = ChannelKt.Channel$default(-1, null, null, 6, null);
            composer.updateRememberedValue(objRememberedValue4);
        }
        final Channel channel = (Channel) objRememberedValue4;
        boolean zChangedInstance = ((((i & 14) ^ 6) > 4 && composer.changedInstance(obj)) || (i & 6) == 4) | composer.changedInstance(channel);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue5 == companion.getEmpty()) {
            objRememberedValue5 = new Function0() { // from class: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m45invoke();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m45invoke() {
                    channel.mo2825trySendJP2dKIU(obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue5);
        }
        EffectsKt.SideEffect((Function0) objRememberedValue5, composer, 0);
        boolean zChangedInstance2 = composer.changedInstance(channel) | composer.changedInstance(animatable) | composer.changed(stateRememberUpdatedState2) | composer.changed(stateRememberUpdatedState);
        Object objRememberedValue6 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue6 == companion.getEmpty()) {
            objRememberedValue6 = new AnimateAsStateKt$animateValueAsState$3$1(channel, animatable, stateRememberUpdatedState2, stateRememberUpdatedState, null);
            composer.updateRememberedValue(objRememberedValue6);
        }
        EffectsKt.LaunchedEffect(channel, (Function2) objRememberedValue6, composer, 0);
        State stateAsState = (State) mutableState.getValue();
        if (stateAsState == null) {
            stateAsState = animatable.asState();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateAsState;
    }
}
