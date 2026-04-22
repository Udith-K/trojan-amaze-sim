package androidx.compose.ui.platform.coreshims;

import android.os.Build;
import android.view.View;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;

/* JADX INFO: loaded from: classes.dex */
public abstract class ViewCompatShims {
    public static void setImportantForContentCapture(View view, int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.setImportantForContentCapture(view, i);
        }
    }

    public static ContentCaptureSessionCompat getContentCaptureSession(View view) {
        ContentCaptureSession contentCaptureSession;
        if (Build.VERSION.SDK_INT < 29 || (contentCaptureSession = Api29Impl.getContentCaptureSession(view)) == null) {
            return null;
        }
        return ContentCaptureSessionCompat.toContentCaptureSessionCompat(contentCaptureSession, view);
    }

    public static AutofillIdCompat getAutofillId(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            return AutofillIdCompat.toAutofillIdCompat(Api26Impl.getAutofillId(view));
        }
        return null;
    }

    static class Api26Impl {
        public static AutofillId getAutofillId(View view) {
            return view.getAutofillId();
        }
    }

    private static class Api29Impl {
        static ContentCaptureSession getContentCaptureSession(View view) {
            return view.getContentCaptureSession();
        }
    }

    private static class Api30Impl {
        static void setImportantForContentCapture(View view, int i) {
            view.setImportantForContentCapture(i);
        }
    }
}
