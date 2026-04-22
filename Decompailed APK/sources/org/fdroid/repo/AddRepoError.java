package org.fdroid.repo;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RepoAdder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0018B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u000e\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006HÆ\u0003J%\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lorg/fdroid/repo/AddRepoError;", "Lorg/fdroid/repo/AddRepoState;", "errorType", "Lorg/fdroid/repo/AddRepoError$ErrorType;", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "<init>", "(Lorg/fdroid/repo/AddRepoError$ErrorType;Ljava/lang/Exception;)V", "getErrorType", "()Lorg/fdroid/repo/AddRepoError$ErrorType;", "getException", "()Ljava/lang/Exception;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ErrorType", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AddRepoError extends AddRepoState {
    private final ErrorType errorType;
    private final Exception exception;

    public static /* synthetic */ AddRepoError copy$default(AddRepoError addRepoError, ErrorType errorType, Exception exc, int i, Object obj) {
        if ((i & 1) != 0) {
            errorType = addRepoError.errorType;
        }
        if ((i & 2) != 0) {
            exc = addRepoError.exception;
        }
        return addRepoError.copy(errorType, exc);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ErrorType getErrorType() {
        return this.errorType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Exception getException() {
        return this.exception;
    }

    public final AddRepoError copy(ErrorType errorType, Exception exception) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        return new AddRepoError(errorType, exception);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddRepoError)) {
            return false;
        }
        AddRepoError addRepoError = (AddRepoError) other;
        return this.errorType == addRepoError.errorType && Intrinsics.areEqual(this.exception, addRepoError.exception);
    }

    public int hashCode() {
        int iHashCode = this.errorType.hashCode() * 31;
        Exception exc = this.exception;
        return iHashCode + (exc == null ? 0 : exc.hashCode());
    }

    public String toString() {
        return "AddRepoError(errorType=" + this.errorType + ", exception=" + this.exception + ")";
    }

    public /* synthetic */ AddRepoError(ErrorType errorType, Exception exc, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(errorType, (i & 2) != 0 ? null : exc);
    }

    public final ErrorType getErrorType() {
        return this.errorType;
    }

    public final Exception getException() {
        return this.exception;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: RepoAdder.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/fdroid/repo/AddRepoError$ErrorType;", "", "<init>", "(Ljava/lang/String;I)V", "UNKNOWN_SOURCES_DISALLOWED", "INVALID_FINGERPRINT", "IS_ARCHIVE_REPO", "INVALID_INDEX", "IO_ERROR", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class ErrorType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ErrorType[] $VALUES;
        public static final ErrorType UNKNOWN_SOURCES_DISALLOWED = new ErrorType("UNKNOWN_SOURCES_DISALLOWED", 0);
        public static final ErrorType INVALID_FINGERPRINT = new ErrorType("INVALID_FINGERPRINT", 1);
        public static final ErrorType IS_ARCHIVE_REPO = new ErrorType("IS_ARCHIVE_REPO", 2);
        public static final ErrorType INVALID_INDEX = new ErrorType("INVALID_INDEX", 3);
        public static final ErrorType IO_ERROR = new ErrorType("IO_ERROR", 4);

        private static final /* synthetic */ ErrorType[] $values() {
            return new ErrorType[]{UNKNOWN_SOURCES_DISALLOWED, INVALID_FINGERPRINT, IS_ARCHIVE_REPO, INVALID_INDEX, IO_ERROR};
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        private ErrorType(String str, int i) {
        }

        static {
            ErrorType[] errorTypeArr$values = $values();
            $VALUES = errorTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(errorTypeArr$values);
        }

        public static ErrorType valueOf(String str) {
            return (ErrorType) Enum.valueOf(ErrorType.class, str);
        }

        public static ErrorType[] values() {
            return (ErrorType[]) $VALUES.clone();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddRepoError(ErrorType errorType, Exception exc) {
        super(null);
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        this.errorType = errorType;
        this.exception = exc;
    }
}
