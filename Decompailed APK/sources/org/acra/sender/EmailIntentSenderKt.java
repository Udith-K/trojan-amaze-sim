package org.acra.sender;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: EmailIntentSender.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class EmailIntentSenderKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final List queryDefaultActivities(PackageManager packageManager, Intent intent) {
        List<ResolveInfo> listQueryIntentActivities;
        if (Build.VERSION.SDK_INT >= 33) {
            listQueryIntentActivities = packageManager.queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(65536L));
        } else {
            listQueryIntentActivities = packageManager.queryIntentActivities(intent, PKIFailureInfo.notAuthorized);
        }
        Intrinsics.checkNotNull(listQueryIntentActivities);
        return listQueryIntentActivities;
    }
}
