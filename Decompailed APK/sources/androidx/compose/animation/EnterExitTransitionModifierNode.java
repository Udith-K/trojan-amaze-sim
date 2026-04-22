package androidx.compose.animation;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EnterExitTransition.kt */
/* JADX INFO: loaded from: classes.dex */
final class EnterExitTransitionModifierNode extends LayoutModifierNodeWithPassThroughIntrinsics {
    private Alignment currentAlignment;
    private EnterTransition enter;
    private ExitTransition exit;
    private GraphicsLayerBlockForEnterExit graphicsLayerBlock;
    private Function0 isEnabled;
    private boolean lookaheadConstraintsAvailable;
    private Transition.DeferredAnimation offsetAnimation;
    private Transition.DeferredAnimation sizeAnimation;
    private Transition.DeferredAnimation slideAnimation;
    private Transition transition;
    private long lookaheadSize = AnimationModifierKt.getInvalidSize();
    private long lookaheadConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);
    private final Function1 sizeTransitionSpec = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$sizeTransitionSpec$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final FiniteAnimationSpec invoke(Transition.Segment segment) {
            EnterExitState enterExitState = EnterExitState.PreEnter;
            EnterExitState enterExitState2 = EnterExitState.Visible;
            FiniteAnimationSpec animationSpec = null;
            if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                ChangeSize changeSize = this.this$0.getEnter().getData$animation_release().getChangeSize();
                if (changeSize != null) {
                    animationSpec = changeSize.getAnimationSpec();
                }
            } else if (!segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                animationSpec = EnterExitTransitionKt.DefaultSizeAnimationSpec;
            } else {
                ChangeSize changeSize2 = this.this$0.getExit().getData$animation_release().getChangeSize();
                if (changeSize2 != null) {
                    animationSpec = changeSize2.getAnimationSpec();
                }
            }
            return animationSpec == null ? EnterExitTransitionKt.DefaultSizeAnimationSpec : animationSpec;
        }
    };
    private final Function1 slideSpec = new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$slideSpec$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final FiniteAnimationSpec invoke(Transition.Segment segment) {
            EnterExitState enterExitState = EnterExitState.PreEnter;
            EnterExitState enterExitState2 = EnterExitState.Visible;
            if (segment.isTransitioningTo(enterExitState, enterExitState2)) {
                this.this$0.getEnter().getData$animation_release().getSlide();
                return EnterExitTransitionKt.DefaultOffsetAnimationSpec;
            }
            if (!segment.isTransitioningTo(enterExitState2, EnterExitState.PostExit)) {
                return EnterExitTransitionKt.DefaultOffsetAnimationSpec;
            }
            this.this$0.getExit().getData$animation_release().getSlide();
            return EnterExitTransitionKt.DefaultOffsetAnimationSpec;
        }
    };

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

    public final void setTransition(Transition transition) {
        this.transition = transition;
    }

    public final void setSizeAnimation(Transition.DeferredAnimation deferredAnimation) {
        this.sizeAnimation = deferredAnimation;
    }

    public final void setOffsetAnimation(Transition.DeferredAnimation deferredAnimation) {
        this.offsetAnimation = deferredAnimation;
    }

    public final void setSlideAnimation(Transition.DeferredAnimation deferredAnimation) {
        this.slideAnimation = deferredAnimation;
    }

    public final EnterTransition getEnter() {
        return this.enter;
    }

    public final void setEnter(EnterTransition enterTransition) {
        this.enter = enterTransition;
    }

    public final ExitTransition getExit() {
        return this.exit;
    }

    public final void setExit(ExitTransition exitTransition) {
        this.exit = exitTransition;
    }

    public final void setEnabled(Function0 function0) {
        this.isEnabled = function0;
    }

    public final void setGraphicsLayerBlock(GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit) {
        this.graphicsLayerBlock = graphicsLayerBlockForEnterExit;
    }

    public EnterExitTransitionModifierNode(Transition transition, Transition.DeferredAnimation deferredAnimation, Transition.DeferredAnimation deferredAnimation2, Transition.DeferredAnimation deferredAnimation3, EnterTransition enterTransition, ExitTransition exitTransition, Function0 function0, GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit) {
        this.transition = transition;
        this.sizeAnimation = deferredAnimation;
        this.offsetAnimation = deferredAnimation2;
        this.slideAnimation = deferredAnimation3;
        this.enter = enterTransition;
        this.exit = exitTransition;
        this.isEnabled = function0;
        this.graphicsLayerBlock = graphicsLayerBlockForEnterExit;
    }

    /* JADX INFO: renamed from: setLookaheadConstraints-BRTryo0, reason: not valid java name */
    private final void m35setLookaheadConstraintsBRTryo0(long j) {
        this.lookaheadConstraintsAvailable = true;
        this.lookaheadConstraints = j;
    }

    public final Alignment getAlignment() {
        Alignment alignment;
        if (this.transition.getSegment().isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible)) {
            ChangeSize changeSize = this.enter.getData$animation_release().getChangeSize();
            if (changeSize == null || (alignment = changeSize.getAlignment()) == null) {
                ChangeSize changeSize2 = this.exit.getData$animation_release().getChangeSize();
                if (changeSize2 != null) {
                    return changeSize2.getAlignment();
                }
                return null;
            }
        } else {
            ChangeSize changeSize3 = this.exit.getData$animation_release().getChangeSize();
            if (changeSize3 == null || (alignment = changeSize3.getAlignment()) == null) {
                ChangeSize changeSize4 = this.enter.getData$animation_release().getChangeSize();
                if (changeSize4 != null) {
                    return changeSize4.getAlignment();
                }
                return null;
            }
        }
        return alignment;
    }

    /* JADX INFO: renamed from: sizeByState-Uzc_VyU, reason: not valid java name */
    public final long m37sizeByStateUzc_VyU(EnterExitState enterExitState, long j) {
        Function1 size;
        Function1 size2;
        int i = WhenMappings.$EnumSwitchMapping$0[enterExitState.ordinal()];
        if (i == 1) {
            return j;
        }
        if (i == 2) {
            ChangeSize changeSize = this.enter.getData$animation_release().getChangeSize();
            return (changeSize == null || (size = changeSize.getSize()) == null) ? j : ((IntSize) size.invoke(IntSize.m2471boximpl(j))).m2479unboximpl();
        }
        if (i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        ChangeSize changeSize2 = this.exit.getData$animation_release().getChangeSize();
        return (changeSize2 == null || (size2 = changeSize2.getSize()) == null) ? j : ((IntSize) size2.invoke(IntSize.m2471boximpl(j))).m2479unboximpl();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        super.onAttach();
        this.lookaheadConstraintsAvailable = false;
        this.lookaheadSize = AnimationModifierKt.getInvalidSize();
    }

    /* JADX INFO: renamed from: targetOffsetByState-oFUgxo0, reason: not valid java name */
    public final long m39targetOffsetByStateoFUgxo0(EnterExitState enterExitState, long j) {
        if (this.currentAlignment != null && getAlignment() != null && !Intrinsics.areEqual(this.currentAlignment, getAlignment())) {
            int i = WhenMappings.$EnumSwitchMapping$0[enterExitState.ordinal()];
            if (i == 1) {
                return IntOffset.Companion.m2464getZeronOccac();
            }
            if (i == 2) {
                return IntOffset.Companion.m2464getZeronOccac();
            }
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            ChangeSize changeSize = this.exit.getData$animation_release().getChangeSize();
            if (changeSize != null) {
                long jM2479unboximpl = ((IntSize) changeSize.getSize().invoke(IntSize.m2471boximpl(j))).m2479unboximpl();
                Alignment alignment = getAlignment();
                Intrinsics.checkNotNull(alignment);
                LayoutDirection layoutDirection = LayoutDirection.Ltr;
                long jMo1066alignKFBX0sM = alignment.mo1066alignKFBX0sM(j, jM2479unboximpl, layoutDirection);
                Alignment alignment2 = this.currentAlignment;
                Intrinsics.checkNotNull(alignment2);
                return IntOffset.m2460minusqkQi6aY(jMo1066alignKFBX0sM, alignment2.mo1066alignKFBX0sM(j, jM2479unboximpl, layoutDirection));
            }
            return IntOffset.Companion.m2464getZeronOccac();
        }
        return IntOffset.Companion.m2464getZeronOccac();
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult mo36measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        State stateAnimate;
        State stateAnimate2;
        if (this.transition.getCurrentState() == this.transition.getTargetState()) {
            this.currentAlignment = null;
        } else if (this.currentAlignment == null) {
            Alignment alignment = getAlignment();
            if (alignment == null) {
                alignment = Alignment.Companion.getTopStart();
            }
            this.currentAlignment = alignment;
        }
        if (measureScope.isLookingAhead()) {
            final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(j);
            long jIntSize = IntSizeKt.IntSize(placeableMo1743measureBRTryo0.getWidth(), placeableMo1743measureBRTryo0.getHeight());
            this.lookaheadSize = jIntSize;
            m35setLookaheadConstraintsBRTryo0(j);
            return MeasureScope.CC.layout$default(measureScope, IntSize.m2476getWidthimpl(jIntSize), IntSize.m2475getHeightimpl(jIntSize), null, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Placeable.PlacementScope) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Placeable.PlacementScope placementScope) {
                    Placeable.PlacementScope.place$default(placementScope, placeableMo1743measureBRTryo0, 0, 0, 0.0f, 4, null);
                }
            }, 4, null);
        }
        if (((Boolean) this.isEnabled.invoke()).booleanValue()) {
            final Function1 function1Init = this.graphicsLayerBlock.init();
            final Placeable placeableMo1743measureBRTryo02 = measurable.mo1743measureBRTryo0(j);
            long jIntSize2 = IntSizeKt.IntSize(placeableMo1743measureBRTryo02.getWidth(), placeableMo1743measureBRTryo02.getHeight());
            final long j2 = AnimationModifierKt.m24isValidozmzZPI(this.lookaheadSize) ? this.lookaheadSize : jIntSize2;
            Transition.DeferredAnimation deferredAnimation = this.sizeAnimation;
            State stateAnimate3 = deferredAnimation != null ? deferredAnimation.animate(this.sizeTransitionSpec, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$animSize$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return IntSize.m2471boximpl(m40invokeYEO4UFw((EnterExitState) obj));
                }

                /* JADX INFO: renamed from: invoke-YEO4UFw, reason: not valid java name */
                public final long m40invokeYEO4UFw(EnterExitState enterExitState) {
                    return this.this$0.m37sizeByStateUzc_VyU(enterExitState, j2);
                }
            }) : null;
            if (stateAnimate3 != null) {
                jIntSize2 = ((IntSize) stateAnimate3.getValue()).m2479unboximpl();
            }
            long jM2400constrain4WqzIAM = ConstraintsKt.m2400constrain4WqzIAM(j, jIntSize2);
            Transition.DeferredAnimation deferredAnimation2 = this.offsetAnimation;
            final long jM2464getZeronOccac = (deferredAnimation2 == null || (stateAnimate2 = deferredAnimation2.animate(new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$offsetDelta$1
                @Override // kotlin.jvm.functions.Function1
                public final FiniteAnimationSpec invoke(Transition.Segment segment) {
                    return EnterExitTransitionKt.DefaultOffsetAnimationSpec;
                }
            }, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$offsetDelta$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return IntOffset.m2451boximpl(m41invokeBjo55l4((EnterExitState) obj));
                }

                /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                public final long m41invokeBjo55l4(EnterExitState enterExitState) {
                    return this.this$0.m39targetOffsetByStateoFUgxo0(enterExitState, j2);
                }
            })) == null) ? IntOffset.Companion.m2464getZeronOccac() : ((IntOffset) stateAnimate2.getValue()).m2463unboximpl();
            Transition.DeferredAnimation deferredAnimation3 = this.slideAnimation;
            long jM2464getZeronOccac2 = (deferredAnimation3 == null || (stateAnimate = deferredAnimation3.animate(this.slideSpec, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$slideOffset$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return IntOffset.m2451boximpl(m42invokeBjo55l4((EnterExitState) obj));
                }

                /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
                public final long m42invokeBjo55l4(EnterExitState enterExitState) {
                    return this.this$0.m38slideTargetValueByStateoFUgxo0(enterExitState, j2);
                }
            })) == null) ? IntOffset.Companion.m2464getZeronOccac() : ((IntOffset) stateAnimate.getValue()).m2463unboximpl();
            Alignment alignment2 = this.currentAlignment;
            final long jM2461plusqkQi6aY = IntOffset.m2461plusqkQi6aY(alignment2 != null ? alignment2.mo1066alignKFBX0sM(j2, jM2400constrain4WqzIAM, LayoutDirection.Ltr) : IntOffset.Companion.m2464getZeronOccac(), jM2464getZeronOccac2);
            return MeasureScope.CC.layout$default(measureScope, IntSize.m2476getWidthimpl(jM2400constrain4WqzIAM), IntSize.m2475getHeightimpl(jM2400constrain4WqzIAM), null, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Placeable.PlacementScope) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Placeable.PlacementScope placementScope) {
                    placementScope.placeWithLayer(placeableMo1743measureBRTryo02, IntOffset.m2457getXimpl(jM2464getZeronOccac) + IntOffset.m2457getXimpl(jM2461plusqkQi6aY), IntOffset.m2458getYimpl(jM2464getZeronOccac) + IntOffset.m2458getYimpl(jM2461plusqkQi6aY), 0.0f, function1Init);
                }
            }, 4, null);
        }
        final Placeable placeableMo1743measureBRTryo03 = measurable.mo1743measureBRTryo0(j);
        return MeasureScope.CC.layout$default(measureScope, placeableMo1743measureBRTryo03.getWidth(), placeableMo1743measureBRTryo03.getHeight(), null, new Function1() { // from class: androidx.compose.animation.EnterExitTransitionModifierNode$measure$3$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                Placeable.PlacementScope.place$default(placementScope, placeableMo1743measureBRTryo03, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    /* JADX INFO: renamed from: slideTargetValueByState-oFUgxo0, reason: not valid java name */
    public final long m38slideTargetValueByStateoFUgxo0(EnterExitState enterExitState, long j) {
        this.enter.getData$animation_release().getSlide();
        IntOffset.Companion companion = IntOffset.Companion;
        long jM2464getZeronOccac = companion.m2464getZeronOccac();
        this.exit.getData$animation_release().getSlide();
        long jM2464getZeronOccac2 = companion.m2464getZeronOccac();
        int i = WhenMappings.$EnumSwitchMapping$0[enterExitState.ordinal()];
        if (i == 1) {
            return companion.m2464getZeronOccac();
        }
        if (i == 2) {
            return jM2464getZeronOccac;
        }
        if (i == 3) {
            return jM2464getZeronOccac2;
        }
        throw new NoWhenBranchMatchedException();
    }
}
