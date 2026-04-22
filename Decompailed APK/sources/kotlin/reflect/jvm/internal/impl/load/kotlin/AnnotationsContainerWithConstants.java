package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader;

/* JADX INFO: compiled from: AbstractBinaryClassAnnotationLoader.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class AnnotationsContainerWithConstants extends AbstractBinaryClassAnnotationLoader.AnnotationsContainer {
    private final Map annotationParametersDefaultValues;
    private final Map memberAnnotations;
    private final Map propertyConstants;

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader.AnnotationsContainer
    public Map getMemberAnnotations() {
        return this.memberAnnotations;
    }

    public final Map getPropertyConstants() {
        return this.propertyConstants;
    }

    public final Map getAnnotationParametersDefaultValues() {
        return this.annotationParametersDefaultValues;
    }

    public AnnotationsContainerWithConstants(Map memberAnnotations, Map propertyConstants, Map annotationParametersDefaultValues) {
        Intrinsics.checkNotNullParameter(memberAnnotations, "memberAnnotations");
        Intrinsics.checkNotNullParameter(propertyConstants, "propertyConstants");
        Intrinsics.checkNotNullParameter(annotationParametersDefaultValues, "annotationParametersDefaultValues");
        this.memberAnnotations = memberAnnotations;
        this.propertyConstants = propertyConstants;
        this.annotationParametersDefaultValues = annotationParametersDefaultValues;
    }
}
