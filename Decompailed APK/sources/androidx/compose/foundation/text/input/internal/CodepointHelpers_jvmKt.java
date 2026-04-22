package androidx.compose.foundation.text.input.internal;

/* JADX INFO: compiled from: CodepointHelpers.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CodepointHelpers_jvmKt {
    public static final int codePointAt(CharSequence charSequence, int i) {
        return Character.codePointAt(charSequence, i);
    }

    public static final int charCount(int i) {
        return Character.charCount(i);
    }

    public static final int codePointBefore(CharSequence charSequence, int i) {
        return Character.codePointBefore(charSequence, i);
    }
}
