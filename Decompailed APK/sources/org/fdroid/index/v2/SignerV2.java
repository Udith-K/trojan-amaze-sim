package org.fdroid.index.v2;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: PackageV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB3\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J#\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\nHÖ\u0001J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001J%\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006#"}, d2 = {"Lorg/fdroid/index/v2/SignerV2;", "", "sha256", "", "", "hasMultipleSigners", "", "<init>", "(Ljava/util/List;Z)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;ZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSha256", "()Ljava/util/List;", "getHasMultipleSigners", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class SignerV2 {
    private final boolean hasMultipleSigners;
    private final List<String> sha256;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer[] $childSerializers = {new ArrayListSerializer(StringSerializer.INSTANCE), null};

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SignerV2 copy$default(SignerV2 signerV2, List list, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            list = signerV2.sha256;
        }
        if ((i & 2) != 0) {
            z = signerV2.hasMultipleSigners;
        }
        return signerV2.copy(list, z);
    }

    public final List<String> component1() {
        return this.sha256;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getHasMultipleSigners() {
        return this.hasMultipleSigners;
    }

    public final SignerV2 copy(List<String> sha256, boolean hasMultipleSigners) {
        Intrinsics.checkNotNullParameter(sha256, "sha256");
        return new SignerV2(sha256, hasMultipleSigners);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SignerV2)) {
            return false;
        }
        SignerV2 signerV2 = (SignerV2) other;
        return Intrinsics.areEqual(this.sha256, signerV2.sha256) && this.hasMultipleSigners == signerV2.hasMultipleSigners;
    }

    public int hashCode() {
        return (this.sha256.hashCode() * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.hasMultipleSigners);
    }

    public String toString() {
        return "SignerV2(sha256=" + this.sha256 + ", hasMultipleSigners=" + this.hasMultipleSigners + ")";
    }

    /* JADX INFO: compiled from: PackageV2.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v2/SignerV2$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v2/SignerV2;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return SignerV2$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ SignerV2(int i, List list, boolean z, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, SignerV2$$serializer.INSTANCE.getDescriptor());
        }
        this.sha256 = list;
        if ((i & 2) == 0) {
            this.hasMultipleSigners = false;
        } else {
            this.hasMultipleSigners = z;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(SignerV2 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, $childSerializers[0], self.sha256);
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.hasMultipleSigners) {
            output.encodeBooleanElement(serialDesc, 1, self.hasMultipleSigners);
        }
    }

    public SignerV2(List<String> sha256, boolean z) {
        Intrinsics.checkNotNullParameter(sha256, "sha256");
        this.sha256 = sha256;
        this.hasMultipleSigners = z;
    }

    public /* synthetic */ SignerV2(List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? false : z);
    }

    public final List<String> getSha256() {
        return this.sha256;
    }

    public final boolean getHasMultipleSigners() {
        return this.hasMultipleSigners;
    }
}
