package org.acra.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.ErrorReporter;

/* JADX INFO: compiled from: StubCreator.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class StubCreator {
    public static final StubCreator INSTANCE = new StubCreator();

    private StubCreator() {
    }

    public final ErrorReporter createErrorReporterStub() {
        return (ErrorReporter) createStub(ErrorReporter.class, new InvocationHandler() { // from class: org.acra.util.StubCreator$$ExternalSyntheticLambda0
            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object obj, Method method, Object[] objArr) {
                return StubCreator.createErrorReporterStub$lambda$1(obj, method, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object createErrorReporterStub$lambda$1(Object obj, Method method, Object[] objArr) {
        String str = ACRA.isACRASenderServiceProcess() ? "in SenderService process" : "before ACRA#init (if you did call #init, check if your configuration is valid)";
        ACRA.log.w(ACRA.LOG_TAG, "ErrorReporter#" + method.getName() + " called " + str + ". THIS CALL WILL BE IGNORED!");
        return null;
    }

    public static final Object createStub(Class interfaceClass, InvocationHandler handler) {
        Intrinsics.checkNotNullParameter(interfaceClass, "interfaceClass");
        Intrinsics.checkNotNullParameter(handler, "handler");
        return Proxy.newProxyInstance(StubCreator.class.getClassLoader(), new Class[]{interfaceClass}, handler);
    }
}
