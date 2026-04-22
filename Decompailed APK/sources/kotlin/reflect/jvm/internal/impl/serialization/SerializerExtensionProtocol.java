package kotlin.reflect.jvm.internal.impl.serialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;

/* JADX INFO: compiled from: SerializerExtensionProtocol.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class SerializerExtensionProtocol {
    private final GeneratedMessageLite.GeneratedExtension classAnnotation;
    private final GeneratedMessageLite.GeneratedExtension compileTimeValue;
    private final GeneratedMessageLite.GeneratedExtension constructorAnnotation;
    private final GeneratedMessageLite.GeneratedExtension enumEntryAnnotation;
    private final ExtensionRegistryLite extensionRegistry;
    private final GeneratedMessageLite.GeneratedExtension functionAnnotation;
    private final GeneratedMessageLite.GeneratedExtension functionExtensionReceiverAnnotation;
    private final GeneratedMessageLite.GeneratedExtension packageFqName;
    private final GeneratedMessageLite.GeneratedExtension parameterAnnotation;
    private final GeneratedMessageLite.GeneratedExtension propertyAnnotation;
    private final GeneratedMessageLite.GeneratedExtension propertyBackingFieldAnnotation;
    private final GeneratedMessageLite.GeneratedExtension propertyDelegatedFieldAnnotation;
    private final GeneratedMessageLite.GeneratedExtension propertyExtensionReceiverAnnotation;
    private final GeneratedMessageLite.GeneratedExtension propertyGetterAnnotation;
    private final GeneratedMessageLite.GeneratedExtension propertySetterAnnotation;
    private final GeneratedMessageLite.GeneratedExtension typeAnnotation;
    private final GeneratedMessageLite.GeneratedExtension typeParameterAnnotation;

    public SerializerExtensionProtocol(ExtensionRegistryLite extensionRegistry, GeneratedMessageLite.GeneratedExtension packageFqName, GeneratedMessageLite.GeneratedExtension constructorAnnotation, GeneratedMessageLite.GeneratedExtension classAnnotation, GeneratedMessageLite.GeneratedExtension functionAnnotation, GeneratedMessageLite.GeneratedExtension generatedExtension, GeneratedMessageLite.GeneratedExtension propertyAnnotation, GeneratedMessageLite.GeneratedExtension propertyGetterAnnotation, GeneratedMessageLite.GeneratedExtension propertySetterAnnotation, GeneratedMessageLite.GeneratedExtension generatedExtension2, GeneratedMessageLite.GeneratedExtension generatedExtension3, GeneratedMessageLite.GeneratedExtension generatedExtension4, GeneratedMessageLite.GeneratedExtension enumEntryAnnotation, GeneratedMessageLite.GeneratedExtension compileTimeValue, GeneratedMessageLite.GeneratedExtension parameterAnnotation, GeneratedMessageLite.GeneratedExtension typeAnnotation, GeneratedMessageLite.GeneratedExtension typeParameterAnnotation) {
        Intrinsics.checkNotNullParameter(extensionRegistry, "extensionRegistry");
        Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
        Intrinsics.checkNotNullParameter(constructorAnnotation, "constructorAnnotation");
        Intrinsics.checkNotNullParameter(classAnnotation, "classAnnotation");
        Intrinsics.checkNotNullParameter(functionAnnotation, "functionAnnotation");
        Intrinsics.checkNotNullParameter(propertyAnnotation, "propertyAnnotation");
        Intrinsics.checkNotNullParameter(propertyGetterAnnotation, "propertyGetterAnnotation");
        Intrinsics.checkNotNullParameter(propertySetterAnnotation, "propertySetterAnnotation");
        Intrinsics.checkNotNullParameter(enumEntryAnnotation, "enumEntryAnnotation");
        Intrinsics.checkNotNullParameter(compileTimeValue, "compileTimeValue");
        Intrinsics.checkNotNullParameter(parameterAnnotation, "parameterAnnotation");
        Intrinsics.checkNotNullParameter(typeAnnotation, "typeAnnotation");
        Intrinsics.checkNotNullParameter(typeParameterAnnotation, "typeParameterAnnotation");
        this.extensionRegistry = extensionRegistry;
        this.packageFqName = packageFqName;
        this.constructorAnnotation = constructorAnnotation;
        this.classAnnotation = classAnnotation;
        this.functionAnnotation = functionAnnotation;
        this.functionExtensionReceiverAnnotation = generatedExtension;
        this.propertyAnnotation = propertyAnnotation;
        this.propertyGetterAnnotation = propertyGetterAnnotation;
        this.propertySetterAnnotation = propertySetterAnnotation;
        this.propertyExtensionReceiverAnnotation = generatedExtension2;
        this.propertyBackingFieldAnnotation = generatedExtension3;
        this.propertyDelegatedFieldAnnotation = generatedExtension4;
        this.enumEntryAnnotation = enumEntryAnnotation;
        this.compileTimeValue = compileTimeValue;
        this.parameterAnnotation = parameterAnnotation;
        this.typeAnnotation = typeAnnotation;
        this.typeParameterAnnotation = typeParameterAnnotation;
    }

    public final ExtensionRegistryLite getExtensionRegistry() {
        return this.extensionRegistry;
    }

    public final GeneratedMessageLite.GeneratedExtension getConstructorAnnotation() {
        return this.constructorAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getClassAnnotation() {
        return this.classAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getFunctionAnnotation() {
        return this.functionAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getFunctionExtensionReceiverAnnotation() {
        return this.functionExtensionReceiverAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getPropertyAnnotation() {
        return this.propertyAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getPropertyGetterAnnotation() {
        return this.propertyGetterAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getPropertySetterAnnotation() {
        return this.propertySetterAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getPropertyExtensionReceiverAnnotation() {
        return this.propertyExtensionReceiverAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getPropertyBackingFieldAnnotation() {
        return this.propertyBackingFieldAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getPropertyDelegatedFieldAnnotation() {
        return this.propertyDelegatedFieldAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getEnumEntryAnnotation() {
        return this.enumEntryAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getCompileTimeValue() {
        return this.compileTimeValue;
    }

    public final GeneratedMessageLite.GeneratedExtension getParameterAnnotation() {
        return this.parameterAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getTypeAnnotation() {
        return this.typeAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension getTypeParameterAnnotation() {
        return this.typeParameterAnnotation;
    }
}
