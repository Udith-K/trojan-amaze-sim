package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.EditorInfo;
import androidx.emoji2.text.EmojiCompat;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: LegacyPlatformTextInputServiceAdapter.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LegacyPlatformTextInputServiceAdapter_androidKt {
    private static Function1 inputMethodManagerFactory = LegacyPlatformTextInputServiceAdapter_androidKt$inputMethodManagerFactory$1.INSTANCE;

    public static final Function1 getInputMethodManagerFactory() {
        return inputMethodManagerFactory;
    }

    public static final LegacyPlatformTextInputServiceAdapter createLegacyPlatformTextInputServiceAdapter() {
        return new AndroidLegacyPlatformTextInputServiceAdapter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateWithEmojiCompat(EditorInfo editorInfo) {
        if (EmojiCompat.isConfigured()) {
            EmojiCompat.get();
            throw null;
        }
    }
}
