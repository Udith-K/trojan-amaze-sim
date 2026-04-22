package androidx.compose.ui.text.android;

import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;

/* JADX INFO: compiled from: BoringLayoutFactory.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BoringLayoutFactory {
    public static final BoringLayoutFactory INSTANCE = new BoringLayoutFactory();

    private BoringLayoutFactory() {
    }

    public final BoringLayout.Metrics measure(CharSequence charSequence, TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic) {
        if (Build.VERSION.SDK_INT >= 33) {
            return BoringLayoutFactory33.isBoring(charSequence, textPaint, textDirectionHeuristic);
        }
        return BoringLayoutFactoryDefault.isBoring(charSequence, textPaint, textDirectionHeuristic);
    }

    public final BoringLayout create(CharSequence charSequence, TextPaint textPaint, int i, BoringLayout.Metrics metrics, Layout.Alignment alignment, boolean z, boolean z2, TextUtils.TruncateAt truncateAt, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("negative width");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("negative ellipsized width");
        }
        if (Build.VERSION.SDK_INT >= 33) {
            return BoringLayoutFactory33.create(charSequence, textPaint, i, alignment, 1.0f, 0.0f, metrics, z, z2, truncateAt, i2);
        }
        return BoringLayoutFactoryDefault.create(charSequence, textPaint, i, alignment, 1.0f, 0.0f, metrics, z, truncateAt, i2);
    }

    public final boolean isFallbackLineSpacingEnabled(BoringLayout boringLayout) {
        if (Build.VERSION.SDK_INT >= 33) {
            return BoringLayoutFactory33.isFallbackLineSpacingEnabled(boringLayout);
        }
        return false;
    }
}
