package androidx.work;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorkerFactory.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class WorkerFactoryKt {
    private static final String TAG;

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("WorkerFactory");
        Intrinsics.checkNotNullExpressionValue(strTagWithPrefix, "tagWithPrefix(\"WorkerFactory\")");
        TAG = strTagWithPrefix;
    }
}
