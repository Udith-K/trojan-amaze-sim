package org.fdroid.fdroid.receiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.work.UnarchiveWorker;

/* JADX INFO: compiled from: UnarchivePackageReceiver.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/fdroid/fdroid/receiver/UnarchivePackageReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
@TargetApi(35)
public final class UnarchivePackageReceiver extends BroadcastReceiver {
    public static final int $stable = 0;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (!Intrinsics.areEqual(intent.getAction(), "android.intent.action.UNARCHIVE_PACKAGE")) {
            Log.w(UnarchivePackageReceiverKt.TAG, "Unknown action: " + intent.getAction());
            return;
        }
        String stringExtra = intent.getStringExtra("android.content.pm.extra.UNARCHIVE_PACKAGE_NAME");
        if (stringExtra == null) {
            throw new IllegalStateException("");
        }
        int intExtra = intent.getIntExtra("android.content.pm.extra.UNARCHIVE_ID", -1);
        boolean booleanExtra = intent.getBooleanExtra("android.content.pm.extra.UNARCHIVE_ALL_USERS", false);
        Log.i(UnarchivePackageReceiverKt.TAG, "Intent received, un-archiving " + stringExtra + "...");
        UnarchiveWorker.INSTANCE.updateNow(context, stringExtra, intExtra, booleanExtra);
    }
}
