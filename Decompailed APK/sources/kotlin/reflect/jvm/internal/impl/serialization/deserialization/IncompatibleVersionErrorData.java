package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

/* JADX INFO: compiled from: IncompatibleVersionErrorData.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class IncompatibleVersionErrorData {
    private final Object actualVersion;
    private final ClassId classId;
    private final Object compilerVersion;
    private final Object expectedVersion;
    private final String filePath;
    private final Object languageVersion;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IncompatibleVersionErrorData)) {
            return false;
        }
        IncompatibleVersionErrorData incompatibleVersionErrorData = (IncompatibleVersionErrorData) obj;
        return Intrinsics.areEqual(this.actualVersion, incompatibleVersionErrorData.actualVersion) && Intrinsics.areEqual(this.compilerVersion, incompatibleVersionErrorData.compilerVersion) && Intrinsics.areEqual(this.languageVersion, incompatibleVersionErrorData.languageVersion) && Intrinsics.areEqual(this.expectedVersion, incompatibleVersionErrorData.expectedVersion) && Intrinsics.areEqual(this.filePath, incompatibleVersionErrorData.filePath) && Intrinsics.areEqual(this.classId, incompatibleVersionErrorData.classId);
    }

    public int hashCode() {
        Object obj = this.actualVersion;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.compilerVersion;
        int iHashCode2 = (iHashCode + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Object obj3 = this.languageVersion;
        int iHashCode3 = (iHashCode2 + (obj3 == null ? 0 : obj3.hashCode())) * 31;
        Object obj4 = this.expectedVersion;
        return ((((iHashCode3 + (obj4 != null ? obj4.hashCode() : 0)) * 31) + this.filePath.hashCode()) * 31) + this.classId.hashCode();
    }

    public String toString() {
        return "IncompatibleVersionErrorData(actualVersion=" + this.actualVersion + ", compilerVersion=" + this.compilerVersion + ", languageVersion=" + this.languageVersion + ", expectedVersion=" + this.expectedVersion + ", filePath=" + this.filePath + ", classId=" + this.classId + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public IncompatibleVersionErrorData(Object obj, Object obj2, Object obj3, Object obj4, String filePath, ClassId classId) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(classId, "classId");
        this.actualVersion = obj;
        this.compilerVersion = obj2;
        this.languageVersion = obj3;
        this.expectedVersion = obj4;
        this.filePath = filePath;
        this.classId = classId;
    }
}
