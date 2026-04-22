package org.fdroid.index.v1;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IndexV1StreamProcessor.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\t¨\u0006\n"}, d2 = {"Lorg/fdroid/index/v1/OldIndexException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "isSameTimestamp", "", "msg", "", "<init>", "(ZLjava/lang/String;)V", "()Z", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class OldIndexException extends Exception {
    private final boolean isSameTimestamp;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OldIndexException(boolean z, String msg) {
        super(msg);
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.isSameTimestamp = z;
    }

    /* JADX INFO: renamed from: isSameTimestamp, reason: from getter */
    public final boolean getIsSameTimestamp() {
        return this.isSameTimestamp;
    }
}
