package androidx.compose.foundation.text.handwriting;

import android.os.Build;

/* JADX INFO: compiled from: StylusHandwriting.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StylusHandwriting_androidKt {
    private static final boolean isStylusHandwritingSupported;

    public static final boolean isStylusHandwritingSupported() {
        return isStylusHandwritingSupported;
    }

    static {
        isStylusHandwritingSupported = Build.VERSION.SDK_INT >= 34;
    }
}
