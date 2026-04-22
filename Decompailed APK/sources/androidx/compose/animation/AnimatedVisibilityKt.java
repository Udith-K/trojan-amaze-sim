package androidx.compose.animation;

import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: AnimatedVisibility.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AnimatedVisibilityKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2 AnimatedEnterExitImpl$lambda$2(State state) {
        return (Function2) state.getValue();
    }

    private static final boolean AnimatedEnterExitImpl$lambda$4(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void AnimatedVisibility(final androidx.compose.foundation.layout.ColumnScope r22, final boolean r23, androidx.compose.ui.Modifier r24, androidx.compose.animation.EnterTransition r25, androidx.compose.animation.ExitTransition r26, java.lang.String r27, final kotlin.jvm.functions.Function3 r28, androidx.compose.runtime.Composer r29, final int r30, final int r31) {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility(androidx.compose.foundation.layout.ColumnScope, boolean, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, java.lang.String, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void AnimatedVisibilityImpl(final Transition transition, final Function1 function1, final Modifier modifier, final EnterTransition enterTransition, final ExitTransition exitTransition, final Function3 function3, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(429978603);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(transition) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(enterTransition) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(exitTransition) ? 16384 : 8192;
        }
        if ((i & 196608) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? PKIFailureInfo.unsupportedVersion : PKIFailureInfo.notAuthorized;
        }
        if ((74899 & i2) != 74898 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(429978603, i2, -1, "androidx.compose.animation.AnimatedVisibilityImpl (AnimatedVisibility.kt:693)");
            }
            int i3 = i2 & 112;
            int i4 = i2 & 14;
            boolean z = (i3 == 32) | (i4 == 4);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function3() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                        return m23invoke3p2s80s((MeasureScope) obj, (Measurable) obj2, ((Constraints) obj3).m2395unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                    public final MeasureResult m23invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                        long jIntSize;
                        final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(j);
                        if (measureScope.isLookingAhead() && !((Boolean) function1.invoke(transition.getTargetState())).booleanValue()) {
                            jIntSize = IntSize.Companion.m2480getZeroYbymL2g();
                        } else {
                            jIntSize = IntSizeKt.IntSize(placeableMo1743measureBRTryo0.getWidth(), placeableMo1743measureBRTryo0.getHeight());
                        }
                        return MeasureScope.CC.layout$default(measureScope, IntSize.m2476getWidthimpl(jIntSize), IntSize.m2475getHeightimpl(jIntSize), null, new Function1() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$1$1.1
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
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            AnimatedEnterExitImpl(transition, function1, LayoutModifierKt.layout(modifier, (Function3) objRememberedValue), enterTransition, exitTransition, new Function2() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibilityImpl.2
                @Override // kotlin.jvm.functions.Function2
                public final Boolean invoke(EnterExitState enterExitState, EnterExitState enterExitState2) {
                    return Boolean.valueOf(enterExitState == enterExitState2 && enterExitState2 == EnterExitState.PostExit);
                }
            }, null, function3, composerStartRestartGroup, i3 | 196608 | i4 | (i2 & 7168) | (57344 & i2) | ((i2 << 6) & 29360128), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibilityImpl.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i5) {
                    AnimatedVisibilityKt.AnimatedVisibilityImpl(transition, function1, modifier, enterTransition, exitTransition, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final void AnimatedEnterExitImpl(Transition transition, Function1 function1, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, Function2 function2, OnLookaheadMeasured onLookaheadMeasured, Function3 function3, Composer composer, int i, int i2) {
        int i3;
        int i4;
        OnLookaheadMeasured onLookaheadMeasured2;
        int i5;
        final OnLookaheadMeasured onLookaheadMeasured3;
        Composer composer2;
        Modifier modifierLayout;
        OnLookaheadMeasured onLookaheadMeasured4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-891967166);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(transition) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changed(enterTransition) ? 2048 : 1024;
        }
        if ((i2 & 16) != 0) {
            i3 |= 24576;
        } else if ((i & 24576) == 0) {
            i3 |= composerStartRestartGroup.changed(exitTransition) ? 16384 : 8192;
        }
        if ((i2 & 32) != 0) {
            i3 |= 196608;
        } else if ((i & 196608) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? PKIFailureInfo.unsupportedVersion : PKIFailureInfo.notAuthorized;
        }
        int i6 = i2 & 64;
        int i7 = 1572864;
        if (i6 != 0) {
            i3 |= i7;
        } else if ((i & 1572864) == 0) {
            i7 = (i & PKIFailureInfo.badSenderNonce) == 0 ? composerStartRestartGroup.changed(onLookaheadMeasured) : composerStartRestartGroup.changedInstance(onLookaheadMeasured) ? PKIFailureInfo.badCertTemplate : PKIFailureInfo.signerNotTrusted;
            i3 |= i7;
        }
        if ((i2 & 128) != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 8388608 : 4194304;
        }
        int i8 = i3;
        if ((4793491 & i8) == 4793490 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            onLookaheadMeasured4 = onLookaheadMeasured;
            composer2 = composerStartRestartGroup;
        } else {
            OnLookaheadMeasured onLookaheadMeasured5 = i6 != 0 ? null : onLookaheadMeasured;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-891967166, i8, -1, "androidx.compose.animation.AnimatedEnterExitImpl (AnimatedVisibility.kt:737)");
            }
            if (((Boolean) function1.invoke(transition.getTargetState())).booleanValue() || ((Boolean) function1.invoke(transition.getCurrentState())).booleanValue() || transition.isSeeking() || transition.getHasInitialValueAnimations()) {
                composerStartRestartGroup.startReplaceGroup(1787977937);
                int i9 = i8 & 14;
                int i10 = i9 | 48;
                int i11 = i10 & 14;
                boolean z = true;
                boolean z2 = ((i11 ^ 6) > 4 && composerStartRestartGroup.changed(transition)) || (i10 & 6) == 4;
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = transition.getCurrentState();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                if (transition.isSeeking()) {
                    objRememberedValue = transition.getCurrentState();
                }
                composerStartRestartGroup.startReplaceGroup(-466616829);
                if (ComposerKt.isTraceInProgress()) {
                    i4 = i9;
                    onLookaheadMeasured2 = onLookaheadMeasured5;
                    ComposerKt.traceEventStart(-466616829, 0, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:742)");
                } else {
                    i4 = i9;
                    onLookaheadMeasured2 = onLookaheadMeasured5;
                }
                int i12 = i8 & 126;
                EnterExitState enterExitStateTargetEnterExit = targetEnterExit(transition, function1, objRememberedValue, composerStartRestartGroup, i12);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composerStartRestartGroup.endReplaceGroup();
                Object targetState = transition.getTargetState();
                composerStartRestartGroup.startReplaceGroup(-466616829);
                if (ComposerKt.isTraceInProgress()) {
                    i5 = i8;
                    ComposerKt.traceEventStart(-466616829, 0, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:742)");
                } else {
                    i5 = i8;
                }
                EnterExitState enterExitStateTargetEnterExit2 = targetEnterExit(transition, function1, targetState, composerStartRestartGroup, i12);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composerStartRestartGroup.endReplaceGroup();
                int i13 = i11 | 3072;
                int i14 = i4;
                OnLookaheadMeasured onLookaheadMeasured6 = onLookaheadMeasured2;
                int i15 = i5;
                Transition transitionCreateChildTransitionInternal = TransitionKt.createChildTransitionInternal(transition, enterExitStateTargetEnterExit, enterExitStateTargetEnterExit2, "EnterExitTransition", composerStartRestartGroup, i13);
                State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i15 >> 15) & 14);
                Object objInvoke = function2.invoke(transitionCreateChildTransitionInternal.getCurrentState(), transitionCreateChildTransitionInternal.getTargetState());
                boolean zChanged = composerStartRestartGroup.changed(transitionCreateChildTransitionInternal) | composerStartRestartGroup.changed(stateRememberUpdatedState);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.Companion.getEmpty()) {
                    objRememberedValue2 = new AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1(transitionCreateChildTransitionInternal, stateRememberUpdatedState, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                State stateProduceState = SnapshotStateKt.produceState(objInvoke, (Function2) objRememberedValue2, composerStartRestartGroup, 0);
                if (getExitFinished(transitionCreateChildTransitionInternal) && AnimatedEnterExitImpl$lambda$4(stateProduceState)) {
                    composerStartRestartGroup.startReplaceGroup(1790256282);
                    composerStartRestartGroup.endReplaceGroup();
                    onLookaheadMeasured3 = onLookaheadMeasured6;
                    composer2 = composerStartRestartGroup;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1788869559);
                    boolean z3 = i14 == 4;
                    Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z3 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                        objRememberedValue3 = new AnimatedVisibilityScopeImpl(transitionCreateChildTransitionInternal);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    AnimatedVisibilityScopeImpl animatedVisibilityScopeImpl = (AnimatedVisibilityScopeImpl) objRememberedValue3;
                    int i16 = i15 >> 6;
                    int i17 = (i16 & 112) | 24576 | (i16 & 896);
                    onLookaheadMeasured3 = onLookaheadMeasured6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifierCreateModifier = EnterExitTransitionKt.createModifier(transitionCreateChildTransitionInternal, enterTransition, exitTransition, null, "Built-in", composer2, i17, 4);
                    if (onLookaheadMeasured3 != null) {
                        composer2.startReplaceGroup(1789227361);
                        Modifier.Companion companion = Modifier.Companion;
                        if ((i15 & 3670016) != 1048576 && ((i15 & PKIFailureInfo.badSenderNonce) == 0 || !composer2.changedInstance(onLookaheadMeasured3))) {
                            z = false;
                        }
                        Object objRememberedValue4 = composer2.rememberedValue();
                        if (z || objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function3(onLookaheadMeasured3) { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                                    return m22invoke3p2s80s((MeasureScope) obj, (Measurable) obj2, ((Constraints) obj3).m2395unboximpl());
                                }

                                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                public final MeasureResult m22invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                                    final Placeable placeableMo1743measureBRTryo0 = measurable.mo1743measureBRTryo0(j);
                                    if (measureScope.isLookingAhead()) {
                                        IntSizeKt.IntSize(placeableMo1743measureBRTryo0.getWidth(), placeableMo1743measureBRTryo0.getHeight());
                                        throw null;
                                    }
                                    return MeasureScope.CC.layout$default(measureScope, placeableMo1743measureBRTryo0.getWidth(), placeableMo1743measureBRTryo0.getHeight(), null, new Function1() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
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
                            };
                            composer2.updateRememberedValue(objRememberedValue4);
                        }
                        modifierLayout = LayoutModifierKt.layout(companion, (Function3) objRememberedValue4);
                        composer2.endReplaceGroup();
                    } else {
                        composer2.startReplaceGroup(1581766416);
                        composer2.endReplaceGroup();
                        modifierLayout = Modifier.Companion;
                    }
                    Modifier modifierThen = modifier.then(modifierCreateModifier.then(modifierLayout));
                    Object objRememberedValue5 = composer2.rememberedValue();
                    if (objRememberedValue5 == Composer.Companion.getEmpty()) {
                        objRememberedValue5 = new AnimatedEnterExitMeasurePolicy(animatedVisibilityScopeImpl);
                        composer2.updateRememberedValue(objRememberedValue5);
                    }
                    AnimatedEnterExitMeasurePolicy animatedEnterExitMeasurePolicy = (AnimatedEnterExitMeasurePolicy) objRememberedValue5;
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierThen);
                    ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
                    Function0 constructor = companion2.getConstructor();
                    if (!(composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM1035constructorimpl = Updater.m1035constructorimpl(composer2);
                    Updater.m1036setimpl(composerM1035constructorimpl, animatedEnterExitMeasurePolicy, companion2.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                    function3.invoke(animatedVisibilityScopeImpl, composer2, Integer.valueOf((i15 >> 18) & 112));
                    composer2.endNode();
                    composer2.endReplaceGroup();
                }
                composer2.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1790262234);
                composerStartRestartGroup.endReplaceGroup();
                onLookaheadMeasured3 = onLookaheadMeasured5;
                composer2 = composerStartRestartGroup;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            onLookaheadMeasured4 = onLookaheadMeasured3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2(function1, modifier, enterTransition, exitTransition, function2, onLookaheadMeasured4, function3, i, i2) { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedEnterExitImpl.4
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ Function3 $content;
                final /* synthetic */ EnterTransition $enter;
                final /* synthetic */ ExitTransition $exit;
                final /* synthetic */ Modifier $modifier;
                final /* synthetic */ Function2 $shouldDisposeBlock;
                final /* synthetic */ Function1 $visible;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                    this.$content = function3;
                    this.$$changed = i;
                    this.$$default = i2;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i18) {
                    AnimatedVisibilityKt.AnimatedEnterExitImpl(this.$transition, this.$visible, this.$modifier, this.$enter, this.$exit, this.$shouldDisposeBlock, null, this.$content, composer3, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getExitFinished(Transition transition) {
        Object currentState = transition.getCurrentState();
        EnterExitState enterExitState = EnterExitState.PostExit;
        return currentState == enterExitState && transition.getTargetState() == enterExitState;
    }

    private static final EnterExitState targetEnterExit(Transition transition, Function1 function1, Object obj, Composer composer, int i) {
        EnterExitState enterExitState;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(361571134, i, -1, "androidx.compose.animation.targetEnterExit (AnimatedVisibility.kt:855)");
        }
        composer.startMovableGroup(-902048200, transition);
        if (transition.isSeeking()) {
            composer.startReplaceGroup(2101296683);
            composer.endReplaceGroup();
            if (((Boolean) function1.invoke(obj)).booleanValue()) {
                enterExitState = EnterExitState.Visible;
            } else if (((Boolean) function1.invoke(transition.getCurrentState())).booleanValue()) {
                enterExitState = EnterExitState.PostExit;
            } else {
                enterExitState = EnterExitState.PreEnter;
            }
        } else {
            composer.startReplaceGroup(2101530516);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            if (((Boolean) function1.invoke(transition.getCurrentState())).booleanValue()) {
                mutableState.setValue(Boolean.TRUE);
            }
            if (((Boolean) function1.invoke(obj)).booleanValue()) {
                enterExitState = EnterExitState.Visible;
            } else if (((Boolean) mutableState.getValue()).booleanValue()) {
                enterExitState = EnterExitState.PostExit;
            } else {
                enterExitState = EnterExitState.PreEnter;
            }
            composer.endReplaceGroup();
        }
        composer.endMovableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return enterExitState;
    }
}
