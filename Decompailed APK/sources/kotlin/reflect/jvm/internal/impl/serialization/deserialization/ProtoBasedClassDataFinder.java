package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$PackageFragment;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

/* JADX INFO: compiled from: ProtoBasedClassDataFinder.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ProtoBasedClassDataFinder implements ClassDataFinder {
    private final Map classIdToProto;
    private final Function1 classSource;
    private final BinaryVersion metadataVersion;
    private final NameResolver nameResolver;

    public ProtoBasedClassDataFinder(ProtoBuf$PackageFragment proto, NameResolver nameResolver, BinaryVersion metadataVersion, Function1 classSource) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(metadataVersion, "metadataVersion");
        Intrinsics.checkNotNullParameter(classSource, "classSource");
        this.nameResolver = nameResolver;
        this.metadataVersion = metadataVersion;
        this.classSource = classSource;
        List class_List = proto.getClass_List();
        Intrinsics.checkNotNullExpressionValue(class_List, "getClass_List(...)");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(class_List, 10)), 16));
        for (Object obj : class_List) {
            linkedHashMap.put(NameResolverUtilKt.getClassId(this.nameResolver, ((ProtoBuf$Class) obj).getFqName()), obj);
        }
        this.classIdToProto = linkedHashMap;
    }

    public final Collection getAllClassIds() {
        return this.classIdToProto.keySet();
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder
    public ClassData findClassData(ClassId classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        ProtoBuf$Class protoBuf$Class = (ProtoBuf$Class) this.classIdToProto.get(classId);
        if (protoBuf$Class == null) {
            return null;
        }
        return new ClassData(this.nameResolver, protoBuf$Class, this.metadataVersion, (SourceElement) this.classSource.invoke(classId));
    }
}
