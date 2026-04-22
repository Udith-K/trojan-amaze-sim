package org.fdroid.fdroid.views.apps;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.TtsSpan;
import android.widget.EditText;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
public class CategoryTextWatcher implements TextWatcher {
    private final Context context;
    private final SearchTermsChangedListener listener;
    private int removeTo = -1;
    private boolean requiresSpanRecalculation = false;
    private final EditText widget;

    interface SearchTermsChangedListener {
        void onSearchTermsChanged(String str, String str2);
    }

    CategoryTextWatcher(Context context, EditText editText, SearchTermsChangedListener searchTermsChangedListener) {
        this.context = context;
        this.widget = editText;
        this.listener = searchTermsChangedListener;
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.removeTo = -1;
        boolean z = i2 > 0;
        if (i == 0 && z) {
            return;
        }
        String string = charSequence.toString();
        if (!z || string.indexOf(58, i) >= i + i2 || string.indexOf(58) < i) {
            return;
        }
        this.removeTo = (i + i2) - 1;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (i3 <= 0 || charSequence.subSequence(i, i3 + i).toString().indexOf(58) < 0 || charSequence.subSequence(0, i).toString().indexOf(58) != -1) {
            return;
        }
        this.requiresSpanRecalculation = true;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        int i = this.removeTo;
        if (i >= 0) {
            removeLeadingCharacters(editable, i);
            this.removeTo = -1;
        } else if (this.requiresSpanRecalculation) {
            prepareSpans(editable);
            this.requiresSpanRecalculation = false;
        }
        int iIndexOf = editable.toString().indexOf(58);
        this.listener.onSearchTermsChanged(iIndexOf == -1 ? null : editable.subSequence(0, iIndexOf).toString(), editable.subSequence(iIndexOf != -1 ? iIndexOf + 1 : 0, editable.length()).toString());
    }

    private void removeLeadingCharacters(Editable editable, int i) {
        this.widget.removeTextChangedListener(this);
        if (editable.length() < i) {
            i = editable.length();
        }
        editable.replace(0, i, "");
        this.widget.addTextChangedListener(this);
    }

    private void prepareSpans(Editable editable) {
        if (editable == null) {
            return;
        }
        removeSpans(editable, CategorySpan.class);
        removeSpans(editable, TtsSpan.class);
        int iIndexOf = editable.toString().indexOf(58);
        if (iIndexOf > 0) {
            editable.setSpan(new CategorySpan(this.context), 0, iIndexOf + 1, 33);
            editable.setSpan(new TtsSpan.TextBuilder(this.context.getString(R.string.tts_category_name, editable.subSequence(0, iIndexOf))).build(), 0, 0, 33);
        }
    }

    private <T> void removeSpans(Editable editable, Class<T> cls) {
        for (Object obj : editable.getSpans(0, editable.length(), cls)) {
            editable.removeSpan(obj);
        }
    }
}
