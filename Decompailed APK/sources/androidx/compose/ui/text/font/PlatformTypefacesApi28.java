package androidx.compose.ui.text.font;

import android.graphics.Typeface;
import androidx.compose.ui.text.font.FontStyle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlatformTypefaces.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class PlatformTypefacesApi28 implements PlatformTypefaces {
    @Override // androidx.compose.ui.text.font.PlatformTypefaces
    /* JADX INFO: renamed from: createDefault-FO1MlWM */
    public Typeface mo2174createDefaultFO1MlWM(FontWeight fontWeight, int i) {
        return m2178createAndroidTypefaceApi28RetOiIg(null, fontWeight, i);
    }

    @Override // androidx.compose.ui.text.font.PlatformTypefaces
    /* JADX INFO: renamed from: createNamed-RetOiIg */
    public Typeface mo2175createNamedRetOiIg(GenericFontFamily genericFontFamily, FontWeight fontWeight, int i) {
        return m2178createAndroidTypefaceApi28RetOiIg(genericFontFamily.getName(), fontWeight, i);
    }

    /* JADX INFO: renamed from: createAndroidTypefaceApi28-RetOiIg, reason: not valid java name */
    private final Typeface m2178createAndroidTypefaceApi28RetOiIg(String str, FontWeight fontWeight, int i) {
        Typeface typefaceCreate;
        FontStyle.Companion companion = FontStyle.Companion;
        if (FontStyle.m2155equalsimpl0(i, companion.m2160getNormal_LCdwA()) && Intrinsics.areEqual(fontWeight, FontWeight.Companion.getNormal()) && (str == null || str.length() == 0)) {
            return Typeface.DEFAULT;
        }
        if (str == null) {
            typefaceCreate = Typeface.DEFAULT;
        } else {
            typefaceCreate = Typeface.create(str, 0);
        }
        return Typeface.create(typefaceCreate, fontWeight.getWeight(), FontStyle.m2155equalsimpl0(i, companion.m2159getItalic_LCdwA()));
    }
}
