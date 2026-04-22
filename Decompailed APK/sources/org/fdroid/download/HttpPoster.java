package org.fdroid.download;

import io.ktor.client.plugins.ResponseException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HttpPoster.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/fdroid/download/HttpPoster;", "", "httpManager", "Lorg/fdroid/download/HttpManager;", "url", "", "<init>", "(Lorg/fdroid/download/HttpManager;Ljava/lang/String;)V", "post", "", "json", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class HttpPoster {
    private final HttpManager httpManager;
    private final String url;

    public HttpPoster(HttpManager httpManager, String url) {
        Intrinsics.checkNotNullParameter(httpManager, "httpManager");
        Intrinsics.checkNotNullParameter(url, "url");
        this.httpManager = httpManager;
        this.url = url;
    }

    /* JADX INFO: renamed from: org.fdroid.download.HttpPoster$post$1, reason: invalid class name */
    /* JADX INFO: compiled from: HttpPoster.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ String $json;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation continuation) {
            super(2, continuation);
            this.$json = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return HttpPoster.this.new AnonymousClass1(this.$json, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws IOException {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    HttpManager httpManager = HttpPoster.this.httpManager;
                    String str = HttpPoster.this.url;
                    String str2 = this.$json;
                    this.label = 1;
                    if (HttpManager.post$default(httpManager, str, str2, null, this, 4, null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            } catch (ResponseException e) {
                throw new IOException(e);
            }
        }
    }

    public final void post(String json) throws IOException {
        Intrinsics.checkNotNullParameter(json, "json");
        BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(json, null), 1, null);
    }
}
