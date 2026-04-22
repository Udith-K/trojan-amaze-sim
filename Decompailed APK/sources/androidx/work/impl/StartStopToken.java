package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StartStopToken.kt */
/* JADX INFO: loaded from: classes.dex */
public final class StartStopToken {
    private final WorkGenerationalId id;

    public StartStopToken(WorkGenerationalId id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }

    public final WorkGenerationalId getId() {
        return this.id;
    }
}
