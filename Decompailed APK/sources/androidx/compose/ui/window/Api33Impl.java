package androidx.compose.ui.window;

import android.view.View;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: AndroidPopup.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class Api33Impl {
    public static final Api33Impl INSTANCE = new Api33Impl();

    private Api33Impl() {
    }

    public static final OnBackInvokedCallback createBackCallback(final Function0 function0) {
        return new OnBackInvokedCallback() { // from class: androidx.compose.ui.window.Api33Impl$$ExternalSyntheticLambda0
            public final void onBackInvoked() {
                Api33Impl.createBackCallback$lambda$0(function0);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createBackCallback$lambda$0(Function0 function0) {
        if (function0 != null) {
            function0.invoke();
        }
    }

    public static final void maybeRegisterBackCallback(View view, Object obj) {
        OnBackInvokedDispatcher onBackInvokedDispatcherFindOnBackInvokedDispatcher;
        if (!(obj instanceof OnBackInvokedCallback) || (onBackInvokedDispatcherFindOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher()) == null) {
            return;
        }
        onBackInvokedDispatcherFindOnBackInvokedDispatcher.registerOnBackInvokedCallback(1000000, (OnBackInvokedCallback) obj);
    }

    public static final void maybeUnregisterBackCallback(View view, Object obj) {
        OnBackInvokedDispatcher onBackInvokedDispatcherFindOnBackInvokedDispatcher;
        if (!(obj instanceof OnBackInvokedCallback) || (onBackInvokedDispatcherFindOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher()) == null) {
            return;
        }
        onBackInvokedDispatcherFindOnBackInvokedDispatcher.unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj);
    }
}
