package androidx.compose.ui.autofill;

import android.util.SparseArray;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Map;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidAutofill.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidAutofill_androidKt {
    public static final void populateViewStructure(AndroidAutofill androidAutofill, ViewStructure viewStructure) {
        int iAddChildCount = AutofillApi23Helper.INSTANCE.addChildCount(viewStructure, androidAutofill.getAutofillTree().getChildren().size());
        for (Map.Entry entry : androidAutofill.getAutofillTree().getChildren().entrySet()) {
            int iIntValue = ((Number) entry.getKey()).intValue();
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(entry.getValue());
            AutofillApi23Helper autofillApi23Helper = AutofillApi23Helper.INSTANCE;
            ViewStructure viewStructureNewChild = autofillApi23Helper.newChild(viewStructure, iAddChildCount);
            if (viewStructureNewChild != null) {
                AutofillApi26Helper autofillApi26Helper = AutofillApi26Helper.INSTANCE;
                AutofillId autofillId = autofillApi26Helper.getAutofillId(viewStructure);
                Intrinsics.checkNotNull(autofillId);
                autofillApi26Helper.setAutofillId(viewStructureNewChild, autofillId, iIntValue);
                autofillApi23Helper.setId(viewStructureNewChild, iIntValue, androidAutofill.getView().getContext().getPackageName(), null, null);
                autofillApi26Helper.setAutofillType(viewStructureNewChild, ContentDataType.Companion.m1072getTextA48pgw8());
                throw null;
            }
            iAddChildCount++;
        }
    }

    public static final void performAutofill(AndroidAutofill androidAutofill, SparseArray sparseArray) {
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = sparseArray.keyAt(i);
            AutofillValue autofillValueM = AndroidAutofill_androidKt$$ExternalSyntheticApiModelOutline0.m(sparseArray.get(iKeyAt));
            AutofillApi26Helper autofillApi26Helper = AutofillApi26Helper.INSTANCE;
            if (autofillApi26Helper.isText(autofillValueM)) {
                androidAutofill.getAutofillTree().performAutofill(iKeyAt, autofillApi26Helper.textValue(autofillValueM).toString());
            } else {
                if (autofillApi26Helper.isDate(autofillValueM)) {
                    throw new NotImplementedError("An operation is not implemented: b/138604541: Add onFill() callback for date");
                }
                if (autofillApi26Helper.isList(autofillValueM)) {
                    throw new NotImplementedError("An operation is not implemented: b/138604541: Add onFill() callback for list");
                }
                if (autofillApi26Helper.isToggle(autofillValueM)) {
                    throw new NotImplementedError("An operation is not implemented: b/138604541:  Add onFill() callback for toggle");
                }
            }
        }
    }
}
