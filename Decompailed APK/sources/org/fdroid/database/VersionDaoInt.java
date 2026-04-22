package org.fdroid.database;

import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import ch.qos.logback.core.joran.action.Action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import org.fdroid.database.VersionDaoInt;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.index.IndexParser;
import org.fdroid.index.v2.PackageVersionV2;
import org.fdroid.index.v2.PermissionV2;
import org.fdroid.index.v2.ReflectionDiffer;

/* JADX INFO: compiled from: VersionDao.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\ba\u0018\u00002\u00020\u0001J@\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\r0\fH\u0017J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\rH\u0017J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H'J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H'J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H'JD\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0016\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\r0\fH\u0016J,\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00182\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\r0\fH\u0002J(\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001c\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00140!2\u0006\u0010\u0006\u001a\u00020\u0007H'J$\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00140!2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J\"\u0010#\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H'J\u001c\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00120\u00142\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014H\u0016J\u001c\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00120\u00142\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014H'J\u001e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J&\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H'J\u0018\u0010(\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J \u0010(\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H'J \u0010)\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H'J(\u0010)\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001fH'J\b\u0010*\u001a\u00020+H'J\b\u0010,\u001a\u00020+H'¨\u0006-"}, d2 = {"Lorg/fdroid/database/VersionDaoInt;", "Lorg/fdroid/database/VersionDao;", "insert", "", "repoId", "", "packageName", "", "packageVersions", "", "Lorg/fdroid/index/v2/PackageVersionV2;", "checkIfCompatible", "Lkotlin/Function1;", "", "versionId", "packageVersion", "isCompatible", "version", "Lorg/fdroid/database/Version;", "versionedString", "", "Lorg/fdroid/database/VersionedString;", "update", "versionsDiffMap", "Lkotlinx/serialization/json/JsonObject;", "Lorg/fdroid/index/v2/PackageManifest;", "diffVersion", "jsonObject", "diffVersionedStrings", Action.KEY_ATTRIBUTE, BonjourPeer.TYPE, "Lorg/fdroid/database/VersionedStringType;", "getAppVersions", "Landroidx/lifecycle/LiveData;", "Lorg/fdroid/database/AppVersion;", "getVersion", "getVersions", "packageNames", "getVersionsInternal", "getVersionedStrings", "deleteAppVersion", "deleteVersionedStrings", "countAppVersions", "", "countVersionedStrings", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface VersionDaoInt extends VersionDao {
    int countAppVersions();

    int countVersionedStrings();

    void deleteAppVersion(long repoId, String packageName);

    void deleteAppVersion(long repoId, String packageName, String versionId);

    void deleteVersionedStrings(long repoId, String packageName, String versionId);

    void deleteVersionedStrings(long repoId, String packageName, String versionId, VersionedStringType type);

    @Override // org.fdroid.database.VersionDao
    LiveData getAppVersions(long repoId, String packageName);

    @Override // org.fdroid.database.VersionDao
    LiveData getAppVersions(String packageName);

    Version getVersion(long repoId, String packageName, String versionId);

    List<VersionedString> getVersionedStrings(long repoId, String packageName);

    List<VersionedString> getVersionedStrings(long repoId, String packageName, String versionId);

    List<Version> getVersions(List<String> packageNames);

    List<Version> getVersionsInternal(List<String> packageNames);

    void insert(long repoId, String packageName, String versionId, PackageVersionV2 packageVersion, boolean isCompatible);

    @Override // org.fdroid.database.VersionDao
    void insert(long repoId, String packageName, Map<String, PackageVersionV2> packageVersions, Function1 checkIfCompatible);

    void insert(List<VersionedString> versionedString);

    void insert(Version version);

    void update(long repoId, String packageName, Map<String, JsonObject> versionsDiffMap, Function1 checkIfCompatible);

    void update(Version version);

    /* JADX INFO: compiled from: VersionDao.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void insert(VersionDaoInt versionDaoInt, long j, String packageName, Map<String, PackageVersionV2> packageVersions, Function1 checkIfCompatible) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            Intrinsics.checkNotNullParameter(packageVersions, "packageVersions");
            Intrinsics.checkNotNullParameter(checkIfCompatible, "checkIfCompatible");
            for (Map.Entry<String, PackageVersionV2> entry : packageVersions.entrySet()) {
                String key = entry.getKey();
                PackageVersionV2 value = entry.getValue();
                versionDaoInt.insert(j, packageName, key, value, ((Boolean) checkIfCompatible.invoke(value)).booleanValue());
            }
        }

        public static void insert(VersionDaoInt versionDaoInt, long j, String packageName, String versionId, PackageVersionV2 packageVersion, boolean z) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            Intrinsics.checkNotNullParameter(versionId, "versionId");
            Intrinsics.checkNotNullParameter(packageVersion, "packageVersion");
            Version version = VersionKt.toVersion(packageVersion, j, packageName, versionId, z);
            versionDaoInt.insert(version);
            versionDaoInt.insert(VersionKt.getVersionedStrings(packageVersion.getManifest(), version));
        }

        public static void update(VersionDaoInt versionDaoInt, long j, String packageName, Map<String, JsonObject> map, Function1 checkIfCompatible) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            Intrinsics.checkNotNullParameter(checkIfCompatible, "checkIfCompatible");
            if (map == null) {
                versionDaoInt.deleteAppVersion(j, packageName);
                return;
            }
            for (Map.Entry<String, JsonObject> entry : map.entrySet()) {
                String key = entry.getKey();
                JsonObject value = entry.getValue();
                if (value == null) {
                    versionDaoInt.deleteAppVersion(j, packageName, key);
                } else {
                    Version version = versionDaoInt.getVersion(j, packageName, key);
                    if (version == null) {
                        Json json = IndexParser.getJson();
                        KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(PackageVersionV2.class));
                        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                        PackageVersionV2 packageVersionV2 = (PackageVersionV2) json.decodeFromJsonElement(kSerializerSerializer, value);
                        versionDaoInt.insert(j, packageName, key, packageVersionV2, ((Boolean) checkIfCompatible.invoke(packageVersionV2.getPackageManifest())).booleanValue());
                    } else {
                        diffVersion(versionDaoInt, version, value, checkIfCompatible);
                    }
                }
            }
        }

        private static void diffVersion(VersionDaoInt versionDaoInt, Version version, JsonObject jsonObject, Function1 function1) {
            for (String str : VersionDaoKt.DENY_LIST) {
                if (jsonObject.containsKey((Object) str)) {
                    throw new SerializationException(str);
                }
            }
            Version version2 = (Version) ReflectionDiffer.INSTANCE.applyDiff(version, jsonObject);
            versionDaoInt.update(version2.copy((1023 & 1) != 0 ? version2.repoId : 0L, (1023 & 2) != 0 ? version2.packageName : null, (1023 & 4) != 0 ? version2.versionId : null, (1023 & 8) != 0 ? version2.added : 0L, (1023 & 16) != 0 ? version2.file : null, (1023 & 32) != 0 ? version2.src : null, (1023 & 64) != 0 ? version2.manifest : null, (1023 & 128) != 0 ? version2.releaseChannels : null, (1023 & 256) != 0 ? version2.antiFeatures : null, (1023 & 512) != 0 ? version2.whatsNew : null, (1023 & 1024) != 0 ? version2.isCompatible : ((Boolean) function1.invoke(version2.getPackageManifest())).booleanValue()));
            JsonElement jsonElement = (JsonElement) jsonObject.get("manifest");
            if (jsonElement instanceof JsonNull) {
                versionDaoInt.deleteVersionedStrings(version.getRepoId(), version.getPackageName(), version.getVersionId());
            } else if (jsonElement instanceof JsonObject) {
                JsonObject jsonObject2 = (JsonObject) jsonElement;
                diffVersionedStrings(versionDaoInt, version, jsonObject2, "usesPermission", VersionedStringType.PERMISSION);
                diffVersionedStrings(versionDaoInt, version, jsonObject2, "usesPermissionSdk23", VersionedStringType.PERMISSION_SDK_23);
            }
        }

        private static void diffVersionedStrings(final VersionDaoInt versionDaoInt, final Version version, JsonObject jsonObject, String str, final VersionedStringType versionedStringType) {
            DbDiffUtils.INSTANCE.diffAndUpdateListTable(jsonObject, str, new Function1() { // from class: org.fdroid.database.VersionDaoInt$DefaultImpls$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VersionDaoInt.DefaultImpls.diffVersionedStrings$lambda$3(version, versionedStringType, (JsonArray) obj);
                }
            }, new Function0() { // from class: org.fdroid.database.VersionDaoInt$DefaultImpls$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return VersionDaoInt.DefaultImpls.diffVersionedStrings$lambda$4(versionDaoInt, version, versionedStringType);
                }
            }, new Function1() { // from class: org.fdroid.database.VersionDaoInt$DefaultImpls$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VersionDaoInt.DefaultImpls.diffVersionedStrings$lambda$5(versionDaoInt, (List) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List diffVersionedStrings$lambda$3(Version version, VersionedStringType versionedStringType, JsonArray permissionArray) {
            Intrinsics.checkNotNullParameter(permissionArray, "permissionArray");
            Json json = IndexParser.getJson();
            KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(List.class, KTypeProjection.Companion.invariant(Reflection.typeOf(PermissionV2.class))));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            return VersionKt.toVersionedString((List) json.decodeFromJsonElement(kSerializerSerializer, permissionArray), version, versionedStringType);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit diffVersionedStrings$lambda$4(VersionDaoInt versionDaoInt, Version version, VersionedStringType versionedStringType) {
            versionDaoInt.deleteVersionedStrings(version.getRepoId(), version.getPackageName(), version.getVersionId(), versionedStringType);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit diffVersionedStrings$lambda$5(VersionDaoInt versionDaoInt, List versionedStrings) {
            Intrinsics.checkNotNullParameter(versionedStrings, "versionedStrings");
            versionDaoInt.insert((List<VersionedString>) versionedStrings);
            return Unit.INSTANCE;
        }

        public static List<Version> getVersions(VersionDaoInt versionDaoInt, List<String> packageNames) {
            Intrinsics.checkNotNullParameter(packageNames, "packageNames");
            if (packageNames.size() <= 999) {
                return versionDaoInt.getVersionsInternal(packageNames);
            }
            List listChunked = CollectionsKt.chunked(packageNames, RoomDatabase.MAX_BIND_PARAMETER_CNT);
            ArrayList arrayList = new ArrayList();
            Iterator it = listChunked.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, versionDaoInt.getVersionsInternal((List) it.next()));
            }
            return arrayList;
        }
    }
}
