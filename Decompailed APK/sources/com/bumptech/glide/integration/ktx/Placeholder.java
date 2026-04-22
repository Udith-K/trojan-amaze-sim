package com.bumptech.glide.integration.ktx;

import android.graphics.drawable.Drawable;
import ch.qos.logback.core.CoreConstants;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Flows.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Placeholder extends GlideFlowInstant {
    private final Drawable placeholder;
    private final Status status;

    /* JADX INFO: compiled from: Flows.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Status.values().length];
            try {
                iArr[Status.SUCCEEDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Status.CLEARED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Status.RUNNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Status.FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Placeholder)) {
            return false;
        }
        Placeholder placeholder = (Placeholder) obj;
        return this.status == placeholder.status && Intrinsics.areEqual(this.placeholder, placeholder.placeholder);
    }

    public int hashCode() {
        int iHashCode = this.status.hashCode() * 31;
        Drawable drawable = this.placeholder;
        return iHashCode + (drawable == null ? 0 : drawable.hashCode());
    }

    public String toString() {
        return "Placeholder(status=" + this.status + ", placeholder=" + this.placeholder + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    @Override // com.bumptech.glide.integration.ktx.GlideFlowInstant
    public Status getStatus() {
        return this.status;
    }

    public final Drawable getPlaceholder() {
        return this.placeholder;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Placeholder(Status status, Drawable drawable) {
        super(null);
        Intrinsics.checkNotNullParameter(status, "status");
        this.status = status;
        this.placeholder = drawable;
        int i = WhenMappings.$EnumSwitchMapping$0[getStatus().ordinal()];
        if (i == 1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (i != 2 && i != 3 && i != 4) {
            throw new NoWhenBranchMatchedException();
        }
    }
}
