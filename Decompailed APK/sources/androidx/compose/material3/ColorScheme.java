package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ColorScheme.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ColorScheme {
    private final long background;
    private ButtonColors defaultButtonColorsCached;
    private CardColors defaultCardColorsCached;
    private CardColors defaultElevatedCardColorsCached;
    private IconButtonColors defaultIconButtonColorsCached;
    private MenuItemColors defaultMenuItemColorsCached;
    private ButtonColors defaultOutlinedButtonColorsCached;
    private TextFieldColors defaultOutlinedTextFieldColorsCached;
    private SwitchColors defaultSwitchColorsCached;
    private TextFieldColors defaultTextFieldColorsCached;
    private TopAppBarColors defaultTopAppBarColorsCached;
    private final long error;
    private final long errorContainer;
    private final long inverseOnSurface;
    private final long inversePrimary;
    private final long inverseSurface;
    private final long onBackground;
    private final long onError;
    private final long onErrorContainer;
    private final long onPrimary;
    private final long onPrimaryContainer;
    private final long onSecondary;
    private final long onSecondaryContainer;
    private final long onSurface;
    private final long onSurfaceVariant;
    private final long onTertiary;
    private final long onTertiaryContainer;
    private final long outline;
    private final long outlineVariant;
    private final long primary;
    private final long primaryContainer;
    private final long scrim;
    private final long secondary;
    private final long secondaryContainer;
    private final long surface;
    private final long surfaceBright;
    private final long surfaceContainer;
    private final long surfaceContainerHigh;
    private final long surfaceContainerHighest;
    private final long surfaceContainerLow;
    private final long surfaceContainerLowest;
    private final long surfaceDim;
    private final long surfaceTint;
    private final long surfaceVariant;
    private final long tertiary;
    private final long tertiaryContainer;

    public /* synthetic */ ColorScheme(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36);
    }

    private ColorScheme(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36) {
        this.primary = j;
        this.onPrimary = j2;
        this.primaryContainer = j3;
        this.onPrimaryContainer = j4;
        this.inversePrimary = j5;
        this.secondary = j6;
        this.onSecondary = j7;
        this.secondaryContainer = j8;
        this.onSecondaryContainer = j9;
        this.tertiary = j10;
        this.onTertiary = j11;
        this.tertiaryContainer = j12;
        this.onTertiaryContainer = j13;
        this.background = j14;
        this.onBackground = j15;
        this.surface = j16;
        this.onSurface = j17;
        this.surfaceVariant = j18;
        this.onSurfaceVariant = j19;
        this.surfaceTint = j20;
        this.inverseSurface = j21;
        this.inverseOnSurface = j22;
        this.error = j23;
        this.onError = j24;
        this.errorContainer = j25;
        this.onErrorContainer = j26;
        this.outline = j27;
        this.outlineVariant = j28;
        this.scrim = j29;
        this.surfaceBright = j30;
        this.surfaceDim = j31;
        this.surfaceContainer = j32;
        this.surfaceContainerHigh = j33;
        this.surfaceContainerHighest = j34;
        this.surfaceContainerLow = j35;
        this.surfaceContainerLowest = j36;
    }

    /* JADX INFO: renamed from: getPrimary-0d7_KjU, reason: not valid java name */
    public final long m638getPrimary0d7_KjU() {
        return this.primary;
    }

    /* JADX INFO: renamed from: getOnPrimary-0d7_KjU, reason: not valid java name */
    public final long m628getOnPrimary0d7_KjU() {
        return this.onPrimary;
    }

    /* JADX INFO: renamed from: getPrimaryContainer-0d7_KjU, reason: not valid java name */
    public final long m639getPrimaryContainer0d7_KjU() {
        return this.primaryContainer;
    }

    /* JADX INFO: renamed from: getOnPrimaryContainer-0d7_KjU, reason: not valid java name */
    public final long m629getOnPrimaryContainer0d7_KjU() {
        return this.onPrimaryContainer;
    }

    /* JADX INFO: renamed from: getInversePrimary-0d7_KjU, reason: not valid java name */
    public final long m623getInversePrimary0d7_KjU() {
        return this.inversePrimary;
    }

    /* JADX INFO: renamed from: getSecondary-0d7_KjU, reason: not valid java name */
    public final long m641getSecondary0d7_KjU() {
        return this.secondary;
    }

    /* JADX INFO: renamed from: getOnSecondary-0d7_KjU, reason: not valid java name */
    public final long m630getOnSecondary0d7_KjU() {
        return this.onSecondary;
    }

    /* JADX INFO: renamed from: getSecondaryContainer-0d7_KjU, reason: not valid java name */
    public final long m642getSecondaryContainer0d7_KjU() {
        return this.secondaryContainer;
    }

    /* JADX INFO: renamed from: getOnSecondaryContainer-0d7_KjU, reason: not valid java name */
    public final long m631getOnSecondaryContainer0d7_KjU() {
        return this.onSecondaryContainer;
    }

    /* JADX INFO: renamed from: getTertiary-0d7_KjU, reason: not valid java name */
    public final long m653getTertiary0d7_KjU() {
        return this.tertiary;
    }

    /* JADX INFO: renamed from: getOnTertiary-0d7_KjU, reason: not valid java name */
    public final long m634getOnTertiary0d7_KjU() {
        return this.onTertiary;
    }

    /* JADX INFO: renamed from: getTertiaryContainer-0d7_KjU, reason: not valid java name */
    public final long m654getTertiaryContainer0d7_KjU() {
        return this.tertiaryContainer;
    }

    /* JADX INFO: renamed from: getOnTertiaryContainer-0d7_KjU, reason: not valid java name */
    public final long m635getOnTertiaryContainer0d7_KjU() {
        return this.onTertiaryContainer;
    }

    /* JADX INFO: renamed from: getBackground-0d7_KjU, reason: not valid java name */
    public final long m619getBackground0d7_KjU() {
        return this.background;
    }

    /* JADX INFO: renamed from: getOnBackground-0d7_KjU, reason: not valid java name */
    public final long m625getOnBackground0d7_KjU() {
        return this.onBackground;
    }

    /* JADX INFO: renamed from: getSurface-0d7_KjU, reason: not valid java name */
    public final long m643getSurface0d7_KjU() {
        return this.surface;
    }

    /* JADX INFO: renamed from: getOnSurface-0d7_KjU, reason: not valid java name */
    public final long m632getOnSurface0d7_KjU() {
        return this.onSurface;
    }

    /* JADX INFO: renamed from: getSurfaceVariant-0d7_KjU, reason: not valid java name */
    public final long m652getSurfaceVariant0d7_KjU() {
        return this.surfaceVariant;
    }

    /* JADX INFO: renamed from: getOnSurfaceVariant-0d7_KjU, reason: not valid java name */
    public final long m633getOnSurfaceVariant0d7_KjU() {
        return this.onSurfaceVariant;
    }

    /* JADX INFO: renamed from: getSurfaceTint-0d7_KjU, reason: not valid java name */
    public final long m651getSurfaceTint0d7_KjU() {
        return this.surfaceTint;
    }

    /* JADX INFO: renamed from: getInverseSurface-0d7_KjU, reason: not valid java name */
    public final long m624getInverseSurface0d7_KjU() {
        return this.inverseSurface;
    }

    /* JADX INFO: renamed from: getInverseOnSurface-0d7_KjU, reason: not valid java name */
    public final long m622getInverseOnSurface0d7_KjU() {
        return this.inverseOnSurface;
    }

    /* JADX INFO: renamed from: getError-0d7_KjU, reason: not valid java name */
    public final long m620getError0d7_KjU() {
        return this.error;
    }

    /* JADX INFO: renamed from: getOnError-0d7_KjU, reason: not valid java name */
    public final long m626getOnError0d7_KjU() {
        return this.onError;
    }

    /* JADX INFO: renamed from: getErrorContainer-0d7_KjU, reason: not valid java name */
    public final long m621getErrorContainer0d7_KjU() {
        return this.errorContainer;
    }

    /* JADX INFO: renamed from: getOnErrorContainer-0d7_KjU, reason: not valid java name */
    public final long m627getOnErrorContainer0d7_KjU() {
        return this.onErrorContainer;
    }

    /* JADX INFO: renamed from: getOutline-0d7_KjU, reason: not valid java name */
    public final long m636getOutline0d7_KjU() {
        return this.outline;
    }

    /* JADX INFO: renamed from: getOutlineVariant-0d7_KjU, reason: not valid java name */
    public final long m637getOutlineVariant0d7_KjU() {
        return this.outlineVariant;
    }

    /* JADX INFO: renamed from: getScrim-0d7_KjU, reason: not valid java name */
    public final long m640getScrim0d7_KjU() {
        return this.scrim;
    }

    /* JADX INFO: renamed from: getSurfaceBright-0d7_KjU, reason: not valid java name */
    public final long m644getSurfaceBright0d7_KjU() {
        return this.surfaceBright;
    }

    /* JADX INFO: renamed from: getSurfaceDim-0d7_KjU, reason: not valid java name */
    public final long m650getSurfaceDim0d7_KjU() {
        return this.surfaceDim;
    }

    /* JADX INFO: renamed from: getSurfaceContainer-0d7_KjU, reason: not valid java name */
    public final long m645getSurfaceContainer0d7_KjU() {
        return this.surfaceContainer;
    }

    /* JADX INFO: renamed from: getSurfaceContainerHigh-0d7_KjU, reason: not valid java name */
    public final long m646getSurfaceContainerHigh0d7_KjU() {
        return this.surfaceContainerHigh;
    }

    /* JADX INFO: renamed from: getSurfaceContainerHighest-0d7_KjU, reason: not valid java name */
    public final long m647getSurfaceContainerHighest0d7_KjU() {
        return this.surfaceContainerHighest;
    }

    /* JADX INFO: renamed from: getSurfaceContainerLow-0d7_KjU, reason: not valid java name */
    public final long m648getSurfaceContainerLow0d7_KjU() {
        return this.surfaceContainerLow;
    }

    /* JADX INFO: renamed from: getSurfaceContainerLowest-0d7_KjU, reason: not valid java name */
    public final long m649getSurfaceContainerLowest0d7_KjU() {
        return this.surfaceContainerLowest;
    }

    /* JADX INFO: renamed from: copy-C-Xl9yA, reason: not valid java name */
    public final ColorScheme m618copyCXl9yA(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36) {
        return new ColorScheme(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, null);
    }

    public String toString() {
        return "ColorScheme(primary=" + ((Object) Color.m1303toStringimpl(this.primary)) + "onPrimary=" + ((Object) Color.m1303toStringimpl(this.onPrimary)) + "primaryContainer=" + ((Object) Color.m1303toStringimpl(this.primaryContainer)) + "onPrimaryContainer=" + ((Object) Color.m1303toStringimpl(this.onPrimaryContainer)) + "inversePrimary=" + ((Object) Color.m1303toStringimpl(this.inversePrimary)) + "secondary=" + ((Object) Color.m1303toStringimpl(this.secondary)) + "onSecondary=" + ((Object) Color.m1303toStringimpl(this.onSecondary)) + "secondaryContainer=" + ((Object) Color.m1303toStringimpl(this.secondaryContainer)) + "onSecondaryContainer=" + ((Object) Color.m1303toStringimpl(this.onSecondaryContainer)) + "tertiary=" + ((Object) Color.m1303toStringimpl(this.tertiary)) + "onTertiary=" + ((Object) Color.m1303toStringimpl(this.onTertiary)) + "tertiaryContainer=" + ((Object) Color.m1303toStringimpl(this.tertiaryContainer)) + "onTertiaryContainer=" + ((Object) Color.m1303toStringimpl(this.onTertiaryContainer)) + "background=" + ((Object) Color.m1303toStringimpl(this.background)) + "onBackground=" + ((Object) Color.m1303toStringimpl(this.onBackground)) + "surface=" + ((Object) Color.m1303toStringimpl(this.surface)) + "onSurface=" + ((Object) Color.m1303toStringimpl(this.onSurface)) + "surfaceVariant=" + ((Object) Color.m1303toStringimpl(this.surfaceVariant)) + "onSurfaceVariant=" + ((Object) Color.m1303toStringimpl(this.onSurfaceVariant)) + "surfaceTint=" + ((Object) Color.m1303toStringimpl(this.surfaceTint)) + "inverseSurface=" + ((Object) Color.m1303toStringimpl(this.inverseSurface)) + "inverseOnSurface=" + ((Object) Color.m1303toStringimpl(this.inverseOnSurface)) + "error=" + ((Object) Color.m1303toStringimpl(this.error)) + "onError=" + ((Object) Color.m1303toStringimpl(this.onError)) + "errorContainer=" + ((Object) Color.m1303toStringimpl(this.errorContainer)) + "onErrorContainer=" + ((Object) Color.m1303toStringimpl(this.onErrorContainer)) + "outline=" + ((Object) Color.m1303toStringimpl(this.outline)) + "outlineVariant=" + ((Object) Color.m1303toStringimpl(this.outlineVariant)) + "scrim=" + ((Object) Color.m1303toStringimpl(this.scrim)) + "surfaceBright=" + ((Object) Color.m1303toStringimpl(this.surfaceBright)) + "surfaceDim=" + ((Object) Color.m1303toStringimpl(this.surfaceDim)) + "surfaceContainer=" + ((Object) Color.m1303toStringimpl(this.surfaceContainer)) + "surfaceContainerHigh=" + ((Object) Color.m1303toStringimpl(this.surfaceContainerHigh)) + "surfaceContainerHighest=" + ((Object) Color.m1303toStringimpl(this.surfaceContainerHighest)) + "surfaceContainerLow=" + ((Object) Color.m1303toStringimpl(this.surfaceContainerLow)) + "surfaceContainerLowest=" + ((Object) Color.m1303toStringimpl(this.surfaceContainerLowest)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public final ButtonColors getDefaultButtonColorsCached$material3_release() {
        return this.defaultButtonColorsCached;
    }

    public final void setDefaultButtonColorsCached$material3_release(ButtonColors buttonColors) {
        this.defaultButtonColorsCached = buttonColors;
    }

    public final ButtonColors getDefaultOutlinedButtonColorsCached$material3_release() {
        return this.defaultOutlinedButtonColorsCached;
    }

    public final void setDefaultOutlinedButtonColorsCached$material3_release(ButtonColors buttonColors) {
        this.defaultOutlinedButtonColorsCached = buttonColors;
    }

    public final CardColors getDefaultCardColorsCached$material3_release() {
        return this.defaultCardColorsCached;
    }

    public final void setDefaultCardColorsCached$material3_release(CardColors cardColors) {
        this.defaultCardColorsCached = cardColors;
    }

    public final CardColors getDefaultElevatedCardColorsCached$material3_release() {
        return this.defaultElevatedCardColorsCached;
    }

    public final void setDefaultElevatedCardColorsCached$material3_release(CardColors cardColors) {
        this.defaultElevatedCardColorsCached = cardColors;
    }

    public final TopAppBarColors getDefaultTopAppBarColorsCached$material3_release() {
        return this.defaultTopAppBarColorsCached;
    }

    public final void setDefaultTopAppBarColorsCached$material3_release(TopAppBarColors topAppBarColors) {
        this.defaultTopAppBarColorsCached = topAppBarColors;
    }

    public final IconButtonColors getDefaultIconButtonColorsCached$material3_release() {
        return this.defaultIconButtonColorsCached;
    }

    public final void setDefaultIconButtonColorsCached$material3_release(IconButtonColors iconButtonColors) {
        this.defaultIconButtonColorsCached = iconButtonColors;
    }

    public final MenuItemColors getDefaultMenuItemColorsCached$material3_release() {
        return this.defaultMenuItemColorsCached;
    }

    public final void setDefaultMenuItemColorsCached$material3_release(MenuItemColors menuItemColors) {
        this.defaultMenuItemColorsCached = menuItemColors;
    }

    public final SwitchColors getDefaultSwitchColorsCached$material3_release() {
        return this.defaultSwitchColorsCached;
    }

    public final void setDefaultSwitchColorsCached$material3_release(SwitchColors switchColors) {
        this.defaultSwitchColorsCached = switchColors;
    }

    public final TextFieldColors getDefaultOutlinedTextFieldColorsCached$material3_release() {
        return this.defaultOutlinedTextFieldColorsCached;
    }

    public final void setDefaultOutlinedTextFieldColorsCached$material3_release(TextFieldColors textFieldColors) {
        this.defaultOutlinedTextFieldColorsCached = textFieldColors;
    }

    public final TextFieldColors getDefaultTextFieldColorsCached$material3_release() {
        return this.defaultTextFieldColorsCached;
    }

    public final void setDefaultTextFieldColorsCached$material3_release(TextFieldColors textFieldColors) {
        this.defaultTextFieldColorsCached = textFieldColors;
    }
}
