package org.fdroid.fdroid.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.bouncycastle.i18n.TextBundle;
import org.fdroid.fdroid.ui.theme.ThemeKt;

/* JADX INFO: compiled from: FDroidSwitchRow.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a[\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\f\u0010\r\u001a\u000f\u0010\u000f\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"", TextBundle.TEXT_ENTRY, "Lkotlin/Function0;", "", "leadingContent", "", "checked", "Lkotlin/Function1;", "onCheckedChange", "enabled", "Landroidx/compose/ui/unit/Dp;", "verticalPadding", "FDroidSwitchRow-HYR8e34", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function1;ZFLandroidx/compose/runtime/Composer;II)V", "FDroidSwitchRow", "FDroidSwitchRowPreview", "(Landroidx/compose/runtime/Composer;I)V", "app_fullRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FDroidSwitchRowKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FDroidSwitchRowPreview$lambda$4(int i, Composer composer, int i2) {
        FDroidSwitchRowPreview(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FDroidSwitchRow_HYR8e34$lambda$3(String str, Function2 function2, boolean z, Function1 function1, boolean z2, float f, int i, int i2, Composer composer, int i3) {
        m2972FDroidSwitchRowHYR8e34(str, function2, z, function1, z2, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FDroidSwitchRow_HYR8e34$lambda$1$lambda$0(boolean z) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:117:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x016a  */
    /* JADX INFO: renamed from: FDroidSwitchRow-HYR8e34, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2972FDroidSwitchRowHYR8e34(final java.lang.String r31, kotlin.jvm.functions.Function2 r32, final boolean r33, kotlin.jvm.functions.Function1 r34, boolean r35, float r36, androidx.compose.runtime.Composer r37, final int r38, final int r39) {
        /*
            Method dump skipped, instruction units count: 604
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.compose.FDroidSwitchRowKt.m2972FDroidSwitchRowHYR8e34(java.lang.String, kotlin.jvm.functions.Function2, boolean, kotlin.jvm.functions.Function1, boolean, float, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void FDroidSwitchRowPreview(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(811737324);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(811737324, i, -1, "org.fdroid.fdroid.compose.FDroidSwitchRowPreview (FDroidSwitchRow.kt:59)");
            }
            ThemeKt.FDroidContent(false, false, ComposableSingletons$FDroidSwitchRowKt.INSTANCE.m2964getLambda1$app_fullRelease(), composerStartRestartGroup, 384, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.fdroid.fdroid.compose.FDroidSwitchRowKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FDroidSwitchRowKt.FDroidSwitchRowPreview$lambda$4(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
