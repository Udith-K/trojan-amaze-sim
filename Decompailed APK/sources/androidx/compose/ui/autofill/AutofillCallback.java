package androidx.compose.ui.autofill;

import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillManager$AutofillCallback;

/* JADX INFO: compiled from: AutofillCallback.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AutofillCallback extends AutofillManager$AutofillCallback {
    public static final AutofillCallback INSTANCE = new AutofillCallback();

    private AutofillCallback() {
    }

    public void onAutofillEvent(View view, int i, int i2) {
        String str;
        super.onAutofillEvent(view, i, i2);
        if (i2 == 1) {
            str = "Autofill popup was shown.";
        } else if (i2 == 2) {
            str = "Autofill popup was hidden.";
        } else if (i2 == 3) {
            str = "Autofill popup isn't shown because autofill is not available.\n\nDid you set up autofill?\n1. Go to Settings > System > Languages&input > Advanced > Autofill Service\n2. Pick a service\n\nDid you add an account?\n1. Go to Settings > System > Languages&input > Advanced\n2. Click on the settings icon next to the Autofill Service\n3. Add your account";
        } else {
            str = "Unknown status event.";
        }
        Log.d("Autofill Status", str);
    }

    public final void register(AndroidAutofill androidAutofill) {
        androidAutofill.getAutofillManager().registerCallback(AutofillCallback$$ExternalSyntheticApiModelOutline0.m(this));
    }

    public final void unregister(AndroidAutofill androidAutofill) {
        androidAutofill.getAutofillManager().unregisterCallback(AutofillCallback$$ExternalSyntheticApiModelOutline0.m(this));
    }
}
