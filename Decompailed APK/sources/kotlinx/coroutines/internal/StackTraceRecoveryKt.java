package kotlinx.coroutines.internal;

import _COROUTINE.ArtificialStackFrames;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;

/* JADX INFO: compiled from: StackTraceRecovery.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class StackTraceRecoveryKt {
    private static final StackTraceElement ARTIFICIAL_FRAME = new ArtificialStackFrames().coroutineBoundary();
    private static final String baseContinuationImplClassName;
    private static final String stackTraceRecoveryClassName;

    public static final Throwable recoverStackTrace(Throwable th) {
        return th;
    }

    static {
        Object objM2639constructorimpl;
        Object objM2639constructorimpl2;
        try {
            Result.Companion companion = Result.Companion;
            objM2639constructorimpl = Result.m2639constructorimpl(BaseContinuationImpl.class.getCanonicalName());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            objM2639constructorimpl = Result.m2639constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2642exceptionOrNullimpl(objM2639constructorimpl) != null) {
            objM2639constructorimpl = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        baseContinuationImplClassName = (String) objM2639constructorimpl;
        try {
            objM2639constructorimpl2 = Result.m2639constructorimpl(StackTraceRecoveryKt.class.getCanonicalName());
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.Companion;
            objM2639constructorimpl2 = Result.m2639constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m2642exceptionOrNullimpl(objM2639constructorimpl2) != null) {
            objM2639constructorimpl2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
        stackTraceRecoveryClassName = (String) objM2639constructorimpl2;
    }
}
