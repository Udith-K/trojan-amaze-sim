package androidx.compose.ui.autofill;

import android.view.ViewStructure;

/* JADX INFO: compiled from: AutofillUtils.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AutofillApi23Helper {
    public static final AutofillApi23Helper INSTANCE = new AutofillApi23Helper();

    private AutofillApi23Helper() {
    }

    public final ViewStructure newChild(ViewStructure viewStructure, int i) {
        return viewStructure.newChild(i);
    }

    public final int addChildCount(ViewStructure viewStructure, int i) {
        return viewStructure.addChildCount(i);
    }

    public final void setId(ViewStructure viewStructure, int i, String str, String str2, String str3) {
        viewStructure.setId(i, str, str2, str3);
    }

    public final void setDimens(ViewStructure viewStructure, int i, int i2, int i3, int i4, int i5, int i6) {
        viewStructure.setDimens(i, i2, i3, i4, i5, i6);
    }
}
