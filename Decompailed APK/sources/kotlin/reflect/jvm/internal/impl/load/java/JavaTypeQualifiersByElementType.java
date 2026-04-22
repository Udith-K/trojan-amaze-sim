package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.EnumMap;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JavaTypeQualifiersByElementType.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class JavaTypeQualifiersByElementType {
    private final EnumMap defaultQualifiers;

    public JavaTypeQualifiersByElementType(EnumMap defaultQualifiers) {
        Intrinsics.checkNotNullParameter(defaultQualifiers, "defaultQualifiers");
        this.defaultQualifiers = defaultQualifiers;
    }

    public final EnumMap getDefaultQualifiers() {
        return this.defaultQualifiers;
    }

    public final JavaDefaultQualifiers get(AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType) {
        return (JavaDefaultQualifiers) this.defaultQualifiers.get(annotationQualifierApplicabilityType);
    }
}
