package com.bumptech.glide.integration.ktx;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Flows.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ImmediateGlideSize extends ResolvableGlideSize {
    private final Size size;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmediateGlideSize) && Intrinsics.areEqual(this.size, ((ImmediateGlideSize) obj).size);
    }

    public int hashCode() {
        return this.size.hashCode();
    }

    public String toString() {
        return "ImmediateGlideSize(size=" + this.size + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImmediateGlideSize(Size size) {
        super(null);
        Intrinsics.checkNotNullParameter(size, "size");
        this.size = size;
    }

    public final Size getSize() {
        return this.size;
    }
}
