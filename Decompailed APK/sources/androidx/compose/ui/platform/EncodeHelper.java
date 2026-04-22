package androidx.compose.ui.platform;

import android.os.Parcel;
import android.util.Base64;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;

/* JADX INFO: compiled from: AndroidClipboardManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class EncodeHelper {
    private Parcel parcel = Parcel.obtain();

    public final void reset() {
        this.parcel.recycle();
        this.parcel = Parcel.obtain();
    }

    public final String encodedString() {
        return Base64.encodeToString(this.parcel.marshall(), 0);
    }

    public final void encode(SpanStyle spanStyle) {
        long jM2087getColor0d7_KjU = spanStyle.m2087getColor0d7_KjU();
        Color.Companion companion = Color.Companion;
        if (!Color.m1296equalsimpl0(jM2087getColor0d7_KjU, companion.m1309getUnspecified0d7_KjU())) {
            encode((byte) 1);
            m1966encode8_81llA(spanStyle.m2087getColor0d7_KjU());
        }
        long jM2088getFontSizeXSAIIZE = spanStyle.m2088getFontSizeXSAIIZE();
        TextUnit.Companion companion2 = TextUnit.Companion;
        if (!TextUnit.m2487equalsimpl0(jM2088getFontSizeXSAIIZE, companion2.m2494getUnspecifiedXSAIIZE())) {
            encode((byte) 2);
            m1963encodeR2X_6o(spanStyle.m2088getFontSizeXSAIIZE());
        }
        FontWeight fontWeight = spanStyle.getFontWeight();
        if (fontWeight != null) {
            encode((byte) 3);
            encode(fontWeight);
        }
        FontStyle fontStyleM2089getFontStyle4Lr2A7w = spanStyle.m2089getFontStyle4Lr2A7w();
        if (fontStyleM2089getFontStyle4Lr2A7w != null) {
            int iM2158unboximpl = fontStyleM2089getFontStyle4Lr2A7w.m2158unboximpl();
            encode((byte) 4);
            m1968encodenzbMABs(iM2158unboximpl);
        }
        FontSynthesis fontSynthesisM2090getFontSynthesisZQGJjVo = spanStyle.m2090getFontSynthesisZQGJjVo();
        if (fontSynthesisM2090getFontSynthesisZQGJjVo != null) {
            int iM2167unboximpl = fontSynthesisM2090getFontSynthesisZQGJjVo.m2167unboximpl();
            encode((byte) 5);
            m1965encode6p3vJLY(iM2167unboximpl);
        }
        String fontFeatureSettings = spanStyle.getFontFeatureSettings();
        if (fontFeatureSettings != null) {
            encode((byte) 6);
            encode(fontFeatureSettings);
        }
        if (!TextUnit.m2487equalsimpl0(spanStyle.m2091getLetterSpacingXSAIIZE(), companion2.m2494getUnspecifiedXSAIIZE())) {
            encode((byte) 7);
            m1963encodeR2X_6o(spanStyle.m2091getLetterSpacingXSAIIZE());
        }
        BaselineShift baselineShiftM2086getBaselineShift5SSeXJ0 = spanStyle.m2086getBaselineShift5SSeXJ0();
        if (baselineShiftM2086getBaselineShift5SSeXJ0 != null) {
            float fM2273unboximpl = baselineShiftM2086getBaselineShift5SSeXJ0.m2273unboximpl();
            encode((byte) 8);
            m1964encode4Dl_Bck(fM2273unboximpl);
        }
        TextGeometricTransform textGeometricTransform = spanStyle.getTextGeometricTransform();
        if (textGeometricTransform != null) {
            encode((byte) 9);
            encode(textGeometricTransform);
        }
        if (!Color.m1296equalsimpl0(spanStyle.m2085getBackground0d7_KjU(), companion.m1309getUnspecified0d7_KjU())) {
            encode((byte) 10);
            m1966encode8_81llA(spanStyle.m2085getBackground0d7_KjU());
        }
        TextDecoration textDecoration = spanStyle.getTextDecoration();
        if (textDecoration != null) {
            encode((byte) 11);
            encode(textDecoration);
        }
        Shadow shadow = spanStyle.getShadow();
        if (shadow != null) {
            encode((byte) 12);
            encode(shadow);
        }
    }

    /* JADX INFO: renamed from: encode-8_81llA, reason: not valid java name */
    public final void m1966encode8_81llA(long j) {
        m1967encodeVKZWuLQ(j);
    }

    /* JADX INFO: renamed from: encode--R2X_6o, reason: not valid java name */
    public final void m1963encodeR2X_6o(long j) {
        long jM2489getTypeUIouoOA = TextUnit.m2489getTypeUIouoOA(j);
        TextUnitType.Companion companion = TextUnitType.Companion;
        byte b = 0;
        if (!TextUnitType.m2503equalsimpl0(jM2489getTypeUIouoOA, companion.m2509getUnspecifiedUIouoOA())) {
            if (TextUnitType.m2503equalsimpl0(jM2489getTypeUIouoOA, companion.m2508getSpUIouoOA())) {
                b = 1;
            } else if (TextUnitType.m2503equalsimpl0(jM2489getTypeUIouoOA, companion.m2507getEmUIouoOA())) {
                b = 2;
            }
        }
        encode(b);
        if (TextUnitType.m2503equalsimpl0(TextUnit.m2489getTypeUIouoOA(j), companion.m2509getUnspecifiedUIouoOA())) {
            return;
        }
        encode(TextUnit.m2490getValueimpl(j));
    }

    public final void encode(FontWeight fontWeight) {
        encode(fontWeight.getWeight());
    }

    /* JADX INFO: renamed from: encode-nzbMABs, reason: not valid java name */
    public final void m1968encodenzbMABs(int i) {
        FontStyle.Companion companion = FontStyle.Companion;
        byte b = 0;
        if (!FontStyle.m2155equalsimpl0(i, companion.m2160getNormal_LCdwA()) && FontStyle.m2155equalsimpl0(i, companion.m2159getItalic_LCdwA())) {
            b = 1;
        }
        encode(b);
    }

    /* JADX INFO: renamed from: encode-6p3vJLY, reason: not valid java name */
    public final void m1965encode6p3vJLY(int i) {
        FontSynthesis.Companion companion = FontSynthesis.Companion;
        byte b = 0;
        if (!FontSynthesis.m2164equalsimpl0(i, companion.m2169getNoneGVVA2EU())) {
            if (FontSynthesis.m2164equalsimpl0(i, companion.m2168getAllGVVA2EU())) {
                b = 1;
            } else if (FontSynthesis.m2164equalsimpl0(i, companion.m2171getWeightGVVA2EU())) {
                b = 2;
            } else if (FontSynthesis.m2164equalsimpl0(i, companion.m2170getStyleGVVA2EU())) {
                b = 3;
            }
        }
        encode(b);
    }

    /* JADX INFO: renamed from: encode-4Dl_Bck, reason: not valid java name */
    public final void m1964encode4Dl_Bck(float f) {
        encode(f);
    }

    public final void encode(TextGeometricTransform textGeometricTransform) {
        encode(textGeometricTransform.getScaleX());
        encode(textGeometricTransform.getSkewX());
    }

    public final void encode(TextDecoration textDecoration) {
        encode(textDecoration.getMask());
    }

    public final void encode(Shadow shadow) {
        m1966encode8_81llA(shadow.m1400getColor0d7_KjU());
        encode(Offset.m1159getXimpl(shadow.m1401getOffsetF1C5BW0()));
        encode(Offset.m1160getYimpl(shadow.m1401getOffsetF1C5BW0()));
        encode(shadow.getBlurRadius());
    }

    public final void encode(byte b) {
        this.parcel.writeByte(b);
    }

    public final void encode(int i) {
        this.parcel.writeInt(i);
    }

    public final void encode(float f) {
        this.parcel.writeFloat(f);
    }

    /* JADX INFO: renamed from: encode-VKZWuLQ, reason: not valid java name */
    public final void m1967encodeVKZWuLQ(long j) {
        this.parcel.writeLong(j);
    }

    public final void encode(String str) {
        this.parcel.writeString(str);
    }
}
