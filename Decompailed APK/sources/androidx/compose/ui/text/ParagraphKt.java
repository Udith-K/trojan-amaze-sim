package androidx.compose.ui.text;

import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import java.util.List;

/* JADX INFO: compiled from: Paragraph.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ParagraphKt {
    /* JADX INFO: renamed from: Paragraph-UdtVg6A, reason: not valid java name */
    public static final Paragraph m2061ParagraphUdtVg6A(String str, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, List list, List list2, int i, boolean z) {
        return androidx.compose.ui.text.platform.AndroidParagraph_androidKt.m2249ActualParagraphO3s9Psw(str, textStyle, list, list2, i, z, j, density, resolver);
    }

    /* JADX INFO: renamed from: Paragraph-_EkL_-Y, reason: not valid java name */
    public static final Paragraph m2063Paragraph_EkL_Y(ParagraphIntrinsics paragraphIntrinsics, long j, int i, boolean z) {
        return androidx.compose.ui.text.platform.AndroidParagraph_androidKt.m2248ActualParagraphhBUhpc(paragraphIntrinsics, i, z, j);
    }

    public static final int ceilToInt(float f) {
        return (int) Math.ceil(f);
    }
}
