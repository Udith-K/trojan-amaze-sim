package androidx.compose.ui.text.android;

import android.os.Build;
import android.text.StaticLayout;

/* JADX INFO: compiled from: StaticLayoutFactory.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class StaticLayoutFactory23 implements StaticLayoutFactoryImpl {
    @Override // androidx.compose.ui.text.android.StaticLayoutFactoryImpl
    public StaticLayout create(StaticLayoutParams staticLayoutParams) {
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(staticLayoutParams.getText(), staticLayoutParams.getStart(), staticLayoutParams.getEnd(), staticLayoutParams.getPaint(), staticLayoutParams.getWidth());
        builderObtain.setTextDirection(staticLayoutParams.getTextDir());
        builderObtain.setAlignment(staticLayoutParams.getAlignment());
        builderObtain.setMaxLines(staticLayoutParams.getMaxLines());
        builderObtain.setEllipsize(staticLayoutParams.getEllipsize());
        builderObtain.setEllipsizedWidth(staticLayoutParams.getEllipsizedWidth());
        builderObtain.setLineSpacing(staticLayoutParams.getLineSpacingExtra(), staticLayoutParams.getLineSpacingMultiplier());
        builderObtain.setIncludePad(staticLayoutParams.getIncludePadding());
        builderObtain.setBreakStrategy(staticLayoutParams.getBreakStrategy());
        builderObtain.setHyphenationFrequency(staticLayoutParams.getHyphenationFrequency());
        builderObtain.setIndents(staticLayoutParams.getLeftIndents(), staticLayoutParams.getRightIndents());
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            StaticLayoutFactory26.setJustificationMode(builderObtain, staticLayoutParams.getJustificationMode());
        }
        if (i >= 28) {
            StaticLayoutFactory28.setUseLineSpacingFromFallbacks(builderObtain, staticLayoutParams.getUseFallbackLineSpacing());
        }
        if (i >= 33) {
            StaticLayoutFactory33.setLineBreakConfig(builderObtain, staticLayoutParams.getLineBreakStyle(), staticLayoutParams.getLineBreakWordStyle());
        }
        return builderObtain.build();
    }

    @Override // androidx.compose.ui.text.android.StaticLayoutFactoryImpl
    public boolean isFallbackLineSpacingEnabled(StaticLayout staticLayout, boolean z) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 33) {
            return StaticLayoutFactory33.isFallbackLineSpacingEnabled(staticLayout);
        }
        if (i >= 28) {
            return z;
        }
        return false;
    }
}
