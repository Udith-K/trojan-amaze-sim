package androidx.compose.ui.text.input;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GapBuffer.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class GapBuffer_jvmKt {
    public static final void toCharArray(String str, char[] cArr, int i, int i2, int i3) {
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type java.lang.String");
        str.getChars(i2, i3, cArr, i);
    }
}
