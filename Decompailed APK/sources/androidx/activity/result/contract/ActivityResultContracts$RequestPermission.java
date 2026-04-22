package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.content.ContextCompat;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActivityResultContracts.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ActivityResultContracts$RequestPermission extends ActivityResultContract {
    @Override // androidx.activity.result.contract.ActivityResultContract
    public Intent createIntent(Context context, String input) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(input, "input");
        return ActivityResultContracts$RequestMultiplePermissions.Companion.createIntent$activity_release(new String[]{input});
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public Boolean parseResult(int i, Intent intent) {
        if (intent == null || i != -1) {
            return Boolean.FALSE;
        }
        int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
        boolean z = false;
        if (intArrayExtra != null) {
            int length = intArrayExtra.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (intArrayExtra[i2] == 0) {
                    z = true;
                    break;
                }
                i2++;
            }
        }
        return Boolean.valueOf(z);
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public ActivityResultContract.SynchronousResult getSynchronousResult(Context context, String input) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(input, "input");
        if (ContextCompat.checkSelfPermission(context, input) == 0) {
            return new ActivityResultContract.SynchronousResult(Boolean.TRUE);
        }
        return null;
    }
}
