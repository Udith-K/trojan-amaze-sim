package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.tokens.OutlinedTextFieldTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes.dex */
public final class OutlinedTextFieldDefaults {
    public static final OutlinedTextFieldDefaults INSTANCE = new OutlinedTextFieldDefaults();
    private static final float MinHeight = Dp.m2416constructorimpl(56);
    private static final float MinWidth = Dp.m2416constructorimpl(280);
    private static final float UnfocusedBorderThickness = Dp.m2416constructorimpl(1);
    private static final float FocusedBorderThickness = Dp.m2416constructorimpl(2);

    private OutlinedTextFieldDefaults() {
    }

    public final Shape getShape(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1066756961, i, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.<get-shape> (TextFieldDefaults.kt:729)");
        }
        Shape value = ShapesKt.getValue(OutlinedTextFieldTokens.INSTANCE.getContainerShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m705getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m706getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m707getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m704getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:150:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00ed  */
    /* JADX INFO: renamed from: Container-4EFweAY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m701Container4EFweAY(final boolean r25, final boolean r26, final androidx.compose.foundation.interaction.InteractionSource r27, androidx.compose.ui.Modifier r28, androidx.compose.material3.TextFieldColors r29, androidx.compose.ui.graphics.Shape r30, float r31, float r32, androidx.compose.runtime.Composer r33, final int r34, final int r35) {
        /*
            Method dump skipped, instruction units count: 581
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.OutlinedTextFieldDefaults.m701Container4EFweAY(boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.ui.Modifier, androidx.compose.material3.TextFieldColors, androidx.compose.ui.graphics.Shape, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:258:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0127  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void DecorationBox(final java.lang.String r42, final kotlin.jvm.functions.Function2 r43, final boolean r44, final boolean r45, final androidx.compose.ui.text.input.VisualTransformation r46, final androidx.compose.foundation.interaction.InteractionSource r47, boolean r48, kotlin.jvm.functions.Function2 r49, kotlin.jvm.functions.Function2 r50, kotlin.jvm.functions.Function2 r51, kotlin.jvm.functions.Function2 r52, kotlin.jvm.functions.Function2 r53, kotlin.jvm.functions.Function2 r54, kotlin.jvm.functions.Function2 r55, androidx.compose.material3.TextFieldColors r56, androidx.compose.foundation.layout.PaddingValues r57, kotlin.jvm.functions.Function2 r58, androidx.compose.runtime.Composer r59, final int r60, final int r61, final int r62) {
        /*
            Method dump skipped, instruction units count: 1034
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.OutlinedTextFieldDefaults.DecorationBox(java.lang.String, kotlin.jvm.functions.Function2, boolean, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.InteractionSource, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: renamed from: contentPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m700contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults outlinedTextFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return outlinedTextFieldDefaults.m703contentPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: contentPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m703contentPaddinga9UjIt4(float f, float f2, float f3, float f4) {
        return PaddingKt.m260PaddingValuesa9UjIt4(f, f2, f3, f4);
    }

    public final TextFieldColors colors(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-471651810, i, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.colors (TextFieldDefaults.kt:921)");
        }
        TextFieldColors defaultOutlinedTextFieldColors = getDefaultOutlinedTextFieldColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i << 3) & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultOutlinedTextFieldColors;
    }

    /* JADX INFO: renamed from: colors-0hiis_0, reason: not valid java name */
    public final TextFieldColors m702colors0hiis_0(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, TextSelectionColors textSelectionColors, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, Composer composer, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        long j43;
        long j44;
        long jM1309getUnspecified0d7_KjU = (i6 & 1) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j;
        long jM1309getUnspecified0d7_KjU2 = (i6 & 2) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j2;
        long jM1309getUnspecified0d7_KjU3 = (i6 & 4) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j3;
        long jM1309getUnspecified0d7_KjU4 = (i6 & 8) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j4;
        long jM1309getUnspecified0d7_KjU5 = (i6 & 16) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j5;
        long jM1309getUnspecified0d7_KjU6 = (i6 & 32) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j6;
        long jM1309getUnspecified0d7_KjU7 = (i6 & 64) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j7;
        long jM1309getUnspecified0d7_KjU8 = (i6 & 128) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j8;
        long jM1309getUnspecified0d7_KjU9 = (i6 & 256) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j9;
        long jM1309getUnspecified0d7_KjU10 = (i6 & 512) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j10;
        TextSelectionColors textSelectionColors2 = (i6 & 1024) != 0 ? null : textSelectionColors;
        long jM1309getUnspecified0d7_KjU11 = (i6 & 2048) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j11;
        long jM1309getUnspecified0d7_KjU12 = (i6 & PKIFailureInfo.certConfirmed) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j12;
        long jM1309getUnspecified0d7_KjU13 = (i6 & 8192) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j13;
        long jM1309getUnspecified0d7_KjU14 = (i6 & 16384) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j14;
        long jM1309getUnspecified0d7_KjU15 = (32768 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j15;
        long jM1309getUnspecified0d7_KjU16 = (65536 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j16;
        long jM1309getUnspecified0d7_KjU17 = (131072 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j17;
        long jM1309getUnspecified0d7_KjU18 = (262144 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j18;
        long jM1309getUnspecified0d7_KjU19 = (524288 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j19;
        long jM1309getUnspecified0d7_KjU20 = (1048576 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j20;
        long jM1309getUnspecified0d7_KjU21 = (2097152 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j21;
        long jM1309getUnspecified0d7_KjU22 = (4194304 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j22;
        long jM1309getUnspecified0d7_KjU23 = (8388608 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j23;
        long jM1309getUnspecified0d7_KjU24 = (16777216 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j24;
        long jM1309getUnspecified0d7_KjU25 = (33554432 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j25;
        long jM1309getUnspecified0d7_KjU26 = (67108864 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j26;
        long jM1309getUnspecified0d7_KjU27 = (134217728 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j27;
        long jM1309getUnspecified0d7_KjU28 = (268435456 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j28;
        long jM1309getUnspecified0d7_KjU29 = (536870912 & i6) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j29;
        long jM1309getUnspecified0d7_KjU30 = (i6 & 1073741824) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j30;
        long jM1309getUnspecified0d7_KjU31 = (i7 & 1) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j31;
        long jM1309getUnspecified0d7_KjU32 = (i7 & 2) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j32;
        long jM1309getUnspecified0d7_KjU33 = (i7 & 4) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j33;
        long jM1309getUnspecified0d7_KjU34 = (i7 & 8) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j34;
        long jM1309getUnspecified0d7_KjU35 = (i7 & 16) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j35;
        long jM1309getUnspecified0d7_KjU36 = (i7 & 32) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j36;
        long jM1309getUnspecified0d7_KjU37 = (i7 & 64) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j37;
        long jM1309getUnspecified0d7_KjU38 = (i7 & 128) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j38;
        long jM1309getUnspecified0d7_KjU39 = (i7 & 256) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j39;
        long jM1309getUnspecified0d7_KjU40 = (i7 & 512) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j40;
        long jM1309getUnspecified0d7_KjU41 = (i7 & 1024) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j41;
        long jM1309getUnspecified0d7_KjU42 = (i7 & 2048) != 0 ? Color.Companion.m1309getUnspecified0d7_KjU() : j42;
        if (ComposerKt.isTraceInProgress()) {
            j43 = jM1309getUnspecified0d7_KjU42;
            j44 = jM1309getUnspecified0d7_KjU6;
            ComposerKt.traceEventStart(1767617725, i, i2, "androidx.compose.material3.OutlinedTextFieldDefaults.colors (TextFieldDefaults.kt:1023)");
        } else {
            j43 = jM1309getUnspecified0d7_KjU42;
            j44 = jM1309getUnspecified0d7_KjU6;
        }
        TextFieldColors textFieldColorsM745copyejIjP34 = getDefaultOutlinedTextFieldColors(MaterialTheme.INSTANCE.getColorScheme(composer, 6), composer, (i5 >> 6) & 112).m745copyejIjP34(jM1309getUnspecified0d7_KjU, jM1309getUnspecified0d7_KjU2, jM1309getUnspecified0d7_KjU3, jM1309getUnspecified0d7_KjU4, jM1309getUnspecified0d7_KjU5, j44, jM1309getUnspecified0d7_KjU7, jM1309getUnspecified0d7_KjU8, jM1309getUnspecified0d7_KjU9, jM1309getUnspecified0d7_KjU10, textSelectionColors2, jM1309getUnspecified0d7_KjU11, jM1309getUnspecified0d7_KjU12, jM1309getUnspecified0d7_KjU13, jM1309getUnspecified0d7_KjU14, jM1309getUnspecified0d7_KjU15, jM1309getUnspecified0d7_KjU16, jM1309getUnspecified0d7_KjU17, jM1309getUnspecified0d7_KjU18, jM1309getUnspecified0d7_KjU19, jM1309getUnspecified0d7_KjU20, jM1309getUnspecified0d7_KjU21, jM1309getUnspecified0d7_KjU22, jM1309getUnspecified0d7_KjU23, jM1309getUnspecified0d7_KjU24, jM1309getUnspecified0d7_KjU25, jM1309getUnspecified0d7_KjU26, jM1309getUnspecified0d7_KjU27, jM1309getUnspecified0d7_KjU28, jM1309getUnspecified0d7_KjU29, jM1309getUnspecified0d7_KjU30, jM1309getUnspecified0d7_KjU31, jM1309getUnspecified0d7_KjU32, jM1309getUnspecified0d7_KjU33, jM1309getUnspecified0d7_KjU34, jM1309getUnspecified0d7_KjU35, jM1309getUnspecified0d7_KjU36, jM1309getUnspecified0d7_KjU37, jM1309getUnspecified0d7_KjU38, jM1309getUnspecified0d7_KjU39, jM1309getUnspecified0d7_KjU40, jM1309getUnspecified0d7_KjU41, j43);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return textFieldColorsM745copyejIjP34;
    }

    public final TextFieldColors getDefaultOutlinedTextFieldColors(ColorScheme colorScheme, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-292363577, i, -1, "androidx.compose.material3.OutlinedTextFieldDefaults.<get-defaultOutlinedTextFieldColors> (TextFieldDefaults.kt:1071)");
        }
        TextFieldColors defaultOutlinedTextFieldColorsCached$material3_release = colorScheme.getDefaultOutlinedTextFieldColorsCached$material3_release();
        composer.startReplaceGroup(1540400102);
        if (defaultOutlinedTextFieldColorsCached$material3_release == null) {
            OutlinedTextFieldTokens outlinedTextFieldTokens = OutlinedTextFieldTokens.INSTANCE;
            long jFromToken = ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusInputColor());
            long jFromToken2 = ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputColor());
            long jM1294copywmQWz5c$default = Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledInputColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null);
            long jFromToken3 = ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorInputColor());
            Color.Companion companion = Color.Companion;
            defaultOutlinedTextFieldColorsCached$material3_release = new TextFieldColors(jFromToken, jFromToken2, jM1294copywmQWz5c$default, jFromToken3, companion.m1308getTransparent0d7_KjU(), companion.m1308getTransparent0d7_KjU(), companion.m1308getTransparent0d7_KjU(), companion.m1308getTransparent0d7_KjU(), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getCaretColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorFocusCaretColor()), (TextSelectionColors) composer.consume(TextSelectionColorsKt.getLocalTextSelectionColors()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusOutlineColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getOutlineColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledOutlineColor()), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorOutlineColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getLeadingIconColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledLeadingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorLeadingIconColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusTrailingIconColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getTrailingIconColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledTrailingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorTrailingIconColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusLabelColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getLabelColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledLabelColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorLabelColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPlaceholderColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPlaceholderColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledInputColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPlaceholderColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getFocusSupportingColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getSupportingColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getDisabledSupportingColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getErrorSupportingColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPrefixColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPrefixColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPrefixColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputPrefixColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputSuffixColor()), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputSuffixColor()), Color.m1294copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputSuffixColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), ColorSchemeKt.fromToken(colorScheme, outlinedTextFieldTokens.getInputSuffixColor()), null);
            colorScheme.setDefaultOutlinedTextFieldColorsCached$material3_release(defaultOutlinedTextFieldColorsCached$material3_release);
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return defaultOutlinedTextFieldColorsCached$material3_release;
    }
}
