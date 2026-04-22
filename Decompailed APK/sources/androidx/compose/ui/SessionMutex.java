package androidx.compose.ui;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: SessionMutex.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SessionMutex {
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static AtomicReference m1068constructorimpl(AtomicReference atomicReference) {
        return atomicReference;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static AtomicReference m1067constructorimpl() {
        return m1068constructorimpl(new AtomicReference(null));
    }

    /* JADX INFO: renamed from: getCurrentSession-impl, reason: not valid java name */
    public static final Object m1069getCurrentSessionimpl(AtomicReference atomicReference) {
        Session session = (Session) atomicReference.get();
        if (session != null) {
            return session.getValue();
        }
        return null;
    }

    /* JADX INFO: renamed from: withSessionCancellingPrevious-impl, reason: not valid java name */
    public static final Object m1070withSessionCancellingPreviousimpl(AtomicReference atomicReference, Function1 function1, Function2 function2, Continuation continuation) {
        return CoroutineScopeKt.coroutineScope(new SessionMutex$withSessionCancellingPrevious$2(function1, atomicReference, function2, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SessionMutex.kt */
    static final class Session {
        private final Job job;
        private final Object value;

        public Session(Job job, Object obj) {
            this.job = job;
            this.value = obj;
        }

        public final Job getJob() {
            return this.job;
        }

        public final Object getValue() {
            return this.value;
        }
    }
}
