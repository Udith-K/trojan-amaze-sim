package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Surface.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SurfaceKt {
    private static final ProvidableCompositionLocal LocalAbsoluteTonalElevation = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.material3.SurfaceKt$LocalAbsoluteTonalElevation$1
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            return Dp.m2414boximpl(m739invokeD9Ej5fM());
        }

        /* JADX INFO: renamed from: invoke-D9Ej5fM, reason: not valid java name */
        public final float m739invokeD9Ej5fM() {
            return Dp.m2416constructorimpl(0);
        }
    }, 1, null);

    /* JADX INFO: renamed from: Surface-T9BRK9s, reason: not valid java name */
    public static final void m733SurfaceT9BRK9s(Modifier modifier, Shape shape, long j, long j2, float f, float f2, BorderStroke borderStroke, final Function2 function2, Composer composer, int i, int i2) {
        Modifier modifier2 = (i2 & 1) != 0 ? Modifier.Companion : modifier;
        Shape rectangleShape = (i2 & 2) != 0 ? RectangleShapeKt.getRectangleShape() : shape;
        long jM643getSurface0d7_KjU = (i2 & 4) != 0 ? MaterialTheme.INSTANCE.getColorScheme(composer, 6).m643getSurface0d7_KjU() : j;
        long jM657contentColorForek8zF_U = (i2 & 8) != 0 ? ColorSchemeKt.m657contentColorForek8zF_U(jM643getSurface0d7_KjU, composer, (i >> 6) & 14) : j2;
        float fM2416constructorimpl = (i2 & 16) != 0 ? Dp.m2416constructorimpl(0) : f;
        float fM2416constructorimpl2 = (i2 & 32) != 0 ? Dp.m2416constructorimpl(0) : f2;
        BorderStroke borderStroke2 = (i2 & 64) != 0 ? null : borderStroke;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-513881741, i, -1, "androidx.compose.material3.Surface (Surface.kt:102)");
        }
        ProvidableCompositionLocal providableCompositionLocal = LocalAbsoluteTonalElevation;
        final float fM2416constructorimpl3 = Dp.m2416constructorimpl(((Dp) composer.consume(providableCompositionLocal)).m2422unboximpl() + fM2416constructorimpl);
        final Modifier modifier3 = modifier2;
        final Shape shape2 = rectangleShape;
        final long j3 = jM643getSurface0d7_KjU;
        final BorderStroke borderStroke3 = borderStroke2;
        final float f3 = fM2416constructorimpl2;
        CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m1290boximpl(jM657contentColorForek8zF_U)), providableCompositionLocal.provides(Dp.m2414boximpl(fM2416constructorimpl3))}, ComposableLambdaKt.rememberComposableLambda(-70914509, true, new Function2() { // from class: androidx.compose.material3.SurfaceKt$Surface$1
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
                if ((i3 & 3) != 2 || !composer2.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-70914509, i3, -1, "androidx.compose.material3.Surface.<anonymous> (Surface.kt:108)");
                    }
                    Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(SemanticsModifierKt.semantics(SurfaceKt.m737surfaceXOJAsU(modifier3, shape2, SurfaceKt.m738surfaceColorAtElevationCLU3JFs(j3, fM2416constructorimpl3, composer2, 0), borderStroke3, ((Density) composer2.consume(CompositionLocalsKt.getLocalDensity())).mo210toPx0680j_4(f3)), false, new Function1() { // from class: androidx.compose.material3.SurfaceKt$Surface$1.2
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((SemanticsPropertyReceiver) obj);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            SemanticsPropertiesKt.setContainer(semanticsPropertyReceiver, true);
                        }
                    }), Unit.INSTANCE, new AnonymousClass3(null));
                    Function2 function22 = function2;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierPointerInput);
                    ComposeUiNode.Companion companion = ComposeUiNode.Companion;
                    Function0 constructor = companion.getConstructor();
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
                    Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash = companion.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    function22.invoke(composer2, 0);
                    composer2.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer2.skipToGroupEnd();
            }

            /* JADX INFO: renamed from: androidx.compose.material3.SurfaceKt$Surface$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: Surface.kt */
            static final class AnonymousClass3 extends SuspendLambda implements Function2 {
                int label;

                AnonymousClass3(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                    return ((AnonymousClass3) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
            }
        }, composer, 54), composer, ProvidedValue.$stable | 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    /* JADX INFO: renamed from: Surface-o_FOJdg, reason: not valid java name */
    public static final void m734Surfaceo_FOJdg(final Function0 function0, Modifier modifier, boolean z, Shape shape, long j, long j2, float f, float f2, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, final Function2 function2, Composer composer, int i, int i2, int i3) {
        final Modifier modifier2 = (i3 & 2) != 0 ? Modifier.Companion : modifier;
        final boolean z2 = (i3 & 4) != 0 ? true : z;
        final Shape rectangleShape = (i3 & 8) != 0 ? RectangleShapeKt.getRectangleShape() : shape;
        final long jM643getSurface0d7_KjU = (i3 & 16) != 0 ? MaterialTheme.INSTANCE.getColorScheme(composer, 6).m643getSurface0d7_KjU() : j;
        long jM657contentColorForek8zF_U = (i3 & 32) != 0 ? ColorSchemeKt.m657contentColorForek8zF_U(jM643getSurface0d7_KjU, composer, (i >> 12) & 14) : j2;
        float fM2416constructorimpl = (i3 & 64) != 0 ? Dp.m2416constructorimpl(0) : f;
        final float fM2416constructorimpl2 = (i3 & 128) != 0 ? Dp.m2416constructorimpl(0) : f2;
        final BorderStroke borderStroke2 = (i3 & 256) != 0 ? null : borderStroke;
        final MutableInteractionSource mutableInteractionSource2 = (i3 & 512) == 0 ? mutableInteractionSource : null;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-789752804, i, i2, "androidx.compose.material3.Surface (Surface.kt:203)");
        }
        ProvidableCompositionLocal providableCompositionLocal = LocalAbsoluteTonalElevation;
        final float fM2416constructorimpl3 = Dp.m2416constructorimpl(((Dp) composer.consume(providableCompositionLocal)).m2422unboximpl() + fM2416constructorimpl);
        CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m1290boximpl(jM657contentColorForek8zF_U)), providableCompositionLocal.provides(Dp.m2414boximpl(fM2416constructorimpl3))}, ComposableLambdaKt.rememberComposableLambda(1279702876, true, new Function2() { // from class: androidx.compose.material3.SurfaceKt$Surface$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i4) {
                if ((i4 & 3) != 2 || !composer2.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1279702876, i4, -1, "androidx.compose.material3.Surface.<anonymous> (Surface.kt:209)");
                    }
                    Modifier modifierM114clickableO2vRcR0$default = ClickableKt.m114clickableO2vRcR0$default(SurfaceKt.m737surfaceXOJAsU(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier2), rectangleShape, SurfaceKt.m738surfaceColorAtElevationCLU3JFs(jM643getSurface0d7_KjU, fM2416constructorimpl3, composer2, 0), borderStroke2, ((Density) composer2.consume(CompositionLocalsKt.getLocalDensity())).mo210toPx0680j_4(fM2416constructorimpl2)), mutableInteractionSource2, RippleKt.m728rippleOrFallbackImplementation9IZ8Weo(false, 0.0f, 0L, composer2, 0, 7), z2, null, null, function0, 24, null);
                    Function2 function22 = function2;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), true);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM114clickableO2vRcR0$default);
                    ComposeUiNode.Companion companion = ComposeUiNode.Companion;
                    Function0 constructor = companion.getConstructor();
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
                    Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
                    Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                    Function2 setCompositeKeyHash = companion.getSetCompositeKeyHash();
                    if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    function22.invoke(composer2, 0);
                    composer2.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer2.skipToGroupEnd();
            }
        }, composer, 54), composer, ProvidedValue.$stable | 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: surface-XO-JAsU, reason: not valid java name */
    public static final Modifier m737surfaceXOJAsU(Modifier modifier, Shape shape, long j, BorderStroke borderStroke, float f) {
        Modifier modifierM1334graphicsLayerAp8cVGQ$default;
        Shape shape2;
        Modifier modifierBorder;
        if (f > 0.0f) {
            modifierM1334graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m1334graphicsLayerAp8cVGQ$default(Modifier.Companion, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, shape, false, null, 0L, 0L, 0, 124895, null);
        } else {
            modifierM1334graphicsLayerAp8cVGQ$default = Modifier.Companion;
        }
        Modifier modifierThen = modifier.then(modifierM1334graphicsLayerAp8cVGQ$default);
        if (borderStroke != null) {
            shape2 = shape;
            modifierBorder = BorderKt.border(Modifier.Companion, borderStroke, shape2);
        } else {
            shape2 = shape;
            modifierBorder = Modifier.Companion;
        }
        return ClipKt.clip(BackgroundKt.m96backgroundbw27NRU(modifierThen.then(modifierBorder), j, shape2), shape2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: surfaceColorAtElevation-CLU3JFs, reason: not valid java name */
    public static final long m738surfaceColorAtElevationCLU3JFs(long j, float f, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2079918090, i, -1, "androidx.compose.material3.surfaceColorAtElevation (Surface.kt:465)");
        }
        long jM655applyTonalElevationRFCenO8 = ColorSchemeKt.m655applyTonalElevationRFCenO8(MaterialTheme.INSTANCE.getColorScheme(composer, 6), j, f, composer, (i << 3) & 1008);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jM655applyTonalElevationRFCenO8;
    }
}
