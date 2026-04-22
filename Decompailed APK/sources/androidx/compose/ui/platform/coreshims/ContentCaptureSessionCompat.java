package androidx.compose.ui.platform.coreshims;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ContentCaptureSessionCompat {
    private final View mView;
    private final Object mWrappedObj;

    public static ContentCaptureSessionCompat toContentCaptureSessionCompat(ContentCaptureSession contentCaptureSession, View view) {
        return new ContentCaptureSessionCompat(contentCaptureSession, view);
    }

    private ContentCaptureSessionCompat(ContentCaptureSession contentCaptureSession, View view) {
        this.mWrappedObj = contentCaptureSession;
        this.mView = view;
    }

    public AutofillId newAutofillId(long j) {
        if (Build.VERSION.SDK_INT < 29) {
            return null;
        }
        ContentCaptureSession contentCaptureSessionM = ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj);
        AutofillIdCompat autofillId = ViewCompatShims.getAutofillId(this.mView);
        Objects.requireNonNull(autofillId);
        return Api29Impl.newAutofillId(contentCaptureSessionM, autofillId.toAutofillId(), j);
    }

    public ViewStructureCompat newVirtualViewStructure(AutofillId autofillId, long j) {
        if (Build.VERSION.SDK_INT >= 29) {
            return ViewStructureCompat.toViewStructureCompat(Api29Impl.newVirtualViewStructure(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), autofillId, j));
        }
        return null;
    }

    public void notifyViewsAppeared(List list) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 34) {
            Api34Impl.notifyViewsAppeared(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), list);
            return;
        }
        if (i >= 29) {
            ViewStructure viewStructureNewViewStructure = Api29Impl.newViewStructure(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), this.mView);
            Api23Impl.getExtras(viewStructureNewViewStructure).putBoolean("TREAT_AS_VIEW_TREE_APPEARING", true);
            Api29Impl.notifyViewAppeared(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), viewStructureNewViewStructure);
            for (int i2 = 0; i2 < list.size(); i2++) {
                Api29Impl.notifyViewAppeared(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), (ViewStructure) list.get(i2));
            }
            ViewStructure viewStructureNewViewStructure2 = Api29Impl.newViewStructure(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), this.mView);
            Api23Impl.getExtras(viewStructureNewViewStructure2).putBoolean("TREAT_AS_VIEW_TREE_APPEARED", true);
            Api29Impl.notifyViewAppeared(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), viewStructureNewViewStructure2);
        }
    }

    public void notifyViewsDisappeared(long[] jArr) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 34) {
            ContentCaptureSession contentCaptureSessionM = ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj);
            AutofillIdCompat autofillId = ViewCompatShims.getAutofillId(this.mView);
            Objects.requireNonNull(autofillId);
            Api29Impl.notifyViewsDisappeared(contentCaptureSessionM, autofillId.toAutofillId(), jArr);
            return;
        }
        if (i >= 29) {
            ViewStructure viewStructureNewViewStructure = Api29Impl.newViewStructure(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), this.mView);
            Api23Impl.getExtras(viewStructureNewViewStructure).putBoolean("TREAT_AS_VIEW_TREE_APPEARING", true);
            Api29Impl.notifyViewAppeared(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), viewStructureNewViewStructure);
            ContentCaptureSession contentCaptureSessionM2 = ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj);
            AutofillIdCompat autofillId2 = ViewCompatShims.getAutofillId(this.mView);
            Objects.requireNonNull(autofillId2);
            Api29Impl.notifyViewsDisappeared(contentCaptureSessionM2, autofillId2.toAutofillId(), jArr);
            ViewStructure viewStructureNewViewStructure2 = Api29Impl.newViewStructure(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), this.mView);
            Api23Impl.getExtras(viewStructureNewViewStructure2).putBoolean("TREAT_AS_VIEW_TREE_APPEARED", true);
            Api29Impl.notifyViewAppeared(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), viewStructureNewViewStructure2);
        }
    }

    public void notifyViewTextChanged(AutofillId autofillId, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.notifyViewTextChanged(ContentCaptureSessionCompat$$ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), autofillId, charSequence);
        }
    }

    private static class Api34Impl {
        static void notifyViewsAppeared(ContentCaptureSession contentCaptureSession, List<ViewStructure> list) {
            contentCaptureSession.notifyViewsAppeared(list);
        }
    }

    private static class Api29Impl {
        static void notifyViewsDisappeared(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long[] jArr) {
            contentCaptureSession.notifyViewsDisappeared(autofillId, jArr);
        }

        static void notifyViewAppeared(ContentCaptureSession contentCaptureSession, ViewStructure viewStructure) {
            contentCaptureSession.notifyViewAppeared(viewStructure);
        }

        static ViewStructure newViewStructure(ContentCaptureSession contentCaptureSession, View view) {
            return contentCaptureSession.newViewStructure(view);
        }

        static ViewStructure newVirtualViewStructure(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j) {
            return contentCaptureSession.newVirtualViewStructure(autofillId, j);
        }

        static AutofillId newAutofillId(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j) {
            return contentCaptureSession.newAutofillId(autofillId, j);
        }

        public static void notifyViewTextChanged(ContentCaptureSession contentCaptureSession, AutofillId autofillId, CharSequence charSequence) {
            contentCaptureSession.notifyViewTextChanged(autofillId, charSequence);
        }
    }

    private static class Api23Impl {
        static Bundle getExtras(ViewStructure viewStructure) {
            return viewStructure.getExtras();
        }
    }
}
