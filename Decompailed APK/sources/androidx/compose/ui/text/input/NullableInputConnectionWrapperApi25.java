package androidx.compose.ui.text.input;

import android.os.Bundle;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: NullableInputConnectionWrapper.android.kt */
/* JADX INFO: loaded from: classes.dex */
class NullableInputConnectionWrapperApi25 extends NullableInputConnectionWrapperApi24 {
    public NullableInputConnectionWrapperApi25(InputConnection inputConnection, Function1 function1) {
        super(inputConnection, function1);
    }

    @Override // androidx.compose.ui.text.input.NullableInputConnectionWrapperApi21, android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
        InputConnection delegate = getDelegate();
        if (delegate != null) {
            return delegate.commitContent(inputContentInfo, i, bundle);
        }
        return false;
    }
}
