package info.guardianproject.panic;

import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public abstract class Panic {
    public static boolean isTriggerIntent(Intent intent) {
        return intent != null && "info.guardianproject.panic.action.TRIGGER".equals(intent.getAction());
    }
}
