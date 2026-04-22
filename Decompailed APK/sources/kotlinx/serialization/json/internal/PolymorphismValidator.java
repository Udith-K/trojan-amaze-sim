package kotlinx.serialization.json.internal;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.modules.SerializersModuleCollector;

/* JADX INFO: compiled from: PolymorphismValidator.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class PolymorphismValidator implements SerializersModuleCollector {
    private final String discriminator;
    private final boolean useArrayPolymorphism;

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public void polymorphicDefaultDeserializer(KClass baseClass, Function1 defaultDeserializerProvider) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(defaultDeserializerProvider, "defaultDeserializerProvider");
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public void polymorphicDefaultSerializer(KClass baseClass, Function1 defaultSerializerProvider) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(defaultSerializerProvider, "defaultSerializerProvider");
    }

    public PolymorphismValidator(boolean z, String discriminator) {
        Intrinsics.checkNotNullParameter(discriminator, "discriminator");
        this.useArrayPolymorphism = z;
        this.discriminator = discriminator;
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public void polymorphic(KClass baseClass, KClass actualClass, KSerializer actualSerializer) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(actualClass, "actualClass");
        Intrinsics.checkNotNullParameter(actualSerializer, "actualSerializer");
        SerialDescriptor descriptor = actualSerializer.getDescriptor();
        checkKind(descriptor, actualClass);
        if (this.useArrayPolymorphism) {
            return;
        }
        checkDiscriminatorCollisions(descriptor, actualClass);
    }

    private final void checkKind(SerialDescriptor serialDescriptor, KClass kClass) {
        SerialKind kind = serialDescriptor.getKind();
        if ((kind instanceof PolymorphicKind) || Intrinsics.areEqual(kind, SerialKind.CONTEXTUAL.INSTANCE)) {
            throw new IllegalArgumentException("Serializer for " + kClass.getSimpleName() + " can't be registered as a subclass for polymorphic serialization because its kind " + kind + " is not concrete. To work with multiple hierarchies, register it as a base class.");
        }
        if (this.useArrayPolymorphism) {
            return;
        }
        if (Intrinsics.areEqual(kind, StructureKind.LIST.INSTANCE) || Intrinsics.areEqual(kind, StructureKind.MAP.INSTANCE) || (kind instanceof PrimitiveKind) || (kind instanceof SerialKind.ENUM)) {
            throw new IllegalArgumentException("Serializer for " + kClass.getSimpleName() + " of kind " + kind + " cannot be serialized polymorphically with class discriminator.");
        }
    }

    private final void checkDiscriminatorCollisions(SerialDescriptor serialDescriptor, KClass kClass) {
        int elementsCount = serialDescriptor.getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            String elementName = serialDescriptor.getElementName(i);
            if (Intrinsics.areEqual(elementName, this.discriminator)) {
                throw new IllegalArgumentException("Polymorphic serializer for " + kClass + " has property '" + elementName + "' that conflicts with JSON class discriminator. You can either change class discriminator in JsonConfiguration, rename property with @SerialName annotation or fall back to array polymorphism");
            }
        }
    }
}
