package androidx.compose.ui.text.font;

import android.graphics.Typeface;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlatformTypefaces.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class PlatformTypefacesApi implements PlatformTypefaces {
    @Override // androidx.compose.ui.text.font.PlatformTypefaces
    /* JADX INFO: renamed from: createDefault-FO1MlWM */
    public Typeface mo2174createDefaultFO1MlWM(FontWeight fontWeight, int i) {
        return m2176createAndroidTypefaceUsingTypefaceStyleRetOiIg(null, fontWeight, i);
    }

    @Override // androidx.compose.ui.text.font.PlatformTypefaces
    /* JADX INFO: renamed from: createNamed-RetOiIg */
    public Typeface mo2175createNamedRetOiIg(GenericFontFamily genericFontFamily, FontWeight fontWeight, int i) {
        Typeface typefaceM2177loadNamedFromTypefaceCacheOrNullRetOiIg = m2177loadNamedFromTypefaceCacheOrNullRetOiIg(PlatformTypefaces_androidKt.getWeightSuffixForFallbackFamilyName(genericFontFamily.getName(), fontWeight), fontWeight, i);
        return typefaceM2177loadNamedFromTypefaceCacheOrNullRetOiIg == null ? m2176createAndroidTypefaceUsingTypefaceStyleRetOiIg(genericFontFamily.getName(), fontWeight, i) : typefaceM2177loadNamedFromTypefaceCacheOrNullRetOiIg;
    }

    /* JADX INFO: renamed from: loadNamedFromTypefaceCacheOrNull-RetOiIg, reason: not valid java name */
    private final Typeface m2177loadNamedFromTypefaceCacheOrNullRetOiIg(String str, FontWeight fontWeight, int i) {
        if (str.length() == 0) {
            return null;
        }
        Typeface typefaceM2176createAndroidTypefaceUsingTypefaceStyleRetOiIg = m2176createAndroidTypefaceUsingTypefaceStyleRetOiIg(str, fontWeight, i);
        if (Intrinsics.areEqual(typefaceM2176createAndroidTypefaceUsingTypefaceStyleRetOiIg, Typeface.create(Typeface.DEFAULT, AndroidFontUtils_androidKt.m2148getAndroidTypefaceStyleFO1MlWM(fontWeight, i))) || Intrinsics.areEqual(typefaceM2176createAndroidTypefaceUsingTypefaceStyleRetOiIg, m2176createAndroidTypefaceUsingTypefaceStyleRetOiIg(null, fontWeight, i))) {
            return null;
        }
        return typefaceM2176createAndroidTypefaceUsingTypefaceStyleRetOiIg;
    }

    /* JADX INFO: renamed from: createAndroidTypefaceUsingTypefaceStyle-RetOiIg, reason: not valid java name */
    private final Typeface m2176createAndroidTypefaceUsingTypefaceStyleRetOiIg(String str, FontWeight fontWeight, int i) {
        if (FontStyle.m2155equalsimpl0(i, FontStyle.Companion.m2160getNormal_LCdwA()) && Intrinsics.areEqual(fontWeight, FontWeight.Companion.getNormal()) && (str == null || str.length() == 0)) {
            return Typeface.DEFAULT;
        }
        int iM2148getAndroidTypefaceStyleFO1MlWM = AndroidFontUtils_androidKt.m2148getAndroidTypefaceStyleFO1MlWM(fontWeight, i);
        if (str == null || str.length() == 0) {
            return Typeface.defaultFromStyle(iM2148getAndroidTypefaceStyleFO1MlWM);
        }
        return Typeface.create(str, iM2148getAndroidTypefaceStyleFO1MlWM);
    }
}
