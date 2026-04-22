package org.bouncycastle.tsp.ers;

/* JADX INFO: loaded from: classes2.dex */
public class ERSException extends Exception {
    private final Throwable cause;

    public ERSException(String str) {
        this(str, null);
    }

    public ERSException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
