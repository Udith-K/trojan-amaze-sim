package androidx.lifecycle;

/* JADX INFO: compiled from: DefaultLifecycleObserver.kt */
/* JADX INFO: loaded from: classes.dex */
public interface DefaultLifecycleObserver extends LifecycleObserver {

    /* JADX INFO: renamed from: androidx.lifecycle.DefaultLifecycleObserver$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: DefaultLifecycleObserver.kt */
    public abstract /* synthetic */ class CC {
    }

    void onCreate(LifecycleOwner lifecycleOwner);

    void onDestroy(LifecycleOwner lifecycleOwner);

    void onPause(LifecycleOwner lifecycleOwner);

    void onResume(LifecycleOwner lifecycleOwner);

    void onStart(LifecycleOwner lifecycleOwner);

    void onStop(LifecycleOwner lifecycleOwner);
}
