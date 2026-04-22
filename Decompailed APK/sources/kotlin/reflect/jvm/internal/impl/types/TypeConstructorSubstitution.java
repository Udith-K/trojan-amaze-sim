package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

/* JADX INFO: compiled from: TypeSubstitution.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class TypeConstructorSubstitution extends TypeSubstitution {
    public static final Companion Companion = new Companion(null);

    public static final TypeSubstitution create(TypeConstructor typeConstructor, List list) {
        return Companion.create(typeConstructor, list);
    }

    public static final TypeConstructorSubstitution createByConstructorsMap(Map map) {
        return Companion.createByConstructorsMap(map);
    }

    public abstract TypeProjection get(TypeConstructor typeConstructor);

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    /* JADX INFO: renamed from: get */
    public TypeProjection mo2786get(KotlinType key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return get(key.getConstructor());
    }

    /* JADX INFO: compiled from: TypeSubstitution.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TypeConstructorSubstitution createByConstructorsMap(Map map) {
            Intrinsics.checkNotNullParameter(map, "map");
            return createByConstructorsMap$default(this, map, false, 2, null);
        }

        private Companion() {
        }

        public static /* synthetic */ TypeConstructorSubstitution createByConstructorsMap$default(Companion companion, Map map, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.createByConstructorsMap(map, z);
        }

        public final TypeConstructorSubstitution createByConstructorsMap(final Map map, final boolean z) {
            Intrinsics.checkNotNullParameter(map, "map");
            return new TypeConstructorSubstitution() { // from class: kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution$Companion$createByConstructorsMap$1
                @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
                public TypeProjection get(TypeConstructor key) {
                    Intrinsics.checkNotNullParameter(key, "key");
                    return (TypeProjection) map.get(key);
                }

                @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
                public boolean isEmpty() {
                    return map.isEmpty();
                }

                @Override // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
                public boolean approximateCapturedTypes() {
                    return z;
                }
            };
        }

        public final TypeSubstitution create(KotlinType kotlinType) {
            Intrinsics.checkNotNullParameter(kotlinType, "kotlinType");
            return create(kotlinType.getConstructor(), kotlinType.getArguments());
        }

        public final TypeSubstitution create(TypeConstructor typeConstructor, List arguments) {
            Intrinsics.checkNotNullParameter(typeConstructor, "typeConstructor");
            Intrinsics.checkNotNullParameter(arguments, "arguments");
            List parameters = typeConstructor.getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
            TypeParameterDescriptor typeParameterDescriptor = (TypeParameterDescriptor) CollectionsKt.lastOrNull(parameters);
            if (typeParameterDescriptor != null && typeParameterDescriptor.isCapturedFromOuterDeclaration()) {
                List parameters2 = typeConstructor.getParameters();
                Intrinsics.checkNotNullExpressionValue(parameters2, "getParameters(...)");
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters2, 10));
                Iterator it = parameters2.iterator();
                while (it.hasNext()) {
                    arrayList.add(((TypeParameterDescriptor) it.next()).getTypeConstructor());
                }
                return createByConstructorsMap$default(this, MapsKt.toMap(CollectionsKt.zip(arrayList, arguments)), false, 2, null);
            }
            return new IndexedParametersSubstitution(parameters, arguments);
        }
    }
}
