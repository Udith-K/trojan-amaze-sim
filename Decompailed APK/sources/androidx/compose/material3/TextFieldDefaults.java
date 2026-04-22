package androidx.compose.material3;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.tokens.FilledTextFieldTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextFieldDefaults {
    private static final float FocusedBorderThickness;
    private static final float FocusedIndicatorThickness;
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    private static final float MinHeight = Dp.m2416constructorimpl(56);
    private static final float MinWidth = Dp.m2416constructorimpl(280);
    private static final float UnfocusedBorderThickness;
    private static final float UnfocusedIndicatorThickness;

    private TextFieldDefaults() {
    }

    public final Shape getShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1941327459, i, -1, "androidx.compose.material3.TextFieldDefaults.<get-shape> (TextFieldDefaults.kt:60)");
        }
        Shape value = ShapesKt.getValue(FilledTextFieldTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m763getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m764getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* JADX INFO: renamed from: getUnfocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m765getUnfocusedIndicatorThicknessD9Ej5fM() {
        return UnfocusedIndicatorThickness;
    }

    /* JADX INFO: renamed from: getFocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m762getFocusedIndicatorThicknessD9Ej5fM() {
        return FocusedIndicatorThickness;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:151:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00ed  */
    /* JADX INFO: renamed from: Container-4EFweAY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m759Container4EFweAY(final boolean r26, final boolean r27, final androidx.compose.foundation.interaction.InteractionSource r28, androidx.compose.ui.Modifier r29, androidx.compose.material3.TextFieldColors r30, androidx.compose.ui.graphics.Shape r31, float r32, float r33, androidx.compose.runtime.Composer r34, final int r35, final int r36) {
        /*
            Method dump skipped, instruction units count: 559
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.m759Container4EFweAY(boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.ui.Modifier, androidx.compose.material3.TextFieldColors, androidx.compose.ui.graphics.Shape, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI, reason: not valid java name */
    public final Modifier m766indicatorLinegv0btCI(Modifier modifier, final boolean z, final boolean z2, final InteractionSource interactionSource, final TextFieldColors textFieldColors, final float f, final float f2) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1() { // from class: androidx.compose.material3.TextFieldDefaults$indicatorLine-gv0btCI$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                invoke((InspectorInfo) null);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                throw null;
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.material3.TextFieldDefaults$indicatorLine$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
            }

            public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                composer.startReplaceGroup(-891038934);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-891038934, i, -1, "androidx.compose.material3.TextFieldDefaults.indicatorLine.<anonymous> (TextFieldDefaults.kt:169)");
                }
                Modifier modifierDrawIndicatorLine = TextFieldKt.drawIndicatorLine(Modifier.Companion, TextFieldImplKt.m789animateBorderStrokeAsStateNuRrP5Q(z, z2, ((Boolean) FocusInteractionKt.collectIsFocusedAsState(interactionSource, composer, 0).getValue()).booleanValue(), textFieldColors, f, f2, composer, 0));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                return modifierDrawIndicatorLine;
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:278:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void DecorationBox(final java.lang.String r43, final kotlin.jvm.functions.Function2 r44, final boolean r45, final boolean r46, final androidx.compose.ui.text.input.VisualTransformation r47, final androidx.compose.foundation.interaction.InteractionSource r48, boolean r49, kotlin.jvm.functions.Function2 r50, kotlin.jvm.functions.Function2 r51, kotlin.jvm.functions.Function2 r52, kotlin.jvm.functions.Function2 r53, kotlin.jvm.functions.Function2 r54, kotlin.jvm.functions.Function2 r55, kotlin.jvm.functions.Function2 r56, androidx.compose.ui.graphics.Shape r57, androidx.compose.material3.TextFieldColors r58, androidx.compose.foundation.layout.PaddingValues r59, kotlin.jvm.functions.Function2 r60, androidx.compose.runtime.Composer r61, final int r62, final int r63, final int r64) {
        /*
            Method dump skipped, instruction units count: 1160
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldDefaults.DecorationBox(java.lang.String, kotlin.jvm.functions.Function2, boolean, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.InteractionSource, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: renamed from: contentPaddingWithLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m756contentPaddingWithLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        return textFieldDefaults.m760contentPaddingWithLabela9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: contentPaddingWithLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m760contentPaddingWithLabela9UjIt4(float f, float f2, float f3, float f4) {
        return PaddingKt.m260PaddingValuesa9UjIt4(f, f3, f2, f4);
    }

    /* JADX INFO: renamed from: contentPaddingWithoutLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m757contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m761contentPaddingWithoutLabela9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: contentPaddingWithoutLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m761contentPaddingWithoutLabela9UjIt4(float f, float f2, float f3, float f4) {
        return PaddingKt.m260PaddingValuesa9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: supportingTextPadding-a9UjIt4$material3_release$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m758supportingTextPaddinga9UjIt4$material3_release$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getSupportingTopPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = Dp.m2416constructorimpl(0);
        }
        return textFieldDefaults.m767supportingTextPaddinga9UjIt4$material3_release(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: supportingTextPadding-a9UjIt4$material3_release, reason: not valid java name */
    public final PaddingValues m767supportingTextPaddinga9UjIt4$material3_release(float f, float f2, float f3, float f4) {
        return PaddingKt.m260PaddingValuesa9UjIt4(f, f2, f3, f4);
    }

    public final TextFieldColors colors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(831731228, i, -1, "androidx.compose.material3.TextFieldDefaults.colors (TextFieldDefaults.kt:336)");
        }
        TextFieldColors defaultTextFieldColors = getDefaultTextFieldColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultTextFieldColors;
    }

    public final TextFieldColors getDefaultTextFieldColors(ColorScheme colorScheme, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1341970309, i, -1, "androidx.compose.material3.TextFieldDefaults.<get-defaultTextFieldColors> (TextFieldDefaults.kt:486)");
        }
        TextFieldColors defaultTextFieldColorsCached$material3_release = colorScheme.getDefaultTextFieldColorsCached$material3_release();
        composer.startReplaceGroup(27085453);
        if (defaultTextFieldColorsCached$material3_release == null) {
            FilledTextFieldTokens filledTextFieldTokens = FilledTextFieldTokens.INSTANCE;
            defaultTextFieldColorsCached$material3_release = new TextFieldColors(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusInputColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledInputColor()), filledTextFieldTokens.getDisabledInputOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorInputColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getContainerColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getCaretColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorFocusCaretColor()), (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusActiveIndicatorColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getActiveIndicatorColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledActiveIndicatorColor()), filledTextFieldTokens.getDisabledActiveIndicatorOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorActiveIndicatorColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getLeadingIconColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledLeadingIconColor()), filledTextFieldTokens.getDisabledLeadingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusTrailingIconColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getTrailingIconColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledTrailingIconColor()), filledTextFieldTokens.getDisabledTrailingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorTrailingIconColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusLabelColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getLabelColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledLabelColor()), filledTextFieldTokens.getDisabledLabelOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorLabelColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPlaceholderColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPlaceholderColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledInputColor()), filledTextFieldTokens.getDisabledInputOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPlaceholderColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getFocusSupportingColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getSupportingColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getDisabledSupportingColor()), filledTextFieldTokens.getDisabledSupportingOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getErrorSupportingColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPrefixColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPrefixColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPrefixColor()), filledTextFieldTokens.getDisabledInputOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputPrefixColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputSuffixColor()), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputSuffixColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputSuffixColor()), filledTextFieldTokens.getDisabledInputOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledTextFieldTokens.getInputSuffixColor()), null);
            colorScheme.setDefaultTextFieldColorsCached$material3_release(defaultTextFieldColorsCached$material3_release);
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultTextFieldColorsCached$material3_release;
    }

    static {
        float fM2416constructorimpl = Dp.m2416constructorimpl(1);
        UnfocusedIndicatorThickness = fM2416constructorimpl;
        float fM2416constructorimpl2 = Dp.m2416constructorimpl(2);
        FocusedIndicatorThickness = fM2416constructorimpl2;
        UnfocusedBorderThickness = fM2416constructorimpl;
        FocusedBorderThickness = fM2416constructorimpl2;
    }
}
