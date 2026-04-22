package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: JavaDefaultQualifiers.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class JavaDefaultQualifiersKt {
    private static final List APPLICABILITY_OF_JAVAX_DEFAULTS;
    private static final List APPLICABILITY_OF_JSPECIFY_DEFAULTS;
    private static final Map BUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS;
    private static final Map JAVAX_DEFAULT_ANNOTATIONS;
    private static final Map JSPECIFY_DEFAULT_ANNOTATIONS;

    static {
        AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType = AnnotationQualifierApplicabilityType.VALUE_PARAMETER;
        List listListOf = CollectionsKt.listOf((Object[]) new AnnotationQualifierApplicabilityType[]{AnnotationQualifierApplicabilityType.FIELD, AnnotationQualifierApplicabilityType.METHOD_RETURN_TYPE, annotationQualifierApplicabilityType, AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS, AnnotationQualifierApplicabilityType.TYPE_USE});
        APPLICABILITY_OF_JSPECIFY_DEFAULTS = listListOf;
        List listListOf2 = CollectionsKt.listOf(annotationQualifierApplicabilityType);
        APPLICABILITY_OF_JAVAX_DEFAULTS = listListOf2;
        FqName jspecify_old_null_marked_annotation_fq_name = JvmAnnotationNamesKt.getJSPECIFY_OLD_NULL_MARKED_ANNOTATION_FQ_NAME();
        NullabilityQualifier nullabilityQualifier = NullabilityQualifier.NOT_NULL;
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to(jspecify_old_null_marked_annotation_fq_name, new JavaDefaultQualifiers(new NullabilityQualifierWithMigrationStatus(nullabilityQualifier, false, 2, null), listListOf, false)), TuplesKt.to(JvmAnnotationNamesKt.getJSPECIFY_NULL_MARKED_ANNOTATION_FQ_NAME(), new JavaDefaultQualifiers(new NullabilityQualifierWithMigrationStatus(nullabilityQualifier, false, 2, null), listListOf, false)), TuplesKt.to(JvmAnnotationNamesKt.getJSPECIFY_NULL_UNMARKED_ANNOTATION_FQ_NAME(), new JavaDefaultQualifiers(new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.FORCE_FLEXIBILITY, false, 2, null), listListOf, false, 4, null)));
        JSPECIFY_DEFAULT_ANNOTATIONS = mapMapOf;
        Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to(JvmAnnotationNamesKt.getJAVAX_PARAMETERS_ARE_NONNULL_BY_DEFAULT_ANNOTATION_FQ_NAME(), new JavaDefaultQualifiers(new NullabilityQualifierWithMigrationStatus(nullabilityQualifier, false, 2, null), listListOf2, false, 4, null)), TuplesKt.to(JvmAnnotationNamesKt.getJAVAX_PARAMETERS_ARE_NULLABLE_BY_DEFAULT_ANNOTATION_FQ_NAME(), new JavaDefaultQualifiers(new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null), listListOf2, false, 4, null)));
        JAVAX_DEFAULT_ANNOTATIONS = mapMapOf2;
        BUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS = MapsKt.plus(mapMapOf, mapMapOf2);
    }

    public static final Map getJSPECIFY_DEFAULT_ANNOTATIONS() {
        return JSPECIFY_DEFAULT_ANNOTATIONS;
    }

    public static final Map getBUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS() {
        return BUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS;
    }
}
