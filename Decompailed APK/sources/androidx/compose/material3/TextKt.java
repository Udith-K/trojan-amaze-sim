package androidx.compose.material3;

import androidx.compose.material3.tokens.TypographyTokensKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Text.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TextKt {
    private static final ProvidableCompositionLocal LocalTextStyle = CompositionLocalKt.compositionLocalOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: androidx.compose.material3.TextKt$LocalTextStyle$1
        @Override // kotlin.jvm.functions.Function0
        public final TextStyle invoke() {
            return TypographyTokensKt.getDefaultTextStyle();
        }
    });

    /* JADX WARN: Removed duplicated region for block: B:108:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:273:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0129  */
    /* JADX INFO: renamed from: Text--4IGK_g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m772Text4IGK_g(final java.lang.String r68, androidx.compose.ui.Modifier r69, long r70, long r72, androidx.compose.ui.text.font.FontStyle r74, androidx.compose.ui.text.font.FontWeight r75, androidx.compose.ui.text.font.FontFamily r76, long r77, androidx.compose.ui.text.style.TextDecoration r79, androidx.compose.ui.text.style.TextAlign r80, long r81, int r83, boolean r84, int r85, int r86, kotlin.jvm.functions.Function1 r87, androidx.compose.ui.text.TextStyle r88, androidx.compose.runtime.Composer r89, final int r90, final int r91, final int r92) {
        /*
            Method dump skipped, instruction units count: 1057
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m772Text4IGK_g(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:285:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0129  */
    /* JADX INFO: renamed from: Text-IbK3jfQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m773TextIbK3jfQ(final androidx.compose.ui.text.AnnotatedString r61, androidx.compose.ui.Modifier r62, long r63, long r65, androidx.compose.ui.text.font.FontStyle r67, androidx.compose.ui.text.font.FontWeight r68, androidx.compose.ui.text.font.FontFamily r69, long r70, androidx.compose.ui.text.style.TextDecoration r72, androidx.compose.ui.text.style.TextAlign r73, long r74, int r76, boolean r77, int r78, int r79, java.util.Map r80, kotlin.jvm.functions.Function1 r81, androidx.compose.ui.text.TextStyle r82, androidx.compose.runtime.Composer r83, final int r84, final int r85, final int r86) {
        /*
            Method dump skipped, instruction units count: 1124
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m773TextIbK3jfQ(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, java.util.Map, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final ProvidableCompositionLocal getLocalTextStyle() {
        return LocalTextStyle;
    }

    public static final void ProvideTextStyle(final TextStyle textStyle, final Function2 function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-460300127);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(textStyle) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-460300127, i2, -1, "androidx.compose.material3.ProvideTextStyle (Text.kt:346)");
            }
            ProvidableCompositionLocal providableCompositionLocal = LocalTextStyle;
            CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(((TextStyle) composerStartRestartGroup.consume(providableCompositionLocal)).merge(textStyle)), function2, composerStartRestartGroup, (i2 & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextKt.ProvideTextStyle.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    TextKt.ProvideTextStyle(textStyle, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }
}
