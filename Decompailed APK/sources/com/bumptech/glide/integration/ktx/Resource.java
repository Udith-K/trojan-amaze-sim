package com.bumptech.glide.integration.ktx;

import ch.qos.logback.core.CoreConstants;
import com.bumptech.glide.load.DataSource;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Flows.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Resource extends GlideFlowInstant {
    private final DataSource dataSource;
    private final boolean isFirstResource;
    private final Object resource;
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
                iArr[Status.RUNNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Status.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Status.CLEARED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Resource)) {
            return false;
        }
        Resource resource = (Resource) obj;
        return this.status == resource.status && Intrinsics.areEqual(this.resource, resource.resource) && this.isFirstResource == resource.isFirstResource && this.dataSource == resource.dataSource;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public int hashCode() {
        int iHashCode = this.status.hashCode() * 31;
        Object obj = this.resource;
        int iHashCode2 = (iHashCode + (obj == null ? 0 : obj.hashCode())) * 31;
        boolean z = this.isFirstResource;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return ((iHashCode2 + r1) * 31) + this.dataSource.hashCode();
    }

    public String toString() {
        return "Resource(status=" + this.status + ", resource=" + this.resource + ", isFirstResource=" + this.isFirstResource + ", dataSource=" + this.dataSource + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    @Override // com.bumptech.glide.integration.ktx.GlideFlowInstant
    public Status getStatus() {
        return this.status;
    }

    public final Object getResource() {
        return this.resource;
    }

    public final DataSource getDataSource() {
        return this.dataSource;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Resource(Status status, Object obj, boolean z, DataSource dataSource) {
        super(null);
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        this.status = status;
        this.resource = obj;
        this.isFirstResource = z;
        this.dataSource = dataSource;
        int i = WhenMappings.$EnumSwitchMapping$0[getStatus().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return;
        }
        if (i == 4) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        throw new NoWhenBranchMatchedException();
    }

    public final Resource asFailure() {
        return new Resource(Status.FAILED, this.resource, this.isFirstResource, this.dataSource);
    }
}
