package androidx.room;

import androidx.room.IMultiInstanceInvalidationCallback;
import java.util.Arrays;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiInstanceInvalidationClient.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MultiInstanceInvalidationClient$callback$1 extends IMultiInstanceInvalidationCallback.Stub {
    final /* synthetic */ MultiInstanceInvalidationClient this$0;

    MultiInstanceInvalidationClient$callback$1(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        this.this$0 = multiInstanceInvalidationClient;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onInvalidation$lambda$0(MultiInstanceInvalidationClient this$0, String[] tables) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(tables, "$tables");
        this$0.getInvalidationTracker().notifyObserversByTableNames((String[]) Arrays.copyOf(tables, tables.length));
    }

    @Override // androidx.room.IMultiInstanceInvalidationCallback.Stub, androidx.room.IMultiInstanceInvalidationCallback
    public void onInvalidation(final String[] tables) {
        Intrinsics.checkNotNullParameter(tables, "tables");
        Executor executor = this.this$0.getExecutor();
        final MultiInstanceInvalidationClient multiInstanceInvalidationClient = this.this$0;
        executor.execute(new Runnable() { // from class: androidx.room.MultiInstanceInvalidationClient$callback$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MultiInstanceInvalidationClient$callback$1.onInvalidation$lambda$0(multiInstanceInvalidationClient, tables);
            }
        });
    }
}
