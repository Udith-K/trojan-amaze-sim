package org.fdroid.index.v1;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: PackageV1.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/fdroid/index/v1/PermissionV1;", "", "name", "", "maxSdk", "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;)V", "getName", "()Ljava/lang/String;", "getMaxSdk", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Integer;)Lorg/fdroid/index/v1/PermissionV1;", "equals", "", "other", "hashCode", "toString", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable(with = PermissionV1Serializer.class)
public final /* data */ class PermissionV1 {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Integer maxSdk;
    private final String name;

    public static /* synthetic */ PermissionV1 copy$default(PermissionV1 permissionV1, String str, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = permissionV1.name;
        }
        if ((i & 2) != 0) {
            num = permissionV1.maxSdk;
        }
        return permissionV1.copy(str, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getMaxSdk() {
        return this.maxSdk;
    }

    public final PermissionV1 copy(String name, Integer maxSdk) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new PermissionV1(name, maxSdk);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PermissionV1)) {
            return false;
        }
        PermissionV1 permissionV1 = (PermissionV1) other;
        return Intrinsics.areEqual(this.name, permissionV1.name) && Intrinsics.areEqual(this.maxSdk, permissionV1.maxSdk);
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        Integer num = this.maxSdk;
        return iHashCode + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "PermissionV1(name=" + this.name + ", maxSdk=" + this.maxSdk + ")";
    }

    /* JADX INFO: compiled from: PackageV1.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v1/PermissionV1$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v1/PermissionV1;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return new PermissionV1Serializer();
        }
    }

    public PermissionV1(String name, Integer num) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.maxSdk = num;
    }

    public /* synthetic */ PermissionV1(String str, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : num);
    }

    public final String getName() {
        return this.name;
    }

    public final Integer getMaxSdk() {
        return this.maxSdk;
    }
}
