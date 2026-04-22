package org.fdroid.fdroid.ui.theme;

import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: Theme.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a1\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\"\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n\"\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\n\"\u0014\u0010\f\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"", "darkTheme", "pureBlack", "Lkotlin/Function0;", "", "content", "FDroidContent", "(ZZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/material3/ColorScheme;", "lightScheme", "Landroidx/compose/material3/ColorScheme;", "darkScheme", "pureBlackScheme", "app_fullRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ThemeKt {
    private static final ColorScheme darkScheme;
    private static final ColorScheme lightScheme;
    private static final ColorScheme pureBlackScheme;

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FDroidContent$lambda$0(boolean z, boolean z2, Function2 function2, int i, int i2, Composer composer, int i3) {
        FDroidContent(z, z2, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static {
        long primaryLight = ColorKt.getPrimaryLight();
        long onPrimaryLight = ColorKt.getOnPrimaryLight();
        long primaryContainerLight = ColorKt.getPrimaryContainerLight();
        long onPrimaryContainerLight = ColorKt.getOnPrimaryContainerLight();
        long secondaryLight = ColorKt.getSecondaryLight();
        long onSecondaryLight = ColorKt.getOnSecondaryLight();
        long secondaryContainerLight = ColorKt.getSecondaryContainerLight();
        long onSecondaryContainerLight = ColorKt.getOnSecondaryContainerLight();
        long tertiaryLight = ColorKt.getTertiaryLight();
        long onTertiaryLight = ColorKt.getOnTertiaryLight();
        long tertiaryContainerLight = ColorKt.getTertiaryContainerLight();
        long onTertiaryContainerLight = ColorKt.getOnTertiaryContainerLight();
        long errorLight = ColorKt.getErrorLight();
        long onErrorLight = ColorKt.getOnErrorLight();
        long errorContainerLight = ColorKt.getErrorContainerLight();
        long onErrorContainerLight = ColorKt.getOnErrorContainerLight();
        long backgroundLight = ColorKt.getBackgroundLight();
        long onBackgroundLight = ColorKt.getOnBackgroundLight();
        long surfaceLight = ColorKt.getSurfaceLight();
        long onSurfaceLight = ColorKt.getOnSurfaceLight();
        long surfaceVariantLight = ColorKt.getSurfaceVariantLight();
        long onSurfaceVariantLight = ColorKt.getOnSurfaceVariantLight();
        long outlineLight = ColorKt.getOutlineLight();
        long outlineVariantLight = ColorKt.getOutlineVariantLight();
        long scrimLight = ColorKt.getScrimLight();
        long inverseSurfaceLight = ColorKt.getInverseSurfaceLight();
        long inverseOnSurfaceLight = ColorKt.getInverseOnSurfaceLight();
        long inversePrimaryLight = ColorKt.getInversePrimaryLight();
        long surfaceDimLight = ColorKt.getSurfaceDimLight();
        long surfaceBrightLight = ColorKt.getSurfaceBrightLight();
        long surfaceContainerLowestLight = ColorKt.getSurfaceContainerLowestLight();
        lightScheme = ColorSchemeKt.m661lightColorSchemeCXl9yA$default(primaryLight, onPrimaryLight, primaryContainerLight, onPrimaryContainerLight, inversePrimaryLight, secondaryLight, onSecondaryLight, secondaryContainerLight, onSecondaryContainerLight, tertiaryLight, onTertiaryLight, tertiaryContainerLight, onTertiaryContainerLight, backgroundLight, onBackgroundLight, surfaceLight, onSurfaceLight, surfaceVariantLight, onSurfaceVariantLight, 0L, inverseSurfaceLight, inverseOnSurfaceLight, errorLight, onErrorLight, errorContainerLight, onErrorContainerLight, outlineLight, outlineVariantLight, scrimLight, surfaceBrightLight, ColorKt.getSurfaceContainerLight(), ColorKt.getSurfaceContainerHighLight(), ColorKt.getSurfaceContainerHighestLight(), ColorKt.getSurfaceContainerLowLight(), surfaceContainerLowestLight, surfaceDimLight, PKIFailureInfo.signerNotTrusted, 0, null);
        long primaryDark = ColorKt.getPrimaryDark();
        long onPrimaryDark = ColorKt.getOnPrimaryDark();
        long primaryContainerDark = ColorKt.getPrimaryContainerDark();
        long onPrimaryContainerDark = ColorKt.getOnPrimaryContainerDark();
        long secondaryDark = ColorKt.getSecondaryDark();
        long onSecondaryDark = ColorKt.getOnSecondaryDark();
        long secondaryContainerDark = ColorKt.getSecondaryContainerDark();
        long onSecondaryContainerDark = ColorKt.getOnSecondaryContainerDark();
        long tertiaryDark = ColorKt.getTertiaryDark();
        long onTertiaryDark = ColorKt.getOnTertiaryDark();
        long tertiaryContainerDark = ColorKt.getTertiaryContainerDark();
        long onTertiaryContainerDark = ColorKt.getOnTertiaryContainerDark();
        long errorDark = ColorKt.getErrorDark();
        long onErrorDark = ColorKt.getOnErrorDark();
        long errorContainerDark = ColorKt.getErrorContainerDark();
        long onErrorContainerDark = ColorKt.getOnErrorContainerDark();
        long backgroundDark = ColorKt.getBackgroundDark();
        long onBackgroundDark = ColorKt.getOnBackgroundDark();
        long surfaceDark = ColorKt.getSurfaceDark();
        long onSurfaceDark = ColorKt.getOnSurfaceDark();
        long surfaceVariantDark = ColorKt.getSurfaceVariantDark();
        long onSurfaceVariantDark = ColorKt.getOnSurfaceVariantDark();
        long outlineDark = ColorKt.getOutlineDark();
        long outlineVariantDark = ColorKt.getOutlineVariantDark();
        long scrimDark = ColorKt.getScrimDark();
        long inverseSurfaceDark = ColorKt.getInverseSurfaceDark();
        long inverseOnSurfaceDark = ColorKt.getInverseOnSurfaceDark();
        long inversePrimaryDark = ColorKt.getInversePrimaryDark();
        long surfaceDimDark = ColorKt.getSurfaceDimDark();
        long surfaceBrightDark = ColorKt.getSurfaceBrightDark();
        long surfaceContainerLowestDark = ColorKt.getSurfaceContainerLowestDark();
        ColorScheme colorSchemeM659darkColorSchemeCXl9yA$default = ColorSchemeKt.m659darkColorSchemeCXl9yA$default(primaryDark, onPrimaryDark, primaryContainerDark, onPrimaryContainerDark, inversePrimaryDark, secondaryDark, onSecondaryDark, secondaryContainerDark, onSecondaryContainerDark, tertiaryDark, onTertiaryDark, tertiaryContainerDark, onTertiaryContainerDark, backgroundDark, onBackgroundDark, surfaceDark, onSurfaceDark, surfaceVariantDark, onSurfaceVariantDark, 0L, inverseSurfaceDark, inverseOnSurfaceDark, errorDark, onErrorDark, errorContainerDark, onErrorContainerDark, outlineDark, outlineVariantDark, scrimDark, surfaceBrightDark, ColorKt.getSurfaceContainerDark(), ColorKt.getSurfaceContainerHighDark(), ColorKt.getSurfaceContainerHighestDark(), ColorKt.getSurfaceContainerLowDark(), surfaceContainerLowestDark, surfaceDimDark, PKIFailureInfo.signerNotTrusted, 0, null);
        darkScheme = colorSchemeM659darkColorSchemeCXl9yA$default;
        pureBlackScheme = colorSchemeM659darkColorSchemeCXl9yA$default.m618copyCXl9yA(((-40961) & 1) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.primary : 0L, ((-40961) & 2) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.onPrimary : 0L, ((-40961) & 4) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.primaryContainer : 0L, ((-40961) & 8) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.onPrimaryContainer : 0L, ((-40961) & 16) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.inversePrimary : 0L, ((-40961) & 32) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.secondary : 0L, ((-40961) & 64) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.onSecondary : 0L, ((-40961) & 128) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.secondaryContainer : 0L, ((-40961) & 256) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.onSecondaryContainer : 0L, ((-40961) & 512) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.tertiary : 0L, ((-40961) & 1024) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.onTertiary : 0L, ((-40961) & 2048) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.tertiaryContainer : 0L, ((-40961) & PKIFailureInfo.certConfirmed) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.onTertiaryContainer : 0L, ((-40961) & 8192) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.background : Color.Companion.m1305getBlack0d7_KjU(), ((-40961) & 16384) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.onBackground : 0L, ((-40961) & 32768) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.surface : androidx.compose.ui.graphics.ColorKt.Color(4280163870L), ((-40961) & PKIFailureInfo.notAuthorized) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.onSurface : 0L, ((-40961) & PKIFailureInfo.unsupportedVersion) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.surfaceVariant : 0L, ((-40961) & PKIFailureInfo.transactionIdInUse) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.onSurfaceVariant : 0L, ((-40961) & PKIFailureInfo.signerNotTrusted) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.surfaceTint : 0L, ((-40961) & PKIFailureInfo.badCertTemplate) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.inverseSurface : 0L, ((-40961) & PKIFailureInfo.badSenderNonce) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.inverseOnSurface : 0L, ((-40961) & 4194304) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.error : 0L, ((-40961) & 8388608) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.onError : 0L, ((-40961) & 16777216) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.errorContainer : 0L, ((-40961) & 33554432) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.onErrorContainer : 0L, ((-40961) & 67108864) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.outline : 0L, ((-40961) & 134217728) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.outlineVariant : 0L, ((-40961) & 268435456) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.scrim : 0L, ((-40961) & PKIFailureInfo.duplicateCertReq) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.surfaceBright : 0L, ((-40961) & 1073741824) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.surfaceDim : 0L, ((-40961) & Integer.MIN_VALUE) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.surfaceContainer : 0L, (15 & 1) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.surfaceContainerHigh : 0L, (15 & 2) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.surfaceContainerHighest : 0L, (15 & 4) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.surfaceContainerLow : 0L, (15 & 8) != 0 ? colorSchemeM659darkColorSchemeCXl9yA$default.surfaceContainerLowest : 0L);
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void FDroidContent(boolean r8, boolean r9, final kotlin.jvm.functions.Function2 r10, androidx.compose.runtime.Composer r11, final int r12, final int r13) {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.ui.theme.ThemeKt.FDroidContent(boolean, boolean, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }
}
