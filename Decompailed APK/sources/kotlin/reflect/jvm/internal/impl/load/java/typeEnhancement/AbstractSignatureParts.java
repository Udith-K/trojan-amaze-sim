package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationQualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;

/* JADX INFO: compiled from: AbstractSignatureParts.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractSignatureParts {
    public abstract boolean forceWarning(Object obj, KotlinTypeMarker kotlinTypeMarker);

    public abstract AbstractAnnotationTypeQualifierResolver getAnnotationTypeQualifierResolver();

    public abstract Iterable getAnnotations(KotlinTypeMarker kotlinTypeMarker);

    public abstract Iterable getContainerAnnotations();

    public abstract AnnotationQualifierApplicabilityType getContainerApplicabilityType();

    public abstract JavaTypeQualifiersByElementType getContainerDefaultTypeQualifiers();

    public abstract boolean getContainerIsVarargParameter();

    protected abstract NullabilityQualifierWithMigrationStatus getDefaultNullability(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus, JavaDefaultQualifiers javaDefaultQualifiers);

    public abstract boolean getEnableImprovementsInStrictMode();

    public abstract KotlinTypeMarker getEnhancedForWarnings(KotlinTypeMarker kotlinTypeMarker);

    public boolean getForceOnlyHeadTypeConstructor() {
        return false;
    }

    public abstract FqNameUnsafe getFqNameUnsafe(KotlinTypeMarker kotlinTypeMarker);

    public abstract boolean getSkipRawTypeArguments();

    public abstract TypeSystemContext getTypeSystem();

    public abstract boolean isArrayOrPrimitiveArray(KotlinTypeMarker kotlinTypeMarker);

    public abstract boolean isCovariant();

    public abstract boolean isEqual(KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2);

    public abstract boolean isFromJava(TypeParameterMarker typeParameterMarker);

    public abstract boolean isNotNullTypeParameterCompat(KotlinTypeMarker kotlinTypeMarker);

    private final NullabilityQualifier getNullabilityQualifier(KotlinTypeMarker kotlinTypeMarker) {
        TypeSystemContext typeSystem = getTypeSystem();
        if (typeSystem.isMarkedNullable(typeSystem.lowerBoundIfFlexible(kotlinTypeMarker))) {
            return NullabilityQualifier.NULLABLE;
        }
        if (typeSystem.isMarkedNullable(typeSystem.upperBoundIfFlexible(kotlinTypeMarker))) {
            return null;
        }
        return NullabilityQualifier.NOT_NULL;
    }

    private final JavaTypeQualifiers extractQualifiers(KotlinTypeMarker kotlinTypeMarker) {
        NullabilityQualifier nullabilityQualifier;
        NullabilityQualifier nullabilityQualifier2 = getNullabilityQualifier(kotlinTypeMarker);
        MutabilityQualifier mutabilityQualifier = null;
        if (nullabilityQualifier2 == null) {
            KotlinTypeMarker enhancedForWarnings = getEnhancedForWarnings(kotlinTypeMarker);
            nullabilityQualifier = enhancedForWarnings != null ? getNullabilityQualifier(enhancedForWarnings) : null;
        } else {
            nullabilityQualifier = nullabilityQualifier2;
        }
        TypeSystemContext typeSystem = getTypeSystem();
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        if (javaToKotlinClassMap.isReadOnly(getFqNameUnsafe(typeSystem.lowerBoundIfFlexible(kotlinTypeMarker)))) {
            mutabilityQualifier = MutabilityQualifier.READ_ONLY;
        } else if (javaToKotlinClassMap.isMutable(getFqNameUnsafe(typeSystem.upperBoundIfFlexible(kotlinTypeMarker)))) {
            mutabilityQualifier = MutabilityQualifier.MUTABLE;
        }
        return new JavaTypeQualifiers(nullabilityQualifier, mutabilityQualifier, getTypeSystem().isDefinitelyNotNullType(kotlinTypeMarker) || isNotNullTypeParameterCompat(kotlinTypeMarker), nullabilityQualifier != nullabilityQualifier2);
    }

    private final JavaTypeQualifiers extractQualifiersFromAnnotations(final TypeAndDefaultQualifiers typeAndDefaultQualifiers) {
        Iterable iterableEmptyList;
        AnnotationQualifierApplicabilityType containerApplicabilityType;
        NullabilityQualifierWithMigrationStatus boundsNullability;
        KotlinTypeMarker type;
        TypeConstructorMarker typeConstructorMarkerTypeConstructor;
        if (typeAndDefaultQualifiers.getType() == null) {
            TypeSystemContext typeSystem = getTypeSystem();
            TypeParameterMarker typeParameterForArgument = typeAndDefaultQualifiers.getTypeParameterForArgument();
            if ((typeParameterForArgument != null ? typeSystem.getVariance(typeParameterForArgument) : null) == TypeVariance.IN) {
                return JavaTypeQualifiers.Companion.getNONE();
            }
        }
        boolean z = false;
        boolean z2 = typeAndDefaultQualifiers.getTypeParameterForArgument() == null;
        KotlinTypeMarker type2 = typeAndDefaultQualifiers.getType();
        if (type2 == null || (iterableEmptyList = getAnnotations(type2)) == null) {
            iterableEmptyList = CollectionsKt.emptyList();
        }
        TypeSystemContext typeSystem2 = getTypeSystem();
        KotlinTypeMarker type3 = typeAndDefaultQualifiers.getType();
        TypeParameterMarker typeParameterClassifier = (type3 == null || (typeConstructorMarkerTypeConstructor = typeSystem2.typeConstructor(type3)) == null) ? null : typeSystem2.getTypeParameterClassifier(typeConstructorMarkerTypeConstructor);
        boolean z3 = getContainerApplicabilityType() == AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS;
        if (z2) {
            if (!z3 && getEnableImprovementsInStrictMode() && (type = typeAndDefaultQualifiers.getType()) != null && isArrayOrPrimitiveArray(type)) {
                Iterable containerAnnotations = getContainerAnnotations();
                ArrayList arrayList = new ArrayList();
                for (Object obj : containerAnnotations) {
                    if (!getAnnotationTypeQualifierResolver().isTypeUseAnnotation(obj)) {
                        arrayList.add(obj);
                    }
                }
                iterableEmptyList = CollectionsKt.plus((Collection) arrayList, iterableEmptyList);
            } else {
                iterableEmptyList = CollectionsKt.plus(getContainerAnnotations(), iterableEmptyList);
            }
        }
        MutabilityQualifier mutabilityQualifierExtractMutability = getAnnotationTypeQualifierResolver().extractMutability(iterableEmptyList);
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusExtractNullability = getAnnotationTypeQualifierResolver().extractNullability(iterableEmptyList, new Function1(this, typeAndDefaultQualifiers) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$0
            private final AbstractSignatureParts arg$0;
            private final AbstractSignatureParts.TypeAndDefaultQualifiers arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = typeAndDefaultQualifiers;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj2) {
                return Boolean.valueOf(AbstractSignatureParts.extractQualifiersFromAnnotations$lambda$6(this.arg$0, this.arg$1, obj2));
            }
        });
        if (nullabilityQualifierWithMigrationStatusExtractNullability != null) {
            NullabilityQualifier qualifier = nullabilityQualifierWithMigrationStatusExtractNullability.getQualifier();
            if (nullabilityQualifierWithMigrationStatusExtractNullability.getQualifier() == NullabilityQualifier.NOT_NULL && typeParameterClassifier != null) {
                z = true;
            }
            return new JavaTypeQualifiers(qualifier, mutabilityQualifierExtractMutability, z, nullabilityQualifierWithMigrationStatusExtractNullability.isForWarningOnly());
        }
        if (z2 || z3) {
            containerApplicabilityType = getContainerApplicabilityType();
        } else {
            containerApplicabilityType = AnnotationQualifierApplicabilityType.TYPE_USE;
        }
        JavaTypeQualifiersByElementType defaultQualifiers = typeAndDefaultQualifiers.getDefaultQualifiers();
        JavaDefaultQualifiers javaDefaultQualifiers = defaultQualifiers != null ? defaultQualifiers.get(containerApplicabilityType) : null;
        NullabilityQualifierWithMigrationStatus boundsNullability2 = typeParameterClassifier != null ? getBoundsNullability(typeParameterClassifier) : null;
        NullabilityQualifierWithMigrationStatus defaultNullability = getDefaultNullability(boundsNullability2, javaDefaultQualifiers);
        boolean z4 = (boundsNullability2 != null ? boundsNullability2.getQualifier() : null) == NullabilityQualifier.NOT_NULL || !(typeParameterClassifier == null || javaDefaultQualifiers == null || !javaDefaultQualifiers.getDefinitelyNotNull());
        TypeParameterMarker typeParameterForArgument2 = typeAndDefaultQualifiers.getTypeParameterForArgument();
        if (typeParameterForArgument2 == null || (boundsNullability = getBoundsNullability(typeParameterForArgument2)) == null) {
            boundsNullability = null;
        } else if (boundsNullability.getQualifier() == NullabilityQualifier.NULLABLE) {
            boundsNullability = NullabilityQualifierWithMigrationStatus.copy$default(boundsNullability, NullabilityQualifier.FORCE_FLEXIBILITY, false, 2, null);
        }
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatusMostSpecific = mostSpecific(boundsNullability, defaultNullability);
        NullabilityQualifier qualifier2 = nullabilityQualifierWithMigrationStatusMostSpecific != null ? nullabilityQualifierWithMigrationStatusMostSpecific.getQualifier() : null;
        if (nullabilityQualifierWithMigrationStatusMostSpecific != null && nullabilityQualifierWithMigrationStatusMostSpecific.isForWarningOnly()) {
            z = true;
        }
        return new JavaTypeQualifiers(qualifier2, mutabilityQualifierExtractMutability, z4, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean extractQualifiersFromAnnotations$lambda$6(AbstractSignatureParts abstractSignatureParts, TypeAndDefaultQualifiers typeAndDefaultQualifiers, Object extractNullability) {
        Intrinsics.checkNotNullParameter(extractNullability, "$this$extractNullability");
        return abstractSignatureParts.forceWarning(extractNullability, typeAndDefaultQualifiers.getType());
    }

    private final NullabilityQualifierWithMigrationStatus mostSpecific(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus, NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus2) {
        return nullabilityQualifierWithMigrationStatus == null ? nullabilityQualifierWithMigrationStatus2 : nullabilityQualifierWithMigrationStatus2 == null ? nullabilityQualifierWithMigrationStatus : (!nullabilityQualifierWithMigrationStatus.isForWarningOnly() || nullabilityQualifierWithMigrationStatus2.isForWarningOnly()) ? (nullabilityQualifierWithMigrationStatus.isForWarningOnly() || !nullabilityQualifierWithMigrationStatus2.isForWarningOnly()) ? (nullabilityQualifierWithMigrationStatus.getQualifier().compareTo(nullabilityQualifierWithMigrationStatus2.getQualifier()) >= 0 && nullabilityQualifierWithMigrationStatus.getQualifier().compareTo(nullabilityQualifierWithMigrationStatus2.getQualifier()) > 0) ? nullabilityQualifierWithMigrationStatus : nullabilityQualifierWithMigrationStatus2 : nullabilityQualifierWithMigrationStatus : nullabilityQualifierWithMigrationStatus2;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus getBoundsNullability(kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker r6) {
        /*
            r5 = this;
            kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext r0 = r5.getTypeSystem()
            boolean r1 = r5.isFromJava(r6)
            r2 = 0
            if (r1 != 0) goto Lc
            return r2
        Lc:
            java.util.List r6 = r0.getUpperBounds(r6)
            boolean r1 = r6 instanceof java.util.Collection
            if (r1 == 0) goto L1c
            boolean r3 = r6.isEmpty()
            if (r3 == 0) goto L1c
            goto Lc1
        L1c:
            java.util.Iterator r3 = r6.iterator()
        L20:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto Lc1
            java.lang.Object r4 = r3.next()
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r4 = (kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker) r4
            boolean r4 = r0.isError(r4)
            if (r4 != 0) goto L20
            if (r1 == 0) goto L3b
            boolean r3 = r6.isEmpty()
            if (r3 == 0) goto L3b
            goto L53
        L3b:
            java.util.Iterator r3 = r6.iterator()
        L3f:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L53
            java.lang.Object r4 = r3.next()
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r4 = (kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker) r4
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r4 = r5.getNullabilityQualifier(r4)
            if (r4 == 0) goto L3f
            r1 = r6
            goto L91
        L53:
            if (r1 == 0) goto L5c
            boolean r1 = r6.isEmpty()
            if (r1 == 0) goto L5c
            goto Lc1
        L5c:
            java.util.Iterator r1 = r6.iterator()
        L60:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto Lc1
            java.lang.Object r3 = r1.next()
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r3 = (kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker) r3
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r3 = r5.getEnhancedForWarnings(r3)
            if (r3 == 0) goto L60
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r2 = r6.iterator()
        L7b:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L91
            java.lang.Object r3 = r2.next()
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r3 = (kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker) r3
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r3 = r5.getEnhancedForWarnings(r3)
            if (r3 == 0) goto L7b
            r1.add(r3)
            goto L7b
        L91:
            boolean r2 = r1 instanceof java.util.Collection
            if (r2 == 0) goto L9c
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L9c
            goto Lb5
        L9c:
            java.util.Iterator r2 = r1.iterator()
        La0:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto Lb5
            java.lang.Object r3 = r2.next()
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r3 = (kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker) r3
            boolean r3 = r0.isNullableType(r3)
            if (r3 != 0) goto La0
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r0 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
            goto Lb7
        Lb5:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r0 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NULLABLE
        Lb7:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r2 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            if (r1 == r6) goto Lbd
            r6 = 1
            goto Lbe
        Lbd:
            r6 = 0
        Lbe:
            r2.<init>(r0, r6)
        Lc1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts.getBoundsNullability(kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus");
    }

    public final Function1 computeIndexedQualifiers(KotlinTypeMarker kotlinTypeMarker, Iterable overrides, final TypeEnhancementInfo typeEnhancementInfo, boolean z) {
        int size;
        KotlinTypeMarker type;
        Intrinsics.checkNotNullParameter(kotlinTypeMarker, "<this>");
        Intrinsics.checkNotNullParameter(overrides, "overrides");
        List indexed = toIndexed(kotlinTypeMarker);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(overrides, 10));
        Iterator it = overrides.iterator();
        while (it.hasNext()) {
            arrayList.add(toIndexed((KotlinTypeMarker) it.next()));
        }
        if (getForceOnlyHeadTypeConstructor()) {
            size = 1;
            break;
        }
        if (isCovariant() && (!(overrides instanceof Collection) || !((Collection) overrides).isEmpty())) {
            Iterator it2 = overrides.iterator();
            while (it2.hasNext()) {
                if (!isEqual(kotlinTypeMarker, (KotlinTypeMarker) it2.next())) {
                    size = 1;
                    break;
                }
            }
        }
        size = indexed.size();
        final JavaTypeQualifiers[] javaTypeQualifiersArr = new JavaTypeQualifiers[size];
        int i = 0;
        while (i < size) {
            JavaTypeQualifiers javaTypeQualifiersExtractQualifiersFromAnnotations = extractQualifiersFromAnnotations((TypeAndDefaultQualifiers) indexed.get(i));
            ArrayList arrayList2 = new ArrayList();
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                TypeAndDefaultQualifiers typeAndDefaultQualifiers = (TypeAndDefaultQualifiers) CollectionsKt.getOrNull((List) it3.next(), i);
                JavaTypeQualifiers javaTypeQualifiersExtractQualifiers = (typeAndDefaultQualifiers == null || (type = typeAndDefaultQualifiers.getType()) == null) ? null : extractQualifiers(type);
                if (javaTypeQualifiersExtractQualifiers != null) {
                    arrayList2.add(javaTypeQualifiersExtractQualifiers);
                }
            }
            javaTypeQualifiersArr[i] = TypeEnhancementUtilsKt.computeQualifiersForOverride(javaTypeQualifiersExtractQualifiersFromAnnotations, arrayList2, i == 0 && isCovariant(), i == 0 && getContainerIsVarargParameter(), z);
            i++;
        }
        return new Function1(typeEnhancementInfo, javaTypeQualifiersArr) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$1
            private final TypeEnhancementInfo arg$0;
            private final JavaTypeQualifiers[] arg$1;

            {
                this.arg$0 = typeEnhancementInfo;
                this.arg$1 = javaTypeQualifiersArr;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return AbstractSignatureParts.computeIndexedQualifiers$lambda$18(this.arg$0, this.arg$1, ((Number) obj).intValue());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JavaTypeQualifiers computeIndexedQualifiers$lambda$18(TypeEnhancementInfo typeEnhancementInfo, JavaTypeQualifiers[] javaTypeQualifiersArr, int i) {
        Map map;
        JavaTypeQualifiers javaTypeQualifiers;
        return (typeEnhancementInfo == null || (map = typeEnhancementInfo.getMap()) == null || (javaTypeQualifiers = (JavaTypeQualifiers) map.get(Integer.valueOf(i))) == null) ? (i < 0 || i >= javaTypeQualifiersArr.length) ? JavaTypeQualifiers.Companion.getNONE() : javaTypeQualifiersArr[i] : javaTypeQualifiers;
    }

    private final void flattenTree(Object obj, List list, Function1 function1) {
        list.add(obj);
        Iterable iterable = (Iterable) function1.invoke(obj);
        if (iterable != null) {
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                flattenTree(it.next(), list, function1);
            }
        }
    }

    private final List flattenTree(Object obj, Function1 function1) {
        ArrayList arrayList = new ArrayList(1);
        flattenTree(obj, arrayList, function1);
        return arrayList;
    }

    private final JavaTypeQualifiersByElementType extractAndMergeDefaultQualifiers(KotlinTypeMarker kotlinTypeMarker, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType) {
        return getAnnotationTypeQualifierResolver().extractAndMergeDefaultQualifiers(javaTypeQualifiersByElementType, getAnnotations(kotlinTypeMarker));
    }

    private final List toIndexed(KotlinTypeMarker kotlinTypeMarker) {
        final TypeSystemContext typeSystem = getTypeSystem();
        return flattenTree(new TypeAndDefaultQualifiers(kotlinTypeMarker, extractAndMergeDefaultQualifiers(kotlinTypeMarker, getContainerDefaultTypeQualifiers()), null), new Function1(this, typeSystem) { // from class: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts$$Lambda$2
            private final AbstractSignatureParts arg$0;
            private final TypeSystemContext arg$1;

            {
                this.arg$0 = this;
                this.arg$1 = typeSystem;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return AbstractSignatureParts.toIndexed$lambda$23$lambda$22(this.arg$0, this.arg$1, (AbstractSignatureParts.TypeAndDefaultQualifiers) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Iterable toIndexed$lambda$23$lambda$22(AbstractSignatureParts abstractSignatureParts, TypeSystemContext typeSystemContext, TypeAndDefaultQualifiers it) {
        KotlinTypeMarker type;
        TypeConstructorMarker typeConstructorMarkerTypeConstructor;
        List parameters;
        TypeAndDefaultQualifiers typeAndDefaultQualifiers;
        KotlinTypeMarker type2;
        Intrinsics.checkNotNullParameter(it, "it");
        if ((abstractSignatureParts.getSkipRawTypeArguments() && (type2 = it.getType()) != null && typeSystemContext.isRawType(type2)) || (type = it.getType()) == null || (typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(type)) == null || (parameters = typeSystemContext.getParameters(typeConstructorMarkerTypeConstructor)) == null) {
            return null;
        }
        List arguments = typeSystemContext.getArguments(it.getType());
        Iterator it2 = parameters.iterator();
        Iterator it3 = arguments.iterator();
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(parameters, 10), CollectionsKt.collectionSizeOrDefault(arguments, 10)));
        while (it2.hasNext() && it3.hasNext()) {
            Object next = it2.next();
            TypeArgumentMarker typeArgumentMarker = (TypeArgumentMarker) it3.next();
            TypeParameterMarker typeParameterMarker = (TypeParameterMarker) next;
            if (typeSystemContext.isStarProjection(typeArgumentMarker)) {
                typeAndDefaultQualifiers = new TypeAndDefaultQualifiers(null, it.getDefaultQualifiers(), typeParameterMarker);
            } else {
                KotlinTypeMarker type3 = typeSystemContext.getType(typeArgumentMarker);
                typeAndDefaultQualifiers = new TypeAndDefaultQualifiers(type3, abstractSignatureParts.extractAndMergeDefaultQualifiers(type3, it.getDefaultQualifiers()), typeParameterMarker);
            }
            arrayList.add(typeAndDefaultQualifiers);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AbstractSignatureParts.kt */
    static final class TypeAndDefaultQualifiers {
        private final JavaTypeQualifiersByElementType defaultQualifiers;
        private final KotlinTypeMarker type;
        private final TypeParameterMarker typeParameterForArgument;

        public TypeAndDefaultQualifiers(KotlinTypeMarker kotlinTypeMarker, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType, TypeParameterMarker typeParameterMarker) {
            this.type = kotlinTypeMarker;
            this.defaultQualifiers = javaTypeQualifiersByElementType;
            this.typeParameterForArgument = typeParameterMarker;
        }

        public final KotlinTypeMarker getType() {
            return this.type;
        }

        public final JavaTypeQualifiersByElementType getDefaultQualifiers() {
            return this.defaultQualifiers;
        }

        public final TypeParameterMarker getTypeParameterForArgument() {
            return this.typeParameterForArgument;
        }
    }
}
