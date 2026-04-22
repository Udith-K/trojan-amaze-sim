package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.jvm.internal.impl.util.ArrayMap;
import kotlin.reflect.jvm.internal.impl.util.AttributeArrayOwner;
import kotlin.reflect.jvm.internal.impl.util.TypeRegistry;

/* JADX INFO: compiled from: TypeAttributes.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class TypeAttributes extends AttributeArrayOwner implements Iterable, KMappedMarker {
    public static final Companion Companion = new Companion(null);
    private static final TypeAttributes Empty = new TypeAttributes(CollectionsKt.emptyList());

    public /* synthetic */ TypeAttributes(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(list);
    }

    private TypeAttributes(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TypeAttribute typeAttribute = (TypeAttribute) it.next();
            registerComponent(typeAttribute.getKey(), typeAttribute);
        }
    }

    /* JADX INFO: compiled from: TypeAttributes.kt */
    public static final class Companion extends TypeRegistry {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.util.TypeRegistry
        public int customComputeIfAbsent(ConcurrentHashMap concurrentHashMap, String key, Function1 compute) {
            int iIntValue;
            Intrinsics.checkNotNullParameter(concurrentHashMap, "<this>");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(compute, "compute");
            Integer num = (Integer) concurrentHashMap.get(key);
            if (num != null) {
                return num.intValue();
            }
            synchronized (concurrentHashMap) {
                try {
                    Integer num2 = (Integer) concurrentHashMap.get(key);
                    if (num2 != null) {
                        iIntValue = num2.intValue();
                    } else {
                        Object objInvoke = compute.invoke(key);
                        concurrentHashMap.putIfAbsent(key, Integer.valueOf(((Number) objInvoke).intValue()));
                        iIntValue = ((Number) objInvoke).intValue();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return iIntValue;
        }

        public final TypeAttributes getEmpty() {
            return TypeAttributes.Empty;
        }

        public final TypeAttributes create(List attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            if (attributes.isEmpty()) {
                return getEmpty();
            }
            return new TypeAttributes(attributes, null);
        }
    }

    private TypeAttributes(TypeAttribute typeAttribute) {
        this(CollectionsKt.listOf(typeAttribute));
    }

    public final boolean contains(TypeAttribute attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        return getArrayMap().get(Companion.getId(attribute.getKey())) != null;
    }

    public final TypeAttributes plus(TypeAttribute attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        if (contains(attribute)) {
            return this;
        }
        if (isEmpty()) {
            return new TypeAttributes(attribute);
        }
        return Companion.create(CollectionsKt.plus((Collection) CollectionsKt.toList(this), (Object) attribute));
    }

    public final TypeAttributes remove(TypeAttribute attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        if (isEmpty()) {
            return this;
        }
        ArrayMap arrayMap = getArrayMap();
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayMap) {
            if (!Intrinsics.areEqual((TypeAttribute) obj, attribute)) {
                arrayList.add(obj);
            }
        }
        return arrayList.size() == getArrayMap().getSize() ? this : Companion.create(arrayList);
    }

    @Override // kotlin.reflect.jvm.internal.impl.util.AbstractArrayMapOwner
    protected TypeRegistry getTypeRegistry() {
        return Companion;
    }

    public final TypeAttributes intersect(TypeAttributes other) {
        TypeAttribute typeAttributeIntersect;
        Intrinsics.checkNotNullParameter(other, "other");
        if (isEmpty() && other.isEmpty()) {
            return this;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = Companion.getIndices().iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            TypeAttribute typeAttribute = (TypeAttribute) getArrayMap().get(iIntValue);
            TypeAttribute typeAttribute2 = (TypeAttribute) other.getArrayMap().get(iIntValue);
            if (typeAttribute == null) {
                typeAttributeIntersect = typeAttribute2 != null ? typeAttribute2.intersect(typeAttribute) : null;
            } else {
                typeAttributeIntersect = typeAttribute.intersect(typeAttribute2);
            }
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList, typeAttributeIntersect);
        }
        return Companion.create(arrayList);
    }

    public final TypeAttributes add(TypeAttributes other) {
        TypeAttribute typeAttributeAdd;
        Intrinsics.checkNotNullParameter(other, "other");
        if (isEmpty() && other.isEmpty()) {
            return this;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = Companion.getIndices().iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            TypeAttribute typeAttribute = (TypeAttribute) getArrayMap().get(iIntValue);
            TypeAttribute typeAttribute2 = (TypeAttribute) other.getArrayMap().get(iIntValue);
            if (typeAttribute == null) {
                typeAttributeAdd = typeAttribute2 != null ? typeAttribute2.add(typeAttribute) : null;
            } else {
                typeAttributeAdd = typeAttribute.add(typeAttribute2);
            }
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList, typeAttributeAdd);
        }
        return Companion.create(arrayList);
    }
}
