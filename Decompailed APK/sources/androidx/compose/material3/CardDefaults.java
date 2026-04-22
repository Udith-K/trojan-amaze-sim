package androidx.compose.material3;

import androidx.compose.material3.tokens.ElevatedCardTokens;
import androidx.compose.material3.tokens.FilledCardTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;

/* JADX INFO: compiled from: Card.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CardDefaults {
    public static final CardDefaults INSTANCE = new CardDefaults();

    private CardDefaults() {
    }

    public final Shape getShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1266660211, i, -1, "androidx.compose.material3.CardDefaults.<get-shape> (Card.kt:376)");
        }
        Shape value = ShapesKt.getValue(FilledCardTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final Shape getElevatedShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-133496185, i, -1, "androidx.compose.material3.CardDefaults.<get-elevatedShape> (Card.kt:380)");
        }
        Shape value = ShapesKt.getValue(ElevatedCardTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: cardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m615cardElevationaqJV_2Y(float f, float f2, float f3, float f4, float f5, float f6, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = FilledCardTokens.INSTANCE.m886getContainerElevationD9Ej5fM();
        }
        if ((i2 & 2) != 0) {
            f2 = FilledCardTokens.INSTANCE.m891getPressedContainerElevationD9Ej5fM();
        }
        float f7 = f2;
        if ((i2 & 4) != 0) {
            f3 = FilledCardTokens.INSTANCE.m889getFocusContainerElevationD9Ej5fM();
        }
        float f8 = f3;
        if ((i2 & 8) != 0) {
            f4 = FilledCardTokens.INSTANCE.m890getHoverContainerElevationD9Ej5fM();
        }
        float f9 = f4;
        if ((i2 & 16) != 0) {
            f5 = FilledCardTokens.INSTANCE.m888getDraggedContainerElevationD9Ej5fM();
        }
        float f10 = f5;
        if ((i2 & 32) != 0) {
            f6 = FilledCardTokens.INSTANCE.m887getDisabledContainerElevationD9Ej5fM();
        }
        float f11 = f6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-574898487, i, -1, "androidx.compose.material3.CardDefaults.cardElevation (Card.kt:405)");
        }
        CardElevation cardElevation = new CardElevation(f, f7, f8, f9, f10, f11, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cardElevation;
    }

    /* JADX INFO: renamed from: elevatedCardElevation-aqJV_2Y, reason: not valid java name */
    public final CardElevation m616elevatedCardElevationaqJV_2Y(float f, float f2, float f3, float f4, float f5, float f6, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            f = ElevatedCardTokens.INSTANCE.m862getContainerElevationD9Ej5fM();
        }
        if ((i2 & 2) != 0) {
            f2 = ElevatedCardTokens.INSTANCE.m867getPressedContainerElevationD9Ej5fM();
        }
        float f7 = f2;
        if ((i2 & 4) != 0) {
            f3 = ElevatedCardTokens.INSTANCE.m865getFocusContainerElevationD9Ej5fM();
        }
        float f8 = f3;
        if ((i2 & 8) != 0) {
            f4 = ElevatedCardTokens.INSTANCE.m866getHoverContainerElevationD9Ej5fM();
        }
        float f9 = f4;
        if ((i2 & 16) != 0) {
            f5 = ElevatedCardTokens.INSTANCE.m864getDraggedContainerElevationD9Ej5fM();
        }
        float f10 = f5;
        if ((i2 & 32) != 0) {
            f6 = ElevatedCardTokens.INSTANCE.m863getDisabledContainerElevationD9Ej5fM();
        }
        float f11 = f6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1154241939, i, -1, "androidx.compose.material3.CardDefaults.elevatedCardElevation (Card.kt:434)");
        }
        CardElevation cardElevation = new CardElevation(f, f7, f8, f9, f10, f11, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cardElevation;
    }

    public final CardColors cardColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1876034303, i, -1, "androidx.compose.material3.CardDefaults.cardColors (Card.kt:476)");
        }
        CardColors defaultCardColors$material3_release = getDefaultCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultCardColors$material3_release;
    }

    public final CardColors getDefaultCardColors$material3_release(ColorScheme colorScheme) {
        CardColors defaultCardColorsCached$material3_release = colorScheme.getDefaultCardColorsCached$material3_release();
        if (defaultCardColorsCached$material3_release != null) {
            return defaultCardColorsCached$material3_release;
        }
        FilledCardTokens filledCardTokens = FilledCardTokens.INSTANCE;
        CardColors cardColors = new CardColors(ColorSchemeKt.fromToken(colorScheme, filledCardTokens.getContainerColor()), ColorSchemeKt.m656contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, filledCardTokens.getContainerColor())), ColorKt.m1313compositeOverOWjLjI(Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, filledCardTokens.getDisabledContainerColor()), filledCardTokens.getDisabledContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, filledCardTokens.getContainerColor())), Color.m1294copywmQWz5c$default(ColorSchemeKt.m656contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, filledCardTokens.getContainerColor())), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultCardColorsCached$material3_release(cardColors);
        return cardColors;
    }

    public final CardColors elevatedCardColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1610137975, i, -1, "androidx.compose.material3.CardDefaults.elevatedCardColors (Card.kt:522)");
        }
        CardColors defaultElevatedCardColors$material3_release = getDefaultElevatedCardColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultElevatedCardColors$material3_release;
    }

    public final CardColors getDefaultElevatedCardColors$material3_release(ColorScheme colorScheme) {
        CardColors defaultElevatedCardColorsCached$material3_release = colorScheme.getDefaultElevatedCardColorsCached$material3_release();
        if (defaultElevatedCardColorsCached$material3_release != null) {
            return defaultElevatedCardColorsCached$material3_release;
        }
        ElevatedCardTokens elevatedCardTokens = ElevatedCardTokens.INSTANCE;
        CardColors cardColors = new CardColors(ColorSchemeKt.fromToken(colorScheme, elevatedCardTokens.getContainerColor()), ColorSchemeKt.m656contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, elevatedCardTokens.getContainerColor())), ColorKt.m1313compositeOverOWjLjI(Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, elevatedCardTokens.getDisabledContainerColor()), elevatedCardTokens.getDisabledContainerOpacity(), 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, elevatedCardTokens.getDisabledContainerColor())), Color.m1294copywmQWz5c$default(ColorSchemeKt.m656contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, elevatedCardTokens.getContainerColor())), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultElevatedCardColorsCached$material3_release(cardColors);
        return cardColors;
    }
}
