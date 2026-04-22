package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeTable;

/* JADX INFO: compiled from: TypeTable.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class TypeTable {
    private final List types;

    public TypeTable(ProtoBuf$TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List typeList = typeTable.getTypeList();
        if (typeTable.hasFirstNullable()) {
            int firstNullable = typeTable.getFirstNullable();
            List typeList2 = typeTable.getTypeList();
            Intrinsics.checkNotNullExpressionValue(typeList2, "getTypeList(...)");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeList2, 10));
            int i = 0;
            for (Object obj : typeList2) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                ProtoBuf$Type protoBuf$TypeBuild = (ProtoBuf$Type) obj;
                if (i >= firstNullable) {
                    protoBuf$TypeBuild = protoBuf$TypeBuild.toBuilder().setNullable(true).build();
                }
                arrayList.add(protoBuf$TypeBuild);
                i = i2;
            }
            typeList = arrayList;
        }
        Intrinsics.checkNotNullExpressionValue(typeList, "run(...)");
        this.types = typeList;
    }

    public final ProtoBuf$Type get(int i) {
        return (ProtoBuf$Type) this.types.get(i);
    }
}
