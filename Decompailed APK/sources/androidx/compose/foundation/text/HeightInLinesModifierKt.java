package androidx.compose.foundation.text;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: HeightInLinesModifier.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HeightInLinesModifierKt {
    public static final void validateMinMaxLines(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException(("both minLines " + i + " and maxLines " + i2 + " must be greater than zero").toString());
        }
        if (i <= i2) {
            return;
        }
        throw new IllegalArgumentException(("minLines " + i + " must be less than or equal to maxLines " + i2).toString());
    }

    public static final Modifier heightInLines(Modifier modifier, final TextStyle textStyle, final int i, final int i2) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1() { // from class: androidx.compose.foundation.text.HeightInLinesModifierKt$heightInLines$$inlined$debugInspectorInfo$1
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
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.foundation.text.HeightInLinesModifierKt.heightInLines.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
            }

            public final Modifier invoke(Modifier modifier2, Composer composer, int i3) {
                composer.startReplaceGroup(408240218);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(408240218, i3, -1, "androidx.compose.foundation.text.heightInLines.<anonymous> (HeightInLinesModifier.kt:59)");
                }
                HeightInLinesModifierKt.validateMinMaxLines(i, i2);
                if (i == 1 && i2 == Integer.MAX_VALUE) {
                    Modifier.Companion companion = Modifier.Companion;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer.endReplaceGroup();
                    return companion;
                }
                Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
                FontFamily.Resolver resolver = (FontFamily.Resolver) composer.consume(CompositionLocalsKt.getLocalFontFamilyResolver());
                LayoutDirection layoutDirection = (LayoutDirection) composer.consume(CompositionLocalsKt.getLocalLayoutDirection());
                boolean zChanged = composer.changed(textStyle) | composer.changed(layoutDirection);
                TextStyle textStyle2 = textStyle;
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = TextStyleKt.resolveDefaults(textStyle2, layoutDirection);
                    composer.updateRememberedValue(objRememberedValue);
                }
                TextStyle textStyle3 = (TextStyle) objRememberedValue;
                boolean zChanged2 = composer.changed(resolver) | composer.changed(textStyle3);
                Object objRememberedValue2 = composer.rememberedValue();
                if (zChanged2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                    FontFamily fontFamily = textStyle3.getFontFamily();
                    FontWeight fontWeight = textStyle3.getFontWeight();
                    if (fontWeight == null) {
                        fontWeight = FontWeight.Companion.getNormal();
                    }
                    FontStyle fontStyleM2132getFontStyle4Lr2A7w = textStyle3.m2132getFontStyle4Lr2A7w();
                    int iM2158unboximpl = fontStyleM2132getFontStyle4Lr2A7w != null ? fontStyleM2132getFontStyle4Lr2A7w.m2158unboximpl() : FontStyle.Companion.m2160getNormal_LCdwA();
                    FontSynthesis fontSynthesisM2133getFontSynthesisZQGJjVo = textStyle3.m2133getFontSynthesisZQGJjVo();
                    objRememberedValue2 = resolver.mo2150resolveDPcqOEQ(fontFamily, fontWeight, iM2158unboximpl, fontSynthesisM2133getFontSynthesisZQGJjVo != null ? fontSynthesisM2133getFontSynthesisZQGJjVo.m2167unboximpl() : FontSynthesis.Companion.m2168getAllGVVA2EU());
                    composer.updateRememberedValue(objRememberedValue2);
                }
                State state = (State) objRememberedValue2;
                boolean zChanged3 = composer.changed(state.getValue()) | composer.changed(density) | composer.changed(resolver) | composer.changed(textStyle) | composer.changed(layoutDirection);
                Object objRememberedValue3 = composer.rememberedValue();
                if (zChanged3 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                    objRememberedValue3 = Integer.valueOf(IntSize.m2475getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText(textStyle3, density, resolver, TextFieldDelegateKt.getEmptyTextReplacement(), 1)));
                    composer.updateRememberedValue(objRememberedValue3);
                }
                int iIntValue = ((Number) objRememberedValue3).intValue();
                boolean zChanged4 = composer.changed(layoutDirection) | composer.changed(density) | composer.changed(resolver) | composer.changed(textStyle) | composer.changed(state.getValue());
                Object objRememberedValue4 = composer.rememberedValue();
                if (zChanged4 || objRememberedValue4 == Composer.Companion.getEmpty()) {
                    objRememberedValue4 = Integer.valueOf(IntSize.m2475getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText(textStyle3, density, resolver, TextFieldDelegateKt.getEmptyTextReplacement() + '\n' + TextFieldDelegateKt.getEmptyTextReplacement(), 2)));
                    composer.updateRememberedValue(objRememberedValue4);
                }
                int iIntValue2 = ((Number) objRememberedValue4).intValue() - iIntValue;
                int i4 = i;
                Integer numValueOf = i4 == 1 ? null : Integer.valueOf(((i4 - 1) * iIntValue2) + iIntValue);
                int i5 = i2;
                Integer numValueOf2 = i5 != Integer.MAX_VALUE ? Integer.valueOf(iIntValue + (iIntValue2 * (i5 - 1))) : null;
                Modifier modifierM276heightInVpY3zN4 = SizeKt.m276heightInVpY3zN4(Modifier.Companion, numValueOf != null ? density.mo207toDpu2uoSUM(numValueOf.intValue()) : Dp.Companion.m2425getUnspecifiedD9Ej5fM(), numValueOf2 != null ? density.mo207toDpu2uoSUM(numValueOf2.intValue()) : Dp.Companion.m2425getUnspecifiedD9Ej5fM());
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceGroup();
                return modifierM276heightInVpY3zN4;
            }
        });
    }
}
