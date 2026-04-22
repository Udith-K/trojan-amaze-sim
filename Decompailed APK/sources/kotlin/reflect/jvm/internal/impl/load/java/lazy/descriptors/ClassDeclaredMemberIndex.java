package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaLoadingKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaRecordComponent;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: DeclaredMemberIndex.kt */
/* JADX INFO: loaded from: classes2.dex */
public class ClassDeclaredMemberIndex implements DeclaredMemberIndex {
    private final Map components;
    private final Map fields;
    private final JavaClass jClass;
    private final Function1 memberFilter;
    private final Function1 methodFilter;
    private final Map methods;

    public ClassDeclaredMemberIndex(JavaClass jClass, Function1 memberFilter) {
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        Intrinsics.checkNotNullParameter(memberFilter, "memberFilter");
        this.jClass = jClass;
        this.memberFilter = memberFilter;
        Function1 function1 = new Function1(this) { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.ClassDeclaredMemberIndex$$Lambda$0
            private final ClassDeclaredMemberIndex arg$0;

            {
                this.arg$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public Object invoke(Object obj) {
                return Boolean.valueOf(ClassDeclaredMemberIndex.methodFilter$lambda$0(this.arg$0, (JavaMethod) obj));
            }
        };
        this.methodFilter = function1;
        Sequence sequenceFilter = SequencesKt.filter(CollectionsKt.asSequence(jClass.getMethods()), function1);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : sequenceFilter) {
            Name name = ((JavaMethod) obj).getName();
            Object arrayList = linkedHashMap.get(name);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(name, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        this.methods = linkedHashMap;
        Sequence sequenceFilter2 = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getFields()), this.memberFilter);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj2 : sequenceFilter2) {
            linkedHashMap2.put(((JavaField) obj2).getName(), obj2);
        }
        this.fields = linkedHashMap2;
        Collection recordComponents = this.jClass.getRecordComponents();
        Function1 function12 = this.memberFilter;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj3 : recordComponents) {
            if (((Boolean) function12.invoke(obj3)).booleanValue()) {
                arrayList2.add(obj3);
            }
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
        for (Object obj4 : arrayList2) {
            linkedHashMap3.put(((JavaRecordComponent) obj4).getName(), obj4);
        }
        this.components = linkedHashMap3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean methodFilter$lambda$0(ClassDeclaredMemberIndex classDeclaredMemberIndex, JavaMethod m) {
        Intrinsics.checkNotNullParameter(m, "m");
        return ((Boolean) classDeclaredMemberIndex.memberFilter.invoke(m)).booleanValue() && !JavaLoadingKt.isObjectMethodInInterface(m);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public Collection findMethodsByName(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List list = (List) this.methods.get(name);
        return list != null ? list : CollectionsKt.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public Set getMethodNames() {
        Sequence sequenceFilter = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getMethods()), this.methodFilter);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = sequenceFilter.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((JavaMethod) it.next()).getName());
        }
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public JavaField findFieldByName(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return (JavaField) this.fields.get(name);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public Set getFieldNames() {
        Sequence sequenceFilter = SequencesKt.filter(CollectionsKt.asSequence(this.jClass.getFields()), this.memberFilter);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = sequenceFilter.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((JavaField) it.next()).getName());
        }
        return linkedHashSet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public Set getRecordComponentNames() {
        return this.components.keySet();
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex
    public JavaRecordComponent findRecordComponentByName(Name name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return (JavaRecordComponent) this.components.get(name);
    }
}
