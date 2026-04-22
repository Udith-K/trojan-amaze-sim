package org.fdroid.download;

import io.ktor.http.Url;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: HttpManager.kt */
/* JADX INFO: loaded from: classes2.dex */
final class HttpManager$getChannel$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ DownloadRequest $request;
    final /* synthetic */ Long $skipFirstBytes;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ HttpManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HttpManager$getChannel$2(HttpManager httpManager, DownloadRequest downloadRequest, Long l, Continuation continuation) {
        super(3, continuation);
        this.this$0 = httpManager;
        this.$request = downloadRequest;
        this.$skipFirstBytes = l;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Mirror mirror, Url url, Continuation continuation) {
        HttpManager$getChannel$2 httpManager$getChannel$2 = new HttpManager$getChannel$2(this.this$0, this.$request, this.$skipFirstBytes, continuation);
        httpManager$getChannel$2.L$0 = mirror;
        httpManager$getChannel$2.L$1 = url;
        return httpManager$getChannel$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0086 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008c A[Catch: all -> 0x001c, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x001c, blocks: (B:8:0x0017, B:36:0x008c, B:39:0x0092, B:40:0x0099), top: B:50:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0092 A[Catch: all -> 0x001c, TRY_ENTER, TryCatch #2 {all -> 0x001c, blocks: (B:8:0x0017, B:36:0x008c, B:39:0x0092, B:40:0x0099), top: B:50:0x0017 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) throws java.lang.Throwable {
        /*
            r14 = this;
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r0 = io.ktor.utils.io.ByteReadChannel.class
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r14.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L32
            if (r2 == r5) goto L2e
            if (r2 == r4) goto L27
            if (r2 != r3) goto L1f
            java.lang.Object r0 = r14.L$0
            io.ktor.client.statement.HttpResponse r0 = (io.ktor.client.statement.HttpResponse) r0
            kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.lang.Throwable -> L1c
            goto L8a
        L1c:
            r15 = move-exception
            goto L9e
        L1f:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L27:
            kotlin.ResultKt.throwOnFailure(r15)     // Catch: java.util.concurrent.CancellationException -> L2b
            goto L66
        L2b:
            r15 = move-exception
            goto La2
        L2e:
            kotlin.ResultKt.throwOnFailure(r15)
            goto L5b
        L32:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            r8 = r15
            org.fdroid.download.Mirror r8 = (org.fdroid.download.Mirror) r8
            java.lang.Object r15 = r14.L$1
            r9 = r15
            io.ktor.http.Url r9 = (io.ktor.http.Url) r9
            org.fdroid.download.HttpManager r6 = r14.this$0
            org.fdroid.download.DownloadRequest r7 = r14.$request
            java.lang.Long r15 = r14.$skipFirstBytes
            if (r15 == 0) goto L4c
            long r10 = r15.longValue()
            goto L4e
        L4c:
            r10 = 0
        L4e:
            r15 = 0
            r14.L$0 = r15
            r14.label = r5
            r12 = r14
            java.lang.Object r15 = org.fdroid.download.HttpManager.access$getHttpStatement(r6, r7, r8, r9, r10, r12)
            if (r15 != r1) goto L5b
            return r1
        L5b:
            io.ktor.client.statement.HttpStatement r15 = (io.ktor.client.statement.HttpStatement) r15
            r14.label = r4     // Catch: java.util.concurrent.CancellationException -> L2b
            java.lang.Object r15 = r15.executeUnsafe(r14)     // Catch: java.util.concurrent.CancellationException -> L2b
            if (r15 != r1) goto L66
            return r1
        L66:
            io.ktor.client.statement.HttpResponse r15 = (io.ktor.client.statement.HttpResponse) r15     // Catch: java.util.concurrent.CancellationException -> L2b
            io.ktor.client.call.HttpClientCall r2 = r15.getCall()     // Catch: java.lang.Throwable -> L9a
            kotlin.reflect.KType r4 = kotlin.jvm.internal.Reflection.typeOf(r0)     // Catch: java.lang.Throwable -> L9a
            java.lang.reflect.Type r5 = kotlin.reflect.TypesJVMKt.getJavaType(r4)     // Catch: java.lang.Throwable -> L9a
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)     // Catch: java.lang.Throwable -> L9a
            io.ktor.util.reflect.TypeInfo r0 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r5, r0, r4)     // Catch: java.lang.Throwable -> L9a
            r14.L$0 = r15     // Catch: java.lang.Throwable -> L9a
            r14.label = r3     // Catch: java.lang.Throwable -> L9a
            java.lang.Object r0 = r2.bodyNullable(r0, r14)     // Catch: java.lang.Throwable -> L9a
            if (r0 != r1) goto L87
            return r1
        L87:
            r13 = r0
            r0 = r15
            r15 = r13
        L8a:
            if (r15 == 0) goto L92
            io.ktor.utils.io.ByteReadChannel r15 = (io.ktor.utils.io.ByteReadChannel) r15     // Catch: java.lang.Throwable -> L1c
            io.ktor.client.statement.HttpResponseKt.complete(r0)     // Catch: java.util.concurrent.CancellationException -> L2b
            return r15
        L92:
            java.lang.NullPointerException r15 = new java.lang.NullPointerException     // Catch: java.lang.Throwable -> L1c
            java.lang.String r1 = "null cannot be cast to non-null type io.ktor.utils.io.ByteReadChannel"
            r15.<init>(r1)     // Catch: java.lang.Throwable -> L1c
            throw r15     // Catch: java.lang.Throwable -> L1c
        L9a:
            r0 = move-exception
            r13 = r0
            r0 = r15
            r15 = r13
        L9e:
            io.ktor.client.statement.HttpResponseKt.complete(r0)     // Catch: java.util.concurrent.CancellationException -> L2b
            throw r15     // Catch: java.util.concurrent.CancellationException -> L2b
        La2:
            java.lang.Throwable r15 = io.ktor.client.utils.ExceptionUtilsJvmKt.unwrapCancellationException(r15)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.download.HttpManager$getChannel$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
