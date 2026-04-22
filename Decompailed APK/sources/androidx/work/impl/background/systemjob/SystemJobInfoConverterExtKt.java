package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.net.NetworkRequest;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SystemJobInfoConverterExt.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SystemJobInfoConverterExtKt {
    public static final void setRequiredNetworkRequest(JobInfo.Builder builder, NetworkRequest networkRequest) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        builder.setRequiredNetwork(networkRequest);
    }
}
