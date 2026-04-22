package org.fdroid.index.v2;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: IndexV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 (2\u00020\u0001:\u0002'(B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tB;\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u001c\u0010\u0013\u001a\u00020\u00142\u0014\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0012\u0004\u0012\u00020\u00140\u0016J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0006HÖ\u0001J%\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\b&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006)"}, d2 = {"Lorg/fdroid/index/v2/IndexV2;", "", "repo", "Lorg/fdroid/index/v2/RepoV2;", "packages", "", "", "Lorg/fdroid/index/v2/PackageV2;", "<init>", "(Lorg/fdroid/index/v2/RepoV2;Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILorg/fdroid/index/v2/RepoV2;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getRepo", "()Lorg/fdroid/index/v2/RepoV2;", "getPackages", "()Ljava/util/Map;", "walkFiles", "", "fileConsumer", "Lkotlin/Function1;", "Lorg/fdroid/index/v2/FileV2;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class IndexV2 {
    private final Map<String, PackageV2> packages;
    private final RepoV2 repo;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer[] $childSerializers = {null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, PackageV2$$serializer.INSTANCE)};

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IndexV2 copy$default(IndexV2 indexV2, RepoV2 repoV2, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            repoV2 = indexV2.repo;
        }
        if ((i & 2) != 0) {
            map = indexV2.packages;
        }
        return indexV2.copy(repoV2, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final RepoV2 getRepo() {
        return this.repo;
    }

    public final Map<String, PackageV2> component2() {
        return this.packages;
    }

    public final IndexV2 copy(RepoV2 repo, Map<String, PackageV2> packages) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(packages, "packages");
        return new IndexV2(repo, packages);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IndexV2)) {
            return false;
        }
        IndexV2 indexV2 = (IndexV2) other;
        return Intrinsics.areEqual(this.repo, indexV2.repo) && Intrinsics.areEqual(this.packages, indexV2.packages);
    }

    public int hashCode() {
        return (this.repo.hashCode() * 31) + this.packages.hashCode();
    }

    public String toString() {
        return "IndexV2(repo=" + this.repo + ", packages=" + this.packages + ")";
    }

    /* JADX INFO: compiled from: IndexV2.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v2/IndexV2$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v2/IndexV2;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return IndexV2$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ IndexV2(int i, RepoV2 repoV2, Map map, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, IndexV2$$serializer.INSTANCE.getDescriptor());
        }
        this.repo = repoV2;
        if ((i & 2) == 0) {
            this.packages = MapsKt.emptyMap();
        } else {
            this.packages = map;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(IndexV2 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, RepoV2$$serializer.INSTANCE, self.repo);
        if (!output.shouldEncodeElementDefault(serialDesc, 1) && Intrinsics.areEqual(self.packages, MapsKt.emptyMap())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.packages);
    }

    public IndexV2(RepoV2 repo, Map<String, PackageV2> packages) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(packages, "packages");
        this.repo = repo;
        this.packages = packages;
    }

    public final RepoV2 getRepo() {
        return this.repo;
    }

    public /* synthetic */ IndexV2(RepoV2 repoV2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(repoV2, (i & 2) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, PackageV2> getPackages() {
        return this.packages;
    }

    public final void walkFiles(Function1 fileConsumer) {
        Intrinsics.checkNotNullParameter(fileConsumer, "fileConsumer");
        this.repo.walkFiles(fileConsumer);
        Iterator<T> it = this.packages.values().iterator();
        while (it.hasNext()) {
            ((PackageV2) it.next()).walkFiles(fileConsumer);
        }
    }
}
