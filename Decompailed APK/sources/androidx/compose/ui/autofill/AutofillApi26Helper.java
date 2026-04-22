package androidx.compose.ui.autofill;

import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;

/* JADX INFO: compiled from: AutofillUtils.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AutofillApi26Helper {
    public static final AutofillApi26Helper INSTANCE = new AutofillApi26Helper();

    private AutofillApi26Helper() {
    }

    public final void setAutofillId(ViewStructure viewStructure, AutofillId autofillId, int i) {
        viewStructure.setAutofillId(autofillId, i);
    }

    public final AutofillId getAutofillId(ViewStructure viewStructure) {
        return viewStructure.getAutofillId();
    }

    public final void setAutofillType(ViewStructure viewStructure, int i) {
        viewStructure.setAutofillType(i);
    }

    public final void setAutofillHints(ViewStructure viewStructure, String[] strArr) {
        viewStructure.setAutofillHints(strArr);
    }

    public final boolean isText(AutofillValue autofillValue) {
        return autofillValue.isText();
    }

    public final boolean isDate(AutofillValue autofillValue) {
        return autofillValue.isDate();
    }

    public final boolean isList(AutofillValue autofillValue) {
        return autofillValue.isList();
    }

    public final boolean isToggle(AutofillValue autofillValue) {
        return autofillValue.isToggle();
    }

    public final CharSequence textValue(AutofillValue autofillValue) {
        return autofillValue.getTextValue();
    }
}
