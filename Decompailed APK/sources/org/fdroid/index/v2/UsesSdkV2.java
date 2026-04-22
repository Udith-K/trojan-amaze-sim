package org.fdroid.index.v2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: PackageV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0005\u0010\nJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006!"}, d2 = {"Lorg/fdroid/index/v2/UsesSdkV2;", "", "minSdkVersion", "", "targetSdkVersion", "<init>", "(II)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMinSdkVersion", "()I", "getTargetSdkVersion", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class UsesSdkV2 {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int minSdkVersion;
    private final int targetSdkVersion;

    public static /* synthetic */ UsesSdkV2 copy$default(UsesSdkV2 usesSdkV2, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = usesSdkV2.minSdkVersion;
        }
        if ((i3 & 2) != 0) {
            i2 = usesSdkV2.targetSdkVersion;
        }
        return usesSdkV2.copy(i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getMinSdkVersion() {
        return this.minSdkVersion;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getTargetSdkVersion() {
        return this.targetSdkVersion;
    }

    public final UsesSdkV2 copy(int minSdkVersion, int targetSdkVersion) {
        return new UsesSdkV2(minSdkVersion, targetSdkVersion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UsesSdkV2)) {
            return false;
        }
        UsesSdkV2 usesSdkV2 = (UsesSdkV2) other;
        return this.minSdkVersion == usesSdkV2.minSdkVersion && this.targetSdkVersion == usesSdkV2.targetSdkVersion;
    }

    public int hashCode() {
        return (this.minSdkVersion * 31) + this.targetSdkVersion;
    }

    public String toString() {
        return "UsesSdkV2(minSdkVersion=" + this.minSdkVersion + ", targetSdkVersion=" + this.targetSdkVersion + ")";
    }

    /* JADX INFO: compiled from: PackageV2.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v2/UsesSdkV2$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v2/UsesSdkV2;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return UsesSdkV2$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ UsesSdkV2(int i, int i2, int i3, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, UsesSdkV2$$serializer.INSTANCE.getDescriptor());
        }
        this.minSdkVersion = i2;
        this.targetSdkVersion = i3;
    }

    public static final /* synthetic */ void write$Self$index_release(UsesSdkV2 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeIntElement(serialDesc, 0, self.minSdkVersion);
        output.encodeIntElement(serialDesc, 1, self.targetSdkVersion);
    }

    public UsesSdkV2(int i, int i2) {
        this.minSdkVersion = i;
        this.targetSdkVersion = i2;
    }

    public final int getMinSdkVersion() {
        return this.minSdkVersion;
    }

    public final int getTargetSdkVersion() {
        return this.targetSdkVersion;
    }
}
