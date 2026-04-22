package org.fdroid.repo;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: RepoAdder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lorg/fdroid/repo/FetchResult;", "", "<init>", "()V", "IsNewRepository", "IsNewRepoAndNewMirror", "IsNewMirror", "IsExistingRepository", "IsExistingMirror", "Lorg/fdroid/repo/FetchResult$IsExistingMirror;", "Lorg/fdroid/repo/FetchResult$IsExistingRepository;", "Lorg/fdroid/repo/FetchResult$IsNewMirror;", "Lorg/fdroid/repo/FetchResult$IsNewRepoAndNewMirror;", "Lorg/fdroid/repo/FetchResult$IsNewRepository;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class FetchResult {
    public /* synthetic */ FetchResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: RepoAdder.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/fdroid/repo/FetchResult$IsNewRepository;", "Lorg/fdroid/repo/FetchResult;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class IsNewRepository extends FetchResult {
        public static final IsNewRepository INSTANCE = new IsNewRepository();

        public boolean equals(Object other) {
            return this == other || (other instanceof IsNewRepository);
        }

        public int hashCode() {
            return 769177307;
        }

        public String toString() {
            return "IsNewRepository";
        }

        private IsNewRepository() {
            super(null);
        }
    }

    private FetchResult() {
    }

    /* JADX INFO: compiled from: RepoAdder.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/fdroid/repo/FetchResult$IsNewRepoAndNewMirror;", "Lorg/fdroid/repo/FetchResult;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class IsNewRepoAndNewMirror extends FetchResult {
        public static final IsNewRepoAndNewMirror INSTANCE = new IsNewRepoAndNewMirror();

        public boolean equals(Object other) {
            return this == other || (other instanceof IsNewRepoAndNewMirror);
        }

        public int hashCode() {
            return 980373707;
        }

        public String toString() {
            return "IsNewRepoAndNewMirror";
        }

        private IsNewRepoAndNewMirror() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: RepoAdder.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b\tJ\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lorg/fdroid/repo/FetchResult$IsNewMirror;", "Lorg/fdroid/repo/FetchResult;", "existingRepoId", "", "<init>", "(J)V", "getExistingRepoId$database_release", "()J", "component1", "component1$database_release", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class IsNewMirror extends FetchResult {
        private final long existingRepoId;

        public static /* synthetic */ IsNewMirror copy$default(IsNewMirror isNewMirror, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = isNewMirror.existingRepoId;
            }
            return isNewMirror.copy(j);
        }

        /* JADX INFO: renamed from: component1$database_release, reason: from getter */
        public final long getExistingRepoId() {
            return this.existingRepoId;
        }

        public final IsNewMirror copy(long existingRepoId) {
            return new IsNewMirror(existingRepoId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof IsNewMirror) && this.existingRepoId == ((IsNewMirror) other).existingRepoId;
        }

        public int hashCode() {
            return LongObjectMap$$ExternalSyntheticBackport0.m(this.existingRepoId);
        }

        public String toString() {
            return "IsNewMirror(existingRepoId=" + this.existingRepoId + ")";
        }

        public IsNewMirror(long j) {
            super(null);
            this.existingRepoId = j;
        }

        public final long getExistingRepoId$database_release() {
            return this.existingRepoId;
        }
    }

    /* JADX INFO: compiled from: RepoAdder.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/fdroid/repo/FetchResult$IsExistingRepository;", "Lorg/fdroid/repo/FetchResult;", "existingRepoId", "", "<init>", "(J)V", "getExistingRepoId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class IsExistingRepository extends FetchResult {
        private final long existingRepoId;

        public static /* synthetic */ IsExistingRepository copy$default(IsExistingRepository isExistingRepository, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = isExistingRepository.existingRepoId;
            }
            return isExistingRepository.copy(j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getExistingRepoId() {
            return this.existingRepoId;
        }

        public final IsExistingRepository copy(long existingRepoId) {
            return new IsExistingRepository(existingRepoId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof IsExistingRepository) && this.existingRepoId == ((IsExistingRepository) other).existingRepoId;
        }

        public int hashCode() {
            return LongObjectMap$$ExternalSyntheticBackport0.m(this.existingRepoId);
        }

        public String toString() {
            return "IsExistingRepository(existingRepoId=" + this.existingRepoId + ")";
        }

        public IsExistingRepository(long j) {
            super(null);
            this.existingRepoId = j;
        }

        public final long getExistingRepoId() {
            return this.existingRepoId;
        }
    }

    /* JADX INFO: compiled from: RepoAdder.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/fdroid/repo/FetchResult$IsExistingMirror;", "Lorg/fdroid/repo/FetchResult;", "existingRepoId", "", "<init>", "(J)V", "getExistingRepoId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class IsExistingMirror extends FetchResult {
        private final long existingRepoId;

        public static /* synthetic */ IsExistingMirror copy$default(IsExistingMirror isExistingMirror, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = isExistingMirror.existingRepoId;
            }
            return isExistingMirror.copy(j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getExistingRepoId() {
            return this.existingRepoId;
        }

        public final IsExistingMirror copy(long existingRepoId) {
            return new IsExistingMirror(existingRepoId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof IsExistingMirror) && this.existingRepoId == ((IsExistingMirror) other).existingRepoId;
        }

        public int hashCode() {
            return LongObjectMap$$ExternalSyntheticBackport0.m(this.existingRepoId);
        }

        public String toString() {
            return "IsExistingMirror(existingRepoId=" + this.existingRepoId + ")";
        }

        public IsExistingMirror(long j) {
            super(null);
            this.existingRepoId = j;
        }

        public final long getExistingRepoId() {
            return this.existingRepoId;
        }
    }
}
