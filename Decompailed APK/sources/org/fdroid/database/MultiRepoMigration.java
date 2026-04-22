package org.fdroid.database;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import androidx.room.migration.AutoMigrationSpec;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;

/* JADX INFO: compiled from: Migrations.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u001c\u0010\u000b\u001a\u00020\u0007*\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/fdroid/database/MultiRepoMigration;", "Landroidx/room/migration/AutoMigrationSpec;", "<init>", "()V", "log", "Lmu/KLogger;", "onPostMigrate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "migrateWeights", "updateRepoWeight", "repo", "Lorg/fdroid/database/MultiRepoMigration$Repo;", "newWeight", "", "getRepo", "c", "Landroid/database/Cursor;", "Repo", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MultiRepoMigration implements AutoMigrationSpec {
    private final KLogger log = KotlinLogging.INSTANCE.logger(new Function0() { // from class: org.fdroid.database.MultiRepoMigration$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Unit.INSTANCE;
        }
    });

    @Override // androidx.room.migration.AutoMigrationSpec
    public void onPostMigrate(SupportSQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(db, "db");
        db.beginTransaction();
        try {
            migrateWeights(db);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private final void migrateWeights(SupportSQLiteDatabase db) {
        ArrayList<Repo> arrayList = new ArrayList();
        HashMap map = new HashMap();
        Cursor cursorQuery = db.query("\n            SELECT repoId, address, certificate, weight FROM CoreRepository\n            JOIN RepositoryPreferences USING (repoId)\n            ORDER BY weight DESC");
        while (cursorQuery.moveToNext()) {
            try {
                final Repo repo = getRepo(cursorQuery);
                this.log.error(new Function0() { // from class: org.fdroid.database.MultiRepoMigration$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return repo.toString();
                    }
                });
                if (repo.isArchive() && repo.getCertificate() != null) {
                    if (map.containsKey(repo.getCertificate())) {
                        this.log.error(new Function0() { // from class: org.fdroid.database.MultiRepoMigration$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MultiRepoMigration.migrateWeights$lambda$3$lambda$2(repo);
                            }
                        });
                        arrayList.add(repo);
                    } else {
                        map.put(repo.getCertificate(), repo);
                    }
                } else {
                    arrayList.add(repo);
                }
            } finally {
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(cursorQuery, null);
        int i = 1000000000;
        for (Repo repo2 : arrayList) {
            Repo repo3 = (Repo) map.get(repo2.getCertificate());
            if (repo3 == null) {
                updateRepoWeight(db, repo2, i);
            } else {
                updateRepoWeight(db, repo2, i);
                updateRepoWeight(db, repo3, i - 1);
                TypeIntrinsics.asMutableMap(map).remove(repo2.getCertificate());
            }
            i -= 2;
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            updateRepoWeight(db, (Repo) ((Map.Entry) it.next()).getValue(), i);
            i--;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object migrateWeights$lambda$3$lambda$2(Repo repo) {
        return "More than two repos with certificate of " + repo.getAddress();
    }

    private final void updateRepoWeight(SupportSQLiteDatabase supportSQLiteDatabase, Repo repo, int i) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("weight", Integer.valueOf(i));
        Unit unit = Unit.INSTANCE;
        if (supportSQLiteDatabase.update(RepositoryPreferences.TABLE, 3, contentValues, "repoId = ?", new Long[]{Long.valueOf(repo.getRepoId())}) <= 1) {
            return;
        }
        throw new IllegalStateException(("repo " + repo.getAddress() + " had more than one preference").toString());
    }

    private final Repo getRepo(Cursor c) {
        long j = c.getLong(0);
        String string = c.getString(1);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return new Repo(j, string, c.getString(2), c.getInt(3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: Migrations.kt */
    static final class Repo {
        private final String address;
        private final String certificate;
        private final long repoId;
        private final int weight;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Repo)) {
                return false;
            }
            Repo repo = (Repo) obj;
            return this.repoId == repo.repoId && Intrinsics.areEqual(this.address, repo.address) && Intrinsics.areEqual(this.certificate, repo.certificate) && this.weight == repo.weight;
        }

        public int hashCode() {
            int iM = ((LongObjectMap$$ExternalSyntheticBackport0.m(this.repoId) * 31) + this.address.hashCode()) * 31;
            String str = this.certificate;
            return ((iM + (str == null ? 0 : str.hashCode())) * 31) + this.weight;
        }

        public String toString() {
            return "Repo(repoId=" + this.repoId + ", address=" + this.address + ", certificate=" + this.certificate + ", weight=" + this.weight + ")";
        }

        public Repo(long j, String address, String str, int i) {
            Intrinsics.checkNotNullParameter(address, "address");
            this.repoId = j;
            this.address = address;
            this.certificate = str;
            this.weight = i;
        }

        public final long getRepoId() {
            return this.repoId;
        }

        public final String getAddress() {
            return this.address;
        }

        public final String getCertificate() {
            return this.certificate;
        }

        public final boolean isArchive() {
            return StringsKt.endsWith$default(StringsKt.trimEnd(this.address, '/'), "/archive", false, 2, (Object) null);
        }
    }
}
