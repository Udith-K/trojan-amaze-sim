package org.fdroid.index.v1;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: IndexV1.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 /2\u00020\u0001:\u0002./BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u001a\b\u0002\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\n¢\u0006\u0004\b\r\u0010\u000eB[\b\u0010\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u001a\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0007\u0018\u00010\n\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\r\u0010\u0013J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u001b\u0010\u001f\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\nHÆ\u0003JI\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u001a\b\u0002\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\nHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0010HÖ\u0001J\t\u0010%\u001a\u00020\u000bHÖ\u0001J%\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R#\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u00060"}, d2 = {"Lorg/fdroid/index/v1/IndexV1;", "", "repo", "Lorg/fdroid/index/v1/RepoV1;", "requests", "Lorg/fdroid/index/v1/Requests;", "apps", "", "Lorg/fdroid/index/v1/AppV1;", "packages", "", "", "Lorg/fdroid/index/v1/PackageV1;", "<init>", "(Lorg/fdroid/index/v1/RepoV1;Lorg/fdroid/index/v1/Requests;Ljava/util/List;Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILorg/fdroid/index/v1/RepoV1;Lorg/fdroid/index/v1/Requests;Ljava/util/List;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getRepo", "()Lorg/fdroid/index/v1/RepoV1;", "getRequests", "()Lorg/fdroid/index/v1/Requests;", "getApps", "()Ljava/util/List;", "getPackages", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class IndexV1 {
    private final List<AppV1> apps;
    private final Map<String, List<PackageV1>> packages;
    private final RepoV1 repo;
    private final Requests requests;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer[] $childSerializers = {null, null, new ArrayListSerializer(AppV1$$serializer.INSTANCE), new LinkedHashMapSerializer(StringSerializer.INSTANCE, new ArrayListSerializer(PackageV1$$serializer.INSTANCE))};

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IndexV1 copy$default(IndexV1 indexV1, RepoV1 repoV1, Requests requests, List list, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            repoV1 = indexV1.repo;
        }
        if ((i & 2) != 0) {
            requests = indexV1.requests;
        }
        if ((i & 4) != 0) {
            list = indexV1.apps;
        }
        if ((i & 8) != 0) {
            map = indexV1.packages;
        }
        return indexV1.copy(repoV1, requests, list, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final RepoV1 getRepo() {
        return this.repo;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Requests getRequests() {
        return this.requests;
    }

    public final List<AppV1> component3() {
        return this.apps;
    }

    public final Map<String, List<PackageV1>> component4() {
        return this.packages;
    }

    public final IndexV1 copy(RepoV1 repo, Requests requests, List<AppV1> apps, Map<String, ? extends List<PackageV1>> packages) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(requests, "requests");
        Intrinsics.checkNotNullParameter(apps, "apps");
        Intrinsics.checkNotNullParameter(packages, "packages");
        return new IndexV1(repo, requests, apps, packages);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IndexV1)) {
            return false;
        }
        IndexV1 indexV1 = (IndexV1) other;
        return Intrinsics.areEqual(this.repo, indexV1.repo) && Intrinsics.areEqual(this.requests, indexV1.requests) && Intrinsics.areEqual(this.apps, indexV1.apps) && Intrinsics.areEqual(this.packages, indexV1.packages);
    }

    public int hashCode() {
        return (((((this.repo.hashCode() * 31) + this.requests.hashCode()) * 31) + this.apps.hashCode()) * 31) + this.packages.hashCode();
    }

    public String toString() {
        return "IndexV1(repo=" + this.repo + ", requests=" + this.requests + ", apps=" + this.apps + ", packages=" + this.packages + ")";
    }

    /* JADX INFO: compiled from: IndexV1.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v1/IndexV1$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v1/IndexV1;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return IndexV1$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ IndexV1(int i, RepoV1 repoV1, Requests requests, List list, Map map, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, IndexV1$$serializer.INSTANCE.getDescriptor());
        }
        this.repo = repoV1;
        if ((i & 2) == 0) {
            this.requests = new Requests(CollectionsKt.emptyList(), CollectionsKt.emptyList());
        } else {
            this.requests = requests;
        }
        if ((i & 4) == 0) {
            this.apps = CollectionsKt.emptyList();
        } else {
            this.apps = list;
        }
        if ((i & 8) == 0) {
            this.packages = MapsKt.emptyMap();
        } else {
            this.packages = map;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(IndexV1 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, RepoV1$$serializer.INSTANCE, self.repo);
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.requests, new Requests(CollectionsKt.emptyList(), CollectionsKt.emptyList()))) {
            output.encodeSerializableElement(serialDesc, 1, Requests$$serializer.INSTANCE, self.requests);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.apps, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 2, kSerializerArr[2], self.apps);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 3) && Intrinsics.areEqual(self.packages, MapsKt.emptyMap())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 3, kSerializerArr[3], self.packages);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public IndexV1(RepoV1 repo, Requests requests, List<AppV1> apps, Map<String, ? extends List<PackageV1>> packages) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(requests, "requests");
        Intrinsics.checkNotNullParameter(apps, "apps");
        Intrinsics.checkNotNullParameter(packages, "packages");
        this.repo = repo;
        this.requests = requests;
        this.apps = apps;
        this.packages = packages;
    }

    public final RepoV1 getRepo() {
        return this.repo;
    }

    public /* synthetic */ IndexV1(RepoV1 repoV1, Requests requests, List list, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(repoV1, (i & 2) != 0 ? new Requests(CollectionsKt.emptyList(), CollectionsKt.emptyList()) : requests, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Requests getRequests() {
        return this.requests;
    }

    public final List<AppV1> getApps() {
        return this.apps;
    }

    public final Map<String, List<PackageV1>> getPackages() {
        return this.packages;
    }
}
