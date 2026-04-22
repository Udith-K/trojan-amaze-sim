package androidx.compose.material3;

import androidx.compose.animation.core.SnapSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.material3.tokens.SwitchTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SwitchKt {
    private static final TweenSpec AnimationSpec;
    private static final SnapSpec SnapSpec;
    private static final float SwitchHeight;
    private static final float SwitchWidth;
    private static final float ThumbDiameter;
    private static final float ThumbPadding;
    private static final float UncheckedThumbDiameter;

    /* JADX WARN: Removed duplicated region for block: B:102:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0114  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void Switch(final boolean r22, final kotlin.jvm.functions.Function1 r23, androidx.compose.ui.Modifier r24, kotlin.jvm.functions.Function2 r25, boolean r26, androidx.compose.material3.SwitchColors r27, androidx.compose.foundation.interaction.MutableInteractionSource r28, androidx.compose.runtime.Composer r29, final int r30, final int r31) {
        /*
            Method dump skipped, instruction units count: 521
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SwitchKt.Switch(boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, boolean, androidx.compose.material3.SwitchColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SwitchImpl(final Modifier modifier, final boolean z, final boolean z2, final SwitchColors switchColors, final Function2 function2, final InteractionSource interactionSource, final Shape shape, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1594099146);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(z2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(switchColors) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(interactionSource) ? PKIFailureInfo.unsupportedVersion : PKIFailureInfo.notAuthorized;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(shape) ? PKIFailureInfo.badCertTemplate : PKIFailureInfo.signerNotTrusted;
        }
        int i3 = i2;
        if ((599187 & i3) != 599186 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1594099146, i3, -1, "androidx.compose.material3.SwitchImpl (Switch.kt:144)");
            }
            long jM743trackColorWaAFU9c$material3_release = switchColors.m743trackColorWaAFU9c$material3_release(z2, z);
            long jM742thumbColorWaAFU9c$material3_release = switchColors.m742thumbColorWaAFU9c$material3_release(z2, z);
            SwitchTokens switchTokens = SwitchTokens.INSTANCE;
            Shape value = ShapesKt.getValue(switchTokens.getTrackShape(), composerStartRestartGroup, 6);
            Modifier modifierM96backgroundbw27NRU = BackgroundKt.m96backgroundbw27NRU(BorderKt.m102borderxT4_qwU(modifier, switchTokens.m956getTrackOutlineWidthD9Ej5fM(), switchColors.m740borderColorWaAFU9c$material3_release(z2, z), value), jM743trackColorWaAFU9c$material3_release, value);
            Alignment.Companion companion = Alignment.Companion;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion.getTopStart(), false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM96backgroundbw27NRU);
            ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
            Function0 constructor = companion2.getConstructor();
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM1035constructorimpl = Updater.m1035constructorimpl(composerStartRestartGroup);
            Updater.m1036setimpl(composerM1035constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            Modifier modifierThen = BoxScopeInstance.INSTANCE.align(Modifier.Companion, companion.getCenterStart()).then(new ThumbElement(interactionSource, z));
            float fM2416constructorimpl = Dp.m2416constructorimpl(switchTokens.m954getStateLayerSizeD9Ej5fM() / 2);
            composer2 = composerStartRestartGroup;
            Modifier modifierM96backgroundbw27NRU2 = BackgroundKt.m96backgroundbw27NRU(IndicationKt.indication(modifierThen, interactionSource, RippleKt.m728rippleOrFallbackImplementation9IZ8Weo(false, fM2416constructorimpl, 0L, composerStartRestartGroup, 54, 4)), jM742thumbColorWaAFU9c$material3_release, shape);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion.getCenter(), false);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, modifierM96backgroundbw27NRU2);
            Function0 constructor2 = companion2.getConstructor();
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor2);
            } else {
                composer2.useNode();
            }
            Composer composerM1035constructorimpl2 = Updater.m1035constructorimpl(composer2);
            Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash2 = companion2.getSetCompositeKeyHash();
            if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion2.getSetModifier());
            composer2.startReplaceGroup(1163457794);
            if (function2 != null) {
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m1290boximpl(switchColors.m741iconColorWaAFU9c$material3_release(z2, z))), function2, composer2, ProvidedValue.$stable | ((i3 >> 9) & 112));
            }
            composer2.endReplaceGroup();
            composer2.endNode();
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SwitchKt.SwitchImpl.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i4) {
                    SwitchKt.SwitchImpl(modifier, z, z2, switchColors, function2, interactionSource, shape, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    static {
        SwitchTokens switchTokens = SwitchTokens.INSTANCE;
        float fM953getSelectedHandleWidthD9Ej5fM = switchTokens.m953getSelectedHandleWidthD9Ej5fM();
        ThumbDiameter = fM953getSelectedHandleWidthD9Ej5fM;
        UncheckedThumbDiameter = switchTokens.m958getUnselectedHandleWidthD9Ej5fM();
        SwitchWidth = switchTokens.m957getTrackWidthD9Ej5fM();
        float fM955getTrackHeightD9Ej5fM = switchTokens.m955getTrackHeightD9Ej5fM();
        SwitchHeight = fM955getTrackHeightD9Ej5fM;
        ThumbPadding = Dp.m2416constructorimpl(Dp.m2416constructorimpl(fM955getTrackHeightD9Ej5fM - fM953getSelectedHandleWidthD9Ej5fM) / 2);
        SnapSpec = new SnapSpec(0, 1, null);
        AnimationSpec = new TweenSpec(100, 0, null, 6, null);
    }

    public static final float getThumbDiameter() {
        return ThumbDiameter;
    }

    public static final float getUncheckedThumbDiameter() {
        return UncheckedThumbDiameter;
    }
}
