package com.bumptech.glide.integration.compose;

import androidx.compose.ui.unit.Constraints;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.integration.ktx.FlowsKt;
import com.bumptech.glide.integration.ktx.Size;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Sizes.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SizesKt {
    public static final Size overrideSize(RequestBuilder requestBuilder) {
        Intrinsics.checkNotNullParameter(requestBuilder, "<this>");
        if (isOverrideSizeSet(requestBuilder)) {
            return new Size(requestBuilder.getOverrideWidth(), requestBuilder.getOverrideHeight());
        }
        return null;
    }

    public static final boolean isOverrideSizeSet(RequestBuilder requestBuilder) {
        Intrinsics.checkNotNullParameter(requestBuilder, "<this>");
        return FlowsKt.isValidGlideDimension(requestBuilder.getOverrideWidth()) && FlowsKt.isValidGlideDimension(requestBuilder.getOverrideHeight());
    }

    /* JADX INFO: renamed from: inferredGlideSize-BRTryo0, reason: not valid java name */
    public static final Size m2590inferredGlideSizeBRTryo0(long j) {
        int iM2389getMaxWidthimpl = Constraints.m2385getHasBoundedWidthimpl(j) ? Constraints.m2389getMaxWidthimpl(j) : Integer.MIN_VALUE;
        int iM2388getMaxHeightimpl = Constraints.m2384getHasBoundedHeightimpl(j) ? Constraints.m2388getMaxHeightimpl(j) : Integer.MIN_VALUE;
        if (FlowsKt.isValidGlideDimension(iM2389getMaxWidthimpl) && FlowsKt.isValidGlideDimension(iM2388getMaxHeightimpl)) {
            return new Size(iM2389getMaxWidthimpl, iM2388getMaxHeightimpl);
        }
        return null;
    }
}
