package androidx.lifecycle;

import android.app.Application;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidViewModel.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidViewModel extends ViewModel {
    private final Application application;

    public AndroidViewModel(Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        this.application = application;
    }

    public <T extends Application> T getApplication() {
        T t = (T) this.application;
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of androidx.lifecycle.AndroidViewModel.getApplication");
        return t;
    }
}
