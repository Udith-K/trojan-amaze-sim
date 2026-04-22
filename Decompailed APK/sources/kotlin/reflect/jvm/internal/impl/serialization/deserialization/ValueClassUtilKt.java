package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.InlineClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.MultiFieldValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;

/* JADX INFO: compiled from: ValueClassUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ValueClassUtilKt {
    public static final ValueClassRepresentation loadValueClassRepresentation(ProtoBuf$Class protoBuf$Class, NameResolver nameResolver, TypeTable typeTable, Function1 typeDeserializer, Function1 typeOfPublicProperty) {
        SimpleTypeMarker simpleTypeMarker;
        List multiFieldValueClassUnderlyingTypeList;
        Intrinsics.checkNotNullParameter(protoBuf$Class, "<this>");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        Intrinsics.checkNotNullParameter(typeDeserializer, "typeDeserializer");
        Intrinsics.checkNotNullParameter(typeOfPublicProperty, "typeOfPublicProperty");
        if (protoBuf$Class.getMultiFieldValueClassUnderlyingNameCount() <= 0) {
            if (!protoBuf$Class.hasInlineClassUnderlyingPropertyName()) {
                return null;
            }
            Name name = NameResolverUtilKt.getName(nameResolver, protoBuf$Class.getInlineClassUnderlyingPropertyName());
            ProtoBuf$Type protoBuf$TypeInlineClassUnderlyingType = ProtoTypeTableUtilKt.inlineClassUnderlyingType(protoBuf$Class, typeTable);
            if ((protoBuf$TypeInlineClassUnderlyingType != null && (simpleTypeMarker = (SimpleTypeMarker) typeDeserializer.invoke(protoBuf$TypeInlineClassUnderlyingType)) != null) || (simpleTypeMarker = (SimpleTypeMarker) typeOfPublicProperty.invoke(name)) != null) {
                return new InlineClassRepresentation(name, simpleTypeMarker);
            }
            throw new IllegalStateException(("cannot determine underlying type for value class " + NameResolverUtilKt.getName(nameResolver, protoBuf$Class.getFqName()) + " with property " + name).toString());
        }
        List<Integer> multiFieldValueClassUnderlyingNameList = protoBuf$Class.getMultiFieldValueClassUnderlyingNameList();
        Intrinsics.checkNotNullExpressionValue(multiFieldValueClassUnderlyingNameList, "getMultiFieldValueClassUnderlyingNameList(...)");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(multiFieldValueClassUnderlyingNameList, 10));
        for (Integer num : multiFieldValueClassUnderlyingNameList) {
            Intrinsics.checkNotNull(num);
            arrayList.add(NameResolverUtilKt.getName(nameResolver, num.intValue()));
        }
        Pair pair = TuplesKt.to(Integer.valueOf(protoBuf$Class.getMultiFieldValueClassUnderlyingTypeIdCount()), Integer.valueOf(protoBuf$Class.getMultiFieldValueClassUnderlyingTypeCount()));
        if (Intrinsics.areEqual(pair, TuplesKt.to(Integer.valueOf(arrayList.size()), 0))) {
            List<Integer> multiFieldValueClassUnderlyingTypeIdList = protoBuf$Class.getMultiFieldValueClassUnderlyingTypeIdList();
            Intrinsics.checkNotNullExpressionValue(multiFieldValueClassUnderlyingTypeIdList, "getMultiFieldValueClassUnderlyingTypeIdList(...)");
            multiFieldValueClassUnderlyingTypeList = new ArrayList(CollectionsKt.collectionSizeOrDefault(multiFieldValueClassUnderlyingTypeIdList, 10));
            for (Integer num2 : multiFieldValueClassUnderlyingTypeIdList) {
                Intrinsics.checkNotNull(num2);
                multiFieldValueClassUnderlyingTypeList.add(typeTable.get(num2.intValue()));
            }
        } else {
            if (!Intrinsics.areEqual(pair, TuplesKt.to(0, Integer.valueOf(arrayList.size())))) {
                throw new IllegalStateException(("class " + NameResolverUtilKt.getName(nameResolver, protoBuf$Class.getFqName()) + " has illegal multi-field value class representation").toString());
            }
            multiFieldValueClassUnderlyingTypeList = protoBuf$Class.getMultiFieldValueClassUnderlyingTypeList();
        }
        Intrinsics.checkNotNull(multiFieldValueClassUnderlyingTypeList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(multiFieldValueClassUnderlyingTypeList, 10));
        Iterator it = multiFieldValueClassUnderlyingTypeList.iterator();
        while (it.hasNext()) {
            arrayList2.add(typeDeserializer.invoke(it.next()));
        }
        return new MultiFieldValueClassRepresentation(CollectionsKt.zip(arrayList, arrayList2));
    }
}
