package androidx.compose.ui.text.font;

import android.os.Build;

/* JADX INFO: compiled from: PlatformTypefaces.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PlatformTypefaces_androidKt {
    public static final PlatformTypefaces PlatformTypefaces() {
        if (Build.VERSION.SDK_INT >= 28) {
            return new PlatformTypefacesApi28();
        }
        return new PlatformTypefacesApi();
    }

    public static final String getWeightSuffixForFallbackFamilyName(String str, FontWeight fontWeight) {
        int weight = fontWeight.getWeight() / 100;
        if (weight >= 0 && weight < 2) {
            return str + "-thin";
        }
        if (2 <= weight && weight < 4) {
            return str + "-light";
        }
        if (weight == 4) {
            return str;
        }
        if (weight == 5) {
            return str + "-medium";
        }
        if ((6 <= weight && weight < 8) || 8 > weight || weight >= 11) {
            return str;
        }
        return str + "-black";
    }
}
