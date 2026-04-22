package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: compiled from: Repository.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 (2\u00020\u0001:\u0001(Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t\u0012\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007j\u0002`\u000b\u0012\u0018\b\u0002\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007j\u0002`\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u0017\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\u0018J\u000e\u0010\u0019\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\b\u001aJ\u0019\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\tHÆ\u0003J\u001e\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007j\u0002`\u000bHÀ\u0003¢\u0006\u0002\b\u001dJ\u001e\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007j\u0002`\u000bHÀ\u0003¢\u0006\u0002\b\u001fJk\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\t2\u0018\b\u0002\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007j\u0002`\u000b2\u0018\b\u0002\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007j\u0002`\u000bHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R$\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007j\u0002`\u000bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R$\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007j\u0002`\u000bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014¨\u0006)"}, d2 = {"Lorg/fdroid/database/ReleaseChannel;", "Lorg/fdroid/database/RepoAttribute;", "repoId", "", "id", "", "icon", "", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", "name", "Lorg/fdroid/index/v2/LocalizedTextV2;", "description", "<init>", "(JLjava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "getRepoId$database_release", "()J", "getId$database_release", "()Ljava/lang/String;", "getIcon", "()Ljava/util/Map;", "getName$database_release", "getDescription$database_release", "component1", "component1$database_release", "component2", "component2$database_release", "component3", "component4", "component4$database_release", "component5", "component5$database_release", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ReleaseChannel extends RepoAttribute {
    public static final String TABLE = "ReleaseChannel";
    private final Map<String, String> description;
    private final Map<String, FileV2> icon;
    private final String id;
    private final Map<String, String> name;
    private final long repoId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ReleaseChannel copy$default(ReleaseChannel releaseChannel, long j, String str, Map map, Map map2, Map map3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = releaseChannel.repoId;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            str = releaseChannel.id;
        }
        String str2 = str;
        if ((i & 4) != 0) {
            map = releaseChannel.icon;
        }
        Map map4 = map;
        if ((i & 8) != 0) {
            map2 = releaseChannel.name;
        }
        Map map5 = map2;
        if ((i & 16) != 0) {
            map3 = releaseChannel.description;
        }
        return releaseChannel.copy(j2, str2, map4, map5, map3);
    }

    /* JADX INFO: renamed from: component1$database_release, reason: from getter */
    public final long getRepoId() {
        return this.repoId;
    }

    /* JADX INFO: renamed from: component2$database_release, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final Map<String, FileV2> component3() {
        return this.icon;
    }

    public final Map<String, String> component4$database_release() {
        return this.name;
    }

    public final Map<String, String> component5$database_release() {
        return this.description;
    }

    public final ReleaseChannel copy(long repoId, String id, Map<String, FileV2> icon, Map<String, String> name, Map<String, String> description) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        return new ReleaseChannel(repoId, id, icon, name, description);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReleaseChannel)) {
            return false;
        }
        ReleaseChannel releaseChannel = (ReleaseChannel) other;
        return this.repoId == releaseChannel.repoId && Intrinsics.areEqual(this.id, releaseChannel.id) && Intrinsics.areEqual(this.icon, releaseChannel.icon) && Intrinsics.areEqual(this.name, releaseChannel.name) && Intrinsics.areEqual(this.description, releaseChannel.description);
    }

    public int hashCode() {
        return (((((((LongObjectMap$$ExternalSyntheticBackport0.m(this.repoId) * 31) + this.id.hashCode()) * 31) + this.icon.hashCode()) * 31) + this.name.hashCode()) * 31) + this.description.hashCode();
    }

    public String toString() {
        return "ReleaseChannel(repoId=" + this.repoId + ", id=" + this.id + ", icon=" + this.icon + ", name=" + this.name + ", description=" + this.description + ")";
    }

    public final long getRepoId$database_release() {
        return this.repoId;
    }

    public final String getId$database_release() {
        return this.id;
    }

    public /* synthetic */ ReleaseChannel(long j, String str, Map map, Map map2, Map map3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, (i & 4) != 0 ? MapsKt.emptyMap() : map, map2, (i & 16) != 0 ? MapsKt.emptyMap() : map3);
    }

    @Override // org.fdroid.database.RepoAttribute
    public Map<String, FileV2> getIcon() {
        return this.icon;
    }

    @Override // org.fdroid.database.RepoAttribute
    public Map<String, String> getName$database_release() {
        return this.name;
    }

    @Override // org.fdroid.database.RepoAttribute
    public Map<String, String> getDescription$database_release() {
        return this.description;
    }

    public ReleaseChannel(long j, String id, Map<String, FileV2> icon, Map<String, String> name, Map<String, String> description) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        this.repoId = j;
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.description = description;
    }
}
