package androidx.compose.runtime;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Composer.kt */
/* JADX INFO: loaded from: classes.dex */
public interface RememberManager {
    void deactivating(ComposeNodeLifecycleCallback composeNodeLifecycleCallback, int i, int i2, int i3);

    void forgetting(RememberObserver rememberObserver, int i, int i2, int i3);

    void releasing(ComposeNodeLifecycleCallback composeNodeLifecycleCallback, int i, int i2, int i3);

    void remembering(RememberObserver rememberObserver);

    void sideEffect(Function0 function0);
}
