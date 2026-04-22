package androidx.compose.foundation.contextmenu;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.compose.ui.window.PopupProperties;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ContextMenuUi.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ContextMenuUi_androidKt {
    private static final ContextMenuColors DefaultContextMenuColors;
    private static final PopupProperties DefaultPopupProperties = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);

    static {
        Color.Companion companion = Color.Companion;
        DefaultContextMenuColors = new ContextMenuColors(companion.m1310getWhite0d7_KjU(), companion.m1305getBlack0d7_KjU(), companion.m1305getBlack0d7_KjU(), Color.m1294copywmQWz5c$default(companion.m1305getBlack0d7_KjU(), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m1294copywmQWz5c$default(companion.m1305getBlack0d7_KjU(), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), null);
    }

    public static final void ContextMenuPopup(final PopupPositionProvider popupPositionProvider, final Function0 function0, Modifier modifier, final Function1 function1, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(712057293);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(popupPositionProvider) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        int i4 = i2 & 4;
        if (i4 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 2048 : 1024;
        }
        if ((i3 & 1171) != 1170 || !composerStartRestartGroup.getSkipping()) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(712057293, i3, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup (ContextMenuUi.android.kt:103)");
            }
            ContextMenuPopup(popupPositionProvider, function0, modifier, computeContextMenuColors(0, 0, composerStartRestartGroup, 0, 3), function1, composerStartRestartGroup, (i3 & 1022) | ((i3 << 3) & 57344), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        final Modifier modifier2 = modifier;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuPopup.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i5) {
                    ContextMenuUi_androidKt.ContextMenuPopup(popupPositionProvider, function0, modifier2, function1, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:74:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void ContextMenuPopup(final androidx.compose.ui.window.PopupPositionProvider r16, final kotlin.jvm.functions.Function0 r17, androidx.compose.ui.Modifier r18, final androidx.compose.foundation.contextmenu.ContextMenuColors r19, final kotlin.jvm.functions.Function1 r20, androidx.compose.runtime.Composer r21, final int r22, final int r23) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuPopup(androidx.compose.ui.window.PopupPositionProvider, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.foundation.contextmenu.ContextMenuColors, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void ContextMenuColumn(final androidx.compose.foundation.contextmenu.ContextMenuColors r23, androidx.compose.ui.Modifier r24, final kotlin.jvm.functions.Function3 r25, androidx.compose.runtime.Composer r26, final int r27, final int r28) {
        /*
            Method dump skipped, instruction units count: 385
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuColumn(androidx.compose.foundation.contextmenu.ContextMenuColors, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:138:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x018f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void ContextMenuItem(final java.lang.String r30, final boolean r31, final androidx.compose.foundation.contextmenu.ContextMenuColors r32, androidx.compose.ui.Modifier r33, kotlin.jvm.functions.Function3 r34, final kotlin.jvm.functions.Function0 r35, androidx.compose.runtime.Composer r36, final int r37, final int r38) {
        /*
            Method dump skipped, instruction units count: 757
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuItem(java.lang.String, boolean, androidx.compose.foundation.contextmenu.ContextMenuColors, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final ContextMenuColors computeContextMenuColors(int i, int i2, Composer composer, int i3, int i4) {
        int i5 = (i4 & 1) != 0 ? R.style.Widget.PopupMenu : i;
        int i6 = (i4 & 2) != 0 ? R.style.TextAppearance.Widget.PopupMenu.Large : i2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1689505294, i3, -1, "androidx.compose.foundation.contextmenu.computeContextMenuColors (ContextMenuUi.android.kt:354)");
        }
        Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        boolean zChanged = composer.changed((Configuration) composer.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration())) | composer.changed(context);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            ContextMenuColors contextMenuColors = DefaultContextMenuColors;
            long jM164resolveColorg2O1Hgs = m164resolveColorg2O1Hgs(context, i5, R.attr.colorBackground, contextMenuColors.m145getBackgroundColor0d7_KjU());
            ColorStateList colorStateListResolveColorStateList = resolveColorStateList(context, i6, R.attr.textColorPrimary);
            long jM163enabledColor4WTKRHQ = m163enabledColor4WTKRHQ(colorStateListResolveColorStateList, contextMenuColors.m149getTextColor0d7_KjU());
            long jM162disabledColor4WTKRHQ = m162disabledColor4WTKRHQ(colorStateListResolveColorStateList, contextMenuColors.m147getDisabledTextColor0d7_KjU());
            objRememberedValue = new ContextMenuColors(jM164resolveColorg2O1Hgs, jM163enabledColor4WTKRHQ, jM163enabledColor4WTKRHQ, jM162disabledColor4WTKRHQ, jM162disabledColor4WTKRHQ, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        ContextMenuColors contextMenuColors2 = (ContextMenuColors) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return contextMenuColors2;
    }

    /* JADX INFO: renamed from: resolveColor-g2O1Hgs, reason: not valid java name */
    private static final long m164resolveColorg2O1Hgs(Context context, int i, int i2, long j) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, new int[]{i2});
        int iM1316toArgb8_81llA = ColorKt.m1316toArgb8_81llA(j);
        int color = typedArrayObtainStyledAttributes.getColor(0, iM1316toArgb8_81llA);
        typedArrayObtainStyledAttributes.recycle();
        return color == iM1316toArgb8_81llA ? j : ColorKt.Color(color);
    }

    private static final ColorStateList resolveColorStateList(Context context, int i, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, new int[]{i2});
        ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(0);
        typedArrayObtainStyledAttributes.recycle();
        return colorStateList;
    }

    /* JADX INFO: renamed from: enabledColor-4WTKRHQ, reason: not valid java name */
    private static final long m163enabledColor4WTKRHQ(ColorStateList colorStateList, long j) {
        int iM1316toArgb8_81llA = ColorKt.m1316toArgb8_81llA(j);
        Integer numValueOf = colorStateList != null ? Integer.valueOf(colorStateList.getColorForState(new int[]{R.attr.state_enabled}, iM1316toArgb8_81llA)) : null;
        return (numValueOf == null || numValueOf.intValue() == iM1316toArgb8_81llA) ? j : ColorKt.Color(numValueOf.intValue());
    }

    /* JADX INFO: renamed from: disabledColor-4WTKRHQ, reason: not valid java name */
    private static final long m162disabledColor4WTKRHQ(ColorStateList colorStateList, long j) {
        int iM1316toArgb8_81llA = ColorKt.m1316toArgb8_81llA(j);
        Integer numValueOf = colorStateList != null ? Integer.valueOf(colorStateList.getColorForState(new int[]{-16842910}, iM1316toArgb8_81llA)) : null;
        return (numValueOf == null || numValueOf.intValue() == iM1316toArgb8_81llA) ? j : ColorKt.Color(numValueOf.intValue());
    }
}
