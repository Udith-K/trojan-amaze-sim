package androidx.compose.ui.autofill;

import android.view.View;
import android.view.autofill.AutofillManager;

/* JADX INFO: compiled from: AndroidAutofill.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidAutofill implements Autofill {
    private final AutofillManager autofillManager;
    private final AutofillTree autofillTree;
    private final View view;

    public AndroidAutofill(View view, AutofillTree autofillTree) {
        this.view = view;
        this.autofillTree = autofillTree;
        AutofillManager autofillManagerM = AndroidAutofill$$ExternalSyntheticApiModelOutline1.m(view.getContext().getSystemService(AndroidAutofill$$ExternalSyntheticApiModelOutline0.m()));
        if (autofillManagerM == null) {
            throw new IllegalStateException("Autofill service could not be located.");
        }
        this.autofillManager = autofillManagerM;
        view.setImportantForAutofill(1);
    }

    public final AutofillTree getAutofillTree() {
        return this.autofillTree;
    }

    public final View getView() {
        return this.view;
    }

    public final AutofillManager getAutofillManager() {
        return this.autofillManager;
    }
}
