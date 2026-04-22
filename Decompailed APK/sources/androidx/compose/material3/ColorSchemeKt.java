package androidx.compose.material3;

import androidx.compose.material3.tokens.ColorDarkTokens;
import androidx.compose.material3.tokens.ColorLightTokens;
import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.functions.Function0;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.eac.EACTags;

/* JADX INFO: compiled from: ColorScheme.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ColorSchemeKt {
    private static final ProvidableCompositionLocal LocalColorScheme = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.material3.ColorSchemeKt$LocalColorScheme$1
        @Override // kotlin.jvm.functions.Function0
        public final ColorScheme invoke() {
            return ColorSchemeKt.m661lightColorSchemeCXl9yA$default(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -1, 15, null);
        }
    });
    private static final ProvidableCompositionLocal LocalTonalElevationEnabled = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.material3.ColorSchemeKt$LocalTonalElevationEnabled$1
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.TRUE;
        }
    });

    /* JADX INFO: compiled from: ColorScheme.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ColorSchemeKeyTokens.values().length];
            try {
                iArr[ColorSchemeKeyTokens.Background.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Error.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ColorSchemeKeyTokens.ErrorContainer.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InverseOnSurface.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InversePrimary.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ColorSchemeKeyTokens.InverseSurface.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnBackground.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnError.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnErrorContainer.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnPrimary.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnPrimaryContainer.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSecondary.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSecondaryContainer.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSurface.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnSurfaceVariant.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceTint.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnTertiary.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OnTertiaryContainer.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Outline.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[ColorSchemeKeyTokens.OutlineVariant.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Primary.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[ColorSchemeKeyTokens.PrimaryContainer.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Scrim.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Secondary.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SecondaryContainer.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Surface.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceVariant.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceBright.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainer.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerHigh.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerHighest.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerLow.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceContainerLowest.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                iArr[ColorSchemeKeyTokens.SurfaceDim.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                iArr[ColorSchemeKeyTokens.Tertiary.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                iArr[ColorSchemeKeyTokens.TertiaryContainer.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: lightColorScheme-C-Xl9yA$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m661lightColorSchemeCXl9yA$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, int i, int i2, Object obj) {
        long jM845getPrimary0d7_KjU = (i & 1) != 0 ? ColorLightTokens.INSTANCE.m845getPrimary0d7_KjU() : j;
        return m660lightColorSchemeCXl9yA(jM845getPrimary0d7_KjU, (i & 2) != 0 ? ColorLightTokens.INSTANCE.m835getOnPrimary0d7_KjU() : j2, (i & 4) != 0 ? ColorLightTokens.INSTANCE.m846getPrimaryContainer0d7_KjU() : j3, (i & 8) != 0 ? ColorLightTokens.INSTANCE.m836getOnPrimaryContainer0d7_KjU() : j4, (i & 16) != 0 ? ColorLightTokens.INSTANCE.m830getInversePrimary0d7_KjU() : j5, (i & 32) != 0 ? ColorLightTokens.INSTANCE.m848getSecondary0d7_KjU() : j6, (i & 64) != 0 ? ColorLightTokens.INSTANCE.m837getOnSecondary0d7_KjU() : j7, (i & 128) != 0 ? ColorLightTokens.INSTANCE.m849getSecondaryContainer0d7_KjU() : j8, (i & 256) != 0 ? ColorLightTokens.INSTANCE.m838getOnSecondaryContainer0d7_KjU() : j9, (i & 512) != 0 ? ColorLightTokens.INSTANCE.m859getTertiary0d7_KjU() : j10, (i & 1024) != 0 ? ColorLightTokens.INSTANCE.m841getOnTertiary0d7_KjU() : j11, (i & 2048) != 0 ? ColorLightTokens.INSTANCE.m860getTertiaryContainer0d7_KjU() : j12, (i & PKIFailureInfo.certConfirmed) != 0 ? ColorLightTokens.INSTANCE.m842getOnTertiaryContainer0d7_KjU() : j13, (i & 8192) != 0 ? ColorLightTokens.INSTANCE.m826getBackground0d7_KjU() : j14, (i & 16384) != 0 ? ColorLightTokens.INSTANCE.m832getOnBackground0d7_KjU() : j15, (i & 32768) != 0 ? ColorLightTokens.INSTANCE.m850getSurface0d7_KjU() : j16, (i & PKIFailureInfo.notAuthorized) != 0 ? ColorLightTokens.INSTANCE.m839getOnSurface0d7_KjU() : j17, (i & PKIFailureInfo.unsupportedVersion) != 0 ? ColorLightTokens.INSTANCE.m858getSurfaceVariant0d7_KjU() : j18, (i & PKIFailureInfo.transactionIdInUse) != 0 ? ColorLightTokens.INSTANCE.m840getOnSurfaceVariant0d7_KjU() : j19, (i & PKIFailureInfo.signerNotTrusted) != 0 ? jM845getPrimary0d7_KjU : j20, (i & PKIFailureInfo.badCertTemplate) != 0 ? ColorLightTokens.INSTANCE.m831getInverseSurface0d7_KjU() : j21, (i & PKIFailureInfo.badSenderNonce) != 0 ? ColorLightTokens.INSTANCE.m829getInverseOnSurface0d7_KjU() : j22, (i & 4194304) != 0 ? ColorLightTokens.INSTANCE.m827getError0d7_KjU() : j23, (i & 8388608) != 0 ? ColorLightTokens.INSTANCE.m833getOnError0d7_KjU() : j24, (i & 16777216) != 0 ? ColorLightTokens.INSTANCE.m828getErrorContainer0d7_KjU() : j25, (i & 33554432) != 0 ? ColorLightTokens.INSTANCE.m834getOnErrorContainer0d7_KjU() : j26, (i & 67108864) != 0 ? ColorLightTokens.INSTANCE.m843getOutline0d7_KjU() : j27, (i & 134217728) != 0 ? ColorLightTokens.INSTANCE.m844getOutlineVariant0d7_KjU() : j28, (i & 268435456) != 0 ? ColorLightTokens.INSTANCE.m847getScrim0d7_KjU() : j29, (i & PKIFailureInfo.duplicateCertReq) != 0 ? ColorLightTokens.INSTANCE.m851getSurfaceBright0d7_KjU() : j30, (i & 1073741824) != 0 ? ColorLightTokens.INSTANCE.m852getSurfaceContainer0d7_KjU() : j31, (i & Integer.MIN_VALUE) != 0 ? ColorLightTokens.INSTANCE.m853getSurfaceContainerHigh0d7_KjU() : j32, (i2 & 1) != 0 ? ColorLightTokens.INSTANCE.m854getSurfaceContainerHighest0d7_KjU() : j33, (i2 & 2) != 0 ? ColorLightTokens.INSTANCE.m855getSurfaceContainerLow0d7_KjU() : j34, (i2 & 4) != 0 ? ColorLightTokens.INSTANCE.m856getSurfaceContainerLowest0d7_KjU() : j35, (i2 & 8) != 0 ? ColorLightTokens.INSTANCE.m857getSurfaceDim0d7_KjU() : j36);
    }

    /* JADX INFO: renamed from: lightColorScheme-C-Xl9yA, reason: not valid java name */
    public static final ColorScheme m660lightColorSchemeCXl9yA(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36) {
        return new ColorScheme(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j36, j31, j32, j33, j34, j35, null);
    }

    /* JADX INFO: renamed from: darkColorScheme-C-Xl9yA$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m659darkColorSchemeCXl9yA$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, int i, int i2, Object obj) {
        long jM810getPrimary0d7_KjU = (i & 1) != 0 ? ColorDarkTokens.INSTANCE.m810getPrimary0d7_KjU() : j;
        return m658darkColorSchemeCXl9yA(jM810getPrimary0d7_KjU, (i & 2) != 0 ? ColorDarkTokens.INSTANCE.m800getOnPrimary0d7_KjU() : j2, (i & 4) != 0 ? ColorDarkTokens.INSTANCE.m811getPrimaryContainer0d7_KjU() : j3, (i & 8) != 0 ? ColorDarkTokens.INSTANCE.m801getOnPrimaryContainer0d7_KjU() : j4, (i & 16) != 0 ? ColorDarkTokens.INSTANCE.m795getInversePrimary0d7_KjU() : j5, (i & 32) != 0 ? ColorDarkTokens.INSTANCE.m813getSecondary0d7_KjU() : j6, (i & 64) != 0 ? ColorDarkTokens.INSTANCE.m802getOnSecondary0d7_KjU() : j7, (i & 128) != 0 ? ColorDarkTokens.INSTANCE.m814getSecondaryContainer0d7_KjU() : j8, (i & 256) != 0 ? ColorDarkTokens.INSTANCE.m803getOnSecondaryContainer0d7_KjU() : j9, (i & 512) != 0 ? ColorDarkTokens.INSTANCE.m824getTertiary0d7_KjU() : j10, (i & 1024) != 0 ? ColorDarkTokens.INSTANCE.m806getOnTertiary0d7_KjU() : j11, (i & 2048) != 0 ? ColorDarkTokens.INSTANCE.m825getTertiaryContainer0d7_KjU() : j12, (i & PKIFailureInfo.certConfirmed) != 0 ? ColorDarkTokens.INSTANCE.m807getOnTertiaryContainer0d7_KjU() : j13, (i & 8192) != 0 ? ColorDarkTokens.INSTANCE.m791getBackground0d7_KjU() : j14, (i & 16384) != 0 ? ColorDarkTokens.INSTANCE.m797getOnBackground0d7_KjU() : j15, (i & 32768) != 0 ? ColorDarkTokens.INSTANCE.m815getSurface0d7_KjU() : j16, (i & PKIFailureInfo.notAuthorized) != 0 ? ColorDarkTokens.INSTANCE.m804getOnSurface0d7_KjU() : j17, (i & PKIFailureInfo.unsupportedVersion) != 0 ? ColorDarkTokens.INSTANCE.m823getSurfaceVariant0d7_KjU() : j18, (i & PKIFailureInfo.transactionIdInUse) != 0 ? ColorDarkTokens.INSTANCE.m805getOnSurfaceVariant0d7_KjU() : j19, (i & PKIFailureInfo.signerNotTrusted) != 0 ? jM810getPrimary0d7_KjU : j20, (i & PKIFailureInfo.badCertTemplate) != 0 ? ColorDarkTokens.INSTANCE.m796getInverseSurface0d7_KjU() : j21, (i & PKIFailureInfo.badSenderNonce) != 0 ? ColorDarkTokens.INSTANCE.m794getInverseOnSurface0d7_KjU() : j22, (i & 4194304) != 0 ? ColorDarkTokens.INSTANCE.m792getError0d7_KjU() : j23, (i & 8388608) != 0 ? ColorDarkTokens.INSTANCE.m798getOnError0d7_KjU() : j24, (i & 16777216) != 0 ? ColorDarkTokens.INSTANCE.m793getErrorContainer0d7_KjU() : j25, (i & 33554432) != 0 ? ColorDarkTokens.INSTANCE.m799getOnErrorContainer0d7_KjU() : j26, (i & 67108864) != 0 ? ColorDarkTokens.INSTANCE.m808getOutline0d7_KjU() : j27, (i & 134217728) != 0 ? ColorDarkTokens.INSTANCE.m809getOutlineVariant0d7_KjU() : j28, (i & 268435456) != 0 ? ColorDarkTokens.INSTANCE.m812getScrim0d7_KjU() : j29, (i & PKIFailureInfo.duplicateCertReq) != 0 ? ColorDarkTokens.INSTANCE.m816getSurfaceBright0d7_KjU() : j30, (i & 1073741824) != 0 ? ColorDarkTokens.INSTANCE.m817getSurfaceContainer0d7_KjU() : j31, (i & Integer.MIN_VALUE) != 0 ? ColorDarkTokens.INSTANCE.m818getSurfaceContainerHigh0d7_KjU() : j32, (i2 & 1) != 0 ? ColorDarkTokens.INSTANCE.m819getSurfaceContainerHighest0d7_KjU() : j33, (i2 & 2) != 0 ? ColorDarkTokens.INSTANCE.m820getSurfaceContainerLow0d7_KjU() : j34, (i2 & 4) != 0 ? ColorDarkTokens.INSTANCE.m821getSurfaceContainerLowest0d7_KjU() : j35, (i2 & 8) != 0 ? ColorDarkTokens.INSTANCE.m822getSurfaceDim0d7_KjU() : j36);
    }

    /* JADX INFO: renamed from: darkColorScheme-C-Xl9yA, reason: not valid java name */
    public static final ColorScheme m658darkColorSchemeCXl9yA(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36) {
        return new ColorScheme(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j36, j31, j32, j33, j34, j35, null);
    }

    /* JADX INFO: renamed from: contentColorFor-4WTKRHQ, reason: not valid java name */
    public static final long m656contentColorFor4WTKRHQ(ColorScheme colorScheme, long j) {
        if (Color.m1296equalsimpl0(j, colorScheme.m638getPrimary0d7_KjU())) {
            return colorScheme.m628getOnPrimary0d7_KjU();
        }
        if (Color.m1296equalsimpl0(j, colorScheme.m641getSecondary0d7_KjU())) {
            return colorScheme.m630getOnSecondary0d7_KjU();
        }
        if (Color.m1296equalsimpl0(j, colorScheme.m653getTertiary0d7_KjU())) {
            return colorScheme.m634getOnTertiary0d7_KjU();
        }
        if (Color.m1296equalsimpl0(j, colorScheme.m619getBackground0d7_KjU())) {
            return colorScheme.m625getOnBackground0d7_KjU();
        }
        if (Color.m1296equalsimpl0(j, colorScheme.m620getError0d7_KjU())) {
            return colorScheme.m626getOnError0d7_KjU();
        }
        if (Color.m1296equalsimpl0(j, colorScheme.m639getPrimaryContainer0d7_KjU())) {
            return colorScheme.m629getOnPrimaryContainer0d7_KjU();
        }
        if (Color.m1296equalsimpl0(j, colorScheme.m642getSecondaryContainer0d7_KjU())) {
            return colorScheme.m631getOnSecondaryContainer0d7_KjU();
        }
        if (Color.m1296equalsimpl0(j, colorScheme.m654getTertiaryContainer0d7_KjU())) {
            return colorScheme.m635getOnTertiaryContainer0d7_KjU();
        }
        if (Color.m1296equalsimpl0(j, colorScheme.m621getErrorContainer0d7_KjU())) {
            return colorScheme.m627getOnErrorContainer0d7_KjU();
        }
        if (Color.m1296equalsimpl0(j, colorScheme.m624getInverseSurface0d7_KjU())) {
            return colorScheme.m622getInverseOnSurface0d7_KjU();
        }
        if (Color.m1296equalsimpl0(j, colorScheme.m643getSurface0d7_KjU())) {
            return colorScheme.m632getOnSurface0d7_KjU();
        }
        if (Color.m1296equalsimpl0(j, colorScheme.m652getSurfaceVariant0d7_KjU())) {
            return colorScheme.m633getOnSurfaceVariant0d7_KjU();
        }
        if (!Color.m1296equalsimpl0(j, colorScheme.m644getSurfaceBright0d7_KjU()) && !Color.m1296equalsimpl0(j, colorScheme.m645getSurfaceContainer0d7_KjU()) && !Color.m1296equalsimpl0(j, colorScheme.m646getSurfaceContainerHigh0d7_KjU()) && !Color.m1296equalsimpl0(j, colorScheme.m647getSurfaceContainerHighest0d7_KjU()) && !Color.m1296equalsimpl0(j, colorScheme.m648getSurfaceContainerLow0d7_KjU()) && !Color.m1296equalsimpl0(j, colorScheme.m649getSurfaceContainerLowest0d7_KjU())) {
            return Color.Companion.m1309getUnspecified0d7_KjU();
        }
        return colorScheme.m632getOnSurface0d7_KjU();
    }

    /* JADX INFO: renamed from: contentColorFor-ek8zF_U, reason: not valid java name */
    public static final long m657contentColorForek8zF_U(long j, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(509589638, i, -1, "androidx.compose.material3.contentColorFor (ColorScheme.kt:878)");
        }
        composer.startReplaceGroup(-1680936624);
        long jM656contentColorFor4WTKRHQ = m656contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme(composer, 6), j);
        if (jM656contentColorFor4WTKRHQ == 16) {
            jM656contentColorFor4WTKRHQ = ((Color) composer.consume(ContentColorKt.getLocalContentColor())).m1304unboximpl();
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jM656contentColorFor4WTKRHQ;
    }

    /* JADX INFO: renamed from: applyTonalElevation-RFCenO8, reason: not valid java name */
    public static final long m655applyTonalElevationRFCenO8(ColorScheme colorScheme, long j, float f, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1610977682, i, -1, "androidx.compose.material3.applyTonalElevation (ColorScheme.kt:895)");
        }
        boolean zBooleanValue = ((Boolean) composer.consume(LocalTonalElevationEnabled)).booleanValue();
        if (Color.m1296equalsimpl0(j, colorScheme.m643getSurface0d7_KjU()) && zBooleanValue) {
            j = m662surfaceColorAtElevation3ABfNKs(colorScheme, f);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return j;
    }

    public static final long fromToken(ColorScheme colorScheme, ColorSchemeKeyTokens colorSchemeKeyTokens) {
        switch (WhenMappings.$EnumSwitchMapping$0[colorSchemeKeyTokens.ordinal()]) {
            case 1:
                return colorScheme.m619getBackground0d7_KjU();
            case 2:
                return colorScheme.m620getError0d7_KjU();
            case 3:
                return colorScheme.m621getErrorContainer0d7_KjU();
            case 4:
                return colorScheme.m622getInverseOnSurface0d7_KjU();
            case 5:
                return colorScheme.m623getInversePrimary0d7_KjU();
            case 6:
                return colorScheme.m624getInverseSurface0d7_KjU();
            case 7:
                return colorScheme.m625getOnBackground0d7_KjU();
            case 8:
                return colorScheme.m626getOnError0d7_KjU();
            case 9:
                return colorScheme.m627getOnErrorContainer0d7_KjU();
            case 10:
                return colorScheme.m628getOnPrimary0d7_KjU();
            case 11:
                return colorScheme.m629getOnPrimaryContainer0d7_KjU();
            case 12:
                return colorScheme.m630getOnSecondary0d7_KjU();
            case 13:
                return colorScheme.m631getOnSecondaryContainer0d7_KjU();
            case 14:
                return colorScheme.m632getOnSurface0d7_KjU();
            case 15:
                return colorScheme.m633getOnSurfaceVariant0d7_KjU();
            case 16:
                return colorScheme.m651getSurfaceTint0d7_KjU();
            case 17:
                return colorScheme.m634getOnTertiary0d7_KjU();
            case 18:
                return colorScheme.m635getOnTertiaryContainer0d7_KjU();
            case 19:
                return colorScheme.m636getOutline0d7_KjU();
            case 20:
                return colorScheme.m637getOutlineVariant0d7_KjU();
            case 21:
                return colorScheme.m638getPrimary0d7_KjU();
            case 22:
                return colorScheme.m639getPrimaryContainer0d7_KjU();
            case 23:
                return colorScheme.m640getScrim0d7_KjU();
            case 24:
                return colorScheme.m641getSecondary0d7_KjU();
            case 25:
                return colorScheme.m642getSecondaryContainer0d7_KjU();
            case 26:
                return colorScheme.m643getSurface0d7_KjU();
            case 27:
                return colorScheme.m652getSurfaceVariant0d7_KjU();
            case 28:
                return colorScheme.m644getSurfaceBright0d7_KjU();
            case 29:
                return colorScheme.m645getSurfaceContainer0d7_KjU();
            case 30:
                return colorScheme.m646getSurfaceContainerHigh0d7_KjU();
            case 31:
                return colorScheme.m647getSurfaceContainerHighest0d7_KjU();
            case 32:
                return colorScheme.m648getSurfaceContainerLow0d7_KjU();
            case 33:
                return colorScheme.m649getSurfaceContainerLowest0d7_KjU();
            case 34:
                return colorScheme.m650getSurfaceDim0d7_KjU();
            case 35:
                return colorScheme.m653getTertiary0d7_KjU();
            case EACTags.APPLICATION_EXPIRATION_DATE /* 36 */:
                return colorScheme.m654getTertiaryContainer0d7_KjU();
            default:
                return Color.Companion.m1309getUnspecified0d7_KjU();
        }
    }

    public static final ProvidableCompositionLocal getLocalColorScheme() {
        return LocalColorScheme;
    }

    public static final long getValue(ColorSchemeKeyTokens colorSchemeKeyTokens, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-810780884, i, -1, "androidx.compose.material3.<get-value> (ColorScheme.kt:1009)");
        }
        long jFromToken = fromToken(MaterialTheme.INSTANCE.getColorScheme(composer, 6), colorSchemeKeyTokens);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jFromToken;
    }

    /* JADX INFO: renamed from: surfaceColorAtElevation-3ABfNKs, reason: not valid java name */
    public static final long m662surfaceColorAtElevation3ABfNKs(ColorScheme colorScheme, float f) {
        if (Dp.m2418equalsimpl0(f, Dp.m2416constructorimpl(0))) {
            return colorScheme.m643getSurface0d7_KjU();
        }
        return ColorKt.m1313compositeOverOWjLjI(Color.m1294copywmQWz5c$default(colorScheme.m651getSurfaceTint0d7_KjU(), ((((float) Math.log(f + 1)) * 4.5f) + 2.0f) / 100.0f, 0.0f, 0.0f, 0.0f, 14, null), colorScheme.m643getSurface0d7_KjU());
    }
}
