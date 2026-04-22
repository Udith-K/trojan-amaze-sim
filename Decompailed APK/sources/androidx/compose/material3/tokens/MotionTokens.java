package androidx.compose.material3.tokens;

import androidx.compose.animation.core.CubicBezierEasing;

/* JADX INFO: compiled from: MotionTokens.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MotionTokens {
    public static final MotionTokens INSTANCE = new MotionTokens();
    private static final CubicBezierEasing EasingEmphasizedCubicBezier = new CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f);
    private static final CubicBezierEasing EasingEmphasizedAccelerateCubicBezier = new CubicBezierEasing(0.3f, 0.0f, 0.8f, 0.15f);
    private static final CubicBezierEasing EasingEmphasizedDecelerateCubicBezier = new CubicBezierEasing(0.05f, 0.7f, 0.1f, 1.0f);
    private static final CubicBezierEasing EasingLegacyCubicBezier = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);
    private static final CubicBezierEasing EasingLegacyAccelerateCubicBezier = new CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f);
    private static final CubicBezierEasing EasingLegacyDecelerateCubicBezier = new CubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f);
    private static final CubicBezierEasing EasingLinearCubicBezier = new CubicBezierEasing(0.0f, 0.0f, 1.0f, 1.0f);
    private static final CubicBezierEasing EasingStandardCubicBezier = new CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f);
    private static final CubicBezierEasing EasingStandardAccelerateCubicBezier = new CubicBezierEasing(0.3f, 0.0f, 1.0f, 1.0f);
    private static final CubicBezierEasing EasingStandardDecelerateCubicBezier = new CubicBezierEasing(0.0f, 0.0f, 0.0f, 1.0f);

    private MotionTokens() {
    }

    public final CubicBezierEasing getEasingEmphasizedCubicBezier() {
        return EasingEmphasizedCubicBezier;
    }

    public final CubicBezierEasing getEasingLinearCubicBezier() {
        return EasingLinearCubicBezier;
    }
}
