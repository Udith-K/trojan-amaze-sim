package androidx.emoji2.viewsintegration;

import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.emoji2.text.EmojiCompat;

/* JADX INFO: loaded from: classes.dex */
final class EmojiTextWatcher implements TextWatcher {
    private final EditText mEditText;
    private final boolean mExpectInitializedEmojiCompat;
    private EmojiCompat.InitCallback mInitCallback;
    private int mMaxEmojiCount = Integer.MAX_VALUE;
    private int mEmojiReplaceStrategy = 0;
    private boolean mEnabled = true;

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    EmojiTextWatcher(EditText editText, boolean z) {
        this.mEditText = editText;
        this.mExpectInitializedEmojiCompat = z;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.mEditText.isInEditMode() || shouldSkipForDisabledOrNotConfigured() || i2 > i3 || !(charSequence instanceof Spannable)) {
            return;
        }
        EmojiCompat.get();
        throw null;
    }

    private boolean shouldSkipForDisabledOrNotConfigured() {
        return (this.mEnabled && (this.mExpectInitializedEmojiCompat || EmojiCompat.isConfigured())) ? false : true;
    }

    public void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            this.mEnabled = z;
            if (z) {
                EmojiCompat.get();
                throw null;
            }
        }
    }
}
