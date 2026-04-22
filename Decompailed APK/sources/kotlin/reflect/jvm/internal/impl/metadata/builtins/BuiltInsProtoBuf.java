package kotlin.reflect.jvm.internal.impl.metadata.builtins;

import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$ValueParameter;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.WireFormat;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BuiltInsProtoBuf {
    public static final GeneratedMessageLite.GeneratedExtension classAnnotation;
    public static final GeneratedMessageLite.GeneratedExtension compileTimeValue;
    public static final GeneratedMessageLite.GeneratedExtension constructorAnnotation;
    public static final GeneratedMessageLite.GeneratedExtension enumEntryAnnotation;
    public static final GeneratedMessageLite.GeneratedExtension functionAnnotation;
    public static final GeneratedMessageLite.GeneratedExtension packageFqName = GeneratedMessageLite.newSingularGeneratedExtension(ProtoBuf$Package.getDefaultInstance(), 0, null, null, 151, WireFormat.FieldType.INT32, Integer.class);
    public static final GeneratedMessageLite.GeneratedExtension parameterAnnotation;
    public static final GeneratedMessageLite.GeneratedExtension propertyAnnotation;
    public static final GeneratedMessageLite.GeneratedExtension propertyGetterAnnotation;
    public static final GeneratedMessageLite.GeneratedExtension propertySetterAnnotation;
    public static final GeneratedMessageLite.GeneratedExtension typeAnnotation;
    public static final GeneratedMessageLite.GeneratedExtension typeParameterAnnotation;

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add(packageFqName);
        extensionRegistryLite.add(classAnnotation);
        extensionRegistryLite.add(constructorAnnotation);
        extensionRegistryLite.add(functionAnnotation);
        extensionRegistryLite.add(propertyAnnotation);
        extensionRegistryLite.add(propertyGetterAnnotation);
        extensionRegistryLite.add(propertySetterAnnotation);
        extensionRegistryLite.add(compileTimeValue);
        extensionRegistryLite.add(enumEntryAnnotation);
        extensionRegistryLite.add(parameterAnnotation);
        extensionRegistryLite.add(typeAnnotation);
        extensionRegistryLite.add(typeParameterAnnotation);
    }

    static {
        ProtoBuf$Class defaultInstance = ProtoBuf$Class.getDefaultInstance();
        ProtoBuf$Annotation defaultInstance2 = ProtoBuf$Annotation.getDefaultInstance();
        WireFormat.FieldType fieldType = WireFormat.FieldType.MESSAGE;
        classAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(defaultInstance, defaultInstance2, null, 150, fieldType, false, ProtoBuf$Annotation.class);
        constructorAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(ProtoBuf$Constructor.getDefaultInstance(), ProtoBuf$Annotation.getDefaultInstance(), null, 150, fieldType, false, ProtoBuf$Annotation.class);
        functionAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(ProtoBuf$Function.getDefaultInstance(), ProtoBuf$Annotation.getDefaultInstance(), null, 150, fieldType, false, ProtoBuf$Annotation.class);
        propertyAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(ProtoBuf$Property.getDefaultInstance(), ProtoBuf$Annotation.getDefaultInstance(), null, 150, fieldType, false, ProtoBuf$Annotation.class);
        propertyGetterAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(ProtoBuf$Property.getDefaultInstance(), ProtoBuf$Annotation.getDefaultInstance(), null, 152, fieldType, false, ProtoBuf$Annotation.class);
        propertySetterAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(ProtoBuf$Property.getDefaultInstance(), ProtoBuf$Annotation.getDefaultInstance(), null, 153, fieldType, false, ProtoBuf$Annotation.class);
        compileTimeValue = GeneratedMessageLite.newSingularGeneratedExtension(ProtoBuf$Property.getDefaultInstance(), ProtoBuf$Annotation.Argument.Value.getDefaultInstance(), ProtoBuf$Annotation.Argument.Value.getDefaultInstance(), null, 151, fieldType, ProtoBuf$Annotation.Argument.Value.class);
        enumEntryAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(ProtoBuf$EnumEntry.getDefaultInstance(), ProtoBuf$Annotation.getDefaultInstance(), null, 150, fieldType, false, ProtoBuf$Annotation.class);
        parameterAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(ProtoBuf$ValueParameter.getDefaultInstance(), ProtoBuf$Annotation.getDefaultInstance(), null, 150, fieldType, false, ProtoBuf$Annotation.class);
        typeAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(ProtoBuf$Type.getDefaultInstance(), ProtoBuf$Annotation.getDefaultInstance(), null, 150, fieldType, false, ProtoBuf$Annotation.class);
        typeParameterAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(ProtoBuf$TypeParameter.getDefaultInstance(), ProtoBuf$Annotation.getDefaultInstance(), null, 150, fieldType, false, ProtoBuf$Annotation.class);
    }
}
