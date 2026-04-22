package androidx.compose.ui.text.style;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.graphics.SolidColor;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextForegroundStyle.kt */
/* JADX INFO: loaded from: classes.dex */
public interface TextForegroundStyle {
    public static final Companion Companion = Companion.$$INSTANCE;

    float getAlpha();

    Brush getBrush();

    /* JADX INFO: renamed from: getColor-0d7_KjU */
    long mo2276getColor0d7_KjU();

    TextForegroundStyle merge(TextForegroundStyle textForegroundStyle);

    TextForegroundStyle takeOrElse(Function0 function0);

    /* JADX INFO: renamed from: androidx.compose.ui.text.style.TextForegroundStyle$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: TextForegroundStyle.kt */
    public abstract /* synthetic */ class CC {
        public static TextForegroundStyle $default$merge(final TextForegroundStyle textForegroundStyle, TextForegroundStyle textForegroundStyle2) {
            boolean z = textForegroundStyle2 instanceof BrushStyle;
            if (z && (textForegroundStyle instanceof BrushStyle)) {
                return new BrushStyle(((BrushStyle) textForegroundStyle2).getValue(), TextDrawStyleKt.takeOrElse(textForegroundStyle2.getAlpha(), new Function0() { // from class: androidx.compose.ui.text.style.TextForegroundStyle.merge.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(TextForegroundStyle.this.getAlpha());
                    }
                }));
            }
            return (!z || (textForegroundStyle instanceof BrushStyle)) ? (z || !(textForegroundStyle instanceof BrushStyle)) ? textForegroundStyle2.takeOrElse(new Function0() { // from class: androidx.compose.ui.text.style.TextForegroundStyle.merge.2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final TextForegroundStyle invoke() {
                    return TextForegroundStyle.this;
                }
            }) : textForegroundStyle : textForegroundStyle2;
        }

        public static TextForegroundStyle $default$takeOrElse(TextForegroundStyle textForegroundStyle, Function0 function0) {
            return !Intrinsics.areEqual(textForegroundStyle, Unspecified.INSTANCE) ? textForegroundStyle : (TextForegroundStyle) function0.invoke();
        }
    }

    /* JADX INFO: compiled from: TextForegroundStyle.kt */
    public static final class Unspecified implements TextForegroundStyle {
        public static final Unspecified INSTANCE = new Unspecified();

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        public float getAlpha() {
            return Float.NaN;
        }

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        public Brush getBrush() {
            return null;
        }

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        public /* synthetic */ TextForegroundStyle merge(TextForegroundStyle textForegroundStyle) {
            return CC.$default$merge(this, textForegroundStyle);
        }

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        public /* synthetic */ TextForegroundStyle takeOrElse(Function0 function0) {
            return CC.$default$takeOrElse(this, function0);
        }

        private Unspecified() {
        }

        @Override // androidx.compose.ui.text.style.TextForegroundStyle
        /* JADX INFO: renamed from: getColor-0d7_KjU */
        public long mo2276getColor0d7_KjU() {
            return Color.Companion.m1309getUnspecified0d7_KjU();
        }
    }

    /* JADX INFO: compiled from: TextForegroundStyle.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        /* JADX INFO: renamed from: from-8_81llA, reason: not valid java name */
        public final TextForegroundStyle m2361from8_81llA(long j) {
            return j != 16 ? new ColorStyle(j, null) : Unspecified.INSTANCE;
        }

        public final TextForegroundStyle from(Brush brush, float f) {
            if (brush == null) {
                return Unspecified.INSTANCE;
            }
            if (brush instanceof SolidColor) {
                return m2361from8_81llA(TextDrawStyleKt.m2360modulateDxMtmZc(((SolidColor) brush).m1410getValue0d7_KjU(), f));
            }
            if (brush instanceof ShaderBrush) {
                return new BrushStyle((ShaderBrush) brush, f);
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}
