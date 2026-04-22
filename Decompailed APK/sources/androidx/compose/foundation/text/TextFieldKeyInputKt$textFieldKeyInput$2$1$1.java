package androidx.compose.foundation.text;

import androidx.compose.ui.input.key.KeyEvent;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: TextFieldKeyInput.kt */
/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class TextFieldKeyInputKt$textFieldKeyInput$2$1$1 extends FunctionReferenceImpl implements Function1 {
    TextFieldKeyInputKt$textFieldKeyInput$2$1$1(Object obj) {
        super(1, obj, TextFieldKeyInput.class, "process", "process-ZmokQxo(Landroid/view/KeyEvent;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return m448invokeZmokQxo(((KeyEvent) obj).m1620unboximpl());
    }

    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
    public final Boolean m448invokeZmokQxo(android.view.KeyEvent keyEvent) {
        return Boolean.valueOf(((TextFieldKeyInput) this.receiver).m446processZmokQxo(keyEvent));
    }
}
