package androidx.compose.ui.text.input;

import android.os.Build;
import android.view.inputmethod.InputConnection;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: NullableInputConnectionWrapper.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class NullableInputConnectionWrapper_androidKt {
    public static final NullableInputConnectionWrapper NullableInputConnectionWrapper(InputConnection inputConnection, Function1 function1) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 34) {
            return new NullableInputConnectionWrapperApi34(inputConnection, function1);
        }
        if (i >= 25) {
            return new NullableInputConnectionWrapperApi25(inputConnection, function1);
        }
        if (i >= 24) {
            return new NullableInputConnectionWrapperApi24(inputConnection, function1);
        }
        return new NullableInputConnectionWrapperApi21(inputConnection, function1);
    }
}
