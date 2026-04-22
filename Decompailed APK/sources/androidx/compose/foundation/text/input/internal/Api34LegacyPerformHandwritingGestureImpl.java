package androidx.compose.foundation.text.input.internal;

import android.os.CancellationSignal;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.PreviewableHandwritingGesture;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.platform.ViewConfiguration;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: RecordingInputConnection.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class Api34LegacyPerformHandwritingGestureImpl {
    public static final Api34LegacyPerformHandwritingGestureImpl INSTANCE = new Api34LegacyPerformHandwritingGestureImpl();

    private Api34LegacyPerformHandwritingGestureImpl() {
    }

    public final void performHandwritingGesture(LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, HandwritingGesture handwritingGesture, ViewConfiguration viewConfiguration, Executor executor, final IntConsumer intConsumer, Function1 function1) {
        final int iPerformHandwritingGesture$foundation_release = legacyTextFieldState != null ? HandwritingGestureApi34.INSTANCE.performHandwritingGesture$foundation_release(legacyTextFieldState, handwritingGesture, textFieldSelectionManager, viewConfiguration, function1) : 3;
        if (intConsumer == null) {
            return;
        }
        if (executor != null) {
            executor.execute(new Runnable() { // from class: androidx.compose.foundation.text.input.internal.Api34LegacyPerformHandwritingGestureImpl$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Api34LegacyPerformHandwritingGestureImpl$$ExternalSyntheticApiModelOutline0.m(intConsumer, iPerformHandwritingGesture$foundation_release);
                }
            });
        } else {
            intConsumer.accept(iPerformHandwritingGesture$foundation_release);
        }
    }

    public final boolean previewHandwritingGesture(LegacyTextFieldState legacyTextFieldState, TextFieldSelectionManager textFieldSelectionManager, PreviewableHandwritingGesture previewableHandwritingGesture, CancellationSignal cancellationSignal) {
        if (legacyTextFieldState != null) {
            return HandwritingGestureApi34.INSTANCE.previewHandwritingGesture$foundation_release(legacyTextFieldState, previewableHandwritingGesture, textFieldSelectionManager, cancellationSignal);
        }
        return false;
    }
}
