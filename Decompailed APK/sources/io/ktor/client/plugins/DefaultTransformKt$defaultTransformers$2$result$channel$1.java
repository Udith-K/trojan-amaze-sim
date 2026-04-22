package io.ktor.client.plugins;

import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelJVMKt;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.WriterScope;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScopeKt;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: DefaultTransform.kt */
/* JADX INFO: loaded from: classes.dex */
final class DefaultTransformKt$defaultTransformers$2$result$channel$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object $body;
    final /* synthetic */ HttpResponse $response;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultTransformKt$defaultTransformers$2$result$channel$1(Object obj, HttpResponse httpResponse, Continuation continuation) {
        super(2, continuation);
        this.$body = obj;
        this.$response = httpResponse;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DefaultTransformKt$defaultTransformers$2$result$channel$1 defaultTransformKt$defaultTransformers$2$result$channel$1 = new DefaultTransformKt$defaultTransformers$2$result$channel$1(this.$body, this.$response, continuation);
        defaultTransformKt$defaultTransformers$2$result$channel$1.L$0 = obj;
        return defaultTransformKt$defaultTransformers$2$result$channel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WriterScope writerScope, Continuation continuation) {
        return ((DefaultTransformKt$defaultTransformers$2$result$channel$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                WriterScope writerScope = (WriterScope) this.L$0;
                ByteReadChannel byteReadChannel = (ByteReadChannel) this.$body;
                ByteWriteChannel channel = writerScope.getChannel();
                this.label = 1;
                if (ByteReadChannelJVMKt.copyTo(byteReadChannel, channel, Preferences.UPDATE_INTERVAL_DISABLED, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                try {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                } catch (Throwable th) {
                    HttpResponseKt.complete(this.$response);
                    throw th;
                }
            }
            HttpResponseKt.complete(this.$response);
            return Unit.INSTANCE;
        } catch (CancellationException e) {
            CoroutineScopeKt.cancel(this.$response, e);
            throw e;
        } catch (Throwable th2) {
            CoroutineScopeKt.cancel(this.$response, "Receive failed", th2);
            throw th2;
        }
    }
}
