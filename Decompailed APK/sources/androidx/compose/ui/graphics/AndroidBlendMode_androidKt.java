package androidx.compose.ui.graphics;

import android.graphics.PorterDuff;
import androidx.compose.ui.graphics.BlendMode;

/* JADX INFO: compiled from: AndroidBlendMode.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidBlendMode_androidKt {

    /* JADX INFO: compiled from: AndroidBlendMode.android.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[android.graphics.BlendMode.values().length];
            try {
                iArr[android.graphics.BlendMode.CLEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[android.graphics.BlendMode.DST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_OVER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_OVER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_IN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_IN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_OUT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_OUT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_ATOP.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_ATOP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[android.graphics.BlendMode.XOR.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[android.graphics.BlendMode.PLUS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[android.graphics.BlendMode.MODULATE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[android.graphics.BlendMode.SCREEN.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[android.graphics.BlendMode.OVERLAY.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[android.graphics.BlendMode.DARKEN.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[android.graphics.BlendMode.LIGHTEN.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[android.graphics.BlendMode.COLOR_DODGE.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[android.graphics.BlendMode.COLOR_BURN.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[android.graphics.BlendMode.HARD_LIGHT.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[android.graphics.BlendMode.SOFT_LIGHT.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[android.graphics.BlendMode.DIFFERENCE.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[android.graphics.BlendMode.EXCLUSION.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[android.graphics.BlendMode.MULTIPLY.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[android.graphics.BlendMode.HUE.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[android.graphics.BlendMode.SATURATION.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr[android.graphics.BlendMode.COLOR.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr[android.graphics.BlendMode.LUMINOSITY.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: toPorterDuffMode-s9anfk8, reason: not valid java name */
    public static final PorterDuff.Mode m1206toPorterDuffModes9anfk8(int i) {
        BlendMode.Companion companion = BlendMode.Companion;
        if (BlendMode.m1246equalsimpl0(i, companion.m1249getClear0nO6VwU())) {
            return PorterDuff.Mode.CLEAR;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1272getSrc0nO6VwU())) {
            return PorterDuff.Mode.SRC;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1255getDst0nO6VwU())) {
            return PorterDuff.Mode.DST;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1276getSrcOver0nO6VwU())) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1259getDstOver0nO6VwU())) {
            return PorterDuff.Mode.DST_OVER;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1274getSrcIn0nO6VwU())) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1257getDstIn0nO6VwU())) {
            return PorterDuff.Mode.DST_IN;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1275getSrcOut0nO6VwU())) {
            return PorterDuff.Mode.SRC_OUT;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1258getDstOut0nO6VwU())) {
            return PorterDuff.Mode.DST_OUT;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1273getSrcAtop0nO6VwU())) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1256getDstAtop0nO6VwU())) {
            return PorterDuff.Mode.DST_ATOP;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1277getXor0nO6VwU())) {
            return PorterDuff.Mode.XOR;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1268getPlus0nO6VwU())) {
            return PorterDuff.Mode.ADD;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1270getScreen0nO6VwU())) {
            return PorterDuff.Mode.SCREEN;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1267getOverlay0nO6VwU())) {
            return PorterDuff.Mode.OVERLAY;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1253getDarken0nO6VwU())) {
            return PorterDuff.Mode.DARKEN;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1263getLighten0nO6VwU())) {
            return PorterDuff.Mode.LIGHTEN;
        }
        if (BlendMode.m1246equalsimpl0(i, companion.m1265getModulate0nO6VwU())) {
            return PorterDuff.Mode.MULTIPLY;
        }
        return PorterDuff.Mode.SRC_OVER;
    }

    /* JADX INFO: renamed from: toAndroidBlendMode-s9anfk8, reason: not valid java name */
    public static final android.graphics.BlendMode m1205toAndroidBlendModes9anfk8(int i) {
        BlendMode.Companion companion = BlendMode.Companion;
        return BlendMode.m1246equalsimpl0(i, companion.m1249getClear0nO6VwU()) ? android.graphics.BlendMode.CLEAR : BlendMode.m1246equalsimpl0(i, companion.m1272getSrc0nO6VwU()) ? android.graphics.BlendMode.SRC : BlendMode.m1246equalsimpl0(i, companion.m1255getDst0nO6VwU()) ? android.graphics.BlendMode.DST : BlendMode.m1246equalsimpl0(i, companion.m1276getSrcOver0nO6VwU()) ? android.graphics.BlendMode.SRC_OVER : BlendMode.m1246equalsimpl0(i, companion.m1259getDstOver0nO6VwU()) ? android.graphics.BlendMode.DST_OVER : BlendMode.m1246equalsimpl0(i, companion.m1274getSrcIn0nO6VwU()) ? android.graphics.BlendMode.SRC_IN : BlendMode.m1246equalsimpl0(i, companion.m1257getDstIn0nO6VwU()) ? android.graphics.BlendMode.DST_IN : BlendMode.m1246equalsimpl0(i, companion.m1275getSrcOut0nO6VwU()) ? android.graphics.BlendMode.SRC_OUT : BlendMode.m1246equalsimpl0(i, companion.m1258getDstOut0nO6VwU()) ? android.graphics.BlendMode.DST_OUT : BlendMode.m1246equalsimpl0(i, companion.m1273getSrcAtop0nO6VwU()) ? android.graphics.BlendMode.SRC_ATOP : BlendMode.m1246equalsimpl0(i, companion.m1256getDstAtop0nO6VwU()) ? android.graphics.BlendMode.DST_ATOP : BlendMode.m1246equalsimpl0(i, companion.m1277getXor0nO6VwU()) ? android.graphics.BlendMode.XOR : BlendMode.m1246equalsimpl0(i, companion.m1268getPlus0nO6VwU()) ? android.graphics.BlendMode.PLUS : BlendMode.m1246equalsimpl0(i, companion.m1265getModulate0nO6VwU()) ? android.graphics.BlendMode.MODULATE : BlendMode.m1246equalsimpl0(i, companion.m1270getScreen0nO6VwU()) ? android.graphics.BlendMode.SCREEN : BlendMode.m1246equalsimpl0(i, companion.m1267getOverlay0nO6VwU()) ? android.graphics.BlendMode.OVERLAY : BlendMode.m1246equalsimpl0(i, companion.m1253getDarken0nO6VwU()) ? android.graphics.BlendMode.DARKEN : BlendMode.m1246equalsimpl0(i, companion.m1263getLighten0nO6VwU()) ? android.graphics.BlendMode.LIGHTEN : BlendMode.m1246equalsimpl0(i, companion.m1252getColorDodge0nO6VwU()) ? android.graphics.BlendMode.COLOR_DODGE : BlendMode.m1246equalsimpl0(i, companion.m1251getColorBurn0nO6VwU()) ? android.graphics.BlendMode.COLOR_BURN : BlendMode.m1246equalsimpl0(i, companion.m1261getHardlight0nO6VwU()) ? android.graphics.BlendMode.HARD_LIGHT : BlendMode.m1246equalsimpl0(i, companion.m1271getSoftlight0nO6VwU()) ? android.graphics.BlendMode.SOFT_LIGHT : BlendMode.m1246equalsimpl0(i, companion.m1254getDifference0nO6VwU()) ? android.graphics.BlendMode.DIFFERENCE : BlendMode.m1246equalsimpl0(i, companion.m1260getExclusion0nO6VwU()) ? android.graphics.BlendMode.EXCLUSION : BlendMode.m1246equalsimpl0(i, companion.m1266getMultiply0nO6VwU()) ? android.graphics.BlendMode.MULTIPLY : BlendMode.m1246equalsimpl0(i, companion.m1262getHue0nO6VwU()) ? android.graphics.BlendMode.HUE : BlendMode.m1246equalsimpl0(i, companion.m1269getSaturation0nO6VwU()) ? android.graphics.BlendMode.SATURATION : BlendMode.m1246equalsimpl0(i, companion.m1250getColor0nO6VwU()) ? android.graphics.BlendMode.COLOR : BlendMode.m1246equalsimpl0(i, companion.m1264getLuminosity0nO6VwU()) ? android.graphics.BlendMode.LUMINOSITY : android.graphics.BlendMode.SRC_OVER;
    }

    public static final int toComposeBlendMode(android.graphics.BlendMode blendMode) {
        switch (WhenMappings.$EnumSwitchMapping$0[blendMode.ordinal()]) {
            case 1:
                return BlendMode.Companion.m1249getClear0nO6VwU();
            case 2:
                return BlendMode.Companion.m1272getSrc0nO6VwU();
            case 3:
                return BlendMode.Companion.m1255getDst0nO6VwU();
            case 4:
                return BlendMode.Companion.m1276getSrcOver0nO6VwU();
            case 5:
                return BlendMode.Companion.m1259getDstOver0nO6VwU();
            case 6:
                return BlendMode.Companion.m1274getSrcIn0nO6VwU();
            case 7:
                return BlendMode.Companion.m1257getDstIn0nO6VwU();
            case 8:
                return BlendMode.Companion.m1275getSrcOut0nO6VwU();
            case 9:
                return BlendMode.Companion.m1258getDstOut0nO6VwU();
            case 10:
                return BlendMode.Companion.m1273getSrcAtop0nO6VwU();
            case 11:
                return BlendMode.Companion.m1256getDstAtop0nO6VwU();
            case 12:
                return BlendMode.Companion.m1277getXor0nO6VwU();
            case 13:
                return BlendMode.Companion.m1268getPlus0nO6VwU();
            case 14:
                return BlendMode.Companion.m1265getModulate0nO6VwU();
            case 15:
                return BlendMode.Companion.m1270getScreen0nO6VwU();
            case 16:
                return BlendMode.Companion.m1267getOverlay0nO6VwU();
            case 17:
                return BlendMode.Companion.m1253getDarken0nO6VwU();
            case 18:
                return BlendMode.Companion.m1263getLighten0nO6VwU();
            case 19:
                return BlendMode.Companion.m1252getColorDodge0nO6VwU();
            case 20:
                return BlendMode.Companion.m1251getColorBurn0nO6VwU();
            case 21:
                return BlendMode.Companion.m1261getHardlight0nO6VwU();
            case 22:
                return BlendMode.Companion.m1271getSoftlight0nO6VwU();
            case 23:
                return BlendMode.Companion.m1254getDifference0nO6VwU();
            case 24:
                return BlendMode.Companion.m1260getExclusion0nO6VwU();
            case 25:
                return BlendMode.Companion.m1266getMultiply0nO6VwU();
            case 26:
                return BlendMode.Companion.m1262getHue0nO6VwU();
            case 27:
                return BlendMode.Companion.m1269getSaturation0nO6VwU();
            case 28:
                return BlendMode.Companion.m1250getColor0nO6VwU();
            case 29:
                return BlendMode.Companion.m1264getLuminosity0nO6VwU();
            default:
                return BlendMode.Companion.m1276getSrcOver0nO6VwU();
        }
    }
}
