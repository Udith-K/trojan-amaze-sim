package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.IntersectionTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

/* JADX INFO: compiled from: AbstractTypeChecker.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class AbstractTypeChecker {
    public static final AbstractTypeChecker INSTANCE = new AbstractTypeChecker();
    public static boolean RUN_SLOW_ASSERTIONS;

    /* JADX INFO: compiled from: AbstractTypeChecker.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TypeVariance.values().length];
            try {
                iArr[TypeVariance.INV.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TypeVariance.OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TypeVariance.IN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[TypeCheckerState.LowerCapturedTypePolicy.values().length];
            try {
                iArr2[TypeCheckerState.LowerCapturedTypePolicy.CHECK_ONLY_LOWER.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[TypeCheckerState.LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[TypeCheckerState.LowerCapturedTypePolicy.SKIP_LOWER.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public final boolean isSubtypeOf(TypeCheckerState state, KotlinTypeMarker subType, KotlinTypeMarker superType) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        return isSubtypeOf$default(this, state, subType, superType, false, 8, null);
    }

    private AbstractTypeChecker() {
    }

    public static /* synthetic */ boolean isSubtypeOf$default(AbstractTypeChecker abstractTypeChecker, TypeCheckerState typeCheckerState, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        return abstractTypeChecker.isSubtypeOf(typeCheckerState, kotlinTypeMarker, kotlinTypeMarker2, z);
    }

    public final boolean isSubtypeOf(TypeCheckerState state, KotlinTypeMarker subType, KotlinTypeMarker superType, boolean z) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superType, "superType");
        if (subType == superType) {
            return true;
        }
        if (state.customIsSubtypeOf(subType, superType)) {
            return completeIsSubTypeOf(state, subType, superType, z);
        }
        return false;
    }

    public final boolean equalTypes(TypeCheckerState state, KotlinTypeMarker a, KotlinTypeMarker b) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        TypeSystemContext typeSystemContext = state.getTypeSystemContext();
        if (a == b) {
            return true;
        }
        AbstractTypeChecker abstractTypeChecker = INSTANCE;
        if (abstractTypeChecker.isCommonDenotableType(typeSystemContext, a) && abstractTypeChecker.isCommonDenotableType(typeSystemContext, b)) {
            KotlinTypeMarker kotlinTypeMarkerPrepareType = state.prepareType(state.refineType(a));
            KotlinTypeMarker kotlinTypeMarkerPrepareType2 = state.prepareType(state.refineType(b));
            SimpleTypeMarker simpleTypeMarkerLowerBoundIfFlexible = typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerPrepareType);
            if (!typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(kotlinTypeMarkerPrepareType), typeSystemContext.typeConstructor(kotlinTypeMarkerPrepareType2))) {
                return false;
            }
            if (typeSystemContext.argumentsCount(simpleTypeMarkerLowerBoundIfFlexible) == 0) {
                return typeSystemContext.hasFlexibleNullability(kotlinTypeMarkerPrepareType) || typeSystemContext.hasFlexibleNullability(kotlinTypeMarkerPrepareType2) || typeSystemContext.isMarkedNullable(simpleTypeMarkerLowerBoundIfFlexible) == typeSystemContext.isMarkedNullable(typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerPrepareType2));
            }
        }
        return isSubtypeOf$default(abstractTypeChecker, state, a, b, false, 8, null) && isSubtypeOf$default(abstractTypeChecker, state, b, a, false, 8, null);
    }

    private final boolean completeIsSubTypeOf(TypeCheckerState typeCheckerState, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, boolean z) {
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        KotlinTypeMarker kotlinTypeMarkerPrepareType = typeCheckerState.prepareType(typeCheckerState.refineType(kotlinTypeMarker));
        KotlinTypeMarker kotlinTypeMarkerPrepareType2 = typeCheckerState.prepareType(typeCheckerState.refineType(kotlinTypeMarker2));
        AbstractTypeChecker abstractTypeChecker = INSTANCE;
        Boolean boolCheckSubtypeForSpecialCases = abstractTypeChecker.checkSubtypeForSpecialCases(typeCheckerState, typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerPrepareType), typeSystemContext.upperBoundIfFlexible(kotlinTypeMarkerPrepareType2));
        if (boolCheckSubtypeForSpecialCases != null) {
            boolean zBooleanValue = boolCheckSubtypeForSpecialCases.booleanValue();
            typeCheckerState.addSubtypeConstraint(kotlinTypeMarkerPrepareType, kotlinTypeMarkerPrepareType2, z);
            return zBooleanValue;
        }
        Boolean boolAddSubtypeConstraint = typeCheckerState.addSubtypeConstraint(kotlinTypeMarkerPrepareType, kotlinTypeMarkerPrepareType2, z);
        return boolAddSubtypeConstraint != null ? boolAddSubtypeConstraint.booleanValue() : abstractTypeChecker.isSubtypeOfForSingleClassifierType(typeCheckerState, typeSystemContext.lowerBoundIfFlexible(kotlinTypeMarkerPrepareType), typeSystemContext.upperBoundIfFlexible(kotlinTypeMarkerPrepareType2));
    }

    private final Boolean checkSubtypeForIntegerLiteralType(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        if (!typeSystemContext.isIntegerLiteralType(simpleTypeMarker) && !typeSystemContext.isIntegerLiteralType(simpleTypeMarker2)) {
            return null;
        }
        if (checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeOrCapturedOne(typeSystemContext, simpleTypeMarker) && checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeOrCapturedOne(typeSystemContext, simpleTypeMarker2)) {
            return Boolean.TRUE;
        }
        if (typeSystemContext.isIntegerLiteralType(simpleTypeMarker)) {
            if (checkSubtypeForIntegerLiteralType$lambda$7$isTypeInIntegerLiteralType(typeSystemContext, typeCheckerState, simpleTypeMarker, simpleTypeMarker2, false)) {
                return Boolean.TRUE;
            }
        } else if (typeSystemContext.isIntegerLiteralType(simpleTypeMarker2) && (checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeInIntersectionComponents(typeSystemContext, simpleTypeMarker) || checkSubtypeForIntegerLiteralType$lambda$7$isTypeInIntegerLiteralType(typeSystemContext, typeCheckerState, simpleTypeMarker2, simpleTypeMarker, true))) {
            return Boolean.TRUE;
        }
        return null;
    }

    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isTypeInIntegerLiteralType(TypeSystemContext typeSystemContext, TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2, boolean z) {
        Collection<KotlinTypeMarker> collectionPossibleIntegerTypes = typeSystemContext.possibleIntegerTypes(simpleTypeMarker);
        if ((collectionPossibleIntegerTypes instanceof Collection) && collectionPossibleIntegerTypes.isEmpty()) {
            return false;
        }
        for (KotlinTypeMarker kotlinTypeMarker : collectionPossibleIntegerTypes) {
            if (Intrinsics.areEqual(typeSystemContext.typeConstructor(kotlinTypeMarker), typeSystemContext.typeConstructor(simpleTypeMarker2)) || (z && isSubtypeOf$default(INSTANCE, typeCheckerState, simpleTypeMarker2, kotlinTypeMarker, false, 8, null))) {
                return true;
            }
        }
        return false;
    }

    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeInIntersectionComponents(TypeSystemContext typeSystemContext, SimpleTypeMarker simpleTypeMarker) {
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(simpleTypeMarker);
        if (typeConstructorMarkerTypeConstructor instanceof IntersectionTypeConstructorMarker) {
            Collection collectionSupertypes = typeSystemContext.supertypes(typeConstructorMarkerTypeConstructor);
            if (!(collectionSupertypes instanceof Collection) || !collectionSupertypes.isEmpty()) {
                Iterator it = collectionSupertypes.iterator();
                while (it.hasNext()) {
                    SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType((KotlinTypeMarker) it.next());
                    if (simpleTypeMarkerAsSimpleType != null && typeSystemContext.isIntegerLiteralType(simpleTypeMarkerAsSimpleType)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isCapturedIntegerLiteralType(TypeSystemContext typeSystemContext, SimpleTypeMarker simpleTypeMarker) {
        if (!(simpleTypeMarker instanceof CapturedTypeMarker)) {
            return false;
        }
        TypeArgumentMarker typeArgumentMarkerProjection = typeSystemContext.projection(typeSystemContext.typeConstructor((CapturedTypeMarker) simpleTypeMarker));
        return !typeSystemContext.isStarProjection(typeArgumentMarkerProjection) && typeSystemContext.isIntegerLiteralType(typeSystemContext.upperBoundIfFlexible(typeSystemContext.getType(typeArgumentMarkerProjection)));
    }

    private static final boolean checkSubtypeForIntegerLiteralType$lambda$7$isIntegerLiteralTypeOrCapturedOne(TypeSystemContext typeSystemContext, SimpleTypeMarker simpleTypeMarker) {
        return typeSystemContext.isIntegerLiteralType(simpleTypeMarker) || checkSubtypeForIntegerLiteralType$lambda$7$isCapturedIntegerLiteralType(typeSystemContext, simpleTypeMarker);
    }

    private final boolean hasNothingSupertype(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker) {
        TypeCheckerState.SupertypesPolicy supertypesPolicy;
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(simpleTypeMarker);
        if (typeSystemContext.isClassTypeConstructor(typeConstructorMarkerTypeConstructor)) {
            return typeSystemContext.isNothingConstructor(typeConstructorMarkerTypeConstructor);
        }
        if (typeSystemContext.isNothingConstructor(typeSystemContext.typeConstructor(simpleTypeMarker))) {
            return true;
        }
        typeCheckerState.initialize();
        ArrayDeque supertypesDeque = typeCheckerState.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set supertypesSet = typeCheckerState.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(simpleTypeMarker);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleTypeMarker simpleTypeMarker2 = (SimpleTypeMarker) supertypesDeque.pop();
            Intrinsics.checkNotNull(simpleTypeMarker2);
            if (supertypesSet.add(simpleTypeMarker2)) {
                if (typeSystemContext.isClassType(simpleTypeMarker2)) {
                    supertypesPolicy = TypeCheckerState.SupertypesPolicy.None.INSTANCE;
                } else {
                    supertypesPolicy = TypeCheckerState.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                }
                if (Intrinsics.areEqual(supertypesPolicy, TypeCheckerState.SupertypesPolicy.None.INSTANCE)) {
                    supertypesPolicy = null;
                }
                if (supertypesPolicy == null) {
                    continue;
                } else {
                    TypeSystemContext typeSystemContext2 = typeCheckerState.getTypeSystemContext();
                    Iterator it = typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(simpleTypeMarker2)).iterator();
                    while (it.hasNext()) {
                        SimpleTypeMarker simpleTypeMarkerMo2785transformType = supertypesPolicy.mo2785transformType(typeCheckerState, (KotlinTypeMarker) it.next());
                        if (typeSystemContext.isNothingConstructor(typeSystemContext.typeConstructor(simpleTypeMarkerMo2785transformType))) {
                            typeCheckerState.clear();
                            return true;
                        }
                        supertypesDeque.add(simpleTypeMarkerMo2785transformType);
                    }
                }
            }
        }
        typeCheckerState.clear();
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean isSubtypeOfForSingleClassifierType(final kotlin.reflect.jvm.internal.impl.types.TypeCheckerState r18, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r19, final kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r20) {
        /*
            Method dump skipped, instruction units count: 442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker.isSubtypeOfForSingleClassifierType(kotlin.reflect.jvm.internal.impl.types.TypeCheckerState, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit isSubtypeOfForSingleClassifierType$lambda$21$lambda$20(Collection collection, final TypeCheckerState typeCheckerState, final TypeSystemContext typeSystemContext, final SimpleTypeMarker simpleTypeMarker, TypeCheckerState.ForkPointContext runForkingPoint) {
        Intrinsics.checkNotNullParameter(runForkingPoint, "$this$runForkingPoint");
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            final SimpleTypeMarker simpleTypeMarker2 = (SimpleTypeMarker) it.next();
            runForkingPoint.fork(new Function0(typeCheckerState, typeSystemContext, simpleTypeMarker2, simpleTypeMarker) { // from class: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker$$Lambda$1
                private final TypeCheckerState arg$0;
                private final TypeSystemContext arg$1;
                private final SimpleTypeMarker arg$2;
                private final SimpleTypeMarker arg$3;

                {
                    this.arg$0 = typeCheckerState;
                    this.arg$1 = typeSystemContext;
                    this.arg$2 = simpleTypeMarker2;
                    this.arg$3 = simpleTypeMarker;
                }

                @Override // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return Boolean.valueOf(AbstractTypeChecker.isSubtypeOfForSingleClassifierType$lambda$21$lambda$20$lambda$19(this.arg$0, this.arg$1, this.arg$2, this.arg$3));
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSubtypeOfForSingleClassifierType$lambda$21$lambda$20$lambda$19(TypeCheckerState typeCheckerState, TypeSystemContext typeSystemContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        return INSTANCE.isSubtypeForSameConstructor(typeCheckerState, typeSystemContext.asArgumentList(simpleTypeMarker), simpleTypeMarker2);
    }

    private final boolean isTypeVariableAgainstStarProjectionForSelfType(TypeSystemContext typeSystemContext, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2, TypeConstructorMarker typeConstructorMarker) {
        SimpleTypeMarker simpleTypeMarkerAsSimpleType = typeSystemContext.asSimpleType(kotlinTypeMarker);
        if (simpleTypeMarkerAsSimpleType instanceof CapturedTypeMarker) {
            CapturedTypeMarker capturedTypeMarker = (CapturedTypeMarker) simpleTypeMarkerAsSimpleType;
            if (typeSystemContext.isOldCapturedType(capturedTypeMarker) || !typeSystemContext.isStarProjection(typeSystemContext.projection(typeSystemContext.typeConstructor(capturedTypeMarker))) || typeSystemContext.captureStatus(capturedTypeMarker) != CaptureStatus.FOR_SUBTYPING) {
                return false;
            }
            typeSystemContext.typeConstructor(kotlinTypeMarker2);
        }
        return false;
    }

    public final boolean isSubtypeForSameConstructor(TypeCheckerState typeCheckerState, TypeArgumentListMarker capturedSubArguments, SimpleTypeMarker superType) {
        boolean zEqualTypes;
        Intrinsics.checkNotNullParameter(typeCheckerState, "<this>");
        Intrinsics.checkNotNullParameter(capturedSubArguments, "capturedSubArguments");
        Intrinsics.checkNotNullParameter(superType, "superType");
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemContext.typeConstructor(superType);
        int size = typeSystemContext.size(capturedSubArguments);
        int iParametersCount = typeSystemContext.parametersCount(typeConstructorMarkerTypeConstructor);
        if (size != iParametersCount || size != typeSystemContext.argumentsCount(superType)) {
            return false;
        }
        for (int i = 0; i < iParametersCount; i++) {
            TypeArgumentMarker argument = typeSystemContext.getArgument(superType, i);
            if (!typeSystemContext.isStarProjection(argument)) {
                KotlinTypeMarker type = typeSystemContext.getType(argument);
                TypeArgumentMarker typeArgumentMarker = typeSystemContext.get(capturedSubArguments, i);
                typeSystemContext.getVariance(typeArgumentMarker);
                TypeVariance typeVariance = TypeVariance.INV;
                KotlinTypeMarker type2 = typeSystemContext.getType(typeArgumentMarker);
                AbstractTypeChecker abstractTypeChecker = INSTANCE;
                TypeVariance typeVarianceEffectiveVariance = abstractTypeChecker.effectiveVariance(typeSystemContext.getVariance(typeSystemContext.getParameter(typeConstructorMarkerTypeConstructor, i)), typeSystemContext.getVariance(argument));
                if (typeVarianceEffectiveVariance == null) {
                    return typeCheckerState.isErrorTypeEqualsToAnything();
                }
                if (typeVarianceEffectiveVariance != typeVariance || (!abstractTypeChecker.isTypeVariableAgainstStarProjectionForSelfType(typeSystemContext, type2, type, typeConstructorMarkerTypeConstructor) && !abstractTypeChecker.isTypeVariableAgainstStarProjectionForSelfType(typeSystemContext, type, type2, typeConstructorMarkerTypeConstructor))) {
                    if (typeCheckerState.argumentsDepth <= 100) {
                        typeCheckerState.argumentsDepth++;
                        int i2 = WhenMappings.$EnumSwitchMapping$0[typeVarianceEffectiveVariance.ordinal()];
                        if (i2 == 1) {
                            zEqualTypes = abstractTypeChecker.equalTypes(typeCheckerState, type2, type);
                        } else if (i2 == 2) {
                            zEqualTypes = isSubtypeOf$default(abstractTypeChecker, typeCheckerState, type2, type, false, 8, null);
                        } else {
                            if (i2 != 3) {
                                throw new NoWhenBranchMatchedException();
                            }
                            zEqualTypes = isSubtypeOf$default(abstractTypeChecker, typeCheckerState, type, type2, false, 8, null);
                        }
                        typeCheckerState.argumentsDepth--;
                        if (!zEqualTypes) {
                            return false;
                        }
                    } else {
                        throw new IllegalStateException(("Arguments depth is too high. Some related argument: " + type2).toString());
                    }
                }
            }
        }
        return true;
    }

    private final boolean isCommonDenotableType(TypeSystemContext typeSystemContext, KotlinTypeMarker kotlinTypeMarker) {
        return (!typeSystemContext.isDenotable(typeSystemContext.typeConstructor(kotlinTypeMarker)) || typeSystemContext.isDynamic(kotlinTypeMarker) || typeSystemContext.isDefinitelyNotNullType(kotlinTypeMarker) || typeSystemContext.isNotNullTypeParameter(kotlinTypeMarker) || typeSystemContext.isFlexibleWithDifferentTypeConstructors(kotlinTypeMarker)) ? false : true;
    }

    public final TypeVariance effectiveVariance(TypeVariance declared, TypeVariance useSite) {
        Intrinsics.checkNotNullParameter(declared, "declared");
        Intrinsics.checkNotNullParameter(useSite, "useSite");
        TypeVariance typeVariance = TypeVariance.INV;
        if (declared == typeVariance) {
            return useSite;
        }
        if (useSite == typeVariance || declared == useSite) {
            return declared;
        }
        return null;
    }

    private final boolean isStubTypeSubtypeOfAnother(TypeSystemContext typeSystemContext, SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        SimpleTypeMarker simpleTypeMarkerOriginal;
        SimpleTypeMarker simpleTypeMarkerOriginal2;
        DefinitelyNotNullTypeMarker definitelyNotNullTypeMarkerAsDefinitelyNotNullType = typeSystemContext.asDefinitelyNotNullType(simpleTypeMarker);
        if (definitelyNotNullTypeMarkerAsDefinitelyNotNullType == null || (simpleTypeMarkerOriginal = typeSystemContext.original(definitelyNotNullTypeMarkerAsDefinitelyNotNullType)) == null) {
            simpleTypeMarkerOriginal = simpleTypeMarker;
        }
        DefinitelyNotNullTypeMarker definitelyNotNullTypeMarkerAsDefinitelyNotNullType2 = typeSystemContext.asDefinitelyNotNullType(simpleTypeMarker2);
        if (definitelyNotNullTypeMarkerAsDefinitelyNotNullType2 == null || (simpleTypeMarkerOriginal2 = typeSystemContext.original(definitelyNotNullTypeMarkerAsDefinitelyNotNullType2)) == null) {
            simpleTypeMarkerOriginal2 = simpleTypeMarker2;
        }
        if (typeSystemContext.typeConstructor(simpleTypeMarkerOriginal) != typeSystemContext.typeConstructor(simpleTypeMarkerOriginal2)) {
            return false;
        }
        if (typeSystemContext.isDefinitelyNotNullType(simpleTypeMarker) || !typeSystemContext.isDefinitelyNotNullType(simpleTypeMarker2)) {
            return !typeSystemContext.isMarkedNullable(simpleTypeMarker) || typeSystemContext.isMarkedNullable(simpleTypeMarker2);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:86:0x013c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Boolean checkSubtypeForSpecialCases(kotlin.reflect.jvm.internal.impl.types.TypeCheckerState r15, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r16, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r17) {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker.checkSubtypeForSpecialCases(kotlin.reflect.jvm.internal.impl.types.TypeCheckerState, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker, kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker):java.lang.Boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0062, code lost:
    
        return r7.getParameter(r7.typeConstructor(r8), r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker getTypeParameterForArgumentInBaseIfItEqualToTarget(kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext r7, kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r8, kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r9) {
        /*
            r6 = this;
            int r0 = r7.argumentsCount(r8)
            r1 = 0
            r2 = r1
        L6:
            r3 = 0
            if (r2 >= r0) goto L66
            kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker r4 = r7.getArgument(r8, r2)
            boolean r5 = r7.isStarProjection(r4)
            if (r5 != 0) goto L14
            r3 = r4
        L14:
            if (r3 == 0) goto L63
            kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker r3 = r7.getType(r3)
            if (r3 != 0) goto L1d
            goto L63
        L1d:
            kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r4 = r7.lowerBoundIfFlexible(r3)
            kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r4 = r7.originalIfDefinitelyNotNullable(r4)
            boolean r4 = r7.isCapturedType(r4)
            if (r4 == 0) goto L3b
            kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r4 = r7.lowerBoundIfFlexible(r9)
            kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker r4 = r7.originalIfDefinitelyNotNullable(r4)
            boolean r4 = r7.isCapturedType(r4)
            if (r4 == 0) goto L3b
            r4 = 1
            goto L3c
        L3b:
            r4 = r1
        L3c:
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r9)
            if (r5 != 0) goto L5a
            if (r4 == 0) goto L53
            kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker r4 = r7.typeConstructor(r3)
            kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker r5 = r7.typeConstructor(r9)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 == 0) goto L53
            goto L5a
        L53:
            kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker r3 = r6.getTypeParameterForArgumentInBaseIfItEqualToTarget(r7, r3, r9)
            if (r3 == 0) goto L63
            return r3
        L5a:
            kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker r8 = r7.typeConstructor(r8)
            kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker r7 = r7.getParameter(r8, r2)
            return r7
        L63:
            int r2 = r2 + 1
            goto L6
        L66:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker.getTypeParameterForArgumentInBaseIfItEqualToTarget(kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext, kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker, kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker):kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker");
    }

    private final List collectAllSupertypesWithGivenTypeConstructor(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        TypeCheckerState.SupertypesPolicy supertypesPolicySubstitutionSupertypePolicy;
        SimpleTypeMarker simpleTypeMarker2 = simpleTypeMarker;
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        List listFastCorrespondingSupertypes = typeSystemContext.fastCorrespondingSupertypes(simpleTypeMarker2, typeConstructorMarker);
        if (listFastCorrespondingSupertypes != null) {
            return listFastCorrespondingSupertypes;
        }
        if (!typeSystemContext.isClassTypeConstructor(typeConstructorMarker) && typeSystemContext.isClassType(simpleTypeMarker2)) {
            return CollectionsKt.emptyList();
        }
        if (typeSystemContext.isCommonFinalClassConstructor(typeConstructorMarker)) {
            if (typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(simpleTypeMarker2), typeConstructorMarker)) {
                SimpleTypeMarker simpleTypeMarkerCaptureFromArguments = typeSystemContext.captureFromArguments(simpleTypeMarker2, CaptureStatus.FOR_SUBTYPING);
                if (simpleTypeMarkerCaptureFromArguments != null) {
                    simpleTypeMarker2 = simpleTypeMarkerCaptureFromArguments;
                }
                return CollectionsKt.listOf(simpleTypeMarker2);
            }
            return CollectionsKt.emptyList();
        }
        SmartList smartList = new SmartList();
        typeCheckerState.initialize();
        ArrayDeque supertypesDeque = typeCheckerState.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set supertypesSet = typeCheckerState.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(simpleTypeMarker2);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + simpleTypeMarker2 + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleTypeMarker simpleTypeMarker3 = (SimpleTypeMarker) supertypesDeque.pop();
            Intrinsics.checkNotNull(simpleTypeMarker3);
            if (supertypesSet.add(simpleTypeMarker3)) {
                SimpleTypeMarker simpleTypeMarkerCaptureFromArguments2 = typeSystemContext.captureFromArguments(simpleTypeMarker3, CaptureStatus.FOR_SUBTYPING);
                if (simpleTypeMarkerCaptureFromArguments2 == null) {
                    simpleTypeMarkerCaptureFromArguments2 = simpleTypeMarker3;
                }
                if (typeSystemContext.areEqualTypeConstructors(typeSystemContext.typeConstructor(simpleTypeMarkerCaptureFromArguments2), typeConstructorMarker)) {
                    smartList.add(simpleTypeMarkerCaptureFromArguments2);
                    supertypesPolicySubstitutionSupertypePolicy = TypeCheckerState.SupertypesPolicy.None.INSTANCE;
                } else if (typeSystemContext.argumentsCount(simpleTypeMarkerCaptureFromArguments2) == 0) {
                    supertypesPolicySubstitutionSupertypePolicy = TypeCheckerState.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                } else {
                    supertypesPolicySubstitutionSupertypePolicy = typeCheckerState.getTypeSystemContext().substitutionSupertypePolicy(simpleTypeMarkerCaptureFromArguments2);
                }
                if (Intrinsics.areEqual(supertypesPolicySubstitutionSupertypePolicy, TypeCheckerState.SupertypesPolicy.None.INSTANCE)) {
                    supertypesPolicySubstitutionSupertypePolicy = null;
                }
                if (supertypesPolicySubstitutionSupertypePolicy != null) {
                    TypeSystemContext typeSystemContext2 = typeCheckerState.getTypeSystemContext();
                    Iterator it = typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(simpleTypeMarker3)).iterator();
                    while (it.hasNext()) {
                        supertypesDeque.add(supertypesPolicySubstitutionSupertypePolicy.mo2785transformType(typeCheckerState, (KotlinTypeMarker) it.next()));
                    }
                }
            }
        }
        typeCheckerState.clear();
        return smartList;
    }

    private final List collectAndFilter(TypeCheckerState typeCheckerState, SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        return selectOnlyPureKotlinSupertypes(typeCheckerState, collectAllSupertypesWithGivenTypeConstructor(typeCheckerState, simpleTypeMarker, typeConstructorMarker));
    }

    private final List selectOnlyPureKotlinSupertypes(TypeCheckerState typeCheckerState, List list) {
        int i;
        TypeSystemContext typeSystemContext = typeCheckerState.getTypeSystemContext();
        if (list.size() < 2) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            TypeArgumentListMarker typeArgumentListMarkerAsArgumentList = typeSystemContext.asArgumentList((SimpleTypeMarker) obj);
            int size = typeSystemContext.size(typeArgumentListMarkerAsArgumentList);
            while (true) {
                if (i < size) {
                    i = typeSystemContext.asFlexibleType(typeSystemContext.getType(typeSystemContext.get(typeArgumentListMarkerAsArgumentList, i))) == null ? i + 1 : 0;
                } else {
                    arrayList.add(obj);
                    break;
                }
            }
        }
        return !arrayList.isEmpty() ? arrayList : list;
    }

    public final List findCorrespondingSupertypes(TypeCheckerState state, SimpleTypeMarker subType, TypeConstructorMarker superConstructor) {
        TypeCheckerState.SupertypesPolicy supertypesPolicy;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(subType, "subType");
        Intrinsics.checkNotNullParameter(superConstructor, "superConstructor");
        TypeSystemContext typeSystemContext = state.getTypeSystemContext();
        if (typeSystemContext.isClassType(subType)) {
            return INSTANCE.collectAndFilter(state, subType, superConstructor);
        }
        if (!typeSystemContext.isClassTypeConstructor(superConstructor) && !typeSystemContext.isIntegerLiteralTypeConstructor(superConstructor)) {
            return INSTANCE.collectAllSupertypesWithGivenTypeConstructor(state, subType, superConstructor);
        }
        SmartList<SimpleTypeMarker> smartList = new SmartList();
        state.initialize();
        ArrayDeque supertypesDeque = state.getSupertypesDeque();
        Intrinsics.checkNotNull(supertypesDeque);
        Set supertypesSet = state.getSupertypesSet();
        Intrinsics.checkNotNull(supertypesSet);
        supertypesDeque.push(subType);
        while (!supertypesDeque.isEmpty()) {
            if (supertypesSet.size() > 1000) {
                throw new IllegalStateException(("Too many supertypes for type: " + subType + ". Supertypes = " + CollectionsKt.joinToString$default(supertypesSet, null, null, null, 0, null, null, 63, null)).toString());
            }
            SimpleTypeMarker simpleTypeMarker = (SimpleTypeMarker) supertypesDeque.pop();
            Intrinsics.checkNotNull(simpleTypeMarker);
            if (supertypesSet.add(simpleTypeMarker)) {
                if (typeSystemContext.isClassType(simpleTypeMarker)) {
                    smartList.add(simpleTypeMarker);
                    supertypesPolicy = TypeCheckerState.SupertypesPolicy.None.INSTANCE;
                } else {
                    supertypesPolicy = TypeCheckerState.SupertypesPolicy.LowerIfFlexible.INSTANCE;
                }
                if (Intrinsics.areEqual(supertypesPolicy, TypeCheckerState.SupertypesPolicy.None.INSTANCE)) {
                    supertypesPolicy = null;
                }
                if (supertypesPolicy != null) {
                    TypeSystemContext typeSystemContext2 = state.getTypeSystemContext();
                    Iterator it = typeSystemContext2.supertypes(typeSystemContext2.typeConstructor(simpleTypeMarker)).iterator();
                    while (it.hasNext()) {
                        supertypesDeque.add(supertypesPolicy.mo2785transformType(state, (KotlinTypeMarker) it.next()));
                    }
                }
            }
        }
        state.clear();
        ArrayList arrayList = new ArrayList();
        for (SimpleTypeMarker simpleTypeMarker2 : smartList) {
            AbstractTypeChecker abstractTypeChecker = INSTANCE;
            Intrinsics.checkNotNull(simpleTypeMarker2);
            CollectionsKt.addAll(arrayList, abstractTypeChecker.collectAndFilter(state, simpleTypeMarker2, superConstructor));
        }
        return arrayList;
    }
}
