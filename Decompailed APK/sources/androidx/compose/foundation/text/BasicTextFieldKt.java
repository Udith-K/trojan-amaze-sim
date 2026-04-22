package androidx.compose.foundation.text;

import androidx.compose.foundation.text.input.TextFieldDecorator;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;

/* JADX INFO: compiled from: BasicTextField.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BasicTextFieldKt {
    private static final TextFieldDecorator DefaultTextFieldDecorator = new TextFieldDecorator() { // from class: androidx.compose.foundation.text.BasicTextFieldKt$DefaultTextFieldDecorator$1
    };
    private static final long MinTouchTargetSizeForHandles;

    static {
        float f = 40;
        MinTouchTargetSizeForHandles = DpKt.m2427DpSizeYgX7TsA(Dp.m2416constructorimpl(f), Dp.m2416constructorimpl(f));
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:266:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x011f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void BasicTextField(final androidx.compose.ui.text.input.TextFieldValue r37, final kotlin.jvm.functions.Function1 r38, androidx.compose.ui.Modifier r39, boolean r40, boolean r41, androidx.compose.ui.text.TextStyle r42, androidx.compose.foundation.text.KeyboardOptions r43, androidx.compose.foundation.text.KeyboardActions r44, boolean r45, int r46, int r47, androidx.compose.ui.text.input.VisualTransformation r48, kotlin.jvm.functions.Function1 r49, androidx.compose.foundation.interaction.MutableInteractionSource r50, androidx.compose.ui.graphics.Brush r51, kotlin.jvm.functions.Function3 r52, androidx.compose.runtime.Composer r53, final int r54, final int r55, final int r56) {
        /*
            Method dump skipped, instruction units count: 1046
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
