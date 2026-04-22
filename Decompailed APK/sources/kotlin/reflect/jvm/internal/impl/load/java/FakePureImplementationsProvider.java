package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.StandardClassIds;

/* JADX INFO: compiled from: FakePureImplementationsProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class FakePureImplementationsProvider {
    public static final FakePureImplementationsProvider INSTANCE;
    private static final Map pureImplementationsClassIds;
    private static final Map pureImplementationsFqNames;

    private FakePureImplementationsProvider() {
    }

    public final FqName getPurelyImplementedInterface(FqName classFqName) {
        Intrinsics.checkNotNullParameter(classFqName, "classFqName");
        return (FqName) pureImplementationsFqNames.get(classFqName);
    }

    static {
        FakePureImplementationsProvider fakePureImplementationsProvider = new FakePureImplementationsProvider();
        INSTANCE = fakePureImplementationsProvider;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        pureImplementationsClassIds = linkedHashMap;
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        fakePureImplementationsProvider.implementedWith(standardClassIds.getMutableList(), fakePureImplementationsProvider.fqNameListOf("java.util.ArrayList", "java.util.LinkedList"));
        fakePureImplementationsProvider.implementedWith(standardClassIds.getMutableSet(), fakePureImplementationsProvider.fqNameListOf("java.util.HashSet", "java.util.TreeSet", "java.util.LinkedHashSet"));
        fakePureImplementationsProvider.implementedWith(standardClassIds.getMutableMap(), fakePureImplementationsProvider.fqNameListOf("java.util.HashMap", "java.util.TreeMap", "java.util.LinkedHashMap", "java.util.concurrent.ConcurrentHashMap", "java.util.concurrent.ConcurrentSkipListMap"));
        ClassId.Companion companion = ClassId.Companion;
        fakePureImplementationsProvider.implementedWith(companion.topLevel(new FqName("java.util.function.Function")), fakePureImplementationsProvider.fqNameListOf("java.util.function.UnaryOperator"));
        fakePureImplementationsProvider.implementedWith(companion.topLevel(new FqName("java.util.function.BiFunction")), fakePureImplementationsProvider.fqNameListOf("java.util.function.BinaryOperator"));
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(TuplesKt.to(((ClassId) entry.getKey()).asSingleFqName(), ((ClassId) entry.getValue()).asSingleFqName()));
        }
        pureImplementationsFqNames = MapsKt.toMap(arrayList);
    }

    private final void implementedWith(ClassId classId, List list) {
        Map map = pureImplementationsClassIds;
        for (Object obj : list) {
            map.put(obj, classId);
        }
    }

    private final List fqNameListOf(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(ClassId.Companion.topLevel(new FqName(str)));
        }
        return arrayList;
    }
}
