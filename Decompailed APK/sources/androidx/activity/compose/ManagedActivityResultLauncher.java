package androidx.activity.compose;

import androidx.activity.result.ActivityResultLauncher;
import androidx.compose.runtime.State;
import androidx.core.app.ActivityOptionsCompat;

/* JADX INFO: compiled from: ActivityResultRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ManagedActivityResultLauncher extends ActivityResultLauncher {
    private final State currentContract;
    private final ActivityResultLauncherHolder launcher;

    public ManagedActivityResultLauncher(ActivityResultLauncherHolder activityResultLauncherHolder, State state) {
        this.launcher = activityResultLauncherHolder;
        this.currentContract = state;
    }

    @Override // androidx.activity.result.ActivityResultLauncher
    public void unregister() {
        throw new UnsupportedOperationException("Registration is automatically handled by rememberLauncherForActivityResult");
    }

    @Override // androidx.activity.result.ActivityResultLauncher
    public void launch(Object obj, ActivityOptionsCompat activityOptionsCompat) {
        this.launcher.launch(obj, activityOptionsCompat);
    }
}
