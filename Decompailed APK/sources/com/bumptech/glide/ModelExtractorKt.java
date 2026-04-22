package com.bumptech.glide;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ModelExtractor.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ModelExtractorKt {
    public static final Object getInternalModel(RequestBuilder requestBuilder) {
        Intrinsics.checkNotNullParameter(requestBuilder, "<this>");
        return requestBuilder.getModel();
    }
}
