package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;

/* JADX INFO: compiled from: JvmProtoBufUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class JvmProtoBufUtil {
    private static final ExtensionRegistryLite EXTENSION_REGISTRY;
    public static final JvmProtoBufUtil INSTANCE = new JvmProtoBufUtil();

    private JvmProtoBufUtil() {
    }

    static {
        ExtensionRegistryLite extensionRegistryLiteNewInstance = ExtensionRegistryLite.newInstance();
        JvmProtoBuf.registerAllExtensions(extensionRegistryLiteNewInstance);
        Intrinsics.checkNotNullExpressionValue(extensionRegistryLiteNewInstance, "apply(...)");
        EXTENSION_REGISTRY = extensionRegistryLiteNewInstance;
    }

    public final ExtensionRegistryLite getEXTENSION_REGISTRY() {
        return EXTENSION_REGISTRY;
    }

    public static final Pair readClassDataFrom(String[] data, String[] strings) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(strings, "strings");
        byte[] bArrDecodeBytes = BitEncoding.decodeBytes(data);
        Intrinsics.checkNotNullExpressionValue(bArrDecodeBytes, "decodeBytes(...)");
        return readClassDataFrom(bArrDecodeBytes, strings);
    }

    public static final Pair readClassDataFrom(byte[] bytes, String[] strings) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.checkNotNullParameter(strings, "strings");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new Pair(INSTANCE.readNameResolver(byteArrayInputStream, strings), ProtoBuf$Class.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    public static final Pair readPackageDataFrom(String[] data, String[] strings) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(strings, "strings");
        byte[] bArrDecodeBytes = BitEncoding.decodeBytes(data);
        Intrinsics.checkNotNullExpressionValue(bArrDecodeBytes, "decodeBytes(...)");
        return readPackageDataFrom(bArrDecodeBytes, strings);
    }

    public static final Pair readPackageDataFrom(byte[] bytes, String[] strings) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.checkNotNullParameter(strings, "strings");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new Pair(INSTANCE.readNameResolver(byteArrayInputStream, strings), ProtoBuf$Package.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    public static final Pair readFunctionDataFrom(String[] data, String[] strings) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(strings, "strings");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(BitEncoding.decodeBytes(data));
        return new Pair(INSTANCE.readNameResolver(byteArrayInputStream, strings), ProtoBuf$Function.parseFrom(byteArrayInputStream, EXTENSION_REGISTRY));
    }

    private final JvmNameResolver readNameResolver(InputStream inputStream, String[] strArr) {
        JvmProtoBuf.StringTableTypes delimitedFrom = JvmProtoBuf.StringTableTypes.parseDelimitedFrom(inputStream, EXTENSION_REGISTRY);
        Intrinsics.checkNotNullExpressionValue(delimitedFrom, "parseDelimitedFrom(...)");
        return new JvmNameResolver(delimitedFrom, strArr);
    }

    public final JvmMemberSignature.Method getJvmMethodSignature(ProtoBuf$Function proto, NameResolver nameResolver, TypeTable typeTable) {
        String string;
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        GeneratedMessageLite.GeneratedExtension methodSignature = JvmProtoBuf.methodSignature;
        Intrinsics.checkNotNullExpressionValue(methodSignature, "methodSignature");
        JvmProtoBuf.JvmMethodSignature jvmMethodSignature = (JvmProtoBuf.JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(proto, methodSignature);
        int name = (jvmMethodSignature == null || !jvmMethodSignature.hasName()) ? proto.getName() : jvmMethodSignature.getName();
        if (jvmMethodSignature == null || !jvmMethodSignature.hasDesc()) {
            List listListOfNotNull = CollectionsKt.listOfNotNull(ProtoTypeTableUtilKt.receiverType(proto, typeTable));
            List<ProtoBuf$ValueParameter> valueParameterList = proto.getValueParameterList();
            Intrinsics.checkNotNullExpressionValue(valueParameterList, "getValueParameterList(...)");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameterList, 10));
            for (ProtoBuf$ValueParameter protoBuf$ValueParameter : valueParameterList) {
                Intrinsics.checkNotNull(protoBuf$ValueParameter);
                arrayList.add(ProtoTypeTableUtilKt.type(protoBuf$ValueParameter, typeTable));
            }
            List listPlus = CollectionsKt.plus((Collection) listListOfNotNull, (Iterable) arrayList);
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
            Iterator it = listPlus.iterator();
            while (it.hasNext()) {
                String strMapTypeDefault = INSTANCE.mapTypeDefault((ProtoBuf$Type) it.next(), nameResolver);
                if (strMapTypeDefault == null) {
                    return null;
                }
                arrayList2.add(strMapTypeDefault);
            }
            String strMapTypeDefault2 = mapTypeDefault(ProtoTypeTableUtilKt.returnType(proto, typeTable), nameResolver);
            if (strMapTypeDefault2 == null) {
                return null;
            }
            string = CollectionsKt.joinToString$default(arrayList2, "", "(", ")", 0, null, null, 56, null) + strMapTypeDefault2;
        } else {
            string = nameResolver.getString(jvmMethodSignature.getDesc());
        }
        return new JvmMemberSignature.Method(nameResolver.getString(name), string);
    }

    public final JvmMemberSignature.Method getJvmConstructorSignature(ProtoBuf$Constructor proto, NameResolver nameResolver, TypeTable typeTable) {
        String string;
        String strJoinToString$default;
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        GeneratedMessageLite.GeneratedExtension constructorSignature = JvmProtoBuf.constructorSignature;
        Intrinsics.checkNotNullExpressionValue(constructorSignature, "constructorSignature");
        JvmProtoBuf.JvmMethodSignature jvmMethodSignature = (JvmProtoBuf.JvmMethodSignature) ProtoBufUtilKt.getExtensionOrNull(proto, constructorSignature);
        if (jvmMethodSignature != null && jvmMethodSignature.hasName()) {
            string = nameResolver.getString(jvmMethodSignature.getName());
        } else {
            string = "<init>";
        }
        if (jvmMethodSignature != null && jvmMethodSignature.hasDesc()) {
            strJoinToString$default = nameResolver.getString(jvmMethodSignature.getDesc());
        } else {
            List<ProtoBuf$ValueParameter> valueParameterList = proto.getValueParameterList();
            Intrinsics.checkNotNullExpressionValue(valueParameterList, "getValueParameterList(...)");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameterList, 10));
            for (ProtoBuf$ValueParameter protoBuf$ValueParameter : valueParameterList) {
                JvmProtoBufUtil jvmProtoBufUtil = INSTANCE;
                Intrinsics.checkNotNull(protoBuf$ValueParameter);
                String strMapTypeDefault = jvmProtoBufUtil.mapTypeDefault(ProtoTypeTableUtilKt.type(protoBuf$ValueParameter, typeTable), nameResolver);
                if (strMapTypeDefault == null) {
                    return null;
                }
                arrayList.add(strMapTypeDefault);
            }
            strJoinToString$default = CollectionsKt.joinToString$default(arrayList, "", "(", ")V", 0, null, null, 56, null);
        }
        return new JvmMemberSignature.Method(string, strJoinToString$default);
    }

    public static /* synthetic */ JvmMemberSignature.Field getJvmFieldSignature$default(JvmProtoBufUtil jvmProtoBufUtil, ProtoBuf$Property protoBuf$Property, NameResolver nameResolver, TypeTable typeTable, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        return jvmProtoBufUtil.getJvmFieldSignature(protoBuf$Property, nameResolver, typeTable, z);
    }

    public final JvmMemberSignature.Field getJvmFieldSignature(ProtoBuf$Property proto, NameResolver nameResolver, TypeTable typeTable, boolean z) {
        String strMapTypeDefault;
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        GeneratedMessageLite.GeneratedExtension propertySignature = JvmProtoBuf.propertySignature;
        Intrinsics.checkNotNullExpressionValue(propertySignature, "propertySignature");
        JvmProtoBuf.JvmPropertySignature jvmPropertySignature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(proto, propertySignature);
        if (jvmPropertySignature == null) {
            return null;
        }
        JvmProtoBuf.JvmFieldSignature field = jvmPropertySignature.hasField() ? jvmPropertySignature.getField() : null;
        if (field == null && z) {
            return null;
        }
        int name = (field == null || !field.hasName()) ? proto.getName() : field.getName();
        if (field == null || !field.hasDesc()) {
            strMapTypeDefault = mapTypeDefault(ProtoTypeTableUtilKt.returnType(proto, typeTable), nameResolver);
            if (strMapTypeDefault == null) {
                return null;
            }
        } else {
            strMapTypeDefault = nameResolver.getString(field.getDesc());
        }
        return new JvmMemberSignature.Field(nameResolver.getString(name), strMapTypeDefault);
    }

    private final String mapTypeDefault(ProtoBuf$Type protoBuf$Type, NameResolver nameResolver) {
        if (protoBuf$Type.hasClassName()) {
            return ClassMapperLite.mapClass(nameResolver.getQualifiedClassName(protoBuf$Type.getClassName()));
        }
        return null;
    }

    public static final boolean isMovedFromInterfaceCompanion(ProtoBuf$Property proto) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Flags.BooleanFlagField is_moved_from_interface_companion = JvmFlags.INSTANCE.getIS_MOVED_FROM_INTERFACE_COMPANION();
        Object extension = proto.getExtension(JvmProtoBuf.flags);
        Intrinsics.checkNotNullExpressionValue(extension, "getExtension(...)");
        Boolean bool = is_moved_from_interface_companion.get(((Number) extension).intValue());
        Intrinsics.checkNotNullExpressionValue(bool, "get(...)");
        return bool.booleanValue();
    }
}
