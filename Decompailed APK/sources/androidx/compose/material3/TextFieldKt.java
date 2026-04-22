package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: TextField.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextFieldKt {
    private static final float TextFieldWithLabelVerticalPadding = Dp.m2416constructorimpl(8);

    /* JADX INFO: Access modifiers changed from: private */
    public static final int substractConstraintSafely(int i, int i2) {
        return i == Integer.MAX_VALUE ? i : i - i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x038f  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x0466  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0488  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x04a0  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x054d  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:360:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0124  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void TextField(final androidx.compose.ui.text.input.TextFieldValue r82, final kotlin.jvm.functions.Function1 r83, androidx.compose.ui.Modifier r84, boolean r85, boolean r86, androidx.compose.ui.text.TextStyle r87, kotlin.jvm.functions.Function2 r88, kotlin.jvm.functions.Function2 r89, kotlin.jvm.functions.Function2 r90, kotlin.jvm.functions.Function2 r91, kotlin.jvm.functions.Function2 r92, kotlin.jvm.functions.Function2 r93, kotlin.jvm.functions.Function2 r94, boolean r95, androidx.compose.ui.text.input.VisualTransformation r96, androidx.compose.foundation.text.KeyboardOptions r97, androidx.compose.foundation.text.KeyboardActions r98, boolean r99, int r100, int r101, androidx.compose.foundation.interaction.MutableInteractionSource r102, androidx.compose.ui.graphics.Shape r103, androidx.compose.material3.TextFieldColors r104, androidx.compose.runtime.Composer r105, final int r106, final int r107, final int r108, final int r109) {
        /*
            Method dump skipped, instruction units count: 1432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldKt.TextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    public static final void TextFieldLayout(final Modifier modifier, final Function2 function2, final Function2 function22, final Function3 function3, final Function2 function23, final Function2 function24, final Function2 function25, final Function2 function26, final boolean z, final float f, final Function2 function27, final Function2 function28, final PaddingValues paddingValues, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        PaddingValues paddingValues2;
        int i5;
        float f2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1830307184);
        if ((i & 6) == 0) {
            i3 = i | (composerStartRestartGroup.changed(modifier) ? 4 : 2);
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function22) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function23) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function24) ? PKIFailureInfo.unsupportedVersion : PKIFailureInfo.notAuthorized;
        }
        if ((1572864 & i) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function25) ? PKIFailureInfo.badCertTemplate : PKIFailureInfo.signerNotTrusted;
        }
        if ((12582912 & i) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function26) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 67108864 : 33554432;
        }
        if ((805306368 & i) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? PKIFailureInfo.duplicateCertReq : 268435456;
        }
        int i6 = i3;
        if ((i2 & 6) == 0) {
            i4 = i2 | (composerStartRestartGroup.changedInstance(function27) ? 4 : 2);
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function28) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            paddingValues2 = paddingValues;
            i4 |= composerStartRestartGroup.changed(paddingValues2) ? 256 : 128;
        } else {
            paddingValues2 = paddingValues;
        }
        int i7 = i4;
        if ((i6 & 306783379) == 306783378 && (i7 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1830307184, i6, i7, "androidx.compose.material3.TextFieldLayout (TextField.kt:453)");
            }
            boolean z2 = ((1879048192 & i6) == 536870912) | ((234881024 & i6) == 67108864) | ((i7 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new TextFieldMeasurePolicy(z, f, paddingValues2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            TextFieldMeasurePolicy textFieldMeasurePolicy = (TextFieldMeasurePolicy) objRememberedValue;
            LayoutDirection layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
            ComposeUiNode.Companion companion = ComposeUiNode.Companion;
            Function0 constructor = companion.getConstructor();
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
            Updater.m1036setimpl(composerM1035constructorimpl, textFieldMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM1035constructorimpl.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM1035constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM1035constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m1036setimpl(composerM1035constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            function27.invoke(composerStartRestartGroup, Integer.valueOf(i7 & 14));
            composerStartRestartGroup.startReplaceGroup(1341517187);
            if (function23 != null) {
                Modifier modifierThen = LayoutIdKt.layoutId(Modifier.Companion, "Leading").then(TextFieldImplKt.getIconDefaultSizeModifier());
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getCenter(), false);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierThen);
                Function0 constructor2 = companion.getConstructor();
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM1035constructorimpl2 = Updater.m1035constructorimpl(composerStartRestartGroup);
                Updater.m1036setimpl(composerM1035constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl2, currentCompositionLocalMap2, companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash2 = companion.getSetCompositeKeyHash();
                if (composerM1035constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM1035constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM1035constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m1036setimpl(composerM1035constructorimpl2, modifierMaterializeModifier2, companion.getSetModifier());
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                function23.invoke(composerStartRestartGroup, Integer.valueOf((i6 >> 12) & 14));
                composerStartRestartGroup.endNode();
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(1341526310);
            if (function24 != null) {
                Modifier modifierThen2 = LayoutIdKt.layoutId(Modifier.Companion, "Trailing").then(TextFieldImplKt.getIconDefaultSizeModifier());
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getCenter(), false);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierThen2);
                Function0 constructor3 = companion.getConstructor();
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM1035constructorimpl3 = Updater.m1035constructorimpl(composerStartRestartGroup);
                Updater.m1036setimpl(composerM1035constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, companion.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl3, currentCompositionLocalMap3, companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash3 = companion.getSetCompositeKeyHash();
                if (composerM1035constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composerM1035constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM1035constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m1036setimpl(composerM1035constructorimpl3, modifierMaterializeModifier3, companion.getSetModifier());
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                function24.invoke(composerStartRestartGroup, Integer.valueOf((i6 >> 15) & 14));
                composerStartRestartGroup.endNode();
            }
            composerStartRestartGroup.endReplaceGroup();
            float fCalculateStartPadding = PaddingKt.calculateStartPadding(paddingValues2, layoutDirection);
            float fCalculateEndPadding = PaddingKt.calculateEndPadding(paddingValues2, layoutDirection);
            if (function23 != null) {
                i5 = 0;
                fCalculateStartPadding = Dp.m2416constructorimpl(RangesKt.coerceAtLeast(Dp.m2416constructorimpl(fCalculateStartPadding - TextFieldImplKt.getHorizontalIconPadding()), Dp.m2416constructorimpl(0)));
            } else {
                i5 = 0;
            }
            if (function24 != null) {
                fCalculateEndPadding = Dp.m2416constructorimpl(RangesKt.coerceAtLeast(Dp.m2416constructorimpl(fCalculateEndPadding - TextFieldImplKt.getHorizontalIconPadding()), Dp.m2416constructorimpl(i5)));
            }
            composerStartRestartGroup.startReplaceGroup(1341556924);
            if (function25 != null) {
                Modifier modifierM265paddingqDBjuR0$default = PaddingKt.m265paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m277heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.Companion, "Prefix"), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), fCalculateStartPadding, 0.0f, TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, 10, null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM265paddingqDBjuR0$default);
                Function0 constructor4 = companion.getConstructor();
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor4);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM1035constructorimpl4 = Updater.m1035constructorimpl(composerStartRestartGroup);
                Updater.m1036setimpl(composerM1035constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy3, companion.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl4, currentCompositionLocalMap4, companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash4 = companion.getSetCompositeKeyHash();
                if (composerM1035constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    composerM1035constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composerM1035constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.m1036setimpl(composerM1035constructorimpl4, modifierMaterializeModifier4, companion.getSetModifier());
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                function25.invoke(composerStartRestartGroup, Integer.valueOf((i6 >> 18) & 14));
                composerStartRestartGroup.endNode();
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(1341568890);
            if (function26 != null) {
                Modifier modifierM265paddingqDBjuR0$default2 = PaddingKt.m265paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m277heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.Companion, "Suffix"), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, fCalculateEndPadding, 0.0f, 10, null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM265paddingqDBjuR0$default2);
                Function0 constructor5 = companion.getConstructor();
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor5);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM1035constructorimpl5 = Updater.m1035constructorimpl(composerStartRestartGroup);
                Updater.m1036setimpl(composerM1035constructorimpl5, measurePolicyMaybeCachedBoxMeasurePolicy4, companion.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl5, currentCompositionLocalMap5, companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash5 = companion.getSetCompositeKeyHash();
                if (composerM1035constructorimpl5.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                    composerM1035constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                    composerM1035constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
                }
                Updater.m1036setimpl(composerM1035constructorimpl5, modifierMaterializeModifier5, companion.getSetModifier());
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                function26.invoke(composerStartRestartGroup, Integer.valueOf((i6 >> 21) & 14));
                composerStartRestartGroup.endNode();
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(1341581092);
            if (function22 != null) {
                Modifier modifierM265paddingqDBjuR0$default3 = PaddingKt.m265paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m277heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.Companion, "Label"), DpKt.m2428lerpMdfbLM(TextFieldImplKt.getMinTextLineHeight(), TextFieldImplKt.getMinFocusedLabelLineHeight(), f), 0.0f, 2, null), null, false, 3, null), fCalculateStartPadding, 0.0f, fCalculateEndPadding, 0.0f, 10, null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM265paddingqDBjuR0$default3);
                Function0 constructor6 = companion.getConstructor();
                f2 = fCalculateStartPadding;
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor6);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM1035constructorimpl6 = Updater.m1035constructorimpl(composerStartRestartGroup);
                Updater.m1036setimpl(composerM1035constructorimpl6, measurePolicyMaybeCachedBoxMeasurePolicy5, companion.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl6, currentCompositionLocalMap6, companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash6 = companion.getSetCompositeKeyHash();
                if (composerM1035constructorimpl6.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                    composerM1035constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                    composerM1035constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
                }
                Updater.m1036setimpl(composerM1035constructorimpl6, modifierMaterializeModifier6, companion.getSetModifier());
                BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                function22.invoke(composerStartRestartGroup, Integer.valueOf((i6 >> 6) & 14));
                composerStartRestartGroup.endNode();
            } else {
                f2 = fCalculateStartPadding;
            }
            composerStartRestartGroup.endReplaceGroup();
            Modifier.Companion companion2 = Modifier.Companion;
            Modifier modifierWrapContentHeight$default = SizeKt.wrapContentHeight$default(SizeKt.m277heightInVpY3zN4$default(companion2, TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null);
            float fM2416constructorimpl = function25 == null ? f2 : Dp.m2416constructorimpl(0);
            if (function26 != null) {
                fCalculateEndPadding = Dp.m2416constructorimpl(0);
            }
            Modifier modifierM265paddingqDBjuR0$default4 = PaddingKt.m265paddingqDBjuR0$default(modifierWrapContentHeight$default, fM2416constructorimpl, 0.0f, fCalculateEndPadding, 0.0f, 10, null);
            composerStartRestartGroup.startReplaceGroup(1341611627);
            if (function3 != null) {
                function3.invoke(LayoutIdKt.layoutId(companion2, "Hint").then(modifierM265paddingqDBjuR0$default4), composerStartRestartGroup, Integer.valueOf((i6 >> 6) & 112));
            }
            composerStartRestartGroup.endReplaceGroup();
            Modifier modifierThen3 = LayoutIdKt.layoutId(companion2, "TextField").then(modifierM265paddingqDBjuR0$default4);
            Alignment.Companion companion3 = Alignment.Companion;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), true);
            int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierThen3);
            Function0 constructor7 = companion.getConstructor();
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor7);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM1035constructorimpl7 = Updater.m1035constructorimpl(composerStartRestartGroup);
            Updater.m1036setimpl(composerM1035constructorimpl7, measurePolicyMaybeCachedBoxMeasurePolicy6, companion.getSetMeasurePolicy());
            Updater.m1036setimpl(composerM1035constructorimpl7, currentCompositionLocalMap7, companion.getSetResolvedCompositionLocals());
            Function2 setCompositeKeyHash7 = companion.getSetCompositeKeyHash();
            if (composerM1035constructorimpl7.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                composerM1035constructorimpl7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                composerM1035constructorimpl7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
            }
            Updater.m1036setimpl(composerM1035constructorimpl7, modifierMaterializeModifier7, companion.getSetModifier());
            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
            function2.invoke(composerStartRestartGroup, Integer.valueOf((i6 >> 3) & 14));
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.startReplaceGroup(1341622624);
            if (function28 != null) {
                Modifier modifierPadding = PaddingKt.padding(SizeKt.wrapContentHeight$default(SizeKt.m277heightInVpY3zN4$default(LayoutIdKt.layoutId(companion2, "Supporting"), TextFieldImplKt.getMinSupportingTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldDefaults.m758supportingTextPaddinga9UjIt4$material3_release$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null));
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(companion3.getTopStart(), false);
                int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPadding);
                Function0 constructor8 = companion.getConstructor();
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor8);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM1035constructorimpl8 = Updater.m1035constructorimpl(composerStartRestartGroup);
                Updater.m1036setimpl(composerM1035constructorimpl8, measurePolicyMaybeCachedBoxMeasurePolicy7, companion.getSetMeasurePolicy());
                Updater.m1036setimpl(composerM1035constructorimpl8, currentCompositionLocalMap8, companion.getSetResolvedCompositionLocals());
                Function2 setCompositeKeyHash8 = companion.getSetCompositeKeyHash();
                if (composerM1035constructorimpl8.getInserting() || !Intrinsics.areEqual(composerM1035constructorimpl8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                    composerM1035constructorimpl8.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash8));
                    composerM1035constructorimpl8.apply(Integer.valueOf(currentCompositeKeyHash8), setCompositeKeyHash8);
                }
                Updater.m1036setimpl(composerM1035constructorimpl8, modifierMaterializeModifier8, companion.getSetModifier());
                function28.invoke(composerStartRestartGroup, Integer.valueOf((i7 >> 3) & 14));
                composerStartRestartGroup.endNode();
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextFieldKt.TextFieldLayout.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i8) {
                    TextFieldKt.TextFieldLayout(modifier, function2, function22, function3, function23, function24, function25, function26, z, f, function27, function28, paddingValues, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateWidth-yeHjK3Y, reason: not valid java name */
    public static final int m771calculateWidthyeHjK3Y(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j) {
        int i8 = i3 + i4;
        return Math.max(i + Math.max(i5 + i8, Math.max(i7 + i8, i6)) + i2, Constraints.m2391getMinWidthimpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateHeight-mKXJcVc, reason: not valid java name */
    public static final int m770calculateHeightmKXJcVc(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f, long j, float f2, PaddingValues paddingValues) {
        boolean z = i2 > 0;
        float fM2416constructorimpl = Dp.m2416constructorimpl(paddingValues.mo255calculateTopPaddingD9Ej5fM() + paddingValues.mo252calculateBottomPaddingD9Ej5fM()) * f2;
        if (z) {
            fM2416constructorimpl = MathHelpersKt.lerp(Dp.m2416constructorimpl(TextFieldImplKt.getTextFieldPadding() * 2) * f2, fM2416constructorimpl, f);
        }
        return Math.max(Constraints.m2390getMinHeightimpl(j), Math.max(i3, Math.max(i4, MathKt.roundToInt(fM2416constructorimpl + MathHelpersKt.lerp(0, i2, f) + ComparisonsKt.maxOf(i, i7, i5, i6, MathHelpersKt.lerp(i2, 0, f))))) + i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeWithLabel(Placeable.PlacementScope placementScope, int i, int i2, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, Placeable placeable7, Placeable placeable8, Placeable placeable9, boolean z, int i3, int i4, float f, float f2) {
        int iRoundToInt;
        Placeable.PlacementScope.m1765place70tqf50$default(placementScope, placeable8, IntOffset.Companion.m2464getZeronOccac(), 0.0f, 2, null);
        int iHeightOrZero = i2 - TextFieldImplKt.heightOrZero(placeable9);
        if (placeable4 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, 0, Alignment.Companion.getCenterVertically().align(placeable4.getHeight(), iHeightOrZero), 0.0f, 4, null);
        }
        if (placeable2 != null) {
            if (z) {
                iRoundToInt = Alignment.Companion.getCenterVertically().align(placeable2.getHeight(), iHeightOrZero);
            } else {
                iRoundToInt = MathKt.roundToInt(TextFieldImplKt.getTextFieldPadding() * f2);
            }
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, TextFieldImplKt.widthOrZero(placeable4), iRoundToInt - MathKt.roundToInt((iRoundToInt - i3) * f), 0.0f, 4, null);
        }
        if (placeable6 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable6, TextFieldImplKt.widthOrZero(placeable4), i4, 0.0f, 4, null);
        }
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeable4) + TextFieldImplKt.widthOrZero(placeable6);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, iWidthOrZero, i4, 0.0f, 4, null);
        if (placeable3 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, iWidthOrZero, i4, 0.0f, 4, null);
        }
        if (placeable7 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable7, (i - TextFieldImplKt.widthOrZero(placeable5)) - placeable7.getWidth(), i4, 0.0f, 4, null);
        }
        if (placeable5 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, i - placeable5.getWidth(), Alignment.Companion.getCenterVertically().align(placeable5.getHeight(), iHeightOrZero), 0.0f, 4, null);
        }
        if (placeable9 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable9, 0, iHeightOrZero, 0.0f, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeWithoutLabel(Placeable.PlacementScope placementScope, int i, int i2, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, Placeable placeable7, Placeable placeable8, boolean z, float f, PaddingValues paddingValues) {
        Placeable.PlacementScope.m1765place70tqf50$default(placementScope, placeable7, IntOffset.Companion.m2464getZeronOccac(), 0.0f, 2, null);
        int iHeightOrZero = i2 - TextFieldImplKt.heightOrZero(placeable8);
        int iRoundToInt = MathKt.roundToInt(paddingValues.mo255calculateTopPaddingD9Ej5fM() * f);
        if (placeable3 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, 0, Alignment.Companion.getCenterVertically().align(placeable3.getHeight(), iHeightOrZero), 0.0f, 4, null);
        }
        if (placeable5 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, TextFieldImplKt.widthOrZero(placeable3), placeWithoutLabel$calculateVerticalPosition(z, iHeightOrZero, iRoundToInt, placeable5), 0.0f, 4, null);
        }
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeable3) + TextFieldImplKt.widthOrZero(placeable5);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, iWidthOrZero, placeWithoutLabel$calculateVerticalPosition(z, iHeightOrZero, iRoundToInt, placeable), 0.0f, 4, null);
        if (placeable2 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, iWidthOrZero, placeWithoutLabel$calculateVerticalPosition(z, iHeightOrZero, iRoundToInt, placeable2), 0.0f, 4, null);
        }
        if (placeable6 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable6, (i - TextFieldImplKt.widthOrZero(placeable4)) - placeable6.getWidth(), placeWithoutLabel$calculateVerticalPosition(z, iHeightOrZero, iRoundToInt, placeable6), 0.0f, 4, null);
        }
        if (placeable4 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, i - placeable4.getWidth(), Alignment.Companion.getCenterVertically().align(placeable4.getHeight(), iHeightOrZero), 0.0f, 4, null);
        }
        if (placeable8 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable8, 0, iHeightOrZero, 0.0f, 4, null);
        }
    }

    private static final int placeWithoutLabel$calculateVerticalPosition(boolean z, int i, int i2, Placeable placeable) {
        return z ? Alignment.Companion.getCenterVertically().align(placeable.getHeight(), i) : i2;
    }

    public static final Modifier drawIndicatorLine(Modifier modifier, final State state) {
        return DrawModifierKt.drawWithContent(modifier, new Function1() { // from class: androidx.compose.material3.TextFieldKt.drawIndicatorLine.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((ContentDrawScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(ContentDrawScope contentDrawScope) {
                contentDrawScope.drawContent();
                float fMo210toPx0680j_4 = contentDrawScope.mo210toPx0680j_4(((BorderStroke) state.getValue()).m110getWidthD9Ej5fM());
                float fM1194getHeightimpl = Size.m1194getHeightimpl(contentDrawScope.mo1483getSizeNHjbRc()) - (fMo210toPx0680j_4 / 2);
                DrawScope.CC.m1502drawLine1RTmtNc$default(contentDrawScope, ((BorderStroke) state.getValue()).getBrush(), OffsetKt.Offset(0.0f, fM1194getHeightimpl), OffsetKt.Offset(Size.m1196getWidthimpl(contentDrawScope.mo1483getSizeNHjbRc()), fM1194getHeightimpl), fMo210toPx0680j_4, 0, null, 0.0f, null, 0, 496, null);
            }
        });
    }

    public static final float getTextFieldWithLabelVerticalPadding() {
        return TextFieldWithLabelVerticalPadding;
    }
}
