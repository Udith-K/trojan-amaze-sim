package io.ktor.client.plugins;

import io.ktor.client.HttpClientConfig;
import io.ktor.client.plugins.HttpCallValidator;
import io.ktor.client.statement.HttpResponse;
import io.ktor.util.AttributeKey;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

/* JADX INFO: compiled from: DefaultResponseValidation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DefaultResponseValidationKt {
    private static final AttributeKey ValidateMark = new AttributeKey("ValidateMark");
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.DefaultResponseValidation");

    public static final void addDefaultResponseValidation(final HttpClientConfig httpClientConfig) {
        Intrinsics.checkNotNullParameter(httpClientConfig, "<this>");
        HttpCallValidatorKt.HttpResponseValidator(httpClientConfig, new Function1() { // from class: io.ktor.client.plugins.DefaultResponseValidationKt.addDefaultResponseValidation.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((HttpCallValidator.Config) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(HttpCallValidator.Config HttpResponseValidator) {
                Intrinsics.checkNotNullParameter(HttpResponseValidator, "$this$HttpResponseValidator");
                HttpResponseValidator.setExpectSuccess(httpClientConfig.getExpectSuccess());
                HttpResponseValidator.validateResponse(new C00511(null));
            }

            /* JADX INFO: renamed from: io.ktor.client.plugins.DefaultResponseValidationKt$addDefaultResponseValidation$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: DefaultResponseValidation.kt */
            static final class C00511 extends SuspendLambda implements Function2 {
                int I$0;
                /* synthetic */ Object L$0;
                Object L$1;
                int label;

                C00511(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    C00511 c00511 = new C00511(continuation);
                    c00511.L$0 = obj;
                    return c00511;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(HttpResponse httpResponse, Continuation continuation) {
                    return ((C00511) create(httpResponse, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Can't wrap try/catch for region: R(7:0|2|(1:(1:(8:6|54|7|30|34|(2:39|(1:(1:48)(1:47))(1:43))(1:38)|49|50)(2:9|10))(1:11))(2:12|(2:14|15)(2:16|(2:51|52)(2:21|(1:23)(1:24))))|25|56|26|(1:28)(6:29|30|34|(2:39|(1:(2:45|48)(0))(0))(0)|49|50)) */
                /* JADX WARN: Code restructure failed: missing block: B:32:0x00d0, code lost:
                
                    r0 = r1;
                    r3 = r5;
                    r1 = r11;
                 */
                /* JADX WARN: Removed duplicated region for block: B:39:0x00e2  */
                /* JADX WARN: Removed duplicated region for block: B:44:0x00ef  */
                /* JADX WARN: Removed duplicated region for block: B:48:0x00fb  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
                    /*
                        Method dump skipped, instruction units count: 304
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.DefaultResponseValidationKt.AnonymousClass1.C00511.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }
        });
    }
}
