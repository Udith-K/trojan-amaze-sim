package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter;

/* JADX INFO: compiled from: protoTypeTableUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ProtoTypeTableUtilKt {
    public static final List supertypes(ProtoBuf$Class protoBuf$Class, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Class, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List supertypeList = protoBuf$Class.getSupertypeList();
        if (supertypeList.isEmpty()) {
            supertypeList = null;
        }
        if (supertypeList == null) {
            List<Integer> supertypeIdList = protoBuf$Class.getSupertypeIdList();
            Intrinsics.checkNotNullExpressionValue(supertypeIdList, "getSupertypeIdList(...)");
            supertypeList = new ArrayList(CollectionsKt.collectionSizeOrDefault(supertypeIdList, 10));
            for (Integer num : supertypeIdList) {
                Intrinsics.checkNotNull(num);
                supertypeList.add(typeTable.get(num.intValue()));
            }
        }
        return supertypeList;
    }

    public static final ProtoBuf$Type inlineClassUnderlyingType(ProtoBuf$Class protoBuf$Class, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Class, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Class.hasInlineClassUnderlyingType()) {
            return protoBuf$Class.getInlineClassUnderlyingType();
        }
        if (protoBuf$Class.hasInlineClassUnderlyingTypeId()) {
            return typeTable.get(protoBuf$Class.getInlineClassUnderlyingTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type type(ProtoBuf$Type.Argument argument, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(argument, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (argument.hasType()) {
            return argument.getType();
        }
        if (argument.hasTypeId()) {
            return typeTable.get(argument.getTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type flexibleUpperBound(ProtoBuf$Type protoBuf$Type, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Type, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Type.hasFlexibleUpperBound()) {
            return protoBuf$Type.getFlexibleUpperBound();
        }
        if (protoBuf$Type.hasFlexibleUpperBoundId()) {
            return typeTable.get(protoBuf$Type.getFlexibleUpperBoundId());
        }
        return null;
    }

    public static final List upperBounds(ProtoBuf$TypeParameter protoBuf$TypeParameter, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeParameter, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List upperBoundList = protoBuf$TypeParameter.getUpperBoundList();
        if (upperBoundList.isEmpty()) {
            upperBoundList = null;
        }
        if (upperBoundList == null) {
            List<Integer> upperBoundIdList = protoBuf$TypeParameter.getUpperBoundIdList();
            Intrinsics.checkNotNullExpressionValue(upperBoundIdList, "getUpperBoundIdList(...)");
            upperBoundList = new ArrayList(CollectionsKt.collectionSizeOrDefault(upperBoundIdList, 10));
            for (Integer num : upperBoundIdList) {
                Intrinsics.checkNotNull(num);
                upperBoundList.add(typeTable.get(num.intValue()));
            }
        }
        return upperBoundList;
    }

    public static final ProtoBuf$Type returnType(ProtoBuf$Function protoBuf$Function, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Function, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Function.hasReturnType()) {
            ProtoBuf$Type returnType = protoBuf$Function.getReturnType();
            Intrinsics.checkNotNullExpressionValue(returnType, "getReturnType(...)");
            return returnType;
        }
        if (protoBuf$Function.hasReturnTypeId()) {
            return typeTable.get(protoBuf$Function.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Function");
    }

    public static final boolean hasReceiver(ProtoBuf$Function protoBuf$Function) {
        Intrinsics.checkNotNullParameter(protoBuf$Function, "<this>");
        return protoBuf$Function.hasReceiverType() || protoBuf$Function.hasReceiverTypeId();
    }

    public static final ProtoBuf$Type receiverType(ProtoBuf$Function protoBuf$Function, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Function, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Function.hasReceiverType()) {
            return protoBuf$Function.getReceiverType();
        }
        if (protoBuf$Function.hasReceiverTypeId()) {
            return typeTable.get(protoBuf$Function.getReceiverTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type returnType(ProtoBuf$Property protoBuf$Property, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Property, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Property.hasReturnType()) {
            ProtoBuf$Type returnType = protoBuf$Property.getReturnType();
            Intrinsics.checkNotNullExpressionValue(returnType, "getReturnType(...)");
            return returnType;
        }
        if (protoBuf$Property.hasReturnTypeId()) {
            return typeTable.get(protoBuf$Property.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Property");
    }

    public static final boolean hasReceiver(ProtoBuf$Property protoBuf$Property) {
        Intrinsics.checkNotNullParameter(protoBuf$Property, "<this>");
        return protoBuf$Property.hasReceiverType() || protoBuf$Property.hasReceiverTypeId();
    }

    public static final ProtoBuf$Type receiverType(ProtoBuf$Property protoBuf$Property, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Property, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Property.hasReceiverType()) {
            return protoBuf$Property.getReceiverType();
        }
        if (protoBuf$Property.hasReceiverTypeId()) {
            return typeTable.get(protoBuf$Property.getReceiverTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type type(ProtoBuf$ValueParameter protoBuf$ValueParameter, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$ValueParameter, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$ValueParameter.hasType()) {
            ProtoBuf$Type type = protoBuf$ValueParameter.getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            return type;
        }
        if (protoBuf$ValueParameter.hasTypeId()) {
            return typeTable.get(protoBuf$ValueParameter.getTypeId());
        }
        throw new IllegalStateException("No type in ProtoBuf.ValueParameter");
    }

    public static final ProtoBuf$Type varargElementType(ProtoBuf$ValueParameter protoBuf$ValueParameter, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$ValueParameter, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$ValueParameter.hasVarargElementType()) {
            return protoBuf$ValueParameter.getVarargElementType();
        }
        if (protoBuf$ValueParameter.hasVarargElementTypeId()) {
            return typeTable.get(protoBuf$ValueParameter.getVarargElementTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type outerType(ProtoBuf$Type protoBuf$Type, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Type, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Type.hasOuterType()) {
            return protoBuf$Type.getOuterType();
        }
        if (protoBuf$Type.hasOuterTypeId()) {
            return typeTable.get(protoBuf$Type.getOuterTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type abbreviatedType(ProtoBuf$Type protoBuf$Type, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Type, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$Type.hasAbbreviatedType()) {
            return protoBuf$Type.getAbbreviatedType();
        }
        if (protoBuf$Type.hasAbbreviatedTypeId()) {
            return typeTable.get(protoBuf$Type.getAbbreviatedTypeId());
        }
        return null;
    }

    public static final ProtoBuf$Type underlyingType(ProtoBuf$TypeAlias protoBuf$TypeAlias, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeAlias, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$TypeAlias.hasUnderlyingType()) {
            ProtoBuf$Type underlyingType = protoBuf$TypeAlias.getUnderlyingType();
            Intrinsics.checkNotNullExpressionValue(underlyingType, "getUnderlyingType(...)");
            return underlyingType;
        }
        if (protoBuf$TypeAlias.hasUnderlyingTypeId()) {
            return typeTable.get(protoBuf$TypeAlias.getUnderlyingTypeId());
        }
        throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias");
    }

    public static final ProtoBuf$Type expandedType(ProtoBuf$TypeAlias protoBuf$TypeAlias, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeAlias, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        if (protoBuf$TypeAlias.hasExpandedType()) {
            ProtoBuf$Type expandedType = protoBuf$TypeAlias.getExpandedType();
            Intrinsics.checkNotNullExpressionValue(expandedType, "getExpandedType(...)");
            return expandedType;
        }
        if (protoBuf$TypeAlias.hasExpandedTypeId()) {
            return typeTable.get(protoBuf$TypeAlias.getExpandedTypeId());
        }
        throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias");
    }

    public static final List contextReceiverTypes(ProtoBuf$Class protoBuf$Class, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Class, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List contextReceiverTypeList = protoBuf$Class.getContextReceiverTypeList();
        if (contextReceiverTypeList.isEmpty()) {
            contextReceiverTypeList = null;
        }
        if (contextReceiverTypeList == null) {
            List<Integer> contextReceiverTypeIdList = protoBuf$Class.getContextReceiverTypeIdList();
            Intrinsics.checkNotNullExpressionValue(contextReceiverTypeIdList, "getContextReceiverTypeIdList(...)");
            contextReceiverTypeList = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextReceiverTypeIdList, 10));
            for (Integer num : contextReceiverTypeIdList) {
                Intrinsics.checkNotNull(num);
                contextReceiverTypeList.add(typeTable.get(num.intValue()));
            }
        }
        return contextReceiverTypeList;
    }

    public static final List contextReceiverTypes(ProtoBuf$Function protoBuf$Function, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Function, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List contextReceiverTypeList = protoBuf$Function.getContextReceiverTypeList();
        if (contextReceiverTypeList.isEmpty()) {
            contextReceiverTypeList = null;
        }
        if (contextReceiverTypeList == null) {
            List<Integer> contextReceiverTypeIdList = protoBuf$Function.getContextReceiverTypeIdList();
            Intrinsics.checkNotNullExpressionValue(contextReceiverTypeIdList, "getContextReceiverTypeIdList(...)");
            contextReceiverTypeList = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextReceiverTypeIdList, 10));
            for (Integer num : contextReceiverTypeIdList) {
                Intrinsics.checkNotNull(num);
                contextReceiverTypeList.add(typeTable.get(num.intValue()));
            }
        }
        return contextReceiverTypeList;
    }

    public static final List contextReceiverTypes(ProtoBuf$Property protoBuf$Property, TypeTable typeTable) {
        Intrinsics.checkNotNullParameter(protoBuf$Property, "<this>");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        List contextReceiverTypeList = protoBuf$Property.getContextReceiverTypeList();
        if (contextReceiverTypeList.isEmpty()) {
            contextReceiverTypeList = null;
        }
        if (contextReceiverTypeList == null) {
            List<Integer> contextReceiverTypeIdList = protoBuf$Property.getContextReceiverTypeIdList();
            Intrinsics.checkNotNullExpressionValue(contextReceiverTypeIdList, "getContextReceiverTypeIdList(...)");
            contextReceiverTypeList = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextReceiverTypeIdList, 10));
            for (Integer num : contextReceiverTypeIdList) {
                Intrinsics.checkNotNull(num);
                contextReceiverTypeList.add(typeTable.get(num.intValue()));
            }
        }
        return contextReceiverTypeList;
    }
}
