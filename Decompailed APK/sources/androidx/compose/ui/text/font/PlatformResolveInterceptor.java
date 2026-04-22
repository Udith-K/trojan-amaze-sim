package androidx.compose.ui.text.font;

import androidx.compose.ui.text.font.PlatformResolveInterceptor;

/* JADX INFO: compiled from: FontFamilyResolver.kt */
/* JADX INFO: loaded from: classes.dex */
public interface PlatformResolveInterceptor {
    public static final Companion Companion = Companion.$$INSTANCE;

    FontFamily interceptFontFamily(FontFamily fontFamily);

    /* JADX INFO: renamed from: interceptFontStyle-T2F_aPo */
    int mo2146interceptFontStyleT2F_aPo(int i);

    /* JADX INFO: renamed from: interceptFontSynthesis-Mscr08Y */
    int mo2147interceptFontSynthesisMscr08Y(int i);

    FontWeight interceptFontWeight(FontWeight fontWeight);

    /* JADX INFO: renamed from: androidx.compose.ui.text.font.PlatformResolveInterceptor$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: FontFamilyResolver.kt */
    public abstract /* synthetic */ class CC {
        public static FontFamily $default$interceptFontFamily(PlatformResolveInterceptor platformResolveInterceptor, FontFamily fontFamily) {
            return fontFamily;
        }

        public static FontWeight $default$interceptFontWeight(PlatformResolveInterceptor platformResolveInterceptor, FontWeight fontWeight) {
            return fontWeight;
        }

        /* JADX INFO: renamed from: $default$interceptFontStyle-T2F_aPo, reason: not valid java name */
        public static int m2172$default$interceptFontStyleT2F_aPo(PlatformResolveInterceptor platformResolveInterceptor, int i) {
            return i;
        }

        /* JADX INFO: renamed from: $default$interceptFontSynthesis-Mscr08Y, reason: not valid java name */
        public static int m2173$default$interceptFontSynthesisMscr08Y(PlatformResolveInterceptor platformResolveInterceptor, int i) {
            return i;
        }
    }

    /* JADX INFO: compiled from: FontFamilyResolver.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final PlatformResolveInterceptor Default = new PlatformResolveInterceptor() { // from class: androidx.compose.ui.text.font.PlatformResolveInterceptor$Companion$Default$1
            @Override // androidx.compose.ui.text.font.PlatformResolveInterceptor
            public /* synthetic */ FontFamily interceptFontFamily(FontFamily fontFamily) {
                return PlatformResolveInterceptor.CC.$default$interceptFontFamily(this, fontFamily);
            }

            @Override // androidx.compose.ui.text.font.PlatformResolveInterceptor
            /* JADX INFO: renamed from: interceptFontStyle-T2F_aPo */
            public /* synthetic */ int mo2146interceptFontStyleT2F_aPo(int i) {
                return PlatformResolveInterceptor.CC.m2172$default$interceptFontStyleT2F_aPo(this, i);
            }

            @Override // androidx.compose.ui.text.font.PlatformResolveInterceptor
            /* JADX INFO: renamed from: interceptFontSynthesis-Mscr08Y */
            public /* synthetic */ int mo2147interceptFontSynthesisMscr08Y(int i) {
                return PlatformResolveInterceptor.CC.m2173$default$interceptFontSynthesisMscr08Y(this, i);
            }

            @Override // androidx.compose.ui.text.font.PlatformResolveInterceptor
            public /* synthetic */ FontWeight interceptFontWeight(FontWeight fontWeight) {
                return PlatformResolveInterceptor.CC.$default$interceptFontWeight(this, fontWeight);
            }
        };

        private Companion() {
        }

        public final PlatformResolveInterceptor getDefault$ui_text_release() {
            return Default;
        }
    }
}
