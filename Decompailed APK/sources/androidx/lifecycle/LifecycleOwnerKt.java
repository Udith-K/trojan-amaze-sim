package androidx.lifecycle;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LifecycleOwner.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LifecycleOwnerKt {
    public static final LifecycleCoroutineScope getLifecycleScope(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<this>");
        return LifecycleKt.getCoroutineScope(lifecycleOwner.getLifecycle());
    }
}
