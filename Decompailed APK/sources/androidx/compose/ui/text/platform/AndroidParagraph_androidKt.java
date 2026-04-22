package androidx.compose.ui.text.platform;

import androidx.compose.ui.text.AndroidParagraph;
import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActualParagraph.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidParagraph_androidKt {
    /* JADX INFO: renamed from: ActualParagraph-O3s9Psw, reason: not valid java name */
    public static final Paragraph m2249ActualParagraphO3s9Psw(String str, TextStyle textStyle, List list, List list2, int i, boolean z, long j, Density density, FontFamily.Resolver resolver) {
        return new AndroidParagraph(new AndroidParagraphIntrinsics(str, textStyle, list, list2, resolver, density), i, z, j, null);
    }

    /* JADX INFO: renamed from: ActualParagraph--hBUhpc, reason: not valid java name */
    public static final Paragraph m2248ActualParagraphhBUhpc(ParagraphIntrinsics paragraphIntrinsics, int i, boolean z, long j) {
        Intrinsics.checkNotNull(paragraphIntrinsics, "null cannot be cast to non-null type androidx.compose.ui.text.platform.AndroidParagraphIntrinsics");
        return new AndroidParagraph((AndroidParagraphIntrinsics) paragraphIntrinsics, i, z, j, null);
    }
}
