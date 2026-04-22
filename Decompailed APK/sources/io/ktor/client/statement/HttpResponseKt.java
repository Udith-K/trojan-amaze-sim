package io.ktor.client.statement;

import io.ktor.client.request.HttpRequest;
import java.nio.charset.Charset;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: HttpResponse.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpResponseKt {

    /* JADX INFO: renamed from: io.ktor.client.statement.HttpResponseKt$bodyAsText$1, reason: invalid class name */
    /* JADX INFO: compiled from: HttpResponse.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpResponseKt.bodyAsText(null, null, this);
        }
    }

    public static final HttpRequest getRequest(HttpResponse httpResponse) {
        Intrinsics.checkNotNullParameter(httpResponse, "<this>");
        return httpResponse.getCall().getRequest();
    }

    public static final void complete(HttpResponse httpResponse) {
        Intrinsics.checkNotNullParameter(httpResponse, "<this>");
        CoroutineContext.Element element = httpResponse.getCoroutineContext().get(Job.Key);
        Intrinsics.checkNotNull(element);
        ((CompletableJob) element).complete();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object bodyAsText(io.ktor.client.statement.HttpResponse r5, java.nio.charset.Charset r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.client.statement.HttpResponseKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.client.statement.HttpResponseKt$bodyAsText$1 r0 = (io.ktor.client.statement.HttpResponseKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.statement.HttpResponseKt$bodyAsText$1 r0 = new io.ktor.client.statement.HttpResponseKt$bodyAsText$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r5 = r0.L$0
            java.nio.charset.CharsetDecoder r5 = (java.nio.charset.CharsetDecoder) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L66
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r7)
            java.nio.charset.Charset r7 = io.ktor.http.HttpMessagePropertiesKt.charset(r5)
            if (r7 != 0) goto L3f
            goto L40
        L3f:
            r6 = r7
        L40:
            java.nio.charset.CharsetDecoder r6 = r6.newDecoder()
            io.ktor.client.call.HttpClientCall r5 = r5.getCall()
            java.lang.Class<io.ktor.utils.io.core.ByteReadPacket> r7 = io.ktor.utils.io.core.ByteReadPacket.class
            kotlin.reflect.KType r2 = kotlin.jvm.internal.Reflection.typeOf(r7)
            java.lang.reflect.Type r4 = kotlin.reflect.TypesJVMKt.getJavaType(r2)
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            io.ktor.util.reflect.TypeInfo r7 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r4, r7, r2)
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = r5.bodyNullable(r7, r0)
            if (r7 != r1) goto L65
            return r1
        L65:
            r5 = r6
        L66:
            if (r7 == 0) goto L77
            io.ktor.utils.io.core.ByteReadPacket r7 = (io.ktor.utils.io.core.ByteReadPacket) r7
            java.lang.String r6 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r6 = 2
            r0 = 0
            r1 = 0
            java.lang.String r5 = io.ktor.utils.io.charsets.EncodingKt.decode$default(r5, r7, r1, r6, r0)
            return r5
        L77:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "null cannot be cast to non-null type io.ktor.utils.io.core.ByteReadPacket"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpResponseKt.bodyAsText(io.ktor.client.statement.HttpResponse, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object bodyAsText$default(HttpResponse httpResponse, Charset charset, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return bodyAsText(httpResponse, charset, continuation);
    }
}
