package androidx.compose.ui.unit;

import androidx.compose.ui.unit.fontscaling.FontScaleConverter;
import androidx.compose.ui.unit.fontscaling.FontScaleConverterFactory;

/* JADX INFO: compiled from: FontScaling.android.kt */
/* JADX INFO: loaded from: classes.dex */
public interface FontScaling {
    float getFontScale();

    /* JADX INFO: renamed from: toDp-GaN1DYA */
    float mo205toDpGaN1DYA(long j);

    /* JADX INFO: renamed from: toSp-0xMU5do */
    long mo212toSp0xMU5do(float f);

    /* JADX INFO: renamed from: androidx.compose.ui.unit.FontScaling$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: FontScaling.android.kt */
    public abstract /* synthetic */ class CC {
        /* JADX INFO: renamed from: $default$toSp-0xMU5do, reason: not valid java name */
        public static long m2450$default$toSp0xMU5do(FontScaling fontScaling, float f) {
            FontScaleConverterFactory fontScaleConverterFactory = FontScaleConverterFactory.INSTANCE;
            if (!fontScaleConverterFactory.isNonLinearFontScalingActive(fontScaling.getFontScale())) {
                return TextUnitKt.getSp(f / fontScaling.getFontScale());
            }
            FontScaleConverter fontScaleConverterForScale = fontScaleConverterFactory.forScale(fontScaling.getFontScale());
            return TextUnitKt.getSp(fontScaleConverterForScale != null ? fontScaleConverterForScale.convertDpToSp(f) : f / fontScaling.getFontScale());
        }

        /* JADX INFO: renamed from: $default$toDp-GaN1DYA, reason: not valid java name */
        public static float m2449$default$toDpGaN1DYA(FontScaling fontScaling, long j) {
            if (!TextUnitType.m2503equalsimpl0(TextUnit.m2489getTypeUIouoOA(j), TextUnitType.Companion.m2508getSpUIouoOA())) {
                InlineClassHelperKt.throwIllegalStateException("Only Sp can convert to Px");
            }
            FontScaleConverterFactory fontScaleConverterFactory = FontScaleConverterFactory.INSTANCE;
            if (!fontScaleConverterFactory.isNonLinearFontScalingActive(fontScaling.getFontScale())) {
                return Dp.m2416constructorimpl(TextUnit.m2490getValueimpl(j) * fontScaling.getFontScale());
            }
            FontScaleConverter fontScaleConverterForScale = fontScaleConverterFactory.forScale(fontScaling.getFontScale());
            return fontScaleConverterForScale == null ? Dp.m2416constructorimpl(TextUnit.m2490getValueimpl(j) * fontScaling.getFontScale()) : Dp.m2416constructorimpl(fontScaleConverterForScale.convertSpToDp(TextUnit.m2490getValueimpl(j)));
        }
    }
}
