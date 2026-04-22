package com.bumptech.glide;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GlideIntegration.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class GlideIntegrationKt {
    public static final RequestManager requestManager(RequestBuilder requestBuilder) {
        Intrinsics.checkNotNullParameter(requestBuilder, "<this>");
        return requestBuilder.getRequestManager();
    }

    public static final void intoDirect(RequestBuilder requestBuilder, Target targetAndRequestListener) {
        Intrinsics.checkNotNullParameter(requestBuilder, "<this>");
        Intrinsics.checkNotNullParameter(targetAndRequestListener, "targetAndRequestListener");
        requestBuilder.into(targetAndRequestListener, (RequestListener) targetAndRequestListener, new Executor() { // from class: com.bumptech.glide.GlideIntegrationKt$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                runnable.run();
            }
        });
    }
}
