package androidx.compose.ui.text.font;

import androidx.compose.runtime.State;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FontFamily.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FontFamily {
    private final boolean canLoadSynchronously;
    public static final Companion Companion = new Companion(null);
    private static final SystemFontFamily Default = new DefaultFontFamily();
    private static final GenericFontFamily SansSerif = new GenericFontFamily("sans-serif", "FontFamily.SansSerif");
    private static final GenericFontFamily Serif = new GenericFontFamily("serif", "FontFamily.Serif");
    private static final GenericFontFamily Monospace = new GenericFontFamily("monospace", "FontFamily.Monospace");
    private static final GenericFontFamily Cursive = new GenericFontFamily("cursive", "FontFamily.Cursive");

    public /* synthetic */ FontFamily(boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(z);
    }

    private FontFamily(boolean z) {
        this.canLoadSynchronously = z;
    }

    /* JADX INFO: compiled from: FontFamily.kt */
    public interface Resolver {
        /* JADX INFO: renamed from: resolve-DPcqOEQ, reason: not valid java name */
        State mo2150resolveDPcqOEQ(FontFamily fontFamily, FontWeight fontWeight, int i, int i2);

        /* JADX INFO: renamed from: androidx.compose.ui.text.font.FontFamily$Resolver$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: FontFamily.kt */
        public abstract /* synthetic */ class CC {
            /* JADX INFO: renamed from: resolve-DPcqOEQ$default, reason: not valid java name */
            public static /* synthetic */ State m2151resolveDPcqOEQ$default(Resolver resolver, FontFamily fontFamily, FontWeight fontWeight, int i, int i2, int i3, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resolve-DPcqOEQ");
                }
                if ((i3 & 1) != 0) {
                    fontFamily = null;
                }
                if ((i3 & 2) != 0) {
                    fontWeight = FontWeight.Companion.getNormal();
                }
                if ((i3 & 4) != 0) {
                    i = FontStyle.Companion.m2160getNormal_LCdwA();
                }
                if ((i3 & 8) != 0) {
                    i2 = FontSynthesis.Companion.m2168getAllGVVA2EU();
                }
                return resolver.mo2150resolveDPcqOEQ(fontFamily, fontWeight, i, i2);
            }
        }
    }

    /* JADX INFO: compiled from: FontFamily.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SystemFontFamily getDefault() {
            return FontFamily.Default;
        }

        public final GenericFontFamily getSansSerif() {
            return FontFamily.SansSerif;
        }

        public final GenericFontFamily getMonospace() {
            return FontFamily.Monospace;
        }
    }
}
