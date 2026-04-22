package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* JADX INFO: compiled from: mappingUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class MappingUtilKt {
    public static final TypeConstructorSubstitution createMappedTypeParametersSubstitution(ClassDescriptor from, ClassDescriptor to) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        from.getDeclaredTypeParameters().size();
        to.getDeclaredTypeParameters().size();
        TypeConstructorSubstitution.Companion companion = TypeConstructorSubstitution.Companion;
        List declaredTypeParameters = from.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(declaredTypeParameters, "getDeclaredTypeParameters(...)");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(declaredTypeParameters, 10));
        Iterator it = declaredTypeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((TypeParameterDescriptor) it.next()).getTypeConstructor());
        }
        List declaredTypeParameters2 = to.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(declaredTypeParameters2, "getDeclaredTypeParameters(...)");
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(declaredTypeParameters2, 10));
        Iterator it2 = declaredTypeParameters2.iterator();
        while (it2.hasNext()) {
            SimpleType defaultType = ((TypeParameterDescriptor) it2.next()).getDefaultType();
            Intrinsics.checkNotNullExpressionValue(defaultType, "getDefaultType(...)");
            arrayList2.add(TypeUtilsKt.asTypeProjection(defaultType));
        }
        return TypeConstructorSubstitution.Companion.createByConstructorsMap$default(companion, MapsKt.toMap(CollectionsKt.zip(arrayList, arrayList2)), false, 2, null);
    }
}
