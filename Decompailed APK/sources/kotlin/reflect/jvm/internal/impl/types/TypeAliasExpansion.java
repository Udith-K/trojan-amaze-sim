package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

/* JADX INFO: compiled from: TypeAliasExpansion.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class TypeAliasExpansion {
    public static final Companion Companion = new Companion(null);
    private final List arguments;
    private final TypeAliasDescriptor descriptor;
    private final Map mapping;
    private final TypeAliasExpansion parent;

    public /* synthetic */ TypeAliasExpansion(TypeAliasExpansion typeAliasExpansion, TypeAliasDescriptor typeAliasDescriptor, List list, Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeAliasExpansion, typeAliasDescriptor, list, map);
    }

    private TypeAliasExpansion(TypeAliasExpansion typeAliasExpansion, TypeAliasDescriptor typeAliasDescriptor, List list, Map map) {
        this.parent = typeAliasExpansion;
        this.descriptor = typeAliasDescriptor;
        this.arguments = list;
        this.mapping = map;
    }

    public final TypeAliasDescriptor getDescriptor() {
        return this.descriptor;
    }

    public final List getArguments() {
        return this.arguments;
    }

    public final TypeProjection getReplacement(TypeConstructor constructor) {
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        ClassifierDescriptor classifierDescriptorMo2781getDeclarationDescriptor = constructor.mo2781getDeclarationDescriptor();
        if (classifierDescriptorMo2781getDeclarationDescriptor instanceof TypeParameterDescriptor) {
            return (TypeProjection) this.mapping.get(classifierDescriptorMo2781getDeclarationDescriptor);
        }
        return null;
    }

    public final boolean isRecursion(TypeAliasDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (!Intrinsics.areEqual(this.descriptor, descriptor)) {
            TypeAliasExpansion typeAliasExpansion = this.parent;
            if (!(typeAliasExpansion != null ? typeAliasExpansion.isRecursion(descriptor) : false)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: compiled from: TypeAliasExpansion.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TypeAliasExpansion create(TypeAliasExpansion typeAliasExpansion, TypeAliasDescriptor typeAliasDescriptor, List arguments) {
            Intrinsics.checkNotNullParameter(typeAliasDescriptor, "typeAliasDescriptor");
            Intrinsics.checkNotNullParameter(arguments, "arguments");
            List parameters = typeAliasDescriptor.getTypeConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
            Iterator it = parameters.iterator();
            while (it.hasNext()) {
                arrayList.add(((TypeParameterDescriptor) it.next()).getOriginal());
            }
            return new TypeAliasExpansion(typeAliasExpansion, typeAliasDescriptor, arguments, MapsKt.toMap(CollectionsKt.zip(arrayList, arguments)), null);
        }
    }
}
