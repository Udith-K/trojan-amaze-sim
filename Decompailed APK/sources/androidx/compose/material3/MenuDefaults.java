package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.ListTokens;
import androidx.compose.material3.tokens.MenuTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: Menu.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MenuDefaults {
    public static final MenuDefaults INSTANCE = new MenuDefaults();
    private static final float TonalElevation = ElevationTokens.INSTANCE.m868getLevel0D9Ej5fM();
    private static final float ShadowElevation = MenuTokens.INSTANCE.m896getContainerElevationD9Ej5fM();
    private static final PaddingValues DropdownMenuItemContentPadding = PaddingKt.m258PaddingValuesYgX7TsA(MenuKt.DropdownMenuItemHorizontalPadding, Dp.m2416constructorimpl(0));

    private MenuDefaults() {
    }

    /* JADX INFO: renamed from: getTonalElevation-D9Ej5fM, reason: not valid java name */
    public final float m695getTonalElevationD9Ej5fM() {
        return TonalElevation;
    }

    /* JADX INFO: renamed from: getShadowElevation-D9Ej5fM, reason: not valid java name */
    public final float m694getShadowElevationD9Ej5fM() {
        return ShadowElevation;
    }

    public final Shape getShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(218702739, i, -1, "androidx.compose.material3.MenuDefaults.<get-shape> (Menu.kt:194)");
        }
        Shape value = ShapesKt.getValue(MenuTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final long getContainerColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1787427929, i, -1, "androidx.compose.material3.MenuDefaults.<get-containerColor> (Menu.kt:198)");
        }
        long value = ColorSchemeKt.getValue(MenuTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final MenuItemColors itemColors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1326531516, i, -1, "androidx.compose.material3.MenuDefaults.itemColors (Menu.kt:204)");
        }
        MenuItemColors defaultMenuItemColors$material3_release = getDefaultMenuItemColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultMenuItemColors$material3_release;
    }

    public final MenuItemColors getDefaultMenuItemColors$material3_release(ColorScheme colorScheme) {
        MenuItemColors defaultMenuItemColorsCached$material3_release = colorScheme.getDefaultMenuItemColorsCached$material3_release();
        if (defaultMenuItemColorsCached$material3_release != null) {
            return defaultMenuItemColorsCached$material3_release;
        }
        ListTokens listTokens = ListTokens.INSTANCE;
        MenuItemColors menuItemColors = new MenuItemColors(ColorSchemeKt.fromToken(colorScheme, listTokens.getListItemLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, listTokens.getListItemLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, listTokens.getListItemTrailingIconColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, listTokens.getListItemDisabledLabelTextColor()), listTokens.getListItemDisabledLabelTextOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, listTokens.getListItemDisabledLeadingIconColor()), listTokens.getListItemDisabledLeadingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, listTokens.getListItemDisabledTrailingIconColor()), listTokens.getListItemDisabledTrailingIconOpacity(), 0.0f, 0.0f, 0.0f, 14, null), null);
        colorScheme.setDefaultMenuItemColorsCached$material3_release(menuItemColors);
        return menuItemColors;
    }

    public final PaddingValues getDropdownMenuItemContentPadding() {
        return DropdownMenuItemContentPadding;
    }
}
