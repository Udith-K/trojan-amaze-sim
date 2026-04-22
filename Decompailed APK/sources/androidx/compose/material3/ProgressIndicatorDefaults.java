package androidx.compose.material3;

import androidx.compose.animation.core.SpringSpec;
import androidx.compose.material3.tokens.ProgressIndicatorTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;

/* JADX INFO: compiled from: ProgressIndicator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ProgressIndicatorDefaults {
    private static final int CircularDeterminateStrokeCap;
    private static final int CircularIndeterminateStrokeCap;
    private static final float CircularIndicatorTrackGapSize;
    private static final float CircularStrokeWidth;
    public static final ProgressIndicatorDefaults INSTANCE = new ProgressIndicatorDefaults();
    private static final float LinearIndicatorTrackGapSize;
    private static final int LinearStrokeCap;
    private static final float LinearTrackStopIndicatorSize;
    private static final SpringSpec ProgressAnimationSpec;

    private ProgressIndicatorDefaults() {
    }

    public final long getLinearColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-914312983, i, -1, "androidx.compose.material3.ProgressIndicatorDefaults.<get-linearColor> (ProgressIndicator.kt:843)");
        }
        long value = ColorSchemeKt.getValue(ProgressIndicatorTokens.INSTANCE.getActiveIndicatorColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final long getCircularColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1803349725, i, -1, "androidx.compose.material3.ProgressIndicatorDefaults.<get-circularColor> (ProgressIndicator.kt:847)");
        }
        long value = ColorSchemeKt.getValue(ProgressIndicatorTokens.INSTANCE.getActiveIndicatorColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final long getLinearTrackColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1677541593, i, -1, "androidx.compose.material3.ProgressIndicatorDefaults.<get-linearTrackColor> (ProgressIndicator.kt:851)");
        }
        long value = ColorSchemeKt.getValue(ProgressIndicatorTokens.INSTANCE.getTrackColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final long getCircularIndeterminateTrackColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1947901123, i, -1, "androidx.compose.material3.ProgressIndicatorDefaults.<get-circularIndeterminateTrackColor> (ProgressIndicator.kt:868)");
        }
        long jM1308getTransparent0d7_KjU = Color.Companion.m1308getTransparent0d7_KjU();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jM1308getTransparent0d7_KjU;
    }

    static {
        ProgressIndicatorTokens progressIndicatorTokens = ProgressIndicatorTokens.INSTANCE;
        CircularStrokeWidth = progressIndicatorTokens.m951getTrackThicknessD9Ej5fM();
        StrokeCap.Companion companion = StrokeCap.Companion;
        LinearStrokeCap = companion.m1416getRoundKaPHkGw();
        CircularDeterminateStrokeCap = companion.m1416getRoundKaPHkGw();
        CircularIndeterminateStrokeCap = companion.m1416getRoundKaPHkGw();
        LinearTrackStopIndicatorSize = progressIndicatorTokens.m950getStopSizeD9Ej5fM();
        LinearIndicatorTrackGapSize = progressIndicatorTokens.m948getActiveTrackSpaceD9Ej5fM();
        CircularIndicatorTrackGapSize = progressIndicatorTokens.m948getActiveTrackSpaceD9Ej5fM();
        ProgressAnimationSpec = new SpringSpec(1.0f, 50.0f, Float.valueOf(0.001f));
    }

    /* JADX INFO: renamed from: getCircularStrokeWidth-D9Ej5fM, reason: not valid java name */
    public final float m713getCircularStrokeWidthD9Ej5fM() {
        return CircularStrokeWidth;
    }

    /* JADX INFO: renamed from: getLinearStrokeCap-KaPHkGw, reason: not valid java name */
    public final int m715getLinearStrokeCapKaPHkGw() {
        return LinearStrokeCap;
    }

    /* JADX INFO: renamed from: getCircularIndeterminateStrokeCap-KaPHkGw, reason: not valid java name */
    public final int m712getCircularIndeterminateStrokeCapKaPHkGw() {
        return CircularIndeterminateStrokeCap;
    }

    /* JADX INFO: renamed from: getLinearIndicatorTrackGapSize-D9Ej5fM, reason: not valid java name */
    public final float m714getLinearIndicatorTrackGapSizeD9Ej5fM() {
        return LinearIndicatorTrackGapSize;
    }
}
