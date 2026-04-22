package androidx.compose.ui.text.platform;

import androidx.compose.runtime.State;
import androidx.emoji2.text.EmojiCompat;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EmojiCompatStatus.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class DefaultImpl implements EmojiCompatStatusDelegate {
    private State loadState;

    public DefaultImpl() {
        this.loadState = EmojiCompat.isConfigured() ? getFontLoadState() : null;
    }

    @Override // androidx.compose.ui.text.platform.EmojiCompatStatusDelegate
    public State getFontLoaded() {
        State state = this.loadState;
        if (state != null) {
            Intrinsics.checkNotNull(state);
            return state;
        }
        if (!EmojiCompat.isConfigured()) {
            return EmojiCompatStatus_androidKt.Falsey;
        }
        State fontLoadState = getFontLoadState();
        this.loadState = fontLoadState;
        Intrinsics.checkNotNull(fontLoadState);
        return fontLoadState;
    }

    private final State getFontLoadState() {
        EmojiCompat.get();
        throw null;
    }
}
