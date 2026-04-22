package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypePreparator;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: NewCapturedType.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class NewCapturedTypeKt {
    public static final SimpleType captureFromArguments(SimpleType type, CaptureStatus status) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(status, "status");
        List listCaptureArguments = captureArguments(type, status);
        if (listCaptureArguments != null) {
            return replaceArguments(type, listCaptureArguments);
        }
        return null;
    }

    private static final SimpleType replaceArguments(UnwrappedType unwrappedType, List list) {
        return KotlinTypeFactory.simpleType$default(unwrappedType.getAttributes(), unwrappedType.getConstructor(), list, unwrappedType.isMarkedNullable(), null, 16, null);
    }

    private static final List captureArguments(UnwrappedType unwrappedType, CaptureStatus captureStatus) {
        if (unwrappedType.getArguments().size() != unwrappedType.getConstructor().getParameters().size()) {
            return null;
        }
        List arguments = unwrappedType.getArguments();
        if (!(arguments instanceof Collection) || !arguments.isEmpty()) {
            Iterator it = arguments.iterator();
            while (it.hasNext()) {
                if (((TypeProjection) it.next()).getProjectionKind() != Variance.INVARIANT) {
                    List parameters = unwrappedType.getConstructor().getParameters();
                    Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
                    List<Pair> listZip = CollectionsKt.zip(arguments, parameters);
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listZip, 10));
                    for (Pair pair : listZip) {
                        TypeProjection typeProjectionAsTypeProjection = (TypeProjection) pair.component1();
                        TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) pair.component2();
                        if (typeProjectionAsTypeProjection.getProjectionKind() != Variance.INVARIANT) {
                            UnwrappedType unwrappedTypeUnwrap = (typeProjectionAsTypeProjection.isStarProjection() || typeProjectionAsTypeProjection.getProjectionKind() != Variance.IN_VARIANCE) ? null : typeProjectionAsTypeProjection.getType().unwrap();
                            Intrinsics.checkNotNull(typeParameterDescriptor);
                            typeProjectionAsTypeProjection = TypeUtilsKt.asTypeProjection(new NewCapturedType(captureStatus, unwrappedTypeUnwrap, typeProjectionAsTypeProjection, typeParameterDescriptor));
                        }
                        arrayList.add(typeProjectionAsTypeProjection);
                    }
                    TypeSubstitutor typeSubstitutorBuildSubstitutor = TypeConstructorSubstitution.Companion.create(unwrappedType.getConstructor(), arrayList).buildSubstitutor();
                    int size = arguments.size();
                    for (int i = 0; i < size; i++) {
                        TypeProjection typeProjection = (TypeProjection) arguments.get(i);
                        TypeProjection typeProjection2 = (TypeProjection) arrayList.get(i);
                        if (typeProjection.getProjectionKind() != Variance.INVARIANT) {
                            List upperBounds = ((TypeParameterDescriptor) unwrappedType.getConstructor().getParameters().get(i)).getUpperBounds();
                            Intrinsics.checkNotNullExpressionValue(upperBounds, "getUpperBounds(...)");
                            ArrayList arrayList2 = new ArrayList();
                            Iterator it2 = upperBounds.iterator();
                            while (it2.hasNext()) {
                                arrayList2.add(KotlinTypePreparator.Default.INSTANCE.prepareType((KotlinTypeMarker) typeSubstitutorBuildSubstitutor.safeSubstitute((KotlinType) it2.next(), Variance.INVARIANT).unwrap()));
                            }
                            if (!typeProjection.isStarProjection() && typeProjection.getProjectionKind() == Variance.OUT_VARIANCE) {
                                arrayList2.add(KotlinTypePreparator.Default.INSTANCE.prepareType((KotlinTypeMarker) typeProjection.getType().unwrap()));
                            }
                            KotlinType type = typeProjection2.getType();
                            Intrinsics.checkNotNull(type, "null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedType");
                            ((NewCapturedType) type).getConstructor().initializeSupertypes(arrayList2);
                        }
                    }
                    return arrayList;
                }
            }
        }
        return null;
    }
}
