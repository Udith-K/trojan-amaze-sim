package androidx.compose.ui.graphics;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: BlendMode.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BlendMode {
    public static final Companion Companion = new Companion(null);
    private static final int Clear = m1245constructorimpl(0);
    private static final int Src = m1245constructorimpl(1);
    private static final int Dst = m1245constructorimpl(2);
    private static final int SrcOver = m1245constructorimpl(3);
    private static final int DstOver = m1245constructorimpl(4);
    private static final int SrcIn = m1245constructorimpl(5);
    private static final int DstIn = m1245constructorimpl(6);
    private static final int SrcOut = m1245constructorimpl(7);
    private static final int DstOut = m1245constructorimpl(8);
    private static final int SrcAtop = m1245constructorimpl(9);
    private static final int DstAtop = m1245constructorimpl(10);
    private static final int Xor = m1245constructorimpl(11);
    private static final int Plus = m1245constructorimpl(12);
    private static final int Modulate = m1245constructorimpl(13);
    private static final int Screen = m1245constructorimpl(14);
    private static final int Overlay = m1245constructorimpl(15);
    private static final int Darken = m1245constructorimpl(16);
    private static final int Lighten = m1245constructorimpl(17);
    private static final int ColorDodge = m1245constructorimpl(18);
    private static final int ColorBurn = m1245constructorimpl(19);
    private static final int Hardlight = m1245constructorimpl(20);
    private static final int Softlight = m1245constructorimpl(21);
    private static final int Difference = m1245constructorimpl(22);
    private static final int Exclusion = m1245constructorimpl(23);
    private static final int Multiply = m1245constructorimpl(24);
    private static final int Hue = m1245constructorimpl(25);
    private static final int Saturation = m1245constructorimpl(26);
    private static final int Color = m1245constructorimpl(27);
    private static final int Luminosity = m1245constructorimpl(28);

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1245constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1246equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1247hashCodeimpl(int i) {
        return i;
    }

    /* JADX INFO: compiled from: BlendMode.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getClear-0nO6VwU, reason: not valid java name */
        public final int m1249getClear0nO6VwU() {
            return BlendMode.Clear;
        }

        /* JADX INFO: renamed from: getSrc-0nO6VwU, reason: not valid java name */
        public final int m1272getSrc0nO6VwU() {
            return BlendMode.Src;
        }

        /* JADX INFO: renamed from: getDst-0nO6VwU, reason: not valid java name */
        public final int m1255getDst0nO6VwU() {
            return BlendMode.Dst;
        }

        /* JADX INFO: renamed from: getSrcOver-0nO6VwU, reason: not valid java name */
        public final int m1276getSrcOver0nO6VwU() {
            return BlendMode.SrcOver;
        }

        /* JADX INFO: renamed from: getDstOver-0nO6VwU, reason: not valid java name */
        public final int m1259getDstOver0nO6VwU() {
            return BlendMode.DstOver;
        }

        /* JADX INFO: renamed from: getSrcIn-0nO6VwU, reason: not valid java name */
        public final int m1274getSrcIn0nO6VwU() {
            return BlendMode.SrcIn;
        }

        /* JADX INFO: renamed from: getDstIn-0nO6VwU, reason: not valid java name */
        public final int m1257getDstIn0nO6VwU() {
            return BlendMode.DstIn;
        }

        /* JADX INFO: renamed from: getSrcOut-0nO6VwU, reason: not valid java name */
        public final int m1275getSrcOut0nO6VwU() {
            return BlendMode.SrcOut;
        }

        /* JADX INFO: renamed from: getDstOut-0nO6VwU, reason: not valid java name */
        public final int m1258getDstOut0nO6VwU() {
            return BlendMode.DstOut;
        }

        /* JADX INFO: renamed from: getSrcAtop-0nO6VwU, reason: not valid java name */
        public final int m1273getSrcAtop0nO6VwU() {
            return BlendMode.SrcAtop;
        }

        /* JADX INFO: renamed from: getDstAtop-0nO6VwU, reason: not valid java name */
        public final int m1256getDstAtop0nO6VwU() {
            return BlendMode.DstAtop;
        }

        /* JADX INFO: renamed from: getXor-0nO6VwU, reason: not valid java name */
        public final int m1277getXor0nO6VwU() {
            return BlendMode.Xor;
        }

        /* JADX INFO: renamed from: getPlus-0nO6VwU, reason: not valid java name */
        public final int m1268getPlus0nO6VwU() {
            return BlendMode.Plus;
        }

        /* JADX INFO: renamed from: getModulate-0nO6VwU, reason: not valid java name */
        public final int m1265getModulate0nO6VwU() {
            return BlendMode.Modulate;
        }

        /* JADX INFO: renamed from: getScreen-0nO6VwU, reason: not valid java name */
        public final int m1270getScreen0nO6VwU() {
            return BlendMode.Screen;
        }

        /* JADX INFO: renamed from: getOverlay-0nO6VwU, reason: not valid java name */
        public final int m1267getOverlay0nO6VwU() {
            return BlendMode.Overlay;
        }

        /* JADX INFO: renamed from: getDarken-0nO6VwU, reason: not valid java name */
        public final int m1253getDarken0nO6VwU() {
            return BlendMode.Darken;
        }

        /* JADX INFO: renamed from: getLighten-0nO6VwU, reason: not valid java name */
        public final int m1263getLighten0nO6VwU() {
            return BlendMode.Lighten;
        }

        /* JADX INFO: renamed from: getColorDodge-0nO6VwU, reason: not valid java name */
        public final int m1252getColorDodge0nO6VwU() {
            return BlendMode.ColorDodge;
        }

        /* JADX INFO: renamed from: getColorBurn-0nO6VwU, reason: not valid java name */
        public final int m1251getColorBurn0nO6VwU() {
            return BlendMode.ColorBurn;
        }

        /* JADX INFO: renamed from: getHardlight-0nO6VwU, reason: not valid java name */
        public final int m1261getHardlight0nO6VwU() {
            return BlendMode.Hardlight;
        }

        /* JADX INFO: renamed from: getSoftlight-0nO6VwU, reason: not valid java name */
        public final int m1271getSoftlight0nO6VwU() {
            return BlendMode.Softlight;
        }

        /* JADX INFO: renamed from: getDifference-0nO6VwU, reason: not valid java name */
        public final int m1254getDifference0nO6VwU() {
            return BlendMode.Difference;
        }

        /* JADX INFO: renamed from: getExclusion-0nO6VwU, reason: not valid java name */
        public final int m1260getExclusion0nO6VwU() {
            return BlendMode.Exclusion;
        }

        /* JADX INFO: renamed from: getMultiply-0nO6VwU, reason: not valid java name */
        public final int m1266getMultiply0nO6VwU() {
            return BlendMode.Multiply;
        }

        /* JADX INFO: renamed from: getHue-0nO6VwU, reason: not valid java name */
        public final int m1262getHue0nO6VwU() {
            return BlendMode.Hue;
        }

        /* JADX INFO: renamed from: getSaturation-0nO6VwU, reason: not valid java name */
        public final int m1269getSaturation0nO6VwU() {
            return BlendMode.Saturation;
        }

        /* JADX INFO: renamed from: getColor-0nO6VwU, reason: not valid java name */
        public final int m1250getColor0nO6VwU() {
            return BlendMode.Color;
        }

        /* JADX INFO: renamed from: getLuminosity-0nO6VwU, reason: not valid java name */
        public final int m1264getLuminosity0nO6VwU() {
            return BlendMode.Luminosity;
        }
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1248toStringimpl(int i) {
        return m1246equalsimpl0(i, Clear) ? "Clear" : m1246equalsimpl0(i, Src) ? "Src" : m1246equalsimpl0(i, Dst) ? "Dst" : m1246equalsimpl0(i, SrcOver) ? "SrcOver" : m1246equalsimpl0(i, DstOver) ? "DstOver" : m1246equalsimpl0(i, SrcIn) ? "SrcIn" : m1246equalsimpl0(i, DstIn) ? "DstIn" : m1246equalsimpl0(i, SrcOut) ? "SrcOut" : m1246equalsimpl0(i, DstOut) ? "DstOut" : m1246equalsimpl0(i, SrcAtop) ? "SrcAtop" : m1246equalsimpl0(i, DstAtop) ? "DstAtop" : m1246equalsimpl0(i, Xor) ? "Xor" : m1246equalsimpl0(i, Plus) ? "Plus" : m1246equalsimpl0(i, Modulate) ? "Modulate" : m1246equalsimpl0(i, Screen) ? "Screen" : m1246equalsimpl0(i, Overlay) ? "Overlay" : m1246equalsimpl0(i, Darken) ? "Darken" : m1246equalsimpl0(i, Lighten) ? "Lighten" : m1246equalsimpl0(i, ColorDodge) ? "ColorDodge" : m1246equalsimpl0(i, ColorBurn) ? "ColorBurn" : m1246equalsimpl0(i, Hardlight) ? "HardLight" : m1246equalsimpl0(i, Softlight) ? "Softlight" : m1246equalsimpl0(i, Difference) ? "Difference" : m1246equalsimpl0(i, Exclusion) ? "Exclusion" : m1246equalsimpl0(i, Multiply) ? "Multiply" : m1246equalsimpl0(i, Hue) ? "Hue" : m1246equalsimpl0(i, Saturation) ? "Saturation" : m1246equalsimpl0(i, Color) ? "Color" : m1246equalsimpl0(i, Luminosity) ? "Luminosity" : "Unknown";
    }
}
