package androidx.activity.compose;

import androidx.activity.result.ActivityResultLauncher;
import androidx.core.app.ActivityOptionsCompat;
import kotlin.Unit;

/* JADX INFO: compiled from: ActivityResultRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ActivityResultLauncherHolder {
    private ActivityResultLauncher launcher;

    public final void setLauncher(ActivityResultLauncher activityResultLauncher) {
        this.launcher = activityResultLauncher;
    }

    public final void launch(Object obj, ActivityOptionsCompat activityOptionsCompat) {
        Unit unit;
        ActivityResultLauncher activityResultLauncher = this.launcher;
        if (activityResultLauncher != null) {
            activityResultLauncher.launch(obj, activityOptionsCompat);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IllegalStateException("Launcher has not been initialized");
        }
    }

    public final void unregister() {
        Unit unit;
        ActivityResultLauncher activityResultLauncher = this.launcher;
        if (activityResultLauncher != null) {
            activityResultLauncher.unregister();
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IllegalStateException("Launcher has not been initialized");
        }
    }
}
