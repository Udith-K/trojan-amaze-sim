package org.fdroid.index;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JarIndexVerifier.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/fdroid/index/SigningException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "msg", "", "cause", "", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/String;)V", "e", "(Ljava/lang/Throwable;)V", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SigningException extends Exception {
    public SigningException(String str, Throwable th) {
        super(str, th);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SigningException(String msg) {
        this(msg, null);
        Intrinsics.checkNotNullParameter(msg, "msg");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SigningException(Throwable e) {
        this(null, e);
        Intrinsics.checkNotNullParameter(e, "e");
    }
}
