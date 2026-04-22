package androidx.compose.ui.window;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.IntRect;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: AndroidPopup.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidPopup_androidKt {
    private static final ProvidableCompositionLocal LocalPopupTestTag = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$LocalPopupTestTag$1
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return "DEFAULT_TEST_TAG";
        }
    }, 1, null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2 Popup$lambda$1(State state) {
        return (Function2) state.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:136:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void Popup(final androidx.compose.ui.window.PopupPositionProvider r35, kotlin.jvm.functions.Function0 r36, androidx.compose.ui.window.PopupProperties r37, final kotlin.jvm.functions.Function2 r38, androidx.compose.runtime.Composer r39, final int r40, final int r41) {
        /*
            Method dump skipped, instruction units count: 772
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.window.AndroidPopup_androidKt.Popup(androidx.compose.ui.window.PopupPositionProvider, kotlin.jvm.functions.Function0, androidx.compose.ui.window.PopupProperties, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int createFlags(boolean z, SecureFlagPolicy secureFlagPolicy, boolean z2) {
        int i = !z ? 262152 : PKIFailureInfo.transactionIdInUse;
        if (secureFlagPolicy == SecureFlagPolicy.SecureOn) {
            i |= 8192;
        }
        return !z2 ? i | 512 : i;
    }

    public static final boolean isFlagSecureEnabled(View view) {
        ViewGroup.LayoutParams layoutParams = view.getRootView().getLayoutParams();
        WindowManager.LayoutParams layoutParams2 = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (layoutParams2 == null || (layoutParams2.flags & 8192) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int flagsWithSecureFlagInherited(PopupProperties popupProperties, boolean z) {
        if (popupProperties.getInheritSecurePolicy$ui_release() && z) {
            return popupProperties.getFlags$ui_release() | 8192;
        }
        if (popupProperties.getInheritSecurePolicy$ui_release() && !z) {
            return popupProperties.getFlags$ui_release() & (-8193);
        }
        return popupProperties.getFlags$ui_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRect toIntBounds(Rect rect) {
        return new IntRect(rect.left, rect.top, rect.right, rect.bottom);
    }
}
