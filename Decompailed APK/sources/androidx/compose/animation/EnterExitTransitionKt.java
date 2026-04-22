package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EnterExitTransition.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class EnterExitTransitionKt {
    private static final TwoWayConverter TransformOriginVectorConverter = VectorConvertersKt.TwoWayConverter(new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return m28invoke__ExYCQ(((TransformOrigin) obj).m1433unboximpl());
        }

        /* JADX INFO: renamed from: invoke-__ExYCQ, reason: not valid java name */
        public final AnimationVector2D m28invoke__ExYCQ(long j) {
            return new AnimationVector2D(TransformOrigin.m1429getPivotFractionXimpl(j), TransformOrigin.m1430getPivotFractionYimpl(j));
        }
    }, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$TransformOriginVectorConverter$2
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return TransformOrigin.m1425boximpl(m29invokeLIALnN8((AnimationVector2D) obj));
        }

        /* JADX INFO: renamed from: invoke-LIALnN8, reason: not valid java name */
        public final long m29invokeLIALnN8(AnimationVector2D animationVector2D) {
            return TransformOriginKt.TransformOrigin(animationVector2D.getV1(), animationVector2D.getV2());
        }
    });
    private static final SpringSpec DefaultAlphaAndScaleSpring = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
    private static final SpringSpec DefaultOffsetAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m2451boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.Companion)), 1, null);
    private static final SpringSpec DefaultSizeAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m2471boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);

    private static final EnterTransition trackActiveEnter$lambda$5(MutableState mutableState) {
        return (EnterTransition) mutableState.getValue();
    }

    private static final ExitTransition trackActiveExit$lambda$8(MutableState mutableState) {
        return (ExitTransition) mutableState.getValue();
    }

    public static /* synthetic */ EnterTransition fadeIn$default(FiniteAnimationSpec finiteAnimationSpec, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return fadeIn(finiteAnimationSpec, f);
    }

    public static final EnterTransition fadeIn(FiniteAnimationSpec finiteAnimationSpec, float f) {
        return new EnterTransitionImpl(new TransitionData(new Fade(f, finiteAnimationSpec), null, null, null, false, null, 62, null));
    }

    public static /* synthetic */ ExitTransition fadeOut$default(FiniteAnimationSpec finiteAnimationSpec, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return fadeOut(finiteAnimationSpec, f);
    }

    public static final ExitTransition fadeOut(FiniteAnimationSpec finiteAnimationSpec, float f) {
        return new ExitTransitionImpl(new TransitionData(new Fade(f, finiteAnimationSpec), null, null, null, false, null, 62, null));
    }

    public static final EnterTransition expandIn(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, Function1 function1) {
        return new EnterTransitionImpl(new TransitionData(null, null, new ChangeSize(alignment, function1, finiteAnimationSpec, z), null, false, null, 59, null));
    }

    public static final ExitTransition shrinkOut(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, Function1 function1) {
        return new ExitTransitionImpl(new TransitionData(null, null, new ChangeSize(alignment, function1, finiteAnimationSpec, z), null, false, null, 59, null));
    }

    public static /* synthetic */ EnterTransition expandHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m2471boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            horizontal = Alignment.Companion.getEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandHorizontally.1
                public final Integer invoke(int i2) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }
            };
        }
        return expandHorizontally(finiteAnimationSpec, horizontal, z, function1);
    }

    public static final EnterTransition expandHorizontally(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, final Function1 function1) {
        return expandIn(finiteAnimationSpec, toAlignment(horizontal), z, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandHorizontally.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntSize.m2471boximpl(m31invokemzRDjE0(((IntSize) obj).m2479unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m31invokemzRDjE0(long j) {
                return IntSizeKt.IntSize(((Number) function1.invoke(Integer.valueOf(IntSize.m2476getWidthimpl(j)))).intValue(), IntSize.m2475getHeightimpl(j));
            }
        });
    }

    public static /* synthetic */ EnterTransition expandVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m2471boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            vertical = Alignment.Companion.getBottom();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandVertically.1
                public final Integer invoke(int i2) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }
            };
        }
        return expandVertically(finiteAnimationSpec, vertical, z, function1);
    }

    public static final EnterTransition expandVertically(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, final Function1 function1) {
        return expandIn(finiteAnimationSpec, toAlignment(vertical), z, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt.expandVertically.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntSize.m2471boximpl(m32invokemzRDjE0(((IntSize) obj).m2479unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m32invokemzRDjE0(long j) {
                return IntSizeKt.IntSize(IntSize.m2476getWidthimpl(j), ((Number) function1.invoke(Integer.valueOf(IntSize.m2475getHeightimpl(j)))).intValue());
            }
        });
    }

    public static /* synthetic */ ExitTransition shrinkHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m2471boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            horizontal = Alignment.Companion.getEnd();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkHorizontally.1
                public final Integer invoke(int i2) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }
            };
        }
        return shrinkHorizontally(finiteAnimationSpec, horizontal, z, function1);
    }

    public static final ExitTransition shrinkHorizontally(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, final Function1 function1) {
        return shrinkOut(finiteAnimationSpec, toAlignment(horizontal), z, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkHorizontally.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntSize.m2471boximpl(m33invokemzRDjE0(((IntSize) obj).m2479unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m33invokemzRDjE0(long j) {
                return IntSizeKt.IntSize(((Number) function1.invoke(Integer.valueOf(IntSize.m2476getWidthimpl(j)))).intValue(), IntSize.m2475getHeightimpl(j));
            }
        });
    }

    public static /* synthetic */ ExitTransition shrinkVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m2471boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.Companion)), 1, null);
        }
        if ((i & 2) != 0) {
            vertical = Alignment.Companion.getBottom();
        }
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkVertically.1
                public final Integer invoke(int i2) {
                    return 0;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }
            };
        }
        return shrinkVertically(finiteAnimationSpec, vertical, z, function1);
    }

    public static final ExitTransition shrinkVertically(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, final Function1 function1) {
        return shrinkOut(finiteAnimationSpec, toAlignment(vertical), z, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt.shrinkVertically.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return IntSize.m2471boximpl(m34invokemzRDjE0(((IntSize) obj).m2479unboximpl()));
            }

            /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
            public final long m34invokemzRDjE0(long j) {
                return IntSizeKt.IntSize(IntSize.m2476getWidthimpl(j), ((Number) function1.invoke(Integer.valueOf(IntSize.m2475getHeightimpl(j)))).intValue());
            }
        });
    }

    private static final Alignment toAlignment(Alignment.Horizontal horizontal) {
        Alignment.Companion companion = Alignment.Companion;
        return Intrinsics.areEqual(horizontal, companion.getStart()) ? companion.getCenterStart() : Intrinsics.areEqual(horizontal, companion.getEnd()) ? companion.getCenterEnd() : companion.getCenter();
    }

    private static final Alignment toAlignment(Alignment.Vertical vertical) {
        Alignment.Companion companion = Alignment.Companion;
        return Intrinsics.areEqual(vertical, companion.getTop()) ? companion.getTopCenter() : Intrinsics.areEqual(vertical, companion.getBottom()) ? companion.getBottomCenter() : companion.getCenter();
    }

    public static final Modifier createModifier(Transition transition, EnterTransition enterTransition, ExitTransition exitTransition, Function0 function0, String str, Composer composer, int i, int i2) {
        Transition.DeferredAnimation deferredAnimation;
        ChangeSize changeSize;
        final Function0 function02 = (i2 & 4) != 0 ? new Function0() { // from class: androidx.compose.animation.EnterExitTransitionKt.createModifier.1
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.TRUE;
            }
        } : function0;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(28261782, i, -1, "androidx.compose.animation.createModifier (EnterExitTransition.kt:869)");
        }
        int i3 = i & 14;
        EnterTransition enterTransitionTrackActiveEnter = trackActiveEnter(transition, enterTransition, composer, i & 126);
        int i4 = i >> 3;
        ExitTransition exitTransitionTrackActiveExit = trackActiveExit(transition, exitTransition, composer, (i4 & 112) | i3);
        enterTransitionTrackActiveEnter.getData$animation_release().getSlide();
        exitTransitionTrackActiveExit.getData$animation_release().getSlide();
        boolean z = true;
        boolean z2 = (enterTransitionTrackActiveEnter.getData$animation_release().getChangeSize() == null && exitTransitionTrackActiveExit.getData$animation_release().getChangeSize() == null) ? false : true;
        composer.startReplaceGroup(-821278096);
        composer.endReplaceGroup();
        Transition.DeferredAnimation deferredAnimation2 = null;
        if (z2) {
            composer.startReplaceGroup(-821202177);
            TwoWayConverter vectorConverter = VectorConvertersKt.getVectorConverter(IntSize.Companion);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = str + " shrink/expand";
                composer.updateRememberedValue(objRememberedValue);
            }
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation = TransitionKt.createDeferredAnimation(transition, vectorConverter, (String) objRememberedValue, composer, i3 | 384, 0);
            composer.endReplaceGroup();
            deferredAnimation = deferredAnimationCreateDeferredAnimation;
        } else {
            composer.startReplaceGroup(-821099041);
            composer.endReplaceGroup();
            deferredAnimation = null;
        }
        if (z2) {
            composer.startReplaceGroup(-821034002);
            TwoWayConverter vectorConverter2 = VectorConvertersKt.getVectorConverter(IntOffset.Companion);
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                objRememberedValue2 = str + " InterruptionHandlingOffset";
                composer.updateRememberedValue(objRememberedValue2);
            }
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation2 = TransitionKt.createDeferredAnimation(transition, vectorConverter2, (String) objRememberedValue2, composer, i3 | 384, 0);
            composer.endReplaceGroup();
            deferredAnimation2 = deferredAnimationCreateDeferredAnimation2;
        } else {
            composer.startReplaceGroup(-820883777);
            composer.endReplaceGroup();
        }
        ChangeSize changeSize2 = enterTransitionTrackActiveEnter.getData$animation_release().getChangeSize();
        final boolean z3 = ((changeSize2 == null || changeSize2.getClip()) && ((changeSize = exitTransitionTrackActiveExit.getData$animation_release().getChangeSize()) == null || changeSize.getClip()) && z2) ? false : true;
        GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExitCreateGraphicsLayerBlock = createGraphicsLayerBlock(transition, enterTransitionTrackActiveEnter, exitTransitionTrackActiveExit, str, composer, i3 | (i4 & 7168));
        Modifier.Companion companion = Modifier.Companion;
        boolean zChanged = composer.changed(z3);
        if ((((i & 7168) ^ 3072) <= 2048 || !composer.changed(function02)) && (i & 3072) != 2048) {
            z = false;
        }
        boolean z4 = zChanged | z;
        Object objRememberedValue3 = composer.rememberedValue();
        if (z4 || objRememberedValue3 == Composer.Companion.getEmpty()) {
            objRememberedValue3 = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createModifier$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((GraphicsLayerScope) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(GraphicsLayerScope graphicsLayerScope) {
                    graphicsLayerScope.setClip(!z3 && ((Boolean) function02.invoke()).booleanValue());
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        Modifier modifierThen = GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) objRememberedValue3).then(new EnterExitTransitionElement(transition, deferredAnimation, deferredAnimation2, null, enterTransitionTrackActiveEnter, exitTransitionTrackActiveExit, function02, graphicsLayerBlockForEnterExitCreateGraphicsLayerBlock));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return modifierThen;
    }

    public static final EnterTransition trackActiveEnter(Transition transition, EnterTransition enterTransition, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(21614502, i, -1, "androidx.compose.animation.trackActiveEnter (EnterExitTransition.kt:910)");
        }
        boolean z = (((i & 14) ^ 6) > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(enterTransition, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        if (transition.getCurrentState() == transition.getTargetState() && transition.getCurrentState() == EnterExitState.Visible) {
            if (transition.isSeeking()) {
                mutableState.setValue(enterTransition);
            } else {
                mutableState.setValue(EnterTransition.Companion.getNone());
            }
        } else if (transition.getTargetState() == EnterExitState.Visible) {
            mutableState.setValue(trackActiveEnter$lambda$5(mutableState).plus(enterTransition));
        }
        EnterTransition enterTransitionTrackActiveEnter$lambda$5 = trackActiveEnter$lambda$5(mutableState);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return enterTransitionTrackActiveEnter$lambda$5;
    }

    public static final ExitTransition trackActiveExit(Transition transition, ExitTransition exitTransition, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1363864804, i, -1, "androidx.compose.animation.trackActiveExit (EnterExitTransition.kt:930)");
        }
        boolean z = (((i & 14) ^ 6) > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(exitTransition, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        if (transition.getCurrentState() == transition.getTargetState() && transition.getCurrentState() == EnterExitState.Visible) {
            if (transition.isSeeking()) {
                mutableState.setValue(exitTransition);
            } else {
                mutableState.setValue(ExitTransition.Companion.getNone());
            }
        } else if (transition.getTargetState() != EnterExitState.Visible) {
            mutableState.setValue(trackActiveExit$lambda$8(mutableState).plus(exitTransition));
        }
        ExitTransition exitTransitionTrackActiveExit$lambda$8 = trackActiveExit$lambda$8(mutableState);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return exitTransitionTrackActiveExit$lambda$8;
    }

    private static final GraphicsLayerBlockForEnterExit createGraphicsLayerBlock(final Transition transition, final EnterTransition enterTransition, final ExitTransition exitTransition, String str, Composer composer, int i) {
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(642253525, i, -1, "androidx.compose.animation.createGraphicsLayerBlock (EnterExitTransition.kt:958)");
        }
        boolean z = true;
        boolean z2 = (enterTransition.getData$animation_release().getFade() == null && exitTransition.getData$animation_release().getFade() == null) ? false : true;
        enterTransition.getData$animation_release().getScale();
        exitTransition.getData$animation_release().getScale();
        if (z2) {
            composer.startReplaceGroup(-675389204);
            TwoWayConverter vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = str + " alpha";
                composer.updateRememberedValue(objRememberedValue);
            }
            deferredAnimationCreateDeferredAnimation = TransitionKt.createDeferredAnimation(transition, vectorConverter, (String) objRememberedValue, composer, (i & 14) | 384, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-675252433);
            composer.endReplaceGroup();
            deferredAnimationCreateDeferredAnimation = null;
        }
        final Transition.DeferredAnimation deferredAnimation = deferredAnimationCreateDeferredAnimation;
        composer.startReplaceGroup(-675057009);
        composer.endReplaceGroup();
        composer.startReplaceGroup(-674835793);
        composer.endReplaceGroup();
        final Transition.DeferredAnimation deferredAnimation2 = null;
        boolean zChangedInstance = composer.changedInstance(deferredAnimation) | ((((i & 112) ^ 48) > 32 && composer.changed(enterTransition)) || (i & 48) == 32) | ((((i & 896) ^ 384) > 256 && composer.changed(exitTransition)) || (i & 384) == 256) | composer.changedInstance(null);
        if ((((i & 14) ^ 6) <= 4 || !composer.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        final Transition.DeferredAnimation deferredAnimation3 = null;
        boolean zChangedInstance2 = zChangedInstance | z | composer.changedInstance(null);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.EnterExitTransitionKt$$ExternalSyntheticLambda0
                @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
                public final Function1 init() {
                    return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$13$lambda$12(deferredAnimation, deferredAnimation2, transition, enterTransition, exitTransition, deferredAnimation3);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit = (GraphicsLayerBlockForEnterExit) objRememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return graphicsLayerBlockForEnterExit;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1 createGraphicsLayerBlock$lambda$13$lambda$12(Transition.DeferredAnimation deferredAnimation, Transition.DeferredAnimation deferredAnimation2, Transition transition, final EnterTransition enterTransition, final ExitTransition exitTransition, Transition.DeferredAnimation deferredAnimation3) {
        final State stateAnimate = null;
        byte b = 0;
        final State stateAnimate2 = deferredAnimation != null ? deferredAnimation.animate(new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$alpha$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final FiniteAnimationSpec invoke(Transition.Segment segment) {
                FiniteAnimationSpec animationSpec;
                FiniteAnimationSpec animationSpec2;
                EnterExitState enterExitState = EnterExitState.PreEnter;
                EnterExitState enterExitState2 = EnterExitState.Visible;
                if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                    Fade fade = enterTransition.getData$animation_release().getFade();
                    return (fade == null || (animationSpec2 = fade.getAnimationSpec()) == null) ? EnterExitTransitionKt.DefaultAlphaAndScaleSpring : animationSpec2;
                }
                if (!segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                Fade fade2 = exitTransition.getData$animation_release().getFade();
                return (fade2 == null || (animationSpec = fade2.getAnimationSpec()) == null) ? EnterExitTransitionKt.DefaultAlphaAndScaleSpring : animationSpec;
            }
        }, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$alpha$2

            /* JADX INFO: compiled from: EnterExitTransition.kt */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Float invoke(EnterExitState enterExitState) {
                int i = WhenMappings.$EnumSwitchMapping$0[enterExitState.ordinal()];
                float alpha = 1.0f;
                if (i != 1) {
                    if (i == 2) {
                        Fade fade = enterTransition.getData$animation_release().getFade();
                        if (fade != null) {
                            alpha = fade.getAlpha();
                        }
                    } else {
                        if (i != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        Fade fade2 = exitTransition.getData$animation_release().getFade();
                        if (fade2 != null) {
                            alpha = fade2.getAlpha();
                        }
                    }
                }
                return Float.valueOf(alpha);
            }
        }) : null;
        final State stateAnimate3 = deferredAnimation2 != null ? deferredAnimation2.animate(new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$scale$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final FiniteAnimationSpec invoke(Transition.Segment segment) {
                EnterExitState enterExitState = EnterExitState.PreEnter;
                EnterExitState enterExitState2 = EnterExitState.Visible;
                if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                    enterTransition.getData$animation_release().getScale();
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                if (!segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
                }
                exitTransition.getData$animation_release().getScale();
                return EnterExitTransitionKt.DefaultAlphaAndScaleSpring;
            }
        }, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$scale$2

            /* JADX INFO: compiled from: EnterExitTransition.kt */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Float invoke(EnterExitState enterExitState) {
                int i = WhenMappings.$EnumSwitchMapping$0[enterExitState.ordinal()];
                if (i != 1) {
                    if (i == 2) {
                        enterTransition.getData$animation_release().getScale();
                    } else {
                        if (i != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        exitTransition.getData$animation_release().getScale();
                    }
                }
                return Float.valueOf(1.0f);
            }
        }) : null;
        if (transition.getCurrentState() == EnterExitState.PreEnter) {
            enterTransition.getData$animation_release().getScale();
            exitTransition.getData$animation_release().getScale();
        } else {
            exitTransition.getData$animation_release().getScale();
            enterTransition.getData$animation_release().getScale();
        }
        if (deferredAnimation3 != null) {
            EnterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$1 enterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$1 = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$1
                @Override // kotlin.jvm.functions.Function1
                public final FiniteAnimationSpec invoke(Transition.Segment segment) {
                    return AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                }
            };
            final byte b2 = b == true ? 1 : 0;
            stateAnimate = deferredAnimation3.animate(enterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$1, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$transformOrigin$2

                /* JADX INFO: compiled from: EnterExitTransition.kt */
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[EnterExitState.values().length];
                        try {
                            iArr[EnterExitState.Visible.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[EnterExitState.PreEnter.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[EnterExitState.PostExit.ordinal()] = 3;
                        } catch (NoSuchFieldError unused3) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return TransformOrigin.m1425boximpl(m30invokeLIALnN8((EnterExitState) obj));
                }

                /* JADX INFO: renamed from: invoke-LIALnN8, reason: not valid java name */
                public final long m30invokeLIALnN8(EnterExitState enterExitState) {
                    TransformOrigin transformOrigin;
                    int i = WhenMappings.$EnumSwitchMapping$0[enterExitState.ordinal()];
                    if (i != 1) {
                        transformOrigin = null;
                        if (i == 2) {
                            enterTransition.getData$animation_release().getScale();
                            exitTransition.getData$animation_release().getScale();
                        } else {
                            if (i != 3) {
                                throw new NoWhenBranchMatchedException();
                            }
                            exitTransition.getData$animation_release().getScale();
                            enterTransition.getData$animation_release().getScale();
                        }
                    } else {
                        transformOrigin = b2;
                    }
                    if (transformOrigin != null) {
                        return transformOrigin.m1433unboximpl();
                    }
                    return TransformOrigin.Companion.m1434getCenterSzJe1aQ();
                }
            });
        }
        return new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$1$block$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((GraphicsLayerScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(GraphicsLayerScope graphicsLayerScope) {
                State state = stateAnimate2;
                graphicsLayerScope.setAlpha(state != null ? ((Number) state.getValue()).floatValue() : 1.0f);
                State state2 = stateAnimate3;
                graphicsLayerScope.setScaleX(state2 != null ? ((Number) state2.getValue()).floatValue() : 1.0f);
                State state3 = stateAnimate3;
                graphicsLayerScope.setScaleY(state3 != null ? ((Number) state3.getValue()).floatValue() : 1.0f);
                State state4 = stateAnimate;
                graphicsLayerScope.mo1339setTransformOrigin__ExYCQ(state4 != null ? ((TransformOrigin) state4.getValue()).m1433unboximpl() : TransformOrigin.Companion.m1434getCenterSzJe1aQ());
            }
        };
    }
}
