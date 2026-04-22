package androidx.core.app;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes.dex */
abstract class NotificationCompatJellybean {
    private static final Object sExtrasLock = new Object();
    private static final Object sActionsLock = new Object();

    static Bundle getBundleForAction(NotificationCompat.Action action) {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        IconCompat iconCompat = action.getIconCompat();
        bundle2.putInt("icon", iconCompat != null ? iconCompat.getResId() : 0);
        bundle2.putCharSequence("title", action.getTitle());
        bundle2.putParcelable("actionIntent", action.getActionIntent());
        if (action.getExtras() != null) {
            bundle = new Bundle(action.getExtras());
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        bundle2.putBundle("extras", bundle);
        bundle2.putParcelableArray("remoteInputs", toBundleArray(action.getRemoteInputs()));
        bundle2.putBoolean("showsUserInterface", action.getShowsUserInterface());
        bundle2.putInt("semanticAction", action.getSemanticAction());
        return bundle2;
    }

    private static Bundle toBundle(RemoteInput remoteInput) {
        new Bundle();
        throw null;
    }

    private static Bundle[] toBundleArray(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[remoteInputArr.length];
        for (int i = 0; i < remoteInputArr.length; i++) {
            RemoteInput remoteInput = remoteInputArr[i];
            bundleArr[i] = toBundle(null);
        }
        return bundleArr;
    }
}
