package androidx.compose.ui.platform;

import android.os.Parcel;
import android.util.Base64;
import androidx.compose.ui.geometry.OffsetKt;
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
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import kotlin.ULong;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: AndroidClipboardManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DecodeHelper {
    private final Parcel parcel;

    public DecodeHelper(String str) {
        Parcel parcelObtain = Parcel.obtain();
        this.parcel = parcelObtain;
        byte[] bArrDecode = Base64.decode(str, 0);
        parcelObtain.unmarshall(bArrDecode, 0, bArrDecode.length);
        parcelObtain.setDataPosition(0);
    }

    public final SpanStyle decodeSpanStyle() {
        MutableSpanStyle mutableSpanStyle;
        MutableSpanStyle mutableSpanStyle2 = mutableSpanStyle;
        MutableSpanStyle mutableSpanStyle3 = new MutableSpanStyle(0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, 16383, null);
        while (this.parcel.dataAvail() > 1) {
            byte bDecodeByte = decodeByte();
            if (bDecodeByte != 1) {
                mutableSpanStyle = mutableSpanStyle2;
                if (bDecodeByte == 2) {
                    if (dataAvailable() < 5) {
                        break;
                    }
                    mutableSpanStyle.m1978setFontSizeR2X_6o(m1960decodeTextUnitXSAIIZE());
                    mutableSpanStyle2 = mutableSpanStyle;
                } else if (bDecodeByte == 3) {
                    if (dataAvailable() < 4) {
                        break;
                    }
                    mutableSpanStyle.setFontWeight(decodeFontWeight());
                    mutableSpanStyle2 = mutableSpanStyle;
                } else if (bDecodeByte == 4) {
                    if (dataAvailable() < 1) {
                        break;
                    }
                    mutableSpanStyle.m1979setFontStylemLjRB2g(FontStyle.m2152boximpl(m1958decodeFontStyle_LCdwA()));
                    mutableSpanStyle2 = mutableSpanStyle;
                } else if (bDecodeByte != 5) {
                    if (bDecodeByte != 6) {
                        if (bDecodeByte != 7) {
                            if (bDecodeByte != 8) {
                                if (bDecodeByte != 9) {
                                    if (bDecodeByte != 10) {
                                        if (bDecodeByte != 11) {
                                            if (bDecodeByte == 12) {
                                                if (dataAvailable() < 20) {
                                                    break;
                                                }
                                                mutableSpanStyle.setShadow(decodeShadow());
                                            }
                                        } else {
                                            if (dataAvailable() < 4) {
                                                break;
                                            }
                                            mutableSpanStyle.setTextDecoration(decodeTextDecoration());
                                        }
                                    } else {
                                        if (dataAvailable() < 8) {
                                            break;
                                        }
                                        mutableSpanStyle.m1975setBackground8_81llA(m1957decodeColor0d7_KjU());
                                    }
                                } else {
                                    if (dataAvailable() < 8) {
                                        break;
                                    }
                                    mutableSpanStyle.setTextGeometricTransform(decodeTextGeometricTransform());
                                }
                            } else {
                                if (dataAvailable() < 4) {
                                    break;
                                }
                                mutableSpanStyle.m1976setBaselineShift_isdbwI(BaselineShift.m2267boximpl(m1955decodeBaselineShifty9eOQZs()));
                            }
                        } else {
                            if (dataAvailable() < 5) {
                                break;
                            }
                            mutableSpanStyle.m1981setLetterSpacingR2X_6o(m1960decodeTextUnitXSAIIZE());
                        }
                    } else {
                        mutableSpanStyle.setFontFeatureSettings(decodeString());
                    }
                    mutableSpanStyle2 = mutableSpanStyle;
                } else {
                    if (dataAvailable() < 1) {
                        break;
                    }
                    mutableSpanStyle.m1980setFontSynthesistDdu0R4(FontSynthesis.m2161boximpl(m1959decodeFontSynthesisGVVA2EU()));
                    mutableSpanStyle2 = mutableSpanStyle;
                }
            } else {
                if (dataAvailable() < 8) {
                    break;
                }
                mutableSpanStyle2.m1977setColor8_81llA(m1957decodeColor0d7_KjU());
            }
        }
        mutableSpanStyle = mutableSpanStyle2;
        return mutableSpanStyle.toSpanStyle();
    }

    /* JADX INFO: renamed from: decodeColor-0d7_KjU, reason: not valid java name */
    public final long m1957decodeColor0d7_KjU() {
        return Color.m1291constructorimpl(m1956decodeULongsVKNKU());
    }

    /* JADX INFO: renamed from: decodeTextUnit-XSAIIZE, reason: not valid java name */
    public final long m1960decodeTextUnitXSAIIZE() {
        long jM2509getUnspecifiedUIouoOA;
        byte bDecodeByte = decodeByte();
        if (bDecodeByte == 1) {
            jM2509getUnspecifiedUIouoOA = TextUnitType.Companion.m2508getSpUIouoOA();
        } else if (bDecodeByte == 2) {
            jM2509getUnspecifiedUIouoOA = TextUnitType.Companion.m2507getEmUIouoOA();
        } else {
            jM2509getUnspecifiedUIouoOA = TextUnitType.Companion.m2509getUnspecifiedUIouoOA();
        }
        if (TextUnitType.m2503equalsimpl0(jM2509getUnspecifiedUIouoOA, TextUnitType.Companion.m2509getUnspecifiedUIouoOA())) {
            return TextUnit.Companion.m2494getUnspecifiedXSAIIZE();
        }
        return TextUnitKt.m2495TextUnitanM5pPY(decodeFloat(), jM2509getUnspecifiedUIouoOA);
    }

    public final FontWeight decodeFontWeight() {
        return new FontWeight(decodeInt());
    }

    /* JADX INFO: renamed from: decodeFontStyle-_-LCdwA, reason: not valid java name */
    public final int m1958decodeFontStyle_LCdwA() {
        byte bDecodeByte = decodeByte();
        if (bDecodeByte == 0) {
            return FontStyle.Companion.m2160getNormal_LCdwA();
        }
        if (bDecodeByte == 1) {
            return FontStyle.Companion.m2159getItalic_LCdwA();
        }
        return FontStyle.Companion.m2160getNormal_LCdwA();
    }

    /* JADX INFO: renamed from: decodeFontSynthesis-GVVA2EU, reason: not valid java name */
    public final int m1959decodeFontSynthesisGVVA2EU() {
        byte bDecodeByte = decodeByte();
        if (bDecodeByte == 0) {
            return FontSynthesis.Companion.m2169getNoneGVVA2EU();
        }
        if (bDecodeByte == 1) {
            return FontSynthesis.Companion.m2168getAllGVVA2EU();
        }
        if (bDecodeByte == 3) {
            return FontSynthesis.Companion.m2170getStyleGVVA2EU();
        }
        if (bDecodeByte == 2) {
            return FontSynthesis.Companion.m2171getWeightGVVA2EU();
        }
        return FontSynthesis.Companion.m2169getNoneGVVA2EU();
    }

    /* JADX INFO: renamed from: decodeBaselineShift-y9eOQZs, reason: not valid java name */
    private final float m1955decodeBaselineShifty9eOQZs() {
        return BaselineShift.m2268constructorimpl(decodeFloat());
    }

    private final TextGeometricTransform decodeTextGeometricTransform() {
        return new TextGeometricTransform(decodeFloat(), decodeFloat());
    }

    private final TextDecoration decodeTextDecoration() {
        int iDecodeInt = decodeInt();
        TextDecoration.Companion companion = TextDecoration.Companion;
        boolean z = (companion.getLineThrough().getMask() & iDecodeInt) != 0;
        boolean z2 = (iDecodeInt & companion.getUnderline().getMask()) != 0;
        if (z && z2) {
            return companion.combine(CollectionsKt.listOf((Object[]) new TextDecoration[]{companion.getLineThrough(), companion.getUnderline()}));
        }
        if (z) {
            return companion.getLineThrough();
        }
        if (z2) {
            return companion.getUnderline();
        }
        return companion.getNone();
    }

    private final Shadow decodeShadow() {
        return new Shadow(m1957decodeColor0d7_KjU(), OffsetKt.Offset(decodeFloat(), decodeFloat()), decodeFloat(), null);
    }

    private final byte decodeByte() {
        return this.parcel.readByte();
    }

    private final int decodeInt() {
        return this.parcel.readInt();
    }

    /* JADX INFO: renamed from: decodeULong-s-VKNKU, reason: not valid java name */
    private final long m1956decodeULongsVKNKU() {
        return ULong.m2693constructorimpl(this.parcel.readLong());
    }

    private final float decodeFloat() {
        return this.parcel.readFloat();
    }

    private final String decodeString() {
        return this.parcel.readString();
    }

    private final int dataAvailable() {
        return this.parcel.dataAvail();
    }
}
