package org.acra.log;

import android.util.Log;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidLogDelegate.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class AndroidLogDelegate implements ACRALog {
    @Override // org.acra.log.ACRALog
    public int d(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        return Log.d(tag, msg);
    }

    @Override // org.acra.log.ACRALog
    public int i(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        return Log.i(tag, msg);
    }

    @Override // org.acra.log.ACRALog
    public int w(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        return Log.w(tag, msg);
    }

    @Override // org.acra.log.ACRALog
    public int w(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tr, "tr");
        return Log.w(tag, msg, tr);
    }

    @Override // org.acra.log.ACRALog
    public int e(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        return Log.e(tag, msg);
    }

    @Override // org.acra.log.ACRALog
    public int e(String tag, String msg, Throwable tr) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tr, "tr");
        return Log.e(tag, msg, tr);
    }
}
