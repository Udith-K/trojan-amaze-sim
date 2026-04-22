package androidx.compose.ui.platform;

import android.view.View;
import android.view.translation.ViewTranslationCallback;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidComposeView.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class AndroidComposeViewTranslationCallback implements ViewTranslationCallback {
    public static final AndroidComposeViewTranslationCallback INSTANCE = new AndroidComposeViewTranslationCallback();

    private AndroidComposeViewTranslationCallback() {
    }

    public boolean onShowTranslation(View view) {
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type androidx.compose.ui.platform.AndroidComposeView");
        ((AndroidComposeView) view).getContentCaptureManager$ui_release().onShowTranslation$ui_release();
        return true;
    }

    public boolean onHideTranslation(View view) {
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type androidx.compose.ui.platform.AndroidComposeView");
        ((AndroidComposeView) view).getContentCaptureManager$ui_release().onHideTranslation$ui_release();
        return true;
    }

    public boolean onClearTranslation(View view) {
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type androidx.compose.ui.platform.AndroidComposeView");
        ((AndroidComposeView) view).getContentCaptureManager$ui_release().onClearTranslation$ui_release();
        return true;
    }
}
