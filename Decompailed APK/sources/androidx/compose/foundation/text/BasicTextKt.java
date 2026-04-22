package androidx.compose.foundation.text;

import androidx.compose.foundation.text.modifiers.SelectionController;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringElement;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BasicText.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BasicTextKt {
    private static final AnnotatedString BasicText_RWo7tUw$lambda$5(MutableState mutableState) {
        return (AnnotatedString) mutableState.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:156:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00fa  */
    /* JADX INFO: renamed from: BasicText-VhcvRP8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m372BasicTextVhcvRP8(final java.lang.String r42, androidx.compose.ui.Modifier r43, androidx.compose.ui.text.TextStyle r44, kotlin.jvm.functions.Function1 r45, int r46, boolean r47, int r48, int r49, androidx.compose.ui.graphics.ColorProducer r50, androidx.compose.runtime.Composer r51, final int r52, final int r53) {
        /*
            Method dump skipped, instruction units count: 763
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m372BasicTextVhcvRP8(java.lang.String, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, int, androidx.compose.ui.graphics.ColorProducer, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:184:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0111  */
    /* JADX INFO: renamed from: BasicText-RWo7tUw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m371BasicTextRWo7tUw(final androidx.compose.ui.text.AnnotatedString r52, androidx.compose.ui.Modifier r53, androidx.compose.ui.text.TextStyle r54, kotlin.jvm.functions.Function1 r55, int r56, boolean r57, int r58, int r59, java.util.Map r60, androidx.compose.ui.graphics.ColorProducer r61, androidx.compose.runtime.Composer r62, final int r63, final int r64) {
        /*
            Method dump skipped, instruction units count: 905
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m371BasicTextRWo7tUw(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, int, java.util.Map, androidx.compose.ui.graphics.ColorProducer, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List measureWithTextRangeMeasureConstraints(List list, Function0 function0) {
        if (!((Boolean) function0.invoke()).booleanValue()) {
            return null;
        }
        TextRangeLayoutMeasureScope textRangeLayoutMeasureScope = new TextRangeLayoutMeasureScope();
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = (Measurable) list.get(i);
            Object parentData = measurable.getParentData();
            Intrinsics.checkNotNull(parentData, "null cannot be cast to non-null type androidx.compose.foundation.text.TextRangeLayoutModifier");
            TextRangeLayoutMeasureResult textRangeLayoutMeasureResultMeasure = ((TextRangeLayoutModifier) parentData).getMeasurePolicy().measure(textRangeLayoutMeasureScope);
            arrayList.add(new Pair(measurable.mo1743measureBRTryo0(Constraints.Companion.m2397fitPrioritizingWidthZbe2FdA(textRangeLayoutMeasureResultMeasure.getWidth(), textRangeLayoutMeasureResultMeasure.getWidth(), textRangeLayoutMeasureResultMeasure.getHeight(), textRangeLayoutMeasureResultMeasure.getHeight())), textRangeLayoutMeasureResultMeasure.getPlace()));
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: textModifier-cFh6CEA, reason: not valid java name */
    private static final Modifier m375textModifiercFh6CEA(Modifier modifier, AnnotatedString annotatedString, TextStyle textStyle, Function1 function1, int i, boolean z, int i2, int i3, FontFamily.Resolver resolver, List list, Function1 function12, SelectionController selectionController, ColorProducer colorProducer, Function1 function13) {
        return modifier.then(Modifier.Companion).then(new TextAnnotatedStringElement(annotatedString, textStyle, resolver, function1, i, z, i2, i3, list, function12, null, colorProducer, function13, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:102:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0453  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0474  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0485  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x04a8  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:280:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0122  */
    /* JADX INFO: renamed from: LayoutWithLinksAndInlineContent-vOo2fZY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m373LayoutWithLinksAndInlineContentvOo2fZY(final androidx.compose.ui.Modifier r46, final androidx.compose.ui.text.AnnotatedString r47, final kotlin.jvm.functions.Function1 r48, final boolean r49, java.util.Map r50, final androidx.compose.ui.text.TextStyle r51, final int r52, final boolean r53, final int r54, final int r55, final androidx.compose.ui.text.font.FontFamily.Resolver r56, final androidx.compose.foundation.text.modifiers.SelectionController r57, final androidx.compose.ui.graphics.ColorProducer r58, final kotlin.jvm.functions.Function1 r59, androidx.compose.runtime.Composer r60, final int r61, final int r62, final int r63) {
        /*
            Method dump skipped, instruction units count: 1253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextKt.m373LayoutWithLinksAndInlineContentvOo2fZY(androidx.compose.ui.Modifier, androidx.compose.ui.text.AnnotatedString, kotlin.jvm.functions.Function1, boolean, java.util.Map, androidx.compose.ui.text.TextStyle, int, boolean, int, int, androidx.compose.ui.text.font.FontFamily$Resolver, androidx.compose.foundation.text.modifiers.SelectionController, androidx.compose.ui.graphics.ColorProducer, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
