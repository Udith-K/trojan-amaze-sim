package io.reactivex.rxjava3.internal.util;

/* JADX INFO: loaded from: classes.dex */
public abstract class ExceptionHelper {
    public static final Throwable TERMINATED = new Termination();

    public static RuntimeException wrapOrThrow(Throwable th) {
        if (th instanceof Error) {
            throw ((Error) th);
        }
        if (th instanceof RuntimeException) {
            return (RuntimeException) th;
        }
        return new RuntimeException(th);
    }

    static final class Termination extends Throwable {
        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            return this;
        }

        Termination() {
            super("No further exceptions");
        }
    }

    public static String nullWarning(String str) {
        return str + " Null values are generally not allowed in 3.x operators and sources.";
    }

    public static NullPointerException createNullPointerException(String str) {
        return new NullPointerException(nullWarning(str));
    }
}
