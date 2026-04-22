package org.fdroid.database;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Version.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\b\u0001\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012&\b\u0002\u0010\u0006\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007j\u0002`\b\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR/\u0010\u0006\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007j\u0002`\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/fdroid/database/HighestVersion;", "", "repoId", "", "packageName", "", "antiFeatures", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", "<init>", "(JLjava/lang/String;Ljava/util/Map;)V", "getRepoId", "()J", "getPackageName", "()Ljava/lang/String;", "getAntiFeatures", "()Ljava/util/Map;", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class HighestVersion {
    public static final String TABLE = "HighestVersion";
    private final Map<String, Map<String, String>> antiFeatures;
    private final String packageName;
    private final long repoId;

    /* JADX WARN: Multi-variable type inference failed */
    public HighestVersion(long j, String packageName, Map<String, ? extends Map<String, String>> map) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.repoId = j;
        this.packageName = packageName;
        this.antiFeatures = map;
    }

    public /* synthetic */ HighestVersion(long j, String str, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, (i & 4) != 0 ? null : map);
    }

    public final long getRepoId() {
        return this.repoId;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final Map<String, Map<String, String>> getAntiFeatures() {
        return this.antiFeatures;
    }
}
